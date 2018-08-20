package org.fkit.oa.identity.repository;

import java.util.List;
import java.util.Map;

import org.fkit.oa.identity.domain.Job;
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
public interface JobRepository extends JpaRepository<Job, String>{
    
	@Query("select new Map(j.code as code ,j.name as name) from Job j")
	public List<Map<String, Object>> findJobs() throws Exception ;
	
}







