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
    public SubZero() {
        super("Sub Zero", 140, 60, 6, "/images/subZero.png");
    }

    @Override
    public void perseguir(Jugador jugador) {
        int distancia = (int) Math.hypot(jugador.getX() - this.x, jugador.getY() - this.y);
        int rangoAtaque = 40; // Puedes ajustar este valor

        if (distancia > rangoAtaque) {
            // Movimiento simple hacia el jugador
            if (jugador.getX() > this.x) this.x += velocidad;
            if (jugador.getX() < this.x) this.x -= velocidad;
            if (jugador.getY() > this.y) this.y += velocidad;
            if (jugador.getY() < this.y) this.y -= velocidad;
        }
        // Si está en rango, no se mueve más (solo ataca desde el hilo)
    }
}
