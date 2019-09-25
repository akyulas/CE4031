/**
 * Created by jodiakyulas on 4/9/19.
 */
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

import static com.sun.org.apache.xerces.internal.impl.Constants.JDK_GENERAL_ENTITY_SIZE_LIMIT;
import static com.sun.org.apache.xerces.internal.impl.Constants.JDK_TOTAL_ENTITY_SIZE_LIMIT;

public class Parser {

    private SAXParser saxParser;
    private ParserHandler parserHandler;

    public Parser() throws ParserConfigurationException, SAXException {
        parserHandler = new ParserHandler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setValidating(true);
        saxParser = saxParserFactory.newSAXParser();
        saxParser.setProperty(JDK_TOTAL_ENTITY_SIZE_LIMIT, "0");
    }

    public void parse(String uri) throws ParserConfigurationException, SAXException, IOException {
        saxParser.parse(uri, parserHandler);
    }
}
