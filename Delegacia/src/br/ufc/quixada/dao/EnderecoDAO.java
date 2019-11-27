package br.ufc.quixada.dao;


import java.sql.ResultSet;

import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Endereco;


public class EnderecoDAO {
	
	
	@Deprecated
	public static Endereco buscar(int id) {
		return null;
	}
	
	@Deprecated
	public static int buscar(Endereco e){
		//se existir
		//return id;
		//se nao existir
		return -1;
		
	}
	
	@Deprecated
	public static boolean cadastrar(Endereco e) {
		return false;
	}
	
	@Deprecated
	public static boolean editar(Endereco e) {
		return false;
	}
	
	@Deprecated
	public static boolean remover(int id) {
		return false;
	}
	
	
	public static Endereco getEnderecoDB(int idEndereco) {
		String sql = "select * from Endereco join (select idEndereco from Pessoa where codPessoa = " + idEndereco + ") e on e.idEndereco = codEndereco";
		Endereco endereco = new Endereco();
		Conexao con = new Conexao();
		con.conectar();
		
		try {
			
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				endereco.setId(result.getInt("codEndereco"));
				endereco.setRuaid(result.getInt("idRua"));
				endereco.setBairroid(result.getInt("idBairro"));
				endereco.setCidadeid(result.getInt("idCidade"));
				endereco.setEstadoid(result.getInt("idUF"));
				endereco.setNumero(String.valueOf(result.getInt("numero")));
				endereco.setComplemento(result.getString("complemento"));
				
			}
			
			result = con.consultar("select nomeRua from Rua where codRua = " + endereco.getRuaid());
			while(result.next()) endereco.setRua(result.getString(1));
			result = con.consultar("select nomeCidade from Cidade where codRua = " + endereco.getCidadeid());
			while(result.next()) endereco.setCidade(result.getString(1));
			result = con.consultar("select nomeUF from UF where codRua = " + endereco.getEstadoid());
			while(result.next()) endereco.setEstado(result.getString(1));
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.desconectar();		
		}
		
		return endereco;
		
		
		
	}
	
	
}
