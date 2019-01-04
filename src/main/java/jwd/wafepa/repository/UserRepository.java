package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository 
	extends JpaRepository<User, Long>{
	
	//TODO popraviti
	//@Query("SELECT u FROM User u WHERE u.email LIKE ?1")
	List<User> findByEmailContaining(String email);
	
	@Query("SELECT u FROM User u WHERE u.firstName = :firstName")
	List<User> findByFirstName(@Param("firstName") String firstName);
	
	@Query("SELECT u FROM User u WHERE u.lastName = :lastName")
	List<User> findByLastName(@Param("lastName") String lastName);
	
}
