package co.edu.unbosque.controller;
import co.edu.unbosque.controller.ControladorGUI;
import co.edu.unbosque.exceptions.SGECException;

public class AplMain {

	public static void main(String[] args) {
		try {
			ControladorGUI controlador = new ControladorGUI();
			agregarShutdownHook(controlador);
			controlador.run();
		} catch (Exception e) {
			System.out.println("Error al iniciar app: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	private static void agregarShutdownHook(ControladorGUI controlador) {
		try {
			controlador.cerrarAplicacion();
		} catch (SGECException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
