package parser;

/**
 * Configuration file to hold immutible data
 * 
 * @project GEParser
 * @author Alexander Sniffin
 * @date Jan 9, 2016
 */
public class Config {

	/**
	 * Each webpage that holds a list of all the items
	 */
	public static final String[] WIKI_PAGES = {
		"http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Banana+sapling#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Book+of+darkness+page+set#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Dark+cavalier#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Gnome+hat#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Iron+scimitar#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Mithril+nails#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Premade+p%27+punch#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Rune+plateskirt+%28g%29#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Steel+set+%28lg%29#mw-pages",
        "http://2007.runescape.wikia.com/wiki/Category:Items_with_GE_modules?pagefrom=Willow+branch#mw-pages"
	};
	
	/**
	 * Whitespace fix for regex
	 */
	public static String whitespace_chars =  ""       /* dummy empty string for homogeneity */
            + "\\u0009" // CHARACTER TABULATION
            + "\\u000A" // LINE FEED (LF)
            + "\\u000B" // LINE TABULATION
            + "\\u000C" // FORM FEED (FF)
            + "\\u000D" // CARRIAGE RETURN (CR)
            + "\\u0020" // SPACE
            + "\\u0085" // NEXT LINE (NEL) 
            + "\\u00A0" // NO-BREAK SPACE
            + "\\u1680" // OGHAM SPACE MARK
            + "\\u180E" // MONGOLIAN VOWEL SEPARATOR
            + "\\u2000" // EN QUAD 
            + "\\u2001" // EM QUAD 
            + "\\u2002" // EN SPACE
            + "\\u2003" // EM SPACE
            + "\\u2004" // THREE-PER-EM SPACE
            + "\\u2005" // FOUR-PER-EM SPACE
            + "\\u2006" // SIX-PER-EM SPACE
            + "\\u2007" // FIGURE SPACE
            + "\\u2008" // PUNCTUATION SPACE
            + "\\u2009" // THIN SPACE
            + "\\u200A" // HAIR SPACE
            + "\\u2028" // LINE SEPARATOR
            + "\\u2029" // PARAGRAPH SEPARATOR
            + "\\u202F" // NARROW NO-BREAK SPACE
            + "\\u205F" // MEDIUM MATHEMATICAL SPACE
            + "\\u3000"; // IDEOGRAPHIC SPACE
	
}
