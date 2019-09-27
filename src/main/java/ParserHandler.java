import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.*;

/**
 * Created by jodiakyulas on 6/9/19.
 */


public class ParserHandler extends DefaultHandler{

    private int level = 0;
    private int count = 0;

    private List<String> publicationType = Arrays.asList("incollection", "book", "inproceedings", "article");
    private List<String> titleModifiers = Arrays.asList("tt", "sub", "i", "sup");

    private Set<String> authorSet = new HashSet<String>();
    private List<Publication> publicationList = new ArrayList<Publication>();
    private Set<Authored> authoredSet = new HashSet<Authored>();

//    private Set<Author> currentAuthorSet = new HashSet<Author>();
    private Publication currentPublication = new Publication();
    private Set<Authored> currentAuthoredSet = new HashSet<Authored>();

    private String currentTitle = "";
    private String currentPublicationKey = "";
    private String currentAuthorName = "";
    private String currentYear = "";
    private String currentBookTitle = "";
    private String currentJournal = "";

    private boolean publication = false;
    private boolean author = false;
    private boolean title = false;
    private boolean year = false;
    private boolean journal =false;
    private boolean booktitle =false;
    private boolean titleModifier = false;

    @Override
    public void startDocument() throws SAXException {
        try {
            CSVWriter.createNewCSV();
        } catch(IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Can't create new csv");
        }
    }

    @Override
    public void startElement(String uri, String eleName, String raw, Attributes attributes) throws SAXException {
        level++;
        if (publicationType.contains(raw)) {
            count++;
            if (count % 100 == 0) {
                try {
                    writeToCSV(new HashSet<String>(), authoredSet, publicationList);
//                    authorSet.clear();
                    authoredSet.clear();
                    publicationList = new ArrayList<Publication>();
                } catch(IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("Can't write to csv");
                }
            }
            publication = true;
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
            currentYear += string;
        } else if (journal) {
            currentJournal += string;
        } else if (booktitle) {
            currentBookTitle += string;
        } else if (titleModifier) {
            currentTitle += string;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (level == 2 && publication) {
            if (currentAuthoredSet.isEmpty()) {
                Authored tempAuthored = new Authored();
                tempAuthored.setAuthorName("N/A");
                tempAuthored.setPublicationKey(currentPublicationKey);
                currentAuthoredSet.add(tempAuthored);
            }
//            authorSet.addAll(currentAuthorSet);
            authoredSet.addAll(currentAuthoredSet);
            publicationList.add(currentPublication);
//            currentAuthorSet.clear();
            currentAuthoredSet.clear();
            currentPublication = new Publication();
            currentPublicationKey = "";
            publication = false;
        } else if (level == 4 && titleModifier) {
            titleModifier = false;
        } else if (level == 3 && title) {
            currentPublication.setTitle(currentTitle);
            currentTitle = "";
            title = false;
        } else if (level == 3 && author) {
            authorSet.add(currentAuthorName);
            Authored tempAuthored = new Authored();
            tempAuthored.setAuthorName(currentAuthorName);
            tempAuthored.setPublicationKey(currentPublicationKey);
            currentAuthoredSet.add(tempAuthored);
            currentAuthorName = "";
            author = false;
        } else if (level == 3 && year) {
            currentPublication.setYear(currentYear);
            currentYear = "";
            year = false;
        } else if (level == 3 && journal) {
            currentPublication.setJournal(currentJournal);
            currentJournal = "";
            journal =false;
        } else if (level == 3 && booktitle) {
            currentPublication.setBooktitle(currentBookTitle);
            currentBookTitle = "";
            booktitle = false;
        }
        level--;
    }

    @Override
    public void endDocument() {
        try {
            writeToCSV(authorSet, authoredSet, publicationList);
        } catch(IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Can't write to csv");
        }
    }

    private void writeToCSV(Set<String> authorSet, Set<Authored> authoredSet, List<Publication> publicationList) throws IOException {
        CSVWriter.writeToCSV(authorSet, authoredSet, publicationList);
    }



}
