/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.controlador;

import com.mycompany.uribejair_lab2u3_met.modelo.Cita;
import com.mycompany.uribejair_lab2u3_met.modelo.ConfiguracionHorario;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorCitas;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorHorarios;
import com.mycompany.uribejair_lab2u3_met.vista.VistaAgendarCitaJU;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * CONTROLADOR DE CITAS
 * Maneja la logica entre el modelo y la vista para agendar citas
 */
public class ControladorCitas {
    
    private GestorCitas gestorCitas;
    private GestorHorarios gestorHorarios;
    private VistaAgendarCitaJU vista;
    
    public ControladorCitas(GestorCitas gestorCitas, VistaAgendarCitaJU vista) {
        this.gestorCitas = gestorCitas;
        this.gestorHorarios = new GestorHorarios();
        this.vista = vista;
        this.vista.setControlador(this);
    }
    
    /**
     * Iniciar el controlador
     */
    public void iniciar() {
        vista.setVisible(true);
        actualizarHorariosDisponibles();
    }
    
    /**
     * Actualizar horarios disponibles en la vista
     */
    public void actualizarHorariosDisponibles() {
        List<LocalDateTime> horariosDisponibles = generarHorariosDisponibles();
        vista.actualizarComboHorarios(horariosDisponibles);
        System.out.println("Horarios disponibles actualizados: " + horariosDisponibles.size());
    }
    
    /**
     * Agendar una nueva cita
     */
    public void agendarCita(String nombre, String email, String telefono, LocalDateTime fechaHora) {
        
        // Validar que el horario no este ocupado
        if (gestorCitas.existeCitaEnHorario(fechaHora)) {
            vista.mostrarError("El horario seleccionado ya esta ocupado");
            return;
        }
        
        // Crear nueva cita
        int nuevoId = gestorCitas.generarNuevoId();
        Cita nuevaCita = new Cita(
                nuevoId,
                nombre,
                email,
                telefono,
                fechaHora,
                "Confirmada"
        );
        
        // Guardar en la base de datos
        boolean exito = gestorCitas.agregarCita(nuevaCita);
        
        if (exito) {
            java.time.format.DateTimeFormatter displayFormatter = 
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            vista.mostrarExito(
                    "Cita agendada exitosamente\n\n" +
                    "ID: " + nuevoId + "\n" +
                    "Paciente: " + nombre + "\n" +
                    "Fecha: " + fechaHora.format(displayFormatter)
            );
            vista.limpiarFormulario();
            actualizarHorariosDisponibles();
        } else {
            vista.mostrarError("Error al agendar la cita. Intente nuevamente.");
        }
    }
    
    /**
     * Generar lista de horarios disponibles
     */
    private List<LocalDateTime> generarHorariosDisponibles() {
        List<LocalDateTime> horarios = new ArrayList<>();
        
        try {
            LocalDate hoy = LocalDate.now();
            LocalDate fechaInicio = hoy.plusDays(1); // Comenzar desde mañana
            LocalDate fechaFin = hoy.plusDays(30); // Generar para los proximos 30 dias
            
            LocalDate fechaActual = fechaInicio;
            
            while (!fechaActual.isAfter(fechaFin)) {
                
                // Obtener dia de la semana
                String diaSemana = obtenerDiaSemana(fechaActual.getDayOfWeek());
                
                // Obtener configuracion del dia
                ConfiguracionHorario config = gestorHorarios.obtenerConfiguracion(diaSemana);
                
                // Si el dia esta activo, generar horarios
                if (config.isActivo()) {
                    List<LocalDateTime> horariosDia = generarHorariosDia(
                            fechaActual,
                            config
                    );
                    horarios.addAll(horariosDia);
                }
                
                fechaActual = fechaActual.plusDays(1);
            }
            
        } catch (Exception e) {
            System.err.println("Error al generar horarios: " + e.getMessage());
            e.printStackTrace();
        }
        
        return horarios;
    }
    
    /**
     * Generar horarios para un dia especifico
     */
    private List<LocalDateTime> generarHorariosDia(LocalDate fecha, ConfiguracionHorario config) {
        List<LocalDateTime> horarios = new ArrayList<>();
        
        LocalTime horaActual = config.getHoraInicio();
        LocalTime horaFin = config.getHoraFin();
        int duracionTotal = config.getDuracionCita() + config.getTiempoDescanso();
        
        while (horaActual.plusMinutes(config.getDuracionCita()).isBefore(horaFin) || 
               horaActual.plusMinutes(config.getDuracionCita()).equals(horaFin)) {
            
            LocalDateTime fechaHora = LocalDateTime.of(fecha, horaActual);
            
            // Verificar si el horario no esta ocupado
            if (!gestorCitas.existeCitaEnHorario(fechaHora)) {
                horarios.add(fechaHora);
            }
            
            horaActual = horaActual.plusMinutes(duracionTotal);
        }
        
        return horarios;
    }
    
    /**
     * Convertir DayOfWeek a nombre en español
     */
    private String obtenerDiaSemana(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "Lunes";
            case TUESDAY: return "Martes";
            case WEDNESDAY: return "Miercoles";
            case THURSDAY: return "Jueves";
            case FRIDAY: return "Viernes";
            case SATURDAY: return "Sabado";
            case SUNDAY: return "Domingo";
            default: return "Lunes";
        }
    }
}