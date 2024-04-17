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
 * <b>StartRouterEzdApplicationEarReplication</b> is generated from s21router.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="replication">
 *               <zeroOrMore>
 *                 <element name="session">
 *                   <optional>
 *                     <attribute name="desc"/>
 *                   </optional>
 *                   <attribute name="name"/>
 *                 </element>
 *               </zeroOrMore>
 *               <zeroOrMore>
 *                 <element name="parameter">
 *                   <optional>
 *                     <attribute name="desc"/>
 *                   </optional>
 *                   <attribute name="name"/>
 *                 </element>
 *               </zeroOrMore>
 *             </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="replication"&gt;
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
 *             &lt;/element&gt;</pre>
 *
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterEzdApplicationEarReplication implements java.io.Serializable, Cloneable {
    // List<StartRouterEzdApplicationEarReplicationSession>
    private java.util.List startRouterEzdApplicationEarReplicationSession_ = new java.util.ArrayList();
    // List<StartRouterEzdApplicationEarReplicationParameter>
    private java.util.List startRouterEzdApplicationEarReplicationParameter_ = new java.util.ArrayList();
    private Element xmlElement;

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code>.
     *
     */
    public StartRouterEzdApplicationEarReplication() {
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code>.
     *
     * @param source
     */
    public StartRouterEzdApplicationEarReplication(StartRouterEzdApplicationEarReplication source) {
        setup(source);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public StartRouterEzdApplicationEarReplication(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public StartRouterEzdApplicationEarReplication(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public StartRouterEzdApplicationEarReplication(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarReplication(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarReplication(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarReplication(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarReplication(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarReplication(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEarReplication</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarReplication(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the StartRouterEzdApplicationEarReplication <code>source</code>.
     *
     * @param source
     */
    public void setup(StartRouterEzdApplicationEarReplication source) {
        int size;
        this.startRouterEzdApplicationEarReplicationSession_.clear();
        size = source.startRouterEzdApplicationEarReplicationSession_.size();
        for (int i = 0;i < size;i++) {
            addStartRouterEzdApplicationEarReplicationSession((StartRouterEzdApplicationEarReplicationSession)source.getStartRouterEzdApplicationEarReplicationSession(i).clone());
        }
        this.startRouterEzdApplicationEarReplicationParameter_.clear();
        size = source.startRouterEzdApplicationEarReplicationParameter_.size();
        for (int i = 0;i < size;i++) {
            addStartRouterEzdApplicationEarReplicationParameter((StartRouterEzdApplicationEarReplicationParameter)source.getStartRouterEzdApplicationEarReplicationParameter(i).clone());
        }
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the Stack <code>stack</code>
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
        startRouterEzdApplicationEarReplicationSession_.clear();
        while (true) {
            if (StartRouterEzdApplicationEarReplicationSession.isMatch(stack)) {
                addStartRouterEzdApplicationEarReplicationSession(new StartRouterEzdApplicationEarReplicationSession(stack));
            } else {
                break;
            }
        }
        startRouterEzdApplicationEarReplicationParameter_.clear();
        while (true) {
            if (StartRouterEzdApplicationEarReplicationParameter.isMatch(stack)) {
                addStartRouterEzdApplicationEarReplicationParameter(new StartRouterEzdApplicationEarReplicationParameter(stack));
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
            StartRouterEzdApplicationEarReplication ctx = (StartRouterEzdApplicationEarReplication) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }
        
        //return (new StartRouterEzdApplicationEarReplication((StartRouterEzdApplicationEarReplication)this));
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
        Element element = doc.createElement("replication");
        int size;
        size = this.startRouterEzdApplicationEarReplicationSession_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationSession value = (StartRouterEzdApplicationEarReplicationSession)this.startRouterEzdApplicationEarReplicationSession_.get(i);
            value.makeElement(element);
        }
        size = this.startRouterEzdApplicationEarReplicationParameter_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationParameter value = (StartRouterEzdApplicationEarReplicationParameter)this.startRouterEzdApplicationEarReplicationParameter_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the File <code>file</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code>
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
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the URL <code>url</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEarReplication</code> by the Reader <code>reader</code>.
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
     * Gets the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     * @return StartRouterEzdApplicationEarReplicationSession[]
     */
    public StartRouterEzdApplicationEarReplicationSession[] getStartRouterEzdApplicationEarReplicationSession() {
        StartRouterEzdApplicationEarReplicationSession[] array = new StartRouterEzdApplicationEarReplicationSession[startRouterEzdApplicationEarReplicationSession_.size()];
        return ((StartRouterEzdApplicationEarReplicationSession[])startRouterEzdApplicationEarReplicationSession_.toArray(array));
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void setStartRouterEzdApplicationEarReplicationSession(StartRouterEzdApplicationEarReplicationSession[] startRouterEzdApplicationEarReplicationSession) {
        this.startRouterEzdApplicationEarReplicationSession_.clear();
        for (int i = 0;i < startRouterEzdApplicationEarReplicationSession.length;i++) {
            addStartRouterEzdApplicationEarReplicationSession(startRouterEzdApplicationEarReplicationSession[i]);
        }
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void setStartRouterEzdApplicationEarReplicationSession(StartRouterEzdApplicationEarReplicationSession startRouterEzdApplicationEarReplicationSession) {
        this.startRouterEzdApplicationEarReplicationSession_.clear();
        addStartRouterEzdApplicationEarReplicationSession(startRouterEzdApplicationEarReplicationSession);
    }

    /**
     * Adds the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void addStartRouterEzdApplicationEarReplicationSession(StartRouterEzdApplicationEarReplicationSession startRouterEzdApplicationEarReplicationSession) {
        this.startRouterEzdApplicationEarReplicationSession_.add(startRouterEzdApplicationEarReplicationSession);
    }

    /**
     * Adds the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void addStartRouterEzdApplicationEarReplicationSession(StartRouterEzdApplicationEarReplicationSession[] startRouterEzdApplicationEarReplicationSession) {
        for (int i = 0;i < startRouterEzdApplicationEarReplicationSession.length;i++) {
            addStartRouterEzdApplicationEarReplicationSession(startRouterEzdApplicationEarReplicationSession[i]);
        }
    }

    /**
     * Gets number of the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     * @return int
     */
    public int sizeStartRouterEzdApplicationEarReplicationSession() {
        return (startRouterEzdApplicationEarReplicationSession_.size());
    }

    /**
     * Gets the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b> by index.
     *
     * @param index
     * @return StartRouterEzdApplicationEarReplicationSession
     */
    public StartRouterEzdApplicationEarReplicationSession getStartRouterEzdApplicationEarReplicationSession(int index) {
        return ((StartRouterEzdApplicationEarReplicationSession)startRouterEzdApplicationEarReplicationSession_.get(index));
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void setStartRouterEzdApplicationEarReplicationSession(int index, StartRouterEzdApplicationEarReplicationSession startRouterEzdApplicationEarReplicationSession) {
        this.startRouterEzdApplicationEarReplicationSession_.set(index, startRouterEzdApplicationEarReplicationSession);
    }

    /**
     * Adds the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void addStartRouterEzdApplicationEarReplicationSession(int index, StartRouterEzdApplicationEarReplicationSession startRouterEzdApplicationEarReplicationSession) {
        this.startRouterEzdApplicationEarReplicationSession_.add(index, startRouterEzdApplicationEarReplicationSession);
    }

    /**
     * Remove the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b> by index.
     *
     * @param index
     */
    public void removeStartRouterEzdApplicationEarReplicationSession(int index) {
        this.startRouterEzdApplicationEarReplicationSession_.remove(index);
    }

    /**
     * Remove the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b> by object.
     *
     * @param startRouterEzdApplicationEarReplicationSession
     */
    public void removeStartRouterEzdApplicationEarReplicationSession(StartRouterEzdApplicationEarReplicationSession startRouterEzdApplicationEarReplicationSession) {
        this.startRouterEzdApplicationEarReplicationSession_.remove(startRouterEzdApplicationEarReplicationSession);
    }

    /**
     * Clear the StartRouterEzdApplicationEarReplicationSession property <b>startRouterEzdApplicationEarReplicationSession</b>.
     *
     */
    public void clearStartRouterEzdApplicationEarReplicationSession() {
        this.startRouterEzdApplicationEarReplicationSession_.clear();
    }

    /**
     * Gets the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     * @return StartRouterEzdApplicationEarReplicationParameter[]
     */
    public StartRouterEzdApplicationEarReplicationParameter[] getStartRouterEzdApplicationEarReplicationParameter() {
        StartRouterEzdApplicationEarReplicationParameter[] array = new StartRouterEzdApplicationEarReplicationParameter[startRouterEzdApplicationEarReplicationParameter_.size()];
        return ((StartRouterEzdApplicationEarReplicationParameter[])startRouterEzdApplicationEarReplicationParameter_.toArray(array));
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void setStartRouterEzdApplicationEarReplicationParameter(StartRouterEzdApplicationEarReplicationParameter[] startRouterEzdApplicationEarReplicationParameter) {
        this.startRouterEzdApplicationEarReplicationParameter_.clear();
        for (int i = 0;i < startRouterEzdApplicationEarReplicationParameter.length;i++) {
            addStartRouterEzdApplicationEarReplicationParameter(startRouterEzdApplicationEarReplicationParameter[i]);
        }
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void setStartRouterEzdApplicationEarReplicationParameter(StartRouterEzdApplicationEarReplicationParameter startRouterEzdApplicationEarReplicationParameter) {
        this.startRouterEzdApplicationEarReplicationParameter_.clear();
        addStartRouterEzdApplicationEarReplicationParameter(startRouterEzdApplicationEarReplicationParameter);
    }

    /**
     * Adds the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void addStartRouterEzdApplicationEarReplicationParameter(StartRouterEzdApplicationEarReplicationParameter startRouterEzdApplicationEarReplicationParameter) {
        this.startRouterEzdApplicationEarReplicationParameter_.add(startRouterEzdApplicationEarReplicationParameter);
    }

    /**
     * Adds the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void addStartRouterEzdApplicationEarReplicationParameter(StartRouterEzdApplicationEarReplicationParameter[] startRouterEzdApplicationEarReplicationParameter) {
        for (int i = 0;i < startRouterEzdApplicationEarReplicationParameter.length;i++) {
            addStartRouterEzdApplicationEarReplicationParameter(startRouterEzdApplicationEarReplicationParameter[i]);
        }
    }

    /**
     * Gets number of the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     * @return int
     */
    public int sizeStartRouterEzdApplicationEarReplicationParameter() {
        return (startRouterEzdApplicationEarReplicationParameter_.size());
    }

    /**
     * Gets the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b> by index.
     *
     * @param index
     * @return StartRouterEzdApplicationEarReplicationParameter
     */
    public StartRouterEzdApplicationEarReplicationParameter getStartRouterEzdApplicationEarReplicationParameter(int index) {
        return ((StartRouterEzdApplicationEarReplicationParameter)startRouterEzdApplicationEarReplicationParameter_.get(index));
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void setStartRouterEzdApplicationEarReplicationParameter(int index, StartRouterEzdApplicationEarReplicationParameter startRouterEzdApplicationEarReplicationParameter) {
        this.startRouterEzdApplicationEarReplicationParameter_.set(index, startRouterEzdApplicationEarReplicationParameter);
    }

    /**
     * Adds the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b> by index.
     *
     * @param index
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void addStartRouterEzdApplicationEarReplicationParameter(int index, StartRouterEzdApplicationEarReplicationParameter startRouterEzdApplicationEarReplicationParameter) {
        this.startRouterEzdApplicationEarReplicationParameter_.add(index, startRouterEzdApplicationEarReplicationParameter);
    }

    /**
     * Remove the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b> by index.
     *
     * @param index
     */
    public void removeStartRouterEzdApplicationEarReplicationParameter(int index) {
        this.startRouterEzdApplicationEarReplicationParameter_.remove(index);
    }

    /**
     * Remove the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b> by object.
     *
     * @param startRouterEzdApplicationEarReplicationParameter
     */
    public void removeStartRouterEzdApplicationEarReplicationParameter(StartRouterEzdApplicationEarReplicationParameter startRouterEzdApplicationEarReplicationParameter) {
        this.startRouterEzdApplicationEarReplicationParameter_.remove(startRouterEzdApplicationEarReplicationParameter);
    }

    /**
     * Clear the StartRouterEzdApplicationEarReplicationParameter property <b>startRouterEzdApplicationEarReplicationParameter</b>.
     *
     */
    public void clearStartRouterEzdApplicationEarReplicationParameter() {
        this.startRouterEzdApplicationEarReplicationParameter_.clear();
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
        return (new String(buffer));
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuilder buffer) {
        int size;
        buffer.append("<replication");
        buffer.append(">");
        size = this.startRouterEzdApplicationEarReplicationSession_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationSession value = (StartRouterEzdApplicationEarReplicationSession)this.startRouterEzdApplicationEarReplicationSession_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.startRouterEzdApplicationEarReplicationParameter_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationParameter value = (StartRouterEzdApplicationEarReplicationParameter)this.startRouterEzdApplicationEarReplicationParameter_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</replication>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<replication");
        buffer.write(">");
        size = this.startRouterEzdApplicationEarReplicationSession_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationSession value = (StartRouterEzdApplicationEarReplicationSession)this.startRouterEzdApplicationEarReplicationSession_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.startRouterEzdApplicationEarReplicationParameter_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationParameter value = (StartRouterEzdApplicationEarReplicationParameter)this.startRouterEzdApplicationEarReplicationParameter_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</replication>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<replication");
        buffer.print(">");
        size = this.startRouterEzdApplicationEarReplicationSession_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationSession value = (StartRouterEzdApplicationEarReplicationSession)this.startRouterEzdApplicationEarReplicationSession_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.startRouterEzdApplicationEarReplicationParameter_.size();
        for (int i = 0;i < size;i++) {
            StartRouterEzdApplicationEarReplicationParameter value = (StartRouterEzdApplicationEarReplicationParameter)this.startRouterEzdApplicationEarReplicationParameter_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</replication>");
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
     * for the <code>StartRouterEzdApplicationEarReplication</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "replication")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        while (true) {
            if (!StartRouterEzdApplicationEarReplicationSession.isMatchHungry(target)) {
                break;
            }
            $match$ = true;
        }
        while (true) {
            if (!StartRouterEzdApplicationEarReplicationParameter.isMatchHungry(target)) {
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
     * is valid for the <code>StartRouterEzdApplicationEarReplication</code>.
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
     * is valid for the <code>StartRouterEzdApplicationEarReplication</code>.
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
