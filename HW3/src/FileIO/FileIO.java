package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Programme.DataClasses.FurnitureRecipe;
import Programme.Enums.FurnitureName;

public class FileIO {
	private ArrayList<FurnitureRecipe> recipe_list;
	private ArrayList<Material> materials;
    	private ArrayList<MaterialType> rawMaterials;
    	private HashMap<String, ArrayListDeque<Material>> vendorInventory;
	
	public FileIO() {
		recipe_list = new ArrayList<FurnitureRecipe>();
	}
	
	public void read_furniture_recipe() {
		try {
			Scanner s = new Scanner(new File("src/Data/FurnitureParts.csv"));
			while(s.hasNextLine()) {
				String[] parts = s.nextLine().split(",");
				FurnitureRecipe recipe = new FurnitureRecipe(FurnitureName.findByCode(parts[0]));
				for(int i = 1; i < parts.length; i += 2) {
					recipe.addPartRecipe(parts[i], Integer.parseInt(parts[i+1]));
				}
				this.recipe_list.add(recipe);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<FurnitureRecipe> getRecipe(){
		return new ArrayList<FurnitureRecipe>(this.recipe_list);
	}

    public void setup() {
        setupRawMaterials();
        setupMaterials();
        setupVendorInventory();
    }

    private void setupRawMaterials() {
        try {
            Scanner scanner = new Scanner("src\\Data\\RawMaterialProperties.csv");

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
            Scanner scanner = new Scanner("src\\Data\\VendorPossesions.csv");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arr = line.split(",");

                if (arr.length != 2) {
                    System.exit(-1);
                }

                String materialCode = arr[0];
                int quality = Integer.parseInt(arr[1]);

                for (MaterialType type : rawMaterials) {
                    if (type.getMaterial_code() == materialCode) {
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
}
