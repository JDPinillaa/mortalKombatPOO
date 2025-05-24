/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Tienda {

    public void comprarMejoraVida(Jugador jugador) {
        int costo = 250;
        if (jugador.getPuntos() >= costo) {
            jugador.mejorarVida();
            jugador.setPuntos(jugador.getPuntos() - costo);
            JOptionPane.showMessageDialog(null, "¡Has comprado una mejora de vida!");
        } else {
            JOptionPane.showMessageDialog(null, "No tienes suficientes puntos para comprar esta mejora.");
        }
    }

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
