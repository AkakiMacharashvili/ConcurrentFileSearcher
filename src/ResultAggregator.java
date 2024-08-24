import java.util.ArrayList;
import java.util.List;

public class ResultAggregator {
    private static final ResultAggregator resultAggregator = new ResultAggregator();

    private List<Data> Storage = new ArrayList<>();

    private ResultAggregator() {}

    public static ResultAggregator getInstance() {
        return resultAggregator;
    }

    public List<Data> getStorage() {
        return Storage;
    }

    public synchronized void addData(Data data) {
        Storage.add(data);
    }
}
