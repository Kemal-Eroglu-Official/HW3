package Programme.DataClasses;

import java.util.ArrayList;
import java.util.HashMap;

import Programme.Enums.FurnitureName;

public class Manufacturer implements ProduceCapable{
	private ArrayList<Furniture> furniture_inventory;
	private HashMap<String, ArrayList<Material>> material_inventory;
	private double totalCost;
	private double totalEarn;
	private HashMap<FurnitureName, Integer> not_made_list;
	
	public Manufacturer(ArrayList<Furniture> furniture_inventory, HashMap<String, ArrayList<Material>> material_inventory) {
		this.furniture_inventory = new ArrayList<Furniture>(furniture_inventory);
		this.material_inventory = copy_content(material_inventory);
		this.totalCost = calculateTotalCost();
		this.totalEarn = calculateTotalEarn();
		this.not_made_list = new HashMap<FurnitureName, Integer>();
	}
	public Manufacturer() {
		this.furniture_inventory = new ArrayList<Furniture>();
		this.material_inventory = new HashMap<String, ArrayList<Material>>();
		this.totalCost = 0;
		this.totalEarn = 0;
		this.not_made_list = new HashMap<FurnitureName, Integer>();
	}
	public Manufacturer(Manufacturer other) {
		this(new ArrayList<Furniture>(other.getFurniture_inventory()), copy_content(other.getMaterial_inventory()));
	}
	
	private static HashMap<String, ArrayList<Material>> copy_content(HashMap<String, ArrayList<Material>> other){
		String[] keys = (String[]) other.keySet().toArray();
		HashMap<String, ArrayList<Material>> temp = new HashMap<String, ArrayList<Material>>();
		for(String key: keys) {
			temp.put(key, new ArrayList<Material>(other.get(key)));
		}
		return temp;
	}
	
	private double calculateTotalCost() {
		double total = 0;
		for(String key: (String[])material_inventory.keySet().toArray()) {
			for(Material m: material_inventory.get(key)) {
				total += m.getCost();
			}
		}
		for(Furniture f: furniture_inventory) {
			total += f.getCost();
		}
		
		return total;
	}
	
	private double calculateTotalEarn() {
		double total = 0;
		for(Furniture f: furniture_inventory) {
			total += f.getPrice();
		}
		return total;
	}
	public ArrayList<Furniture> getFurniture_inventory() {
		return new ArrayList<Furniture>(this.furniture_inventory);
	}
	public HashMap<String, ArrayList<Material>> getMaterial_inventory() {
		return copy_content(this.material_inventory);
	}
	public double getTotalCost() {
		return this.totalCost;
	}
	public double getTotalEarn() {
		return this.totalEarn;
	}
	public HashMap<FurnitureName, Integer> getNotMadeList(){
		return new HashMap<FurnitureName, Integer>(this.not_made_list);
	}
	
	public void addMaterialsToInventory(Material material) {
		this.material_inventory.get(material.getMaterial_code()).add(material);
		this.calculateTotalCost();
	}
	
	@Override
	public void makeFurnituresByOrder(HashMap<String , Integer> order, HashMap<FurnitureName, FurnitureRecipe> recipe_book) {
		ArrayList<Material> parts = new ArrayList<Material>();
		for(String order_key: (order.keySet())) {
			FurnitureName order_name = FurnitureName.findByCode(order_key);
			FurnitureRecipe recipe = recipe_book.get(order_name);
			for(int count = 0; count < order.get(order_name); count++) {
				boolean producable = true;
				for(String material_code: recipe.getParts().keySet()) {
					if(this.material_inventory.get(material_code).size() < recipe.getParts().get(material_code))
						producable = false;
				}
				if(producable) {
					for(String material_code: recipe.getParts().keySet()) {
						for(int i = 0; i < recipe.getParts().get(material_code); i++) {
							parts.add(this.material_inventory.get(material_code).remove(0));
						}
					}
					this.furniture_inventory.add(new Furniture(order_name, parts));
				}else {
					this.not_made_list.put(order_name, this.not_made_list.get(order_name) + 1);
				}
			}
		}
		this.calculateTotalEarn();
	}
	
	// TOSTRING AND EQUALS
}
