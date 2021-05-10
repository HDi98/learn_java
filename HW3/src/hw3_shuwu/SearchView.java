package hw3_shuwu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class SearchView {


    ComboBox<String> gameComboBox = new ComboBox<>(); //shows drop down for filtering the tableView data
    TextField searchTextField = new TextField();  //for entering search letters
    TableView<Score> searchTableView = new TableView<>();  //displays data from scores.csv
    /**
     * setupView() sets up the GUI components
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

        gameComboBox.setValue("All games");
        ObservableList<String> options = FXCollections.observableArrayList("All games", "Hangman", "Twister");
        gameComboBox.getItems().addAll(options);
        searchLabel.setStyle("-fx-font: 30px Tahoma;" +
                " -fx-fill: linear-gradient(from 0% 50% to 50% 100%, repeat, lightgreen 0%, lightblue 50%);" +
                " -fx-stroke: gray;" +
                " -fx-background-color: gray;" +
                " -fx-stroke-width: 1;");
        searchHBox.setPrefSize(WordNerd.GAME_SCENE_WIDTH, WordNerd.GAME_SCENE_HEIGHT / 3);
        gameComboBox.setPrefWidth(200);
        searchTextField.setPrefWidth(300);
        searchHBox.setAlignment(Pos.CENTER);
        searchVBox.setAlignment(Pos.CENTER);
        searchHBox.setSpacing(10);

        setupSearchTableView(2);

        WordNerd.root.setPadding(new Insets(10, 10, 10, 10));
        WordNerd.root.setTop(searchVBox);
        WordNerd.root.setCenter(searchTableView);
        WordNerd.root.setBottom(WordNerd.exitButton);


        TableColumn column0 = new TableColumn("Game");

        column0.setCellValueFactory(
                new PropertyValueFactory<>("gameName"));

        TableColumn column1 = new TableColumn("Word");
        column1.setCellValueFactory(
                new PropertyValueFactory<>("puzzleWord"));

        TableColumn column2 = new TableColumn("Time(sec)");
        column2.setCellValueFactory(
                new PropertyValueFactory<>("timeStamp"));

        TableColumn column3 = new TableColumn("Score");
        column3.setCellValueFactory(
                new PropertyValueFactory<>("scoreString"));


        searchTableView.getColumns().addAll(column0, column1, column2, column3);

    }

    void setupSearchTableView(int gameId) {
        searchTableView.getItems().clear();
        WordNerdModel wordNerdModel = new WordNerdModel();
        wordNerdModel.readScore();
        ObservableList<Score> scores = wordNerdModel.scoreList;
        List<Score> resultScore = new ArrayList<>();
        int[] searchSum = new int[26];
        char[] searchWordArray = searchTextField.getText().toCharArray();
        for(char c : searchWordArray){
            try{
                searchSum[c - 'a']++;
            }catch (Exception e){

            }

        }

        for(Score curScore : scores){
            if (gameId != 2 && curScore.getGameId() != gameId) {
                continue;
            }else{
                if(isAdded(curScore.getPuzzleWord(), searchSum)){
                   Float sc = curScore.getScore();
//                   curScore.setScore(Float.parseFloat(String.format("%.2f", sc)));
                  resultScore.add(curScore);
                }
            }
        }

        searchTableView.setItems(FXCollections.observableArrayList(resultScore));
        searchTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }





    private boolean isAdded(String puzzleWord, int[] searchSum){
        int[] puzzleSum = new int[26];
        for(char c : puzzleWord.toCharArray()){
            try{
                puzzleSum[c - 'a']++;
            }catch (Exception e){

            }
        }

        for(int i = 0; i < 26; i++){
            if(puzzleSum[i] < searchSum[i]){
                return false;
            }
        }
        return true;
    }


}













