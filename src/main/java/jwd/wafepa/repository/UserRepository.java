package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository 
	extends PagingAndSortingRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.email LIKE CONCAT('%',:email,'%')")
	Page<User> findByEmailContaining(@Param("email") String email, Pageable pageRequest);
	
	@Query("SELECT u FROM User u WHERE u.firstName = :firstName")
	Page<User> findByFirstName(@Param("firstName") String firstName, Pageable pageRequest);
	
	@Query("SELECT u FROM User u WHERE u.lastName = :lastName")
	Page<User> findByLastName(@Param("lastName") String lastName, Pageable pageRequest);
	
	@Query("SELECT u FROM User u WHERE u.firstName LIKE CONCAT('%',:name,'%') OR "
			+ "u.lastName LIKE CONCAT('%',:name,'%')")
	Page<User> findByFirstOrLastNameContaining(
			@Param("name") String name, 
			Pageable pageRequest);
	
}
