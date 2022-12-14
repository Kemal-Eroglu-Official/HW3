package Programme.DataClasses;

import java.util.HashMap;
import Programme.Enums.FurnitureName;

//STATIC YAPILCAK
public class FurnitureRecipe {
	private FurnitureName furniture_name;
	private HashMap<String, Integer> parts;
	
	public FurnitureRecipe(String furniture_code, HashMap<String, Integer> parts) {
		this.furniture_name = FurnitureName.findByCode(furniture_code);
		this.parts = new HashMap<>(parts);
	}
	public FurnitureRecipe(FurnitureName furniture_name, HashMap<String, Integer> parts) {
		this.furniture_name = furniture_name;
		this.parts = new HashMap<>(parts);
	}
	public FurnitureRecipe(FurnitureName name) {
		this(name, new HashMap<String, Integer>());
	}
	public FurnitureRecipe() {
		this("", new HashMap<String, Integer>());
	}
	public FurnitureRecipe(FurnitureRecipe other) {
		this(other.furniture_name, new HashMap<>(other.parts));
	}
	public FurnitureRecipe(String furniture_code) {
		this(furniture_code, new HashMap<String, Integer>());
	}
	
	public FurnitureName getFurnitureName() {
		return this.furniture_name;
	}
	
	public HashMap<String, Integer> getParts(){
		return new HashMap<String, Integer>(this.parts);
	}
	
	public void addPartRecipe(String part_code, Integer count) {
		this.parts.put(part_code, count);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		FurnitureRecipe temp = (FurnitureRecipe) obj;
		return this.furniture_name == temp.getFurnitureName() && this.parts.equals(temp.getParts());
	}
	
	@Override
	public String toString() {
		return "Furniture Code: " + this.furniture_name.toString() + "\tParts: " + this.parts.keySet();
	}
}
