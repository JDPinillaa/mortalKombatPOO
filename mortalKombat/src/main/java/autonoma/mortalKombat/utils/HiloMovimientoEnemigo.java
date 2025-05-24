/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.utils;

import autonoma.mortalKombat.models.*;

/**
 *
 * @author juand
 */
public class HiloMovimientoEnemigo extends Thread {
    private Enemigo enemigo;
    private Jugador jugador;
    private boolean activo;

    public HiloMovimientoEnemigo(Enemigo enemigo, Jugador jugador) {
        this.enemigo = enemigo;
        this.jugador = jugador;
        this.activo = true;
    }

    @Override
    public void run() {
        while (activo) {
            try {
                // El enemigo se mueve persiguiendo al jugador
                enemigo.perseguir(jugador);

                // El enemigo ataca si está cerca
                enemigo.atacar(jugador);

                // Controla la frecuencia de movimiento según la velocidad del enemigo
                Thread.sleep(1000 / enemigo.getVelocidad());
            } catch (InterruptedException e) {
                System.out.println("Hilo de movimiento interrumpido.");
                activo = false;
            }
        }
    }

    public void detener() {
        activo = false;
    }
}

