/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB238001;

import static com.canon.cusa.s21.batch.NWA.NWAB238001.NWAB238001Constant.*;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.INTFC_BO_WRKTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RCPT_CMPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21DateManagement;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NWAB238001 Customer Back Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 2013/10/07   Fujitsu         A.Suda          Create          MEX-IF013
 * 2013/11/22   Fujitsu         A.Suda          Update          Defect#3161
 * 2013/12/16   CSAI            Y.Imazu         Update          QC3258
 * 2016/06/07   CITS            N.Akaishi       Update          V1.0
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 09/05/2018   CITS            T.Tokutomi      Update          QC#27327
 * 03/28/2019   CITS            T.Tokutomi      Update          QC#30962
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 04/11/2019   CITS            T.Tokutomi      Update          QC#31175
 * 2020/02/04   Fujitsu         K.Kato          Update          QC#55572
 *</pre>
 */
public class NWAB238001 extends S21BatchMain {

    // -- Variable --------------
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batchProcDt = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Total count */
    private int totalCount = 0;

    /** Normal count */
    private int normalRecCnt = 0;

    /** Error count */
    private int errRecCnt = 0;

    /** error Flag */
    private boolean errFlg = false;

    /** output Log List */
    private List<String> outPutLogList = new ArrayList<String>();

    /** Term code */
    private TERM_CD termCd = null;

    /** BO Status List */
    private List<String> boStatusList = null;

    /** Rsvd BO Status List */
    private List<String> rsvdBoStatusList = null;

    /** Cinc Glbl WH Code */
    private String cincGlblWhCd = null;

    /** Cinc Vnd Code */
    private String cincVndCd = null;

    /** Carr Code */
    private String carrCd = null;

    /** Category Code for A1 */
    private String catgA1Cd = null;

    /** Parts Charge Indicator */
    private String partsChrgIndComp = null;

    /** Parts Charge Indicator */
    private String partsChrgIndNoComp = null;

    /** Parts Emergency Order Indicator */
    private String prtEmerOrdInd = null;

    /** Parts Include Tech Inventory Cinc Flg */
    private String prtInclTechInvtyCincFlg = null;

    /** Parts Exclude Inventory Location Code */
    private List<String> prtExclInvtyLocCdCincList = null;

    /** Category Code for ZZ */
    private String catgZZCd = null;

    /** ShpgMeth for 99 */
    private String shpgMeth99 = null;

    /** S-CUBE Vendor Type Code List */
    private List<String> scubeVndSysTpCdList = null;

    // 2020/02/04 QC#55572 Add Start
    /** S-CUBE Exclude Sub Warehouse Code List */
    private List<String> scubeExclSwhCdList = null;
    // 2020/02/04 QC#55572 Add End

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {

        // Initialization S21BatchMain
        new NWAB238001().executeBatch(NWAB238001.class.getSimpleName());
    }

    /**
     * <pre>
     * Initialization routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    protected void initRoutine() {

        // Initialization
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        termCd = TERM_CD.NORMAL_END;

        if (!isGlobalCompanyCode()) {

            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Global Company Code" });
        }

        if (!getBatProcDate()) {

            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Batch Process Date" });
        }
    }

    /**
     * <pre>
     * Main routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#mainRoutine()
     */
    protected void mainRoutine() {

        // Delete Work Date of Batch Date (For Rerun)
        removeIntfcBoWrkOfBctDate();

        // getOrder
        executeBackOrderAndInsert();

        // Delete
        removeIntfcBoWrk();

    }

    /**
     * <pre>
     * Term routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
     */
    protected void termRoutine() {

        // output Log
        for (String errorMsg : outPutLogList) {

            S21InfoLogOutput.println(errorMsg);
        }

        // post error mail.
        if (outPutLogList.size() > 0) {

            if (errFlg) {

                termCd = TERM_CD.WARNING_END;
            }

            NWXC100001SendMailForErrorInfo errMailCtrl = new NWXC100001SendMailForErrorInfo();
            errMailCtrl.addErrMsgList(outPutLogList);
            String retValue = errMailCtrl.sendMail(glblCmpyCd, NWAB238001.class.getSimpleName());

            if (ZYPCommonFunc.hasValue(retValue)) {

                termCd = TERM_CD.ABNORMAL_END;
            }
        }

        // Set term code and total commit count.
        setTermState(termCd, normalRecCnt, errRecCnt, totalCount);
    }

    private boolean isGlobalCompanyCode() {

        glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            return false;
        }

        return true;
    }

    private boolean getBatProcDate() {

        batchProcDt = S21DateManagement.getBatProcDate(glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(batchProcDt)) {

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Deletes all the records of the INTFC_BO_WRK table of Batch Date.
     * </pre>
     */
    private void removeIntfcBoWrkOfBctDate() {

        INTFC_BO_WRKTMsg wrkTMsg = new INTFC_BO_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wrkTMsg.intfcCratDt, batchProcDt);

        int rsltCnt = S21FastTBLAccessor.removeByPartialValue(wrkTMsg, new String[] {BIND_GLBL_CMPY_CD, BIND_INTFC_CRAT_DT});
        final String rtnCd = wrkTMsg.getReturnCode();

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !EZDTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {

            throw new S21AbendException(MSG_ID_NWAM0221E, new String[] {"Delete", INTFC_BO_WRK, rtnCd });
        }

        commit();

        S21InfoLogOutput.println("------------------------------------------");
        S21InfoLogOutput.println("INTFC_BO_WRK Delete Count: " + rsltCnt);
        S21InfoLogOutput.println("------------------------------------------");
    }

    /**
     * <pre>
     * Deletes all the records of the INTFC_BO_WRK table.
     * </pre>
     */
    private void removeIntfcBoWrk() {

        Map<String, String> param = new HashMap<String, String>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_BATCH_PROC_DT, batchProcDt);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<Map<String, Object>> removeDataList = ssmBatchClient.queryObjectList(STMT_GET_REMOVE_INTFC_BO_INFO, param, execParam);

        int totCnt = 0;

        if (!isEmpty(removeDataList)) {

            for (Map<String, Object> removeData : removeDataList) {

                String wrkCratDt = (String) removeData.get(INTFC_CRAT_DT);
                INTFC_BO_WRKTMsg wrkTMsg = new INTFC_BO_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wrkTMsg.intfcCratDt, wrkCratDt);

                int rsltCnt = S21FastTBLAccessor.removeByPartialValue(wrkTMsg, new String[] {BIND_GLBL_CMPY_CD, BIND_INTFC_CRAT_DT });
                final String rtnCd = wrkTMsg.getReturnCode();

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !EZDTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {

                    throw new S21AbendException(MSG_ID_NWAM0221E, new String[] {"Delete", INTFC_BO_WRK, rtnCd });
                }

                totCnt = totCnt + rsltCnt;
            }

            commit();
        }

        S21InfoLogOutput.println("------------------------------------------");
        S21InfoLogOutput.println("INTFC_BO_WRK Delete Count: " + totCnt);
        S21InfoLogOutput.println("------------------------------------------");
    }

    private boolean getVarCharConstValue() {

        List<String> tempboStatusList = getVarCharConstValueList(VAR_CHAR_CONST_KEY_BO_STATUS);
        List<String> temprsvdBoStatusList = getVarCharConstValueList(VAR_CHAR_CONST_KEY_RSVD_BO_STATUS);
        List<String> tempcincGlblWhCd = getVarCharConstValueList(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD);
        List<String> tempcincVndCd = getVarCharConstValueList(VAR_CHAR_CONST_KEY_CINC_VND_CD);
        List<String> tempcarrCd = getVarCharConstValueList(VAR_CHAR_CONST_KEY_CARR_CD);
        List<String> tempcatgCdA1 = getVarCharConstValueList(VAR_CHAR_CONST_KEY_CATG_CD_A1);
        List<String> temppartsChrgIndComp = getVarCharConstValueList(VAR_CHAR_CONST_KEY_PRT_CHRG_IND_COMP);
        List<String> temppartsChrgIndNoComp = getVarCharConstValueList(VAR_CHAR_CONST_KEY_PRT_CHRG_IND_NO_COMP);
        List<String> tempprtEmerOrdInd = getVarCharConstValueList(VAR_CHAR_CONST_KEY_PRT_EMER_ORD_IND_NO);
        List<String> tempprtInclTechCincFlg = getVarCharConstValueList(VAR_CHAR_CONST_KEY_PRT_INCL_TECH_INVTY_CINC_FLG);
        List<String> tempprtExcInvtyLocCdList = getVarCharConstValueList(VAR_CHAR_CONST_KEY_PRT_EXCL_INVTY_LOC_CD_CINC);
        List<String> tempcatgCdZZ = getVarCharConstValueList(VAR_CHAR_CONST_KEY_CATG_CD_ZZ);
        List<String> tempShpgMeth99 = getVarCharConstValueList(VAR_CHAR_CONST_KEY_SHPG_METH_OTHER);
        List<String> tempScubeVndSysTpCdList = getVarCharConstValueList(VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD);
        // 2020/02/04 QC#55572 Add Start
        List<String> tempScubeExclSwhCdList = getVarCharConstValueList(VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST);
        // 2020/02/04 QC#55572 Add End

        boolean hasVarCharboStatusFlg = hasVarCharConstValue(tempboStatusList, VAR_CHAR_CONST_KEY_BO_STATUS);
        boolean hasVarCharrsvdBoStatusFlg = hasVarCharConstValue(temprsvdBoStatusList, VAR_CHAR_CONST_KEY_RSVD_BO_STATUS);
        boolean hasVarCharcincGlblWhCdFlg = hasVarCharConstValue(tempcincGlblWhCd, VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD);
        boolean hasVarCharcincVndCdFlg = hasVarCharConstValue(tempcincVndCd, VAR_CHAR_CONST_KEY_CINC_VND_CD);
        boolean hasVarCharcarrCdFlg = hasVarCharConstValue(tempcarrCd, VAR_CHAR_CONST_KEY_CARR_CD);
        boolean hasVarCharcatgCdA1Flg = hasVarCharConstValue(tempcatgCdA1, VAR_CHAR_CONST_KEY_CATG_CD_A1);
        boolean hasVarCharPartsCompFlg = hasVarCharConstValue(temppartsChrgIndComp, VAR_CHAR_CONST_KEY_PRT_CHRG_IND_COMP);
        boolean hasVarCharPartsNoCompFlg = hasVarCharConstValue(temppartsChrgIndNoComp, VAR_CHAR_CONST_KEY_PRT_CHRG_IND_NO_COMP);
        boolean hasVarCharPrtEmerOrdIndFlg = hasVarCharConstValue(tempprtEmerOrdInd, VAR_CHAR_CONST_KEY_PRT_EMER_ORD_IND_NO);
        boolean hasVarCharTechInvtyCincFlg = hasVarCharConstValue(tempprtInclTechCincFlg, VAR_CHAR_CONST_KEY_PRT_INCL_TECH_INVTY_CINC_FLG);
        boolean hasVarCharcatgCdZZFlg = hasVarCharConstValue(tempcatgCdZZ, VAR_CHAR_CONST_KEY_CATG_CD_ZZ);
        boolean hasVarShpgMeth99Flg = hasVarCharConstValue(tempShpgMeth99, VAR_CHAR_CONST_KEY_SHPG_METH_OTHER);

        if (!hasVarCharboStatusFlg) {
            return false;
        }
        if (!hasVarCharrsvdBoStatusFlg) {
            return false;
        }
        if (!hasVarCharcincGlblWhCdFlg) {
            return false;
        }
        if (!hasVarCharcincVndCdFlg) {
            return false;
        }
        if (!hasVarCharcarrCdFlg) {
            return false;
        }
        if (!hasVarCharcatgCdA1Flg) {
            return false;
        }
        if (!hasVarCharPartsCompFlg) {
            return false;
        }
        if (!hasVarCharPartsNoCompFlg) {
            return false;
        }
        if (!hasVarCharPrtEmerOrdIndFlg) {
            return false;
        }

        if (!hasVarCharTechInvtyCincFlg) {
            return false;
        }
        if (!hasVarCharcatgCdZZFlg) {
            return false;
        }
        if (!hasVarShpgMeth99Flg) {
            return false;
        }

        boStatusList = tempboStatusList;
        rsvdBoStatusList = temprsvdBoStatusList;
        cincGlblWhCd = tempcincGlblWhCd.get(0);
        cincVndCd = tempcincVndCd.get(0);
        carrCd = tempcarrCd.get(0);
        catgA1Cd = tempcatgCdA1.get(0);
        partsChrgIndComp = temppartsChrgIndComp.get(0);
        partsChrgIndNoComp = temppartsChrgIndNoComp.get(0);
        prtEmerOrdInd = tempprtEmerOrdInd.get(0);
        scubeVndSysTpCdList = tempScubeVndSysTpCdList;

        if (!ZYPConstant.FLG_OFF_N.equals(tempprtInclTechCincFlg.get(0)) && !ZYPConstant.FLG_ON_Y.equals(tempprtInclTechCincFlg.get(0))) {
            String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0300E, new String[] {VAR_CHAR_CONST_KEY_PRT_INCL_TECH_INVTY_CINC_FLG });
            errFlg = true;
            outPutLogList.add(errMsg);
            return false;
        }
        prtInclTechInvtyCincFlg = tempprtInclTechCincFlg.get(0);
        prtExclInvtyLocCdCincList = tempprtExcInvtyLocCdList;
        catgZZCd = tempcatgCdZZ.get(0);
        shpgMeth99 = tempShpgMeth99.get(0);

        // 2020/02/04 QC#55572 Add Start
        scubeExclSwhCdList = tempScubeExclSwhCdList;
        // 2020/02/04 QC#55572 Add End
        
        return true;
    }

    /**
     * <pre>
     * Acquire information to insert Interface Back Order Work.
     * </pre>
     */
    private void executeBackOrderAndInsert() {

        if (!getVarCharConstValue()) {

            return;
        }

        // Check Work
        boolean hasWorkData = isWorkData();

        // Get Back Order
        // Stock Out
        insertToWrk(getStockOutBoList(), hasWorkData, TRX_TP_STOCK_OUT);

        // Return
        insertToWrk(getReturnBoList(), hasWorkData, TRX_TP_RETUEN);

        // WH Transfer
        insertToWrk(getWHTransferList(), hasWorkData, TRX_TP_WHTRANSFER);

        // Tech Transfer
        insertToWrk(getTechTransferList(), hasWorkData, TRX_TP_TECHTRANSFER);

        // Disposal
        insertToWrk(getDisposalBoList(), hasWorkData, TRX_TP_DISPOSAL);

        // Item Change
        insertToWrk(getItemChangeBoList(), hasWorkData, TRX_TP_ITEMCHANGE);

        // Work Order (Stock Out)
        insertToWrk(getWorkOrderStockOutBoList(), hasWorkData, TRX_TP_WORKORDER_STOCK_OUT);

        // Work Order (Stock In)
        insertToWrk(getWorkOrderStockInBoList(), hasWorkData, TRX_TP_WORKORDER_STOCK_IN);
    }

    private List<Map<String, Object>> getStockOutBoList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<String> allBoStsList = new ArrayList<String>();
        allBoStsList.addAll(boStatusList);
        allBoStsList.addAll(rsvdBoStatusList);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_BO_STS_CD_LIST, boStatusList);
        param.put(BIND_BO_RSVD_STS_CD_LIST, rsvdBoStatusList);
        param.put(BIND_ALL_BO_STS_CD_LIST, allBoStsList);

        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);

        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, this.shpgMeth99);
        param.put(BIND_DS_COND_CONST_GRP_ID_WHT_DS_ORD_TP, DS_COND_CONST_KEY_SCUBE_WHT_DS_ORD_TP);
        param.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);

        if (ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {
            param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        } else {
            param.put(BIND_TECHNICIAN, null);
        }

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_BO_INFO, param, execParam);
    }

    private List<Map<String, Object>> getReturnBoList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);

        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);

        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_RTRN_LINE_STS_CD, PO_LINE_STS.CLOSED);
        param.put(BIND_SUBMIT_FLG, ZYPConstant.FLG_ON_Y);

        if (ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {
            param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        } else {
            param.put(BIND_TECHNICIAN, null);
        }

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_RETURN_INFO, param, execParam);
    }

    private List<Map<String, Object>> getDisposalBoList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<String> invtyOrdTpList = new ArrayList<String>();
        invtyOrdTpList.add(INVTY_ORD_TP.DISPOSAL);
        invtyOrdTpList.add(INVTY_ORD_TP.WRITE_OFF);
        invtyOrdTpList.add(INVTY_ORD_TP.SUBCONTRACT);
        invtyOrdTpList.add(INVTY_ORD_TP.REFURBISH_OUT);

        List<String> invtyOrdDtlStsList = new ArrayList<String>();
        invtyOrdDtlStsList.add(INVTY_ORD_STS.CLOSED);
        invtyOrdDtlStsList.add(INVTY_ORD_STS.CANCEL);

        List<String> invtyOrdDtlStsBoList = new ArrayList<String>();
        invtyOrdDtlStsBoList.add(INVTY_ORD_STS.OPEN);
        invtyOrdDtlStsBoList.add(INVTY_ORD_STS.FINALIZED);
        invtyOrdDtlStsBoList.add(INVTY_ORD_STS.INTERNAL_APPROVED);

        List<String> invtyOrdDtlStsRsvdBoList = new ArrayList<String>();
        invtyOrdDtlStsRsvdBoList.add(INVTY_ORD_STS.APPROVED);
        invtyOrdDtlStsRsvdBoList.add(INVTY_ORD_STS.PRINTED);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);

        if (ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {
            param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        } else {
            param.put(BIND_TECHNICIAN, null);
        }

        param.put(BIND_INVTY_ORD_DTL_STS_LIST, invtyOrdDtlStsList);
        param.put(BIND_INVTY_ORD_DTL_STS_BO_LIST, invtyOrdDtlStsBoList);
        param.put(BIND_INVTY_ORD_DTL_STS_RSVD_BO_LIST, invtyOrdDtlStsRsvdBoList);
        param.put(BIND_INVTY_ORD_TP_LIST, invtyOrdTpList);

        param.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, shpgMeth99);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_DISPOSAL_INFO, param, execParam);
    }

    private List<Map<String, Object>> getItemChangeBoList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);

        param.put(BIND_INVTY_ORD_TP_ITM_CHG, INVTY_ORD_TP.ITEM_CHANGE);
        param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_ITEM_CHANGE_INFO, param, execParam);
    }

    private List<Map<String, Object>> getWorkOrderStockOutBoList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<String> workOrderStsList = new ArrayList<String>();
        workOrderStsList.add(WRK_ORD_STS.HARD_ALLOCATED);
        workOrderStsList.add(WRK_ORD_STS.VALIDATED);
        workOrderStsList.add(WRK_ORD_STS.SHIPPED);
        workOrderStsList.add(WRK_ORD_STS.RECEIVING);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, this.shpgMeth99);

        param.put(BIND_WORK_ORDER_STS_LIST, workOrderStsList);
        param.put(BIND_VND_CD, cincVndCd);
        param.put(BIND_CARR_CD, ASTERISK);
        param.put(BIND_FRT_COND_CD, ASTERISK);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_WORK_ORDER_STOCK_OUT_INFO, param, execParam);
    }

    private List<Map<String, Object>> getWorkOrderStockInBoList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<String> workOrderStsList = new ArrayList<String>();
        workOrderStsList.add(WRK_ORD_STS.HARD_ALLOCATED);
        workOrderStsList.add(WRK_ORD_STS.VALIDATED);
        workOrderStsList.add(WRK_ORD_STS.SHIPPED);
        workOrderStsList.add(WRK_ORD_STS.RECEIVING);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, this.shpgMeth99);

        param.put(BIND_WORK_ORDER_STS_LIST, workOrderStsList);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_WORK_ORDER_STOCK_IN_INFO, param, execParam);
    }

    private List<Map<String, Object>> getWHTransferList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<String> allBoStsList = new ArrayList<String>();
        allBoStsList.addAll(boStatusList);
        allBoStsList.addAll(rsvdBoStatusList);

        // QC#27327 Delete.
//        if (ZYPConstant.FLG_ON_Y.equals(prtInclTechInvtyCincFlg) && isEmpty(prtExclInvtyLocCdCincList)) {
//            return new ArrayList<Map<String, Object>>();
//        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_BO_STS_CD_LIST, boStatusList);
        param.put(BIND_BO_RSVD_STS_CD_LIST, rsvdBoStatusList);
        param.put(BIND_ALL_BO_STS_CD_LIST, allBoStsList);

        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);

        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, this.shpgMeth99);
        param.put(BIND_DS_COND_CONST_GRP_ID_WHT_DS_ORD_TP, DS_COND_CONST_KEY_SCUBE_WHT_DS_ORD_TP);
        param.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);
        param.put(BIND_SUBMIT_FLG, ZYPConstant.FLG_ON_Y);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_WH_TRANSFER_INFO, param, execParam);
    }

    private List<Map<String, Object>> getTechTransferList() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST, prtExclInvtyLocCdCincList);
        param.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, prtInclTechInvtyCincFlg);

        param.put(BIND_VND_SYS_TP_CD_LIST, this.scubeVndSysTpCdList);
        param.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        param.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);

        param.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        param.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);

        param.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        param.put(BIND_IDX_1, IDX_1);
        param.put(BIND_INVTY_ORD_TP_DC_TRANSFER, INVTY_ORD_TP.DC_TRANSFER);
        param.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);
        param.put(BIND_SUBMIT_FLG, ZYPConstant.FLG_ON_Y);

        param.put(BIND_INVTY_ORD_STS_APPROVED, INVTY_ORD_STS.APPROVED);
        param.put(BIND_SHPG_PLN_N, ZYPConstant.FLG_OFF_N);
        param.put(BIND_TRX_LINE_SUB_NUM, VAL_001);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            param.put("exclMdseCdList", exclMdseList);
        }

        // 2020/02/04 QC#55572 Add Start
        if (scubeExclSwhCdList != null) {
            param.put(BIND_SCUBE_EXCL_SWH_CD_LIST, scubeExclSwhCdList);
        }
        // 2020/02/04 QC#55572 Add End

        // Execute Search BackOrder.
        return ssmBatchClient.queryObjectList(STMT_GET_TECH_TRANSFER_INFO, param, execParam);
    }

    private void insertToWrk(List<Map<String, Object>> insertDataList, boolean hasWorkData, String trxTp) {

        // Insert
        List<INTFC_BO_WRKTMsg> boTmsgList = new ArrayList<INTFC_BO_WRKTMsg>();
        String doneOrderNum = "";
        boolean errorFlg = false;

        // QC#26966 Update
        String cincGlblWhCdClumbus = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cincGlblWhCdClumbus)) {
            cincGlblWhCdClumbus = CINC_GLBL_WH_CD_CLMBUS_DEFAULT;
        }


        for (Map<String, Object> boData : insertDataList) {

            INTFC_BO_WRKTMsg boTmsg = null;

            totalCount++;
            String ordNum = (String) boData.get(ORD_NUM);

            if (!ordNum.equals(doneOrderNum)) {

                if (errorFlg) {

                    errorFlg = false;

                } else {

                    if (boTmsgList.size() > 0) {

                        int insListCnt = boTmsgList.size();
                        int rsltCnt = S21FastTBLAccessor.insert(boTmsgList.toArray(new INTFC_BO_WRKTMsg[0]));

                        if (insListCnt == rsltCnt) {

                            commit();
                            normalRecCnt = normalRecCnt + insListCnt;

                        } else {

                            rollback();
                            String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0546E, new String[] {"INTFC_BO_WRK, Key OrdNum:" + doneOrderNum });
                            outPutLogList.add(errMsg);
                            errFlg = true;
                        }

                        boTmsgList.clear();
                    }
                }

                boTmsgList.clear();
                doneOrderNum = ordNum;

            } else {

                if (errorFlg) {

                    continue;
                }
            }

            // Set Data
            if (TRX_TP_STOCK_OUT.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForStockOut(boData);

            } else if (TRX_TP_RETUEN.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForReturn(boData);

            } else if (TRX_TP_WHTRANSFER.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForWHTrans(boData, TRX_TP_WHTRANSFER);

            } else if (TRX_TP_TECHTRANSFER.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForTechTrans(boData, TRX_TP_TECHTRANSFER);

            } else if (TRX_TP_DISPOSAL.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForDisposal(boData);

            } else if (TRX_TP_ITEMCHANGE.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForItemChange(boData);

            } else if (TRX_TP_WORKORDER_STOCK_OUT.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForWorkOrd(boData, TRX_TP_WORKORDER_STOCK_OUT);

            } else if (TRX_TP_WORKORDER_STOCK_IN.equals(trxTp)) {

                boTmsg = setIntfcBoWrkForWorkOrd(boData, TRX_TP_WORKORDER_STOCK_IN);
            }

            if (null == boTmsg) {

                errorFlg = true;
                boTmsgList.clear();
                rollback();
                continue;
            }

            // QC#26966 Update.
            if (!cincGlblWhCdClumbus.equals(boTmsg.cincGlblWhCd.getValue()) //
                    && catgA1Cd.equals(boTmsg.cincGlblOrdCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, CINC_PO_GLBL_CATG_CD_BZ);
            }

            if (chkBoQty(boTmsg)) {

                if (!hasWorkData) {

                    boolean scubeSendFlg = true;

                    if (TRX_TP_WHTRANSFER.equals(trxTp)) {
                        scubeSendFlg = chkTargetGlblWhCd(boTmsg);
                    }

                    if (scubeSendFlg) {
                        boTmsgList.add(boTmsg);
                    }

                } else {

                    if (isDuplicationData(boTmsg)) {

                        String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0551W, new String[] {"OrdNum: " + boTmsg.intfcCpoOrdNum.getValue() + ",PrtMdseCd: " + boTmsg.intfcPrtMdseCd.getValue() + ",prtSizeTxt: "
                                + boTmsg.prtSizeTxt.getValue() + ",CincGlblShpgMethCd: " + boTmsg.cincGlblShpgMethCd.getValue() + ",IntfcCratDt: " + boTmsg.intfcCratDt.getValue() });
                        outPutLogList.add(errMsg);
                        normalRecCnt++;

                    } else {

                        boolean scubeSendFlg = true;

                        if (TRX_TP_WHTRANSFER.equals(trxTp)) {
                            scubeSendFlg = chkTargetGlblWhCd(boTmsg);
                        }

                        if (scubeSendFlg) {
                            boTmsgList.add(boTmsg);
                        }
                    }
                }
            }
        }

        if (boTmsgList.size() > 0) {

            int insListCnt = boTmsgList.size();
            int rsltCnt = S21FastTBLAccessor.insert(boTmsgList.toArray(new INTFC_BO_WRKTMsg[0]));
            boTmsgList.clear();

            if (insListCnt == rsltCnt) {

                commit();
                normalRecCnt = normalRecCnt + insListCnt;

            } else {

                rollback();
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0546E, new String[] {"INTFC_BO_WRK, Key OrdNum:" + doneOrderNum });
                outPutLogList.add(errMsg);
                errFlg = true;
            }
        }

        errRecCnt = totalCount - normalRecCnt;
    }

    private static boolean chkBoQty(INTFC_BO_WRKTMsg boTmsg) {

        if (BigDecimal.ZERO.compareTo(boTmsg.intfcBoQty.getValue()) == 0 && BigDecimal.ZERO.compareTo(boTmsg.intfcRsvdBoQty.getValue()) == 0) {

            return false;
        }

        return true;
    }

    private static boolean chkTargetGlblWhCd(INTFC_BO_WRKTMsg boTmsg) {

        boolean scubeSendFlg = true;
        if (ZYPCommonFunc.hasValue(boTmsg.cincGlblWhCd) && ZYPCommonFunc.hasValue(boTmsg.cincRcvGlblWhCd)) {
            if (boTmsg.cincGlblWhCd.getValue().equals(boTmsg.cincRcvGlblWhCd.getValue())) {
                scubeSendFlg = false;
            }
        }

        return scubeSendFlg;
    }

    private List<String> getVarCharConstValueList(String varCharConstNm) {

        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(varCharConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);

        varCharConstTMsg = (VAR_CHAR_CONSTTMsg) S21FastTBLAccessor.findByKey(varCharConstTMsg);

        if (null == varCharConstTMsg) {

            return null;

        } else {

            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();

            if (!ZYPCommonFunc.hasValue(varCharConstVal)) {

                return null;

            } else {

                return asList(varCharConstVal.split(","));
            }
        }
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForWorkOrd(Map<String, Object> boData, String trxTp) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, (String) boData.get(WRK_ORD_DT));

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.INTERNAL);
        if (TRX_TP_WORKORDER_STOCK_OUT.equals(trxTp)) {

            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(FROM_RTL_WH_CD), (String) boData.get(FROM_WH_OWNR_CD), (String) boData.get(FROM_INVTY_LOC_TP_CD)));

            ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(SELL_TO_CUST_CD));

            // QC#31175 Delete cincRcvGlblWhCd.
            String dsWrkOrdTp = (String) boData.get(DS_WRK_ORD_TP_CD);

            // Get Parts Mdse Code
            NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(ORIG_GOODS_MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
            if (null == bean) {
                return null;
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
                ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
            }
            if (ZYPCommonFunc.hasValue((String) boData.get(TRD_PTNR_SHPG_METH_CD))) {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, (String) boData.get(TRD_PTNR_SHPG_METH_CD));
                if (shpgMeth99.equals(boTmsg.cincGlblShpgMethCd.getValue())) {
                    String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                            "ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd,
                            "CpoOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd });
                    errFlg = true;
                    outPutLogList.add(errMsg);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                        "ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd,
                        "CpoOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd });
                errFlg = true;
                outPutLogList.add(errMsg);
            }
            // Qty
            if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_ORD_QTY))) {
                BigDecimal bigOrdQty = (BigDecimal) boData.get(ORIG_ORD_QTY);
                // QC#31175 Update.
                if (DS_WRK_ORD_TP.KIT.equals(dsWrkOrdTp)) {
                    if (bigOrdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);

                    }
                } else {
                    // Un-Kit
                    if (bigOrdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    }
                }
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(TOT_ORD_QTY));
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);

            }

            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);

            if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_BAL_QTY))) {
                BigDecimal bigRsvdQty = (BigDecimal) boData.get(ORIG_BAL_QTY);
                // QC#31175 Update.
                if (DS_WRK_ORD_TP.KIT.equals(dsWrkOrdTp)) {
                    if (bigRsvdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);

                    }
                } else {
                    if (bigRsvdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    }
                }
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(TOT_BAL_QTY));
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);

            }

        } else if (TRX_TP_WORKORDER_STOCK_IN.equals(trxTp)) {

            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, this.cincGlblWhCd);

            // QC#31175 Delete cincRcvGlblWhCd.
            String dsWrkOrdTp = (String) boData.get(DS_WRK_ORD_TP_CD);

            ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(VND_CD));
            // Get Parts Mdse Code
            NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(FNSH_GOODS_MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
            if (null == bean) {
                return null;
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
                ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);

            // Qty
            if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_ORD_QTY))) {
                BigDecimal bigOrdQty = (BigDecimal) boData.get(ORIG_ORD_QTY);
                // QC#31175 Update.
                if (DS_WRK_ORD_TP.KIT.equals(dsWrkOrdTp)) {
                    if (bigOrdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    }
                } else {
                    // Un-Kit
                    if (bigOrdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);

                    }
                }
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(TOT_ORD_QTY));
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);

            }

            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);

            if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_BAL_QTY))) {
                BigDecimal bigRsvdQty = (BigDecimal) boData.get(ORIG_BAL_QTY);
                // QC#31175 Update.
                if (DS_WRK_ORD_TP.KIT.equals(dsWrkOrdTp)) {
                    if (bigRsvdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    }
                } else {
                    // Un-Kit
                    if (bigRsvdQty.compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);

                    }
                }
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(TOT_BAL_QTY));
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);

            }
        }

        if (ZYPCommonFunc.hasValue((String) boData.get(RQST_RCV_DT))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(RQST_RCV_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, getBusinessDate((String) boData.get(WRK_ORD_DT), ADD_DATE_1));
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, catgZZCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.prtChrgInd, partsChrgIndComp);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        return boTmsg;
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForItemChange(Map<String, Object> boData) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(FROM_RTL_WH_CD), (String) boData.get(FROM_WH_OWNR_CD), (String) boData.get(FROM_INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.INTERNAL);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(SELL_TO_CUST_CD));

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
        if (null == bean) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_ORD_QTY))) {
            BigDecimal bigordQty = (BigDecimal) boData.get(ORIG_ORD_QTY);
            if (bigordQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(TOT_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(TOT_ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);

        }

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);

        String intfcCpoOrdDt = ZYPDateUtil.DateFormatter((String) boData.get(CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, intfcCpoOrdDt);
        if (ZYPCommonFunc.hasValue((String) boData.get(RSD_DT))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(RSD_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, getBusinessDate(intfcCpoOrdDt, ADD_DATE_1));
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, catgZZCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.prtChrgInd, partsChrgIndComp);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        if (!ZYPCommonFunc.hasValue((String) boData.get(SHPG_SVC_LVL_CD))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
        } else {
            String trdPtnrShpgMethCd = getInvtyOrdTrdPtnrShpgMethCd((String) boData.get(SHPG_SVC_LVL_CD));
            if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                        "ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd,
                        "InvtyOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd });
                errFlg = true;
                outPutLogList.add(errMsg);
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, trdPtnrShpgMethCd);
            }
        }

        return boTmsg;
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForDisposal(Map<String, Object> boData) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(FROM_RTL_WH_CD), (String) boData.get(FROM_WH_OWNR_CD), (String) boData.get(FROM_INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.INTERNAL);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(SELL_TO_CUST_CD));

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
        if (null == bean) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
        }

        if (ZYPCommonFunc.hasValue((String) boData.get(SHPG_SVC_LVL_CD))) {
            String trdPtnrShpgMethCd = getInvtyOrdTrdPtnrShpgMethCd((String) boData.get(SHPG_SVC_LVL_CD));
            if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                        ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd,
                        "InvtyOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd });
                errFlg = true;
                outPutLogList.add(errMsg);
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, trdPtnrShpgMethCd);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
        }

        BigDecimal bigboQty = BigDecimal.ZERO;
        BigDecimal bigrsvdQty = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_BO_ORD_QTY))) {
            bigboQty = (BigDecimal) boData.get(ORIG_BO_ORD_QTY);
            if (bigboQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, (BigDecimal) boData.get(TOT_BO_ORD_QTY));

        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_RSVD_ORD_QTY))) {
            bigrsvdQty = (BigDecimal) boData.get(ORIG_RSVD_ORD_QTY);
            if (bigrsvdQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(TOT_RSVD_ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);
        }

        BigDecimal origtotQty = bigboQty.add(bigrsvdQty);
        BigDecimal totQty = boTmsg.intfcBoQty.getValue().add(boTmsg.intfcRsvdBoQty.getValue());

        if (origtotQty.compareTo(BigDecimal.ZERO) < 0) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);

        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
        }
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, totQty);

        String intfcCpoOrdDt = ZYPDateUtil.DateFormatter((String) boData.get(CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, intfcCpoOrdDt);
        if (ZYPCommonFunc.hasValue((String) boData.get(RSD_DT))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(RSD_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, getBusinessDate(intfcCpoOrdDt, ADD_DATE_1));
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, catgZZCd);

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.prtChrgInd, partsChrgIndComp);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        return boTmsg;
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForWHTrans(Map<String, Object> boData, String trxTp) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(FROM_RTL_WH_CD), (String) boData.get(FROM_WH_OWNR_CD), (String) boData.get(FROM_INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincRcvGlblWhCd, getCincGlblWhCd((String) boData.get(TO_RTL_WH_CD), (String) boData.get(TO_WH_OWNR_CD), (String) boData.get(TO_INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, (String) boData.get(CPO_ORD_TS));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, glblCmpyCd);

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(
                (String) boData.get(FROM_RTL_WH_CD),
                (String) boData.get(FROM_WH_OWNR_CD),
                (String) boData.get(FROM_INVTY_LOC_TP_CD),
                (String) boData.get(TO_RTL_WH_CD),
                (String) boData.get(TO_WH_OWNR_CD),
                (String) boData.get(TO_INVTY_LOC_TP_CD)
        );

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get(CINC_GLBL_CMPY_CATG_CD));

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(SELL_TO_CUST_CD));

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
        if (null == bean) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
        }

        if (ZYPCommonFunc.hasValue((String) boData.get(SHPG_SVC_LVL_CD))) {
            String trdPtnrShpgMethCd = getCPOTrdPtnrShpgMethCd((String) boData.get(SHPG_SVC_LVL_CD), (String) boData.get(CARR_CD));
            if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                        ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + (String) boData.get(CARR_CD),
                        "CpoOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + (String) boData.get(CARR_CD) });
                errFlg = true;
                outPutLogList.add(errMsg);
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, trdPtnrShpgMethCd);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORD_QTY))) {
            BigDecimal ordQty = (BigDecimal) boData.get(ORD_QTY);
            if (ordQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(BO_QTY))) {
            BigDecimal ordQty = (BigDecimal) boData.get(BO_QTY);
            if (ordQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, (BigDecimal) boData.get(BO_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(RSVD_QTY))) {
            BigDecimal ordQty = (BigDecimal) boData.get(RSVD_QTY);
            if (ordQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(RSVD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);
        }

        if (ZYPCommonFunc.hasValue((String) boData.get(EXPD_SHIP_DT))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(EXPD_SHIP_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, getBusinessDate((String) boData.get(CPO_ORD_TS), ADD_DATE_1));
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get(CINC_GLBL_ORD_CATG_CD));

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.prtChrgInd, partsChrgIndComp);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        return boTmsg;
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForTechTrans(Map<String, Object> boData, String trxTp) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(FROM_RTL_WH_CD), (String) boData.get(FROM_WH_OWNR_CD), (String) boData.get(FROM_INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincRcvGlblWhCd, getCincGlblWhCd((String) boData.get(TO_RTL_WH_CD), (String) boData.get(TO_WH_OWNR_CD), (String) boData.get(TO_INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, glblCmpyCd);

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(
                (String) boData.get(FROM_RTL_WH_CD),
                (String) boData.get(FROM_WH_OWNR_CD),
                (String) boData.get(FROM_INVTY_LOC_TP_CD),
                (String) boData.get(TO_RTL_WH_CD),
                (String) boData.get(TO_WH_OWNR_CD),
                (String) boData.get(TO_INVTY_LOC_TP_CD)
        );

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get(CINC_GLBL_CMPY_CATG_CD));

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(SELL_TO_CUST_CD));

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
        if (null == bean) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
        }

        if (ZYPCommonFunc.hasValue((String) boData.get(SHPG_SVC_LVL_CD))) {
            String trdPtnrShpgMethCd = getInvtyOrdTrdPtnrShpgMethCd((String) boData.get(SHPG_SVC_LVL_CD));
            if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                        ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd,
                        "CpoOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + carrCd });
                errFlg = true;
                outPutLogList.add(errMsg);
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, trdPtnrShpgMethCd);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORD_QTY))) {
            BigDecimal bigOrdQty = (BigDecimal) boData.get(ORD_QTY);
            if (bigOrdQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(ORD_QTY));
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);

        String intfcCpoOrdDt = ZYPDateUtil.DateFormatter((String) boData.get(CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, intfcCpoOrdDt);
        if (ZYPCommonFunc.hasValue((String) boData.get(RSD_DT))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(RSD_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, getBusinessDate(intfcCpoOrdDt, ADD_DATE_1));
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get(CINC_GLBL_ORD_CATG_CD));

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.prtChrgInd, partsChrgIndComp);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        return boTmsg;
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForReturn(Map<String, Object> boData) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(RTL_WH_CD), (String) boData.get(WH_OWNR_CD), (String) boData.get(INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, (String) boData.get(CPO_ORD_TS));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, (String) boData.get(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, (String) boData.get(GLBL_CMPY_CD));
        // QC#30962 Add. 
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.OTHER);

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, (String) boData.get(SELL_TO_CUST_CD));

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
        if (null == bean) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);

        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(ORIG_RTRN_QTY))) {
            BigDecimal bigRtrnQty = (BigDecimal) boData.get(ORIG_RTRN_QTY);
            if (bigRtrnQty.compareTo(BigDecimal.ZERO) < 0) {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
                ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_NEGATIVE);
            }
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(TOT_RTRN_QTY));
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(TOT_RTRN_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue((String) boData.get(RQST_PICK_UP_DT))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(RQST_PICK_UP_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, getBusinessDate((String) boData.get(CPO_ORD_TS), ADD_DATE_1));
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(boTmsg.cincBillToGlblCmpyCd.getValue()));

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.prtChrgInd, partsChrgIndComp);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        return boTmsg;
    }

    private NMXC104001ConvertPartsMdseCdBean getConvertPartsMdse(String mdseCd, String ordNum, String cusaVndCd) {

        NMXC104001ConvertPartsMdseCdBean bean = new NMXC104001ConvertPartsMdseCdBean();
        bean.setMode(NMXC104001ConvertPartsMdseCd.MODE_MDSE);
        bean.setGlblCmpyCd(glblCmpyCd);
        bean.setMdseCd(mdseCd);
        bean.setCusaVndCd(cusaVndCd);

        NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(bean);

        if (ZYPCommonFunc.hasValue(bean.getErrCd())) {
            // ERROR
            String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0528E, new String[] {"NMXC104001ConvertPartsMdseCd", bean.getErrCd(), "OrdNum: " + ordNum + ",MdseCode: " + mdseCd });
            String errApiMsg = S21MessageFunc.clspGetMessage(bean.getErrCd());
            outPutLogList.add(errMsg);
            outPutLogList.add(errApiMsg);
            errFlg = true;
            return null;

        }

        return bean;
    }

    private INTFC_BO_WRKTMsg setIntfcBoWrkForStockOut(Map<String, Object> boData) {

        INTFC_BO_WRKTMsg boTmsg = new INTFC_BO_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(boTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblWhCd, getCincGlblWhCd((String) boData.get(RTL_WH_CD), (String) boData.get(WH_OWNR_CD), (String) boData.get(INVTY_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdDt, (String) boData.get(CPO_ORD_TS));
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCpoOrdNum, (String) boData.get(ORD_NUM));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincShipToGlblCmpyCd, (String) boData.get(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(boTmsg.cincBillToGlblCmpyCd, (String) boData.get(GLBL_CMPY_CD));
        // QC#30962 Add.
        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.OTHER);

        String sellToCustCd = (String) boData.get(SELL_TO_CUST_CD);
        if (ZYPCommonFunc.hasValue(sellToCustCd) && sellToCustCd.length() > SELL_TO_CUST_CD_LENGTH_12) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, sellToCustCd.substring(0, SELL_TO_CUST_CD_LENGTH_12));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcSellToCustCd, sellToCustCd);
        }

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = getConvertPartsMdse((String) boData.get(MDSE_CD), (String) boData.get(ORD_NUM), (String) boData.get(CUSA_VND_CD));
        if (null == bean) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(boTmsg.prtSizeTxt, bean.getXtrnlSysSize());
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQtySgnTxt, QTY_SGN_TXT_PLUS);
        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(TOT_ORD_QTY))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, (BigDecimal) boData.get(TOT_ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcOrdQty, BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQtySgnTxt, QTY_SGN_TXT_PLUS);
        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(BO_ORD_QTY))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, (BigDecimal) boData.get(BO_ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcBoQty, BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQtySgnTxt, QTY_SGN_TXT_PLUS);
        if (ZYPCommonFunc.hasValue((BigDecimal) boData.get(RSVD_BO_ORD_QTY))) {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, (BigDecimal) boData.get(RSVD_BO_ORD_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.intfcRsvdBoQty, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue((String) boData.get(EXPD_SHIP_DT))) {

            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(EXPD_SHIP_DT));

        } else {

            ZYPEZDItemValueSetter.setValue(boTmsg.intfcExpdShipDt, (String) boData.get(CPO_ORD_TS));
        }

        if (ZYPCommonFunc.hasValue((String) boData.get(SHPG_SVC_LVL_CD))) {
            String trdPtnrShpgMethCd = getCPOTrdPtnrShpgMethCd((String) boData.get(SHPG_SVC_LVL_CD), (String) boData.get(CARR_CD));
            if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0312W, new String[] {TRD_PTNR_SHPG_X_REF,
                        ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + (String) boData.get(CARR_CD),
                        "CpoOrdNum: " + (String) boData.get(ORD_NUM) + ",ShpgSvcLclCd: " + (String) boData.get(SHPG_SVC_LVL_CD) + ",VndCd: " + cincVndCd + ",CarrCd: " + (String) boData.get(CARR_CD) });
                errFlg = true;
                outPutLogList.add(errMsg);
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
            } else {
                ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, trdPtnrShpgMethCd);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblShpgMethCd, shpgMeth99);
        }

        ZYPEZDItemValueSetter.setValue(boTmsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(boTmsg.cincBillToGlblCmpyCd.getValue()));

        ZYPEZDItemValueSetter.setValue(boTmsg.prtEmerOrdInd, prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(boTmsg.intfcCratDt, batchProcDt);

        setExpdShipDtAndPrtChrgInd(boData, boTmsg, partsChrgIndComp, partsChrgIndNoComp);

        return boTmsg;
    }

    private boolean isWorkData() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> param = new HashMap<String, String>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_BATCH_PROC_DT, batchProcDt);

        List<Map<String, Object>> wrkDataList = ssmBatchClient.queryObjectList(STMT_GET_INTFC_BO_WRK, param, execParam);

        return !isEmpty(wrkDataList);
    }

    private void setExpdShipDtAndPrtChrgInd(Map<String, Object> boData, INTFC_BO_WRKTMsg boWrkTmg, String prtChrgIndComp, String prtChrgIndNoComp) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        List<String> ordLineStsList = new ArrayList<String>();
        ordLineStsList.add(ORD_LINE_STS.VALIDATED);
        ordLineStsList.add(ORD_LINE_STS.PARTIALLY_SHIPPED);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_CPO_ORD_NUM, (String) boData.get(ORD_NUM));
        param.put(BIND_MDSE_CD, (String) boData.get(MDSE_CD));
        param.put(BIND_SHPG_SVC_LVL_CD, (String) boData.get(SHPG_SVC_LVL_CD));
        param.put(BIND_ORD_LINE_STS_LIST, ordLineStsList);

        List<Map<String, Object>> resBoDataList = ssmBatchClient.queryObjectList(STMT_EXPD_SHIP_DT_AND_PRT_CHRG_IND, param, execParam);

        if (isEmpty(resBoDataList)) {

            ZYPEZDItemValueSetter.setValue(boWrkTmg.prtChrgInd, prtChrgIndComp);
            ZYPEZDItemValueSetter.setValue(boWrkTmg.intfcExpdShipDt, getBusinessDate((String) boData.get(CPO_ORD_TS), ADD_DATE_1));

        } else {

            Map<String, Object> resBoData = resBoDataList.get(0);

            if (ZYPCommonFunc.hasValue((String) resBoData.get(EXPD_SHIP_DT))) {

                ZYPEZDItemValueSetter.setValue(boWrkTmg.intfcExpdShipDt, (String) resBoData.get(EXPD_SHIP_DT));

            } else {

                ZYPEZDItemValueSetter.setValue(boWrkTmg.intfcExpdShipDt, getBusinessDate((String) resBoData.get(CPO_ORD_TS), ADD_DATE_1));
            }

            ZYPEZDItemValueSetter.setValue(boWrkTmg.prtChrgInd, prtChrgIndComp);
        }
    }

    private boolean isDuplicationData(INTFC_BO_WRKTMsg boTmsg) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        param.put(BIND_CINC_GLBL_WH_CD, boTmsg.cincGlblWhCd.getValue());
        param.put(BIND_INTFC_CPO_ORD_DT, boTmsg.intfcCpoOrdDt.getValue());
        param.put(BIND_INTFC_CPO_ORD_NUM, boTmsg.intfcCpoOrdNum.getValue());
        param.put(BIND_INTFC_PRT_MDSE_CD, boTmsg.intfcPrtMdseCd.getValue());
        param.put(BIND_PRT_SIZE_TXT, boTmsg.prtSizeTxt.getValue());
        param.put(BIND_GLBL_SHPG_METH_CD, boTmsg.cincGlblShpgMethCd.getValue());
        param.put(BIND_BATCH_PROC_DT, batchProcDt);

        List<Map<String, Object>> resBoDataList = ssmBatchClient.queryObjectList(STMT_GET_DUPLICATION_DATA, param, execParam);

        return !isEmpty(resBoDataList);
    }

    private static boolean isEmpty(List list) {

        return list == null || list.isEmpty();
    }

    private boolean hasVarCharConstValue(List<String> varCharConstValueList, String varCharConstNm) {

        if (null == varCharConstValueList) {

            String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NWAM0525E, new String[] {VAR_CHAR_CONST, varCharConstNm });
            errFlg = true;
            outPutLogList.add(errMsg);
            return false;
        }

        return true;
    }

    private String getBusinessDate(String targetShipDt, int days) {

        return ZYPDateUtil.addBusinessDay(glblCmpyCd, targetShipDt, days);
    }

    private String getCincGlblOrdCatgCd(String billToGlblCmpyCd) {

        if (glblCmpyCd.equals(billToGlblCmpyCd)) {

            return catgZZCd;
        }

        return catgA1Cd;
    }

    /**
     * getCincGlblWhCd
     */
    private String getCincGlblWhCd(String rtlWhCd, String whOwnrCd, String locTpCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        for (int i = 0; i < IDX_4; i++) {

            if (i == IDX_0) {
                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    // priority 1
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_RTL_WH_CD, rtlWhCd);
                    queryParam.put(BIND_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_1) {
                if (ZYPCommonFunc.hasValue(whOwnrCd)) {
                    // priority 2
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_WH_OWNR_CD, whOwnrCd);
                    queryParam.put(BIND_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_2) {
                if (ZYPCommonFunc.hasValue(locTpCd)) {
                    // priority 3
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_LOC_TP_CD, locTpCd);
                }
            }
            if (i == IDX_3) {
                // other
                queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(BIND_RTL_WH_CD, ASTERISK);
                queryParam.put(BIND_WH_OWNR_CD, ASTERISK);
                queryParam.put(BIND_LOC_TP_CD, ASTERISK);
            }

            cincGlblWhCd = (String) ssmBatchClient.queryObject(STMT_CINC_GLBL_WH_CD, queryParam);
            if (ZYPCommonFunc.hasValue(cincGlblWhCd)) {
                break;
            }
            queryParam.clear();
        }

        return cincGlblWhCd;
    }

    /**
     * getCincGlblCatgCdToWHT
     */
    private Map<String, Object> getCincGlblCatgCdToWHT(
            String fromRtlWhCd, String fromWhOwnrCd, String fromLocTpCd,
            String toRtlWhCd, String toWhOwnrCd, String toLocTpCd) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> cincGlblCatgCd = new HashMap<String, Object>();

        Map<String, Object> queryParam = new HashMap<String, Object>();

        for (int i = 0; i < IDX_10; i++) {
            if (i == IDX_0) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case A1
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, fromRtlWhCd);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                    queryParam.put(BIND_TO_RTL_WH_CD, toRtlWhCd);
                    queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_1) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case A2
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, fromRtlWhCd);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                    queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_TO_WH_OWNR_CD, toWhOwnrCd);
                    queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_2) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case A3
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, fromRtlWhCd);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                    queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_TO_LOC_TP_CD, toLocTpCd);
                }
            }
            if (i == IDX_3) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case B1
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, fromWhOwnrCd);
                    queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                    queryParam.put(BIND_TO_RTL_WH_CD, toRtlWhCd);
                    queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_4) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case B2
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, fromWhOwnrCd);
                    queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                    queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_TO_WH_OWNR_CD, toWhOwnrCd);
                    queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_5) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case B3
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, fromWhOwnrCd);
                    queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                    queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_TO_LOC_TP_CD, toLocTpCd);
                }
            }
            if (i == IDX_6) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case C1
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_FROM_LOC_TP_CD, fromLocTpCd);
                    queryParam.put(BIND_TO_RTL_WH_CD, toRtlWhCd);
                    queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_7) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case C2
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_FROM_LOC_TP_CD, fromLocTpCd);
                    queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_TO_WH_OWNR_CD, toWhOwnrCd);
                    queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
                }
            }
            if (i == IDX_8) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case C3
                    queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_FROM_LOC_TP_CD, fromLocTpCd);
                    queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                    queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                    queryParam.put(BIND_TO_LOC_TP_CD, toLocTpCd);
                }
            }
            if (i == IDX_9) {
                // other
                queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(BIND_FROM_RTL_WH_CD, ASTERISK);
                queryParam.put(BIND_FROM_WH_OWNR_CD, ASTERISK);
                queryParam.put(BIND_FROM_LOC_TP_CD, ASTERISK);
                queryParam.put(BIND_TO_RTL_WH_CD, ASTERISK);
                queryParam.put(BIND_TO_WH_OWNR_CD, ASTERISK);
                queryParam.put(BIND_TO_LOC_TP_CD, ASTERISK);
            }
            cincGlblCatgCd = (Map<String, Object>) ssmBatchClient.queryObject(STMT_CINC_GLBL_CATG_CD, queryParam, execParam);
            if (cincGlblCatgCd != null) {
                String catgCd = (String) cincGlblCatgCd.get(CINC_GLBL_CMPY_CATG_CD);
                if (ZYPCommonFunc.hasValue(catgCd)) {
                    break;
                }
            }

            queryParam.clear();
        }

        return cincGlblCatgCd;
    }

    /**
     * getCPOTrdPtnrShpgMethCd
     */
    private String getCPOTrdPtnrShpgMethCd(String shpgSvcLclCd, String pCarrCd) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // priority 1
        if (!ZYPCommonFunc.hasValue(shpgSvcLclCd)) {
            return this.shpgMeth99;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        String trdPtnrShpgMethCd = null;

        if (ZYPCommonFunc.hasValue(carrCd)) {
            // priority 2
            queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLclCd);
            queryParam.put(BIND_VND_CD, this.cincVndCd);
            queryParam.put(BIND_CARR_CD, carrCd);
            queryParam.put(BIND_FRT_COND_CD, ASTERISK);
            trdPtnrShpgMethCd = (String) ssmBatchClient.queryObject(STMT_TRD_PTNR_SHPG_METH_CD, queryParam, execParam);
            if (ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                return trdPtnrShpgMethCd;
            }
        }

        // priority 3
        queryParam.clear();
        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLclCd);
        queryParam.put(BIND_VND_CD, this.cincVndCd);
        queryParam.put(BIND_CARR_CD, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD, ASTERISK);
        trdPtnrShpgMethCd = (String) ssmBatchClient.queryObject(STMT_TRD_PTNR_SHPG_METH_CD, queryParam, execParam);
        if (ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
            return trdPtnrShpgMethCd;
        }

        // priority 4
        if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
            trdPtnrShpgMethCd = this.shpgMeth99;
        }

        return trdPtnrShpgMethCd;
    }

    /**
     * getInvtyOrdTrdPtnrShpgMethCd
     */
    private String getInvtyOrdTrdPtnrShpgMethCd(String shpgSvcLclCd) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // priority 1
        if (!ZYPCommonFunc.hasValue(shpgSvcLclCd)) {
            return this.shpgMeth99;
        }

        // priority 2
        Map<String, Object> queryParam = new HashMap<String, Object>();
        String trdPtnrShpgMethCd = null;

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLclCd);
        queryParam.put(BIND_VND_CD, this.cincVndCd);
        queryParam.put(BIND_CARR_CD, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD, ASTERISK);
        trdPtnrShpgMethCd = (String) ssmBatchClient.queryObject(STMT_TRD_PTNR_SHPG_METH_CD, queryParam, execParam);

        return trdPtnrShpgMethCd;
    }

}
