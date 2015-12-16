package control;

import model.Histogram;
import view.AttributeDialog;
import view.HistogramBuilder;
import view.HistogramDisplay;
import view.PopulationDialog;

public class CalculateCommand implements Command{
    private final AttributeDialog attributeDialog;
    private final PopulationDialog populationDialog;
    private final HistogramDisplay display;

    public CalculateCommand(AttributeDialog attributeDialog, PopulationDialog populationDialog, HistogramDisplay histogramDisplay) {
        this.attributeDialog = attributeDialog;
        this.populationDialog = populationDialog;
        this.display = histogramDisplay;
    }
    
    @Override
    public void execute() {
        Histogram histogram = new HistogramBuilder(populationDialog.population()).build(attributeDialog.attribute());
        display.show(histogram);
    }
}
