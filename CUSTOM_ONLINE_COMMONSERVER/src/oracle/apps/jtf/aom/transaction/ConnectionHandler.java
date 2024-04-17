package oracle.apps.jtf.aom.transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConnectionHandler implements InvocationHandler {

    static Pattern SP_CALL_PATTERN = Pattern.compile("(?:\\\\n|\\s)*(?:\\{call|begin)(?:\\\\n|\\s)*(?:.*?\\.)?(?:\\\\\"|\")?(.*?)(?:\\\\\"|\")?\\.(?:\\\\\"|\")?(.*?)(?:\\\\\"|\")?\\(.*?\\)(?:\\\\n|\\s|;)*(?:\\}|end)(?:\\\\n|\\s|;)*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    static Map PROCEDURE_NAME_2_FILE_NAME = new HashMap();

    static void init() {
        loadFileSystemSqls();
        System.out.println(PROCEDURE_NAME_2_FILE_NAME);
    }

    private final Connection connection;

    public ConnectionHandler(Connection c) {
        connection = c;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            final String methodName = method.getName();
            System.out.println(connection.getClass().getSimpleName() + " " + methodName + " " + TransactionUtil.getArgs(args, 2));
            if ("unwrap" == methodName) {
                return unwrap();
            }else if ("prepareCall" == methodName && args.length == 1 && args[0] instanceof String) {
                return prepareCall((String) args[0]);
            }else if ("close" == methodName) {
            	TransactionUtil.printDbmsOutput(connection);
            }else if ("close" != methodName) {
                return method.invoke(connection, args);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Object unwrap() {
        return connection;
    }

    private Object prepareCall(String sql) {
        Matcher m = SP_CALL_PATTERN.matcher(sql);
        if (m.matches() && m.groupCount() == 2) {
            //String oraclePackageName = m.group(1).toUpperCase();
            String oracleProcedureName = m.group(2).toUpperCase();
            OracleProcedure oraproc = null;
            try {
                oraproc = getOracleProcedure(oracleProcedureName);
                if (oraproc != null) {
                    System.out.println("found local plsql procedure " + oraproc);
                    sql = sql.indexOf('?') > 0 && oraproc.bindVariables != null && oraproc.bindVariables.size() > 0
                            ? oraproc.convertBindVariables()
                            : oraproc.body;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        try {
            Statement statement = connection.prepareCall(sql);
            return Proxy.newProxyInstance(ConnectionHandler.class.getClassLoader(),
                    new Class[]{oracle.jdbc.OracleCallableStatement.class},
                    new StatementHandler(statement));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static final String BIND_VARIABLE_DEF = "-- bind variables =";

    public static OracleProcedure getOracleProcedure(String oracleProcedureName) throws Exception {
        String path = (String) PROCEDURE_NAME_2_FILE_NAME.get(oracleProcedureName);
        if (path == null) {
            return null;
        }
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        OracleProcedure oraproc = new OracleProcedure();
        oraproc.body = "";
        oraproc.path = path;
        oraproc.name = oracleProcedureName;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(BIND_VARIABLE_DEF)) {
                oraproc.bindVariables = Arrays.asList(line.substring(BIND_VARIABLE_DEF.length()).trim().split("\\s+"));
            } else {
                oraproc.body = oraproc.body + line + "\n";
            }
        }
        br.close();
        return oraproc;
    }

    final static String PL_SQL_PATH = "C:\\Temp\\NetBeansProjects\\MY_TEST\\plsql\\";

    private static void loadFileSystemSqls() {
        File folder = new File(PL_SQL_PATH);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();
//                System.out.println("Found File " + fileName);
                PROCEDURE_NAME_2_FILE_NAME.put(removeExtension(fileName).toUpperCase(), listOfFiles[i].getAbsolutePath());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Found Directory " + listOfFiles[i].getName());
            }
        }
    }

    private static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }

    static class OracleProcedure {

        String name;
        List bindVariables;
        String body;
        String path;

        /**
         * convert bind variable name based to index based
         */
        String convertBindVariables() {
            String body1 = body;
            for (int i = 0; bindVariables != null && i < bindVariables.size(); i++) {
                String var = (String) bindVariables.get(i);
                body1 = body1.replaceAll(var, "?");
            }
            return body1;
        }

        public String toString() {
            return "OracleProcedure{" + "name=" + name + ", bindVariables=" + bindVariables + '}';
        }

    }

    public static void main(String[] args) {
        init();
        try {
            OracleProcedure op = getOracleProcedure("GET_CNTR_READ_DETAILS");
            System.out.println(op);
//            System.out.println(op.convertBindVariables());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
