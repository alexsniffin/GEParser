package parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import parser.database.MySQLConnection;

/**
 * Driver class for the GE Parsing application
 * 
 * @project GEParser
 * @author Alexander Sniffin
 * @date Jan 8, 2016
 */
public class Driver {
	
	/**
	 * Database object
	 */
	private static MySQLConnection database;
	
	/**
	 * Lists of parsed data
	 */
	private static ArrayList<Item> items = new ArrayList<Item>();
	private static ArrayList<String> links = new ArrayList<String>();

	public static void main(String[] args) {
		database = new MySQLConnection("ip", "user", "pass");

		//Setup connection to the database
		if (database.establish()) {
			System.out.println("Database connection established...");
			
			//Get a list of all of the wiki links to all items
			for (int i = 0; i < Config.WIKI_PAGES.length; i++) {
				getWikiLinks(Config.WIKI_PAGES[i]);
			}
			
			//Parse out the data from each wiki page
			for (String itemUrl : links) {
				getData(itemUrl);
			}
			
			//Update or insert data to the database
			for (Item item : items) {
				dbUpdate(item);
			}
			
			try {
				database.getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to connect to database...");
			System.exit(0);
		}
		
	}
	
	/**
	 * Insert or update the item into the Items table
	 * 
	 * @param item
	 */
	private static void dbUpdate(Item item) {
		try {
			java.sql.Statement st = database.getConn().createStatement();
			
			st.executeUpdate("insert into Items values (null, '" + item.getName().replaceAll("'", "''") + "', " + item.getPrice() + ", " + item.getAlch() + ", "
					+ "" + (item.isMembers() ? 1 : 0) + ") ON DUPLICATE KEY UPDATE price = " + item.getPrice() + ", alch = " + item.getAlch() + "");
			
			System.out.println(item.getName() + " inserted or updated into database successfully!");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse out the data from each item url and add it to a list
	 * 
	 * @param url
	 */
	private static void getData(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			
			int alchTr = 0;
			String amount, ge_amount;
			
			//Check if the item is tradable or alchable
			if (!doc.select("#mw-content-text > div.infobox-wrapper > table > tbody > tr:nth-child(5) > td").text().equals("No") ||
					doc.select("#mw-content-text > div.infobox-wrapper > table > tbody > tr:nth-child(8) > td").text()
					.equals("Cannot be alchemised")) {
				
				//Item name
				Elements name = doc.select("#WikiaPageHeader > div.header-container > div.header-column.header-title > h1");
				
				//Membership
				Elements members = doc.select("#mw-content-text > div.infobox-wrapper > table > tbody > tr:nth-child(3) > td");
				
				//Find which element has High Alch values
				for (int i = 0; i < 12; i++)
					if (doc.select("#mw-content-text > div.infobox-wrapper > table > tbody > tr:nth-child(" + i + ") > th > a")
							.text().equals("High Alch"))
						alchTr = i;
				Elements alch = doc.select("#mw-content-text > div.infobox-wrapper > table > tbody > tr:nth-child(" + alchTr + ") > td");
				
				//Check if the item is a pie/pizza and parse out the correct value
				if (doc.select("#mw-content-text > div.infobox-wrapper > table > tbody > tr:nth-child(" + alchTr + ") > td").text().contains("Whole")) {
					amount = alch.text().substring(0, alch.text().indexOf("Half")).replaceAll("["+Config.whitespace_chars+",:a-zA-Z]", "");
				} else {
					amount = alch.text().replaceAll("["+Config.whitespace_chars+",:a-zA-Z]", "");
				}
				
				//If the parse failed or there is no value, leave the result as 0
				amount = amount.equals("") ? "0" : amount;
				
				//GE Price
				Elements ge = doc.select("#GEPrice");
				
				//Parse out the price as an integer
				ge_amount = ge.text().replaceAll("["+Config.whitespace_chars+",:a-zA-Z]", "");

				//If the parse failed or there is no value, leave the result as 0
				ge_amount = ge_amount.equals("") ? "0" : ge_amount;
				
				try {
					System.out.println("Item: " + name.text() + ", Members: " + (members.text().equals("Yes") ? true : false) + ", Alch: " + Integer.parseInt(amount) + ", GE: " + Integer.parseInt(ge_amount));
					
					//Add the item to a list as an Item object
					Item item = new Item(name.text(), Integer.parseInt(ge_amount), Integer.parseInt(amount), members.text().equals("Yes") ? true : false);
					items.add(item);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Using the Wiki url, parse each link out from the item section and add it to a list
	 * 
	 * @param url
	 */
	private static void getWikiLinks(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			
			Elements page = doc.select("#mw-pages > div > table > tbody > tr > td > ul > li > a");
			
			//Add each element to the list
			for (Element pg : page) {
				System.out.println(pg.attr("abs:href"));
				links.add(pg.attr("abs:href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
