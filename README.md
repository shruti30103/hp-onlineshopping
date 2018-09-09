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

I have successfully added ip validation exercise as well. 

Logic:
  1. Validating IP address && CIDR range
  2. Convert IP and CIDR ip to bit format
  3. find mask
  4. calculate highest and lowest range
  5. Find the IP between the range and return boolean.

Testing has been done using main function:
This testing can be easily extended using Junit / or we can also extend this main function to run many test cases. Locally I have tested it using Junit but didn't wanted to overwhelm with too much redundant assert code so simply added a main function with three test case. 

    public static void main(String args[]){
    	String ipaddress = "192.168.0.1";
    	String Cidr = "192.168.0.0/24";
    	System.out.println(validateIpAddress(ipaddress, Cidr));
    	ipaddress = "192,168.0.1";
    	Cidr = "192.168.0.0/24";
    	System.out.println(validateIpAddress(ipaddress, Cidr));
    	ipaddress = "192.168.0.1";
    	Cidr = "192.168.0.0/35";
    	System.out.println(validateIpAddress(ipaddress, Cidr));
    }
        
