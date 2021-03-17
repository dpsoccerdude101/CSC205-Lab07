package InvoicePackage;


/**
 * HouseList Model Object (Models an ArrayList of House objects).
 * This contains methods to read House objects from a text document.
 *
 * @author Dennis Pavlyuk
 * @version 1.1
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HouseList {
    private ArrayList<House> houseList = new ArrayList<House>();

    /**
     * Constructor.
     * Reads a file of text creates a house object from it. Then adds that house object to a list of House objects.
     *
     * @param fileName
     */
    public HouseList(String fileName) {
        File file = new File(fileName);
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (fileInput.hasNextLine()) {
            // reads next line of data
            String str = fileInput.nextLine();

            // Separate into tokens
            String[] split = str.split("\\s+");
            String address = split[0];

            // format address
            String[] addressSplit = address.split("-");
            addressSplit[1] = addressSplit[1].substring(0, 1).toUpperCase() + addressSplit[1].substring(1);
            addressSplit[2] = addressSplit[2].substring(0, 1).toUpperCase() + addressSplit[2].substring(1);
            address = String.join(" ", addressSplit);
            int price = Integer.parseInt(split[1]);
            int area = Integer.parseInt(split[2]);
            int numberOfBedrooms = Integer.parseInt(split[3]);
            House houseObjectFromDataSet = new House(address, price, area, numberOfBedrooms);
            houseList.add(houseObjectFromDataSet);
        }
    }
       /**
     * This method uses the House method, satisifies, to see if each House object
     * within the ArrayList, houseList, matches with Criteria object c and uses the House.toString()
     * method to print out the concatenated string of the matched house.
     *
     * @param c (Criteria object)
     */
    public void printHouses(Criteria c) {
        for (int count = 0; count < this.houseList.size(); count++) {
            if(this.houseList.get(count).satisfies(c)) {
                System.out.println(this.houseList.get(count).toString());
            }
        }
    }
    public ArrayList<House> getHouses(Criteria c) {
        ArrayList<House> matchedHouses = new ArrayList<House>();
        for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).satisfies(c)) {
                matchedHouses.add(houseList.get(i));
            }
        }
        return matchedHouses;
    }
    //randomly picks a house in the matched arrayList
    public House getRandom(ArrayList<House> matchedHouses) {

        if (matchedHouses.get(0).getAddress().equals("0")) {
            return new House ("1", 0, 0, 0);
        }
        else {
            int randomIndex = (int) (Math.random() * matchedHouses.size());
            House matched = matchedHouses.get(randomIndex);
            return matched;
        }
    }


}
