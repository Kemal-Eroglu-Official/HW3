package App;

import FileIO.FileIO;
import Programme.DataClasses.Material;

public class App {

	public static void main(String[] args) {
		FileIO fileIO = new FileIO();
		fileIO.setup();

		for (Material material : fileIO.getMaterials()) {
			System.out.println(material.toString());
		}

		System.out.println(fileIO.getMaterials().size());
		System.out.println("Merhaba");
	}
}
