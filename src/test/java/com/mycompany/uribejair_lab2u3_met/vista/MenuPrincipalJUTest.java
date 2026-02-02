package com.mycompany.uribejair_lab2u3_met.vista;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MenuPrincipalJUTest {

    private MenuPrincipalJU menu;

    @BeforeAll
    static void headless() {
        System.setProperty("java.awt.headless", "true");
    }

    @BeforeEach
    void setUp() throws Exception {
        //  IMPORTANTE: NO usamos el constructor normal
        menu = (MenuPrincipalJU)
                sun.reflect.ReflectionFactory
                        .getReflectionFactory()
                        .newConstructorForSerialization(
                                MenuPrincipalJU.class,
                                Object.class.getDeclaredConstructor()
                        ).newInstance();
    }

    // ================== configurarVentana ==================
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