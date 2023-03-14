package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Centro;
import models.Nivel;

public class Controlador_nivel {
	private static Connection conn = null;

	public static List<Nivel> cargarMaterias(String descri) {
		List<Nivel> lc = new ArrayList<Nivel>();
		try {
			conn = controllers.ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn
					.prepareStatement("select * from centroeducativoexamen.nivel where descripcion = " + descri);
			ResultSet rs = ps.executeQuery();
			Nivel m = null;
			while (rs.next()) {
				m = new Nivel();
				m.setId(rs.getInt(1));
				m.setDescri(rs.getString(2));
				m.setIdCentro(rs.getInt(3));
				Centro c = new Centro();
				c.setDescri(rs.getString(4));
				lc.add(m);

			}
			rs.close();
			ps.close();
			conn.close();
			return lc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Nivel guardar(Nivel mat) {
		try {

			if (mat.getId() != 0) {
				update(mat);
				return null;
			} else {
				return guardarNuevo(mat);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 */
	private static Nivel guardarNuevo(Nivel mat) {
		try {

			conn = controllers.ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn.prepareStatement(
					"insert into centroeducativo.materia set id = ?, nombre = ?, acronimo = ?, curso_id = ?");

			ps.setInt(1, nextId());

			ps.setInt(2, mat.getId());
			ps.setString(3, mat.getDescri());
			ps.setInt(4, mat.getIdCentro());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mat;

	}

	/**
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public static void update(Nivel mat) throws NumberFormatException, SQLException {
		conn = controllers.ConnectionManagerV1.getConexion();
		PreparedStatement ps = conn.prepareStatement(
				"update centroeducativoexamen set descri = ?, idCentro = ?, id = ? where descri =" + mat.getDescri());

		ps.setInt(1, mat.getId());
		ps.setString(2, mat.getDescri());
		ps.setInt(3, mat.getIdCentro());

		ps.executeUpdate();

		ps.close();
		conn.close();

	}

	public static Nivel cargarSiguienteRegistro(Nivel mat) {
		try {
			conn = controllers.ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn
					.prepareStatement("select * from centroeducativoexamen.nivel where descripcion > " + mat.getDescri()
							+ " order by id limit 1");
			ResultSet rs = ps.executeQuery();
			Nivel m = new Nivel();
			if (rs.next()) {

				m.setId(rs.getInt(1));

				m.setDescri(rs.getString(2));
				m.setIdCentro(rs.getInt(3));
				Centro c = new Centro();
				c.setId(rs.getInt(4));

				rs.close();
				ps.close();
				conn.close();
				return m;

			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Nivel cargarPrimerRegistro() {

		try {
			conn = controllers.ConnectionManagerV1.getConexion();
			Nivel mat = null;
			PreparedStatement ps = conn
					.prepareStatement("select * from centroeducativoexamen.descripcion order by id limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				mat = new Nivel();
				mat.setId(rs.getInt(1));
				mat.setDescri(rs.getString(2));
				mat.setIdCentro(rs.getInt(3));
				Centro c = new Centro();
				c.setId(rs.getInt(4));

				rs.close();
				ps.close();
				conn.close();
				return mat;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static int nextId() throws SQLException {
		conn = controllers.ConnectionManagerV1.getConexion();
		PreparedStatement ps = conn.prepareStatement("select max(id) from centroeducativoexamen.nivel");
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1) + 1;

		}
		ps.close();
		rs.close();
		conn.close();
		return 1;

	}

	public static List<Nivel> findAll() {
		List<Nivel> lc = new ArrayList<Nivel>();
		Nivel ma = cargarPrimerRegistro();
		do {
			lc.add(ma);
			ma = cargarSiguienteRegistro(ma);
		} while (ma != null);
		return lc;
	}

}
