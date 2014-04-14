package net.ncoif.dotclear;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CsvHandler {

	private CSVReader reader;
	
	public CsvHandler(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		this.reader = new CSVReader(new FileReader(file));

	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.reader.close();
	}
	
	public List<String[]> readLines() throws IOException {
		return this.reader.readAll();
	}
}
