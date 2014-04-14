package net.ncoif.dotclear;

import java.util.Set;

import org.joda.time.DateTime;

public class BlogPost {
	private final String title;
	private final String content;
	private final String author;
	private final String category;
	private final Set<String> tags;
	private final DateTime date;
	
	public BlogPost(String title, String content, String author,
			String category, Set<String> tags, DateTime date) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
		this.category = category;
		this.tags = tags;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public Set<String> getTags() {
		return tags;
	}

	public DateTime getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "BlogPost [title=" + title + ", content=" + content
				+ ", author=" + author + ", category=" + category + ", tags="
				+ tags + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
