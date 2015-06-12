package com.uttara.lpsq;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="STUDENT")
@Table(name="Students")
public class StudentBean 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SL_NO")
	private int id;
	
	@Column(name="NAME")
	private String name;
	@Column(name="CITY")
	private String city;
	@Column(name="DOB")
	private String dob;
	@Column(name="INTRESTED")
	private String intrested;
	@Column(name="EMAIL",unique=true)
	private String email;
	@Column(name="PASS")
	private String pass;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="STUDENT_QUERIES_SL_NO")
	private List<Queries> queries=new ArrayList<Queries>();	
	
	
	public List<Queries> getQueries() {
		return queries;
	}
	public void setQueries(List<Queries> queries) {
		this.queries = queries;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getIntrested() {
		return intrested;
	}
	public void setIntrested(String intrested) {
		this.intrested = intrested;
	}
	public String getEmail() {
		return email;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((intrested == null) ? 0 : intrested.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentBean other = (StudentBean) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
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
		if (intrested == null) {
			if (other.intrested != null)
				return false;
		} else if (!intrested.equals(other.intrested))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StudentBean [id=" + id + ", name=" + name + ", city=" + city
				+ ", dob=" + dob + ", intrested=" + intrested + ", email="
				+ email + ", pass=" + pass + "]";
	}
	
	
	
	
	
}
