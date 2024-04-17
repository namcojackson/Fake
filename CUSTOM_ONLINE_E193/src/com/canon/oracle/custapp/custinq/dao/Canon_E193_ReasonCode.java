package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_ReasonCodeObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

public class Canon_E193_ReasonCode {

	/**
	 * Canon_E193_ReasonCode constructor.
	 */
	public Canon_E193_ReasonCode() {
		super();
	}

	/**
	 * This method will return the list of history records.
	 * <p>
	 * 
	 * @return java.util.ArrayList.
	 * @param strAcctType
	 *            java.lang.String.
	 * @param strAcctValue
	 *            java.lang.String.
	 * @param strCustAcctId
	 *            int.
	 * @param iOrgId
	 *            int.
	 * @param strOrderBy
	 *            java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getReasonCodes(int strApplId, String strReasonCode,
			boolean bFirstTime) throws CanonCustAppExceptionUtil {
		ArrayList alReasonCodes = new ArrayList();
		// Get Connection
		CanonCustAppDBUtil connReasonConnection = null;
		Connection connReason = null;
		CallableStatement pCall = null;
		ResultSet rsReason = null;
		Canon_E193_ReasonCodeObj objReason = null;
		try {
			ArrayList alReasonCd = null;// new ArrayList();
			ArrayList alReasonDesc = new ArrayList();
			connReasonConnection = new CanonCustAppDBUtil();
			connReason = (Connection) (connReasonConnection.getConnection());
			if (bFirstTime) {
				alReasonCd = new ArrayList();
				pCall = connReason
						.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_reason_codes(?,?)}");
				pCall.setInt(1, strApplId);
				pCall.registerOutParameter(2, OracleTypes.CURSOR);

				pCall.execute();
				rsReason = (ResultSet) pCall.getObject(2);
				while (rsReason.next()) {
					objReason = new Canon_E193_ReasonCodeObj();
					objReason.setReasonCode(rsReason.getString(1));
					objReason.setMeaning(rsReason.getString(2));
					alReasonCd.add(objReason);
					if (strReasonCode == null)
						strReasonCode = objReason.getReasonCode();
				}
			}
			pCall = null;
			rsReason = null;
			pCall = connReason
					.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_reason_desc(?,?)}");
			pCall.setString(1, strReasonCode);
			pCall.registerOutParameter(2, OracleTypes.CURSOR);

			pCall.execute();
			rsReason = (ResultSet) pCall.getObject(2);
			while (rsReason.next()) {
				objReason = new Canon_E193_ReasonCodeObj();
				objReason.setReason(rsReason.getString(1));
				objReason.setDescription(rsReason.getString(2));
				alReasonDesc.add(objReason);
			}
			if (alReasonCd != null && alReasonCd.size() > 0)
				alReasonCodes.add(alReasonCd);
			alReasonCodes.add(alReasonDesc);
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Ticket, Method:getReasonCodes()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Ticket, Method:getReasonCodes()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (rsReason != null)
					rsReason.close();
				if (connReasonConnection != null)
					connReasonConnection.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Ticket, Method:getReasonCodes()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Ticket, Method:getReasonCodes()"));
			}
		}
		return alReasonCodes;
	}

}
