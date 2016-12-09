package cn.ziav.xfinal.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Entity;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class DataAccessAnnotationBeanProcessor extends InstantiationAwareBeanPostProcessorAdapter {
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {

			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				if (!field.isAnnotationPresent(DataAccess.class)) {
					return;
				}
				if (field.getType().isAssignableFrom(EntityCacheService.class)) {
					injectEntityCacheService(bean, beanName, field);
				}
			}

		});
		return super.postProcessAfterInitialization(bean, beanName);
	}

	private void injectEntityCacheService(Object bean, String beanName, Field field) {
		ParameterizedType genericType = (ParameterizedType) field.getGenericType();
		Class<? extends Type> entityClz = genericType.getActualTypeArguments()[0].getClass();
		if (!entityClz.isAnnotationPresent(Entity.class)) {
			throw new IllegalArgumentException("注入字段[]的类型声明必须为Entity注解标注");
		}
		Class<?> type = field.getType();
		try {
			Constructor<?> ctr = type.getConstructor(StringRedisTemplate.class, Class.class);
			Object newInstance = ctr.newInstance(redisTemplate, entityClz);
			field.set(bean, newInstance);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}