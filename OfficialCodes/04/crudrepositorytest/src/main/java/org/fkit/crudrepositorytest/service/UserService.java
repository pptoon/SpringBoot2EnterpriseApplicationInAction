package org.fkit.crudrepositorytest.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.fkit.crudrepositorytest.bean.User;
import org.fkit.crudrepositorytest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	// 注入UserRepository
	@Resource
	private UserRepository userRepository;

	/**
	 * save,update ,delete 方法需要绑定事务. 使用@Transactional进行事务的绑定.
	 * 
	 * 保存对象
	 * @param User
	 * @return 包含自动生成的id的User对象        
	 */
	@Transactional
	public User save(User User) {
		return userRepository.save(User);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		userRepository.deleteById(id);
		
	}

	/**
	 * 查询所有数据
	 * 
	 * @return 返回所有User对象
	 */
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}
	
	/**
	 * 根据id查询数据
	 * 
	 * @return  返回id对应的User对象
	 */
	public User getById(Integer id) {
		// 根据id查询出对应的持久化对象
		Optional<User> op = userRepository.findById(id);
		 return op.get();
	}
	/**
	 * 修改用户对象数据，持久化对象修改会自动更新到数据库
	 * 
	 * @param user
	 */
	@Transactional
	public void update(User user) {
		// 直接调用持久化对象的set方法修改对象的数据
		user.setUsername("孙悟空");
		user.setLoginName("swk");
	}

}
