package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PortManager {
    private static PortManager instance;
    private PortManagerGUI gui;

    // Constructor privado para evitar instanciación directa
    public PortManager() {
        gui = new PortManagerGUI();
    }

    // Método estático para obtener la instancia del singleton
    public static PortManager getInstance() {
        if (instance == null) {
            instance = new PortManager();
        }
        return instance;
    }

    // Método para abrir un puerto utilizando netsh
    public void openPort(String port, String name, String dir, String action, String protocol) {
        try {

                    /* 
            ProcessBuilder builder = new ProcessBuilder("netsh", "advfirewall", "firewall", "add", "rule",
                    "name="+ name + "dir=in", "action=allow", "protocol=TCP", "localport=" + port);
            */

            // Construir el comando para abrir el puerto
            String[] command = {"netsh", "advfirewall", "firewall", "add", "rule",
                "name=" + name, "dir=" + dir, "action=" + action, "protocol=" + protocol, "localport=" + port};
        
            // Crear el ProcessBuilder con el comando
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();

            // Esperar a que el proceso termine
            process.waitFor();

            // Ejecutar el comando para mostrar la regla del firewall recién agregada
            String[] showRuleCommand = {"netsh", "advfirewall", "firewall", "show", "rule", "name=" + name};
            ProcessBuilder showRuleBuilder = new ProcessBuilder(showRuleCommand);
            Process showRuleProcess = showRuleBuilder.start();

            // Leer la salida del proceso
            StringBuilder showRuleOutput = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(showRuleProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                showRuleOutput.append(line).append("\n");
            }

            // Esperar a que el proceso termine
            showRuleProcess.waitFor();

            // Mostrar el resultado en la GUI
            gui.displayResult(showRuleOutput.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar un puerto utilizando netsh
    public void closePort(String name) {
        try {
            // Ejecutar el comando para eliminar la regla del firewall
            String[] command = {"netsh", "advfirewall", "firewall", "delete", "rule", "name=" + name};
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();

            // Esperar a que el proceso termine
            process.waitFor();

            // Mostrar el resultado en la GUI si lo deseas
            // Aquí puedes agregar una llamada a gui.displayResult() si quieres mostrar el resultado en la GUI
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
