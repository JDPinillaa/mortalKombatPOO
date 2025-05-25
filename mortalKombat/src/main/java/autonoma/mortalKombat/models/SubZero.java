/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonoma.mortalKombat.models;

/**
 * Enemigo específico: SubZero.
 * Define los atributos iniciales de SubZero.
 * Hereda la lógica de movimiento y ataque de la clase base Enemigo.
 * 
 * @author Santiago
 * @since 16-5-2025
 * @version 1.0
 */
public class SubZero extends Enemigo {

    public SubZero() {
        super("Sub Zero", 1400, 60, 5, "/images/subZero.png");
    }
}
