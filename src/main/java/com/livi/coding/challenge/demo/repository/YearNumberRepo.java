package com.livi.coding.challenge.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.livi.coding.challenge.demo.domain.OperatedYearNumber;


/**
 * The Interface YearNumberRepo.
 */
@Repository
public interface YearNumberRepo extends CrudRepository<OperatedYearNumber, Integer> {

	/**
	 * Year no score.
	 *
	 * @param yearNo the year no
	 * @return the optional
	 */
	@Transactional(readOnly = true)
	@Query("select score from OperatedYearNumber where yearNoFrom <= :yearNo and yearNoTo >= :yearNo")
	public Optional<Long> yearNoScore(@Param("yearNo") Integer yearNo);

}
