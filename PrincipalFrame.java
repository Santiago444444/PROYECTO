package com.transporte.gestion.vista.sistema;

import com.transporte.gestion.controlador.sistema.MenuController;
import com.transporte.gestion.vista.sistema.MisDatosFrame;
import com.transporte.gestion.vista.sistema.LoginFrame;
import com.transporte.gestion.global.modelos.SesionGlobal;

public class PrincipalFrame extends javax.swing.JFrame {

    private final MenuController menuController = new MenuController(this);

    public PrincipalFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Sistema de gestión de transporte");
        cargarUsuarioEnPantalla();

        if (SesionGlobal.haySesionActiva()) {
            menuController.construirMenu(treeMenu);
            menuController.enlazar(treeMenu);
        } else {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No hay sesión activa.",
                    "Acceso denegado",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            new LoginFrame().setVisible(true);
            dispose();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeMenu = new javax.swing.JTree();
        panelContenido = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCuenta = new javax.swing.JMenu();
        itemVerDatos = new javax.swing.JMenuItem();
        menuSesion = new javax.swing.JMenu();
        itemCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 50));

        lblUsuario.setText("Holi :v");

        jLabel3.setText("Sistema de gestión de transporte");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(jLabel3))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelMenu.setPreferredSize(new java.awt.Dimension(220, 165));

        jScrollPane1.setViewportView(treeMenu);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout panelContenidoLayout = new javax.swing.GroupLayout(panelContenido);
        panelContenido.setLayout(panelContenidoLayout);
        panelContenidoLayout.setHorizontalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelContenidoLayout.setVerticalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        getContentPane().add(panelContenido, java.awt.BorderLayout.CENTER);

        menuCuenta.setText("Mi cuenta");

        itemVerDatos.setText("Ver mis datos");
        itemVerDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVerDatosActionPerformed(evt);
            }
        });
        menuCuenta.add(itemVerDatos);

        jMenuBar1.add(menuCuenta);

        menuSesion.setText("Sesión");

        itemCerrarSesion.setText("Cerrar sesión");
        itemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarSesionActionPerformed(evt);
            }
        });
        menuSesion.add(itemCerrarSesion);

        jMenuBar1.add(menuSesion);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarSesionActionPerformed

        int r = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Desea cerrar sesión?",
                "Confirmación",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (r == javax.swing.JOptionPane.YES_OPTION) {
            SesionGlobal.cerrarSesion();

            LoginFrame login = new LoginFrame();
            login.setVisible(true);

            this.dispose();
        }


    }//GEN-LAST:event_itemCerrarSesionActionPerformed

    private void itemVerDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVerDatosActionPerformed
        MisDatosFrame datos = new MisDatosFrame();
        datos.setVisible(true);
    }//GEN-LAST:event_itemVerDatosActionPerformed

    private void cargarUsuarioEnPantalla() {
        if (SesionGlobal.haySesionActiva()) {

            String nombreUser = SesionGlobal.getNombreUsuario();
            lblUsuario.setText("Usuario: " + nombreUser);

        } else {
            lblUsuario.setText("Usuario: (sin sesión)");
        }
    }

    public void mostrarPanel(javax.swing.JPanel panel) {
        panelContenido.removeAll();
        panelContenido.setLayout(new java.awt.BorderLayout());
        panelContenido.add(panel, java.awt.BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemVerDatos;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menuCuenta;
    private javax.swing.JMenu menuSesion;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTree treeMenu;
    // End of variables declaration//GEN-END:variables
}
