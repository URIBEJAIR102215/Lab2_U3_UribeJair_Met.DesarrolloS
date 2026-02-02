/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.vista;


import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class VistaAgendarCitaJUTest {

    @Test
    void testCrearVistaAgendarCita() {
        VistaAgendarCitaJU vista = new VistaAgendarCitaJU();
        assertNotNull(vista);
    }

    @Test
    void testSetControladorNoLanzaError() {
        VistaAgendarCitaJU vista = new VistaAgendarCitaJU();
        vista.setControlador(null); // permitido
        assertTrue(true); // si llega aquí, pasa
    }

    @Test
    void testLimpiarFormularioNoFalla() {
        VistaAgendarCitaJU vista = new VistaAgendarCitaJU();
        vista.limpiarFormulario();
        assertTrue(true);
    }

    @Test
    void testActualizarComboHorariosConListaVacia() {
        VistaAgendarCitaJU vista = new VistaAgendarCitaJU();
        vista.actualizarComboHorarios(java.util.Collections.emptyList());
        assertTrue(true);
    }
}