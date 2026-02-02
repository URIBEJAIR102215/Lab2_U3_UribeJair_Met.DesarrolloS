/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.uribejair_lab2u3_met;

import com.mycompany.uribejair_lab2u3_met.modelo.ConexionMongoDB;
import com.mycompany.uribejair_lab2u3_met.vista.MenuPrincipalJU;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author HP
 */
public class UribeJair_Lab2U3_Met {

    public static void main(String[] args) {
        // Configurar Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer Look and Feel: " + e.getMessage());
        }

        // Verificar conexion a MongoDB
        ConexionMongoDB conexion = ConexionMongoDB.getInstancia();

        if (conexion.isConectado()) {
            System.out.println("=================================");
            System.out.println("SISTEMA DE GESTION DE CITAS");
            System.out.println("Conexion a MongoDB: EXITOSA");
            System.out.println("=================================\n");

            // Ejecutar interfaz grafica en el Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                MenuPrincipalJU menu = new MenuPrincipalJU();
                menu.setVisible(true);
            });

        } else {
            System.err.println("=================================");
            System.err.println("ERROR: No se pudo conectar a MongoDB");
            System.err.println("Verifique que MongoDB este ejecutandose");
            System.err.println("=================================");
        }

        // Agregar shutdown hook para cerrar la conexion
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nCerrando aplicacion...");
            ConexionMongoDB.getInstancia().cerrarConexion();
            System.out.println("Aplicacion cerrada correctamente");
        }));
    }
}
