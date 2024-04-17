/*
 * The Relaxer artifact Copyright (c) 2000-2004, ASAMI Tomoharu, All
 * rights reserved. Permission is hereby granted, free of charge, to
 * any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions: - Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package business.servlet.ZZZL0010.xml;

import java.io.*;
import java.net.URL;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <b>StartRouterEzdApplication</b> is generated from s21router.rng
 * by Relaxer. This class is derived from: <!-- for programmer
 * <element name="ezd-application"> <oneOrMore> <element name="ear">
 * <attribute name="context-root"/> <optional> <attribute
 * name="desc"/> </optional> <attribute name="id"> <data
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
 * </element> </oneOrMore> </element>--> <!-- for javadoc -->
 * 
 * <pre> &lt;element name="ezd-application"&gt;
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
 *       &lt;/element&gt;</pre>
 * 
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterEzdApplication implements java.io.Serializable, Cloneable {
    // List<StartRouterEzdApplicationEar>
    private java.util.List startRouterEzdApplicationEar_ = new java.util.ArrayList();

    private Element xmlElement;

    /**
     * Creates a <code>StartRouterEzdApplication</code>.
     */
    public StartRouterEzdApplication() {
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code>.
     * @param source
     */
    public StartRouterEzdApplication(StartRouterEzdApplication source) {
        setup(source);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the Stack
     * <code>stack</code> that contains Elements. This constructor
     * is supposed to be used internally by the Relaxer system.
     * @param stack
     */
    public StartRouterEzdApplication(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the
     * Document <code>doc</code>.
     * @param doc
     */
    public StartRouterEzdApplication(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the
     * Element <code>element</code>.
     * @param element
     */
    public StartRouterEzdApplication(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the File
     * <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplication(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the
     * String representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplication(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the URL
     * <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplication(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the
     * InputStream <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplication(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the
     * InputSource <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplication(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>StartRouterEzdApplication</code> by the
     * Reader <code>reader</code>.
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplication(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * StartRouterEzdApplication <code>source</code>.
     * @param source
     */
    public void setup(StartRouterEzdApplication source) {
        int size;
        this.startRouterEzdApplicationEar_.clear();
        size = source.startRouterEzdApplicationEar_.size();
        for (int i = 0; i < size; i++) {
            addStartRouterEzdApplicationEar((StartRouterEzdApplicationEar) source.getStartRouterEzdApplicationEar(i).clone());
        }
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * Document <code>doc</code>.
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * Element <code>element</code>.
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * Stack <code>stack</code> that contains Elements. This
     * constructor is supposed to be used internally by the Relaxer
     * system.
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
        startRouterEzdApplicationEar_.clear();
        while (true) {
            if (StartRouterEzdApplicationEar.isMatch(stack)) {
                addStartRouterEzdApplicationEar(new StartRouterEzdApplicationEar(stack));
            } else {
                break;
            }
        }
    }

    /**
     * @return Object
     */
    public Object clone() {

        try {
            StartRouterEzdApplication ctx = (StartRouterEzdApplication) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }
        // return (new
        // StartRouterEzdApplication((StartRouterEzdApplication)
        // this));
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
        Element element = doc.createElement("ezd-application");
        int size;
        size = this.startRouterEzdApplicationEar_.size();
        for (int i = 0; i < size; i++) {
            StartRouterEzdApplicationEar value = (StartRouterEzdApplicationEar) this.startRouterEzdApplicationEar_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * File <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file.toURL());
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * String representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * URL <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * InputStream <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * InputSource <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplication</code> by the
     * Reader <code>reader</code>.
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
     * Gets the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     * @return StartRouterEzdApplicationEar[]
     */
    public StartRouterEzdApplicationEar[] getStartRouterEzdApplicationEar() {
        StartRouterEzdApplicationEar[] array = new StartRouterEzdApplicationEar[startRouterEzdApplicationEar_.size()];
        return ((StartRouterEzdApplicationEar[]) startRouterEzdApplicationEar_.toArray(array));
    }

    /**
     * Sets the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     * @param startRouterEzdApplicationEar
     */
    public void setStartRouterEzdApplicationEar(StartRouterEzdApplicationEar[] startRouterEzdApplicationEar) {
        this.startRouterEzdApplicationEar_.clear();
        for (int i = 0; i < startRouterEzdApplicationEar.length; i++) {
            addStartRouterEzdApplicationEar(startRouterEzdApplicationEar[i]);
        }
    }

    /**
     * Sets the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     * @param startRouterEzdApplicationEar
     */
    public void setStartRouterEzdApplicationEar(StartRouterEzdApplicationEar startRouterEzdApplicationEar) {
        this.startRouterEzdApplicationEar_.clear();
        addStartRouterEzdApplicationEar(startRouterEzdApplicationEar);
    }

    /**
     * Adds the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     * @param startRouterEzdApplicationEar
     */
    public void addStartRouterEzdApplicationEar(StartRouterEzdApplicationEar startRouterEzdApplicationEar) {
        this.startRouterEzdApplicationEar_.add(startRouterEzdApplicationEar);
    }

    /**
     * Adds the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     * @param startRouterEzdApplicationEar
     */
    public void addStartRouterEzdApplicationEar(StartRouterEzdApplicationEar[] startRouterEzdApplicationEar) {
        for (int i = 0; i < startRouterEzdApplicationEar.length; i++) {
            addStartRouterEzdApplicationEar(startRouterEzdApplicationEar[i]);
        }
    }

    /**
     * Gets number of the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     * @return int
     */
    public int sizeStartRouterEzdApplicationEar() {
        return (startRouterEzdApplicationEar_.size());
    }

    /**
     * Gets the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b> by index.
     * @param index
     * @return StartRouterEzdApplicationEar
     */
    public StartRouterEzdApplicationEar getStartRouterEzdApplicationEar(int index) {
        return ((StartRouterEzdApplicationEar) startRouterEzdApplicationEar_.get(index));
    }

    /**
     * Sets the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b> by index.
     * @param index
     * @param startRouterEzdApplicationEar
     */
    public void setStartRouterEzdApplicationEar(int index, StartRouterEzdApplicationEar startRouterEzdApplicationEar) {
        this.startRouterEzdApplicationEar_.set(index, startRouterEzdApplicationEar);
    }

    /**
     * Adds the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b> by index.
     * @param index
     * @param startRouterEzdApplicationEar
     */
    public void addStartRouterEzdApplicationEar(int index, StartRouterEzdApplicationEar startRouterEzdApplicationEar) {
        this.startRouterEzdApplicationEar_.add(index, startRouterEzdApplicationEar);
    }

    /**
     * Remove the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b> by index.
     * @param index
     */
    public void removeStartRouterEzdApplicationEar(int index) {
        this.startRouterEzdApplicationEar_.remove(index);
    }

    /**
     * Remove the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b> by object.
     * @param startRouterEzdApplicationEar
     */
    public void removeStartRouterEzdApplicationEar(StartRouterEzdApplicationEar startRouterEzdApplicationEar) {
        this.startRouterEzdApplicationEar_.remove(startRouterEzdApplicationEar);
    }

    /**
     * Clear the StartRouterEzdApplicationEar property
     * <b>startRouterEzdApplicationEar</b>.
     */
    public void clearStartRouterEzdApplicationEar() {
        this.startRouterEzdApplicationEar_.clear();
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
        return buffer.toString();
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(StringBuilder buffer) {
        int size;
        buffer.append("<ezd-application");
        buffer.append(">");
        size = this.startRouterEzdApplicationEar_.size();
        for (int i = 0; i < size; i++) {
            StartRouterEzdApplicationEar value = (StartRouterEzdApplicationEar) this.startRouterEzdApplicationEar_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</ezd-application>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<ezd-application");
        buffer.write(">");
        size = this.startRouterEzdApplicationEar_.size();
        for (int i = 0; i < size; i++) {
            StartRouterEzdApplicationEar value = (StartRouterEzdApplicationEar) this.startRouterEzdApplicationEar_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</ezd-application>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<ezd-application");
        buffer.print(">");
        size = this.startRouterEzdApplicationEar_.size();
        for (int i = 0; i < size; i++) {
            StartRouterEzdApplicationEar value = (StartRouterEzdApplicationEar) this.startRouterEzdApplicationEar_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</ezd-application>");
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
     * <code>StartRouterEzdApplication</code>.
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "ezd-application")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (!StartRouterEzdApplicationEar.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        while (true) {
            if (!StartRouterEzdApplicationEar.isMatchHungry(target)) {
                break;
            }
            $match$ = true;
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code> is
     * valid for the <code>StartRouterEzdApplication</code>. This
     * mehtod is supposed to be used internally by the Relaxer system.
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
     * valid for the <code>StartRouterEzdApplication</code>. This
     * method consumes the stack contents during matching operation.
     * This mehtod is supposed to be used internally by the Relaxer
     * system.
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
