/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.controlador;

import com.mycompany.uribejair_lab2u3_met.modelo.Cita;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorCitas;
import com.mycompany.uribejair_lab2u3_met.vista.VistaCancelarCitaJU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorCancelarCitaTest {

    private ControladorCancelarCita controlador;
    private GestorCitasFake gestor;
    private VistaCancelarCitaFake vista;

    @BeforeEach
    void setUp() {
        gestor = new GestorCitasFake();
        vista = new VistaCancelarCitaFake();
        controlador = new ControladorCancelarCita(gestor, vista);
    }

    @Test
    void cancelarCitaExistente() {
        controlador.cancelarCita(1);
        assertEquals("Cancelada", gestor.cita.getEstado());
        assertTrue(vista.exitoMostrado);
    }

    @Test
    void cancelarCitaInexistente() {
        controlador.cancelarCita(99);
        assertTrue(vista.errorMostrado);
    }

    @Test
    void cancelarCitaYaCancelada() {
        gestor.cita.setEstado("Cancelada");
        controlador.cancelarCita(1);
        assertTrue(vista.errorMostrado);
    }

    // ================= FAKE CLASSES =================
    class GestorCitasFake extends GestorCitas {

        Cita cita = new Cita(1, "Juan", "a@a.com", "123",
                LocalDateTime.now(), "Confirmada");

        @Override
        public Cita obtenerCitaPorId(int id) {
            return id == 1 ? cita : null;
        }

        @Override
        public boolean actualizarEstadoCita(int id, String estado) {
            cita.setEstado(estado);
            return true;
        }

        @Override
        public List<Cita> obtenerTodasLasCitas() {
            List<Cita> lista = new ArrayList<>();
            lista.add(cita);
            return lista;
        }
    }

    class VistaCancelarCitaFake extends VistaCancelarCitaJU {

        boolean exitoMostrado = false;
        boolean errorMostrado = false;

        @Override
        public void mostrarExito(String msg) {
            exitoMostrado = true;
        }

        @Override
        public void mostrarError(String msg) {
            errorMostrado = true;
        }
    }
}
