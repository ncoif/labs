package net.ncoif.dotclear;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.overzealous.remark.Remark;

public class MarkdownHelper {
	public void write(String html, FileWriter writer) throws Exception {

		InputStream xsltFile = getClass().getResourceAsStream("markdown.xsl");

		Source xmlSource = new StreamSource(new StringReader(html));
		Source xsltSource = new StreamSource(xsltFile);

		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer trans = transFact.newTransformer(xsltSource);

		StringWriter result = new StringWriter();
		trans.transform(xmlSource, new StreamResult(result));
		System.out.println("debug:" + result.toString());

		trans.transform(xmlSource, new StreamResult(writer));
		writer.flush(); // write content
	}
	
	public void write2(String html, StringWriter writer) {
		Remark remark = new Remark();
		String markdown = remark.convertFragment(html, "public/");
		writer.append(markdown);
	}
}
