package net.ncoif.dotclear;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class HtmlHandler {

	public static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");

	private static final String TITLE_SELECTOR = ".post-title";
	private static final String CONTENT_SELECTOR = ".post-content";
	private static final String CATEGORY_SELECTOR = ".post-info a";
	private static final String TAGS_SELECTOR = ".post-tags li";

	private static final String META_NAME = "name";
	private static final String META_CONTENT = "content";
	private static final String META_AUTHOR = "author";
	private static final String META_DATE = "dc.date";

	private Document doc;

	public HtmlHandler(String filePath) throws IOException {
		File input = new File(filePath);
		this.doc = Jsoup.parse(input, "UTF-8", "");
	}

	public BlogPost parseBlogPost() throws ElementNotFoundException {
		String title = parseTitle();
		String content = parseContent();
		String author = parseAuthor();
		String category = parseCategory();
		Set<String> tags = parseTags();
		DateTime date = parseDate();

		return new BlogPost(title, content, author, category, tags, date);
	}

	public String parseTitle() {
		Elements elem = this.doc.select(TITLE_SELECTOR);
		return elem.html();
	}

	public String parseContent() {
		Elements elem = this.doc.select(CONTENT_SELECTOR);
		return elem.html();
	}

	public String parseCategory() throws ElementNotFoundException {
		Elements elem = this.doc.select(CATEGORY_SELECTOR);
		// the first link is the category, the second is the permalink

		try {
			return Jsoup.clean(elem.get(0).html(), Whitelist.none());
		} catch (IndexOutOfBoundsException e) {
			throw new ElementNotFoundException(e);
		}
	}

	public Set<String> parseTags() {
		Elements elem = this.doc.select(TAGS_SELECTOR);
		Set<String> results = new HashSet<String>();
		for (Iterator<Element> it = elem.iterator(); it.hasNext();) {
			Element element = it.next();
			String elemClean = Jsoup.clean(element.html(), Whitelist.none());
			results.add(elemClean);
		}
		return results;
	}

	public String parseAuthor() {
		// <meta name="dc.publisher" content="Nicolas" />
		Elements elem = this.doc.getElementsByAttributeValue(META_NAME, META_AUTHOR);
		return elem.attr(META_CONTENT);
	}

	public DateTime parseDate() {
		// <meta name="dc.date" scheme="W3CDTF" content="2010-07-07T12:58:00+01:00" />
		Elements elem = this.doc.getElementsByAttributeValue(META_NAME, META_DATE);
		String date = elem.attr(META_CONTENT);
		return formatter.parseDateTime(date);
	}

}
