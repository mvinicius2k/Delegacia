package br.ufc.quixada.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Arma;
import br.ufc.quixada.model.Crime;
import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Lei;
import br.ufc.quixada.model.Vitima;

public class CrimeDAO {
	
	//private Crime c;
	
	
	public static boolean cadastrar(Crime c) {
		int idEnd = EnderecoDAO.buscar(c.getLocal());
		if(idEnd == -1) {
			EnderecoDAO.cadastrar(c.getLocal());
			idEnd = EnderecoDAO.buscar(c.getLocal());
		}
		
		c.setEnderecoid(idEnd);
		
		try {
			//c.getCriminosos().toString();
		
			int a = 1;
			
			String sql = "insert into Crime(descricao, dataOcorrencia, dataComunicacao, fragrante, consumado, idEndereco) values (? ,? ,? ,? ,? ,?)";
			
			Conexao con = new Conexao();
			con.conectar();
			
			
			PreparedStatement ptt = con.preInserir(sql);
			
			ptt.setString(a++, c.getDescricao());
			ptt.setString(a++, c.getDataOcorrencia());
			ptt.setTimestamp(a++, Timestamp.valueOf(c.getDataComunicacao()));
			ptt.setBoolean(a++, c.isFragrante());
			ptt.setBoolean(a++, c.isConsumado());
			ptt.setInt(a++, c.getEnderecoid());
			
			
			
			ptt.executeUpdate();
			
			ResultSet result = con.consultar("select MAX(codCrime) from Crime");
			int idCrime = -1;
			while (result.next()) {
				idCrime = result.getInt(1);
			}
			
			
			
			for(int i = 0; i < c.getVitimas().size(); i++) {
				//sd
				ptt = con.preInserir("insert into CrimeVitima(idVitima, idCrime) values (?, ?)");
				ptt.setInt(1, c.getVitimas().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			for(int i = 0; i < c.getCriminosos().size(); i++) {
				ptt = con.preInserir("insert into CrimeCriminoso(idCriminoso, idCrime) values (?, ?)");
				ptt.setInt(1, c.getCriminosos().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			
			for(int i = 0; i < c.getArmas().size(); i++) {
				c.getArmas().get(i).setId(ArmaDAO.buscar(c.getArmas().get(i)));
				ptt = con.preInserir("insert into CrimeArma(idArma, idCrime) values (?, ?)");
				ptt.setInt(1, c.getArmas().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			con.desconectar();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Crime buscar(int id) {
		Conexao con = new Conexao();
		try{
			con.conectar();
			
			ResultSet result = con.consultar("select * from Crime where codCrime = " + id + " limit 1");
			
			ArrayList<Crime> resultado = resultSetToCrime(result);
			con.desconectar();
			return resultado.get(0);
			
			
		} catch (IndexOutOfBoundsException e) {
			System.out.print("O crime de id " + id + " nao existe");
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			con.desconectar();
		}
	}
	
	public static ArrayList<Crime> buscar(String chave){
		Conexao con = new Conexao();
		try {
			
			
			ResultSet result;
			
			
			con.conectar();
				
			//result = con.consultar("select * from Crime join (select id from crimesBusca where result like '%" + chave.toLowerCase() + "%') b on Crime.id = b.id");
			result = con.consultar("select * from Crime");
			
			ArrayList<Crime> busca = resultSetToCrime(result);
			ArrayList<Crime> resultado = new ArrayList<Crime>();
			for(int i = 0; i < busca.size(); i++) {
				
				if(busca.get(i).toString().toLowerCase().contains(chave.toLowerCase())) {
					
					resultado.add(busca.get(i));
				}
			}
				
			
			
			con.desconectar();
			
			return resultado;
			
		} catch (SQLException e) {
			System.err.println("Erro de SQL");
			con.desconectar();
			e.printStackTrace();
			return null;
		}
}
	
	
	
	public static ArrayList<Crime> buscarDataOcorrencia(String date){
		String sql = "select * from Crime where dataOcorrencia ilike '%" + date.toLowerCase() + "%'";
		//System.out.println(sql);
		ArrayList<Crime> lista = new ArrayList<>();
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			ResultSet result = con.consultar(sql);
			
			
			lista = resultSetToCrime(result);
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			con.desconectar();
		}
		
		return lista;
	}
	
	public static ArrayList<Crime> buscarLei(int idLei){
		ArrayList<Integer> idsCrime = new ArrayList<Integer>();
		ArrayList<Crime> lista = new ArrayList<>();
		
		String sql = "select idCrime from CrimeLei where idLei = " + idLei;
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			ResultSet result = con.consultar(sql);
			
			while(result.next())
				idsCrime.add(result.getInt("idCrime"));
			
			for(int i = 0; i < idsCrime.size(); i++) {
				result = con.consultar("select * from Crime where codCrime = " + idsCrime.get(i));
				
				lista = (resultSetToCrime(result));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			con.desconectar();
		}
		
		return lista;
	}
	
	
	public static boolean editar(Crime c) {
		if(buscar(c.getId()) == null) {
			System.err.println("Crime nao encontrado");
			return false;
		}
		
		boolean retorno = true;
		
		String sql = "";
		
		int idEnd = EnderecoDAO.buscar(c.getLocal());
		if(idEnd == -1) {
			EnderecoDAO.cadastrar(c.getLocal());
			idEnd = EnderecoDAO.buscar(c.getLocal());
		}
		
		
		sql = "update Crime set idEndereco=?,dataOcorrencia=?, dataComunicacao=?, fragrante=?, consumado=?, descricao=? where codCrime = " + c.getId();
		Conexao con = new Conexao();
		System.out.println("edit " + sql);
		con.conectar();
		
		try {
			PreparedStatement ptt = con.preInserir(sql);
			int a = 1;
			
			ptt.setInt(a++, idEnd);
			ptt.setString(a++, c.getDataOcorrencia());
			ptt.setTimestamp(a++, Timestamp.valueOf(c.getDataComunicacao()));
			ptt.setBoolean(a++, c.isFragrante());
			ptt.setBoolean(a++, c.isConsumado());
			ptt.setString(a++, c.getDescricao());
			
			int crimeid = c.getId();
			apagarCrimesArmas(crimeid);
			apagarCrimesVitima(crimeid);
			apagarCrimesCriminoso(crimeid);
			
			ptt.executeUpdate();
			ptt.close();
			
			int idCrime = c.getId();
			for(int i = 0; i < c.getVitimas().size(); i++) {
				//sd
				ptt = con.preInserir("insert into CrimeVitima(idVitima, idCrime) values (?, ?)");
				ptt.setInt(1, c.getVitimas().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			for(int i = 0; i < c.getCriminosos().size(); i++) {
				ptt = con.preInserir("insert into CrimeCriminoso(idCriminoso, idCrime) values (?, ?)");
				ptt.setInt(1, c.getCriminosos().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			
			for(int i = 0; i < c.getArmas().size(); i++) {
				c.getArmas().get(i).setId(ArmaDAO.buscar(c.getArmas().get(i)));
				ptt = con.preInserir("insert into CrimeArma(idArma, idCrime) values (?, ?)");
				ptt.setInt(1, c.getArmas().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			ptt.close();
			
			
			
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			con.desconectar();
		}
		
		return retorno;
	}
	
	public static void apagarCrimesArmas(int crimeid) {
		String sql = "delete from CrimeArma where idCrime = " + crimeid;
		
		Conexao con = new Conexao();
		con.conectar();
		try {
			
			con.deletar(sql).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			con.desconectar();
		}
	}
	
	public static void apagarCrimesCriminoso(int crimeid) {
		String sql = "delete from CrimeCriminoso where idCrime = " + crimeid;
		
		Conexao con = new Conexao();
		con.conectar();
		try {
			
			con.deletar(sql).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			con.desconectar();
		}
	}
	
	public static void apagarCrimesVitima(int crimeid) {
		String sql = "delete from CrimeVitima where idCrime = " + crimeid;
		
		Conexao con = new Conexao();
		con.conectar();
		try {
			
			con.deletar(sql).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			con.desconectar();
		}
	}
	
	public static boolean remover(ArrayList<Integer> id) {
		Conexao con = new Conexao();
		boolean retorno = true;
		
		con.conectar();
		
		try {
			for(int i = 0; i < id.size(); i++) {
				int _id = id.get(i);
				String sql = "";
				
				apagarCrimesArmas(_id);
				apagarCrimesCriminoso(_id);
				apagarCrimesVitima(_id);
				
				PreparedStatement ptt;
				sql = "delete from CrimeLei where idCrime = " + _id;
				ptt = con.deletar(sql);
				ptt.executeUpdate();
				sql = "delete from Crime where codCrime = " + _id;
				ptt = con.deletar(sql);
				ptt.executeUpdate();
				
				
			}
			
		} catch (Exception e) {
			retorno = false;
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
	return retorno;
	}
	
	
	
	
	
	
	
	public static ArrayList<Criminoso> getCriminososDB(int idCrime){
		String sql = "select * from Pessoa join (select * from Criminoso join (select idCriminoso from CrimeCriminoso where idCrime = " + idCrime + ")  c on c.idCriminoso = Criminoso.codCriminoso) cc on cc.idPessoa = Pessoa.codPessoa";
		ArrayList<Criminoso> lista = new ArrayList<>();
		Conexao con = new Conexao();
		con.conectar();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				Criminoso c = new Criminoso();
				c.setIdPessoa(result.getInt("codPessoa"));
				c.setNome(result.getString("nomePessoa"));
				c.setCpf(result.getString("cpf"));
				c.setSexo(result.getString("sexo").toCharArray()[0]);
				c.setDataNasc(result.getDate("dataNasc").toLocalDate());
				c.setEndereco(EnderecoDAO.getEnderecoDB(result.getInt("idEndereco")));
				c.setContato(getContatosDB(result.getInt("codPessoa")));
				
				c.setId(result.getInt("codCriminoso"));
				c.setEscolaridade((result.getString("escolaridade")));
				
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
	
	
	public static ArrayList<Vitima> getVitimasDB(int idCrime){
		String sql = "select * from Pessoa join (select * from Vitima join (select idVitima from CrimeVitima where idCrime = " + idCrime + ")  c on c.idVitima = Vitima.codVitima) cc on cc.idPessoa = Pessoa.codPessoa";
		//System.out.println(sql);
		ArrayList<Vitima> lista = new ArrayList<>();
		Conexao con = new Conexao();
		con.conectar();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				Vitima v = new Vitima();
				v.setIdPessoa(result.getInt("codPessoa"));
				v.setNome(result.getString("nomePessoa"));
				v.setCpf(result.getString("cpf"));
				v.setSexo(result.getString("sexo").toCharArray()[0]);
				v.setDataNasc(result.getDate("dataNasc").toLocalDate());
				v.setEndereco(EnderecoDAO.getEnderecoDB(result.getInt("idEndereco")));
				v.setContato(getContatosDB(result.getInt("codPessoa")));
				
				v.setId(result.getInt("codVitima"));
				v.setEstadoCorpo(result.getString("estadoCorpo"));
				
				lista.add(v);
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			lista = null;
		} finally {
			con.desconectar();
		}
		
		return lista;
	}
	
	
	
	
	public static ArrayList<Arma> getArmasDB(int idCrime){
		Conexao con = new Conexao();
		ArrayList<Arma> lista = new ArrayList<>();
		String sql = "select * from Arma join (select idArma from CrimeArma where idCrime = " + idCrime + ") d on d.idArma = Arma.codArma";
		//System.err.println(sql);
		con.conectar();
		
		
		
		try {
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				Arma a = new Arma();
				
				a.setId(result.getInt("codArma"));
				a.setNome(result.getString("nome"));
				a.setDescricao(result.getString("descricao"));
				lista.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			con.desconectar();
		}
		//System.out.println("debug " + lista.toString());
		return lista;
		
		
	}
	
	public static ArrayList<Lei> getLeisDB(int idCrime){
		String sql = "select * from Lei join (select idLei from CrimeLei where idCrime = 1) c on c.idLei = Lei.codLei";
		Conexao con = new Conexao();
		ArrayList<Lei> lista = new ArrayList<>();
		
		con.conectar();
		
		
		
		try {
			
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				Lei l = new Lei(result.getInt("codLei"), result.getString("descricao"));
				lista.add(l);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			
			con.desconectar();
			
		}
		return lista;
	}
	
	
	public static ArrayList<String> getContatosDB(int idPessoa) {
		Conexao con = new Conexao();
		ArrayList<String> contatos = new ArrayList<String>();
		String sql = "select * from contato where idPessoa = " + idPessoa;
		
		con.conectar();
		try {
			ResultSet result = con.consultar(sql);
		
			while(result.next()) 
				contatos.add(result.getString("contato"));
				
		} catch (SQLException e) {
			e.printStackTrace();
			contatos = null;
		} catch (Exception e) {
			e.printStackTrace();
			contatos = null;
		} finally {
			con.desconectar();
		}
		return contatos;
	}
	
		

	private static ArrayList<Crime> resultSetToCrime(ResultSet result) throws SQLException {
		ArrayList<Crime> resultados = new ArrayList<Crime>();
		
		while(result.next()) {
			Crime c = new Crime();
			c.setId(result.getInt("codCrime"));
			c.setDataOcorrencia(result.getString("dataOcorrencia"));
			c.setDataComunicacao(result.getTimestamp("dataComunicacao").toLocalDateTime());
			c.setFragrante(result.getBoolean("fragrante"));
			c.setConsumado(result.getBoolean("consumado"));
			c.setDescricao(result.getString("descricao"));
			c.setEnderecoid(result.getInt("idEndereco"));
			
			
			resultados.add(c);
			
		}
		
		for(int i = 0; i < resultados.size(); i++) {
			Crime c = resultados.get(i);
			c.setLocal(EnderecoDAO.getEnderecoDB(c.getEnderecoid()));
			c.setVitimas(getVitimasDB(c.getId()));
			c.setCriminosos(getCriminososDB(c.getId()));
			c.setArmas(getArmasDB(c.getId()));
			c.setLeis(getLeisDB(c.getId()));
			
			//System.out.println("><" + (c.getLocal().toString()));
		}
		
		
		
		return resultados;
	}

	

	
	
	
}