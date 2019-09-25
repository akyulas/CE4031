import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.*;

/**
 * Created by jodiakyulas on 6/9/19.
 */


public class ParserHandler extends DefaultHandler{

    int count = 0;

    private List<String> publicationType = Arrays.asList("incollection", "book", "inproceedings", "article");
    private List<String> titleModifiers = Arrays.asList("tt", "sub", "i", "sup");

    private Set<Author> authorSet = new HashSet<Author>();
    private List<Publication> publicationList = new ArrayList<Publication>();
    private Set<Authored> authoredSet = new HashSet<Authored>();

    private Set<Author> currentAuthorSet = null;
    private Publication currentPublication = null;
    private Set<Authored> currentAuthoredSet = null;

    private String currentTitle = "";
    private String currentPublicationKey = "";
    private String currentAuthorName = "";

    private boolean publication = false;
    private boolean author = false;
    private boolean title = false;
    private boolean year = false;
    private boolean journal =false;
    private boolean booktitle =false;
    private boolean titleModifier = false;

    @Override
    public void startElement(String uri, String eleName, String raw, Attributes attributes) throws SAXException {
        count++;
        if (publicationType.contains(raw)) {
            publication = true;
            currentAuthorSet = new HashSet<Author>();
            currentPublication = new Publication();
            currentAuthoredSet = new HashSet<Authored>();
            String pubKey = attributes.getValue("key");
            String mdate = attributes.getValue("mdate");
            currentPublicationKey = pubKey;
            currentPublication.setType(raw);
            currentPublication.setPubKey(pubKey);
            currentPublication.setMdate(mdate);
        } else if (publication) {
            if (raw.equalsIgnoreCase("author")) {
                author = true;
            } else if (raw.equalsIgnoreCase("title")) {
                title = true;
            } else if (raw.equalsIgnoreCase("year")) {
                year = true;
            } else if (raw.equalsIgnoreCase("journal")) {
                journal = true;
            } else if (raw.equalsIgnoreCase("booktitle")) {
                booktitle = true;
            } else if (titleModifiers.contains(raw)) {
                titleModifier = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length);
        if (author) {
            currentAuthorName += string;
        } else if (title) {
            currentTitle += string;
        } else if (year) {
            currentPublication.setYear(string);
            year = false;
        } else if (journal) {
            currentPublication.setJournal(string);
            journal =false;
        } else if (booktitle) {
            currentPublication.setBooktitle(string);
            booktitle = false;
        } else if (titleModifier) {
            currentTitle += string;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (count == 2 && publication) {
            if (currentAuthoredSet.isEmpty()) {
                Authored tempAuthored = new Authored();
                tempAuthored.setAuthorName("N/A");
                tempAuthored.setPublicationKey(currentPublicationKey);
                currentAuthoredSet.add(tempAuthored);
            }
            authorSet.addAll(currentAuthorSet);
            authoredSet.addAll(currentAuthoredSet);
            publicationList.add(currentPublication);
            currentPublicationKey = "";
            publication = false;
        } else if (count == 4 && titleModifier) {
            titleModifier = false;
        } else if (count == 3 && title) {
            currentPublication.setTitle(currentTitle);
            currentTitle = "";
            title = false;
        } else if (count == 3 && author) {
            Author tempAuthor = new Author();
            tempAuthor.setName(currentAuthorName);
            currentAuthorSet.add(tempAuthor);
            Authored tempAuthored = new Authored();
            tempAuthored.setAuthorName(currentAuthorName);
            tempAuthored.setPublicationKey(currentPublicationKey);
            currentAuthoredSet.add(tempAuthored);
            currentAuthorName = "";
            author = false;
        }
        count--;
    }

    @Override
    public void endDocument() {
        try {
            CSVWriter.writeToCSV(authorSet, authoredSet, publicationList);
        } catch(IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Can't write to csv");
        }
    }



}
