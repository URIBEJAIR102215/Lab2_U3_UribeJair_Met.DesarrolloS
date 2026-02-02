/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class GestorCitasTest {

    @Test
    void constructor_no_debe_lanzar_excepcion() {
        assertDoesNotThrow(GestorCitas::new);
    }

    @Test
    void generarNuevoId_no_debe_lanzar_excepcion() {
        GestorCitas gestor = new GestorCitas();
        assertDoesNotThrow(gestor::generarNuevoId);
    }

    @Test
    void obtenerTodasLasCitas_no_debe_retornar_null() {
        GestorCitas gestor = new GestorCitas();
        List<Cita> citas = gestor.obtenerTodasLasCitas();
        assertNotNull(citas);
    }

    @Test
    void obtenerCitaPorId_no_debe_lanzar_excepcion() {
        GestorCitas gestor = new GestorCitas();
        assertDoesNotThrow(() -> gestor.obtenerCitaPorId(1));
    }

    @Test
    void obtenerCitasPorEstado_no_debe_retornar_null() {
        GestorCitas gestor = new GestorCitas();
        List<Cita> citas = gestor.obtenerCitasPorEstado("Confirmada");
        assertNotNull(citas);
    }

    @Test
    void existeCitaEnHorario_no_debe_lanzar_excepcion() {
        GestorCitas gestor = new GestorCitas();
        LocalDateTime fecha = LocalDateTime.now();

        assertDoesNotThrow(() -> gestor.existeCitaEnHorario(fecha));
    }
}
