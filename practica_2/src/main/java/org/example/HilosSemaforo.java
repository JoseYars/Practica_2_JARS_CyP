package org.example;

import java.util.concurrent.Semaphore;

public class HilosSemaforo {
    private static int contador = 0;
    private static Semaphore semaforo = new Semaphore(1);  // Semáforo binario

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Tarea());
        Thread hilo2 = new Thread(new Tarea());

        hilo1.start();
        hilo2.start();
    }

    static class Tarea implements Runnable {
        @Override
        public void run() {
            try {
                semaforo.acquire();  // Adquiere el semáforo, bloqueando si es necesario
                for (int i = 0; i < 5; i++) {
                    contador++;
                    System.out.println(Thread.currentThread().getName() + " incrementa contador a: " + contador);
                    Thread.sleep(100);  // Simula trabajo
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release();  // Libera el semáforo
            }
        }
    }
}