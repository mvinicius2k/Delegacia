package br.ufc.quixada.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	public  Connection con = null;
	public Statement stt;
	public PreparedStatement ptt;
	
	private String endereco, usuario, senha;
	
	
	public Conexao(String endereco, String usuario, String senha) {
		super();
		this.endereco = endereco;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Conexao() {
		super();
		this.endereco = "jdbc:postgresql://localhost:5432/Delegacia4";
		this.usuario = "postgres";
		this.senha = "alunoufc";
		
		
	}

	
	
	/*
	 * Se a conex�o tiver sucesso, um booleano true � retornado.
	 * Se a conexao n�o tiver sucesso, um boolean false � retornado.
	 */

	public boolean conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection(this.endereco, this.usuario, this.senha);
			stt = con.createStatement();
			
			return true;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Classe n�o encontrada");
			e.printStackTrace();
			return false;
			
		} catch (SQLException e) {
			System.err.println("Erro de query SQL");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	/*
	 * Se a desconex�o tiver sucesso, um booleano true � retornado.
	 * Se a desconexao n�o tiver sucesso, um boolean false � retornado.
	 */
	public boolean desconectar() {
		try {
			this.con.close();
			return true;
			
		} catch (SQLException e) {
			System.err.println("Erro ao fechar o banco");
			e.printStackTrace();
			return false;
		}
	}
	
	public PreparedStatement preInserir(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	
	public PreparedStatement deletar(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	
	public PreparedStatement criar(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	
	
		
	public ResultSet consultar(String sql) throws SQLException {
		return stt.executeQuery(sql);
	}
}
