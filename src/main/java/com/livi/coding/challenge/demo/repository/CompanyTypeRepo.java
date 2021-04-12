package com.livi.coding.challenge.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.livi.coding.challenge.demo.domain.CompanyType;


/**
 * The Interface CompanyTypeRepo.
 */
@Repository
public interface CompanyTypeRepo extends CrudRepository<CompanyType, Integer> {

	/**
	 * Company type score.
	 *
	 * @param companyType the company type
	 * @return the optional
	 */
	@Transactional(readOnly = true)
	@Query("select score from CompanyType where companyType = :companyType")
	public Optional<Long> companyTypeScore(@Param("companyType") String companyType);

}
