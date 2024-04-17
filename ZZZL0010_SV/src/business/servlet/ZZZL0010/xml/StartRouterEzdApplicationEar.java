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
 * <b>StartRouterEzdApplicationEar</b> is generated from s21router.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="ear">
 *             <attribute name="context-root"/>
 *             <optional>
 *               <attribute name="desc"/>
 *             </optional>
 *             <attribute name="id">
 *               <data type="NCName"/>
 *             </attribute>
 *             <attribute name="url">
 *               <data type="anyURI"/>
 *             </attribute>
 *             <element name="application">
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
 *             </element>
 *             <element name="replication">
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
 *             </element>
 *           </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="ear"&gt;
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
 *           &lt;/element&gt;</pre>
 *
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterEzdApplicationEar implements java.io.Serializable, Cloneable {
    private String contextRoot_;
    private String desc_;
    private String id_;
    private String url_;
    private StartRouterEzdApplicationEarApplication startRouterEzdApplicationEarApplication_;
    private StartRouterEzdApplicationEarReplication startRouterEzdApplicationEarReplication_;
    private Element xmlElement;

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code>.
     *
     */
    public StartRouterEzdApplicationEar() {
        contextRoot_ = "";
        id_ = "";
        url_ = "";
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code>.
     *
     * @param source
     */
    public StartRouterEzdApplicationEar(StartRouterEzdApplicationEar source) {
        setup(source);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public StartRouterEzdApplicationEar(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public StartRouterEzdApplicationEar(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public StartRouterEzdApplicationEar(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEar(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEar(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEar(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEar(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEar(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>StartRouterEzdApplicationEar</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEar(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the StartRouterEzdApplicationEar <code>source</code>.
     *
     * @param source
     */
    public void setup(StartRouterEzdApplicationEar source) {
        int size;
        contextRoot_ = source.contextRoot_;
        desc_ = source.desc_;
        id_ = source.id_;
        url_ = source.url_;
        if (source.startRouterEzdApplicationEarApplication_ != null) {
            setStartRouterEzdApplicationEarApplication((StartRouterEzdApplicationEarApplication)source.getStartRouterEzdApplicationEarApplication().clone());
        }
        if (source.startRouterEzdApplicationEarReplication_ != null) {
            setStartRouterEzdApplicationEarReplication((StartRouterEzdApplicationEarReplication)source.getStartRouterEzdApplicationEarReplication().clone());
        }
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the Stack <code>stack</code>
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
        contextRoot_ = URelaxer.getAttributePropertyAsString(element, "context-root");
        desc_ = URelaxer.getAttributePropertyAsString(element, "desc");
        id_ = URelaxer.getAttributePropertyAsString(element, "id");
        url_ = URelaxer.getAttributePropertyAsString(element, "url");
        setStartRouterEzdApplicationEarApplication(new StartRouterEzdApplicationEarApplication(stack));
        setStartRouterEzdApplicationEarReplication(new StartRouterEzdApplicationEarReplication(stack));
    }

    /**
     * @return Object
     */
    public Object clone() {
        
        try {
            StartRouterEzdApplicationEar ctx = (StartRouterEzdApplicationEar) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }
        
        //return (new StartRouterEzdApplicationEar((StartRouterEzdApplicationEar)this));
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
        Element element = doc.createElement("ear");
        int size;
        if (this.contextRoot_ != null) {
            URelaxer.setAttributePropertyByString(element, "context-root", this.contextRoot_);
        }
        if (this.desc_ != null) {
            URelaxer.setAttributePropertyByString(element, "desc", this.desc_);
        }
        if (this.id_ != null) {
            URelaxer.setAttributePropertyByString(element, "id", this.id_);
        }
        if (this.url_ != null) {
            URelaxer.setAttributePropertyByString(element, "url", this.url_);
        }
        this.startRouterEzdApplicationEarApplication_.makeElement(element);
        this.startRouterEzdApplicationEarReplication_.makeElement(element);
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the File <code>file</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEar</code>
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
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the URL <code>url</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>StartRouterEzdApplicationEar</code> by the Reader <code>reader</code>.
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
     * Gets the String property <b>contextRoot</b>.
     *
     * @return String
     */
    public String getContextRoot() {
        return (contextRoot_);
    }

    /**
     * Sets the String property <b>contextRoot</b>.
     *
     * @param contextRoot
     */
    public void setContextRoot(String contextRoot) {
        this.contextRoot_ = contextRoot;
    }

    /**
     * Gets the String property <b>desc</b>.
     *
     * @return String
     */
    public String getDesc() {
        return (desc_);
    }

    /**
     * Sets the String property <b>desc</b>.
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc_ = desc;
    }

    /**
     * Gets the String property <b>id</b>.
     *
     * @return String
     */
    public String getId() {
        return (id_);
    }

    /**
     * Sets the String property <b>id</b>.
     *
     * @param id
     */
    public void setId(String id) {
        this.id_ = id;
    }

    /**
     * Gets the String property <b>url</b>.
     *
     * @return String
     */
    public String getUrl() {
        return (url_);
    }

    /**
     * Sets the String property <b>url</b>.
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url_ = url;
    }

    /**
     * Gets the StartRouterEzdApplicationEarApplication property <b>startRouterEzdApplicationEarApplication</b>.
     *
     * @return StartRouterEzdApplicationEarApplication
     */
    public StartRouterEzdApplicationEarApplication getStartRouterEzdApplicationEarApplication() {
        return (startRouterEzdApplicationEarApplication_);
    }

    /**
     * Sets the StartRouterEzdApplicationEarApplication property <b>startRouterEzdApplicationEarApplication</b>.
     *
     * @param startRouterEzdApplicationEarApplication
     */
    public void setStartRouterEzdApplicationEarApplication(StartRouterEzdApplicationEarApplication startRouterEzdApplicationEarApplication) {
        this.startRouterEzdApplicationEarApplication_ = startRouterEzdApplicationEarApplication;
    }

    /**
     * Gets the StartRouterEzdApplicationEarReplication property <b>startRouterEzdApplicationEarReplication</b>.
     *
     * @return StartRouterEzdApplicationEarReplication
     */
    public StartRouterEzdApplicationEarReplication getStartRouterEzdApplicationEarReplication() {
        return (startRouterEzdApplicationEarReplication_);
    }

    /**
     * Sets the StartRouterEzdApplicationEarReplication property <b>startRouterEzdApplicationEarReplication</b>.
     *
     * @param startRouterEzdApplicationEarReplication
     */
    public void setStartRouterEzdApplicationEarReplication(StartRouterEzdApplicationEarReplication startRouterEzdApplicationEarReplication) {
        this.startRouterEzdApplicationEarReplication_ = startRouterEzdApplicationEarReplication;
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
        buffer.append("<ear");
        if (contextRoot_ != null) {
            buffer.append(" context-root=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getContextRoot())));
            buffer.append("\"");
        }
        if (desc_ != null) {
            buffer.append(" desc=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getDesc())));
            buffer.append("\"");
        }
        if (id_ != null) {
            buffer.append(" id=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getId())));
            buffer.append("\"");
        }
        if (url_ != null) {
            buffer.append(" url=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getUrl())));
            buffer.append("\"");
        }
        buffer.append(">");
        startRouterEzdApplicationEarApplication_.makeTextElement(buffer);
        startRouterEzdApplicationEarReplication_.makeTextElement(buffer);
        buffer.append("</ear>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<ear");
        if (contextRoot_ != null) {
            buffer.write(" context-root=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getContextRoot())));
            buffer.write("\"");
        }
        if (desc_ != null) {
            buffer.write(" desc=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getDesc())));
            buffer.write("\"");
        }
        if (id_ != null) {
            buffer.write(" id=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getId())));
            buffer.write("\"");
        }
        if (url_ != null) {
            buffer.write(" url=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getUrl())));
            buffer.write("\"");
        }
        buffer.write(">");
        startRouterEzdApplicationEarApplication_.makeTextElement(buffer);
        startRouterEzdApplicationEarReplication_.makeTextElement(buffer);
        buffer.write("</ear>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<ear");
        if (contextRoot_ != null) {
            buffer.print(" context-root=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getContextRoot())));
            buffer.print("\"");
        }
        if (desc_ != null) {
            buffer.print(" desc=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getDesc())));
            buffer.print("\"");
        }
        if (id_ != null) {
            buffer.print(" id=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getId())));
            buffer.print("\"");
        }
        if (url_ != null) {
            buffer.print(" url=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getUrl())));
            buffer.print("\"");
        }
        buffer.print(">");
        startRouterEzdApplicationEarApplication_.makeTextElement(buffer);
        startRouterEzdApplicationEarReplication_.makeTextElement(buffer);
        buffer.print("</ear>");
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
     * Gets the property value as String.
     *
     * @return String
     */
    public String getContextRootAsString() {
        return (URelaxer.getString(getContextRoot()));
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getDescAsString() {
        return (URelaxer.getString(getDesc()));
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getIdAsString() {
        return (URelaxer.getString(getId()));
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getUrlAsString() {
        return (URelaxer.getString(getUrl()));
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setContextRootByString(String string) {
        setContextRoot(string);
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setDescByString(String string) {
        setDesc(string);
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setIdByString(String string) {
        setId(string);
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setUrlByString(String string) {
        setUrl(URelaxer.getString(string));
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
     * for the <code>StartRouterEzdApplicationEar</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "ear")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (!URelaxer.hasAttributeHungry(target, "context-root")) {
            return (false);
        }
        $match$ = true;
        if (!URelaxer.hasAttributeHungry(target, "id")) {
            return (false);
        }
        $match$ = true;
        if (!URelaxer.hasAttributeHungry(target, "url")) {
            return (false);
        }
        $match$ = true;
        if (!StartRouterEzdApplicationEarApplication.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        if (!StartRouterEzdApplicationEarReplication.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>StartRouterEzdApplicationEar</code>.
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
     * is valid for the <code>StartRouterEzdApplicationEar</code>.
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
