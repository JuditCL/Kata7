package view;

import java.util.List;
import model.Attribute;
import model.Histogram;

public class HistogramBuilder<T> {

    private final List<T> list;

    public HistogramBuilder(List<T> list) {
        this.list = list;
    }

    public <A> Histogram<A> build(Attribute<T, A> attribute) {
        Histogram<A> histogram = new Histogram<>();
        for (T item : list) {
            histogram.increment(attribute.get(item));
        }
        return histogram;
    }

}
