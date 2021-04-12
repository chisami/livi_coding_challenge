package com.livi.coding.challenge.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.livi.coding.challenge.demo.domain.EmployeeNumber;


/**
 * The Interface EmployeeNumberRepo.
 */
@Repository
public interface EmployeeNumberRepo extends CrudRepository<EmployeeNumber, Integer> {

	/**
	 * Employee no score.
	 *
	 * @param employeeNo the employee no
	 * @return the optional
	 */
	@Transactional(readOnly = true)
	@Query("select score from EmployeeNumber where employeeNoFrom <= :employeeNo and employeeNoTo >= :employeeNo")
	public Optional<Long> employeeNoScore(@Param("employeeNo") Integer employeeNo);

}
