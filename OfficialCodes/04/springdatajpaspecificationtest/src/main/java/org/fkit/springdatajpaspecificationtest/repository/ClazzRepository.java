package org.fkit.springdatajpaspecificationtest.repository;
import org.fkit.springdatajpaspecificationtest.bean.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @Author dlei(徐磊)
 * @Email dlei0009@163.com
 */
public interface ClazzRepository extends JpaRepository<Clazz, Integer> ,JpaSpecificationExecutor<Clazz>{
	
}
