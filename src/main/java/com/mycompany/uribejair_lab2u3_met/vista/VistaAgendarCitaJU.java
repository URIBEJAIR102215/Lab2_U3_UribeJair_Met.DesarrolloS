/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.vista;

import com.mycompany.uribejair_lab2u3_met.controlador.ControladorCitas;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * VISTA AGENDAR CITA Interfaz rediseñada para agendar nuevas citas
 */
public class VistaAgendarCitaJU extends JFrame {

    private ControladorCitas controlador;
    private JTextField txtNombre, txtEmail, txtTelefono;
    JComboBox<String> comboHorarios;
    private JButton btnAgendar, btnActualizar, btnVolver;

    private List<LocalDateTime> horariosActuales;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ================== PALETA OSCURA ==================
    private final Color COLOR_PRIMARIO = new Color(30, 30, 30);      // Negro suave
    private final Color COLOR_SECUNDARIO = new Color(20, 33, 61);    // Azul acero
    private final Color COLOR_ACENTO = new Color(0, 121, 107);       // Verde petróleo
    private final Color COLOR_PELIGRO = new Color(176, 32, 32);      // Rojo oscuro
    private final Color COLOR_FONDO = new Color(240, 240, 240);      // Gris claro
    private final Color COLOR_PANEL = Color.WHITE;

    public VistaAgendarCitaJU() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Citas | Agendar");
        setSize(780, 760);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(COLOR_FONDO);
    }

    private void inicializarComponentes() {
        add(crearPanelTitulo(), BorderLayout.NORTH);
        add(crearPanelFormulario(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    // ================== TITULO ==================
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_SECUNDARIO);
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("AGENDAR NUEVA CITA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Ingrese los datos requeridos para el registro");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(200, 200, 200));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(lblSub);

        return panel;
    }

    // ================== FORMULARIO ==================
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_PRIMARIO, 2, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(14, 14, 14, 14);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(crearLabel("Nombre Completo"), gbc);

        gbc.gridx = 1;
        txtNombre = crearTextField();
        panel.add(txtNombre, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(crearLabel("Correo Electrónico"), gbc);

        gbc.gridx = 1;
        txtEmail = crearTextField();
        panel.add(txtEmail, gbc);

        // Teléfono
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(crearLabel("Número de Teléfono"), gbc);

        gbc.gridx = 1;
        txtTelefono = crearTextField();
        panel.add(txtTelefono, gbc);

        // Horario
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(crearLabel("Horario Disponible"), gbc);

        gbc.gridx = 1;
        comboHorarios = new JComboBox<>();
        comboHorarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboHorarios.setPreferredSize(new Dimension(380, 42));
        comboHorarios.setBackground(Color.WHITE);
        comboHorarios.setBorder(new LineBorder(COLOR_PRIMARIO, 1, true));
        panel.add(comboHorarios, gbc);

        // Info
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 14, 10, 14);
        panel.add(crearPanelInfo(), gbc);

        return panel;
    }

    // ================== PANEL INFO (ESTILO REGLAS) ==================
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(38, 50, 56));
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2, true),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel titulo = new JLabel("REGLAS DEL SISTEMA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));

        String[] reglas = {
            "La cita debe registrarse con 24 horas de anticipación",
            "Duración estándar: 30 minutos",
            "La cancelación se realiza desde el menú principal",
            "Se enviará confirmación al correo registrado"
        };

        for (String r : reglas) {
            JLabel lbl = new JLabel("• " + r);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lbl.setForeground(new Color(207, 216, 220));
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(lbl);
            panel.add(Box.createVerticalStrut(4));
        }

        return panel;
    }

    // ================== BOTONES ==================
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        panel.setBackground(COLOR_FONDO);

        btnVolver = crearBoton("Volver", new Color(69, 90, 100));
        btnActualizar = crearBoton("Actualizar Horarios", COLOR_SECUNDARIO);
        btnAgendar = crearBoton("Confirmar Cita", COLOR_ACENTO);

        btnVolver.addActionListener(e -> dispose());
        btnActualizar.addActionListener(e -> controlador.actualizarHorariosDisponibles());
        btnAgendar.addActionListener(e -> agendarCita());

        panel.add(btnVolver);
        panel.add(btnActualizar);
        panel.add(btnAgendar);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(210, 48));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(COLOR_PRIMARIO);
        return lbl;
    }

    private JTextField crearTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setPreferredSize(new Dimension(380, 42));
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_PRIMARIO, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        return txt;
    }

    // ================== LOGICA (SIN CAMBIOS) ==================
    private void agendarCita() {
        if (comboHorarios.getSelectedIndex() == -1 || horariosActuales == null || horariosActuales.isEmpty()) {
            mostrarError("Seleccione un horario válido");
            return;
        }

        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            mostrarError("Complete todos los campos");
            return;
        }

        LocalDateTime fecha = horariosActuales.get(comboHorarios.getSelectedIndex());
        controlador.agendarCita(nombre, email, telefono, fecha);
    }

    public void setControlador(ControladorCitas c) {
        this.controlador = c;
    }

    public void actualizarComboHorarios(List<LocalDateTime> horarios) {
        horariosActuales = horarios;
        comboHorarios.removeAllItems();

        if (horarios.isEmpty()) {
            comboHorarios.addItem("Sin horarios disponibles");
            comboHorarios.setEnabled(false);
            btnAgendar.setEnabled(false);
            return;
        }

        comboHorarios.setEnabled(true);
        btnAgendar.setEnabled(true);

        for (LocalDateTime h : horarios) {
            comboHorarios.addItem(h.format(formatter));
        }
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        if (comboHorarios.getItemCount() > 0) {
            comboHorarios.setSelectedIndex(0);
        }
        txtNombre.requestFocus();
        System.out.println("Formulario limpiado");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Informacion",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje,
                "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}
