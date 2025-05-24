/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonoma.mortalKombat.models;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ACER
 */
public class Jugador {
    private String nombre;
    private Image imagen;
    private int x, y;
    private int vida = 100; // Puedes ajustar el valor inicial según tu juego

    public Jugador(String nombre, String rutaImagen, int x, int y) {
        this.nombre = nombre;
        this.imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
        this.x = x;
        this.y = y;
    }
    public Image getImagen() { return imagen; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public int getVida() {
        return vida;
    }
    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    // Métodos para mover, atacar, etc.
}

