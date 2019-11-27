package br.ufc.quixada.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Vitima;

public class VitimaDAO {
	
	
	@Deprecated
	public static  Vitima buscar(int id) {
		return null;
	}
	
	@Deprecated
	public static ArrayList<Vitima> buscar(String chave){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Vitima> buscarNome(String nome){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Vitima> buscarCpf(String nome){
		return null;
	}
	
	@Deprecated
	public static ArrayList<Vitima> buscarNome(LocalDate date){
		return null;
	}
	
	@Deprecated
	public static boolean cadastrar(Vitima v) {
		return false;
	}
	
	@Deprecated
	public static boolean editar(Vitima v) {
		return false;
	}
	
	@Deprecated
	public static boolean remover(int id) {
		return false;
	}
	
}
