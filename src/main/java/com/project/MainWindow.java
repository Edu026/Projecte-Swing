package com.project;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        // Configurar la ventana principal
        setTitle("Mi Aplicación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear el panel principal con color azul claro
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230)); // Azul claro

        // Crear el panel central
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Crear el panel para los botones en el centro
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(173, 216, 230)); // Azul claro

        // Crear los botones
        JButton button1 = new JButton("Nova regla");
        JButton button2 = new JButton("Modificar");
        JButton button3 = new JButton("Esborrar");

        // Agregar botón1 con relleno a la izquierda
        JPanel leftButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtonsPanel.setBackground(new Color(173, 216, 230)); // Azul claro
        leftButtonsPanel.add(button1);
        buttonPanel.add(leftButtonsPanel,BorderLayout.WEST);
        buttonPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER); // Relleno al centro
        // Agregar botón2 y botón3 al lado derecho
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setBackground(new Color(173, 216, 230)); // Azul claro
        rightButtonsPanel.add(button2);
        rightButtonsPanel.add(button3);
        buttonPanel.add(rightButtonsPanel, BorderLayout.EAST);

        // Crear el panel para las etiquetas en el centro
        JPanel labelPanel = new JPanel(new GridLayout(1, 8));

        // Agregar las etiquetas al panel de etiquetas
        labelPanel.add(new JLabel("Nom"));
        labelPanel.add(new JLabel("Port"));
        labelPanel.add(new JLabel("Tipus"));
        labelPanel.add(new JLabel("Aplicació"));
        labelPanel.add(new JLabel("Usuari"));
        labelPanel.add(new JLabel("Direcció IP"));
        labelPanel.add(new JLabel("Acció"));
        labelPanel.add(new JLabel("Interface"));
        labelPanel.add(new JLabel("Sentit"));

        // Agregar el panel de etiquetas al panel central
        centerPanel.add(labelPanel, BorderLayout.NORTH);

        // Agregar el panel de botones al panel central
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel central al panel principal
        mainPanel.add(new Label("Regles:"),BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        add(mainPanel);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}





