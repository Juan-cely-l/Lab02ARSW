package main.java.edu.eci.arsw;

import main.java.edu.eci.arsw.threads.PrimeFinderThread;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		int r = (Integer.MAX_VALUE / 3) * 2;
		PrimeFinderThread pft1=new PrimeFinderThread(0, (200000000/3)-1);
		PrimeFinderThread pft2=new PrimeFinderThread(200000000/3,2*(200000000/3));
		PrimeFinderThread pft3=new PrimeFinderThread(2*(200000000/3)+1,200000000);
		pft1.start();
		pft2.start();
		pft3.start();

		Timer timer = new Timer();
		Scanner scanner = new Scanner(System.in);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// Interrumpe los hilos
				pft1.pauseThread();
				pft2.pauseThread();
				pft3.pauseThread();
				int totalPrimes = pft1.getPrimes() + pft2.getPrimes() + pft3.getPrimes();
				System.out.println("El numero de primos encontrados hasta el momento es de: " + totalPrimes);
				System.out.println("Presione Enter para continuar.");

				String isEnter = scanner.nextLine();
				while (!isEnter.equals("")) {
					System.out.println("Presione Enter para continuar.");
					isEnter = scanner.nextLine();
				}
				pft1.resumeThread();
				pft2.resumeThread();
				pft3.resumeThread();

				timer.cancel(); // Detiene el temporizador
			}
		}, 5000);
	}
}
	

