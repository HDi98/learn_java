package hw3_shuwu;


import javafx.scene.chart.LineChart;

import java.util.List;

public class ScoreController extends WordNerdController {

    ScoreView scoreView;

    @Override
    void startController() {
        scoreView = new ScoreView();
        scoreView.setupView();
        setupBindings();

        WordNerdModel wordNerdModel = new WordNerdModel();
        wordNerdModel.readScore();
        ScoreChart scoreChart = new ScoreChart();

        List<LineChart<Number, Number>> lineCharts = scoreChart.drawChart(wordNerdModel.scoreList);
        scoreView.scoreGrid.add(lineCharts.get(0), 0, 1, 2, 1);
        scoreView.scoreGrid.add(lineCharts.get(1), 0, 2, 2, 1);

    }

    @Override
    void setupBindings() {

    }


}
