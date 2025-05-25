/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

import javax.swing.ImageIcon;

/**
 * Clase base que representa un enemigo en el juego.
 * Define los atributos y comportamientos básicos que comparten todos los enemigos,
 * como vida, daño, velocidad, imagen y posición.
 * 
 * Incluye una implementación por defecto del método {@code perseguir(Jugador jugador)},
 * que mueve al enemigo hacia el jugador en línea recta. Las subclases pueden sobrescribir
 * este método para definir comportamientos de persecución personalizados.
 *
 * @author Santiago
 * @since 16-5-2025
 * @version 1.1
 */
public class Enemigo {
    protected String nombre;
    protected int vida;
    protected int daño;
    protected int velocidad;
    protected ImageIcon imagen;
    protected int x;
    protected int y;

    /**
     * Constructor base para un enemigo.
     * @param nombre Nombre del enemigo.
     * @param vida Vida inicial del enemigo.
     * @param daño Daño que inflige el enemigo.
     * @param velocidad Velocidad de movimiento del enemigo.
     * @param rutaImagen Ruta del recurso de la imagen del enemigo.
     */
    public Enemigo(String nombre, int vida, int daño, int velocidad, String rutaImagen) {
        this.nombre = nombre;
        this.vida = vida;
        this.daño = daño;
        this.velocidad = velocidad;
        this.imagen = new ImageIcon(getClass().getResource(rutaImagen));
        this.x = 300;
        this.y = 300;
    }

    /**
     * Reduce la vida del enemigo al recibir daño.
     * @param cantidad Cantidad de daño recibido.
     */
    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    /**
     * Ataca al jugador, infligiendo daño.
     * @param jugador Jugador objetivo del ataque.
     */
    public void atacar(Jugador jugador) {
        jugador.recibirDaño(daño);
    }

    /**
     * Lógica de persecución del jugador.
     * Por defecto, mueve al enemigo hacia el jugador en línea recta.
     * Las subclases pueden sobrescribir este método para un comportamiento especial.
     * @param jugador Jugador a perseguir.
     */
    public void perseguir(Jugador jugador) {
        int distancia = (int) Math.hypot(jugador.getX() - this.x, jugador.getY() - this.y);
        int rangoAtaque = 40; // Puedes ajustar este valor

        if (distancia > rangoAtaque) {
            if (jugador.getX() > this.x) this.x += velocidad;
            if (jugador.getX() < this.x) this.x -= velocidad;
            if (jugador.getY() > this.y) this.y += velocidad;
            if (jugador.getY() < this.y) this.y -= velocidad;
        }
    }

    // Getters y Setters
    public int getVida() { return vida; }
    public int getDaño() { return daño; }
    public int getVelocidad() { return velocidad; }
    public ImageIcon getImagen() { return imagen; }
    public String getNombre() { return nombre; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
