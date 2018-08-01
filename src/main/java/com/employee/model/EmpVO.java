package com.employee.model;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class EmpVO implements Serializable {
	private String emp_id;
	private String emp_pwd;
	private String emp_name;
	private String emp_sex;
	private String emp_idnum;
	private String emp_addr;
	private String emp_mail;
	private String emp_phone;
	private Date emp_bday;
	private Date emp_reg;
	private Date emp_due;
	private String picture;
	private String key_id;
	private Date key_date;
	private String pass_code;
	
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getEmp_pwd() {
		return emp_pwd;
	}
	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
	public String getEmp_sex() {
		return emp_sex;
	}
	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}
	public String getEmp_idnum() {
		return emp_idnum;
	}
	public void setEmp_idnum(String emp_idnum) {
		this.emp_idnum = emp_idnum;
	}
	public String getEmp_addr() {
		return emp_addr;
	}
	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}
	public String getEmp_mail() {
		return emp_mail;
	}
	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public Date getEmp_bday() {
		return emp_bday;
	}
	public void setEmp_bday(Date emp_bday) {
		this.emp_bday = emp_bday;
	}
	public Date getEmp_reg() {
		return emp_reg;
	}
	public void setEmp_reg(Date emp_reg) {
		this.emp_reg = emp_reg;
	}
	public Date getEmp_due() {
		return emp_due;
	}
	public void setEmp_due(Date emp_due) {
		this.emp_due = emp_due;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getKey_id() {
		return key_id;
	}
	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}
	public Date getKey_date() {
		return key_date;
	}
	public void setKey_date(Date key_date) {
		this.key_date = key_date;
	}
	public String getPass_code() {
		return pass_code;
	}
	public void setPass_code(String pass_code) {
		this.pass_code = pass_code;
	}
	

}
