package InvoicePackage;


/**
 * House Model Object Represents a house that is for sale. Defined by the
 * following data: address, price, area, and number of bedrooms Address: this is
 * the street address such as 23-Linden-Avenue. Even though there can be many
 * words, we make it one word by introducing hyphens. Price: this is a number
 * like 149999. Area: the square-footage of the house, again a number, such as
 * 1800. numberOfBedrooms: gain, a small number, like 3
 *
 * @author Dennis Pavlyuk
 * @version 1.1
 */
public class House {
    private String address;
    private int price;
    private int area;
    private int numberOfBedrooms;

    /**
     * Constructor.
     *
     * @param address
     * @param price            Integer
     * @param area             square feet
     * @param numberOfBedrooms Integer
     */
    public House(String address, int price, int area, int numberOfBedrooms) {
        this.address = address;
        this.price = price;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the area
     */
    public int getArea() {
        return area;
    }

    /**
     * @return the numberOfBedrooms
     */
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * @return string concatenation of house object variables.
     */
    public String toString() {
        String house;
        house = ("The property at " + address);
        house += (" costs $" + price + ", has " + area + " square feet ");
        house += ("and " + numberOfBedrooms + " bedrooms.");
        return house;
    }
    /**
     * Checks if the callee of type (House) matches the specifications of called Criteria, object c
     *
     * @param c (Criteria object)
     * @return true if this House is within specified criteria, else false.
     */
    public boolean satisfies(Criteria c) {
        if (this.getPrice() >= c.getMinimumPrice()) {
            if (this.getPrice() <= c.getMaximumPrice()) {
                if (this.getArea() >= c.getMinimumArea()) {
                    if (this.getArea() <= c.getMaximumArea()) {
                        if (this.getNumberOfBedrooms() >= c.getMinimumNumberOfBedrooms()) {
                            if (this.getNumberOfBedrooms() <= c.getMaximumNumberOfBedrooms()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}