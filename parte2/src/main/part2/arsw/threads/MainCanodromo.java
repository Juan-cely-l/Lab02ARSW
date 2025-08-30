package main.part2.arsw.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainCanodromo {
    private static Galgo[] galgos;
    private static Canodromo can;
    private static RegistroLlegada reg = new RegistroLlegada();
    private static PauseControl control = new PauseControl(); //  monitor único

    public static void main(String[] args) {
        can = new Canodromo(17, 100);
        galgos = new Galgo[can.getNumCarriles()];
        can.setVisible(true);

        can.setStartAction(e -> {
            ((JButton) e.getSource()).setEnabled(false);
            new Thread(() -> {
                for (int i = 0; i < can.getNumCarriles(); i++) {
                    galgos[i] = new Galgo(can.getCarril(i), "" + i, reg, control);
                    galgos[i].start();
                }
                for (Galgo galgo : galgos) {
                    try {
                        galgo.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                can.winnerDialog(reg.getGanador(), reg.getUltimaPosicionAlcanzada() - 1);
                System.out.println("El ganador fue: " + reg.getGanador());
            }).start();
        });

        can.setStopAction(e -> {
            control.pauseAll();
            System.out.println("Carrera pausada!");
        });

        can.setContinueAction(e -> {
            control.resumeAll();
            System.out.println("Carrera reanudada!");
        });
    }
}
