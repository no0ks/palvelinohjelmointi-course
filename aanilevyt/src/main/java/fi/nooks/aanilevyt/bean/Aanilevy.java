package fi.nooks.aanilevyt.bean;

public class Aanilevy {

	private int levyId, julkaisuvuosi;
	private String levyNimi, artisti;
	
	public Aanilevy() {
		super();
	}

	public Aanilevy(int levyId, int julkaisuvuosi, String levyNimi, String artisti) {
		super();
		this.levyId = levyId;
		this.julkaisuvuosi = julkaisuvuosi;
		this.levyNimi = levyNimi;
		this.artisti = artisti;
	}

	public int getLevyId() {
		return levyId;
	}

	public void setLevyId(int levyId) {
		this.levyId = levyId;
	}

	public int getJulkaisuvuosi() {
		return julkaisuvuosi;
	}

	public void setJulkaisuvuosi(int julkaisuvuosi) {
		this.julkaisuvuosi = julkaisuvuosi;
	}

	public String getLevyNimi() {
		return levyNimi;
	}

	public void setLevyNimi(String levyNimi) {
		this.levyNimi = levyNimi;
	}

	public String getArtisti() {
		return artisti;
	}

	public void setArtisti(String artisti) {
		this.artisti = artisti;
	}

	@Override
	public String toString() {
		return "Aanilevy [levyId=" + levyId + ", julkaisuvuosi=" + julkaisuvuosi + ", levyNimi=" + levyNimi
				+ ", artisti=" + artisti + "]";
	}

}
