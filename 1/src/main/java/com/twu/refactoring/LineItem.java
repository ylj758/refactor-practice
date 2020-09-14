package com.twu.refactoring;

public class LineItem {
	private String description;
	private double price;
	private int quantity;

	public LineItem(String description, double price, int qty) {
		super();
		this.description = description;
		this.price = price;
		this.quantity = qty;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    double totalAmount() {
        return price * quantity;
    }
}