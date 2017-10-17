package fi.nooks.tuntikirjanpito.bean;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class WorkHours {

	private int entryId;
	
	private String username;
	
	@NotNull
	@DecimalMin("0.00")
	@DecimalMax("24.00")
	private BigDecimal hours;
	
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Past
	private Calendar workDate;
	
	private String firstName;
	
	private String lastName;

	public WorkHours() {
		super();
	}

	public WorkHours(int entryId, String username, BigDecimal hours, Calendar workDate, String firstName,
			String lastName) {
		super();
		this.entryId = entryId;
		this.username = username;
		this.hours = hours;
		this.workDate = workDate;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public Calendar getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Calendar workDate) {
		this.workDate = workDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "WorkHours [entryId=" + entryId + ", username=" + username + ", hours=" + hours + ", workDate="
				+ workDate + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
