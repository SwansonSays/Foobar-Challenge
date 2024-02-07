package foobar3part2;

import java.util.Arrays;

public class Solution {
	public static int solution(int[] l) {
		int length = l.length;
		int count = 0;	
				
		if(length < 3 || length > 2000) {
			return 0;
		}		
		
		
		Arrays.sort(l);
		
		int[] c = new int[length];
		Arrays.fill(c, 0);
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < i; j++) {
				if(l[i] % l[j] == 0) {
					c[i] += 1;
					count += c[j];
				}
			}
		}
		
		
		/*
		for(int i = 0; i < length - 1; i++ ) {
			for (int j = i + 1; j < length; j++) {
				if((l[j] % l[i]) == 0) {
					for(int k = j + 1; k < length; k++) {
						if((l[k] % l[j]) == 0) {
							count++;
							//System.out.println("[" + l[i] + ", " + l[j] + ", " + l[k] +"]");
						}
					}
				}
			}
		}		
		*/
		return count;
	}
	
	public static int solutions(int[] l) {
		int length = l.length;
		int count = 0;	
				
		if(length < 3 || length > 2000) {
			return 0;
		}		
		
		

		for(int i = 0; i < length - 1; i++ ) {
			for (int j = i + 1; j < length; j++) {
				if((l[j] % l[i]) == 0) {
					for(int k = j + 1; k < length; k++) {
						if((l[k] % l[j]) == 0) {
							count++;
							//System.out.println("[" + l[i] + ", " + l[j] + ", " + l[k] +"]");
						}
					}
				}
			}
		}		

		return count;
	}
}