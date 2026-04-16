
package co.edu.unbosque.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;


public class FileHandler {

	// Texto
	public static File archivo;// Cualquier cosa que tenga static debe ser publico
	public static PrintWriter escritor;// Para escribir en archivos
	public static Scanner lector;
	// Serializados
	public static FileInputStream fis;
	public static ObjectInputStream ois;
	public static FileOutputStream fos;
	public static ObjectOutputStream oos;

	public static void escribirDatos(String url, String datos) {
		try {
			archivo = new File(url);// inicializar file no significa que el archivo se cree, solo la busca si
									// existe se agrega el contenido. Si no se lanza una excepcion.
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			escritor = new PrintWriter(archivo);
			escritor.println(datos);
			escritor.close();// Solo un aplicativo puede tener el archivo abierto
		} catch (IOException e) {
			System.err.println("Hubo un problema al crear y escribir el archivo de texto");
			System.err.println(e.getMessage());
			// e.printStackTrace();
		}

	}

	public static String leerDesdeArchivoDeTexto(String url) {

		try {
			archivo = new File(url);

			if (!archivo.exists()) {
				archivo.createNewFile();
			}

			lector = new Scanner(archivo);
			String contenido = "";
			while (lector.hasNext()) {
				contenido += lector.nextLine() + "\n";

			}
			lector.close();
			return contenido;

			// Todos los archivos tienen un caracter EOF, que significa end of file, que
			// indica el final del archivo

		} catch (IOException e) {
			System.err.println("Problema al leer o crear el archivo de texto");
			e.printStackTrace();
			return null;
		}

	}

	public static void escribirArchivoSerializado(String url, Object contenido) {

		try {

			archivo = new File(url);

			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(contenido);

			oos.close();
			fos.close();

		} catch (IOException e) {

			System.err.println("Problema al leer el archivo serializado");
			e.printStackTrace();

		}

	}

	public static Object leerArchivoSerializado(String url) {
		// Si uno se tira el archivo serializado, solo se puede recuperarn haciendolo
		// otra vez desde el java
		try {

			archivo = new File(url);

			if (!archivo.exists()) {
				archivo.createNewFile();
			}

			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);

			Object contenido = ois.readObject();
			ois.close();
			fis.close();

			return contenido;

		} catch (IOException | ClassNotFoundException e) {

			System.err.println("Problema al deserializar los datos del archivo");
			e.printStackTrace();
			return null;

		}

	}

	public static Properties prop;

	public static Properties cargarArchivoPropiedades(String url) {
		try {

			archivo = new File(url);

			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			prop = new Properties();
			prop.load(new FileInputStream(archivo));
			return prop;
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo de propiedades");
			e.printStackTrace();
			return null;
		}

	}
	

}
