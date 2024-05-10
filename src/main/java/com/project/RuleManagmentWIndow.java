package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RuleManagmentWIndow extends JFrame implements ActionListener {
    private JTextField campoNom, campoPort, campoProtocol, campoApp, campoUsuari, campoGrup, campoIP, campoAccio, campoInterface, campoSentit;
    private JButton botonGuardar, botonCancelar;
    private int selectedRow;

    public RuleManagmentWIndow(Object rowData, int selectedRow) {
        // Configurar la ventana
        setTitle("Modificar Datos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear paneles
        JPanel panelPrincipal = new JPanel();
        JPanel panelBotones = new JPanel();

        this.selectedRow = selectedRow; 

        // Crear campos de texto
        campoNom = new JTextField(10);
        campoPort = new JTextField(10);
        campoProtocol = new JTextField(10);
        campoApp = new JTextField(10);
        campoUsuari = new JTextField(10);
        campoGrup = new JTextField(10);
        campoIP = new JTextField(10);
        campoAccio = new JTextField(10);
        campoInterface = new JTextField(10);
        campoSentit = new JTextField(10);

        // Crear botones
        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);

        // Agregar componentes a paneles
        panelPrincipal.setLayout(new GridLayout(11, 2));
        panelPrincipal.add(new JLabel("Nom:"));
        panelPrincipal.add(campoNom);
        panelPrincipal.add(new JLabel("Port:"));
        panelPrincipal.add(campoPort);
        panelPrincipal.add(new JLabel("Protocol:"));
        panelPrincipal.add(campoProtocol);
        panelPrincipal.add(new JLabel("App:"));
        panelPrincipal.add(campoApp);
        panelPrincipal.add(new JLabel("Usuari:"));
        panelPrincipal.add(campoUsuari);
        panelPrincipal.add(new JLabel("Grup:"));
        panelPrincipal.add(campoGrup);
        panelPrincipal.add(new JLabel("IP:"));
        panelPrincipal.add(campoIP);
        panelPrincipal.add(new JLabel("Accio:"));
        panelPrincipal.add(campoAccio);
        panelPrincipal.add(new JLabel("Interface:"));
        panelPrincipal.add(campoInterface);
        panelPrincipal.add(new JLabel("Sentit:"));
        panelPrincipal.add(campoSentit);

        panelBotones.add(botonGuardar);
        panelBotones.add(botonCancelar);

        // Agregar paneles a la ventana
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        // Establecer los datos en los campos de texto si rowData no es null
        if (rowData != null) {
            establecerDatos(rowData);
        }

    }

// Método para establecer los datos en los campos de texto
private void establecerDatos(Object rowData) {
    if (rowData instanceof String[]) {
        String[] datos = (String[]) rowData;
        if (datos.length >= 10) { // Verificar que hay suficientes elementos en el array
            campoNom.setText(datos[0] != null ? datos[0] : "");
            campoPort.setText(datos[1] != null ? datos[1] : "");
            campoProtocol.setText(datos[2] != null ? datos[2] : "");
            campoApp.setText(datos[3] != null ? datos[3] : "");
            campoUsuari.setText(datos[4] != null ? datos[4] : "");
            campoGrup.setText(datos[5] != null ? datos[5] : "");
            campoIP.setText(datos[6] != null ? datos[6] : "");
            campoAccio.setText(datos[7] != null ? datos[7] : "");
            campoInterface.setText(datos[8] != null ? datos[8] : "");
            campoSentit.setText(datos[9] != null ? datos[9] : "");
        } else {
            System.err.println("Error: rowData no tiene suficientes elementos para establecer los datos.");
        }
    } else {
        System.err.println("Error: rowData no es un array de Strings.");
    }
}



@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botonGuardar) {
        // Obtener los nuevos datos desde los campos de texto
        String nom = campoNom.getText();
        String port = campoPort.getText();
        String protocol = campoProtocol.getText();
        String app = campoApp.getText();
        String usuari = campoUsuari.getText();
        String grup = campoGrup.getText();
        String ip = campoIP.getText();
        String accio = campoAccio.getText();
        String interf = campoInterface.getText();
        String sentit = campoSentit.getText();
        
        // Actualizar la fila en la tabla con los nuevos datos
        String[] newData = {nom, port, protocol, app, usuari, grup, ip, accio, interf, sentit};
        if(selectedRow == -1){
            PortManager portManager = new PortManager(); // Crear una instancia de PortManager
            portManager.openPort(port, nom, sentit, accio, protocol); // Llamar al método openPort(String) en la instancia
            MainWindow.addTableRow(newData);
        }
        else{
            MainWindow.updateTableRow(newData, selectedRow); // Debes definir rowIndexToUpdate
        } 
        MainWindow.saveDataToJSON();
        
        // Cerrar la ventana de modificación
        dispose();
    } else if (e.getSource() == botonCancelar) {
        // Cerrar la ventana si se presiona el botón Cancelar
        dispose();
    }
}

}
