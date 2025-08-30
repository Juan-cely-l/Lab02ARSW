package main.part2.arsw.threads;

public class RegistroLlegada {

	private int ultimaPosicionAlcanzada=1;
	private  final Object lockUltimaPosicionAlcanzada=new Object();

	private String ganador=null;
	
	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public int getUltimaPosicionAlcanzadaIncremento() {
		synchronized (lockUltimaPosicionAlcanzada){
			int pos=ultimaPosicionAlcanzada;
			ultimaPosicionAlcanzada++;
		return pos;}
	}

	public int getUltimaPosicionAlcanzada() {
		synchronized (lockUltimaPosicionAlcanzada) {
		return ultimaPosicionAlcanzada;}
	}

	public void setUltimaPosicionAlcanzada(int nuevaUltimaPosicionAlcanzada) {
		synchronized (lockUltimaPosicionAlcanzada) {
			this.ultimaPosicionAlcanzada = nuevaUltimaPosicionAlcanzada;
		}
	}



	
	
}
