package InvoicePackage;

/**
 * Criteria Model Object
 * This contains the criteria specified by the user to select houses.
 *
 * @author Dennis Pavlyuk
 * @version 1.1
 */
public class Criteria {
    private int minimumPrice;
    private int maximumPrice;
    private int minimumArea;
    private int maximumArea;
    private int minimumNumberOfBedrooms;
    private int maximumNumberOfBedrooms;

    /**
     * Constructor.
     *
     * @param minimumPrice
     * @param maximumPrice
     * @param minimumArea
     * @param maximumArea
     * @param minimumNumberOfBedrooms
     * @param maximumNumberOfBedrooms
     */
    public Criteria (int minimumPrice, int maximumPrice, int minimumArea, int maximumArea,
                     int minimumNumberOfBedrooms, int maximumNumberOfBedrooms) {
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
        this.minimumArea = minimumArea;
        this.maximumArea = maximumArea;
        this.minimumNumberOfBedrooms = minimumNumberOfBedrooms;
        this.maximumNumberOfBedrooms = maximumNumberOfBedrooms;
    }

    /**
     * @return the minimumPrice
     */
    public int getMinimumPrice() {
        return minimumPrice;
    }

    /**
     * @return the maximumPrice
     */
    public int getMaximumPrice() {
        return maximumPrice;
    }

    /**
     * @return the maximumArea
     */
    public int getMaximumArea() {
        return maximumArea;
    }

    /**
     * @return the maximumNumberOfBedrooms
     */
    public int getMaximumNumberOfBedrooms() {
        return maximumNumberOfBedrooms;
    }

    /**
     * @return the minimumArea
     */
    public int getMinimumArea() {
        return minimumArea;
    }

    /**
     * @return the minimumNumberOfBedrooms
     */
    public int getMinimumNumberOfBedrooms() {
        return minimumNumberOfBedrooms;
    }
}