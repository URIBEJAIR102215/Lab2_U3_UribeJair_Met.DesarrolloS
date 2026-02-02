/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uribejair_lab2u3_met.vista;

import com.mycompany.uribejair_lab2u3_met.controlador.ControladorCancelarCita;
import com.mycompany.uribejair_lab2u3_met.modelo.Cita;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * VISTA GESTIONAR CITAS
 * Interfaz rediseñada para cancelar y eliminar citas
 */
public class VistaCancelarCitaJU extends JFrame {

    private ControladorCancelarCita controlador;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;

    private JButton btnCancelar, btnActualizar, btnEliminar, btnVolver;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ================== PALETA OSCURA ==================
    private final Color COLOR_PRIMARIO = new Color(30, 30, 30);
    private final Color COLOR_SECUNDARIO = new Color(20, 33, 61);
    private final Color COLOR_ACENTO = new Color(0, 121, 107);
    private final Color COLOR_PELIGRO = new Color(176, 32, 32);
    private final Color COLOR_GRIS = new Color(69, 90, 100);
    private final Color FONDO_GENERAL = new Color(240, 240, 240);
    private final Color BLANCO = Color.WHITE;

    public VistaCancelarCitaJU() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Citas | Gestión");
        setSize(1050, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(FONDO_GENERAL);
    }

    private void inicializarComponentes() {
        add(crearPanelTitulo(), BorderLayout.NORTH);
        add(crearPanelTabla(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    // ================== TITULO ==================
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_SECUNDARIO);
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("GESTIÓN DE CITAS AGENDADAS");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Visualice, cancele o elimine citas registradas");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sub.setForeground(new Color(210, 210, 210));
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(sub);

        return panel;
    }

    // ================== TABLA ==================
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));
        panel.setBackground(FONDO_GENERAL);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(BLANCO);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_PRIMARIO, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        String[] columnas = {
                "ID", "Nombre", "Correo Electrónico",
                "Teléfono", "Fecha y Hora", "Estado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tablaCitas = new JTable(modeloTabla);
        tablaCitas.setRowHeight(40);
        tablaCitas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCitas.setSelectionBackground(new Color(200, 230, 225));
        tablaCitas.setSelectionForeground(COLOR_PRIMARIO);
        tablaCitas.setGridColor(new Color(200, 200, 200));
        tablaCitas.setShowVerticalLines(false);

        // ===== HEADER (CORREGIDO DEFINITIVO) =====
        JTableHeader header = tablaCitas.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 42));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(220, 220, 220)); // gris claro
        headerRenderer.setForeground(Color.BLACK);              // 🔥 LETRAS NEGRAS
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 14));

        for (int i = 0; i < tablaCitas.getColumnModel().getColumnCount(); i++) {
            tablaCitas.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // ===== RENDER GENERAL =====
        DefaultTableCellRenderer general = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean selected,
                    boolean focus, int row, int col) {

                Component c = super.getTableCellRendererComponent(
                        table, value, selected, focus, row, col);

                if (!selected) {
                    c.setBackground(row % 2 == 0 ? BLANCO : new Color(245, 245, 245));
                    c.setForeground(COLOR_PRIMARIO);
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        };

        for (int i = 0; i < 5; i++) {
            tablaCitas.getColumnModel().getColumn(i).setCellRenderer(general);
        }

        // ===== RENDER ESTADO =====
        tablaCitas.getColumnModel().getColumn(5).setCellRenderer(
                new DefaultTableCellRenderer() {
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean selected,
                            boolean focus, int row, int col) {

                        Component c = super.getTableCellRendererComponent(
                                table, value, selected, focus, row, col);

                        setHorizontalAlignment(SwingConstants.CENTER);
                        setFont(new Font("Segoe UI", Font.BOLD, 13));

                        if (!selected) {
                            c.setBackground(row % 2 == 0 ? BLANCO : new Color(245, 245, 245));
                            if ("Confirmada".equalsIgnoreCase(value.toString())) {
                                c.setForeground(COLOR_ACENTO);
                            } else {
                                c.setForeground(COLOR_PELIGRO);
                            }
                        }
                        return c;
                    }
                });

        // ===== ANCHOS =====
        tablaCitas.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaCitas.getColumnModel().getColumn(1).setPreferredWidth(180);
        tablaCitas.getColumnModel().getColumn(2).setPreferredWidth(240);
        tablaCitas.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaCitas.getColumnModel().getColumn(4).setPreferredWidth(180);
        tablaCitas.getColumnModel().getColumn(5).setPreferredWidth(110);

        JScrollPane scroll = new JScrollPane(tablaCitas);
        scroll.setBorder(new LineBorder(COLOR_PRIMARIO, 1));
        scroll.getViewport().setBackground(BLANCO);

        contenedor.add(scroll, BorderLayout.CENTER);
        panel.add(contenedor, BorderLayout.CENTER);

        return panel;
    }

    // ================== BOTONES ==================
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 18));
        panel.setBackground(FONDO_GENERAL);

        btnVolver = crearBoton("Volver", COLOR_GRIS);
        btnActualizar = crearBoton("Actualizar", COLOR_SECUNDARIO);
        btnCancelar = crearBoton("Cancelar Cita", COLOR_PELIGRO);
        btnEliminar = crearBoton("Eliminar Cita", new Color(120, 120, 120));

        btnVolver.addActionListener(e -> dispose());
        btnActualizar.addActionListener(e -> controlador.actualizarListaCitas());
        btnCancelar.addActionListener(e -> cancelarCitaSeleccionada());
        btnEliminar.addActionListener(e -> eliminarCitaSeleccionada());

        panel.add(btnVolver);
        panel.add(btnActualizar);
        panel.add(btnCancelar);
        panel.add(btnEliminar);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(200, 46));
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

    // ================== LOGICA ==================
    private void cancelarCitaSeleccionada() {
        int fila = tablaCitas.getSelectedRow();
        if (fila == -1) {
            mostrarError("Seleccione una cita");
            return;
        }
        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        controlador.cancelarCita(id);
    }

    private void eliminarCitaSeleccionada() {
        int fila = tablaCitas.getSelectedRow();
        if (fila == -1) {
            mostrarError("Seleccione una cita");
            return;
        }
        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        controlador.eliminarCita(id);
    }

    // ================== METODOS PUBLICOS ==================
    public void setControlador(ControladorCancelarCita c) {
        controlador = c;
    }

    public void actualizarTabla(List<Cita> citas) {
        modeloTabla.setRowCount(0);
        for (Cita c : citas) {
            modeloTabla.addRow(new Object[]{
                    c.getId(),
                    c.getNombreUsuario(),
                    c.getEmail(),
                    c.getTelefono(),
                    c.getFechaHora().format(formatter),
                    c.getEstado()
            });
        }
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Operacion Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Informacion",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
