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
	
	

	public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.id = -1;
		this.estadoid = -1;
		this.cidadeid = -1;
		this.bairroid = -1;
		this.ruaid = -1;
	}



	public Endereco () {
		this.id = -1;
		this.estadoid = -1;
		this.cidadeid = -1;
		this.bairroid = -1;
		this.ruaid = -1;
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + bairroid;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + cidadeid;
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + estadoid;
		result = prime * result + id;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result + ruaid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (bairroid != other.bairroid)
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cidadeid != other.cidadeid)
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (estadoid != other.estadoid)
			return false;
		if (id != other.id)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (ruaid != other.ruaid)
			return false;
		return true;
	}
	
	

}
