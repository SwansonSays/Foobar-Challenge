package foobar2part2;
import java.math.BigInteger;

public class Solution {
	public static String solution(int[] xs) {
		int negativeCount = 0, zeroCount = 0;
		BigInteger product = new BigInteger("1");
		int largestNegative = -1000, length = xs.length;
		
		if(length == 1) {
			return Integer.toString(xs[0]);
		}
		
		if(length > 50 || length == 0) {
			return "xs must contain 1 to 50 integers.";
		}
		
		for(int i = 0; i < length; i++) {
			if(xs[i] < 0) {
				negativeCount++;
				largestNegative = Math.max(largestNegative, xs[i]);
			}
			
			if(xs[i] != 0) {
				product = product.multiply(BigInteger.valueOf(xs[i]));
			} else {
				zeroCount++;
			}
		}
		
		if(zeroCount == length) {
			return "0";
		}
		
		if(negativeCount % 2 == 1) {
			if(negativeCount == 1 && negativeCount + zeroCount == length) {
				return "0";
			}
			product = product.divide(BigInteger.valueOf(largestNegative));
		}
		
		return product.toString();
	}
}