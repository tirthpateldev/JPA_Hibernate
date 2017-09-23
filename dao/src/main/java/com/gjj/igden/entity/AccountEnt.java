package com.gjj.igden.entity;

import javax.persistence.*;


@Entity
@Table(name = "Account")
public class AccountEnt {
	@Id@GeneratedValue 
	public int account_Id;
	@Column(columnDefinition = "VARCHAR(45)")
	public String account_name;
	@Column(columnDefinition = "VARCHAR(45)",unique = true)
	public String email;
	@Column(columnDefinition = "VARCHAR(450)")
	public String additional_info;
	@Column(columnDefinition = "VARCHAR(128)")
	public String password;
	@Column(columnDefinition = "VARCHAR(45)")
	public String creation_date;
	public int getAccount_Id() {
		return account_Id;
	}
	public void setAccount_Id(int account_Id) {
		this.account_Id = account_Id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	
	
	
	
}
