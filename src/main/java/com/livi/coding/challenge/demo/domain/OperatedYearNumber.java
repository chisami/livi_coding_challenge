package com.livi.coding.challenge.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * Instantiates a new operated year number.
 */
@Data
@Table(name = "li_t_operate_years")
@Entity
public class OperatedYearNumber {

	/** The id. */
	@Id
	@GeneratedValue
	private Integer id;

	/** The year no from. */
	@Column(name = "year_no_from")
	private Integer yearNoFrom;
	
	/** The year no to. */
	@Column(name = "year_no_to")
	private Integer yearNoTo;
	
	/** The score. */
	@Column(name = "score")
	private Long score;

}
