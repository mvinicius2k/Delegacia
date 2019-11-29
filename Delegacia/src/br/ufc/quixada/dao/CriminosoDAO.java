package br.ufc.quixada.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Vitima;

public class CriminosoDAO {
	
	
	
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
		String sql = "select * from Criminoso join (select * from Pessoa where nomePessoa like '%" + nome + "%') p on p.codPessoa = Criminoso.idPessoa";
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
	
	
	public static boolean cadastrar(Criminoso v) {
		String sql = "insert into Pessoa(nomePessoa, cpf, sexo, dataNasc, idEndereco) values (?,?,?,?,?)";
		EnderecoDAO.cadastrar(v.getEndereco());
		v.setEnderecoid(EnderecoDAO.buscar(v.getEndereco()));
		boolean retorno = true;
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			PreparedStatement ptt = con.preInserir(sql);
			ptt.setString(1, v.getNome());
			ptt.setString(2, v.getCpf());
			ptt.setString(3, String.valueOf(v.getSexo()));
			ptt.setDate(4, Date.valueOf(v.getDataNasc()));
			ptt.setInt(5, v.getEnderecoid());
			ptt.executeUpdate();
			ptt.close();
			
			ResultSet result = con.consultar("select MAX(codPessoa) from Pessoa");
			while(result.next()) v.setIdPessoa(result.getInt(1));
			
			for(int i = 0; i < v.getContato().size(); i++) {
				ptt = con.preInserir("insert into Contato(idPessoa, contato) values(?,?)");
				ptt.setInt(1, v.getIdPessoa());
				ptt.executeUpdate();
				ptt.close();
			}
			
			ptt = con.preInserir("insert into Criminoso(idPessoa, escolaridade) values(?,?)");
			
			ptt.setInt(1, v.getIdPessoa());
			ptt.setString(2, v.getEscolaridade());
			ptt.executeUpdate();
			ptt.close();
			
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			con.desconectar();
			
		}
		
		return retorno;
	}
	
	
	
	
	
}
