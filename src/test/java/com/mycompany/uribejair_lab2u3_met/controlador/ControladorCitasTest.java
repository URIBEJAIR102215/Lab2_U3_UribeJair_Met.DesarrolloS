/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.controlador;

import com.mycompany.uribejair_lab2u3_met.modelo.Cita;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorCitas;
import com.mycompany.uribejair_lab2u3_met.vista.VistaAgendarCitaJU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorCitasTest {

    private ControladorCitas controlador;
    private GestorCitasFake gestor;
    private VistaAgendarCitaFake vista;

    @BeforeEach
    void setUp() {
        gestor = new GestorCitasFake();
        vista = new VistaAgendarCitaFake();
        controlador = new ControladorCitas(gestor, vista);
    }

    @Test
    void noAgendarSiHorarioOcupado() {
        gestor.ocupado = true;
        controlador.agendarCita("Ana", "a@a.com", "123", LocalDateTime.now());
        assertTrue(vista.errorMostrado);
    }

    @Test
    void agendarCitaCorrectamente() {
        gestor.ocupado = false;
        controlador.agendarCita("Ana", "a@a.com", "123", LocalDateTime.now());
        assertTrue(vista.exitoMostrado);
    }

    // ================= FAKE CLASSES =================
    class GestorCitasFake extends GestorCitas {

        boolean ocupado = false;

        @Override
        public boolean existeCitaEnHorario(LocalDateTime fechaHora) {
            return ocupado;
        }

        @Override
        public int generarNuevoId() {
            return 1;
        }

        @Override
        public boolean agregarCita(Cita cita) {
            return true;
        }
    }

    class VistaAgendarCitaFake extends VistaAgendarCitaJU {

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
