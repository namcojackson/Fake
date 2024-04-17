/*
 * Copyright (c) 2012 Canon USA Inc. All rights reserved. Original
 * Author: Masaaki Yaguchi Company: Fujitsu Limited Date: 2013/09/10
 */

package business.blap.ZZIL0100.common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import parts.common.EZDDebugOutput;
import parts.common.EZDStringUtil;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZIL0100.ZZIL0100SMsg;
import business.db.ITRL_INTFC_TRX_CONFIGTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * S21IntMasterTableAccessor is an API class that provides the access to Master table for Administration screen. 
 * API is offered when required to access Database.
 * </pre>
 * @author Q04877
 *
 */
public class InternalTransactionTableAccessor {

    /** Interface Transaction Table Name */
    private String intTrxTbl = null;

    /** Message Debug Code */
    private static final int D_MES_CODE = 600;

    /** SQL Connection */
    private Connection con;

    /** Prepared Statements */
    // prepared statement
    private static final String FIND_BY_KEY_FOR_UPDATE_STMT = "SELECT EZTABLEID,EZCANCELFLAG,EZINTIME,EZINTIMEZONE,EZINCOMPANYCODE,EZINUSERID,EZINAPLID,EZUPTIME,EZUPTIMEZONE,EZUPCOMPANYCODE,EZUPUSERID,EZUPAPLID,ITRL_INTFC_ID,ITRL_TRX_SQ,ITRL_INTFC_TRX_CONFIG_ID,ITRL_PROC_FLG,ITRL_INTFC_REC_CNT FROM #TRANSACTION_TABLE# WHERE ITRL_INTFC_ID=? AND ITRL_TRX_SQ=? AND ITRL_INTFC_TRX_CONFIG_ID=? AND EZCANCELFLAG='0' FOR UPDATE NOWAIT";
    private static final String UPDATE_PROCESSED_FLAG_STMT = "UPDATE #TRANSACTION_TABLE# SET EZUPTIME=?,EZUPTIMEZONE=?,EZUPCOMPANYCODE=?,EZUPUSERID=?,EZUPAPLID=?,ITRL_PROC_FLG=? WHERE ITRL_INTFC_ID=? AND ITRL_TRX_SQ=? AND ITRL_INTFC_TRX_CONFIG_ID=? AND EZCANCELFLAG='0'";
    private static final String REPLACE_TRAN_TBL_KEY = "#TRANSACTION_TABLE#";
    
    /**
     * Constructor with configId for target system 
     * @param configId This id and record is stored on INT_INTFC_TRANS_CONFIG Table for each target system.
     */
    public InternalTransactionTableAccessor(String configId) {
        if (configId == null)  {
            throw new S21AbendException("ZZIM0012E", new String[] {"configId", "null"});
        }

        // select by INT_TRN_CONFIG_ID
        ITRL_INTFC_TRX_CONFIGTMsg tmsg = new ITRL_INTFC_TRX_CONFIGTMsg();
        tmsg.itrlIntfcTrxConfigId.setValue(configId);
        ITRL_INTFC_TRX_CONFIGTMsg res = (ITRL_INTFC_TRX_CONFIGTMsg)EZDTBLAccessor.findByKey(tmsg);

        if (res == null)  {
            throw new S21AbendException("ZZIM0073E", new String[] {configId});
        }

        this.intTrxTbl = res.itrlIntfcTrxTblId.getValue();

        if (res.itrlIntfcTrxTblId.getValue() == null) {
            String[] msgArg = {"ITRL_INTFC_TRX_TBL_ID",configId};
            throw new S21AbendException("ZZIM0074E",msgArg);
        } else if (res.itrlIntfcMstrTblId.getValue() == null) {
            String[] msgArg = {"ITRL_INTFC_MSTR_TBL_ID",configId};
            throw new S21AbendException("ZZIM0074E",msgArg);
        }
    }

    /**
     * lock Transaction Record 
     * @param sMsg EZDSMsg
     * @param index int
     * @return ezuptime
     */
    public String selectForUpdateItrlProcFlg(ZZIL0100SMsg sMsg, int i) {
        EZDDebugOutput.println(D_MES_CODE, "[[START]]selectForUpdateItrlProcFlg", this);
        
		String itrlIntfcId = sMsg.itrlIntfcId.getValue();
		BigDecimal transactionId = sMsg.A.no(i).transactionId_A.getValue();
		String configId = sMsg.itrlIntfcTrxConfigId_PS.getValue();

        String findKeyStmt = FIND_BY_KEY_FOR_UPDATE_STMT.replaceFirst(REPLACE_TRAN_TBL_KEY, this.intTrxTbl);

        PreparedStatement pstmt = null;
        try {
            con = EZDConnectionMgr.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(findKeyStmt);
       
            // Initial data to select
            pstmt.setString(1, itrlIntfcId);/* INTERFACE_ID */
            pstmt.setBigDecimal(2, transactionId);/* ITRL_TRX_SQ */
            pstmt.setString(3, configId);/* ITRL_INTFC_TRX_CONFIG_ID */
            ResultSet res = pstmt.executeQuery();
            if (res.next() == false) {
                return null;
            }
            return res.getString(8); // EZUPTIME
        } catch (SQLException e) {
     	   int code = e.getErrorCode();
           if (code == 54 || code == 30006) {
               throw new EZDDBRecordLockedException(e);
           } else {
               StringBuffer sql = new StringBuffer(findKeyStmt); 
               String[] msgArg = {itrlIntfcId, transactionId.toString(), sql.toString(),e.getMessage()};
               throw new S21AbendException(e, "ZZIM0080E", msgArg);
           }
        } finally {
            EZDDebugOutput.println(D_MES_CODE, "[[END]]selectForUpdateItrlProcFlg:", this);
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                String[] msgArg = {itrlIntfcId, transactionId.toString(), e.getMessage()};
                throw new S21AbendException(e, "ZZIM0041E", msgArg);
            }
        }
    }


    /**
     * update Transaction Record 
     * @param sMsg EZDSMsg
     * @param index int
     * @param processed flag String
     * @return updated number
     */
    public int updateItrlProcFlg(ZZIL0100SMsg sMsg, int i, String itrlProcFlg) {
        EZDDebugOutput.println(D_MES_CODE, "[[START]]updateItrlProcFlg", this);

		String itrlIntfcId = sMsg.itrlIntfcId.getValue();
		BigDecimal transactionId = sMsg.A.no(i).transactionId_A.getValue();
		String configId = sMsg.itrlIntfcTrxConfigId_PS.getValue();
		String upTime = EZDStringUtil.getCurrentDate();
		String upTimeZone = EZDDBCICarrier.getUpTimeZone();
		String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
		String upUserId = EZDDBCICarrier.getUserID();
		String uppId = EZDDBCICarrier.getUppgID();


        String updateProcessedFlagStmt = UPDATE_PROCESSED_FLAG_STMT.replaceFirst(REPLACE_TRAN_TBL_KEY, this.intTrxTbl);

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
        	

            con = EZDConnectionMgr.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(updateProcessedFlagStmt);

            pstmt.setString(1, upTime); /* EZUPTIME */
            pstmt.setString(2, upTimeZone); /* EZUPTIMEZONE */
            pstmt.setString(3, upCmpyCd); /* EZUPCOMPANYCODE */
            pstmt.setString(4, upUserId); /* EZUPUSERID */
            pstmt.setString(5, uppId); /* EZUPAPLID */
            pstmt.setString(6, itrlProcFlg); /* ITRL_PROC_FLG */
            pstmt.setString(7, itrlIntfcId);/* ITRL_INTFC_ID */
            pstmt.setBigDecimal(8, transactionId);/* ITRL_TRX_SQ */
            pstmt.setString(9, configId);/* ITRL_INTFC_TRX_CONFIG_ID */

            
            int updatedNum = pstmt.executeUpdate();

            sMsg.A.no(i).procPgmId_AU.setValue(uppId);
            sMsg.A.no(i).processedFlag_A.setValue(itrlProcFlg);
            sMsg.A.no(i).xxDtTm_AU.setValue(upTime);
            sMsg.A.no(i).xxIntfcUpdTz_AU.setValue(upTimeZone);
            
            return updatedNum;
            
        } catch (SQLException e) {
        	   int code = e.getErrorCode();
               if (code == 54 || code == 30006) {
                   throw new EZDDBRecordLockedException(e);
               } else {
                   StringBuffer sql = new StringBuffer(updateProcessedFlagStmt); 
                   String[] msgArg = {itrlIntfcId, transactionId.toString(), sql.toString(),e.getMessage()};
                   throw new S21AbendException(e, "ZZIM0080E", msgArg);
               }
        } finally {
            EZDDebugOutput.println(D_MES_CODE, "[[END]]updateItrlProcFlg:", this);
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                String[] msgArg = {itrlIntfcId, transactionId.toString(), e.getMessage()};
                throw new S21AbendException(e, "ZZIM0041E", msgArg);
            }
        }
    }
   
 
}
