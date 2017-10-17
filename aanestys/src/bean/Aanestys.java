package bean;

public class Aanestys {

	private int aanestysId, vastausKylla, vastausEi;
	private String kysymys;
	
	public Aanestys() {
		super();
	}
	public Aanestys(int aanestysId, int vastausKylla, int vastausEi, String kysymys) {
		super();
		this.aanestysId = aanestysId;
		this.vastausKylla = vastausKylla;
		this.vastausEi = vastausEi;
		this.kysymys = kysymys;
	}
	public int getAanestysId() {
		return aanestysId;
	}
	public void setAanestysId(int aanestysId) {
		this.aanestysId = aanestysId;
	}
	public int getVastausKylla() {
		return vastausKylla;
	}
	public void setVastausKylla(int vastausKylla) {
		this.vastausKylla = vastausKylla;
	}
	public int getVastausEi() {
		return vastausEi;
	}
	public void setVastausEi(int vastausEi) {
		this.vastausEi = vastausEi;
	}
	public String getKysymys() {
		return kysymys;
	}
	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}
	@Override
	public String toString() {
		return "Aanestys [aanestysId=" + aanestysId + ", vastausKylla=" + vastausKylla + ", vastausEi=" + vastausEi
				+ ", kysymys=" + kysymys + "]";
	}
	
}
