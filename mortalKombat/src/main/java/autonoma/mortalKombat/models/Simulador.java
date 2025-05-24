/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;

import autonoma.mortalKombat.utils.*;

/**
 *
 * @author ACER
 */
public class Simulador {
    private int nivelDesbloqueado;
    private Jugador jugador;
    private Enemigo enemigo;
    private Tienda tienda;
    private ArchivoProgreso archivoProgreso;

    public Simulador() {
        archivoProgreso = new ArchivoProgreso();
        tienda = new Tienda();
        jugador = new Jugador("jugador");
        cargarProgreso();
    }

    public void iniciarJuego() {
        System.out.println("¡Bienvenido al juego!");
        seleccionarNivel(nivelDesbloqueado); // Carga el nivel más alto desbloqueado por defecto
    }

    public void cargarProgreso() {
        nivelDesbloqueado = archivoProgreso.leerNivelDesbloqueado();
        int puntosGuardados = archivoProgreso.leerPuntos();
        jugador.ganarPuntos(puntosGuardados);

        var mejoras = archivoProgreso.leerMejoras();
        if (mejoras != null) {
            jugador.setVida(mejoras.getOrDefault("vida", jugador.getVida()));
            jugador.setDaño(mejoras.getOrDefault("daño", jugador.getDaño()));
        }

        System.out.println("Progreso cargado. Nivel desbloqueado: " + nivelDesbloqueado);
    }

    public void actualizarProgreso(int nivelSuperado) {
        if (nivelSuperado > nivelDesbloqueado) {
            nivelDesbloqueado = nivelSuperado;
            archivoProgreso.guardarNivelDesbloqueado(nivelDesbloqueado);
            System.out.println("¡Nuevo nivel desbloqueado: " + nivelDesbloqueado + "!");
        }

        archivoProgreso.guardarPuntos(jugador.getPuntos());
        archivoProgreso.guardarMejoras(jugador.getVida(), jugador.getDaño());
    }

    public void seleccionarNivel(int nivel) {
        if (nivel > nivelDesbloqueado) {
            System.out.println("¡Debes superar el nivel anterior para acceder a este!");
            return;
        }

        switch (nivel) {
            case 1:
                enemigo = new JohnnyCage();
                break;
            case 2:
                enemigo = new Scorpion();
                break;
            case 3:
                enemigo = new SubZero();
                break;
            default:
                System.out.println("Nivel inválido.");
                return;
        }

        System.out.println("Nivel " + nivel + " cargado: " + enemigo.getNombre());

        // Iniciar el hilo de movimiento del enemigo
        HiloMovimientoEnemigo hilo = new HiloMovimientoEnemigo(enemigo, jugador);
        Thread t = new Thread(hilo);
        t.start();
    }

    public void abrirTienda() {
        tienda.mostrarOpciones();
    }
}

