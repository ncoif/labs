package net.ncoif;

import org.junit.Test;

public class StringConcatenateTest {
	private static final int BENCHMARK = 10000;
	
	@Test
	public void stringConcatenate() {		
		long startTime = System.nanoTime();
		stringConcatenate(BENCHMARK);
		long endTime = System.nanoTime();
		System.out.println("concat duration=" + (endTime - startTime) / 1000000.0 + "ms");
	}
		
	@Test
	public void stringBuilderTest() {	
		long startTime = System.nanoTime();
		stringBuilderTest(BENCHMARK);
		long endTime = System.nanoTime();
		System.out.println("stringDuilder duration=" + (endTime - startTime) / 1000000.0 + "ms");
	}	
	
	private String stringConcatenate(int n) {
		String result = "";
		for (int i = 0; i <= n; i++) {
			result += i;
		}
		return result;
	}
	
	private String stringBuilderTest(int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= n; i++) {
			builder.append(i);
		}
		return builder.toString();
	}
}
