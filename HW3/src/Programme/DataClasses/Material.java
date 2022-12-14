package Programme.DataClasses;

public class Material {
	private MaterialType property;
	private int quality;
	
	public Material(MaterialType property ,int quality) {
		this.property = new MaterialType(property);
		this.quality = quality;
	}
	public Material() {
		this(new MaterialType(), 0);
	}
	public Material(Material other) {
		this(new MaterialType(other.getProperty()), other.getQuality());
	}
	public Material(String material_code, int length, int width, int height, int cost, int quality) {
		this(new MaterialType(material_code, length, width, height, cost), quality);
	}
	
	// kemal string constructor
	
	public MaterialType getProperty() {
		return new MaterialType(this.property);
	}
	public int getQuality() {
		return quality;
	}
	
	public String getMaterial_code() {
		return this.property.getMaterial_code();
	}
	public int getLength() {
		return this.property.getLength();
	}
	public int getWidth() {
		return this.property.getWidth();
	}
	public int getHeight() {
		return this.property.getHeight();
	}
	public int getVolume() {
		return this.property.getVolume();
	}
	public int getCost() {
		return this.property.getCost();
	}	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Material temp = (Material) obj;
		return this.property.equals(temp.property) && this.quality == temp.quality;
	}
	
	@Override
	public String toString() {
		return this.property.toString() + "\tQuality: " + this.quality;
	}
}
