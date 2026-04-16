package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class VentanaFinDeJornada extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblFecha;
    private JLabel lblClientes;
    private JTable tablaClientes;
    private DefaultTableModel modeloClientes;
    private JScrollPane scrollClientes;
    private JLabel lblKit;
    private JTextArea txtKit;
    private JLabel lblOperaciones;
    private JTable tablaOperaciones;
    private DefaultTableModel modeloOperaciones;
    private JScrollPane scrollOperaciones;
    private JButton btnCerrar;

    public VentanaFinDeJornada() {
        this.setTitle("Fin de Jornada — Resumen");
        this.setLayout(null);
        this.setSize(820, 620);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(210, 225, 245));
        this.setResizable(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(null);
        panelInterno.setBounds(30, 30, 740, 530);
        panelInterno.setBackground(Color.WHITE);
        panelInterno.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        lblTitulo = new JLabel("Resumen de Fin de Jornada");
        lblTitulo.setBounds(20, 15, 500, 32);
        lblTitulo.setFont(new Font("Monospace", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(4, 15, 48));

        lblFecha = new JLabel("Fecha: " + java.time.LocalDate.now());
        lblFecha.setBounds(530, 20, 190, 22);
        lblFecha.setFont(new Font("Monospace", Font.PLAIN, 12));
        lblFecha.setForeground(new Color(100, 100, 100));

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 55, 700, 2);
        sep.setForeground(new Color(180, 200, 230));

        lblClientes = new JLabel("Clientes registrados");
        lblClientes.setBounds(20, 65, 300, 22);
        lblClientes.setFont(new Font("Monospace", Font.BOLD, 13));
        lblClientes.setForeground(new Color(33, 97, 172));

        String[] colsCliente = {"UUID", "Nombre", "Apellido", "Tipo Sangre"};
        modeloClientes = new DefaultTableModel(colsCliente, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaClientes = new JTable(modeloClientes);
        tablaClientes.setFont(new Font("Consolas", Font.PLAIN, 11));
        tablaClientes.setRowHeight(24);
        tablaClientes.getTableHeader().setBackground(new Color(33, 97, 172));
        tablaClientes.getTableHeader().setForeground(Color.WHITE);
        tablaClientes.getTableHeader().setFont(new Font("Monospace", Font.BOLD, 11));
        tablaClientes.setGridColor(new Color(210, 225, 245));

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaClientes.getColumnCount(); i++) {
            tablaClientes.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        scrollClientes = new JScrollPane(tablaClientes);
        scrollClientes.setBounds(20, 90, 700, 130);
        scrollClientes.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        lblKit = new JLabel("Kit seleccionado");
        lblKit.setBounds(20, 235, 300, 22);
        lblKit.setFont(new Font("Monospace", Font.BOLD, 13));
        lblKit.setForeground(new Color(33, 97, 172));

        txtKit = new JTextArea();
        txtKit.setEditable(false);
        txtKit.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtKit.setBackground(new Color(245, 248, 252));
        txtKit.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        txtKit.setText("Sin kit seleccionado");

        JScrollPane scrollKit = new JScrollPane(txtKit);
        scrollKit.setBounds(20, 260, 700, 70);
        scrollKit.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        // selecci[on operaciones
        lblOperaciones = new JLabel("Operaciones / Móviles despachados");
        lblOperaciones.setBounds(20, 345, 400, 22);
        lblOperaciones.setFont(new Font("Monospace", Font.BOLD, 13));
        lblOperaciones.setForeground(new Color(33, 97, 172));

        String[] colsOp = {"ID", "Cliente", "Técnico", "Kit", "Estado", "Fecha"};
        modeloOperaciones = new DefaultTableModel(colsOp, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaOperaciones = new JTable(modeloOperaciones);
        tablaOperaciones.setFont(new Font("Consolas", Font.PLAIN, 11));
        tablaOperaciones.setRowHeight(24);
        tablaOperaciones.getTableHeader().setBackground(new Color(33, 97, 172));
        tablaOperaciones.getTableHeader().setForeground(Color.WHITE);
        tablaOperaciones.getTableHeader().setFont(new Font("Monospace", Font.BOLD, 11));
        tablaOperaciones.setGridColor(new Color(210, 225, 245));

        for (int i = 0; i < tablaOperaciones.getColumnCount(); i++) {
            tablaOperaciones.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        scrollOperaciones = new JScrollPane(tablaOperaciones);
        scrollOperaciones.setBounds(20, 370, 700, 110);
        scrollOperaciones.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        btnCerrar = new JButton("Cerrar Jornada");
        btnCerrar.setBounds(560, 492, 160, 35);
        btnCerrar.setBackground(new Color(33, 97, 172));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Monospace", Font.BOLD, 12));
        btnCerrar.setBorder(null);

        panelInterno.add(lblTitulo);
        panelInterno.add(lblFecha);
        panelInterno.add(sep);
        panelInterno.add(lblClientes);
        panelInterno.add(scrollClientes);
        panelInterno.add(lblKit);
        panelInterno.add(scrollKit);
        panelInterno.add(lblOperaciones);
        panelInterno.add(scrollOperaciones);
        panelInterno.add(btnCerrar);

        this.add(panelInterno);
    }

    public void cargarClientes(List<String[]> clientes) {
        modeloClientes.setRowCount(0);
        for (String[] fila : clientes) {
            modeloClientes.addRow(fila);
        }
    }

    public void cargarKit(String kitSeleccionado, String[] elementos) {
        if (kitSeleccionado == null) {
            txtKit.setText("Sin kit seleccionado");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Kit: ").append(kitSeleccionado).append("\n");
            sb.append("Elementos: ");
            for (String el : elementos) sb.append(el).append("  |  ");
            txtKit.setText(sb.toString());
        }
    }

    public void cargarOperaciones(List<String[]> operaciones) {
        modeloOperaciones.setRowCount(0);
        for (String[] fila : operaciones) {
            modeloOperaciones.addRow(fila);
        }
    }

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public JLabel getLblClientes() {
		return lblClientes;
	}

	public void setLblClientes(JLabel lblClientes) {
		this.lblClientes = lblClientes;
	}

	public JTable getTablaClientes() {
		return tablaClientes;
	}

	public void setTablaClientes(JTable tablaClientes) {
		this.tablaClientes = tablaClientes;
	}

	public DefaultTableModel getModeloClientes() {
		return modeloClientes;
	}

	public void setModeloClientes(DefaultTableModel modeloClientes) {
		this.modeloClientes = modeloClientes;
	}

	public JScrollPane getScrollClientes() {
		return scrollClientes;
	}

	public void setScrollClientes(JScrollPane scrollClientes) {
		this.scrollClientes = scrollClientes;
	}

	public JLabel getLblKit() {
		return lblKit;
	}

	public void setLblKit(JLabel lblKit) {
		this.lblKit = lblKit;
	}

	public JTextArea getTxtKit() {
		return txtKit;
	}

	public void setTxtKit(JTextArea txtKit) {
		this.txtKit = txtKit;
	}

	public JLabel getLblOperaciones() {
		return lblOperaciones;
	}

	public void setLblOperaciones(JLabel lblOperaciones) {
		this.lblOperaciones = lblOperaciones;
	}

	public JTable getTablaOperaciones() {
		return tablaOperaciones;
	}

	public void setTablaOperaciones(JTable tablaOperaciones) {
		this.tablaOperaciones = tablaOperaciones;
	}

	public DefaultTableModel getModeloOperaciones() {
		return modeloOperaciones;
	}

	public void setModeloOperaciones(DefaultTableModel modeloOperaciones) {
		this.modeloOperaciones = modeloOperaciones;
	}

	public JScrollPane getScrollOperaciones() {
		return scrollOperaciones;
	}

	public void setScrollOperaciones(JScrollPane scrollOperaciones) {
		this.scrollOperaciones = scrollOperaciones;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}  

}