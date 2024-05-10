package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PortManager {
    private static PortManager instance;

    // Constructor privado para evitar instanciación directa
    private PortManager() {
        // Inicialización del singleton
    }

    // Método estático para obtener la instancia del singleton
    public static PortManager getInstance() {
        if (instance == null) {
            instance = new PortManager();
        }
        return instance;
    }

    // Método para abrir un puerto utilizando netsh
    public void openPort(String port) {
        try {
            // Ejecutar el comando netsh para abrir el puerto
            ProcessBuilder builder = new ProcessBuilder("netsh", "advfirewall", "firewall", "add", "rule",
                    "name=OpenPort", "dir=in", "action=allow", "protocol=TCP", "localport=" + port);
            Process process = builder.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            process.waitFor();

            // Imprimir el resultado
            System.out.println("Puerto " + port + " abierto exitosamente.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar un puerto utilizando netsh
    public void closePort(String port) {
        try {
            // Ejecutar el comando netsh para cerrar el puerto
            ProcessBuilder builder = new ProcessBuilder("netsh", "advfirewall", "firewall", "delete", "rule",
                    "name=OpenPort", "protocol=TCP", "localport=" + port);
            Process process = builder.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            process.waitFor();

            // Imprimir el resultado
            System.out.println("Puerto " + port + " cerrado exitosamente.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}