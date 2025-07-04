/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.utils;

import autonoma.mortalKombat.models.Enemigo;
import autonoma.mortalKombat.models.Jugador;
import autonoma.mortalKombat.views.PanelJuego;

/**
 * Hilo encargado de mover al enemigo y gestionar sus ataques al jugador en tiempo real.
 * El enemigo persigue al jugador y ataca automáticamente si está dentro del rango de ataque,
 * respetando un tiempo de espera (cooldown) entre ataques.
 * 
 * Este hilo actualiza la posición del enemigo, verifica si el jugador ha sido derrotado
 * y repinta el panel de juego.
 *
 * @author Juan Diego
 * @since 16-5-2025
 * @version 1.0
 */
public class HiloMovimientoEnemigo extends Thread {
    private Enemigo enemigo;
    private Jugador jugador;
    private boolean activo;
    private PanelJuego panelJuego; 

    /**
     * Crea un hilo para mover y gestionar los ataques del enemigo.
     * @param enemigo Enemigo a controlar.
     * @param jugador Jugador objetivo.
     * @param panelJuego Panel de juego para repintar y verificar derrota.
     */
    public HiloMovimientoEnemigo(Enemigo enemigo, Jugador jugador, PanelJuego panelJuego) {
        this.enemigo = enemigo;
        this.jugador = jugador;
        this.panelJuego = panelJuego;
        this.activo = true;
    }

    @Override
    public void run() {
        long ultimoAtaque = 0;
        while (activo) {
            try {
                enemigo.perseguir(jugador);

                int distancia = (int) Math.hypot(jugador.getX() - enemigo.getX(), jugador.getY() - enemigo.getY());
                int rangoAtaque = 40;
                long ahora = System.currentTimeMillis();
                if (distancia <= rangoAtaque && ahora - ultimoAtaque >= 1000) { // 1000 ms = 1 segundo
                    enemigo.atacar(jugador);
                    jugador.recibirDaño(enemigo.getDaño());
                    panelJuego.verificarDerrota();
                    panelJuego.repaint();
                    ultimoAtaque = ahora;
                }

                Thread.sleep(1000 / enemigo.getVelocidad());
            } catch (InterruptedException e) {
                System.out.println("Hilo de movimiento interrumpido.");
                activo = false;
            }
        }
    }

    /**
     * Detiene la ejecución del hilo.
     */
    public void detener() {
        activo = false;
    }
}

