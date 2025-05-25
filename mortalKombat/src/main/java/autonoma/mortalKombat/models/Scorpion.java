/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

/**
 * Enemigo específico: Scorpion.
 * Define los atributos iniciales de Scorpion.
 * Hereda la lógica de movimiento y ataque de la clase base Enemigo.
 * 
 * @author Santiago
 * @since 16-5-2025
 * @version 1.0
 */
public class Scorpion extends Enemigo {

    public Scorpion() {
        super("Scorpion", 720, 30, 3, "/images/scorpion.png");
    }
}
