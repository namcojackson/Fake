/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB129001;

import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.MDSE_CD_REFUND;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.NFBM0199E;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.NFBM0207E;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.NONE;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.REFUND;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.STR_00;
import static com.canon.cusa.s21.batch.NFB.NFBB129001.constant.NFBB129001Constant.VARCHAR_CONST_AP_LINE_TP_ITEM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AR_RF_TRXTMsg;
import business.db.AR_RF_TRXTMsgArray;
import business.db.CCYTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.COA_ACCTTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_INV_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * AP Invoice Data Creation for AR Refund
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/02   Hitachi         J.Kim           Create          N/A
 * 2016/04/22   Hitachi         T.Tsuchida      Update          QC#6418
 * 2016/11/17   Hitachi         K.Kasai         Update          QC#15966
 * 2017/05/25   CITS            T.Tokutomi      Update          RS#8439
 * 2017/12/19   Hitachi         J.Kim           Update          QC#22199
 * 2017/12/19   Hitachi         Y.Takeno        Update          QC#23022
 * 2018/03/02   Hitachi         E.Kameishi      Update          QC#22765
 * 2018/03/22   CITS            T.Kikuhara      Update          QC#20316
 * 2018/06/12   Hitachi         E.Kameishi      Update          QC#22765
 * 2018/08/07   CITS            K.Ogino         Update          QC#27280
 * 09/05/2018   Fujitsu         S.Ohki          Update          QC#28040
 * </pre>
 */
public class NFBB129001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales date */
    private String batProcDate = null;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Commit Count */
    private int commitCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    private String varCharConstApLineTpItem;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0199E);
        }

        // "Batch Process Date"
        this.batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0207E, new String[] {"Batch Process Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get VAR_CHAR_CONST : AP_LINE_TP_ITEM
        varCharConstApLineTpItem = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_ITEM, glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount, this.normalCount + this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB129001().executeBatch(NFBB129001.class.getSimpleName());
    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<AR_RF_TRXTMsg> arRfTrxInfoList = new ArrayList<AR_RF_TRXTMsg>();
        // START 2018/03/23 [QC#20316, DEL]
        //List<CM_INV_ACCT_HDRTMsg> cmInvAcctHdrInfoList = new ArrayList<CM_INV_ACCT_HDRTMsg>();
        // END 2018/03/23 [QC#20316, DEL]
        List<CM_INV_ACCT_DISTTMsg> cmInvAcctDistInfoList = new ArrayList<CM_INV_ACCT_DISTTMsg>();
        List<CM_AP_INV_HDRTMsg> cmApInvHdrList = new ArrayList<CM_AP_INV_HDRTMsg>();
        List<CM_AP_INV_DTLTMsg> cmApInvDtlList = new ArrayList<CM_AP_INV_DTLTMsg>();

        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("arRfStsC", AR_RF_STS.CREATED);
        stesParam.put("cmDefAcctCd", CM_DEF_ACCT.REFUND_COA);

        try {

            ps = this.ssmLLClient.createPreparedStatement("getArRfTrxInfo", stesParam, execParam());
            rs = ps.executeQuery();

            // START 2018/04/10 [QC#20316, ADD]
            BigDecimal arRfRqstPk = BigDecimal.ZERO;
            CM_AP_INV_HDRTMsg outCmApInvHdrTmsg = new CM_AP_INV_HDRTMsg();
            // END 2018/04/10 [QC#20316, ADD]

            while (rs.next()) {

                AR_RF_TRXTMsg arRfTrxInTMsg = new AR_RF_TRXTMsg();
                // START 2017/12/15 J.Kim [QC#22199,ADD]
                // ZYPEZDItemValueSetter.setValue(arRfTrxInTMsg.glblCmpyCd, this.glblCmpyCd);
                // ZYPEZDItemValueSetter.setValue(arRfTrxInTMsg.arRfTrxPk, rs.getBigDecimal("AR_RF_TRX_PK"));
                // AR_RF_TRXTMsg arRfTrxOutTMsg = (AR_RF_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(arRfTrxInTMsg);
                // if (arRfTrxOutTMsg != null) {
                arRfTrxInTMsg.setSQLID("001");
                arRfTrxInTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                arRfTrxInTMsg.setConditionValue("arRfRqstPk01", rs.getBigDecimal("AR_RF_RQST_PK"));
                AR_RF_TRXTMsgArray arRfTrxTmsgArray = (AR_RF_TRXTMsgArray) EZDTBLAccessor.findByCondition(arRfTrxInTMsg);

                if (arRfTrxTmsgArray != null && arRfTrxTmsgArray.getValidCount() > 0) {
                    AR_RF_TRXTMsg arRfTrxOutTMsg = arRfTrxTmsgArray.no(0);
                    // END 2017/12/15 J.Kim [QC#22199,ADD]

                    // START 2018/03/23 [QC#20316, DEL]
                    //CM_INV_ACCT_HDRTMsg inTMsg = new CM_INV_ACCT_HDRTMsg();
                    //ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                    //ZYPEZDItemValueSetter.setValue(inTMsg.apVndCd, arRfTrxOutTMsg.vndCd);
                    //ZYPEZDItemValueSetter.setValue(inTMsg.apVndInvNum, arRfTrxOutTMsg.apVndInvNum);
                    //ZYPEZDItemValueSetter.setValue(inTMsg.apVndInvSqNum, STR_00);
                    //CM_INV_ACCT_HDRTMsg outTMsg = (CM_INV_ACCT_HDRTMsg) S21FastTBLAccessor.findByKey(inTMsg);
                    //
                    //if (outTMsg == null) {
                    //    cmInvAcctHdrInfoList.add(insertCmInvAcctHdrRecord(rs, inTMsg));
                    //}
                    // END 2018/03/23 [QC#20316, DEL]

                    CM_INV_ACCT_DISTTMsg inCiadTMsg = new CM_INV_ACCT_DISTTMsg();
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.glblCmpyCd, this.glblCmpyCd);
                    // START 2018/03/23 [QC#20316, MOD]
                    //ZYPEZDItemValueSetter.setValue(inCiadTMsg.apVndCd, arRfTrxOutTMsg.vndCd);
                    //ZYPEZDItemValueSetter.setValue(inCiadTMsg.apVndInvNum, arRfTrxOutTMsg.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.apVndCd, rs.getString("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.apVndInvNum, rs.getString("AP_VND_INV_NUM"));
                    // END 2018/03/23 [QC#20316, MOD]
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.apVndInvSqNum, STR_00);
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.mdseCd, MDSE_CD_REFUND);
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.apInvTpCd, STR_00);
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.poNum, NONE);
                    ZYPEZDItemValueSetter.setValue(inCiadTMsg.delyOrdNum, NONE);
                    CM_INV_ACCT_DISTTMsg outCiadTMsg = (CM_INV_ACCT_DISTTMsg) S21FastTBLAccessor.findByKey(inCiadTMsg);

                    if (outCiadTMsg == null) {
                        cmInvAcctDistInfoList.add(insertCmInvAcctDistRecord(rs, inCiadTMsg));
                    }

                    // START 2018/03/23 [QC#20316, MOD]
                    if (arRfRqstPk.compareTo(rs.getBigDecimal("AR_RF_RQST_PK")) != 0) {
                        arRfRqstPk = rs.getBigDecimal("AR_RF_RQST_PK");
                        CM_AP_INV_HDRTMsg inCmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
                        ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.glblCmpyCd, this.glblCmpyCd);
                        //ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.apVndCd, arRfTrxOutTMsg.vndCd);
                        //ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.apVndInvNum, arRfTrxOutTMsg.apVndInvNum);
                        ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.apVndCd, rs.getString("VND_CD"));
                        ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.apVndInvNum, rs.getString("AP_VND_INV_NUM"));
                        ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.apVndInvSqNum, STR_00);
                        ZYPEZDItemValueSetter.setValue(inCmApInvHdrTMsg.apInvTpCd, STR_00);
                        outCmApInvHdrTmsg = (CM_AP_INV_HDRTMsg) S21FastTBLAccessor.findByKey(inCmApInvHdrTMsg);

                        if (outCmApInvHdrTmsg == null) {
                            outCmApInvHdrTmsg = createCmApInvHdrTMsg(arRfTrxOutTMsg, rs);
                            cmApInvHdrList.add(outCmApInvHdrTmsg);
                        }
                    }
                    // END 2018/03/23 [QC#20316, MOD]

                    CM_AP_INV_DTLTMsg inCmApInvDtlTMsg = new CM_AP_INV_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                    // START 2018/03/23 [QC#20316, MOD]
                    //ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.apVndCd, arRfTrxOutTMsg.vndCd);
                    //ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.apVndInvNum, arRfTrxOutTMsg.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.apVndCd, rs.getString("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.apVndInvNum, rs.getString("AP_VND_INV_NUM"));
                    // END 2018/03/23 [QC#20316, MOD]
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.apVndInvSqNum, STR_00);
                    // START 2018/03/23 [QC#20316, MOD]
                    //ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.vndCd, arRfTrxOutTMsg.vndCd);
                    //ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.vndInvNum, arRfTrxOutTMsg.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.vndCd, rs.getString("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.vndInvNum, rs.getString("AP_VND_INV_NUM"));
                    // END 2018/03/23 [QC#20316, MOD]
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.mdseCd, REFUND);
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.delyOrdNum, NONE);
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.apInvTpCd, STR_00);
                    ZYPEZDItemValueSetter.setValue(inCmApInvDtlTMsg.poNum, NONE);
                    CM_AP_INV_DTLTMsg outCmApInvDtlTMsg = (CM_AP_INV_DTLTMsg) S21FastTBLAccessor.findByKey(inCmApInvDtlTMsg);

                    if (outCmApInvDtlTMsg == null) {
                        cmApInvDtlList.add(createCmApInvDtlTMsg(arRfTrxOutTMsg, rs, outCmApInvHdrTmsg));
                    }
                }

                // START 2017/12/15 J.Kim [QC#22199,ADD]
                for (int i = 0; i < arRfTrxTmsgArray.getValidCount(); i++) {
                    AR_RF_TRXTMsg arRfTrxOutTMsg = arRfTrxTmsgArray.no(i);

                    ZYPEZDItemValueSetter.setValue(arRfTrxOutTMsg.arRfStsCd, AR_RF_STS.AP_INOVICE_CREATED);
                    ZYPEZDItemValueSetter.setValue(arRfTrxOutTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
                    arRfTrxInfoList.add(arRfTrxOutTMsg);
                }
                // END 2017/12/15 J.Kim [QC#22199,ADD]

                if (this.commitNumber == arRfTrxInfoList.size()) {

                    // START 2018/03/22 [QC#20316, MOD]
                    //executeInsertUpdate(arRfTrxInfoList, cmInvAcctHdrInfoList, cmInvAcctDistInfoList, cmApInvHdrList, cmApInvDtlList);
                    executeInsertUpdate(arRfTrxInfoList, cmInvAcctDistInfoList, cmApInvHdrList, cmApInvDtlList);
                    // END 2018/03/22 [QC#20316, MOD]

                    // init
                    this.commitCount = 0;
                    arRfTrxInfoList = new ArrayList<AR_RF_TRXTMsg>();
                    // START 2018/03/23 [QC#20316, DEL]
                    //cmInvAcctHdrInfoList = new ArrayList<CM_INV_ACCT_HDRTMsg>();
                    // END 2018/03/23 [QC#20316, DEL]
                    cmInvAcctDistInfoList = new ArrayList<CM_INV_ACCT_DISTTMsg>();
                    cmApInvHdrList = new ArrayList<CM_AP_INV_HDRTMsg>();
                    cmApInvDtlList = new ArrayList<CM_AP_INV_DTLTMsg>();
                }
            }

            if (arRfTrxInfoList.size() > 0) {
                // START 2018/03/22 [QC#20316, MOD]
                //executeInsertUpdate(arRfTrxInfoList, cmInvAcctHdrInfoList, cmInvAcctDistInfoList, cmApInvHdrList, cmApInvDtlList);
                executeInsertUpdate(arRfTrxInfoList, cmInvAcctDistInfoList, cmApInvHdrList, cmApInvDtlList);
                // END 2018/03/22 [QC#20316, MOD]
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    // START 2018/03/23 [QC#20316, DEL]
    //private CM_INV_ACCT_HDRTMsg insertCmInvAcctHdrRecord(ResultSet rs, CM_INV_ACCT_HDRTMsg inTMsg) throws SQLException {
    //
    //    ZYPEZDItemValueSetter.setValue(inTMsg.invDt, this.batProcDate);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.acInvTotCostAmt, rs.getBigDecimal("FUNC_RF_AMT"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaCmpyCd, rs.getString("SPLY_COA_CMPY_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaBrCd, rs.getString("SPLY_COA_BR_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaCcCd, rs.getString("SPLY_COA_CC_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaAcctCd, rs.getString("SPLY_COA_ACCT_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaProdCd, rs.getString("SPLY_COA_PROD_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaChCd, rs.getString("SPLY_COA_CH_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaAfflCd, rs.getString("SPLY_COA_AFFL_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaProjCd, rs.getString("SPLY_COA_PROJ_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.coaExtnCd, rs.getString("SPLY_COA_EXTN_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.apInvCatgCd, AP_INV_CATG.AR_REFUND);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.invAuthDt, this.batProcDate);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.prntVndCd, rs.getString("PRNT_VND_CD"));
    //    ZYPEZDItemValueSetter.setValue(inTMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
    //    ZYPEZDItemValueSetter.setValue(inTMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
    //    // START 2017/12/19 [QC#23022, ADD]
    //    if (ZYPCommonFunc.hasValue(rs.getBigDecimal("AR_RF_RQST_PK"))) {
    //        ZYPEZDItemValueSetter.setValue(inTMsg.invHdrDescTxt, REFUND + rs.getBigDecimal("AR_RF_RQST_PK").toPlainString());
    //    } else {
    //        ZYPEZDItemValueSetter.setValue(inTMsg.invHdrDescTxt, REFUND);
    //    }
    //    // END   2017/12/19 [QC#23022, ADD]
    //    // START 2018/03/02 E.Kameishi [QC#22765,ADD]
    //    ZYPEZDItemValueSetter.setValue(inTMsg.invHdrDescTxt, rs.getString("AR_RF_CHK_CMNT_TXT"));
    //    // END 2018/03/02 E.Kameishi [QC#22765,ADD]
    //
    //    return inTMsg;
    //}
    // END 2018/03/23 [QC#20316, DEL]

    private CM_INV_ACCT_DISTTMsg insertCmInvAcctDistRecord(ResultSet rs, CM_INV_ACCT_DISTTMsg inTMsg) throws SQLException {

        // START 2018/03/23 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(inTMsg.apVndCd, rs.getString("VND_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.apVndInvNum, rs.getString("AP_VND_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaCmpyCd, rs.getString("CM_COA_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaBrCd, rs.getString("CM_COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaCcCd, rs.getString("CM_COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaAcctCd, rs.getString("CM_COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaProdCd, rs.getString("CM_COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaChCd, rs.getString("CM_COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaAfflCd, rs.getString("CM_COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaProjCd, rs.getString("CM_COA_PROJ_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.drCoaExtnCd, rs.getString("CM_COA_EXTN_CD"));
        // END 2018/03/23 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(inTMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inTMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inTMsg.acInvJrnlCostAmt, rs.getBigDecimal("FUNC_RF_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.apLineTpCd, varCharConstApLineTpItem);
        // START 2018/03/23 [QC#20316, ADD]
        ZYPEZDItemValueSetter.setValue(inTMsg.apVndInvLineNum, rs.getString("AP_VND_INV_LINE_NUM"));
        // END 2018/03/23 [QC#20316, ADD]
        // START 2016/11/17 [QC#15996, ADD]
        ZYPEZDItemValueSetter.setValue(inTMsg.cmInvAcctDistLineNum, rs.getString("CM_INV_ACCT_DIST_LINE_NUM"));
        // END 2016/11/17 [QC#15996, ADD]
        // START 2017/12/18 [QC#23022, ADD]
        ZYPEZDItemValueSetter.setValue(inTMsg.mdseDescShortTxt, REFUND);
        // END   2017/12/18 [QC#23022, ADD]

        // START 2018/03/23 [QC#20316, ADD]
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaCmpyCd, rs.getString("SPLY_COA_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaBrCd, rs.getString("SPLY_COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaCcCd, rs.getString("SPLY_COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaAcctCd, rs.getString("SPLY_COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaProdCd, rs.getString("SPLY_COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaChCd, rs.getString("SPLY_COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaAfflCd, rs.getString("SPLY_COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaProjCd, rs.getString("SPLY_COA_PROJ_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.crCoaExtnCd, rs.getString("SPLY_COA_EXTN_CD"));
        // END 2018/03/23 [QC#20316, ADD]

        // START 2018/09/06 [QC#28040, ADD]
        String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, rs.getString("CM_COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.apAcctDescTxt, coaAcctDescTxt);

        EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
        int txtDigit = getDigitApInvDescTxt.getDigit();

        if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
            ZYPEZDItemValueSetter.setValue(inTMsg.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.apInvDescTxt, coaAcctDescTxt);
        }
        // END 2018/09/06 [QC#28040, ADD]

        return inTMsg;
    }

    /**
     * createCmApInvHdrTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @return CM_AP_INV_HDRTMsg
     * @throws SQLException SQLException
     */
    private CM_AP_INV_HDRTMsg createCmApInvHdrTMsg(AR_RF_TRXTMsg rqstTMsg, ResultSet rs) throws SQLException {
        CM_AP_INV_HDRTMsg tMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2018/03/23 [QC#20316, MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.apVndCd, rqstTMsg.vndCd);
        //ZYPEZDItemValueSetter.setValue(tMsg.apVndInvNum, rqstTMsg.apVndInvNum);
        ZYPEZDItemValueSetter.setValue(tMsg.apVndCd, rs.getString("VND_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvNum, rs.getString("AP_VND_INV_NUM"));
        // END 2018/03/23 [QC#20316, ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvSqNum, STR_00);
        ZYPEZDItemValueSetter.setValue(tMsg.apInvTpCd, STR_00);
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, rqstTMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.lastTrxDt, NFBCommonBusiness.getCmProcDt(this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTotFobCostAmt, rs.getBigDecimal("FUNC_RF_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTotGndCostAmt, tMsg.acOcTotFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTotGndInvAmt, tMsg.acOcTotFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acScTotFobCostAmt, calcScFob(tMsg.acOcTotFobCostAmt.getValue(), tMsg.ccyCd.getValue(), tMsg.invDt.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.acScTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.acScTotGndCostAmt, tMsg.acScTotFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acScTotGndInvAmt, tMsg.acScTotFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.poNum, NONE);

        // START 2018/03/23 [QC#20316, ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.acInvTotCostAmt, rs.getBigDecimal("FUNC_RF_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.apInvCatgCd, AP_INV_CATG.AR_REFUND);
        ZYPEZDItemValueSetter.setValue(tMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
        ZYPEZDItemValueSetter.setValue(tMsg.invAuthDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(tMsg.prntVndCd, rs.getString("PRNT_VND_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/03/23 [QC#20316, ADD]
        // START 2018/06/12 E.Kameishi [QC#22765,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.invHdrDescTxt, rs.getString("AR_RF_CHK_CMNT_TXT"));
        // END 2018/06/12 E.Kameishi [QC#22765,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.vndPmtTermCd, rs.getString("VND_PMT_TERM_CD"));

        return tMsg;
    }

    /**
     * createCmApInvDtlTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @return CM_AP_INV_DTLTMsg
     * @throws SQLException SQLException
     */
    private CM_AP_INV_DTLTMsg createCmApInvDtlTMsg(AR_RF_TRXTMsg rqstTMsg, ResultSet rs, CM_AP_INV_HDRTMsg hdrTmsg) throws SQLException {
        CM_AP_INV_DTLTMsg tMsg = new CM_AP_INV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2018/03/23 [QC#20316, MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.apVndCd, rqstTMsg.vndCd);
        //ZYPEZDItemValueSetter.setValue(tMsg.apVndInvNum, rqstTMsg.apVndInvNum);
        ZYPEZDItemValueSetter.setValue(tMsg.apVndCd, rs.getString("VND_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvNum, rs.getString("AP_VND_INV_NUM"));
        // END 2018/03/23 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvSqNum, STR_00);
        // START 2018/03/23 [QC#20316, MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.vndCd, rqstTMsg.vndCd);
        //ZYPEZDItemValueSetter.setValue(tMsg.vndInvNum, rqstTMsg.apVndInvNum);
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, rs.getString("VND_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.vndInvNum, rs.getString("AP_VND_INV_NUM"));
        // END 2018/03/23 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, REFUND);
        ZYPEZDItemValueSetter.setValue(tMsg.delyOrdNum, NONE);
        ZYPEZDItemValueSetter.setValue(tMsg.apInvTpCd, STR_00);
        ZYPEZDItemValueSetter.setValue(tMsg.poNum, NONE);
        ZYPEZDItemValueSetter.setValue(tMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.acOcFobCostAmt, rs.getBigDecimal("FUNC_RF_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTotCostAmt, tMsg.acOcFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acOcTotInvAmt, tMsg.acOcFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acScFobCostAmt, calcScFob(tMsg.acOcFobCostAmt.getValue(), hdrTmsg.ccyCd.getValue(), hdrTmsg.invDt.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.acScTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.acScTotCostAmt, tMsg.acScFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acScTotInvAmt, tMsg.acScFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.applyExchRate, getExchRate(hdrTmsg.ccyCd.getValue(), hdrTmsg.invDt.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.trnstJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.stkInJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.invRcvQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.origScFobCostAmt, tMsg.acScFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.ocUnitExtCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.ocExtCostAmt, tMsg.acOcFobCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.scUnitExtCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.poQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthFobCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.exchRate, BigDecimal.ZERO);
        tMsg.poOrdDtlLineNum.clear();
        // START 2018/03/23 [QC#20316, MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.apVndInvLineNum, rs.getString("CM_INV_ACCT_DIST_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvLineNum, rs.getString("AP_VND_INV_LINE_NUM"));
        // END 2018/03/23 [QC#20316, MOD]
        // START 2017/12/18 [QC#23022, ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, REFUND);
        // END   2017/12/18 [QC#23022, ADD]

        return tMsg;
    }

    /**
     * calcScFob
     * @param amt BigDecimal
     * @param ccyCd String
     * @param invDt String
     * @return ScFob Amount BigDecimal
     */
    private BigDecimal calcScFob(BigDecimal amt, String ccyCd, String invDt) {
        // Calculate SC-FOB

        BigDecimal exchRate = getExchRate(ccyCd, invDt);
        CCYTMsg ccyMsg = NFBCommonBusiness.getCcyInfo(this.glblCmpyCd, ccyCd);
        String acctArthTpCd = ccyMsg.acctArthTpCd.getValue();
        BigDecimal aftDeclPntDigitNum = ccyMsg.aftDeclPntDigitNum.getValue();

        return NFBCommonBusiness.calcStdAmt(amt, exchRate, acctArthTpCd, aftDeclPntDigitNum);
    }

    /**
     * getExchRate
     * @param ccyCd String
     * @param invDt String
     * @return exchRate BigDecimal
     */
    private BigDecimal getExchRate(String ccyCd, String invDt) {
        BigDecimal exchRate = NFBCommonBusiness.getAcctDlyActlExchRate(this.glblCmpyCd, ccyCd, invDt);
        String cmProcDt = NFBCommonBusiness.getCmProcDt(this.glblCmpyCd);
        if (exchRate == null) {
            exchRate = NFBCommonBusiness.getAcctDlyActlExchRate(this.glblCmpyCd, ccyCd, cmProcDt);
            if (exchRate == null) {
                exchRate = BigDecimal.ONE;
            }
        }
        return exchRate;
    }

    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * executeInsertUpdate
     * @param arRfTrxList List<AR_RF_TRXTMsg>
     * @param cmInvAcctHdrList List<CM_INV_ACCT_HDRTMsg>
     * @param cmInvAcctDistList List<CM_INV_ACCT_DISTTMsg>
     * @param cmApInvHdrList List<CM_AP_INV_HDRTMsg>
     * @param cmApInvDtlList List<CM_AP_INV_DTLTMsg>
     */
    private void executeInsertUpdate(//
            List<AR_RF_TRXTMsg> arRfTrxList //
            // START 2018/03/23 [QC#20316, DEL]
            //, List<CM_INV_ACCT_HDRTMsg> cmInvAcctHdrList //
            // END 2018/03/23 [QC#20316, DEL]
            , List<CM_INV_ACCT_DISTTMsg> cmInvAcctDistList //
            , List<CM_AP_INV_HDRTMsg> cmApInvHdrList //
            , List<CM_AP_INV_DTLTMsg> cmApInvDtlList) {

        this.commitCount = this.executionUpdateArRfTrx(arRfTrxList);

        if (this.commitCount != arRfTrxList.size()) {
            this.errorCount = arRfTrxList.size() - this.commitCount;
            throw new S21AbendException("NFBM0028E");
        }
        // START 2018/03/23 [QC#20316, DEL]
        //int isrtCmInvHdrCnt = this.executionInsertHdr(cmInvAcctHdrList);
        //
        //if (isrtCmInvHdrCnt != cmInvAcctHdrList.size()) {
        //    this.errorCount = cmInvAcctHdrList.size() - isrtCmInvHdrCnt;
        //    throw new S21AbendException("NFBM0028E");
        //}
        // END 2018/03/23 [QC#20316, DEL]
        int isrtCmInvDtlCnt = this.executionInsertDist(cmInvAcctDistList);

        if (isrtCmInvDtlCnt != cmInvAcctDistList.size()) {
            this.errorCount = cmInvAcctDistList.size() - isrtCmInvDtlCnt;
            throw new S21AbendException("NFBM0028E");
        }
        int isrtCmApHdrCnt = this.executionInsertCmApHdr(cmApInvHdrList);

        if (isrtCmApHdrCnt != cmApInvHdrList.size()) {
            this.errorCount = cmApInvHdrList.size() - isrtCmApHdrCnt;
            throw new S21AbendException("NFBM0028E");
        }
        int isrtCmApDtlrCnt = this.executionInsertCmApDtl(cmApInvDtlList);

        if (isrtCmApDtlrCnt != cmApInvDtlList.size()) {
            this.errorCount = cmApInvDtlList.size() - isrtCmApDtlrCnt;
            throw new S21AbendException("NFBM0028E");
        }
        this.normalCount += this.commitCount;

        commit();
    }

    // START 2018/03/23 [QC#20316, DEL]
    //private int executionInsertHdr(List<CM_INV_ACCT_HDRTMsg> inTMsgList) {
    //
    //    CM_INV_ACCT_HDRTMsg[] inTMsgArray = new CM_INV_ACCT_HDRTMsg[inTMsgList.size()];
    //    int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inTMsgArray));
    //    return insertCount;
    //}
    // END 2018/03/23 [QC#20316, DEL]

    private int executionInsertDist(List<CM_INV_ACCT_DISTTMsg> inTMsgList) {

        CM_INV_ACCT_DISTTMsg[] inTMsgArray = new CM_INV_ACCT_DISTTMsg[inTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inTMsgArray));
        return insertCount;
    }

    private int executionUpdateArRfTrx(List<AR_RF_TRXTMsg> inTMsgList) {

        AR_RF_TRXTMsg[] inTMsgArray = new AR_RF_TRXTMsg[inTMsgList.size()];
        int updateCont = S21FastTBLAccessor.update(inTMsgList.toArray(inTMsgArray));
        return updateCont;
    }

    private int executionInsertCmApHdr(List<CM_AP_INV_HDRTMsg> inTMsgList) {

        CM_AP_INV_HDRTMsg[] inTMsgArray = new CM_AP_INV_HDRTMsg[inTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inTMsgArray));
        return insertCount;
    }

    private int executionInsertCmApDtl(List<CM_AP_INV_DTLTMsg> inTMsgList) {

        CM_AP_INV_DTLTMsg[] inTMsgArray = new CM_AP_INV_DTLTMsg[inTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inTMsgArray));
        return insertCount;
    }

    // START 2018/09/06 [QC#28040, ADD]
    /**
     * getCoaAcctDescTxt
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @retrun coaAcctDescTxt String
     */
    public static String getCoaAcctDescTxt(String glblCmpyCd, String coaAcctCd) {

        String rtnCoaAcctDescTxt = null;
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(coaAcctCd)) {
            return null;
        }
        COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, coaAcctCd);

        coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
        if (coaAcct != null) {
            rtnCoaAcctDescTxt = coaAcct.coaAcctDescTxt.getValue();
        }
        return rtnCoaAcctDescTxt;
    }
    // END 2018/09/06 [QC#28040, ADD]
}
