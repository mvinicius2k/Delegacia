package br.ufc.quixada.model;

public abstract class Pessoa {
	private int id;
	private String nome, sobrenome;
	private Endereco endereco;
	
	
	
	public Pessoa(int id, String nome, String sobrenome, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.endereco = endereco;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
