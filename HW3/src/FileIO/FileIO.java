package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Programme.DataClasses.FurnitureRecipe;
import Programme.Enums.FurnitureName;

public class FileIO {
	private ArrayList<FurnitureRecipe> recipe_list;
	
	public FileIO() {
		recipe_list = new ArrayList<FurnitureRecipe>();
	}
	
	public void read_furniture_recipe() {
		try {
			Scanner s = new Scanner(new File("src/Data/FurnitureParts.csv"));
			while(s.hasNextLine()) {
				String[] parts = s.nextLine().split(",");
				FurnitureRecipe recipe = new FurnitureRecipe(FurnitureName.findByCode(parts[0]));
				for(int i = 1; i < parts.length; i += 2) {
					recipe.addPartRecipe(parts[i], Integer.parseInt(parts[i+1]));
				}
				this.recipe_list.add(recipe);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<FurnitureRecipe> getRecipe(){
		return new ArrayList<FurnitureRecipe>(this.recipe_list);
	}
}
