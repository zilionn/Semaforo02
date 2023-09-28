package controller;

import java.util.concurrent.Semaphore;

public class ControllerPessoa extends Thread{
	
	private Semaphore semaforo;
	private int pessoaId;
	
	public ControllerPessoa(int pessoaId, Semaphore semaforo) {
		this.pessoaId = pessoaId;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		caminharPessoa();
		try {
			semaforo.acquire();
			cruzarPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sairPessoa();
			semaforo.release();
		}

	}

	private void caminharPessoa() {
		int distPercorrida = 0;
		while (distPercorrida < 200) {
			int metrosPorSeg = (int)((Math.random() * 3) + 4); // 4 - 6 m.
			try {
				sleep(1000); // 1 s.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			distPercorrida = distPercorrida + metrosPorSeg > 200 ? 200 : (distPercorrida + metrosPorSeg);
			System.out.println("A pessoa #" + pessoaId + " percorreu " + distPercorrida + " m.");
		}
		System.out.println("A pessoa #" + pessoaId + " chegou na porta.");
	}

	private void cruzarPorta() {
		int tempoAbrindo = (int)((Math.random() * 1001) + 1000); // 1 - 2 s.
		System.out.println("A pessoa #" + pessoaId + " abriu a porta.");
		try {
			sleep(tempoAbrindo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sairPessoa() {
		System.out.println("A pessoa #" + pessoaId + " passou pela porta.");
	}
}