package com.livi.coding.challenge.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * Instantiates a new company type.
 */
@Data
@Table(name = "li_t_company_types")
@Entity
public class CompanyType {

	/** The id. */
	@Id
    @GeneratedValue
    private Integer id;
    
	/** The company type. */
	@Column(name="company_type")
    private String companyType;
	
	/** The score. */
	@Column(name="score")
    private Long score;
    
}


