package Programme.DataClasses;

import java.util.HashMap;

import Programme.ArrayListDeque.ArrayListDeque;

public class Vendor {
	private HashMap<String, ArrayListDeque<Material>> inventory;
	
	public Vendor(HashMap<String, ArrayListDeque<Material>> inventory) {
		this.inventory = copy_content(inventory);
	}
	
	public Vendor() {
		this(new HashMap<String, ArrayListDeque<Material>>());
	}
	
	public Vendor(Vendor other) {
		this(copy_content(other.getInventory()));
	}
	
	private static HashMap<String, ArrayListDeque<Material>> copy_content(HashMap<String, ArrayListDeque<Material>> other){
		String[] keys = (String[]) other.keySet().toArray();
		HashMap<String, ArrayListDeque<Material>> temp = new HashMap<String, ArrayListDeque<Material>>();
		for(String key: keys) {
			temp.put(key, new ArrayListDeque<Material>(other.get(key)));
		}
		return temp;
	}
	
	public static boolean equals_content(HashMap<String, ArrayListDeque<Material>> target1, HashMap<String, ArrayListDeque<Material>> target2) {
		String[] keys1 = (String[]) target1.keySet().toArray();
		String[] keys2 = (String[]) target2.keySet().toArray();
		if(keys1.length != keys2.length)
			return false;
		for(int i = 0; i < keys1.length; i++) {
			if(!keys1[i].equals(keys2[i]))
				return false;
		}
		for(String key: keys1) {
			if(!target1.get(key).equals(target2.get(key)))
				return false;
		}
		return true;
	}
	
	public HashMap<String, ArrayListDeque<Material>> getInventory(){
		return copy_content(this.inventory);
	}
	
	//Add material 
	
	public Material pickFront(String material_code) {
		return this.inventory.get(material_code).removeFront();
	}
	
	public Material pickBack(String material_code) {
		return this.inventory.get(material_code).removeBack();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Vendor temp = (Vendor) obj;
		return equals_content(this.inventory, temp.getInventory());
	}
	
	@Override
	public String toString() {
		String result = "";
		String[] keys = (String[]) this.inventory.keySet().toArray();
		for(String key: keys) {
			result += this.inventory.get(key).toString() + "\n";
		}
		return result;
	}
}
