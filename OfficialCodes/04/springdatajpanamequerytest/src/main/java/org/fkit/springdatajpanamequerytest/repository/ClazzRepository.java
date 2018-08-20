package org.fkit.springdatajpanamequerytest.repository;

import org.fkit.springdatajpanamequerytest.bean.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author dlei(徐磊)
 * @Email dlei0009@163.com
 */
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
	
}
