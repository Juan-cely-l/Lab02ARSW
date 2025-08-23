package main.java.edu.eci.arsw;


import main.java.edu.eci.arsw.threads.PrimeFinderThread;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int r = (Integer.MAX_VALUE / 3) * 2;
		
		PrimeFinderThread pft=new PrimeFinderThread(0, (200000000/3)-1);
		PrimeFinderThread ptf2=new PrimeFinderThread(200000000/3,2*(200000000/3));
		PrimeFinderThread ptf3=new PrimeFinderThread(2*(200000000/3)+1,200000000);
		
		pft.start();
		ptf2.start();
		ptf3.start();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Presiona enter para reanudar ....... " );
		scanner.nextLine();
		pft.resumeThread();
		ptf2.resumeThread();
		ptf3.resumeThread();

	}
}
	

