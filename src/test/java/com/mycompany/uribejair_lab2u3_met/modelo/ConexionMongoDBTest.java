/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author HP
 */
public class ConexionMongoDBTest {

    @Test
    void getInstancia_debe_retornar_instancia_no_nula() {
        ConexionMongoDB conexion = ConexionMongoDB.getInstancia();
        assertNotNull(conexion);
    }

    @Test
    void getInstancia_debe_retornar_la_misma_instancia_singleton() {
        ConexionMongoDB conexion1 = ConexionMongoDB.getInstancia();
        ConexionMongoDB conexion2 = ConexionMongoDB.getInstancia();

        assertSame(conexion1, conexion2, "Debe devolver la misma instancia (Singleton)");
    }

    @Test
    void getDatabase_puede_ser_accedido_sin_error() {
        ConexionMongoDB conexion = ConexionMongoDB.getInstancia();
        assertDoesNotThrow(conexion::getDatabase);
    }

    @Test
    void cerrarConexion_no_debe_lanzar_excepcion() {
        ConexionMongoDB conexion = ConexionMongoDB.getInstancia();

        assertDoesNotThrow(conexion::cerrarConexion);
    }

    @Test
    void isConectado_no_debe_lanzar_excepcion() {
        ConexionMongoDB conexion = ConexionMongoDB.getInstancia();

        assertDoesNotThrow(conexion::isConectado);
    }
}