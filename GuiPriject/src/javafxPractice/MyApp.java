package javafxPractice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MyApp extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		
		Circle circ = new Circle(50, 50, 50);
		Group root = new Group(circ);
		Scene scene = new Scene(root, 250, 100);
		
		arg0.setTitle("My Test JAVAFX Application");
		arg0.setScene(scene);
		arg0.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
