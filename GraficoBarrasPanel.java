/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.global.utils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 *
 * @author Lenovo ThinkPad
 */
public class GraficoBarrasPanel extends JPanel {

    private String[] etiquetas;
    private double[] valores;
    private final int ANCHO_BARRA = 180;
    private final int GAP = 50;

    public GraficoBarrasPanel(String[] etiquetas, double[] valores) {
        this.etiquetas = etiquetas;
        this.valores = valores;
        
        int anchoNecesario = (etiquetas.length * (ANCHO_BARRA + GAP)) + 100;
        
        setPreferredSize(new Dimension(anchoNecesario, 350)); 
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        int margen = 50;

        double valorMax = obtenerMaximo();
        if (valorMax == 0) valorMax = 1;

        g2.drawLine(margen, alto - margen, ancho - margen, alto - margen);
        g2.drawLine(margen, margen, margen, alto - margen);

        DecimalFormat df = new DecimalFormat("#,##0.00");

        for (int i = 0; i < valores.length; i++) {
            // Calcular altura
            int alturaBarra = (int) ((valores[i] / valorMax) * (alto - 2 * margen));
            
            int x = margen + GAP + (i * (ANCHO_BARRA + GAP));
            int y = alto - margen - alturaBarra;

            g2.setColor(new Color(255, 102, 0));
            g2.fillRect(x, y, ANCHO_BARRA, alturaBarra);
            
            g2.setColor(Color.GRAY);
            g2.drawRect(x, y, ANCHO_BARRA, alturaBarra);

            g2.setColor(Color.BLACK);
            String valStr = df.format(valores[i]);
            int strWidth = g2.getFontMetrics().stringWidth(valStr);
            g2.drawString(valStr, x + (ANCHO_BARRA - strWidth) / 2, y - 5);

            String lbl = etiquetas[i] != null ? etiquetas[i] : "";
            
            
            int lblWidth = g2.getFontMetrics().stringWidth(lbl);
            // Centrar el texto largo debajo de la barra ancha
            g2.drawString(lbl, x + (ANCHO_BARRA - lblWidth) / 2, alto - margen + 20);
        }
    }

    private double obtenerMaximo() {
        if (valores.length == 0) return 0;
        double max = valores[0];
        for (double v : valores) {
            if (v > max) max = v;
        }
        return max;
    }
}
