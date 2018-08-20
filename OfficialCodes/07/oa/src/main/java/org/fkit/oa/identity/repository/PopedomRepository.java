package org.fkit.oa.identity.repository;

import java.util.List;

import org.fkit.oa.identity.domain.Popedom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月14日上午9:44:47
 * @From http://www.fkjava.org 
 *
 */
public interface PopedomRepository extends JpaRepository<Popedom, Long>{
	
	@Query("select p.opera.code from Popedom p where p.role.id = :id and p.module.code = :parentCode")
	public List<String> findByIdAndParentCode(@Param("id")long id, @Param("parentCode")String parentCode);
    
	@Modifying
	@Query("delete Popedom p where p.role.id = :id and p.module.code = :parentCode")
	public void setByIdAndParentCode(@Param("id")long id, @Param("parentCode")String parentCode);
	
	@Query("select distinct p.module.code from Popedom p where "
			+ "p.role.id in(select r.id from Role r "
			+ "inner join r.users u where u.userId = ?1 ) "
			+ "order by p.module.code asc")
	public List<String> getUserPopedomModuleCodes(String userId);

	@Query("select distinct p.opera.code from Popedom p "
			+ "where p.role.id in(select r.id from Role r "
			+ "inner join r.users u where u.userId = ?1 ) order by p.opera.code asc")
	public List<String> getUserPopedomOperasCodes(String userId);
	
	
}





