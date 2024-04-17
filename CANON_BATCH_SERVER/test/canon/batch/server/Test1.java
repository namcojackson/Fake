package canon.batch.server;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;

import parts.dbcommon.EZDConnectionMgr;

public class Test1 {

	private Context context = null;

	private Test1(Context context) {
		this.context = context;

	}

	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}

	public static void test2() throws Exception {
		System.out.println("use ezd api.");
		Connection conn = EZDConnectionMgr.getInstance().getConnection();
		System.out.println(conn);
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println("User Name "+metaData.getUserName());
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT 1 FROM Dual");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO : log the exception ...
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}

	}

	public static void test1() throws Exception {
		System.out.println("use jndi.");

		// Create the environment for constructing the initial JNDI
		// naming context.

		Hashtable<String, String> env = new Hashtable<String, String>();

		// Store the environment attributes that tell JNDI which initial context
		// factory to use and where to find the provider.//

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.fscontext.RefFSContextFactory");
		// env.put(Context.PROVIDER_URL,
		// "file:///C:/Temp/s21test/jndi/jndi/dvlp/");
		env.put(Context.PROVIDER_URL, "file:////home/csawdspoc/jndi/dvlp");

		// Create the initial context.

		Context context = new InitialContext(env);
		// new Test1(context).printJNDITree("jdbc");

		NamingEnumeration bindings = context.listBindings("jdbc");

		while (bindings.hasMore()) {
			Binding bd = (Binding) bindings.next();
			System.out.println(bd.getName() + ": " + bd.getObject());
		}

		// Look up the connection factory object in the JNDI object store.

		DataSource ds = (DataSource) context.lookup("jdbc/S21CSAWDSAP5");
		// S21CSAEXTN5: oracle.jdbc.pool.OracleDataSource@2b275d39

		System.out.println(ds);
		Connection conn = ds.getConnection();
		System.out.println(conn);
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println("User Name "+metaData.getUserName());
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT 1 FROM Dual");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO : log the exception ...
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}

	}

	public void printJNDITree(String ct) {
		try {
			printNE(context.list(ct), ct);
		} catch (NamingException e) {
			// ignore leaf node exception
		}
	}

	private void printNE(NamingEnumeration ne, String parentctx)
			throws NamingException {
		while (ne.hasMoreElements()) {
			NameClassPair next = (NameClassPair) ne.nextElement();
			printEntry(next);
			increaseIndent();
			printJNDITree((parentctx.length() == 0) ? next.getName()
					: parentctx + "/" + next.getName());
			decreaseIndent();
		}
	}

	private void printEntry(NameClassPair next) {
		System.out.println(printIndent() + "-->" + next);
	}

	private int indentLevel = 0;

	private void increaseIndent() {
		indentLevel += 4;
	}

	private void decreaseIndent() {
		indentLevel -= 4;
	}

	private String printIndent() {
		StringBuffer buf = new StringBuffer(indentLevel);
		for (int i = 0; i < indentLevel; i++) {
			buf.append(" ");
		}
		return buf.toString();
	}

}
