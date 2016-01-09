package parser;

/**
 * Item object holding information about the items price, alch, etc.
 * 
 * @project GEParser
 * @author Alexander Sniffin
 * @date Jan 8, 2016
 */
public class Item {

	private int price, alch;
	private boolean members;
	private String name;

	public Item(String name, int price, int alch, boolean members) {
		this.name = name;
		this.price = price;
		this.alch = alch;
		this.members = members;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAlch() {
		return alch;
	}

	public void setAlch(int alch) {
		this.alch = alch;
	}

	public boolean isMembers() {
		return members;
	}

	public void setMembers(boolean members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
