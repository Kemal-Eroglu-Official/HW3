package Programme.Enums;

public enum FurnitureName {
	OBSIDIAN, PEARL, ELEGANT, WALNUT, GARDEN, LAVINIA, LOKI, ATLAS, CORNER, HARMONY, LUNA, HITTITE;
	
	public static FurnitureName findByCode(String code) {
		switch (code) {
		case "TB1401":
			return OBSIDIAN;
		case "TB1402":
			return PEARL;
		case "TB1501":
			return ELEGANT;
		case "TB1502":
			return WALNUT;
		case "TB1503":
			return GARDEN;
		case "WD2201":
			return LAVINIA;
		case "WD2202":
			return LOKI;
		case "WD2203":
			return ATLAS;
		case "SH5001":
			return CORNER;
		case "SH5002":
			return HARMONY;
		case "SH5003":
			return LUNA;
		case "SH5101":
			return HITTITE;
		default:
			return OBSIDIAN;
		}
	}
	@Override
	public String toString() {
		switch (this) {
			case OBSIDIAN:
				return "Obsidian";
			case PEARL:
				return "Pearl";
			case ELEGANT:
				return "Elegant";
			case WALNUT:
				return "Walnut";
			case GARDEN:
				return "Garden";
			case LAVINIA:
				return "Lavinia";
			case LOKI:
				return "Loki";
			case ATLAS:
				return "Atlas";
			case CORNER:
				return "Corner";
			case HARMONY:
				return "Harmony";
			case LUNA:
				return "Luna";
			case HITTITE:
				return "Hittite";
			default:
				return "";
		}
	}
}
