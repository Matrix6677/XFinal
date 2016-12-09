package cn.ziav.xfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.ziav.xfinal.common.DataAccess;
import cn.ziav.xfinal.common.EntityCacheService;

@RestController
@RequestMapping("/hello")
public class HelloController {
	@DataAccess
	private EntityCacheService<User> cacheService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping("/index/{param}")
	public void index(@PathVariable String param) {
		System.err.println(param);
	}

	@ResponseBody
	@RequestMapping("/create/{name}")
	public User createUser(@PathVariable String name) {
		User user = User.valueOf(name);
		return userDao.save(user);
	}

	@ResponseBody
	@RequestMapping("/find/{id}")
	public User findUser(@PathVariable long id) {
		BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps("users");
		String idStr = String.valueOf(id);
		if (boundHashOps.hasKey(idStr)) {
			Object object = boundHashOps.get(idStr);
			return (User) object;
		}
		User user = userDao.findOne(id);
		boundHashOps.put(idStr, user);
		return user;
	}
}