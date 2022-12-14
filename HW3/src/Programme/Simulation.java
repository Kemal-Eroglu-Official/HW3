package Programme;

import java.util.ArrayList;
import java.util.HashMap;

import FileIO.FileIO;
import Programme.DataClasses.FurnitureRecipe;
import Programme.DataClasses.Manufacturer;
import Programme.DataClasses.Vendor;
import Programme.Enums.FurnitureName;

public class Simulation {
	private FileIO fileio;
	private int duration;
	Manufacturer manufacturer1, manufacturer2;
	Vendor vendor;
	ArrayList<FurnitureRecipe> recipe;
	
	public Simulation(){
		this.fileio = new FileIO();
		manufacturer1 = new Manufacturer();
		manufacturer2 = new Manufacturer();
		setup();
	}
	
	public void start() {
		for(int day = 0; day < duration; day++) {
			ArrayList<ArrayList<HashMap<String, Integer>>> manufacturers_material_shopping = fileio.getManufacturerMaterialShopRecords();
			
			HashMap<String, Integer> daily_shopping_record1 = manufacturers_material_shopping.get(0).get(day);
			for(String key: daily_shopping_record1.keySet()) {
				for(int i = 0; i < daily_shopping_record1.get(key); i++) {
					manufacturer1.addMaterialsToInventory(vendor.pickFront(key));
				}
			}
			
			HashMap<String, Integer> daily_shopping_record2 = manufacturers_material_shopping.get(0).get(day);
			for(String key: daily_shopping_record2.keySet()) {
				for(int i = 0; i < daily_shopping_record2.get(key); i++) {
					manufacturer2.addMaterialsToInventory(vendor.pickBack(key));
				}
			}
			
			ArrayList<ArrayList<HashMap<String, Integer>>> manufacturers_furniture_produce = fileio.getManufacturerFurnitureProduceRecords();
			
			HashMap<String, Integer> daily_produce_record1 = manufacturers_material_shopping.get(0).get(day);
			//manufacturer1.makeFurnituresByOrder(daily_produce_record1, getRecipeAsHashMap());
			//NOT DONE
		}
	}
	private void setup() {
		this.fileio.setup();
		duration = fileio.getManufacturerMaterialShopRecords().get(0).size();
		vendor = new Vendor(fileio.getVendorInventory());
		recipe = fileio.getRecipe();
	}
	
	private HashMap<FurnitureName, FurnitureRecipe> getRecipeAsHashMap(){
		HashMap<FurnitureName, FurnitureRecipe> hash_recipe = new HashMap<FurnitureName, FurnitureRecipe>();
		for(FurnitureRecipe f: recipe) {
			hash_recipe.put(f.getFurnitureName(), f);
		}
		return hash_recipe;
	}
	}
