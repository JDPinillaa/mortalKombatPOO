package autonoma.mortalKombat.exceptions;

/**
 * Excepción que se lanza cuando el jugador intenta acceder a un nivel
 * que aún no ha sido desbloqueado.
 *
 * @author juand
 * @version 1.0
 * @since 25-5-2025
 */
public class NivelBloqueadoException extends Exception {
    /**
     * Crea una nueva excepción con el mensaje especificado.
     * @param mensaje Mensaje descriptivo del error.
     */
    public NivelBloqueadoException(String mensaje) {
        super(mensaje);
    }
}