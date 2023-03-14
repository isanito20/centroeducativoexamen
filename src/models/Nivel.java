package models;

public class Nivel {
	private int id;
	private String descri;
	private int idCentro;
	/**
	 * 
	 */
	public Nivel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param descri
	 * @param idCentro
	 */
	public Nivel(int id, String descri, int idCentro) {
		super();
		this.id = id;
		this.descri = descri;
		this.idCentro = idCentro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}
	@Override
	public String toString() {
		return "Nivel [id=" + id + ", descri=" + descri + ", idCentro=" + idCentro + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.descri == ((Nivel)obj).descri;
	}
	
}
