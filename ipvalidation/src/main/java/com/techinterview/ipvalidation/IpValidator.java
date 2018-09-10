package com.techinterview.ipvalidation;

public class IpValidator {

    /**
     * Implement the function that validates if provided IP address is in the range defined by cidrRange parameter.
     * You can read about CIDR here https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
     * Also, you can use this page https://www.ipaddressguide.com/cidr to understand better CIDR concept.
     *
     * Code organization, implementing other functions that are called from validateAddress - all of this up to you.
     * The final code must be compilable, buildable and tested.
     *
     * You are free to use any build framework to build the code (maven, gradle, ant, etc.).
     *
     * You can use any java version for development (8, 9, 10, 11).
     *
     * You can use any libraries to simplify any auxiliary logic (e.g. string parsing).
     * But you cannot use any 3rd party libraries to implement validation itself.
     *
     * Testing framework selection is up to you. Testing the logic from "public static void main" is also OK.
     *
     * If you are familiar with Git, do your work in a branch and create pull request.
     *
     * @param ipAddress String representation of IP address that needs to be validated.
     *                  Function must verify that IP address definition itself is valid.
     *                  If IP address format is invalid, function must throw IllegalArgumentException.
     *                  E.g. 192.168.0.1 is correct definition of IP address.
     *                  256.0.0.1, 192,168.o.1, 192,168.0.1 are examples of invalid IP addresses.
     * @param cidrRange Classless Inter-Domain Routing (CIDR) definition that defines range of allowed IP addresses.
     *                  For example 11.1.0.0/24 CIDR defines IP addresses range from 11.1.0.0 to 11.1.0.255
     *                  Function must verify that cidrRange definition itself is valid.
     *                  If cidrRange format is invalid, function must throw IllegalArgumentException.
     *                  E.g. 192.168.0.0/24, 100.0.0.0/16, 10.10.0.0/32, 10.10.0.1/16 are valid definitions.
     *                  192.168.0.0/35, 300.0.0.0/16, 10.10,0.0/32, 10.10.0.256/16 are invalid definitions
     *                  If slash is omitted in cidrRange definition, you can assume that / 32 is used.
     *                  E.g. cidrRange 10.10.0.1 can be treated as 10.10.0.1/32
     * @throws IllegalArgumentException if either ipAddress or cidrRange definitions is invalid.
     * @return true if provided IP address is covered by the CIDR range; false otherwise.
     */
    public static boolean validateIpAddress(String ipAddress, String cidrRange) {
        boolean result = true;
    	// Validate ip and cidr range
    	try{
    		result = result && validIP(ipAddress);
    		result = result && validCidrRange(ipAddress);
    	}
    	catch (IllegalArgumentException iae) {
    		throw new IllegalArgumentException();
    	}
    	
    	// Converting ip address to bits format.
    	String[] ipParts = ipAddress.split("\\.");
    	int a = Integer.parseInt(ipParts[0]);
    	int b = Integer.parseInt(ipParts[1]);
    	int c = Integer.parseInt(ipParts[2]);
    	int d = Integer.parseInt(ipParts[3]);
    	
    	int ipaddr = (( a << 24 ) & 0xFF000000) 
    	           | (( b << 16 ) & 0xFF0000) 
    	           | (( c << 8 ) & 0xFF00) 
    	           |  ( d & 0xFF);
    	
    	int cidrIp = 0;
    	int mask = 32;
    	// Converting cidr address to bits format.
    	if(!cidrRange.contains("\\")){
    		String[] cidrParts = ipAddress.split("\\.");
    		int p = Integer.parseInt(cidrParts[0]);
        	int q = Integer.parseInt(cidrParts[1]);
        	int r = Integer.parseInt(cidrParts[2]);
        	int s = Integer.parseInt(cidrParts[3]);
        	
        	cidrIp = (( p << 24 ) & 0xFF000000) 
     	           | (( q << 16 ) & 0xFF0000) 
     	           | (( r << 8 ) & 0xFF00) 
     	           |  ( s & 0xFF);
    	}
    	else{
    		String[] parts = cidrRange.split("\\");
       		String[] cidrParts = parts[0].split(".");
    		int p = Integer.parseInt(cidrParts[0]);
        	int q = Integer.parseInt(cidrParts[1]);
        	int r = Integer.parseInt(cidrParts[2]);
        	int s = Integer.parseInt(cidrParts[3]);
        	
        	cidrIp = (( p << 24 ) & 0xFF000000) 
     	           | (( q << 16 ) & 0xFF0000) 
     	           | (( r << 8 ) & 0xFF00) 
     	           |  ( s & 0xFF);
        	
        	mask = Integer.parseInt(parts[1]);
    	}
        
    	// Find mask
    	mask = (-1) << (32 - mask);
    	// find lowest ip address
    	int lowest = cidrIp & mask;
    	// find highest ip address
    	int highest = lowest + (~mask);
    	// Check range
    	result = result && lowest <= ipaddr && ipaddr <= highest;
        return result;
    }
    
    public static boolean validCidrRange (String cidrRange) throws IllegalArgumentException {
    	boolean result = true;
    	if(!cidrRange.contains("\\")){
    		try{
    			result = result && validIP(cidrRange);
    		}
    		catch (IllegalArgumentException iae) {
    			throw new IllegalArgumentException();
    		}
    	}
    	else{
			String[] parts = cidrRange.split("\\");
			try{
    			result = result && validIP(parts[0]);
    		}
    		catch (IllegalArgumentException iae) {
    			throw new IllegalArgumentException();
    		}
			if (parts[1] != "8" || parts[1] != "16" || parts[1] != "24" || parts[1] != "32"){
				throw new IllegalArgumentException();
			}
    	}

    	return result;
    }
    
    public static boolean validIP (String ip) throws IllegalArgumentException {
        try {
            if ( ip == null || ip.isEmpty() ) {
            	throw new IllegalArgumentException();
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
            	throw new IllegalArgumentException();
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                	throw new IllegalArgumentException();
                }
            }
            if ( ip.endsWith(".") ) {
            	throw new IllegalArgumentException();
            }

            return true;
        } catch (NumberFormatException nfe) {
        	throw new IllegalArgumentException();
        }
    }
    
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
    
    
}

