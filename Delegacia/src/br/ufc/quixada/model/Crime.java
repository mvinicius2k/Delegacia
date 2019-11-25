package br.ufc.quixada.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import br.ufc.quixada.db.Conexao;

public class Crime {
	private int id;
	private String descricao;
	private LocalDateTime dataOcorrencia, dataComunicacao;
	private boolean fragrante, consumado;
	private Endereco local;
	private List<Vitima> vitimas;
	private List<Criminoso> criminosos;
	private List<Arma> armas;
	private TreeMap<Integer, Lei> julgamento;
	
	
	
	
	
	public Crime() {
		super();
		this.vitimas = new ArrayList<>();
		this.criminosos = new ArrayList<>();
		this.armas = new ArrayList<>();
		this.julgamento = new TreeMap<Integer, Lei>();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public LocalDateTime getDataComunicacao() {
		return dataComunicacao;
	}

	public void setDataComunicacao(LocalDateTime dataComunicacao) {
		this.dataComunicacao = dataComunicacao;
	}

	public boolean isFragrante() {
		return fragrante;
	}

	public void setFragrante(boolean fragrante) {
		this.fragrante = fragrante;
	}

	public boolean isConsumado() {
		return consumado;
	}

	public void setConsumado(boolean consumado) {
		this.consumado = consumado;
	}

	public Endereco getLocal() {
		return local;
	}

	public void setLocal(Endereco local) {
		this.local = local;
	}

	public List<Vitima> getVitimas() {
		return vitimas;
	}

	public void setVitimas(List<Vitima> vitimas) {
		this.vitimas = vitimas;
	}

	public List<Criminoso> getCriminosos() {
		return criminosos;
	}

	public void setCriminosos(List<Criminoso> criminosos) {
		this.criminosos = criminosos;
	}

	public List<Arma> getArmas() {
		return armas;
	}

	public void setArmas(List<Arma> armas) {
		this.armas = armas;
	}

	public TreeMap<Integer, Lei> getJulgamento() {
		return julgamento;
	}

	public void setJulgamento(TreeMap<Integer, Lei> julgamento) {
		this.julgamento = julgamento;
	}

	

	public ArrayList<Crime> buscar(String chave){
		try {
			ArrayList<Crime> resultados = new ArrayList<Crime>();
			Conexao con = new Conexao();
			con.conectar();
			ResultSet result;
			
			result = con.consultar("select * from Crime join (select id from crimesBusca where like '%" + chave.toLowerCase() + "%') b on Crime.id = b.id");
			
			
			while(result.next()) {
				Crime c = new Crime();
				c.id = result.getInt("id");
				c.descricao = result.getString("descricao");
				//...
				c.
				
			}
			
			
			
			con.desconectar();
			return resultados;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
}
