//name: haonan di
//andrew id: hdi
package hw3;

import java.util.List;

import javafx.scene.chart.LineChart;


public class ScoreController extends WordNerdController{
	
	ScoreView scoreView;
	ScoreChart scoreChart;

	@Override
	void startController() {
		scoreView = new ScoreView();
		scoreView.setupView();
		// read the score file in
		WordNerdModel.readScoreFile(WordNerdModel.SCORE_FILE_NAME);
		scoreChart = new ScoreChart();
		// draw the graph 
		List<LineChart<Number,Number>> graph = scoreChart.drawChart(WordNerdModel.scoreFromFile);
		
		scoreView.scoreGrid.add(graph.get(0), 0, 1, 2, 1);
		scoreView.scoreGrid.add(graph.get(1), 0, 2, 2, 1);
		
		setupBindings();
		
		// set up the view for scoreBoard
		WordNerd.root.setCenter(scoreView.scoreGrid);
		
		
	}

	@Override
	void setupBindings() {
		//to be implemented in HW3
	}
	
	

}
