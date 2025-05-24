/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.views;

import autonoma.mortalKombat.models.Enemigo;
import autonoma.mortalKombat.models.Jugador;
import autonoma.mortalKombat.models.SubZero;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ACER
 */
public class PanelJuego extends JPanel {
    private Image fondo;
    private int mouseX;
    private int mouseY;
    private Jugador jugador;
    private Enemigo enemigo;

    public PanelJuego() {
        try {
            fondo = ImageIO.read(getClass().getResource("/images/campoBatalla.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("No se pudo cargar la imagen de fondo: " + e.getMessage());
        }
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocusInWindow();

        // Inicializa los personajes primero
        jugador = new Jugador("Scorpion", "/images/Scorpion.png", 100, 300);
        enemigo = new SubZero("SubZero", 100, 10, 5, "/images/SubZero.png"); // Cambiado aquí

        // Luego agrega el KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: jugador.mover(-10, 0); break;
                    case KeyEvent.VK_RIGHT: jugador.mover(10, 0); break;
                    case KeyEvent.VK_UP: jugador.mover(0, -10); break;
                    case KeyEvent.VK_DOWN: jugador.mover(0, 10); break;
                }
                repaint();
            }
        });

        // Ejemplo de interacción con el mouse (puedes personalizarlo)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Aquí puedes manejar clics para acciones del juego
                // Por ejemplo: seleccionar personaje, atacar, etc.
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        // Dibuja jugador y enemigo
        if (jugador != null) {
            g.drawImage(jugador.getImagen(), jugador.getX(), jugador.getY(), this);
        }
        if (enemigo != null) {
            g.drawImage(enemigo.getImagen(), enemigo.getX(), enemigo.getY(), this);
        }
    }

    public void actualizar() {
        repaint();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
