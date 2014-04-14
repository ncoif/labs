package net.ncoif.dotclear;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CsvHandlerTest {

	@Test
	public void test_ReadLines() {
		URL file = getClass().getResource("posts-list.csv");
		
		try {
			CsvHandler reader = new CsvHandler(file.getFile());
			List<String[]> results = reader.readLines();
			
			Assert.assertEquals(392, results.size());
			Assert.assertEquals("index.php?post%2F2010%2F07%2F07%2FJe-part-en-Chine-!!!!.html", results.get(0)[0]);
		} catch (IOException e) {
			Assert.fail();
		}
	}
}
