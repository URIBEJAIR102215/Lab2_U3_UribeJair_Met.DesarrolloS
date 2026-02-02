/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.controlador;

import com.mycompany.uribejair_lab2u3_met.modelo.Cita;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorCitas;
import com.mycompany.uribejair_lab2u3_met.vista.VistaCancelarCitaJU;
import java.util.List;

/**
 * CONTROLADOR CANCELAR CITA
 * Maneja la logica entre el modelo y la vista para cancelar/eliminar citas
 */
public class ControladorCancelarCita {
    
    private GestorCitas gestorCitas;
    private VistaCancelarCitaJU vista;
    
    public ControladorCancelarCita(GestorCitas gestorCitas, VistaCancelarCitaJU vista) {
        this.gestorCitas = gestorCitas;
        this.vista = vista;
        this.vista.setControlador(this);
    }
    
    /**
     * Iniciar el controlador
     */
    public void iniciar() {
        vista.setVisible(true);
        actualizarListaCitas();
    }
    
    /**
     * Actualizar la lista de citas en la vista
     */
    public void actualizarListaCitas() {
        List<Cita> todasLasCitas = gestorCitas.obtenerTodasLasCitas();
        vista.actualizarTabla(todasLasCitas);
        System.out.println("Lista de citas actualizada: " + todasLasCitas.size() + " citas");
    }
    
    /**
     * Cancelar una cita (cambiar estado a "Cancelada")
     */
    public void cancelarCita(int idCita) {
        
        // Verificar que la cita existe
        Cita cita = gestorCitas.obtenerCitaPorId(idCita);
        
        if (cita == null) {
            vista.mostrarError("No se encontro la cita con ID: " + idCita);
            return;
        }
        
        // Verificar que no este ya cancelada
        if (cita.getEstado().equalsIgnoreCase("Cancelada")) {
            vista.mostrarError("Esta cita ya se encuentra cancelada");
            return;
        }
        
        // Actualizar estado a "Cancelada"
        boolean exito = gestorCitas.actualizarEstadoCita(idCita, "Cancelada");
        
        if (exito) {
            vista.mostrarExito(
                    "Cita cancelada exitosamente\n\n" +
                    "ID: " + idCita + "\n" +
                    "Paciente: " + cita.getNombreUsuario() + "\n" +
                    "Estado: Cancelada"
            );
            actualizarListaCitas();
        } else {
            vista.mostrarError("Error al cancelar la cita. Intente nuevamente.");
        }
    }
    
    /**
     * Eliminar una cita de forma permanente
     */
    public void eliminarCita(int idCita) {
        
        // Verificar que la cita existe
        Cita cita = gestorCitas.obtenerCitaPorId(idCita);
        
        if (cita == null) {
            vista.mostrarError("No se encontro la cita con ID: " + idCita);
            return;
        }
        
        // Eliminar de la base de datos
        boolean exito = gestorCitas.eliminarCita(idCita);
        
        if (exito) {
            vista.mostrarExito(
                    "Cita eliminada permanentemente\n\n" +
                    "ID: " + idCita + "\n" +
                    "Paciente: " + cita.getNombreUsuario() + "\n" +
                    "La cita ha sido removida del sistema"
            );
            actualizarListaCitas();
        } else {
            vista.mostrarError("Error al eliminar la cita. Intente nuevamente.");
        }
    }
    
    /**
     * Obtener citas filtradas por estado
     */
    public void filtrarPorEstado(String estado) {
        List<Cita> citasFiltradas;
        
        if (estado.equals("Todas")) {
            citasFiltradas = gestorCitas.obtenerTodasLasCitas();
        } else {
            citasFiltradas = gestorCitas.obtenerCitasPorEstado(estado);
        }
        
        vista.actualizarTabla(citasFiltradas);
        System.out.println("Citas filtradas por estado '" + estado + "': " + citasFiltradas.size());
    }
    
    /**
     * Obtener estadisticas de citas
     */
    public void obtenerEstadisticas() {
        List<Cita> todasLasCitas = gestorCitas.obtenerTodasLasCitas();
        
        int totalCitas = todasLasCitas.size();
        int confirmadas = 0;
        int canceladas = 0;
        
        for (Cita cita : todasLasCitas) {
            if (cita.getEstado().equalsIgnoreCase("Confirmada")) {
                confirmadas++;
            } else if (cita.getEstado().equalsIgnoreCase("Cancelada")) {
                canceladas++;
            }
        }
        
        System.out.println("=== ESTADISTICAS ===");
        System.out.println("Total: " + totalCitas);
        System.out.println("Confirmadas: " + confirmadas);
        System.out.println("Canceladas: " + canceladas);
    }
}

