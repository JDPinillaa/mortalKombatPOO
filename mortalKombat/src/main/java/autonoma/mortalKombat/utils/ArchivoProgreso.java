/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.utils;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class ArchivoProgreso {

    private final String rutaArchivo = "progreso.txt";

    public int leerNivelDesbloqueado() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = br.readLine();
            if (linea != null) {
                return Integer.parseInt(linea.split(":")[1].trim());
            }
        } catch (Exception e) {
            System.out.println("No se encontró archivo de progreso, iniciando desde nivel 1.");
        }
        return 1; // Nivel inicial si no existe archivo
    }

    public void guardarNivelDesbloqueado(int nivel) {
        Map<String, String> datos = leerDatosExistentes();
        datos.put("nivel", String.valueOf(nivel));
        guardarDatos(datos);
    }

    public void guardarPuntos(int puntos) {
        Map<String, String> datos = leerDatosExistentes();
        datos.put("puntos", String.valueOf(puntos));
        guardarDatos(datos);
    }

    public int leerPuntos() {
        Map<String, String> datos = leerDatosExistentes();
        if (datos.containsKey("puntos")) {
            return Integer.parseInt(datos.get("puntos"));
        }
        return 0;
    }

    public void guardarMejoras(int vida, int daño) {
        Map<String, String> datos = leerDatosExistentes();
        datos.put("vida", String.valueOf(vida));
        datos.put("daño", String.valueOf(daño));
        guardarDatos(datos);
    }

    public Map<String, Integer> leerMejoras() {
        Map<String, String> datos = leerDatosExistentes();
        Map<String, Integer> mejoras = new HashMap<>();

        if (datos.containsKey("vida")) {
            mejoras.put("vida", Integer.parseInt(datos.get("vida")));
        }
        if (datos.containsKey("daño")) {
            mejoras.put("daño", Integer.parseInt(datos.get("daño")));
        }

        return mejoras;
    }

    // Utilidad: leer todos los datos del archivo
    private Map<String, String> leerDatosExistentes() {
        Map<String, String> datos = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    datos.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (Exception e) {
            // Si no hay archivo, lo ignoramos
        }
        return datos;
    }

    // Utilidad: sobrescribe el archivo con los datos actuales
    private void guardarDatos(Map<String, String> datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Map.Entry<String, String> entry : datos.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el progreso: " + e.getMessage());
        }
    }
}
