package main.java.edu.eci.arsw.threads;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread {
	int a,b;
	private final List<Integer> primes=new LinkedList<Integer>();
	private Integer totalPrimes = 0;
	private volatile boolean paused = false;

	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public void run() {
		for (int i = a; i <= b; i++) {
			synchronized (this) {
				while (paused) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
			}
			if (isPrime(i)){
				primes.add(i);
				totalPrimes++;
				System.out.println(i);
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public Integer getPrimes() {
		return totalPrimes;
	}

	public void pauseThread() {
		paused = true;
	}

	public synchronized void resumeThread() {
		paused = false;
		notifyAll();
	}
}


