package com.livi.coding.challenge.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * Instantiates a new employee number.
 */
@Data
@Table(name = "li_t_employee_scores")
@Entity
public class EmployeeNumber {

	/** The id. */
	@Id
    @GeneratedValue
    private Integer id;
    
	/** The employee no from. */
	@Column(name="employee_no_from")
    private Integer employeeNoFrom;
	
	/** The employee no to. */
	@Column(name="employee_no_to")
    private Integer employeeNoTo;
	
	/** The score. */
	@Column(name="score")
    private Long score;
    
}


