package br.ufc.quixada.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Vitima;

public class CriminosoDAO {
	
	
	@Deprecated
	public static Vitima buscar(int id) {
		return null;
	}
	
	@Deprecated
	public static ArrayList<Criminoso> buscar(String chave){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Criminoso> buscarNome(String nome){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Criminoso> buscarCpf(String nome){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Criminoso> buscarData(LocalDate date){
		return null;
	}
	
	@Deprecated
	public static boolean cadastrar(Criminoso v) {
		return false;
	}
	
	@Deprecated
	public static boolean editar(Criminoso v) {
		return false;
	}
	
	@Deprecated
	public static boolean remover(int id) {
		return false;
	}
	
}
