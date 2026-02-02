package com.mycompany.uribejair_lab2u3_met.vista;

import com.mycompany.uribejair_lab2u3_met.controlador.ControladorCancelarCita;
import com.mycompany.uribejair_lab2u3_met.controlador.ControladorCitas;
import com.mycompany.uribejair_lab2u3_met.controlador.ControladorHorarios;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorCitas;
import com.mycompany.uribejair_lab2u3_met.modelo.GestorHorarios;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * MENU PRINCIPAL - DASHBOARD
 * Version limpia, moderna y sin cuadrados feos 😎
 */
public class MenuPrincipalJU extends JFrame {

    private GestorCitas gestorCitas;
    private GestorHorarios gestorHorarios;

    // Paleta sobria
    private final Color COLOR_PRIMARIO = new Color(44, 62, 80);
    private final Color COLOR_SECUNDARIO = new Color(52, 152, 219);
    private final Color COLOR_ACENTO = new Color(39, 174, 96);
    private final Color COLOR_INFO = new Color(142, 68, 173);
    private final Color COLOR_OSCURO = new Color(52, 73, 94);
    private final Color FONDO_CLARO = new Color(236, 240, 241);
    private final Color TARJETA = Color.WHITE;

    public MenuPrincipalJU() {
        gestorCitas = new GestorCitas();
        gestorHorarios = new GestorHorarios();
        configurarVentana();
        cargarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestion de Citas");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(FONDO_CLARO);
    }

    private void cargarComponentes() {
        add(crearEncabezado(), BorderLayout.NORTH);
        add(crearDashboard(), BorderLayout.CENTER);
        add(crearPiePagina(), BorderLayout.SOUTH);
    }

    // ================= ENCABEZADO =================

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PRIMARIO);
        panel.setBorder(new EmptyBorder(25, 35, 25, 35));

        JLabel titulo = new JLabel("Sistema de Gestión de Citas Médicas");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));

        JLabel sub = new JLabel("Panel de administración y gestión");
        sub.setForeground(new Color(200, 200, 200));
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(sub);

        panel.add(textos, BorderLayout.WEST);
        return panel;
    }

    // ================= DASHBOARD =================

    private JPanel crearDashboard() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setBackground(FONDO_CLARO);

        panel.add(crearTarjeta("Agendar Nueva Cita",
                "Reserva una cita en el sistema",
                COLOR_SECUNDARIO,
                this::abrirAgendarCita));

        panel.add(crearTarjeta("Gestionar Mis Citas",
                "Consulta, modifica o cancela citas",
                COLOR_ACENTO,
                this::abrirCancelarCita));

        panel.add(crearTarjeta("Configurar Horarios",
                "Define horarios de atención",
                COLOR_INFO,
                this::abrirGestionHorarios));

        panel.add(crearTarjeta("Historial de Citas",
                "Revisa citas anteriores",
                COLOR_SECUNDARIO,
                () -> mostrarProximamente("Historial de Citas")));

        panel.add(crearTarjeta("Notificaciones",
                "Alertas y recordatorios",
                COLOR_ACENTO,
                () -> mostrarProximamente("Notificaciones")));

        panel.add(crearTarjeta("Reportes y Estadísticas",
                "Análisis del sistema",
                COLOR_OSCURO,
                () -> mostrarProximamente("Reportes y Estadísticas")));

        return panel;
    }

    // ================= TARJETA MODERNA =================

    private JPanel crearTarjeta(String titulo, String descripcion, Color color, Runnable accion) {

        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(TARJETA);
        tarjeta.setBorder(new LineBorder(new Color(210, 210, 210), 1, true));
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Barra lateral de color (CLAVE)
        JPanel barra = new JPanel();
        barra.setBackground(color);
        barra.setPreferredSize(new Dimension(6, 0));
        tarjeta.add(barra, BorderLayout.WEST);

        // Contenido
        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COLOR_PRIMARIO);

        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(120, 120, 120));

        JButton btn = new JButton("Acceder");
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(120, 36));
        btn.setAlignmentX(Component.RIGHT_ALIGNMENT);

        btn.addActionListener(e -> accion.run());

        contenido.add(lblTitulo);
        contenido.add(Box.createVerticalStrut(6));
        contenido.add(lblDesc);
        contenido.add(Box.createVerticalGlue());
        contenido.add(btn);

        tarjeta.add(contenido, BorderLayout.CENTER);

        // Hover elegante
        tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                tarjeta.setBorder(new LineBorder(color, 2, true));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                tarjeta.setBorder(new LineBorder(new Color(210, 210, 210), 1, true));
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
                accion.run();
            }
        });

        return tarjeta;
    }

    // ================= PIE =================

    private JPanel crearPiePagina() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_PRIMARIO);
        panel.setBorder(new EmptyBorder(12, 10, 12, 10));

        JLabel lbl = new JLabel("Sistema de Gestión de Citas v2.0 | Java Swing");
        lbl.setForeground(new Color(200, 200, 200));
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        panel.add(lbl);
        return panel;
    }

    // ================= ACCIONES =================

    private void abrirAgendarCita() {
        VistaAgendarCitaJU vista = new VistaAgendarCitaJU();
        ControladorCitas c = new ControladorCitas(gestorCitas, vista);
        c.iniciar();
    }

    private void abrirCancelarCita() {
        VistaCancelarCitaJU vista = new VistaCancelarCitaJU();
        ControladorCancelarCita c = new ControladorCancelarCita(gestorCitas, vista);
        c.iniciar();
    }

    private void abrirGestionHorarios() {
        VistaGestionHorarios vista = new VistaGestionHorarios();
        ControladorHorarios c = new ControladorHorarios(gestorHorarios, vista);
        c.iniciar();
    }

    private void mostrarProximamente(String f) {
        JOptionPane.showMessageDialog(this,
                "La funcionalidad \"" + f + "\" estará disponible próximamente.",
                "Próximamente",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
