package InvoicePackage;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {
    // for communication to the Javascript engine.
    private JSObject javascriptConnector;

    //private HouseList houseList = new HouseList("C:\\Users\\denni\\eclipse-workspace\\Project01\\src\\project01\\houses.txt");
    private HouseList houseList = new HouseList("C:\\Users\\denni\\OneDrive\\Desktop\\CSC205\\Project02 Intellij\\houses.txt");

    private ArrayList<House> matchedHouses = new ArrayList<House>();
    private Criteria criteriaObject;
    // for communication from the Javascript engine. //
    private JavaConnector javaConnector = new JavaConnector();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real Estate Listings");
        StackPane pane = new StackPane ();
        pane.setPadding(new Insets(15, 5, 5, 5));

        final WebView browser = new WebView();
        // final WebEngine webEngine = browser.getEngine();
        WebEngine webEngine = browser.getEngine();

        URL url = null;
        try {
            url = new URL("https://dpsoccerdude101.github.io/dpsoccerdude101.github.io/Project2%20Intellij/Real-Estate-Listings.html");
            System.out.print(url.toExternalForm());
            webEngine.load(url.toExternalForm());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            webEngine.loadContent("<html><head></head><body><h1>There's a bloody error.</h1></body></html>");
        }
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);

                // get the Javascript connector object.
                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });


        pane.getChildren().add(browser);
        pane.setPrefWidth(550);
        pane.setPrefHeight(610);
        //200, 250
        Scene scene = new Scene(pane, 600, 650);
        primaryStage.setScene(scene);

        primaryStage.show();

       webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                EventListener listener = new EventListener() {
                    @Override
                    public void handleEvent(org.w3c.dom.events.Event ev) {
                        matchedHouses.clear();
                    }
                };
                Document doc = webEngine.getDocument();
                Element reset = doc.getElementById("reset");
                ((EventTarget) reset).addEventListener("click", listener, false);
            }
        });
        // set up the listener

    }
    public class JavaConnector {

        private String value;
        /**
         * called when the JS side wants a String to be converted.
         *
         * @param value
         *         the String to convert
         */
        public void toJava(String value) {
            this.value = value;
            String[] tokens = value.split("&");
            Integer[] tokensInt = new Integer[tokens.length];
            for (int count = 0; count < tokens.length; count++) {
                String[] miniTokens = tokens[count].split("=");
                tokensInt[count] = Integer.parseInt(miniTokens[1]);
            }
            criteriaObject = new Criteria(tokensInt[0], tokensInt[1], tokensInt[2], tokensInt[3], tokensInt[4], tokensInt[5]);

            //this sets the matchedHouses List
            if (matchedHouses.isEmpty()) {
                matchedHouses = houseList.getHouses(criteriaObject);
            }
            //if the criteria did not match with anything then it will trigger "No houses left to display"
            if (matchedHouses.isEmpty())
                matchedHouses.add(new House("1", 0, 0, 0));
            House randomHouse = houseList.getRandom(matchedHouses);
            String randomHouseAddress;
            if (randomHouse.getAddress().equals("1"))
                randomHouseAddress = ("No houses left to display");
            else
                randomHouseAddress = randomHouse.getAddress();

            matchedHouses.remove(randomHouse);
            if (matchedHouses.isEmpty()) {
                matchedHouses.add(new House("0", 0, 0, 0));
            }
            if (this.value != null) {
                javascriptConnector.call("showResult", randomHouseAddress);
            }
        }
    }
}
