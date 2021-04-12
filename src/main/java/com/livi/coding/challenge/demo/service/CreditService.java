package com.livi.coding.challenge.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.livi.coding.challenge.demo.domain.Credit;
import com.livi.coding.challenge.demo.payload.CreditPayload;
import com.livi.coding.challenge.demo.repository.CompanyTypeRepo;
import com.livi.coding.challenge.demo.repository.EmployeeNumberRepo;
import com.livi.coding.challenge.demo.repository.YearNumberRepo;


/**
 * The Class CreditService.
 */
@Service
@Transactional
public class CreditService {

	/** The employee number repo. */
	private EmployeeNumberRepo employeeNumberRepo;
	
	/** The company type repo. */
	private CompanyTypeRepo companyTypeRepo;
	
	/** The year number repo. */
	private YearNumberRepo yearNumberRepo;

	/**
	 * Instantiates a new credit service.
	 *
	 * @param employeeNumberRepo the employee number repo
	 * @param companyTypeRepo the company type repo
	 * @param yearNumberRepo the year number repo
	 */
	@Autowired
	public CreditService(EmployeeNumberRepo employeeNumberRepo, CompanyTypeRepo companyTypeRepo,
			YearNumberRepo yearNumberRepo) {
		this.employeeNumberRepo = employeeNumberRepo;
		this.companyTypeRepo = companyTypeRepo;
		this.yearNumberRepo = yearNumberRepo;
	}

	/**
	 * Retrieve credit score.
	 *
	 * @param credit the credit
	 * @return the credit payload
	 */
	public CreditPayload retrieveCreditScore(Credit credit) {

		Long creditScore = employeeNumberRepo.employeeNoScore(credit.getNumberOfEmployees()).orElse(0l)
				+ companyTypeRepo.companyTypeScore(credit.getCompanyType()).orElse(0l)
				+ yearNumberRepo.yearNoScore(credit.getNumberOfYearsOperated()).orElse(0l);

		return CreditPayload.builder().creditScore(creditScore).build();

	}

}
