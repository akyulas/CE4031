import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Created by jodiakyulas on 24/9/19.
 */
public class CSVWriter {

    private static final String publicationCSVName = "publication.csv";
    private static final String authorCSVName = "author.csv";
    private static final String authoredCSVName = "authored.csv";
    private static final String CSV_SEPARATOR = "|";

    public static void writeToCSV(Set<Author> authorSet, Set<Authored> authoredSet, List<Publication> publicationList) throws IOException {
        writeToAuthorCSV(authorSet);
        writeToAuthoredCSV(authoredSet);
        writeToPublicationCSV(publicationList);
    }

    private static void writeToPublicationCSV(List<Publication> publicationList) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(publicationCSVName), "UTF-8"));
        StringBuffer oneLine = new StringBuffer();
        oneLine.append("pubKey");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("mdate");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("title");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("type");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("journal");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("bookTitle");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("year");
        bw.write(oneLine.toString());
        bw.newLine();
        for (Publication publication: publicationList) {
            oneLine =  new StringBuffer();
            oneLine.append(publication.getPubKey());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(publication.getMdate());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(publication.getTitle());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(publication.getType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(publication.getJournal());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(publication.getBooktitle());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(publication.getYear());
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void writeToAuthorCSV(Set<Author> authorSet) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authorCSVName), "UTF-8"));
        StringBuffer oneLine = new StringBuffer();
        oneLine.append("author");
        bw.write(oneLine.toString());
        bw.newLine();
        for (Author author: authorSet) {
            oneLine =  new StringBuffer();
            oneLine.append(author.getName());
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void writeToAuthoredCSV(Set<Authored> authoredSet) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authoredCSVName), "UTF-8"));
        StringBuffer oneLine = new StringBuffer();
        oneLine.append("author_name");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("publication_key");
        bw.write(oneLine.toString());
        bw.newLine();
        for (Authored authored: authoredSet) {
            oneLine =  new StringBuffer();
            oneLine.append(authored.getAuthorName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(authored.getPublicationKey());
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

}
