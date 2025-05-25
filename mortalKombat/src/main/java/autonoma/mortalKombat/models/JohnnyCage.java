/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

/**
 * Enemigo específico: Johnny Cage.
 * Define los atributos iniciales de Johnny Cage.
 * Hereda la lógica de movimiento y ataque de la clase base Enemigo.
 * 
 * @author Santiago
 * @since 16-5-2025
 * @version 1.0
 */
public class JohnnyCage extends Enemigo {

    public JohnnyCage() {
        super("Johnny Cage", 500, 15, 4, "/images/johnnyCage.png");
    }
}
