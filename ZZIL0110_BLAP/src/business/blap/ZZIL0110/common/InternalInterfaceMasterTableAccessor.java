/*
 * Copyright (c) 2012 Canon USA Inc. All rights reserved. Original
 * Author: Masaaki Yaguchi Company: Fujitsu Limited Date: 2013/09/10
 */

package business.blap.ZZIL0110.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import parts.common.EZDDebugOutput;
import parts.common.EZDStringUtil;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;

import business.db.ITRL_INTFC_TRX_CONFIGTMsg;

import com.canon.cusa.s21.framework.integration.S21IntegrationDefinition;
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
public class InternalInterfaceMasterTableAccessor {

    /** Interface Table Master Table Name */
    private String intTblMst = null;

    /** Message Debug Code */
    private static final int D_MES_CODE = 600;

    /** SQL Connection */
    private Connection con;

    /** Prepared Statements */
    private static final String FIND_BY_KEY_FOR_UPDATE_STMT = "SELECT EZTABLEID,EZCANCELFLAG,EZINTIME,EZINTIMEZONE,EZINCOMPANYCODE,EZINUSERID,EZINAPLID,EZUPTIME,EZUPTIMEZONE,EZUPCOMPANYCODE,EZUPUSERID,EZUPAPLID,ITRL_INTFC_ID,ITRL_INTFC_TBL_ID,ITRL_INTFC_TRX_CONFIG_ID,GLBL_CMPY_CD FROM #INT_IF_MSTR_TABLE# WHERE ITRL_INTFC_ID=? AND ITRL_INTFC_TBL_ID=? AND ITRL_INTFC_TRX_CONFIG_ID=? AND EZCANCELFLAG=? FOR UPDATE NOWAIT";
    private static final String UPDATE_PROCESSED_FLAG_STMT = "UPDATE #INT_IF_MSTR_TABLE# SET EZTABLEID=?,EZCANCELFLAG=?,EZUPTIME=?,EZUPTIMEZONE=?,EZUPCOMPANYCODE=?,EZUPUSERID=?,EZUPAPLID=?,GLBL_CMPY_CD=? WHERE ITRL_INTFC_ID=? AND ITRL_INTFC_TBL_ID=? AND ITRL_INTFC_TRX_CONFIG_ID=?";
    private static final String CREATE_TRAN_STMT = "INSERT INTO #INT_IF_MSTR_TABLE# (EZTABLEID, EZCANCELFLAG, EZINTIME, EZINTIMEZONE, EZINCOMPANYCODE, EZINUSERID, EZINAPLID, EZUPTIME, EZUPTIMEZONE, EZUPCOMPANYCODE, EZUPUSERID, EZUPAPLID, ITRL_INTFC_ID, ITRL_INTFC_TBL_ID, ITRL_INTFC_TRX_CONFIG_ID, GLBL_CMPY_CD) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String REPLACE_IF_MSTR_TBL_KEY = "#INT_IF_MSTR_TABLE#";
    
    /**
     * Constructor with configId for target system 
     * @param configId This id and record is stored on INT_INTFC_TRANS_CONFIG Table for each target system.
     */
    public InternalInterfaceMasterTableAccessor(String configId) {
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

        this.intTblMst = res.itrlIntfcMstrTblId.getValue();

        if (res.itrlIntfcTrxTblId.getValue() == null) {
            String[] msgArg = {"ITRL_INTFC_TRX_TBL_ID",configId};
            throw new S21AbendException("ZZIM0074E",msgArg);
        } else if (res.itrlIntfcMstrTblId.getValue() == null) {
            String[] msgArg = {"ITRL_INTFC_MSTR_TBL_ID",configId};
            throw new S21AbendException("ZZIM0074E",msgArg);
        }
    }

    /**
     * update ezdCancelFlug to 1
     * @param interfaceId
     * @param interfaceTblId
     * @param interfaceConfigId
     */
    public boolean cancelMasterRecord(final String interfaceId, final String interfaceTblId, final String interfaceConfigId){
        return changeMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, "1");
    }
    
    /**
     * insert/update Master Record
     * @param interfaceId
     * @param interfaceTblId
     * @param interfaceConfigId
     */
    public boolean makeMasterRecord(final String interfaceId, final String interfaceTblId, final String interfaceConfigId){
        return changeMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, "0");
    }
    
    
    /**
     * Adds a new record to the integration interface master table(For Internal Data Transmit)<BR>
     * Additional records will be given a unique keys, transactionId and interfaceId. <BR>
     * If the operation fails, throws S21AbendException.
     * @param interfaceId Adding records interfaceId
     * @param transactionId Adding records transactionId
     */
    public boolean changeMasterRecord(final String interfaceId, final String interfaceTblId, final String interfaceConfigId, final String cancel) {
        EZDDebugOutput.println(D_MES_CODE, "[[START]]create / update Interface Master Record:", this);
        EZDDebugOutput.println(D_MES_CODE, "[ITRL_INTFC_ID:" + interfaceId + "]", this);
        S21InfoLogOutput.println("ZZIM0078I", new String[] {interfaceId, interfaceTblId, "createMasterRecord"});

        // check parameter
        try {
            checkParams(interfaceId, interfaceTblId, interfaceConfigId);
        } catch (Exception e) {
            throw new S21AbendException(e, e.getMessage());
        }
        
        if("0".equals(cancel)){
            // create
            if(lockMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, cancel)) return false; // duplicate
            if(lockMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, "1")) {
                updateMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, cancel);
            }else{
                createMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, cancel);
            }
        }else{
            if(!lockMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, "0")) return false; // not found
            updateMasterRecord(interfaceId, interfaceTblId, interfaceConfigId, cancel);
        }
        
        // insert record to Transaction management table
        S21InfoLogOutput.println("ZZIM0075I", new String[] {interfaceId, interfaceTblId, "create / update Interface Master Record"});
        EZDDebugOutput.println(D_MES_CODE, "[[END]]create / update Interface Master Record:", this);
        return true;
    }

    /**
     * create Master Record 
     * @param interfaceId
     * @param interfaceTblId
     * @param interfaceConfigId
     */
    private void createMasterRecord(final String interfaceId, final String interfaceTblId,  final String interfaceConfigId, final String cancel) {
    	EZDDebugOutput.println(D_MES_CODE, "[[START]]createTransactionRecord", this);

        String insertTranDataStmt = CREATE_TRAN_STMT.replaceFirst(REPLACE_IF_MSTR_TBL_KEY, this.intTblMst);

        PreparedStatement pstmt = null;
        try {
            con = EZDConnectionMgr.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(insertTranDataStmt);
            
            String nowTime = EZDStringUtil.getCurrentDate();
            String nowTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();
            String cmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String uppgId = EZDDBCICarrier.getUppgID();

            // Initial data to insert
            pstmt.setString(1, this.intTblMst );/* EZTABLEID */
            pstmt.setString(2, cancel); /* EZCANCELFLAG */
            pstmt.setString(3, nowTime); /* EZINTIME */
            pstmt.setString(4, nowTimeZone); /* EZUPTIMEZONE */
            pstmt.setString(5, cmpyCd); /* EZINCOMPANYCODE */
            pstmt.setString(6, userId); /* EZINUSERID */
            pstmt.setString(7, uppgId); /* EZINAPLID */
            pstmt.setString(8, nowTime); /* EZUPTIME */
            pstmt.setString(9, nowTimeZone); /* EZUPTIMEZONE */
            pstmt.setString(10, cmpyCd); /* EZUPCOMPANYCODE */
            pstmt.setString(11, userId); /* EZUPUSERID */
            pstmt.setString(12, uppgId); /* EZUPAPLID */
            pstmt.setString(13, interfaceId); /* ITRL_INTFC_ID */
            pstmt.setString(14, interfaceTblId); /* ITRL_INTFC_TBL_ID */
            pstmt.setString(15, interfaceConfigId); /* ITRL_INTFC_TRX_CONFIG_ID */
            pstmt.setString(16, cmpyCd); /* GLBL_CMPY_CD */

            pstmt.executeUpdate();
        
        } catch (SQLException e) {
            StringBuffer sql = new StringBuffer(insertTranDataStmt); 
            String[] msgArg = {interfaceId, interfaceTblId, sql.toString(), e.getMessage()};
            throw new S21AbendException(e, "ZZIM0080E", msgArg);
        } finally {
            try {
                if (pstmt != null) {
                    EZDDebugOutput.println(D_MES_CODE, "[[END]]createTransactionRecord:", this);
                    pstmt.close();
                }
            } catch (SQLException e) {
            	String[] msgArg = {interfaceId, interfaceTblId, e.getMessage()};
            	throw new S21AbendException(e, "ZZIM0041E", msgArg);
            }
        }
    }
    
    

    /**
     * lock Master Record 
     * @param interfaceId
     * @param interfaceTblId
     * @param interfaceConfigId
     * @return true - lock, false - can't lock
     */
    private boolean lockMasterRecord(final String interfaceId, final String interfaceTblId, final String interfaceConfigId, final String cancel) {
        EZDDebugOutput.println(D_MES_CODE, "[[START]]lockMasterRecord", this);

        String findKeyStmt = FIND_BY_KEY_FOR_UPDATE_STMT.replaceFirst(REPLACE_IF_MSTR_TBL_KEY, this.intTblMst);

        PreparedStatement pstmt = null;
        try {
            con = EZDConnectionMgr.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(findKeyStmt);
       
            // Initial data to select
            pstmt.setString(1, interfaceId);/* INTERFACE_ID */
            pstmt.setString(2, interfaceTblId);/* ITRL_INTFC_TBL_ID */
            pstmt.setString(3, interfaceConfigId);/* ITRL_INTFC_TRX_CONFIG_ID */
            pstmt.setString(4, cancel);  /* EZCANCELFLAG */
            ResultSet res = pstmt.executeQuery();
            if (res.next() == false) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            StringBuffer sql = new StringBuffer(findKeyStmt); 
            String[] msgArg = {interfaceId, interfaceTblId, sql.toString(), e.getMessage()};
            throw new S21AbendException(e, "ZZIM0080E", msgArg);
        } finally {
            try {
                if (pstmt != null) {
                    EZDDebugOutput.println(D_MES_CODE, "[[END]]lockMasterRecord:", this);
                    pstmt.close();
                }
            } catch (SQLException e) {
                String[] msgArg = {interfaceId, interfaceTblId, e.getMessage()};
                throw new S21AbendException(e, "ZZIM0041E", msgArg);
            }
        }
    }


    /**
     * update Master Record 
     * @param interfaceId
     * @param interfaceTblId
     * @param interfaceConfigId
     */
    private void updateMasterRecord (final String interfaceId, final String interfaceTblId, final String interfaceConfigId, final String cancel) {
        EZDDebugOutput.println(D_MES_CODE, "[[START]]updateTransactionRecord", this);
        String updateProcessedFlagStmt = UPDATE_PROCESSED_FLAG_STMT.replaceFirst(REPLACE_IF_MSTR_TBL_KEY, this.intTblMst);

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = EZDConnectionMgr.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(updateProcessedFlagStmt);

            pstmt.setString(1, this.intTblMst );/* EZTABLEID */
            pstmt.setString(2, cancel);/* EZCANCELFLAG */
            pstmt.setString(3, EZDStringUtil.getCurrentDate()); /* EZUPTIME */
            pstmt.setString(4, EZDDBCICarrier.getUpTimeZone()); /* EZUPTIMEZONE */
            pstmt.setString(5, EZDDBCICarrier.getUpCmpyCd()); /* EZUPCOMPANYCODE */
            pstmt.setString(6, EZDDBCICarrier.getUserID()); /* EZUPUSERID */
            pstmt.setString(7, EZDDBCICarrier.getUppgID()); /* EZUPAPLID */
            pstmt.setString(8, EZDDBCICarrier.getUpCmpyCd());/* GLBL_CMPY_CD */
            pstmt.setString(9, interfaceId);/* ITRL_INTFC_ID */
            pstmt.setString(10, interfaceTblId);/* ITRL_INTFC_TBL_ID */
            pstmt.setString(11, interfaceConfigId);/* ITRL_INTFC_TRX_CONFIG_ID */

            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            StringBuffer sql = new StringBuffer(updateProcessedFlagStmt); 
            String[] msgArg = {interfaceId, interfaceTblId, sql.toString(),e.getMessage()};
            throw new S21AbendException(e, "ZZIM0080E", msgArg);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    }
                if (pstmt != null) {
                    EZDDebugOutput.println(D_MES_CODE, "[[END]]updateTransactionRecord:", this);
                    pstmt.close();
                }
            } catch (SQLException e) {
                String[] msgArg = {interfaceId, interfaceId, e.getMessage()};
                throw new S21AbendException(e, "ZZIM0041E", msgArg);
            }
        }
    }

    
    /** argument name InterfaceId */
    private static final String COL_INTERFACE_ID = "InterfaceId";
    
    /** argument name InterfaceTableId */
    private static final String COL_INTERFACE_TBL_ID = "InterfaceTableId";
    
    /** argument name InterfaceConfigId */
    private static final String COL_INTERFACE_CONFIG_ID = "InterfaceConfigId";

    /** interfaceId maximum length */
    private static final int MAX_LEN_INTERFACE_ID = 30;
    
    /** interfaceTableId maximum length */
    private static final int MAX_LEN_INTERFACE_TBL_ID = 28;
    
    /** interfaceConfigId maximum length */
    private static final int MAX_LEN_INTERFACE_CONFIG_ID = 24;
    
    /**
     * 
     * @param interfaceId
     * @param interfaceTblId
     * @param interfaceConfigId
     * @throws S21IntegrationException
     */
    private void checkParams(final String interfaceId, final String interfaceTblId, final String interfaceConfigId) throws Exception{
        checkInterfaceId(interfaceId);
        checkInterfaceTblId(interfaceTblId);
        checkInterfaceConfigId(interfaceConfigId);
    }
    
    private void checkInterfaceId(final String interfaceId) throws Exception{
        // check interfaceId
        if (interfaceId == null || interfaceId.length() == 0) {
            // An argument injustice error
            String[] mesArg = new String[] {COL_INTERFACE_ID, interfaceId };
            String errorMessage = S21MessageFunc.clspGetMessage("ZZIM0012E", mesArg);
            throw new Exception(errorMessage);
        } else if (interfaceId.length() > MAX_LEN_INTERFACE_ID) {
            // maximum length
            // An argument injustice error
            String[] mesArg = new String[] {COL_INTERFACE_ID, interfaceId };
            String errorMessage = S21MessageFunc.clspGetMessage("ZZIM0012E", mesArg);
            throw new Exception(errorMessage);
        }
    }
    
    private void checkInterfaceTblId(final String interfaceTblId) throws Exception{
        // check interfaceId
        if (interfaceTblId == null || interfaceTblId.length() == 0) {
            // An argument injustice error
            String[] mesArg = new String[] {COL_INTERFACE_TBL_ID, interfaceTblId };
            String errorMessage = S21MessageFunc.clspGetMessage("ZZIM0012E", mesArg);
            throw new Exception(errorMessage);
        } else if (interfaceTblId.length() > MAX_LEN_INTERFACE_TBL_ID) {
            // maximum length
            // An argument injustice error
            String[] mesArg = new String[] {COL_INTERFACE_TBL_ID, interfaceTblId };
            String errorMessage = S21MessageFunc.clspGetMessage("ZZIM0012E", mesArg);
            throw new Exception(errorMessage);
        }
    }
    
    private void checkInterfaceConfigId(final String interfaceConfigId) throws Exception{
        // check interfaceId
        if (interfaceConfigId == null || interfaceConfigId.length() == 0) {
            // An argument injustice error
            String[] mesArg = new String[] {COL_INTERFACE_CONFIG_ID, interfaceConfigId };
            String errorMessage = S21MessageFunc.clspGetMessage("ZZIM0012E", mesArg);
            throw new Exception(errorMessage);
        } else if (interfaceConfigId.length() > MAX_LEN_INTERFACE_CONFIG_ID) {
            // maximum length
            // An argument injustice error
            String[] mesArg = new String[] {COL_INTERFACE_CONFIG_ID, interfaceConfigId };
            String errorMessage = S21MessageFunc.clspGetMessage("ZZIM0012E", mesArg);
            throw new Exception(errorMessage);
        }
    }
}
