package oracle.apps.jtf.aom.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.apps.jtf.base.resources.FrameworkException;

public abstract class TransactionScope {

	static Connection conn;

	static {
		try {
			conn = parts.dbcommon.EZDConnectionMgr.getInstance().getConnection(1);
			System.out.println("conn is " + conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		final Thread mainThread = Thread.currentThread();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (conn != null) {
						conn.close();
					}
					System.out.println("in shutdown hook closed connections."
							+ mainThread.isAlive());

					mainThread.join(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	public static Connection nativeConnection(java.sql.Connection conn) {
		return conn;
	}

	public static Connection getConnection() throws FrameworkException {
		return conn;
	}
	
	public static Connection getNewConnection() throws TxnException,
			FrameworkException {
		return getConnection();
	}

	public static void releaseConnection(java.sql.Connection conn) {
		 if (conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}