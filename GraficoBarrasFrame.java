package com.transporte.gestion.global.utils;

import javax.swing.*;

public class GraficoBarrasFrame extends JFrame {

    public GraficoBarrasFrame(String tituloVentana, String[] etiquetas, double[] valores) {

        setTitle(tituloVentana);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GraficoBarrasPanel panel = new GraficoBarrasPanel(etiquetas, valores);
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
