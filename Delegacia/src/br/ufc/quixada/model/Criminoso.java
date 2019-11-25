package br.ufc.quixada.model;

public class Criminoso extends Pessoa{
	private String escolaridade;

	public Criminoso(int id, String nome, String sobrenome, Endereco endereco, String escolaridade) {
		super(id, nome, sobrenome, endereco);
		this.escolaridade = escolaridade;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	
}
