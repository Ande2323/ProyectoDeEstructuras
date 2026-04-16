package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class VentanaInicio extends JFrame {

   
	private JTextPane servicioAyuda;
    private JLabel iconoPersona;
    private JTextPane oficinaVirtual;
    private JTextPane iniciarSesion;
    private JLabel iconoEntrada;
    private JButton btnContinuar;

    public VentanaInicio() {

        this.setBounds(30, 30, 600, 400);
        this.setTitle("AUTO SERVICIO APP");
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(210, 225, 245));
        this.setResizable(false);

        
        // notas pnales en ventana
        // 1: crear el panel interno (solo declararlo, no agregar nada aún)
        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(null);
        panelInterno.setBounds(40, 40, 500, 300);
        panelInterno.setBackground(Color.WHITE);
        panelInterno.setBorder(new LineBorder(new Color(180, 200, 230), 1));

        // =2: definir estilos
        SimpleAttributeSet derecha = new SimpleAttributeSet();
        StyleConstants.setAlignment(derecha, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontSize(derecha, 32);
        StyleConstants.setFontFamily(derecha, "Monospace");
        StyleConstants.setBold(derecha, true);

        SimpleAttributeSet izquierda = new SimpleAttributeSet();
        StyleConstants.setAlignment(izquierda, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontSize(izquierda, 13);
        StyleConstants.setFontFamily(izquierda, "Monospace");

        SimpleAttributeSet izqPeq = new SimpleAttributeSet();
        StyleConstants.setAlignment(izqPeq, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontSize(izqPeq, 11);
        StyleConstants.setFontFamily(izqPeq, "Monospace");

        // 3: crear todos los componentes
        servicioAyuda = new JTextPane();
        servicioAyuda.setText("INFORMACIÓN ");
        servicioAyuda.setBounds(10, 20, 460, 60);
        servicioAyuda.setForeground(new Color(4, 15, 48));
        servicioAyuda.setEditable(false);
        servicioAyuda.setOpaque(false);
        StyledDocument doc1 = servicioAyuda.getStyledDocument();
        doc1.setParagraphAttributes(0, doc1.getLength(), derecha, false);

        iconoPersona = new JLabel();
        iconoPersona.setBounds(40, 120, 52, 52);
        try {
            BufferedImage img = ImageIO.read(new File("src/co/edu/unbosque/view/logoUsuario.png"));
            Image imgRedim = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            iconoPersona.setIcon(new ImageIcon(imgRedim));
        } catch (IOException e) {
            iconoPersona.setText("👤");
            iconoPersona.setHorizontalAlignment(JLabel.CENTER);
            iconoPersona.setVerticalAlignment(JLabel.CENTER);
            iconoPersona.setForeground(new Color(4, 15, 48));
            iconoPersona.setFont(new java.awt.Font("Monospace", java.awt.Font.PLAIN, 28));
        }

        iniciarSesion = new JTextPane();
        iniciarSesion.setText("Iniciar sesión");
        iniciarSesion.setBounds(100, 120, 160, 25);
        iniciarSesion.setForeground(new Color(16, 16, 16));
        iniciarSesion.setEditable(false);
        iniciarSesion.setOpaque(false);
        StyledDocument doc3 = iniciarSesion.getStyledDocument();
        doc3.setParagraphAttributes(0, doc3.getLength(), izquierda, false);

        oficinaVirtual = new JTextPane();
        oficinaVirtual.setText("Oficina Virtual");
        oficinaVirtual.setBounds(100, 145, 160, 22);
        oficinaVirtual.setForeground(new Color(80, 80, 80));
        oficinaVirtual.setEditable(false);
        oficinaVirtual.setOpaque(false);
        StyledDocument doc2 = oficinaVirtual.getStyledDocument();
        doc2.setParagraphAttributes(0, doc2.getLength(), izqPeq, false);

        iconoEntrada = new JLabel();
        iconoEntrada.setBounds(300, 110, 60, 60);
        try {
            BufferedImage img = ImageIO.read(new File("src/co/edu/unbosque/view/logoEntrada.jpg"));
            Image imgRedim = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            iconoEntrada.setIcon(new ImageIcon(imgRedim));
        } catch (IOException e) {
            iconoEntrada.setText("🔑");
            iconoEntrada.setHorizontalAlignment(JLabel.CENTER);
            iconoEntrada.setFont(new java.awt.Font("Monospace", java.awt.Font.PLAIN, 28));
        }

        btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(180, 240, 120, 35);
        btnContinuar.setBackground(new Color(242, 209, 109));
        btnContinuar.setForeground(new Color(15, 12, 2));
        btnContinuar.setBorder(null);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setFont(new java.awt.Font("Monospace", java.awt.Font.BOLD,13));
        
        panelInterno.add(servicioAyuda);
        panelInterno.add(iconoPersona);
        panelInterno.add(iniciarSesion);
        panelInterno.add(oficinaVirtual);
        panelInterno.add(iconoEntrada);
        panelInterno.add(btnContinuar);

        // 4: Agregar el panel al JFrame
        this.add(panelInterno);
        
    }

	public JTextPane getServicioAyuda() {
		return servicioAyuda;
	}

	public void setServicioAyuda(JTextPane servicioAyuda) {
		this.servicioAyuda = servicioAyuda;
	}

	public JLabel getIconoPersona() {
		return iconoPersona;
	}

	public void setIconoPersona(JLabel iconoPersona) {
		this.iconoPersona = iconoPersona;
	}

	public JTextPane getOficinaVirtual() {
		return oficinaVirtual;
	}

	public void setOficinaVirtual(JTextPane oficinaVirtual) {
		this.oficinaVirtual = oficinaVirtual;
	}

	public JTextPane getIniciarSesion() {
		return iniciarSesion;
	}

	public void setIniciarSesion(JTextPane iniciarSesion) {
		this.iniciarSesion = iniciarSesion;
	}

	public JLabel getIconoEntrada() {
		return iconoEntrada;
	}

	public void setIconoEntrada(JLabel iconoEntrada) {
		this.iconoEntrada = iconoEntrada;
	}

	public JButton getBtnContinuar() {
		return btnContinuar;
	}

	public void setBtnContinuar(JButton btnContinuar) {
		this.btnContinuar = btnContinuar;
	} 	
 }
    
    