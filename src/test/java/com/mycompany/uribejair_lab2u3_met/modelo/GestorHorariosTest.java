/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class GestorHorariosTest {

    @Test
    void constructor_no_debe_lanzar_excepcion() {
        assertDoesNotThrow(GestorHorarios::new);
    }

    @Test
    void obtenerConfiguracion_no_debe_retornar_null() {
        GestorHorarios gestor = new GestorHorarios();
        ConfiguracionHorario config = gestor.obtenerConfiguracion("Lunes");

        assertNotNull(config);
        assertEquals("Lunes", config.getDiaSemana());
    }

    @Test
    void obtenerTodasLasConfiguraciones_no_debe_retornar_null() {
        GestorHorarios gestor = new GestorHorarios();
        List<ConfiguracionHorario> configs = gestor.obtenerTodasLasConfiguraciones();

        assertNotNull(configs);
    }

    @Test
    void obtenerDiasActivos_no_debe_retornar_null() {
        GestorHorarios gestor = new GestorHorarios();
        List<String> dias = gestor.obtenerDiasActivos();

        assertNotNull(dias);
    }

    @Test
    void actualizarEstadoDia_no_debe_lanzar_excepcion() {
        GestorHorarios gestor = new GestorHorarios();

        assertDoesNotThrow(() -> gestor.actualizarEstadoDia("Lunes", true));
    }

    @Test
    void eliminarConfiguracion_no_debe_lanzar_excepcion() {
        GestorHorarios gestor = new GestorHorarios();

        assertDoesNotThrow(() -> gestor.eliminarConfiguracion("Domingo"));
    }
}
