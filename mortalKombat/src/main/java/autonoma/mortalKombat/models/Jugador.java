/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonoma.mortalKombat.models;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 *
 * @author ACER
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

    public Jugador() {
        this.vida = 100;
        this.daño = 20;
        this.velocidad = 5;
        this.puntos = 0;
        this.x = 100; // posición inicial en X
        this.y = 100; // posición inicial en Y
        this.imagen = new ImageIcon("images/jugador.png");
        this.rand = new Random();
    }

    


    

    // Mueve según dirección y limita dentro del panel
    public void mover(String direccion, int anchoPanel, int altoPanel) {
        int paso = velocidad;
        switch (direccion) {
            case "W": y -= paso; break;
            case "S": y += paso; break;
            case "A": x -= paso; break;
            case "D": x += paso; break;
        }
        // Limitar dentro del panel
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > anchoPanel - imagen.getIconWidth()) x = anchoPanel - imagen.getIconWidth();
        if (y > altoPanel - imagen.getIconHeight()) y = altoPanel - imagen.getIconHeight();
    }

    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void usarHabilidadEspecial(int anchoPanel, int altoPanel) {
        int nuevoX = rand.nextInt(anchoPanel - imagen.getIconWidth());
        int nuevoY = rand.nextInt(altoPanel - imagen.getIconHeight());
        this.setX(nuevoX);
        this.setY(nuevoY);
    }

    public void mejorarVida() { vida += 20; }
    public void mejorarDaño() { daño += 5; }
    public void ganarPuntos(int cantidad) { puntos += cantidad; }

    // Getters y setters
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getVida() { return vida; }
    public int getDaño() { return daño; }
    public int getPuntos() { return puntos; }
    public ImageIcon getImagen() { return imagen; }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }
    
}

