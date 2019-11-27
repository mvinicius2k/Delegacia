package br.ufc.quixada.model;

public class Endereco {
	private String estado, cidade, bairro, rua, numero, complemento;
	private int id, estadoid, cidadeid, bairroid, ruaid;

	
	
	
	
	public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento, int id,
			int estadoid, int cidadeid, int bairroid, int ruaid) {
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.id = id;
		this.estadoid = estadoid;
		this.cidadeid = cidadeid;
		this.bairroid = bairroid;
		this.ruaid = ruaid;
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
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(int estadoid) {
		this.estadoid = estadoid;
	}

	public int getCidadeid() {
		return cidadeid;
	}

	public void setCidadeid(int cidadeid) {
		this.cidadeid = cidadeid;
	}

	public int getBairroid() {
		return bairroid;
	}

	public void setBairroid(int bairroid) {
		this.bairroid = bairroid;
	}

	public int getRuaid() {
		return ruaid;
	}

	public void setRuaid(int ruaid) {
		this.ruaid = ruaid;
	}

	@Override
    public String toString() {
        return 	"END.:\n" +
				"Rua >> " + this.rua + "\n" +
				"Número >> " + this.numero + "\n" +
				"Bairro >> " + this.bairro + "\n" +
				"Cidade >> " + this.cidade  + "\n" + 
				"Estado >> " + this.estado  + "\n" +
				"Complemento >> " + this.complemento  + "\n";
	}

}
