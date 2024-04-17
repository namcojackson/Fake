package com.canon.cusa.s21.batch.NFD.NFDB002001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import business.db.CLT_DUN_LTR_WRKTMsg;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * Dunning Letter Work Data Creation
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 01/14/2016   CSAI            K.Uramori       Create          N/A
 * 04/19/2016   CSAI            K.Uramori       Update          QC#7166
 * 2016/04/28   Fujitsu         C.Naito         Update          QC#7310
 * 2016/07/21   Hitachi         K.Kojima        Update          QC#11128
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2017/01/05   Hitachi         E.Kameishi      Update          QC#16817
 * 2017/06/26   Hitachi         E.Kameishi      Update          QC#18754
 * 2018/03/29   Hitachi         J.Kim           Update          QC#21733
 * 2018/06/18   Hitachi         E.Kameishi      Update          QC#25343
 * 2021/05/10   CITS            G.Delgado       Update          QC#58340
 * 2021/05/13   CITS            K.Suzuki        Update          QC#58340
 * 2021/06/09   CITS            K.Suzuki        Update          QC#58340
 * 2021/08/10   CITS            K.Suzuki        Update          QC#58340-4
 * 2021/08/13   CITS            K.Suzuki        Update          QC#59070
 *</pre>
 */
public class NFDB002001 extends S21BatchMain implements NFDB002001Constant {
    /** Normal Counter */
    private int normCnt = 0;

    /** Normal Counter */
    private int normTmpCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Ssm Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Default email address */
    private String defEmlAdd = "";

    // START 2016/07/21 K.Kojima [QC#11128,ADD]
    /** Header Print Company Name */
    private String arHdrPrintCmpyNm = "";

    // END 2016/07/21 K.Kojima [QC#11128,ADD]

    /** Default email address list */
    private List<String> defEmlAddList = new ArrayList<String>();

    /** Company Info */
    private NFDB002001_CmpInfo cmpInfo = new NFDB002001_CmpInfo();

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        // VAR_CHAR_CONST
        defEmlAdd = ZYPCodeDataUtil.getVarCharConstValue(AR_CLT_DEF_EML_ADDR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defEmlAdd)) {
            // error
            throw new S21AbendException(ZZZM9025E, new String[] {"Default Collector's Email Address" });
        }

        // START 2016/07/21 K.Kojima [QC#11128,ADD]
        arHdrPrintCmpyNm = ZYPCodeDataUtil.getVarCharConstValue(AR_HDR_PRINT_CMPY_NM, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(arHdrPrintCmpyNm)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Header Print Company Name" });
        }
        // END 2016/07/21 K.Kojima [QC#11128,ADD]

        // split the email addresses
        divDefEmlAdd();

        // get company info
        getCompanyInfo();

    }

    private void divDefEmlAdd() {
        StringTokenizer st = new StringTokenizer(defEmlAdd, ",");

        while (st.hasMoreTokens()) {
            defEmlAddList.add(st.nextToken());
        }

    }

    private void getCompanyInfo() {

        /** get data from VAR_CHAR_CONST **/
        cmpInfo.setFirstAddr(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_FIRST_ADDR, this.glblCmpyCd));
        cmpInfo.setScdAddr(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_SCD_ADDR, this.glblCmpyCd));
        cmpInfo.setThirdAddr(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_THIRD_ADDR, this.glblCmpyCd));
        cmpInfo.setFrthAddr(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_FRTH_ADDR, this.glblCmpyCd));
        cmpInfo.setCityAddr(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_CITY_ADDR, this.glblCmpyCd));
        cmpInfo.setProvNm(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_PROV_NM, this.glblCmpyCd));
        cmpInfo.setStCd(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_ST_CD, this.glblCmpyCd));
        cmpInfo.setPostCd(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_POST_CD, this.glblCmpyCd));
        cmpInfo.setTelNum(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_OFC_TEL_NUM, this.glblCmpyCd));
        cmpInfo.setUrl(ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_CMPY_URL, this.glblCmpyCd));

    }

    @Override
    protected void mainRoutine() {
        NFDB002001_ErrorMessage errMsg = new NFDB002001_ErrorMessage();

        List<TargetData> targetData = getTargetData();
        List<CLT_DUN_LTR_WRKTMsg> tmsgList = new ArrayList<CLT_DUN_LTR_WRKTMsg>();

        for (TargetData rec : targetData) {
            // check if same data exists in CLT_DUN_LTR_WRK
            List<Map<String, Object>> result = getDunLtrWrk(rec.getBillToCustCd(), rec.getCltDunLtrTpCd());

            // If exsits, delete the records.
            for (Map<String, Object> pk : result) {
                // delete
                CLT_DUN_LTR_WRKTMsg tmsg = new CLT_DUN_LTR_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmsg.cltDunLtrWrkPk, (BigDecimal) pk.get("CLT_DUN_LTR_WRK_PK"));

                tmsg = (CLT_DUN_LTR_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

                // delete
                int rtn = S21FastTBLAccessor.removePhysical(new CLT_DUN_LTR_WRKTMsg[] {tmsg });

                if (rtn != 1) {
                    errMsg.setMsgId(NFDM0004E);
                    errMsg.setMsgTxt(new String[] {"CLT_DUN_LTR_WRK" });
                    break;
                }
            }

            // [QC#7310] DELETE start
//            String psnCd = "";
//            // get collector's user info
//            if (hasValue(rec.getCltPsnCdBill())) { // bill to has
//                // priority
//                psnCd = rec.getCltPsnCdBill();
//            } else {
//                psnCd = rec.getCltPsnCdAcct();
//            }
//
//            S21UserInfo cltUsrInfo = getUserProfileService().getUserInfoFor(psnCd);
//
//            if (cltUsrInfo == null) {
//                errMsg.setMsgId(NFCM0531E);
//                errMsg.setMsgTxt(new String[] {"User Infomation" });
//                break;
//            }
            // [QC#7310] DELETE start

            // get detail info
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                ps = getDetailData(rec.getBillToCustCd());
                rs = ps.executeQuery();

                while (rs.next()) {

                    // Compile DTL_INV_CATG_TXT
                    String dtlInvCatgTxt = cmplDtlInvCatgTxt(rs.getString("SVC_INV_NUM"), rs.getString("DS_ORD_CATG_NM"));

                    // create CLT_DUN_LTR_WRK
                    // [QC#7310] UPDATE start
//                    tmsgList.add(createRecord(rec, rs, dtlInvCatgTxt, cltUsrInfo));
                    tmsgList.add(createRecord(rec, rs, dtlInvCatgTxt));
                    // [QC#7310] UPDATE end

                } // while loop

            } catch (SQLException ex) {
                errMsg.setMsgId(NFDM0003E);
                errMsg.setMsgTxt(new String[] {ex.getMessage() });
                break;
            } finally {
                S21SsmLowLevelCodingClient.closeResource(ps, rs);
            }

            try {
                // if no detail, create work table only with header
                // info
                if (tmsgList.size() == 0) {
                    // [QC#7310] UPDATE start
//                    tmsgList.add(createRecord(rec, null, null, cltUsrInfo));
                    tmsgList.add(createRecord(rec, null, null));
                    // [QC#7310] UPDATE end
                }
            } catch (SQLException ex) {
                errMsg.setMsgId(NFDM0003E);
                errMsg.setMsgTxt(new String[] {ex.getMessage() });
                break;
            }

            // submit created record
            if (!submitRecords(tmsgList)) {
                errMsg.setMsgId(NFDM0013E);
                errMsg.setMsgTxt(new String[] {"CLT_DUN_LTR_WRK" });
                break;
            }
            // reset
            tmsgList.clear();

            // update status of CLT_STRGY_WRK_ITEM_TRX
            if (!updateWrkItemTrx(rec.getCltStrgyWrkItemTrxPk())) {
                errMsg.setMsgId(NFDM0004E);
                errMsg.setMsgTxt(new String[] {"CLT_STRGY_WRK_ITEM_TRX" });
                break;
            }

            normCnt += normTmpCnt;

            // reset
            normTmpCnt = 0;

            // commit per target data
            commit();

        } // for loop

        // In case of error, rollback and have it abend
        if (ZYPCommonFunc.hasValue(errMsg.getMsgId())) {
            rollback();

            throw new S21AbendException(errMsg.getMsgId(), errMsg.getMsgTxt());
        }

    }

    private boolean submitRecords(List<CLT_DUN_LTR_WRKTMsg> tmsgList) {
        if (tmsgList.size() == 0) {
            return true;
        }

        int rtn = S21FastTBLAccessor.insert(tmsgList.toArray(new CLT_DUN_LTR_WRKTMsg[tmsgList.size()]));

        if (rtn != tmsgList.size()) {
            return false;
        }

        return true;
    }

    private boolean updateWrkItemTrx(BigDecimal pk) {

        CLT_STRGY_WRK_ITEM_TRXTMsg tmsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.cltStrgyWrkItemTrxPk, pk);

        tmsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        if (tmsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemWsrdDt, this.batProcDt);
        // START 2016/09/26 K.Kojima [QC#13004,MOD]
        // setValue(tmsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.CLOSE);
        ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.COMPLETED);
        // END 2016/09/26 K.Kojima [QC#13004,MOD]
        // START 2017/01/05 E.Kameishi [QC#16817,ADD]
        ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemWerdDt, this.batProcDt);
        // END 2017/01/05 E.Kameishi [QC#16817,ADD]

        int rtn = S21FastTBLAccessor.update(new CLT_STRGY_WRK_ITEM_TRXTMsg[] {tmsg });

        if (rtn != 1) {
            return false;
        }

        return true;
    }

    private CLT_DUN_LTR_WRKTMsg createRecord(TargetData hdr, ResultSet rs, String dtlInvCatgTxt) throws SQLException {

        CLT_DUN_LTR_WRKTMsg tmsg = new CLT_DUN_LTR_WRKTMsg();

        // Control
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.cltDunLtrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_DUN_LTR_WRK_SQ"));

        ZYPEZDItemValueSetter.setValue(tmsg.cltDunLtrTpCd, hdr.getCltDunLtrTpCd());
        ZYPEZDItemValueSetter.setValue(tmsg.cltDunLtrIssDt, this.batProcDt);
        ZYPEZDItemValueSetter.setValue(tmsg.cltStrgyWrkItemTrxPk, hdr.getCltStrgyWrkItemTrxPk());
        ZYPEZDItemValueSetter.setValue(tmsg.cltWrkTpCd, hdr.getCltWrkTpCd());
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustCd, hdr.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(tmsg.cltAcctCd, hdr.getCltAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.cltWfStsCd, CLT_WF_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(tmsg.cltPrintStsCd, "0");
        // [QC#7310] UPDATE start
        // setValue(tmsg.cltPsnCd, nullVal(hdr.getCltPsnCdBill(), hdr.getCltPsnCdAcct()));
        ZYPEZDItemValueSetter.setValue(tmsg.cltPsnCd, (hdr.getCltPsnCd()));
        // [QC#7310] UPDATE end
        // START 2021/05/10 G.Delgado [QC#58340,ADD]
        ZYPEZDItemValueSetter.setValue(tmsg.rptPrintRqstCpltFlg, ZYPConstant.FLG_OFF_N);
        // END 2021/05/10 G.Delgado [QC#58340,ADD]
        // START 2021/06/09 K.Suzuki [QC#58340,ADD]
        ZYPEZDItemValueSetter.setValue(tmsg.rptEmlOtptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.rptPrintOtptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.rptOtptCpltFlg, ZYPConstant.FLG_OFF_N);
        // END 2021/06/09 K.Suzuki [QC#58340,ADD]

        /** Header **/
        // [QC#7310] UPDATE start
        //setValue(tmsg.hdrCmpyNm, hdr.getGlblCmpyNm());
        //setValue(tmsg.hdrOfcFirstLineAddr, cmpInfo.getFirstAddr());
        // START 2016/07/21 K.Kojima [QC#11128,MOD]
        // setValue(tmsg.hdrCmpyNm,
        // ZYPCodeDataUtil.getVarCharConstValue("AR_HDR_PRINT_CMPY_NM",
        // this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCmpyNm, this.arHdrPrintCmpyNm);
        // END 2016/07/21 K.Kojima [QC#11128,MOD]
        // START 2021/05/13 K.Suzuki [QC#58340,MOD]
        //setValue(tmsg.hdrOfcFirstLineAddr, ZYPCodeDataUtil.getVarCharConstValue("AR_HDR_PRINT_OFC_FIRST_ADDR", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcFirstLineAddr, cmpInfo.getFirstAddr());
        // END 2021/05/13 K.Suzuki [QC#58340,MOD]
        // [QC#7310] UPDATE end
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcScdLineAddr, cmpInfo.getScdAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcThirdLineAddr, cmpInfo.getThirdAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcFrthLineAddr, cmpInfo.getFrthAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcCtyAddr, cmpInfo.getCityAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcProvNm, cmpInfo.getProvNm());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcStCd, cmpInfo.getStCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcPostCd, cmpInfo.getPostCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrOfcTelNum, cmpInfo.getTelNum());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCmpyUrl, cmpInfo.getUrl());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrDsAcctNum, hdr.getCltAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrBillToCustCd, hdr.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrDunIssDt, this.batProcDt);
        // START 2016/07/21 K.Kojima [QC#11128,MOD]
        // setValue(tmsg.hdrRemCmpyNm, hdr.getCmpyNm());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrRemCmpyNm, this.arHdrPrintCmpyNm);
        // END 2016/07/21 K.Kojima [QC#11128,MOD]
        ZYPEZDItemValueSetter.setValue(tmsg.hdrFirstRemToLocNm, hdr.getRemFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrScdRemToLocNm, hdr.getRemScdLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrThirdRemToLineAddr, hdr.getRemThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrFrthRemToLineAddr, hdr.getRemFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrRemToCtyAddr, hdr.getRemCtyAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrRemToProvNm, hdr.getRemProvNm());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrRemToStCd, hdr.getRemStCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrRemToPostCd, hdr.getRemPostCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustNm, hdr.getLocNm());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrAddlLocNm, hdr.getAddlLocNm());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustFirstLineAddr, hdr.getBtcFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustScdLineAddr, hdr.getBtcScdLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustThirdLineAddr, hdr.getBtcThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustFrthLineAddr, hdr.getBtcFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustCtyAddr, hdr.getBtcCtyAddr());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustProvNm, hdr.getBtcProvNm());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustStCd, hdr.getBtcStCd());
        ZYPEZDItemValueSetter.setValue(tmsg.hdrCustPostCd, hdr.getBtcPostCd());

        if (rs != null) {
            // detail
            // START 2021/08/10 K.Suzuki [QC#58340-4,MOD]
            //if (this.batProcDt.compareTo(rs.getString("INV_DUE_DT")) > 0) {
            if (this.batProcDt.compareTo(rs.getString("INV_DUE_DT")) >= 0) {
                // END   2021/08/10 K.Suzuki [QC#58340-4,MOD]
                ZYPEZDItemValueSetter.setValue(tmsg.invPastDueFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tmsg.invPastDueFlg, ZYPConstant.FLG_OFF_N);
            }

            // START 2018/03/29 J.Kim [QC#21733,MOD]
            // setValue(tmsg.dtlInvNum, rs.getString("AR_TRX_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlInvNum, rs.getString("AR_CUST_REF_NUM"));
            // END 2018/03/29 J.Kim [QC#21733,MOD]
            ZYPEZDItemValueSetter.setValue(tmsg.dtlInvDueDt, rs.getString("INV_DUE_DT"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlInvCatgTxt, dtlInvCatgTxt);
            ZYPEZDItemValueSetter.setValue(tmsg.dtlDealCcySgnTxt, rs.getString("CCY_SGN_TXT"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlInvRmngBalAmt, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlRefOrdNum, nullVal(rs.getString("DS_CONTR_NUM"), rs.getString("CPO_ORD_NUM")));

            if (DS_CONTR_CATG.FLEET.equals(rs.getString("DS_CONTR_CATG_CD"))) {
                ZYPEZDItemValueSetter.setValue(tmsg.dtlSerNum, "FLEET");
            } else {
                ZYPEZDItemValueSetter.setValue(tmsg.dtlSerNum, rs.getString("SER_NUM"));
            }

            ZYPEZDItemValueSetter.setValue(tmsg.dtlBllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlBllgPerToDt, rs.getString("BLLG_PER_TO_DT"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlCustIssPoNumTxt, rs.getString("CUST_ISS_PO_NUM"));
            // START 2018/06/18 E.Kameishi [QC#25343,MOD]
            //setValue(tmsg.dtlLateDaysAot, rs.getBigDecimal("DTL_LATE_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(tmsg.dtlLateDaysAot, changeNumIfNumOver(rs.getBigDecimal("DTL_LATE_DAYS_AOT")));
            // END 2018/06/18 E.Kameishi [QC#25343,MOD]

        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.invPastDueFlg, ZYPConstant.FLG_OFF_N);
        }

        // Footer
        // [QC#7310] UPDATE start
//        setValue(tmsg.ftrRpyToCltPsnNm, nullVal(hdr.getCltPsnNmBill(), hdr.getCltPsnNmAcct()));
//        setValue(tmsg.ftrRpyToFaxNum, cltUsrInfo.getUserDetails().getFaxNumber());
//        setValue(tmsg.ftrRpyToTelNum, cltUsrInfo.getUserDetails().getWorkPhoneNumber());
//        setValue(tmsg.ftrRpyToEmlAddr, cltUsrInfo.getUserDetails().getEmailAddress());
        ZYPEZDItemValueSetter.setValue(tmsg.ftrRpyToCltPsnNm, hdr.getCltPsnNm());
        ZYPEZDItemValueSetter.setValue(tmsg.ftrRpyToFaxNum, hdr.getCltStmtFaxNum());
        ZYPEZDItemValueSetter.setValue(tmsg.ftrRpyToTelNum, hdr.getCltStmtPhoNum());
        ZYPEZDItemValueSetter.setValue(tmsg.ftrRpyToEmlAddr, hdr.getCltPsnEmlAddr());
        // [QC#7310] UPDATE end

        // count up
        normTmpCnt += 1;

        return tmsg;
    }

    private String nullVal(String val, String rpls) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        }

        return rpls;
    }

    @SuppressWarnings("unused")
    private List<Map<String, Object>> getDunLtrWrk(String billToCustCd, String dunLtrTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("billToCustCd", billToCustCd);
        queryParam.put("dunLtrTpCd", dunLtrTpCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDunLtrWrk", queryParam);

    }

    private String cmplDtlInvCatgTxt(String svcInvNum, String dsOrdCatgNm) {
        String rslt = "Usage";

        if (ZYPCommonFunc.hasValue(svcInvNum)) {
            // get SVC_INV_CHRG_TP_CD
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("svcInvNum", svcInvNum);

            List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcInvChrgTp", queryParam);

            if (result.size() == 1) {
                Map<String, Object> rec = result.get(0);
                String chrgTp = (String) rec.get("SVC_INV_CHRG_TP_CD");

                if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(chrgTp)) {
                    rslt = "Service";
                }
            } else if (result.size() > 1) {
                boolean isBC = false;
                for (Map<String, Object> rec : result) {

                    String chrgTp = (String) rec.get("SVC_INV_CHRG_TP_CD");

                    if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(chrgTp)) {
                        isBC = true;
                    }

                }

                if (isBC) {
                    rslt = "CONS";
                } else {
                    rslt = "Usage";
                }

            }
        } else {
            rslt = dsOrdCatgNm;
        }

        return rslt;

    }

    private PreparedStatement getDetailData(String billToCustCd) throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("billToCustCd", billToCustCd);
        queryParam.put("arTrxTpCrm", AR_TRX_TP.CREDIT_MEMO);
        queryParam.put("arTrxTpAcc", AR_TRX_TP.ON_ACCOUNT);
        queryParam.put("arTrxTpRcpt", AR_TRX_TP.RECEIPT);
        queryParam.put("unApply", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("batProcDt", this.batProcDt);
        //---- start 2016/04/19 add
        queryParam.put("ded", AR_TRX_TP.DEDUCTION);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        //---- end 2016/04/19

        return this.ssmLLClient.createPreparedStatement("getDetailData", queryParam, getExecPrm());

    }

    private List<TargetData> getTargetData() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("batProcDt", this.batProcDt);
        queryParam.put("wrkTpManual", CLT_WRK_TP.MANUAL);
        queryParam.put("wrkItmStsPending", CLT_WRK_ITEM_STS.PENDING);
        // START 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("wrkItmStsOpen", CLT_WRK_ITEM_STS.OPEN);
        // END 2017/06/26 E.Kameishi [QC#18754, ADD]
        // START 2021/08/13 K.Suzuki [QC#59070, ADD]
        String autoExeManualWrkItemCd = ZYPCodeDataUtil.getVarCharConstValue(AUTO_EXE_MANUAL_WRK_ITEM_CD, this.glblCmpyCd);
        List<String> autoExeManualWrkItemCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(autoExeManualWrkItemCd)) {
            autoExeManualWrkItemCdList = Arrays.asList(autoExeManualWrkItemCd.split(","));
        } else {
            autoExeManualWrkItemCdList = null;
        }
        queryParam.put("autoExeManualWrkItemCdList", autoExeManualWrkItemCdList);
        // END   2021/08/13 K.Suzuki [QC#59070, ADD]

        return (List<TargetData>) ssmBatchClient.queryObjectList("getTargetData", queryParam);
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NFDB002001().executeBatch(NFDB002001.class.getSimpleName());

    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }
    // START 2018/06/18 E.Kameishi [QC#25343,ADD]
    /**
     * changeNumIfNumOver
     * @return BigDecimal
     */
    private BigDecimal changeNumIfNumOver(BigDecimal num) {
        BigDecimal maxNum = new BigDecimal(MAX_LATE_DAYS);
        if (maxNum.compareTo(num) < 0) {
            return maxNum;
        }
        return num;
    }
    // END 2018/06/18 E.Kameishi [QC#25343,ADD]
}
