package javafxtest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application{
	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane root = new BorderPane();
		Text message = new Text ("Hello World!");
		root.setCenter(message);
		Scene scene = new Scene(root, 100, 100);
		arg0.setScene(scene);
		arg0.show();
}
	public static void main(String[] args) {
		launch(args);
	}
}