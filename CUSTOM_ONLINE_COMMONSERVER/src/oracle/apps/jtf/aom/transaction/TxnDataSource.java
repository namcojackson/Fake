package oracle.apps.jtf.aom.transaction;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import oracle.apps.jtf.base.resources.FrameworkException;


public class TxnDataSource implements DataSource {
	
	private Connection  con_;
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public Object unwrap(Class iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class iface) throws SQLException {
		return false;
	}

	public Connection getConnection() throws SQLException {
		try {
			Connection con=TransactionScope.getConnection();
			TransactionUtil.enableDbmsOutput(con);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void close(){
		System.out.println("in TxnDataSource.close");
		TransactionUtil.printDbmsOutput(con_);
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDataSource(DataSource ds){
		try {
			con_=ds.getConnection();
//			TransactionScope.setConnection(con_);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
