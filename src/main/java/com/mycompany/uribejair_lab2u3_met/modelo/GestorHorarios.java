/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * GESTOR DE HORARIOS
 * Maneja todas las operaciones CRUD de configuracion de horarios en MongoDB
 */
public class GestorHorarios {
    
    private MongoCollection<Document> coleccionHorarios;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
    
    public GestorHorarios() {
        MongoDatabase database = ConexionMongoDB.getInstancia().getDatabase();
        coleccionHorarios = database.getCollection("horarios");
        System.out.println("GestorHorarios inicializado");
        inicializarHorariosDefault();
    }
    
    // =============== CREATE ===============
    
    /**
     * Guardar o actualizar configuracion de horario
     */
    public boolean guardarConfiguracion(ConfiguracionHorario config) {
        try {
            Document doc = new Document()
                    .append("diaSemana", config.getDiaSemana())
                    .append("horaInicio", config.getHoraInicio().format(timeFormatter))
                    .append("horaFin", config.getHoraFin().format(timeFormatter))
                    .append("duracionCita", config.getDuracionCita())
                    .append("tiempoDescanso", config.getTiempoDescanso())
                    .append("activo", config.isActivo());
            
            // Actualizar si existe, insertar si no existe
            Document filtro = new Document("diaSemana", config.getDiaSemana());
            Document existente = coleccionHorarios.find(filtro).first();
            
            if (existente != null) {
                coleccionHorarios.replaceOne(filtro, doc);
                System.out.println("Configuracion actualizada para: " + config.getDiaSemana());
            } else {
                coleccionHorarios.insertOne(doc);
                System.out.println("Configuracion creada para: " + config.getDiaSemana());
            }
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al guardar configuracion: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // =============== READ ===============
    
    /**
     * Obtener configuracion por dia de la semana
     */
    public ConfiguracionHorario obtenerConfiguracion(String diaSemana) {
        try {
            Document doc = coleccionHorarios.find(Filters.eq("diaSemana", diaSemana)).first();
            if (doc != null) {
                return documentToConfiguracion(doc);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener configuracion: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Retornar configuracion por defecto si no existe
        return obtenerConfiguracionDefault(diaSemana);
    }
    
    /**
     * Obtener todas las configuraciones
     */
    public List<ConfiguracionHorario> obtenerTodasLasConfiguraciones() {
        List<ConfiguracionHorario> configuraciones = new ArrayList<>();
        
        try {
            for (Document doc : coleccionHorarios.find()) {
                configuraciones.add(documentToConfiguracion(doc));
            }
            System.out.println("Configuraciones recuperadas: " + configuraciones.size());
            
        } catch (Exception e) {
            System.err.println("Error al obtener configuraciones: " + e.getMessage());
            e.printStackTrace();
        }
        
        return configuraciones;
    }
    
    /**
     * Obtener dias activos
     */
    public List<String> obtenerDiasActivos() {
        List<String> diasActivos = new ArrayList<>();
        
        try {
            for (Document doc : coleccionHorarios.find(Filters.eq("activo", true))) {
                diasActivos.add(doc.getString("diaSemana"));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener dias activos: " + e.getMessage());
        }
        
        return diasActivos;
    }
    
    // =============== UPDATE ===============
    
    /**
     * Activar o desactivar un dia
     */
    public boolean actualizarEstadoDia(String diaSemana, boolean activo) {
        try {
            coleccionHorarios.updateOne(
                    Filters.eq("diaSemana", diaSemana),
                    new Document("$set", new Document("activo", activo))
            );
            System.out.println("Estado de " + diaSemana + " actualizado a: " + activo);
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar estado del dia: " + e.getMessage());
            return false;
        }
    }
    
    // =============== DELETE ===============
    
    /**
     * Eliminar configuracion de un dia
     */
    public boolean eliminarConfiguracion(String diaSemana) {
        try {
            coleccionHorarios.deleteOne(Filters.eq("diaSemana", diaSemana));
            System.out.println("Configuracion eliminada: " + diaSemana);
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar configuracion: " + e.getMessage());
            return false;
        }
    }
    
    // =============== METODOS AUXILIARES ===============
    
    /**
     * Convertir Document a ConfiguracionHorario
     */
    private ConfiguracionHorario documentToConfiguracion(Document doc) {
        ConfiguracionHorario config = new ConfiguracionHorario();
        config.setDiaSemana(doc.getString("diaSemana"));
        config.setHoraInicio(LocalTime.parse(doc.getString("horaInicio"), timeFormatter));
        config.setHoraFin(LocalTime.parse(doc.getString("horaFin"), timeFormatter));
        config.setDuracionCita(doc.getInteger("duracionCita"));
        config.setTiempoDescanso(doc.getInteger("tiempoDescanso"));
        config.setActivo(doc.getBoolean("activo"));
        return config;
    }
    
    /**
     * Obtener configuracion por defecto
     */
    private ConfiguracionHorario obtenerConfiguracionDefault(String diaSemana) {
        return new ConfiguracionHorario(
                diaSemana,
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                30,
                5,
                true
        );
    }
    
    /**
     * Inicializar horarios por defecto si no existen
     */
    private void inicializarHorariosDefault() {
        try {
            long count = coleccionHorarios.countDocuments();
            
            if (count == 0) {
                System.out.println("Inicializando horarios por defecto...");
                
                String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
                
                for (String dia : dias) {
                    ConfiguracionHorario config = new ConfiguracionHorario(
                            dia,
                            LocalTime.of(9, 0),
                            LocalTime.of(17, 0),
                            30,
                            5,
                            !dia.equals("Domingo") // Todos activos excepto domingo
                    );
                    guardarConfiguracion(config);
                }
                
                System.out.println("Horarios por defecto inicializados");
            }
        } catch (Exception e) {
            System.err.println("Error al inicializar horarios: " + e.getMessage());
        }
    }
}

