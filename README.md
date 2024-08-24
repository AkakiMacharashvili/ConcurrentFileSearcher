File Searcher Project
Overview
This project is a multi-threaded file searcher that scans through a directory and its subdirectories to find files containing a specific keyword. It efficiently searches through files using a thread pool, aggregates the results, and then outputs the findings.

Project Structure

1. Data Class
   The Data class is a simple data structure that holds information about where a keyword is found within a file.

Attributes:
String filePath: The path to the file where the keyword was found.
int lineNumber: The line number in the file where the keyword appears.
int keywordIndex: The index within the line where the keyword starts.

Constructor:
Data(String filePath, int lineNumber, int keywordIndex): Initializes a Data object with the specified file path, line number, and keyword index.

Methods:
String toString(): Returns a string representation of the Data object, displaying the file path, line number, and keyword index.


2. FileSearcher Class
   The FileSearcher class is the main entry point of the application. It handles user input, starts the file search process, and displays the results.

Attributes:
static ExecutorService service: A thread pool with a fixed number of threads used to manage concurrent file searches.

Main Method:
Prompts the user to input a root directory and a keyword to search.
Initializes the ThreadPoolManager with the provided root directory and keyword.
Starts the search process.
Retrieves and prints the search results from the ResultAggregator.

3. FileSearchTask Class
   The FileSearchTask class implements Runnable and is responsible for searching a list of files for the specified keyword.

Attributes:
List<String> files: A list of file paths to search.
String keyword: The keyword to search for within the files.
List<Data> data: A list to store instances of Data when the keyword is found.

Methods:
void run(): Searches each file in the list for the keyword. When the keyword is found, a Data object is created and added to the data list.
List<Integer> find(String line): A helper method that returns a list of indices where the keyword appears in a line.
void storeData(): Stores the search results in the ResultAggregator.


4. ResultAggregator Class
   The ResultAggregator class is a singleton responsible for storing and managing the results of the file search tasks.

Attributes:
private static final ResultAggregator resultAggregator: The singleton instance of ResultAggregator.
private List<Data> Storage: A list that stores Data objects representing the search results.

Methods:
static ResultAggregator getInstance(): Returns the singleton instance of ResultAggregator.
synchronized void addData(Data data): Adds a Data object to the storage list.
List<Data> getStorage(): Returns the list of stored Data objects.


5. ThreadPoolManager Class
   The ThreadPoolManager class is responsible for managing the thread pool and orchestrating the file search process.

Attributes:
static ExecutorService service: A thread pool with a fixed number of threads used to manage concurrent file searches.
Path path: The root directory path to start the search.
String keyword: The keyword to search for.

Constructor:
ThreadPoolManager(Path path, String keyword): Initializes the ThreadPoolManager with the root directory path and keyword.

Methods:
void exploreDirectory(): Walks through the directory tree starting from the root path and submits file search tasks to the thread pool for each non-Java file.
void start(): Starts the directory exploration and search process, then shuts down the thread pool.
private static void shutDown(): Shuts down the thread pool gracefully, ensuring all tasks are completed.

Usage
Compile the project.
Run the FileSearcher class.
Input the root directory path when prompted.
Input the keyword to search for.
The program will output the results showing the file paths, line numbers, and keyword indices where the keyword was found.