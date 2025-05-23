/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.mortalKombat.views;

import autonoma.mortalKombat.models.Simulador;
import autonoma.mortalKombat.models.Jugador;
import autonoma.mortalKombat.models.Tienda;
import autonoma.mortalKombat.exceptions.PuntosInsuficientesException;

/**
 *
 * @author juand
 */
public class PantallaTienda extends javax.swing.JFrame {
    private Simulador simulador;

    /**
     * Creates new form PantallaTienda
     */
    public PantallaTienda(Simulador simulador) {
        this.simulador = simulador;
        initComponents();
        actualizarPuntos();

        // Acción para el botón confirmar
        confirmarCambiosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaDeInicio inicio = new PantallaDeInicio(simulador);
                inicio.setVisible(true);
                dispose(); // Cierra la tienda
            }
        });
    }

    private void actualizarPuntos() {
        puntosActualesLabel.setText(String.valueOf(simulador.getJugador().getPuntos()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        aumentarDañoButton = new javax.swing.JButton();
        mejorarVidaButton = new javax.swing.JButton();
        confirmarCambiosButton = new javax.swing.JButton();
        puntosActualesLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Tienda");

        aumentarDañoButton.setBackground(new java.awt.Color(0, 102, 153));
        aumentarDañoButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aumentarDañoButton.setText(" Aumentar daño : $250");
        aumentarDañoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aumentarDañoButtonActionPerformed(evt);
            }
        });

        mejorarVidaButton.setBackground(new java.awt.Color(0, 102, 153));
        mejorarVidaButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mejorarVidaButton.setText("Mejorar vida : $250");
        mejorarVidaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mejorarVidaButtonActionPerformed(evt);
            }
        });

        confirmarCambiosButton.setBackground(new java.awt.Color(0, 102, 153));
        confirmarCambiosButton.setText("confirmar");

        puntosActualesLabel.setText("0");

        jLabel2.setText("Puntos Actuales:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(puntosActualesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(mejorarVidaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aumentarDañoButton))))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(confirmarCambiosButton)
                        .addGap(105, 105, 105))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puntosActualesLabel)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addComponent(mejorarVidaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aumentarDañoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmarCambiosButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mejorarVidaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mejorarVidaButtonActionPerformed
        try {
            simulador.getTienda().comprarMejoraVida(simulador.getJugador());
        } catch (PuntosInsuficientesException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Puntos insuficientes", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        actualizarPuntos();
    }//GEN-LAST:event_mejorarVidaButtonActionPerformed

    private void aumentarDañoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aumentarDañoButtonActionPerformed
        simulador.getTienda().comprarMejoraDaño(simulador.getJugador());
        actualizarPuntos();
    }//GEN-LAST:event_aumentarDañoButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aumentarDañoButton;
    private javax.swing.JButton confirmarCambiosButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mejorarVidaButton;
    private javax.swing.JLabel puntosActualesLabel;
    // End of variables declaration//GEN-END:variables
}
