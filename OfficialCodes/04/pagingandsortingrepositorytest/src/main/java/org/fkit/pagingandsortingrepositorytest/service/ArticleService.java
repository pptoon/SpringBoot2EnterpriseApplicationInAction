package org.fkit.pagingandsortingrepositorytest.service;

import javax.annotation.Resource;

import org.fkit.pagingandsortingrepositorytest.bean.Article;
import org.fkit.pagingandsortingrepositorytest.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	
	// 注入数据访问层接口对象 
	@Resource
	private ArticleRepository articleRepository;
	
	public Iterable<Article> findAllSort(Sort sort) {
		return articleRepository.findAll(sort);
	}

	public Page<Article> findAll(Pageable page) {
		return articleRepository.findAll(page);
	}

}
