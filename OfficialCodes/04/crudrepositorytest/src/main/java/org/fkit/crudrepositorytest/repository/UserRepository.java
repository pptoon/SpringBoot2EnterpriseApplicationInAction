package org.fkit.crudrepositorytest.repository;

import org.fkit.crudrepositorytest.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

}
