package br.ufc.quixada.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;


import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Endereco;


public class EnderecoDAO {
	
	
	
	public static Endereco buscar(int id) {
		return getEnderecoDB(id);
		
	}
	
	
	public static int buscar(Endereco e){
		String sql = "select codEndereco from Endereco where idRua="+e.getRuaid()+" and idBairro="+e.getBairroid()+" and idCidade="+e.getCidadeid()+" and idUF="+e.getEstadoid()+" and numero="+Integer.parseInt(e.getNumero())+" and complemento='"+e.getComplemento()+"'";
		int id = -1;
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				id = result.getInt(1);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			id = -1;
		} finally {
			con.desconectar();
		}

		return id;
		
	}
	
	
	public static boolean cadastrar(Endereco e) {
		boolean retorno = true;
		Conexao con = new Conexao();
		
		try {
			con.conectar();
			ResultSet result;
			String sql = "";
			
			//UF
			sql = "select codUF from UF where nomeUF = '" + e.getEstado() + "'";
			result = con.consultar(sql);
			while(result.next())
				e.setEstadoid(result.getInt(1));
			
			if(e.getEstadoid() == -1) {
				PreparedStatement ptt = con.preInserir("insert into UF(nomeUF) values (?)");
				ptt.setString(1, e.getEstado());
				ptt.executeUpdate();
				ptt.close();
				
				result = con.consultar(sql);
				
				while(result.next())
					e.setEstadoid(result.getInt(1));
				
			}

			
			//Cidade
			sql = "select codCidade from Cidade where nomeCidade = '" + e.getCidade() + "' and idUF = " + e.getEstadoid();
			result = con.consultar(sql);
			while(result.next())
				e.setCidadeid(result.getInt(1));
			
			if(e.getCidadeid() == -1) {
				PreparedStatement ptt = con.preInserir("insert into Cidade(nomeCidade, idUF) values (?,?)");
				ptt.setString(1, e.getCidade());
				ptt.setInt(2, e.getEstadoid());
				ptt.executeUpdate();
				ptt.close();
				
				result = con.consultar(sql);
				
				while(result.next())
					e.setCidadeid(result.getInt(1));
				
			}
			
			//Bairro
			sql = "select codBairro from Bairro where nomeBairro = '" + e.getBairro() + "' and idCidade = '" + e.getCidadeid() + "'";
			result = con.consultar(sql);
			while(result.next())
				e.setBairroid(result.getInt(1));
			
			if(e.getBairroid() == -1) {
				PreparedStatement ptt = con.preInserir("insert into Bairro(nomeBairro,idCidade) values (?,?)");
				ptt.setString(1, e.getBairro());
				ptt.setInt(2, e.getCidadeid());
				ptt.executeUpdate();
				ptt.close();
				
				result = con.consultar(sql);
				
				while(result.next())
					e.setBairroid(result.getInt(1));
				
			}
			
			
			
			//Rua
			sql = "select codRua from Rua where nomeRua = '" + e.getRua() + "' and idBairro = '" + e.getBairroid() + "'";
			result = con.consultar(sql);
			while(result.next())
				e.setRuaid(result.getInt(1));
			
			if(e.getRuaid() == -1) {
				PreparedStatement ptt = con.preInserir("insert into Rua(nomeRua,idBairro) values (?,?)");
				ptt.setString(1, e.getRua());
				ptt.setInt(2, e.getBairroid());
				ptt.executeUpdate();
				ptt.close();
				
				result = con.consultar(sql);
				
				while(result.next())
					e.setRuaid(result.getInt(1));
				
			}
			
			//Endereco
			
			
			int id = buscar(e);
			if(id == -1) {
				int a = 1;
				sql = "insert into Endereco(idRua,idBairro,idCidade,idUF,numero,complemento) values (?,?,?,?,?,?)";
				PreparedStatement ptt = con.preInserir(sql);
				
				ptt.setInt(a++, e.getRuaid());
				ptt.setInt(a++, e.getBairroid());
				ptt.setInt(a++, e.getCidadeid());
				ptt.setInt(a++, e.getEstadoid());
				ptt.setInt(a++, Integer.parseInt(e.getNumero()));
				ptt.setString(a++, e.getComplemento());
				ptt.executeUpdate();
				
			}
			
			
				
		} catch (Exception ex) {
			retorno = false;
			ex.printStackTrace();
		} finally {
			con.desconectar();
		}
		
		return retorno;
		
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
			
			if(endereco.getId() == -1) {
				con.desconectar();
				return null;
			}
			
			result = con.consultar("select nomeRua from Rua where codRua = " + endereco.getRuaid());
			while(result.next()) endereco.setRua(result.getString(1));
			result = con.consultar("select nomeCidade from Cidade where codCidade = " + endereco.getCidadeid());
			while(result.next()) endereco.setCidade(result.getString(1));
			result = con.consultar("select nomeBairro from Bairro where codBairro = " + endereco.getBairroid());
			while(result.next()) endereco.setBairro(result.getString(1));
			result = con.consultar("select nomeUF from UF where codUF = " + endereco.getEstadoid());
			while(result.next()) endereco.setEstado(result.getString(1));
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			e = null;
		} finally {
			con.desconectar();		
		}
		
		return endereco;
		
		
		
	}
	
	
}
