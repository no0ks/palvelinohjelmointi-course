package fi.nooks.pistekirjanpito.bean;

public class DemoPiste {

	private String oppilasnro;
	private int arvosana;
	
	public DemoPiste() {
		super();
	}
	public DemoPiste(String oppilasnro, int arvosana) {
		super();
		this.oppilasnro = oppilasnro;
		this.arvosana = arvosana;
	}
	public String getOppilasnro() {
		return oppilasnro;
	}
	public void setOppilasnro(String oppilasnro) {
		this.oppilasnro = oppilasnro;
	}
	public int getArvosana() {
		return arvosana;
	}
	public void setArvosana(int arvosana) {
		this.arvosana = arvosana;
	}
	@Override
	public String toString() {
		return "DemoPiste [oppilasnro=" + oppilasnro + ", arvosana=" + arvosana + "]";
	}
	
}
