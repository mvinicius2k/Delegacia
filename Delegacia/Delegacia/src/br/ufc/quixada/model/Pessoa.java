package br.ufc.quixada.model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pessoa {
	private int id;
	private String nome, cpf;
	private LocalDate dataNasc;
	private Endereco endereco;
	private ArrayList<String> contato;
	private char sexo;
	
	public Pessoa(int id, String nome, String cpf, LocalDate dataNasc, Endereco endereco,
			char sexo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.sexo = sexo;
		this.contato = new ArrayList<>();
	}

	public Pessoa(){
		this.contato = new ArrayList<>();
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
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public ArrayList<String> getContato() {
		return contato;
	}

	public void setContato(ArrayList<String> contato) {
		this.contato = contato;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
    public String toString() {
		return 	"Pessoa.:\n" +
				"Nome Completo >> " + this.nome + "\n" +
				"CPF >> " + this.cpf + "\n" +
				"Data Nasc. >> " + this.dataNasc.toString() + "\n" +
				"Sexo >> " + this.sexo  + "\n" +
				"End. >> " + this.endereco.toString()  + "\n" + 
				"Contatos >> " + this.contato.toString()  + "\n";
	}

	

	
}
