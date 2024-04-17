/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPB.NPBB003001;

import static com.canon.cusa.s21.batch.NPB.NPBB003001.constant.NPBB003001Constant.NPAM1479E;
import static com.canon.cusa.s21.batch.NPB.NPBB003001.constant.NPBB003001Constant.NPAM1480E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.batch.NPB.NPBB003001.constant.NPBB003001Constant;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NPBB003001:Create Drop Vendor Return Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/01   CITS            T.Kikuhara      Create          QC#18365(L3)
 * 2017/09/26   CITS            T.Hakodate      Update          QC#19805(L3)
 * 2017/11/17   CITS            S.Katsuma       Update          QC#22163
 * 2018/06/22   CITS            T,Hakodate      Update          QC#26839
 * 2018/10/31   CITS            M.Naito         Update          QC#29009
 * 
 *</pre>
 */
public class NPBB003001 extends S21BatchMain {

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** error count */
    private List<String> errMsgList = new ArrayList<String>();

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPBB003001().executeBatch(NPBB003001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        globalCompanyCode = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAM1479E);
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAM1480E);
        }
    }

    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", globalCompanyCode);
            paramMap.put("salesDate", salesDate);
            paramMap.put("vndReturn", PRCH_REQ_TP.VENDOR_RETURN);
            paramMap.put("inCompleted", PRCH_REQ_REL_STS.IN_COMPLETED);
            paramMap.put("error", PRCH_REQ_REL_STS.ERROR);
            paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
            paramMap.put("dropRma", RTL_WH_CATG.DROP_RMA);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("search", paramMap, execParam);
            NPZC103001PMsg pMsg = new NPZC103001PMsg();
            resultSet = preparedStatement.executeQuery();

            String invtyLocCd = "";
            int validCount = 0;
            while (resultSet.next()) {

                if (0 < totalCount && !invtyLocCd.equals(resultSet.getString("INVTY_LOC_CD"))) {
                    NPZC103001 dPZC103001 = new NPZC103001();
                    dPZC103001.execute(pMsg, ONBATCH_TYPE.BATCH);
                    if (S21ApiUtil.isXxMsgId(pMsg)) {
                        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                        int i = 0;
                        for (String msgId : msgIdList) {
                            if (msgId.endsWith("E")) {
                                String xxMsgTxt = S21MessageFunc.clspGetMessage(msgId) + " MDSE_CD=" + pMsg.prchReqInfo.no(i).mdseCd.getValue() + " INVTY_LOC_CD=" + pMsg.prchReqInfo.no(i).srcInvtyLocCd.getValue();
                                errMsgList.add(xxMsgTxt);
                                i++;
                            }
                        }
                        // QC#26839 ADD START
                    } else {
                        // commit with INVTY_LOC_CD
                        commit();
                    }
                    pMsg = new NPZC103001PMsg();
                    // QC#26839 ADD END
                    validCount = 0;
                    invtyLocCd = "";
                }

                if (!invtyLocCd.equals(resultSet.getString("INVTY_LOC_CD"))) {
                    invtyLocCd = resultSet.getString("INVTY_LOC_CD");
                    // PR header set
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
                    ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(pMsg.procDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.VENDOR_RETURN);
                    // START 2017/11/17 S.Katsuma [QC#22163,ADD]
//                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, GROUP_ID);
//                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, GROUP_ID);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPBB003001Constant.PR_CRAT_SYSTEM_USER, globalCompanyCode));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPBB003001Constant.PR_CRAT_SYSTEM_USER, globalCompanyCode));
                    // END 2017/11/17 S.Katsuma [QC#22163,ADD]
                    // START 2018/10/31 M.Naito [QC#29009,MOD]
//                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.DROP_VENDOR_RETURN);
                    // END 2018/10/31 M.Naito [QC#29009,MOD]
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
                    //QC#19805(L3) UPD START
                   // ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, resultSet.getString("SHIP_TO_CUST_CD"));
                   // ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, resultSet.getString("SHIP_TO_LOC_NM"));
                    //QC#19805(L3) UPD END
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, resultSet.getString("RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, resultSet.getString("RTL_WH_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, resultSet.getString("ADDL_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, resultSet.getString("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, resultSet.getString("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, resultSet.getString("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, resultSet.getString("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, resultSet.getString("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, resultSet.getString("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, resultSet.getString("PROV_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, resultSet.getString("CNTY_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, resultSet.getString("POST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, resultSet.getString("CTRY_CD"));
                }

                // PR Detail set
                NPZC103001_prchReqInfoPMsg apMsg = pMsg.prchReqInfo.no(validCount);
                ZYPEZDItemValueSetter.setValue(apMsg.mdseCd, resultSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, resultSet.getBigDecimal("REQ_QTY"));
                // QC#26839 ADD START
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqDispQty, resultSet.getBigDecimal("REQ_QTY"));
                // QC#26839 ADD END
                ZYPEZDItemValueSetter.setValue(apMsg.srcInvtyLocCd, resultSet.getString("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(apMsg.prntVndCd, resultSet.getString("PRNT_VND_CD"));
                ZYPEZDItemValueSetter.setValue(apMsg.vndCd, resultSet.getString("VND_CD"));
                ZYPEZDItemValueSetter.setValue(apMsg.fromStkStsCd, resultSet.getString("STK_STS_CD"));

                // START 2018/10/31 M.Naito [QC#29009,ADD]
                NLXC001001GetInventoryItemCostBean nlxc001001Bean = getInvtyItemCost(globalCompanyCode, apMsg.srcInvtyLocCd.getValue(), apMsg.mdseCd.getValue(), apMsg.prchReqDispQty.getValue());
                if (nlxc001001Bean.getErrList().isEmpty()) {
                    ZYPEZDItemValueSetter.setValue(apMsg.entDealNetUnitPrcAmt, nlxc001001Bean.getUnitPrcAmt());
                }
                // END 2018/10/31 M.Naito [QC#29009,ADD]
                validCount++;
                pMsg.prchReqInfo.setValidCount(validCount);

                totalCount++;
            }

            if (0 < validCount) {
                NPZC103001 dPZC103001 = new NPZC103001();
                dPZC103001.execute(pMsg, ONBATCH_TYPE.BATCH);
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                    int i = 0;
                    for (String msgId : msgIdList) {
                        if (msgId.endsWith("E")) {
                            String xxMsgTxt = S21MessageFunc.clspGetMessage(msgId) + " MDSE_CD=" + pMsg.prchReqInfo.no(i).mdseCd.getValue() + " INVTY_LOC_CD=" + pMsg.prchReqInfo.no(i).srcInvtyLocCd.getValue();
                            errMsgList.add(xxMsgTxt);
                            i++;
                        }
                    }
                }
            }

            if (0 < errMsgList.size()) {
                rollback();
                errorCount = totalCount;
                termCd = TERM_CD.ABNORMAL_END;
                for (String errMsg : errMsgList) {
                    S21InfoLogOutput.println(errMsg);
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, totalCount - errorCount, errorCount, totalCount);
    }

    // START 2018/10/31 M.Naito [QC#29009,ADD]
    /**
     * getInvtyItemCost
     * @pamram glblCmpyCd String
     * @pamram invtyLocCd String
     * @pamram mdseCd String
     * @pamram qty BigDecimal
     */
    public static NLXC001001GetInventoryItemCostBean getInvtyItemCost(String glblCmpyCd, String invtyLocCd, String mdseCd, BigDecimal qty) {
        NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
        nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
        nlxc001001Bean.setInvtyLocCd(invtyLocCd);
        nlxc001001Bean.setMdseCd(mdseCd);
        if (ZYPCommonFunc.hasValue(qty)) {
            nlxc001001Bean.setQty(qty);
        } else {
            nlxc001001Bean.setQty(BigDecimal.ONE);
        }

        NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
        return nlxc001001Bean;
    }
    // END 2018/10/31 M.Naito [QC#29009,ADD]

}
