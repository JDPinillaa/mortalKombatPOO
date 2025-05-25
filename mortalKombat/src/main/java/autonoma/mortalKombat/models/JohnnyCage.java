/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

/**
 *
 * @author ACER
 */
public class JohnnyCage extends Enemigo {

    public JohnnyCage(){
        super("Johnny Cage", 100, 15, 7, "/images/johnnyCage.png");
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
    }    
    
}
