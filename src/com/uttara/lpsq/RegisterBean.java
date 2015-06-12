package com.uttara.lpsq;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "REGISTERS")
public class RegisterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterBean() {
		System.out.println("inside of Register bean Constructor");
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SL_NO")
	private int sl_no;
	

	@Column(name = "FIRSTNAME")
	@NotEmpty(message = "First Name")
	private String fname;
	
	@Column(name = "LASTNAME")
	@NotEmpty(message = "Last Name")
	private String lname;
	
	@Column(name = "DOB")
	@NotEmpty(message = "Date Empty or Feature Date is not Excepted")
	private String dob;
	
	@Column(name = "ADDRESS")
	@NotEmpty(message = "Address")
	private String address;
	
	@Column(name = "TRAINING")
	@NotEmpty(message = "Training")
	private String training;
	
	@Column(name = "COMPANY")
	@NotEmpty(message = "Company")
	private String company;
	
	@Column(name = "EMAIL")
	@NotEmpty(message = "Enter the proper Email")
	@Email
	private String email;
	
	@Column(name = "PASS")
	@Length(min = 2, max = 20, message = "Password")
	private String pass;
	
	@Column(name = "RPASS")
	@Length(min = 2, max = 20, message = "Repeat password")
	private String rpass;
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getEmail() {
		return email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		System.out.println("inside of Setter of DOB and date is " + dob);
		if (dob.length() < 10) {
			throw new IllegalArgumentException("Kindly Enter Proper Date");
		} else {
			String compDate = dob.substring(8) + "/" + dob.substring(5, 7)
					+ "/" + dob.substring(0, 4);
			String res = DateHelper.DateChecker(compDate);
			if (res.equals(Constants.SUCCESS))
				this.dob = compDate;
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRpass() {
		return rpass;
	}

	public void setRpass(String rpass) {
		if (rpass.equals(pass))
			this.rpass = rpass;
	}

	

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((rpass == null) ? 0 : rpass.hashCode());
		result = prime * result
				+ ((training == null) ? 0 : training.hashCode());
		return result;
	}
	
	

	public int getId() {
		return sl_no;
	}

	public void setId(int id) {
		this.sl_no		= id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterBean other = (RegisterBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (rpass == null) {
			if (other.rpass != null)
				return false;
		} else if (!rpass.equals(other.rpass))
			return false;
		if (training == null) {
			if (other.training != null)
				return false;
		} else if (!training.equals(other.training))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegisterBean [fname=" + fname + ", lname=" + lname + ", dob="
				+ dob + ", address=" + address + ", training=" + training
				+ ", company=" + company + ", email=" + email + ", pass="
				+ pass + ", rpass=" + rpass + "]";
	}
}