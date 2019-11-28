package br.ufc.quixada.model;

public class Lei {
	private int id;
	private String descricao;
	
	
	
	public Lei(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
    public String toString() {
        return "LEI\n" +
                "Nº da Lei >> " + this.id + "\n" +
                "Descricao >> " + this.descricao + "\n";
    }
    
}
