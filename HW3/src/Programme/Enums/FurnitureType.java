package Programme.Enums;

public enum FurnitureType {
	TABLE, WARDROBE, SHELF;
	public static FurnitureType findByFurnitureName(FurnitureName name) {
		if(name == FurnitureName.OBSIDIAN || name == FurnitureName.PEARL || name == FurnitureName.ELEGANT || name == FurnitureName.WALNUT || name == FurnitureName.GARDEN)
			return TABLE;
		else if(name == FurnitureName.LAVINIA || name == FurnitureName.LOKI || name == FurnitureName.ATLAS)
			return WARDROBE;
		else
			return SHELF;
	}
	
	public static double getPercentageValue(FurnitureType type) {
		if(type == TABLE)
			return 3;
		else if(type == WARDROBE)
			return 3.2;
		else
			return 2.8;
	}
	
	@Override
	public String toString() {
		if(this == TABLE)
			return "Table";
		else if(this == WARDROBE)
			return "Wardrobe";
		else
			return "Shelf";
	}
}
