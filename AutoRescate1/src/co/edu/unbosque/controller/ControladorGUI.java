package co.edu.unbosque.controller;

import co.edu.unbosque.persistence.*;
import co.edu.unbosque.view.*;
import co.edu.unbosque.exceptions.SGECException;
import co.edu.unbosque.model.ListaEnlazada;
import co.edu.unbosque.model.Nodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControladorGUI implements ActionListener {

	private VentanaInicio vp;
	private VentanaSupervisor sv;
	private VentanaFinDeJornada vfj;
	private int contadorClientes = 0;
	private ClienteDAO clienteDAO = new ClienteDAO();

	private ClienteDTO clienteTemp = new ClienteDTO(); // cliente en construcción

	public ControladorGUI() {
		vp = new VentanaInicio();
		sv = new VentanaSupervisor();
		vfj = new VentanaFinDeJornada();
	}

	public void run() {
		mostrarVp();
		mostrarSv();
		cargarDatosGuardados(); // carga al iniciar
		vp.setVisible(true);
	}

	public void mostrarVp() {
		if (vp != null && vp.getBtnContinuar() != null) {
			vp.getBtnContinuar().addActionListener(this);
			vp.getBtnContinuar().setActionCommand("abrirInfo");
		} else {
			System.out.println("Error: el botón inicio es nulo.");
		}
	}

	public void mostrarSv() {
		PanelCliente pc = sv.getPanelCliente();
		pc.getBtnNombre().addActionListener(this);
		pc.getBtnNombre().setActionCommand("registroNombre");
		pc.getBtnApellido().addActionListener(this);
		pc.getBtnApellido().setActionCommand("registroTipoCliente");
		pc.getBtnUUID().addActionListener(this);
		pc.getBtnUUID().setActionCommand("registroContacto");
		pc.getBtnTipoSangre().addActionListener(this);
		pc.getBtnTipoSangre().setActionCommand("registroTipoSangre");

		PanelTransporte pt = sv.getPanelTransporte();
		pt.getBtnInvertirAccion().addActionListener(this);
		pt.getBtnInvertirAccion().setActionCommand("invertirAccion");
		pt.getTablaRecursos().addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = pt.getTablaRecursos().getSelectedRow();
				if (fila >= 0) {
					pt.mostrarDialogoRecurso(fila);
				}
			}
		});

		PanelKit pk = sv.getPanelKit();
		pk.getBtnCerrar().addActionListener(this);
		pk.getBtnCerrar().setActionCommand("cerrarJornada");

	}

	private void agregarSeparador(JTextArea area) { // para separar los datos de dif clientes
		contadorClientes++;
		area.append("\n===Cliente #" + contadorClientes + " ===\n");
	}

	private void cargarDatosGuardados() {

		JTextArea txt = sv.getPanelCliente().getTxtAreaInfo();
		txt.setText("");

		ListaEnlazada<ClienteDTO> clientes = clienteDAO.leerTodos();

		if (clientes == null || clientes.estaVacia()) {
			txt.setText("Información de clientes...\n\nAquí se mostrará la información guardada.");
			return;
		}

		Nodo<ClienteDTO> actual = clientes.getCabeza();
		int i = 1;

		while (actual != null) {

			ClienteDTO c = actual.getDato();

			txt.append("Nombre: " + c.getNombre() + "\n");
			txt.append("Tipo Cliente: " + c.getTipoCliente() + "\n");
			txt.append("Contacto: " + c.getContacto() + "\n");
			txt.append("Tipo Sangre: " + c.getTipoSangre() + "\n\n");

			actual = actual.getSiguiente();
		}

		contadorClientes = clientes.getTamaño();
	}

	private boolean clienteEnProceso = false;

	// acciones de botones y ventanas
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {

		case "abrirInfo":
			vp.setVisible(false);
			sv.setVisible(true);
			break;

		case "registroNombre": {

			if (clienteEnProceso && clienteTemp.getNombre() != null) {
				JOptionPane.showMessageDialog(null, "Ya estás registrando un cliente. Termina el proceso.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			String valor = JOptionPane.showInputDialog("Ingresa el nombre:");
			if (valor != null && !valor.isBlank()) {
				clienteTemp = new ClienteDTO();
				clienteTemp.setNombre(valor);
				clienteEnProceso = true;

				JTextArea txt = sv.getPanelCliente().getTxtAreaInfo();
				agregarSeparador(txt);
				txt.append("Nombre: " + valor + "\n");
			}
			break;
		}

		case "registroTipoCliente": {

			if (!clienteEnProceso || clienteTemp.getNombre() == null) {
				JOptionPane.showMessageDialog(null, "Primero debes registrar el nombre.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			if (clienteTemp.getTipoCliente() != null) {
				JOptionPane.showMessageDialog(null, "El tipo de cliente ya fue registrado.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			String valor = JOptionPane.showInputDialog("Ingresa el tipo de cliente:");
			if (valor != null && !valor.isBlank()) {
				clienteTemp.setTipoCliente(valor);
				sv.getPanelCliente().getTxtAreaInfo().append("Tipo Cliente: " + valor + "\n");
			}
			break;
		}

		case "registroContacto": {

			if (clienteTemp.getTipoCliente() == null) {
				JOptionPane.showMessageDialog(null, "Primero debes registrar el tipo de cliente.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			String valor = JOptionPane.showInputDialog("Ingresa el contacto:");

			if (valor == null || valor.isBlank()) {
				JOptionPane.showMessageDialog(null, "El contacto no puede estar vacío.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			clienteTemp.setContacto(valor);
			sv.getPanelCliente().getTxtAreaInfo().append("Contacto: " + valor + "\n");

			break;
		}

		case "registroTipoSangre": {

			if (clienteTemp.getContacto() == null) {
				JOptionPane.showMessageDialog(null, "Primero debes registrar el contacto.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			if (clienteTemp.getTipoSangre() != null) {
				JOptionPane.showMessageDialog(null, "El tipo de sangre ya fue registrado.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			String[] opciones = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };

			String valor = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de sangre:", "Tipo de Sangre",
					JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

			if (valor != null) {

				clienteTemp.setTipoSangre(valor);

				JTextArea txt = sv.getPanelCliente().getTxtAreaInfo();
				txt.append("Tipo de Sangre: " + valor + "\n\n");

				

				if (clienteTemp.getNombre() == null || clienteTemp.getTipoCliente() == null
						|| clienteTemp.getContacto() == null || clienteTemp.getTipoSangre() == null) {

					JOptionPane.showMessageDialog(null, "Faltan datos del cliente.", "Error",
							JOptionPane.ERROR_MESSAGE);
					break;
				}else {
					clienteDAO.crear(clienteTemp);
				JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				
				clienteTemp = new ClienteDTO();
				clienteEnProceso = false;
				}
				cargarDatosGuardados();
			}
			break;
		}

		case "invertirAccion":

			if (clienteEnProceso) {
				JOptionPane.showMessageDialog(null, "Termina el registro del cliente antes de usar esta opción.",
						"Acción bloqueada", JOptionPane.WARNING_MESSAGE);
				break;
			}

			JOptionPane.showMessageDialog(null, "Acción invertida correctamente.", "Operación exitosa",
					JOptionPane.INFORMATION_MESSAGE);

			break;

		// para mostrar la ultima ventana
		case "cerrarJornada":
			vfj.setVisible(true);
			break;

		default:
			System.out.println("Acción no reconocida: " + cmd);
			break;
		}
	}

	public void inicializarSistema() throws SGECException {
		System.out.println("Datos cargados");
	}

	public void cerrarAplicacion() throws SGECException {
		System.out.println("Datos guardados antes de cerrar");
	}
}