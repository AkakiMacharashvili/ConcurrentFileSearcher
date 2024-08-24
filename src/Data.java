public class Data {
    String filePath;
    int lineNumber;
    int keywordIndex;

    public Data(String filePath, int lineNumber, int keywordIndex) {
        this.filePath = filePath;
        this.lineNumber = lineNumber;
        this.keywordIndex = keywordIndex;
    }

    @Override
    public String toString() {
        return "Data{" +
                "filePath='" + filePath + '\'' +
                ", lineNumber=" + lineNumber +
                ", keywordIndex=" + keywordIndex +
                '}';
    }
}
