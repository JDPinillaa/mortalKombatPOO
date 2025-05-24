/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

import javax.swing.ImageIcon;

/**
 *
 * @author ACER
 */
public abstract class Enemigo {
    protected String nombre;
    protected int vida;
    protected int daño;
    protected int velocidad;
    protected ImageIcon imagen;
    protected int x;
    protected int y;

    public Enemigo(String nombre, int vida, int daño, int velocidad, String rutaImagen) {
        this.nombre = nombre;
        this.vida = vida;
        this.daño = daño;
        this.velocidad = velocidad;
        this.imagen = new ImageIcon(rutaImagen);
        this.x=100;
        this.y=100;
    }

    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void atacar(Jugador jugador) {
        jugador.recibirDaño(daño);
    }

    public abstract void perseguir(Jugador jugador);

    // Getters y Setters
    public int getVida() { return vida; }
    public int getDaño() { return daño; }
    public int getVelocidad() { return velocidad; }
    public ImageIcon getImagen() { return imagen; }
    public String getNombre() { return nombre; }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

}
