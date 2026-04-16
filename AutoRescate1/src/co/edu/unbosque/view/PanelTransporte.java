package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class PanelTransporte extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblRecursos;
    private JLabel lblIncidentes;

    // tabla de recursos
    private JTable tablaRecursos;
    private JScrollPane scrollTabla;
    private DefaultTableModel modeloTabla;

    // panel de incidentes recientes
    private JTextArea txtIncidentes;
    private JScrollPane scrollIncidentes;
    private JLabel lblLiveFeed;
    private JLabel lblContador;

    // botón acción
    private JButton btnInvertirAccion;

    public PanelTransporte() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        lblTitulo = new JLabel("Operaciones en Tiempo Real");
        lblTitulo.setBounds(20, 15, 400, 30);
        lblTitulo.setFont(new Font("Monospace", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(4, 15, 48));

        //subtitulo
        lblRecursos = new JLabel("Recursos Disponibles");
        lblRecursos.setBounds(20, 55, 220, 22);
        lblRecursos.setFont(new Font("Monospace", Font.BOLD, 13));
        lblRecursos.setForeground(new Color(33, 97, 172));

        // tabla de recursos (Nombre + Tipo + Estado + Disponible) 
        String[] columnas = {"Nombre", "Tipo", "Estado", "Disponible"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // no editable
            }
        };

        // datos  ejemplo
        modeloTabla.addRow(new Object[]{"Unidad 01", "Grúa", "Disponible", Boolean.TRUE});
        modeloTabla.addRow(new Object[]{"Unidad 02", "Grúa", "En Servicio", Boolean.FALSE});
        modeloTabla.addRow(new Object[]{"Moto 01", "Moto", "Disponible", Boolean.TRUE});
        modeloTabla.addRow(new Object[]{"Moto 02", "Moto", "En Servicio", Boolean.FALSE});

        tablaRecursos = new JTable(modeloTabla) {
            // colorear fila según disponibilidad
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                boolean disponible = (Boolean) modeloTabla.getValueAt(row, 3);
                if (!isRowSelected(row)) {
                    c.setBackground(disponible
                        ? new Color(220, 245, 220)  
                        : new Color(245, 220, 220));
                }
                return c;
            }
        };

        tablaRecursos.setFont(new Font("Consolas", Font.PLAIN, 12));
        tablaRecursos.setRowHeight(28);
        tablaRecursos.getTableHeader().setBackground(new Color(33, 97, 172));
        tablaRecursos.getTableHeader().setForeground(Color.WHITE);
        tablaRecursos.getTableHeader().setFont(new Font("Monospace", Font.BOLD, 12));
        tablaRecursos.setGridColor(new Color(210, 225, 245));
        tablaRecursos.setSelectionBackground(new Color(180, 210, 240));

        // centrar columnas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaRecursos.getColumnCount(); i++) {
            tablaRecursos.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        // ocultar columna "Disponible" (solo se usa para colorear)
        tablaRecursos.getColumnModel().getColumn(3).setMinWidth(0);
        tablaRecursos.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaRecursos.getColumnModel().getColumn(3).setWidth(0);

        scrollTabla = new JScrollPane(tablaRecursos);
        scrollTabla.setBounds(20, 85, 360, 260);
        scrollTabla.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        // subtítulo derecho 
        lblIncidentes = new JLabel("Incidentes Recientes");
        lblIncidentes.setBounds(410, 55, 250, 22);
        lblIncidentes.setFont(new Font("Monospace", Font.BOLD, 13));
        lblIncidentes.setForeground(new Color(33, 97, 172));

        // panel tiempo real
        lblLiveFeed = new JLabel("Live Feed");
        lblLiveFeed.setBounds(410, 82, 120, 18);
        lblLiveFeed.setFont(new Font("Monospace", Font.PLAIN, 11));
        lblLiveFeed.setForeground(new Color(0, 160, 60));

        // contador incidentes 
        lblContador = new JLabel("17 Avisos");
        lblContador.setBounds(540, 82, 100, 18);
        lblContador.setFont(new Font("Monospace", Font.PLAIN, 11));
        lblContador.setForeground(new Color(120, 120, 120));

        // area de incidentes
        txtIncidentes = new JTextArea();
        txtIncidentes.setEditable(false);
        txtIncidentes.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtIncidentes.setBackground(new Color(245, 248, 252));
        txtIncidentes.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        txtIncidentes.setText(
            "[10:32] Grúa Unidad 01 — Despachada\n" +
            "[10:28] Moto 01 — En ruta\n" +
            "[10:15] Unidad 02 — Servicio completado\n" +
            "[09:58] Nuevo incidente registrado\n" +
            "[09:45] Moto 02 — Despachada\n"
        );

        scrollIncidentes = new JScrollPane(txtIncidentes);
        scrollIncidentes.setBounds(410, 105, 280, 240);
        scrollIncidentes.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        btnInvertirAccion = new JButton("↺  Invertir Acción");
        btnInvertirAccion.setBounds(410, 360, 180, 35);
        btnInvertirAccion.setBackground(new Color(33, 97, 172));
        btnInvertirAccion.setForeground(Color.WHITE);
        btnInvertirAccion.setFocusPainted(false);
        btnInvertirAccion.setFont(new Font("Monospace", Font.BOLD, 12));
        btnInvertirAccion.setBorder(null);

        this.add(lblTitulo);
        this.add(lblRecursos);
        this.add(scrollTabla);
        this.add(lblIncidentes);
        this.add(lblLiveFeed);
        this.add(lblContador);
        this.add(scrollIncidentes);
        this.add(btnInvertirAccion);
    }

    // método para abrir popup al hacer clic en una fila
    public void mostrarDialogoRecurso(int fila) {
        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        String tipo   = (String) modeloTabla.getValueAt(fila, 1);
        String estado = (String) modeloTabla.getValueAt(fila, 2);
        boolean disponible = (Boolean) modeloTabla.getValueAt(fila, 3);
       
        String nuevoEstado = disponible ? "En Servicio" : "Disponible";
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "Recurso: " + nombre + " (" + tipo + ")\n" +
            "Estado actual: " + estado + "\n\n" +
            "¿Cambiar estado a \"" + nuevoEstado + "\"?",
            "Detalle del Recurso",
            JOptionPane.YES_NO_OPTION
        );
        
        if (respuesta == JOptionPane.YES_OPTION) {
            modeloTabla.setValueAt(nuevoEstado, fila, 2);
            modeloTabla.setValueAt(!disponible, fila, 3);
            tablaRecursos.repaint(); // actualizar colores
        }
    }

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblRecursos() {
		return lblRecursos;
	}

	public void setLblRecursos(JLabel lblRecursos) {
		this.lblRecursos = lblRecursos;
	}

	public JLabel getLblIncidentes() {
		return lblIncidentes;
	}

	public void setLblIncidentes(JLabel lblIncidentes) {
		this.lblIncidentes = lblIncidentes;
	}

	public JTable getTablaRecursos() {
		return tablaRecursos;
	}

	public void setTablaRecursos(JTable tablaRecursos) {
		this.tablaRecursos = tablaRecursos;
	}

	public JScrollPane getScrollTabla() {
		return scrollTabla;
	}

	public void setScrollTabla(JScrollPane scrollTabla) {
		this.scrollTabla = scrollTabla;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(DefaultTableModel modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public JTextArea getTxtIncidentes() {
		return txtIncidentes;
	}

	public void setTxtIncidentes(JTextArea txtIncidentes) {
		this.txtIncidentes = txtIncidentes;
	}

	public JScrollPane getScrollIncidentes() {
		return scrollIncidentes;
	}

	public void setScrollIncidentes(JScrollPane scrollIncidentes) {
		this.scrollIncidentes = scrollIncidentes;
	}

	public JLabel getLblLiveFeed() {
		return lblLiveFeed;
	}

	public void setLblLiveFeed(JLabel lblLiveFeed) {
		this.lblLiveFeed = lblLiveFeed;
	}

	public JLabel getLblContador() {
		return lblContador;
	}

	public void setLblContador(JLabel lblContador) {
		this.lblContador = lblContador;
	}

	public JButton getBtnInvertirAccion() {
		return btnInvertirAccion;
	}

	public void setBtnInvertirAccion(JButton btnInvertirAccion) {
		this.btnInvertirAccion = btnInvertirAccion;
	}

  }