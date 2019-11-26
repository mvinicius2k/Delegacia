package br.ufc.quixada.model;

public class Endereco {
	private String estado, cidade, bairro, rua, numero, complemento;

	
	
	public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
		
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public Endereco () {
		
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	@Override
    public String toString() {
        return 	"END.:\n" +
				"Rua >> " + this.rua + "\n" +
				"Nº >> " + this.numero + "\n" +
				"Bairro >> " + this.bairro + "\n" +
				"Cidade >> " + this.cidade  + "\n" + 
				"Estado >> " + this.estado  + "\n" +
				"Complemento >> " + this.complemento  + "\n";
	}

}
