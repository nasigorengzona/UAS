import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class Inventory {
	// Item, Qty
	private List<InventoryItem> items;
	private int t;
	

	public Inventory() {
		this.items = new ArrayList<InventoryItem>();
	}

	public List<InventoryItem> getItems() { return this.items; }
	
	public boolean contains(Item item) {
		for (InventoryItem i: items) {
			if (i.getItem().getName().equals(item.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public int find(Item item) {
		for (InventoryItem i: items) {
			if (i.getItem().getName().equals(item.getName())) {
				return items.indexOf(i);
			}
		}
		return -1;
	}
	
	// mengembalikan item tertentu, kembalikan -1 jika tidak ditemukan
	public int getItemCount(Item item) { 
		if (contains(item)) return items.get(find(item)).getQty();
		return -1;
	}
	
	// mengurangi jumlah qty item tertentu, kembalikan jumlah qty yang berhasil diambil
	// jika qty > banyak item di inventory, kembalikan banyak item di inventory
	// jika tidak temukan throw NoSuchElementException
	// jika qty <= 0 throw IllegalArgumentException
	public int takeItem(Item item, int qty) { 
		if (!contains(item)) {
			throw new NoSuchElementException();
		}
		if (qty <= 0) throw new IllegalArgumentException();
		
		int temp = getItemCount(item);
		if (qty < temp) {
			items.get(find(item)).setQty(temp-qty);
			return qty;
		} else if (qty > temp) {
			items.get(find(item)).setQty(0);
			return temp;
		} 
		return -1;
	}
	
	// mengurangi jumlah qty item tertentu, kembalikan jumlah qty yang berhasil diambil
	// jika qty > banyak item di inventory, kembalikan banyak item di inventory
	// jika tidak temukan throw NoSuchElementException
	// jika qty <= 0 throw IllegalArgumentException

	// menambahkan item tertentu ke inventory sebanyak inventory
	// jika qty <= 0 throw IllegalArgumentException
	public void putItem(Item item, int qty) {
		if (qty <= 0) throw new IllegalArgumentException();
		items.add(new InventoryItem(item,qty));
	}
	
	// kembalikan list items yang diurutkan berdasarkan harga item (descending)
	public List<InventoryItem> getOrderByPrice() {
		items.sort(new Comparator<InventoryItem>() {
			public int compare(InventoryItem a, InventoryItem b) {
				return (int)(b.getItem().getPrice()) - (int)(a.getItem().getPrice())*-1;
			}
		});
		return this.items;
	}
	
	// kembalikan list items yang diurutukan berdasarkan berat item (descending)
	public List<InventoryItem> getOrderByWeight() { 
		items.sort(new Comparator<InventoryItem>() {
			public int compare(InventoryItem a, InventoryItem b) {
				return (int)(b.getItem().getWeight()*100) - (int)(a.getItem().getWeight()*100) *-1;
			}
		});
		return this.items;
	}
	// kembalikan list items yang diurutkan berdasarkan qty item (descending)
	public List<InventoryItem> getOrderByQty() {
		items.sort(new Comparator<InventoryItem>() {
			public int compare(InventoryItem a, InventoryItem b) {
				return b.getQty() - a.getQty()*-1;
			}
		});
		return this.items;
	}

	public int getItemTotalCount() { 
		this.t = 0;
		for (InventoryItem i: items) {
			this.t += i.getQty();
		}
		return this.t;
	}
}
