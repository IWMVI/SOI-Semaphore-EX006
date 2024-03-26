package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	private Semaphore saque;
	private Semaphore deposito;
	private String opcaoEscolhida;
	private int cliente;
	private int opcaoNumero;
	private static int saldo;

	public ThreadBanco(int cliente, Semaphore saque, Semaphore deposito) {
		this.cliente = cliente;
		this.saque = saque;
		this.deposito = deposito;
	}

	@Override
	public void run() {
		iniciarSistema();
	}

	private void iniciarSistema() {
		difinirOperacao();
		if (opcaoNumero == 1) {
			try {
				saque.acquire();
				iniciarOperacao();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				saque.release();
			}
			iniciarOperacao();
		} else {
			try {
				deposito.acquire();
				iniciarOperacao();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				deposito.release();
			}
		}

	}

	private void difinirOperacao() {
		int aux = (int) ((Math.random() * 2) + 1);
		if (aux == 1) {
			opcaoEscolhida = "Saque";
			opcaoNumero = 1;
		} else {
			opcaoEscolhida = "Deposito";
			opcaoNumero = 2;
		}
	}

	private void iniciarOperacao() {
		try {
			sleep(1000);
			if (opcaoNumero == 1) {
				int valor = (int) (Math.random() * 1001);
				saldo -= valor;
				System.out.println("Cliente #" + cliente + " realizou um saque de R$ " + valor);
			} else {
				int valor = (int) (Math.random() * 1001);
				saldo += valor;
				System.out.println("Cliente #" + cliente + " realizou um depósito de R$ " + valor);
			}
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
