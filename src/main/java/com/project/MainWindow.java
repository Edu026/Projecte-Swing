package com.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainWindow extends JFrame {
    private static JTable table;
    private static DefaultTableModel model;
    private static final File jsonFile = new File("rule_data.json");
    

    public MainWindow() {
        // Configurar el JFrame
        JFrame frame = new JFrame("Firewall Rules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Usar BorderLayout

        // Model de la taula
        String[] columnNames = {"Nom", "Port", "Protocol", "App", "Usuari","Grup","IP","Accio","Interface","Sentit"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Cargar nombres de reglas desde el archivo JSON
        loadDataFromJSON();

        // Afegir la taula a un JScrollPane i afegir al centre del JFrame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel per als botons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Nova Regla");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Esborrar");

        // Afegir botons al panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Listeners dels botons
        addButton.addActionListener(MainWindow::addRule);
        editButton.addActionListener(MainWindow::editRule);
        deleteButton.addActionListener(MainWindow::deleteRule);

        // Mostrar el JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addRule(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        String[] rowDataStrings = new String[table.getColumnCount()];
        new RuleManagmentWIndow(rowDataStrings, -1).setVisible(true);
    }

    private static void editRule(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtener datos de la fila y convertirlos a Strings
            String[] rowDataStrings = new String[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++) {
                rowDataStrings[i] = String.valueOf(table.getValueAt(selectedRow, i));
            }
            System.out.println("Editant la regla...");
            // Crear y mostrar la ventana de modificación con los datos de la fila seleccionada
            new RuleManagmentWIndow(rowDataStrings, selectedRow).setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Seleccioneu una fila per editar.");
        }
    }

    private static void deleteRule(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtener datos de la fila seleccionada
            Object[] rowData = new Object[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++) {
                rowData[i] = table.getValueAt(selectedRow, i);
            }

            // Crear el mensaje de confirmación con la información de la regla
            String message = "Estàs segur que vols esborrar la regla amb la següent informació?\n\n";
            message += "Nom: " + rowData[0] + "\n";
            message += "Port: " + rowData[1] + "\n";
            message += "Protocol: " + rowData[2] + "\n";
            message += "App: " + rowData[3] + "\n";
            message += "Usuari: " + rowData[4] + "\n";
            message += "Grup: " + rowData[5] + "\n";
            message += "IP: " + rowData[6] + "\n";
            message += "Accio: " + rowData[7] + "\n";
            message += "Interface: " + rowData[8] + "\n";
            message += "Sentit: " + rowData[9] + "\n\n";
            message += "Esta acció no es pot desfer. Estàs segur?";

            // Mostrar ventana emergente de confirmación
            int option = JOptionPane.showConfirmDialog(null, message, "Confirmació", JOptionPane.YES_NO_OPTION);

            // Realizar la acción correspondiente
            if (option == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                PortManager portManager = new PortManager();
                System.out.println(rowData[0].toString());
                portManager.closePort(rowData[0].toString());

                saveDataToJSON(); // Guardar datos actualizados en el archivo JSON
                System.out.println("Regla esborrada.");
            } else {
                System.out.println("Esborrat cancel·lat.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccioneu una fila per esborrar.");
        }
    }

    static void saveDataToJSON() {
        try {
            List<Rule> rules = new ArrayList<>();
            for (int i = 0; i < model.getRowCount(); i++) {
                String[] rowData = new String[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowData[j] = (String) model.getValueAt(i, j);
                }
                Rule rule = new Rule(rowData[0],(rowData[1]), rowData[2], rowData[3], rowData[4], rowData[5], rowData[6], rowData[7], rowData[8], rowData[9]);
                rules.add(rule);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(jsonFile, rules);
            System.out.println("Datos guardados en el archivo JSON.");
        } catch (IOException ex) {
            System.err.println("Error al guardar datos en el archivo JSON: " + ex.getMessage());
        }
    }
    
    private static void loadDataFromJSON() {
        try {
            if (!jsonFile.exists()) {
                System.out.println("El archivo JSON no existe.");
                return;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<Rule> rules = objectMapper.readValue(jsonFile, new TypeReference<List<Rule>>() {
            });
            for (Rule rule : rules) {
                model.addRow(new Object[]{rule.getName(), rule.getPort(), rule.getProtocol(), rule.getApp(), rule.getUser(), rule.getGroup(), rule.getIp(), rule.getAction(), rule.getInterfaze(), rule.getDirection()});
            }
            System.out.println("Datos cargados desde el archivo JSON.");
        } catch (IOException ex) {
            System.err.println("Error al cargar datos desde el archivo JSON: " + ex.getMessage());
        }
    }
    

    public static void updateTableRow(String[] newData, int rowIndex) {
        for (int i = 0; i < newData.length; i++) {
            table.setValueAt(newData[i], rowIndex, i);
        }
    }

    public static void addTableRow(String[] newData) {
        model.addRow(newData);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
