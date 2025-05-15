package controller;

import java.util.Scanner;

import model.API;

public class Main 
{
	public static void main(String[] args) 
	{
		API api = API.getObject();
		
		String color;
		Scanner scanner = new Scanner(System.in);
		int[] origin, destiny;
		boolean flag;
		
		do
		{
			api.printBoard();
			color = strColor(api.getTurnColor());
			System.out.println("Vez do " + color);
			
			origin = getInput("Qual a posição de origem? ", scanner);
			destiny = getInput("Qual a posição de destino? ", scanner);
			
			flag = api.movePeca(origin[0], origin[1], destiny[0], destiny[1]);
			
			if (!flag) { System.out.println("Movimento inválido."); } 
			
			System.out.println();
			
		} while(true);
		
		// scanner.close();
	}
	
	private static String strColor(char color)
	{
		if (color == 'W') return "Branco";
		else return "Preto";
	}
	
	private static int[] getInput(String output, Scanner scanner)
	{
		int i, j;
		int[] input = new int[2];
		String temp;
		
		while(true)
		{
			System.out.print(output);
			
			// Tenta ler inteiros
			try {
				
				i = scanner.nextInt();
				temp = scanner.next(); j = getNumber(temp);
				
				scanner.nextLine(); // Descarta o \n
				
				if(i>=0 && i<=7 && j >=0 && j<=7)
				{
					input[0] = i; input[1] = j;
					return input;
				}
				else { System.out.println("Posição inválida."); }
				
			}
			
			// Se o usuário entrar com um valor que não possa ser inteiro
			catch (Exception e)
			{ 
				System.out.println("Erro: utilize apenas números inteiros."); 
				scanner.nextLine();
			}
		}
	}
	
	private static int getNumber(String text)
	{
		char temp = text.toLowerCase().charAt(0);
		return temp - 'a';
	}
	
}