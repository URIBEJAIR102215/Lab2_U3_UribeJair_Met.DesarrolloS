/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import java.time.LocalDateTime;

/**
 * MODELO: Cita
 * Representa una cita medica en el sistema
 */
public class Cita {
    
    private int id;
    private String nombreUsuario;
    private String email;
    private String telefono;
    private LocalDateTime fechaHora;
    private String estado; // "Confirmada", "Cancelada"
    
    // Constructor vacio
    public Cita() {
    }
    
    // Constructor completo
    public Cita(int id, String nombreUsuario, String email, String telefono, 
                LocalDateTime fechaHora, String estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.telefono = telefono;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaHora=" + fechaHora +
                ", estado='" + estado + '\'' +
                '}';
    }
}

