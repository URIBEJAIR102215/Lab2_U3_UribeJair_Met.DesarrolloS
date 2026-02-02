/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.vista;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class VistaCancelarCitaJUTest {

    @Test
    public void testCrearVistaCancelarCita() {
        VistaCancelarCitaJU vista = new VistaCancelarCitaJU();
        assertNotNull(vista, "La vista no debe ser null");
    }

    @Test
    public void testActualizarTablaConListaVaciaNoLanzaExcepcion() {
        VistaCancelarCitaJU vista = new VistaCancelarCitaJU();

        assertDoesNotThrow(() -> {
            vista.actualizarTabla(new ArrayList<>());
        }, "actualizarTabla no debe lanzar excepciones con lista vacía");
    }
}
