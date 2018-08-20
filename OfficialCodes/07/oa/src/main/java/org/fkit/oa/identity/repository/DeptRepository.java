package org.fkit.oa.identity.repository;

import java.util.List;
import java.util.Map;

import org.fkit.oa.identity.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface DeptRepository extends JpaRepository<Dept, Long>{
	
	@Query("select new Map(p.id as code , p.name as name) from Dept p")
	public List<Map<String, Object>> findDepts();
	
}







