package br.ufc.quixada.model;


public class Vitima extends Pessoa{
	
	private String estadoCorpo;
	
	public Vitima(int id, String nome, String sobrenome, Endereco endereco, String estadoCorpo) {
		super(id, nome, sobrenome, endereco);
		this.estadoCorpo = estadoCorpo;
		
	}

	public String getEstadoCorpo() {
		return estadoCorpo;
	}

	public void setEstadoCorpo(String estadoCorpo) {
		this.estadoCorpo = estadoCorpo;
	}
	
	
}
