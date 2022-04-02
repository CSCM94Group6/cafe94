import java.util.ArrayList;
/**
 * The Order Class allows the customer and waiter to make orders. An order may be eat-in, take-away, or delivery. eat-in
 * and take-away orders are approved by default. Delivery orders have to be approved by a waiter.
 * @author Eylul Altun
 * @author Purit jessadakannasoon
 * @author liz Jones
 * @version 1.0
 */

public class Order {
	private String type;
	private ArrayList<Menu> menu;
	private String dailySpecial;
	private int userId;
	private boolean approved;
	private boolean ready;
	
	public Order(int id) {
		this.menu = new ArrayList<Menu>();
		this.userId = id;
		this.approved = true;
		this.ready = false;
		this.type = "Eat-in";
	}
	
	public Order() {}

	public boolean isApproved() {
		return approved;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	/**
	 * The <code>setApproved</code> method flags whether an order needs approval, and if it does, allows it
	 * to be approved.
	 * @param approved the waiter inputs true to approve an order.
	 * */
	public void setApproved(boolean approved) {
		if (type.equalsIgnoreCase("takeaway") || type.equalsIgnoreCase("Eat-in")){
			this.approved = true;
		} else this.approved = approved;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDailySpecial() {
		return dailySpecial;
	}

	public void setDailySpecial(String dailySpecial) {
		this.dailySpecial = dailySpecial;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void addToOrder(Menu item) {
		menu.add(item);
	}
	
	public String toString() {
		String items = "";
		for (Menu m: menu) {
			items = items + m;
		}
		return String.format("User Id: %d%n"
				+ "Order Type: %s%n" +
				"Approved: %b%n"
				+ "Items: %n[%s]%n%n", userId, type, approved,items);
	}
	
	private chefsdailyspecials(){
		return dailyspecials;
	}
	
}

//chef adds the specials menu.
