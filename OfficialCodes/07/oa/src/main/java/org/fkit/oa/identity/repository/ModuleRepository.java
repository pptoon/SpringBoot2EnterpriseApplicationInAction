package org.fkit.oa.identity.repository;

import java.util.List;

import org.fkit.oa.identity.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface ModuleRepository extends JpaRepository<Module, String> , JpaSpecificationExecutor<Module>{

	@Modifying
	@Query("delete Module m where m.code like ?1")
	public void setCode(String code) ;
    
	@Query("select m from Module m where m.code like :parentCode and length(m.code) = :sonCodeLen")
	public List<Module> findModules(@Param("parentCode")String parentCode, @Param("sonCodeLen")int sonCodeLen);
	
	@Query("select Max(m.code) from Module m where m.code like :parentCode and  length(m.code) = :sonCodeLen ")
	public String findUniqueEntity(@Param("parentCode")String parentCode, @Param("sonCodeLen")int sonCodeLen);
	
	
}







