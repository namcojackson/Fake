package oracle.apps.jtf.aom.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Statement;


public class StatementHandler implements InvocationHandler {

    final private Statement statement;

    public StatementHandler(Statement ps) {
        this.statement = ps;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            final String methodName = method.getName();
            Object obj = method.invoke(statement, args);
            String msg = statement.getClass().getSimpleName() + " " + methodName + " " + TransactionUtil.getArgs(args, 2);
            if (methodName.startsWith("get")) {
                msg += " = " + obj;
            }
            System.out.println(msg);
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
