package br.ufc.quixada.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Vitima;
import br.ufc.quixada.model.Vitima;
import br.ufc.quixada.model.Vitima;

public class VitimaDAO {
	
	
	
	public static  Vitima buscar(int id) {
		String sql = "select * from Pessoa join (select * from Vitima where codVitima = " + id + ") cc on cc.idPessoa = Pessoa.codPessoa";
		Vitima c = null;
		Conexao con = new Conexao();
		con.conectar();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				c = new Vitima();
				c.setIdPessoa(result.getInt("codPessoa"));
				c.setNome(result.getString("nomePessoa"));
				c.setCpf(result.getString("cpf"));
				c.setSexo(result.getString("sexo").toCharArray()[0]);
				c.setDataNasc(result.getDate("dataNasc").toLocalDate());
				c.setEndereco(EnderecoDAO.getEnderecoDB(result.getInt("idEndereco")));
				c.setContato(CrimeDAO.getContatosDB(result.getInt("codPessoa")));
				
				c.setId(result.getInt("codVitima"));
				c.setEstadoCorpo((result.getString("estadoCorpo")));
				
		
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			c = null;
		} finally {
			con.desconectar();
		}
		
		return c;
	}
	
	
	
	
	public static ArrayList<Vitima> buscarNome(String nome){
		String sql = "select * from Vitima join (select * from Pessoa where nomePessoa like '%" + nome + "%') p on p.codPessoa = Vitima.idPessoa";
		Vitima c = null;
		Conexao con = new Conexao();
		con.conectar();
		ArrayList<Vitima> lista = new ArrayList<Vitima>();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				c = new Vitima();
				c.setIdPessoa(result.getInt("codPessoa"));
				c.setNome(result.getString("nomePessoa"));
				c.setCpf(result.getString("cpf"));
				c.setSexo(result.getString("sexo").toCharArray()[0]);
				c.setDataNasc(result.getDate("dataNasc").toLocalDate());
				c.setEndereco(EnderecoDAO.getEnderecoDB(result.getInt("idEndereco")));
				c.setContato(CrimeDAO.getContatosDB(result.getInt("codPessoa")));
				
				c.setId(result.getInt("codVitima"));
				c.setEstadoCorpo((result.getString("estadoCorpo")));
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
	public static ArrayList<Vitima> buscarCpf(String nome){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Vitima> buscarData(LocalDate date){
		return null;
	}
	
	
	public static boolean cadastrar(Vitima v) {
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
			
			ptt = con.preInserir("insert into Vitima(idPessoa, estadoCorpo) values(?,?)");
			
			ptt.setInt(1, v.getIdPessoa());
			ptt.setString(2, v.getEstadoCorpo());
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
	
	@Deprecated
	public static boolean editar(Vitima v) {
		return false;
	}
	
	@Deprecated
	public static boolean remover(int id) {
		return false;
	}
	
}
