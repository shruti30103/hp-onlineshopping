# hp-onlineshopping

I have made the online shopping as general as possible:

  1. I have created a hashmap with class Attribute Name = Map Key, Attribute Value = Map Value, So that we can change the String orientation and return format like json/csv in the future. Because we can get values using backPackMap.get("color")
  2. The online shopping exercise has been tested using below main function.

        public static void main(String args[]){
        	
        	List<Product> products = new ArrayList<Product>();
        	BackPack bp = new BackPack("77777777", "cool backpack");
        	bp.setColor("\"Black\"");
        	bp.setCapacity(15);
        	bp.setMaxContentWeight(20);
        	products.add(bp);
        	SmartPhone sp = new SmartPhone("88888888", "Android");
        	sp.setColor("\"Red\"");
        	sp.setManufacturer("Nokia");
        	sp.setNumberOfCPUs(8);
        	sp.setRamSize(258);
        	sp.setScreenResolution("1094 * 456");
        	products.add(sp);
        	ProductListProcessor pl = new ProductListProcessor();
        	System.out.println(pl.productListToString(products));
        }

# ipvalidation
        
