package view;

import model.Histogram;

public interface HistogramDisplay {
    
    public Histogram histogram();
    
    public void show(Histogram histogram);
}
