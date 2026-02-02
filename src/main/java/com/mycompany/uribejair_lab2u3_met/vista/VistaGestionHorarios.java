/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.vista;

import com.mycompany.uribejair_lab2u3_met.controlador.ControladorHorarios;
import com.mycompany.uribejair_lab2u3_met.modelo.ConfiguracionHorario;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalTime;

/**
 * VISTA GESTION DE HORARIOS
 * Interfaz rediseñada para configurar horarios de atencion
 */
public class VistaGestionHorarios extends JFrame {
    
    private ControladorHorarios controlador;
    private JComboBox<String> comboDias;
    private JSpinner spinnerHoraInicio;
    private JSpinner spinnerHoraFin;
    private JSpinner spinnerDuracion;
    private JSpinner spinnerDescanso;
    private JCheckBox checkActivo;
    private JLabel lblCapacidad;
    private JButton btnGuardar, btnCargar, btnVolver, btnRestaurar;
    
    private String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    
    // Paleta de colores
    private final Color COLOR_PRIMARIO = new Color(44, 62, 80);
    private final Color COLOR_SECUNDARIO = new Color(52, 152, 219);
    private final Color COLOR_ACENTO = new Color(39, 174, 96);
    private final Color COLOR_INFO = new Color(142, 68, 173);
    private final Color COLOR_ADVERTENCIA = new Color(231, 76, 60);
    private final Color COLOR_OSCURO = new Color(52, 73, 94);
    private final Color FONDO_CLARO = new Color(236, 240, 241);
    private final Color FONDO_BLANCO = new Color(255, 255, 255);
    
    public VistaGestionHorarios() {
        configurarVentana();
        inicializarComponentes();
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Gestion de Citas - Configuracion de Horarios");
        setSize(800, 750);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(FONDO_CLARO);
    }
    
    private void inicializarComponentes() {
        add(crearPanelTitulo(), BorderLayout.NORTH);
        add(crearPanelFormulario(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_INFO);
        panel.setBorder(new EmptyBorder(30, 25, 30, 25));
        
        JLabel lblTitulo = new JLabel("Configuracion de Horarios");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Configure los horarios de atencion por dia de la semana");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitulo.setForeground(new Color(236, 240, 241));
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblSubtitulo);
        
        return panel;
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(25, 25, 25, 25),
            BorderFactory.createCompoundBorder(
                new LineBorder(new Color(189, 195, 199), 2, true),
                new EmptyBorder(30, 30, 30, 30)
            )
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);
        
        // Dia de la semana
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(crearLabel("Dia de la Semana"), gbc);
        
        gbc.gridx = 1;
        comboDias = new JComboBox<>(dias);
        comboDias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboDias.setPreferredSize(new Dimension(300, 40));
        comboDias.setBackground(FONDO_BLANCO);
        comboDias.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
        comboDias.addActionListener(e -> cargarConfiguracionActual());
        panel.add(comboDias, gbc);
        
        // Hora inicio
        gbc.gridx = 0; 
        gbc.gridy = 1;
        panel.add(crearLabel("Hora de Inicio"), gbc);
        
        gbc.gridx = 1;
        spinnerHoraInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm");
        spinnerHoraInicio.setEditor(editorInicio);
        spinnerHoraInicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerHoraInicio.setPreferredSize(new Dimension(300, 40));
        spinnerHoraInicio.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
        spinnerHoraInicio.addChangeListener(e -> actualizarCapacidad());
        panel.add(spinnerHoraInicio, gbc);
        
        // Hora fin
        gbc.gridx = 0; 
        gbc.gridy = 2;
        panel.add(crearLabel("Hora de Fin"), gbc);
        
        gbc.gridx = 1;
        spinnerHoraFin = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spinnerHoraFin, "HH:mm");
        spinnerHoraFin.setEditor(editorFin);
        spinnerHoraFin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerHoraFin.setPreferredSize(new Dimension(300, 40));
        spinnerHoraFin.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
        spinnerHoraFin.addChangeListener(e -> actualizarCapacidad());
        panel.add(spinnerHoraFin, gbc);
        
        // Duracion de cita
        gbc.gridx = 0; 
        gbc.gridy = 3;
        panel.add(crearLabel("Duracion por Cita (minutos)"), gbc);
        
        gbc.gridx = 1;
        spinnerDuracion = new JSpinner(new SpinnerNumberModel(30, 15, 120, 15));
        spinnerDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerDuracion.setPreferredSize(new Dimension(300, 40));
        spinnerDuracion.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
        spinnerDuracion.addChangeListener(e -> actualizarCapacidad());
        panel.add(spinnerDuracion, gbc);
        
        // Tiempo de descanso
        gbc.gridx = 0; 
        gbc.gridy = 4;
        panel.add(crearLabel("Tiempo de Descanso (minutos)"), gbc);
        
        gbc.gridx = 1;
        spinnerDescanso = new JSpinner(new SpinnerNumberModel(5, 0, 30, 5));
        spinnerDescanso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerDescanso.setPreferredSize(new Dimension(300, 40));
        spinnerDescanso.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
        spinnerDescanso.addChangeListener(e -> actualizarCapacidad());
        panel.add(spinnerDescanso, gbc);
        
        // Dia activo
        gbc.gridx = 0; 
        gbc.gridy = 5;
        panel.add(crearLabel("Dia Activo"), gbc);
        
        gbc.gridx = 1;
        checkActivo = new JCheckBox("Habilitar atencion en este dia");
        checkActivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        checkActivo.setBackground(FONDO_BLANCO);
        checkActivo.setSelected(true);
        checkActivo.setFocusPainted(false);
        checkActivo.addActionListener(e -> actualizarCapacidad());
        panel.add(checkActivo, gbc);
        
        // Panel de capacidad calculada
        gbc.gridx = 0; 
        gbc.gridy = 6; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 15, 15, 15);
        panel.add(crearPanelCapacidad(), gbc);
        
        // Panel informativo
        gbc.gridx = 0; 
        gbc.gridy = 7; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 15, 15, 15);
        panel.add(crearPanelInfo(), gbc);
        
        return panel;
    }
    
    private JPanel crearPanelCapacidad() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 243, 205));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(241, 196, 15), 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTitulo = new JLabel("Capacidad Estimada");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblCapacidad = new JLabel("0 citas por dia");
        lblCapacidad.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblCapacidad.setForeground(new Color(241, 196, 15));
        lblCapacidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblCapacidad);
        
        return panel;
    }
    
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(174, 214, 241));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_SECUNDARIO, 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblInfo = new JLabel("Informacion");
        lblInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblInfo.setForeground(COLOR_PRIMARIO);
        lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        String[] detalles = {
            "La duracion de cita incluye el tiempo de atencion al paciente",
            "El tiempo de descanso se agrega entre cada cita",
            "Los dias inactivos no generaran horarios disponibles",
            "La capacidad se calcula automaticamente segun la configuracion"
        };
        
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(12));
        
        for (String detalle : detalles) {
            JLabel lblDetalle = new JLabel("• " + detalle);
            lblDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblDetalle.setForeground(new Color(52, 73, 94));
            lblDetalle.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(lblDetalle);
            panel.add(Box.createVerticalStrut(5));
        }
        
        return panel;
    }
    
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(COLOR_PRIMARIO);
        return label;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        panel.setBackground(FONDO_CLARO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        btnVolver = crearBoton("Volver al Menu", COLOR_OSCURO);
        btnVolver.addActionListener(e -> dispose());
        
        btnRestaurar = crearBoton("Restaurar Valores", new Color(149, 165, 166));
        btnRestaurar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "Esto restaurara todos los horarios a sus valores por defecto\nDesea continuar?",
                "Confirmar Restauracion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                controlador.restaurarHorariosDefault();
            }
        });
        
        btnCargar = crearBoton("Cargar Configuracion", COLOR_SECUNDARIO);
        btnCargar.addActionListener(e -> cargarConfiguracionActual());
       
        btnGuardar = crearBoton("Guardar Configuracion", COLOR_ACENTO);
        btnGuardar.addActionListener(e -> guardarConfiguracion());
        
        panel.add(btnVolver);
        panel.add(btnRestaurar);
        panel.add(btnCargar);
        panel.add(btnGuardar);
        
        return panel;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(210, 48));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });
        
        return btn;
    }
    
    private void cargarConfiguracionActual() {
        String diaSeleccionado = (String) comboDias.getSelectedItem();
        if (controlador != null) {
            controlador.cargarConfiguracion(diaSeleccionado);
        }
    }
    
    private void guardarConfiguracion() {
        String dia = (String) comboDias.getSelectedItem();
        
        java.util.Date dateInicio = (java.util.Date) spinnerHoraInicio.getValue();
        java.util.Date dateFin = (java.util.Date) spinnerHoraFin.getValue();
        
        LocalTime inicio = LocalTime.of(dateInicio.getHours(), dateInicio.getMinutes());
        LocalTime fin = LocalTime.of(dateFin.getHours(), dateFin.getMinutes());
        
        if (fin.isBefore(inicio) || fin.equals(inicio)) {
            mostrarError("La hora de fin debe ser posterior a la hora de inicio");
            return;
        }
        
        int duracion = (Integer) spinnerDuracion.getValue();
        int descanso = (Integer) spinnerDescanso.getValue();
        boolean activo = checkActivo.isSelected();
        
        if (controlador != null) {
            controlador.guardarConfiguracion(dia, inicio, fin, duracion, descanso, activo);
        }
    }
    
    private void actualizarCapacidad() {
        try {
            java.util.Date dateInicio = (java.util.Date) spinnerHoraInicio.getValue();
            java.util.Date dateFin = (java.util.Date) spinnerHoraFin.getValue();
            
            int minutosDisponibles = (dateFin.getHours() * 60 + dateFin.getMinutes()) -
                                   (dateInicio.getHours() * 60 + dateInicio.getMinutes());
            
            int duracion = (Integer) spinnerDuracion.getValue();
            int descanso = (Integer) spinnerDescanso.getValue();
            int minutosPorCita = duracion + descanso;
            
            int capacidad = checkActivo.isSelected() && minutosPorCita > 0 
                ? minutosDisponibles / minutosPorCita 
                : 0;
                
            lblCapacidad.setText(capacidad + " citas por dia");
        } catch (Exception e) {
            lblCapacidad.setText("-- citas por dia");
        }
    }
    
    // ================== METODOS PUBLICOS PARA EL CONTROLADOR ==================
    
    public void setControlador(ControladorHorarios controlador) {
        this.controlador = controlador;
    }
    
    public void cargarDatos(ConfiguracionHorario config) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        cal.set(java.util.Calendar.HOUR_OF_DAY, config.getHoraInicio().getHour());
        cal.set(java.util.Calendar.MINUTE, config.getHoraInicio().getMinute());
        spinnerHoraInicio.setValue(cal.getTime());
        
        cal.set(java.util.Calendar.HOUR_OF_DAY, config.getHoraFin().getHour());
        cal.set(java.util.Calendar.MINUTE, config.getHoraFin().getMinute());
        spinnerHoraFin.setValue(cal.getTime());
        
        spinnerDuracion.setValue(config.getDuracionCita());
        spinnerDescanso.setValue(config.getTiempoDescanso());
        checkActivo.setSelected(config.isActivo());
        
        actualizarCapacidad();
    }
    
    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Operacion Exitosa", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
