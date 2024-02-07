package foobar3part3;
import java.math.BigInteger; //String inputs will be larger then Integer size

public class Solution {
    public static String solution(String x, String y) {
    	//Convert strings to BigIntegers
    	BigInteger count = new BigInteger("0");
    	BigInteger mach = new BigInteger(x);
    	BigInteger facula = new BigInteger(y);
    	BigInteger temp;
    	
    	//while mach and facula do not both equal 1
    	while(!mach.equals(BigInteger.ONE) || !facula.equals(BigInteger.ONE)) {
    		if(notSolveable(mach, facula)) {
    			return "impossible";
    		}
    		
    		//If mach = 1, increment count by facula - 1 and return
    		if(mach.equals(BigInteger.ONE)) {
    			count = count.add(facula.subtract(BigInteger.ONE));
    			return count.toString();
    		}
    		//If facula = 1 , increment count by mach - 1 and return
    		if(facula.equals(BigInteger.ONE)) {
    			count = count.add(mach.subtract(BigInteger.ONE));
    			return count.toString();
    		}
    		
    		//mach = mach - facula * (mach / facula)
    		//increment count by mach / facula, rounded down
    		if(mach.compareTo(facula) > 0) {
    			temp = mach.divide(facula);
    			count = count.add(temp);
    			temp = temp.multiply(facula);
    			mach = mach.subtract(temp);
    			
    		//facula = facula - mach * ( facula / mach)
    		//increment count by facula / mach, rounded down
    		} else {
    			temp = facula.divide(mach);
    			count = count.add(temp);
    			temp = temp.multiply(mach);
    			facula = facula.subtract(temp);
    		}
    	}
    	
    	return count.toString();
    	
    }
    
    //Not solvable if either mach or facula is < 1 or mach = facula
    public static boolean notSolveable(BigInteger mach, BigInteger facula) {
		if(mach.compareTo(BigInteger.ZERO) < 1 || facula.compareTo(BigInteger.ZERO) < 1) {
			return true;
		}
		if(mach.equals(facula)) {
			return true;
		}
		
		return false;
    }
}