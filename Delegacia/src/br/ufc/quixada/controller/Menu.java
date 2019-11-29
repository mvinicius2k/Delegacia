package br.ufc.quixada.controller;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufc.quixada.dao.ArmaDAO;
import br.ufc.quixada.dao.CrimeDAO;
import br.ufc.quixada.dao.CriminosoDAO;
import br.ufc.quixada.dao.EnderecoDAO;
import br.ufc.quixada.dao.VitimaDAO;
import br.ufc.quixada.db.Conexao;
import br.ufc.quixada.model.Arma;
import br.ufc.quixada.model.Crime;
import br.ufc.quixada.model.Criminoso;
import br.ufc.quixada.model.Endereco;
import br.ufc.quixada.model.Lei;
import br.ufc.quixada.model.Vitima;

public class Menu {
	Scanner sc = new Scanner(System.in);
	Crime c = new Crime();
	CrimeDAO boletim;
	public void menuPrincipal () {
		System.out.println("1 - Cadastrar Boletim (B.O)"+"\n"+
				"2 - Consultar Boletins\n0 - Sair do sistema");
				
	}	
	
	boolean booltoint(int op) {
		if(op == 1) return true;
		else return false;
	}
	
	public void cadastrarBoletim() {
		cadastrarBoletim(-1);
	}
	
	public void cadastrarBoletim(int id) {
		ArrayList<Vitima> vitimas = new ArrayList<Vitima>();
		ArrayList<Criminoso> criminoso = new ArrayList<>();
		
		System.out.println("Ha suspeitos no crime? (1) Sim (2) Nao");
		boolean boolLoop = booltoint(sc.nextInt()); sc = new Scanner(System.in);
		
		while(boolLoop == true) {
			
			
			
			criminoso.add(receberCriminoso());
			System.out.println("Adicionar mais um suspeito? (1) Sim (2) Nao");
			boolLoop = booltoint(sc.nextInt()); sc = new Scanner(System.in);
			
		}
		
		boolLoop = true;
		
		while(boolLoop == true) {
			vitimas.add(receberVitima());
			System.out.println("Adicionar mais uma Vitima? (1) Sim (2) Nao");
			boolLoop = booltoint(sc.nextInt()); sc = new Scanner(System.in);
			
		}
		
		
		
		
		Arma a = new Arma(receberArma(),"");
		hr();
		System.out.println("Local do crime");
		Endereco e = receberEndereco();
		hr();
		c.setDataOcorrencia(receberDataOC());
		c.setDataComunicacao(LocalDateTime.now());
		
		System.out.println("Consumado? (1) Sim (2) Nao");
		int str1 = sc.nextInt(); sc = new Scanner(System.in);
		c.setConsumado(booltoint(str1));
		System.out.println("Pego em flagrante? (1) Sim (2) Nao");
		int str2 = sc.nextInt(); sc = new Scanner(System.in);
		c.setFragrante(booltoint(str2));
		System.out.println("Descricao");
		String str = sc.nextLine(); sc = new Scanner(System.in);
		
		c.setDescricao(str);
		
		ArmaDAO.cadastrar(a);
		EnderecoDAO.cadastrar(e);
		
		
		
		c.setLocal(e);
		c.setVitimas(vitimas);
		c.setCriminosos(criminoso);
		c.getArmas().add(a);
		if(id != -1) {
			c.setId(id);
			CrimeDAO.editar(c);
			System.out.println("Edicao feita com sucesso");
			return;
		} else {
			CrimeDAO.cadastrar(c);
			
			System.out.println("Cadastro concluido");
		}
		
		
	}
	
	
	public void listarBoletins() {
		
		ArrayList<Crime> lista = boletim.buscar("");

		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).toString();			
		}
		
}
	
	public Crime buscaGeralBoletim() {
		Crime crimeAtual;
		
		System.out.println("1 >> Para buscar por data de ocorrencia"+"\n"+
				"2 >> Para buscar por palavra chave"+ "\n");
		int c = sc.nextInt(); sc = new Scanner(System.in);
		System.out.println("\n" + "Buscar por: >> ");
		sc = new Scanner(System.in);
		String ler = sc.nextLine(); sc = new Scanner(System.in); sc = new Scanner(System.in);
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		
		
		switch (c) {
		case 1:
			crimes = CrimeDAO.buscarDataOcorrencia(ler);
			break;
		case 2:
			crimes = CrimeDAO.buscar(ler);
			break;
		default:
			System.out.println("\n" + "ESCOLHA INVALIDA");
			buscaGeralBoletim();
			break;
		}
		
		
			
			
			
			
			hr();
			for(int i = 0; i < crimes.size(); i++) {
				System.out.println(crimes.toString());
				
			}
			
			if(crimes.isEmpty()) {
				System.out.println("Nenhum crime encontrado");
				hr();
				do {
					System.out.println("N - Nova busca\t0 - Sair");
					sc = new Scanner(System.in);
					ler = sc.nextLine(); sc = new Scanner(System.in); sc = new Scanner(System.in);
					switch (ler.toUpperCase()) {
					case "N":
						return buscaGeralBoletim();
						
					case "0":
						return null;

					default:
						break;
					}
				} while(true);
				
				
			}
			
			hr();
			System.out.println("Busca concluida\n\nE - Editar\tR - Remover\t0 - Sair");
			sc = new Scanner(System.in);
			ler = sc.nextLine(); sc = new Scanner(System.in); sc = new Scanner(System.in);
			
			switch (ler.toUpperCase()) {
			case "E":
				System.out.println("Digite o codigo do crime que desejas editar");
				ler = sc.nextLine(); sc = new Scanner(System.in); sc = new Scanner(System.in);
				int editId = Integer.valueOf(ler);				
				
				
				cadastrarBoletim(editId);
				//System.out.println("Edicao feita com sucesso");
				return null;
				
			case "R":
				System.out.println("Digite o codigo do crime que desejas remover");
				ler = sc.nextLine(); sc = new Scanner(System.in); sc = new Scanner(System.in);
				ArrayList<Integer> ids = new ArrayList<Integer>();
				ids.add(Integer.valueOf(ler));
				CrimeDAO.remover(ids);
				System.out.println("remocao feita com sucesso");
				break;
			case "0" :
				return null;
				
			default:
				return null;
				
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
		int sel = sc.nextInt(); sc = new Scanner(System.in);
		
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
		int sel = sc.nextInt(); sc = new Scanner(System.in);
		
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
		ano = sc.nextInt(); sc = new Scanner(System.in);
		System.out.println("\n" + "Digite o Mes(1 - 12) >> ");
		mes = sc.nextInt(); sc = new Scanner(System.in);
		System.out.println("\n" + "Digite o Dia(1 - 31) >> ");
		dia = sc.nextInt(); sc = new Scanner(System.in);
		LocalDate data = LocalDate.of(ano,mes,dia);		
		return data;
		
	}
	
	public String receberNomeCompleto() {
		String nome;
		System.out.println("\n" + "Digite o nome >> ");
		nome = sc.nextLine(); sc = new Scanner(System.in);
		return nome;
		
	}
	
	public String receberCPF() {
		String cpf;
		System.out.println("\n" + "Digite o CPF (no Padrao) >> ");
		cpf = sc.nextLine(); sc = new Scanner(System.in);
		return cpf;
		
	}
	
	public String receberArma() {
		String arma;
		System.out.println("\n" + "Digite o nome da Arma >> ");
		arma = sc.nextLine(); sc = new Scanner(System.in);
		return arma;
		
	}
	
	public int receberLei() {
		int id;
		System.out.println("\n" + "Digite o numero da lei >>" );
		id = sc.nextInt(); sc = new Scanner(System.in);
		return id;
	}
	
	public String receberDataOC() {
		System.out.println("\n" + "Digite a data(e tempo) do ocorrido");
		String data = sc.nextLine(); sc = new Scanner(System.in);
		return data;
	}
	
	public Endereco receberEndereco() {
		String estado, cidade, bairro, rua, numero, complemento;
		System.out.println("\n" + "Preencha as informacoes (coloque >> ? << p/ nulo)");
		System.out.println("\n "+ "Estado?");
		estado = sc.nextLine(); sc = new Scanner(System.in);
		System.out.println("\n "+ "Cidade?");
		cidade = sc.nextLine(); sc = new Scanner(System.in);
		System.out.println("\n "+ "Bairro?");
		bairro = sc.nextLine(); sc = new Scanner(System.in);
		System.out.println("\n "+ "Rua?");
		rua = sc.nextLine(); sc = new Scanner(System.in);
		System.out.println("\n "+ "Numero?");
		numero = sc.nextLine(); sc = new Scanner(System.in);
		System.out.println("\n "+ "Complemento?");
		complemento = sc.nextLine(); sc = new Scanner(System.in);
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
		String des = sc.nextLine(); sc = new Scanner(System.in);
		return des;
	}
	
	public String receberEscolaridade(){
		System.out.println("\n" + "Digite a Escolaridade >> ");
		String des = sc.nextLine(); sc = new Scanner(System.in);
		return des;
	}
	
	public String receberDescricaoCorpo() {
		System.out.println("\n" + "Digite a Descriçao do Estado do Corpo >> ");
		String des = sc.nextLine(); sc = new Scanner(System.in);
		return des;
	}
	
	public boolean flag() {
		System.out.println("\n" + "Flagrante? (1 (SIM) ou 2 (NÃO)");
		int c = sc.nextInt(); sc = new Scanner(System.in);
		if (c == 1) return true;
		else return false;
	}
	
	public boolean consumado() {
		System.out.println("\n" + "Consumado? (1 (SIM) ou 2 (NÃO)");
		int c = sc.nextInt(); sc = new Scanner(System.in);
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
		int scan = sc.nextInt(); sc = new Scanner(System.in);
		if (scan == 1) return 'F';
		else return 'M';
	}
	
	public void hr() {
		System.out.println("------------------------");
	}
	
	public Vitima receberVitima() {
		
		System.out.println("Digite o nome da vitima");
		String bnome = sc.nextLine(); sc = new Scanner(System.in);
		ArrayList<Vitima> c = VitimaDAO.buscarNome(bnome);
		hr();
		for(int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i).toString());
			
		}
		
		
		if(c.isEmpty()) {
			
			System.out.println("Nenhuma vitima encontrada no historico do sistema com esse nome.");
			hr();
			System.out.println("C - cadastrar uma vitima nova \t V - Nova busca");
		} else {
			hr();
			System.out.println("Digite o codigo da vitima\tou\tC - Cadastrar nova vitima");
		}
		
		
		
		
		String newId = sc.nextLine(); sc = new Scanner(System.in);
		if(newId.equalsIgnoreCase("V")) return receberVitima();
		if(!newId.equalsIgnoreCase("C")) {
			try{
				return VitimaDAO.buscar(Integer.valueOf(newId));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
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
		
		VitimaDAO.cadastrar(novaVitima);
		
		String sql = "select MAX(codVitima) from Vitima";
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				novaVitima.setId(result.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
		return novaVitima;
	}
	
	public Criminoso receberCriminoso() {
		
		
		System.out.println("Digite o nome do suspeito");
		String bnome = sc.nextLine(); sc = new Scanner(System.in);
		ArrayList<Criminoso> criminoso = CriminosoDAO.buscarNome(bnome);
		System.out.println("------------------------");
		for(int i = 0; i < criminoso.size(); i++) {
			System.out.println(criminoso.get(i).toString());
			
		}
		
		
		if(criminoso.isEmpty()) {
			
			System.out.println("Nenhum criminoso encontrado no historico do sistema com esse nome.");
			hr();
			System.out.println("C - cadastrar um suspeito novo \t V - Nova busca");
		} else {
			hr();
			System.out.println("Digite o codigo do suspeito\tou\tC - Cadastrar novo suspeito\tV - Nova busca");;
		}
			
			
		
		
		
		String newId = sc.nextLine(); sc = new Scanner(System.in);
		if(newId.equalsIgnoreCase("V")) return receberCriminoso();
		if(!newId.equalsIgnoreCase("C")) {
			try{
				return CriminosoDAO.buscar(Integer.valueOf(newId));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
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
		
		CriminosoDAO.cadastrar(novoCriminoso);
		String sql = "select MAX(codCriminoso) from Criminoso";
		Conexao con = new Conexao();
		
		con.conectar();
		
		try {
			ResultSet result = con.consultar(sql);
			
			while(result.next()) {
				novoCriminoso.setId(result.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
		
		return novoCriminoso;
	}
	
	
	
	public Arma instanciarArma() {
		String nome;
		System.out.println("\n" + "Digite o nome da Arma >> ");
		nome = sc.nextLine(); sc = new Scanner(System.in);		
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
		int ler = sc.nextInt(); sc = new Scanner(System.in);
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
		int cmd = sc.nextInt(); sc = new Scanner(System.in);
		if (cmd ==  1) {
			comando();
		}else {
			System.exit(0);
		}

		
	}

	

	
}
