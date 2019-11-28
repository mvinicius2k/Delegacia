package br.ufc.quixada.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufc.quixada.dao.CrimeDAO;
import br.ufc.quixada.model.Arma;
import br.ufc.quixada.model.Crime;
import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Endereco;
import br.ufc.quixada.model.Lei;
import br.ufc.quixada.model.Vitima;

public class Menu {
	Scanner sc = new Scanner(System.in);
	
	CrimeDAO boletim;
	public void menuPrincipal () {
		System.out.println("1 >> Cadastrar Boletim (B.O)"+"\n"+
				"2 >> Buscar Boletim"+ "\n"+
				"3 >> Editar Boletim"+ "\n"+
				"4 >> Remover Boletim"+ "\n"+
				"5 >> Finalizar"+"\n");
	}	
	
	
	public void cadastrarBoletim() {
		
		
	}
	
	public void listarBoletins() {
		ArrayList<Crime> lista = boletim.buscar("");

		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).toString();			
		}
		
}
	
	public Crime buscaGeralBoletim() {
		Crime crimeAtual;
		
		System.out.println("1 >> Para buscar por ID diretamente"+"\n"+
				"2 >> Para buscar por palavra chave"+ "\n");
		int c = sc.nextInt();
		
		if(c == 1) {
			listarBoletins();
			System.out.println("\n" + "Digite o ID desejado >> ");
			int l = sc.nextInt();
			crimeAtual = buscaBoletimID(l);
			return crimeAtual;
		}
		
		if(c == 2) {
			System.out.println("\n" + "BUSCA POR PALAVRAS CHAVES");
			System.out.println("\n" + "DIGITE A SUA BUSCA >> ");
			String ler = sc.nextLine();
			crimeAtual = buscaBoletimKey(ler);
			return crimeAtual;
		}
		else if(c != 1 || c != 2) {
			System.out.println("\n" + "ESCOLHA INVALIDA");
			buscaGeralBoletim();
		}
		return null;
		
		
	}
	
	public Crime buscaBoletimID(int id) {
		Crime boletimAtual = boletim.buscar(id);
		return boletimAtual;
	}
	
	public Crime buscaBoletimKey(String key) {
		ArrayList<Integer> idLista = new ArrayList<>();
		ArrayList<Crime> lista = boletim.buscar(key);
		
		
		int id = -420000; //guarda o id atual 
		for (int i = 0; i < lista.size(); i++) {//imprime todos crimes que contem a string ler
			lista.get(i).toString();  //imprime todos crimes que contem a string ler
			
			id = lista.get(i).getId();
			idLista.add(id); //guarda todos os IDs dos crimes encontrados
		}
		
		System.out.println("\n" + "DIGITE o ID do Crime Desejado >> ");
		int sel = sc.nextInt();
		
		for (int i = 0; i <idLista.size(); i++) {
			if (sel == idLista.get(i)){
				Crime crime = boletim.buscar(sel);  
				return crime;
			}
		}
		
		return null;
		
	}
	
	public int buscaBoletimAlterar() {
		Crime crimeAtual = buscaGeralBoletim();
		if(crimeAtual != null) return crimeAtual.getId();
		else return -1;
	}
	
	
	public Vitima buscarVitima() {
		String nome = receberNomeCompleto();
		String cpf  = receberCPF();
		LocalDate data = receberData();
		
		ArrayList<Crime> lista = boletim.buscar("");

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getVitimas().get(i).getNome() == nome) {
				if (lista.get(i).getVitimas().get(i).getCpf() == cpf) {
					if (lista.get(i).getVitimas().get(i).getDataNasc() == data) {
						return lista.get(i).getVitimas().get(i);
					}
				}
			}
		}
		
		
		return null;
 	}
	
	
	public Criminoso buscarSuspeito() {
		String nome = receberNomeCompleto();
		String cpf  = receberCPF();
		LocalDate data = receberData();
		
		ArrayList<Crime> lista = boletim.buscar("");

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCriminosos().get(i).getNome() == nome) {
				if (lista.get(i).getCriminosos().get(i).getCpf() == cpf) {
					if (lista.get(i).getCriminosos().get(i).getDataNasc() == data) {
						return lista.get(i).getCriminosos().get(i);
					}
				}
			}
		}
		
		
		return null;
 	}
	
	public Crime buscaPorArma() {
		String arma = receberArma();
		return buscaBoletimKey(arma);		
	}
	
	public Crime buscaPorLei() {
		int lei = receberLei();
		ArrayList<Integer> idLista = new ArrayList<>();
		ArrayList<Crime> lista = boletim.buscar("");
		
		
		int id = -42000000; //guarda o id atual 
		for (int i = 0; i < lista.size(); i++) {//imprime todos crimes que contem a string ler
			if((lista.get(i).getLeis().get(i).getId()) == lei);  //imprime todos crimes que contem a string ler
			
			id = lista.get(i).getId();
			idLista.add(id); //guarda todos os IDs dos crimes encontrados
		}
		
		System.out.println("\n" + "DIGITE o ID do Crime Desejado >> ");
		int sel = sc.nextInt();
		
		for (int i = 0; i <idLista.size(); i++) {
			if (sel == idLista.get(i)){
				Crime crime = boletim.buscar(sel);  
				return crime;
			}
		}
		
		return null;
		
	}
	
	public Crime buscaPorData() {
		String data = receberDataOC();
		return buscaBoletimKey(data);
	}
	
	
	public LocalDate receberData() {
		int ano, mes, dia;
		System.out.println("\n" + "Digite o Ano >> ");
		ano = sc.nextInt();
		System.out.println("\n" + "Digite o Mês(1 - 12) >> ");
		mes = sc.nextInt();
		System.out.println("\n" + "Digite o Dia(1 - 31) >> ");
		dia = sc.nextInt();
		LocalDate data = LocalDate.of(ano,mes,dia);		
		return data;
		
	}
	
	public String receberNomeCompleto() {
		String nome;
		System.out.println("\n" + "Digite o nome >> ");
		nome = sc.nextLine();
		return nome;
		
	}
	
	public String receberCPF() {
		String cpf;
		System.out.println("\n" + "Digite o CPF (no Padrão) >> ");
		cpf = sc.nextLine();
		return cpf;
		
	}
	
	public String receberArma() {
		String arma;
		System.out.println("\n" + "Digite o nome da Arma >> ");
		arma = sc.nextLine();
		return arma;
		
	}
	
	public int receberLei() {
		int id;
		System.out.println("\n" + "Digite o nº da lei >>" );
		id = sc.nextInt();
		return id;
	}
	
	public String receberDataOC() {
		System.out.println("\n" + "Digite a data(e tempo) do ocorrido");
		String data = sc.nextLine();
		return data;
	}
	
	public Endereco receberEndereco() {
		String estado, cidade, bairro, rua, numero, complemento;
		System.out.println("\n" + "Preencha as informações (coloque >> ? << p/ nulo)");
		System.out.println("\n "+ "Estado?");
		estado = sc.nextLine();
		System.out.println("\n "+ "Cidade?");
		cidade = sc.nextLine();
		System.out.println("\n "+ "Bairro?");
		bairro = sc.nextLine();
		System.out.println("\n "+ "Rua?");
		rua = sc.nextLine();
		System.out.println("\n "+ "Número?");
		numero = sc.nextLine();
		System.out.println("\n "+ "Complemento?");
		complemento = sc.nextLine();
		Endereco local = new Endereco(estado, cidade, bairro, rua, numero, complemento);
		return local;
	}
	 
	public int selecionarEndereco() {
		Endereco end = receberEndereco();
		ArrayList<Integer> idLista = new ArrayList<>();
		ArrayList<Crime> lista = boletim.buscar("");
		
		
		int id = -42000000; //guarda o id atual 
		for (int i = 0; i < lista.size(); i++) {//imprime todos crimes que contem a string ler
			if((lista.get(i).getLocal().equals(end)));  //imprime todos crimes que contem a string ler
			lista.get(i).getLocal().toString();
			return lista.get(i).getLocal().getId();
		}
		
		return -1;
	}
	
	public String receberDescricao() {
		System.out.println("\n" + "Digite a Descriçao >> ");
		String des = sc.nextLine();
		return des;
	}
	
	public String receberEscolaridade(){
		System.out.println("\n" + "Digite a Escolaridade >> ");
		String des = sc.nextLine();
		return des;
	}
	
	public String receberDescricaoCorpo() {
		System.out.println("\n" + "Digite a Descriçao do Estado do Corpo >> ");
		String des = sc.nextLine();
		return des;
	}
	
	public boolean flag() {
		System.out.println("\n" + "Flagrante? (1 (SIM) ou 2 (NÃO)");
		int c = sc.nextInt();
		if (c == 1) return true;
		else return false;
	}
	
	public boolean consumado() {
		System.out.println("\n" + "Consumado? (1 (SIM) ou 2 (NÃO)");
		int c = sc.nextInt();
		if (c == 1) return true;
		else return false;
	}
	
	
	public Crime receberCrime(int x, int y) {
		int id, enderecoid;
		String descricao;
		String dataOcorrencia;
		LocalDateTime dataComunicacao = null;
		boolean fragrante, consumado;
		Endereco local;
		
		id = x;
		enderecoid = y;
		descricao = receberDescricao();
		local = receberEndereco();
		fragrante = flag();
		consumado = consumado();
		dataOcorrencia = receberDataOC();
		dataComunicacao = LocalDateTime.now();
		
		
		Crime novoCrime = new Crime(id,enderecoid,descricao,dataOcorrencia,dataComunicacao, 
				fragrante,consumado,local);
		return novoCrime;
		
	}
	
	public char receberSexo() {
		System.out.println("\n"+ "Selecione o sexo: 1 (Feminino) e 2(Masculino) >> ");
		int scan = sc.nextInt();
		if (scan == 1) return 'F';
		else return 'M';
	}
	
	public Vitima receberVitima() {
		int id = -1, id2 = -1;
		String nome, cpf, estadoCorpo;
		LocalDate dataNasc;
		Endereco endereco;
		char sexo;
		
		nome = receberNomeCompleto();
		cpf = receberCPF();
		dataNasc = receberData();
		estadoCorpo = receberDescricaoCorpo();
		sexo = receberSexo();
		endereco = receberEndereco();
		
		Vitima novaVitima = new Vitima(id, nome, cpf, dataNasc, endereco, sexo,
				estadoCorpo, id2);
		
		
		return novaVitima;
	}
	
	public Criminoso receberCriminoso() {
		int id = -1, id2 = -1;
		String nome, cpf, escolaridade;
		LocalDate dataNasc;
		Endereco endereco;
		char sexo;
		
		nome = receberNomeCompleto();
		cpf = receberCPF();
		dataNasc = receberData();
		escolaridade = receberEscolaridade();
		sexo = receberSexo();
		endereco = receberEndereco();
		
		Criminoso novoCriminoso = new Criminoso(id, nome, cpf, dataNasc, endereco, sexo,
				escolaridade, id2);
		
		
		return novoCriminoso;
	}
	
	
	
	public Arma instanciarArma() {
		String nome;
		System.out.println("\n" + "Digite o nome da Arma >> ");
		nome = sc.nextLine();		
		String des = receberDescricao();
		Arma novaArma = new Arma(nome, des);
		return novaArma;
	}
	
	 public Lei instanciarLei(){
		 int id = receberLei();
		 String des = receberDescricao();
		 Lei novaLei = new Lei(id, des);
		 return novaLei;
	 }
	
	public void cadastrarCrime(int x, int y) {
		Crime novoCrime = receberCrime(x,y);
		Vitima vit = receberVitima();
		Criminoso crim = receberCriminoso();
		Arma arma = instanciarArma();
		Lei lei = instanciarLei();
		
		novoCrime.getArmas().add(arma);
		novoCrime.getLeis().add(lei);
		novoCrime.getCriminosos().add(crim);
		novoCrime.getVitimas().add(vit);
		
		if (boletim.cadastrar(novoCrime)){
			System.out.println("Cadastrado com Sucesso");
			novoCrime.toString();
			return;
		}
		System.out.println("\n"+"Erro ao cadastrar");

	}
	public Crime retornaCrime(int x, int y) {
		Crime novoCrime = receberCrime(x,y);
		Vitima vit = receberVitima();
		Criminoso crim = receberCriminoso();
		Arma arma = instanciarArma();
		Lei lei = instanciarLei();
		
		novoCrime.getArmas().add(arma);
		novoCrime.getLeis().add(lei);
		novoCrime.getCriminosos().add(crim);
		novoCrime.getVitimas().add(vit);
		
		return novoCrime;

	}
	
	public void editarBoletim() {
		int id  = buscaBoletimAlterar();
		Crime crimeAtual = buscaBoletimID(id);
		int end = crimeAtual.getEnderecoid();
		System.out.println("\n" + "Digite as informações atualizadas >>");
		crimeAtual = retornaCrime(id,end);
		if(boletim.editar(crimeAtual)) {
			System.out.println("\n"+"Editado com sucesso!");
		}
		else System.out.println("Erro");
		
		
			
	}
	
	public void removerBoletim() {
		int id = buscaBoletimAlterar();
		ArrayList <Integer> ls = new ArrayList<>();
		
		ls.add(id);
		
		boletim.remover(ls);
		
	}
	
	
	
	public void comando() {
		menuPrincipal();
		int ler = sc.nextInt();
		switch(ler){
			  case 1: cadastrarCrime(-1,-1);
			  		  voltar();
			  		  break;
			  case 2: buscaGeralBoletim();
			  		  voltar();
			  		  break;
			  case 3: editarBoletim();
			  		  voltar();
			  		  break;
			  case 4: removerBoletim();
			  		  voltar();
			  		  break;
			  case 5: System.exit(0);
			  default: System.out.println("Comando Invalido!");
			  		   voltar();
			  
		}
	}
	
	public void voltar() {
		System.out.println("1: Voltar p/ Menu"+"\n"+ "2: Finalizar");
		int cmd = sc.nextInt();
		if (cmd ==  1) {
			comando();
		}else {
			System.exit(0);
		}

		
	}

	

	
}
