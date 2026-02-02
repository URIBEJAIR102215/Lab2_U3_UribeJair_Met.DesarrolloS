/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import java.time.LocalTime;

/**
 * MODELO: ConfiguracionHorario
 * Representa la configuracion de horarios de atencion por dia
 */
public class ConfiguracionHorario {
    
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int duracionCita; // en minutos
    private int tiempoDescanso; // en minutos
    private boolean activo;
    
    // Constructor vacio
    public ConfiguracionHorario() {
    }
    
    // Constructor completo
    public ConfiguracionHorario(String diaSemana, LocalTime horaInicio, LocalTime horaFin,
                                int duracionCita, int tiempoDescanso, boolean activo) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.duracionCita = duracionCita;
        this.tiempoDescanso = tiempoDescanso;
        this.activo = activo;
    }
    
    // Getters y Setters
    public String getDiaSemana() {
        return diaSemana;
    }
    
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public LocalTime getHoraFin() {
        return horaFin;
    }
    
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public int getDuracionCita() {
        return duracionCita;
    }
    
    public void setDuracionCita(int duracionCita) {
        this.duracionCita = duracionCita;
    }
    
    public int getTiempoDescanso() {
        return tiempoDescanso;
    }
    
    public void setTiempoDescanso(int tiempoDescanso) {
        this.tiempoDescanso = tiempoDescanso;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return "ConfiguracionHorario{" +
                "diaSemana='" + diaSemana + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", duracionCita=" + duracionCita +
                ", tiempoDescanso=" + tiempoDescanso +
                ", activo=" + activo +
                '}';
    }
}

