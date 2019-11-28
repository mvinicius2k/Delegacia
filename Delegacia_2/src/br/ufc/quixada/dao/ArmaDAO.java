package br.ufc.quixada.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Arma;

public class ArmaDAO {
	
	
	@Deprecated
	public static Arma buscar(int id) {
		return null;
	}
	
	
	public static int buscar(Arma a){
		String sql = "select codArma from Arma where nome = '" + a.getNome() + "'";
		Conexao con = new Conexao();
		
		con.conectar();
		int id = -1;
		try {
			
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				id = result.getInt("codArma");
			}
			
		}catch (Exception e) {
			id = -1;
			e.printStackTrace();
		
		} finally {
			con.desconectar();
		}
		
		return id;
	}
	
	
	public static boolean cadastrar(Arma a) {
		String sql = "insert into Arma(nome,descricao) values (?,?)";
		boolean retorno = true;
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			PreparedStatement ptt = con.preInserir(sql);
			ptt.setString(1, a.getNome());
			ptt.setString(2, " ");
			ptt.executeUpdate();
			ptt.close();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		}finally {
			con.desconectar();
		}
		
		return retorno;
	}
	
	
	
}
