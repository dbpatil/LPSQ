package com.uttara.lpsq;

import java.io.Serializable;
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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "RESPONDERS")
@Entity(name = "RESPONDER")
public class ResponderBean implements Serializable {

	public ResponderBean() {
		System.out.println("INSIDE OF NO-ARG CONSTRUTOR RESPONDERS");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SL_NO")
	private int id;

	@Column(name = "FIRSTNAME")
	@NotEmpty(message = "First Name")
	private String fname;

	@Column(name = "LASTNAME")
	@NotEmpty(message = "Last Name")
	private String lname;

	@Column(name = "DOB")
	@NotEmpty(message = "Date Empty or Feature Date is not Excepted")
	private String dob;

	@Column(name = "TOPIC")
	@NotEmpty(message = "Topic")
	private String topic;

	@Column(name = "STATUS")
	private String isActive;

	@Column(name = "EMAIL")
	@NotEmpty(message = "Enter the proper Email")
	@Email
	private String email;

	@Column(name = "PASS")
	@Length(min = 2, max = 20, message = "Password")
	private String pass;

	@Column(name = "USER_SL_NO")
	private int user_sl_no;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="RESPONDER_QUERIES_SL_NO")
	private List<Queries> queries;	
	
	public List<Queries> getQueries() {
		return queries;
	}

	public void setQueries(List<Queries> queries) {
		this.queries = queries;
	}

	public int getUser_sl_no() {
		return user_sl_no;
	}

	public void setUser_sl_no(int user_sl_no) {
		this.user_sl_no = user_sl_no;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		ResponderBean other = (ResponderBean) obj;
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
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
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
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResponderBean [id=" + id + ", fname=" + fname + ", lname="
				+ lname + ", dob=" + dob + ", topic=" + topic + ", isActive="
				+ isActive + ", email=" + email + ", pass=" + pass
				+ ", user_sl_no=" + user_sl_no + "]";
	}

}
