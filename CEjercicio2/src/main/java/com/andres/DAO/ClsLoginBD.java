package com.andres.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.andres.Conexion.ConexionBd;
import com.andres.Entidades.usuario;

public class ClsLoginBD {

	ConexionBd conexion = new ConexionBd();
	Connection con = conexion.RetornarConexion();
	
	public usuario ValidarLogin(usuario usu) {
		usuario user = new usuario();
		try {
			CallableStatement consulta = con.prepareCall("call SP_S_LOGIN(?,?);");
			consulta.setString("PUsuario", usu.getUsuario());
			consulta.setString("PPass", usu.getPass());
			ResultSet resultado = consulta.executeQuery();
			System.out.println("Paso 1" + usu.getUsuario());
			while(resultado.next()) {
				System.out.println("Paso 2");
				user.setIdUsuario(resultado.getInt("idUsuario"));
				user.setUsuario(resultado.getString("Usuario"));
				user.setPass(resultado.getString("PassWord"));
				user.setTipoUsuario(resultado.getInt("tipoUsuario"));
				
				System.out.println(resultado.getString("Usuario"));
			}
			System.out.println("Paso 3");
		} catch (Exception e) {
			System.out.println("> Usuario no encontrado.");
		}
		
		return user;
	}
}
