//name: Haonan Di
//andrew id: hdi
package hw3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchView{

	ComboBox<String> gameComboBox = new ComboBox<>(); //shows drop down for filtering the tableView data
	TextField searchTextField = new TextField();  //for entering search letters
	TableView<Score> searchTableView = new TableView<>();  //displays data from scores.csv
	
	
	/**setupView() sets up the GUI components
	 * for Search functionality
	 */
	void setupView() {
		
		VBox searchVBox = new VBox();  //searchVBox contains searchLabel and searchHBox
		Text searchLabel = new Text("Search");
		searchVBox.getChildren().add(searchLabel);

		HBox searchHBox = new HBox();  //searchHBox contain gameComboBox and searchTextField
		searchHBox.getChildren().add(gameComboBox);
		searchHBox.getChildren().add(new Text("Search letters"));
		searchHBox.getChildren().add(searchTextField);
		searchVBox.getChildren().add(searchHBox);
		
		searchLabel.setStyle( "-fx-font: 30px Tahoma;" + 
				" -fx-fill: linear-gradient(from 0% 50% to 50% 100%, repeat, lightgreen 0%, lightblue 50%);" +
				" -fx-stroke: gray;" +
				" -fx-background-color: gray;" +
				" -fx-stroke-width: 1;") ;
		searchHBox.setPrefSize(WordNerd.GAME_SCENE_WIDTH, WordNerd.GAME_SCENE_HEIGHT / 3);
		gameComboBox.setPrefWidth(200);
		searchTextField.setPrefWidth(300);
		searchHBox.setAlignment(Pos.CENTER);
		searchVBox.setAlignment(Pos.CENTER);
		searchHBox.setSpacing(10);
		
		setupSearchTableView();
		
		WordNerd.root.setPadding(new Insets(10, 10, 10, 10));
		WordNerd.root.setTop(searchVBox);
		WordNerd.root.setCenter(searchTableView);
		WordNerd.root.setBottom(WordNerd.exitButton);
	}

	void setupSearchTableView() {
		//write your code here
		// to make the columns fill in all the graph area
		searchTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
		TableColumn<Score, String> column1 = new TableColumn<>("Game");
	    column1.setCellValueFactory(new PropertyValueFactory<>("gameName"));

	    TableColumn<Score, String> column2 = new TableColumn<>("Word");
	    column2.setCellValueFactory(new PropertyValueFactory<>("puzzleWord"));

	    TableColumn<Score, String> column3 = new TableColumn<>("Time (sec)");
	    column3.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
	    
	    TableColumn<Score, String> column4 = new TableColumn<>("Score");
	    column4.setCellValueFactory(new PropertyValueFactory<>("score"));

	    
	    searchTableView.getColumns().add(column1);
	    searchTableView.getColumns().add(column2);
	    searchTableView.getColumns().add(column3);
	    searchTableView.getColumns().add(column4);

	    //add the data to the table
	    searchTableView.getItems().addAll(WordNerdModel.scoreFromFile);

	}
	
	
}
