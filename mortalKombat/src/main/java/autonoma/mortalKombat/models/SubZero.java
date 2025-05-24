/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonoma.mortalKombat.models;

/**
 *
 * @author ACER
 */
public class SubZero extends Enemigo {
    public SubZero(String nombre, int vida, int daño, int velocidad, String rutaImagen) {
        super(nombre, vida, daño, velocidad, rutaImagen);
    }

    @Override
    public void perseguir(Jugador jugador) {
        // Lógica simple de persecución (puedes mejorarla)
        if (jugador.getX() > this.x) this.x += velocidad;
        if (jugador.getX() < this.x) this.x -= velocidad;
        if (jugador.getY() > this.y) this.y += velocidad;
        if (jugador.getY() < this.y) this.y -= velocidad;
    }
}
