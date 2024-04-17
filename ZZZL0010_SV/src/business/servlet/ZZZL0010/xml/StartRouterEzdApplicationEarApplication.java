/*
 * The Relaxer artifact
 * Copyright (c) 2000-2004, ASAMI Tomoharu, All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer. 
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package business.servlet.ZZZL0010.xml;

import java.io.*;
import java.net.URL;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * <b>StartRouterEzdApplicationEarApplication</b> is generated from s21router.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="application">
 *               <zeroOrMore>
 *                 <element name="subsystem">
 *                   <optional>
 *                     <attribute name="desc"/>
 *                   </optional>
 *                   <attribute name="id">
 *                     <data type="NCName"/>
 *                   </attribute>
 *                 </element>
 *               </zeroOrMore>
 *               <zeroOrMore>
 *                 <element name="business">
 *                   <optional>
 *                     <attribute name="desc"/>
 *                   </optional>
 *                   <attribute name="id">
 *                     <data type="NCName"/>
 *                   </attribute>
 *                 </element>
 *               </zeroOrMore>
 *             </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="application"&gt;
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
 *             &lt;/element&gt;</pre>
 *
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterEzdApplicationEarApplication implements java.io.Serializable, Cloneable {
    // List<StartRouterEzdApplicationEarApplicationSubsystem>
    private java.util.List startRouterEzdApplicationEarApplicationSubsystem_ = new java.util.ArrayList();
    // List<StartRouterEzdApplicationEarApplicationBusiness>
    private java.util.List startRouterEzdApplicationEarApplicationBusiness_ = new java.util.ArrayList();
    private Element xmlElement;

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code>.
     *
     */
    public StartRouterEzdApplicationEarApplication() {
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code>.
     *
     * @param source
     */
    public StartRouterEzdApplicationEarApplication(StartRouterEzdApplicationEarApplication source) {
        setup(source);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public StartRouterEzdApplicationEarApplication(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public StartRouterEzdApplicationEarApplication(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public StartRouterEzdApplicationEarApplication(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplication(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplication(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplication(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplication(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplication(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarApplication</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplication(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the StartRouterEzdApplicationEarApplication <code>source</code>.
     *
     * @param source
     */
    public void setup(StartRouterEzdApplicationEarApplication source) {
        int size;
        this.startRouterEzdApplicationEarApplicationSubsystem_.clear();
        size = source.startRouterEzdApplicationEarApplicationSubsystem_.size();
        for (int i = 0;i < size;i++) {
            addStartRouterEzdApplicationEarApplicationSubsystem((StartRouterEzdApplicationEarApplicationSubsystem)source.getStartRouterEzdApplicationEarApplicationSubsystem(i).clone());
        }
        this.startRouterEzdApplicationEarApplicationBusiness_.clear();
        size = source.startRouterEzdApplicationEarApplicationBusiness_.size();
        for (int i = 0;i < size;i++) {
            addStartRouterEzdApplicationEarApplicationBusiness((StartRouterEzdApplicationEarApplicationBusiness)source.getStartRouterEzdApplicationEarApplicationBusiness(i).clone());
        }
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
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
        startRouterEzdApplicationEarApplicationSubsystem_.clear();
        while (true) {
            if (StartRouterEzdApplicationEarApplicationSubsystem.isMatch(stack)) {
                addStartRouterEzdApplicationEarApplicationSubsystem(new StartRouterEzdApplicationEarApplicationSubsystem(stack));
            } else {
                break;
            }
        }
        startRouterEzdApplicationEarApplicationBusiness_.clear();
        while (true) {
            if (StartRouterEzdApplicationEarApplicationBusiness.isMatch(stack)) {
                addStartRouterEzdApplicationEarApplicationBusiness(new StartRouterEzdApplicationEarApplicationBusiness(stack));
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
            StartRouterEzdApplicationEarApplication ctx = (StartRouterEzdApplicationEarApplication) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }
        
        //return (new StartRouterEzdApplicationEarApplication((StartRouterEzdApplicationEarApplication)this));
    }

    /**
     * Creates a DOM representation of the object.
     * Result is appended to the Node <code>parent</code>.
     *
     * @param parent
     */
    public void makeElement(Node parent) {
        Document doc;
        if (parent instanceof Document) {
            doc = (Document)parent;
        } else {
            doc = parent.getOwnerDocument();
        }
        Element element = doc.createElement("application");
        int size;
        size = this.startRouterEzdApplicationEarApplicationSubsystem_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationSubsystem value = (StartRouterEzdApplicationEarApplicationSubsystem)this.startRouterEzdApplicationEarApplicationSubsystem_.get(i);
            value.makeElement(element);
        }
        size = this.startRouterEzdApplicationEarApplicationBusiness_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationBusiness value = (StartRouterEzdApplicationEarApplicationBusiness)this.startRouterEzdApplicationEarApplicationBusiness_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file.toURL());
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarApplication</code> by the Reader <code>reader</code>.
     *
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
     *
     * @exception ParserConfigurationException
     * @return Document
     */
    public Document makeDocument() throws ParserConfigurationException {
        Document doc = UJAXP.makeDocument();
        makeElement(doc);
        return (doc);
    }

    /**
     * Gets the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     * @return StartRouterEzdApplicationEarApplicationSubsystem[]
     */
    public StartRouterEzdApplicationEarApplicationSubsystem[] getStartRouterEzdApplicationEarApplicationSubsystem() {
        StartRouterEzdApplicationEarApplicationSubsystem[] array = new StartRouterEzdApplicationEarApplicationSubsystem[startRouterEzdApplicationEarApplicationSubsystem_.size()];
        return ((StartRouterEzdApplicationEarApplicationSubsystem[])startRouterEzdApplicationEarApplicationSubsystem_.toArray(array));
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void setStartRouterEzdApplicationEarApplicationSubsystem(StartRouterEzdApplicationEarApplicationSubsystem[] startRouterEzdApplicationEarApplicationSubsystem) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.clear();
        for (int i = 0;i < startRouterEzdApplicationEarApplicationSubsystem.length;i++) {
            addStartRouterEzdApplicationEarApplicationSubsystem(startRouterEzdApplicationEarApplicationSubsystem[i]);
        }
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void setStartRouterEzdApplicationEarApplicationSubsystem(StartRouterEzdApplicationEarApplicationSubsystem startRouterEzdApplicationEarApplicationSubsystem) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.clear();
        addStartRouterEzdApplicationEarApplicationSubsystem(startRouterEzdApplicationEarApplicationSubsystem);
    }

    /**
     * Adds the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void addStartRouterEzdApplicationEarApplicationSubsystem(StartRouterEzdApplicationEarApplicationSubsystem startRouterEzdApplicationEarApplicationSubsystem) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.add(startRouterEzdApplicationEarApplicationSubsystem);
    }

    /**
     * Adds the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void addStartRouterEzdApplicationEarApplicationSubsystem(StartRouterEzdApplicationEarApplicationSubsystem[] startRouterEzdApplicationEarApplicationSubsystem) {
        for (int i = 0;i < startRouterEzdApplicationEarApplicationSubsystem.length;i++) {
            addStartRouterEzdApplicationEarApplicationSubsystem(startRouterEzdApplicationEarApplicationSubsystem[i]);
        }
    }

    /**
     * Gets number of the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     * @return int
     */
    public int sizeStartRouterEzdApplicationEarApplicationSubsystem() {
        return (startRouterEzdApplicationEarApplicationSubsystem_.size());
    }

    /**
     * Gets the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b> by index.
     *
     * @param index
     * @return StartRouterEzdApplicationEarApplicationSubsystem
     */
    public StartRouterEzdApplicationEarApplicationSubsystem getStartRouterEzdApplicationEarApplicationSubsystem(int index) {
        return ((StartRouterEzdApplicationEarApplicationSubsystem)startRouterEzdApplicationEarApplicationSubsystem_.get(index));
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void setStartRouterEzdApplicationEarApplicationSubsystem(int index, StartRouterEzdApplicationEarApplicationSubsystem startRouterEzdApplicationEarApplicationSubsystem) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.set(index, startRouterEzdApplicationEarApplicationSubsystem);
    }

    /**
     * Adds the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void addStartRouterEzdApplicationEarApplicationSubsystem(int index, StartRouterEzdApplicationEarApplicationSubsystem startRouterEzdApplicationEarApplicationSubsystem) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.add(index, startRouterEzdApplicationEarApplicationSubsystem);
    }

    /**
     * Remove the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b> by index.
     *
     * @param index
     */
    public void removeStartRouterEzdApplicationEarApplicationSubsystem(int index) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.remove(index);
    }

    /**
     * Remove the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b> by object.
     *
     * @param startRouterEzdApplicationEarApplicationSubsystem
     */
    public void removeStartRouterEzdApplicationEarApplicationSubsystem(StartRouterEzdApplicationEarApplicationSubsystem startRouterEzdApplicationEarApplicationSubsystem) {
        this.startRouterEzdApplicationEarApplicationSubsystem_.remove(startRouterEzdApplicationEarApplicationSubsystem);
    }

    /**
     * Clear the StartRouterEzdApplicationEarApplicationSubsystem property <b>startRouterEzdApplicationEarApplicationSubsystem</b>.
     *
     */
    public void clearStartRouterEzdApplicationEarApplicationSubsystem() {
        this.startRouterEzdApplicationEarApplicationSubsystem_.clear();
    }

    /**
     * Gets the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     * @return StartRouterEzdApplicationEarApplicationBusiness[]
     */
    public StartRouterEzdApplicationEarApplicationBusiness[] getStartRouterEzdApplicationEarApplicationBusiness() {
        StartRouterEzdApplicationEarApplicationBusiness[] array = new StartRouterEzdApplicationEarApplicationBusiness[startRouterEzdApplicationEarApplicationBusiness_.size()];
        return ((StartRouterEzdApplicationEarApplicationBusiness[])startRouterEzdApplicationEarApplicationBusiness_.toArray(array));
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void setStartRouterEzdApplicationEarApplicationBusiness(StartRouterEzdApplicationEarApplicationBusiness[] startRouterEzdApplicationEarApplicationBusiness) {
        this.startRouterEzdApplicationEarApplicationBusiness_.clear();
        for (int i = 0;i < startRouterEzdApplicationEarApplicationBusiness.length;i++) {
            addStartRouterEzdApplicationEarApplicationBusiness(startRouterEzdApplicationEarApplicationBusiness[i]);
        }
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void setStartRouterEzdApplicationEarApplicationBusiness(StartRouterEzdApplicationEarApplicationBusiness startRouterEzdApplicationEarApplicationBusiness) {
        this.startRouterEzdApplicationEarApplicationBusiness_.clear();
        addStartRouterEzdApplicationEarApplicationBusiness(startRouterEzdApplicationEarApplicationBusiness);
    }

    /**
     * Adds the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void addStartRouterEzdApplicationEarApplicationBusiness(StartRouterEzdApplicationEarApplicationBusiness startRouterEzdApplicationEarApplicationBusiness) {
        this.startRouterEzdApplicationEarApplicationBusiness_.add(startRouterEzdApplicationEarApplicationBusiness);
    }

    /**
     * Adds the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void addStartRouterEzdApplicationEarApplicationBusiness(StartRouterEzdApplicationEarApplicationBusiness[] startRouterEzdApplicationEarApplicationBusiness) {
        for (int i = 0;i < startRouterEzdApplicationEarApplicationBusiness.length;i++) {
            addStartRouterEzdApplicationEarApplicationBusiness(startRouterEzdApplicationEarApplicationBusiness[i]);
        }
    }

    /**
     * Gets number of the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     * @return int
     */
    public int sizeStartRouterEzdApplicationEarApplicationBusiness() {
        return (startRouterEzdApplicationEarApplicationBusiness_.size());
    }

    /**
     * Gets the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b> by index.
     *
     * @param index
     * @return StartRouterEzdApplicationEarApplicationBusiness
     */
    public StartRouterEzdApplicationEarApplicationBusiness getStartRouterEzdApplicationEarApplicationBusiness(int index) {
        return ((StartRouterEzdApplicationEarApplicationBusiness)startRouterEzdApplicationEarApplicationBusiness_.get(index));
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void setStartRouterEzdApplicationEarApplicationBusiness(int index, StartRouterEzdApplicationEarApplicationBusiness startRouterEzdApplicationEarApplicationBusiness) {
        this.startRouterEzdApplicationEarApplicationBusiness_.set(index, startRouterEzdApplicationEarApplicationBusiness);
    }

    /**
     * Adds the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void addStartRouterEzdApplicationEarApplicationBusiness(int index, StartRouterEzdApplicationEarApplicationBusiness startRouterEzdApplicationEarApplicationBusiness) {
        this.startRouterEzdApplicationEarApplicationBusiness_.add(index, startRouterEzdApplicationEarApplicationBusiness);
    }

    /**
     * Remove the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b> by index.
     *
     * @param index
     */
    public void removeStartRouterEzdApplicationEarApplicationBusiness(int index) {
        this.startRouterEzdApplicationEarApplicationBusiness_.remove(index);
    }

    /**
     * Remove the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b> by object.
     *
     * @param startRouterEzdApplicationEarApplicationBusiness
     */
    public void removeStartRouterEzdApplicationEarApplicationBusiness(StartRouterEzdApplicationEarApplicationBusiness startRouterEzdApplicationEarApplicationBusiness) {
        this.startRouterEzdApplicationEarApplicationBusiness_.remove(startRouterEzdApplicationEarApplicationBusiness);
    }

    /**
     * Clear the StartRouterEzdApplicationEarApplicationBusiness property <b>startRouterEzdApplicationEarApplicationBusiness</b>.
     *
     */
    public void clearStartRouterEzdApplicationEarApplicationBusiness() {
        this.startRouterEzdApplicationEarApplicationBusiness_.clear();
    }

    /**
     * Gets the element to be used in the object construction.
     *
     * @return Element
     */
    public Element rGetElement() {
        return (xmlElement);
    }

    /**
     * Makes an XML text representation.
     *
     * @return String
     */
    public String makeTextDocument() {
        StringBuilder buffer = new StringBuilder();
        makeTextElement(buffer);
        return buffer.toString();
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuilder buffer) {
        int size;
        buffer.append("<application");
        buffer.append(">");
        size = this.startRouterEzdApplicationEarApplicationSubsystem_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationSubsystem value = (StartRouterEzdApplicationEarApplicationSubsystem)this.startRouterEzdApplicationEarApplicationSubsystem_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.startRouterEzdApplicationEarApplicationBusiness_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationBusiness value = (StartRouterEzdApplicationEarApplicationBusiness)this.startRouterEzdApplicationEarApplicationBusiness_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</application>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<application");
        buffer.write(">");
        size = this.startRouterEzdApplicationEarApplicationSubsystem_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationSubsystem value = (StartRouterEzdApplicationEarApplicationSubsystem)this.startRouterEzdApplicationEarApplicationSubsystem_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.startRouterEzdApplicationEarApplicationBusiness_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationBusiness value = (StartRouterEzdApplicationEarApplicationBusiness)this.startRouterEzdApplicationEarApplicationBusiness_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</application>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<application");
        buffer.print(">");
        size = this.startRouterEzdApplicationEarApplicationSubsystem_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationSubsystem value = (StartRouterEzdApplicationEarApplicationSubsystem)this.startRouterEzdApplicationEarApplicationSubsystem_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.startRouterEzdApplicationEarApplicationBusiness_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarApplicationBusiness value = (StartRouterEzdApplicationEarApplicationBusiness)this.startRouterEzdApplicationEarApplicationBusiness_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</application>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(StringBuffer buffer) {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer) throws IOException {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
    }

    /**
     * Returns a String representation of this object.
     * While this method informs as XML format representaion, 
     *  it's purpose is just information, not making 
     * a rigid XML documentation.
     *
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
     * Tests if a Element <code>element</code> is valid
     * for the <code>StartRouterEzdApplicationEarApplication</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "application")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        while (true) {
            if (!StartRouterEzdApplicationEarApplicationSubsystem.isMatchHungry(target)) {
                break;
            }
            $match$ = true;
        }
        while (true) {
            if (!StartRouterEzdApplicationEarApplicationBusiness.isMatchHungry(target)) {
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
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>StartRouterEzdApplicationEarApplication</code>.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
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
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>StartRouterEzdApplicationEarApplication</code>.
     * This method consumes the stack contents during matching operation.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
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
