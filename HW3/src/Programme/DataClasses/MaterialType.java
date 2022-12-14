package Programme.DataClasses;

public class MaterialType {
	private String material_code;
	private int length;
	private int width;
	private int height;
	private int volume;
	private int cost;
	
	public MaterialType(String material_code, int length, int width, int height, int cost) {
		this.material_code = material_code;
		this.length = length;
		this.width = width;
		this.height = height;
		this.volume = length * width * height;
		this.cost = cost;
	}
	public MaterialType() {
		this("", 0, 0, 0, 0);
	}
	public MaterialType(MaterialType other) {
		this(other.material_code, other.length, other.width, other.height, other.cost);
	}
	public String getMaterial_code() {
		return material_code;
	}
	public int getLength() {
		return length;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getVolume() {
		return volume;
	}
	public int getCost() {
		return cost;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		MaterialType temp = (MaterialType) obj;
		return this.material_code.equals(temp.getMaterial_code()) && this.length == temp.getLength() && this.width == temp.getWidth() && this.height == temp.getHeight()
				&& this.volume == temp.getVolume() && this.cost == temp.getCost();
	}
	
	@Override
	public String toString() {
		return "Material Code: "+ this.material_code + "\tLength: " + this.length + "\tWidth: " + this.width + "\tHeight: " + this.height + "\tVolume: "
				+ this.volume + "\tCost: " + this.cost;
	}
	
}
