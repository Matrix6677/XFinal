package cn.ziav.xfinal.common.data;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.google.common.base.Preconditions;

/**
 * 数据访问服务注解{@link DataAccess} 处理器
 * @author Z.avi
 */
@Component
public class DataAccessAnnotationBeanProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConfigurableListableBeanFactory beanFactory;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new DataAccessFieldCallback(bean, beanFactory));
		return super.postProcessAfterInitialization(bean, beanName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	class DataAccessFieldCallback implements FieldCallback {
		private static final String BEAN_SEPARATOR = "$";

		private Object bean;
		private ConfigurableListableBeanFactory beanFactory;

		public DataAccessFieldCallback(Object bean, ConfigurableListableBeanFactory beanFactory) {
			this.bean = bean;
			this.beanFactory = beanFactory;
		}

		@Override
		public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
			if (!field.isAnnotationPresent(DataAccess.class)) {
				return;
			}
			if (!EntityService.class.isAssignableFrom(field.getType())) {
				return;
			}

			ParameterizedType genericType = (ParameterizedType) field.getGenericType();
			Type entityType = genericType.getActualTypeArguments()[1];
			Class<? extends IEntity> entityClz = (Class<? extends IEntity>) entityType;
			String beanName = field.getType().getSimpleName() + BEAN_SEPARATOR + entityClz.getSimpleName();
			Preconditions.checkArgument(entityClz.isAnnotationPresent(Entity.class), "注入字段[%s]的类型声明必须为Entity注解标注", field.getName());

			Object newInstance = null;
			// 判断是否已初始化
			if (beanFactory.containsBean(beanName)) {
				newInstance = beanFactory.getBean(beanName);
			} else {
				newInstance = injectEntityService(beanName, entityClz);
			}

			ReflectionUtils.makeAccessible(field);
			field.set(bean, newInstance);
		}

		private Object injectEntityService(String beanName, Class<? extends IEntity> entityClz) {
			// 判断是否含有Id注解的字段
			Preconditions.checkArgument(hasIdField(entityClz), "实体[%s]不存在Id注解字段", entityClz.getSimpleName());

			EntityServiceImpl newInstance = new EntityServiceImpl<>(entityManager, entityClz);
			Object initializeBean = beanFactory.initializeBean(newInstance, beanName);

			beanFactory.autowireBeanProperties(initializeBean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
			beanFactory.registerSingleton(beanName, initializeBean);
			logger.info("Bean named '{}' created successfully.", beanName);
			return initializeBean;
		}

		private boolean hasIdField(Class<? extends IEntity> entityClz) {
			Field[] fields = entityClz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
					return true;
				}
			}
			return false;
		}
	}

}