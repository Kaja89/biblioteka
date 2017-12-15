package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.Library;

public class FileManager {
	public static final String FILE_NAME = "Library.o";

	public void writeLibraryToFile(Library lib) {//zapis do pliku
		try (FileOutputStream fos = new FileOutputStream(FILE_NAME);// utworzenie
																	// pliku
				ObjectOutputStream oos = new ObjectOutputStream(fos);) { // utworzenie
																			// obiektu
																			// przyjmującego
																			// plik
																			// za
																			// argument
			oos.writeObject(lib);
		} catch (FileNotFoundException e){
			System.err.println("Nie znaleziono pliku " + FILE_NAME);
		} catch (IOException e){
			System.err.println("Błąd podczas zapisu danych do pliku" + FILE_NAME);
		}
	}
	
	public Library readLibraryFromFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		Library library = null;//odczyt z pliku
		try(FileInputStream fis = new FileInputStream(FILE_NAME); 
			ObjectInputStream ois = new ObjectInputStream(fis);){
			library = (Library) ois.readObject();//metoda zwróci typ obeject dlatego rzutujemy
		} catch (FileNotFoundException e){
			System.err.println("Nie odnaleziono pliku " + FILE_NAME);
			throw e;
		} catch (IOException e){
			System.err.println("Błąd podczas zapisu danych do pliku " + FILE_NAME);
			throw e;
		}catch (ClassNotFoundException e){
			System.err.println("Nieprawidłowy format pliku");
			throw e;
		}
		return library;
	}
}
