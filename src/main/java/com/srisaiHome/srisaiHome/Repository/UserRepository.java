package com.srisaiHome.srisaiHome.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.srisaiHome.srisaiHome.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.username=:n AND u.password=:p")
	public List<User> findByName(@Param("n") String name,@Param("p") String password);
	
	@Query("select u from User u where u.username=:n")
	public User getUserbyUserName(@Param("n") String email);

}
