package Programme.DataClasses;
import java.util.ArrayList;

import Programme.Enums.FurnitureName;
import Programme.Enums.FurnitureType;
import Programme.Enums.QualityRange;

public class Furniture {
	private FurnitureName furniture_name;
	private FurnitureType furniture_type;
	private ArrayList<Material> parts;
	private QualityRange quality;
	private double cost;
	private double price;
	
	public Furniture(FurnitureName furniture_name, ArrayList<Material> parts) {
		this.furniture_name = furniture_name;
		this.furniture_type = FurnitureType.findByFurnitureName(furniture_name);
		this.parts = new ArrayList<Material>(parts);
		this.quality = QualityRange.percentageToQuality(measureQuality(parts));
		this.cost = calculateTotalCost(parts);
		this.price = calculatePrice();
	}
	public Furniture(String furniture_code, ArrayList<Material> parts) {
		this(FurnitureName.findByCode(furniture_code), parts);
	}
	public Furniture() {
		this(FurnitureName.OBSIDIAN, new ArrayList<Material>());
	}
	public Furniture(Furniture other) {
		this(other.getFurniture_name(), new ArrayList<Material>(other.getParts()));
	}
	
	private static double measureQuality(ArrayList<Material> parts) {
		int total_count = 0;
		int total_quality = 0;
		for(Material m: parts) {
			total_quality += m.getQuality();
			total_count += 1;
		}
		return total_quality/total_count;
	}
	
	private static double calculateTotalCost(ArrayList<Material> parts) {
		double cost = 0;
		for(Material m: parts) {
			cost += m.getCost();
		}
		return cost;
	}
	
	private double calculatePrice() {
		return this.cost * FurnitureType.getPercentageValue(this.furniture_type);
	}
	public FurnitureName getFurniture_name() {
		return furniture_name;
	}
	public FurnitureType getFurniture_type() {
		return furniture_type;
	}
	public ArrayList<Material> getParts() {
		return new ArrayList<Material>(this.parts);
	}
	public QualityRange getQuality() {
		return quality;
	}
	public double getCost() {
		return cost;
	}
	public double getPrice() {
		return price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Furniture temp = (Furniture) obj;
		return this.furniture_name == temp.getFurniture_name() && this.furniture_type == temp.getFurniture_type() && this.parts.equals(temp.getParts()) 
				&& this.quality == temp.getQuality() && this.cost == temp.getCost() && this.price == temp.getPrice();
	}
	
	@Override
	public String toString() {
		return "Name: " + this.furniture_name.toString() + " (" + this.furniture_type.toString() + ")\nQuality: " + this.quality.toString()+"\nCost: "
				+ this.cost + "\nPrice: " + this.price;
	}
}
