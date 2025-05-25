/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.utils;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Utilidad para reproducir archivos de sonido (.wav) desde los recursos del proyecto.
 * 
 * La ruta debe ser relativa al classpath y comenzar con '/', por ejemplo: "/sounds/golpe.wav".
 * Los archivos deben estar ubicados en la carpeta de recursos (src/main/resources).
 * 
 * Ejemplo de uso:
 *   Reproductor.reproducirSonido("/sounds/golpe.wav");
 * 
 * @author juand
 * @version 1.0
 * @since 2025-25-05
 */
public class Reproductor {
    /**
     * Reproduce un archivo de sonido (.wav) desde los recursos del proyecto.
     * 
     * @param rutaArchivo Ruta relativa al classpath, comenzando con '/'.
     */
    public static void reproducirSonido(String rutaArchivo) {
        try {
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    Reproductor.class.getResourceAsStream(rutaArchivo))) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
            System.err.println("Error al reproducir el sonido: " + rutaArchivo + " - " + e.getMessage());
        }
    }
}
