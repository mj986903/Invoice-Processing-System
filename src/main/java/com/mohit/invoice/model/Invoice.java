package com.mohit.invoice.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Invoice{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String vendor;
	private String product;
	private long amount;
	private Date date;
	private String status;
	
	public Invoice(){
		this.status="pending";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", vendor=" + vendor + ", product=" + product + ", amount=" + amount + ", date="
				+ date + ", status=" + status + "]";
	}
	
}
