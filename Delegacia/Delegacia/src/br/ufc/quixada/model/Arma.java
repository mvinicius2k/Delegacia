package br.ufc.quixada.model;

public class Arma {
    private String nome;
    private String descricao;
    
    public Arma(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    } 

    public Arma (){}

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ARMA DO CRIME\n" +
                "Nome da Arma >> " + this.nome + "\n" +
                "Descricao >> " + this.descricao + "\n";
    }
    
}