package bean;

import java.sql.Timestamp;

public class PaperinOstaja {

	private int ostajaId, ostettuMaara, ostettava;
	private String ostajaNimi;
	private Timestamp paivitetty;
	
	public PaperinOstaja() {
		super();
	}
	public PaperinOstaja(int ostajaId, int ostettuMaara, int ostettava, String ostajaNimi, Timestamp paivitetty) {
		super();
		this.ostajaId = ostajaId;
		this.ostettuMaara = ostettuMaara;
		this.ostettava = ostettava;
		this.ostajaNimi = ostajaNimi;
		this.paivitetty = paivitetty;
	}
	public int getOstajaId() {
		return ostajaId;
	}
	public void setOstajaId(int ostajaId) {
		this.ostajaId = ostajaId;
	}
	public int getOstettuMaara() {
		return ostettuMaara;
	}
	public void setOstettuMaara(int ostettuMaara) {
		this.ostettuMaara = ostettuMaara;
	}
	public String getOstajaNimi() {
		return ostajaNimi;
	}
	public void setOstajaNimi(String ostajaNimi) {
		this.ostajaNimi = ostajaNimi;
	}
	public int getOstettava() {
		return ostettava;
	}
	public void setOstettava(int ostettava) {
		this.ostettava = ostettava;
	}
	public Timestamp getPaivitetty() {
		return paivitetty;
	}
	public void setPaivitetty(Timestamp paivitetty) {
		this.paivitetty = paivitetty;
	}
	@Override
	public String toString() {
		return "PaperinOstaja [ostajaId=" + ostajaId + ", ostettuMaara=" + ostettuMaara + ", ostettava=" + ostettava
				+ ", ostajaNimi=" + ostajaNimi + ", paivitetty=" + paivitetty + "]";
	}
}
