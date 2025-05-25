/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package autonoma.mortalKombat.main;

import autonoma.mortalKombat.models.Simulador;

/** 
 *
 * @author Juan Diego
 * @since 16-5-2025
 * @version 1.0
 */ 
public class MortalKombat {

    public static void main(String[] args) {
        Simulador simulador = new Simulador();
        autonoma.mortalKombat.views.PantallaDeInicio inicio = new autonoma.mortalKombat.views.PantallaDeInicio(simulador);
        inicio.setVisible(true);
    }
}
