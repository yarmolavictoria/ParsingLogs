import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.*;

public class ParsingLogs {

    public static void main(String[] args) throws IOException {
        String title = "Captured transactions:";
        String ignoreTilte = "None";
        boolean isTransactionCaptured = false;

        List<FindLogsLines> findlogs = new ArrayList<FindLogsLines>();
        File folder = new File("C:/Users/Vika/Desktop/logs/");
        File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {

                    List<String> lines = readAllLines(Paths.get(file.getPath()));
                    FindLogsLines findLines = new FindLogsLines();
                    for (String line : lines ) {
                            if(isTransactionCaptured == true){
                                int length = line.lastIndexOf(':');
                                findLines.description = line.substring(length+1, line.length());
                                String LogsDateTime = line.substring(0, Math.min(line.length(), 15));
                                findLines.logTime = LogsDateTime;
                                    isTransactionCaptured =false;
                                    findlogs.add(findLines);
                                    findLines = new FindLogsLines();
                            }
                                if (line.contains(title)&& !line.contains(ignoreTilte)){
                                isTransactionCaptured = true;
                    }
                }

            }
        }
        // TODO: 15.05.2018 add parsing for datetime and compare the logs with the same time;
        String output = "";
        for (FindLogsLines lines1 : findlogs) {
            output += lines1.logTime + ":" + "\n";
            output += lines1.description+ "\n";
        }
        File fileOutput = new File("outputLogs.log");
        FileWriter writer = new FileWriter(fileOutput);
        fileOutput.createNewFile();
        writer.write(output);
        writer.close();
    }

}
