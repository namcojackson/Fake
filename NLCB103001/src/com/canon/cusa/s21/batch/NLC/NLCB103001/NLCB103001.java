/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB103001;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.PO_DTLTMsg;
import business.db.PRT_STK_IN_RSLT_WRKTMsg;
import business.db.SHPG_ORDTMsg;

import com.canon.cusa.s21.batch.NLC.NLCB103001.common.NLCB103001Common;
import com.canon.cusa.s21.batch.NLC.NLCB103001.constant.NLCB103001Constant;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 *  Stock In Data of the Day
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   CITS            R.Shimamoto     Create          N/A
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 03/29/2019   CITS            T.Tokutomi      Update          QC#30959
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 02/04/2020   Fujitsu         S.Iidaka        Update          QC#55572
 *</pre>
 */
public class NLCB103001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** global company code */
    private String glblCmpyCd = "";

    /** Sales Date */
    private String slsDt = "";

    /** Previous Date */
    private String prevDt = "";

    /** CINC Vender Code */
    private String cincVndCd = "";

    /** CINC Vender Code */
    private String cincCarrCd = "";

    /** Stock In Type : Stock In */
    private String stockInTypeStockIn = "";

    /** Stock In Type : Movement */
    private String stockInTypeMovement = "";

    /** Stock In Type : Other */
    private String stockInTypeOther = "";

    /** Ship Type : Nomal */
    private String shipTypeNomal = "";

    /** Ship Type : Other */
    private String shipTypeOther = "";

    /** Shipping Methode : Other */
    private String shipMethodeOther = "";

    /** Onerous Type : Onerous */
    private String onerousTypeOnerous = "";

    /** Include Technician Inventory Flag */
    private String inclTechInvtyFlg = "";

    /** exclude Inventory Location Code List */
    private String exclInvtyLocCdList = "";

    /** Parts Exclude Inventory Location Code CINC Map */
    private Map<String, String> prtExclInvtyLocCdCincMap;

    /** total correct count */
    private int totalCorrectCount = 0;

    /** total error count */
    private int totalErrorCount = 0;

    /** total Execute count */
    private int totalExecuteCount = 0;

    /** error message parts */
    private NPXC001001SendMailForErrorInfo sendMailForErrorInfo = new NPXC001001SendMailForErrorInfo();

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Commit Number */
    private int commitNumber;

    // QC#26966 Add.
    /**  canon inc global warehouse code CLUMBUS */
    private String cincGlblWhCdOfCLUMBUS = "";

    // QC#30959 Add.
    /**  canon inc interface parts vendor dummy code */
    private String dummyVndCd = "";

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NLCB103001().executeBatch(NLCB103001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of termination code
        setTermCd(TERM_CD.NORMAL_END);

        // Get the Batch Mode
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= NLCB103001Constant.MAX_COMMIT_NUMBER) {

            this.commitNumber = NLCB103001Constant.MAX_COMMIT_NUMBER;
        }

        // Get Environment variable
        getEnvVariable();

        this.prtExclInvtyLocCdCincMap = getVarCharConstMapData(NLCB103001Constant.VC_KEY_PRT_EXCL_INVTY_LOC_CD_CINC);

    }

    @Override
    protected void mainRoutine() {

        prevDt = getPrevDt();

        Map<String, Object> queryParam = new HashMap<String, Object>();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB103001Constant.BIND_SLS_DT, slsDt);
        queryParam.put(NLCB103001Constant.BIND_PREV_DT, prevDt);

        queryParam.put(NLCB103001Constant.BIND_EXCL_INVTY_LOC_CD_LIST, NLCB103001Common.splitValue(exclInvtyLocCdList));
        String exclLocTpCd = "";
        if (ZYPConstant.FLG_OFF_N.equals(inclTechInvtyFlg)) {
            exclLocTpCd = LOC_TP.TECHNICIAN;
        }
        queryParam.put(NLCB103001Constant.BIND_EXCL_LOC_TP_CD, exclLocTpCd);

        // get vndSysTpCdList
        String vndSysTpCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD, glblCmpyCd);
        queryParam.put(NLCB103001Constant.BIND_VND_SYS_TP_CD_LIST, NLCB103001Common.splitValue(vndSysTpCd));

        queryParam.put(NLCB103001Constant.BIND_EFF_FROM_DT_DEFALUT, NLCB103001Constant.EFF_FROM_DT_DEFALUT);
        queryParam.put(NLCB103001Constant.BIND_NUMONE, NLCB103001Constant.NUM_ONE);
        queryParam.put(NLCB103001Constant.BIND_DS_COND_CONST_GRP_ID, NLCB103001Constant.VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(NLCB103001Constant.BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(NLCB103001Constant.BIND_DS_COND_CONST_GRP_ID_STK_IN, NLCB103001Constant.VAR_CHAR_CONST_KEY_SCUBE_IF_STK_IN);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            queryParam.put("exclMdseCdList", exclMdseList);
        }

        // Add Start 2020/02/04 QC#55572
        String scubeExclSwhCdList = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(scubeExclSwhCdList)) {
            String[] exclSwhCdList = scubeExclSwhCdList.split(",");

            queryParam.put("exclSwhCdList", exclSwhCdList);
        }
        // Add End 2020/02/04 QC#55572

        ssmBatchClient.queryObject("getInvTrx", queryParam, new CreateWrkTblFromInvTrx());

        if (ZYPCommonFunc.hasValue(prevDt)) {
            List<PRT_STK_IN_RSLT_WRKTMsg> workTMsgList = getDeleteKey();
            if (workTMsgList != null && workTMsgList.size() > 0) {
                int retCnt = this.deleteOldData(workTMsgList);
                if (retCnt != workTMsgList.size()) {
                    setTermCd(TERM_CD.WARNING_END);
                    rollback();
                } else {
                    commit();
                }
            }
        }

    }

    @Override
    protected void termRoutine() {

        if (TERM_CD.WARNING_END.equals(termCd)) {
            // send mail
            sendMailForErrorInfo.sendMail(glblCmpyCd, NLCB103001Constant.BIZ_ID);
        }

        // End Process
        totalErrorCount = totalExecuteCount - totalCorrectCount;
        setTermState(this.termCd, this.totalCorrectCount, this.totalErrorCount, this.totalExecuteCount);

    }

    /**
     * <PRE>
     * Set termCd
     * </PRE>
     */
    private void setTermCd(TERM_CD ptermCd) {

        if (TERM_CD.WARNING_END.equals(ptermCd)) {
            if (TERM_CD.NORMAL_END.equals(termCd)) {
                termCd = ptermCd;
            }
        } else {
            termCd = ptermCd;
        }
    }

    /**
     * Get Environment variable
     * @param
     * @return Boolean
     */
    private void getEnvVariable() {

        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLCB103001Constant.ZZZM9025E, new String[] {NLCB103001Constant.ERR_ITEM_GLBL_CMPY_CD });
        }

        slsDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(slsDt)) {
            throw new S21AbendException(NLCB103001Constant.ZZZM9025E, new String[] {NLCB103001Constant.ERR_ITEM_SLS_DT });
        }

        cincVndCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_CINC_VND_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cincVndCd)) {
            cincVndCd = NLCB103001Constant.SCUBE_IF_CINC_VND_CD_DEFAULT;
        }

        cincCarrCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_CARR_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cincCarrCd)) {
            cincCarrCd = NLCB103001Constant.SCUBE_IF_CARR_CD_DEFAULT;
        }

        stockInTypeStockIn = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_STOCK_IN_TYPE_STOCK_IN, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(stockInTypeStockIn)) {
            stockInTypeStockIn = NLCB103001Constant.SCUBE_IF_STK_IN_TP_SI_DEFAULT;
        }

        stockInTypeMovement = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_STOCK_IN_TYPE_MOVEMENT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(stockInTypeMovement)) {
            stockInTypeMovement = NLCB103001Constant.SCUBE_IF_STK_IN_TP_MV_DEFAULT;
        }

        stockInTypeOther = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_STOCK_IN_TYPE_OTHER, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(stockInTypeOther)) {
            stockInTypeOther = NLCB103001Constant.SCUBE_IF_STK_IN_TP_OT_DEFAULT;
        }

        shipTypeNomal = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_SHIP_TYPE_NOMAL, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(shipTypeNomal)) {
            shipTypeNomal = NLCB103001Constant.SCUBE_IF_SHIP_TP_N_DEFAULT;
        }

        shipTypeOther = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_SHIP_TYPE_OTHER, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(shipTypeOther)) {
            shipTypeOther = NLCB103001Constant.SCUBE_IF_SHIP_TP_O_DEFAULT;
        }

        onerousTypeOnerous = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_ONEROUS_TYPE_ONEROUS, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(onerousTypeOnerous)) {
            onerousTypeOnerous = NLCB103001Constant.SCUBE_IF_PRT_CHRG_IND_C_DEFAULT;
        }

        shipMethodeOther = NLCB103001Constant.SCUBE_IF_SHPG_METH_OTHER;

        inclTechInvtyFlg = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_PRT_INCL_TECH_INVTY_FLG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(inclTechInvtyFlg)) {
            inclTechInvtyFlg = NLCB103001Constant.PRT_INCL_TECH_INVTY_CINC_FLG_DEFAULT;
        } else {
            if (!ZYPConstant.FLG_ON_Y.equals(inclTechInvtyFlg) && !ZYPConstant.FLG_OFF_N.equals(inclTechInvtyFlg)) {
                throw new S21AbendException(NLCB103001Constant.NLCM0128E, new String[] {NLCB103001Constant.ERR_ITEM_INCL_TECH_INVTY_FLG, NLCB103001Constant.ERR_TBL_VAR_CHAR_CONST, NLCB103001Constant.VC_KEY_PRT_INCL_TECH_INVTY_FLG });
            }
        }

        // QC#26966 Add.
        cincGlblWhCdOfCLUMBUS = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cincGlblWhCdOfCLUMBUS)) {
            cincGlblWhCdOfCLUMBUS = NLCB103001Constant.CINC_GLBL_WH_CD_CLMBUS_DEFAULT;
        }

        exclInvtyLocCdList = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_PRT_EXCL_INVTY_LOC_CD_CINC, glblCmpyCd);

        // QC#30959 Add. VND_CD length 12.
        dummyVndCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB103001Constant.VC_KEY_SCUBE_IF_DUMMY_VND_CD, glblCmpyCd);
        if (dummyVndCd.length() > NLCB103001Constant.LENGTH_CINC_VND_CD) {
            dummyVndCd = dummyVndCd.substring(0, NLCB103001Constant.LENGTH_CINC_VND_CD);
        }

        // Initialization of SSM
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    /**
     *<pre>
     * Get Previous Date
     *</pre>
     * @return Previous Date (INTFC_CRAT_DT)
     */
    private String getPrevDt() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        ssmParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLCB103001Constant.BIND_INTFC_CRAT_DT, slsDt);
        return (String) ssmBatchClient.queryObject("getPrevDt", ssmParam, execParam);
    }

    /**
     * <PRE>
     * Create WrkTbl From InvTrx
     * </PRE>
     */
    private class CreateWrkTblFromInvTrx extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            // has no result data
            if (!rs.next()) {
                return true;
            }

            try {
                do {
                    PRT_STK_IN_RSLT_WRKTMsg wrkTMsg = new PRT_STK_IN_RSLT_WRKTMsg();

                    String cincGlblWhCd = getCincGlblWhCd(rs.getString(NLCB103001Constant.DB_CLMN_TO_RTL_WH_CD), rs.getString(NLCB103001Constant.DB_CLMN_TO_WH_OWNR_CD), rs.getString(NLCB103001Constant.DB_CLMN_TO_LOC_TP_CD));

                    String cincSplyGlblWhCd = null;
                    if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NLCB103001Constant.DB_CLMN_VAL_TXT_04))) {

                        cincSplyGlblWhCd = getCincGlblWhCd(rs.getString(NLCB103001Constant.DB_CLMN_FROM_RTL_WH_CD), rs.getString(NLCB103001Constant.DB_CLMN_FROM_WH_OWNR_CD), rs.getString(NLCB103001Constant.DB_CLMN_FROM_LOC_TP_CD));

                        if (isTargetLoc(rs.getString(NLCB103001Constant.DB_CLMN_FROM_INVTY_LOC_CD), rs.getString(NLCB103001Constant.DB_CLMN_FROM_LOC_TP_CD))) {
                            if (!isTargetGlobalWH(rs, cincSplyGlblWhCd, cincGlblWhCd)) {
                                continue;
                            }

                        }
                    }
                    // Get Work Table
                    boolean hasError = !createPrtStkInRsltWrkTMsg(rs, wrkTMsg);

                    if (!hasError) {
                        S21FastTBLAccessor.insert(wrkTMsg);
                        commit();
                        totalCorrectCount++;
                    } else {
                        setTermCd(TERM_CD.WARNING_END);
                    }

                    totalExecuteCount++;

                } while (rs.next());

            } catch (SQLException e) {
                // Insert AbnomalEnd
                // RollBack
                rollback();
                setTermState(TERM_CD.ABNORMAL_END, totalCorrectCount, totalErrorCount, totalExecuteCount);
                sqlExceptionHandler(e);
            }

            return true;
        }

        private String getShpgSvcLvlCdFromPo(String poOrdNum, String poOrdDtlLineNum) {
            PO_DTLTMsg inMsg = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poOrdDtlLineNum);
            PO_DTLTMsg outMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
            if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.shpgSvcLvlCd.getValue())) {
                return outMsg.shpgSvcLvlCd.getValue();
            } else {
                return null;
            }
        }

        private String getShpgSvcLvlCdFromSo(String soNum) {
            SHPG_ORDTMsg inMsg = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.soNum, soNum);
            SHPG_ORDTMsg outMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(inMsg);
            if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.shpgSvcLvlCd.getValue())) {
                return outMsg.shpgSvcLvlCd.getValue();
            } else {
                return null;
            }

        }

        /**
         * <PRE>
         * create PRT_STK_IN_RSLT_WRKTMsg
         * </PRE>
         * @param rs ResultSet
         * @param workTMsg PRT_STK_IN_RSLT_WRKTMsg
         * @return Boolean
         */
        private Boolean createPrtStkInRsltWrkTMsg(ResultSet rs, PRT_STK_IN_RSLT_WRKTMsg workTMsg) throws SQLException {

            BigDecimal invtyTrxPk = rs.getBigDecimal(NLCB103001Constant.DB_CLMN_INVTY_TRX_PK);
            String invtyTrxDt = rs.getString(NLCB103001Constant.DB_CLMN_INVTY_TRX_DT);
            String vndCd = rs.getString(NLCB103001Constant.DB_CLMN_VND_CD);
            // QC#30959 Update.
            if (!ZYPCommonFunc.hasValue(vndCd)) {
                vndCd = NLCB103001.this.dummyVndCd;
            }
            String mdseCd = rs.getString(NLCB103001Constant.DB_CLMN_MDSE_CD);
            BigDecimal invtyTrxQty = rs.getBigDecimal(NLCB103001Constant.DB_CLMN_INVTY_TRX_QTY);
            String poOrdNum = rs.getString(NLCB103001Constant.DB_CLMN_PO_ORD_NUM);
            String poOrdDtlLineNum = rs.getString(NLCB103001Constant.DB_PO_ORD_DTL_LINE_NUM);
            String soNum = rs.getString(NLCB103001Constant.SO_NUM);
            String vndGlblCmpyCd = rs.getString(NLCB103001Constant.DB_CLMN_VND_GLBL_CMPY_CD);
            String intfcPoOrdNum = rs.getString(NLCB103001Constant.DB_CLMN_INVTY_TRX_SLP_NUM);

            // Convert parts code
            String cincSplyGlblCmpyCd = "";
            String cincStkInGlblCatgCd = "";
            String cincPoGlblCatgCd = "";
            String prtChrgInd = "";
            String intfcStkInSgnTxt = "";

            // To Location GLBL_WH_CD
            String cincGlblWhCd = getCincGlblWhCd(rs.getString(NLCB103001Constant.DB_CLMN_TO_RTL_WH_CD), rs.getString(NLCB103001Constant.DB_CLMN_TO_WH_OWNR_CD), rs.getString(NLCB103001Constant.DB_CLMN_TO_LOC_TP_CD));

            String cincSplyGlblWhCd = null;
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NLCB103001Constant.DB_CLMN_VAL_TXT_04))) {

                // From Location GLBL_WH_CD
                cincSplyGlblWhCd = getCincGlblWhCd(rs.getString(NLCB103001Constant.DB_CLMN_FROM_RTL_WH_CD), rs.getString(NLCB103001Constant.DB_CLMN_FROM_WH_OWNR_CD), rs.getString(NLCB103001Constant.DB_CLMN_FROM_LOC_TP_CD));

                Map<String, Object> cincGlblCatgCd = getCincGlblCatgCd(rs);
                cincStkInGlblCatgCd = cincGlblCatgCd.get(NLCB103001Constant.DB_CLMN_CINC_STK_IN_GLBL_CATG_CD).toString();
                cincPoGlblCatgCd = cincGlblCatgCd.get(NLCB103001Constant.DB_CLMN_CINC_PO_GLBL_CATG_CD).toString();

            } else {
                cincStkInGlblCatgCd = stockInTypeStockIn;
                cincPoGlblCatgCd = shipTypeNomal;
            }

            // Check Exist Shipping Methode Info
            String trdPtnrShpgMethCd = shipMethodeOther;
            String shpgSvcLvlCd = null;
            // INVTY_TRX.PO_ORD_NUM is not NULL
            if (ZYPCommonFunc.hasValue(poOrdNum)) {

                shpgSvcLvlCd = getShpgSvcLvlCdFromPo(poOrdNum, poOrdDtlLineNum);

                if (ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {

                    Map<String, Object> getShpgMethCd = getShpgMethCdForPo(poOrdNum, poOrdDtlLineNum);

                    if (getShpgMethCd == null) {
                        String[] errMsg = new String[] {NLCB103001Constant.ERR_ITEM_SHIP_METHODE, "INVTY_TRX_PK:" + invtyTrxPk + " MDSE_CD:" + mdseCd };
                        sendMailForErrorInfo.addErrMsgWithLogOutput(NLCB103001Constant.NLCM0127W, errMsg);
                        setTermCd(TERM_CD.WARNING_END);
                    } else if (getShpgMethCd != null && getShpgMethCd.get(NLCB103001Constant.DB_CLMN_TRD_PTNR_SHPG_METH_CD) != null) {
                        trdPtnrShpgMethCd = getShpgMethCd.get(NLCB103001Constant.DB_CLMN_TRD_PTNR_SHPG_METH_CD).toString();
                    }
                } else {
                    trdPtnrShpgMethCd = shipMethodeOther;
                }

            }

            // INVTY_TRX.SO_NUM is not NULL
            if (!ZYPCommonFunc.hasValue(poOrdNum) && ZYPCommonFunc.hasValue(soNum)) {

                shpgSvcLvlCd = getShpgSvcLvlCdFromSo(soNum);

                if (ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
                    Map<String, Object> getShpgMethCd = getShpgMethCdForSo(soNum);

                    if (getShpgMethCd == null) {
                        String[] errMsg = new String[] {NLCB103001Constant.ERR_ITEM_SHIP_METHODE, "INVTY_TRX_PK:" + invtyTrxPk + " MDSE_CD:" + mdseCd };
                        sendMailForErrorInfo.addErrMsgWithLogOutput(NLCB103001Constant.NLCM0127W, errMsg);
                        setTermCd(TERM_CD.WARNING_END);
                    } else if (getShpgMethCd != null && getShpgMethCd.get(NLCB103001Constant.DB_CLMN_TRD_PTNR_SHPG_METH_CD) != null) {
                        trdPtnrShpgMethCd = getShpgMethCd.get(NLCB103001Constant.DB_CLMN_TRD_PTNR_SHPG_METH_CD).toString();
                    }
                } else {
                    trdPtnrShpgMethCd = shipMethodeOther;
                }
            }
            // Get parts size code from mdse code
            NMXC104001ConvertPartsMdseCdBean convPrtMdseBean = new NMXC104001ConvertPartsMdseCdBean();
            convPrtMdseBean.setMode(NMXC104001ConvertPartsMdseCd.MODE_MDSE);
            convPrtMdseBean.setGlblCmpyCd(glblCmpyCd);
            convPrtMdseBean.setMdseCd(mdseCd);
            convPrtMdseBean.setCusaVndCd(rs.getString(NLCB103001Constant.DB_CLMN_CUSA_VND_CD));
            NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(convPrtMdseBean);
            if (ZYPCommonFunc.hasValue(convPrtMdseBean.getErrCd())) {
                String[] errMsg = new String[] {NLCB103001Constant.PGM_ID_CONV_MDSE, convPrtMdseBean.getErrCd(), "INVTY_TRX_PK:" + invtyTrxPk + " MDSE_CD:" + mdseCd };
                sendMailForErrorInfo.addErrMsgWithLogOutput(NLCB103001Constant.NLCM0126W, errMsg);
                return Boolean.FALSE;
            }
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcPrtMdseCd, convPrtMdseBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(workTMsg.prtSizeTxt, convPrtMdseBean.getXtrnlSysSize());

            if (ZYPCommonFunc.hasValue(vndGlblCmpyCd)) {
                cincSplyGlblCmpyCd = vndGlblCmpyCd;
            } else {
                cincSplyGlblCmpyCd = glblCmpyCd;
            }

            prtChrgInd = onerousTypeOnerous;

            if (ZYPCommonFunc.hasValue(invtyTrxQty) && invtyTrxQty.compareTo(BigDecimal.ZERO) >= 0) {
                intfcStkInSgnTxt = NLCB103001Constant.STOCK_IN_SIGN_PLUS;
            } else {
                intfcStkInSgnTxt = NLCB103001Constant.STOCK_IN_SIGN_MINUS;
            }

            invtyTrxQty = invtyTrxQty.abs();

            ZYPEZDItemValueSetter.setValue(workTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(workTMsg.invtyTrxPk, invtyTrxPk);
            ZYPEZDItemValueSetter.setValue(workTMsg.cincGlblWhCd, cincGlblWhCd);
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcStkInDt, invtyTrxDt);
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcPoOrdNum, intfcPoOrdNum);
            // ZYPEZDItemValueSetter.setValue(workTMsg.cincSsNum , );
            ZYPEZDItemValueSetter.setValue(workTMsg.cincSplyGlblCmpyCd, cincSplyGlblCmpyCd);
            ZYPEZDItemValueSetter.setValue(workTMsg.cincSplyGlblWhCd, cincSplyGlblWhCd);
            ZYPEZDItemValueSetter.setValue(workTMsg.cincVndGlblCmpyCd, cincSplyGlblCmpyCd);
            // QC#30959 Update.
            if (ZYPCommonFunc.hasValue(vndCd)) {
                ZYPEZDItemValueSetter.setValue(workTMsg.intfcPrtVndCd, vndCd);
            }
            ZYPEZDItemValueSetter.setValue(workTMsg.cincGlblShpgMethCd, trdPtnrShpgMethCd);
            // QC#26966 Update.
            if (cincGlblWhCdOfCLUMBUS.equals(cincGlblWhCd) //
                    && stockInTypeStockIn.equals(cincStkInGlblCatgCd)) {
                ZYPEZDItemValueSetter.setValue(workTMsg.cincStkInGlblCatgCd, cincStkInGlblCatgCd);
            } else {
                ZYPEZDItemValueSetter.setValue(workTMsg.cincStkInGlblCatgCd, stockInTypeOther);
            }
            ZYPEZDItemValueSetter.setValue(workTMsg.cincPoGlblCatgCd, cincPoGlblCatgCd);
            ZYPEZDItemValueSetter.setValue(workTMsg.prtChrgInd, prtChrgInd);
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcStkInSgnTxt, intfcStkInSgnTxt);
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcStkInQty, invtyTrxQty);
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcCratDt, slsDt);
            ZYPEZDItemValueSetter.setValue(workTMsg.intfcProcFlg, ZYPConstant.FLG_OFF_N);

            return true;
        }

        /**
         *<pre>
         * Get Shipping Method Code for Po
         *</pre>
         * @param poOrdNum po Ord Num
         * @param poOrdDtlLineNum po Ord Dtl Line Num
         * @return Shipping Method Code
         */
        private Map<String, Object> getShpgMethCdForPo(final String poOrdNum, final String poOrdDtlLineNum) {

            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NLCB103001Constant.BIND_PO_ORD_NUM, poOrdNum);
            paramMap.put(NLCB103001Constant.BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
            paramMap.put(NLCB103001Constant.BIND_VND_CD, cincVndCd);
            paramMap.put(NLCB103001Constant.BIND_CARR_CD, cincCarrCd);
            paramMap.put(NLCB103001Constant.BIND_FRT_COND_CD, NLCB103001Constant.ASTERISK);
            paramMap.put(NLCB103001Constant.BIND_ROWNUM, NLCB103001Constant.NUM_ONE);

            return (Map<String, Object>) ssmBatchClient.queryObject("getShpgMethCdForPo", paramMap);
        }

        /**
         *<pre>
         * Get Shipping Method Code for So
         *</pre>
         * @param soNum So Num
         * @return Shipping Method Code
         */
        private Map<String, Object> getShpgMethCdForSo(final String soNum) {

            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NLCB103001Constant.BIND_SO_NUM, soNum);
            paramMap.put(NLCB103001Constant.BIND_VND_CD, cincVndCd);
            paramMap.put(NLCB103001Constant.BIND_CARR_CD, cincCarrCd);
            paramMap.put(NLCB103001Constant.BIND_FRT_COND_CD, NLCB103001Constant.ASTERISK);

            return (Map<String, Object>) ssmBatchClient.queryObject("getShpgMethCdForSo", paramMap);
        }

    }

    /**
     *<pre>
     * Get Delete Key
     *</pre>
     * @return List<PRT_STK_IN_RSLT_WRKTMsg>
     */
    private List<PRT_STK_IN_RSLT_WRKTMsg> getDeleteKey() {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(NLCB103001Constant.BIND_INTFC_CRAT_DT, prevDt);

        return (List<PRT_STK_IN_RSLT_WRKTMsg>) ssmBatchClient.queryObjectList("getDeleteKey", paramMap);
    }

    /**
     * <PRE>                                                                                                                                               
     * Delete Old Data (PRT_STK_IN_RSLT_WRK)
     * </PRE>
     * @param workTMsgList List<PRT_STK_IN_RSLT_WRKTMsg>
     * @return Delete count
     */
    private int deleteOldData(List<PRT_STK_IN_RSLT_WRKTMsg> workTMsgList) {

        for (PRT_STK_IN_RSLT_WRKTMsg workTMsg : workTMsgList) {
            PRT_STK_IN_RSLT_WRKTMsg tmpTMsg = (PRT_STK_IN_RSLT_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(workTMsg);
            if (tmpTMsg == null) {
                String[] errMsg = new String[] {NLCB103001Constant.ERR_TBL_PRT_STK_IN_RSLT_WRK };
                sendMailForErrorInfo.addErrMsgWithLogOutput(NLCB103001Constant.NLCM0131E, errMsg);
                return 0;
            }
        }
        int retValue = S21FastTBLAccessor.removePhysical(workTMsgList.toArray(new PRT_STK_IN_RSLT_WRKTMsg[0]));
        if (retValue != workTMsgList.size()) {
            String[] errMsg = new String[] {NLCB103001Constant.ERR_TBL_PRT_STK_IN_RSLT_WRK };
            sendMailForErrorInfo.addErrMsgWithLogOutput(NLCB103001Constant.NLCM0131E, errMsg);
            return 0;
        }
        return retValue;
    }

    /**
     * getCincGlblWhCd
     */
    private String getCincGlblWhCd(String rtlWhCd, String whOwnrCd, String locTpCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        String cincGlblWhCd = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (int i = 0; i < NLCB103001Constant.IDX_4; i++) {

            if (i == NLCB103001Constant.IDX_0) {
                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    // priority 1
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_RTL_WH_CD, rtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_1) {
                if (ZYPCommonFunc.hasValue(whOwnrCd)) {
                    // priority 2
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_WH_OWNR_CD, whOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_2) {
                if (ZYPCommonFunc.hasValue(locTpCd)) {
                    // priority 3
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_LOC_TP_CD, locTpCd);
                }
            }
            if (i == NLCB103001Constant.IDX_3 || !ZYPCommonFunc.hasValue(rtlWhCd)) {
                // other
                queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(NLCB103001Constant.BIND_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_LOC_TP_CD, NLCB103001Constant.ASTERISK);
            }

            cincGlblWhCd = (String) ssmBatchClient.queryObject("getCincGlblWhCd", queryParam, execParam);
            if (ZYPCommonFunc.hasValue(cincGlblWhCd)) {
                break;
            }
            queryParam.clear();
        }

        return cincGlblWhCd;
    }

    /**
     * getCincGlblCatgCd
     */
    private Map<String, Object> getCincGlblCatgCd(ResultSet rs) throws SQLException {

        Map<String, Object> cincGlblCatgCd = new HashMap<String, Object>();

        String fromRtlWhCd = rs.getString(NLCB103001Constant.DB_CLMN_FROM_RTL_WH_CD);
        String fromWhOwnrCd = rs.getString(NLCB103001Constant.DB_CLMN_FROM_WH_OWNR_CD);
        String fromLocTpCd = rs.getString(NLCB103001Constant.DB_CLMN_FROM_LOC_TP_CD);
        String toRtlWhCd = rs.getString(NLCB103001Constant.DB_CLMN_TO_RTL_WH_CD);
        String toWhOwnrCd = rs.getString(NLCB103001Constant.DB_CLMN_TO_WH_OWNR_CD);
        String toLocTpCd = rs.getString(NLCB103001Constant.DB_CLMN_TO_LOC_TP_CD);

        Map<String, Object> queryParam = new HashMap<String, Object>();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (int i = 0; i < NLCB103001Constant.IDX_10; i++) {
            if (i == NLCB103001Constant.IDX_0) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case A1
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, fromRtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, toRtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_1) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case A2
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, fromRtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, toWhOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_2) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case A3
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, fromRtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, toLocTpCd);
                }
            }
            if (i == NLCB103001Constant.IDX_3) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case B1
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, fromWhOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, toRtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_4) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case B2
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, fromWhOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, toWhOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_5) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case B3
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, fromWhOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, toLocTpCd);
                }
            }
            if (i == NLCB103001Constant.IDX_6) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case C1
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, fromLocTpCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, toRtlWhCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_7) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case C2
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, fromLocTpCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, toWhOwnrCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                }
            }
            if (i == NLCB103001Constant.IDX_8) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case C3
                    queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, fromLocTpCd);
                    queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                    queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, toLocTpCd);
                }
            }
            if (i == NLCB103001Constant.IDX_9 || !ZYPCommonFunc.hasValue(fromRtlWhCd)) {
                // other
                queryParam.put(NLCB103001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(NLCB103001Constant.BIND_FROM_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_FROM_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_FROM_LOC_TP_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_TO_RTL_WH_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_TO_WH_OWNR_CD, NLCB103001Constant.ASTERISK);
                queryParam.put(NLCB103001Constant.BIND_TO_LOC_TP_CD, NLCB103001Constant.ASTERISK);
            }
            cincGlblCatgCd = (Map<String, Object>) ssmBatchClient.queryObject("getCincGlblCatgCd", queryParam, execParam);
            if (cincGlblCatgCd != null) {
                String catgCd = (String) cincGlblCatgCd.get(NLCB103001Constant.DB_CLMN_CINC_PO_GLBL_CATG_CD);
                if (ZYPCommonFunc.hasValue(catgCd)) {
                    break;
                }
            }

            queryParam.clear();
        }

        return cincGlblCatgCd;
    }

    /**
     * Check Target Location
     * @param locCd String
     * @param locTpCd String
     * @return boolean
     */
    private boolean isTargetLoc(String locCd, String locTpCd) {

        if (LOC_TP.TECHNICIAN.equals(locTpCd)) {

            if (ZYPConstant.FLG_ON_Y.equals(inclTechInvtyFlg)) {

                return true;
            }

            return false;
        }

        if (prtExclInvtyLocCdCincMap.containsKey(locCd)) {

            return false;
        }

        return true;
    }

    /**
     * Check Target Global WH
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean isTargetGlobalWH(ResultSet rs, String fromGlobalWhCd, String toGlobalWhCd) throws SQLException {

        String valTxt5 = rs.getString(NLCB103001Constant.DB_CLMN_VAL_TXT_05);

        if ((ZYPConstant.FLG_ON_1.equals(valTxt5) && !fromGlobalWhCd.equals(toGlobalWhCd)
                || (NLCB103001Constant.STR_2.equals(valTxt5) && fromGlobalWhCd.equals(toGlobalWhCd)))) {

            return true;

        } else {

            return false;

        }

    }

    /**
     * Get Inventory Location Code of Out of Target
     * @param ket String
     * @return Map<String, String>
     */
    private Map<String, String> getVarCharConstMapData(String key) {

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(key, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(varCharConstVal)) {

            return new HashMap<String, String>();
        }

        String[] itemArray = varCharConstVal.split(NLCB103001Constant.COMMA);
        Map<String, String> rtnMap = new HashMap<String, String>();

        for (String item : itemArray) {

            if (!ZYPCommonFunc.hasValue(item)) {

                continue;
            }

            rtnMap.put(item, item);
        }

        return rtnMap;
    }

}
