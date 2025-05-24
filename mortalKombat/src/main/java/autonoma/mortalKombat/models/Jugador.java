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
    private String nombre;
    private int vida;
    private int daño;
    private int velocidad;
    private int puntos;
    private ImageIcon imagen;
    private int x;
    private int y;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vida = 100;
        this.daño = 20;
        this.velocidad = 9;
        this.imagen = new ImageIcon(getClass().getResource("/images/jugador.png"));
        this.puntos = 0;
        this.x= 100;
        this.y=100;
    }

    public void mover(String direccion) {
        System.out.println(nombre + " se mueve hacia " + direccion);
    }

    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void usarHabilidadEspecial() {
        System.out.println(nombre + " usa la habilidad especial");
    }

    public void mejorarVida() {
        vida += 20;
    }

    public void mejorarDaño() {
        daño += 5;
    }

    public void ganarPuntos(int cantidad) {
        puntos += cantidad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public ImageIcon getImagen() {
        return imagen;
    }
}