/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

/**
 *
 * @author ACER
 */
import javax.swing.ImageIcon;

public class Jugador {
    private int vida;
    private int daño;
    private int velocidad;
    private int puntos;
    private ImageIcon imagen;

    public Jugador(int vida, int daño, int velocidad, ImageIcon imagen) {
        this.vida = vida;
        this.daño = daño;
        this.velocidad = velocidad;
        this.imagen = imagen;
        this.puntos = 0;
    }

    public void mover(String direccion) {
        System.out.println("Jugador se mueve hacia " + direccion);
    }

    public void recibirDaño(int cantidad) {
        vida -= cantidad;
    }

    public void usarHabilidadEspecial() {
        System.out.println("Habilidad especial usada");
    }

    public void mejorarVida() {
        vida += 20;
    }

    public void mejorarDaño() {
        daño += 5;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getVida() {
        return vida;
    }

    public int getDaño() {
        return daño;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public ImageIcon getImagen() {
        return imagen;
    }
}

