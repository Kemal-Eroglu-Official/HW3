package Programme.Enums;

public enum QualityRange {
	BAD, NORMAL, GOOD, VERYGOOD, PERFECT;
	public static QualityRange percentageToQuality(double percentage) {
		if(percentage < 92)
			return BAD;
		else if(percentage <= 94)
			return NORMAL;
		else if(percentage <= 96)
			return GOOD;
		else if(percentage <= 98)
			return VERYGOOD;
		else
			return PERFECT;
	}
	
	@Override
	public String toString() {
		if(this == BAD)
			return "Bad Qlt";
		else if(this == NORMAL)
			return "Normal Qlt";
		else if(this == GOOD)
			return "Good Qlt";
		else if(this == VERYGOOD)
			return "Very Good Qlt";
		else
			return "Perfect Qlt";
	}
}
