package fi.nooks.painonhallinta.bean;

import java.sql.Timestamp;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

public class Weight {

	private int entryId;
	
	@NotNull
	@DecimalMax("999.9")
	private double kg;
	
	private Timestamp entryDate;
	
	public Weight() {
		super();
	}

	public Weight(int entryId, double kg, Timestamp entryDate) {
		super();
		this.entryId = entryId;
		this.kg = kg;
		this.entryDate = entryDate;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public double getKg() {
		return kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}

	public Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "Weight [entryId=" + entryId + ", kg=" + kg + ", entryDate=" + entryDate + "]";
	}
	
}
