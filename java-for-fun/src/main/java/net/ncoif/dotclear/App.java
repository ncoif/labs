package net.ncoif.dotclear;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Joiner;
import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;

/**
 * Hello world!
 * 
 */
public class App {
	private static String CSV_LIST = "/home/nicolas/hacking/dotclearToWordpress/nico.china.free.fr/list.csv";
	private static String DOTCLEAR_DIR = "/home/nicolas/hacking/dotclearToWordpress/nico.china.free.fr/";
	private static String DOTCLEAR_RESULT = "/home/nicolas/hacking/travel-ncoif-net/_posts/";

	private static String TO_REMOVE_URL_BASE = "http://remove-me-after-conversion/";
	private static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		System.out.println("Dotclear blog converter!");

		try {
			CsvHandler reader = new CsvHandler(CSV_LIST);

			List<String[]> results = reader.readLines();
			for (String[] filename : results) {
				HtmlHandler htmlParser = new HtmlHandler(DOTCLEAR_DIR + filename[0]);
				BlogPost blogPost = htmlParser.parseBlogPost();

				String resultFilename = DOTCLEAR_RESULT + "/" + getFileName(blogPost) + ".md";
				System.out.print("writing file \"" + resultFilename + "\" ...");
				FileWriter writer = new FileWriter(resultFilename);
				writeHeader(blogPost, writer);

				String content = fixFrenchWriting(blogPost.getContent());
				String markdown = toMarkdown(content);
				markdown = fixForRemark(markdown);

				writer.write(markdown);
				writer.flush(); // write file
				System.out.println(" done");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeHeader(BlogPost blogPost, FileWriter writer) throws IOException {
		StringBuilder headerBuilder = new StringBuilder();
		headerBuilder.append("---\n");
		headerBuilder.append("layout: post\n");
		String title = fixFrenchWriting(blogPost.getTitle());
		title = title.replace("\"", "\\\"");
		headerBuilder.append("title: \"" + title + "\"\n");

		String category = fixFrenchWriting(blogPost.getCategory());
		category = category.replace("\"", "\\\"");
		headerBuilder.append("categories: \"" + category + "\"\n");

		String tags = fixFrenchWriting(Joiner.on(" , ").join(blogPost.getTags()));
		headerBuilder.append("tags: [" + tags + "]\n");
		headerBuilder.append("date: " + dateTimeFormatter.print(blogPost.getDate()) + "\n");

		headerBuilder.append("---\n");
		headerBuilder.append("\n");

		writer.append(headerBuilder.toString());
	}

	private static String getFileName(BlogPost blogPost) {
		DateTime date = blogPost.getDate();
		String title = deleteHTMLEntities(blogPost.getTitle());
		title = title.replaceAll(" ", "-");
		title = title.replaceAll("/", "-");
		title = title.replaceAll(":", "-");
		return dateFormatter.print(date) + "-" + title;
	}

	private static String fixFrenchWriting(final String input) {
		return StringEscapeUtils.unescapeHtml4(input);
	}

	/**
	 * because need a BASE_URL to convert to markdown
	 * 
	 * @param input
	 * @return
	 */
	private static String toMarkdown(final String input) {
		Options opts = Options.markdown();
		opts.preserveRelativeLinks = true;
		Remark remark = new Remark(opts);
		return remark.convertFragment(input, TO_REMOVE_URL_BASE);
	}

	/**
	 * divers fixes for the link problem of the images
	 */
	private static final Pattern p = Pattern.compile("(/.)([a-zA-Z_0-9]*)(_[tms]{1})(.)(jpg|JPG)");

	private static String fixForRemark(final String markdown) {
		String result = markdown;
		result = result.replaceAll("public/", "/images/");
		result = result.replaceAll(".JPG", ".jpg");
		
//		 return result;

		StringBuffer s = new StringBuffer();
		Matcher m = p.matcher(result);
		while (m.find()) {
			m.appendReplacement(s, "/" + m.group(2) + ".jpg");
		}
		m.appendTail(s);

		return s.toString();
	}

	private static final Pattern htmlentities = Pattern.compile("&[a-zA-Z]{0,8};");

	private static String deleteHTMLEntities(final String input) {
		StringBuffer s = new StringBuffer();
		Matcher m = htmlentities.matcher(input);
		while (m.find()) {
			m.appendReplacement(s, "-");
		}
		m.appendTail(s);

		return s.toString();
	}

}
