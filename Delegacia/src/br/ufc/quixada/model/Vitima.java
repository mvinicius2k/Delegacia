package br.ufc.quixada.model;

import java.time.LocalDate;

public class Vitima extends Pessoa {

	private String estadoCorpo;
	private int id;

	public Vitima(int id, String nome, String cpf, LocalDate dataNasc, Endereco endereco, char sexo,
			String estadoCorpo, int id2) {
		super(id, nome, cpf, dataNasc, endereco, sexo);
		this.estadoCorpo = estadoCorpo;
		this.id = id2;
	}
	
	
	

	public Vitima() {
		
	}




	public String getEstadoCorpo() {
		return estadoCorpo;
	}

	public void setEstadoCorpo(String estadoCorpo) {
		this.estadoCorpo = estadoCorpo;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPessoa() {
		return super.getId();
	}


	public void setIdPessoa(int id) {
		super.setId(id);
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
