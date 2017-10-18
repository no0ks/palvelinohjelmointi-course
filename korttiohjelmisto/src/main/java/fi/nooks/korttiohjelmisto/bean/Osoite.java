package fi.nooks.korttiohjelmisto.bean;

public class Osoite {

	private int henkiloId;
	private String etunimi, sukunimi, katuosoite, postinro, postitmp;
	
	public Osoite() {
		super();
	}
	public Osoite(int henkiloId, String etunimi, String sukunimi, String katuosoite, String postinro, String postitmp) {
		super();
		this.henkiloId = henkiloId;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.katuosoite = katuosoite;
		this.postinro = postinro;
		this.postitmp = postitmp;
	}
	public int getHenkiloId() {
		return henkiloId;
	}
	public void setHenkiloId(int henkiloId) {
		this.henkiloId = henkiloId;
	}
	public String getEtunimi() {
		return etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	public String getSukunimi() {
		return sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	public String getKatuosoite() {
		return katuosoite;
	}
	public void setKatuosoite(String katuosoite) {
		this.katuosoite = katuosoite;
	}
	public String getPostinro() {
		return postinro;
	}
	public void setPostinro(String postinro) {
		this.postinro = postinro;
	}
	public String getPostitmp() {
		return postitmp;
	}
	public void setPostitmp(String postitmp) {
		this.postitmp = postitmp;
	}
	@Override
	public String toString() {
		return "Osoite [henkiloId=" + henkiloId + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", katuosoite="
				+ katuosoite + ", postinro=" + postinro + ", postitmp=" + postitmp + "]";
	}

}
