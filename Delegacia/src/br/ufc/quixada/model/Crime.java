package br.ufc.quixada.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import br.ufc.quixada.db.Conexao;

public class Crime {
	private int id, vitimaid, cirminososid, julgamentoid, armaid;
	private String descricao;
	private LocalDateTime dataOcorrencia, dataComunicacao;
	private boolean fragrante, consumado;
	private Endereco local;
	private List<Vitima> vitimas;
	private List<Criminoso> criminosos;
	private List<Arma> armas;
	private List<Lei> julgamento;

	
	
	
	
	
	public Crime() {
		super();
		this.vitimas = new ArrayList<>();
		this.criminosos = new ArrayList<>();
		this.armas = new ArrayList<>();
		this.julgamento = new ArrayList();
		
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

	public int getVitimaid() {
		return vitimaid;
	}

	public void setVitimaid(int vitimaid) {
		this.vitimaid = vitimaid;
	}

	public int getCirminososid() {
		return cirminososid;
	}

	public void setCirminososid(int cirminososid) {
		this.cirminososid = cirminososid;
	}

	public int getJulgamentoid() {
		return julgamentoid;
	}

	public void setJulgamentoid(int julgamentoid) {
		this.julgamentoid = julgamentoid;
	}

	public List<Lei> getJulgamento() {
		return julgamento;
	}

	public void setJulgamento(List<Lei> julgamento) {
		this.julgamento = julgamento;
	}

	public int getArmaid() {
		return armaid;
	}

	public void setArmaid(int armaid) {
		this.armaid = armaid;
	}
	
	
	
	
	
	
	
	
}
