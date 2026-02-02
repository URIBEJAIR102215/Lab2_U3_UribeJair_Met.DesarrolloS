package com.mycompany.uribejair_lab2u3_met.vista;

import com.mycompany.uribejair_lab2u3_met.modelo.ConfiguracionHorario;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class VistaGestionHorariosTest {

    @Test
    public void testCrearVistaGestionHorarios() {
        VistaGestionHorarios vista = new VistaGestionHorarios();
        assertNotNull(vista, "La vista no debe ser null");
    }

    @Test
    public void testCargarDatosNoLanzaExcepcion() {
        VistaGestionHorarios vista = new VistaGestionHorarios();

        ConfiguracionHorario config = new ConfiguracionHorario(
                "Lunes",
                LocalTime.of(8, 0),
                LocalTime.of(12, 0),
                30,
                5,
                true
        );

        assertDoesNotThrow(() -> {
            vista.cargarDatos(config);
        }, "cargarDatos no debe lanzar excepciones");
    }
}
