package net.ncoif.dotclear;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HtmlHandlerTest {

	private HtmlHandler reader;
	private BlogPost expected;
	
	@Before
	public void setUp() throws IOException {
		URL file = getClass().getResource("index.php-post-Je-part-en-Chine-.html");
		this.reader = new HtmlHandler(file.getFile());
		
		String title = "Je part en Chine !!!!";
		String content = "<p><img src=\"public/2010_ete/.chine_drapeau_t.jpg\" alt=\"Drapeau Chinois\" style=\"float:left; margin: 0 1em 1em 0;\" title=\"Drapeau Chinois, juil. 2010\" />l'universit&eacute; de <acronym title=\"qīnghu&aacute;\">清华</acronym>, &agrave; P&eacute;kin.</p>";
		String author = "Nicolas";
		String category = "G&eacute;n&eacute;ral";
		Set<String> tags = new HashSet<String>();
		tags.addAll(Arrays.asList("France", "G&eacute;n&eacute;ral"));
		DateTime date = HtmlHandler.formatter.parseDateTime("2010-07-07T12:58:00+01:00");
		
		this.expected = new BlogPost(title, content, author, category, tags, date);
	}
	
	@Test
	public void test_parseTitle() {
		Assert.assertEquals(expected.getTitle(), reader.parseTitle());
	}
	
	@Test
	public void test_parseContent() {
		String content = this.reader.parseContent();
		Assert.assertEquals(expected.getContent(), content);
	}
	
	@Test
	public void test_parseCategory() {
		try {
			String result = reader.parseCategory();
			Assert.assertEquals(expected.getCategory(), result);
		} catch (ElementNotFoundException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test_parseTags() {
		Set<String> result = this.reader.parseTags();
		Assert.assertEquals(expected.getTags(), result);
	}
	
	@Test
	public void test_parseAuthor() {
		String author = this.reader.parseAuthor();
		Assert.assertEquals(expected.getAuthor(), author);
	}
	
	@Test
	public void test_parseDate() {
		DateTime result = this.reader.parseDate();
		Assert.assertEquals(expected.getDate(), result);
	}
	
	@Test
	public void test_parseBlogPost() {
		try {
			BlogPost result = this.reader.parseBlogPost();
			Assert.assertEquals(expected, result);
		} catch (ElementNotFoundException e) {
			Assert.fail();
		}
	}
}
