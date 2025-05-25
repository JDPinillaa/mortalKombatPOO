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
import autonoma.mortalKombat.utils.Reproductor;

/**
 * Panel principal donde se desarrolla la batalla entre el jugador y el enemigo.
 * Gestiona el renderizado de los personajes, el fondo, la información en pantalla,
 * la interacción por teclado y mouse, y la lógica de victoria/derrota.
 * 
 * Permite mover al jugador, atacar al enemigo, usar habilidades especiales,
 * y muestra información relevante de ambos personajes.
 * 
 * También controla el hilo de movimiento del enemigo y la transición entre pantallas
 * según el resultado de la batalla.
 *
 * @author Santiago
 * @since 24-5-2025
 * @version 1.0
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

    /**
     * Crea el panel de juego, inicializando el fondo, los personajes y los listeners.
     * 
     * @param nivelActual Nivel actual de la partida.
     * @param simulador   Instancia del simulador principal del juego.
     */
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
        setPreferredSize(new java.awt.Dimension(1300, 900)); // Cambia a las dimensiones que quieras

        hiloEnemigo = new HiloMovimientoEnemigo(enemigo, jugador, this);
        hiloEnemigo.start();

        // Listener para controlar el movimiento y acciones del jugador por teclado
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
                        if (hiloEnemigo != null) hiloEnemigo.detener();
                        javax.swing.SwingUtilities.getWindowAncestor(PanelJuego.this).dispose();
                        new autonoma.mortalKombat.views.PantallaDeInicio(simulador).setVisible(true);
                        return;
                    case KeyEvent.VK_E:
                        jugador.usarHabilidadEspecial(getWidth(), getHeight());
                        repaint();
                        break;
                    case KeyEvent.VK_SPACE:
                        if (juegoTerminado) return;
                        if (enemigo != null) {
                            int jugadorCentroX = jugador.getX() + jugador.getImagen().getIconWidth() / 2;
                            int jugadorCentroY = jugador.getY() + jugador.getImagen().getIconHeight() / 2;
                            int enemigoCentroX = enemigo.getX() + enemigo.getImagen().getIconWidth() / 2;
                            int enemigoCentroY = enemigo.getY() + enemigo.getImagen().getIconHeight() / 2;
                            int distancia = (int) Math.hypot(jugadorCentroX - enemigoCentroX, jugadorCentroY - enemigoCentroY);
                            int rangoAtaque = 200; // Ajusta el rango si lo deseas
                            if (distancia <= rangoAtaque) {
                                enemigo.recibirDaño(jugador.getDaño());
                                Reproductor.reproducirSonido("/sounds/golpe.wav");
                                verificarVictoria();
                                repaint();
                            } else {
                                System.out.println("¡Estás demasiado lejos para atacar!");
                            }
                        }
                        break;
                }
                if (dir != null) {
                    jugador.mover(dir, getWidth(), getHeight());
                    repaint();
                }
            }
        });

        // Listener para interacción con el mouse (ataque por cercanía)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (juegoTerminado) return;
                if (enemigo != null) {
                    int jugadorCentroX = jugador.getX() + jugador.getImagen().getIconWidth() / 2;
                    int jugadorCentroY = jugador.getY() + jugador.getImagen().getIconHeight() / 2;
                    int enemigoCentroX = enemigo.getX() + enemigo.getImagen().getIconWidth() / 2;
                    int enemigoCentroY = enemigo.getY() + enemigo.getImagen().getIconHeight() / 2;
                    int distancia = (int) Math.hypot(jugadorCentroX - enemigoCentroX, jugadorCentroY - enemigoCentroY);
                    int rangoAtaque = 200; // Ajusta si quieres
                    if (distancia <= rangoAtaque) {
                        enemigo.recibirDaño(jugador.getDaño());
                        Reproductor.reproducirSonido("/sounds/golpe.wav");
                        verificarVictoria();
                        repaint();
                    } else {
                        System.out.println("¡Estás demasiado lejos para atacar!");
                    }
                }
            }
        });

        // Listener para actualizar la posición del mouse (útil para mostrar información contextual)
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
    }

    /**
     * Dibuja el fondo, los personajes y la información en pantalla.
     * 
     * @param g Contexto gráfico.
     */
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

    /**
     * Fuerza el repintado del panel.
     */
    public void actualizar() {
        repaint();
    }

    /**
     * Obtiene la posición X actual del mouse sobre el panel.
     * @return Coordenada X del mouse.
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Obtiene la posición Y actual del mouse sobre el panel.
     * @return Coordenada Y del mouse.
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Verifica si el jugador ha ganado la batalla.
     * Si es así, muestra el mensaje correspondiente, reproduce el sonido de victoria,
     * otorga puntos y vuelve a la pantalla de niveles.
     */
    private void verificarVictoria() {
        if (enemigo.getVida() <= 0 && !juegoTerminado) {
            juegoTerminado = true;
            if (hiloEnemigo != null) hiloEnemigo.detener();
            Reproductor.reproducirSonido("/sounds/victory.wav");
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

    /**
     * Verifica si el jugador ha sido derrotado.
     * Si es así, muestra el mensaje correspondiente, reproduce el sonido de derrota
     * y vuelve a la pantalla de niveles.
     */
    public void verificarDerrota() {
        if (jugador.getVida() <= 0 && !juegoTerminado) {
            juegoTerminado = true;
            if (hiloEnemigo != null) hiloEnemigo.detener();
            Reproductor.reproducirSonido("/sounds/youLose.wav");
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
