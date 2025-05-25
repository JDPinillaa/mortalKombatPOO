/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

import javax.swing.JOptionPane;

import autonoma.mortalKombat.exceptions.PuntosInsuficientesException;

/**
 * Clase que representa la tienda del juego Mortal Kombat.
 * Permite al jugador comprar mejoras de vida y daño a cambio de puntos.
 * Si el jugador no tiene suficientes puntos, se lanza una excepción o se muestra un mensaje.
 *
 * @author Santiago
 * @since 16-5-2025
 * @version 1.0
 */
public class Tienda {

    /**
     * Permite al jugador comprar una mejora de vida si tiene suficientes puntos.
     * Lanza una excepción si no tiene los puntos necesarios.
     *
     * @param jugador El jugador que realiza la compra.
     * @throws PuntosInsuficientesException Si el jugador no tiene suficientes puntos.
     */
    public void comprarMejoraVida(Jugador jugador) throws PuntosInsuficientesException {
        int costo = 250;
        if (jugador.getPuntos() < costo) {
            throw new PuntosInsuficientesException("No tienes suficientes puntos para comprar esta mejora.");
        }
        jugador.mejorarVida();
        jugador.setPuntos(jugador.getPuntos() - costo);
        JOptionPane.showMessageDialog(null, "¡Has comprado una mejora de vida!");
    }

    /**
     * Permite al jugador comprar una mejora de daño si tiene suficientes puntos.
     * Muestra un mensaje si no tiene los puntos necesarios.
     *
     * @param jugador El jugador que realiza la compra.
     */
    public void comprarMejoraDaño(Jugador jugador) {
        int costo = 250;
        if (jugador.getPuntos() >= costo) {
            jugador.mejorarDaño();
            jugador.setPuntos(jugador.getPuntos() - costo);
            JOptionPane.showMessageDialog(null, "¡Has comprado una mejora de daño!");
        } else {
            JOptionPane.showMessageDialog(null, "No tienes suficientes puntos para comprar esta mejora.");
        }
    }
}
