package main.part2.arsw.threads;

public class PauseControl {
    private boolean paused = false;

    public synchronized void pauseAll() {
        paused = true;
    }

    public synchronized void resumeAll() {
        paused = false;
        notifyAll();
    }

    public synchronized void checkPaused() throws InterruptedException {
        while (paused) {
            wait();
        }
    }
}
