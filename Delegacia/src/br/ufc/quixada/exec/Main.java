package br.ufc.quixada.exec;

import java.util.Scanner;

import br.ufc.quixada.controller.Menu;

public class Main {
	
	
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		Scanner sc = new Scanner(System.in);
		while(true) {
			menu.menuPrincipal();
			int op = sc.nextInt();
			
			switch (op) {
			case 1:
				menu.cadastrarBoletim();
				break;
			case 2:
				menu.buscaGeralBoletim();
				
				break;
			case 0:
				System.exit(0);
				break;

			default:
				break;
			}
			
		}

	}

}
