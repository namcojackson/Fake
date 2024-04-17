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

/**
 * <b>StartRouterEzdApplicationEarApplicationSubsystem</b> is
 * generated from s21router.rng by Relaxer. This class is derived
 * from: <!-- for programmer <element name="subsystem"> <optional>
 * <attribute name="desc"/> </optional> <attribute name="id"> <data
 * type="NCName"/> </attribute> </element>--> <!-- for javadoc -->
 * 
 * <pre> &lt;element name="subsystem"&gt;
 *                   &lt;optional&gt;
 *                     &lt;attribute name="desc"/&gt;
 *                   &lt;/optional&gt;
 *                   &lt;attribute name="id"&gt;
 *                     &lt;data type="NCName"/&gt;
 *                   &lt;/attribute&gt;
 *                 &lt;/element&gt;</pre>
 * 
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterEzdApplicationEarApplicationSubsystem implements java.io.Serializable, Cloneable {
    private String desc_;

    private String id_;

    private Element xmlElement;

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>.
     */
    public StartRouterEzdApplicationEarApplicationSubsystem() {
        id_ = "";
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>.
     * @param source
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(StartRouterEzdApplicationEarApplicationSubsystem source) {
        setup(source);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Stack <code>stack</code> that contains Elements. This
     * constructor is supposed to be used internally by the Relaxer
     * system.
     * @param stack
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Document <code>doc</code>.
     * @param doc
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Element <code>element</code>.
     * @param element
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(Element element) {
        setup(element);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the File <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the String representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the URL <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the InputStream <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the InputSource <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Reader <code>reader</code>.
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterEzdApplicationEarApplicationSubsystem(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the StartRouterEzdApplicationEarApplicationSubsystem
     * <code>source</code>.
     * @param source
     */
    public void setup(StartRouterEzdApplicationEarApplicationSubsystem source) {
        int size;
        desc_ = source.desc_;
        id_ = source.id_;
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Document <code>doc</code>.
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Element <code>element</code>.
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Stack <code>stack</code> that contains Elements. This
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
        desc_ = URelaxer.getAttributePropertyAsString(element, "desc");
        id_ = URelaxer.getAttributePropertyAsString(element, "id");
    }

    /**
     * @return Object
     */
    public Object clone() {
        
        try {
            StartRouterEzdApplicationEarApplicationSubsystem ctx = (StartRouterEzdApplicationEarApplicationSubsystem) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }

        
        //return (new StartRouterEzdApplicationEarApplicationSubsystem((StartRouterEzdApplicationEarApplicationSubsystem) this));
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
        Element element = doc.createElement("subsystem");
        int size;
        if (this.desc_ != null) {
            URelaxer.setAttributePropertyByString(element, "desc", this.desc_);
        }
        if (this.id_ != null) {
            URelaxer.setAttributePropertyByString(element, "id", this.id_);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the File <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file.toURL());
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the String representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the URL <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the InputStream <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the InputSource <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>
     * by the Reader <code>reader</code>.
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
     * Gets the String property <b>desc</b>.
     * @return String
     */
    public String getDesc() {
        return (desc_);
    }

    /**
     * Sets the String property <b>desc</b>.
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc_ = desc;
    }

    /**
     * Gets the String property <b>id</b>.
     * @return String
     */
    public String getId() {
        return (id_);
    }

    /**
     * Sets the String property <b>id</b>.
     * @param id
     */
    public void setId(String id) {
        this.id_ = id;
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
        return (new String(buffer));
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(StringBuilder buffer) {
        buffer.append("<subsystem");
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
        buffer.append(">");
        buffer.append("</subsystem>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        buffer.write("<subsystem");
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
        buffer.write(">");
        buffer.write("</subsystem>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        buffer.print("<subsystem");
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
        buffer.print(">");
        buffer.print("</subsystem>");
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
     * Gets the property value as String.
     * @return String
     */
    public String getDescAsString() {
        return (URelaxer.getString(getDesc()));
    }

    /**
     * Gets the property value as String.
     * @return String
     */
    public String getIdAsString() {
        return (URelaxer.getString(getId()));
    }

    /**
     * Sets the property value by String.
     * @param string
     */
    public void setDescByString(String string) {
        setDesc(string);
    }

    /**
     * Sets the property value by String.
     * @param string
     */
    public void setIdByString(String string) {
        setId(string);
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
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>.
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "subsystem")) {
            return (false);
        }
        RStack target = new RStack(element);
        // boolean $match$ = false;
        // Element child;
        if (!URelaxer.hasAttributeHungry(target, "id")) {
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
     * valid for the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>.
     * This mehtod is supposed to be used internally by the Relaxer
     * system.
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
     * valid for the
     * <code>StartRouterEzdApplicationEarApplicationSubsystem</code>.
     * This method consumes the stack contents during matching
     * operation. This mehtod is supposed to be used internally by the
     * Relaxer system.
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
