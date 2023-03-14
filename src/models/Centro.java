package models;

public class Centro {
	private int id;
	private String descri;

	/**
	 * 
	 */
	public Centro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param descri
	 */
	public Centro(int id, String descri) {
		super();
		this.id = id;
		this.descri = descri;
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

	@Override
	public String toString() {
		return "Centro [id=" + id + ", descri=" + descri + "]";
	}
	public boolean equals(Object obj) {
		return this.descri == ((Centro) obj).descri;
	}

}
