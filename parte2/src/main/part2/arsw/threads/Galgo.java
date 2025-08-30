package main.part2.arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	private PauseControl control;

	public Galgo(Carril carril, String name, RegistroLlegada reg, PauseControl control) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		this.control=control;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {
			control.checkPaused();
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			
			if (paso == carril.size()) {						
				carril.finish();
				int ubicacion=regl.getUltimaPosicionAlcanzadaIncremento();

				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				if (ubicacion==1){
					regl.setGanador(this.getName());
				}

			}
		}
	}


	@Override
	public void run() {

		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



}
