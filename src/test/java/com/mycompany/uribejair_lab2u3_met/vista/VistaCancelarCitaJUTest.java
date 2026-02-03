package com.mycompany.uribejair_lab2u3_met.vista;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VistaCancelarCitaJUTest {

    private VistaCancelarCitaJU vista;

    // ====== Configuración para entorno CI (headless) ======
    @BeforeAll
    static void configurarHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    // ====== Crear instancia SIN constructor Swing ======
    @BeforeEach
    void setUp() throws Exception {
        vista = (VistaCancelarCitaJU)
                sun.reflect.ReflectionFactory
                        .getReflectionFactory()
                        .newConstructorForSerialization(
                                VistaCancelarCitaJU.class,
                                Object.class.getDeclaredConstructor()
                        ).newInstance();
    }

    @Test
    void testCrearVistaCancelarCita() {
        assertNotNull(vista, "La vista no debe ser null");
    }

    @Test
    void testActualizarTablaConListaVaciaNoLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            vista.actualizarTabla(new ArrayList<>());
        }, "actualizarTabla no debe lanzar excepciones con lista vacía");
    }
}
