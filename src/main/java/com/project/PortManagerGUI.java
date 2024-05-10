package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PortManagerGUI {
    private JFrame frame;

    public PortManagerGUI() {
        frame = new JFrame("Resultado del comando");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayResult(String result) {
        JTextArea resultTextArea = new JTextArea(result);
        resultTextArea.setEditable(false); // Evita la edición del texto
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra el diálogo al hacer clic en OK
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setSize(400, 300); // Tamaño inicial de la ventana
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
    }
}

