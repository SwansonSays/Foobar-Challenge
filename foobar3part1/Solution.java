package foobar3part1;

public class Solution {
	public static int solution(int start, int length) {
		int checkSum = 0;
		int rowStart = start;
		
		//Input error handling
		if(start < 0 || start > 2000000000) {
			System.out.println("Start must be between 0 and 2000000000 inclusive.");
			return -1;
		}
		if(length < 1) {
			System.out.println("Length must be 1 or greater.");
			return -1;
		}
		
		//XOR the range from [startRow, endRow] for each row
		for(int i = 1; i <= length; i++) {
			if(i != 1) {
				rowStart += length;
			}
			
			checkSum ^= xorRange(rowStart, rowStart + length - i);
			
		}		
		return checkSum;	
	}

	//XOR elements from [1, n] using repeating values with multiples of 4 
	public static int xorOneToN(int n) {
		int mod = n % 4;
		
		if(mod == 0) {
			return n;
		} else if(mod == 1) {
			return 1;
		} else if(mod == 2) {
			return n + 1;
		}
		
		return 0;
	}
	
	//XOR elements from [start, end] which is equivalent to XORing the ranges [1, start - 1] ^ [1, end]
	public static int xorRange(int start, int end) {
		return (xorOneToN(start - 1) ^ xorOneToN(end));
	}
}