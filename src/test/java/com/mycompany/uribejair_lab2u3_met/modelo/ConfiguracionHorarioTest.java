/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class ConfiguracionHorarioTest {

    @Test
    void constructor_vacio_debe_crear_objeto() {
        ConfiguracionHorario config = new ConfiguracionHorario();
        assertNotNull(config);
    }

    @Test
    void constructor_completo_debe_asignar_valores_correctos() {
        LocalTime inicio = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(17, 0);

        ConfiguracionHorario config = new ConfiguracionHorario(
                "Lunes",
                inicio,
                fin,
                30,
                5,
                true
        );

        assertEquals("Lunes", config.getDiaSemana());
        assertEquals(inicio, config.getHoraInicio());
        assertEquals(fin, config.getHoraFin());
        assertEquals(30, config.getDuracionCita());
        assertEquals(5, config.getTiempoDescanso());
        assertTrue(config.isActivo());
    }

    @Test
    void setters_y_getters_deben_funcionar_correctamente() {
        ConfiguracionHorario config = new ConfiguracionHorario();

        config.setDiaSemana("Martes");
        config.setHoraInicio(LocalTime.of(8, 0));
        config.setHoraFin(LocalTime.of(16, 0));
        config.setDuracionCita(20);
        config.setTiempoDescanso(10);
        config.setActivo(false);

        assertEquals("Martes", config.getDiaSemana());
        assertEquals(LocalTime.of(8, 0), config.getHoraInicio());
        assertEquals(LocalTime.of(16, 0), config.getHoraFin());
        assertEquals(20, config.getDuracionCita());
        assertEquals(10, config.getTiempoDescanso());
        assertFalse(config.isActivo());
    }

    @Test
    void toString_debe_contener_informacion_clave() {
        ConfiguracionHorario config = new ConfiguracionHorario(
                "Viernes",
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                30,
                5,
                true
        );

        String texto = config.toString();

        assertTrue(texto.contains("Viernes"));
        assertTrue(texto.contains("30"));
        assertTrue(texto.contains("true"));
    }
}
