package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportJSON extends Report{
    public void generateReport(List<LibraryBook> books) throws IOException {
        FileWriter fileWriter = new FileWriter("JSONraport.txt");
        ObjectMapper objectMapper = new ObjectMapper();
        fileWriter.write(objectMapper.writeValueAsString(books));
        fileWriter.close();
    }
}
