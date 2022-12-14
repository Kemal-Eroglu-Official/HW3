package Programme.DataClasses;

import java.util.HashMap;

import Programme.Enums.FurnitureName;

public interface ProduceCapable {
	public void makeFurnituresByOrder(HashMap<String , Integer> order, HashMap<FurnitureName, FurnitureRecipe> recipe_book);
}
