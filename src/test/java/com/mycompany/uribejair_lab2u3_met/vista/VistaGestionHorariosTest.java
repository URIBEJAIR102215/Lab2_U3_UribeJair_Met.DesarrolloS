package com.mycompany.uribejair_lab2u3_met.vista;

import com.mycompany.uribejair_lab2u3_met.modelo.ConfiguracionHorario;
import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class VistaGestionHorariosTest {

    private VistaGestionHorarios vista;

    // ====== Configuración para entorno CI (headless) ======
    @BeforeAll
    static void configurarHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    // ====== Crear instancia SIN constructor Swing ======
    @BeforeEach
    void setUp() throws Exception {
        vista = (VistaGestionHorarios)
                sun.reflect.ReflectionFactory
                        .getReflectionFactory()
                        .newConstructorForSerialization(
                                VistaGestionHorarios.class,
                                Object.class.getDeclaredConstructor()
                        ).newInstance();
    }

    @Test
    void testCrearVistaGestionHorarios() {
        assertNotNull(vista, "La vista no debe ser null");
    }

    @Test
    void testCargarDatosNoLanzaExcepcion() {
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
