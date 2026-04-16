package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class VentanaSupervisor extends JFrame {

    private JTabbedPane pestanias;
    private PanelCliente panelCliente;
    private PanelTransporte panelTransporte;
    private PanelKit panelKit;

    public VentanaSupervisor() {
        this.setTitle("Información de Jornada");
        this.setLayout(null); 
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.setSize(800, 550);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(210, 225, 245));
        this.setResizable(false);


        inicializarComponentes(); 
    }

    private void inicializarComponentes() {

        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(null);
        panelInterno.setBounds(30, 30, 720, 460);
        panelInterno.setBackground(Color.WHITE);
        panelInterno.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        pestanias = new JTabbedPane();
        pestanias.setBounds(10, 10, 700, 440);
        pestanias.setBackground(new Color(33, 97, 172));   // fondo pestaña activa
        pestanias.setForeground(Color.WHITE);              // texto pestañas
        pestanias.setFont(new Font("Monospace", Font.BOLD, 13));

        panelCliente = new PanelCliente();
        panelTransporte = new PanelTransporte();
        panelKit = new PanelKit();

        pestanias.addTab("Clientes", panelCliente);
        pestanias.addTab("Móviles", panelTransporte);
        pestanias.addTab("Kit", panelKit);

        panelInterno.add(pestanias);
        this.add(panelInterno);
    }

	public JTabbedPane getPestanias() {
		return pestanias;
	}

	public void setPestanias(JTabbedPane pestanias) {
		this.pestanias = pestanias;
	}

	public PanelCliente getPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(PanelCliente panelCliente) {
		this.panelCliente = panelCliente;
	}

	public PanelTransporte getPanelTransporte() {
		return panelTransporte;
	}

	public void setPanelTransporte(PanelTransporte panelTransporte) {
		this.panelTransporte = panelTransporte;
	}

	public PanelKit getPanelKit() {
		return panelKit;
	}

	public void setPanelKit(PanelKit panelKit) {
		this.panelKit = panelKit;
	}

}