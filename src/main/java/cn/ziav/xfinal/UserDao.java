package cn.ziav.xfinal;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
	User findUserById(long id);
}