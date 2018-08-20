package org.fkit.pagingandsortingrepositorytest.repository;

import org.fkit.pagingandsortingrepositorytest.bean.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

}
