package br.ufc.quixada.model;

import java.time.LocalDate;

public class Vitima extends Pessoa {

	private String estadoCorpo;

	public Vitima(int id, String nome, String cpf, LocalDate dataNasc, Endereco endereco, char sexo,
			String estadoCorpo) {
		super(id, nome, cpf, dataNasc, endereco, sexo);
		this.estadoCorpo = estadoCorpo;
	}
	

	public String getEstadoCorpo() {
		return estadoCorpo;
	}

	public void setEstadoCorpo(String estadoCorpo) {
		this.estadoCorpo = estadoCorpo;
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
				"Estado do Corpo >> " + this.estadoCorpo + "\n";
	}
	
}
