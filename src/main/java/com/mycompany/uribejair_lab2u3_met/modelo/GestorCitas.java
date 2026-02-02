/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * GESTOR DE CITAS
 * Maneja todas las operaciones CRUD de citas en MongoDB
 */
public class GestorCitas {
    
    private MongoCollection<Document> coleccionCitas;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    public GestorCitas() {
        MongoDatabase database = ConexionMongoDB.getInstancia().getDatabase();
        coleccionCitas = database.getCollection("citas");
        System.out.println("GestorCitas inicializado");
    }
    
    // =============== CREATE ===============
    
    /**
     * Agregar una nueva cita a la base de datos
     */
    public boolean agregarCita(Cita cita) {
        try {
            Document doc = new Document()
                    .append("id", cita.getId())
                    .append("nombreUsuario", cita.getNombreUsuario())
                    .append("email", cita.getEmail())
                    .append("telefono", cita.getTelefono())
                    .append("fechaHora", cita.getFechaHora().format(formatter))
                    .append("estado", cita.getEstado());
            
            coleccionCitas.insertOne(doc);
            System.out.println("Cita agregada exitosamente: ID " + cita.getId());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al agregar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // =============== READ ===============
    
    /**
     * Obtener todas las citas
     */
    public List<Cita> obtenerTodasLasCitas() {
        List<Cita> citas = new ArrayList<>();
        
        try {
            for (Document doc : coleccionCitas.find()) {
                Cita cita = documentToCita(doc);
                citas.add(cita);
            }
            System.out.println("Citas recuperadas: " + citas.size());
            
        } catch (Exception e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
            e.printStackTrace();
        }
        
        return citas;
    }
    
    /**
     * Obtener una cita por ID
     */
    public Cita obtenerCitaPorId(int id) {
        try {
            Document doc = coleccionCitas.find(Filters.eq("id", id)).first();
            if (doc != null) {
                return documentToCita(doc);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener cita por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Obtener citas por estado
     */
    public List<Cita> obtenerCitasPorEstado(String estado) {
        List<Cita> citas = new ArrayList<>();
        
        try {
            for (Document doc : coleccionCitas.find(Filters.eq("estado", estado))) {
                citas.add(documentToCita(doc));
            }
            System.out.println("Citas con estado '" + estado + "': " + citas.size());
            
        } catch (Exception e) {
            System.err.println("Error al obtener citas por estado: " + e.getMessage());
            e.printStackTrace();
        }
        
        return citas;
    }
    
    // =============== UPDATE ===============
    
    /**
     * Actualizar estado de una cita
     */
    public boolean actualizarEstadoCita(int id, String nuevoEstado) {
        try {
            coleccionCitas.updateOne(
                    Filters.eq("id", id),
                    Updates.set("estado", nuevoEstado)
            );
            System.out.println("Estado de cita " + id + " actualizado a: " + nuevoEstado);
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Actualizar cita completa
     */
    public boolean actualizarCita(Cita cita) {
        try {
            Document doc = new Document()
                    .append("nombreUsuario", cita.getNombreUsuario())
                    .append("email", cita.getEmail())
                    .append("telefono", cita.getTelefono())
                    .append("fechaHora", cita.getFechaHora().format(formatter))
                    .append("estado", cita.getEstado());
            
            coleccionCitas.updateOne(
                    Filters.eq("id", cita.getId()),
                    new Document("$set", doc)
            );
            System.out.println("Cita actualizada: ID " + cita.getId());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // =============== DELETE ===============
    
    /**
     * Eliminar una cita por ID
     */
    public boolean eliminarCita(int id) {
        try {
            coleccionCitas.deleteOne(Filters.eq("id", id));
            System.out.println("Cita eliminada: ID " + id);
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // =============== METODOS AUXILIARES ===============
    
    /**
     * Convertir Document de MongoDB a objeto Cita
     */
    private Cita documentToCita(Document doc) {
        Cita cita = new Cita();
        cita.setId(doc.getInteger("id"));
        cita.setNombreUsuario(doc.getString("nombreUsuario"));
        cita.setEmail(doc.getString("email"));
        cita.setTelefono(doc.getString("telefono"));
        cita.setFechaHora(LocalDateTime.parse(doc.getString("fechaHora"), formatter));
        cita.setEstado(doc.getString("estado"));
        return cita;
    }
    
    /**
     * Generar un nuevo ID unico
     */
    public int generarNuevoId() {
        try {
            Document ultimaCita = coleccionCitas.find()
                    .sort(new Document("id", -1))
                    .first();
            
            if (ultimaCita != null) {
                return ultimaCita.getInteger("id") + 1;
            }
        } catch (Exception e) {
            System.err.println("Error al generar ID: " + e.getMessage());
        }
        return 1001; // ID inicial
    }
    
    /**
     * Verificar si existe una cita en un horario especifico
     */
    public boolean existeCitaEnHorario(LocalDateTime fechaHora) {
        try {
            long count = coleccionCitas.countDocuments(
                    Filters.and(
                            Filters.eq("fechaHora", fechaHora.format(formatter)),
                            Filters.ne("estado", "Cancelada")
                    )
            );
            return count > 0;
            
        } catch (Exception e) {
            System.err.println("Error al verificar horario: " + e.getMessage());
            return false;
        }
    }
}
