package autonoma.mortalKombat.exceptions;

/**
 * Excepción que se lanza cuando el jugador intenta realizar una acción
 * (como comprar una mejora) y no tiene suficientes puntos.
 *
 * @author juand
 * @version 1.0
 * @since 25-5-2025
 */
public class PuntosInsuficientesException extends Exception {
    /**
     * Crea una nueva excepción con el mensaje especificado.
     * @param mensaje Mensaje descriptivo del error.
     */
    public PuntosInsuficientesException(String mensaje) {
        super(mensaje);
    }
}