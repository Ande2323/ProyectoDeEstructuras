package co.edu.unbosque.view;

import java.awt.color.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView;


public class PanelKit extends JPanel{

	private JLabel lblTitulo;
	private JComboBox<String> comboTipoKit;
	private JList<String> listaElementos;
	private DefaultListModel<String> modeloLista;
	private JScrollPane scrollLista; 
	private JTable tablaDisponibilidad;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	private JLabel lblDisponibilidad;
	private String kitSeleccionado = null;
	private JButton btnCerrar;
	
	//dropdown del kit medico
	private final String[] kitQuirurgico = {
			"Hemorragias", 
			"Fracturas",
			"Quemaduras",
			"Contusiones",
			"Laceraciones",
			"TCE"
	};
	
	//dropdown kit carros
	private final String[] kitMecanico = {
			"Llantas planchadas",
			"Sin bateria",
			"Daño estructural",
			"Suspensión",
			"Mal funcionamiento de bolsa",
			"Carburador obstruido", 
			"Fusibles quemados",
			"Motor recalentado",
			"Bomba de gasolina obstruida",
			"Discos de clutch gastados",
			"Cadena rota"
	};
	
	public PanelKit() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		
		lblTitulo = new JLabel("Gestión de Kits"); //mensaje inicial
		lblTitulo.setBounds(20, 15, 300, 30);
		lblTitulo.setFont(new Font("Monospace", Font.BOLD,18));
		lblTitulo.setForeground(new Color(4,15,48));
		
		
		JLabel lblSeleccionar = new JLabel("Seleccionar tipo de kit: "); //mensaje referencia
		lblSeleccionar.setBounds(20, 58,200,22);
		lblSeleccionar.setFont(new Font ("Monospace", Font.PLAIN, 12));
		lblSeleccionar.setForeground(new Color(33,97,172));
	
		comboTipoKit = new JComboBox<>(new String[] { //incio dropdown con 2 opciones (similar ventana emergente de busqueda)
				"==Seleccione un kit==",
				"Kit Quirúrgico",
				"Kit Mecánico"
		});
		
		comboTipoKit.setBounds(20, 82,220, 32);
		comboTipoKit.setBackground(Color.WHITE);
		comboTipoKit.setForeground(new Color(4,15,48));
		comboTipoKit.setFont(new Font ("Monospace", Font.PLAIN, 12));
		comboTipoKit.setBorder(new LineBorder(new Color(33,97,172),1));

		JLabel lblElementos = new JLabel("Elementos del kit: ");
		lblElementos.setBounds(20, 125, 200, 22);
		lblElementos.setFont(new Font("Monospace", Font.PLAIN,12));
		lblElementos.setForeground(new Color(33,97,172));
		
		modeloLista = new DefaultListModel<>();
		modeloLista.addElement("Seleccione un kit para ver sus elementos"); //mensaje dentro del cuadro azul que muestra opciones
		
		listaElementos = new JList<>(modeloLista); //se muestra al ver el dropdown y es como especificar el resto de las opciones
		listaElementos.setFont(new Font ("Consolas", Font.PLAIN,12));
		listaElementos.setBackground(new Color(245,248,252));
		listaElementos.setForeground(new Color(4,15,48));
		listaElementos.setSelectionBackground(new Color(180,210,240));
		listaElementos.setBorder(new LineBorder(new Color(33,97,172),1));
		listaElementos.setFixedCellHeight(28);
		
		scrollLista = new JScrollPane(listaElementos);
		scrollLista.setBounds(20, 150, 220, 220);
		scrollLista.setBorder(new LineBorder(new Color(180,200,230),1));
		
		JLabel lblDisponibilidad = new JLabel("disponibiliad del kit: ");
		lblDisponibilidad.setBounds(270, 58, 250, 22);
		lblDisponibilidad.setFont(new Font("Monospace", Font.PLAIN,13));
		lblDisponibilidad.setForeground(new Color(33,97,172));
	
		String[] columnas = {"Kit", "Elemento", "Disponible"};
		modeloTabla = new DefaultTableModel(columnas,0) {
			@Override
			public boolean isCellEditable (int row, int col) {
				return false;
			} @Override
			public Class<?> getColumnClass(int col){
				return col == 2 ? Boolean.class : String.class;
			}
		};
			
		//los kits siempre deben estar disponibles y asi se ven verdeisots
		modeloTabla.addRow(new Object[] {
				"Quirúrgico", "Hemorragias", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Quirúrgico", "Fracturas", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Quirúrgico", "Quemaduras", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Quirúrgico", "Contusiones", Boolean.TRUE
		});

		modeloTabla.addRow(new Object[] {
				"Quirúrgico", "Laceraciones", Boolean.TRUE
		});

		modeloTabla.addRow(new Object[] {
				"Quirúrgico", "TCE", Boolean.TRUE
		});


		modeloTabla.addRow(new Object[] {
				"Mecánico", "Llantas planchadas", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Sin bateria", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Daño estructural", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Suspensión", Boolean.TRUE
		});

		modeloTabla.addRow(new Object[] {
				"Mecánico", "Mal funcionamiento de bolsa", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Carburador obstruido", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Motor recalentado", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Bomba de gasolina obstruida", Boolean.TRUE
		});
		modeloTabla.addRow(new Object[] {
				"Mecánico", "Discos de clutch gastados", Boolean.TRUE
		});

		modeloTabla.addRow(new Object[] {
				"Mecánico", "Cadena rota", Boolean.TRUE
		});

	tablaDisponibilidad = new JTable(modeloTabla) { //creo la tabla donde se ven los dif tipos de kits
		@Override
		public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
			Component c = super.prepareRenderer(renderer, row, col);
			if( ! isRowSelected(row)) {
				boolean disponible = (Boolean) modeloTabla.getValueAt(row,2);
				c.setBackground(disponible
						? new Color (220,245,220)
								: new Color (245,220,220));
			} return c;
		}
	};
	
	tablaDisponibilidad.setFont(new Font("Consolas", Font.PLAIN, 12));
	tablaDisponibilidad.setRowHeight(26);
	tablaDisponibilidad.getTableHeader().setBackground(new Color (33,97,172));
	tablaDisponibilidad.getTableHeader().setForeground(Color.WHITE);
	tablaDisponibilidad.getTableHeader().setFont(new Font ("Monospace", Font.BOLD,12));
	tablaDisponibilidad.setGridColor(new Color (210,225,245));
	tablaDisponibilidad.setSelectionBackground(new Color (180,210,240));

	DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
    centrado.setHorizontalAlignment(JLabel.CENTER);
    tablaDisponibilidad.getColumnModel().getColumn(0).setCellRenderer(centrado);
    tablaDisponibilidad.getColumnModel().getColumn(1).setCellRenderer(centrado);

    scrollTabla = new JScrollPane(tablaDisponibilidad);
    scrollTabla.setBounds(270, 85, 420, 290);
    scrollTabla.setBorder(new LineBorder(new Color(180, 200, 230), 1));

    btnCerrar = new JButton("Cerrar Jornada");
    btnCerrar.setBounds(600, 380, 100, 35);
    btnCerrar.setBackground(new Color(33, 97, 172));
    btnCerrar.setForeground(Color.WHITE);
    btnCerrar.setFocusPainted(false);
    btnCerrar.setFont(new Font("Monospace", Font.BOLD, 10));
    btnCerrar.setBorder(null);

    
    comboTipoKit.addItemListener(e -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String seleccion = (String) comboTipoKit.getSelectedItem();
            modeloLista.clear();

            if (seleccion.equals("Kit Quirúrgico")) { //popup confirmar
                int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Confirma la selección del Kit Quirúrgico?\n" +
                    "Esta elección quedará registrada para el fin de jornada.",
                    "Confirmar selección de Kit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (respuesta == JOptionPane.YES_OPTION) {
                    kitSeleccionado = "Kit Quirúrgico"; // guardar selección
                    for (String item : kitQuirurgico) modeloLista.addElement("  • " + item);
                } else {
                    comboTipoKit.setSelectedIndex(0); // volver al placeholder
                }

            } else if (seleccion.equals("Kit Mecánico")) {
                int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Confirma la selección del Kit Mecánico?\n" +
                    "Esta elección quedará registrada para el fin de jornada.",
                    "Confirmar selección de Kit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (respuesta == JOptionPane.YES_OPTION) {
                    kitSeleccionado = "Kit Mecánico";
                    for (String item : kitMecanico) modeloLista.addElement("  • " + item);
                } else {
                    comboTipoKit.setSelectedIndex(0);
                }

            } else {
                kitSeleccionado = null;
                modeloLista.addElement("Seleccione un kit para ver sus elementos");
            }
        }
    });

    this.add(lblTitulo);
    this.add(lblSeleccionar);
    this.add(comboTipoKit);
    this.add(lblElementos);
    this.add(scrollLista);
    this.add(lblDisponibilidad);
    this.add(scrollTabla);
    this.add(btnCerrar);
}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JComboBox<String> getComboTipoKit() {
		return comboTipoKit;
	}

	public void setComboTipoKit(JComboBox<String> comboTipoKit) {
		this.comboTipoKit = comboTipoKit;
	}

	public JList<String> getListaElementos() {
		return listaElementos;
	}

	public void setListaElementos(JList<String> listaElementos) {
		this.listaElementos = listaElementos;
	}

	public DefaultListModel<String> getModeloLista() {
		return modeloLista;
	}

	public void setModeloLista(DefaultListModel<String> modeloLista) {
		this.modeloLista = modeloLista;
	}

	public JScrollPane getScrollLista() {
		return scrollLista;
	}

	public void setScrollLista(JScrollPane scrollLista) {
		this.scrollLista = scrollLista;
	}

	public JTable getTablaDisponibilidad() {
		return tablaDisponibilidad;
	}

	public void setTablaDisponibilidad(JTable tablaDisponibilidad) {
		this.tablaDisponibilidad = tablaDisponibilidad;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(DefaultTableModel modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public JScrollPane getScrollTabla() {
		return scrollTabla;
	}

	public void setScrollTabla(JScrollPane scrollTabla) {
		this.scrollTabla = scrollTabla;
	}

	public JLabel getLblDisponibilidad() {
		return lblDisponibilidad;
	}

	public void setLblDisponibilidad(JLabel lblDisponibilidad) {
		this.lblDisponibilidad = lblDisponibilidad;
	}

	public String[] getKitQuirurgico() {
		return kitQuirurgico;
	}

	public String[] getKitMecanico() {
		return kitMecanico;
	}

	public String getKitSeleccionado() {
		return kitSeleccionado;
	}

	public void setKitSeleccionado(String kitSeleccionado) {
		this.kitSeleccionado = kitSeleccionado;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}
	
} 
