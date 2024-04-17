/*
 * Following classes are generated from excel.jjt
 *    Excel
 *    ExcelConstants
 *    ExcelTokenManager
 *    ExcelTreeConstants
 *    JJTExcelState
 *    Node
 *    ParseException
 *    SimpleCharStream
 *    SimpleNode
 *    Token
 *    TokenMgrError
 */

package oracle.apps.e580.itt.workbench;
import java.util.Map;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.ref.Reference;
import java.util.Collections;

import canon.excel.cells.Shape;
import canon.excel.cells.Cell;
import canon.excel.cells.CellValueType;

import java.util.HashMap;

import canon.excel.cells.Comment;

import java.beans.IntrospectionException;
import java.util.Stack;
import java.io.InputStream;
import java.io.PrintWriter;

import canon.excel.cells.Worksheet;

import java.util.regex.Pattern;

import canon.excel.cells.FileFormatType;

import java.util.ArrayList;

import canon.excel.cells.PageSetup;

import java.util.List;

import canon.excel.cells.NamedRange;
import canon.excel.cells.Workbook;

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import java.util.WeakHashMap;
import java.math.BigDecimal;

import canon.excel.cells.CellArea;

import java.lang.reflect.Modifier;

public class CanonE580ITTWorkbenchPOHelperBase{
    
static public class ExcelBase implements ExcelConstants, ExcelTreeConstants {

    static Pattern pattern = Pattern.compile("(?<!\\\\)&[a-zA-Z0-9.]*"); //(?<!\\)&[a-zA-Z0-9.]*

    static boolean DEBUG_FLAG = false;

    static private void log(String prefix, String msg) {
        if (DEBUG_FLAG) {
            System.out.println(prefix == null ? msg : prefix + " " + msg);
        }
    }
 
    static private void log(String msg) {
        log(null, msg);
    }

    public static String unquote(String s) {
        return s.substring(1, s.length() - 1);
    }

    public static String toControlImage(int c) {
        String s = tokenImage[c];
        return unquote(s);
    }

    public static String cellName(int row, int col) {
        return canon.excel.cells.CellsHelper.convertCellIndexToName(row, col);
    }
    public static ToStringHelper toStringHelper = new ToStringHelper(",");

    public static class ToStringHelper {

        private String separator;
        private String arrow;

        public ToStringHelper(String separator) {
            this(separator, null);
        }

        public ToStringHelper(String separator, String arrow) {
            this.separator = separator;
            this.arrow = arrow;
        }

        public String toString(List l) {
            StringBuffer sb = new StringBuffer("(");
            sb.append(" size=" + l.size() + " ");
            String sep = "";
            for (int i = 0; i < l.size(); i++) {
                Object object = l.get(i);
                sb.append(sep).append(object.toString());
                sep = separator;
            }
            return sb.append(")").toString();
        }

        public String toString(Object[] objs) {
            if (objs == null) {
                return "null";
            }
            StringBuffer sb = new StringBuffer("(");
            sb.append(" size=" + objs.length + " ");
            String sep = "";
            for (int i = 0; i < objs.length; i++) {
                Object object = objs[i];
                sb.append(sep).append(object == null ? "null" : object.toString());
                sep = separator;
            }
            return sb.append(")").toString();
        }
    }

    public static abstract class Name {

        public final static int NS_GLOBAL = 0;
        public final static int NS_LOCAL = 1;
        public static Name ROOT = new SimpleName("ROOT", null, NS_GLOBAL);
        Name parent;
        private List children = new ArrayList();
        String name;
        private Location location;
        int nameSpace;

        public Name(String name, int nameSpace) {
            this.name = name;
            this.nameSpace = nameSpace;
        }

        public int getNameSpace() {
            return nameSpace;
        }

        public void setNameSpace(int nameSpace) {
            this.nameSpace = nameSpace;
        }

        public List getChildren() {
            return children;
        }

        public void setChildren(List children) {
            this.children = children;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Name getParent() {
            return parent;
        }

        public void setParent(Name parent) {
            this.parent = parent;
        }

        public abstract Object getValue();

        public abstract void setValue(Object value);

        public String stringValue() {
            return (String) getValue();
        }

        public boolean booleanValue() {
            return ((Boolean) getValue()).booleanValue();
        }

        public int intValue() {
            return ((Integer) getValue()).intValue();
        }

        public void appendChild(Name newchild) {
            children.add(newchild);
            newchild.setParent(this);
        }

        public void removeChild(Name rmchild) {
            children.remove(rmchild);
        }

        public List findChild(String childName, int nameSpace) {
            List l = new ArrayList();
            for (int i = 0; i < children.size(); i++) {
                Name child = (Name) children.get(i);
                if (childName.equals(child.getName())) {
                    l.add(child);
                }
            }
            return l;
        }

        public void removeMe() {
            log("in removeMe" + toString());
            parent.removeChild(this);
            for (int i = 0; i < children.size(); i++) {
                Name child = (Name) children.get(i);
                parent.appendChild(child);
            }

        }

        public void dump(String prefix) {
            log(prefix, toString());
            if (children != null) {
                for (int i = 0; i < children.size(); ++i) {
                    Name n = (Name) children.get(i);
                    if (n != null) {
                        n.dump(prefix + " ");
                    }
                }
            }
        }

        public int hashCode() {
            int hash = 1;
            hash = hash * 31 + name == null ? 0 : name.hashCode();
            hash = hash * 31 + nameSpace;
            return hash;
        }

        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other == this) {
                return true;
            }
            if (this.getClass() != other.getClass()) {
                return false;
            }
            Name o = (Name) other;
            return name.equals(o.getName()) && nameSpace == o.getNameSpace();
        }

        public String toString() {
            return "Name{" + super.toString() + " parent=" + (parent == null ? "" : parent.getName()) + ", children=" + children.size() + ", name=" + name + ", location=" + location + ", nameSpace=" + nameSpace + '}';
        }
    }

    public static class NullName extends Name {

        public NullName(String name, int nameSpace) {
            super(name, nameSpace);
        }

        public Object getValue() {
            return null;
        }

        public void setValue(Object value) {
        }
    }

    public static class MultiName extends Name {

        List list = new ArrayList();

        public MultiName(String name, List list, int nameSpace) {
            super(name, nameSpace);
            this.list = list;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public Object getValue() {
            return list;
        }

        public void setValue(Object value) {
            this.list = (List) value;
        }
    }

    public static class SimpleName extends Name {

        Object value;

        public SimpleName(String name, Object value, int nameSpace) {
            super(name, nameSpace);
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String toString() {
            return "SimpleName{" + super.toString() + " value=" + value + " type=" + (value==null?"null": value.getClass().toString())+ super.toString()+ '}';
        }
    }

    public static class FormularName extends Name {

        private Formular formular;

        public FormularName(String name, Formular formular, int nameSpace) {
            super(name, nameSpace);
            this.formular = formular;
        }

        public Formular getFormular() {
            return formular;
        }

        public void setFormular(Formular formular) {
            this.formular = formular;
        }

        public Object getValue() {
            return formular;
        }

        public void setValue(Object value) {
            setFormular((Formular) value);
        }

        public String toString() {
            return "FormularName{" + super.toString() + " formular=" + formular + '}';
        }
    }

    public static class BeanName extends Name {

        public BeanName(String name, int nameSpace) {
            super(name, nameSpace);
        }

        public Object getValue() {
            return parent == null || parent.getValue() == null ? null : getPropertyValue(parent.getValue(), name);
        }

        public void setValue(Object value) {
            setPropertyValue(parent.getValue(), name, (value instanceof Name ? ((Name) value).getValue() : value));
        }
    }

    public static class Location {

        private int sheetIndex;
        private String sheetName;
        private int row;
        private int col;

        public Location(int sheetIndex, String sheetName, int row, int col) {
            this.sheetIndex = sheetIndex;
            this.sheetName = sheetName;
            this.row = row;
            this.col = col;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        public String toString() {
            return "Location{" + "sheetIndex=" + sheetIndex + ", row=" + row + ", col=" + col + '}';
        }

        public String toCellName() {
            return sheetName + "!" + cellName(row, col);
        }

        public int getSheetIndex() {
            return sheetIndex;
        }

        public void setSheetIndex(int sheetIndex) {
            this.sheetIndex = sheetIndex;
        }
    }

    public static interface Formular {

        String getDefinition();

        Formular add(Formular f);

        Formular substract(Formular f);

        Formular multi(Formular f);

        Formular div(Formular f);
    }

    public static class EmptyFormular implements Formular {

        public String getDefinition() {
            return null;
        }

        public String toString() {
            return "EmptyFormular{" + '}';
        }

        public Formular add(Formular f) {
            return f;
        }

        public Formular substract(Formular f) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Formular multi(Formular f) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Formular div(Formular f) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static class SimpleFormular implements Formular {

        private String definition;

        public SimpleFormular(String definition) {
            this.definition = definition;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String d) {
            this.definition = d;
        }

        public String toString() {
            return "SimpleFormular{" + super.toString() + "definition=" + definition + '}';
        }

        public Formular add(Formular f) {
            if (definition != null && f.getDefinition() != null) {
                return new SimpleFormular(definition + "+" + f.getDefinition());
            } else if (definition != null) {
                return (this);
            } else {
                return f;
            }
        }

        public Formular substract(Formular f) {
            if (definition != null && f.getDefinition() != null) {
                return new SimpleFormular(definition + "-" + f.getDefinition());
            } else if (definition != null) {
                return (this);
            } else {
                return new SimpleFormular("-" + f.getDefinition());
            }
        }

        public Formular multi(Formular f) {
            if (definition != null && f.getDefinition() != null) {
                return new SimpleFormular(definition + "*" + f.getDefinition());
            } else if (definition != null) {
                return (this);
            } else {
                return f;
            }
        }

        public Formular div(Formular f) {
            if (definition != null && f.getDefinition() != null) {
                return new SimpleFormular(definition + "/" + f.getDefinition());
            } else if (definition != null) {
                return (this);
            } else {
                return f;
            }
        }
    }

    public static class ExcelTemplate {

        Workbook workbook;
        ExcelNode root;
//        static HashMap cache = new HashMap();

        public static ExcelTemplate getInstance(String filename,InputStream templateInputStream, int maxRow) {
            // TODO
//            if (cache.containsKey(filename)) {
//                System.out.println("load template from cache " + filename);
//                return (ExcelTemplate) cache.get(filename);
//            }
            ExcelTemplate instance = new ExcelTemplate();
            try {
                instance.load(filename,templateInputStream,maxRow);
//                cache.put(filename, instance);
                
                System.out.println("created new template from " + filename);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return instance;
        }

        public ExcelTemplate load(String filename,InputStream templateInputStream,int maxRow) throws Exception {
        	System.out.println("Before Workbook creation");
            workbook = canon.excel.cells.CellsHelper.newWorkbook();
            workbook.open(templateInputStream, FileFormatType.EXCEL97TO2003);
            System.out.println("After Workbook creation");
            String tmpl = scan(workbook,maxRow);
            log("======");
            log(tmpl);
            log("======");
            Excel parser = new Excel(new java.io.StringReader(tmpl));
            root = (ExcelNode) parser.Input();
//            root.dump("");
            return this;
        }

        public ExcelTemplate load(Workbook workbook,int maxRow) throws Exception {
            this.workbook = workbook;
            String tmpl = scan(workbook,maxRow);
            log("======");
            log(tmpl);
            log("======");
            Excel parser = new Excel(new java.io.StringReader(tmpl));
            root = (ExcelNode) parser.Input();
//            root.dump("");
            return this;
        }

        public String scan(Workbook wb,int maxRow) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            try {
                writer.println(toControlImage(XLS_WB_START));
                for (int wsidx = 0; wsidx < wb.getWorksheets().size(); wsidx++) {
                    Worksheet ws = wb.getWorksheets().getSheet(wsidx);
                    writer.println(toControlImage(XLS_WS_START) + " " + wsidx);
                    int rowcount = maxRow + 1;
                    int colcount = ws.getCells().getMaxColumn() + 1;
                    log("rowcount " + rowcount);
                    log("colcount " + colcount);
                    for (int r = 0; r < rowcount; r++) {
                        for (int c = 0; c < colcount; c++) {
                            Comment comment = getComment(ws, r, c);
                            String note = comment == null ? null : comment.getText();
                            if (note != null) {
                                writer.println(note);
                            }
                            writer.print(toControlImage(XLS_CELL) + "(" + r + "," + c + ") ");
                            CellArea cellArea = getMergedCell(ws, r, c);
                            if (cellArea != null) {
                                writer.print(toControlImage(XLS_MERGED_CELL) + "(" + (cellArea.getEndRow() - r) + "," + (cellArea.getEndColumn() - c) + ") ");
                            }
                            Cell cell=ws.getCells().getCell(r, c);
                            Object o=cell.getValue();
                            if(o!=null && o instanceof String){
                                String v=parseCellValue(writer,(String)o);
                                cell.setValue(v);
                            }
                        }
                        writer.print(toControlImage(XLS_ROW_BREAK));
                        writer.println();
                    }
//                    writer.println(toControlImage(XLS_WS_END));
                }
                writer.println(toControlImage(XLS_WB_END));
                return stringWriter.getBuffer().toString();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            return null;
        }

        private Comment getComment(Worksheet ws, int r, int c) {
            for (int i = 0; i < ws.getComments().size(); i++) {
                Comment comment = ws.getComments().get(i);
                if (comment.getColumn() == c && comment.getRow() == r) {
                    return comment;
                }
            }
            return null;
        }
        
        /**
         * Cell value may contains block statement, extract the statement to PrintWriter
         * and return remains string value;
         */
        public static String parseCellValue(PrintWriter pw,String s){
            Pattern pattern = Pattern.compile("<%.*?%>",Pattern.DOTALL);
            Matcher matcher = pattern.matcher(s);
            int start=0;
            StringBuffer b=new StringBuffer();
            while (matcher.find()) {
                String f=matcher.group();
                pw.print(f.substring(2,f.length()-2));
                b.append(s.substring(start, matcher.start()));
                start=matcher.end();
            }
            if(start!=s.length()-1){
                b.append(s.substring(start, s.length()));
            }
            return b.toString();
        }
        
        private CellArea getMergedCell(Worksheet ws, int r, int c) {
            List mergedCells = ws.getCells().getMergedCells();
            for (int i = 0; i < mergedCells.size(); i++) {
                CellArea cellarea = (CellArea) mergedCells.get(i);
                if (cellarea.getStartRow() == r && cellarea.getStartColumn() == c) {
                    return cellarea;
                }
            }
            return null;
        }

        public Workbook createInvoice(Map data) {
            try {
                WorkBookContext context = new WorkBookContext();
                context.init(data);
                context.setSourceWorkbook(workbook);
                root.apply(context, "");
                context.executePendingTask();
                Workbook newwb = context.getTarget();
                context.rootName.dump("");
                newwb.calculateFormula();
                return newwb;
            } catch (Exception ex) {
//                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    static void copySheet(Worksheet ws, Worksheet newws) {
        if (newws.getName() == null || newws.getName().startsWith("Sheet")) {
//            newws.setName(ws.getName());
        }
        int rowcount = ws.getCells().getMaxRow() + 1;
        int colcount = ws.getCells().getMaxColumn() + 1;

        for (int c = 0; c < colcount; c++) {
            newws.getCells().setColumnWidth(c, ws.getCells().getColumnWidth(c));
        }
        for (int r = 0; r < rowcount; r++) {
            newws.getCells().setRowHeight(r, ws.getCells().getRowHeight(r));
        }
        ws.getShapes().clearComments();

        copyPageSetup(ws.getPageSetup(), newws.getPageSetup());

    }

    static private void copyPageSetup(PageSetup ps, PageSetup newps) {
        newps.setPrintArea(ps.getPrintArea());
        newps.setOrientation(ps.getOrientation());
        newps.setZoom(ps.getZoom());
        newps.setFitToPagesTall(ps.getFitToPagesTall());
        newps.setFitToPagesWide(ps.getFitToPagesWide());
        newps.setRightFooter(ps.getRightFooter());
        newps.setPrintTitleRows(ps.getPrintTitleRows());

    }
    public final static Map wrappermap = new HashMap();

    static {
        wrappermap.put(Boolean.class, boolean.class);
        wrappermap.put(Byte.class, byte.class);
        wrappermap.put(Short.class, short.class);
        wrappermap.put(Character.class, char.class);
        wrappermap.put(Integer.class, int.class);
        wrappermap.put(Long.class, long.class);
        wrappermap.put(Float.class, float.class);
        wrappermap.put(Double.class, double.class);
    }

    private static Class[] types(Object[] args) {
        Class[] ts = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            ts[i] = args[i].getClass();
        }
        return ts;
    }

    private static Class[] primitiveTypes(Object[] args) {
        Class[] ts = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Class clz = args[i].getClass();
            ts[i] = wrappermap.containsKey(clz) ? (Class) wrappermap.get(clz) : clz;
        }
        return ts;

    }

    public static Method getMethod(Object bean, String propName, Object[] args) {
        try {
            log("in getMethod args are " + args);
            Class[] ts = (args == null ? new Class[]{} : types(args));
            log("in getMethod args are " + new ToStringHelper("").toString(args));
            log("in getMethod types are " + new ToStringHelper("").toString(ts));
            return bean.getClass().getDeclaredMethod(propName, ts);
        } catch (Exception ex) {
            log("exception: " + ex.toString());
            log("try resolve the method with primitive types.");
            Class[] ts = (args == null ? new Class[]{} : primitiveTypes(args));
            log("in getMethod types are " + new ToStringHelper("").toString(ts));
            try {
                return bean.getClass().getDeclaredMethod(propName, ts);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        return null;
    }
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    public static Object invokeMethod(Object bean, String methodName, Object[] args) {
        try {
            log("in invokeMethod " + bean + " " + methodName + "()");
            return MethodUtils.invokeMethod(bean, methodName, args);
        } catch (Exception ex) {
            System.out.println("cause " + ex.getCause());
            if (ex.getCause() instanceof AssertException) {
                throw (AssertException) ex.getCause();
            }
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
//        return null;
    }
    static final String NULL_STR = "null";

    public static Object getPropertyValue(Object bean, String propName) {
        try {
//            log("in getPropertyValue " + bean + "(" + bean.getClass() + ")" + propName);
            Method readMethod = getReadPropertyMethod(bean, propName);
            Object value = null;
            if (readMethod != null) {
                value = readMethod.invoke(bean, EMPTY_OBJECT_ARRAY);
            } else if (bean instanceof Map) {
                return ((Map) bean).get(propName);
            }
            if (NULL_STR.equals(value)) {
                value = null;
            }
            return (value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static Object setPropertyValue(Object bean, String propName, Object newValue) {
        try {
            log("in setPropertyValue " + bean + "(" + bean.getClass() + ")" + propName + " newValue " + newValue);
            Method writeMethod = getWritePropertyMethod(bean, propName);
            Object value = writeMethod.invoke(bean, new Object[]{newValue});
            return (value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;

        }
        return name.substring(0, 1).toUpperCase(java.util.Locale.ENGLISH) + name.substring(1);
    }

    static Method getReadPropertyMethod(Object bean, String propName) {
        try {
            return new PropertyDescriptor(propName, bean.getClass(), "get" + capitalize(propName), null).getReadMethod();
        } catch (Exception ex) {
        }
        try {
            return new PropertyDescriptor(propName, bean.getClass(), "is" + capitalize(propName), null).getReadMethod();
        } catch (IntrospectionException ex) {
        }
        return null;
    }

    static Method getWritePropertyMethod(Object bean, String propName) {
        try {
            String writeMethodName = "set" + capitalize(propName);
            return new PropertyDescriptor(propName, bean.getClass(), null, writeMethodName).getWriteMethod();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static String[] beanName(String fullname) {
        int idx = fullname.indexOf('.');
        return idx < 0 ? new String[]{fullname} : new String[]{fullname.substring(0, idx), fullname.substring(idx + 1)};
    }

    static interface NameProvider {

        Name getName(String name);

        Name addSimpleName(String name, Object value);

        Name addBeanName(String name);

        Name addFormularName(String name, Formular formular);

        void removeAll();

        String dump();
    }

    static interface NameSpace {

        Name resolveName(String name, boolean leftValue);

        List resolveNameList(String name, boolean leftValue);
    }

    static class NameSpaceImpl implements NameSpace {

        private NameProvider nameProvider;

        public NameSpaceImpl(NameProvider nameProvider) {
            this.nameProvider = nameProvider;
        }

        public Name resolveName(String name, boolean leftValue) {
            List l = resolveNameList(name, leftValue);
            if (l == null || l.size() == 0) {
                return null;
            }
            if (l.size() == 1) {
                return (Name) l.get(0);
            }
            throw new RuntimeException("Found " + l.size() + " name(" + name + "), but request only one.");
        }

        public List resolveNameList(String s, boolean leftValue) {
            List l = new ArrayList();
            Name name = nameProvider.getName(s);
            if (name != null) {
                l.add(name);
                return l;
            }
            String names[] = beanName(s);
            if (names.length > 1) {
                String beanname = names[0];
                name = nameProvider.getName(beanname);
                if (name != null && names.length > 1) {
                    String propertyName = names[1];
                    return resolveNameByProperty(name, propertyName, leftValue);
                }
            }
            return l;
        }

        public List resolveNameByProperty(Name name, String propertyName, boolean leftValue) {
            log("in resolveLocalNameByProperty name is " + name + " property Name is " + propertyName);
            List l = new ArrayList();
            if (propertyName.indexOf(".") > 0) {
                String names[] = beanName(propertyName);
                List l1 = resolveNameByProperty(name, names[0], leftValue);
                for (int i = 0; i < l1.size(); i++) {
                    Name n = (Name) l1.get(i);
                    List l2 = resolveNameByProperty(n, names[1], leftValue);
                    if (l2.size() > 0) {
                        l.addAll(l2);
                    }
                }
                return l;
            } else {
                List children = name.findChild(propertyName, Name.NS_LOCAL);
                if (children != null && children.size() > 0) {
                    return children;
                }
                if (name.getValue() != null) {
                    Method propertyMethod = getReadPropertyMethod(name.getValue(), propertyName);
                    if (propertyMethod != null) {
                        Object propertyValue = getPropertyValue(name.getValue(), propertyName);
                        Name child = Formular.class.isAssignableFrom(propertyMethod.getReturnType())
                                ? nameProvider.addFormularName(propertyName, (Formular) propertyValue)
                                : (leftValue
                                ? nameProvider.addBeanName(propertyName)
                                : nameProvider.addSimpleName(propertyName, propertyValue));
                        name.appendChild(child);
                        l.add(child);
                    } else {
                        Name n = new NullName(propertyName, Name.NS_LOCAL);
                        n.setParent(name);
                        l.add(n);
                    }
                }
            }
            return l;
        }
    }

    static interface Task {

        void execute(Object o);
    }

    public static class CellTask implements Task {

        private Cell source;
        private Cell target;
        private String prefix;
        private ExcelNode node;
        private int templateSheetIndex;
        private int targetSheetIndex;

        public CellTask(ExcelNode node, Cell source, Cell target, String prefix, int templateSheetIndex, int targetSheetIndex) {
            this.node = node;
            this.source = source;
            this.target = target;
            this.prefix = prefix;
            this.templateSheetIndex = templateSheetIndex;
            this.targetSheetIndex = targetSheetIndex;
        }

        public void execute(Object o) {
            WorkBookContext context = (WorkBookContext) o;
            context.gotoSheet(templateSheetIndex, targetSheetIndex, templateSheetIndex);
            node.finalizing(target, context, prefix);
        }
    }

    // a sequence of excel Named Range 
    public static class View {

        private String viewName;
        private int viewIndex;
        private int sheetIndex;
        private int startRow;
        private int startColumn;
        private int endRow;
        private int endColumn;
        private int rowIndex;
        private Workbook target;
        private Workbook source;

        public View(Workbook source, Workbook target, String viewName) {
            this.source = source;
            this.target = target;
            this.viewName = viewName;
        }

        private void init(int viewIndex, int sheetIndex, int startRow, int startColumn, int endRow, int endColumn) {
            this.viewName = viewName;
            this.viewIndex = viewIndex;
            this.sheetIndex = sheetIndex;
            this.startRow = startRow;
            this.startColumn = startColumn;
            this.endRow = endRow;
            this.endColumn = endColumn;
            rowIndex = this.startRow;
        }

        public int getEndColumn() {
            return endColumn;
        }

        public void setEndColumn(int endColumn) {
            this.endColumn = endColumn;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getViewIndex() {
            return viewIndex;
        }

        public void setViewIndex(int viewIndex) {
            this.viewIndex = viewIndex;
        }

        public String getViewName() {
            return viewName;
        }

        public void setViewName(String viewName) {
            this.viewName = viewName;
        }

        public int getSheetIndex() {
            return sheetIndex;
        }

        public void setSheetIndex(int sourceSheetIndex) {
            this.sheetIndex = sourceSheetIndex;
        }

        public int getStartColumn() {
            return startColumn;
        }

        public void setStartColumn(int startColumn) {
            this.startColumn = startColumn;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        private static View getInstance(Workbook source, Workbook target, String viewName) {
            for (int i = 0; i < 20; i++) {
                String name = "__" + viewName + "__" + i;
                NamedRange nr = source.getWorksheets().getRangeByName(name);
                if (nr != null) {
                    View v = new View(source, target, viewName);
                    v.init(i, nr.getSourceSheet().getIndex(), nr.getStartRow(), nr.getStartColumn(), nr.getEndRow(), nr.getEndColumn());
                    return v;
                }

            }
            return null;
        }

        private void gotoNextView() {
            for (int i = viewIndex + 1; i < 20; i++) {
                String name = "__" + viewName + "__" + i;
                NamedRange nr = source.getWorksheets().getRangeByName(name);
                if (nr != null) {
                    init(i, nr.getSourceSheet().getIndex(), nr.getStartRow(), nr.getStartColumn(), nr.getEndRow(), nr.getEndColumn());
                    break;
                }
            }
        }

        public void moveToNextRow(int noBreak) {
            if ((rowIndex + noBreak) < endRow) {
                rowIndex++;
            } else if ((rowIndex + noBreak) >= endRow) {
                gotoNextView();
            }
        }

        private Cell gotoColumn(int c) {
            if (rowIndex > endRow) {
                return null;
            } else {
                return getCurrentCell(c);
            }
        }

        private Cell getCurrentCell(int colIndex) {
            return target.getWorksheets().getSheet(sheetIndex).getCells().getCell(rowIndex, colIndex);
        }

        public String toString() {
            return "View{" + "viewName=" + viewName + ", viewIndex=" + viewIndex + ", sheetIndex=" + sheetIndex + ", startRow=" + startRow + ", startColumn=" + startColumn + ", endRow=" + endRow + ", endColumn=" + endColumn + ", rowIndex=" + rowIndex + '}';
        }
    }
    static int CREATE_NEW_SHEET = -1;

    public static class WorkBookContext implements Assert, ExcelTool{

        private Name rootName = Name.ROOT;
        private Workbook target;
        private Workbook source;
        private HashMap globalNames;
        private int targetSheetIndex = -1;
        private int templateSheetIndex = -1;
        private int rowIndex = -1;
        private int colIndex = -1;
        private List pendingTask = new ArrayList();
        private boolean dynamic;
        private HashMap views = new HashMap();
        private String currentViewName;
        private String direction;
        private int noBreak;
        private boolean leftValue = false;
        private NameProvider globalNameProvider = new NameProvider() {

            public Name getName(String name) {
                return (Name) globalNames.get(name);
            }

            public Name addSimpleName(String name, Object value) {
                return new SimpleName(name, value, Name.NS_GLOBAL);
            }

            public void removeAll() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Name addFormularName(String name, Formular formular) {
                return new FormularName(name, formular, Name.NS_GLOBAL);
            }

            public Name addBeanName(String name) {
                return new BeanName(name, Name.NS_GLOBAL);
            }

            public String dump() {
                return " globalNames " + globalNames.keySet();
            }
        };
        private NameSpace globalNameSpace = new NameSpaceImpl(globalNameProvider);

        public NameSpace getNameSpace() {
            return globalNameSpace;
        }

        public Name getRootName() {
            return rootName;
        }

        public boolean isLeftValue() {
            return leftValue;
        }

        public void setLeftValue(boolean leftValue) {
            this.leftValue = leftValue;
        }

        public Workbook getSourceWorkbook() {
            return source;
        }

        public void setSourceWorkbook(Workbook source) {
            this.source = source;
        }

        public Workbook getTarget() {
            return target;
        }

        public void setTarget(Workbook wb) {
            this.target = wb;
        }

        public WorkBookContext() {
        }

        public void init(Map data) throws Exception {
            //this.data=data;
            HashMap names = new HashMap();
            Iterator i = data.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                String name = (String) e.getKey();
                Object value = e.getValue();
                Name newname = new SimpleName(name, value, Name.NS_GLOBAL);
                rootName.appendChild(newname);
                names.put(name, newname);
            }
            this.globalNames = names;

        }

        private void gotoSheet(int tmplSheetIndex, int trgtSheetIndex, int cloneSheetFrom) {
            this.templateSheetIndex = tmplSheetIndex;
            if (CREATE_NEW_SHEET == trgtSheetIndex) {
                this.targetSheetIndex++;
                Worksheet newws = target.getWorksheets().addSheet(source.getWorksheets().getSheetName(cloneSheetFrom));
                copySheet(source.getWorksheets().getSheet(cloneSheetFrom), newws);
            } else {
                this.targetSheetIndex = trgtSheetIndex;
            }
        }

        public void moveToNextColumn() {
            colIndex++;
            log("moveToNextColumn " + colIndex);
        }

        private void moveToNextSheet(int sheetIndex) {
            templateSheetIndex = sheetIndex;
            log("in moveToNextSheet sourceSheetIndex " + templateSheetIndex);
            log("targetSheetIndex " + targetSheetIndex);
            gotoSheet(templateSheetIndex, CREATE_NEW_SHEET, templateSheetIndex);
            gotoRowAndColumn(0, 0);
        }

        private Cell getSourceCell(int r, int c) {
            Cell t = source.getWorksheets().getSheet(templateSheetIndex).getCells().getCell(r, c);
            return t;
        }

        private Cell getCurrentCell() {
            return target.getWorksheets().getSheet(targetSheetIndex).getCells().getCell(rowIndex, colIndex);
        }

        private Cell gotoRowAndColumn(int r, int c) {
            rowIndex = r;
            colIndex = c;
            return getCurrentCell();
        }
        // row is controlled by ROW_BREAK

        private Cell gotoColumn(int c) {
            if ("horizontal".equalsIgnoreCase(direction)) {
                moveToNextColumn();
            } else {
                colIndex = c;
            }
            return getCurrentCell();
        }

        private Cell gotoRow(int r) {
            rowIndex = r;
            return getCurrentCell();
        }

        private View getCurrentView() {
            return currentViewName == null ? null : (View) views.get(currentViewName);
        }

        public Cell moveTo(int sheetIndex, int r, int c) {
            log("in moveTo currentViewName is " + currentViewName);
            log("in moveTo views is " + views);
            View currentView = getCurrentView();
            log("in moveTo currentView is " + currentView);
            if (currentView != null) {
                return currentView.gotoColumn(c);
            } else if (dynamic) {
                return gotoColumn(c);
            } else {
                gotoSheet(sheetIndex, targetSheetIndex, sheetIndex);
                return gotoRowAndColumn(r, c);
            }
        }

        public void moveToNextRow() {
            View currentView = getCurrentView();
            if (currentView != null) {
                currentView.moveToNextRow(noBreak);
                gotoSheet(templateSheetIndex, currentView.getSheetIndex(), currentView.getSheetIndex());
            } else {
                rowIndex++;
            }
        }

        // endrow and endcol are reletice to current row index
        private void mergeCell(int endrow, int endcol) {
            target.getWorksheets().getSheet(targetSheetIndex).getCells().merge(rowIndex, colIndex,  rowIndex + endrow, colIndex + endcol);
        }

        private void addPendingTask(Task a) {
            pendingTask.add(a);
        }

        private void executePendingTask() {
            log("in executePendingTask.");
            List l = new ArrayList(pendingTask);
            for (int i = 0; i < l.size(); i++) {
                Task t = (Task) l.get(i);
                t.execute(this);
            }
        }

        public boolean isDynamic() {
            return dynamic;
        }

        public void resetBinding(NameSpace localns) {
            Name name = localns.resolveName("dynamic", false);
            dynamic = name == null ? false : name.booleanValue();

            name = localns.resolveName("view", false);
            currentViewName = name == null ? null : name.stringValue();
            if (currentViewName != null && !views.containsKey(currentViewName)) {
                View v = View.getInstance(source, target, currentViewName);
                if (v != null) {
                    views.put(currentViewName, v);
                }
            }

            name = localns.resolveName("noBreak", false);
            noBreak = name == null ? 0 : name.intValue();

            name = localns.resolveName("direction", false);
            direction = name == null ? null : name.stringValue();
            log("in resetbidding " + " dynamic  is " + dynamic + " direction is " + direction);

        }

        public int getTemplateSheetIndex() {
            return templateSheetIndex;
        }

        public int getTargetSheetIndex() {
            return targetSheetIndex;
        }

        public String getTargetSheetName() {
            return target.getWorksheets().getSheetName(targetSheetIndex);
        }

        Worksheet getCurrentWorksheet() {
            return target.getWorksheets().getSheet(targetSheetIndex);
        }

        public void assertNotNull(Object o) {
            if (o == null) {
                throw new AssertException("Expected not null, but got " + o);
            }
        }

        public void assertTrue(Boolean b) {
            if (!b.booleanValue()) {
                throw new AssertException("Expected true, but got " + b);
            }
        }

        public void assertTrue(Name n) {
            if (n == null || !(n.getValue() instanceof Boolean)) {
                throw new AssertException("Expected true, but got " + n);
            }
            assertTrue((Boolean) n.getValue());
        }

        public void assertEquals(String expect, String value) {
            if ((expect == null && value != null) || !expect.equals(value)) {
                throw new AssertException("Expected " + expect + " but got " + value);
            }
        }

        public void assertEquals(Name expect, String value) {
            if ((expect == null && value != null)) {
                throw new AssertException("Expected " + expect + ", but got " + value);
            }
            assertEquals((String) expect.getValue(), value);
        }

        public void assertEquals(String expect, Name value) {
            if ((expect == null && value != null)) {
                throw new AssertException("Expected " + expect + ", but got " + value);
            }
            assertEquals(expect, (String) value.getValue());
        }

        public void assertEquals(Name expect, Name value) {
            if ((expect == null && value != null)) {
                throw new AssertException("Expected " + expect + ", but got " + value);
            }
            assertEquals((String) expect.getValue(), (String) value.getValue());
        }

        public void fail() {
            throw new AssertException();
        }

        public void println(Object o) {
            if (o instanceof Name) {
                println(((Name) o).getValue());
            } else {
                System.out.println(o);
            }
        }
        public void enableLogging() {
            DEBUG_FLAG = true;
        }

        public void disableLogging() {
            DEBUG_FLAG = false;
        }
        
        public boolean isEmpty(String s){
            return s==null || s.trim().length()==0;
        }

        public boolean isNull(Object s){
            return s==null;
        }

        public String defaultString(String str){
            return str == null ? "" : str;
        }
    }
    
    public static interface Assert{
        public void assertNotNull(Object o);
        public void assertTrue(Boolean b);
        public void assertTrue(Name n);
        public void assertEquals(String expect, String value);
        public void assertEquals(Name expect, String value);
        public void assertEquals(String expect, Name value);
        public void assertEquals(Name expect, Name value);
        public void fail();
    }

    public static interface ExcelTool{
        public void println(Object o);
        public void enableLogging();
        public void disableLogging();
        public boolean isEmpty(String s);
        public String defaultString(String s);
        public boolean isNull(Object s);
    }
    
    public static class AssertException extends RuntimeException {

        public AssertException() {
        }

        ;
        public AssertException(String s) {
            super(s);
        }
    }

    public static class ExcelNode extends SimpleNode {
//        protected static Object[] stack = new Object[1024];

        static Stack stack = new Stack();
        static int top = -1;
        int sheetIndex;
        int rowIndex;
        int columnIndex;
        int endRow;
        int endCol;
        NameProvider localNameProvider = new NameProvider() {

            private HashMap localNames = hasLocalName(id) ? new HashMap() : null;

            public Name getName(String name) {
                if (hasLocalName(id) && localNames.get(name) != null) {
                    return (Name) localNames.get(name);
                } else {
                    return parent() == null ? null : parent().getNameProvider().getName(name);
                }
            }

            public Name addSimpleName(String name, Object value) {
                if (hasLocalName(id)) {
                    Name newname = new SimpleName(name, value, Name.NS_LOCAL);
                    localNames.put(name, newname);
                    return newname;
                } else {
                    return parent().getNameProvider().addSimpleName(name, value);
                }
            }

            public Name addFormularName(String name, Formular formular) {
                if (hasLocalName(id)) {
                    Name newname = new FormularName(name, formular, Name.NS_LOCAL);
                    localNames.put(name, newname);
                    return newname;
                } else {
                    return parent().getNameProvider().addFormularName(name, formular);
                }
            }

            public void removeAll() {
                if (localNames != null && !localNames.isEmpty()) {
                    log("in removeAllLocalName " + localNames);
                    localNames.clear();
                }
            }

            public String dump() {
                return "{" + "localNames=" + localNames.keySet() + '}';
            }

            public Name addBeanName(String name) {
                if (hasLocalName(id)) {
                    Name newname = new BeanName(name, Name.NS_LOCAL);
                    localNames.put(name, newname);
                    return newname;
                } else {
                    return parent().getNameProvider().addBeanName(name);
                }
            }
        };
        NameSpace localNameSpace = new NameSpaceImpl(localNameProvider);

        public ExcelNode(int i) {
            super(i);
        }

        public ExcelNode(Excel p, int id) {
            super(p, id);
        }

        public NameProvider getNameProvider() {
            return localNameProvider;
        }

        public NameSpace getNameSpace() {
            return localNameSpace;
        }

        private boolean hasLocalName(int id) {
            return id == JJTWORKBOOK || id == JJTWORKSHEET || id == JJTBLOCK;
        }

        public ExcelNode parent() {
            return (ExcelNode) jjtGetParent();
        }

        public int getChildrenCount() {
            return this.children == null ? 0 : children.length;
        }

        public ExcelNode at(int index) {
            return (ExcelNode) (this.children == null
                    || index < 0
                    || index >= this.children.length
                    ? null
                    : this.children[index]);
        }

        public String getNodeName() {
            return ExcelTreeConstants.jjtNodeName[this.id];
        }

        public void setColumnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public void setSheetIndex(int sheetIndex) {
            this.sheetIndex = sheetIndex;
        }

        public int getSheetIndex() {
            return sheetIndex;
        }

        public void setEndCol(int endCol) {
            this.endCol = endCol;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public void dumpNames() {
            if (parent != null) {
                ((ExcelNode) parent).dumpNames();
            }
            if (hasLocalName(id)) {
                log(localNameProvider.dump());
            }
        }

        public void dump(String prefix) {
            log(toString(prefix));
            if (children != null) {
                for (int i = 0; i < children.length; ++i) {
                    SimpleNode n = (SimpleNode) children[i];
                    if (n != null) {
                        n.dump(prefix + " ");
                    }
                }
            }
        }

        private void applyChildren(WorkBookContext c, String prefix) {
            for (int i = 0; i < getChildrenCount(); ++i) {
                ExcelNode child = at(i);
                child.apply(c, prefix);
            }
        }

        boolean isTrue(Object obj) {
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            } else if (obj instanceof Name) {
                return isTrue(((Name) obj).getValue());
            } else if (obj instanceof List) {
                return !((List) obj).isEmpty();
            }
            return false;
        }

        private Name singleName(Object o) {
            if (o instanceof List) {
                List l = (List) o;
                if (l.size() == 0) {
                    return null;
                }
                if (l.size() > 1) {
                    throw new RuntimeException("Invalid Name " + l);
                }
                return (Name) l.get(0);
            } else {
                return (Name) o;
            }
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            applyChildren(c, prefix + " ");
            if (this.id == JJTBLOCK && ((ExcelNode) this.parent).id != JJTFORSTATEMENT) {
                localNameProvider.removeAll();
                c.resetBinding(localNameSpace);
            } else if (this.id == JJTWORKBOOK) {
                localNameProvider.addSimpleName("pages", new Integer(c.getTarget().getWorksheets().size()));
            }

        }

        private void copyShapes(Cell s, Cell t, WorkBookContext c){
            Worksheet targetWS=c.getCurrentWorksheet();
            Worksheet templateWS=c.getSourceWorkbook().getWorksheets().getSheet(c.getTemplateSheetIndex());
            for (int i = 0; i < templateWS.getShapes().size(); i++) {
                Shape sh = templateWS.getShapes().get(i);
                if(sh.getUpperLeftColumn()==s.getColumnIndex() && sh.getUpperLeftRow()==s.getRowIndex() ){
                    targetWS.getShapes().addCopy(sh,t.getRowIndex(),t.getColumnIndex());
                }
            }
        }
        public void copyCell(Cell s, Cell t, WorkBookContext c, String prefix, boolean emptyUnResolved) {
            Object cellvalue = null;
            t.setStyle(s.getStyle());
            copyShapes(s,t,c);
            
            boolean fullyResolved = true;
            log(prefix, "in copyCell " + c.getTemplateSheetIndex() + " " + cellName(s.getRowIndex(), s.getColumnIndex()) + "=>" + c.getTargetSheetIndex() + " " + cellName(t.getRowIndex(), t.getColumnIndex()));
            if (s.getValue() != null && CellValueType.STRING == s.getValueType()) {
                Object[] ret = resolveStringValue(s.getValue().toString(), c, prefix);
                cellvalue = ret[0];
                if (ret[1] != null) {
                    ((Name) ret[1]).setLocation(new Location(c.getTargetSheetIndex(), c.getTargetSheetName(), t.getRowIndex(), t.getColumnIndex()));
                    log(prefix, "in copyCell new location " + ((Name) ret[1]).getLocation());
                }
                fullyResolved = ((Boolean) ret[2]).booleanValue();
            } else {
                cellvalue = s.getValue();
            }
            if (cellvalue != null) {
                log(prefix, "before setValue value is " + cellvalue+ " type is "+cellvalue.getClass());
                if (cellvalue instanceof Formular) {
                    Formular f = (Formular) cellvalue;
                    if (f.getDefinition() != null && f.getDefinition().length() > 1) {
                        log(prefix, "set formular definition " + f.getDefinition());
                        t.setFormula("=" + optimizeFormular(f.getDefinition(), c.getTargetSheetName()));

                    } else {
                        log(prefix, "unresolved formular " + s.getValue().toString());
                        fullyResolved = false;
                    }
                } else {
                    t.setValue(cellvalue);
                }
            }
            if (!fullyResolved) {
                if (emptyUnResolved) {
                    t.setValue((String) null);
                } else {
                    c.addPendingTask(new CellTask(this, s, t, prefix, c.getTemplateSheetIndex(), c.getTargetSheetIndex()));
                }
            }
        }
        
        public void finalizing(Cell t, WorkBookContext c, String prefix) {
            Object cellvalue = null;
            if (t.getValue() != null && CellValueType.STRING == t.getValueType()) {
                Object[] ret = resolveStringValue(t.getValue().toString(), c, prefix);
                cellvalue = ret[0];
                if (ret[1] != null) {
                    ((Name) ret[1]).setLocation(new Location(c.getTargetSheetIndex(), c.getTargetSheetName(), t.getRowIndex(), t.getColumnIndex()));
                    log(prefix, "in copyCell new location " + ((Name) ret[1]).getLocation());
                }
            } else {
                cellvalue = t.getValue();
            }
            if (cellvalue != null) {
                log(prefix, "before setValue value is " + cellvalue+ " type is "+cellvalue.getClass());
                if (cellvalue instanceof Formular) {
                    Formular f = (Formular) cellvalue;
                    if (f.getDefinition() != null && f.getDefinition().length() > 1) {
                        log(prefix, "set formular definition " + f.getDefinition());
                        t.setFormula("=" + optimizeFormular(f.getDefinition(), c.getTargetSheetName()));

                    } else {
                        log(prefix, "unresolved formular " + t.getValue().toString());
                    }
                } else {
                    t.setValue(cellvalue);
                }
            }
        }

        /**
         * @return [value,name,fullyResolved]
         */
        public Object[] resolveStringValue(String stringvalue, WorkBookContext c, String prefix) {
            Matcher matcher = pattern.matcher(stringvalue);
            if (matcher.matches()) {
                log(prefix, "before resolve name " + stringvalue);
                stringvalue = stringvalue.substring(1);
                Name name = resolveName(stringvalue, c);
                if (name != null) {
                    value = name.getValue();
                    log(prefix, "resolved name " + name);
                    return new Object[]{value, name, Boolean.TRUE};
                }
            }
            int start = 0;
            String replaced = null;
            boolean found = false;
            matcher.reset();
            boolean fullyResolved = true;
            while (matcher.find()) {
                found = true;
                String matcherGroup = matcher.group();
                log(prefix, "matcherGroup  " + matcherGroup);
                int startIdx = matcher.start();
                int endIdx = matcher.end();
                Name n = resolveName(matcherGroup.substring(1), c);
                replaced = (replaced == null ? "" : replaced) + stringvalue.substring(start, startIdx);
                replaced += n == null ? matcherGroup : n.getValue();
                start = endIdx;
                if (n == null) {
                    fullyResolved = false;
                }
            }
            if (found) {
                if (start < stringvalue.length()) {
                    replaced += stringvalue.substring(start);
                }
                value = replaced.replaceAll("\\\\&", "&");
            } else {
                value = stringvalue.replaceAll("\\\\&", "&");
            }
            log("replaced <" + stringvalue + "> with <" + value + ">");
            return new Object[]{value, null, Boolean.valueOf(fullyResolved)};
        }

        private String optimizeFormular(String def, String sheetName) {
            String result = def.replaceAll(sheetName + "\\!", "");
            log("in optimizeFormular " + def + " " + sheetName + " result " + result);
            return result;
        }

        public Name resolveName(String name, WorkBookContext c) {
            return resolveName(name, c, false);
        }

        public Name resolveName(String name, WorkBookContext c, boolean leftValue) {
            Name n = localNameSpace.resolveName(name, leftValue);
            if (n == null) {
                n = c.getNameSpace().resolveName(name, leftValue);
            }
            return n;
        }

//        public List resolveNameList(String name, WorkBookContext c) {
//            return resolveNameList(name, c );
//        }
//
        public List resolveNameList(String name, WorkBookContext c) {
            List l = localNameSpace.resolveNameList(name, c.isLeftValue());
            if (l == null || l.size() < 1) {
                l = c.getNameSpace().resolveNameList(name, c.isLeftValue());
            }
            return l;
        }
    }

    public static void main1(String[] args) {
        SimpleFormular sf = new SimpleFormular("=A10");
        Method m = getReadPropertyMethod(sf, "simple");
        System.out.println("is Simple " + m);

        Name root = new SimpleName("x", new SimpleFormular("=A10"), Name.NS_GLOBAL);
        Name prop1 = new BeanName("definition", Name.NS_GLOBAL);
        root.appendChild(prop1);
        System.out.println(prop1.stringValue());

        Name prop2 = new BeanName("simple", Name.NS_GLOBAL);
        root.appendChild(prop2);
        System.out.println(prop2.booleanValue());

        prop2.setValue(Boolean.FALSE);
        prop1.setValue("Desc");
        System.out.println(prop1.stringValue());
//        prop1.setValue("=B10");

        System.out.println(invokeMethod("Hello", "toString", null));
//        System.out.println(invokeMethod(new ExcelTest3.Tab("testinvoke"),"testMethod"));
    }

// start of Nodes
    static public class ASTAdditiveExpression extends ExcelNode {

        public ASTAdditiveExpression(int id) {
            super(id);
        }

        public ASTAdditiveExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=8d0dbedcdad0a7a810c215a517e4790e (do not edit this line) */

    static public class ASTAllocationExpression extends ExcelNode {

        public ASTAllocationExpression(int id) {
            super(id);
        }

        public ASTAllocationExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=2c043147dbb94c56a899c8e8528d31be (do not edit this line) */

    static public class ASTAndExpression extends ExcelNode {

        public ASTAndExpression(int id) {
            super(id);
        }

        public ASTAndExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=fd8c2992f358cdfd5fea608cbfa7bada (do not edit this line) */

    static public class ASTArgumentList extends ExcelNode {

        public ASTArgumentList(int id) {
            super(id);
        }

        public ASTArgumentList(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            for (int i = 0; i < getChildrenCount(); i++) {
                at(i).apply(c, prefix + " ");
            }
            stack.push(new Integer(getChildrenCount()));
            return;
        }
    }
    /* JavaCC - OriginalChecksum=ce131cfed25b109b78b7c2565e1beab9 (do not edit this line) */

    static public class ASTArguments extends ExcelNode {

        public ASTArguments(int id) {
            super(id);
        }

        public ASTArguments(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            if (getChildrenCount() == 0) {
                stack.push(null);
                return;
            }
            if (getChildrenCount() != 1) {
                throw new RuntimeException("Invalid Arguments Node");
            }
            at(0).apply(c, prefix + " ");
            Integer argsCount = (Integer) stack.pop();
            Object[] args = new Object[argsCount.intValue()];
            for (int i = 0; i < args.length; i++) {
                args[argsCount.intValue() - 1 - i] = stack.pop();
            }
            stack.push(args);
            log(prefix, "in ASTArguments " + toStringHelper.toString(args));
            return;
        }
    }
    /* JavaCC - OriginalChecksum=a62f2c604967f08980aee2e0ec526c3e (do not edit this line) */

    static public class ASTArrayDimensions extends ExcelNode {

        public ASTArrayDimensions(int id) {
            super(id);
        }

        public ASTArrayDimensions(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=f7f789f2f760b4add307b09952cfe08a (do not edit this line) */

    static public class ASTAssignment extends ExcelNode {

        public ASTAssignment(int id) {
            super(id);
        }

        public ASTAssignment(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            at(2).apply(c, prefix + " ");
            Object rvalue = stack.pop();
            Object operator = stack.pop();
            Name lvalue = (Name) stack.pop();
            log(prefix, "in ASTAssignment lvalue is " + lvalue + " operator is " + operator + "  rvalue is " + rvalue);
            if (!"=".equals(operator)) {
                throw new RuntimeException("Invalid Operator " + operator);
            }
            if (rvalue != null) {
                if (lvalue == null || lvalue instanceof NullName) {
                    lvalue = localNameProvider.addSimpleName(lvalue.getName(), rvalue instanceof Name ? ((Name) rvalue).getValue() : rvalue);
                    log(prefix, "local name not found created new " + lvalue);
                } else {
                    log(prefix, "found local name " + lvalue);
                    if (lvalue instanceof FormularName && rvalue instanceof Name && ((Name) rvalue).getLocation() != null) {
                        lvalue.setValue(new SimpleFormular(((Name) rvalue).getLocation().toCellName()));
                    } else {
                        lvalue.setValue(rvalue instanceof Name ? ((Name) rvalue).getValue() : rvalue);
                    }
                    log(prefix, "after set new value name " + lvalue);
                }
            } else {
                log("invalid rvalue.", prefix + " ");
            }
            c.resetBinding(localNameSpace);

            return;

        }
    }
    /* JavaCC - OriginalChecksum=cae2b334e54707e380a280a06fddeb7a (do not edit this line) */

    static public class ASTAssignmentOperator extends ExcelNode {

        public ASTAssignmentOperator(int id) {
            super(id);
        }

        public ASTAssignmentOperator(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            stack.push(jjtGetValue());
            return;
        }
    }
    /* JavaCC - OriginalChecksum=96542819c92a3583bbadff719e968cc1 (do not edit this line) */

    static public class ASTBlock extends ExcelNode {

        public ASTBlock(int id) {
            super(id);
        }

        public ASTBlock(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=7b08c398392cfb38b4d4c51ceb6ce3f8 (do not edit this line) */

    static public class ASTBlockStatement extends ExcelNode {

        public ASTBlockStatement(int id) {
            super(id);
        }

        public ASTBlockStatement(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=cecad108bf91d3111795889e5c73d6f3 (do not edit this line) */

    static public class ASTBooleanLiteral extends ExcelNode {

        public ASTBooleanLiteral(int id) {
            super(id);
        }

        public ASTBooleanLiteral(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=13ea791e11384c5fe549d9aa9d6bc0fb (do not edit this line) */

    static public class ASTCastExpression extends ExcelNode {

        public ASTCastExpression(int id) {
            super(id);
        }

        public ASTCastExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=f5cfe30e8dc4bec265795d3d0e19e3c6 (do not edit this line) */

    static public class ASTCastLookahead extends ExcelNode {

        public ASTCastLookahead(int id) {
            super(id);
        }

        public ASTCastLookahead(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=e27f3bbe26285284e8b0dfac06fd4360 (do not edit this line) */

    static public class ASTCell extends ExcelNode {

        public ASTCell(int id) {
            super(id);
        }

        public ASTCell(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            log(prefix, "template cell at " + c.getTemplateSheetIndex() + " " + cellName(rowIndex, columnIndex));
            Cell cell = c.moveTo(sheetIndex, rowIndex, columnIndex);
            log(prefix, "target cell " + cell);

            if (cell != null) {
                if (endRow > 0 || endCol > 0) {
                    c.mergeCell(endRow, endCol);
                }
                Cell sourceCell = c.getSourceCell(rowIndex, columnIndex);
                localNameProvider.addSimpleName("cell", cell);
                copyCell(sourceCell, cell, c, prefix, false);
            }
        }
    }
    /* JavaCC - OriginalChecksum=111fc8067fdeb9f24c1104655ae44956 (do not edit this line) */

    static public class ASTConditionalAndExpression extends ExcelNode {

        public ASTConditionalAndExpression(int id) {
            super(id);
        }

        public ASTConditionalAndExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=09121e569d5fd0ff8f7fa77f89195f41 (do not edit this line) */

    static public class ASTConditionalExpression extends ExcelNode {

        public ASTConditionalExpression(int id) {
            super(id);
        }

        public ASTConditionalExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=656bda791482aca20db398dc3a1e2fc2 (do not edit this line) */

    static public class ASTConditionalOrExpression extends ExcelNode {

        public ASTConditionalOrExpression(int id) {
            super(id);
        }

        public ASTConditionalOrExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=d24c969c8bdb4ad2b3824ff5f420d906 (do not edit this line) */

    static public class ASTEqualityExpression extends ExcelNode {

        public ASTEqualityExpression(int id) {
            super(id);
        }

        public ASTEqualityExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=3b082a977a3ae2541fd1406db7f8057c (do not edit this line) */

    static public class ASTExclusiveOrExpression extends ExcelNode {

        public ASTExclusiveOrExpression(int id) {
            super(id);
        }

        public ASTExclusiveOrExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=ea75079abc21f83fd586c93125a37276 (do not edit this line) */

    static public class ASTExpression extends ExcelNode {

        public ASTExpression(int id) {
            super(id);
        }

        public ASTExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=cc30a0d88569b6b7e0790f54661f1db2 (do not edit this line) */

    static public class ASTForStatement extends ExcelNode {

        public ASTForStatement(int id) {
            super(id);
        }

        public ASTForStatement(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            String name = (String) at(0).jjtGetValue();
            at(1).apply(c, prefix + " ");
            Name forname = (Name) stack.pop();
            if (forname == null) {
                log(c.globalNameProvider.dump());
                dumpNames();
                throw new RuntimeException("Invalid name :" + at(1).at(0) + " in for statement");
            }
            Object forvalue = forname.getValue();
            ExcelNode block = at(2);
            if (forvalue != null && !(forvalue instanceof List)) {
                List l = new ArrayList();
                l.add(forvalue);
                forvalue = l;
            }
            if (forvalue instanceof List) {
                Iterator i = ((List) forvalue).iterator();
                int idx = 0;
                while (i.hasNext()) {
                    log("run loop at " + idx++);
                    Object loopvar = i.next();
                    Name loopname = block.getNameProvider().addSimpleName(name, loopvar);
                    log("loopname is " + loopname);
                    forname.appendChild(loopname);
                    block.apply(c, prefix + " ");
                    loopname.removeMe();
                }
            }
            ((ExcelNode) block).getNameProvider().removeAll();
            localNameProvider.removeAll();
            c.resetBinding(localNameSpace);
        }
    }
    /* JavaCC - OriginalChecksum=e5fb54e42186dc8ed61d596cfa7e4b4a (do not edit this line) */

    static public class ASTFunction extends ExcelNode {

        public ASTFunction(int id) {
            super(id);
        }

        public ASTFunction(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=67c29a5318422107cddeb1088a87e417 (do not edit this line) */

    static public class ASTID extends ExcelNode {

        public ASTID(int id) {
            super(id);
        }

        public ASTID(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=3b18cce0cab0e71a45d3c6169b783a3d (do not edit this line) */

    static public class ASTIfStatement extends ExcelNode {

        public ASTIfStatement(int id) {
            super(id);
        }

        public ASTIfStatement(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            at(0).apply(c, prefix + " ");
            Object value = stack.pop();
            if (isTrue(value)) {
                log(prefix, "run if block.");
                at(1).apply(c, prefix + " ");
            } else if (at(2) != null) {
                log(prefix, "run else block.");
                at(2).apply(c, prefix + " ");
            }
        }
    }

    static public class ASTWhileStatement extends ExcelNode {

        public ASTWhileStatement(int id) {
            super(id);
        }

        public ASTWhileStatement(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            do {
                at(0).apply(c, prefix + " ");
                Object value = stack.pop();
                if (isTrue(value)) {
                    log(prefix, "run while block.");
                    at(1).apply(c, prefix + " ");
                } else {
                    break;
                }

            } while (true);
        }
    }
    /* JavaCC - OriginalChecksum=ede03a36ae2be2bbd0c921d8f8cfd511 (do not edit this line) */

    static public class ASTInclusiveOrExpression extends ExcelNode {

        public ASTInclusiveOrExpression(int id) {
            super(id);
        }

        public ASTInclusiveOrExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=fb0606b162ac97694491bb5adca27afc (do not edit this line) */

    static public class ASTInput extends ExcelNode {

        public ASTInput(int id) {
            super(id);
        }

        public ASTInput(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=eed670e121f655b8797463c09b664694 (do not edit this line) */

    static public class ASTInstanceOfExpression extends ExcelNode {

        public ASTInstanceOfExpression(int id) {
            super(id);
        }

        public ASTInstanceOfExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=6a7b913a2eac6ed0315ad6ee149f61ae (do not edit this line) */

    static public class ASTLiteral extends ExcelNode {

        public ASTLiteral(int id) {
            super(id);
        }

        public ASTLiteral(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            stack.push(jjtGetValue());
            return;
        }
    }
    /* JavaCC - OriginalChecksum=8a431173407488a9582b3e17a80ef8b6 (do not edit this line) */

    static public class ASTMultiplicativeExpression extends ExcelNode {

        public ASTMultiplicativeExpression(int id) {
            super(id);
        }

        public ASTMultiplicativeExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=d2718a0d46ac2c2e21f0bc1cea15eecf (do not edit this line) */

    static public class ASTName extends ExcelNode {

        public ASTName(int id) {
            super(id);
        }

        public ASTName(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            List l = resolveNameList((String) jjtGetValue(), c);
            if (l != null && l.size() == 1) {
                Name idname = (Name) l.get(0);
                /*              if (idname != null && idname.getValue() instanceof EmptyFormular) {
                why?                stack.push(new NullName((String) jjtGetValue(), Name.NS_LOCAL));
                return;
                }
                 */
                stack.push(idname);
                return;
            } else {
                if (l.size() == 0) {
                    stack.push(new NullName((String) jjtGetValue(), Name.NS_LOCAL));
                } else {
                    stack.push(new MultiName((String) jjtGetValue(), l, Name.NS_LOCAL));
                }
                return;
            }
        }
    }
    /* JavaCC - OriginalChecksum=544fac62969e4f50d1d023fa5ea5465d (do not edit this line) */

    static public class ASTPostfixExpression extends ExcelNode {

        public ASTPostfixExpression(int id) {
            super(id);
        }

        public ASTPostfixExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=d769991731e75355e7822f16fcc80554 (do not edit this line) */

    static public class ASTPreDecrementExpression extends ExcelNode {

        public ASTPreDecrementExpression(int id) {
            super(id);
        }

        public ASTPreDecrementExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=045c91be02dfe14f489aeaf5f7ec79ce (do not edit this line) */

    static public class ASTPreIncrementExpression extends ExcelNode {

        public ASTPreIncrementExpression(int id) {
            super(id);
        }

        public ASTPreIncrementExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=bd5abb89eb3556e132e9b7c8f50207ee (do not edit this line) */

    static public class ASTPrimaryExpression extends ExcelNode {

        public ASTPrimaryExpression(int id) {
            super(id);
        }

        public ASTPrimaryExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=b606098cb2176160400306590c522499 (do not edit this line) */

    static public class ASTPrimaryPrefix extends ExcelNode {

        public ASTPrimaryPrefix(int id) {
            super(id);
        }

        public ASTPrimaryPrefix(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            c.setLeftValue(true);
            super.apply(c, prefix);
            c.setLeftValue(false);
        }
    }
    /* JavaCC - OriginalChecksum=4dedab298e4734a197a6d97de3ddbba0 (do not edit this line) */

    static public class ASTPrimarySuffix extends ExcelNode {

        public ASTPrimarySuffix(int id) {
            super(id);
        }

        public ASTPrimarySuffix(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {

            super.apply(c, prefix);
        }
    }
    /* JavaCC - OriginalChecksum=83e913e4673c459adc189823fa453359 (do not edit this line) */

    static public class ASTPrimitiveType extends ExcelNode {

        public ASTPrimitiveType(int id) {
            super(id);
        }

        public ASTPrimitiveType(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=5a230b6850134e9675d8ccd653fe81da (do not edit this line) */

    static public class ASTRelationalExpression extends ExcelNode {

        public ASTRelationalExpression(int id) {
            super(id);
        }

        public ASTRelationalExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=949a72966a2625d8e6f95e2dc4ba808f (do not edit this line) */

    static public class ASTResultType extends ExcelNode {

        public ASTResultType(int id) {
            super(id);
        }

        public ASTResultType(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=27e2a263646ff8b830ba94d7a3dcaa3c (do not edit this line) */

    static public class ASTRowBreak extends ExcelNode {

        public ASTRowBreak(int id) {
            super(id);
        }

        public ASTRowBreak(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
//                    case JJTROWBREAK: {
            c.moveToNextRow();
        }
    }
    /* JavaCC - OriginalChecksum=ad2dba32c5cad42080d07b712b80719e (do not edit this line) */

    static public class ASTShiftExpression extends ExcelNode {

        public ASTShiftExpression(int id) {
            super(id);
        }

        public ASTShiftExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=b98752936fd57162cd158674c59ad9af (do not edit this line) */

    static public class ASTStatement extends ExcelNode {

        public ASTStatement(int id) {
            super(id);
        }

        public ASTStatement(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=7040a51b1a092280ceabc7dbade2a89e (do not edit this line) */

    static public class ASTStatementExpression extends ExcelNode {

        public ASTStatementExpression(int id) {
            super(id);
        }

        public ASTStatementExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=066f4f11916192bbf99f2d55df9b46e6 (do not edit this line) */

    static public class ASTType extends ExcelNode {

        public ASTType(int id) {
            super(id);
        }

        public ASTType(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=eb163c3c12a628ab695be4f44b77c14d (do not edit this line) */

    static public class ASTUnaryExpression extends ExcelNode {

        public ASTUnaryExpression(int id) {
            super(id);
        }

        public ASTUnaryExpression(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=1a7c181a0eb8a100264099a34f59a4ce (do not edit this line) */

    static public class ASTUnaryExpressionNotPlusMinus extends ExcelNode {

        public ASTUnaryExpressionNotPlusMinus(int id) {
            super(id);
        }

        public ASTUnaryExpressionNotPlusMinus(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=77e665478f9671da683ba96b1717ad9d (do not edit this line) */

    static public class ASTVariable extends ExcelNode {

        public ASTVariable(int id) {
            super(id);
        }

        public ASTVariable(Excel p, int id) {
            super(p, id);
        }
    }
    /* JavaCC - OriginalChecksum=4dba5051ace2712c73f56cd2d2561a73 (do not edit this line) */

    static public class ASTWorkBook extends ExcelNode {

        public ASTWorkBook(int id) {
            super(id);
        }

        public ASTWorkBook(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            Workbook wb= canon.excel.cells.CellsHelper.newWorkbook();
            wb.getWorksheets().removeSheet(0);
            c.setTarget(wb);
            localNameProvider.addSimpleName("workbook", wb);
            localNameProvider.addSimpleName("context", c);
            localNameProvider.addSimpleName("tools", c);
            super.apply(c, prefix);
        }
    }
    /* JavaCC - OriginalChecksum=4936816b7f44d572ac0958df6ad86776 (do not edit this line) */

    static public class ASTWorkSheet extends ExcelNode {

        public ASTWorkSheet(int id) {
            super(id);
        }

        public ASTWorkSheet(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            c.moveToNextSheet(sheetIndex);
            localNameProvider.addSimpleName("sheet", c.getCurrentWorksheet());
            localNameProvider.addSimpleName("page", new Integer(c.getTargetSheetIndex() + 1));
            super.apply(c, prefix);
        }
    }

    static class MyNumber {

        Number n;

        MyNumber(Number n) {
            this.n = n;
        }

        MyNumber promote() {
            if (n instanceof BigDecimal) {
                return MyNumber.this;
            }
            return new MyNumber(toBigDecimal(n.toString(), false));
        }

        MyNumber add(MyNumber b) {
            if (n instanceof Integer && b.n instanceof Integer) {
                return new MyNumber(new Integer(n.intValue() + b.n.intValue()));
            }
            if (n instanceof BigDecimal && b.n instanceof BigDecimal) {
                return new MyNumber(((BigDecimal) n).add((BigDecimal) b.n));
            }
            if (n instanceof BigDecimal || b.n instanceof BigDecimal) {
                return (this).promote().add(b.promote());
            }
            throw new RuntimeException("Unsupported number add " + n + " + " + b.n);
        }

        MyNumber substract(MyNumber b) {
            if (n instanceof Integer && b.n instanceof Integer) {
                return new MyNumber(new Integer(n.intValue() - b.n.intValue()));
            }
            if (n instanceof BigDecimal && b.n instanceof BigDecimal) {
                return new MyNumber(((BigDecimal) n).subtract((BigDecimal) b.n));
            }
            if (n instanceof BigDecimal || b.n instanceof BigDecimal) {
                return (this).promote().substract(b.promote());
            }
            throw new RuntimeException("Unsupported number add " + n + " + " + b.n);
        }

        MyNumber multi(MyNumber b) {
            if (n instanceof Integer && b.n instanceof Integer) {
                return new MyNumber(new Integer(n.intValue() * b.n.intValue()));
            }
            if (n instanceof BigDecimal && b.n instanceof BigDecimal) {
                return new MyNumber(((BigDecimal) n).multiply((BigDecimal) b.n));
            }
            if (n instanceof BigDecimal || b.n instanceof BigDecimal) {
                return (this).promote().multi(b.promote());
            }
            throw new RuntimeException("Unsupported number add " + n + " + " + b.n);
        }

        MyNumber div(MyNumber b) {
            if (n instanceof Integer && b.n instanceof Integer) {
                return new MyNumber(new Integer(n.intValue() / b.n.intValue()));
            }
            if (n instanceof BigDecimal && b.n instanceof BigDecimal) {
                return new MyNumber(((BigDecimal) n).divide((BigDecimal) b.n, BigDecimal.ROUND_HALF_UP));
            }
            if (n instanceof BigDecimal || b.n instanceof BigDecimal) {
                return (this).promote().div(b.promote());
            }
            throw new RuntimeException("Unsupported number add " + n + " + " + b.n);
        }

        static MyNumber valueOf(Object o) {
            if (o instanceof Number) {
                Number n = (Number) o;
                if (n instanceof Integer) {
                    return new MyNumber(n);
                }
                return new MyNumber(toNumber(n));
            }
            if (o instanceof Name) {
                Name n = (Name) o;
                return new MyNumber(toNumber(n.getValue()));
            }
            throw new RuntimeException("Unsupported number " + o);
        }
    }

    static class MyBoolean {

        Boolean b;

        MyBoolean(Boolean b) {
            this.b = b;
        }

        MyBoolean and(MyBoolean mb) {
            return new MyBoolean(Boolean.valueOf(b.booleanValue() && mb.b.booleanValue()));
        }

        MyBoolean or(MyBoolean mb) {
            return new MyBoolean(Boolean.valueOf(b.booleanValue() || mb.b.booleanValue()));
        }

        MyBoolean not() {
            return new MyBoolean(Boolean.valueOf(!b.booleanValue()));
        }

        static MyBoolean valueOf(Object o) {
            if (o instanceof Boolean) {
                return new MyBoolean((Boolean) o);
            }
            if (o instanceof Name) {
                Name n = (Name) o;
                return new MyBoolean(toBoolean(n.getValue()));
            }
            throw new RuntimeException("Unsupported boolean " + o);
        }
    }

    static Formular toFormular(Object o) {
        if (o instanceof Formular) {
            return (Formular) o;
        }
        if (o instanceof Name) {
            Name n = (Name) o;
            if (n instanceof FormularName) {
                return ((FormularName) n).getFormular();
            }
            if (n.getLocation() != null) {
                return new SimpleFormular(n.getLocation().toCellName());
            }
        }
//        throw new RuntimeException("Unsupported formular add");
        return new EmptyFormular();
    }

    static public class ASTAddNode extends ExcelNode {

        public ASTAddNode(int id) {
            super(id);
        }

        public ASTAddNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(add(o1, o2));
        }

        Object add(Object o1, Object o2) {
            if (o1 instanceof Number || o2 instanceof Number) {
                MyNumber mn = MyNumber.valueOf(o1).add(MyNumber.valueOf(o2));
                return mn.n;
            } else if (o1 instanceof String && o2 instanceof String) {
                return (o1 != null ? o1.toString() : "") + (o2 != null ? o2.toString() : "");
            } else {
                return (toFormular(o1).add(toFormular(o2)));
            }
        }
    }

    public static Boolean toBoolean(Object o) {
        if (o instanceof Boolean) {
            return (Boolean) o;
        }
        if (o != null) {
            String s = (String) o.toString();
            if (s != null) {
                String ns = s.trim();
                return Boolean.valueOf(ns);
            }
        }
        return null;
    }

    public static Number toNumber(Object o) {
        if (o instanceof Number) {
            return (Number) o;
        }
        if (o != null) {
            String s = (String) o.toString();
            if (s != null) {
                String ns = s.trim();
                try {
                    return Integer.valueOf(ns);
                } catch (NumberFormatException e) {
                    return toBigDecimal(ns, false);
                }
            }
        }
        return null;
    }
    static BigDecimal BigDecimal_ZERO = new BigDecimal("0");

    public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
        if (s != null && s.trim().length() > 0) {
            try {
                return new BigDecimal(s.trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return nullToZero ? BigDecimal_ZERO : null;
    }

    static public class ASTSubtractNode extends ExcelNode {

        public ASTSubtractNode(int id) {
            super(id);
        }

        public ASTSubtractNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(substract(o1, o2));
        }

        Object substract(Object o1, Object o2) {
            if (o1 instanceof Number || o2 instanceof Number) {
                MyNumber mn = MyNumber.valueOf(o1).substract(MyNumber.valueOf(o2));
                return mn.n;
            } else if (o1 instanceof String && o2 instanceof String) {
                return (o1 != null ? o1.toString() : "") + (o2 != null ? o2.toString() : "");
            } else {
                return (toFormular(o1).substract(toFormular(o2)));
            }
        }
    }

    static public class ASTMulNode extends ExcelNode {

        public ASTMulNode(int id) {
            super(id);
        }

        public ASTMulNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(multi(o1, o2));
        }

        Object multi(Object o1, Object o2) {
            if (o1 instanceof Number || o2 instanceof Number) {
                MyNumber mn = MyNumber.valueOf(o1).multi(MyNumber.valueOf(o2));
                return mn.n;
            } else if (o1 instanceof String && o2 instanceof String) {
                return (o1 != null ? o1.toString() : "") + (o2 != null ? o2.toString() : "");
            } else {
                return (toFormular(o1).multi(toFormular(o2)));
            }
        }
    }

    static public class ASTDivNode extends ExcelNode {

        public ASTDivNode(int id) {
            super(id);
        }

        public ASTDivNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(div(o1, o2));
        }

        Object div(Object o1, Object o2) {
            if (o1 instanceof Number || o2 instanceof Number) {
                MyNumber mn = MyNumber.valueOf(o1).div(MyNumber.valueOf(o2));
                return mn.n;
            } else if (o1 instanceof String && o2 instanceof String) {
                return (o1 != null ? o1.toString() : "") + (o2 != null ? o2.toString() : "");
            } else {
                return (toFormular(o1).div(toFormular(o2)));
            }
        }
    }

    static public class ASTModNode extends ExcelNode {

        public ASTModNode(int id) {
            super(id);
        }

        public ASTModNode(Excel p, int id) {
            super(p, id);
        }
    }

    static public class ASTAndNode extends ExcelNode {

        public ASTAndNode(int id) {
            super(id);
        }

        public ASTAndNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(conditionAnd(o1, o2));
        }

        Object conditionAnd(Object o1, Object o2) {
            MyBoolean mn = MyBoolean.valueOf(o1).and(MyBoolean.valueOf(o2));
            return mn.b;
        }
    }

    static public class ASTOrNode extends ExcelNode {

        public ASTOrNode(int id) {
            super(id);
        }

        public ASTOrNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(conditionOr(o1, o2));
        }

        Object conditionOr(Object o1, Object o2) {
            MyBoolean mn = MyBoolean.valueOf(o1).or(MyBoolean.valueOf(o2));
            return mn.b;
        }
    }

    static public class ASTNotNode extends ExcelNode {

        public ASTNotNode(int id) {
            super(id);
        }

        public ASTNotNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            Object o1 = stack.pop();
            stack.push(conditionNot(o1));
        }

        Object conditionNot(Object o1) {
            MyBoolean mn = MyBoolean.valueOf(o1).not();
            return mn.b;
        }
    }

    static public class ASTMethodName extends ExcelNode {

        public ASTMethodName(int id) {
            super(id);
        }

        public ASTMethodName(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            stack.push(ASTMethodName.this);
        }
    }

    static public class ASTMethodInvocation extends ExcelNode {

        public ASTMethodInvocation(int id) {
            super(id);
        }

        public ASTMethodInvocation(Excel p, int id) {
            super(p, id);
        }

        Formular sum(Object arguments[], WorkBookContext c, String prefix) {
            List l = new ArrayList();
            for (int i = 0; i < arguments.length; i++) {
                Object o = arguments[i];
                if (!(o instanceof Name)) {
                    throw new RuntimeException("Invalid arguments in sum");
                }
                Name n = (Name) o;
                if (n instanceof MultiName) {
                    MultiName m = (MultiName) n;
                    l.addAll(m.getList());
                } else {
                    l.add(n);
                }
            }
            String formular = null;
            for (int idx = 0; idx < l.size(); idx++) {
                Name n = (Name) l.get(idx);
                log(prefix, " in eval formular name is " + n);
                log(prefix, " in eval formular location is " + n.getLocation());
                if (n.getLocation() != null) {
                    String fx = n.getLocation().toCellName();
                    if (formular == null || formular.length() == 1) {
                        formular = fx;
                    } else {
                        formular += "+" + fx;
                    }
                }
            }
            return formular == null ? null : new SimpleFormular(formular);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            Object arguments[] = (Object[]) stack.pop();
            Object target = stack.pop();
            String methodName = null;
            if (target instanceof ASTSum) {
                stack.push(sum(arguments, c, prefix));
                return;
            } else if (target instanceof ASTMethodName) {
                methodName = (String) ((ASTMethodName) target).jjtGetValue();
                target = stack.pop();
            } else if (target instanceof NullName) {
                Name n = (Name) target;
                target = n.getParent();
                methodName = n.getName();
            }
            if (target instanceof Name) {
                target = ((Name) target).getValue();
            }
            if (methodName != null && target != null) {
                if (arguments != null) {
                    Object args[] = new Object[arguments.length];
                    for (int i = 0; i < arguments.length; i++) {
                        // 
                        args[i] = (arguments[i] instanceof Name) ? ((Name) arguments[i]).getValue() : arguments[i];
                    }
                    arguments = args;
                }
                Object result = invokeMethod(target, methodName, arguments);
                log(prefix, "ASTMethodInvocation result is " + result);
                stack.push(result);
            } else {
                throw new RuntimeException("Invalid PrimarySuffix");
            }

        }
    }

    static public class ASTSum extends ExcelNode {

        public ASTSum(int id) {
            super(id);
        }

        public ASTSum(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            stack.push(ASTSum.this);
        }
    }

    static class MyComparable {

        Comparable c;

        MyComparable(Comparable c) {
            this.c = c;
        }

        boolean lt(MyComparable b) {
            return c.compareTo(b.c) < 0;
        }

        boolean gt(MyComparable b) {
            return c.compareTo(b.c) > 0;
        }

        boolean equal(MyComparable b) {
            return c.compareTo(b.c) == 0;
        }

        static MyComparable valueOf(Object o) {
            if (o instanceof Comparable) {
                return new MyComparable((Comparable) o);
            }
            if (o instanceof Name) {
                Name n = (Name) o;
                return new MyComparable((Comparable) n.getValue());
            }
            throw new RuntimeException("Unsupported boolean " + o);
        }
    }

    static public class ASTLTNode extends ExcelNode {

        public ASTLTNode(int id) {
            super(id);
        }

        public ASTLTNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(lt(o1, o2));
        }

        Object lt(Object o1, Object o2) {
            MyComparable m1 = MyComparable.valueOf(o1);
            MyComparable m2 = MyComparable.valueOf(o2);
            return Boolean.valueOf(m1.lt(m2));
        }
    }

    static public class ASTGTNode extends ExcelNode {

        public ASTGTNode(int id) {
            super(id);
        }

        public ASTGTNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(gt(o1, o2));
        }

        Object gt(Object o1, Object o2) {
            MyComparable m1 = MyComparable.valueOf(o1);
            MyComparable m2 = MyComparable.valueOf(o2);
            return Boolean.valueOf(m1.gt(m2));
        }
    }

    static public class ASTLENode extends ExcelNode {

        public ASTLENode(int id) {
            super(id);
        }

        public ASTLENode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(le(o1, o2));
        }

        Object le(Object o1, Object o2) {
            MyComparable m1 = MyComparable.valueOf(o1);
            MyComparable m2 = MyComparable.valueOf(o2);
            return Boolean.valueOf(m1.lt(m2) || m1.equal(m2));
        }
    }

    static public class ASTGENode extends ExcelNode {

        public ASTGENode(int id) {
            super(id);
        }

        public ASTGENode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(le(o1, o2));
        }

        Object le(Object o1, Object o2) {
            MyComparable m1 = MyComparable.valueOf(o1);
            MyComparable m2 = MyComparable.valueOf(o2);
            return Boolean.valueOf(m1.gt(m2) || m1.equal(m2));
        }
    }

    static public class ASTEQNode extends ExcelNode {

        public ASTEQNode(int id) {
            super(id);
        }

        public ASTEQNode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(le(o1, o2));
        }

        Object le(Object o1, Object o2) {
            MyComparable m1 = MyComparable.valueOf(o1);
            MyComparable m2 = MyComparable.valueOf(o2);
            return Boolean.valueOf(m1.equal(m2));
        }
    }

    static public class ASTNENode extends ExcelNode {

        public ASTNENode(int id) {
            super(id);
        }

        public ASTNENode(Excel p, int id) {
            super(p, id);
        }

        public void apply(WorkBookContext c, String prefix) {
            log(toString(prefix));
            at(0).apply(c, prefix + " ");
            at(1).apply(c, prefix + " ");
            Object o2 = stack.pop();
            Object o1 = stack.pop();
            stack.push(le(o1, o2));
        }

        Object le(Object o1, Object o2) {
            MyComparable m1 = MyComparable.valueOf(o1);
            MyComparable m2 = MyComparable.valueOf(o2);
            return Boolean.valueOf(!m1.equal(m2));
        }
    }
}

static public class TokenMgrError extends Error
{

  /**
   * The version identifier for this Serializable class.
   * Increment only if the <i>serialized</i> form of the
   * class changes.
   */
  private static final long serialVersionUID = 1L;

  /*
   * Ordinals for various reasons why an Error of this type can be thrown.
   */

  /**
   * Lexical error occurred.
   */
  static final int LEXICAL_ERROR = 0;

  /**
   * An attempt was made to create a second instance of a static token manager.
   */
  static final int STATIC_LEXER_ERROR = 1;

  /**
   * Tried to change to an invalid lexical state.
   */
  static final int INVALID_LEXICAL_STATE = 2;

  /**
   * Detected (and bailed out of) an infinite loop in the token manager.
   */
  static final int LOOP_DETECTED = 3;

  /**
   * Indicates the reason why the exception is thrown. It will have
   * one of the above 4 values.
   */
  int errorCode;

  /**
   * Replaces unprintable characters by their escaped (or unicode escaped)
   * equivalents in the given string
   */
  protected static final String addEscapes(String str) {
    StringBuffer retval = new StringBuffer();
    char ch;
    for (int i = 0; i < str.length(); i++) {
      switch (str.charAt(i))
      {
        case 0 :
          continue;
        case '\b':
          retval.append("\\b");
          continue;
        case '\t':
          retval.append("\\t");
          continue;
        case '\n':
          retval.append("\\n");
          continue;
        case '\f':
          retval.append("\\f");
          continue;
        case '\r':
          retval.append("\\r");
          continue;
        case '\"':
          retval.append("\\\"");
          continue;
        case '\'':
          retval.append("\\\'");
          continue;
        case '\\':
          retval.append("\\\\");
          continue;
        default:
          if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
            String s = "0000" + Integer.toString(ch, 16);
            retval.append("\\u" + s.substring(s.length() - 4, s.length()));
          } else {
            retval.append(ch);
          }
          continue;
      }
    }
    return retval.toString();
  }

  /**
   * Returns a detailed message for the Error when it is thrown by the
   * token manager to indicate a lexical error.
   * Parameters :
   *    EOFSeen     : indicates if EOF caused the lexical error
   *    curLexState : lexical state in which this error occurred
   *    errorLine   : line number when the error occurred
   *    errorColumn : column number when the error occurred
   *    errorAfter  : prefix that was seen before this error occurred
   *    curchar     : the offending character
   * Note: You can customize the lexical error message by modifying this method.
   */
  protected static String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
    return("Lexical error at line " +
          errorLine + ", column " +
          errorColumn + ".  Encountered: " +
          (EOFSeen ? "<EOF> " : ("\"" + addEscapes(String.valueOf(curChar)) + "\"") + " (" + (int)curChar + "), ") +
          "after : \"" + addEscapes(errorAfter) + "\"");
  }

  /**
   * You can also modify the body of this method to customize your error messages.
   * For example, cases like LOOP_DETECTED and INVALID_LEXICAL_STATE are not
   * of end-users concern, so you can return something like :
   *
   *     "Internal Error : Please file a bug report .... "
   *
   * from this method for such cases in the release version of your parser.
   */
  public String getMessage() {
    return super.getMessage();
  }

  /*
   * Constructors of various flavors follow.
   */

  /** No arg constructor. */
  public TokenMgrError() {
  }

  /** Constructor with message and reason. */
  public TokenMgrError(String message, int reason) {
    super(message);
    errorCode = reason;
  }

  /** Full Constructor. */
  public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
    this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
  }
}
/* JavaCC - OriginalChecksum=96bc0b5c5dbf65c493e0535a85fffd8d (do not edit this line) */

static public class Token implements java.io.Serializable {

  /**
   * The version identifier for this Serializable class.
   * Increment only if the <i>serialized</i> form of the
   * class changes.
   */
  private static final long serialVersionUID = 1L;

  /**
   * An integer that describes the kind of this token.  This numbering
   * system is determined by JavaCCParser, and a table of these numbers is
   * stored in the file ...Constants.java.
   */
  public int kind;

  /** The line number of the first character of this Token. */
  public int beginLine;
  /** The column number of the first character of this Token. */
  public int beginColumn;
  /** The line number of the last character of this Token. */
  public int endLine;
  /** The column number of the last character of this Token. */
  public int endColumn;

  /**
   * The string image of the token.
   */
  public String image;

  /**
   * A reference to the next regular (non-special) token from the input
   * stream.  If this is the last token from the input stream, or if the
   * token manager has not read tokens beyond this one, this field is
   * set to null.  This is true only if this token is also a regular
   * token.  Otherwise, see below for a description of the contents of
   * this field.
   */
  public Token next;

  /**
   * This field is used to access special tokens that occur prior to this
   * token, but after the immediately preceding regular (non-special) token.
   * If there are no such special tokens, this field is set to null.
   * When there are more than one such special token, this field refers
   * to the last of these special tokens, which in turn refers to the next
   * previous special token through its specialToken field, and so on
   * until the first special token (whose specialToken field is null).
   * The next fields of special tokens refer to other special tokens that
   * immediately follow it (without an intervening regular token).  If there
   * is no such token, this field is null.
   */
  public Token specialToken;

  /**
   * An optional attribute value of the Token.
   * Tokens which are not used as syntactic sugar will often contain
   * meaningful values that will be used later on by the compiler or
   * interpreter. This attribute value is often different from the image.
   * Any subclass of Token that actually wants to return a non-null value can
   * override this method as appropriate.
   */
  public Object getValue() {
    return null;
  }

  /**
   * No-argument constructor
   */
  public Token() {}

  /**
   * Constructs a new token for the specified Image.
   */
  public Token(int kind)
  {
    this(kind, null);
  }

  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public Token(int kind, String image)
  {
    this.kind = kind;
    this.image = image;
  }

  /**
   * Returns the image.
   */
  public String toString()
  {
    return image;
  }

  /**
   * Returns a new Token object, by default. However, if you want, you
   * can create and return subclass objects based on the value of ofKind.
   * Simply add the cases to the switch for all those special cases.
   * For example, if you have a subclass of Token called IDToken that
   * you want to create if ofKind is ID, simply add something like :
   *
   *    case MyParserConstants.ID : return new IDToken(ofKind, image);
   *
   * to the following switch statement. Then you can cast matchedToken
   * variable to the appropriate type and use sit in your lexical actions.
   */
  public static Token newToken(int ofKind, String image)
  {
    switch(ofKind)
    {
      default : return new Token(ofKind, image);
    }
  }

  public static Token newToken(int ofKind)
  {
    return newToken(ofKind, null);
  }

}
/* JavaCC - OriginalChecksum=3f285f96e8dbb888816c3d530ccf4845 (do not edit this line) */

static public
class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected Excel parser;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(Excel p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) { this.value = value; }
  public Object jjtGetValue() { return value; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() { return ExcelTreeConstants.jjtNodeName[id]+" "+(value==null?"":value); }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        if (n != null) {
          n.dump(prefix + " ");
        }
      }
    }
  }
}

/* JavaCC - OriginalChecksum=a6c8ecfaad3a2c51bbdfdb3c399fad28 (do not edit this line) */

static public class SimpleCharStream
{
/** Whether parser is static. */
  public static final boolean staticFlag = false;
  int bufsize;
  int available;
  int tokenBegin;
/** Position in buffer. */
  public int bufpos = -1;
  protected int bufline[];
  protected int bufcolumn[];

  protected int column = 0;
  protected int line = 1;

  protected boolean prevCharIsCR = false;
  protected boolean prevCharIsLF = false;

  protected java.io.Reader inputStream;

  protected char[] buffer;
  protected int maxNextCharInd = 0;
  protected int inBuf = 0;
  protected int tabSize = 8;

  protected void setTabSize(int i) { tabSize = i; }
  protected int getTabSize(int i) { return tabSize; }


  protected void ExpandBuff(boolean wrapAround)
  {
    char[] newbuffer = new char[bufsize + 2048];
    int newbufline[] = new int[bufsize + 2048];
    int newbufcolumn[] = new int[bufsize + 2048];

    try
    {
      if (wrapAround)
      {
        System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
        System.arraycopy(buffer, 0, newbuffer, bufsize - tokenBegin, bufpos);
        buffer = newbuffer;

        System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
        System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
        bufline = newbufline;

        System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
        System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
        bufcolumn = newbufcolumn;

        maxNextCharInd = (bufpos += (bufsize - tokenBegin));
      }
      else
      {
        System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
        buffer = newbuffer;

        System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
        bufline = newbufline;

        System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
        bufcolumn = newbufcolumn;

        maxNextCharInd = (bufpos -= tokenBegin);
      }
    }
    catch (Throwable t)
    {
      throw new Error(t.getMessage());
    }


    bufsize += 2048;
    available = bufsize;
    tokenBegin = 0;
  }

  protected void FillBuff() throws java.io.IOException
  {
    if (maxNextCharInd == available)
    {
      if (available == bufsize)
      {
        if (tokenBegin > 2048)
        {
          bufpos = maxNextCharInd = 0;
          available = tokenBegin;
        }
        else if (tokenBegin < 0)
          bufpos = maxNextCharInd = 0;
        else
          ExpandBuff(false);
      }
      else if (available > tokenBegin)
        available = bufsize;
      else if ((tokenBegin - available) < 2048)
        ExpandBuff(true);
      else
        available = tokenBegin;
    }

    int i;
    try {
      if ((i = inputStream.read(buffer, maxNextCharInd, available - maxNextCharInd)) == -1)
      {
        inputStream.close();
        throw new java.io.IOException();
      }
      else
        maxNextCharInd += i;
      return;
    }
    catch(java.io.IOException e) {
      --bufpos;
      backup(0);
      if (tokenBegin == -1)
        tokenBegin = bufpos;
      throw e;
    }
  }

/** Start. */
  public char BeginToken() throws java.io.IOException
  {
    tokenBegin = -1;
    char c = readChar();
    tokenBegin = bufpos;

    return c;
  }

  protected void UpdateLineColumn(char c)
  {
    column++;

    if (prevCharIsLF)
    {
      prevCharIsLF = false;
      line += (column = 1);
    }
    else if (prevCharIsCR)
    {
      prevCharIsCR = false;
      if (c == '\n')
      {
        prevCharIsLF = true;
      }
      else
        line += (column = 1);
    }

    switch (c)
    {
      case '\r' :
        prevCharIsCR = true;
        break;
      case '\n' :
        prevCharIsLF = true;
        break;
      case '\t' :
        column--;
        column += (tabSize - (column % tabSize));
        break;
      default :
        break;
    }

    bufline[bufpos] = line;
    bufcolumn[bufpos] = column;
  }

/** Read a character. */
  public char readChar() throws java.io.IOException
  {
    if (inBuf > 0)
    {
      --inBuf;

      if (++bufpos == bufsize)
        bufpos = 0;

      return buffer[bufpos];
    }

    if (++bufpos >= maxNextCharInd)
      FillBuff();

    char c = buffer[bufpos];

    UpdateLineColumn(c);
    return c;
  }

  /**
   * @deprecated
   * @see #getEndColumn
   */

  public int getColumn() {
    return bufcolumn[bufpos];
  }

  /**
   * @deprecated
   * @see #getEndLine
   */

  public int getLine() {
    return bufline[bufpos];
  }

  /** Get token end column number. */
  public int getEndColumn() {
    return bufcolumn[bufpos];
  }

  /** Get token end line number. */
  public int getEndLine() {
     return bufline[bufpos];
  }

  /** Get token beginning column number. */
  public int getBeginColumn() {
    return bufcolumn[tokenBegin];
  }

  /** Get token beginning line number. */
  public int getBeginLine() {
    return bufline[tokenBegin];
  }

/** Backup a number of characters. */
  public void backup(int amount) {

    inBuf += amount;
    if ((bufpos -= amount) < 0)
      bufpos += bufsize;
  }

  /** Constructor. */
  public SimpleCharStream(java.io.Reader dstream, int startline,
  int startcolumn, int buffersize)
  {
    inputStream = dstream;
    line = startline;
    column = startcolumn - 1;

    available = bufsize = buffersize;
    buffer = new char[buffersize];
    bufline = new int[buffersize];
    bufcolumn = new int[buffersize];
  }

  /** Constructor. */
  public SimpleCharStream(java.io.Reader dstream, int startline,
                          int startcolumn)
  {
    this(dstream, startline, startcolumn, 4096);
  }

  /** Constructor. */
  public SimpleCharStream(java.io.Reader dstream)
  {
    this(dstream, 1, 1, 4096);
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader dstream, int startline,
  int startcolumn, int buffersize)
  {
    inputStream = dstream;
    line = startline;
    column = startcolumn - 1;

    if (buffer == null || buffersize != buffer.length)
    {
      available = bufsize = buffersize;
      buffer = new char[buffersize];
      bufline = new int[buffersize];
      bufcolumn = new int[buffersize];
    }
    prevCharIsLF = prevCharIsCR = false;
    tokenBegin = inBuf = maxNextCharInd = 0;
    bufpos = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader dstream, int startline,
                     int startcolumn)
  {
    ReInit(dstream, startline, startcolumn, 4096);
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader dstream)
  {
    ReInit(dstream, 1, 1, 4096);
  }
  /** Constructor. */
  public SimpleCharStream(java.io.InputStream dstream, String encoding, int startline,
  int startcolumn, int buffersize) throws java.io.UnsupportedEncodingException
  {
    this(encoding == null ? new java.io.InputStreamReader(dstream) : new java.io.InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
  }

  /** Constructor. */
  public SimpleCharStream(java.io.InputStream dstream, int startline,
  int startcolumn, int buffersize)
  {
    this(new java.io.InputStreamReader(dstream), startline, startcolumn, buffersize);
  }

  /** Constructor. */
  public SimpleCharStream(java.io.InputStream dstream, String encoding, int startline,
                          int startcolumn) throws java.io.UnsupportedEncodingException
  {
    this(dstream, encoding, startline, startcolumn, 4096);
  }

  /** Constructor. */
  public SimpleCharStream(java.io.InputStream dstream, int startline,
                          int startcolumn)
  {
    this(dstream, startline, startcolumn, 4096);
  }

  /** Constructor. */
  public SimpleCharStream(java.io.InputStream dstream, String encoding) throws java.io.UnsupportedEncodingException
  {
    this(dstream, encoding, 1, 1, 4096);
  }

  /** Constructor. */
  public SimpleCharStream(java.io.InputStream dstream)
  {
    this(dstream, 1, 1, 4096);
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream dstream, String encoding, int startline,
                          int startcolumn, int buffersize) throws java.io.UnsupportedEncodingException
  {
    ReInit(encoding == null ? new java.io.InputStreamReader(dstream) : new java.io.InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream dstream, int startline,
                          int startcolumn, int buffersize)
  {
    ReInit(new java.io.InputStreamReader(dstream), startline, startcolumn, buffersize);
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream dstream, String encoding) throws java.io.UnsupportedEncodingException
  {
    ReInit(dstream, encoding, 1, 1, 4096);
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream dstream)
  {
    ReInit(dstream, 1, 1, 4096);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream dstream, String encoding, int startline,
                     int startcolumn) throws java.io.UnsupportedEncodingException
  {
    ReInit(dstream, encoding, startline, startcolumn, 4096);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream dstream, int startline,
                     int startcolumn)
  {
    ReInit(dstream, startline, startcolumn, 4096);
  }
  /** Get token literal value. */
  public String GetImage()
  {
    if (bufpos >= tokenBegin)
      return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
    else
      return new String(buffer, tokenBegin, bufsize - tokenBegin) +
                            new String(buffer, 0, bufpos + 1);
  }

  /** Get the suffix. */
  public char[] GetSuffix(int len)
  {
    char[] ret = new char[len];

    if ((bufpos + 1) >= len)
      System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
    else
    {
      System.arraycopy(buffer, bufsize - (len - bufpos - 1), ret, 0,
                                                        len - bufpos - 1);
      System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
    }

    return ret;
  }

  /** Reset buffer when finished. */
  public void Done()
  {
    buffer = null;
    bufline = null;
    bufcolumn = null;
  }

  /**
   * Method to adjust line and column numbers for the start of a token.
   */
  public void adjustBeginLineColumn(int newLine, int newCol)
  {
    int start = tokenBegin;
    int len;

    if (bufpos >= tokenBegin)
    {
      len = bufpos - tokenBegin + inBuf + 1;
    }
    else
    {
      len = bufsize - tokenBegin + bufpos + 1 + inBuf;
    }

    int i = 0, j = 0, k = 0;
    int nextColDiff = 0, columnDiff = 0;

    while (i < len && bufline[j = start % bufsize] == bufline[k = ++start % bufsize])
    {
      bufline[j] = newLine;
      nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
      bufcolumn[j] = newCol + columnDiff;
      columnDiff = nextColDiff;
      i++;
    }

    if (i < len)
    {
      bufline[j] = newLine++;
      bufcolumn[j] = newCol + columnDiff;

      while (i++ < len)
      {
        if (bufline[j = start % bufsize] != bufline[++start % bufsize])
          bufline[j] = newLine++;
        else
          bufline[j] = newLine;
      }
    }

    line = bufline[j];
    column = bufcolumn[j];
  }

}
/* JavaCC - OriginalChecksum=113331da05d67397c04063877425d0b4 (do not edit this line) */

static public class ParseException extends Exception {

  /**
   * The version identifier for this Serializable class.
   * Increment only if the <i>serialized</i> form of the
   * class changes.
   */
  private static final long serialVersionUID = 1L;

  /**
   * This constructor is used by the method "generateParseException"
   * in the generated parser.  Calling this constructor generates
   * a new object of this type with the fields "currentToken",
   * "expectedTokenSequences", and "tokenImage" set.
   */
  public ParseException(Token currentTokenVal,
                        int[][] expectedTokenSequencesVal,
                        String[] tokenImageVal
                       )
  {
    super(initialise(currentTokenVal, expectedTokenSequencesVal, tokenImageVal));
    currentToken = currentTokenVal;
    expectedTokenSequences = expectedTokenSequencesVal;
    tokenImage = tokenImageVal;
  }

  /**
   * The following constructors are for use by you for whatever
   * purpose you can think of.  Constructing the exception in this
   * manner makes the exception behave in the normal way - i.e., as
   * documented in the class "Throwable".  The fields "errorToken",
   * "expectedTokenSequences", and "tokenImage" do not contain
   * relevant information.  The JavaCC generated code does not use
   * these constructors.
   */

  public ParseException() {
    super();
  }

  /** Constructor with message. */
  public ParseException(String message) {
    super(message);
  }


  /**
   * This is the last token that has been consumed successfully.  If
   * this object has been created due to a parse error, the token
   * followng this token will (therefore) be the first error token.
   */
  public Token currentToken;

  /**
   * Each entry in this array is an array of integers.  Each array
   * of integers represents a sequence of tokens (by their ordinal
   * values) that is expected at this point of the parse.
   */
  public int[][] expectedTokenSequences;

  /**
   * This is a reference to the "tokenImage" array of the generated
   * parser within which the parse error occurred.  This array is
   * defined in the generated ...Constants interface.
   */
  public String[] tokenImage;

  /**
   * It uses "currentToken" and "expectedTokenSequences" to generate a parse
   * error message and returns it.  If this object has been created
   * due to a parse error, and you do not catch it (it gets thrown
   * from the parser) the correct error message
   * gets displayed.
   */
  private static String initialise(Token currentToken,
                           int[][] expectedTokenSequences,
                           String[] tokenImage) {
    String eol = System.getProperty("line.separator", "\n");
    StringBuffer expected = new StringBuffer();
    int maxSize = 0;
    for (int i = 0; i < expectedTokenSequences.length; i++) {
      if (maxSize < expectedTokenSequences[i].length) {
        maxSize = expectedTokenSequences[i].length;
      }
      for (int j = 0; j < expectedTokenSequences[i].length; j++) {
        expected.append(tokenImage[expectedTokenSequences[i][j]]).append(' ');
      }
      if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0) {
        expected.append("...");
      }
      expected.append(eol).append("    ");
    }
    String retval = "Encountered \"";
    Token tok = currentToken.next;
    for (int i = 0; i < maxSize; i++) {
      if (i != 0) retval += " ";
      if (tok.kind == 0) {
        retval += tokenImage[0];
        break;
      }
      retval += " " + tokenImage[tok.kind];
      retval += " \"";
      retval += add_escapes(tok.image);
      retval += " \"";
      tok = tok.next;
    }
    retval += "\" at line " + currentToken.next.beginLine + ", column " + currentToken.next.beginColumn;
    retval += "." + eol;
    if (expectedTokenSequences.length == 1) {
      retval += "Was expecting:" + eol + "    ";
    } else {
      retval += "Was expecting one of:" + eol + "    ";
    }
    retval += expected.toString();
    return retval;
  }

  /**
   * The end of line string for this machine.
   */
  protected String eol = System.getProperty("line.separator", "\n");

  /**
   * Used to convert raw characters to their escaped version
   * when these raw version cannot be used as part of an ASCII
   * string literal.
   */
  static String add_escapes(String str) {
      StringBuffer retval = new StringBuffer();
      char ch;
      for (int i = 0; i < str.length(); i++) {
        switch (str.charAt(i))
        {
           case 0 :
              continue;
           case '\b':
              retval.append("\\b");
              continue;
           case '\t':
              retval.append("\\t");
              continue;
           case '\n':
              retval.append("\\n");
              continue;
           case '\f':
              retval.append("\\f");
              continue;
           case '\r':
              retval.append("\\r");
              continue;
           case '\"':
              retval.append("\\\"");
              continue;
           case '\'':
              retval.append("\\\'");
              continue;
           case '\\':
              retval.append("\\\\");
              continue;
           default:
              if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
                 String s = "0000" + Integer.toString(ch, 16);
                 retval.append("\\u" + s.substring(s.length() - 4, s.length()));
              } else {
                 retval.append(ch);
              }
              continue;
        }
      }
      return retval.toString();
   }

}
/* JavaCC - OriginalChecksum=151ca40e07b496ced1ea022ca5e124c0 (do not edit this line) */

static public
interface Node {

  /** This method is called after the node has been made the current
    node.  It indicates that child nodes can now be added to it. */
  public void jjtOpen();

  /** This method is called after all the child nodes have been
    added. */
  public void jjtClose();

  /** This pair of methods are used to inform the node of its
    parent. */
  public void jjtSetParent(Node n);
  public Node jjtGetParent();

  /** This method tells the node to add its argument to the node's
    list of children.  */
  public void jjtAddChild(Node n, int i);

  /** This method returns a child node.  The children are numbered
     from zero, left to right. */
  public Node jjtGetChild(int i);

  /** Return the number of children the node has. */
  public int jjtGetNumChildren();
}
/* JavaCC - OriginalChecksum=bd1a7a18db79b7cb988e60e609ffe919 (do not edit this line) */

static public class JJTExcelState {
  private java.util.List nodes;
  private java.util.List marks;

  private int sp;        // number of nodes on stack
  private int mk;        // current mark
  private boolean node_created;

  public JJTExcelState() {
    nodes = new java.util.ArrayList();
    marks = new java.util.ArrayList();
    sp = 0;
    mk = 0;
  }

  /* Determines whether the current node was actually closed and
     pushed.  This should only be called in the final user action of a
     node scope.  */
  public boolean nodeCreated() {
    return node_created;
  }

  /* Call this to reinitialize the node stack.  It is called
     automatically by the parser's ReInit() method. */
  public void reset() {
    nodes.clear();
    marks.clear();
    sp = 0;
    mk = 0;
  }

  /* Returns the root node of the AST.  It only makes sense to call
     this after a successful parse. */
  public Node rootNode() {
    return (Node)nodes.get(0);
  }

  /* Pushes a node on to the stack. */
  public void pushNode(Node n) {
    nodes.add(n);
    ++sp;
  }

  /* Returns the node on the top of the stack, and remove it from the
     stack.  */
  public Node popNode() {
    if (--sp < mk) {
      mk = ((Integer)marks.remove(marks.size()-1)).intValue();
    }
    return (Node)nodes.remove(nodes.size()-1);
  }

  /* Returns the node currently on the top of the stack. */
  public Node peekNode() {
    return (Node)nodes.get(nodes.size()-1);
  }

  /* Returns the number of children on the stack in the current node
     scope. */
  public int nodeArity() {
    return sp - mk;
  }


  public void clearNodeScope(Node n) {
    while (sp > mk) {
      popNode();
    }
    mk = ((Integer)marks.remove(marks.size()-1)).intValue();
  }


  public void openNodeScope(Node n) {
    marks.add(new Integer(mk));
    mk = sp;
    n.jjtOpen();
  }


  /* A definite node is constructed from a specified number of
     children.  That number of nodes are popped from the stack and
     made the children of the definite node.  Then the definite node
     is pushed on to the stack. */
  public void closeNodeScope(Node n, int num) {
    mk = ((Integer)marks.remove(marks.size()-1)).intValue();
    while (num-- > 0) {
      Node c = popNode();
      c.jjtSetParent(n);
      n.jjtAddChild(c, num);
    }
    n.jjtClose();
    pushNode(n);
    node_created = true;
  }


  /* A conditional node is constructed if its condition is true.  All
     the nodes that have been pushed since the node was opened are
     made children of the conditional node, which is then pushed
     on to the stack.  If the condition is false the node is not
     constructed and they are left on the stack. */
  public void closeNodeScope(Node n, boolean condition) {
    if (condition) {
      int a = nodeArity();
      mk = ((Integer)marks.remove(marks.size()-1)).intValue();
      while (a-- > 0) {
        Node c = popNode();
        c.jjtSetParent(n);
        n.jjtAddChild(c, a);
      }
      n.jjtClose();
      pushNode(n);
      node_created = true;
    } else {
      mk = ((Integer)marks.remove(marks.size()-1)).intValue();
      node_created = false;
    }
  }
}
/* JavaCC - OriginalChecksum=95314b47b649e41088ed032ce408532f (do not edit this line) */

static public interface ExcelTreeConstants
{
  public int JJTINPUT = 0;
  public int JJTWORKBOOK = 1;
  public int JJTWORKSHEET = 2;
  public int JJTBLOCK = 3;
  public int JJTROWBREAK = 4;
  public int JJTBLOCKSTATEMENT = 5;
  public int JJTSTATEMENT = 6;
  public int JJTFORSTATEMENT = 7;
  public int JJTVARIABLE = 8;
  public int JJTIFSTATEMENT = 9;
  public int JJTWHILESTATEMENT = 10;
  public int JJTLITERAL = 11;
  public int JJTBOOLEANLITERAL = 12;
  public int JJTSTATEMENTEXPRESSION = 13;
  public int JJTARGUMENTS = 14;
  public int JJTARGUMENTLIST = 15;
  public int JJTSUM = 16;
  public int JJTID = 17;
  public int JJTNAME = 18;
  public int JJTCELL = 19;
  public int JJTEXPRESSION = 20;
  public int JJTASSIGNMENT = 21;
  public int JJTASSIGNMENTOPERATOR = 22;
  public int JJTCONDITIONALEXPRESSION = 23;
  public int JJTCONDITIONALOREXPRESSION = 24;
  public int JJTORNODE = 25;
  public int JJTCONDITIONALANDEXPRESSION = 26;
  public int JJTANDNODE = 27;
  public int JJTINCLUSIVEOREXPRESSION = 28;
  public int JJTEXCLUSIVEOREXPRESSION = 29;
  public int JJTANDEXPRESSION = 30;
  public int JJTEQUALITYEXPRESSION = 31;
  public int JJTEQNODE = 32;
  public int JJTNENODE = 33;
  public int JJTINSTANCEOFEXPRESSION = 34;
  public int JJTRELATIONALEXPRESSION = 35;
  public int JJTLTNODE = 36;
  public int JJTGTNODE = 37;
  public int JJTLENODE = 38;
  public int JJTGENODE = 39;
  public int JJTSHIFTEXPRESSION = 40;
  public int JJTVOID = 41;
  public int JJTADDNODE = 42;
  public int JJTSUBTRACTNODE = 43;
  public int JJTMULNODE = 44;
  public int JJTDIVNODE = 45;
  public int JJTMODNODE = 46;
  public int JJTUNARYEXPRESSION = 47;
  public int JJTPREINCREMENTEXPRESSION = 48;
  public int JJTPREDECREMENTEXPRESSION = 49;
  public int JJTUNARYEXPRESSIONNOTPLUSMINUS = 50;
  public int JJTNOTNODE = 51;
  public int JJTCASTLOOKAHEAD = 52;
  public int JJTPOSTFIXEXPRESSION = 53;
  public int JJTCASTEXPRESSION = 54;
  public int JJTPRIMARYEXPRESSION = 55;
  public int JJTPRIMARYPREFIX = 56;
  public int JJTMETHODINVOCATION = 57;
  public int JJTMETHODNAME = 58;
  public int JJTTYPE = 59;
  public int JJTPRIMITIVETYPE = 60;
  public int JJTRESULTTYPE = 61;
  public int JJTALLOCATIONEXPRESSION = 62;
  public int JJTARRAYDIMENSIONS = 63;


  public String[] jjtNodeName = {
    "Input",
    "WorkBook",
    "WorkSheet",
    "Block",
    "RowBreak",
    "BlockStatement",
    "Statement",
    "ForStatement",
    "Variable",
    "IfStatement",
    "WhileStatement",
    "Literal",
    "BooleanLiteral",
    "StatementExpression",
    "Arguments",
    "ArgumentList",
    "Sum",
    "ID",
    "Name",
    "Cell",
    "Expression",
    "Assignment",
    "AssignmentOperator",
    "ConditionalExpression",
    "ConditionalOrExpression",
    "OrNode",
    "ConditionalAndExpression",
    "AndNode",
    "InclusiveOrExpression",
    "ExclusiveOrExpression",
    "AndExpression",
    "EqualityExpression",
    "EQNode",
    "NENode",
    "InstanceOfExpression",
    "RelationalExpression",
    "LTNode",
    "GTNode",
    "LENode",
    "GENode",
    "ShiftExpression",
    "void",
    "AddNode",
    "SubtractNode",
    "MulNode",
    "DivNode",
    "ModNode",
    "UnaryExpression",
    "PreIncrementExpression",
    "PreDecrementExpression",
    "UnaryExpressionNotPlusMinus",
    "NotNode",
    "CastLookahead",
    "PostfixExpression",
    "CastExpression",
    "PrimaryExpression",
    "PrimaryPrefix",
    "MethodInvocation",
    "MethodName",
    "Type",
    "PrimitiveType",
    "ResultType",
    "AllocationExpression",
    "ArrayDimensions",
  };
}
/* JavaCC - OriginalChecksum=66b4d21b548f904dcc53d4eb98bd7781 (do not edit this line) */

static public class ExcelTokenManager implements ExcelConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active1 & 0x4008000000000L) != 0L)
            return 0;
         if ((active1 & 0x100000L) != 0L)
            return 10;
         if ((active0 & 0xffffffffffffc040L) != 0L)
         {
            jjmatchedKind = 75;
            return 34;
         }
         return -1;
      case 1:
         if ((active0 & 0x806000000L) != 0L)
            return 34;
         if ((active0 & 0xfffffff7f9ffc040L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 75;
               jjmatchedPos = 1;
            }
            return 34;
         }
         return -1;
      case 2:
         if ((active0 & 0x1000098200000040L) != 0L)
            return 34;
         if ((active0 & 0xeffff675fdffc000L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 75;
               jjmatchedPos = 2;
            }
            return 34;
         }
         return -1;
      case 3:
         if ((active0 & 0x2880120408160000L) != 0L)
            return 34;
         if ((active0 & 0xc77fe571f5e9c000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 3;
            return 34;
         }
         return -1;
      case 4:
         if ((active0 & 0x83140001e0690000L) != 0L)
            return 34;
         if ((active0 & 0x446be5701580c000L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 75;
               jjmatchedPos = 4;
            }
            return 34;
         }
         return -1;
      case 5:
         if ((active0 & 0x22b042004000000L) != 0L)
            return 34;
         if ((active0 & 0x4440e1509180c000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 5;
            return 34;
         }
         return -1;
      case 6:
         if ((active0 & 0x600091008000L) != 0L)
            return 34;
         if ((active0 & 0x4440815000804000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 6;
            return 34;
         }
         return -1;
      case 7:
         if ((active0 & 0x4000000000804000L) != 0L)
            return 34;
         if ((active0 & 0x440815000000000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 7;
            return 34;
         }
         return -1;
      case 8:
         if ((active0 & 0x400810000000000L) != 0L)
            return 34;
         if ((active0 & 0x40005000000000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 8;
            return 34;
         }
         return -1;
      case 9:
         if ((active0 & 0x5000000000L) != 0L)
            return 34;
         if ((active0 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 9;
            return 34;
         }
         return -1;
      case 10:
         if ((active0 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 10;
            return 34;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 88;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x80000000L);
      case 36:
         return jjMoveStringLiteralDfa1_0(0x3f80L, 0x0L);
      case 37:
         jjmatchedKind = 107;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x40000000000000L);
      case 38:
         jjmatchedKind = 104;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x8000200000000L);
      case 40:
         return jjStopAtPos(0, 76);
      case 41:
         return jjStopAtPos(0, 77);
      case 42:
         jjmatchedKind = 102;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2000000000000L);
      case 43:
         jjmatchedKind = 100;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x800400000000L);
      case 44:
         return jjStopAtPos(0, 83);
      case 45:
         jjmatchedKind = 101;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1000800000000L);
      case 46:
         return jjStartNfaWithStates_0(0, 84, 10);
      case 47:
         jjmatchedKind = 103;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x4000000000000L);
      case 58:
         return jjStopAtPos(0, 91);
      case 59:
         return jjStopAtPos(0, 82);
      case 60:
         jjmatchedKind = 87;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x80100020000000L);
      case 61:
         jjmatchedKind = 85;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x10000000L);
      case 62:
         jjmatchedKind = 86;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x300600040000000L);
      case 63:
         return jjStopAtPos(0, 90);
      case 91:
         return jjStopAtPos(0, 80);
      case 93:
         return jjStopAtPos(0, 81);
      case 94:
         jjmatchedKind = 106;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x20000000000000L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa1_0(0x4000L, 0x0L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa1_0(0x38000L, 0x0L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa1_0(0xfc0000L, 0x0L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa1_0(0x7000000L, 0x0L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa1_0(0x18000000L, 0x0L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa1_0(0x3e0000000L, 0x0L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa1_0(0x400000000L, 0x0L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa1_0(0x1f800000000L, 0x0L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa1_0(0x20000000000L, 0x0L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa1_0(0x1c0000000000L, 0x0L);
      case 80:
      case 112:
         return jjMoveStringLiteralDfa1_0(0x1e00000000000L, 0x0L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa1_0(0x2000000000000L, 0x0L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa1_0(0x7c000000000040L, 0x0L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1f80000000000000L, 0x0L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa1_0(0x6000000000000000L, 0x0L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa1_0(0x8000000000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 78);
      case 124:
         jjmatchedKind = 105;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x10000100000000L);
      case 125:
         return jjStopAtPos(0, 79);
      case 126:
         return jjStopAtPos(0, 89);
      default :
         return jjMoveNfa_0(5, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active1 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 97);
         break;
      case 43:
         if ((active1 & 0x400000000L) != 0L)
            return jjStopAtPos(1, 98);
         break;
      case 45:
         if ((active1 & 0x800000000L) != 0L)
            return jjStopAtPos(1, 99);
         break;
      case 60:
         if ((active1 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 108;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x80000000000000L);
      case 61:
         if ((active1 & 0x10000000L) != 0L)
            return jjStopAtPos(1, 92);
         else if ((active1 & 0x20000000L) != 0L)
            return jjStopAtPos(1, 93);
         else if ((active1 & 0x40000000L) != 0L)
            return jjStopAtPos(1, 94);
         else if ((active1 & 0x80000000L) != 0L)
            return jjStopAtPos(1, 95);
         else if ((active1 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 111);
         else if ((active1 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 112);
         else if ((active1 & 0x2000000000000L) != 0L)
            return jjStopAtPos(1, 113);
         else if ((active1 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 114);
         else if ((active1 & 0x8000000000000L) != 0L)
            return jjStopAtPos(1, 115);
         else if ((active1 & 0x10000000000000L) != 0L)
            return jjStopAtPos(1, 116);
         else if ((active1 & 0x20000000000000L) != 0L)
            return jjStopAtPos(1, 117);
         else if ((active1 & 0x40000000000000L) != 0L)
            return jjStopAtPos(1, 118);
         break;
      case 62:
         if ((active1 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 109;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x300400000000000L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x2400200c0000L, active1, 0L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L, active1, 0L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L, active1, 0L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x2080001000000L, active1, 0L);
      case 70:
      case 102:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(1, 35, 34);
         break;
      case 72:
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x8384000000100000L, active1, 0L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0xc0000000L, active1, 0L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x108200000L, active1, 0L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x3000001000L, active1, 0L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x1c000000000L, active1, 0L);
      case 79:
      case 111:
         if ((active0 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 25;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x6000020604c08000L, active1, 0L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x1c00c00000012000L, active1, 0L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000000L, active1, 0L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x11100000000040L, active1, 0L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000000780L, active1, 0L);
      case 88:
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L, active1, 0L);
      case 89:
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000020000L, active1, 0L);
      case 124:
         if ((active1 & 0x100000000L) != 0L)
            return jjStopAtPos(1, 96);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1);
      return 2;
   }
   switch(curChar)
   {
      case 61:
         if ((active1 & 0x80000000000000L) != 0L)
            return jjStopAtPos(2, 119);
         else if ((active1 & 0x100000000000000L) != 0L)
            return jjStopAtPos(2, 120);
         break;
      case 62:
         if ((active1 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 110;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 0x200000000000000L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x408000000300000L, active1, 0L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000180L, active1, 0L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000000L, active1, 0L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x11800L, active1, 0L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000L, active1, 0L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0xa0a0400000000000L, active1, 0L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000100020000000L, active1, 0L);
      case 77:
      case 109:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(2, 6, 34);
         break;
      case 78:
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x400200c0c00000L, active1, 0L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x480010000a000L, active1, 0L);
      case 80:
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x10003000000000L, active1, 0L);
      case 82:
      case 114:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(2, 33, 34);
         return jjMoveStringLiteralDfa3_0(active0, 0x300000000000000L, active1, 0L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x4008044600L, active1, 0L);
      case 84:
      case 116:
         if ((active0 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 39;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x20504100a0000L, active1, 0L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000004000000L, active1, 0L);
      case 87:
      case 119:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 43, 34);
         break;
      case 89:
      case 121:
         if ((active0 & 0x1000000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 60, 34);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1);
      return 3;
   }
   switch(curChar)
   {
      case 61:
         if ((active1 & 0x200000000000000L) != 0L)
            return jjStopAtPos(3, 121);
         break;
      case 95:
         return jjMoveStringLiteralDfa4_0(active0, 0x780L, active1, 0L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000001c1010000L, active1, 0L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000L, active1, 0L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000080000L, active1, 0L);
      case 68:
      case 100:
         if ((active0 & 0x2000000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 61, 34);
         break;
      case 69:
      case 101:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(3, 17, 34);
         else if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(3, 18, 34);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(3, 27, 34);
         else if ((active0 & 0x800000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 59, 34);
         return jjMoveStringLiteralDfa4_0(active0, 0x10010010000000L, active1, 0L);
      case 71:
      case 103:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 41, 34);
         break;
      case 73:
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000000L, active1, 0L);
      case 75:
      case 107:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000000L, active1, 0L);
      case 76:
      case 108:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 44, 34);
         return jjMoveStringLiteralDfa4_0(active0, 0x8001001000008800L, active1, 0L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000000000L, active1, 0L);
      case 79:
      case 111:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(3, 34, 34);
         return jjMoveStringLiteralDfa4_0(active0, 0x300002000000000L, active1, 0L);
      case 82:
      case 114:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(3, 20, 34);
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000001000L, active1, 0L);
      case 83:
      case 115:
         if ((active0 & 0x80000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 55, 34);
         return jjMoveStringLiteralDfa4_0(active0, 0x20600000L, active1, 0L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x28804000804000L, active1, 0L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000000L, active1, 0L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000000L, active1, 0L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 95:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x604000000000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000000L);
      case 69:
      case 101:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(4, 29, 34);
         else if ((active0 & 0x8000000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 63, 34);
         return jjMoveStringLiteralDfa5_0(active0, 0x801000008500L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      case 72:
      case 104:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(4, 19, 34);
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000000000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x9000000800000L);
      case 75:
      case 107:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(4, 16, 34);
         break;
      case 76:
      case 108:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(4, 11);
         else if ((active0 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 30;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x84000000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000L);
      case 82:
      case 114:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 52, 34);
         return jjMoveStringLiteralDfa5_0(active0, 0x2012000004000L);
      case 83:
      case 115:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(4, 21, 34);
         return jjMoveStringLiteralDfa5_0(active0, 0x400000000000280L);
      case 84:
      case 116:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(4, 22, 34);
         else if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(4, 32, 34);
         else if ((active0 & 0x4000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 50, 34);
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000000000000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000000L);
      case 87:
      case 119:
         if ((active0 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 56;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x200000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, 0L);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, 0L);
      return 5;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0xc000L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 67:
      case 99:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 48, 34);
         else if ((active0 & 0x8000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 51, 34);
         return jjMoveStringLiteralDfa6_0(active0, 0x800000000000L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000L);
      case 69:
      case 101:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(5, 26, 34);
         else if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 42, 34);
         return jjMoveStringLiteralDfa6_0(active0, 0x1000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000000L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000000000L);
      case 72:
      case 104:
         if ((active0 & 0x20000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 53, 34);
         break;
      case 73:
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x4400000000000000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x81000000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000000L);
      case 78:
      case 110:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 49, 34);
         return jjMoveStringLiteralDfa6_0(active0, 0x4000800500L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000000000000L);
      case 83:
      case 115:
         if ((active0 & 0x200000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 57, 34);
         break;
      case 84:
      case 116:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 37, 34);
         return jjMoveStringLiteralDfa6_0(active0, 0x400000000280L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0, 0L);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, 0L);
      return 6;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000000280L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000004000L);
      case 68:
      case 100:
         if ((active0 & 0x100L) != 0L)
            return jjStopAtPos(6, 8);
         else if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(6, 10);
         return jjMoveStringLiteralDfa7_0(active0, 0x1000L);
      case 69:
      case 101:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 45, 34);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 46, 34);
         return jjMoveStringLiteralDfa7_0(active0, 0x400001000000000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000000000000000L);
      case 78:
      case 110:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(6, 15, 34);
         break;
      case 79:
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x40000000000000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000L);
      case 83:
      case 115:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(6, 28, 34);
         break;
      case 84:
      case 116:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(6, 24, 34);
         return jjMoveStringLiteralDfa7_0(active0, 0x800000000000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000L);
      case 89:
      case 121:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(6, 31, 34);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0, 0L);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 95:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000000000L);
      case 69:
      case 101:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(7, 23, 34);
         else if ((active0 & 0x4000000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 62, 34);
         return jjMoveStringLiteralDfa8_0(active0, 0x804000002000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa8_0(active0, 0x440001000000000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa8_0(active0, 0x280L);
      case 84:
      case 116:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(7, 14, 34);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, 0L);
      return 8;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa9_0(active0, 0x2000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa9_0(active0, 0x1000L);
      case 68:
      case 100:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 47, 34);
         break;
      case 69:
      case 101:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 40, 34);
         break;
      case 73:
      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x40000000000000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000000L);
      case 84:
      case 116:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(8, 7);
         else if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(8, 9);
         else if ((active0 & 0x400000000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 58, 34);
         return jjMoveStringLiteralDfa9_0(active0, 0x1000000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0, 0L);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, 0L);
      return 9;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         return jjMoveStringLiteralDfa10_0(active0, 0x1000L);
      case 70:
      case 102:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(9, 38, 34);
         break;
      case 75:
      case 107:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(9, 13);
         break;
      case 83:
      case 115:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(9, 36, 34);
         break;
      case 90:
      case 122:
         return jjMoveStringLiteralDfa10_0(active0, 0x40000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0, 0L);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0, 0L);
      return 10;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         return jjMoveStringLiteralDfa11_0(active0, 0x40000000000000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa11_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0, 0L);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0, 0L);
      return 11;
   }
   switch(curChar)
   {
      case 68:
      case 100:
         if ((active0 & 0x40000000000000L) != 0L)
            return jjStartNfaWithStates_0(11, 54, 34);
         break;
      case 76:
      case 108:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(11, 12);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0, 0L);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 67;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 5:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 6);
                  else if (curChar == 34)
                     jjCheckNAddStates(7, 9);
                  else if (curChar == 39)
                     jjAddStates(10, 11);
                  else if (curChar == 46)
                     jjCheckNAdd(10);
                  else if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 0;
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 64)
                        kind = 64;
                     jjCheckNAddTwoStates(7, 8);
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 64)
                        kind = 64;
                     jjCheckNAddStates(12, 16);
                  }
                  break;
               case 0:
                  if (curChar != 47)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAddStates(17, 19);
                  break;
               case 1:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAddStates(17, 19);
                  break;
               case 2:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 3:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 4:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 6:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddTwoStates(7, 8);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddTwoStates(7, 8);
                  break;
               case 9:
                  if (curChar == 46)
                     jjCheckNAdd(10);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddStates(20, 22);
                  break;
               case 12:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(13);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddTwoStates(13, 14);
                  break;
               case 15:
                  if (curChar == 39)
                     jjAddStates(10, 11);
                  break;
               case 16:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 17:
                  if (curChar == 39 && kind > 73)
                     kind = 73;
                  break;
               case 19:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 20:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(21, 17);
                  break;
               case 21:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 22:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 23:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(21);
                  break;
               case 24:
                  if (curChar == 34)
                     jjCheckNAddStates(7, 9);
                  break;
               case 25:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 27:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 28:
                  if (curChar == 34 && kind > 74)
                     kind = 74;
                  break;
               case 29:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(23, 26);
                  break;
               case 30:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 31:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 32;
                  break;
               case 32:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(30);
                  break;
               case 34:
                  if ((0x3ff400000000000L & l) == 0L)
                     break;
                  if (kind > 75)
                     kind = 75;
                  jjstateSet[jjnewStateCnt++] = 34;
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 6);
                  break;
               case 36:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(27, 29);
                  break;
               case 38:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(39);
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(39, 14);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(40, 41);
                  break;
               case 42:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(43);
                  break;
               case 43:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddTwoStates(43, 14);
                  break;
               case 44:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(44, 45);
                  break;
               case 45:
                  if (curChar != 46)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddStates(30, 32);
                  break;
               case 46:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddStates(30, 32);
                  break;
               case 48:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(49);
                  break;
               case 49:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddTwoStates(49, 14);
                  break;
               case 50:
                  if (curChar != 48)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddStates(12, 16);
                  break;
               case 52:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddTwoStates(52, 8);
                  break;
               case 53:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddTwoStates(53, 8);
                  break;
               case 55:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjAddStates(33, 34);
                  break;
               case 56:
                  if (curChar == 46)
                     jjCheckNAdd(57);
                  break;
               case 57:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(57, 58);
                  break;
               case 59:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(60);
                  break;
               case 60:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddTwoStates(60, 14);
                  break;
               case 62:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(35, 37);
                  break;
               case 63:
                  if (curChar == 46)
                     jjCheckNAdd(64);
                  break;
               case 65:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(66);
                  break;
               case 66:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 68)
                     kind = 68;
                  jjCheckNAddTwoStates(66, 14);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 5:
               case 34:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 75)
                     kind = 75;
                  jjCheckNAdd(34);
                  break;
               case 1:
                  if (kind > 5)
                     kind = 5;
                  jjAddStates(17, 19);
                  break;
               case 8:
                  if ((0x100000001000L & l) != 0L && kind > 64)
                     kind = 64;
                  break;
               case 11:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(38, 39);
                  break;
               case 14:
                  if ((0x5000000050L & l) != 0L && kind > 68)
                     kind = 68;
                  break;
               case 16:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 18:
                  if (curChar == 92)
                     jjAddStates(40, 42);
                  break;
               case 19:
                  if ((0x14404410144044L & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 25:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 26:
                  if (curChar == 92)
                     jjAddStates(43, 45);
                  break;
               case 27:
                  if ((0x14404410144044L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 37:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(46, 47);
                  break;
               case 41:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(48, 49);
                  break;
               case 47:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(50, 51);
                  break;
               case 51:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(52);
                  break;
               case 52:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddTwoStates(52, 8);
                  break;
               case 54:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAddTwoStates(55, 56);
                  break;
               case 55:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStates(55, 56);
                  break;
               case 57:
                  if ((0x7e0000007eL & l) != 0L)
                     jjAddStates(52, 53);
                  break;
               case 58:
                  if ((0x1000000010000L & l) != 0L)
                     jjAddStates(54, 55);
                  break;
               case 61:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(62);
                  break;
               case 62:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddStates(35, 37);
                  break;
               case 64:
                  if ((0x1000000010000L & l) != 0L)
                     jjAddStates(56, 57);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjAddStates(17, 19);
                  break;
               case 16:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 25:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(7, 9);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 67 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   36, 37, 14, 40, 41, 44, 45, 25, 26, 28, 16, 18, 51, 53, 8, 54, 
   61, 1, 2, 4, 10, 11, 14, 25, 26, 30, 28, 36, 37, 14, 46, 47, 
   14, 55, 56, 62, 63, 64, 12, 13, 19, 20, 22, 27, 29, 31, 38, 39, 
   42, 43, 48, 49, 57, 58, 59, 60, 65, 66, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, "\50", "\51", "\173", "\175", "\133", 
"\135", "\73", "\54", "\56", "\75", "\76", "\74", "\41", "\176", "\77", "\72", 
"\75\75", "\74\75", "\76\75", "\41\75", "\174\174", "\46\46", "\53\53", "\55\55", "\53", 
"\55", "\52", "\57", "\46", "\174", "\136", "\45", "\74\74", "\76\76", "\76\76\76", 
"\53\75", "\55\75", "\52\75", "\57\75", "\46\75", "\174\75", "\136\75", "\45\75", 
"\74\74\75", "\76\76\75", "\76\76\76\75", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xffffffffffffffc1L, 0x3fffffffffffe11L, 
};
static final long[] jjtoSkip = {
   0x3eL, 0x0L, 
};
static final long[] jjtoSpecial = {
   0x20L, 0x0L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[67];
private final int[] jjstateSet = new int[134];
protected char curChar;
/** Constructor. */
public ExcelTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ExcelTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 67; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}

static public interface ExcelConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 5;
  /** RegularExpression Id. */
  int SUM = 6;
  /** RegularExpression Id. */
  int XLS_WB_START = 7;
  /** RegularExpression Id. */
  int XLS_WB_END = 8;
  /** RegularExpression Id. */
  int XLS_WS_START = 9;
  /** RegularExpression Id. */
  int XLS_WS_END = 10;
  /** RegularExpression Id. */
  int XLS_CELL = 11;
  /** RegularExpression Id. */
  int XLS_MERGED_CELL = 12;
  /** RegularExpression Id. */
  int XLS_ROW_BREAK = 13;
  /** RegularExpression Id. */
  int ABSTRACT = 14;
  /** RegularExpression Id. */
  int BOOLEAN = 15;
  /** RegularExpression Id. */
  int BREAK = 16;
  /** RegularExpression Id. */
  int BYTE = 17;
  /** RegularExpression Id. */
  int CASE = 18;
  /** RegularExpression Id. */
  int CATCH = 19;
  /** RegularExpression Id. */
  int CHAR = 20;
  /** RegularExpression Id. */
  int CLASS = 21;
  /** RegularExpression Id. */
  int CONST = 22;
  /** RegularExpression Id. */
  int CONTINUE = 23;
  /** RegularExpression Id. */
  int _DEFAULT = 24;
  /** RegularExpression Id. */
  int DO = 25;
  /** RegularExpression Id. */
  int DOUBLE = 26;
  /** RegularExpression Id. */
  int ELSE = 27;
  /** RegularExpression Id. */
  int EXTENDS = 28;
  /** RegularExpression Id. */
  int FALSE = 29;
  /** RegularExpression Id. */
  int FINAL = 30;
  /** RegularExpression Id. */
  int FINALLY = 31;
  /** RegularExpression Id. */
  int FLOAT = 32;
  /** RegularExpression Id. */
  int FOR = 33;
  /** RegularExpression Id. */
  int GOTO = 34;
  /** RegularExpression Id. */
  int IF = 35;
  /** RegularExpression Id. */
  int IMPLEMENTS = 36;
  /** RegularExpression Id. */
  int IMPORT = 37;
  /** RegularExpression Id. */
  int INSTANCEOF = 38;
  /** RegularExpression Id. */
  int INT = 39;
  /** RegularExpression Id. */
  int INTERFACE = 40;
  /** RegularExpression Id. */
  int LONG = 41;
  /** RegularExpression Id. */
  int NATIVE = 42;
  /** RegularExpression Id. */
  int NEW = 43;
  /** RegularExpression Id. */
  int NULL = 44;
  /** RegularExpression Id. */
  int PACKAGE = 45;
  /** RegularExpression Id. */
  int PRIVATE = 46;
  /** RegularExpression Id. */
  int PROTECTED = 47;
  /** RegularExpression Id. */
  int PUBLIC = 48;
  /** RegularExpression Id. */
  int RETURN = 49;
  /** RegularExpression Id. */
  int SHORT = 50;
  /** RegularExpression Id. */
  int STATIC = 51;
  /** RegularExpression Id. */
  int SUPER = 52;
  /** RegularExpression Id. */
  int SWITCH = 53;
  /** RegularExpression Id. */
  int SYNCHRONIZED = 54;
  /** RegularExpression Id. */
  int THIS = 55;
  /** RegularExpression Id. */
  int THROW = 56;
  /** RegularExpression Id. */
  int THROWS = 57;
  /** RegularExpression Id. */
  int TRANSIENT = 58;
  /** RegularExpression Id. */
  int TRUE = 59;
  /** RegularExpression Id. */
  int TRY = 60;
  /** RegularExpression Id. */
  int VOID = 61;
  /** RegularExpression Id. */
  int VOLATILE = 62;
  /** RegularExpression Id. */
  int WHILE = 63;
  /** RegularExpression Id. */
  int INTEGER_LITERAL = 64;
  /** RegularExpression Id. */
  int DECIMAL_LITERAL = 65;
  /** RegularExpression Id. */
  int HEX_LITERAL = 66;
  /** RegularExpression Id. */
  int OCTAL_LITERAL = 67;
  /** RegularExpression Id. */
  int FLOATING_POINT_LITERAL = 68;
  /** RegularExpression Id. */
  int DECIMAL_FLOATING_POINT_LITERAL = 69;
  /** RegularExpression Id. */
  int DECIMAL_EXPONENT = 70;
  /** RegularExpression Id. */
  int HEXADECIMAL_FLOATING_POINT_LITERAL = 71;
  /** RegularExpression Id. */
  int HEXADECIMAL_EXPONENT = 72;
  /** RegularExpression Id. */
  int CHARACTER_LITERAL = 73;
  /** RegularExpression Id. */
  int STRING_LITERAL = 74;
  /** RegularExpression Id. */
  int IDENTIFIER = 75;
  /** RegularExpression Id. */
  int LPAREN = 76;
  /** RegularExpression Id. */
  int RPAREN = 77;
  /** RegularExpression Id. */
  int LBRACE = 78;
  /** RegularExpression Id. */
  int RBRACE = 79;
  /** RegularExpression Id. */
  int LBRACKET = 80;
  /** RegularExpression Id. */
  int RBRACKET = 81;
  /** RegularExpression Id. */
  int SEMICOLON = 82;
  /** RegularExpression Id. */
  int COMMA = 83;
  /** RegularExpression Id. */
  int DOT = 84;
  /** RegularExpression Id. */
  int ASSIGN = 85;
  /** RegularExpression Id. */
  int GT = 86;
  /** RegularExpression Id. */
  int LT = 87;
  /** RegularExpression Id. */
  int BANG = 88;
  /** RegularExpression Id. */
  int TILDE = 89;
  /** RegularExpression Id. */
  int HOOK = 90;
  /** RegularExpression Id. */
  int COLON = 91;
  /** RegularExpression Id. */
  int EQ = 92;
  /** RegularExpression Id. */
  int LE = 93;
  /** RegularExpression Id. */
  int GE = 94;
  /** RegularExpression Id. */
  int NE = 95;
  /** RegularExpression Id. */
  int SC_OR = 96;
  /** RegularExpression Id. */
  int SC_AND = 97;
  /** RegularExpression Id. */
  int INCR = 98;
  /** RegularExpression Id. */
  int DECR = 99;
  /** RegularExpression Id. */
  int PLUS = 100;
  /** RegularExpression Id. */
  int MINUS = 101;
  /** RegularExpression Id. */
  int STAR = 102;
  /** RegularExpression Id. */
  int SLASH = 103;
  /** RegularExpression Id. */
  int BIT_AND = 104;
  /** RegularExpression Id. */
  int BIT_OR = 105;
  /** RegularExpression Id. */
  int XOR = 106;
  /** RegularExpression Id. */
  int REM = 107;
  /** RegularExpression Id. */
  int LSHIFT = 108;
  /** RegularExpression Id. */
  int RSIGNEDSHIFT = 109;
  /** RegularExpression Id. */
  int RUNSIGNEDSHIFT = 110;
  /** RegularExpression Id. */
  int PLUSASSIGN = 111;
  /** RegularExpression Id. */
  int MINUSASSIGN = 112;
  /** RegularExpression Id. */
  int STARASSIGN = 113;
  /** RegularExpression Id. */
  int SLASHASSIGN = 114;
  /** RegularExpression Id. */
  int ANDASSIGN = 115;
  /** RegularExpression Id. */
  int ORASSIGN = 116;
  /** RegularExpression Id. */
  int XORASSIGN = 117;
  /** RegularExpression Id. */
  int REMASSIGN = 118;
  /** RegularExpression Id. */
  int LSHIFTASSIGN = 119;
  /** RegularExpression Id. */
  int RSIGNEDSHIFTASSIGN = 120;
  /** RegularExpression Id. */
  int RUNSIGNEDSHIFTASSIGN = 121;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "<SINGLE_LINE_COMMENT>",
    "\"sum\"",
    "\"$WB_START\"",
    "\"$WB_END\"",
    "\"$WS_START\"",
    "\"$WS_END\"",
    "\"$CELL\"",
    "\"$MERGED_CELL\"",
    "\"$ROW_BREAK\"",
    "\"abstract\"",
    "\"boolean\"",
    "\"break\"",
    "\"byte\"",
    "\"case\"",
    "\"catch\"",
    "\"char\"",
    "\"class\"",
    "\"const\"",
    "\"continue\"",
    "\"default\"",
    "\"do\"",
    "\"double\"",
    "\"else\"",
    "\"extends\"",
    "\"false\"",
    "\"final\"",
    "\"finally\"",
    "\"float\"",
    "\"for\"",
    "\"goto\"",
    "\"if\"",
    "\"implements\"",
    "\"import\"",
    "\"instanceof\"",
    "\"int\"",
    "\"interface\"",
    "\"long\"",
    "\"native\"",
    "\"new\"",
    "\"null\"",
    "\"package\"",
    "\"private\"",
    "\"protected\"",
    "\"public\"",
    "\"return\"",
    "\"short\"",
    "\"static\"",
    "\"super\"",
    "\"switch\"",
    "\"synchronized\"",
    "\"this\"",
    "\"throw\"",
    "\"throws\"",
    "\"transient\"",
    "\"true\"",
    "\"try\"",
    "\"void\"",
    "\"volatile\"",
    "\"while\"",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<HEX_LITERAL>",
    "<OCTAL_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<DECIMAL_FLOATING_POINT_LITERAL>",
    "<DECIMAL_EXPONENT>",
    "<HEXADECIMAL_FLOATING_POINT_LITERAL>",
    "<HEXADECIMAL_EXPONENT>",
    "<CHARACTER_LITERAL>",
    "<STRING_LITERAL>",
    "<IDENTIFIER>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\"=\"",
    "\">\"",
    "\"<\"",
    "\"!\"",
    "\"~\"",
    "\"?\"",
    "\":\"",
    "\"==\"",
    "\"<=\"",
    "\">=\"",
    "\"!=\"",
    "\"||\"",
    "\"&&\"",
    "\"++\"",
    "\"--\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"&\"",
    "\"|\"",
    "\"^\"",
    "\"%\"",
    "\"<<\"",
    "\">>\"",
    "\">>>\"",
    "\"+=\"",
    "\"-=\"",
    "\"*=\"",
    "\"/=\"",
    "\"&=\"",
    "\"|=\"",
    "\"^=\"",
    "\"%=\"",
    "\"<<=\"",
    "\">>=\"",
    "\">>>=\"",
  };

}

static public class Excel extends ExcelBase/*@bgen(jjtree)*/implements ExcelTreeConstants, ExcelConstants {/*@bgen(jjtree)*/
  protected JJTExcelState jjtree = new JJTExcelState();

/** Root production. */
  final public SimpleNode Input() throws ParseException {
 /*@bgen(jjtree) Input */
  ASTInput jjtn000 = new ASTInput(JJTINPUT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      WorkBook();
      jj_consume_token(0);
     jjtree.closeNodeScope(jjtn000, true);
     jjtc000 = false;
     {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
     if (jjtc000) {
       jjtree.clearNodeScope(jjtn000);
       jjtc000 = false;
     } else {
       jjtree.popNode();
     }
     if (jjte000 instanceof RuntimeException) {
       {if (true) throw (RuntimeException)jjte000;}
     }
     if (jjte000 instanceof ParseException) {
       {if (true) throw (ParseException)jjte000;}
     }
     {if (true) throw (Error)jjte000;}
    } finally {
     if (jjtc000) {
       jjtree.closeNodeScope(jjtn000, true);
     }
    }
    throw new Error("Missing return statement in function");
  }

  final public void WorkBook() throws ParseException {
 /*@bgen(jjtree) WorkBook */
  ASTWorkBook jjtn000 = new ASTWorkBook(JJTWORKBOOK);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(XLS_WB_START);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case XLS_WS_START:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        WorkSheet();
      }
      jj_consume_token(XLS_WB_END);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void WorkSheet() throws ParseException {
 /*@bgen(jjtree) WorkSheet */
    ASTWorkSheet jjtn000 = new ASTWorkSheet(JJTWORKSHEET);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    int sheetIndex;
    try {
      jj_consume_token(XLS_WS_START);
      t = jj_consume_token(INTEGER_LITERAL);
        sheetIndex=Integer.parseInt(t.image);
        jjtn000.setSheetIndex(sheetIndex);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SUM:
        case XLS_CELL:
        case XLS_ROW_BREAK:
        case FALSE:
        case FOR:
        case IF:
        case NEW:
        case SUPER:
        case THIS:
        case TRUE:
        case WHILE:
        case INTEGER_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case LBRACE:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_2;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case XLS_CELL:
          Cell(sheetIndex);
          break;
        case XLS_ROW_BREAK:
          RowBreak();
          break;
        case SUM:
        case FALSE:
        case FOR:
        case IF:
        case NEW:
        case SUPER:
        case THIS:
        case TRUE:
        case WHILE:
        case INTEGER_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case LBRACE:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          Statement(sheetIndex);
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case XLS_WS_END:
        jj_consume_token(XLS_WS_END);
        break;
      default:
        jj_la1[3] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Block(int sheetIndex) throws ParseException {
 /*@bgen(jjtree) Block */
  ASTBlock jjtn000 = new ASTBlock(JJTBLOCK);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(LBRACE);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SUM:
        case XLS_WS_START:
        case XLS_CELL:
        case XLS_ROW_BREAK:
        case FALSE:
        case FOR:
        case IF:
        case NEW:
        case SUPER:
        case THIS:
        case TRUE:
        case WHILE:
        case INTEGER_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case LBRACE:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_3;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case XLS_CELL:
          Cell(sheetIndex);
          break;
        case XLS_ROW_BREAK:
          RowBreak();
          break;
        case SUM:
        case FALSE:
        case FOR:
        case IF:
        case NEW:
        case SUPER:
        case THIS:
        case TRUE:
        case WHILE:
        case INTEGER_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case LBRACE:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          BlockStatement(sheetIndex);
          break;
        case XLS_WS_START:
          WorkSheet();
          break;
        default:
          jj_la1[5] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(RBRACE);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void RowBreak() throws ParseException {
 /*@bgen(jjtree) RowBreak */
  ASTRowBreak jjtn000 = new ASTRowBreak(JJTROWBREAK);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(XLS_ROW_BREAK);
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void BlockStatement(int sheetIndex) throws ParseException {
 /*@bgen(jjtree) BlockStatement */
  ASTBlockStatement jjtn000 = new ASTBlockStatement(JJTBLOCKSTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Statement(sheetIndex);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Statement(int sheetIndex) throws ParseException {
 /*@bgen(jjtree) Statement */
  ASTStatement jjtn000 = new ASTStatement(JJTSTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
        Block(sheetIndex);
        break;
      case FOR:
        ForStatement(sheetIndex);
        break;
      case IF:
        IfStatement(sheetIndex);
        break;
      case WHILE:
        WhileStatement(sheetIndex);
        break;
      case SUM:
      case FALSE:
      case NEW:
      case SUPER:
      case THIS:
      case TRUE:
      case INTEGER_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case BANG:
      case TILDE:
      case INCR:
      case DECR:
      case PLUS:
      case MINUS:
        StatementExpression();
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ForStatement(int sheetIndex) throws ParseException {
 /*@bgen(jjtree) ForStatement */
  ASTForStatement jjtn000 = new ASTForStatement(JJTFORSTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(FOR);
      jj_consume_token(LPAREN);
      Variable();
      jj_consume_token(COLON);
      Expression();
      jj_consume_token(RPAREN);
      Block(sheetIndex);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Variable() throws ParseException {
 /*@bgen(jjtree) Variable */
   ASTVariable jjtn000 = new ASTVariable(JJTVARIABLE);
   boolean jjtc000 = true;
   jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    jjtn000.jjtSetValue(t.image);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void IfStatement(int sheetIndex) throws ParseException {
 /*@bgen(jjtree) IfStatement */
  ASTIfStatement jjtn000 = new ASTIfStatement(JJTIFSTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(IF);
      jj_consume_token(LPAREN);
      Expression();
      jj_consume_token(RPAREN);
      Block(sheetIndex);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ELSE:
        jj_consume_token(ELSE);
        Block(sheetIndex);
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void WhileStatement(int sheetIndex) throws ParseException {
 /*@bgen(jjtree) WhileStatement */
  ASTWhileStatement jjtn000 = new ASTWhileStatement(JJTWHILESTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(WHILE);
      jj_consume_token(LPAREN);
      Expression();
      jj_consume_token(RPAREN);
      Block(sheetIndex);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public String Literal() throws ParseException {
 /*@bgen(jjtree) Literal */
    ASTLiteral jjtn000 = new ASTLiteral(JJTLITERAL);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    Boolean b;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
        t = jj_consume_token(INTEGER_LITERAL);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(new Integer(t.image));
        {if (true) return t.image;}
        break;
      case CHARACTER_LITERAL:
        t = jj_consume_token(CHARACTER_LITERAL);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(unquote(t.image));
        {if (true) return t.image;}
        break;
      case STRING_LITERAL:
        t = jj_consume_token(STRING_LITERAL);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(unquote(t.image));
        {if (true) return t.image;}
        break;
      case FALSE:
      case TRUE:
        b = BooleanLiteral();
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(b);
        {if (true) return b.toString();}
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public Boolean BooleanLiteral() throws ParseException {
 /*@bgen(jjtree) BooleanLiteral */
    ASTBooleanLiteral jjtn000 = new ASTBooleanLiteral(JJTBOOLEANLITERAL);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
        t = jj_consume_token(TRUE);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        {if (true) return Boolean.TRUE;}
        break;
      case FALSE:
        t = jj_consume_token(FALSE);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        {if (true) return Boolean.FALSE;}
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public void StatementExpression() throws ParseException {
 /*@bgen(jjtree) StatementExpression */
  ASTStatementExpression jjtn000 = new ASTStatementExpression(JJTSTATEMENTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Expression();
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void Arguments() throws ParseException {
 /*@bgen(jjtree) Arguments */
  ASTArguments jjtn000 = new ASTArguments(JJTARGUMENTS);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(LPAREN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUM:
      case FALSE:
      case NEW:
      case SUPER:
      case THIS:
      case TRUE:
      case INTEGER_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case BANG:
      case TILDE:
      case INCR:
      case DECR:
      case PLUS:
      case MINUS:
        ArgumentList();
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      jj_consume_token(RPAREN);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ArgumentList() throws ParseException {
 /*@bgen(jjtree) ArgumentList */
  ASTArgumentList jjtn000 = new ASTArgumentList(JJTARGUMENTLIST);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Expression();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        Expression();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Sum() throws ParseException {
 /*@bgen(jjtree) Sum */
  ASTSum jjtn000 = new ASTSum(JJTSUM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(SUM);
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public String ID() throws ParseException {
 /*@bgen(jjtree) ID */
    ASTID jjtn000 = new ASTID(JJTID);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        {if (true) return t.image;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public void Name() throws ParseException {
 /*@bgen(jjtree) Name */
    ASTName jjtn000 = new ASTName(JJTNAME);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String s;
    Token t1;
    Token t2;
    try {
      t1 = jj_consume_token(IDENTIFIER);
    s=t1.image;
      label_5:
      while (true) {
        if (jj_2_1(2)) {
          ;
        } else {
          break label_5;
        }
        jj_consume_token(DOT);
        t2 = jj_consume_token(IDENTIFIER);
        s+="."+t2.image;
      }
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(s);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Cell(int i) throws ParseException {
 /*@bgen(jjtree) Cell */
   ASTCell jjtn000 = new ASTCell(JJTCELL);
   boolean jjtc000 = true;
   jjtree.openNodeScope(jjtn000);Token row=null;
   Token col=null;
   Token endrow=null;
   Token endcol=null;
    try {
      jj_consume_token(XLS_CELL);
      jj_consume_token(LPAREN);
      row = jj_consume_token(INTEGER_LITERAL);
      jj_consume_token(COMMA);
      col = jj_consume_token(INTEGER_LITERAL);
      jj_consume_token(RPAREN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case XLS_MERGED_CELL:
        jj_consume_token(XLS_MERGED_CELL);
        jj_consume_token(LPAREN);
        endrow = jj_consume_token(INTEGER_LITERAL);
        jj_consume_token(COMMA);
        endcol = jj_consume_token(INTEGER_LITERAL);
        jj_consume_token(RPAREN);
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.setSheetIndex(i);
        if(row!=null && col!=null){
                    jjtn000.setRowIndex(Integer.parseInt(row.image));
                    jjtn000.setColumnIndex(Integer.parseInt(col.image));
                    jjtn000.setEndRow(endrow==null? 0: Integer.parseInt(endrow.image));
                    jjtn000.setEndCol(endcol==null? 0: Integer.parseInt(endcol.image));
        }
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

/*
 * Expression syntax follows.
 */
  final public void Expression() throws ParseException {
 /*@bgen(jjtree) Expression */
  ASTExpression jjtn000 = new ASTExpression(JJTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_2(2147483647)) {
        Assignment();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SUM:
        case FALSE:
        case NEW:
        case SUPER:
        case THIS:
        case TRUE:
        case INTEGER_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          ConditionalExpression();
          break;
        default:
          jj_la1[13] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Assignment() throws ParseException {
 /*@bgen(jjtree) Assignment */
  ASTAssignment jjtn000 = new ASTAssignment(JJTASSIGNMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      PrimaryExpression();
      AssignmentOperator();
      Expression();
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void AssignmentOperator() throws ParseException {
 /*@bgen(jjtree) AssignmentOperator */
ASTAssignmentOperator jjtn000 = new ASTAssignmentOperator(JJTASSIGNMENTOPERATOR);
boolean jjtc000 = true;
jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASSIGN:
        t = jj_consume_token(ASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case STARASSIGN:
        t = jj_consume_token(STARASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case SLASHASSIGN:
        t = jj_consume_token(SLASHASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case REMASSIGN:
        t = jj_consume_token(REMASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case PLUSASSIGN:
        t = jj_consume_token(PLUSASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case MINUSASSIGN:
        t = jj_consume_token(MINUSASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case LSHIFTASSIGN:
        t = jj_consume_token(LSHIFTASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case RSIGNEDSHIFTASSIGN:
        t = jj_consume_token(RSIGNEDSHIFTASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case RUNSIGNEDSHIFTASSIGN:
        t = jj_consume_token(RUNSIGNEDSHIFTASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case ANDASSIGN:
        t = jj_consume_token(ANDASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case XORASSIGN:
        t = jj_consume_token(XORASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      case ORASSIGN:
        t = jj_consume_token(ORASSIGN);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ConditionalExpression() throws ParseException {
 /*@bgen(jjtree) ConditionalExpression */
  ASTConditionalExpression jjtn000 = new ASTConditionalExpression(JJTCONDITIONALEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      ConditionalOrExpression();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HOOK:
        jj_consume_token(HOOK);
        Expression();
        jj_consume_token(COLON);
        ConditionalExpression();
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ConditionalOrExpression() throws ParseException {
 /*@bgen(jjtree) ConditionalOrExpression */
  ASTConditionalOrExpression jjtn000 = new ASTConditionalOrExpression(JJTCONDITIONALOREXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      ConditionalAndExpression();
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SC_OR:
          ;
          break;
        default:
          jj_la1[16] = jj_gen;
          break label_6;
        }
        jj_consume_token(SC_OR);
                                      ASTOrNode jjtn001 = new ASTOrNode(JJTORNODE);
                                      boolean jjtc001 = true;
                                      jjtree.openNodeScope(jjtn001);
        try {
          ConditionalAndExpression();
        } catch (Throwable jjte001) {
                                      if (jjtc001) {
                                        jjtree.clearNodeScope(jjtn001);
                                        jjtc001 = false;
                                      } else {
                                        jjtree.popNode();
                                      }
                                      if (jjte001 instanceof RuntimeException) {
                                        {if (true) throw (RuntimeException)jjte001;}
                                      }
                                      if (jjte001 instanceof ParseException) {
                                        {if (true) throw (ParseException)jjte001;}
                                      }
                                      {if (true) throw (Error)jjte001;}
        } finally {
                                      if (jjtc001) {
                                        jjtree.closeNodeScope(jjtn001,  2);
                                      }
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ConditionalAndExpression() throws ParseException {
 /*@bgen(jjtree) ConditionalAndExpression */
  ASTConditionalAndExpression jjtn000 = new ASTConditionalAndExpression(JJTCONDITIONALANDEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      InclusiveOrExpression();
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SC_AND:
          ;
          break;
        default:
          jj_la1[17] = jj_gen;
          break label_7;
        }
        jj_consume_token(SC_AND);
                                   ASTAndNode jjtn001 = new ASTAndNode(JJTANDNODE);
                                   boolean jjtc001 = true;
                                   jjtree.openNodeScope(jjtn001);
        try {
          InclusiveOrExpression();
        } catch (Throwable jjte001) {
                                   if (jjtc001) {
                                     jjtree.clearNodeScope(jjtn001);
                                     jjtc001 = false;
                                   } else {
                                     jjtree.popNode();
                                   }
                                   if (jjte001 instanceof RuntimeException) {
                                     {if (true) throw (RuntimeException)jjte001;}
                                   }
                                   if (jjte001 instanceof ParseException) {
                                     {if (true) throw (ParseException)jjte001;}
                                   }
                                   {if (true) throw (Error)jjte001;}
        } finally {
                                   if (jjtc001) {
                                     jjtree.closeNodeScope(jjtn001,  2);
                                   }
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void InclusiveOrExpression() throws ParseException {
 /*@bgen(jjtree) InclusiveOrExpression */
  ASTInclusiveOrExpression jjtn000 = new ASTInclusiveOrExpression(JJTINCLUSIVEOREXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      ExclusiveOrExpression();
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BIT_OR:
          ;
          break;
        default:
          jj_la1[18] = jj_gen;
          break label_8;
        }
        jj_consume_token(BIT_OR);
        ExclusiveOrExpression();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ExclusiveOrExpression() throws ParseException {
 /*@bgen(jjtree) ExclusiveOrExpression */
  ASTExclusiveOrExpression jjtn000 = new ASTExclusiveOrExpression(JJTEXCLUSIVEOREXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      AndExpression();
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case XOR:
          ;
          break;
        default:
          jj_la1[19] = jj_gen;
          break label_9;
        }
        jj_consume_token(XOR);
        AndExpression();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void AndExpression() throws ParseException {
 /*@bgen(jjtree) AndExpression */
  ASTAndExpression jjtn000 = new ASTAndExpression(JJTANDEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      EqualityExpression();
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BIT_AND:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_10;
        }
        jj_consume_token(BIT_AND);
        EqualityExpression();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void EqualityExpression() throws ParseException {
 /*@bgen(jjtree) EqualityExpression */
  ASTEqualityExpression jjtn000 = new ASTEqualityExpression(JJTEQUALITYEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      InstanceOfExpression();
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EQ:
        case NE:
          ;
          break;
        default:
          jj_la1[21] = jj_gen;
          break label_11;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EQ:
          jj_consume_token(EQ);
         ASTEQNode jjtn001 = new ASTEQNode(JJTEQNODE);
         boolean jjtc001 = true;
         jjtree.openNodeScope(jjtn001);
          try {
            InstanceOfExpression();
          } catch (Throwable jjte001) {
         if (jjtc001) {
           jjtree.clearNodeScope(jjtn001);
           jjtc001 = false;
         } else {
           jjtree.popNode();
         }
         if (jjte001 instanceof RuntimeException) {
           {if (true) throw (RuntimeException)jjte001;}
         }
         if (jjte001 instanceof ParseException) {
           {if (true) throw (ParseException)jjte001;}
         }
         {if (true) throw (Error)jjte001;}
          } finally {
         if (jjtc001) {
           jjtree.closeNodeScope(jjtn001,  2);
         }
          }
          break;
        case NE:
          jj_consume_token(NE);
          ASTNENode jjtn002 = new ASTNENode(JJTNENODE);
          boolean jjtc002 = true;
          jjtree.openNodeScope(jjtn002);
          try {
            InstanceOfExpression();
          } catch (Throwable jjte002) {
          if (jjtc002) {
            jjtree.clearNodeScope(jjtn002);
            jjtc002 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte002 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte002;}
          }
          if (jjte002 instanceof ParseException) {
            {if (true) throw (ParseException)jjte002;}
          }
          {if (true) throw (Error)jjte002;}
          } finally {
          if (jjtc002) {
            jjtree.closeNodeScope(jjtn002,  2);
          }
          }
          break;
        default:
          jj_la1[22] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void InstanceOfExpression() throws ParseException {
 /*@bgen(jjtree) InstanceOfExpression */
  ASTInstanceOfExpression jjtn000 = new ASTInstanceOfExpression(JJTINSTANCEOFEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      RelationalExpression();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INSTANCEOF:
        jj_consume_token(INSTANCEOF);
        Type();
        break;
      default:
        jj_la1[23] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void RelationalExpression() throws ParseException {
 /*@bgen(jjtree) RelationalExpression */
  ASTRelationalExpression jjtn000 = new ASTRelationalExpression(JJTRELATIONALEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      ShiftExpression();
      label_12:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case GT:
        case LT:
        case LE:
        case GE:
          ;
          break;
        default:
          jj_la1[24] = jj_gen;
          break label_12;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LT:
          jj_consume_token(LT);
       ASTLTNode jjtn001 = new ASTLTNode(JJTLTNODE);
       boolean jjtc001 = true;
       jjtree.openNodeScope(jjtn001);
          try {
            ShiftExpression();
          } catch (Throwable jjte001) {
       if (jjtc001) {
         jjtree.clearNodeScope(jjtn001);
         jjtc001 = false;
       } else {
         jjtree.popNode();
       }
       if (jjte001 instanceof RuntimeException) {
         {if (true) throw (RuntimeException)jjte001;}
       }
       if (jjte001 instanceof ParseException) {
         {if (true) throw (ParseException)jjte001;}
       }
       {if (true) throw (Error)jjte001;}
          } finally {
       if (jjtc001) {
         jjtree.closeNodeScope(jjtn001,  2);
       }
          }
          break;
        case GT:
          jj_consume_token(GT);
         ASTGTNode jjtn002 = new ASTGTNode(JJTGTNODE);
         boolean jjtc002 = true;
         jjtree.openNodeScope(jjtn002);
          try {
            ShiftExpression();
          } catch (Throwable jjte002) {
         if (jjtc002) {
           jjtree.clearNodeScope(jjtn002);
           jjtc002 = false;
         } else {
           jjtree.popNode();
         }
         if (jjte002 instanceof RuntimeException) {
           {if (true) throw (RuntimeException)jjte002;}
         }
         if (jjte002 instanceof ParseException) {
           {if (true) throw (ParseException)jjte002;}
         }
         {if (true) throw (Error)jjte002;}
          } finally {
         if (jjtc002) {
           jjtree.closeNodeScope(jjtn002,  2);
         }
          }
          break;
        case LE:
          jj_consume_token(LE);
          ASTLENode jjtn003 = new ASTLENode(JJTLENODE);
          boolean jjtc003 = true;
          jjtree.openNodeScope(jjtn003);
          try {
            ShiftExpression();
          } catch (Throwable jjte003) {
          if (jjtc003) {
            jjtree.clearNodeScope(jjtn003);
            jjtc003 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte003 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte003;}
          }
          if (jjte003 instanceof ParseException) {
            {if (true) throw (ParseException)jjte003;}
          }
          {if (true) throw (Error)jjte003;}
          } finally {
          if (jjtc003) {
            jjtree.closeNodeScope(jjtn003,  2);
          }
          }
          break;
        case GE:
          jj_consume_token(GE);
         ASTGENode jjtn004 = new ASTGENode(JJTGENODE);
         boolean jjtc004 = true;
         jjtree.openNodeScope(jjtn004);
          try {
            ShiftExpression();
          } catch (Throwable jjte004) {
         if (jjtc004) {
           jjtree.clearNodeScope(jjtn004);
           jjtc004 = false;
         } else {
           jjtree.popNode();
         }
         if (jjte004 instanceof RuntimeException) {
           {if (true) throw (RuntimeException)jjte004;}
         }
         if (jjte004 instanceof ParseException) {
           {if (true) throw (ParseException)jjte004;}
         }
         {if (true) throw (Error)jjte004;}
          } finally {
         if (jjtc004) {
           jjtree.closeNodeScope(jjtn004,  2);
         }
          }
          break;
        default:
          jj_la1[25] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ShiftExpression() throws ParseException {
 /*@bgen(jjtree) ShiftExpression */
  ASTShiftExpression jjtn000 = new ASTShiftExpression(JJTSHIFTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      AdditiveExpression();
      label_13:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LSHIFT:
        case RSIGNEDSHIFT:
        case RUNSIGNEDSHIFT:
          ;
          break;
        default:
          jj_la1[26] = jj_gen;
          break label_13;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LSHIFT:
          jj_consume_token(LSHIFT);
          break;
        case RSIGNEDSHIFT:
          jj_consume_token(RSIGNEDSHIFT);
          break;
        case RUNSIGNEDSHIFT:
          jj_consume_token(RUNSIGNEDSHIFT);
          break;
        default:
          jj_la1[27] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        AdditiveExpression();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void AdditiveExpression() throws ParseException {
    MultiplicativeExpression();
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[28] = jj_gen;
        break label_14;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
                 ASTAddNode jjtn001 = new ASTAddNode(JJTADDNODE);
                 boolean jjtc001 = true;
                 jjtree.openNodeScope(jjtn001);
        try {
          MultiplicativeExpression();
        } catch (Throwable jjte001) {
                 if (jjtc001) {
                   jjtree.clearNodeScope(jjtn001);
                   jjtc001 = false;
                 } else {
                   jjtree.popNode();
                 }
                 if (jjte001 instanceof RuntimeException) {
                   {if (true) throw (RuntimeException)jjte001;}
                 }
                 if (jjte001 instanceof ParseException) {
                   {if (true) throw (ParseException)jjte001;}
                 }
                 {if (true) throw (Error)jjte001;}
        } finally {
                 if (jjtc001) {
                   jjtree.closeNodeScope(jjtn001,  2);
                 }
        }
        break;
      case MINUS:
        jj_consume_token(MINUS);
                ASTSubtractNode jjtn002 = new ASTSubtractNode(JJTSUBTRACTNODE);
                boolean jjtc002 = true;
                jjtree.openNodeScope(jjtn002);
        try {
          MultiplicativeExpression();
        } catch (Throwable jjte002) {
                if (jjtc002) {
                  jjtree.clearNodeScope(jjtn002);
                  jjtc002 = false;
                } else {
                  jjtree.popNode();
                }
                if (jjte002 instanceof RuntimeException) {
                  {if (true) throw (RuntimeException)jjte002;}
                }
                if (jjte002 instanceof ParseException) {
                  {if (true) throw (ParseException)jjte002;}
                }
                {if (true) throw (Error)jjte002;}
        } finally {
                if (jjtc002) {
                  jjtree.closeNodeScope(jjtn002,  2);
                }
        }
        break;
      default:
        jj_la1[29] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void MultiplicativeExpression() throws ParseException {
    UnaryExpression();
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
      case SLASH:
      case REM:
        ;
        break;
      default:
        jj_la1[30] = jj_gen;
        break label_15;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
        jj_consume_token(STAR);
             ASTMulNode jjtn001 = new ASTMulNode(JJTMULNODE);
             boolean jjtc001 = true;
             jjtree.openNodeScope(jjtn001);
        try {
          UnaryExpression();
        } catch (Throwable jjte001) {
             if (jjtc001) {
               jjtree.clearNodeScope(jjtn001);
               jjtc001 = false;
             } else {
               jjtree.popNode();
             }
             if (jjte001 instanceof RuntimeException) {
               {if (true) throw (RuntimeException)jjte001;}
             }
             if (jjte001 instanceof ParseException) {
               {if (true) throw (ParseException)jjte001;}
             }
             {if (true) throw (Error)jjte001;}
        } finally {
             if (jjtc001) {
               jjtree.closeNodeScope(jjtn001,  2);
             }
        }
        break;
      case SLASH:
        jj_consume_token(SLASH);
             ASTDivNode jjtn002 = new ASTDivNode(JJTDIVNODE);
             boolean jjtc002 = true;
             jjtree.openNodeScope(jjtn002);
        try {
          UnaryExpression();
        } catch (Throwable jjte002) {
             if (jjtc002) {
               jjtree.clearNodeScope(jjtn002);
               jjtc002 = false;
             } else {
               jjtree.popNode();
             }
             if (jjte002 instanceof RuntimeException) {
               {if (true) throw (RuntimeException)jjte002;}
             }
             if (jjte002 instanceof ParseException) {
               {if (true) throw (ParseException)jjte002;}
             }
             {if (true) throw (Error)jjte002;}
        } finally {
             if (jjtc002) {
               jjtree.closeNodeScope(jjtn002,  2);
             }
        }
        break;
      case REM:
        jj_consume_token(REM);
             ASTModNode jjtn003 = new ASTModNode(JJTMODNODE);
             boolean jjtc003 = true;
             jjtree.openNodeScope(jjtn003);
        try {
          UnaryExpression();
        } catch (Throwable jjte003) {
             if (jjtc003) {
               jjtree.clearNodeScope(jjtn003);
               jjtc003 = false;
             } else {
               jjtree.popNode();
             }
             if (jjte003 instanceof RuntimeException) {
               {if (true) throw (RuntimeException)jjte003;}
             }
             if (jjte003 instanceof ParseException) {
               {if (true) throw (ParseException)jjte003;}
             }
             {if (true) throw (Error)jjte003;}
        } finally {
             if (jjtc003) {
               jjtree.closeNodeScope(jjtn003,  2);
             }
        }
        break;
      default:
        jj_la1[31] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void UnaryExpression() throws ParseException {
 /*@bgen(jjtree) UnaryExpression */
  ASTUnaryExpression jjtn000 = new ASTUnaryExpression(JJTUNARYEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
          break;
        case MINUS:
          jj_consume_token(MINUS);
          break;
        default:
          jj_la1[32] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        UnaryExpression();
        break;
      case INCR:
        PreIncrementExpression();
        break;
      case DECR:
        PreDecrementExpression();
        break;
      case SUM:
      case FALSE:
      case NEW:
      case SUPER:
      case THIS:
      case TRUE:
      case INTEGER_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case BANG:
      case TILDE:
        UnaryExpressionNotPlusMinus();
        break;
      default:
        jj_la1[33] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PreIncrementExpression() throws ParseException {
 /*@bgen(jjtree) PreIncrementExpression */
  ASTPreIncrementExpression jjtn000 = new ASTPreIncrementExpression(JJTPREINCREMENTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(INCR);
      PrimaryExpression();
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PreDecrementExpression() throws ParseException {
 /*@bgen(jjtree) PreDecrementExpression */
  ASTPreDecrementExpression jjtn000 = new ASTPreDecrementExpression(JJTPREDECREMENTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(DECR);
      PrimaryExpression();
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void UnaryExpressionNotPlusMinus() throws ParseException {
 /*@bgen(jjtree) UnaryExpressionNotPlusMinus */
  ASTUnaryExpressionNotPlusMinus jjtn000 = new ASTUnaryExpressionNotPlusMinus(JJTUNARYEXPRESSIONNOTPLUSMINUS);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BANG:
      case TILDE:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TILDE:
          jj_consume_token(TILDE);
          UnaryExpression();
          break;
        case BANG:
          jj_consume_token(BANG);
             ASTNotNode jjtn001 = new ASTNotNode(JJTNOTNODE);
             boolean jjtc001 = true;
             jjtree.openNodeScope(jjtn001);
          try {
            UnaryExpression();
          } catch (Throwable jjte001) {
             if (jjtc001) {
               jjtree.clearNodeScope(jjtn001);
               jjtc001 = false;
             } else {
               jjtree.popNode();
             }
             if (jjte001 instanceof RuntimeException) {
               {if (true) throw (RuntimeException)jjte001;}
             }
             if (jjte001 instanceof ParseException) {
               {if (true) throw (ParseException)jjte001;}
             }
             {if (true) throw (Error)jjte001;}
          } finally {
             if (jjtc001) {
               jjtree.closeNodeScope(jjtn001,  1);
             }
          }
          break;
        default:
          jj_la1[34] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[35] = jj_gen;
        if (jj_2_3(2147483647)) {
          CastExpression();
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case SUM:
          case FALSE:
          case NEW:
          case SUPER:
          case THIS:
          case TRUE:
          case INTEGER_LITERAL:
          case CHARACTER_LITERAL:
          case STRING_LITERAL:
          case IDENTIFIER:
          case LPAREN:
            PostfixExpression();
            break;
          default:
            jj_la1[36] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

// This production is to determine lookahead only.  The LOOKAHEAD specifications
// below are not used, but they are there just to indicate that we know about
// this.
  final public void CastLookahead() throws ParseException {
 /*@bgen(jjtree) CastLookahead */
  ASTCastLookahead jjtn000 = new ASTCastLookahead(JJTCASTLOOKAHEAD);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_4(2)) {
        jj_consume_token(LPAREN);
        PrimitiveType();
      } else if (jj_2_5(2147483647)) {
        jj_consume_token(LPAREN);
        Name();
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAREN:
          jj_consume_token(LPAREN);
          Name();
          jj_consume_token(RPAREN);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case TILDE:
            jj_consume_token(TILDE);
            break;
          case BANG:
            jj_consume_token(BANG);
            break;
          case LPAREN:
            jj_consume_token(LPAREN);
            break;
          case IDENTIFIER:
            jj_consume_token(IDENTIFIER);
            break;
          case THIS:
            jj_consume_token(THIS);
            break;
          case SUPER:
            jj_consume_token(SUPER);
            break;
          case NEW:
            jj_consume_token(NEW);
            break;
          case FALSE:
          case TRUE:
          case INTEGER_LITERAL:
          case CHARACTER_LITERAL:
          case STRING_LITERAL:
            Literal();
            break;
          default:
            jj_la1[37] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[38] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PostfixExpression() throws ParseException {
 /*@bgen(jjtree) PostfixExpression */
  ASTPostfixExpression jjtn000 = new ASTPostfixExpression(JJTPOSTFIXEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      PrimaryExpression();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INCR:
      case DECR:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INCR:
          jj_consume_token(INCR);
          break;
        case DECR:
          jj_consume_token(DECR);
          break;
        default:
          jj_la1[39] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[40] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void CastExpression() throws ParseException {
 /*@bgen(jjtree) CastExpression */
  ASTCastExpression jjtn000 = new ASTCastExpression(JJTCASTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_6(2)) {
        jj_consume_token(LPAREN);
        PrimitiveType();
        label_16:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LBRACKET:
            ;
            break;
          default:
            jj_la1[41] = jj_gen;
            break label_16;
          }
          jj_consume_token(LBRACKET);
          jj_consume_token(RBRACKET);
        }
        jj_consume_token(RPAREN);
        UnaryExpression();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAREN:
          jj_consume_token(LPAREN);
          Name();
          label_17:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case LBRACKET:
              ;
              break;
            default:
              jj_la1[42] = jj_gen;
              break label_17;
            }
            jj_consume_token(LBRACKET);
            jj_consume_token(RBRACKET);
          }
          jj_consume_token(RPAREN);
          UnaryExpressionNotPlusMinus();
          break;
        default:
          jj_la1[43] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PrimaryExpression() throws ParseException {
 /*@bgen(jjtree) PrimaryExpression */
  ASTPrimaryExpression jjtn000 = new ASTPrimaryExpression(JJTPRIMARYEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      PrimaryPrefix();
      label_18:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAREN:
        case LBRACKET:
        case DOT:
          ;
          break;
        default:
          jj_la1[44] = jj_gen;
          break label_18;
        }
        PrimarySuffix();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PrimaryPrefix() throws ParseException {
 /*@bgen(jjtree) PrimaryPrefix */
  ASTPrimaryPrefix jjtn000 = new ASTPrimaryPrefix(JJTPRIMARYPREFIX);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FALSE:
      case TRUE:
      case INTEGER_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
        Literal();
        break;
      case SUM:
        Sum();
        break;
      case IDENTIFIER:
        Name();
        break;
      case THIS:
        jj_consume_token(THIS);
        break;
      case SUPER:
        jj_consume_token(SUPER);
        jj_consume_token(DOT);
        jj_consume_token(IDENTIFIER);
        break;
      case LPAREN:
        jj_consume_token(LPAREN);
        Expression();
        jj_consume_token(RPAREN);
        break;
      case NEW:
        AllocationExpression();
        break;
      default:
        jj_la1[45] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PrimarySuffix() throws ParseException {
Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACKET:
      jj_consume_token(LBRACKET);
      Expression();
      jj_consume_token(RBRACKET);
      break;
    case DOT:
      jj_consume_token(DOT);
      MethodName();
      break;
    case LPAREN:
    ASTMethodInvocation jjtn001 = new ASTMethodInvocation(JJTMETHODINVOCATION);
    boolean jjtc001 = true;
    jjtree.openNodeScope(jjtn001);
      try {
        Arguments();
      } catch (Throwable jjte001) {
    if (jjtc001) {
      jjtree.clearNodeScope(jjtn001);
      jjtc001 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte001 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte001;}
    }
    if (jjte001 instanceof ParseException) {
      {if (true) throw (ParseException)jjte001;}
    }
    {if (true) throw (Error)jjte001;}
      } finally {
    if (jjtc001) {
      jjtree.closeNodeScope(jjtn001,  1);
    }
      }
      break;
    default:
      jj_la1[46] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public String MethodName() throws ParseException {
 /*@bgen(jjtree) MethodName */
    ASTMethodName jjtn000 = new ASTMethodName(JJTMETHODNAME);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.jjtSetValue(t.image);
        {if (true) return t.image;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

/*
 * Type, name and expression syntax follows.
 */
  final public void Type() throws ParseException {
 /*@bgen(jjtree) Type */
  ASTType jjtn000 = new ASTType(JJTTYPE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FLOAT:
      case INT:
      case LONG:
      case SHORT:
        PrimitiveType();
        break;
      case IDENTIFIER:
        Name();
        break;
      default:
        jj_la1[47] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_19:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACKET:
          ;
          break;
        default:
          jj_la1[48] = jj_gen;
          break label_19;
        }
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void PrimitiveType() throws ParseException {
 /*@bgen(jjtree) PrimitiveType */
  ASTPrimitiveType jjtn000 = new ASTPrimitiveType(JJTPRIMITIVETYPE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
        jj_consume_token(BOOLEAN);
        break;
      case CHAR:
        jj_consume_token(CHAR);
        break;
      case BYTE:
        jj_consume_token(BYTE);
        break;
      case SHORT:
        jj_consume_token(SHORT);
        break;
      case INT:
        jj_consume_token(INT);
        break;
      case LONG:
        jj_consume_token(LONG);
        break;
      case FLOAT:
        jj_consume_token(FLOAT);
        break;
      case DOUBLE:
        jj_consume_token(DOUBLE);
        break;
      default:
        jj_la1[49] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ResultType() throws ParseException {
 /*@bgen(jjtree) ResultType */
  ASTResultType jjtn000 = new ASTResultType(JJTRESULTTYPE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VOID:
        jj_consume_token(VOID);
        break;
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FLOAT:
      case INT:
      case LONG:
      case SHORT:
      case IDENTIFIER:
        Type();
        break;
      default:
        jj_la1[50] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void AllocationExpression() throws ParseException {
 /*@bgen(jjtree) AllocationExpression */
  ASTAllocationExpression jjtn000 = new ASTAllocationExpression(JJTALLOCATIONEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_7(2)) {
        jj_consume_token(NEW);
        PrimitiveType();
        ArrayDimensions();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEW:
          jj_consume_token(NEW);
          Name();
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LPAREN:
            Arguments();
            break;
          case LBRACKET:
            ArrayDimensions();
            break;
          default:
            jj_la1[51] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[52] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

/*
 * The second LOOKAHEAD specification below is to parse to PrimarySuffix
 * if there is an expression between the "[...]".
 */
  final public void ArrayDimensions() throws ParseException {
 /*@bgen(jjtree) ArrayDimensions */
  ASTArrayDimensions jjtn000 = new ASTArrayDimensions(JJTARRAYDIMENSIONS);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      label_20:
      while (true) {
        jj_consume_token(LBRACKET);
        Expression();
        jj_consume_token(RBRACKET);
        if (jj_2_8(2)) {
          ;
        } else {
          break label_20;
        }
      }
      label_21:
      while (true) {
        if (jj_2_9(2)) {
          ;
        } else {
          break label_21;
        }
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_3R_107() {
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3R_100() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_107()) {
    jj_scanpos = xsp;
    if (jj_3R_108()) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_109()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_77() {
    if (jj_scan_token(TRUE)) return true;
    return false;
  }

  private boolean jj_3R_76() {
    if (jj_3R_81()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_87()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_71() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_77()) {
    jj_scanpos = xsp;
    if (jj_3R_78()) return true;
    }
    return false;
  }

  private boolean jj_3R_70() {
    if (jj_3R_76()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_85()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_62() {
    if (jj_3R_70()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_82()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_68() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_66() {
    if (jj_3R_71()) return true;
    return false;
  }

  private boolean jj_3R_55() {
    if (jj_3R_62()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_79()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_65() {
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_61() {
    if (jj_3R_69()) return true;
    return false;
  }

  private boolean jj_3R_60() {
    if (jj_scan_token(DOT)) return true;
    if (jj_3R_68()) return true;
    return false;
  }

  private boolean jj_3R_64() {
    if (jj_scan_token(CHARACTER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_52() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_59()) {
    jj_scanpos = xsp;
    if (jj_3R_60()) {
    jj_scanpos = xsp;
    if (jj_3R_61()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_59() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_27()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_41() {
    if (jj_scan_token(ORASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_40() {
    if (jj_scan_token(XORASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_56() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_63()) {
    jj_scanpos = xsp;
    if (jj_3R_64()) {
    jj_scanpos = xsp;
    if (jj_3R_65()) {
    jj_scanpos = xsp;
    if (jj_3R_66()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3R_63() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_29() {
    if (jj_3R_52()) return true;
    return false;
  }

  private boolean jj_3R_51() {
    if (jj_3R_58()) return true;
    return false;
  }

  private boolean jj_3R_39() {
    if (jj_scan_token(ANDASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_50() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_27()) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  private boolean jj_3R_133() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_49() {
    if (jj_scan_token(SUPER)) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_38() {
    if (jj_scan_token(RUNSIGNEDSHIFTASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_48() {
    if (jj_3R_26()) return true;
    return false;
  }

  private boolean jj_3R_37() {
    if (jj_scan_token(RSIGNEDSHIFTASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_47() {
    if (jj_3R_57()) return true;
    return false;
  }

  private boolean jj_3R_134() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_132() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_scan_token(99)) return true;
    }
    return false;
  }

  private boolean jj_3R_46() {
    if (jj_3R_56()) return true;
    return false;
  }

  private boolean jj_3R_28() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_46()) {
    jj_scanpos = xsp;
    if (jj_3R_47()) {
    jj_scanpos = xsp;
    if (jj_3R_48()) {
    jj_scanpos = xsp;
    if (jj_scan_token(55)) {
    jj_scanpos = xsp;
    if (jj_3R_49()) {
    jj_scanpos = xsp;
    if (jj_3R_50()) {
    jj_scanpos = xsp;
    if (jj_3R_51()) return true;
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_36() {
    if (jj_scan_token(LSHIFTASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_35() {
    if (jj_scan_token(MINUSASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_22() {
    if (jj_3R_28()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_29()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_34() {
    if (jj_scan_token(PLUSASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_131() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_26()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_134()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_117()) return true;
    return false;
  }

  private boolean jj_3R_33() {
    if (jj_scan_token(REMASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_129() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3R_131()) return true;
    }
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_25()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_133()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_32() {
    if (jj_scan_token(SLASHASSIGN)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_26()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_130() {
    if (jj_3R_22()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_132()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_31() {
    if (jj_scan_token(STARASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_30() {
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  private boolean jj_3R_23() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) {
    jj_scanpos = xsp;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) {
    jj_scanpos = xsp;
    if (jj_3R_38()) {
    jj_scanpos = xsp;
    if (jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) {
    jj_scanpos = xsp;
    if (jj_3R_41()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_43() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_26()) return true;
    if (jj_scan_token(RPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(89)) {
    jj_scanpos = xsp;
    if (jj_scan_token(88)) {
    jj_scanpos = xsp;
    if (jj_scan_token(76)) {
    jj_scanpos = xsp;
    if (jj_scan_token(75)) {
    jj_scanpos = xsp;
    if (jj_scan_token(55)) {
    jj_scanpos = xsp;
    if (jj_scan_token(52)) {
    jj_scanpos = xsp;
    if (jj_scan_token(43)) {
    jj_scanpos = xsp;
    if (jj_3R_53()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_42() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_26()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_22()) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_54() {
    if (jj_3R_22()) return true;
    if (jj_3R_23()) return true;
    if (jj_3R_27()) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3R_42()) {
    jj_scanpos = xsp;
    if (jj_3R_43()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_45() {
    if (jj_3R_55()) return true;
    return false;
  }

  private boolean jj_3R_123() {
    if (jj_3R_130()) return true;
    return false;
  }

  private boolean jj_3R_44() {
    if (jj_3R_54()) return true;
    return false;
  }

  private boolean jj_3R_27() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_44()) {
    jj_scanpos = xsp;
    if (jj_3R_45()) return true;
    }
    return false;
  }

  private boolean jj_3R_128() {
    if (jj_scan_token(BANG)) return true;
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_122() {
    if (jj_3R_129()) return true;
    return false;
  }

  private boolean jj_3R_127() {
    if (jj_scan_token(TILDE)) return true;
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_121() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_127()) {
    jj_scanpos = xsp;
    if (jj_3R_128()) return true;
    }
    return false;
  }

  private boolean jj_3R_117() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_121()) {
    jj_scanpos = xsp;
    if (jj_3R_122()) {
    jj_scanpos = xsp;
    if (jj_3R_123()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_116() {
    if (jj_scan_token(DECR)) return true;
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_115() {
    if (jj_scan_token(INCR)) return true;
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3R_118() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_124()) {
    jj_scanpos = xsp;
    if (jj_3R_125()) {
    jj_scanpos = xsp;
    if (jj_3R_126()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_113() {
    if (jj_3R_117()) return true;
    return false;
  }

  private boolean jj_3R_112() {
    if (jj_3R_116()) return true;
    return false;
  }

  private boolean jj_3R_111() {
    if (jj_3R_115()) return true;
    return false;
  }

  private boolean jj_3R_110() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(100)) {
    jj_scanpos = xsp;
    if (jj_scan_token(101)) return true;
    }
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_101() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_110()) {
    jj_scanpos = xsp;
    if (jj_3R_111()) {
    jj_scanpos = xsp;
    if (jj_3R_112()) {
    jj_scanpos = xsp;
    if (jj_3R_113()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3R_126() {
    if (jj_scan_token(REM)) return true;
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_102() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(108)) {
    jj_scanpos = xsp;
    if (jj_scan_token(109)) {
    jj_scanpos = xsp;
    if (jj_scan_token(110)) return true;
    }
    }
    if (jj_3R_94()) return true;
    return false;
  }

  private boolean jj_3R_125() {
    if (jj_scan_token(SLASH)) return true;
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_74() {
    if (jj_3R_72()) return true;
    return false;
  }

  private boolean jj_3R_124() {
    if (jj_scan_token(STAR)) return true;
    if (jj_3R_101()) return true;
    return false;
  }

  private boolean jj_3R_120() {
    if (jj_scan_token(MINUS)) return true;
    if (jj_3R_98()) return true;
    return false;
  }

  private boolean jj_3R_98() {
    if (jj_3R_101()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_118()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_26() {
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_1()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_119() {
    if (jj_scan_token(PLUS)) return true;
    if (jj_3R_98()) return true;
    return false;
  }

  private boolean jj_3R_114() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_119()) {
    jj_scanpos = xsp;
    if (jj_3R_120()) return true;
    }
    return false;
  }

  private boolean jj_3R_95() {
    if (jj_scan_token(INSTANCEOF)) return true;
    if (jj_3R_100()) return true;
    return false;
  }

  private boolean jj_3R_73() {
    if (jj_3R_69()) return true;
    return false;
  }

  private boolean jj_3R_94() {
    if (jj_3R_98()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_114()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_27()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_72() {
    Token xsp;
    if (jj_3_8()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_8()) { jj_scanpos = xsp; break; }
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_9()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_92() {
    if (jj_3R_94()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_102()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_83() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_27()) return true;
    return false;
  }

  private boolean jj_3R_106() {
    if (jj_scan_token(GE)) return true;
    if (jj_3R_92()) return true;
    return false;
  }

  private boolean jj_3R_67() {
    if (jj_scan_token(NEW)) return true;
    if (jj_3R_26()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_73()) {
    jj_scanpos = xsp;
    if (jj_3R_74()) return true;
    }
    return false;
  }

  private boolean jj_3R_105() {
    if (jj_scan_token(LE)) return true;
    if (jj_3R_92()) return true;
    return false;
  }

  private boolean jj_3R_104() {
    if (jj_scan_token(GT)) return true;
    if (jj_3R_92()) return true;
    return false;
  }

  private boolean jj_3R_91() {
    if (jj_scan_token(BIT_AND)) return true;
    if (jj_3R_86()) return true;
    return false;
  }

  private boolean jj_3R_57() {
    if (jj_scan_token(SUM)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(NEW)) return true;
    if (jj_3R_25()) return true;
    if (jj_3R_72()) return true;
    return false;
  }

  private boolean jj_3R_58() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3R_67()) return true;
    }
    return false;
  }

  private boolean jj_3R_90() {
    if (jj_3R_92()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_99()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_103() {
    if (jj_scan_token(LT)) return true;
    if (jj_3R_92()) return true;
    return false;
  }

  private boolean jj_3R_99() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_103()) {
    jj_scanpos = xsp;
    if (jj_3R_104()) {
    jj_scanpos = xsp;
    if (jj_3R_105()) {
    jj_scanpos = xsp;
    if (jj_3R_106()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3R_109() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3R_75() {
    if (jj_3R_80()) return true;
    return false;
  }

  private boolean jj_3R_88() {
    if (jj_3R_90()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_95()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_87() {
    if (jj_scan_token(BIT_OR)) return true;
    if (jj_3R_81()) return true;
    return false;
  }

  private boolean jj_3R_80() {
    if (jj_3R_27()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_83()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_89() {
    if (jj_scan_token(XOR)) return true;
    if (jj_3R_84()) return true;
    return false;
  }

  private boolean jj_3R_97() {
    if (jj_scan_token(NE)) return true;
    if (jj_3R_88()) return true;
    return false;
  }

  private boolean jj_3R_108() {
    if (jj_3R_26()) return true;
    return false;
  }

  private boolean jj_3R_85() {
    if (jj_scan_token(SC_AND)) return true;
    if (jj_3R_76()) return true;
    return false;
  }

  private boolean jj_3R_96() {
    if (jj_scan_token(EQ)) return true;
    if (jj_3R_88()) return true;
    return false;
  }

  private boolean jj_3R_93() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_96()) {
    jj_scanpos = xsp;
    if (jj_3R_97()) return true;
    }
    return false;
  }

  private boolean jj_3R_69() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_75()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  private boolean jj_3R_86() {
    if (jj_3R_88()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_93()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_82() {
    if (jj_scan_token(SC_OR)) return true;
    if (jj_3R_70()) return true;
    return false;
  }

  private boolean jj_3R_84() {
    if (jj_3R_86()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_91()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_79() {
    if (jj_scan_token(HOOK)) return true;
    if (jj_3R_27()) return true;
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_55()) return true;
    return false;
  }

  private boolean jj_3R_78() {
    if (jj_scan_token(FALSE)) return true;
    return false;
  }

  private boolean jj_3R_81() {
    if (jj_3R_84()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_89()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_25() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(15)) {
    jj_scanpos = xsp;
    if (jj_scan_token(20)) {
    jj_scanpos = xsp;
    if (jj_scan_token(17)) {
    jj_scanpos = xsp;
    if (jj_scan_token(50)) {
    jj_scanpos = xsp;
    if (jj_scan_token(39)) {
    jj_scanpos = xsp;
    if (jj_scan_token(41)) {
    jj_scanpos = xsp;
    if (jj_scan_token(32)) {
    jj_scanpos = xsp;
    if (jj_scan_token(26)) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_53() {
    if (jj_3R_56()) return true;
    return false;
  }

  /** Generated Token Manager. */
  public ExcelTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[53];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static private int[] jj_la1_3;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
      jj_la1_init_3();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200,0x20002840,0x20002840,0x400,0x20002a40,0x20002a40,0x20000040,0x8000000,0x20000000,0x20000000,0x20000040,0x0,0x1000,0x20000040,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x20000040,0x0,0x0,0x20000040,0x20000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x20000040,0x0,0x4128000,0x0,0x4128000,0x4128000,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x8890080a,0x8890080a,0x0,0x8890080a,0x8890080a,0x8890080a,0x0,0x8000000,0x8000000,0x8900800,0x0,0x0,0x8900800,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x40,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x8900800,0x0,0x0,0x8900800,0x8900800,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x8900800,0x0,0x40281,0x0,0x40281,0x20040281,0x0,0x800,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x0,0x3005e01,0x3005e01,0x0,0x3005e01,0x3005e01,0x3005e01,0x0,0x601,0x0,0x3001e01,0x80000,0x0,0x3001e01,0x200000,0x4000000,0x0,0x0,0x0,0x0,0x0,0x90000000,0x90000000,0x0,0x60c00000,0x60c00000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3001e01,0x3000000,0x3000000,0x1e01,0x3001e01,0x1000,0x0,0x0,0x10000,0x10000,0x1000,0x111000,0x1e01,0x111000,0x800,0x10000,0x0,0x800,0x11000,0x0,};
   }
   private static void jj_la1_init_3() {
      jj_la1_3 = new int[] {0x0,0x3c,0x3c,0x0,0x3c,0x3c,0x3c,0x0,0x0,0x0,0x3c,0x0,0x0,0x3c,0x3ff8000,0x0,0x1,0x2,0x200,0x400,0x100,0x0,0x0,0x0,0x0,0x0,0x7000,0x7000,0x30,0x30,0x8c0,0x8c0,0x30,0x3c,0x0,0x0,0x0,0x0,0x0,0xc,0xc,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[9];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Excel(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Excel(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ExcelTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 53; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 53; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Excel(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ExcelTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 53; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 53; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Excel(ExcelTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 53; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ExcelTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 53; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[122];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 53; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
          if ((jj_la1_3[i] & (1<<j)) != 0) {
            la1tokens[96+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 122; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 9; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}

static public interface Log {

    public boolean isDebugEnabled();

    public boolean isErrorEnabled();

    public boolean isFatalEnabled();

    public boolean isInfoEnabled();

    public boolean isTraceEnabled();

    public boolean isWarnEnabled();

    public void trace(Object o);

    public void trace(Object o, Throwable thrwbl);

    public void debug(Object o);

    public void debug(Object o, Throwable thrwbl);

    public void info(Object o);

    public void info(Object o, Throwable thrwbl);

    public void warn(Object o);

    public void warn(Object o, Throwable thrwbl);

    public void error(Object o);

    public void error(Object o, Throwable thrwbl);

    public void fatal(Object o);

    public void fatal(Object o, Throwable thrwbl);
}

static public class LogFactory {
    static Log log=new Log(){

        public boolean isDebugEnabled() {
            return false;
        }

        public boolean isErrorEnabled() {
            return false;
        }

        public boolean isFatalEnabled() {
            return false;
        }

        public boolean isInfoEnabled() {
            return false;
        }

        public boolean isTraceEnabled() {
            return false;
        }

        public boolean isWarnEnabled() {
            return false;
        }

        public void trace(Object o) {
        }

        public void trace(Object o, Throwable thrwbl) {
        }

        public void debug(Object o) {
        }

        public void debug(Object o, Throwable thrwbl) {
        }

        public void info(Object o) {
        }

        public void info(Object o, Throwable thrwbl) {
        }

        public void warn(Object o) {
        }

        public void warn(Object o, Throwable thrwbl) {
        }

        public void error(Object o) {
        }

        public void error(Object o, Throwable thrwbl) {
        }

        public void fatal(Object o) {
        }

        public void fatal(Object o, Throwable thrwbl) {
        }
        
    };
    public static Log getLog(Class type){
        return log;
    }
    
}

static public class MethodUtils {

    // --------------------------------------------------------- Private Methods
    
    /** 
     * Only log warning about accessibility work around once.
     * <p>
     * Note that this is broken when this class is deployed via a shared
     * classloader in a container, as the warning message will be emitted
     * only once, not once per webapp. However making the warning appear
     * once per webapp means having a map keyed by context classloader
     * which introduces nasty memory-leak problems. As this warning is
     * really optional we can ignore this problem; only one of the webapps
     * will get the warning in its logs but that should be good enough.
     */
    private static boolean loggedAccessibleWarning = false;
    
    /** 
     * Indicates whether methods should be cached for improved performance.
     * <p>
     * Note that when this class is deployed via a shared classloader in
     * a container, this will affect all webapps. However making this
     * configurable per webapp would mean having a map keyed by context classloader
     * which may introduce memory-leak problems.
     */
    private static boolean CACHE_METHODS = true;

    /** An empty class array */
    private static final Class[] EMPTY_CLASS_PARAMETERS = new Class[0];
    /** An empty object array */
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    /**
     * Stores a cache of MethodDescriptor -> Method in a WeakHashMap.
     * <p>
     * The keys into this map only ever exist as temporary variables within
     * methods of this class, and are never exposed to users of this class.
     * This means that the WeakHashMap is used only as a mechanism for 
     * limiting the size of the cache, ie a way to tell the garbage collector
     * that the contents of the cache can be completely garbage-collected 
     * whenever it needs the memory. Whether this is a good approach to
     * this problem is doubtful; something like the commons-collections
     * LRUMap may be more appropriate (though of course selecting an
     * appropriate size is an issue).
     * <p>
     * This static variable is safe even when this code is deployed via a
     * shared classloader because it is keyed via a MethodDescriptor object
     * which has a Class as one of its members and that member is used in
     * the MethodDescriptor.equals method. So two components that load the same
     * class via different classloaders will generate non-equal MethodDescriptor
     * objects and hence end up with different entries in the map.
     */
    private static final Map cache = Collections.synchronizedMap(new WeakHashMap());
    
    // --------------------------------------------------------- Public Methods

    /**
     * Set whether methods should be cached for greater performance or not,
     * default is <code>true</code>.
     *
     * @param cacheMethods <code>true</code> if methods should be
     * cached for greater performance, otherwise <code>false</code>
     * @since 1.8.0
     */
    public static synchronized void setCacheMethods(boolean cacheMethods) {
        CACHE_METHODS = cacheMethods;
        if (!CACHE_METHODS) {
            clearCache();
        }
    }

    /**
     * Clear the method cache.
     * @return the number of cached methods cleared
     * @since 1.8.0
     */
    public static synchronized int clearCache() {
        int size = cache.size();
        cache.clear();
        return size;
    }
    
    /**
     * <p>Invoke a named method whose parameter type matches the object type.</p>
     *
     * <p>The behaviour of this method is less deterministic 
     * than <code>invokeExactMethod()</code>.
     * It loops through all methods with names that match
     * and then executes the first it finds with compatable parameters.</p>
     *
     * <p>This method supports calls to methods taking primitive parameters 
     * via passing in wrapping classes. So, for example, a <code>Boolean</code> class
     * would match a <code>boolean</code> primitive.</p>
     *
     * <p> This is a convenient wrapper for
     * {@link #invokeMethod(Object object,String methodName,Object [] args)}.
     * </p>
     *
     * @param object invoke method on this object
     * @param methodName get method with this name
     * @param arg use this argument
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     */
    public static Object invokeMethod(
            Object object,
            String methodName,
            Object arg)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {

        Object[] args = {arg};
        return invokeMethod(object, methodName, args);

    }


    /**
     * <p>Invoke a named method whose parameter type matches the object type.</p>
     *
     * <p>The behaviour of this method is less deterministic 
     * than {@link #invokeExactMethod(Object object,String methodName,Object [] args)}. 
     * It loops through all methods with names that match
     * and then executes the first it finds with compatable parameters.</p>
     *
     * <p>This method supports calls to methods taking primitive parameters 
     * via passing in wrapping classes. So, for example, a <code>Boolean</code> class
     * would match a <code>boolean</code> primitive.</p>
     *
     * <p> This is a convenient wrapper for
     * {@link #invokeMethod(Object object,String methodName,Object [] args,Class[] parameterTypes)}.
     * </p>
     *
     * @param object invoke method on this object
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     */
    public static Object invokeMethod(
            Object object,
            String methodName,
            Object[] args)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }else{
            boolean nullargs=true;
            for (int i = 0; i < args.length; i++) {
                if(args[i]!=null){
                    nullargs=false;
                    break;
                }
            }
            if(nullargs){
                return invokeFirstMethod(object, methodName, args);
            }
        }
        int arguments = args.length;
        Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; i++) {
                parameterTypes[i] = args[i].getClass();
        }
        return invokeMethod(object, methodName, args, parameterTypes);

    }
    
    static private Method getFirstMethod(Object o,String methodName){
        Method[] methods = o.getClass().getMethods();
        for(int i=0;i<methods.length;i++){
            if(methodName.equals(methods[i].getName())){
                return methods[i];
            }
        }
        return null;
    }
    
    static Object invokeFirstMethod(Object o, String methodName, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
       Method m= getFirstMethod(o,methodName);
       if(m!=null) return m.invoke(o, args);
       else throw new NoSuchMethodException("Invalid method "+methodName+" on "+o);
    }


    /**
     * <p>Invoke a named method whose parameter type matches the object type.</p>
     *
     * <p>The behaviour of this method is less deterministic 
     * than {@link 
     * #invokeExactMethod(Object object,String methodName,Object [] args,Class[] parameterTypes)}. 
     * It loops through all methods with names that match
     * and then executes the first it finds with compatable parameters.</p>
     *
     * <p>This method supports calls to methods taking primitive parameters 
     * via passing in wrapping classes. So, for example, a <code>Boolean</code> class
     * would match a <code>boolean</code> primitive.</p>
     *
     *
     * @param object invoke method on this object
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @param parameterTypes match these parameters - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     */
    public static Object invokeMethod(
            Object object,
            String methodName,
            Object[] args,
            Class[] parameterTypes)
                throws
                    NoSuchMethodException,
                    IllegalAccessException,
                    InvocationTargetException {
                    
        if (parameterTypes == null) {
            parameterTypes = EMPTY_CLASS_PARAMETERS;
        }        
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  

        Method method = getMatchingAccessibleMethod(
                object.getClass(),
                methodName,
                parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " +
                    methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, args);
    }


    /**
     * <p>Invoke a method whose parameter type matches exactly the object
     * type.</p>
     *
     * <p> This is a convenient wrapper for
     * {@link #invokeExactMethod(Object object,String methodName,Object [] args)}.
     * </p>
     *
     * @param object invoke method on this object
     * @param methodName get method with this name
     * @param arg use this argument
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     */
    public static Object invokeExactMethod(
            Object object,
            String methodName,
            Object arg)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {

        Object[] args = {arg};
        return invokeExactMethod(object, methodName, args);

    }


    /**
     * <p>Invoke a method whose parameter types match exactly the object
     * types.</p>
     *
     * <p> This uses reflection to invoke the method obtained from a call to
     * <code>getAccessibleMethod()</code>.</p>
     *
     * @param object invoke method on this object
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     */
    public static Object invokeExactMethod(
            Object object,
            String methodName,
            Object[] args)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  
        int arguments = args.length;
        Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeExactMethod(object, methodName, args, parameterTypes);

    }


    /**
     * <p>Invoke a method whose parameter types match exactly the parameter
     * types given.</p>
     *
     * <p>This uses reflection to invoke the method obtained from a call to
     * <code>getAccessibleMethod()</code>.</p>
     *
     * @param object invoke method on this object
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @param parameterTypes match these parameters - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     */
    public static Object invokeExactMethod(
            Object object,
            String methodName,
            Object[] args,
            Class[] parameterTypes)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  
                
        if (parameterTypes == null) {
            parameterTypes = EMPTY_CLASS_PARAMETERS;
        }

        Method method = getAccessibleMethod(
                object.getClass(),
                methodName,
                parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " +
                    methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, args);

    }

    /**
     * <p>Invoke a static method whose parameter types match exactly the parameter
     * types given.</p>
     *
     * <p>This uses reflection to invoke the method obtained from a call to
     * {@link #getAccessibleMethod(Class, String, Class[])}.</p>
     *
     * @param objectClass invoke static method on this class
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @param parameterTypes match these parameters - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     * @since 1.8.0
     */
    public static Object invokeExactStaticMethod(
            Class objectClass,
            String methodName,
            Object[] args,
            Class[] parameterTypes)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  
                
        if (parameterTypes == null) {
            parameterTypes = EMPTY_CLASS_PARAMETERS;
        }

        Method method = getAccessibleMethod(
                objectClass,
                methodName,
                parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " +
                    methodName + "() on class: " + objectClass.getName());
        }
        return method.invoke(null, args);

    }

    /**
     * <p>Invoke a named static method whose parameter type matches the object type.</p>
     *
     * <p>The behaviour of this method is less deterministic 
     * than {@link #invokeExactMethod(Object, String, Object[], Class[])}. 
     * It loops through all methods with names that match
     * and then executes the first it finds with compatable parameters.</p>
     *
     * <p>This method supports calls to methods taking primitive parameters 
     * via passing in wrapping classes. So, for example, a <code>Boolean</code> class
     * would match a <code>boolean</code> primitive.</p>
     *
     * <p> This is a convenient wrapper for
     * {@link #invokeStaticMethod(Class objectClass,String methodName,Object [] args)}.
     * </p>
     *
     * @param objectClass invoke static method on this class
     * @param methodName get method with this name
     * @param arg use this argument
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     * @since 1.8.0
     */
    public static Object invokeStaticMethod(
            Class objectClass,
            String methodName,
            Object arg)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {

        Object[] args = {arg};
        return invokeStaticMethod (objectClass, methodName, args);

    }


    /**
     * <p>Invoke a named static method whose parameter type matches the object type.</p>
     *
     * <p>The behaviour of this method is less deterministic 
     * than {@link #invokeExactMethod(Object object,String methodName,Object [] args)}. 
     * It loops through all methods with names that match
     * and then executes the first it finds with compatable parameters.</p>
     *
     * <p>This method supports calls to methods taking primitive parameters 
     * via passing in wrapping classes. So, for example, a <code>Boolean</code> class
     * would match a <code>boolean</code> primitive.</p>
     *
     * <p> This is a convenient wrapper for
     * {@link #invokeStaticMethod(Class objectClass,String methodName,Object [] args,Class[] parameterTypes)}.
     * </p>
     *
     * @param objectClass invoke static method on this class
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     * @since 1.8.0
     */
    public static Object invokeStaticMethod(
            Class objectClass,
            String methodName,
            Object[] args)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  
        int arguments = args.length;
        Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeStaticMethod (objectClass, methodName, args, parameterTypes);

    }


    /**
     * <p>Invoke a named static method whose parameter type matches the object type.</p>
     *
     * <p>The behaviour of this method is less deterministic 
     * than {@link 
     * #invokeExactStaticMethod(Class objectClass,String methodName,Object [] args,Class[] parameterTypes)}. 
     * It loops through all methods with names that match
     * and then executes the first it finds with compatable parameters.</p>
     *
     * <p>This method supports calls to methods taking primitive parameters 
     * via passing in wrapping classes. So, for example, a <code>Boolean</code> class
     * would match a <code>boolean</code> primitive.</p>
     *
     *
     * @param objectClass invoke static method on this class
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @param parameterTypes match these parameters - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     * @since 1.8.0
     */
    public static Object invokeStaticMethod(
            Class objectClass,
            String methodName,
            Object[] args,
            Class[] parameterTypes)
                throws
                    NoSuchMethodException,
                    IllegalAccessException,
                    InvocationTargetException {
                    
        if (parameterTypes == null) {
            parameterTypes = EMPTY_CLASS_PARAMETERS;
        }        
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  

        Method method = getMatchingAccessibleMethod(
                objectClass,
                methodName,
                parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " +
                    methodName + "() on class: " + objectClass.getName());
        }
        return method.invoke(null, args);
    }


    /**
     * <p>Invoke a static method whose parameter type matches exactly the object
     * type.</p>
     *
     * <p> This is a convenient wrapper for
     * {@link #invokeExactStaticMethod(Class objectClass,String methodName,Object [] args)}.
     * </p>
     *
     * @param objectClass invoke static method on this class
     * @param methodName get method with this name
     * @param arg use this argument
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     * @since 1.8.0
     */
    public static Object invokeExactStaticMethod(
            Class objectClass,
            String methodName,
            Object arg)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {

        Object[] args = {arg};
        return invokeExactStaticMethod (objectClass, methodName, args);

    }


    /**
     * <p>Invoke a static method whose parameter types match exactly the object
     * types.</p>
     *
     * <p> This uses reflection to invoke the method obtained from a call to
     * {@link #getAccessibleMethod(Class, String, Class[])}.</p>
     *
     * @param objectClass invoke static method on this class
     * @param methodName get method with this name
     * @param args use these arguments - treat null as empty array
     * @return The value returned by the invoked method
     *
     * @throws NoSuchMethodException if there is no such accessible method
     * @throws InvocationTargetException wraps an exception thrown by the
     *  method invoked
     * @throws IllegalAccessException if the requested method is not accessible
     *  via reflection
     * @since 1.8.0
     */
    public static Object invokeExactStaticMethod(
            Class objectClass,
            String methodName,
            Object[] args)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        if (args == null) {
            args = EMPTY_OBJECT_ARRAY;
        }  
        int arguments = args.length;
        Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeExactStaticMethod(objectClass, methodName, args, parameterTypes);

    }


    /**
     * <p>Return an accessible method (that is, one that can be invoked via
     * reflection) with given name and a single parameter.  If no such method
     * can be found, return <code>null</code>.
     * Basically, a convenience wrapper that constructs a <code>Class</code>
     * array for you.</p>
     *
     * @param clazz get method from this class
     * @param methodName get method with this name
     * @param parameterType taking this type of parameter
     * @return The accessible method
     */
    public static Method getAccessibleMethod(
            Class clazz,
            String methodName,
            Class parameterType) {

        Class[] parameterTypes = {parameterType};
        return getAccessibleMethod(clazz, methodName, parameterTypes);

    }


    /**
     * <p>Return an accessible method (that is, one that can be invoked via
     * reflection) with given name and parameters.  If no such method
     * can be found, return <code>null</code>.
     * This is just a convenient wrapper for
     * {@link #getAccessibleMethod(Method method)}.</p>
     *
     * @param clazz get method from this class
     * @param methodName get method with this name
     * @param parameterTypes with these parameters types
     * @return The accessible method
     */
    public static Method getAccessibleMethod(
            Class clazz,
            String methodName,
            Class[] parameterTypes) {

        try {
            MethodDescriptor md = new MethodDescriptor(clazz, methodName, parameterTypes, true);
            // Check the cache first
            Method method = getCachedMethod(md);
            if (method != null) {
                return method;
            }
            
            method =  getAccessibleMethod
                    (clazz, clazz.getMethod(methodName, parameterTypes));
            cacheMethod(md, method);
            return method;
        } catch (NoSuchMethodException e) {
            return (null);
        }

    }


    /**
     * <p>Return an accessible method (that is, one that can be invoked via
     * reflection) that implements the specified Method.  If no such method
     * can be found, return <code>null</code>.</p>
     *
     * @param method The method that we wish to call
     * @return The accessible method
     */
    public static Method getAccessibleMethod(Method method) {

        // Make sure we have a method to check
        if (method == null) {
            return (null);
        }

        return getAccessibleMethod(method.getDeclaringClass(), method);

    }



    /**
     * <p>Return an accessible method (that is, one that can be invoked via
     * reflection) that implements the specified Method.  If no such method
     * can be found, return <code>null</code>.</p>
     *
     * @param clazz The class of the object
     * @param method The method that we wish to call
     * @return The accessible method
     * @since 1.8.0
     */
    public static Method getAccessibleMethod(Class clazz, Method method) {

        // Make sure we have a method to check
        if (method == null) {
            return (null);
        }

        // If the requested method is not public we cannot call it
        if (!Modifier.isPublic(method.getModifiers())) {
            return (null);
        }

        boolean sameClass = true;
        if (clazz == null) {
            clazz = method.getDeclaringClass();
        } else {
            sameClass = clazz.equals(method.getDeclaringClass());
            if (!method.getDeclaringClass().isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(clazz.getName() +
                        " is not assignable from " + method.getDeclaringClass().getName());
            }
        }

        // If the class is public, we are done
        if (Modifier.isPublic(clazz.getModifiers())) {
            if (!sameClass && !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
                setMethodAccessible(method); // Default access superclass workaround
            }
            return (method);
        }

        String methodName      = method.getName();
        Class[] parameterTypes = method.getParameterTypes();

        // Check the implemented interfaces and subinterfaces
        method =
                getAccessibleMethodFromInterfaceNest(clazz,
                        methodName,
                        parameterTypes);

        // Check the superclass chain
        if (method == null) {
            method = getAccessibleMethodFromSuperclass(clazz,
                        methodName,
                        parameterTypes);
        }

        return (method);

    }


    // -------------------------------------------------------- Private Methods

    /**
     * <p>Return an accessible method (that is, one that can be invoked via
     * reflection) by scanning through the superclasses. If no such method
     * can be found, return <code>null</code>.</p>
     *
     * @param clazz Class to be checked
     * @param methodName Method name of the method we wish to call
     * @param parameterTypes The parameter type signatures
     */
    private static Method getAccessibleMethodFromSuperclass
            (Class clazz, String methodName, Class[] parameterTypes) {

        Class parentClazz = clazz.getSuperclass();
        while (parentClazz != null) {
            if (Modifier.isPublic(parentClazz.getModifiers())) {
                try {
                    return parentClazz.getMethod(methodName, parameterTypes);
                } catch (NoSuchMethodException e) {
                    return null;
                }
            }
            parentClazz = parentClazz.getSuperclass();
        }
        return null;
    }

    /**
     * <p>Return an accessible method (that is, one that can be invoked via
     * reflection) that implements the specified method, by scanning through
     * all implemented interfaces and subinterfaces.  If no such method
     * can be found, return <code>null</code>.</p>
     *
     * <p> There isn't any good reason why this method must be private.
     * It is because there doesn't seem any reason why other classes should
     * call this rather than the higher level methods.</p>
     *
     * @param clazz Parent class for the interfaces to be checked
     * @param methodName Method name of the method we wish to call
     * @param parameterTypes The parameter type signatures
     */
    private static Method getAccessibleMethodFromInterfaceNest
            (Class clazz, String methodName, Class[] parameterTypes) {

        Method method = null;

        // Search up the superclass chain
        for (; clazz != null; clazz = clazz.getSuperclass()) {

            // Check the implemented interfaces of the parent class
            Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {

                // Is this interface public?
                if (!Modifier.isPublic(interfaces[i].getModifiers())) {
                    continue;
                }

                // Does the method exist on this interface?
                try {
                    method = interfaces[i].getDeclaredMethod(methodName,
                            parameterTypes);
                } catch (NoSuchMethodException e) {
                    /* Swallow, if no method is found after the loop then this
                     * method returns null.
                     */
                }
                if (method != null) {
                    return method;
                }

                // Recursively check our parent interfaces
                method =
                        getAccessibleMethodFromInterfaceNest(interfaces[i],
                                methodName,
                                parameterTypes);
                if (method != null) {
                    return method;
                }

            }

        }

        // We did not find anything
        return (null);

    }

    /**
     * <p>Find an accessible method that matches the given name and has compatible parameters.
     * Compatible parameters mean that every method parameter is assignable from 
     * the given parameters.
     * In other words, it finds a method with the given name 
     * that will take the parameters given.<p>
     *
     * <p>This method is slightly undeterminstic since it loops 
     * through methods names and return the first matching method.</p>
     * 
     * <p>This method is used by 
     * {@link 
     * #invokeMethod(Object object,String methodName,Object [] args,Class[] parameterTypes)}.
     *
     * <p>This method can match primitive parameter by passing in wrapper classes.
     * For example, a <code>Boolean</code> will match a primitive <code>boolean</code>
     * parameter.
     *
     * @param clazz find method in this class
     * @param methodName find method with this name
     * @param parameterTypes find method with compatible parameters 
     * @return The accessible method
     */
    public static Method getMatchingAccessibleMethod(
                                                Class clazz,
                                                String methodName,
                                                Class[] parameterTypes) {
        // trace logging
        Log log = LogFactory.getLog(MethodUtils.class);
        if (log.isTraceEnabled()) {
            log.trace("Matching name=" + methodName + " on " + clazz);
        }
        MethodDescriptor md = new MethodDescriptor(clazz, methodName, parameterTypes, false);
        
        // see if we can find the method directly
        // most of the time this works and it's much faster
        try {
            // Check the cache first
            Method method = getCachedMethod(md);
            if (method != null) {
                return method;
            }

            method = clazz.getMethod(methodName, parameterTypes);
            if (log.isTraceEnabled()) {
                log.trace("Found straight match: " + method);
                log.trace("isPublic:" + Modifier.isPublic(method.getModifiers()));
            }
            
            setMethodAccessible(method); // Default access superclass workaround

            cacheMethod(md, method);
            return method;
            
        } catch (NoSuchMethodException e) { /* SWALLOW */ }
        
        // search through all methods 
        int paramSize = parameterTypes.length;
        Method bestMatch = null;
        Method[] methods = clazz.getMethods();
        float bestMatchCost = Float.MAX_VALUE;
        float myCost = Float.MAX_VALUE;
        for (int i = 0, size = methods.length; i < size ; i++) {
            if (methods[i].getName().equals(methodName)) {
                // log some trace information
                if (log.isTraceEnabled()) {
                    log.trace("Found matching name:");
                    log.trace(methods[i]);
                }                
                
                // compare parameters
                Class[] methodsParams = methods[i].getParameterTypes();
                int methodParamSize = methodsParams.length;
                if (methodParamSize == paramSize) {          
                    boolean match = true;
                    for (int n = 0 ; n < methodParamSize; n++) {
                        if (log.isTraceEnabled()) {
                            log.trace("Param=" + parameterTypes[n].getName());
                            log.trace("Method=" + methodsParams[n].getName());
                        }
                        if (!isAssignmentCompatible(methodsParams[n], parameterTypes[n])) {
                            if (log.isTraceEnabled()) {
                                log.trace(methodsParams[n] + " is not assignable from " 
                                            + parameterTypes[n]);
                            }    
                            match = false;
                            break;
                        }
                    }
                    
                    if (match) {
                        // get accessible version of method
                        Method method = getAccessibleMethod(clazz, methods[i]);
                        if (method != null) {
                            if (log.isTraceEnabled()) {
                                log.trace(method + " accessible version of " 
                                            + methods[i]);
                            }
                            setMethodAccessible(method); // Default access superclass workaround
                            myCost = getTotalTransformationCost(parameterTypes,method.getParameterTypes());
                            if ( myCost < bestMatchCost ) {
                               bestMatch = method;
                               bestMatchCost = myCost;
                            }
                        }
                        
                        log.trace("Couldn't find accessible method.");
                    }
                }
            }
        }
        if ( bestMatch != null ){
                 cacheMethod(md, bestMatch);
        } else {
        // didn't find a match
               log.trace("No match found.");
        }
        
        return bestMatch;                                        
    }

    /**
     * Try to make the method accessible
     * @param method The source arguments
     */
    private static void setMethodAccessible(Method method) {
        try {
            //
            // XXX Default access superclass workaround
            //
            // When a public class has a default access superclass
            // with public methods, these methods are accessible.
            // Calling them from compiled code works fine.
            //
            // Unfortunately, using reflection to invoke these methods
            // seems to (wrongly) to prevent access even when the method
            // modifer is public.
            //
            // The following workaround solves the problem but will only
            // work from sufficiently privilages code. 
            //
            // Better workarounds would be greatfully accepted.
            //
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            
        } catch (SecurityException se) {
            // log but continue just in case the method.invoke works anyway
            Log log = LogFactory.getLog(MethodUtils.class);
            if (!loggedAccessibleWarning) {
                boolean vulnerableJVM = false;
                try {
                    String specVersion = System.getProperty("java.specification.version");
                    if (specVersion.charAt(0) == '1' && 
                            (specVersion.charAt(2) == '0' ||
                             specVersion.charAt(2) == '1' ||
                             specVersion.charAt(2) == '2' ||
                             specVersion.charAt(2) == '3')) {
                             
                        vulnerableJVM = true;
                    }
                } catch (SecurityException e) {
                    // don't know - so display warning
                    vulnerableJVM = true;
                }
                if (vulnerableJVM) {
                    log.warn(
                        "Current Security Manager restricts use of workarounds for reflection bugs "
                        + " in pre-1.4 JVMs.");
                }
                loggedAccessibleWarning = true;
            }
            log.debug("Cannot setAccessible on method. Therefore cannot use jvm access bug workaround.", se);
        }
    }

    /**
     * Returns the sum of the object transformation cost for each class in the source
     * argument list.
     * @param srcArgs The source arguments
     * @param destArgs The destination arguments
     * @return The total transformation cost
     */
    private static float getTotalTransformationCost(Class[] srcArgs, Class[] destArgs) {

        float totalCost = 0.0f;
        for (int i = 0; i < srcArgs.length; i++) {
            Class srcClass, destClass;
            srcClass = srcArgs[i];
            destClass = destArgs[i];
            totalCost += getObjectTransformationCost(srcClass, destClass);
        }

        return totalCost;
    }
    
    /**
     * Gets the number of steps required needed to turn the source class into the 
     * destination class. This represents the number of steps in the object hierarchy 
     * graph.
     * @param srcClass The source class
     * @param destClass The destination class
     * @return The cost of transforming an object
     */
    private static float getObjectTransformationCost(Class srcClass, Class destClass) {
        float cost = 0.0f;
        while (destClass != null && !destClass.equals(srcClass)) {
            if (destClass.isInterface() && isAssignmentCompatible(destClass,srcClass)) {
                // slight penalty for interface match. 
                // we still want an exact match to override an interface match, but  
                // an interface match should override anything where we have to get a 
                // superclass.
                cost += 0.25f;
                break;
            }
            cost++;
            destClass = destClass.getSuperclass();
        }

        /*
         * If the destination class is null, we've travelled all the way up to 
         * an Object match. We'll penalize this by adding 1.5 to the cost.
         */
        if (destClass == null) {
            cost += 1.5f;
        }

        return cost;
    }
    
    
    /**
     * <p>Determine whether a type can be used as a parameter in a method invocation.
     * This method handles primitive conversions correctly.</p>
     *
     * <p>In order words, it will match a <code>Boolean</code> to a <code>boolean</code>,
     * a <code>Long</code> to a <code>long</code>,
     * a <code>Float</code> to a <code>float</code>,
     * a <code>Integer</code> to a <code>int</code>,
     * and a <code>Double</code> to a <code>double</code>.
     * Now logic widening matches are allowed.
     * For example, a <code>Long</code> will not match a <code>int</code>.
     *
     * @param parameterType the type of parameter accepted by the method
     * @param parameterization the type of parameter being tested 
     *
     * @return true if the assignement is compatible.
     */
    public static final boolean isAssignmentCompatible(Class parameterType, Class parameterization) {
        // try plain assignment
        if (parameterType.isAssignableFrom(parameterization)) {
            return true;
        }
        
        if (parameterType.isPrimitive()) {
            // this method does *not* do widening - you must specify exactly
            // is this the right behaviour?
            Class parameterWrapperClazz = getPrimitiveWrapper(parameterType);
            if (parameterWrapperClazz != null) {
                return parameterWrapperClazz.equals(parameterization);
            }
        }
        
        return false;
    }
    
    /**
     * Gets the wrapper object class for the given primitive type class.
     * For example, passing <code>boolean.class</code> returns <code>Boolean.class</code>
     * @param primitiveType the primitive type class for which a match is to be found
     * @return the wrapper type associated with the given primitive 
     * or null if no match is found
     */
    public static Class getPrimitiveWrapper(Class primitiveType) {
        // does anyone know a better strategy than comparing names?
        if (boolean.class.equals(primitiveType)) {
            return Boolean.class;
        } else if (float.class.equals(primitiveType)) {
            return Float.class;
        } else if (long.class.equals(primitiveType)) {
            return Long.class;
        } else if (int.class.equals(primitiveType)) {
            return Integer.class;
        } else if (short.class.equals(primitiveType)) {
            return Short.class;
        } else if (byte.class.equals(primitiveType)) {
            return Byte.class;
        } else if (double.class.equals(primitiveType)) {
            return Double.class;
        } else if (char.class.equals(primitiveType)) {
            return Character.class;
        } else {
            
            return null;
        }
    }

    /**
     * Gets the class for the primitive type corresponding to the primitive wrapper class given.
     * For example, an instance of <code>Boolean.class</code> returns a <code>boolean.class</code>. 
     * @param wrapperType the 
     * @return the primitive type class corresponding to the given wrapper class,
     * null if no match is found
     */
    public static Class getPrimitiveType(Class wrapperType) {
        // does anyone know a better strategy than comparing names?
        if (Boolean.class.equals(wrapperType)) {
            return boolean.class;
        } else if (Float.class.equals(wrapperType)) {
            return float.class;
        } else if (Long.class.equals(wrapperType)) {
            return long.class;
        } else if (Integer.class.equals(wrapperType)) {
            return int.class;
        } else if (Short.class.equals(wrapperType)) {
            return short.class;
        } else if (Byte.class.equals(wrapperType)) {
            return byte.class;
        } else if (Double.class.equals(wrapperType)) {
            return double.class;
        } else if (Character.class.equals(wrapperType)) {
            return char.class;
        } else {
            Log log = LogFactory.getLog(MethodUtils.class);
            if (log.isDebugEnabled()) {
                log.debug("Not a known primitive wrapper class: " + wrapperType);
            }
            return null;
        }
    }
    
    /**
     * Find a non primitive representation for given primitive class.
     *
     * @param clazz the class to find a representation for, not null
     * @return the original class if it not a primitive. Otherwise the wrapper class. Not null
     */
    public static Class toNonPrimitiveClass(Class clazz) {
        if (clazz.isPrimitive()) {
            Class primitiveClazz = MethodUtils.getPrimitiveWrapper(clazz);
            // the above method returns 
            if (primitiveClazz != null) {
                return primitiveClazz;
            } else {
                return clazz;
            }
        } else {
            return clazz;
        }
    }
    

    /**
     * Return the method from the cache, if present.
     *
     * @param md The method descriptor
     * @return The cached method
     */
    private static Method getCachedMethod(MethodDescriptor md) {
        if (CACHE_METHODS) {
            Reference methodRef = (Reference)cache.get(md);
            if (methodRef != null) {
                return (Method)methodRef.get();
            }
        }
        return null;
    }

    /**
     * Add a method to the cache.
     *
     * @param md The method descriptor
     * @param method The method to cache
     */
    private static void cacheMethod(MethodDescriptor md, Method method) {
        if (CACHE_METHODS) {
            if (method != null) {
                cache.put(md, new WeakReference(method));
            }
        }
    }

    /**
     * Represents the key to looking up a Method by reflection.
     */
    private static class MethodDescriptor {
        private Class cls;
        private String methodName;
        private Class[] paramTypes;
        private boolean exact;
        private int hashCode;

        /**
         * The sole constructor.
         *
         * @param cls  the class to reflect, must not be null
         * @param methodName  the method name to obtain
         * @param paramTypes the array of classes representing the paramater types
         * @param exact whether the match has to be exact.
         */
        public MethodDescriptor(Class cls, String methodName, Class[] paramTypes, boolean exact) {
            if (cls == null) {
                throw new IllegalArgumentException("Class cannot be null");
            }
            if (methodName == null) {
                throw new IllegalArgumentException("Method Name cannot be null");
            }
            if (paramTypes == null) {
                paramTypes = EMPTY_CLASS_PARAMETERS;
            }

            this.cls = cls;
            this.methodName = methodName;
            this.paramTypes = paramTypes;
            this.exact= exact;

            this.hashCode = methodName.length();
        }
        /**
         * Checks for equality.
         * @param obj object to be tested for equality
         * @return true, if the object describes the same Method.
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MethodDescriptor)) {
                return false;
            }
            MethodDescriptor md = (MethodDescriptor)obj;

            return (
                exact == md.exact &&
                methodName.equals(md.methodName) &&
                cls.equals(md.cls) &&
                java.util.Arrays.equals(paramTypes, md.paramTypes)
            );
        }
        /**
         * Returns the string length of method name. I.e. if the
         * hashcodes are different, the objects are different. If the
         * hashcodes are the same, need to use the equals method to
         * determine equality.
         * @return the string length of method name.
         */
        public int hashCode() {
            return hashCode;
        }
    }
    }

}
