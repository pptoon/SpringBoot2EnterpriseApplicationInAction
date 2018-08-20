package org.fkit.oa.identity.repository;

import org.fkit.oa.identity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月14日上午9:44:47
 * @From http://www.fkjava.org 
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> , JpaSpecificationExecutor<Role>{

}







