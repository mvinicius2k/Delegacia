package br.ufc.quixada.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Crime;

public class CrimeDAO {
	
	Crime c;
	
	public ArrayList<Crime> buscar(String chave){
		try {
			ArrayList<Crime> resultados = new ArrayList<Crime>();
			
			ResultSet result;
			Conexao con = new Conexao();
			
			con.conectar();
				
			result = con.consultar("select * from Crime join (select id from crimesBusca where like '%" + chave.toLowerCase() + "%') b on Crime.id = b.id");
				
			while(result.next()) {
				Crime c = new Crime();
				c.setId(result.getInt("id"));
				c.setDescricao(result.getString("descricao"));
				//...
				c.setArmaid(result.getInt("vitimaid"));
				c.getCirminososid = result.getInt("criminosoid");
				c.getArmaid = result.getInt("armaid");
				c.getJulgamentoid = result.getInt("julgamentoid");
				
				resultados.add(c);
				
			}
			
			for(int i = 0; i < resultados.size(); i++) {
				Crime c = resultados
			}
			c.vitimas = getVitimasDB(vitimaid);
			c.criminosos = getCriminososDB(criminososid);
			c.armas = getArmasDB(armaid);
			c.julgamento = getJulgamentoDB(julgamentoid);
			
			con.desconectar();
			return resultados;
		} catch (Exception e) {
			
			return null;
		}
}
