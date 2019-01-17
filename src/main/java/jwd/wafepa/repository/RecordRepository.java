package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Record;

@Repository
public interface RecordRepository
	extends JpaRepository<Record, Long>{

	Page<Record> findByUserId(Long id, Pageable page);
	
	Page<Record> findByActivityId(Long id, Pageable page);
	
	@Query("SELECT r FROM Record r WHERE "
			+ "(:activityName IS null OR r.activity.name LIKE :activityName) AND "
			+ "(:minDuration IS null OR r.duration >= :minDuration) AND "
			+ "(:intensity IS null OR r.intensity LIKE :intensity)")
	Page<Record> search(
			@Param("activityName") String name,
			@Param("minDuration") Integer minDuration,
			@Param("intensity") String intensity,
			Pageable pageRequest);
}
