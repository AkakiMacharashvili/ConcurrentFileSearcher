import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSearchTask implements Runnable{
    public List<String> files;
    public String keyword;
    public List<Data> data;
    public FileSearchTask(List<String> files, String keyword) {
        this.files = files;
        this.keyword = keyword;
        data = new ArrayList<>();
    }

    @Override
    public void run() {
//        you can uncomment it if you want to see information about working threads
//        System.out.println(Thread.currentThread());
        for(String file : files){
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int cur = 1;
                while ((line = reader.readLine()) != null) {
                    List<Integer> list = find(line);
                    for(var index : list){
                        Data currentData = new Data(file, cur, index);
                        data.add(currentData);
                    }
                    cur++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        storeData();
    }

    private void storeData() {
        ResultAggregator resultAggregator = ResultAggregator.getInstance();
        for(var d : data){
            resultAggregator.addData(d);
        }
    }

    public List<Integer> find(String line){
        List<Integer> indexList = new ArrayList<>();
        for(int i = 0; i < line.length() - keyword.length(); i++){
            String currentString = line.substring(i, i + keyword.length());
            if(currentString.equals(keyword)) indexList.add(i);
        }
        return indexList;
    }


}
