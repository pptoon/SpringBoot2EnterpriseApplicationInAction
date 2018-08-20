package org.fkit.oa.identity.repository;

import java.util.List;

import org.fkit.oa.identity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月14日上午9:44:47
 * @From http://www.fkjava.org 
 *
 */
public interface UserRepository extends JpaRepository<User, String> , JpaSpecificationExecutor<User>{
    
	@Query("select u.userId from User u where u.userId not in(select u.userId from User u inner join u.roles r where r.id = ?1)")
	List<String> getRolesUsers(Long id);
    
	@Query("select u.userId from User u inner join u.roles r where r.id = ?1")
	List<String> findRoleUsers(Long id);

}







