package br.ufc.quixada.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Crime {
	private int id, enderecoid;
	private String descricao;
	private String dataOcorrencia;
	private LocalDateTime dataComunicacao;
	private boolean fragrante, consumado;
	private Endereco local;
	private List<Vitima> vitimas;
	private List<Criminoso> criminosos;
	private List<Arma> armas;
	private List<Lei> leis;

	
	
	
	public Crime(int id, int enderecoid, String descricao, String dataOcorrencia, LocalDateTime dataComunicacao,
			boolean fragrante, boolean consumado, Endereco local) {
		super();
		this.id = id;
		this.enderecoid = enderecoid;
		this.descricao = descricao;
		this.dataOcorrencia = dataOcorrencia;
		this.dataComunicacao = dataComunicacao;
		this.fragrante = fragrante;
		this.consumado = consumado;
		this.local = local;
	}

	public Crime() {
		super();
		this.vitimas = new ArrayList<>();
		this.criminosos = new ArrayList<>();
		this.armas = new ArrayList<>();
		this.leis = new ArrayList<>();
		this.id = -1;
		this.enderecoid = -1;
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

	public String getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(String dataOcorrencia) {
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


	public List<Lei> getLeis() {
		return leis;
	}

	public int getEnderecoid() {
		return enderecoid;
	}


	public void setEnderecoid(int enderecoid) {
		this.enderecoid = enderecoid;
	}


	public void setLeis(List<Lei> leis) {
		this.leis = leis;
	}


	
	@Override
    public String toString() {
		return 	"CRIME:\n" +
				"COD. >> " + this.id+ "\n" +
				"Data do Ocorrido >> " + this.dataOcorrencia.toString() + "\n" +
				"Data do Relato >> " + this.dataComunicacao.toString() + "\n" +
				"Fragrante? >> " + this.fragrante  + "\n" +
				"Consumado? >> " + this.consumado  + "\n" +
				"End. >> " + this.local.toString()  + "\n" + 
				"Vitima >> " + this.vitimas.toString()  + "\n" +
				"Suspeito/Investigado >> " + this.criminosos.toString()  + "\n"+ 
				"Arma >> " + this.armas.toString()  + "\n" +
				"Lei >> " + this.leis.toString()  + "\n" +
				"Descricao >> " + this.descricao  + "\n";

	}
	
	
	
	
	
	
	
	
}
