/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.mortalKombat.views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import autonoma.mortalKombat.models.Enemigo;
import autonoma.mortalKombat.models.Jugador;
import autonoma.mortalKombat.models.Simulador;
import autonoma.mortalKombat.utils.HiloMovimientoEnemigo;

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
    private int nivelActual;
    private Simulador simulador;
    private boolean juegoTerminado = false;
    private HiloMovimientoEnemigo hiloEnemigo;

    public PanelJuego(int nivelActual, Simulador simulador) {
        this.nivelActual = nivelActual;
        this.simulador = simulador;
        this.jugador = simulador.getJugador();
        this.enemigo = simulador.getEnemigo();
        try {
            fondo = ImageIO.read(getClass().getResource("/images/campoBatalla.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("No se pudo cargar la imagen de fondo: " + e.getMessage());
        }
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocusInWindow();

        hiloEnemigo = new HiloMovimientoEnemigo(enemigo, jugador, this);
        hiloEnemigo.start();

        // Luego agrega el KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String dir = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: dir = "W"; break;
                    case KeyEvent.VK_S: dir = "S"; break;
                    case KeyEvent.VK_A: dir = "A"; break;
                    case KeyEvent.VK_D: dir = "D"; break;
                    case KeyEvent.VK_Q:
                        // Volver a la pantalla de inicio
                        javax.swing.SwingUtilities.getWindowAncestor(PanelJuego.this).dispose();
                        new autonoma.mortalKombat.views.PantallaDeInicio(simulador).setVisible(true);
                        return; // Salir para no mover al jugador
                        
                    case KeyEvent.VK_E:
                        jugador.usarHabilidadEspecial(getWidth(), getHeight());
                        repaint();
                        break;
                }
                if (dir != null) {
                    jugador.mover(dir, getWidth(), getHeight());
                    repaint();
                }
            }
        });

        // Ejemplo de interacción con el mouse (puedes personalizarlo)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (juegoTerminado) return; // <--- Bloquea ataques si ya terminó
                if (enemigo != null) {
                    int ex = enemigo.getX();
                    int ey = enemigo.getY();
                    int ew = enemigo.getImagen().getIconWidth();
                    int eh = enemigo.getImagen().getIconHeight();
                    if (e.getX() >= ex && e.getX() <= ex + ew && e.getY() >= ey && e.getY() <= ey + eh) {
                        enemigo.recibirDaño(jugador.getDaño());
                        verificarVictoria();
                        repaint();
                    }
                }
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
            g.drawImage(jugador.getImagen().getImage(), jugador.getX(), jugador.getY(), this);
        }
        if (enemigo != null) {
            g.drawImage(enemigo.getImagen().getImage(), enemigo.getX(), enemigo.getY(), this);
        }

        // Información del jugador (esquina superior izquierda)
        if (jugador != null) {
            g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 15f));
            g.setColor(java.awt.Color.GREEN);
            String[] infoJugador = {
                "Tú",
                "Vida: " + jugador.getVida(),
                "Daño de ataque: " + jugador.getDaño()
            };
            int x = 20, y = 30, salto = 20;
            for (String linea : infoJugador) {
                g.drawString(linea, x, y);
                y += salto;
            }
        }

        // Información del enemigo (esquina superior derecha)
        if (enemigo != null) {
            g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 15f));
            g.setColor(java.awt.Color.RED);
            String[] infoEnemigo = {
                enemigo.getNombre(),
                "Vida: " + enemigo.getVida(),
                "Daño de ataque: " + enemigo.getDaño()
            };
            int salto = 20;
            int y = 30;
            for (String linea : infoEnemigo) {
                int ancho = g.getFontMetrics().stringWidth(linea);
                int x = getWidth() - ancho - 20;
                g.drawString(linea, x, y);
                y += salto;
            }
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

    private void verificarVictoria() {
        if (enemigo.getVida() <= 0 && !juegoTerminado) {
            juegoTerminado = true;
            if (hiloEnemigo != null) hiloEnemigo.detener(); // <--- Detiene el hilo
            jugador.ganarPuntos(500);
            if (nivelActual == 3) {
                JOptionPane.showMessageDialog(this,
                    "¡Felicidades! Has completado el juego y ganas +500 puntos.",
                    "Victoria final",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                int siguienteNivel = nivelActual + 1;
                JOptionPane.showMessageDialog(this,
                    "¡Has ganado!\nGanas +500 puntos y desbloqueas el nivel " + siguienteNivel + ".",
                    "Victoria",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            simulador.actualizarProgreso(Math.min(nivelActual + 1, 3));

            // Volver a la pantalla de niveles
            javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
            PantallaNiveles pantallaNiveles = new PantallaNiveles(simulador);
            pantallaNiveles.setVisible(true);
        }
    }

    public void verificarDerrota() {
        if (jugador.getVida() <= 0 && !juegoTerminado) {
            juegoTerminado = true;
            if (hiloEnemigo != null) hiloEnemigo.detener(); // <--- Detiene el hilo
            JOptionPane.showMessageDialog(this,
                "¡Has perdido! Intenta de nuevo.",
                "Derrota",
                JOptionPane.INFORMATION_MESSAGE
            );
            // Volver a la pantalla de niveles
            javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
            PantallaNiveles pantallaNiveles = new PantallaNiveles(simulador);
            pantallaNiveles.setVisible(true);
        }
    }
}
