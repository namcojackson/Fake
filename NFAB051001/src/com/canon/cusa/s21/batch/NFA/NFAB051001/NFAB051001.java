/*
 * <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB051001;

import java.sql.SQLException;

import business.parts.NFACommonJrnlEntryConstant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchMain.TERM_CD;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;

/**
 * <pre>
 * Copyright (c) 2010 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch delete AJE_INTFC_CTRL depend on each Interface tables
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/21/2011   CSAI            H.Nakamura      Create          N/A
 * </pre>
 */
public class NFAB051001 extends S21BatchMain implements ZYPConstant, NFACommonJrnlEntryConstant {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** Termination Code */
    private TERM_CD termCd;

    /** procCount */
    private int procCount = 0;

    /** normalCnt */
    private int normalCnt = 0;

    /** errCnt */
    private int errCnt = 0;

    /**
     * Debug Entry Point
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFAB051001().executeBatch(NFAB051001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = getGlobalCompanyCode();
        this.termCd = TERM_CD.NORMAL_END;
    }

    @Override
    protected void mainRoutine() {
        try {
            /**
             * Delete AJE_OM_INTFC(TP_CD = 01)
             */
            deleteOmIntfc();
            /**
             * Delete AJE_INVTY_INTFC(TP_CD = 02)
             */
            deleteInvtyIntfc();
            /**
             * Delete AJE_PRT_INVTY_INTFC(TP_CD = 03)
             */
            deletePrtInvtyIntfc();
            /**
             * Delete AJE_AR_INTFC(TP_CD = 04)
             */
            deleteArIntfc();
            /**
             * Delete AJE_CM_INTFC(TP_CD = 05)
             */
            deleteCostIntfc();
            /**
             * Delete AJE_LOAN_DEPC_INTFC(TP_CD = 06)
             */
            deleteLoanDepcIntfc();
            /**
             * Delete AJE_ROSS_INTFC(TP_CD = 07)
             */
            deleteRossIntfc();
            /**
             * Delete AJE_NTC_INTFC(TP_CD = 08)
             */
            deleteNtcIntfc();
            /**
             * Delete AJE_MDSE_RVAL_INTFC(TP_CD = 09)
             */
            deleteMdseRvalIntfc();
            /**
             * Delete AJE_PRT_RVAL_INTFC(TP_CD = 10)
             */
            deletePrtRvalIntfc();
            /**
             * Delete AJE_VND_RTRN_INTFC(TP_CD = 11)
             */
            deleteVndIntfc();
            /**
             * Delete AJE_MDSE_RECLS_INTFC(TP_CD = 12)
             */
            deleteMdseReclsIntfc();
            /**
             * Delete AJE_PRT_RECLS_INTFC(TP_CD = 13)
             */
            deletePrtReclsIntfc();
            
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void deleteOmIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '01'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '01'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_OM_INTFC_PK");
        sb.append(" FROM   AJE_OM_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteInvtyIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '02'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '02'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_INVTY_INTFC_PK");
        sb.append(" FROM   AJE_INVTY_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deletePrtInvtyIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '03'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '03'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_PRT_INVTY_INTFC_PK");
        sb.append(" FROM   AJE_PRT_INVTY_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }
    
    private void deleteArIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '04'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '04'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_INTFC_PK");
        sb.append(" FROM   AJE_AR_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteCostIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '05'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '05'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_CM_INTFC_PK");
        sb.append(" FROM   AJE_CM_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteLoanDepcIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '06'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '06'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_LOAN_DEPC_INTFC_PK");
        sb.append(" FROM   AJE_LOAN_DEPC_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteRossIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '07'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '07'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_ROSS_INTFC_PK");
        sb.append(" FROM   AJE_ROSS_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteNtcIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '08'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '08'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_NTC_INTFC_PK");
        sb.append(" FROM   AJE_NTC_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteMdseRvalIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '09'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '09'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_MDSE_RVAL_INTFC_PK");
        sb.append(" FROM   AJE_MDSE_RVAL_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deletePrtRvalIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '10'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '10'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_PRT_RVAL_INTFC_PK");
        sb.append(" FROM   AJE_PRT_RVAL_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteVndIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '11'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '11'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_VND_RTRN_INTFC_PK");
        sb.append(" FROM   AJE_VND_RTRN_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deleteMdseReclsIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '12'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '12'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_MDSE_RECLS_INTFC_PK");
        sb.append(" FROM   AJE_MDSE_RECLS_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

    private void deletePrtReclsIntfc() throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM AJE_INTFC_CTRL");
        sb.append(" WHERE AJE_INTFC_TP_CD = '13'");
        sb.append(" AND AJE_INTFC_PK IN");
        sb.append(" (SELECT CTRL.AJE_INTFC_PK");
        sb.append(" FROM   AJE_INTFC_CTRL CTRL");
        sb.append(" WHERE  CTRL.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    CTRL.EZCANCELFLAG = '0'");
        sb.append(" AND    CTRL.AJE_INTFC_TP_CD = '13'");
        sb.append(" MINUS");
        sb.append(" SELECT INTFC.AJE_PRT_RECLS_INTFC_PK");
        sb.append(" FROM   AJE_PRT_RECLS_INTFC INTFC");
        sb.append(" WHERE  INTFC.GLBL_CMPY_CD = '").append(this.glblCmpyCd).append("'");
        sb.append(" AND    INTFC.EZCANCELFLAG = '0')");
        S21BatchTransactionQuery query = new S21BatchTransactionQuery();
        query.executeQuery(sb.toString());
    }

 
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCnt, this.errCnt, this.procCount);
    }

}
