package InvoicePackage;

public class Invoice {
	private double shirtsTotal;
	private double pantsTotal;
	private double tiesTotal;
	private double shoesTotal;
	private double salesTax;
	private double totalBill;
	//3.00 to 9.50, in increments of 0.25
	public Invoice (double shirtsTotal, double pantsTotal, double tiesTotal, double shoesTotal, double salesTax, double totalBill){
		this.shirtsTotal = shirtsTotal;
		this.pantsTotal = pantsTotal;
		this.tiesTotal = tiesTotal;
		this.shoesTotal = shoesTotal;
		this.salesTax = salesTax;
		this.totalBill = totalBill;
	}
	public String toString() {
		return "hello";
	}
}
