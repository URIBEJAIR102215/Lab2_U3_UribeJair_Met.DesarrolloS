/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * CONEXION MONGODB
 * Clase singleton para gestionar la conexion a MongoDB
 */
public class ConexionMongoDB {
    
    private static ConexionMongoDB instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;
    
    // Configuracion de conexion
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "sistema_citas";
    
    // Constructor privado (Singleton)
    private ConexionMongoDB() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexion exitosa a MongoDB: " + DATABASE_NAME);
        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Obtener instancia unica (Singleton)
    public static ConexionMongoDB getInstancia() {
        if (instancia == null) {
            synchronized (ConexionMongoDB.class) {
                if (instancia == null) {
                    instancia = new ConexionMongoDB();
                }
            }
        }
        return instancia;
    }
    
    // Obtener la base de datos
    public MongoDatabase getDatabase() {
        return database;
    }
    
    // Cerrar conexion
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexion a MongoDB cerrada");
        }
    }
    
    // Verificar si la conexion esta activa
    public boolean isConectado() {
        try {
            if (database != null) {
                database.listCollectionNames().first();
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al verificar conexion: " + e.getMessage());
        }
        return false;
    }
}
