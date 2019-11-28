package br.ufc.quixada.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Vitima;

public class CriminosoDAO {
	
	
	@Deprecated
	public static Criminoso buscar(int id) {
		String sql = "select * from Pessoa join (select * from Criminoso where codCriminoso = " + id + ") cc on cc.idPessoa = Pessoa.codPessoa";
		Criminoso c = null;
		Conexao con = new Conexao();
		con.conectar();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				c = new Criminoso();
				c.setIdPessoa(result.getInt("codPessoa"));
				c.setNome(result.getString("nomePessoa"));
				c.setCpf(result.getString("cpf"));
				c.setSexo(result.getString("sexo").toCharArray()[0]);
				c.setDataNasc(result.getDate("dataNasc").toLocalDate());
				c.setEndereco(EnderecoDAO.getEnderecoDB(result.getInt("idEndereco")));
				c.setContato(CrimeDAO.getContatosDB(result.getInt("codPessoa")));
				
				c.setId(result.getInt("codCriminoso"));
				c.setEscolaridade(result.getString("escolaridade"));
				
		
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			c = null;
		} finally {
			con.desconectar();
		}
		
		return c;
	}
	
	public static ArrayList<Criminoso> buscarNome(String nome){
		String sql = "select * from Criminoso join (select * from Pessoa where nomePessoa like '%" + nome + "%') p on p.codPessoa";
		Criminoso c = null;
		Conexao con = new Conexao();
		con.conectar();
		ArrayList<Criminoso> lista = new ArrayList<Criminoso>();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				c = new Criminoso();
				c.setIdPessoa(result.getInt("codPessoa"));
				c.setNome(result.getString("nomePessoa"));
				c.setCpf(result.getString("cpf"));
				c.setSexo(result.getString("sexo").toCharArray()[0]);
				c.setDataNasc(result.getDate("dataNasc").toLocalDate());
				c.setEndereco(EnderecoDAO.getEnderecoDB(result.getInt("idEndereco")));
				c.setContato(CrimeDAO.getContatosDB(result.getInt("codPessoa")));
				
				c.setId(result.getInt("codCriminoso"));
				c.setEscolaridade(result.getString("escolaridade"));
				lista.add(c);
		
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			lista = null;
		} finally {
			con.desconectar();
		}
		
		return lista;
	}
	
	@Deprecated
	public static ArrayList<Criminoso> buscarCpf(String nome){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Criminoso> buscarData(LocalDate date){
		return null;
	}
	
	@Deprecated
	public static boolean cadastrar(Criminoso v) {
		return false;
	}
	
	@Deprecated
	public static boolean editar(Criminoso v) {
		return false;
	}
	
	@Deprecated
	public static boolean remover(int id) {
		return false;
	}
	
}
