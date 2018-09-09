package com.techinterview.onlinestore.service;

import domain.*;
import service.ProductListProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.techinterview.onlinestore.domain.Product;

import java.util.List;

/**
 * Implement the function productListToString that does conversion of provided list of products to text representation
 * of this list in this way.
 * - Result string is \n - separated string. E.g.
 *
 *   product 1 details
 *   product 2 details
 *   ...
 *   product 3 details
 *
 * - Each line contains details of one product
 * - Product description line should look like this:
 *   Product name (product GUID), product attribute 1 name:product attribute1 value; ... product attribute n name:product attributen value;
 *   For example. List contains BackPack definition:
 *   BackPack {
 *       guid: 111-222-333
 *       name: Cool Backpack
 *       maxContentWeight: 15
 *       color: "Black"
 *       capacity: 20
 *   }
 *   This becka pakc description string should look like this:
 *   Cool Backpack (111-222-333), maxContentWeight: 15, color: "Black", capacity: 20
 *
 * Keep in mind these requirements:
 * - String reorientation can be modified in future.
 * - There is a possibility to support multiply ways to convert list of products to string. E.g. it it is possible that in future
 *   you will need to implement support of different formats of outpust string (e.g. json instead of \n-separated string).
 * The basic idea is to make your current implementation flexible and modifiable in future.
 *
 * You can use any build system to build the sources (maven, gralde, ant).
 * You can use any 3rd party libs in your application.
 * Any java version (>=8).
 * Code must be tested (framework is up to you).
 *
 * If you are familiar with Git, please do work in a separate branch and create a pull request with your changes.
 */
public class ProductListProcessor {

    /**
     * Make String representation of providd product list.
     * @param products list of the products that needs to be converted to String
     * @return String representation of the provided list.
     */
    public String productListToString(List<Product> products) {
    	public String productListToString(List<Product> products) {
        	StringBuilder sb = new StringBuilder(""); 
        	if(products.size() == 0)
        		sb.append("No products in the list");
        	for(int i = 0; i < products.size(); i++){
        		if(products.get(i) instanceof BackPack){
        			BackPack bp = (BackPack) products.get(i);
        			Map<String, String> bpMap = getBackPackOrientationMap(bp);
        			sb.append(getBackPackString(bpMap));
        		}
        		if(products.get(i) instanceof SmartPhone){
        			SmartPhone sp = (SmartPhone) products.get(i);
        			Map<String, String> spMap = getSmartPhoneOrientationMap(sp);
        			sb.append(getSmartPhoneString(spMap));
        		}
        		sb.append("\n");
        	}
            return sb.toString();
        }
        
        /**
         * Make String representation of Smart Phone object.
         * @param Map<String, String> key is attribute name and value is attribute value
         * @return String representation of the provided object.
         * Note: We can also generalize this function to return json/csv format.
         */
        private String getSmartPhoneString(Map<String, String> spMap) {
    		StringBuilder sb = new StringBuilder("");
    		sb.append(spMap.get("name"));
    		sb.append(" ");
    		sb.append("(");
    		sb.append(spMap.get("guid"));
    		sb.append(")");
    		sb.append(", ");
    		sb.append("color: ");
    		sb.append(spMap.get("color"));
    		sb.append(", ");
    		sb.append("manufacturer: ");
    		sb.append(spMap.get("manufacturer"));
    		sb.append(", ");
    		sb.append("numberOfCPUs: ");
    		sb.append(spMap.get("numberOfCPUs"));
    		sb.append(", ");
    		sb.append("ramSize: ");
    		sb.append(spMap.get("ramSize"));
    		sb.append(", ");
    		sb.append("screenResolution: ");
    		sb.append(spMap.get("screenResolution"));
    		return sb.toString();
    	}

        /**
         * Make String representation of Back Pack object.
         * @param Map<String, String> key is attribute name and value is attribute value
         * @return String representation of the provided object.
         * Note: We can also generalize this function to return json/csv format.
         */
    	private String getBackPackString(Map<String, String> bpMap) {
    		StringBuilder sb = new StringBuilder("");
    		sb.append(bpMap.get("name"));
    		sb.append(" ");
    		sb.append("(");
    		sb.append(bpMap.get("guid"));
    		sb.append(")");
    		sb.append(", ");
    		sb.append("color: ");
    		sb.append(bpMap.get("color"));
    		sb.append(", ");
    		sb.append("capacity: ");
    		sb.append(bpMap.get("capacity"));
    		sb.append(", ");
    		sb.append("maxContentWeight: ");
    		sb.append(bpMap.get("maxContentWeight"));
    		return sb.toString();
    	}

        /**
         * Return map of Back Pack's attribute and it's value.
         * @param BackPack object of Back Pack class
         * @return Map<String, String> key is attribute name and value is attribute value
         * Note: We can change the orientation of the string as this is the map and we can
         * put it wherever we want by accessing attribute key/value. So, the orientation
         * won't be fixed
         */
    	private Map<String, String> getBackPackOrientationMap(BackPack bp){
        	String guid =  bp.getGuid();
        	String name = bp.getName();
        	String color = bp.getColor();
        	Double capacity = bp.getCapacity();
        	Double maxContentWeight = bp.getMaxContentWeight();
        	Map<String, String> bpMap = new HashMap<String,String>();
        	bpMap.put("guid", guid);
        	bpMap.put("name", name);
        	bpMap.put("color", color);
        	bpMap.put("capacity", String.valueOf(capacity));
        	bpMap.put("maxContentWeight", String.valueOf(maxContentWeight));
        	return bpMap;
        }
       
        /**
         * Return map of Smart Phone's attribute and it's value.
         * @param SmartPhone object of Back Pack class
         * @return Map<String, String> key is attribute name and value is attribute value
         * Note: We can change the orientation of the string as this is the map and we can
         * put it wherever we want by accessing attribute key/value. So, the orientation
         * won't be fixed
         */
        private Map<String, String> getSmartPhoneOrientationMap(SmartPhone sp){
        	String guid =  sp.getGuid();
        	String name = sp.getName();
        	String color = sp.getColor();
        	String manufacturer = sp.getManufacturer();
        	int numberOfCPUs = sp.getNumberOfCPUs();
        	double ramSize =  sp.getRamSize();
        	String screenResolution = sp.getScreenResolution();
        	Map<String, String> spMap = new HashMap<String,String>();
        	spMap.put("guid", guid);
        	spMap.put("name", name);
        	spMap.put("color", color);
        	spMap.put("manufacturer", manufacturer);
        	spMap.put("numberOfCPUs", String.valueOf(numberOfCPUs));
        	spMap.put("ramSize", String.valueOf(ramSize));
        	spMap.put("screenResolution", screenResolution);
        	return spMap;
        }
       
        //I have tested above functions using main method.
        /** 
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
        */
    }

