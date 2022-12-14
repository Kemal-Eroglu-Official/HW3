package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import Programme.ArrayListDeque.ArrayListDeque;
import Programme.DataClasses.FurnitureRecipe;
import Programme.DataClasses.Material;
import Programme.DataClasses.MaterialType;
import Programme.Enums.FurnitureName;

public class FileIO {
	private ArrayList<FurnitureRecipe> recipeList;
	private ArrayList<ArrayList<HashMap<String, Integer>>> manufacturersMaterialShopping;
	private ArrayList<ArrayList<HashMap<String, Integer>>> manufacturersFurnitureProduce;
	private ArrayList<Material> materials;
    	private ArrayList<MaterialType> rawMaterials;
    	private HashMap<String, ArrayListDeque<Material>> vendorInventory;
	
	public FileIO() {
		recipeList = new ArrayList<FurnitureRecipe>();
		manufacturersMaterialShopping = new ArrayList<ArrayList<HashMap<String,Integer>>>();
		manufacturersFurnitureProduce = new ArrayList<ArrayList<HashMap<String,Integer>>>();
		materials = new ArrayList<Material>();
		rawMaterials = new ArrayList<MaterialType>();
		vendorInventory = new HashMap<String, ArrayListDeque<Material>>();
	}
	
	/** 
	* Method that reads files and stores them in memory.
	* It is mandatory to invoke any fileio method before using it.
	*/
	public void setup() {
        	setupRawMaterials();
        	setupMaterials();
        	setupVendorInventory();
        	readFurnitureRecipe();
        	readManufacturesMaterialShopping();
        	readManufacturesFurnitureProduce();
    	}
	
	// The method that creates recipes of furnitures.
	private void readFurnitureRecipe() {
		try {
			Scanner s = new Scanner(new File("src/Data/FurnitureParts.csv"));
			while(s.hasNextLine()) {
				String[] parts = s.nextLine().split(",");
				FurnitureRecipe recipe = new FurnitureRecipe(
					FurnitureName.findByCode(parts[0])
				);
				
				for(int i = 1; i < parts.length; i += 2) {
					recipe.addPartRecipe(parts[i], Integer.parseInt(parts[i+1]));
				}
				
				this.recipeList.add(recipe);
			}
			s.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// The method that reads manufacturers' daily material request.
	private void readManufacturesMaterialShopping() {
		try {
			Scanner s1 = new Scanner(new File("src/Data/Manufacturer1Materials.csv"));
			Scanner s2 = new Scanner(new File("src/Data/Manufacturer2Materials.csv"));
			ArrayList<HashMap<String, Integer>> dailyRecord1 = new ArrayList<HashMap<String, Integer>>();
			ArrayList<HashMap<String, Integer>> dailyRecord2 = new ArrayList<HashMap<String, Integer>>();
			
			while(s1.hasNextLine()) {
				String[] parts = s1.nextLine().split(",");
				HashMap<String, Integer> shopCommand = new HashMap<String, Integer>();
				for(int i = 1; i < parts.length; i += 2) {
					shopCommand.put(parts[i], Integer.parseInt(parts[i+1]));
				}
				dailyRecord1.add(shopCommand);
			}
			
			while(s2.hasNextLine()) {
				String[] parts = s2.nextLine().split(",");
				HashMap<String, Integer> shopCommand = new HashMap<String, Integer>();
				for(int i = 1; i < parts.length; i += 2) {
					shopCommand.put(parts[i], Integer.parseInt(parts[i+1]));
				}
				dailyRecord2.add(shopCommand);
			}
			
			s1.close();
			s2.close();
			this.manufacturersMaterialShopping.add(dailyRecord1);
			this.manufacturersMaterialShopping.add(dailyRecord2);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void readManufacturesFurnitureProduce() {
		try {
			Scanner s1 = new Scanner(new File("src/Data/Manufacturer1Furnitures.csv"));
			Scanner s2 = new Scanner(new File("src/Data/Manufacturer2Furnitures.csv"));
			ArrayList<HashMap<String, Integer>> dailyRecord1 = new ArrayList<HashMap<String,Integer>>();
			ArrayList<HashMap<String, Integer>> dailyRecord2 = new ArrayList<HashMap<String,Integer>>();
			
			while(s1.hasNextLine()) {
				String[] parts = s1.nextLine().split(",");
				HashMap<String, Integer> shopCommand = new HashMap<String, Integer>();
				for(int i = 1; i < parts.length; i += 2) {
					shopCommand.put(parts[i], Integer.parseInt(parts[i+1]));
				}
				dailyRecord1.add(shop_command);
			}
			
			while(s2.hasNextLine()) {
				String[] parts = s2.nextLine().split(",");
				HashMap<String, Integer> shopCommand = new HashMap<String, Integer>();
				for(int i = 1; i < parts.length; i += 2) {
					shopCommand.put(parts[i], Integer.parseInt(parts[i+1]));
				}
				dailyRecord2.add(shop_command);
			}
			
			s1.close();
			s2.close();
			this.manufacturersFurnitureProduce.add(dailyRecord1);
			this.manufacturersFurnitureProduce.add(dailyRecord2);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

    private void setupRawMaterials() {
        try {
            Scanner scanner = new Scanner(new File("src/Data/RawMaterialProperties.csv"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arrayOfProperties = line.split(",");
                
                if (arrayOfProperties.length != 5) {
                    System.exit(-1);
                }

                String materialCode = arrayOfProperties[0];
                int length = Integer.parseInt(arrayOfProperties[1]);
                int width  = Integer.parseInt(arrayOfProperties[2]);
                int height = Integer.parseInt(arrayOfProperties[3]);
                int cost   = Integer.parseInt(arrayOfProperties[4]);

                MaterialType type = new MaterialType(materialCode, length, width, height, cost);
                rawMaterials.add(type);
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    	private void setupMaterials() {
        	if (rawMaterials == null) {
            		return;
        	}

        	try {
            		Scanner scanner = new Scanner(new File("src\\Data\\VendorPossessions.csv"));

            		while (scanner.hasNextLine()) {
                		String line = scanner.nextLine();
                		String[] arr = line.split(",");

                		if (arr.length != 2) {
			        	System.exit(-1);
                		}

                		String materialCode = arr[0];
                		int quality = Integer.parseInt(arr[1]);

                		for (MaterialType type : rawMaterials) {
                    			if (type.getMaterial_code().equals(materialCode)) {
                        			materials.add(
                            				new Material(
                                				new MaterialType(type), 
                                				quality
                            				)
                        			);
                    			}
                		}
            		}
            		scanner.close();
        	} 
        	catch (Exception e) {
            		System.out.println(e.getMessage());
        	}
   	}

    	private void setupVendorInventory() {
        	if (materials == null) {
            		return;
       	 	}

        	for (Material material : this.materials) {
            		if (vendorInventory.keySet().contains(material.getMaterial_code())) {
                		vendorInventory.get(material.getMaterial_code()).addBack(new Material(material));
            		}
            		else {
                		ArrayListDeque<Material> deque = new ArrayListDeque<>();
                		deque.addFront(new Material(material));
                		vendorInventory.put(material.getMaterial_code(), deque);
            		}
        	}
    	}

    	public ArrayList<Material> getMaterials() {
        	return materials;
    	}

    	public ArrayList<MaterialType> getRawMaterials() {
        	return rawMaterials;
    	}

    	public HashMap<String, ArrayListDeque<Material>> getVendorInventory() {
        	return vendorInventory;
    	}
		
	public ArrayList<FurnitureRecipe> getRecipe(){
		return new ArrayList<FurnitureRecipe>(this.recipe_list);
	}

	public ArrayList<ArrayList<HashMap<String, Integer>>> getManufacturerMaterialShopRecords(){
		return new ArrayList<ArrayList<HashMap<String, Integer>>>(this.manufacturers_material_shopping);
	}
	
	public ArrayList<ArrayList<HashMap<String, Integer>>> getManufacturerFurnitureProduceRecords(){
		return new ArrayList<ArrayList<HashMap<String, Integer>>>(this.manufacturers_furniture_produce);
	}

}
