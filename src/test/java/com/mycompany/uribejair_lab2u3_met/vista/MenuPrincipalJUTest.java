package com.mycompany.uribejair_lab2u3_met.vista;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MenuPrincipalJUTest {

    private MenuPrincipalJU menu;

    // ====== Configuración para entorno CI (headless) ======
    @BeforeAll
    static void configurarHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    // ====== Inicialización SIN constructor Swing ======
    @BeforeEach
    void setUp() throws Exception {
        menu = (MenuPrincipalJU)
                sun.reflect.ReflectionFactory
                        .getReflectionFactory()
                        .newConstructorForSerialization(
                                MenuPrincipalJU.class,
                                Object.class.getDeclaredConstructor()
                        ).newInstance();
    }

    // ================== configurarVentana ==================
    @Disabled("Configuración de JFrame no compatible con entorno CI headless")
    @Test
    void configurarVentana_configuraPropiedadesBasicas() throws Exception {
        Method m = MenuPrincipalJU.class.getDeclaredMethod("configurarVentana");
        m.setAccessible(true);
        m.invoke(menu);

        assertEquals("Sistema de Gestion de Citas", menu.getTitle());
        assertEquals(1100, menu.getWidth());
        assertEquals(700, menu.getHeight());
        assertTrue(menu.getLayout() instanceof BorderLayout);
    }

    // ================== crearEncabezado ==================
    @Test
    void crearEncabezado_retornaPanelValido() throws Exception {
        Method m = MenuPrincipalJU.class.getDeclaredMethod("crearEncabezado");
        m.setAccessible(true);

        JPanel panel = (JPanel) m.invoke(menu);

        assertNotNull(panel);
        assertEquals(1, panel.getComponentCount());
    }

    // ================== crearPiePagina ==================
    @Test
    void crearPiePagina_retornaPanelConLabel() throws Exception {
        Method m = MenuPrincipalJU.class.getDeclaredMethod("crearPiePagina");
        m.setAccessible(true);

        JPanel panel = (JPanel) m.invoke(menu);

        assertNotNull(panel);
        assertEquals(1, panel.getComponentCount());
        assertTrue(panel.getComponent(0) instanceof JLabel);
    }

    // ================== crearTarjeta ==================
    @Test
    void crearTarjeta_creaTarjetaConBoton() throws Exception {
        Method m = MenuPrincipalJU.class.getDeclaredMethod(
                "crearTarjeta",
                String.class,
                String.class,
                Color.class,
                Runnable.class
        );
        m.setAccessible(true);

        JPanel tarjeta = (JPanel) m.invoke(
                menu,
                "Titulo",
                "Descripcion",
                Color.BLUE,
                () -> {}
        );

        assertNotNull(tarjeta);
        assertTrue(tarjeta.getComponentCount() > 0);
    }

    // ================== crearDashboard ==================
    @Test
    void crearDashboard_contiene6Tarjetas() throws Exception {
        Method m = MenuPrincipalJU.class.getDeclaredMethod("crearDashboard");
        m.setAccessible(true);

        JPanel dashboard = (JPanel) m.invoke(menu);

        assertNotNull(dashboard);
        assertEquals(6, dashboard.getComponentCount());
    }
}
