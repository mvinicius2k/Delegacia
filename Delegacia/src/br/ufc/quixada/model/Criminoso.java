package br.ufc.quixada.model;

import java.time.LocalDate;

public class Criminoso extends Pessoa {
	private String escolaridade;
	private int id; 

	public Criminoso(int id, String nome, String cpf, LocalDate dataNasc, Endereco endereco, char sexo,
			String escolaridade, int id2) {
		super(id, nome, cpf, dataNasc, endereco, sexo);
		this.escolaridade = escolaridade;
		this.id = id2;
	}

	public Criminoso(){}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
    public String toString() {
		return 	"Vitima.:\n" +
				"Nome Completo >> " + super.getNome() + "\n" +
				"CPF >> " + super.getCpf() + "\n" +
				"Data Nasc. >> " + super.getDataNasc().toString() + "\n" +
				"Sexo >> " + super.getSexo()  + "\n" +
				"End. >> " + super.getEndereco().toString()  + "\n" + 
				"Contatos >> " + super.getContato().toString()  + "\n" +
				"Escolaridade >> " + this.escolaridade + "\n";		
	}

	

	
	
}
