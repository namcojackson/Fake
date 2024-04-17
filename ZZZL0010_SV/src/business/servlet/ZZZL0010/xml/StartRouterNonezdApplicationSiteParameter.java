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
 * <b>StartRouterNonezdApplicationSiteParameter</b> is generated from
 * s21router.rng by Relaxer. This class is derived from: <!-- for
 * programmer <element name="parameter"> <optional> <attribute
 * name="desc"/> </optional> <attribute name="name"/> </element>-->
 * <!-- for javadoc -->
 * 
 * <pre> &lt;element name="parameter"&gt;
 *                 &lt;optional&gt;
 *                   &lt;attribute name="desc"/&gt;
 *                 &lt;/optional&gt;
 *                 &lt;attribute name="name"/&gt;
 *               &lt;/element&gt;</pre>
 * 
 * @version s21router.rng (Fri Jun 12 17:27:13 EDT 2009)
 * @author Relaxer 1.1b (http://www.relaxer.org)
 */
public class StartRouterNonezdApplicationSiteParameter implements java.io.Serializable, Cloneable {
    private String desc_;

    private String name_;

    private Element xmlElement;

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code>.
     */
    public StartRouterNonezdApplicationSiteParameter() {
        name_ = "";
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code>.
     * @param source
     */
    public StartRouterNonezdApplicationSiteParameter(StartRouterNonezdApplicationSiteParameter source) {
        setup(source);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * Stack <code>stack</code> that contains Elements. This
     * constructor is supposed to be used internally by the Relaxer
     * system.
     * @param stack
     */
    public StartRouterNonezdApplicationSiteParameter(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * Document <code>doc</code>.
     * @param doc
     */
    public StartRouterNonezdApplicationSiteParameter(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * Element <code>element</code>.
     * @param element
     */
    public StartRouterNonezdApplicationSiteParameter(Element element) {
        setup(element);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * File <code>file</code>.
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplicationSiteParameter(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * String representation of URI <code>uri</code>.
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplicationSiteParameter(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * URL <code>url</code>.
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplicationSiteParameter(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * InputStream <code>in</code>.
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplicationSiteParameter(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * InputSource <code>is</code>.
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplicationSiteParameter(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * Reader <code>reader</code>.
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public StartRouterNonezdApplicationSiteParameter(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * StartRouterNonezdApplicationSiteParameter <code>source</code>.
     * @param source
     */
    public void setup(StartRouterNonezdApplicationSiteParameter source) {
        int size;
        desc_ = source.desc_;
        name_ = source.name_;
    }

    /**
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * Document <code>doc</code>.
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
     * Element <code>element</code>.
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
        desc_ = URelaxer.getAttributePropertyAsString(element, "desc");
        name_ = URelaxer.getAttributePropertyAsString(element, "name");
    }

    /**
     * @return Object
     */
    public Object clone() {
        
        try {
            StartRouterNonezdApplicationSiteParameter ctx = (StartRouterNonezdApplicationSiteParameter) super.clone();
            ctx.setup(this);
            return ctx;
        } catch (CloneNotSupportedException e) {
            return null;
        }

        //return (new StartRouterNonezdApplicationSiteParameter((StartRouterNonezdApplicationSiteParameter) this));
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
        Element element = doc.createElement("parameter");
        int size;
        if (this.desc_ != null) {
            URelaxer.setAttributePropertyByString(element, "desc", this.desc_);
        }
        if (this.name_ != null) {
            URelaxer.setAttributePropertyByString(element, "name", this.name_);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
     * Initializes the
     * <code>StartRouterNonezdApplicationSiteParameter</code> by the
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
     * Gets the String property <b>name</b>.
     * @return String
     */
    public String getName() {
        return (name_);
    }

    /**
     * Sets the String property <b>name</b>.
     * @param name
     */
    public void setName(String name) {
        this.name_ = name;
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

        buffer.append("<parameter");
        if (desc_ != null) {
            buffer.append(" desc=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getDesc())));
            buffer.append("\"");
        }
        if (name_ != null) {
            buffer.append(" name=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
            buffer.append("\"");
        }
        buffer.append(">");
        buffer.append("</parameter>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<parameter");
        if (desc_ != null) {
            buffer.write(" desc=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getDesc())));
            buffer.write("\"");
        }
        if (name_ != null) {
            buffer.write(" name=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
            buffer.write("\"");
        }
        buffer.write(">");
        buffer.write("</parameter>");
    }

    /**
     * Makes an XML text representation.
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        buffer.print("<parameter");
        if (desc_ != null) {
            buffer.print(" desc=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getDesc())));
            buffer.print("\"");
        }
        if (name_ != null) {
            buffer.print(" name=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
            buffer.print("\"");
        }
        buffer.print(">");
        buffer.print("</parameter>");
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
    public String getNameAsString() {
        return (URelaxer.getString(getName()));
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
    public void setNameByString(String string) {
        setName(string);
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
     * <code>StartRouterNonezdApplicationSiteParameter</code>.
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "parameter")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (!URelaxer.hasAttributeHungry(target, "name")) {
            return (false);
        }
        $match$ = true;
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code> is
     * valid for the
     * <code>StartRouterNonezdApplicationSiteParameter</code>. This
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
     * valid for the
     * <code>StartRouterNonezdApplicationSiteParameter</code>. This
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
