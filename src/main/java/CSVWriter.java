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
    private static final String CSV_SEPARATOR = "`";

    public static void writeToCSV(Set<String> authorSet, Set<Authored> authoredSet, List<Publication> publicationList) throws IOException {
        writeToAuthorCSV(authorSet);
        writeToAuthoredCSV(authoredSet);
        writeToPublicationCSV(publicationList);
    }

    public static void createNewCSV() throws IOException {
        createNewPublicationCSV();
        createNewAuthorCSV();
        createNewAuthoredCSV();
    }

    private static void createNewPublicationCSV() throws IOException {
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
        oneLine.append("publishedYear");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("publishedMonth");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("crossRef");
        bw.write(oneLine.toString());
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void writeToPublicationCSV(List<Publication> publicationList) throws IOException {
        if (publicationList.size() == 0) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(publicationCSVName, true), "UTF-8"));
        for (Publication publication: publicationList) {
            StringBuffer oneLine =  new StringBuffer();
            oneLine.append(escapeSpecialCharacters(publication.getPubKey()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getMdate()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getTitle()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getType()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getJournal()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getBooktitle()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getYear()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getMonth()));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(escapeSpecialCharacters(publication.getCrossRef()));
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void createNewAuthorCSV() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authorCSVName), "UTF-8"));
        StringBuffer oneLine = new StringBuffer();
        oneLine.append("name");
        bw.write(oneLine.toString());
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void writeToAuthorCSV(Set<String> authorSet) throws IOException {
        if (authorSet.size() == 0) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authorCSVName, true), "UTF-8"));
        for (String author: authorSet) {
            StringBuffer oneLine =  new StringBuffer();
            oneLine.append(author);
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void createNewAuthoredCSV() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authoredCSVName), "UTF-8"));
        StringBuffer oneLine = new StringBuffer();
        oneLine.append("author_name");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("publication_key");
        bw.write(oneLine.toString());
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void writeToAuthoredCSV(Set<Authored> authoredSet) throws IOException {
        if (authoredSet.size() == 0) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authoredCSVName, true), "UTF-8"));
        for (Authored authored: authoredSet) {
            StringBuffer oneLine =  new StringBuffer();
            oneLine.append(authored.getAuthorName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(authored.getPublicationKey());
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static String escapeSpecialCharacters(String string) {
        if (string == null) {
            return "null";
        }
        String result = string.replaceAll("\"", "\\\\\"");
        result = result.replaceAll("\'", "\\\\'");
        result = result.replaceAll("\\\\0", "\\\\\\\\0");
        return result;
    }

}
