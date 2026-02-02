/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.controlador;

import com.mycompany.uribejair_lab2u3_met.modelo.ConfiguracionHorario;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorHorarios;
import com.mycompany.uribejair_lab2u3_met.vista.VistaGestionHorarios;
import java.time.LocalTime;

/**
 * CONTROLADOR DE HORARIOS
 * Maneja la logica entre el modelo y la vista para configurar horarios
 */
public class ControladorHorarios {
    
    private GestorHorarios gestorHorarios;
    private VistaGestionHorarios vista;
    
    public ControladorHorarios(GestorHorarios gestorHorarios, VistaGestionHorarios vista) {
        this.gestorHorarios = gestorHorarios;
        this.vista = vista;
        this.vista.setControlador(this);
    }
    
    /**
     * Iniciar el controlador
     */
    public void iniciar() {
        vista.setVisible(true);
        // Cargar configuracion del primer dia por defecto
        cargarConfiguracion("Lunes");
    }
    
    /**
     * Cargar configuracion de un dia especifico
     */
    public void cargarConfiguracion(String diaSemana) {
        try {
            ConfiguracionHorario config = gestorHorarios.obtenerConfiguracion(diaSemana);
            
            if (config != null) {
                vista.cargarDatos(config);
                System.out.println("Configuracion cargada para: " + diaSemana);
            } else {
                vista.mostrarError("No se pudo cargar la configuracion de: " + diaSemana);
            }
            
        } catch (Exception e) {
            System.err.println("Error al cargar configuracion: " + e.getMessage());
            vista.mostrarError("Error al cargar configuracion: " + e.getMessage());
        }
    }
    
    /**
     * Guardar configuracion de horarios
     */
    public void guardarConfiguracion(String diaSemana, LocalTime horaInicio, LocalTime horaFin,
                                     int duracionCita, int tiempoDescanso, boolean activo) {
        
        try {
            // Validaciones
            if (horaFin.isBefore(horaInicio) || horaFin.equals(horaInicio)) {
                vista.mostrarError("La hora de fin debe ser posterior a la hora de inicio");
                return;
            }
            
            if (duracionCita < 15 || duracionCita > 120) {
                vista.mostrarError("La duracion de cita debe estar entre 15 y 120 minutos");
                return;
            }
            
            if (tiempoDescanso < 0 || tiempoDescanso > 60) {
                vista.mostrarError("El tiempo de descanso debe estar entre 0 y 60 minutos");
                return;
            }
            
            // Crear objeto de configuracion
            ConfiguracionHorario config = new ConfiguracionHorario(
                    diaSemana,
                    horaInicio,
                    horaFin,
                    duracionCita,
                    tiempoDescanso,
                    activo
            );
            
            // Guardar en la base de datos
            boolean exito = gestorHorarios.guardarConfiguracion(config);
            
            if (exito) {
                vista.mostrarExito(
                        "Configuracion guardada exitosamente\n\n" +
                        "Dia: " + diaSemana + "\n" +
                        "Horario: " + horaInicio + " - " + horaFin + "\n" +
                        "Duracion cita: " + duracionCita + " min\n" +
                        "Descanso: " + tiempoDescanso + " min\n" +
                        "Estado: " + (activo ? "Activo" : "Inactivo")
                );
                
                // Calcular capacidad del dia
                calcularCapacidadDia(config);
                
            } else {
                vista.mostrarError("Error al guardar la configuracion. Intente nuevamente.");
            }
            
        } catch (Exception e) {
            System.err.println("Error al guardar configuracion: " + e.getMessage());
            vista.mostrarError("Error al guardar configuracion: " + e.getMessage());
        }
    }
    
    /**
     * Activar o desactivar un dia
     */
    public void cambiarEstadoDia(String diaSemana, boolean activo) {
        boolean exito = gestorHorarios.actualizarEstadoDia(diaSemana, activo);
        
        if (exito) {
            String mensaje = activo ? "activado" : "desactivado";
            vista.mostrarExito("El dia " + diaSemana + " ha sido " + mensaje);
        } else {
            vista.mostrarError("Error al cambiar estado del dia");
        }
    }
    
    /**
     * Calcular capacidad de citas en un dia
     */
    private void calcularCapacidadDia(ConfiguracionHorario config) {
        try {
            if (!config.isActivo()) {
                System.out.println("Capacidad del dia " + config.getDiaSemana() + ": 0 (inactivo)");
                return;
            }
            
            int minutosDisponibles = calcularMinutosDisponibles(
                    config.getHoraInicio(),
                    config.getHoraFin()
            );
            
            int minutosPorCita = config.getDuracionCita() + config.getTiempoDescanso();
            int capacidad = minutosDisponibles / minutosPorCita;
            
            System.out.println("=== CAPACIDAD DEL DIA " + config.getDiaSemana() + " ===");
            System.out.println("Minutos disponibles: " + minutosDisponibles);
            System.out.println("Minutos por cita (incluye descanso): " + minutosPorCita);
            System.out.println("Capacidad total: " + capacidad + " citas");
            
        } catch (Exception e) {
            System.err.println("Error al calcular capacidad: " + e.getMessage());
        }
    }
    
    /**
     * Calcular minutos disponibles entre dos horas
     */
    private int calcularMinutosDisponibles(LocalTime inicio, LocalTime fin) {
        int minutosInicio = inicio.getHour() * 60 + inicio.getMinute();
        int minutosFin = fin.getHour() * 60 + fin.getMinute();
        return minutosFin - minutosInicio;
    }
    
    /**
     * Obtener resumen de todos los horarios
     */
    public void mostrarResumenHorarios() {
        System.out.println("\n=== RESUMEN DE HORARIOS ===");
        
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        
        for (String dia : dias) {
            ConfiguracionHorario config = gestorHorarios.obtenerConfiguracion(dia);
            
            if (config != null) {
                String estado = config.isActivo() ? "ACTIVO" : "INACTIVO";
                System.out.println(dia + ": " + config.getHoraInicio() + " - " + 
                                 config.getHoraFin() + " [" + estado + "]");
            }
        }
        
        System.out.println("========================\n");
    }
    
    /**
     * Restaurar horarios por defecto
     */
    public void restaurarHorariosDefault() {
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        
        for (String dia : dias) {
            ConfiguracionHorario config = new ConfiguracionHorario(
                    dia,
                    LocalTime.of(9, 0),
                    LocalTime.of(17, 0),
                    30,
                    5,
                    !dia.equals("Domingo")
            );
            gestorHorarios.guardarConfiguracion(config);
        }
        
        vista.mostrarExito("Horarios restaurados a valores por defecto");
        cargarConfiguracion("Lunes");
    }
}

