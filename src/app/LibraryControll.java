package app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import utils.DataReader;
import utils.FileManager;
import utils.LibraryUtils;
import data.Book;
import data.Library;
import data.Magazine;

public class LibraryControll {

	private DataReader dataReader;
	private FileManager fileManager;

	private Library library;

	public LibraryControll() {
		dataReader = new DataReader();
		fileManager = new FileManager();

		try {
			library = fileManager.readLibraryFromFile();
			System.out.println("Wczytano dane biblioteki z pliku");
		} catch (ClassNotFoundException | IOException e) {
			library = new Library();
			System.out.println("Utworzono nową bazę biblioteki");
		}
	}

	public void controlLoop() {
		Option option = null;

		while (option != Option.EXIT) {
			try {
				printOptions();
				option = Option.createFromInt(dataReader.getInt());

				switch (option) {
				case ADD_BOOK:
					addBook();
					break;
				case ADD_MAGAZINE:
					addMagazine();
					break;
				case PRINT_BOOKS:
					printBooks();
					break;
				case PRINT_MAGAZINES:
					printMagazines();
					break;
				case EXIT:
					exit();
				}
			} catch (InputMismatchException e) {
				System.out
						.println("Wprowadziłeś niepoprawne dane, pozycji nie dodano");
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out
						.println("Wybrana opcja nie istnieje, wybierz ponownie: ");
			}
		}
		dataReader.close();
	}

	private void printOptions() {
		System.out.println("Wybierz opcję: ");
		for (Option o : Option.values()) {
			System.out.println(o);
		}
	}

	private void addBook() {
		Book book = dataReader.readAndCreateBook();
		library.addPublication(book);
	}

	private void printBooks() {
		LibraryUtils.printBooks(library);
	}

	private void addMagazine() {
		Magazine magazine = dataReader.readAndCreateMagazine();
		library.addPublication(magazine);
	}

	private void printMagazines() {
		LibraryUtils.printMagazines(library);
	}

	private void exit() {
		fileManager.writeLibraryToFile(library);
	}

	private enum Option {
		EXIT(0, "Wyjśćie z programu"), ADD_BOOK(1, "Dodanie książki"), ADD_MAGAZINE(
				2, "Dodanie magazynu/gazety"), PRINT_BOOKS(3,
				"Wyświetlenie dostępnych książek"), PRINT_MAGAZINES(4,
				"Wyświetlenie dostępnych magazynow/gazet");

		private int value;
		private String description;

		Option(int value, String desc) {
			this.value = value;
			this.description = desc;
		}

		@Override
		public String toString() {
			return value + " - " + description;
		}

		public static Option createFromInt(int option)
				throws NoSuchElementException {
			Option result = null;
			try {
				result = Option.values()[option];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new NoSuchElementException("Brak elementu o wskazanym ID");
			}
			return result;
		}
	}

}
