/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB120001;

import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.CRLF;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DATE_TIME_FORMAT_14;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_DP_SEND_PO_EML_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_DP_TRSMT_METH_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_FAX_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_PO_RPT_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_PO_SUBMT_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_SEND_PO_FAX_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_VD_SEND_PO_EML_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_VD_TRSMT_METH_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.DB_ITEM_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.INDENT;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.MAIL_ADDRESS_LENGTH;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.MAIL_FORMAT_SIZE_16;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.MAIL_FORMAT_SIZE_30;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.NPAF0010_PURGE_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.NPAM0078E;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.NPAM1267E;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.NPAM1342E;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.RETRY_COUNT;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SPACE;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_EDI_SEND_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_PO_APVL_STS_CD_01;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_PO_APVL_STS_CD_02;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_PO_SEND_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_PO_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_PURGE_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_TRSMT_METH_TP_CD_05;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.SQL_ITEM_TRSMT_METH_TP_CD_06;
import static com.canon.cusa.s21.batch.NPA.NPAB120001.constant.NPAB120001Constant.WAIT_TIME;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.POTMsg;
import business.db.POTMsgArray;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.db.PO_RPT_WRKTMsg;
import business.parts.NPZC005001PMsg;
import business.parts.NPZC007001PMsg;
import business.parts.NPZC104001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001;
import com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NPAB120001:Send PO Form
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/12/2013   Hitachi         T.Kanasaka      Create          N/A
 * 03/29/2013   Hitachi         S.Tanabe        Update          QC799
 * 01/27/2016   CITS            R Shimamoto     Update          V1.0
 * 11/28/2016   CITS            Y.Fujii         Update          R350
 * 07/06/2018   CITS            K.Ogino         Update          QC#27153
 * 02/20/2019   CITS            T.Tokutomi      Update          QC#30350
 * 07/29/2019   Fujitsu         S.Ohki          Update          QC#51910
 * 11/28/2019   Fujitsu         T.Ogura         Update          QC#54867
 *</pre>
 */
public class NPAB120001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Proc Date */
    private String batProcDate = null;

    /** User Profile Service */
    private S21UserProfileService profSrvc = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** NPZC1040 API MODE */
    private String mode = "4";

    // 2013/03/29 QC799 S.Tanabe Delete Start
    // /** SSM Batch Client */
    // private S21SsmBatchClient ssmBatchClient = null;
    //
    // 2013/03/29 QC799 S.Tanabe Delete End
    /** Term code */
    private TERM_CD termCd = null;

    /** total count */
    private int totCnt;

    /** Error count */
    private int errCnt;

    /** Error Mail */
    private NPAB120001Mail errMail;

    /** report work table purge date. */
    private BigDecimal purgeDt;

    /**
     * Main
     * @param args Parameters
     */
    public static void main(String[] args) {
        new NPAB120001().executeBatch(NPAB120001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        profSrvc = S21UserProfileServiceFactory.getInstance().getService();
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // 2013/03/29 QC799 S.Tanabe Delete Start
        // ssmBatchClient =
        // S21SsmBatchClient.getClient(this.getClass());
        // 2013/03/29 QC799 S.Tanabe Delete End
        termCd = TERM_CD.ABNORMAL_END;
        totCnt = 0;
        errCnt = 0;

        // Check the input parameters.
        checkParams();

        this.errMail = new NPAB120001Mail(this.glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {

        // Remove Report Work Table
        removePORptWrk();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(SQL_ITEM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(SQL_ITEM_PO_STS_CD, PO_STS.VALIDATED);
            // 2016/01/26 R.Shimamoto [V1.0 Delete] Start
            // queryParam.put(SQL_ITEM_TRSMT_METH_TP_CD_01,
            // TRSMT_METH_TP.EMAIL);
            // 2016/01/26 R.Shimamoto [V1.0 Delete] End
            queryParam.put(SQL_ITEM_TRSMT_METH_TP_CD_05, TRSMT_METH_TP.EMAIL_PDF);
            queryParam.put(SQL_ITEM_TRSMT_METH_TP_CD_06, TRSMT_METH_TP.FAX);
            // 2016/01/28 R.Shimamoto [V1.0 Add] Start
            queryParam.put(SQL_ITEM_PO_APVL_STS_CD_01, PO_APVL_STS.APPROVED);
            queryParam.put(SQL_ITEM_PO_APVL_STS_CD_02, PO_APVL_STS.PRE_APPROVED);
            queryParam.put(SQL_ITEM_PO_SEND_FLG, ZYPConstant.FLG_OFF_N);
            queryParam.put(SQL_ITEM_EDI_SEND_FLG, ZYPConstant.FLG_ON_Y);
            // 2016/01/28 R.Shimamoto [V1.0 Add] End
            // QC#30350 Add Search Param.
            queryParam.put(SQL_ITEM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            // QC#27153
            ssmEexcParam.setFetchSize(1000);
            ssmEexcParam.setMaxRows(0);
            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            ssmEexcParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Get PO List
            stmt = this.ssmLLClient.createPreparedStatement("getPoList", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            int checkCnt = 0;
            while (rs.next()) {
                checkCnt = this.errMail.errDetailMap.length();

                // Send PO Form Main Process
                mainProcessPoSendForm(rs);

                totCnt++;
                if (this.errMail.errDetailMap.length() > checkCnt) {
                    errCnt++;
                    rollback();
                } else {
                    commit();
                }
            }

            if (this.errMail.errDetailMap.length() == 0) {
                termCd = TERM_CD.NORMAL_END;
            } else {
                // send err mail
                this.errMail.send();
                termCd = TERM_CD.WARNING_END;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, totCnt - errCnt, errCnt, totCnt);
    }

    /**
     * Check the input parameters. If an error occurs, throws
     * Exception.
     */
    private void checkParams() {

        // Get the Global Company Code.
        glblCmpyCd = profSrvc.getGlobalCompanyCode();
        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            noValueError(new String[] {"Global Company Code" });
        }

        // 2016/01/26 R.Shimamoto [V1.0 ADD] Start
        // Get the Batch Proc Date.
        batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        // 2016/01/26 R.Shimamoto [V1.0 ADD] End

        purgeDt = ZYPCodeDataUtil.getNumConstValue(NPAF0010_PURGE_DT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(purgeDt)) {
            noValueError(new String[] {NPAF0010_PURGE_DT});
        }
    }

    /**
     * No Value Error
     * @param item Error Item
     */
    private void noValueError(String[] item) {
        throw new S21AbendException(NPAM0078E, item);
    }

    /**
     * Main Process PO Send Form
     * @param rs getPoList ResultSet
     */
    private void mainProcessPoSendForm(ResultSet rs) throws SQLException {

        String poOrdNum = rs.getString(DB_ITEM_PO_ORD_NUM);
        String vndCd = rs.getString(DB_ITEM_VND_CD);
        String procDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        String trsmtMethTpCd = null;
        String emlAddr = null;
        String faxNum = null;

        // QC#30350 Add.
        String contactPsnEmail = null;
        if(!ZYPCommonFunc.hasValue(emlAddr)){
            contactPsnEmail = getEmlAddressFromContactPsn(vndCd);
        }

        if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_DP_TRSMT_METH_TP_CD))) {
            trsmtMethTpCd = rs.getString(DB_ITEM_DP_TRSMT_METH_TP_CD);
            // QC#30350 Update
            if (trsmtMethTpCd.equals(TRSMT_METH_TP.EMAIL_PDF) //
                    && !ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_DP_SEND_PO_EML_ADDR)) //
                    && !ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_VD_SEND_PO_EML_ADDR)) //
                    && !ZYPCommonFunc.hasValue(contactPsnEmail)) {
                return;
            }
            if (trsmtMethTpCd.equals(TRSMT_METH_TP.FAX) && !ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_SEND_PO_FAX_NUM)) && !ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_FAX_NUM))) {
                return;
            }

            // Set Email
            if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_DP_SEND_PO_EML_ADDR))) {
                emlAddr = rs.getString(DB_ITEM_DP_SEND_PO_EML_ADDR);
            } else if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_VD_SEND_PO_EML_ADDR))) {
                emlAddr = rs.getString(DB_ITEM_VD_SEND_PO_EML_ADDR);
            } else {
                emlAddr = contactPsnEmail;
            }

            // Set FaxNum
            if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_SEND_PO_FAX_NUM))) {
                faxNum = rs.getString(DB_ITEM_SEND_PO_FAX_NUM);
            } else {
                faxNum = rs.getString(DB_ITEM_FAX_NUM);
            }

        } else {
            trsmtMethTpCd = rs.getString(DB_ITEM_VD_TRSMT_METH_TP_CD);

            if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_FAX_NUM))) {
                faxNum = rs.getString(DB_ITEM_FAX_NUM);
            } else {
                faxNum = rs.getString(DB_ITEM_SEND_PO_FAX_NUM);
            }

            // Set Email
            if (TRSMT_METH_TP.EMAIL_PDF.equals(trsmtMethTpCd)) {

                // QC#30350 Update.
                if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_VD_SEND_PO_EML_ADDR))) {
                    emlAddr = rs.getString(DB_ITEM_VD_SEND_PO_EML_ADDR);
                } else if (ZYPCommonFunc.hasValue(rs.getString(DB_ITEM_DP_SEND_PO_EML_ADDR))) {
                    emlAddr = rs.getString(DB_ITEM_DP_SEND_PO_EML_ADDR);
                } else {
                    emlAddr = contactPsnEmail;
                }
            }
        }

        // Get Target PO Data For Update
        POTMsg inMsg = new POTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("poOrdNum01", poOrdNum);

        POTMsg poTMsg = getPoForUpdatePo(inMsg, 0);
        if (poTMsg == null) {
            setMailDetailLine(poOrdNum, vndCd, S21MessageFunc.clspGetMessage(NPAM1267E, new String[] {DB_ITEM_PO }));
            return;
        }


        // 2016/01/26 R.Shimamoto [V1.0 ADD] Get Target PO_DTL Data
        // For Update
        PO_DTLTMsg inDsPoDtlMsg = new PO_DTLTMsg();
        inDsPoDtlMsg.setSQLID("001");
        inDsPoDtlMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inDsPoDtlMsg.setConditionValue("poOrdNum01", poOrdNum);

        PO_DTLTMsg dsPoDtlMsg = getPoForUpdateDsPoDtl(inDsPoDtlMsg, 0);
        if (dsPoDtlMsg == null) {
            setMailDetailLine(poOrdNum, vndCd, S21MessageFunc.clspGetMessage(NPAM1267E, new String[] {DB_ITEM_PO }));
            return;
        }
        // 2016/01/26 R.Shimamoto [V1.0 ADD] End

        // Call Create PO Report Api
        NPZC007001PMsg dPZC007001PMsg = callCreatePoReportApi(rs, procDt);
        if (!checkApiErr(dPZC007001PMsg, poOrdNum, vndCd)) {
            return;
        }
        String rcvRptTs = dPZC007001PMsg.rcvRptTs.getValue();
        BigDecimal poRptPrintRqstSq = dPZC007001PMsg.poRptPrintRqstSq.getValue();

        // 2016/01/26 R.Shimamoto [V1.0 Delete] Start
        // Commit Before Call Purchase Order Api
        // commit();
        //
        // // Get Target PO Data For Update Again
        // poTMsg = getPoForUpdatePo(inMsg, 0);
        // if (poTMsg == null) {
        // setMailDetailLine(poOrdNum, vndCd,
        // S21MessageFunc.clspGetMessage(NPAM1267E, new String[]
        // {DB_ITEM_PO }));
        // return;
        // }
        // 2016/01/26 R.Shimamoto [V1.0 Delete] End

        // Call Purchase Order Api
        NPZC005001PMsg dPZC005001PMsg = callPurchaseOrderApi(rs, trsmtMethTpCd, emlAddr, faxNum, rcvRptTs, poRptPrintRqstSq);
        if (!checkApiErr(dPZC005001PMsg, poOrdNum, vndCd)) {
            return;
        }

        // get EIP Report Request PK
        BigDecimal eipReportRequestPk = dPZC005001PMsg.eipRptRqstPk.getValue();

        // 2016/01/26 R.Shimamoto [V1.0 Delete] Start
        // Update PO.PO_SEND_FLG
        // poTMsg.poSendFlg.setValue(ZYPConstant.FLG_ON_Y);
        // S21ApiTBLAccessor.update(poTMsg);
        // 2016/01/26 R.Shimamoto [V1.0 Delete] End

        // 2016/01/27 R.Shimamoto [V1.0 Add] Start
        // Call Purchase Order Create Api
        NPZC104001PMsg dPZC104001PMsg = callPurchaseOrderCreateApi(rs, trsmtMethTpCd, emlAddr, faxNum, rcvRptTs, eipReportRequestPk);
        if (!checkApiErr(dPZC104001PMsg, poOrdNum, vndCd)) {
            return;
        }
        // 2016/01/27 R.Shimamoto [V1.0 Add] End

    }

    // 2013/03/29 QC799 S.Tanabe Delete Start
    // /**
    // * Get Email Address String
    // * @param mdseSendToList List<String> (ex.)
    // * {"aaa@xx,bbb@xx,ccc@xx", "ddd@xx",,,}
    // * @return EmlAddrString
    // */
    // private String getEmlAddrString(List<String> mdseSendToList) {
    //
    // // split comma
    // List<String> emlAddrList = new ArrayList<String>();
    // for (String sendPoEmlAddr : mdseSendToList) {
    // sendPoEmlAddr = sendPoEmlAddr.replace(SPACE, BLANK);
    // String[] emlAddrs = sendPoEmlAddr.split(COMMA);
    // for (String eml : emlAddrs) {
    // // exclude same address
    // if (!emlAddrList.contains(eml)) {
    // emlAddrList.add(eml);
    // }
    // }
    // }
    //
    // // to String
    // StringBuilder sb = new StringBuilder();
    // for (String eml : emlAddrList) {
    // if (sb.length() != 0) {
    // sb.append(COMMA);
    // }
    // sb.append(eml);
    // }
    //
    // return sb.toString();
    // }
    //
    // 2013/03/29 QC799 S.Tanabe Delete End
    /**
     * Call Create PO Report Api
     * @param rs getPoList ResultSet
     * @param procDt
     * @return NPZC007001PMsg
     */
    private NPZC007001PMsg callCreatePoReportApi(ResultSet rs, String procDt) throws SQLException {

        // set param
        NPZC007001PMsg dPZC007001PMsg = new NPZC007001PMsg();
        ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.whCd, rs.getString(DB_ITEM_SHIP_TO_CUST_CD));
        // 2016/01/26 R.Shimamoto [V1.0 Delete] Start
        // ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.poChrgCd,
        // rs.getString(DB_ITEM_PO_CHRG_CD));
        // 2016/01/26 R.Shimamoto [V1.0 Delete] End
        ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.vndCd, rs.getString(DB_ITEM_VND_CD));
        ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.poOrdNum, rs.getString(DB_ITEM_PO_ORD_NUM));
        // 2016/01/26 R.Shimamoto [V1.0 Delete] Start
        // ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.poOrdCmntTxt,
        // rs.getString(DB_ITEM_PO_HDR_TXT));
        // 2016/01/26 R.Shimamoto [V1.0 Delete] End
        ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.usrId, rs.getString(DB_ITEM_PO_SUBMT_PSN_CD));
        ZYPEZDItemValueSetter.setValue(dPZC007001PMsg.procDt, procDt);

        // call API
        NPZC007001 createPoReportApi = new NPZC007001();
        createPoReportApi.execute(dPZC007001PMsg, ONBATCH_TYPE.BATCH);

        return dPZC007001PMsg;
    }

    /**
     * Call Purchase Order Api
     * @param rs getPoList ResultSet
     * @param trsmtMethTpCd String
     * @param emlAddr String
     * @param faxNum String
     * @param rcvRptTs String
     * @param poRptPrintRqstSq BigDecimal
     * @return NPZC005001PMsg
     */
    private NPZC005001PMsg callPurchaseOrderApi(ResultSet rs, String trsmtMethTpCd, String emlAddr, String faxNum, String rcvRptTs, BigDecimal poRptPrintRqstSq) throws SQLException {

        // set param
        NPZC005001PMsg dPZC00501PMsg = new NPZC005001PMsg();
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.userId, rs.getString(DB_ITEM_PO_SUBMT_PSN_CD));
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.poOrdNum, rs.getString(DB_ITEM_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.procStartTs, rcvRptTs);
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.trsmtMethTpCd, trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.poRptPrintRqstSq, poRptPrintRqstSq);
        dPZC00501PMsg.rptDestId.clear();
        dPZC00501PMsg.emlFromAddr.clear();
        dPZC00501PMsg.emlSubjTxt.clear();
        dPZC00501PMsg.faxSubjNm.clear();
        ZYPEZDItemValueSetter.setValue(dPZC00501PMsg.faxNum, faxNum);

        // create email address list
        int cnt = 0;
        int idx = 0;
        while (emlAddr != null && emlAddr.length() > MAIL_ADDRESS_LENGTH) {
            idx = emlAddr.lastIndexOf(COMMA, MAIL_ADDRESS_LENGTH);
            dPZC00501PMsg.sendPoEmlAddrList.no(cnt).emlToAddr.setValue(emlAddr.substring(0, idx));
            emlAddr = emlAddr.substring(idx + 1);
            cnt++;
        }
        if (ZYPCommonFunc.hasValue(emlAddr)) {
            dPZC00501PMsg.sendPoEmlAddrList.no(cnt).emlToAddr.setValue(emlAddr);
            cnt++;
        }
        dPZC00501PMsg.sendPoEmlAddrList.setValidCount(cnt);

        // call API
        NPZC005001 purchaseOrderApi = new NPZC005001();
        purchaseOrderApi.execute(dPZC00501PMsg, ONBATCH_TYPE.BATCH);

        return dPZC00501PMsg;
    }

    /**
     * Call Purchase Order Create Api
     * @param rs getPoList ResultSet
     * @param trsmtMethTpCd String
     * @param emlAddr String
     * @param faxNum String
     * @param rcvRptTs String
     * @return NPZC104001PMsg
     */
    private NPZC104001PMsg callPurchaseOrderCreateApi(ResultSet rs, String trsmtMethTpCd, String emlAddr, String faxNum, String rcvRptTs, BigDecimal eipRptRqstPk) throws SQLException {

        // set param
        NPZC104001PMsg dPZC104001PMsg = new NPZC104001PMsg();
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.procDt, batProcDate);
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.xxRqstTs, rcvRptTs);
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.poOrdNum, rs.getString(DB_ITEM_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.trsmtMethTpCd, trsmtMethTpCd);

        // Case Email(PDF)
        if (TRSMT_METH_TP.EMAIL_PDF.equals(trsmtMethTpCd)) {
            // 2019/07/26 QC#51910 ADD Start
            int idx = 0;
            if (emlAddr != null && emlAddr.length() > MAIL_ADDRESS_LENGTH) {
                idx = emlAddr.lastIndexOf(COMMA, MAIL_ADDRESS_LENGTH);
                emlAddr = emlAddr.substring(0, idx);
            }
            // 2019/07/26 QC#51910 ADD End
            ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.sendPoEmlAddr, emlAddr);
        }
        // Case FAX
        if (TRSMT_METH_TP.FAX.equals(trsmtMethTpCd)) {
            ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.sendPoFaxNum, faxNum);
        }

        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.poSendFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.poSendTs, rcvRptTs);
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.poPrintFlg, ZYPConstant.FLG_ON_Y);

        // set EIP Report Request PK
        ZYPEZDItemValueSetter.setValue(dPZC104001PMsg.eipRptRqstPk, eipRptRqstPk);

        dPZC104001PMsg.poSubmtPsnCd.clear();

        // call API
        NPZC104001 createPoReportApi = new NPZC104001();
        createPoReportApi.execute(dPZC104001PMsg, ONBATCH_TYPE.BATCH);

        return dPZC104001PMsg;
    }

    /**
     * Get PO For Update
     * @param inMsg
     * @param reTryCount
     * @return Target Data
     */
    private POTMsg getPoForUpdatePo(POTMsg inMsg, int reTryCount) {

        POTMsg rtnTMsg = null;
        try {
            POTMsgArray resultMsgArray = (POTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg, WAIT_TIME);

            if (resultMsgArray.getValidCount() > 0) {
                rtnTMsg = (POTMsg) resultMsgArray.get(0);
            }
        } catch (EZDDBRecordLockedException e) {
            reTryCount++;
            if (RETRY_COUNT >= reTryCount) {
                rtnTMsg = getPoForUpdatePo(inMsg, reTryCount);
            }
        }

        return rtnTMsg;
    }

    /**
     * Get DS_PO_DTL For Update
     * @param inMsg
     * @param reTryCount
     * @return Target Data
     */
    private PO_DTLTMsg getPoForUpdateDsPoDtl(PO_DTLTMsg inMsg, int reTryCount) {

        PO_DTLTMsg rtnTMsg = null;
        try {
            PO_DTLTMsgArray resultMsgArray = (PO_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg, WAIT_TIME);

            if (resultMsgArray.getValidCount() > 0) {
                rtnTMsg = (PO_DTLTMsg) resultMsgArray.get(0);
            }
        } catch (EZDDBRecordLockedException e) {
            reTryCount++;
            if (RETRY_COUNT >= reTryCount) {
                rtnTMsg = getPoForUpdateDsPoDtl(inMsg, reTryCount);
            }
        }

        return rtnTMsg;
    }

    /**
     * Check Api Error
     * @param pMsg EZDPMsg
     * @param poOrdNum String
     * @param vndCd String
     * @return true:OK, false:NG
     */
    private boolean checkApiErr(EZDPMsg pMsg, String poOrdNum, String vndCd) {

        boolean result = true;

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            StringBuilder msg = new StringBuilder();
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String msgId : msgList) {
                msg.append(S21MessageFunc.clspGetMessage(msgId));
            }

            setMailDetailLine(poOrdNum, vndCd, msg.toString());
            result = false;
        }

        return result;
    }

    /**
     * Set Mail Detail Line
     * @param poOrdNum String
     * @param vndCd String
     * @param errMsg String
     */
    private void setMailDetailLine(String poOrdNum, String vndCd, String errMsg) {
        StringBuilder errDetailMap = new StringBuilder();
        errDetailMap.append(INDENT);
        errDetailMap.append(errMail.formatString(poOrdNum, MAIL_FORMAT_SIZE_16));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(vndCd, MAIL_FORMAT_SIZE_30));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMsg);
        errDetailMap.append(CRLF);
        this.errMail.setErrDetailMap(errDetailMap);
    }

    /**
     * Remove Purge Record
     */
    private void removePORptWrk() {

        //Check Target Date Record
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String purgeDtTm = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_14);
        purgeDtTm = ZYPDateUtil.addDays(purgeDtTm, purgeDt.negate().intValue());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(SQL_ITEM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(SQL_ITEM_PURGE_TS, purgeDtTm);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);
            preparedStatement = ssmLLClient.createPreparedStatement("getRemovPpoRptWrkData", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PO_RPT_WRKTMsg tMsg = new PO_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.poRptWrkPk, resultSet.getBigDecimal(DB_ITEM_PO_RPT_WRK_PK));

                // Delete Target Date Record
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NPAM1342E, new String[] {tMsg.getTableName()});
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    // QC#30350 Add method.
    /**
     * getEmlAddressFromContactPsn
     * @param vndCd String
     * @return emlAddress Return {@code null} if contact person not set email Address.
     */
    private String getEmlAddressFromContactPsn(String vndCd) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        // 2019/07/26 QC#51910 MOD Start
//        String emlAddress = null;
        StringBuffer emlAddress = new StringBuffer();
        boolean commaFlg = false;
        // 2019/07/26 QC#51910 MOD End
        // START 2019/11/28 T.Ogura [QC#54867,ADD]
        String ctacPsnEml = "";
        String inactiveDate = "";
        String salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        // END   2019/11/28 T.Ogura [QC#54867,ADD]
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("defThruDt", "99991231");
            params.put("dsCtacPntTpCd_T", DS_CTAC_PNT_TP.PHONE_WORK);
            params.put("dsCtacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
            params.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
            params.put("glblCmpyCd", glblCmpyCd);
            params.put("vndCd", vndCd);
            params.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
            params.put("ctacTpCdDI", CTAC_TP.DELIVERY_INSTALL);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);

            stmt = this.ssmLLClient.createPreparedStatement("getContactInfo", params, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (TRSMT_METH_TP.EMAIL_PDF.equals(rs.getString("TRSMT_METH_TP_CD"))) {
                    // START 2019/11/28 T.Ogura [QC#54867,MOD]
//                    // 2019/07/26 QC#51910 MOD Start
////                    emlAddress = rs.getString("CTAC_PSN_EML");
//                	if (commaFlg) {
//                		emlAddress.append(COMMA);
//                	}
//                    emlAddress.append(rs.getString("CTAC_PSN_EML"));
//                    commaFlg = true;
//                    // 2019/07/26 QC#51910 MOD End
                    ctacPsnEml = rs.getString("CTAC_PSN_EML");
                    inactiveDate = rs.getString("INAC_DT");
                    if (ZYPCommonFunc.hasValue(ctacPsnEml)
                            && (!ZYPCommonFunc.hasValue(inactiveDate) || ZYPDateUtil.compare(salesDt, inactiveDate) < 0)) {
                        if (commaFlg) {
                            emlAddress.append(COMMA);
                        }
                        emlAddress.append(ctacPsnEml);
                        commaFlg = true;
                    }
                    // END   2019/11/28 T.Ogura [QC#54867,MOD]
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        // 2019/07/26 QC#51910 MOD Start
//        return emlAddress;
        return emlAddress.toString();
        // 2019/07/26 QC#51910 MOD End
    }
}
