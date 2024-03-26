package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Principal {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for(int i = 0; i < 20; i++) {
			Thread t = new ThreadBanco(i, semaforo, semaforo);
			t.start();
		}
	}
}
