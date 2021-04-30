//name: Haonan Di
//andrew id: hdi
package hw3;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ScoreChart {

	List<LineChart<Number,Number>>  drawChart(ObservableList<Score> scores) {
		//thanks to https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
		//defining the axes
		

		final NumberAxis hangmanX = new NumberAxis();
		final NumberAxis hangmanY = new NumberAxis();
		hangmanX.setLabel("Rounds");
		hangmanY.setLabel("Score");
		
		final NumberAxis twisterX = new NumberAxis();
		final NumberAxis twisterY = new NumberAxis();
		twisterX.setLabel("Rounds");
		twisterY.setLabel("Score");

		//creating the chart
		final LineChart<Number,Number> hangmanLineChart = new LineChart<>(hangmanX,hangmanY);
		final LineChart<Number,Number> twisterLineChart = new LineChart<>(twisterX,twisterY);
		
		//defining a series
		XYChart.Series<Number, Number> hangmanSeries = new XYChart.Series<>();
		XYChart.Series<Number, Number> twisterSeries = new XYChart.Series<>();
		
		//populating the series with data
		int twisterCount = 0, hangmanCount = 0;
		for (Score score : scores) {
			if (score.getGameId() == WordNerd.TWISTER_ID)
				twisterSeries.getData().add(new XYChart.Data<Number, Number>(++twisterCount, score.getScore()));
			else 
				hangmanSeries.getData().add(new XYChart.Data<Number, Number>(++hangmanCount, score.getScore()));
		}
		twisterLineChart.getData().add(twisterSeries);
		hangmanLineChart.getData().add(hangmanSeries);
		
		twisterX.setTickUnit(1);
		hangmanX.setTickUnit(1);
		
		//setup look and feel
		hangmanLineChart.setTitle("Hangman");
		twisterLineChart.setTitle("Twister");
		hangmanLineChart.setPrefSize(WordNerd.GAME_SCENE_WIDTH, WordNerd.GAME_SCENE_HEIGHT / 3);
		twisterLineChart.setPrefSize(WordNerd.GAME_SCENE_WIDTH, WordNerd.GAME_SCENE_HEIGHT / 3);
		hangmanLineChart.setLegendVisible(false);
		twisterLineChart.setLegendVisible(false);
		hangmanLineChart.setStyle("-fx-border-color: lightgray");
		twisterLineChart.setStyle("-fx-border-color: lightgray");
		
		//create a list of lineCharts and add the two lineCharts to it
		List<LineChart<Number, Number>> lineChartList = new ArrayList<>();
		lineChartList.add(hangmanLineChart);
		lineChartList.add(twisterLineChart);
		
		return lineChartList;

	}
}
