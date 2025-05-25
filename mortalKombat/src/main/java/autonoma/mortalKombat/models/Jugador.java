/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonoma.mortalKombat.models;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Representa al jugador en el juego Mortal Kombat.
 * Contiene atributos como vida, daño, velocidad, puntos, posición e imagen.
 * Permite mover al jugador, recibir daño, mejorar atributos, usar habilidades especiales
 * y restablecer la vida al máximo.
 * 
 * @author Santiago
 * @version 1.0
 * @since 2025-05-25
 */
public class Jugador {
    private int vida;
    private int daño;
    private int velocidad;
    private int puntos;
    private int x;
    private int y;
    private ImageIcon imagen;
    private final Random rand;
    private int vidaMaxima;

    /**
     * Crea un jugador con atributos iniciales por defecto.
     */
    public Jugador() {
        this.vida = 400;
        this.vidaMaxima = 400;
        this.daño = 20;
        this.velocidad = 5;
        this.puntos = 0;
        this.x = 100;
        this.y = 100;
        this.imagen = new ImageIcon(getClass().getResource("/images/jugador.png"));
        this.rand = new Random();
    }

    /**
     * Mueve al jugador en la dirección indicada y limita su posición dentro del panel.
     * @param direccion Dirección ("W", "A", "S", "D").
     * @param anchoPanel Ancho del panel.
     * @param altoPanel Alto del panel.
     */
    public void mover(String direccion, int anchoPanel, int altoPanel) {
        int paso = velocidad;
        switch (direccion) {
            case "W": y -= paso; break;
            case "S": y += paso; break;
            case "A": x -= paso; break;
            case "D": x += paso; break;
        }
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > anchoPanel - imagen.getIconWidth()) x = anchoPanel - imagen.getIconWidth();
        if (y > altoPanel - imagen.getIconHeight()) y = altoPanel - imagen.getIconHeight();
    }

    /**
     * Reduce la vida del jugador al recibir daño.
     * @param cantidad Cantidad de daño recibido.
     */
    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    /**
     * Usa la habilidad especial del jugador (teletransportación aleatoria).
     * @param anchoPanel Ancho del panel.
     * @param altoPanel Alto del panel.
     */
    public void usarHabilidadEspecial(int anchoPanel, int altoPanel) {
        int nuevoX = rand.nextInt(anchoPanel - imagen.getIconWidth());
        int nuevoY = rand.nextInt(altoPanel - imagen.getIconHeight());
        this.setX(nuevoX);
        this.setY(nuevoY);
    }

    /**
     * Mejora la vida máxima y actual del jugador.
     */
    public void mejorarVida() { 
        vida += 20; 
        vidaMaxima += 20;
    }

    /**
     * Mejora el daño del jugador.
     */
    public void mejorarDaño() { daño += 5; }

    /**
     * Suma puntos al jugador.
     * @param cantidad Cantidad de puntos a sumar.
     */
    public void ganarPuntos(int cantidad) { puntos += cantidad; }

    /**
     * Restablece la vida del jugador a su valor máximo.
     */
    public void restablecerVida() {
        this.vida = this.vidaMaxima;
    }

    // Getters y setters

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getVida() { return vida; }
    public int getDaño() { return daño; }
    public int getPuntos() { return puntos; }
    public ImageIcon getImagen() { return imagen; }
    public void setPuntos(int puntos) { this.puntos = puntos; }
    public void setVida(int vida) { this.vida = vida; }
    public void setDaño(int daño) { this.daño = daño; }
    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }
}

