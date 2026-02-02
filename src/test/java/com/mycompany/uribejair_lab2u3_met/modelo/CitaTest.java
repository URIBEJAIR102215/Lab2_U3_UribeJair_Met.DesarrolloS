/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.modelo;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.assertions.Assertions.assertTrue;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author HP
 */
public class CitaTest {

    @Test
    void constructor_vacio_debe_crear_objeto() {
        Cita cita = new Cita();
        assertNotNull(cita);
    }

    @Test
    void constructor_completo_debe_asignar_valores_correctos() {
        LocalDateTime fecha = LocalDateTime.now();

        Cita cita = new Cita(
                1,
                "Juan Perez",
                "juan@email.com",
                "0999999999",
                fecha,
                "Confirmada"
        );

        assertEquals(1, cita.getId());
        assertEquals("Juan Perez", cita.getNombreUsuario());
        assertEquals("juan@email.com", cita.getEmail());
        assertEquals("0999999999", cita.getTelefono());
        assertEquals(fecha, cita.getFechaHora());
        assertEquals("Confirmada", cita.getEstado());
    }

    @Test
    void setters_y_getters_deben_funcionar_correctamente() {
        Cita cita = new Cita();
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 10, 30);

        cita.setId(10);
        cita.setNombreUsuario("Maria");
        cita.setEmail("maria@mail.com");
        cita.setTelefono("0988888888");
        cita.setFechaHora(fecha);
        cita.setEstado("Cancelada");

        assertEquals(10, cita.getId());
        assertEquals("Maria", cita.getNombreUsuario());
        assertEquals("maria@mail.com", cita.getEmail());
        assertEquals("0988888888", cita.getTelefono());
        assertEquals(fecha, cita.getFechaHora());
        assertEquals("Cancelada", cita.getEstado());
    }

    @Test
    void toString_debe_contener_datos_principales() {
        LocalDateTime fecha = LocalDateTime.now();
        Cita cita = new Cita(5, "Luis", "luis@mail.com", "0977777777", fecha, "Confirmada");

        String texto = cita.toString();

        assertTrue(texto.contains("Luis"));
        assertTrue(texto.contains("Confirmada"));
        assertTrue(texto.contains("id=5"));
    }
}
