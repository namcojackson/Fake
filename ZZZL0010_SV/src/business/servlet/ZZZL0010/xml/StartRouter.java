package business.servlet.ZZZL0010.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>StartRouter</b> is generated from s21router.rng by Relaxer.
 * This class is derived from: <!-- for programmer <element
 * name="router"> <element name="ezd-application"> <oneOrMore>
 * <element name="ear"> <attribute name="context-root"/> <optional>
 * <attribute name="desc"/> </optional> <attribute name="id"> <data
 * type="NCName"/> </attribute> <attribute name="url"> <data
 * type="anyURI"/> </attribute> <element name="application">
 * <zeroOrMore> <element name="subsystem"> <optional> <attribute
 * name="desc"/> </optional> <attribute name="id"> <data
 * type="NCName"/> </attribute> </element> </zeroOrMore> <zeroOrMore>
 * <element name="business"> <optional> <attribute name="desc"/>
 * </optional> <attribute name="id"> <data type="NCName"/>
 * </attribute> </element> </zeroOrMore> </element> <element
 * name="replication"> <zeroOrMore> <element name="session">
 * <optional> <attribute name="desc"/> </optional> <attribute
 * name="name"/> </element> </zeroOrMore> <zeroOrMore> <element
 * name="parameter"> <optional> <attribute name="desc"/> </optional>
 * <attribute name="name"/> </element> </zeroOrMore> </element>
 * </element> </oneOrMore> </element> <element
 * name="nonezd-application"> <zeroOrMore> <element name="site">
 * <optional> <attribute name="desc"/> </optional> <attribute
 * name="id"> <data type="NCName"/> </attribute> <attribute
 * name="url"> <data type="anyURI"/> </attribute> <zeroOrMore>
 * <element name="parameter"> <optional> <attribute name="desc"/>
 * </optional> <attribute name="name"/> </element> </zeroOrMore>
 * </element> </zeroOrMore> </element> </element>--> <!-- for javadoc
 * -->
 * 
 * <pre> &lt;element name="router"&gt;
 * 
 *       &lt;element name="ezd-application"&gt;
 *         &lt;oneOrMore&gt;
 *           &lt;element name="ear"&gt;
 *             &lt;attribute name="context-root"/&gt;
 *             &lt;optional&gt;
 *               &lt;attribute name="desc"/&gt;
 *             &lt;/optional&gt;
 *             &lt;attribute name="id"&gt;
 *               &lt;data type="NCName"/&gt;
 *             &lt;/attribute&gt;
 *             &lt;attribute name="url"&gt;
 *               &lt;data type="anyURI"/&gt;
 *             &lt;/attribute&gt;
 *             &lt;element name="application"&gt;
 *               &lt;zeroOrMore&gt;
 *                 &lt;element name="subsystem"&gt;
 *                   &lt;optional&gt;
 *                     &lt;attribute name="desc"/&gt;
 *                   &lt;/optional&gt;
 *                   &lt;attribute name="id"&gt;
 *                     &lt;data type="NCName"/&gt;
 *                   &lt;/attribute&gt;
 *                 &lt;/element&gt;
 *               &lt;/zeroOrMore&gt;
 *               &lt;zeroOrMore&gt;
 *                 &lt;element name="business"&gt;
 *                   &lt;optional&gt;
 *                     &lt;attribute name="desc"/&gt;
 *                   &lt;/optional&gt;
 *                   &lt;attribute name="id"&gt;
 *                     &lt;data type="NCName"/&gt;
 *                   &lt;/attribute&gt;
 *                 &lt;/element&gt;
 *               &lt;/zeroOrMore&gt;
 *             &lt;/element&gt;
 *             &lt;element name="replication"&gt;
 *               &lt;zeroOrMore&gt;
 *                 &lt;element name="session"&gt;
 *                   &lt;optional&gt;
 *                     &lt;attribute name="desc"/&gt;
 *                   &lt;/optional&gt;
 *                   &lt;attribute name="name"/&gt;
 *                 &lt;/element&gt;
 *               &lt;/zeroOrMore&gt;
 *               &lt;zeroOrMore&gt;
 *                 &lt;element name="parameter"&gt;
 *                   &lt;optional&gt;
 *                     &lt;attribute name="desc"/&gt;
 *                   &lt;/optional&gt;
 *                   &lt;attribute name="name"/&gt;
 *                 &lt;/element&gt;
 *               &lt;/zeroOrMore&gt;
 *             &lt;/element&gt;
 *           &lt;/element&gt;
 *         &lt;/oneOrMore&gt;
 *       &lt;/element&gt;
 * 
 *       &lt;element name="nonezd-application"&gt;
 *         &lt;zeroOrMore&gt;
 *           &lt;element name="site"&gt;
 *             &lt;optional&gt;
 *               &lt;attribute name="desc"/&gt;
 *             &lt;/optional&gt;
 *             &lt;attribute name="id"&gt;
 *               &lt;data type="NCName"/&gt;
 *             &lt;/attribute&gt;
 *             &lt;attribute name="url"&gt;
 *               &lt;data type="anyURI"/&gt;
 *             &lt;/attribute&gt;
 *             &lt;zeroOrMore&gt;
 *               &lt;element name="parameter"&gt;
 *                 &lt;optional&gt;
 *                   &lt;attribute name="desc"/&gt;
 *                 &lt;/optional&gt;
 *                 &lt;attribute name="name"/&gt;
 *               &lt;/element&gt;
 *             &lt;/zeroOrMore&gt;
 *           &lt;/element&gt;
 *         &lt;/zeroOrMore&gt;
 *       &lt;/element&gt;
 *     &lt;/element&gt;</pre>
 * 
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouter implements java.io.Serializable, Cloneable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1816100236867288228L;

    private StartRouterEzdApplication startRouterEzdApplication_;

    private StartRouterNonezdApplication startRouterNonezdApplication_;

    private Element xmlElement;

    /**
     * Creates a <code>StartRouter</code>.
     */
    public StartRouter() {
    }

    /**
     * Creates a <code>StartRouter</code>.
     * @param source
     */
    public StartRouter(StartRouter source) {
        setup(source);
    }

    /**
     * Creates a <code>StartRouter</code> by the Stack
     * <code>stack</code> that contains Elements. This constructor
     * is supposed to be used internally by the Relaxer system.
     * @param stack
     */
    public StartRouter(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>StartRouter</code> by the Document
     * <code>doc</code>.
     * @param doc
     */
    public StartRouter(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>StartRouter</code> by the Element
     * <code>element</code>.
     * @param element
     */
    public StartRouter(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>StartRouter</code> by the File
     * <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouter(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>StartRouter</code> by the String
     * representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouter(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>StartRouter</code> by the URL
     * <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouter(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>StartRouter</code> by the InputStream
     * <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouter(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>StartRouter</code> by the InputSource
     * <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouter(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>StartRouter</code> by the Reader
     * <code>reader</code>.
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouter(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>StartRouter</code> by the StartRouter
     * <code>source</code>.
     * @param source
     */
    public void setup(StartRouter source) {
        if (source.startRouterEzdApplication_ != null) {
            setStartRouterEzdApplication((StartRouterEzdApplication) source.getStartRouterEzdApplication().clone());
        }
        if (source.startRouterNonezdApplication_ != null) {
            setStartRouterNonezdApplication((StartRouterNonezdApplication) source.getStartRouterNonezdApplication().clone());
        }
    }

    /**
     * Initializes the <code>StartRouter</code> by the Document
     * <code>doc</code>.
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>StartRouter</code> by the Element
     * <code>element</code>.
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>StartRouter</code> by the Stack
     * <code>stack</code> that contains Elements. This constructor
     * is supposed to be used internally by the Relaxer system.
     * @param stack
     */
    public void setup(RStack stack) {
        init(stack.popElement());
    }

    /**
     * @param element
     */
    private void init(Element element) {
        xmlElement = element;
        RStack stack = new RStack(element);
        setStartRouterEzdApplication(new StartRouterEzdApplication(stack));
        setStartRouterNonezdApplication(new StartRouterNonezdApplication(stack));
    }

    /**
     * @return Object
     */
    protected Object clone() {
        return (new StartRouter((StartRouter) this));
    }

    /**
     * Creates a DOM representation of the object. Result is appended
     * to the Node <code>parent</code>.
     * @param parent
     */
    public void makeElement(Node parent) {
        Document doc;
        if (parent instanceof Document) {
            doc = (Document) parent;
        } else {
            doc = parent.getOwnerDocument();
        }
        Element element = doc.createElement("router");
        this.startRouterEzdApplication_.makeElement(element);
        this.startRouterNonezdApplication_.makeElement(element);
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>StartRouter</code> by the File
     * <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file.toURL());
    }

    /**
     * Initializes the <code>StartRouter</code> by the String
     * representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouter</code> by the URL
     * <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouter</code> by the InputStream
     * <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouter</code> by the InputSource
     * <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouter</code> by the Reader
     * <code>reader</code>.
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(reader, UJAXP.FLAG_NONE));
    }

    /**
     * Creates a DOM document representation of the object.
     * @exception ParserConfigurationException
     * @return Document
     */
    public Document makeDocument() throws ParserConfigurationException {
        Document doc = UJAXP.makeDocument();
        makeElement(doc);
        return (doc);
    }

    /**
     * Gets the StartRouterEzdApplication property
     * <b>startRouterEzdApplication</b>.
     * @return StartRouterEzdApplication
     */
    public StartRouterEzdApplication getStartRouterEzdApplication() {
        return (startRouterEzdApplication_);
    }

    /**
     * Sets the StartRouterEzdApplication property
     * <b>startRouterEzdApplication</b>.
     * @param startRouterEzdApplication
     */
    public void setStartRouterEzdApplication(StartRouterEzdApplication startRouterEzdApplication) {
        this.startRouterEzdApplication_ = startRouterEzdApplication;
    }

    /**
     * Gets the StartRouterNonezdApplication property
     * <b>startRouterNonezdApplication</b>.
     * @return StartRouterNonezdApplication
     */
    public StartRouterNonezdApplication getStartRouterNonezdApplication() {
        return (startRouterNonezdApplication_);
    }

    /**
     * Sets the StartRouterNonezdApplication property
     * <b>startRouterNonezdApplication</b>.
     * @param startRouterNonezdApplication
     */
    public void setStartRouterNonezdApplication(StartRouterNonezdApplication startRouterNonezdApplication) {
        this.startRouterNonezdApplication_ = startRouterNonezdApplication;
    }

    /**
     * Gets the element to be used in the object construction.
     * @return Element
     */
    public Element rGetElement() {
        return (xmlElement);
    }

    /**
     * Makes an XML text representation.
     * @return String
     */
    public String makeTextDocument() {
        StringBuilder buffer = new StringBuilder();
        makeTextElement(buffer);
        return (buffer.toString());
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(StringBuilder buffer) {
        buffer.append("<router");
        buffer.append(">");
        startRouterEzdApplication_.makeTextElement(buffer);
        startRouterNonezdApplication_.makeTextElement(buffer);
        buffer.append("</router>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        buffer.write("<router");
        buffer.write(">");
        startRouterEzdApplication_.makeTextElement(buffer);
        startRouterNonezdApplication_.makeTextElement(buffer);
        buffer.write("</router>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        buffer.print("<router");
        buffer.print(">");
        startRouterEzdApplication_.makeTextElement(buffer);
        startRouterNonezdApplication_.makeTextElement(buffer);
        buffer.print("</router>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextAttribute(StringBuffer buffer) {
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer) throws IOException {
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
    }

    /**
     * Returns a String representation of this object. While this
     * method informs as XML format representaion, it's purpose is
     * just information, not making a rigid XML documentation.
     * @return String
     */
    public String toString() {
        try {
            return (makeTextDocument());
        } catch (Exception e) {
            return (super.toString());
        }
    }

    /**
     * Tests if a Element <code>element</code> is valid for the
     * <code>StartRouter</code>.
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "router")) {
            return (false);
        }
        RStack target = new RStack(element);
        // boolean $match$ = false;
        // Element child;
        if (!StartRouterEzdApplication.isMatchHungry(target)) {
            return (false);
        }
        // $match$ = true;
        if (!StartRouterNonezdApplication.isMatchHungry(target)) {
            return (false);
        }
        // $match$ = true;
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code> is
     * valid for the <code>StartRouter</code>. This mehtod is
     * supposed to be used internally by the Relaxer system.
     * @param stack
     * @return boolean
     */
    public static boolean isMatch(RStack stack) {
        Element element = stack.peekElement();
        if (element == null) {
            return (false);
        }
        return (isMatch(element));
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code> is
     * valid for the <code>StartRouter</code>. This method consumes
     * the stack contents during matching operation. This mehtod is
     * supposed to be used internally by the Relaxer system.
     * @param stack
     * @return boolean
     */
    public static boolean isMatchHungry(RStack stack) {
        Element element = stack.peekElement();
        if (element == null) {
            return (false);
        }
        if (isMatch(element)) {
            stack.popElement();
            return (true);
        } else {
            return (false);
        }
    }
}
