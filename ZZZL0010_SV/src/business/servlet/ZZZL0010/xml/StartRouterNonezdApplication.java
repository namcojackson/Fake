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
 * <b>StartRouterNonezdApplication</b> is generated from s21router.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="nonezd-application">
 *         <zeroOrMore>
 *           <element name="site">
 *             <optional>
 *               <attribute name="desc"/>
 *             </optional>
 *             <attribute name="id">
 *               <data type="NCName"/>
 *             </attribute>
 *             <attribute name="url">
 *               <data type="anyURI"/>
 *             </attribute>
 *             <zeroOrMore>
 *               <element name="parameter">
 *                 <optional>
 *                   <attribute name="desc"/>
 *                 </optional>
 *                 <attribute name="name"/>
 *               </element>
 *             </zeroOrMore>
 *           </element>
 *         </zeroOrMore>
 *       </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="nonezd-application"&gt;
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
 *       &lt;/element&gt;</pre>
 *
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterNonezdApplication implements java.io.Serializable, Cloneable {
    // List<StartRouterNonezdApplicationSite>
    private java.util.List startRouterNonezdApplicationSite_ = new java.util.ArrayList();
    private Element xmlElement;

    /**
     * Creates a <code>StartRouterNonezdApplication</code>.
     *
     */
    public StartRouterNonezdApplication() {
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code>.
     *
     * @param source
     */
    public StartRouterNonezdApplication(StartRouterNonezdApplication source) {
        setup(source);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public StartRouterNonezdApplication(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public StartRouterNonezdApplication(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public StartRouterNonezdApplication(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplication(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplication(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplication(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplication(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplication(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>StartRouterNonezdApplication</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplication(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>StartRouterNonezdApplication</code> by the StartRouterNonezdApplication <code>source</code>.
     *
     * @param source
     */
    public void setup(StartRouterNonezdApplication source) {
        int size;
        this.startRouterNonezdApplicationSite_.clear();
        size = source.startRouterNonezdApplicationSite_.size();
        for (int i = 0;i < size;i++) {
            addStartRouterNonezdApplicationSite((StartRouterNonezdApplicationSite)source.getStartRouterNonezdApplicationSite(i).clone());
        }
    }

    /**
     * Initializes the <code>StartRouterNonezdApplication</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>StartRouterNonezdApplication</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>StartRouterNonezdApplication</code> by the Stack <code>stack</code>
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
        startRouterNonezdApplicationSite_.clear();
        while (true) {
            if (StartRouterNonezdApplicationSite.isMatch(stack)) {
                addStartRouterNonezdApplicationSite(new StartRouterNonezdApplicationSite(stack));
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
            StartRouterNonezdApplication ctx = (StartRouterNonezdApplication) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }
        
        //return (new StartRouterNonezdApplication((StartRouterNonezdApplication)this));
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
        Element element = doc.createElement("nonezd-application");
        int size;
        size = this.startRouterNonezdApplicationSite_.size();
        for (int i = 0;i < size;i++) {
            StartRouterNonezdApplicationSite value = (StartRouterNonezdApplicationSite)this.startRouterNonezdApplicationSite_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>StartRouterNonezdApplication</code> by the File <code>file</code>.
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
     * Initializes the <code>StartRouterNonezdApplication</code>
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
     * Initializes the <code>StartRouterNonezdApplication</code> by the URL <code>url</code>.
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
     * Initializes the <code>StartRouterNonezdApplication</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>StartRouterNonezdApplication</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>StartRouterNonezdApplication</code> by the Reader <code>reader</code>.
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
     * Gets the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     * @return StartRouterNonezdApplicationSite[]
     */
    public StartRouterNonezdApplicationSite[] getStartRouterNonezdApplicationSite() {
        StartRouterNonezdApplicationSite[] array = new StartRouterNonezdApplicationSite[startRouterNonezdApplicationSite_.size()];
        return ((StartRouterNonezdApplicationSite[])startRouterNonezdApplicationSite_.toArray(array));
    }

    /**
     * Sets the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     * @param startRouterNonezdApplicationSite
     */
    public void setStartRouterNonezdApplicationSite(StartRouterNonezdApplicationSite[] startRouterNonezdApplicationSite) {
        this.startRouterNonezdApplicationSite_.clear();
        for (int i = 0;i < startRouterNonezdApplicationSite.length;i++) {
            addStartRouterNonezdApplicationSite(startRouterNonezdApplicationSite[i]);
        }
    }

    /**
     * Sets the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     * @param startRouterNonezdApplicationSite
     */
    public void setStartRouterNonezdApplicationSite(StartRouterNonezdApplicationSite startRouterNonezdApplicationSite) {
        this.startRouterNonezdApplicationSite_.clear();
        addStartRouterNonezdApplicationSite(startRouterNonezdApplicationSite);
    }

    /**
     * Adds the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     * @param startRouterNonezdApplicationSite
     */
    public void addStartRouterNonezdApplicationSite(StartRouterNonezdApplicationSite startRouterNonezdApplicationSite) {
        this.startRouterNonezdApplicationSite_.add(startRouterNonezdApplicationSite);
    }

    /**
     * Adds the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     * @param startRouterNonezdApplicationSite
     */
    public void addStartRouterNonezdApplicationSite(StartRouterNonezdApplicationSite[] startRouterNonezdApplicationSite) {
        for (int i = 0;i < startRouterNonezdApplicationSite.length;i++) {
            addStartRouterNonezdApplicationSite(startRouterNonezdApplicationSite[i]);
        }
    }

    /**
     * Gets number of the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     * @return int
     */
    public int sizeStartRouterNonezdApplicationSite() {
        return (startRouterNonezdApplicationSite_.size());
    }

    /**
     * Gets the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b> by index.
     *
     * @param index
     * @return StartRouterNonezdApplicationSite
     */
    public StartRouterNonezdApplicationSite getStartRouterNonezdApplicationSite(int index) {
        return ((StartRouterNonezdApplicationSite)startRouterNonezdApplicationSite_.get(index));
    }

    /**
     * Sets the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b> by index.
     *
     * @param index
     * @param startRouterNonezdApplicationSite
     */
    public void setStartRouterNonezdApplicationSite(int index, StartRouterNonezdApplicationSite startRouterNonezdApplicationSite) {
        this.startRouterNonezdApplicationSite_.set(index, startRouterNonezdApplicationSite);
    }

    /**
     * Adds the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b> by index.
     *
     * @param index
     * @param startRouterNonezdApplicationSite
     */
    public void addStartRouterNonezdApplicationSite(int index, StartRouterNonezdApplicationSite startRouterNonezdApplicationSite) {
        this.startRouterNonezdApplicationSite_.add(index, startRouterNonezdApplicationSite);
    }

    /**
     * Remove the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b> by index.
     *
     * @param index
     */
    public void removeStartRouterNonezdApplicationSite(int index) {
        this.startRouterNonezdApplicationSite_.remove(index);
    }

    /**
     * Remove the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b> by object.
     *
     * @param startRouterNonezdApplicationSite
     */
    public void removeStartRouterNonezdApplicationSite(StartRouterNonezdApplicationSite startRouterNonezdApplicationSite) {
        this.startRouterNonezdApplicationSite_.remove(startRouterNonezdApplicationSite);
    }

    /**
     * Clear the StartRouterNonezdApplicationSite property <b>startRouterNonezdApplicationSite</b>.
     *
     */
    public void clearStartRouterNonezdApplicationSite() {
        this.startRouterNonezdApplicationSite_.clear();
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
        buffer.append("<nonezd-application");
        buffer.append(">");
        size = this.startRouterNonezdApplicationSite_.size();
        for (int i = 0;i < size;i++) {
            StartRouterNonezdApplicationSite value = (StartRouterNonezdApplicationSite)this.startRouterNonezdApplicationSite_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</nonezd-application>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<nonezd-application");
        buffer.write(">");
        size = this.startRouterNonezdApplicationSite_.size();
        for (int i = 0;i < size;i++) {
            StartRouterNonezdApplicationSite value = (StartRouterNonezdApplicationSite)this.startRouterNonezdApplicationSite_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</nonezd-application>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<nonezd-application");
        buffer.print(">");
        size = this.startRouterNonezdApplicationSite_.size();
        for (int i = 0;i < size;i++) {
            StartRouterNonezdApplicationSite value = (StartRouterNonezdApplicationSite)this.startRouterNonezdApplicationSite_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</nonezd-application>");
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
     * for the <code>StartRouterNonezdApplication</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "nonezd-application")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        while (true) {
            if (!StartRouterNonezdApplicationSite.isMatchHungry(target)) {
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
     * is valid for the <code>StartRouterNonezdApplication</code>.
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
     * is valid for the <code>StartRouterNonezdApplication</code>.
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
