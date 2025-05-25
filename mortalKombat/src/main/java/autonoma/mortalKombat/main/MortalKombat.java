/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package autonoma.mortalKombat.main;

import autonoma.mortalKombat.models.Simulador;
import autonoma.mortalKombat.views.PantallaNiveles;

/**
 *
 * @author Juan Diego
 * @since 16-5-2025
 * @version 1.0
 */
public class MortalKombat {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Simulador simulador = new Simulador();
        PantallaNiveles pantalla = new PantallaNiveles(simulador);
        pantalla.setVisible(true);
    }
}
