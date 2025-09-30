package common.utils;

import java.util.Random;

public class MathUtils {
	
	public static void main(String[] args) {
		System.out.println("test");
	}
	
	public static int getRandomIntWithRange(int min, int max) {
		
		Random random = new Random();
		
		return random.nextInt((max - min) + 1) + min;
	}
	
	
}
