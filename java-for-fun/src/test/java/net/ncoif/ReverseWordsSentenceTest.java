package net.ncoif;

import org.junit.Assert;
import org.junit.Test;

public class ReverseWordsSentenceTest {

	@Test
	public void reverseWordsSentence() {
		Assert.assertEquals("chocolate love I", ReverseWordsSentence.reverseWordsString("I love chocolate"));
		Assert.assertEquals("chocolate love We", ReverseWordsSentence.reverseWordsString("We love chocolate"));
		Assert.assertEquals("chocolate", ReverseWordsSentence.reverseWordsString("chocolate"));
	}
	
	@Test
	public void withMultipleSpaces() {
		Assert.assertEquals("chocolate  love I", ReverseWordsSentence.reverseWordsString("I love  chocolate"));
		Assert.assertEquals("chocolate  love  I", ReverseWordsSentence.reverseWordsString("I  love  chocolate"));
	}
	
}
