/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB131001;

import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.AP_VND_INV_SQ_NUM_DEF;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.AP_VND_TP_CD_02;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.COA_CONST_GRP_ID;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.COA_CONST_RESRC_ID;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.DEFAULT_DB_NONE;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.INTERFACE_ID_CUSA_PARTS;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.LEN_AP_VND_INV_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.LEN_CM_INV_ACCT_DIST_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NAZM0173E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0044E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0073E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0202E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0203E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0224E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0270E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0275E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0276E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0277E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0288E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFBM0289E;
import static com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.NFCM0508E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_DEF_ACCTTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_INV_ACCT_DISTTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_CONSTTMsg;
import business.db.CR_DR_RSNTMsg;
import business.db.MDSETMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.VND_INV_BOL_WRKTMsg;
import business.db.VND_INV_BOL_WRKTMsgArray;
import business.db.VND_INV_ERR_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsgArray;
import business.db.VND_INV_WRKTMsg;
import business.db.VND_RTRNTMsg;
import business.parts.NFBCommonBusiness;
import business.parts.NFBCommonBusiness.DefCoaValues;

import com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.AR_PROC_FLG;
import com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.DTO_COL_NAME;
import com.canon.cusa.s21.batch.NFB.NFBB131001.NFBB131001Constant.VAR_CHAR_CONST;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_INV_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * CSMP Credit Memo Data Validation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   CSAI            Y.Miyauchi      Create          N/A
 * 2016/07/27   Fujitsu         S.Yoshida       Update          QC#12535
 * 2016/08/01   CSAI            Y.Miyauchi      Create          QC#12627
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12825
 * 09/20/2016   CSAI            Y.Imazu         Update          QC#14435
 * 09/30/2016   Hitachi         K.Kasai         Update          QC#14797
 * 10/05/2016   CSAI            Y.Imazu         Update          QC#14435
 * 10/05/2016   Hitachi         K.Kasai         Update          QC#14797
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#13335
 * 2016/11/02   Hitachi         E.Kameishi      Update          QC#14821
 * 2016/11/04   Hitachi         J.Kim           Update          QC#15659
 * 2016/11/04   Hitachi         T.Tsuchida      Update          QC#15798
 * 2016/12/13   Hitachi         K.Kasai         Update          QC#16132
 * 2016/12/20   Fujitsu         H.Ikeda         Update          QC#15823
 * 2016/12/26   Hitachi         K.Kasai         Update          QC#16700
 * 2017/10/26   CITS            K.Ogino         Update          QC#22403
 * 2017/12/18   Hitachi         Y.Takeno        Update          QC#23022
 * 2018/01/11   Hitachi         Y.Takeno        Update          QC#23436
 * 2018/01/12   Hitachi         Y.Takeno        Update          QC#23297
 * 2018/02/20   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/03/27   CITS            T.Kikuhara      Update          QC#20316
 * 2018/05/14   Hitachi         Y.Takeno        Update          QC#25601
 * 2018/06/26   CITS            S.Katsuma       Update          QC#26566
 * 2018/09/06   Fujitsu         S.Ohki          Update          QC#28040
 * 2019/04/09   Hitachi         Y.Takeno        Update          QC#31071
 * 2019/04/11   Hitachi         Y.Takeno        Update          QC#31071
 * 2019/05/17   Hitachi         Y.Takeno        Update          QC#50204
 * 2019/08/20   Hitachi         Y.Takeno        Update          QC#52280
 * 2020/05/20   Fujitsu         H.Ikeda         Update          QC#56270
 *</pre>
 */
public class NFBB131001 extends S21BatchMain {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Normal Record Count */
    private int normalRcCnt = 0;

    /** Total Record Count */
    private int totRcCnt = 0;

    /** Error Record Count */
    private int errRcCnt = 0;

    /** VAR_CHAR_CONST : Vendor Invoice Type Code (Invoice) */
    private String vndInvTpCdInv;

    /** VAR_CHAR_CONST : Vendor Invoice Type Code (Credit Memo) */
    private String vndInvTpCdCr;

    /** VAR_CHAR_CONST : Credit/Debit Reason Code (CSMP) */
    private String vndCrDrRsnCdCsmp;

    /** VAR_CHAR_CONST : Credit/Debit Reason Code (Growth Bonus) */
    private String vndCrDrRsnCdGrowthBonus;

    /** VAR_CHAR_CONST : Credit/Debit Reason Code (Tax) */
    private String vndCrDrRsnCdTax;

    /** VAR_CHAR_CONST : Credit/Debit Reason Code (Freight) */
    private String vndCrDrRsnCdFrt;

    /** VAR_CHAR_CONST : Credit/Debit Reason Code (Return) */
    private List<String> vndCrDrRsnCdRtrnList;

    // START 2020/05/20 [QC#56270, ADD]
    /** VAR_CHAR_CONST : EXTRA_COA_AFFL_CD */
    private List<String> extraCoaAfflCdList;
    // END   2020/05/20 [QC#56270, ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB131001().executeBatch(NFBB131001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        normalRcCnt = 0;
        totRcCnt = 0;
        errRcCnt = 0;

        // Varchar Constant (Vendor Invoice Type)
        vndInvTpCdInv = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_INV_TP_CD_INV.toString(), this.glblCmpyCd);
        vndInvTpCdCr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_INV_TP_CD_CR.toString(), this.glblCmpyCd);

        // Varchar Constant (Vendor CR/DR Reason)
        vndCrDrRsnCdCsmp = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_CSMP.toString(), this.glblCmpyCd);
        vndCrDrRsnCdGrowthBonus = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_GROWTH_BONUS.toString(), this.glblCmpyCd);
        vndCrDrRsnCdTax = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_CR_DR_RSN_TAX.toString(), this.glblCmpyCd);
        vndCrDrRsnCdFrt = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_CR_DR_RSN_FRT.toString(), this.glblCmpyCd);
        vndCrDrRsnCdRtrnList = splitStringTxt(ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_CR_DR_RSN_RTRN.toString(), this.glblCmpyCd));

        // START 2020/05/20 [QC#56270, ADD]
        extraCoaAfflCdList = splitStringTxt(ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_EXTRA_COA_AFFL_CD.toString(), this.glblCmpyCd));
        // END   2020/05/20 [QC#56270, ADD]
        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        String vndCdDef = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFBB131001_VND_CD_DEF.toString(), this.glblCmpyCd);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", this.glblCmpyCd);
        queryMap.put("processFlgP", AR_PROC_FLG.PROCESS.getValue());
        queryMap.put("crDrRsnCd10", vndCrDrRsnCdCsmp);
        queryMap.put("invTpCdInv", vndInvTpCdInv);
        queryMap.put("vndInvProcStsCdProc", VND_INV_PROC_STS.PROCESSED);
        queryMap.put("glblCmpyCdCusa", "ABR");
        // START 2019/08/20 [QC#52280, ADD]
        queryMap.put("cmProcStsCdN", ZYPConstant.FLG_OFF_N);
        // END   2019/08/20 [QC#52280, ADD]

        List<Map<String, Object>> vndInvWrkMapList = (ArrayList<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTarget", queryMap);

        // No Search Result
        if (vndInvWrkMapList == null || vndInvWrkMapList.isEmpty()) {

            totRcCnt = 0;
            normalRcCnt = 0;
            errRcCnt = 0;

        } else {

            CM_DEF_ACCTTMsg cmDefAcctTaxTMsg = getCmDefAcctByKey(CM_DEF_ACCT.TAX_COA);
            CM_DEF_ACCTTMsg cmDefAcctFrtTMsg = getCmDefAcctByKey(CM_DEF_ACCT.FREIGHT_COA);

            totRcCnt = vndInvWrkMapList.size();

            for (Map<String, Object> vndInvWrkMap : vndInvWrkMapList) {

                String vndInvNum = (String) vndInvWrkMap.get("VND_INV_NUM");
                String vndCd = (String) vndInvWrkMap.get("VND_CD");
                // START 2019/05/17 [QC#50204, DEL]
                // String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
                // END   2019/05/17 [QC#50204, DEL]

                // Check Vendor Return Order
                VND_RTRNTMsg vndRtrnTMsg = new VND_RTRNTMsg();
                String vndRtrnNum = (String) vndInvWrkMap.get("CUST_ISS_PO_NUM");
                boolean vndRtrnChkSkip = false;

                // if target data is Vendor Return
                // START 2019/05/17 [QC#50204, MOD]
                // if (isVndRtrn(crDrRsnCd)) {
                if (isVndRtrn(vndInvWrkMap)) {
                // END   2019/05/17 [QC#50204, MOD]

                    // START 2019/05/17 [QC#50204, DEL]
                    //// get PO number
                    // String poOrdNum = getPoOrdNum(vndInvNum);
                    // 
                    //// check PO Number and Customer Issue PO Number
                    // if (!ZYPCommonFunc.hasValue(poOrdNum) && !ZYPCommonFunc.hasValue(vndRtrnNum)) {
                    //
                    //    setVndInvWrkErr(vndInvNum, NAZM0173E, null);
                    //    errRcCnt++;
                    //     continue;
                    // 
                    // } else if (ZYPCommonFunc.hasValue(poOrdNum)) {
                    //
                    //  vndRtrnNum = poOrdNum;
                    //  vndRtrnChkSkip = true;
                    //
                    // START 2018/01/12 [QC#23297, ADD]
                    // START 2018/05/14 [QC#25601, DEL]
                    // } else {
                    //     setVndInvWrkErr(vndInvNum, NFBM0202E, null);
                    //     errRcCnt++;
                    //     continue;
                    // END   2018/05/14 [QC#25601, DEL]
                    // }
                    // END   2018/01/12 [QC#23297, ADD]
                    // END   2019/05/17 [QC#50204, DEL]

                    // START 2019/05/17 [QC#50204, ADD]
                    // check Customer Issue PO Number
                    if (!ZYPCommonFunc.hasValue(vndRtrnNum)) {
                        setVndInvWrkErr(vndInvNum, NAZM0173E, null);
                        errRcCnt++;
                        continue;
                    }
                    // END   2019/05/17 [QC#50204, ADD]

                    // START 2018/05/14 [QC#25601, MOD]
                    // ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.glblCmpyCd, this.glblCmpyCd);
                    // ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.vndRtrnNum, vndRtrnNum);
                    // vndRtrnTMsg = (VND_RTRNTMsg) EZDTBLAccessor.findByKey(vndRtrnTMsg);
                    vndRtrnTMsg = getVndRtrnNum(vndRtrnNum);
                    // END   2018/05/14 [QC#25601, MOD]

                    if (vndRtrnTMsg == null) {

                        if (!vndRtrnChkSkip) {

                            setVndInvWrkErr(vndInvNum, NFBM0202E, null);
                            errRcCnt++;
                            continue;
                        }

                    } else {

                        vndCd = vndRtrnTMsg.billToVndCd.getValue();
                    }
                }

                // Set Default Vendor Code
                if (!ZYPCommonFunc.hasValue(vndCd)) {

                    vndCd = vndCdDef;
                }

                // START 2019/08/20 [QC#52280, DEL]
                // // Validation
                // if (isCheckError(vndInvNum, vndCd)) {
                //     errRcCnt++;
                //     continue;
                // }
                // END   2019/08/20 [QC#52280, DEL]

                // START 2018/03/27 [QC#20316, DEL]
                // Insert CM_INV_ACCT_HDR
                //if (!insertCmInvAcctHdr(vndInvWrkMap, vndCd, vndRtrnNum)) {
                //
                //    errRcCnt++;
                //    continue;
                //}
                // END 2018/03/27 [QC#20316, DEL]

                // START 2018/03/27 [QC#20316, ADD]
                // Get Vendor Information
                queryMap = new HashMap<String, Object>();
                queryMap.put("glblCmpyCd", this.glblCmpyCd);
                queryMap.put("invVndCd", vndCd);
                // START 2019/04/09 [QC#31071, ADD]
                queryMap.put("splyPmtFlgY", ZYPConstant.FLG_ON_Y);
                queryMap.put("splyPmtFlgN", ZYPConstant.FLG_OFF_N);
                // END   2019/04/09 [QC#31071, ADD]

                List<Map<String, Object>> vndInfoList = (ArrayList<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getVndInfo", queryMap);
                if (vndInfoList == null || vndInfoList.size() != 1) {
                    // START 2019/08/20 [QC#52280, ADD]
                    setVndInvWrkErr(vndInvNum, NFBM0289E, null);
                    // END   2019/08/20 [QC#52280, ADD]
                    errRcCnt++;
                    continue;
                }
                // END 2018/03/27 [QC#20316, ADD]

                // START 2019/04/09 [QC#31071, ADD]
                String splyPmtFlg = (String) vndInfoList.get(0).get("SPLY_PMT_FLG");
                // START 2019/05/17 [QC#50204, MOD]
                // if (isVndRtrn(crDrRsnCd) && ZYPConstant.FLG_OFF_N.equals(splyPmtFlg)) {
                if (isVndRtrn(vndInvWrkMap) && ZYPConstant.FLG_OFF_N.equals(splyPmtFlg)) {
                // END   2019/05/17 [QC#50204, MOD]
                    
                    List<Map<String, Object>> paySiteVndInfoList = (ArrayList<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPaySiteVndInfo", queryMap);
                    if (paySiteVndInfoList != null && paySiteVndInfoList.size() == 1) {
                        vndInfoList = paySiteVndInfoList;
                    // START 2019/08/20 [QC#52280, ADD]
                    } else {
                        setVndInvWrkErr(vndInvNum, NFBM0289E, null);
                        errRcCnt++;
                        continue;
                    }
                    // END   2019/08/20 [QC#52280, ADD]
                }
                // END   2019/04/09 [QC#31071, ADD]

                // START 2019/08/20 [QC#52280, ADD]
                if (isCheckError(vndInvWrkMap, vndInvNum, vndCd, vndInfoList)) {
                    setVndInvWrkErr(vndInvNum, NFBM0288E, null);
                    errRcCnt++;
                    continue;
                }
                // START 2019/08/20 [QC#52280, ADD]

                // Insert CM_INV_ACCT_DIST
                // START 2018/03/27 [QC#20316, MOD]
                if (!insertCmInvAcctDist(vndInvWrkMap, cmDefAcctTaxTMsg, cmDefAcctFrtTMsg, vndCd, vndRtrnChkSkip, vndRtrnNum, vndInfoList)) {
                // END 2018/03/27 [QC#20316, MOD]

                    errRcCnt++;
                    continue;
                }

                // Insert CM_AP_INV_HDR & DTL
                // START 2018/03/27 [QC#20316, MOD]
                if (!insertCmApInvoice(vndInvWrkMap, vndCd, vndRtrnNum, cmDefAcctTaxTMsg, cmDefAcctFrtTMsg, vndInfoList)) {
                // END 2018/03/27 [QC#20316, MOD]
                    errRcCnt++;
                    continue;
                }

                // START 2019/05/17 [QC#50204, ADD]
                if (!updateVndInvBolWrk(vndInvWrkMap, vndCd, vndRtrnNum, vndInfoList)) {
                    errRcCnt++;
                    continue;
                }
                // END   2019/05/17 [QC#50204, ADD]

                // START 2019/08/20 [QC#52280, ADD]
                if (!updateVndInvWrk(vndInvWrkMap, vndCd)) {
                    errRcCnt++;
                    continue;
                }
                // END   2019/08/20 [QC#52280, ADD]

//                commit();
                normalRcCnt++;
            }
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        super.setTermState(TERM_CD.NORMAL_END, this.normalRcCnt, this.errRcCnt, this.totRcCnt);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    /**
     * getPoOrdNum
     * @param vndInvNum String
     * @return String
     */
    private String getPoOrdNum(String vndInvNum) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", this.glblCmpyCd);
        queryMap.put("vndInvNum", vndInvNum);

        List<String> poOrdNumList = (ArrayList<String>) this.ssmBatchClient.queryObjectList("getPoOrdNum", queryMap);

        if (poOrdNumList == null || poOrdNumList.isEmpty() || poOrdNumList.size() > 1) {

            return null;
        }

        return poOrdNumList.get(0);
    }

    /**
     * setVndInvWrkErr
     * @param vndInvNum String
     * @param errMsgId String
     * @param vndInvLineWrkTMsg VND_INV_LINE_WRKTMsg
     */
    private void setVndInvWrkErr(String vndInvNum, String errMsgId, VND_INV_LINE_WRKTMsg vndInvLineWrkTMsg) {

        S21InfoLogOutput.println(errMsgId);

        VND_INV_WRKTMsg vndInvWrkTMsg = new VND_INV_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(vndInvWrkTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvWrkTMsg.vndInvNum, vndInvNum);
        vndInvWrkTMsg = (VND_INV_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(vndInvWrkTMsg);

        ZYPEZDItemValueSetter.setValue(vndInvWrkTMsg.vndInvProcStsCd, VND_INV_PROC_STS.ERROR);
        EZDTBLAccessor.update(vndInvWrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvWrkTMsg.getReturnCode())) {

            throw new S21AbendException(NFBM0224E, new String[] {"VND_INV_WRK", vndInvWrkTMsg.getReturnCode() });

        } else {

            VND_INV_ERR_WRKTMsg vndInvErrWrkTMsg = new VND_INV_ERR_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvErrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.VND_INV_ERR_WRK_SQ));
            ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvNum, vndInvNum);

            if (vndInvLineWrkTMsg != null) {

                ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvBolLineNum, vndInvLineWrkTMsg.vndInvBolLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvLineNum, vndInvLineWrkTMsg.vndInvLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvLineSubNum, vndInvLineWrkTMsg.vndInvLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvLineSubTrxNum, vndInvLineWrkTMsg.vndInvLineSubTrxNum.getValue());
            }

            ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.vndInvErrMsgId, errMsgId);
            ZYPEZDItemValueSetter.setValue(vndInvErrWrkTMsg.batErrMsgTxt, S21MessageFunc.clspGetMessage(errMsgId));
            EZDTBLAccessor.insert(vndInvErrWrkTMsg);
            // START 2016/12/09 H.Ikeda [QC#15823, ADD]
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvErrWrkTMsg.getReturnCode())) {
                throw new S21AbendException(NFBM0073E, new String[] {"VND_INV_ERR_WRK", vndInvErrWrkTMsg.getReturnCode() });
            }
            // END 2016/12/09 H.Ikeda [QC#15823, ADD]
            commit();
        }
    }

    /**
     * Method for Validation
     * @param vndInvWrkMap Map<String, Object>
     * @param vndInvNum String
     * @param vndCd String
     * @param vndInfoList List<Map<String, Object>>
     * @return boolean
     */
    // START 2019/08/20 [QC#52280, MOD]
    private boolean isCheckError(Map<String, Object> vndInvWrkMap, String vndInvNum, String vndCd, List<Map<String, Object>> vndInfoList) {

        // START 2018/03/27 [QC#20316, DEL]
        //CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, this.glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, vndInvNum);
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_DEF);
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, vndCd);
        //
        //cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKey(cmInvAcctHdrTMsg);
        //
        //if (cmInvAcctHdrTMsg != null) {
        //
        //    return true;
        //}
        // END 2018/03/27 [QC#20316, DEL]

        // START 2018/03/27 [QC#20316, ADD]
        CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
        // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, vndInvNum);
        // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_DEF);
        //  ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, vndCd);
        cmInvAcctDistTMsg.setSQLID("001");
        cmInvAcctDistTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        cmInvAcctDistTMsg.setConditionValue("apVndInvNum01", vndInvNum);
        cmInvAcctDistTMsg.setConditionValue("apVndInvSqNum01", AP_VND_INV_SQ_NUM_DEF);
        if (isVndRtrn(vndInvWrkMap)) {
            cmInvAcctDistTMsg.setConditionValue("apVndCd01", (String) vndInfoList.get(0).get("INV_VND_CD"));
        } else {
            cmInvAcctDistTMsg.setConditionValue("apVndCd01", vndCd);
        }

        // cmInvAcctDistTMsg = (CM_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKey(cmInvAcctDistTMsg);
        // if (cmInvAcctDistTMsg != null) {
        //     return true;
        // }
        CM_INV_ACCT_DISTTMsgArray result = (CM_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(cmInvAcctDistTMsg);
        if (result != null && result.getValidCount() > 0) {
            return true;
        }

        // END 2018/03/27 [QC#20316, ADD]

        return false;
    }
    // END   2019/08/20 [QC#52280, MOD]

// START 2018/03/27 [QC#20316, DEL]
//    /**
//     * Insert CM_INV_ACCT_HDR
//     * @param vndInvWrkMap Map<String, Object>
//     * @param vndCd String
//     * @param vndRtrnNum String
//     * @return boolean
//     */
//    private boolean insertCmInvAcctHdr(Map<String, Object> vndInvWrkMap, String vndCd, String vndRtrnNum) {
//
//        // Get Vendor Information
//        Map<String, Object> queryMap = new HashMap<String, Object>();
//        queryMap.put("glblCmpyCd", this.glblCmpyCd);
//        queryMap.put("invVndCd", vndCd);
//
//        List<Map<String, Object>> vndInfoList = (ArrayList<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getVndInfo", queryMap);
//
//        if (vndInfoList == null || vndInfoList.size() != 1) {
//
//            return false;
//
//        } else {
//
//            BigDecimal acInvTotCostAmt = BigDecimal.ZERO;
//
//            if (ZYPCommonFunc.hasValue((BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_NET_AMT"))) {
//
//                acInvTotCostAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_NET_AMT");
//
//                if (vndInvTpCdCr.equals((String) vndInvWrkMap.get("INV_TP_CD"))) {
//
//                    acInvTotCostAmt = acInvTotCostAmt.negate();
//                }
//            }
//
//            CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, vndCd);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, (String) vndInvWrkMap.get("VND_INV_NUM"));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_DEF);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.invDt, (String) vndInvWrkMap.get("INV_DT"));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acInvTotCostAmt, acInvTotCostAmt);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaCmpyCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_CMPY_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaBrCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_BR_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaCcCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_CC_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaAcctCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_ACCT_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaProdCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_PROD_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaChCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_CH_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaAfflCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_AFFL_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaProjCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_PROJ_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaExtnCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_EXTN_CD.toString()));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
//            // START 2016/10/20 K.Kojima [QC#13335,ADD]
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.prntVndCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.PRNT_VND_CD.toString()));
//            // END 2016/10/20 K.Kojima [QC#13335,ADD]
//
//            // AP Invoice Category
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apInvCatgCd, AP_INV_CATG.OTHER);
//
//            String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
//
//            if (ZYPCommonFunc.hasValue(crDrRsnCd)) {
//
//                if (crDrRsnCd.equals(vndCrDrRsnCdCsmp)) {
//
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apInvCatgCd, AP_INV_CATG.CSMP);
//
//                } else if (crDrRsnCd.equals(vndCrDrRsnCdGrowthBonus)) {
//
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apInvCatgCd, AP_INV_CATG.GROWTH_BONUS);
//                }
//            }
//
//            // Vendor Return
//            if (ZYPCommonFunc.hasValue(vndRtrnNum)) {
//
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.poNum, vndRtrnNum);
//            }
//
//            // Insert
//            EZDTBLAccessor.insert(cmInvAcctHdrTMsg);
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
//
//                // START 2016/12/26 [QC#16700, ADD]
//                rollback();
//                setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0271E, null);
//                // END 2016/12/26 [QC#16700, ADD]
//                return false;
//            }
//
//            return true;
//        }
//    }
// END 2018/03/27 [QC#20316, DEL]

    // START 2018/03/27 [QC#20316, MOD]
    /**
     * Insert CM_INV_ACCT_DIST
     * @param vndInvWrkMap Map<String, Object>
     * @param cmDefAcctTaxTMsg CM_DEF_ACCTTMsg
     * @param cmDefAcctFrtTMsg CM_DEF_ACCTTMsg
     * @param vndCd String
     * @param vndRtrnChkSkip boolean
     * @param vndRtrnNum String
     * @param vndInfoList List<Map<String, Object>>
     * @return boolean
     */
    private boolean insertCmInvAcctDist(Map<String, Object> vndInvWrkMap, CM_DEF_ACCTTMsg cmDefAcctTaxTMsg, CM_DEF_ACCTTMsg cmDefAcctFrtTMsg, String vndCd, boolean vndRtrnChkSkip, String vndRtrnNum, List<Map<String, Object>> vndInfoList) {
    // END 2018/03/27 [QC#20316, MOD]

        Map<String, CM_INV_ACCT_DISTTMsg> cmInvAcctDistTMsgMap = new HashMap<String, CM_INV_ACCT_DISTTMsg>();
        List<String> mdseCdList = new ArrayList<String>();

        // Get VND_INV_LINE_WRK
        VND_INV_LINE_WRKTMsgArray vndInvLineWrkTMsgAry = getVndInvLineWrk(vndInvWrkMap);
        if (vndInvLineWrkTMsgAry.length() == 0) {
            // START 2016/12/26 [QC#16700, ADD]
            rollback();
            setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0203E, null);
            // END 2016/12/26 [QC#16700, ADD]
            return false;
        }

        String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
        String invTpCd = (String) vndInvWrkMap.get("INV_TP_CD");
        String dealCcyCd = (String) vndInvWrkMap.get("DEAL_CCY_CD");
        int newScale = this.getCcyScale(dealCcyCd);

        // Get CM_DEF_ACCT Data for COA
        CM_DEF_ACCTTMsg cmDefAcctTMsg = setCmDefAcctData(crDrRsnCd);

        // START 2016/09/30 [QC#14797, ADD]
        int apVndInvAcctDistNum = 1;
        // END 2016/09/30 [QC#14797, ADD]

        VND_INV_LINE_WRKTMsg vndInvLineWrkTMsg = new VND_INV_LINE_WRKTMsg();
        for (int i = 0; i < vndInvLineWrkTMsgAry.length(); i++) {

            vndInvLineWrkTMsg = vndInvLineWrkTMsgAry.no(i);

            PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();

            // Merchandise Code & AP Line Type
            String mdseCd = DEFAULT_DB_NONE;
            String mdseDescShortTxt = "";
            String apLineTpCd = AP_LINE_TP.ITEM;

            if (ZYPCommonFunc.hasValue(vndInvLineWrkTMsg.mdseCd)) {

                mdseCd = vndInvLineWrkTMsg.mdseCd.getValue();
                // START 2017/12/19 [QC#23022, MOD]
                // mdseDescShortTxt = getMdseDescShortTxt(mdseCd);
                mdseDescShortTxt = vndInvLineWrkTMsg.mdseNm.getValue();
                // END   2017/12/19 [QC#23022, MOD]

            // START 2019/05/17  [QC#50204, MOD]
            // } else if (ZYPCommonFunc.hasValue(crDrRsnCd)) {
            } else if (ZYPCommonFunc.hasValue(crDrRsnCd) || isVndRtrn(vndInvWrkMap)) {
            // END   2019/05/17  [QC#50204, MOD]

                // in the case of vendor return
                // START 2019/05/17  [QC#50204, MOD]
                // if (vndCrDrRsnCdRtrnList.contains(crDrRsnCd)) {
                if (isVndRtrn(vndInvWrkMap)) {
                // END   2019/05/17  [QC#50204, MOD]
                    
                    if (ZYPCommonFunc.hasValue(vndInvLineWrkTMsg.vndMdseCd)) {

                        mdseCd = vndInvLineWrkTMsg.vndMdseCd.getValue();
                        // START 2017/12/19 [QC#23022, MOD]
                        // mdseDescShortTxt = getMdseDescShortTxt(mdseCd);
                        mdseDescShortTxt = vndInvLineWrkTMsg.mdseNm.getValue();
                        // END   2017/12/19 [QC#23022, MOD]
                    }

                    // in the case of NOT vendor return
                } else {

                    mdseCd = crDrRsnCd;
                    // START 2017/12/19 [QC#23022, MOD]
                    // mdseDescShortTxt = (String) vndInvWrkMap.get("CR_DR_RSN_DESC_TXT");
                    mdseDescShortTxt = vndInvLineWrkTMsg.mdseNm.getValue();
                    // END   2017/12/19 [QC#23022, MOD]

                    if (crDrRsnCd.equals(vndCrDrRsnCdTax)) {

                        apLineTpCd = AP_LINE_TP.TAX;

                    } else if (crDrRsnCd.equals(vndCrDrRsnCdFrt)) {

                        apLineTpCd = AP_LINE_TP.FREIGHT;
                    }
                }
            }

            // Quantity
            BigDecimal invQty = setAmount(vndInvLineWrkTMsg.shipQty);

            // Vendor Return Line Check
            // START 2019/05/17  [QC#50204, MOD]
            // if (ZYPCommonFunc.hasValue(crDrRsnCd) && vndCrDrRsnCdRtrnList.contains(crDrRsnCd) && ZYPCommonFunc.hasValue(vndRtrnNum) && invQty.compareTo(BigDecimal.ZERO) > 0) {
            if (isVndRtrn(vndInvWrkMap) && ZYPCommonFunc.hasValue(vndRtrnNum)) {
            // END   2019/05/17  [QC#50204, MOD]

                prchReqDtlTMsg = getPrchReqDtlForVndRtrn(vndRtrnNum, mdseCd, invQty, vndCd);

                if (prchReqDtlTMsg == null && !vndRtrnChkSkip) {

                    rollback();
                    setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0202E, vndInvLineWrkTMsg);
                    return false;
                }
            }

            // Amount
            BigDecimal thisMthFobCostAmt = setAmount(vndInvLineWrkTMsg.dealGrsTotPrcAmt);
            BigDecimal acInvTaxAmt = setAmount(vndInvLineWrkTMsg.vndInvLineDealTaxAmt);
            BigDecimal acInvJrnlCostAmt = setAmount(vndInvLineWrkTMsg.vndInvLineDealNetAmt);

            // START 2016/11/04 J.Kim [QC#15659,ADD]
            BigDecimal vndInvLineDealNetAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(vndInvLineWrkTMsg.vndInvLineDealNetAmt)) {
                BigDecimal vndInvLineAmt = vndInvLineWrkTMsg.vndInvLineDealNetAmt.getValue();
                vndInvLineDealNetAmt = vndInvLineAmt.divide(invQty, newScale, BigDecimal.ROUND_HALF_UP);
            }
            // END 2016/11/04 J.Kim [QC#15659,ADD]

            // Credit
            // START 2019/05/17  [QC#50204, MOD]
            if (vndInvTpCdCr.equals(invTpCd) && !isCUSAPartsVndRtrn(vndInvWrkMap)) {
            // END   2019/05/17  [QC#50204, MOD]

                invQty = invQty.negate();
                thisMthFobCostAmt = thisMthFobCostAmt.negate();
                acInvTaxAmt = acInvTaxAmt.negate();
                acInvJrnlCostAmt = acInvJrnlCostAmt.negate();
            }

            // START 2018/03/27 [QC#20316, MOD]
            // set CM_INV_ACCT_DIST data for insert
            // Same Merchandise Line Exist
            //if (!cmInvAcctDistTMsgMap.isEmpty() && cmInvAcctDistTMsgMap.containsKey(mdseCd)) {
            //
            //    CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = cmInvAcctDistTMsgMap.get(mdseCd);
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, cmInvAcctDistTMsg.invQty.getValue().add(invQty));
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, cmInvAcctDistTMsg.apBillQty.getValue().add(invQty));
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, cmInvAcctDistTMsg.thisMthFobCostAmt.getValue().add(thisMthFobCostAmt));
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, cmInvAcctDistTMsg.acInvTaxAmt.getValue().add(acInvTaxAmt));
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, cmInvAcctDistTMsg.acInvJrnlCostAmt.getValue().add(acInvJrnlCostAmt));
            //    // START 2016/10/05 [QC#14797, DEL]
            //    // START 2016/09/30 [QC#14797, ADD]
            //    // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum,
            //    // ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum),
            //    // 3, "0"));
            //    // apVndInvAcctDistNum++;
            //    // END 2016/09/30 [QC#14797, ADD]
            //    // END 2016/10/05 [QC#14797, DEL]
            //
            //    // START 2016/11/04 J.Kim [QC#15659,ADD]
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, vndInvLineDealNetAmt);
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, vndInvLineDealNetAmt);
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
            //    // END 2016/11/04 J.Kim [QC#15659,ADD]
            //
            //    cmInvAcctDistTMsgMap.put(mdseCd, cmInvAcctDistTMsg);
            //
            //} else {

            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = setCmInvAcctDist(vndInvWrkMap, cmDefAcctTMsg, vndCd, prchReqDtlTMsg, vndInfoList);
            // START 2019/04/11 [QC#31071, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.vndRtrnNum, vndRtrnNum);
            // START 2019/05/17  [QC#50204, MOD]
            // if (isVndRtrn(crDrRsnCd) && ZYPCommonFunc.hasValue(vndRtrnNum)) {
            if (isVndRtrn(vndInvWrkMap) && ZYPCommonFunc.hasValue(vndRtrnNum)) {
            // END   2019/05/17  [QC#50204, MOD]
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.vndRtrnNum, vndRtrnNum);
            }
            // END   2019/04/11 [QC#31071, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, mdseDescShortTxt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, invQty);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, invQty);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, thisMthFobCostAmt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, acInvTaxAmt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, acInvJrnlCostAmt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, apLineTpCd);
            // START 2016/09/30 [QC#14797, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_CM_INV_ACCT_DIST_LINE_NUM, "0"));
            // START 2018/03/27 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_AP_VND_INV_LINE_NUM, "0"));
            // END 2018/03/27 [QC#20316, ADD]
            apVndInvAcctDistNum++;
            // END 2016/09/30 [QC#14797, ADD]

            if (AP_LINE_TP.ITEM.equals(apLineTpCd) && vndCrDrRsnCdCsmp.equals(crDrRsnCd)) {

                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.csmpInvNum, getCsmpInvNum((String) vndInvWrkMap.get("CUST_ISS_PO_NUM")));
            }
            // START 2016/11/01 E.Kameishi [QC#14821, add]
            if (ZYPCommonFunc.hasValue(crDrRsnCd) && !vndCrDrRsnCdRtrnList.contains(crDrRsnCd)) {
                if (AP_LINE_TP.ITEM.equals(apLineTpCd)) {
                    COA_CONSTTMsg coaConstTMsg = new COA_CONSTTMsg();
                    ZYPEZDItemValueSetter.setValue(coaConstTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(coaConstTMsg.coaConstGrpId, COA_CONST_GRP_ID);
                    ZYPEZDItemValueSetter.setValue(coaConstTMsg.coaConstResrcId, COA_CONST_RESRC_ID);
                    ZYPEZDItemValueSetter.setValue(coaConstTMsg.coaConstCd, crDrRsnCd);
                    coaConstTMsg = (COA_CONSTTMsg) S21FastTBLAccessor.findByKey(coaConstTMsg);
                    if (coaConstTMsg == null) {
                        rollback();
                        setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFCM0508E, vndInvLineWrkTMsg);
                        return false;
                    }
                    // START 2018/03/27 [QC#20316, MOD]
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, coaConstTMsg.coaCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, coaConstTMsg.coaBrCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, coaConstTMsg.coaCcCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, coaConstTMsg.coaAcctCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, coaConstTMsg.coaProdCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, coaConstTMsg.coaChCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, coaConstTMsg.coaAfflCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, coaConstTMsg.coaProjCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, coaConstTMsg.coaExtnCd);
                    // END 2018/03/27 [QC#20316, MOD]

                    // START 2018/09/06 [QC#28040, ADD]
                    String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, cmInvAcctDistTMsg.drCoaAcctCd.getValue());
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apAcctDescTxt, coaAcctDescTxt);

                    EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
                    int txtDigit = getDigitApInvDescTxt.getDigit();

                    if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
                    } else {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, coaAcctDescTxt);
                    }
                    // END 2018/09/06 [QC#28040, ADD]
                }
            }
            // END 2016/11/01 E.Kameishi [QC#14821, add]
            // START 2016/11/04 J.Kim [QC#15659,ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, vndInvLineDealNetAmt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, vndInvLineDealNetAmt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
            // END 2016/11/04 J.Kim [QC#15659,ADD]

            cmInvAcctDistTMsgMap.put(mdseCd, cmInvAcctDistTMsg);
            mdseCdList.add(mdseCd);
            //}

            // Insert
            EZDTBLAccessor.insert(cmInvAcctDistTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctDistTMsg.getReturnCode())) {
                rollback();
                setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0270E, null);
                return false;
            }
            // END 2018/03/27 [QC#20316, MOD]
        }

        // START 2018/03/27 [QC#20316, DEL]
        // Insert CM_INV_ACCT_DIST
        // START 2016/12/09 H.Ikeda [QC#15823, MOD]
        //if (mdseCdList.size() > 0) {
        //    CM_INV_ACCT_DISTTMsg[] cmInvAcctDistTMsgs = new CM_INV_ACCT_DISTTMsg[mdseCdList.size()];
        //    int listCnt = 0;
        //    for (String mdseCd : mdseCdList) {
        //
        //        CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = cmInvAcctDistTMsgMap.get(mdseCd);
        //        cmInvAcctDistTMsgs[listCnt] = cmInvAcctDistTMsg;
        //        listCnt++;
        //    }
        //    // Insert
        //    int rCnt = S21FastTBLAccessor.insert(cmInvAcctDistTMsgs);
        //    // START 2016/12/26 [QC#16700, ADD]
        //    if (rCnt != listCnt) {
        //        rollback();
        //        setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0270E, null);
        //        // END 2016/12/26 [QC#16700, ADD]
        //        return false;
        //    }
        //}
        // END 2016/12/09 H.Ikeda [QC#15823, MOD]
        // END 2018/03/27 [QC#20316, DEL]

        // CR/DR Reason is Tax or Freight, no need to create tax or
        // freight data from INV_TOT_DEAL_TAX_AMT or
        // INV_TOT_DEAL_FRT_AMT.
        if (ZYPCommonFunc.hasValue(crDrRsnCd)) {

            if (crDrRsnCd.equals(vndCrDrRsnCdTax) || crDrRsnCd.equals(vndCrDrRsnCdFrt)) {

                return true;
            }
        }

        // Create the Tax Record if there is Tax in.
        BigDecimal invTotDealTaxAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_TAX_AMT");

        // START 2016/11/04 T.Tsuchida [QC#15798,MOD]
        if (ZYPCommonFunc.hasValue(invTotDealTaxAmt) && invTotDealTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
            // END 2016/11/04 T.Tsuchida [QC#15798,MOD]

            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = setCmInvAcctDist(vndInvWrkMap, cmDefAcctTaxTMsg, vndCd, null, vndInfoList);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, vndCrDrRsnCdTax);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, getCrDrRsnDescTxt(vndCrDrRsnCdTax));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, AP_LINE_TP.TAX);
            // START 2016/09/30 [QC#14797, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_CM_INV_ACCT_DIST_LINE_NUM, "0"));
            // START 2018/03/27 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_AP_VND_INV_LINE_NUM, "0"));
            // END 2018/03/27 [QC#20316, ADD]
            apVndInvAcctDistNum++;
            // END 2016/09/30 [QC#14797, ADD]

            // START 2019/05/17  [QC#50204, MOD]
            // if (vndInvTpCdCr.equals(invTpCd)) {
            if (vndInvTpCdCr.equals(invTpCd) && !isCUSAPartsVndRtrn(vndInvWrkMap)) {
            // END   2019/05/17  [QC#50204, MOD]

                invTotDealTaxAmt = invTotDealTaxAmt.negate();
            }

            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, invTotDealTaxAmt);

            // Insert
            EZDTBLAccessor.insert(cmInvAcctDistTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctDistTMsg.getReturnCode())) {

                // START 2016/12/26 [QC#16700, ADD]
                rollback();
                setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0270E, null);
                // END 2016/12/26 [QC#16700, ADD]
                return false;
            }
        }

        // Create the Tax Record if there is Tax in.
        BigDecimal invTotDealFrtAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_FRT_AMT");

        if (invTotDealFrtAmt.compareTo(BigDecimal.ZERO) != 0) {

            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = setCmInvAcctDist(vndInvWrkMap, cmDefAcctFrtTMsg, vndCd, null, vndInfoList);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, vndCrDrRsnCdFrt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, getCrDrRsnDescTxt(vndCrDrRsnCdFrt));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, AP_LINE_TP.FREIGHT);
            // START 2016/09/30 [QC#14797, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_CM_INV_ACCT_DIST_LINE_NUM, "0"));
            // START 2018/03/27 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_AP_VND_INV_LINE_NUM, "0"));
            // END 2018/03/27 [QC#20316, ADD]
            apVndInvAcctDistNum++;
            // END 2016/09/30 [QC#14797, ADD]

            // START 2019/05/17  [QC#50204, MOD]
            // if (vndInvTpCdCr.equals(invTpCd)) {
            if (vndInvTpCdCr.equals(invTpCd) && !isCUSAPartsVndRtrn(vndInvWrkMap)) {
            // END   2019/05/17  [QC#50204, MOD]

                invTotDealFrtAmt = invTotDealFrtAmt.negate();
            }

            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, invTotDealFrtAmt);

            // Insert
            EZDTBLAccessor.insert(cmInvAcctDistTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctDistTMsg.getReturnCode())) {

                // START 2016/12/26 [QC#16700, ADD]
                rollback();
                setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0270E, null);
                // END 2016/12/26 [QC#16700, ADD]
                return false;
            }
        }

        return true;
    }

    private BigDecimal setAmount(EZDTBigDecimalItem inAmt) {
        BigDecimal outAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(inAmt)) {

            outAmt = inAmt.getValue();
        }
        return outAmt;
    }

    private VND_INV_LINE_WRKTMsgArray getVndInvLineWrk(Map<String, Object> vndInvWrkMap) {
        VND_INV_LINE_WRKTMsg inTMsg = new VND_INV_LINE_WRKTMsg();
        // START 2018/03/27 [QC#20316, MOD]
        inTMsg.setSQLID("001");
        // END 2018/03/27 [QC#20316, MOD]
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("vndInvNum01", (String) vndInvWrkMap.get("VND_INV_NUM"));

        VND_INV_LINE_WRKTMsgArray vndInvLineWrkTMsgAry = (VND_INV_LINE_WRKTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return vndInvLineWrkTMsgAry;
    }

    private CM_DEF_ACCTTMsg setCmDefAcctData(String crDrRsnCd) {
        String cmDefAcctCd = CM_DEF_ACCT.OTHER_COA;

        if (ZYPCommonFunc.hasValue(crDrRsnCd)) {

            if (crDrRsnCd.equals(vndCrDrRsnCdCsmp)) {

                cmDefAcctCd = CM_DEF_ACCT.CSMP_COA;

            } else if (crDrRsnCd.equals(vndCrDrRsnCdGrowthBonus)) {

                cmDefAcctCd = CM_DEF_ACCT.GROWTH_BONUS_COA;

            } else if (crDrRsnCd.equals(vndCrDrRsnCdTax)) {

                cmDefAcctCd = CM_DEF_ACCT.TAX_COA;

            } else if (crDrRsnCd.equals(vndCrDrRsnCdFrt)) {

                cmDefAcctCd = CM_DEF_ACCT.FREIGHT_COA;
            }
        }
        return getCmDefAcctByKey(cmDefAcctCd);
    }

    /**
     * Get MDSE Description Short Text
     * @param mdseCd String
     * @return String
     */
    private String getMdseDescShortTxt(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {

            return null;
        }

        return mdseTMsg.mdseDescShortTxt.getValue();
    }

    /**
     * Get CR/DR Reason Description Text
     * @param mdseCd String
     * @return String
     */
    private String getCrDrRsnDescTxt(String crDrRsnCd) {

        CR_DR_RSNTMsg crDrRsnTMsg = new CR_DR_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(crDrRsnTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crDrRsnTMsg.crDrRsnCd, crDrRsnCd);
        crDrRsnTMsg = (CR_DR_RSNTMsg) EZDTBLAccessor.findByKey(crDrRsnTMsg);

        if (crDrRsnTMsg == null) {

            return null;
        }

        return crDrRsnTMsg.crDrRsnDescTxt.getValue();
    }

    /**
     * Get Purchase Requisition Detail
     * @param vndRtrnNum String
     * @param mdseCd String
     * @param invQty BigDecimal
     * @param vndCd String
     * @return CM_INV_ACCT_DISTTMsg
     */
    private PRCH_REQ_DTLTMsg getPrchReqDtlForVndRtrn(String vndRtrnNum, String mdseCd, BigDecimal invQty, String vndCd) {

        // Get Purchase Requisition Detail
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", this.glblCmpyCd);
        queryMap.put("prchReqNum", vndRtrnNum);
        queryMap.put("mdseCd", mdseCd);
        queryMap.put("prchReqLineSts30", PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
        queryMap.put("prchReqLineSts31", PRCH_REQ_LINE_STS.SHIPPED);
        queryMap.put("prchReqLineSts90", PRCH_REQ_LINE_STS.CLOSED);
        // START 2016/12/13 [QC#16132, ADD]
        queryMap.put("coaProjAcctTpAccrual", COA_PROJ_ACCT_TP.ACCRUAL);
        queryMap.put("invVndCd", vndCd);
        // END 2016/12/13 [QC#16132, ADD]

        List<Map<String, Object>> prchReqDtlList = (ArrayList<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrchReqDtl", queryMap);

        if (prchReqDtlList == null || prchReqDtlList.isEmpty()) {

            return null;

        } else if (!PRCH_REQ_TP.VENDOR_RETURN.equals((String) prchReqDtlList.get(0).get("PRCH_REQ_TP_CD"))) {

            return null;
        }

        PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, (String) prchReqDtlList.get(0).get("PRCH_REQ_NUM"));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, (String) prchReqDtlList.get(0).get("PRCH_REQ_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, (BigDecimal) prchReqDtlList.get(0).get("PRCH_REQ_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, (String) prchReqDtlList.get(0).get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, (BigDecimal) prchReqDtlList.get(0).get("SHIP_QTY"));
        // START 2016/12/13 [QC#16132, MOD]
        DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCmpyCd, defCoa.getDefCoaCmpy());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAfflCd, defCoa.getDefCoaAffl());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaBrCd, defCoa.getDefCoaBr());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaChCd, defCoa.getDefCoaCh());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCcCd, defCoa.getDefCoaCc());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAcctCd, (String) prchReqDtlList.get(0).get("COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProjCd, defCoa.getDefCoaProj());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaExtnCd, defCoa.getDefCoaExtn());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProdCd, defCoa.getDefCoaProd());
        // END 2016/12/13 [QC#16132, MOD]

        return prchReqDtlTMsg;
    }

    // START 2018/03/27 [QC#20316, MOD]
    /**
     * Set CM_INV_ACCT_DISTTMsg for All Pattern
     * @param vndInvWrkMap Map<String, Object>
     * @param cmDefAcctTMsg CM_DEF_ACCTTMsg
     * @param vndCd String
     * @param prchReqDtlTMsg PRCH_REQ_DTLTMsg
     * @param vndInfoList List<Map<String, Object>>
     * @return CM_INV_ACCT_DISTTMsg
     */
    private CM_INV_ACCT_DISTTMsg setCmInvAcctDist(Map<String, Object> vndInvWrkMap, CM_DEF_ACCTTMsg cmDefAcctTMsg, String vndCd, PRCH_REQ_DTLTMsg prchReqDtlTMsg, List<Map<String, Object>> vndInfoList) {
    // END 2018/03/27 [QC#20316, MOD]

        CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2019/04/09 [QC#31071, MOD]
        // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, vndCd);
        // START 2019/05/17  [QC#50204, MOD]
        //if (isVndRtrn((String) vndInvWrkMap.get("CR_DR_RSN_CD"))) {
        if (isVndRtrn(vndInvWrkMap)) {
        // END   2019/05/17  [QC#50204, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, (String) vndInfoList.get(0).get("INV_VND_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, vndCd);
        }
        // END   2019/04/09 [QC#31071, MOD]
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, (String) vndInvWrkMap.get("VND_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_DEF);

        // START 2018/02/20 [QC#21703, MOD]
        // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, AP_VND_INV_SQ_NUM_DEF);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, AP_VND_TP_CD_02);
        // END   2018/02/20 [QC#21703, MOD]
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, DEFAULT_DB_NONE);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.delyOrdNum, DEFAULT_DB_NONE);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invTpCd, (String) vndInvWrkMap.get("INV_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invRcvQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apRejQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);

        // START 2018/06/26 S.Katsuma [QC#26566,ADD]
        String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
        // END 2018/06/26 S.Katsuma [QC#26566,ADD]
        if (prchReqDtlTMsg != null && ZYPCommonFunc.hasValue(prchReqDtlTMsg.prchReqNum)) {

            // START 2018/06/26 S.Katsuma [QC#26566,MOD]
            // START 2019/05/17  [QC#50204, MOD]
            // if (ZYPCommonFunc.hasValue(crDrRsnCd) && vndCrDrRsnCdRtrnList.contains(crDrRsnCd)) {
            if (isVndRtrn(vndInvWrkMap)) {
            // END   2019/05/17  [QC#50204, MOD]
                setVndRtrnAcct(cmInvAcctDistTMsg);
            } else {
                // START 2018/03/27 [QC#20316, MOD]
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, prchReqDtlTMsg.coaCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, prchReqDtlTMsg.coaBrCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, prchReqDtlTMsg.coaCcCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, prchReqDtlTMsg.coaAcctCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, prchReqDtlTMsg.coaProdCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, prchReqDtlTMsg.coaChCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, prchReqDtlTMsg.coaAfflCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, prchReqDtlTMsg.coaProjCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, prchReqDtlTMsg.coaExtnCd.getValue());
                // END 2018/03/27 [QC#20316, MOD]
            }
            // END 2018/06/26 S.Katsuma [QC#26566,MOD]
        } else if (cmDefAcctTMsg != null) {
            // START 2018/06/26 S.Katsuma [QC#26566,MOD]
            // START 2019/05/17  [QC#50204, MOD]
            // if (ZYPCommonFunc.hasValue(crDrRsnCd) && vndCrDrRsnCdRtrnList.contains(crDrRsnCd)) {
            if (isVndRtrn(vndInvWrkMap)) {
            // END   2019/05/17  [QC#50204, MOD]
                setVndRtrnAcct(cmInvAcctDistTMsg);
            } else {
                // START 2018/03/27 [QC#20316, MOD]
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, cmDefAcctTMsg.cmCoaCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, cmDefAcctTMsg.cmCoaBrCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, cmDefAcctTMsg.cmCoaCcCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, cmDefAcctTMsg.cmCoaAcctCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, cmDefAcctTMsg.cmCoaProdCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, cmDefAcctTMsg.cmCoaChCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, cmDefAcctTMsg.cmCoaAfflCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, cmDefAcctTMsg.cmCoaProjCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, cmDefAcctTMsg.cmCoaExtnCd.getValue());
                // END 2018/03/27 [QC#20316, MOD]
            }
            // END 2018/06/26 S.Katsuma [QC#26566,MOD]
        }

        // START 2018/03/27 [QC#20316, ADD]
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCmpyCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_CMPY_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaBrCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_BR_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCcCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_CC_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAcctCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_ACCT_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProdCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_PROD_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaChCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_CH_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAfflCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_AFFL_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProjCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_PROJ_CD.toString()));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaExtnCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.SPLY_COA_EXTN_CD.toString()));
        // END 2018/03/27 [QC#20316, ADD]

        // START 2018/09/06 [QC#28040, ADD]
        String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, cmInvAcctDistTMsg.drCoaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apAcctDescTxt, coaAcctDescTxt);

        EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
        int txtDigit = getDigitApInvDescTxt.getDigit();

        if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, coaAcctDescTxt);
        }
        // END 2018/09/06 [QC#28040, ADD]

        return cmInvAcctDistTMsg;
    }

    /**
     * Get CM_DEF_ACCT Data by Key
     * @param cmDefAcctCd String
     * @return CM_DEF_ACCTTMsg
     */
    private CM_DEF_ACCTTMsg getCmDefAcctByKey(String cmDefAcctCd) {

        CM_DEF_ACCTTMsg cmDefAcctTMsg = new CM_DEF_ACCTTMsg();
        cmDefAcctTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        cmDefAcctTMsg.cmDefAcctCd.setValue(cmDefAcctCd);
        cmDefAcctTMsg = (CM_DEF_ACCTTMsg) EZDTBLAccessor.findByKey(cmDefAcctTMsg);

        if (cmDefAcctTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(cmDefAcctTMsg.getReturnCode())) {

            return null;
        }

        return cmDefAcctTMsg;
    }

    /**
     * Get CSMP Invoice Number
     * @param cmDefAcctCd String
     * @return CM_DEF_ACCTTMsg
     */
    private String getCsmpInvNum(String csmpInvNum) {

        if (!ZYPCommonFunc.hasValue(csmpInvNum)) {

            return "";
        }

        if (csmpInvNum.indexOf("-") < 0) {

            return csmpInvNum;
        }

        return csmpInvNum.substring(0, csmpInvNum.indexOf("-"));
    }

    /**
     * get array from search text if it has "," in text field.
     * @param val String
     * @return ArrayList<String>
     */
    private static ArrayList<String> splitStringTxt(String val) {

        ArrayList<String> splitValList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(val)) {

            if (val.indexOf(",") != -1) {

                String[] splitValArray = val.split(",");

                for (int i = 0; i < splitValArray.length; i++) {

                    if (!splitValArray[i].trim().equals("") && splitValArray[i].length() > 0) {

                        splitValList.add(splitValArray[i].trim());
                    }
                }
            }
            // START 2020/05/20 [QC#56555, ADD]
            else {
                splitValList.add(val);
            }
            // END   2020/05/20 [QC#56555, ADD]
        }

        return splitValList;
    }

    // START 2016/11/04 J.Kim [QC#15659,ADD]
    /**
     * Get the scale of Currency Code from CCY Master.
     * @param ccyCd
     * @return
     */
    private int getCcyScale(String ccyCd) {

        CCYTMsg tMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, ccyCd);
        tMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            throw new S21AbendException(NFBM0044E, new String[] {"Currency Code:" + ccyCd });
        }
        return tMsg.aftDeclPntDigitNum.getValueInt();
    }

    // END 2016/11/04 J.Kim [QC#15659,ADD]

    /**
     * insertCmApInvoice
     * @param vndInvWrkMap Map<String, Object>
     * @param vndCd String
     * @param vndRtrnNum String
     * @param cmDefAcctTaxTMsg CM_DEF_ACCTTMsg
     * @param cmDefAcctFrtTMsg CM_DEF_ACCTTMsg
     * @param vndInfoList List<Map<String, Object>>
     * @return true:success/false:error
     */
    private boolean insertCmApInvoice(Map<String, Object> vndInvWrkMap, String vndCd, String vndRtrnNum, CM_DEF_ACCTTMsg cmDefAcctTaxTMsg, CM_DEF_ACCTTMsg cmDefAcctFrtTMsg, List<Map<String, Object>> vndInfoList) {

        CM_AP_INV_HDRTMsg cmApInvHdr = createCmApInvHdrTMsg(vndInvWrkMap, vndCd, vndRtrnNum, vndInfoList);
        EZDTBLAccessor.insert(cmApInvHdr);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvHdr.getReturnCode())) {
            rollback();
            setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0275E, null);
            return false;
        }

        List<CM_AP_INV_DTLTMsg> cmApInvDtlList = createCmApInvDtlTMsgList(vndInvWrkMap, vndCd, vndRtrnNum, cmApInvHdr);
        int insertCnt = S21FastTBLAccessor.insert(cmApInvDtlList.toArray(new CM_AP_INV_DTLTMsg[cmApInvDtlList.size()]));

        if (insertCnt != cmApInvDtlList.size()) {
            rollback();
            setVndInvWrkErr((String) vndInvWrkMap.get("VND_INV_NUM"), NFBM0276E, null);
            return false;
        }

        return true;
    }

    /**
     * insertCmApInvHdrTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @param vndInfoList List<Map<String, Object>>
     * @return CM_AP_INV_HDRTMsg
     */
    private CM_AP_INV_HDRTMsg createCmApInvHdrTMsg(Map<String, Object> vndInvWrkMap, String vndCd, String vndRtrnNum, List<Map<String, Object>> vndInfoList) {
        CM_AP_INV_HDRTMsg tMsg = new CM_AP_INV_HDRTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2019/04/09 [QC#31071, MOD]
        // setValue(tMsg.apVndCd, vndCd);
        // START 2019/05/17 [QC#50204, MOD]
        // if (isVndRtrn((String) vndInvWrkMap.get("CR_DR_RSN_CD"))) {
        if (isVndRtrn(vndInvWrkMap)) {
        // END   2019/05/17 [QC#50204, MOD]
            setValue(tMsg.apVndCd, (String) vndInfoList.get(0).get("INV_VND_CD"));
        } else {
            setValue(tMsg.apVndCd, vndCd);
        }
        // END   2019/04/09 [QC#31071, MOD]
        setValue(tMsg.apVndInvNum, (String) vndInvWrkMap.get("VND_INV_NUM"));
        setValue(tMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_DEF);
        // START 2018/02/20 [QC#21703, MOD]
        // setValue(tMsg.apInvTpCd, AP_VND_INV_SQ_NUM_DEF);
        setValue(tMsg.apInvTpCd, AP_VND_TP_CD_02);
        // END   2018/02/20 [QC#21703, MOD]
        setValue(tMsg.invDt, (String) vndInvWrkMap.get("INV_DT"));
        setValue(tMsg.ccyCd, (String) vndInvWrkMap.get("DEAL_CCY_CD"));
        setValue(tMsg.lastTrxDt, NFBCommonBusiness.getCmProcDt(this.glblCmpyCd));

        String invTpCd = (String) vndInvWrkMap.get("INV_TP_CD");

        BigDecimal invTotDealNetAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_NET_AMT");
        BigDecimal invTotDealFrtAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_FRT_AMT");
        BigDecimal invTotDealTaxAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_TAX_AMT");

        // START 2019/05/17  [QC#50204, MOD]
        // if (vndInvTpCdCr.equals(invTpCd)) {
        if (vndInvTpCdCr.equals(invTpCd) && !isCUSAPartsVndRtrn(vndInvWrkMap)) {
        // END   2019/05/17  [QC#50204, MOD]
            invTotDealNetAmt = invTotDealNetAmt.negate();
            invTotDealFrtAmt = invTotDealFrtAmt.negate();
            invTotDealTaxAmt = invTotDealTaxAmt.negate();
        }

        setValue(tMsg.acOcTotFobCostAmt, invTotDealNetAmt);
        setValue(tMsg.acOcTotFrtCostAmt, invTotDealFrtAmt);
        setValue(tMsg.acOcTotTaxAmt, invTotDealTaxAmt);

        BigDecimal sumAcOcTotAmt = sum(Arrays.asList(invTotDealNetAmt, invTotDealFrtAmt, invTotDealTaxAmt));

        setValue(tMsg.acOcTotGndCostAmt, sumAcOcTotAmt);
        setValue(tMsg.acOcTotGndInvAmt, sumAcOcTotAmt);
        setValue(tMsg.acScTotFobCostAmt, calcScAmt(tMsg.acOcTotFobCostAmt.getValue(), tMsg.ccyCd.getValue(), tMsg.invDt.getValue()));
        setValue(tMsg.acScTotFrtCostAmt, calcScAmt(tMsg.acOcTotFrtCostAmt.getValue(), tMsg.ccyCd.getValue(), tMsg.invDt.getValue()));
        setValue(tMsg.acScTotTaxAmt, calcScAmt(tMsg.acOcTotTaxAmt.getValue(), tMsg.ccyCd.getValue(), tMsg.invDt.getValue()));

        BigDecimal sumAcScTotAmt = sum(Arrays.asList(//
                tMsg.acScTotFobCostAmt.getValue()//
                , tMsg.acScTotFrtCostAmt.getValue()//
                , tMsg.acScTotTaxAmt.getValue()));

        setValue(tMsg.acScTotGndCostAmt, sumAcScTotAmt);
        setValue(tMsg.acScTotGndInvAmt, sumAcScTotAmt);
        setValue(tMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        // START 2019/04/11 [QC#31071, MOD]
        // setValue(tMsg.vndRtrnNum, vndRtrnNum);

        // START 2019/05/17 [QC#50204, MOD]
        // if (isVndRtrn((String) vndInvWrkMap.get("CR_DR_RSN_CD")) && ZYPCommonFunc.hasValue(vndRtrnNum)) {
        if (isVndRtrn(vndInvWrkMap) && ZYPCommonFunc.hasValue(vndRtrnNum)) {
        // END   2019/05/17 [QC#50204, MOD]
            setValue(tMsg.vndRtrnNum, vndRtrnNum);
        }
        // END   2019/04/11 [QC#31071, MOD]

        // START 2018/03/27 [QC#20316, ADD]
        BigDecimal acInvTotCostAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue((BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_NET_AMT"))) {
            acInvTotCostAmt = (BigDecimal) vndInvWrkMap.get("INV_TOT_DEAL_NET_AMT");
            // START 2019/05/17  [QC#50204, MOD]
            // if (vndInvTpCdCr.equals((String) vndInvWrkMap.get("INV_TP_CD"))) {
            if (vndInvTpCdCr.equals((String) vndInvWrkMap.get("INV_TP_CD")) && !isCUSAPartsVndRtrn(vndInvWrkMap)) {
                acInvTotCostAmt = acInvTotCostAmt.negate();
            }
            // END   2019/05/17  [QC#50204, MOD]
        }
        ZYPEZDItemValueSetter.setValue(tMsg.acInvTotCostAmt, acInvTotCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
        ZYPEZDItemValueSetter.setValue(tMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.prntVndCd, (String) vndInfoList.get(0).get(DTO_COL_NAME.PRNT_VND_CD.toString()));
        ZYPEZDItemValueSetter.setValue(tMsg.apInvCatgCd, AP_INV_CATG.OTHER);
        String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
        if (ZYPCommonFunc.hasValue(crDrRsnCd)) {
            if (crDrRsnCd.equals(vndCrDrRsnCdCsmp)) {
                ZYPEZDItemValueSetter.setValue(tMsg.apInvCatgCd, AP_INV_CATG.CSMP);
            } else if (crDrRsnCd.equals(vndCrDrRsnCdGrowthBonus)) {
                ZYPEZDItemValueSetter.setValue(tMsg.apInvCatgCd, AP_INV_CATG.GROWTH_BONUS);
            }
        }
        // END 2018/03/27 [QC#20316, ADD]

        return tMsg;
    }

    private BigDecimal sum(List<BigDecimal> bigDecimalList) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal b : bigDecimalList) {
            sum = sum.add(b);
        }
        return sum;
    }

    /**
     * createCmApInvDtlTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @return CM_AP_INV_DTLTMsg
     */
    private List<CM_AP_INV_DTLTMsg> createCmApInvDtlTMsgList(Map<String, Object> vndInvWrkMap, String vndCd, String vndRtrnNum, CM_AP_INV_HDRTMsg hdrTMsg) {

        String vndInvNum = (String) vndInvWrkMap.get("VND_INV_NUM");
        String invTpCd = (String) vndInvWrkMap.get("INV_TP_CD");
        List<Map<String, Object>> sumVndInvLineList = sumVndInvLine(this.glblCmpyCd, vndInvNum);
        String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
        // START 2019/05/17  [QC#50204, MOD]
        // boolean isVndRtrn = isVndRtrn(crDrRsnCd);
        boolean isVndRtrn = isVndRtrn(vndInvWrkMap);
        boolean isCUSAPartsVndRtrn = isCUSAPartsVndRtrn(vndInvWrkMap);
        // END   2019/05/17  [QC#50204, MOD]
        int scale = getCcyScale(hdrTMsg.ccyCd.getValue());
        BigDecimal exchRate = getExchRate(hdrTMsg.ccyCd.getValue(), hdrTMsg.invDt.getValue());

        // START 2018/03/27 [QC#20316, MOD]
        int apVndInvAcctDistNum = 1;
        // END 2018/03/27 [QC#20316, MOD]

        List<CM_AP_INV_DTLTMsg> cmApInvDtlList = new ArrayList<CM_AP_INV_DTLTMsg>();
        for (Map<String, Object> sumVndInvLine : sumVndInvLineList) {
            CM_AP_INV_DTLTMsg tMsg = new CM_AP_INV_DTLTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            // START 2019/04/09 [QC#31071, MOD]
            // setValue(tMsg.apVndCd, vndCd);
            if (isVndRtrn) {
                setValue(tMsg.apVndCd, hdrTMsg.apVndCd);
            } else {
                setValue(tMsg.apVndCd, vndCd);
            }
            // END   2019/04/09 [QC#31071, MOD]
            
            setValue(tMsg.apVndInvNum, vndInvNum);
            setValue(tMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_DEF);
            setValue(tMsg.vndCd, vndCd);
            setValue(tMsg.vndInvNum, vndInvNum);

            if (isVndRtrn) {
                setValue(tMsg.mdseCd, (String) sumVndInvLine.get("VND_MDSE_CD"));
            } else {
                setValue(tMsg.mdseCd, crDrRsnCd);
            }

            setValue(tMsg.delyOrdNum, DEFAULT_DB_NONE);
            // START 2018/02/20 [QC#21703, MOD]
            // setValue(tMsg.apInvTpCd, AP_VND_INV_SQ_NUM_DEF);
            setValue(tMsg.apInvTpCd, AP_VND_TP_CD_02);
            // END   2018/02/20 [QC#21703, MOD]
            setValue(tMsg.poNum, DEFAULT_DB_NONE);

            // START 2019/04/11 [QC#31071, MOD]
            // setValue(tMsg.vndRtrnNum, vndRtrnNum);
            // START 2019/05/17  [QC#50204, MOD]
            //if (isVndRtrn(crDrRsnCd) && ZYPCommonFunc.hasValue(vndRtrnNum)) {
            if (isVndRtrn && ZYPCommonFunc.hasValue(vndRtrnNum)) {
            // END   2019/05/17  [QC#50204, MOD]
                setValue(tMsg.vndRtrnNum, vndRtrnNum);
            }
            // END   2019/04/11 [QC#31071, MOD]

            setValue(tMsg.invTpCd, invTpCd);

            BigDecimal sumShipQty = (BigDecimal) sumVndInvLine.get("SUM_SHIP_QTY");
            BigDecimal sumNetAmt = (BigDecimal) sumVndInvLine.get("SUM_VND_INV_LINE_DEAL_NET_AMT");
            BigDecimal sumFrtAmt = (BigDecimal) sumVndInvLine.get("SUM_VND_INV_LINE_DEAL_FRT_AMT");
            BigDecimal sumTaxAmt = (BigDecimal) sumVndInvLine.get("SUM_VND_INV_LINE_DEAL_TAX_AMT");

            // START 2019/05/17  [QC#50204, MOD]
            // if (vndInvTpCdCr.equals(invTpCd)) {
            if (vndInvTpCdCr.equals(invTpCd) && !isCUSAPartsVndRtrn) {
            // END   2019/05/17  [QC#50204, MOD]
                sumShipQty = sumShipQty.negate();
                sumNetAmt = sumNetAmt.negate();
                sumFrtAmt = sumFrtAmt.negate();
                sumTaxAmt = sumTaxAmt.negate();
            }

            setValue(tMsg.invQty, sumShipQty);
            setValue(tMsg.acOcFobCostAmt, sumNetAmt);
            setValue(tMsg.acOcFrtCostAmt, sumFrtAmt);
            setValue(tMsg.acOcTaxAmt, sumTaxAmt);

            BigDecimal sumOcAmt = sum(Arrays.asList(sumNetAmt, sumFrtAmt, sumTaxAmt));

            setValue(tMsg.acOcTotCostAmt, sumOcAmt);
            setValue(tMsg.acOcTotInvAmt, sumOcAmt);

            setValue(tMsg.acScFobCostAmt, calcScAmt(tMsg.acOcFobCostAmt.getValue(), hdrTMsg.ccyCd.getValue(), hdrTMsg.invDt.getValue()));

            BigDecimal hdrScFrt = hdrTMsg.acScTotFrtCostAmt.getValue();
            BigDecimal hdrScFob = hdrTMsg.acScTotFobCostAmt.getValue();
            BigDecimal lineScFob = tMsg.acScFobCostAmt.getValue();

            // QC#22043 Mod Start
            if (BigDecimal.ZERO.compareTo(hdrScFob) != 0 && BigDecimal.ZERO.compareTo(lineScFob) != 0) {
                // START 2018/01/11 [QC#23436, MOD]
                // setValue(tMsg.acScFrtCostAmt, hdrScFrt.divide(lineScFob.divide(hdrScFob, scale, BigDecimal.ROUND_HALF_UP), scale, BigDecimal.ROUND_HALF_UP));
                BigDecimal scFobRatio = lineScFob.divide(hdrScFob, scale, BigDecimal.ROUND_HALF_UP);
                if (BigDecimal.ZERO.compareTo(hdrScFrt) != 0 && BigDecimal.ZERO.compareTo(scFobRatio) != 0) {
                    setValue(tMsg.acScFrtCostAmt, hdrScFrt.divide(scFobRatio, scale, BigDecimal.ROUND_HALF_UP));
                } else {
                    setValue(tMsg.acScFrtCostAmt, BigDecimal.ZERO);
                }
                // END   2018/01/11 [QC#23436, MOD]
            } else {
                setValue(tMsg.acScFrtCostAmt, BigDecimal.ZERO);
            }
            // QC#22043 Mod End
            setValue(tMsg.acScTaxAmt, calcScAmt(tMsg.acOcTaxAmt.getValue(), hdrTMsg.ccyCd.getValue(), hdrTMsg.invDt.getValue()));

            BigDecimal sumScAmt = sum(Arrays.asList(//
                    tMsg.acScFobCostAmt.getValue() //
                    , tMsg.acScFrtCostAmt.getValue() //
                    , tMsg.acScTaxAmt.getValue()));

            setValue(tMsg.acScTotCostAmt, sumScAmt);
            setValue(tMsg.acScTotInvAmt, sumScAmt);
            setValue(tMsg.applyExchRate, exchRate);

            setValue(tMsg.trnstJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            setValue(tMsg.stkInJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            setValue(tMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
            setValue(tMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            setValue(tMsg.invRcvQty, BigDecimal.ZERO);
            setValue(tMsg.origScFobCostAmt, tMsg.acScFobCostAmt);

            BigDecimal sumDealGrsTotPrcAmt = (BigDecimal) sumVndInvLine.get("SUM_DEAL_GRS_TOT_PRC_AMT");
            setValue(tMsg.thisMthFobCostAmt, sumDealGrsTotPrcAmt);

            setValue(tMsg.ocExtCostAmt, tMsg.acOcFobCostAmt);

            if (BigDecimal.ZERO.compareTo(tMsg.invQty.getValue()) == 0) {
                setValue(tMsg.ocUnitExtCostAmt, BigDecimal.ZERO);
                setValue(tMsg.scUnitExtCostAmt, BigDecimal.ZERO);
                setValue(tMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
                setValue(tMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
            } else {
                BigDecimal ocFobCostAmt = tMsg.acOcFobCostAmt.getValue();
                BigDecimal scFobCostAmt = tMsg.acScFobCostAmt.getValue();
                BigDecimal qty = tMsg.invQty.getValue().abs();
                BigDecimal diviedOcFobCostAmt = ocFobCostAmt.divide(qty, scale, BigDecimal.ROUND_HALF_UP);

                setValue(tMsg.ocUnitExtCostAmt, diviedOcFobCostAmt);
                setValue(tMsg.scUnitExtCostAmt, scFobCostAmt.divide(qty, scale, BigDecimal.ROUND_HALF_UP));
                setValue(tMsg.entDealNetUnitPrcAmt, diviedOcFobCostAmt);
                setValue(tMsg.entFuncNetUnitPrcAmt, diviedOcFobCostAmt);
            }

            setValue(tMsg.poQty, BigDecimal.ZERO);
            setValue(tMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
            setValue(tMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);

            // START 2016/09/30 [QC#14797, MOD]
            setValue(tMsg.apVndInvLineNum, getApVndInvLineNum(tMsg, apVndInvAcctDistNum));
            apVndInvAcctDistNum++;
            // END 2016/09/30 [QC#14797, MOD]

            // START 2017/12/18 [QC#23022, MOD]
            // setValue(tMsg.mdseDescShortTxt, getMdseDescShortTxt(tMsg.mdseCd.getValue()));
            setValue(tMsg.mdseDescShortTxt, (String) sumVndInvLine.get("MDSE_NM"));
            // END   2017/12/18 [QC#23022, MOD]

            cmApInvDtlList.add(tMsg);
        }

        return cmApInvDtlList;
    }

    /**
     * calcScFob
     * @param amt BigDecimal
     * @param ccyCd String
     * @param invDt String
     * @return ScFob Amount BigDecimal
     */
    private BigDecimal calcScAmt(BigDecimal amt, String ccyCd, String invDt) {
        // Calculate

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

    /**
     * sumVndInvLine
     * @param gCmpyCd String
     * @param vndInvNum String
     * @return sum List<Map<String, Object>>
     */
    private List<Map<String, Object>> sumVndInvLine(String gCmpyCd, String vndInvNum) {
        // Get Purchase Requisition Detail
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", gCmpyCd);
        ssmParam.put("vndInvNum", vndInvNum);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("sumVndInvLine", ssmParam);
    }

    // START 2019/05/16 [QC#50204, DEL]
    // /**
    //  * isVndRtrn
    //  * @param crDrRsnCd
    //  * @return true:Vnd Return boolean
    //  */
    // private boolean isVndRtrn(String crDrRsnCd) {
    //     return ZYPCommonFunc.hasValue(crDrRsnCd) && vndCrDrRsnCdRtrnList.contains(crDrRsnCd);
    // }
    // END   2019/05/16 [QC#50204, DEL]

    // START 2019/05/16 [QC#50204, ADD]
    private boolean isVndRtrn(Map<String, Object> vndInvWrkMap) {
        return isCUSAWSVndRtrn(vndInvWrkMap) || isCUSAPartsVndRtrn(vndInvWrkMap);
    }

    private boolean isCUSAWSVndRtrn(Map<String, Object> vndInvWrkMap) {
        String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
        return ZYPCommonFunc.hasValue(crDrRsnCd) && vndCrDrRsnCdRtrnList.contains(crDrRsnCd);
    }

    private boolean isCUSAPartsVndRtrn(Map<String, Object> vndInvWrkMap) {
        String crDrRsnCd = (String) vndInvWrkMap.get("CR_DR_RSN_CD");
        String itrlIntfcId = (String) vndInvWrkMap.get("ITRL_INTFC_ID");
        return !ZYPCommonFunc.hasValue(crDrRsnCd) && INTERFACE_ID_CUSA_PARTS.equals(itrlIntfcId);
    }
    // END   2019/05/16 [QC#50204, ADD]

    /**
     * getApVndInvLineNum
     * @param tMsg
     * @param apVndInvAcctDistNum int
     * @return
     */
    private String getApVndInvLineNum(CM_AP_INV_DTLTMsg tMsg, int apVndInvAcctDistNum) {
        CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
        setValue(cmInvAcctDist.glblCmpyCd, tMsg.glblCmpyCd);
        setValue(cmInvAcctDist.apVndCd, tMsg.apVndCd);
        setValue(cmInvAcctDist.apVndInvNum, tMsg.apVndInvNum);
        setValue(cmInvAcctDist.apVndInvSqNum, tMsg.apVndInvSqNum);
        // START 2018/03/27 [QC#20316, MOD]
        //setValue(cmInvAcctDist.mdseCd, tMsg.mdseCd);
        //setValue(cmInvAcctDist.apInvTpCd, tMsg.apInvTpCd);
        //setValue(cmInvAcctDist.poNum, tMsg.poNum);
        //setValue(cmInvAcctDist.delyOrdNum, tMsg.delyOrdNum);
        setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_AP_VND_INV_LINE_NUM, "0"));
        setValue(cmInvAcctDist.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), LEN_CM_INV_ACCT_DIST_LINE_NUM, "0"));
        // END 2018/03/27 [QC#20316, MOD]

        cmInvAcctDist = (CM_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKey(cmInvAcctDist);

        if (cmInvAcctDist == null) {
            setVndInvWrkErr(tMsg.vndInvNum.getValue(), NFBM0277E, null);
            // END 2016/12/26 [QC#16700, ADD]
            return null;
        }

        // START 2018/03/27 [QC#20316, MOD]
        return cmInvAcctDist.apVndInvLineNum.getValue();
        // END 2018/03/27 [QC#20316, MOD]
    }

    // START 2018/05/14 [QC#25601, ADD]
    /**
     * getVndRtrnNum
     * @param vndRtrnNum String
     * @return String
     */
    private VND_RTRNTMsg getVndRtrnNum(String vndRtrnNum) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", this.glblCmpyCd);
        queryMap.put("vndRtrnNum", vndRtrnNum);
        // START 2020/05/20 [QC#56270, ADD]
        if (extraCoaAfflCdList.size() == 0) {
            return null;
        } else {
            queryMap.put("coaAfflCd", extraCoaAfflCdList);
        }
        // END   2020/05/20 [QC#56270, ADD]
        String result = (String) this.ssmBatchClient.queryObject("getVndRtrnNum", queryMap);
        if (!ZYPCommonFunc.hasValue(result)) {
            return null;
        }

        VND_RTRNTMsg vndRtrnTMsg = new VND_RTRNTMsg();
        ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.vndRtrnNum, vndRtrnNum);
        return (VND_RTRNTMsg) EZDTBLAccessor.findByKey(vndRtrnTMsg);
    }
    // END   2018/05/14 [QC#25601, ADD]

    // START 2018/06/26 S.Katsuma [QC#26566,ADD]
    /**
     * setVndRtrnAcct
     * @param cmInvAcctDistTMsg CM_INV_ACCT_DISTTMsg
     */
    private void setVndRtrnAcct(CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg) {
        CM_DEF_ACCTTMsg cmDefAcctRtrnTMsg = getCmDefAcctByKey(CM_DEF_ACCT.RETURN_COA);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, cmDefAcctRtrnTMsg.cmCoaCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, cmDefAcctRtrnTMsg.cmCoaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, cmDefAcctRtrnTMsg.cmCoaCcCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, cmDefAcctRtrnTMsg.cmCoaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, cmDefAcctRtrnTMsg.cmCoaProdCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, cmDefAcctRtrnTMsg.cmCoaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, cmDefAcctRtrnTMsg.cmCoaAfflCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, cmDefAcctRtrnTMsg.cmCoaProjCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, cmDefAcctRtrnTMsg.cmCoaExtnCd.getValue());
    }
    // END 2018/06/26 S.Katsuma [QC#26566,ADD]

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

    // START 2019/05/17 [QC#50204, ADD]
    private boolean updateVndInvBolWrk(Map<String, Object> vndInvWrkMap, String vndCd, String vndRtrnNum, List<Map<String, Object>> vndInfoList) {
        String vndInvNum = (String) vndInvWrkMap.get("VND_INV_NUM");

        VND_INV_BOL_WRKTMsg inMsg = new VND_INV_BOL_WRKTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("vndInvNum01", vndInvNum);

        VND_INV_BOL_WRKTMsgArray invBolWrkTMsgArray = (VND_INV_BOL_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
        for (int bolIdx = 0; bolIdx < invBolWrkTMsgArray.getValidCount() ; bolIdx++) {
            VND_INV_BOL_WRKTMsg vndInvBolWrk = invBolWrkTMsgArray.no(bolIdx);
            ZYPEZDItemValueSetter.setValue(vndInvBolWrk.poOrdNum, vndRtrnNum);
            EZDTBLAccessor.update(vndInvBolWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvBolWrk.getReturnCode())) {
                throw new S21AbendException(NFBM0224E, new String[] {"VND_INV_BOL_WRK", vndInvBolWrk.getReturnCode() });
            }
        }

        return true;
    }
    // END   2019/05/17 [QC#50204, ADD]

    // START 2019/08/20 [QC#52280, ADD]
    private boolean updateVndInvWrk(Map<String, Object> vndInvWrkMap, String vndCd) {
        String vndInvNum = (String) vndInvWrkMap.get("VND_INV_NUM");

        VND_INV_WRKTMsg inMsg = new VND_INV_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndInvNum, vndInvNum);

        VND_INV_WRKTMsg invWrkTMsg = (VND_INV_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
        ZYPEZDItemValueSetter.setValue(invWrkTMsg.cmProcStsCd, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.update(invWrkTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invWrkTMsg.getReturnCode())) {
            throw new S21AbendException(NFBM0224E, new String[] {"VND_INV_WRK", invWrkTMsg.getReturnCode() });
        }

        return true;
    }
    // END   2019/08/20 [QC#52280, ADD]
}
