package oracle.apps.e633.server;

/***********************************************************************
 *  FileName : CanonE633ExtractConcProg.java
 *	
 *	Modification History :
 *	Date		ITG Ticket #	Author			Description
 *	---------------------------------------------------------------------
 *	
 *
 ************************************************************************/

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonE633ConvProg {

	public static final String CONV_DB = "{call CANON_E633_LFSPPS_CONV_PKG.extract_prc(?,?,?)}";

	public static void main(String args[]) throws Exception {

		String param = "";
		String inputParam1 = System.getenv("VAR_USER1");

		try {
			if (inputParam1 == null || inputParam1.trim().length() == 0) {
				inputParam1 = "No Value Provided";
				System.out
						.println("Please provide value for Conversion. valid values are Customers, Install Base , Configurations, Machine Contact, Meter Reads, pen Service Calls , Closed Service CallS, AR Aging , Order Headers, Order Details, Order Comments ");
				throw new Exception("Please provide value for Conversion");
			} else {

				executeCONVPKG(CONV_DB, inputParam1);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(99);
			throw ex;
		}
	}

	public static void executeCONVPKG(String dbCallStr, String convmodel)
			throws Exception {
		System.out.println("in executeSQLPKG ");
		System.out.println("Extract for : " + convmodel);
		System.out.println("DB Call : " + dbCallStr);
		CallableStatement statement = null;
		Connection connection = null;
		try {
			connection = TransactionScope.getConnection();
			System.out.println("connection:" + connection);
			if (connection != null) {
				statement = (CallableStatement) connection
						.prepareCall(dbCallStr);
				if (statement != null) {
					statement.registerOutParameter(1, OracleTypes.VARCHAR);
					statement.registerOutParameter(2, OracleTypes.VARCHAR);
					if (convmodel != null)
						statement.setObject(3, convmodel);

					statement.execute();
					String statusCode = statement.getString(2);
					System.out.println("statusCode: " + statusCode);
					if (statusCode != null
							&& !statusCode.isEmpty()
							&& ("E".equalsIgnoreCase(statusCode) || "2"
									.equalsIgnoreCase(statusCode))) {
						System.err.println("CONV PKG error: statusCode :"
								+ statusCode + " message:"
								+ statement.getString(1));
						throw new Exception(statement.getString(1));
					}
				} else {
					System.err
							.println("executeCONVPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
					throw new Exception(
							"executeCONVPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
				}
			} else {
				System.err
						.println("executeCONVPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
				throw new Exception(
						"executeCONVPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception exp) {
					exp.printStackTrace();
					throw exp;
				}
			}
			if (connection != null) {
				try {
					TransactionScope.releaseConnection(connection);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw ex;
				}
			}

		}
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

	public static Timestamp toTimestamp(String tsStr) {
		try {
			return tsStr == null || tsStr.trim().length() == 0 ? null
					: new Timestamp(sdf.parse(tsStr).getTime());
		} catch (ParseException e) {
			System.out.println(e);
			return null;
		}
	}

}