/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.models;
import autonoma.mortalKombat.exceptions.NivelBloqueadoException;
import autonoma.mortalKombat.utils.ArchivoProgreso;

/**
 * Clase principal que gestiona la lógica del juego Mortal Kombat.
 * Se encarga de manejar el progreso del jugador, los niveles desbloqueados,
 * la selección de enemigos, la tienda y la persistencia de datos.
 * 
 * Permite cargar y actualizar el progreso, seleccionar niveles, y acceder
 * a los objetos principales del juego como el jugador, enemigo y tienda.
 * 
 * @author Santiago
 * @version 1.0
 * @since 2025-05-25
 */
public class Simulador {
    private int nivelDesbloqueado;
    private Jugador jugador;
    private Enemigo enemigo;
    private Tienda tienda;
    private ArchivoProgreso archivoProgreso;
    private Thread hiloEnemigo;

    /**
     * Crea un simulador e inicializa los componentes principales.
     * Carga el progreso guardado del jugador.
     */
    public Simulador() {
        archivoProgreso = new ArchivoProgreso();
        tienda = new Tienda();
        jugador = new Jugador();
        cargarProgreso();
    }

    /**
     * Inicia el juego cargando el nivel más alto desbloqueado.
     * @throws NivelBloqueadoException si el nivel no está desbloqueado.
     */
    public void iniciarJuego() throws NivelBloqueadoException {
        System.out.println("¡Bienvenido al juego!");
        seleccionarNivel(nivelDesbloqueado); // Carga el nivel más alto desbloqueado por defecto
    }

    /**
     * Carga el progreso del jugador desde el archivo de progreso.
     * Incluye nivel desbloqueado, puntos y mejoras.
     */
    public void cargarProgreso() {
        nivelDesbloqueado = archivoProgreso.leerNivelDesbloqueado();
        int puntosGuardados = archivoProgreso.leerPuntos();
        jugador.ganarPuntos(puntosGuardados);

        var mejoras = archivoProgreso.leerMejoras();
        if (mejoras != null) {
            if (mejoras.containsKey("vidaMaxima")) jugador.setVidaMaxima(mejoras.get("vidaMaxima"));
            if (mejoras.containsKey("daño")) jugador.setDaño(mejoras.get("daño"));
            jugador.restablecerVida(); // Para que la vida actual sea igual a la máxima al cargar
        }

        System.out.println("Progreso cargado. Nivel desbloqueado: " + nivelDesbloqueado);
    }

    /**
     * Actualiza el progreso del jugador, guardando el nivel superado,
     * los puntos y las mejoras obtenidas.
     * @param nivelSuperado Nivel que el jugador ha superado.
     */
    public void actualizarProgreso(int nivelSuperado) {
        if (nivelSuperado > nivelDesbloqueado) {
            nivelDesbloqueado = nivelSuperado;
            archivoProgreso.guardarNivelDesbloqueado(nivelDesbloqueado);
            System.out.println("¡Nuevo nivel desbloqueado: " + nivelDesbloqueado + "!");
        }

        archivoProgreso.guardarPuntos(jugador.getPuntos());
        archivoProgreso.guardarMejoras(jugador.getVidaMaxima(), jugador.getDaño());
    }

    /**
     * Selecciona el nivel y crea el enemigo correspondiente.
     * Restablece la vida del jugador y detiene el hilo anterior si existe.
     * @param nivel Nivel a seleccionar.
     * @throws NivelBloqueadoException si el nivel no está desbloqueado.
     */
    public void seleccionarNivel(int nivel) throws NivelBloqueadoException {
        if (nivel > nivelDesbloqueado) {
            throw new NivelBloqueadoException("¡Debes superar el nivel anterior para acceder a este!");
        }

        // Restablecer la vida del jugador al valor base o al valor mejorado
        jugador.restablecerVida();

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
                enemigo = new JohnnyCage();
        }

        System.out.println("Nivel " + nivel + " cargado: " + enemigo.getNombre());

        // Detener hilo anterior si existe
        if (hiloEnemigo != null && hiloEnemigo.isAlive()) {
            hiloEnemigo.interrupt();
        }
    }

    /**
     * Devuelve el jugador actual.
     * @return Jugador.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Devuelve la tienda del juego.
     * @return Tienda.
     */
    public Tienda getTienda() {
        return tienda;
    }

    /**
     * Devuelve el enemigo actual.
     * @return Enemigo.
     */
    public Enemigo getEnemigo() {
        return enemigo;
    }
}

