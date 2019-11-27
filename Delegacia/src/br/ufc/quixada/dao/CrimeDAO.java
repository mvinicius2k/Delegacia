package br.ufc.quixada.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Arma;
import br.ufc.quixada.model.Crime;
import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Endereco;
import br.ufc.quixada.model.Lei;
import br.ufc.quixada.model.Vitima;

public class CrimeDAO {
	
	//private Crime c;
	
	
	public static boolean cadastrar(Crime c) {
		try {
			
		
			int a = 1;
			String sql = "insert into Crime(descricao, dataOcorrencia, dataComunicacao, flagrante, consumado, enderecoid) values (? ,? ,? ,? ,? ,?)";
		
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
			
			ResultSet result = con.consultar("select MAX(id) from Crime");
			int idCrime = -1;
			while (result.next()) {
				idCrime = result.getInt(1);
			}
			
			
			
			for(int i = 0; i < c.getVitimas().size(); i++) {
				ptt = con.preInserir("insert into CrimeVitima(idVitima, idCrime) values (?, ?)");
				ptt.setInt(1, c.getVitimas().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			for(int i = 0; i < c.getCriminosos().size(); i++) {
				ptt = con.preInserir("insert into CrimeVitima(idCriminoso, idCrime) values (?, ?)");
				ptt.setInt(1, c.getCriminosos().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			for(int i = 0; i < c.getLeis().size(); i++) {
				ptt = con.preInserir("insert into CrimeLei(idLei, idCrime) values (?, ?)");
				ptt.setInt(1, c.getLeis().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			for(int i = 0; i < c.getArmas().size(); i++) {
				ptt = con.preInserir("insert into CrimeArmas(idArma, idCrime) values (?, ?)");
				ptt.setInt(1, c.getArmas().get(i).getId());
				ptt.setInt(2, idCrime);
				ptt.executeUpdate();
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Crime buscar(int id) {
		try{
			Conexao con = new Conexao();
			
			ResultSet result = con.consultar("select * from Crime where id = " + id + " limit 1");
			
			ArrayList<Crime> resultado = resultSetToCrime(result);
			
			return resultado.get(0);
			
			
		} catch (IndexOutOfBoundsException e) {
			System.out.print("O crime de id " + id + " não existe");
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Crime> buscar(String chave){
		try {
			
			
			ResultSet result;
			Conexao con = new Conexao();
			
			con.conectar();
				
			result = con.consultar("select * from Crime join (select id from crimesBusca where like '%" + chave.toLowerCase() + "%') b on Crime.id = b.id");
				
			ArrayList<Crime> resultado = resultSetToCrime(result);
			
			con.desconectar();
			
			return resultado;
			
		} catch (SQLException e) {
			System.err.println("Erro de SQL");
			e.printStackTrace();
			return null;
		}
}
	
	@Deprecated
	public static boolean editar(Crime c) {
			return true;
	}
	
	@Deprecated
	public static boolean remover(ArrayList<Integer> id) {
		return false;
	}
	
	
	
	
	
	
	@Deprecated
	public static ArrayList<Criminoso> getCriminososDB(int idCrime){
		String sql = "select * Pessoa from pessoa join (select * from Criminoso join (select idCriminoso from CrimeCriminoso where idCrime = " + idCrime + ") c on c.idCriminoso = Criminoso.id) on cc.idPessoa = Pessoa.id";
		List<Criminoso> lista = new ArrayList<>();
		Conexao con = new Conexao();
		con.conectar();
		ResultSet result;
		try {
			result = con.consultar(sql);
			
			while(result.next()) {
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
		
		return null;
	}
	
	@Deprecated
	public static ArrayList<Vitima> getVitimasDB(int idCrime){
		return null;
	}
	@Deprecated	
	public static ArrayList<Arma> getArmasDB(int idCrime){
		return null;
	}
	@Deprecated
	public static ArrayList<Lei> getLeisDB(int idCrime){
		return null;
	}
	@Deprecated	
	public static Endereco getEnderecoDB(int idCrime) {
		return null;
	}
	
	private static ArrayList<Crime> resultSetToCrime(ResultSet result) throws SQLException {
		ArrayList<Crime> resultados = new ArrayList<Crime>();
		
		while(result.next()) {
			Crime c = new Crime();
			c.setId(result.getInt("id"));
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
			c.setLocal(getEnderecoDB(c.getId()));
			c.setVitimas(getVitimasDB(c.getId()));
			c.setCriminosos(getCriminososDB(c.getId()));
			c.setArmas(getArmasDB(c.getId()));
			c.setLeis(getLeisDB(c.getId()));
			
		}
		
		return resultados;
	}

	

	
	
	
}