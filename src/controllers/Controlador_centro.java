package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Centro;

public class Controlador_centro {
	private static Connection conn = null;

	public static Centro guardar(Centro curs) {
		try {

			if (curs.getId() != 0) {
				update(curs);
				return null;
			} else {
				return guardarNuevo(curs);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 */
	private static Centro guardarNuevo(Centro curs) {
		try {
			conn = controllers.ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn
					.prepareStatement("insert into centroeducativo.curso set id = ?, descripcion = ? ");

			ps.setInt(1, nextId());

			ps.setString(2, curs.getDescri());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return curs;

	}

	/**
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private static void update(Centro curs) throws NumberFormatException, SQLException {
		conn = controllers.ConnectionManagerV1.getConexion();
		PreparedStatement ps = conn
				.prepareStatement("update centroeducativo.curso set descripcion = ? where id =" + curs.getId());

		ps.setString(1, curs.getDescri());

		ps.executeUpdate();

		ps.close();
		conn.close();

	}

	/**
	 * 
	 */
	public static Centro cargarAnteriorRegistro(Centro curs) {
		try {
			conn = controllers.ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn.prepareStatement(
					"select * from centroeducativo.curso where id < " + curs.getId() + " order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			Centro c = new Centro();
			if (rs.next()) {

				c.setId(rs.getInt(1));
				c.setDescri(rs.getString(2));
				rs.close();
				ps.close();
				conn.close();
				return c;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public static Centro cargarSiguienteRegistro(Centro curs) {
		try {
			conn = controllers.ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn.prepareStatement(
					"select * from centroeducativo.curso where id > " + curs.getId() + " order by id limit 1");
			ResultSet rs = ps.executeQuery();
			Centro c = new Centro();
			if (rs.next()) {

				c.setId(rs.getInt(1));
				c.setDescri(rs.getString(2));

				rs.close();
				ps.close();
				conn.close();
				return c;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public static Centro cargarUltimoRegistro() {
		try {
			conn = controllers.ConnectionManagerV1.getConexion();
			Centro curs = null;
			PreparedStatement ps = conn
					.prepareStatement("select * from centroeducativo.curso order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				curs = new Centro();
				curs.setId(rs.getInt(1));
				curs.setDescri(rs.getString(2));

			}
			rs.close();
			ps.close();
			conn.close();
			return curs;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static Centro cargarPrimerRegistro() {

		try {
			conn = controllers.ConnectionManagerV1.getConexion();
			Centro curs = null;
			PreparedStatement ps = conn.prepareStatement("select * from centroeducativo.curso order by id limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				curs = new Centro();
				curs.setId(rs.getInt(1));
				curs.setDescri(rs.getString(2));
			}
			rs.close();
			ps.close();
			conn.close();
			return curs;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public static int nextId() throws SQLException {
		conn = controllers.ConnectionManagerV1.getConexion();
		PreparedStatement ps = conn.prepareStatement("select max(id) from centroeducativo.curso");
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int maxid = rs.getInt(1) + 1;
			ps.close();
			rs.close();
			return maxid;

		}
		ps.close();
		rs.close();
		return 1;

	}

	public static List<Centro> findAll() {
		List<Centro> lc = new ArrayList<Centro>();
		Centro c = cargarPrimerRegistro();
		do {
			lc.add(c);
			c = cargarSiguienteRegistro(c);
		} while (c != null);
		return lc;
	}
}
