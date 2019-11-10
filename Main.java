package InvoicePackage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.events.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Invoice");
		VBox pane = new VBox(1);
		pane.setPadding(new Insets(15, 5, 5, 5));
		//file:///C:/Users/denni/Desktop/Desktop/CSC205/CSC205-Lab07/Lab07
		
		URL url = this.getClass().getResource("/MyForm.html");
		
		
		pane.getChildren().addAll(new Label("Shirts"), new TextField());
		pane.getChildren().addAll(new Label("Pants"), new TextField());
		pane.getChildren().addAll(new Label("Ties"), new TextField());
		pane.getChildren().addAll(new Label("Shoes"), new TextField());
		
		final WebView browser = new WebView();
		//final WebEngine webEngine = browser.getEngine();
		WebEngine webEngine = browser.getEngine();
		System.out.print(url.toString());
		webEngine.load(url.toString());
	
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().add("noborder-scroll-pane");
        scrollPane.setStyle("-fx-background-color: white");
        scrollPane.setContent(browser);
        scrollPane.setFitToWidth(true);
        
        pane.getChildren().add(scrollPane);
		
		
		Scene scene = new Scene(pane, 200, 250);
		primaryStage.setScene(scene);;
		primaryStage.show();
		
		EventListener listener = new EventListener() {
			@Override
			public void handleEvent(org.w3c.dom.events.Event ev) {
		        Platform.exit();
		    }
		};
		
		HTMLDocument doc = (HTMLDocument) webEngine.getDocument();
		HTMLElement form1 = (HTMLElement) doc.getElementById("submit");
		((EventTarget) form1).addEventListener("click", listener, false);
		
		
	}
	
}
