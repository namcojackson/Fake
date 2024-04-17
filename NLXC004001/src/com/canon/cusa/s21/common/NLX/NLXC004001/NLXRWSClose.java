/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC004001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.INBD_VISTMsg;

import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/09/2015   CITS            Hisashi         Create          N/A
 * 03/02/2017   CITS           T.Hakodate       Update          QC#13811
 *</pre>
 */
public class NLXRWSClose {

    // Message ID
    // ------------------------------------------------------------------------
    /**
     */
    public static final String NLAM1132E = "NLAM1132E";

    /**
     */
    public static final String NLAM1133E = "NLAM1133E";

    // Statement ID
    // ------------------------------------------------------------------------
    /**
     * statementId:getInbdVisInfo03
     */
    private static final String GET_INBD_VISINFO03 = "getInbdVisInfo03";

    // ------------------------------------------------------------------------

    /**
     * TMSG_INBD_LTST_REC_FLG
     */
    private static final String TMSG_INBD_LTST_REC_FLG = "inbdLtstRecFlg";

    /**
     * TMSG_GLBL_CMPY_CD
     */
    private static final String TMSG_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * TMSG_INBD_VIS_PK
     */
    private static final String TMSG_INBD_VIS_PK = "inbdVisPk";

    /**
     * INVTY_STK_STS_CD
     */
    private static final String INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /**
     * TRX_ORD_NUM
     */
    private static final String TRX_ORD_NUM = "TRX_ORD_NUM";

    /**
     * TRX_LINE_NUM
     */
    private static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /**
     * RWS_NUM
     */
    private static final String RWS_NUM = "RWS_NUM";

    /**
     * RWS_REF_NUM
     */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /**
     * RWS_DTL_LINE_NUM
     */
    private static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /**
     * MDSE_CD
     */
    private static final String MDSE_CD = "MDSE_CD";

    /**
     * RWS_STK_QTY
     */
    private static final String RWS_STK_QTY = "RWS_STK_QTY";

    /**
     * RWS_STK_DT_TM_TS
     */
    private static final String RWS_STK_DT_TM_TS = "RWS_STK_DT_TM_TS";

    /**
     * WH_CD
     */
    private static final String WH_CD = "WH_CD";

    /**
     * LOC_NM
     */
    private static final String LOC_NM = "LOC_NM";

    /**
     * CUST_PO_NUM
     */
    private static final String CUST_PO_NUM = "CUST_PO_NUM";

    // ------------------------------------------------------------------------

    /**
     * DIGIT_DATE_FROM
     */
    private static final int DIGIT_DATE_FROM = 0;

    /**
     * DIGIT_DATE_TO
     */
    private static final int DIGIT_DATE_TO = 8;

    /**
     * FETCH_SIZE
     */
    private static final int FETCH_SIZE = 1000;

    // ------------------------------------------------------------------------
    /**
     */
    private S21SsmLowLevelCodingClient ssmLowLev = null;

    /**
     */
    private S21SsmBatchClient ssmBatch = null;

    /**
     */
    private S21SsmExecutionParameter execParam = null;

    /**
     * SCE Order Type List to create Inbound Visibility
     */
    private ArrayList<String> condListCR01 = null;

    /**
     * SCE ORder Type List to set key numbers to create Inbound Visibility
     */
    private ArrayList<String> condListCIV03 = null;

    /**
     */
    private String sysDtTs = "";

    /** Locale: YYYYMMDDHHMMSSsss */
    private static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     */
    public NLXRWSClose() {
        this.condListCR01 = new ArrayList<String>();
        this.condListCIV03 = new ArrayList<String>();
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_DT);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_DP);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_KT);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_KC);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_DG);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_DN);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_KU);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_RP);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_BB);
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_TR);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_DT);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_DP);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_KT);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_KC);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_DG);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_DN);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_KU);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_RP);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_BB);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_TR);

        // QC#13811 START
        this.condListCR01.add(NLXSceConst.SCE_ORD_TP_CD_RM);
        this.condListCIV03.add(NLXSceConst.SCE_ORD_TP_CD_RM);
        // QC#13811 END

        this.ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.ssmBatch = S21SsmBatchClient.getClient(this.getClass());

        this.execParam = new S21SsmExecutionParameter();

        this.execParam.setFetchSize(FETCH_SIZE);
    }

    /**
     * 
     * @param glblCmpyCd  Global Company Code
     * @param rwsNum      RWS Number
     * @param sceOrdTpCd  SCE order type code
     * @param sysDt       System date
     * @param onBatchType onBatchType
     * @throws SQLException SQLException
     */
    public void closeRws(String glblCmpyCd, String rwsNum, String sceOrdTpCd, String sysDt, ONBATCH_TYPE onBatchType) throws SQLException {

        if (condListCR01.contains(sceOrdTpCd)) {
            sysDtTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);

            createInbdVis(glblCmpyCd, rwsNum, sysDt, sceOrdTpCd);
        }
    }

    /**
     * @param rwsNum RWS#
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    private void createInbdVis(String glblCmpyCd, String rwsNum, String sysDt, String sceOrdTpCd) throws SQLException {

        INBD_VISTMsg inbdVisTMsg = new INBD_VISTMsg();

        // delete Inbound Visibility if it has minus qty
        inbdVisTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inbdVisTMsg.rwsNum.setValue(rwsNum);
        List<BigDecimal> inbdVisPkList = (List<BigDecimal>) ssmBatch.queryObjectList("getInbdVisHasMinusQty", inbdVisTMsg);
        if (!inbdVisPkList.isEmpty()) {
            INBD_VISTMsg[] removeTMsgs = new INBD_VISTMsg[inbdVisPkList.size()];
            for (int i = 0; i < inbdVisPkList.size(); i++) {
                removeTMsgs[i] = new INBD_VISTMsg();
                removeTMsgs[i].glblCmpyCd.setValue(glblCmpyCd);
                removeTMsgs[i].inbdVisPk.setValue((BigDecimal) inbdVisPkList.get(i));
            }
            S21FastTBLAccessor.removePhysical(removeTMsgs);
        }

        PreparedStatement inbdState = null;
        ResultSet inbdRs = null;
        String statementId = "";

        try {

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("rwsNum", rwsNum);
            inbdState = ssmLowLev.createPreparedStatement(GET_INBD_VISINFO03, paramMap, this.execParam);
            inbdRs    = inbdState.executeQuery();

            String[] updateItem = new String[] {TMSG_INBD_LTST_REC_FLG };
            String[] conItem    = new String[] {TMSG_GLBL_CMPY_CD, TMSG_INBD_VIS_PK };

            int dataCnt = 0;
            List<INBD_VISTMsg> insertInbdVisList = new ArrayList<INBD_VISTMsg>();
            while (inbdRs.next()) {
                dataCnt++;

                //Update INBD_LTST_REC_FLG Of INBD_VIS Planning Recode
                INBD_VISTMsg inbdVisT = new INBD_VISTMsg();
                List<INBD_VISTMsg> ltstInVisTList = null;

                inbdVisT.glblCmpyCd.setValue(glblCmpyCd);
                inbdVisT.poRcvNum.setValue(inbdRs.getString(TRX_ORD_NUM));
                inbdVisT.poRcvLineNum.setValue(inbdRs.getString(TRX_LINE_NUM));
                inbdVisT.visLocTpCd.setValue(VIS_LOC_TP.DC);
                inbdVisT.visLocCd.setValue(inbdRs.getString(WH_CD));
                inbdVisT.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);

                ltstInVisTList = (List<INBD_VISTMsg>) this.ssmBatch.queryObjectList("getLtstInvdVisInfoDo", inbdVisT);

                if (!ltstInVisTList.isEmpty()) {
                    for (int i = 0; i < ltstInVisTList.size(); i++) {
                        INBD_VISTMsg tmpResultListIv = (INBD_VISTMsg) ltstInVisTList.get(i);
                        INBD_VISTMsg inbdVisTcondMsg = new INBD_VISTMsg();
                        INBD_VISTMsg inbdVisTupdMsg  = new INBD_VISTMsg();
                        inbdVisTcondMsg.glblCmpyCd.setValue(glblCmpyCd);
                        inbdVisTcondMsg.inbdVisPk.setValue(tmpResultListIv.inbdVisPk.getValue());
                        inbdVisTupdMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_OFF_N);
                        S21ApiTBLAccessor.updateByPartialValue(inbdVisTcondMsg, conItem, inbdVisTupdMsg, updateItem);
                    }
                }

                //Create INBD_VIS TMsg For Insert Completion Recode
                BigDecimal seqBigDecimal = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);
                INBD_VISTMsg inMsg = new INBD_VISTMsg();
                inMsg.glblCmpyCd.setValue(glblCmpyCd);
                inMsg.inbdVisPk.setValue(seqBigDecimal);
                inMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);
                inMsg.inbdVisEventCd.setValue(INBD_VIS_EVENT.PO_COMPLETE_RECEIVE);
                inMsg.inbdVisDataTpCd.setValue(INBD_VIS_DATA_TP.STOCK_IN_DC);
                inMsg.inbdVisActlFlg.setValue(ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, inbdRs.getString(RWS_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.rwsRefNum, inbdRs.getString(RWS_REF_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.rwsDtlLineNum, inbdRs.getString(RWS_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, inbdRs.getString(MDSE_CD));
                inMsg.visLocTpCd.setValue(VIS_LOC_TP.DC);
                ZYPEZDItemValueSetter.setValue(inMsg.visLocCd, inbdRs.getString(WH_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.visLocNm, inbdRs.getString(LOC_NM));
                ZYPEZDItemValueSetter.setValue(inMsg.visQty, this.editQty(inbdRs.getBigDecimal(RWS_STK_QTY)));
                ZYPEZDItemValueSetter.setValue(inMsg.etaEtdDt, this.editDate(inbdRs.getString(RWS_STK_DT_TM_TS), sysDt));
                inMsg.calcFlg.setValue(ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inMsg.cratTs, sysDtTs);
                ZYPEZDItemValueSetter.setValue(inMsg.regdTs, sysDtTs);
                ZYPEZDItemValueSetter.setValue(inMsg.invtyStkStsCd, inbdRs.getString(INVTY_STK_STS_CD));

                if (condListCIV03.contains(sceOrdTpCd)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.poRcvNum, inbdRs.getString(TRX_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(inMsg.poRcvLineNum, inbdRs.getString(TRX_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(inMsg.imptCustPoNum, inbdRs.getString(CUST_PO_NUM));
                }
                insertInbdVisList.add(inMsg);
            }

            if (dataCnt != 0) {
                //Insert INBD_VIS Completion Recode
                INBD_VISTMsg[] insertArray = (INBD_VISTMsg[]) insertInbdVisList.toArray(new INBD_VISTMsg[0]);
                S21FastTBLAccessor.insert(insertArray);
            } else {
                throw new S21AbendException(NLAM1132E, new String[] {statementId, NLXCMsgHelper.toListedString(glblCmpyCd, rwsNum) });
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(inbdState, inbdRs);
        }
    }

    /**
     * @param val BigDecimal
     * @return String
     */
    private BigDecimal editQty(BigDecimal val) {

        if (!ZYPCommonFunc.hasValue(val)) {
            val = BigDecimal.ZERO;
        }
        return val;
    }

    /**
     * @param str String
     * @param sysDt String
     * @return String
     */
    private String editDate(String str, String sysDt) {

        if (!ZYPCommonFunc.hasValue(str)) {
            str = sysDt;
        }
        return str.substring(DIGIT_DATE_FROM, DIGIT_DATE_TO);
    }
}
