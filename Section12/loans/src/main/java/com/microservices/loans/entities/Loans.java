package com.microservices.loans.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Loans extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

//	public Loans() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

//	public Loans(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
//		super(createdAt, createdBy, updatedAt, updatedBy);
//		// TODO Auto-generated constructor stub
//	}
//
//	public Loans(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, Long loanId,
//			String mobileNumber, String loanNumber, String loanType, int totalLoan, int amountPaid,
//			int outstandingAmount) {
//		super(createdAt, createdBy, updatedAt, updatedBy);
//		this.loanId = loanId;
//		this.mobileNumber = mobileNumber;
//		this.loanNumber = loanNumber;
//		this.loanType = loanType;
//		this.totalLoan = totalLoan;
//		this.amountPaid = amountPaid;
//		this.outstandingAmount = outstandingAmount;
//	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	private String mobileNumber;

	private String loanNumber;

	private String loanType;

	private int totalLoan;

	private int amountPaid;

	private int outstandingAmount;
	
}