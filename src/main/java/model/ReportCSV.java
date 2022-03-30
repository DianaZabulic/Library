package model;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportCSV extends Report {
    public void generateReport(List<Book> books, List<PublishedBook> publishedBooks) {
        File file = new File("CSVraport.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"Titlu", "Autor", "Domeniu", "Numar inventar", "Editura", "An publicare", "ISBN"});
            for (int i = 0; i < books.size(); i++) {
                data.add(new String[]{books.get(i).getTitle(), books.get(i).getAuthor(), books.get(i).getDomain(),
                        Long.toString(publishedBooks.get(i).getID()), publishedBooks.get(i).getPublisher(),
                        Integer.toString(publishedBooks.get(i).getPublicationYear()),
                        Long.toString(publishedBooks.get(i).getIsbn())});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
