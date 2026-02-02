/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.controlador;

import com.mycompany.uribejair_lab2u3_met.modelo.GestorHorarios;
import com.mycompany.uribejair_lab2u3_met.vista.VistaGestionHorarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
public class ControladorHorariosTest {

    private ControladorHorarios controlador;
    private VistaFake vista;

    @BeforeEach
    void setUp() {
        controlador = new ControladorHorarios(new GestorHorarios(), vista = new VistaFake());
    }

    @Test
    void noGuardarSiHoraFinEsMenor() {
        controlador.guardarConfiguracion("Lunes",
                LocalTime.of(10, 0),
                LocalTime.of(9, 0),
                30, 5, true);

        assertTrue(vista.errorMostrado);
    }

    // ============== FAKE VISTA =================
    class VistaFake extends VistaGestionHorarios {
        boolean errorMostrado = false;

        @Override
        public void mostrarError(String msg) {
            errorMostrado = true;
        }
    }
}