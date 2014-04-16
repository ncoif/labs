package net.ncoif.dotclear;

import org.junit.Test;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;

public class RemarkTest {
//	<html><head></head><body><p><img src="public/2010_ete/.chine_drapeau_t.jpg" alt="Drapeau Chinois" style="float:left; margin: 0 1em 1em 0;" title="Drapeau Chinois, juil. 2010" />Aujourd'hui, j'ai reçu un e-mail de ParisTech, m'informant que ma candidature pour le programme d'échange et la bourse CSC ont été acceptées. Ça y est, c'est sûr, je vais partir en Chine !!!!! Pour l'occasion, j'ai décidé de créer un blog, afin de tenir le reste du monde (et plus modestement ma famille et mes amis) au courant de mes pérégrinations dans l'Empire du Milieu.</p> 
//	<p>Enfin, il me reste tout de même encore quelques démarches administratives, comme par exemple obtenir un Visa étudiant et réserver mon billet d'avion (un aller simple !). Pour cela, j'attends que l' <em>"Admission Notice"</em> arrive par la poste. C'est en effet ce précieux sésame qui officialisera mon inscription à l'université de <acronym title="qīnghuá">清华</acronym>, à Pékin.</p></body></html>

//	private static final String html = "<img src=\"public/2010_ete/.chine_drapeau_t.jpg\" alt=\"Drapeau Chinois\" style=\"float:left; margin: 0 1em 1em 0;\" title=\"Drapeau Chinois, juil. 2010\" />";
	private static final String html = "<img src=\"public/flag.jpg\" title=\"flag\" />";
	
	@Test
	public void testRemarkImageLocal() {
		Options opts = Options.markdown();
		opts.preserveRelativeLinks = false;
		Remark remark = new Remark(opts);
		String markdown = remark.convertFragment(html, "http://remove-me-after-conversion/");
		System.out.println(markdown);
	}
	
	@Test
	public void testApostrophe() {
		String s = "Bar \"Coretto\"";
		s = s.replace("\"", "\\\"");
		System.out.println(s);
	}
}
