package models;

import java.sql.Date;

public class Materia {
	private int id;
	private String nombre;
	private int idNivel;
	private String codigo;
	private boolean admiteMatricula;
	private Date fechainicio;

	/**
	 * 
	 */
	public Materia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param nombre
	 * @param idNivel
	 * @param codigo
	 * @param admiteMatricula
	 * @param fechainicio
	 */
	public Materia(int id, String nombre, int idNivel, String codigo, boolean admiteMatricula, Date fechainicio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idNivel = idNivel;
		this.codigo = codigo;
		this.admiteMatricula = admiteMatricula;
		this.fechainicio = fechainicio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isAdmiteMatricula() {
		return admiteMatricula;
	}

	public void setAdmiteMatricula(boolean admiteMatricula) {
		this.admiteMatricula = admiteMatricula;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@Override
	public String toString() {
		return "Materia [id=" + id + ", nombre=" + nombre + ", idNivel=" + idNivel + ", codigo=" + codigo
				+ ", admiteMatricula=" + admiteMatricula + ", fechainicio=" + fechainicio + "]";
	}

}
