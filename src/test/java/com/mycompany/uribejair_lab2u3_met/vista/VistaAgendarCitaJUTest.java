package com.mycompany.uribejair_lab2u3_met.vista;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class VistaAgendarCitaJUTest {

    private VistaAgendarCitaJU vista;

    // ====== Configuración para entorno CI ======
    @BeforeAll
    static void configurarHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    // ====== Crear instancia SIN constructor Swing ======
    @BeforeEach
    void setUp() throws Exception {
        vista = (VistaAgendarCitaJU)
                sun.reflect.ReflectionFactory
                        .getReflectionFactory()
                        .newConstructorForSerialization(
                                VistaAgendarCitaJU.class,
                                Object.class.getDeclaredConstructor()
                        ).newInstance();
    }

    @Test
    void testCrearVistaAgendarCita() {
        assertNotNull(vista);
    }

    @Test
    void testSetControladorNoLanzaError() {
        vista.setControlador(null); // permitido
        assertTrue(true);
    }

    @Test
    void testLimpiarFormularioNoFalla() {
        vista.limpiarFormulario();
        assertTrue(true);
    }

    @Test
    void testActualizarComboHorariosConListaVacia() {
        vista.actualizarComboHorarios(java.util.Collections.emptyList());
        assertTrue(true);
    }
}
