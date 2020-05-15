package com.example.model;

public class OrderTransaction {

	@Override
	public String toString() {
		return "OrderTransaction [from=" + from + ", to=" + to + ", amount=" + amount + "]";
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	private String from;
	private String to;
	private int amount;
}
