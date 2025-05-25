/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.utils;

import autonoma.mortalKombat.models.Enemigo;
import autonoma.mortalKombat.models.Jugador;
import autonoma.mortalKombat.views.PanelJuego;

/**
 *
 * @author juand
 */
public class HiloMovimientoEnemigo extends Thread {
    private Enemigo enemigo;
    private Jugador jugador;
    private boolean activo;
    private PanelJuego panelJuego; // <--- referencia al panel

    public HiloMovimientoEnemigo(Enemigo enemigo, Jugador jugador, PanelJuego panelJuego) {
        this.enemigo = enemigo;
        this.jugador = jugador;
        this.panelJuego = panelJuego;
        this.activo = true;
    }

    @Override
    public void run() {
        while (activo) {
            try {
                enemigo.perseguir(jugador);

                // Solo ataca si está cerca
                int distancia = (int) Math.hypot(jugador.getX() - enemigo.getX(), jugador.getY() - enemigo.getY());
                int rangoAtaque = 40; // Debe coincidir con el de perseguir
                if (distancia <= rangoAtaque) {
                    enemigo.atacar(jugador);
                    jugador.recibirDaño(enemigo.getDaño());
                    panelJuego.verificarDerrota(); // <--- notifica al panel
                    panelJuego.repaint(); // <--- repinta el panel
                }

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

