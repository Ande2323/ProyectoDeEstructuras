package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class PanelCliente extends JPanel {

    private JLabel lblTitulo;
    private JButton btnNombre;
    private JButton btnApellido;
    private JButton btnUUID;
    private JButton btnTipoSangre;
    private JTextArea txtAreaInfo;
    private JScrollPane scrollPane;

    public PanelCliente() {
        this.setLayout(null);
        this.setBackground(Color.WHITE); 
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        // Título 
        lblTitulo = new JLabel("Clientes");
        lblTitulo.setBounds(20, 15, 200, 30);
        lblTitulo.setFont(new Font("Monospace", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(4, 15, 48));

        int xBtn = 20;
        int yInicio = 60;
        int alturaBtn = 38;
        int espacio = 12;

        //botones para registrar datos clientes
        btnNombre = new JButton("Registrar Nombre");
        btnNombre.setBounds(xBtn, yInicio, 200, alturaBtn);
        configurarBoton(btnNombre);

        btnApellido = new JButton("Registrar Apellido");
        btnApellido.setBounds(xBtn, yInicio + (alturaBtn + espacio), 200, alturaBtn);
        configurarBoton(btnApellido);

        btnUUID = new JButton("Registrar UUID");
        btnUUID.setBounds(xBtn, yInicio + 2 * (alturaBtn + espacio), 200, alturaBtn);
        configurarBoton(btnUUID);

        btnTipoSangre = new JButton("Registrar Tipo Sangre");
        btnTipoSangre.setBounds(xBtn, yInicio + 3 * (alturaBtn + espacio), 200, alturaBtn);
        configurarBoton(btnTipoSangre);

        // area para mostrar info del cliente
        txtAreaInfo = new JTextArea();
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtAreaInfo.setBackground(new Color(245, 248, 252));
        txtAreaInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtAreaInfo.setText("Información de clientes...\n\nAquí se mostrará la información guardada. \n\n");

        scrollPane = new JScrollPane(txtAreaInfo);
        scrollPane.setBounds(240, 60, 500, 280); // a la derecha de los botones
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 200, 230)),
            "Información del Sistema"
        ));

        this.add(lblTitulo);
        this.add(btnNombre);
        this.add(btnApellido);
        this.add(btnUUID);
        this.add(btnTipoSangre);
        this.add(scrollPane); // solo el scrollPane, no txtAreaInfo
    }

    private void configurarBoton(JButton boton) {
        boton.setBackground(new Color(33, 97, 172));  
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Monospace", Font.PLAIN, 12));
        boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JButton getBtnNombre() {
		return btnNombre;
	}

	public void setBtnNombre(JButton btnNombre) {
		this.btnNombre = btnNombre;
	}

	public JButton getBtnApellido() {
		return btnApellido;
	}

	public void setBtnApellido(JButton btnApellido) {
		this.btnApellido = btnApellido;
	}

	public JButton getBtnUUID() {
		return btnUUID;
	}

	public void setBtnUUID(JButton btnUUID) {
		this.btnUUID = btnUUID;
	}

	public JButton getBtnTipoSangre() {
		return btnTipoSangre;
	}

	public void setBtnTipoSangre(JButton btnTipoSangre) {
		this.btnTipoSangre = btnTipoSangre;
	}

	public JTextArea getTxtAreaInfo() {
		return txtAreaInfo;
	}

	public void setTxtAreaInfo(JTextArea txtAreaInfo) {
		this.txtAreaInfo = txtAreaInfo;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

   }