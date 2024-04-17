package business.blap.NFCL3030.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDSBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.blap.NFCL3030.NFCL3030CMsg;
import business.blap.NFCL3030.NFCL3030Query;
import business.blap.NFCL3030.NFCL3030SMsg;
import business.blap.NFCL3030.constant.NFCL3030Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RF_RQSTTMsg;
import business.db.AR_RF_RQST_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.AR_UPD_CUST_CD_LOCK_WRKTMsg;
import business.db.DS_CUST_BANK_ACCT_RELNTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NFZC202001PMsg;
import business.parts.NWZC203001PMsg;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 04/25/2016   Fujitsu         S.Fujita        Update          QC#5671
 * 2016/07/25   Hitachi         J.Kim           Update          QC#9476
 * 2016/07/29   Hitachi         J.Kim           Update          QC#12674
 * 2016/08/05   Hitachi         J.Kim           Update          QC#12657
 * 2016/08/09   Hitachi         T.Tsuchida      Update          QC#12657
 * 2016/08/22   Hitachi         T.Tsuchida      Update          QC#13565
 * 2016/08/23   Hitachi         T.Tsuchida      Update          QC#13570
 * 10/14/2016   Hitachi         E.Kameishi      Update          QC#14814
 * 2016/11/11   Hitachi         J.Kim           Update          QC#15756
 * 2017/03/13   Hitachi         Y.Tsuchimoto    Update          QC#17918
 * 2017/06/13   Hitachi         E.Kameishi      Update          QC#18595
 * 2017/08/15   Fujitsu         H.Ikeda         Update          QC#20585
 * 2018/01/19   Fujitsu         H.Ikeda         Update          QC#23136
 * 2018/01/26   Fujitsu         T.Murai         Update          QC#21401
 * 2018/02/06   Fujitsu         T.Murai         Update          QC#21372
 * 2018/04/04   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 2018/05/09   Fujitsu         Y.Matsui        Update          QC#25856
 * 2018/10/09   Fujitsu         T.Ogura         Update          QC#28166
 * 2018/11/29   Fujitsu         Y.Matsui        Update          QC#29479
 * 2019/07/09   Fujitsu         T.Murai         Update          QC#51282
 * 2019/08/13   Fujitsu         H.Ikeda         Update          QC#52593
 * 2019/08/26   Fujitsu         H.Ikeda         Update          QC#52885
 * 2019/09/02   Fujitsu         M.Ishii         Update          QC#53097
 * 2019/08/26   Fujitsu         H.Ikeda         Update          QC#53382
 * 2020/01/31   Fujitsu         Y.Matsui        Update          QC#54826
 * 2020/06/22   CITS            R.Kurahashi     Update          QC#56012-1
 * 2020/07/10   CITS            R.Kurahashi     Update          QC#56012-2
 * 2020/09/04   CITS            R.Kurahashi     Update          QC#56012-3
 * 2022/04/26   CITS            K.Suzuki        Update          QC#59333
 * 2022/08/04   CITS            D.Mamaril       Update          QC#60376
 * 2022/08/29   Hitachi         A.Kohinata      Update          QC#57417-1
 * 2022/08/31   CITS            K.Suzuki        Update          QC#60510
 * 2023/07/03   Hitachi         S.Nakatani      Update          QC#55645
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 * 2024/03/21   Hitachi         S.Ikariya       Update          QC#63718
 *</pre>
 */
public class NFCL3030CommonLogic implements NFCL3030Constant {

    /**
     * Get AR Account Date
     * @param glblCmpyCd String
     * @return String
     */
    public static String getArAcctDt(String glblCmpyCd) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY, glblCmpyCd);
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.subSysCd.setValue(subSysCd);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return null;
        }
        return outMsg.acctDt.getValue();
    }

    /**
     * Get AR Account Year Month
     * @param glblCmpyCd String
     * @return String
     */
    public static String getArAcctYrMth(String glblCmpyCd) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY, glblCmpyCd);
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.subSysCd.setValue(subSysCd);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return null;
        }
        return outMsg.acctYrMth.getValue();
    }

    /**
     * Check Payer Customer Code Locked
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @param userInfo S21UserInfo
     * @return boolean
     */
    public static boolean checkPayerCustCdLock(String glblCmpyCd, NFCL3030CMsg bizMsg, S21UserInfo userInfo) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findArUpdCustCdLockWrkInfo(glblCmpyCd, bizMsg.payerCustCd_H.getValue());

        if (findMsg == null) {
            return true;
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(findMsg.arLockUpFlg.getValue())) {
                // Err
                bizMsg.setMessageInfo("NFCM0166E", new String[] {findMsg.arLockUsrId.getValue() });
                return false;
            } else {
                return true;
            }
        }

    }

    // 2018/01/19 QC#23136 add start
    /**
     * Check Invoice Apply
     * @param glblCmpyCd String
     * @param rcptNum String
     * @return boolean
     */
    public static boolean checkInvoiceApply(String glblCmpyCd, NFCL3030CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().getRcptNumDataList(glblCmpyCd, bizMsg.rcptNum_H.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map> ssmResultList = (List<Map>) ssmResult.getResultObject();
            for (int i = 0; i < ssmResultList.size(); i++) {
                // 2019/08/26 QC#52885 mod start
                //String artrxNum = ssmResultList.get(i).get("AR_TRX_NUM").toString();
                if (ZYPCommonFunc.hasValue((String)ssmResultList.get(i).get("AR_TRX_NUM"))) {
                    bizMsg.setMessageInfo("NFCM0882E", new String[] {ssmResultList.get(i).get("AR_TRX_NUM").toString()});
                    //return true;
                } else {
                    bizMsg.setMessageInfo(NFCM0909E);
                }
                return true;
                // 2019/08/26 QC#52885 mod end
            }
            return false;
        } else if (ssmResult.isCodeNotFound()) {
            return false;
        } else {
            bizMsg.setMessageInfo("NFCM0864E", new String[] {"AR_TRX_NUM", bizMsg.rcptNum_H.getValue()});
        }
        return true;
    }
    // 2018/01/19 QC#23136 add end

    /**
     * find AR_UPD_CUST_CD_LOCK_WRK Record
     * @param glblCmpyCd String
     * @param payerCustCd String
     * @return AR_UPD_CUST_CD_LOCK_WRKTMsg
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg findArUpdCustCdLockWrkInfo(String glblCmpyCd, String payerCustCd) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg lockTmsg = new AR_UPD_CUST_CD_LOCK_WRKTMsg();

        lockTmsg.glblCmpyCd.setValue(glblCmpyCd);
        lockTmsg.updLockTrgtCustCd.setValue(payerCustCd);

        AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = (AR_UPD_CUST_CD_LOCK_WRKTMsg) EZDTBLAccessor.findByKey(lockTmsg);

        return outMsg;

    }

    /**
     * Check GL Date
     * @param glblCmpyCd String
     * @param glDt String
     * @return boolean
     */
    public static boolean checkGlDate(String glblCmpyCd, String glDt) {
        String batProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        String arAcctYrMth = NFCL3030CommonLogic.getArAcctYrMth(glblCmpyCd);

        String batProcThisYrMth = batProcDt.substring(0, 6);
        String glYtMth = glDt.substring(0, 6);

        if (arAcctYrMth.equals(batProcThisYrMth)) {
            if (!glYtMth.equals(batProcThisYrMth)) {
                return false;
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            int batProcYear = Integer.parseInt(batProcDt.substring(0, 4));
            int batProcMonth = Integer.parseInt(batProcDt.substring(4, 6));
            calendar.set(batProcYear, batProcMonth, 1);
            calendar.add(Calendar.MONTH, -2);
            String batProcLastYrMth = new SimpleDateFormat("yyyyMM").format(calendar.getTime());

            if (glYtMth.equals(batProcThisYrMth)) {
                return false;
            } else if (glYtMth.equals(batProcLastYrMth)) {

                return false;
            }
        }
        return true;
    }

    /**
     * Initialize Pull down Create  
     * @param pulldownCd EZDCStringItemArray
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList EZDCStringItemArray
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
//            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[0]), ":", (String) pullDownData.get(dbColumn[1])));
        }
    }

    /**
     * Initialize Pull down Create  
     * @param pulldownCd EZDCBigDecimalItemArray
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList EZDCStringItemArray
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDCBigDecimalItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((BigDecimal) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
//            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[1]), ":", (String) pullDownData.get(dbColumn[2])));
        }
    }

    /**
     * Create Pull down list : AR Receipt Source
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @param arRcptManCrftFlg String
     */
    // START 2018/04/03 H.Ikeda [QC#21737-1, MOD]
    public static void createPulldownListArRcptSrc(String glblCmpyCd, NFCL3030CMsg bizMsg, String arRcptManCrftFlg) {
    // END   2018/04/03 H.Ikeda [QC#21737-1, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // START 2018/04/03 H.Ikeda [QC#21737-1, ADD]
        ssmParam.put("arRcptManCrftFlg", arRcptManCrftFlg);
        // END   2018/04/03 H.Ikeda [QC#21737-1, ADD]
        S21SsmEZDResult arRcptSrc = NFCL3030Query.getInstance().getArRcptSrcPullDownList(bizMsg, ssmParam);

        if (arRcptSrc.isCodeNormal()) {
            List<Map> arRcptSrcList = (List<Map>) arRcptSrc.getResultObject();
            initPullDownCreate(bizMsg.arRcptSrcCd_LC, bizMsg.arRcptSrcNm_LD, arRcptSrcList, new String[] {"AR_RCPT_SRC_CD", "AR_RCPT_SRC_NM" });
        }
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     */
    public static void createPulldownListArRcptTrxTp(String glblCmpyCd, NFCL3030CMsg bizMsg) {
        List<Map> arRcptTrxTpList = new ArrayList<Map>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        String arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue("AR_NFCL3030_RCPT_TRX_TP", glblCmpyCd);
        ssmParam.put("arRcptTrxTpCd", arRcptTrxTpCdList);

        S21SsmEZDResult arRcptTrxTp = NFCL3030Query.getInstance().geArRcptTrxTpPullDownList(bizMsg, ssmParam);

        if (arRcptTrxTp.isCodeNormal()) {
            arRcptTrxTpList = (List<Map>) arRcptTrxTp.getResultObject();
            initPullDownCreate(bizMsg.arRcptTrxTpCd_LC, bizMsg.arRcptTrxTpNm_LD, arRcptTrxTpList, new String[] {"AR_RCPT_TRX_TP_CD", "AR_RCPT_TRX_TP_NM"});
        }
    }

    /**
     * Set Account Name
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     */
    public static void setDsAcctNm(String glblCmpyCd, NFCL3030CMsg bizMsg) {
        bizMsg.dsAcctNm_H.clear();
        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("sellToCustCd", bizMsg.payerCustCd_H.getValue());
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult dsAcct = NFCL3030Query.getInstance().getDsAcctNm(bizMsg, ssmParam);
            if (dsAcct.isCodeNormal()) {
                String dsAcctNm = (String) dsAcct.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, dsAcctNm);
            } else {
                bizMsg.payerCustCd_H.setErrorInfo(1, "NFCM0763E");
            }
        }
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     */
    public static void setLocNm(String glblCmpyCd, NFCL3030CMsg bizMsg) {
        bizMsg.locNm_H.clear();
        if (ZYPCommonFunc.hasValue(bizMsg.locNum_H)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("locNum", bizMsg.locNum_H.getValue());
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult loc = NFCL3030Query.getInstance().getLocNm(bizMsg, ssmParam);
            if (loc.isCodeNormal()) {
                String locNm = (String) loc.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.locNm_H, locNm);
            } else {
                bizMsg.locNum_H.setErrorInfo(1, "NFCM0777E");
            }
        }
    }

    /**
     * 
     * @param bizMsg NFCL3030CMsg
     */
    public static void clearScreen(NFCL3030CMsg bizMsg) {

        bizMsg.arBatRcptNm_H.clear();
        bizMsg.rcptDt_H.clear();
        bizMsg.ezUpTimeZone_H.clear();
        bizMsg.ezUpTime_H.clear();
        bizMsg.arRcptTrxTpCd_H.clear();
//        bizMsg.arRcptTrxTpCd_LC.clear();
//        bizMsg.arRcptTrxTpNm_LD.clear();
        bizMsg.arRcptStsCd_H.clear();
//        bizMsg.arRcptStsCd_LC.clear();
//        bizMsg.arRcptStsNm_LD.clear();
        bizMsg.arRcptSrcCd_H.clear();
//        bizMsg.arRcptSrcCd_LC.clear();
//        bizMsg.arRcptSrcNm_LD.clear();
        bizMsg.glDt_H.clear();
        bizMsg.arRcptNoteTxt_H.clear();
        bizMsg.xxTotAmt_H1.clear();
        bizMsg.xxTotAmt_H2.clear();
        bizMsg.xxTotAmt_H3.clear();
        bizMsg.xxTotAmt_H4.clear();
        bizMsg.xxTotAmt_H5.clear();
        bizMsg.arRcptRemDt_H.clear();
        bizMsg.crCardApvlCd_H.clear();
        bizMsg.pmtSvcOrdNum_H.clear();
        bizMsg.payerCustCd_H.clear();
        bizMsg.dsAcctNm_H.clear();
        bizMsg.arRcptRefTxt_H.clear();
        bizMsg.arRcptCmntTxt_H.clear();
        // START 2020/01/31 [QC#54826, ADD]
        bizMsg.arRcptMiscCmntTxt_H.clear();
        // END 2020/01/31 [QC#54826, ADD]
        bizMsg.voidDt_H.clear();
        bizMsg.arRcptVoidRsnCd_H.clear();
        bizMsg.arRcptVoidRsnCd_LC.clear();
        bizMsg.arRcptVoidRsnNm_LD.clear();
        bizMsg.arRcptRvrsRsnCd_H.clear();
        bizMsg.arRcptRvrsRsnCd_LC.clear();
        bizMsg.arRcptRvrsRsnNm_LD.clear();
        bizMsg.voidGlDt_H.clear();
        bizMsg.arRcptRvrsCmntTxt_H.clear();
        bizMsg.arCashApplyStsCd_H.clear();
        bizMsg.xxEdtModeFlg_H.clear();
        bizMsg.dsBankCd_H1.clear();
        bizMsg.bankRteNum_H1.clear();
        bizMsg.remDsBankAcctPk_H1.clear();
        bizMsg.dsBankCd_H2.clear();
        bizMsg.bankRteNum_H2.clear();
        bizMsg.custDsBankAcctPk_H2.clear();

        bizMsg.locNum_H.clear();
        bizMsg.locNm_H.clear();
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        bizMsg.invNum_H.clear();
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]

        bizMsg.rcptChkNum_H.clear();
        bizMsg.dsBankAcctNm_H1.clear();
        bizMsg.dsBankBrNm_H1.clear();
        bizMsg.dsBankAcctNum_H1.clear();
        bizMsg.dsBankAcctNm_H2.clear();
        bizMsg.dsBankBrNm_H2.clear();
        bizMsg.dsBankAcctNum_H2.clear();
        // START 2023/07/03 S.Nakatani [QC#55645, ADD]
        bizMsg.dsBankAcctNum_M1.clear();
        // END 2023/07/03 S.Nakatani [QC#55645, ADD]

        bizMsg.arCashApplyStsCd_H.clear();
        bizMsg.ajeCratCpltFlg_H.setValue(ZYPConstant.FLG_OFF_N);
        // START 2016/10/14 E.Kameishi [QC#14814,ADD]
        bizMsg.xxPopPrm_01.clear();
        bizMsg.xxPopPrm_02.clear();
        bizMsg.xxPopPrm_03.clear();
        bizMsg.xxPopPrm_04.clear();
        bizMsg.xxPopPrm_05.clear();
        bizMsg.xxPopPrm_06.clear();
        bizMsg.xxPopPrm_07.clear();
        bizMsg.xxPopPrm_08.clear();
        bizMsg.xxPopPrm_09.clear();
        bizMsg.xxPopPrm_10.clear();
        bizMsg.xxPopPrm_11.clear();
        bizMsg.xxPopPrm_12.clear();
        bizMsg.xxPopPrm_13.clear();
        // END 2016/10/14 E.Kameishi [QC#14814,ADD]
    }

    //Def#3269
    /**
     * 
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public static boolean checkReceiptDate(NFCL3030CMsg bizMsg) {
        // START 2017/03/13 Y.Tsuchimoto [QC#17918,MOD]
        //String startDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, 0, -1);
        //String endDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, 0, 1);
        //if(startDt.compareTo(bizMsg.rcptDt_H.getValue()) > 0 ) {
        //    bizMsg.rcptDt_H.setErrorInfo(1, "NFCM0827E", new String[] {startDt, endDt});
        //    return false;
        //}
        //if(endDt.compareTo(bizMsg.rcptDt_H.getValue()) < 0 ) {
        //    bizMsg.rcptDt_H.setErrorInfo(1, "NFCM0827E", new String[] {startDt, endDt});
        //    return false;
        //}

        // START 2019/08/13 H.Ikeda [QC#52593, ADD]
        if (bizMsg.rcptDt_H.getValue().equals(bizMsg.rcptDt_BK.getValue())) {
            return true;
        }
        // END   2019/08/13 H.Ikeda [QC#52593, ADD]

        BigDecimal widowYears = ZYPCodeDataUtil.getNumConstValue(NFCL3030_WINDOW_YEARS_RCPT_DT, bizMsg.glblCmpyCd.getValue());

        // widowYears < 1 : set 1.
        if (widowYears == null || BigDecimal.ONE.compareTo(widowYears) > 0) {
            widowYears = BigDecimal.ONE;
        // widowYears > 10 : set 10.
        } else if ((new BigDecimal(10)).compareTo(widowYears) < 0) {
            widowYears = new BigDecimal(10);
        // widowYears is not integer : set integer(ROUND_DOWN).
        } else if (!isInteger(widowYears)) {
            widowYears = widowYears.setScale(0, BigDecimal.ROUND_DOWN);
        }

        String startDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, 0, widowYears.intValue() * -1);
        String endDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, 0, widowYears.intValue());
        if (startDt.compareTo(bizMsg.rcptDt_H.getValue()) > 0) {
            bizMsg.rcptDt_H.setErrorInfo(1, "NFCM0827E", new String[] {ZYPDateUtil.formatEzd8ToDisp(startDt), ZYPDateUtil.formatEzd8ToDisp(endDt) });
            return false;
        }
        if (endDt.compareTo(bizMsg.rcptDt_H.getValue()) < 0) {
            bizMsg.rcptDt_H.setErrorInfo(1, "NFCM0827E", new String[] {ZYPDateUtil.formatEzd8ToDisp(startDt), ZYPDateUtil.formatEzd8ToDisp(endDt) });
            return false;
        }
        // END   2017/03/13 Y.Tsuchimoto [QC#17918,MOD]
        return true;
    }

    // START 2017/03/13 Y.Tsuchimoto [QC#17918,ADD]
    /**
     * isInteger
     * @param val BigDecimal
     * @return boolean true:integer, false:not integer.
     */
    private static boolean isInteger(BigDecimal val) {
        if (val == null) {
            return false;
        }
        BigDecimal decimal = val.setScale(0, BigDecimal.ROUND_DOWN);
        if (val.subtract(decimal).compareTo(BigDecimal.ZERO) > 0) {
            return false;
        } else {
            return true;
        }
    }
    // END   2017/03/13 Y.Tsuchimoto [QC#17918,ADD]

    /**
     * 
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public static boolean checkRemittanceDate(NFCL3030CMsg bizMsg) {
        // START 2019/08/13 H.Ikeda [QC#52593, ADD]
        if (bizMsg.arRcptRemDt_H.getValue().equals(bizMsg.arRcptRemDt_BK.getValue())) {
            return true;
        }
        // END   2019/08/13 H.Ikeda [QC#52593, ADD]

        String startDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 0, -1, 0);
        String endDt = NFCCmnMethod.calcDate(bizMsg.procDt.getValue(), 7, 0, 0);

        if (startDt.compareTo(bizMsg.arRcptRemDt_H.getValue()) > 0) {
            bizMsg.arRcptRemDt_H.setErrorInfo(1, "NFCM0827E", new String[] {startDt, endDt});
            return false;
        }
        if (endDt.compareTo(bizMsg.arRcptRemDt_H.getValue()) < 0) {
            bizMsg.arRcptRemDt_H.setErrorInfo(1, "NFCM0827E", new String[] {startDt, endDt});
            return false;
        }
        return true;
    }

    // START 2016/04/25 S.Fujita [QC#5671,MOD]
    /**
     * Is Receipt Date
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public static boolean isReceiptDate(String glblCmpyCd, NFCL3030CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // START 2016/08/22 T.Tsuchida [QC#13565,ADD]
        ssmParam.put("rcptNum", bizMsg.rcptNum_H.getValue());
        // END 2016/08/22 T.Tsuchida [QC#13565,ADD]
        ssmParam.put("rcptDt", bizMsg.rcptDt_H.getValue());
        ssmParam.put("rcptAmt", bizMsg.xxTotAmt_H1.getValue());
        ssmParam.put("dsAcctNum", bizMsg.payerCustCd_H.getValue());
        ssmParam.put("custDsBankAcctPk", bizMsg.custDsBankAcctPk_H2.getValue());
        // START 2017/08/15 H.Ikeda [QC#20585,ADD]
        ssmParam.put("rcptChkNum", bizMsg.rcptChkNum_H.getValue());
        // END   2017/08/15 H.Ikeda [QC#20585,ADD]

        S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().isReceiptDate(bizMsg, ssmParam);
        Integer rcptCnt = (Integer) ssmResult.getResultObject();

        if (rcptCnt != 0) {
            bizMsg.setMessageInfo("NFCM0834E", null);
            return false;
        }
        return true;
    }
    // END 2016/04/25 S.Fujita [QC#5671,MOD]

    // START 2016/08/23 T.Tsuchida [QC#13570,ADD]
    /**
     * Exist Direct Sales Customer Bank Account Relation
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public static boolean existDsCustBankAcctReln(String glblCmpyCd, NFCL3030CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)
            && ZYPCommonFunc.hasValue(bizMsg.custDsBankAcctPk_H2)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("sellToCustCd", bizMsg.payerCustCd_H.getValue());
            ssmParam.put("custDsBankAcctPk", bizMsg.custDsBankAcctPk_H2.getValue());

            S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().existDsCustBankAcctReln(bizMsg, ssmParam);
            Integer cnt = (Integer) ssmResult.getResultObject();

            if (cnt == 0) {
                // START 2017/06/09 E.Kameishi[QC#18595,DEL]
                //bizMsg.setMessageInfo("NFCM0866E", null);
                // END 2017/06/09 E.Kameishi[QC#18595,DEL]
                return false;
            }
        }
        return true;
    }
    // END 2016/08/23 T.Tsuchida [QC#13570,ADD]
    // START 2017/06/09 E.Kameishi[QC#18595,ADD]
    /**
     * Check Refund Data
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @param salesDt String
     * @return boolean
     */
    public static boolean insertDsCustBankAcctReln(String glblCmpyCd, NFCL3030CMsg bizMsg, String salesDt) {

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", bizMsg.payerCustCd_H.getValue());

        // START 2024/03/21 S.Ikariya [QC#63718, ADD]
        String bankAcctNotMappingConst = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_BANK_ACCT_NOT_MAPPING, glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(bankAcctNotMappingConst)) {
            bankAcctNotMappingConst = VAR_CHAR_CONST_DUMMY_CUSTOMER;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H.getValue())) {
            String[] bankAcctNotMappingArry = bankAcctNotMappingConst.split(",");
            if (!Arrays.asList(bankAcctNotMappingArry).contains(bizMsg.payerCustCd_H.getValue())) {
        // END 2024/03/21 S.Ikariya [QC#63718, ADD]
                SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
                BigDecimal dsAcctCustPk = null;
                if (outMsg.getValidCount() > 0) {
                    dsAcctCustPk = outMsg.no(0).sellToCustPk.getValue();
                } else {
                    bizMsg.setMessageInfo("NFCM0864E", new String[] {"SELL_TO_CUST", bizMsg.payerCustCd_H.getValue() });
                    return false;
                }

                DS_CUST_BANK_ACCT_RELNTMsg inTmsg = new DS_CUST_BANK_ACCT_RELNTMsg();

                BigDecimal dsCustBankAcctRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_BANK_ACCT_RELN_SQ);

                ZYPEZDItemValueSetter.setValue(inTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTmsg.dsCustBankAcctRelnPk, dsCustBankAcctRelnPk);
                ZYPEZDItemValueSetter.setValue(inTmsg.dsAcctCustPk, dsAcctCustPk);
                ZYPEZDItemValueSetter.setValue(inTmsg.dsBankAcctPk, bizMsg.custDsBankAcctPk_H2);
                ZYPEZDItemValueSetter.setValue(inTmsg.effFromDt, salesDt);
                ZYPEZDItemValueSetter.setValue(inTmsg.locNum, bizMsg.locNum_H);

                EZDTBLAccessor.create(inTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0861E", new String[] {"DS_CUST_BANK_ACCT_RELN" });
                    return false;
                }
            // START 2024/03/21 S.Ikariya [QC#63718, ADD]
            }
        }
            // END 2024/03/21 S.Ikariya [QC#63718, ADD]
        return true;
    }
    // END 2017/06/09 E.Kameishi[QC#18595,ADD]
    // START 2016/07/25 J.Kim [QC#9476,ADD]
    /**
     * Check Refund Data
     * @param bizMsg NFCL3030CMsg
     * @param glblMsg NFCL3030SMsg
     * @return boolean
     */
    public static boolean checkRefundData(NFCL3030CMsg bizMsg, NFCL3030SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().getRefundInfo(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NFCM0520E);
            return false;
        } else {
            NFCL3030SMsg sMsg = (NFCL3030SMsg) ssmResult.getResultObject();
            if (!ZYPCommonFunc.hasValue(sMsg.crCardApvlCd)) {
                bizMsg.setMessageInfo(NFCM0852E);
                return false;
            }

            // START 2019/10/08 H.Ikeda [QC#53382, DEL]
//            // START 2016/07/26 J.Kim [QC#12674,MOD]
//            if (ZYPCommonFunc.hasValue(sMsg.arTrxBalPk_I)) {
//                if (!AR_CASH_APPLY_STS.PARTIAL.equals(sMsg.arCashApplyStsCd.getValue()) && !AR_CASH_APPLY_STS.UNAPPLIED.equals(sMsg.arCashApplyStsCd.getValue())) {
//                    bizMsg.setMessageInfo(NFCM0853E);
//                    return false;
//                } else if (!ZYPCommonFunc.hasValue(sMsg.dealRmngBalGrsAmt_I) || BigDecimal.ZERO.compareTo(sMsg.dealRmngBalGrsAmt_I.getValue()) == 0) {
//                    bizMsg.setMessageInfo(NFCM0854E);
//                    return false;
//                }
//            }
//            // END 2016/07/26 J.Kim [QC#12674,MOD]
            // END 2019/10/08 H.Ikeda [QC#53382, DEL]
        }

        // START 2019/07/09 [QC#51282,ADD]
        // START 2016/08/05 J.Kim [QC#9476,DEL]
        BigDecimal resultMap = (BigDecimal) NFCL3030Query.getInstance().getArRfRqstPk(bizMsg).getResultObject();
        if (!ZYPCommonFunc.hasValue(resultMap) || BigDecimal.ZERO.compareTo(resultMap) < 0) {
           bizMsg.setMessageInfo(NFCM0036E);
           return false;
        }
        // END 2016/08/05 J.Kim [QC#9476,DEL]
        // END 2019/07/09 [QC#51282,ADD]
        return true;
    }

    /**
     * getArRfRqstPk
     * @param bizMsg NFCL3030CMsg
     */
    public static void getArRfRqstPk(NFCL3030CMsg bizMsg) {
        BigDecimal resultMap = (BigDecimal) NFCL3030Query.getInstance().getArRfRqstPk(bizMsg).getResultObject();
        if (ZYPCommonFunc.hasValue(resultMap) && BigDecimal.ZERO.compareTo(resultMap) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_ON_1);
        }
    }

    // START 2016/11/11 J.Kim [QC#15756,ADD]
    /**
     * getCreditCardRefundArRfRqstInfo
     * @param bizMsg NFCL3030CMsg
     */
    public static void getCreditCardRefundArRfRqstInfo(NFCL3030CMsg bizMsg) {
        BigDecimal resultMap = (BigDecimal) NFCL3030Query.getInstance().getCreditCardRefundArRfRqstInfo(bizMsg).getResultObject();
        if (ZYPCommonFunc.hasValue(resultMap) && BigDecimal.ZERO.compareTo(resultMap) < 0) {
            bizMsg.setMessageInfo(NFCM0871E);
        }
    }
    // END 2016/11/11 J.Kim [QC#15756,ADD]

    /**
     * getRefundData
     * @param bizMsg NFCL3030CMsg
     * @param glblMsg NFCL3030SMsg
     * @return boolean
     */
    public static boolean getRefundData(NFCL3030CMsg bizMsg, NFCL3030SMsg glblMsg) {

        // Call Credit Card Validation API
        if (!callCreditCardValidationAPI(bizMsg, glblMsg)) {
            return false;
        }

        // Insert AR Refund Request, AR Refund Request Detail
        if (!insertArRefundRequest(bizMsg, glblMsg)) {
            return false;
        }

        bizMsg.setMessageInfo(NZZM0002I);
        return true;
    }

    private static boolean callCreditCardValidationAPI(NFCL3030CMsg bizMsg, NFCL3030SMsg glblMsg) {

        // NWZC2030(Credit Card Validation API)
        NWZC203001PMsg pMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        // Mod Start 2018/01/26 S21_NA#21401
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC203001Constant.PROC_MODE_REFUND);
        // ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, AR_RCPT_STS_RESERVED_AUTO);
        // Mod End 2018/01/26 S21_NA#21401

        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, glblMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthRefNum, glblMsg.crCardAuthRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bizMsg.payerCustCd_H);
        // START 2016/08/09 T.Tsuchida [QC#12657,MOD]
        //// START 2016/08/05 J.Kim [QC#12657,MOD]
        //// ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt,
        //// glblMsg.dealRmngBalGrsAmt);
        //ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, glblMsg.dealOrigGrsAmt);
        //// END 2016/08/05 J.Kim [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, glblMsg.dealRmngBalGrsAmt);
        // END 2016/08/09 T.Tsuchida [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.AR);
        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, glblMsg.firstTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.scdTrxInfoTxt, glblMsg.scdTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.thirdTrxInfoTxt, glblMsg.thirdTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.frthTrxInfoTxt, glblMsg.frthTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.fifthTrxInfoTxt, glblMsg.fifthTrxInfoTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoNum, glblMsg.firstTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.scdTrxInfoNum, glblMsg.scdTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.thirdTrxInfoNum, glblMsg.thirdTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.frthTrxInfoNum, glblMsg.frthTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.fifthTrxInfoNum, glblMsg.fifthTrxInfoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.origCrCardTrxPk, glblMsg.origCrCardTrxPk);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, TAX_IND_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcPrflOrdOvrdCd, ORD_OVRD_CD);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, bizMsg.rcptNum_H);

        NWZC203001 api = new NWZC203001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        if (!ZYPConstant.FLG_OFF_0.equals(pMsg.xxPmtcProcStsCd.getValue()) && !ZYPConstant.FLG_ON_1.equals(pMsg.xxPmtcApvlStsNum.getValue())) {
            bizMsg.setMessageInfo(NFCM0855E);
            return false;
        }
        return true;
    }

    private static boolean insertArRefundRequest(NFCL3030CMsg bizMsg, NFCL3030SMsg glblMsg) {

        // CC_RF_AR_RCPT_RF_RSN_CD
        String rsnCd = ZYPCodeDataUtil.getVarCharConstValue(CC_RF_AR_RCPT_RF_RSN_CD, bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(rsnCd)) {
            bizMsg.setMessageInfo(NFCM0856E, new String[] {CC_RF_AR_RCPT_RF_RSN_CD });
            return false;
        }

        // CC_RF_AR_RCPT_RF_RSN_CMNT_TXT
        String rsnCmntTxt = ZYPCodeDataUtil.getVarCharConstValue(CC_RF_AR_RCPT_RF_RSN_CMNT_TXT, bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(rsnCmntTxt)) {
            bizMsg.setMessageInfo(NFCM0856E, new String[] {CC_RF_AR_RCPT_RF_RSN_CMNT_TXT });
            return false;
        }

        // CC_RF_AR_RF_CHK_CMNT_TXT
        String chkCmntTxt = ZYPCodeDataUtil.getVarCharConstValue(CC_RF_AR_RF_CHK_CMNT_TXT, bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(chkCmntTxt)) {
            bizMsg.setMessageInfo(NFCM0856E, new String[] {CC_RF_AR_RF_CHK_CMNT_TXT });
            return false;
        }

        String userId = getUserId();
        String arRfApvlUsrNm = NFCL3030Query.getInstance().getAuthPsn(bizMsg, userId);

        String prntVnd = (String) NFCL3030Query.getInstance().getPrntVnd(bizMsg).getResultObject();
        // START 2016/07/29 J.Kim [QC#9476,DEL]
        //if (!ZYPCommonFunc.hasValue(prntVnd)) {
        //    bizMsg.setMessageInfo(NFCM0845E, new String[] {"Parent Vendor", bizMsg.payerCustCd_H.getValue() });
        //    return false;
        //}
        // END 2016/07/29 J.Kim [QC#9476,DEL]

        // Sequence Number
        BigDecimal arRfRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RF_RQST_SQ);
        BigDecimal arRfRqstDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RF_RQST_DTL_SQ);

        AR_RF_RQSTTMsg arRfRqstTMsg = new AR_RF_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfRqstPk, arRfRqstPk);
        // START 2016/07/29 J.Kim [QC#12674,MOD]
        // ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfTpCd, AR_RF_TP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfTpCd, AR_RF_TP.CREDIT_CARD_REFUND);
        // END 2016/07/29 J.Kim [QC#12674,MOD]
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.billToCustAcctCd, bizMsg.payerCustCd_H);
        // del start 2022/08/29 QC#57417-1
        //ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRcptRfRsnCd, rsnCd);
        // del end 2022/08/29 QC#57417-1
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRcptRfCmntTxt, rsnCmntTxt);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfChkCmntTxt, chkCmntTxt);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.vndCd, prntVnd);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfRqstUsrId, userId);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfCratDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfApvlUsrId, userId);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arRfApvlUsrNm, arRfApvlUsrNm);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.dealCcyCd, glblMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.arDsWfStsCd, AR_DS_WF_STS.APPROVED);
        // START 2016/08/09 T.Tsuchida [QC#12657,MOD]
        //// START 2016/08/05 J.Kim [QC#12657,MOD]
        //// ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.funcRfAmt, refundAmt(glblMsg.dealRmngBalGrsAmt));
        //// ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.dealRfAmt, refundAmt(glblMsg.funcRmngBalGrsAmt));
        //ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.funcRfAmt, refundAmt(glblMsg.funcOrigGrsAmt));
        //ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.dealRfAmt, refundAmt(glblMsg.dealOrigGrsAmt));
        //// END 2016/08/05 J.Kim [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.funcRfAmt, refundAmt(glblMsg.funcRmngBalGrsAmt));
        ZYPEZDItemValueSetter.setValue(arRfRqstTMsg.dealRfAmt, refundAmt(glblMsg.dealRmngBalGrsAmt));
        // START 2016/08/09 T.Tsuchida [QC#12657,MOD]
        S21FastTBLAccessor.insert(arRfRqstTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arRfRqstTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"AR_RF_RQST" });
            return false;
        }

        AR_RF_RQST_DTLTMsg arRfRqstDtlTMsg = new AR_RF_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arRfRqstDtlPk, arRfRqstDtlPk);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arRfRqstPk, arRfRqstPk);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.billToCustCd, bizMsg.locNum_H);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arTrxNum, bizMsg.rcptNum_H);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arTrxBalPk, glblMsg.arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arTrxBalLastUpdTs, glblMsg.arTrxBalLastUpdTs);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arTrxBalTz, glblMsg.arTrxBalTz);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.dealOrigGrsAmt, refundAmt(glblMsg.dealOrigGrsAmt));
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.dealRmngBalGrsAmt, refundAmt(glblMsg.dealRmngBalGrsAmt));
        // START 2016/08/09 T.Tsuchida [QC#12657,MOD]
        //// START 2016/08/05 J.Kim [QC#12657,MOD]
        //// ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.dealRfAmt, refundAmt(glblMsg.dealRmngBalGrsAmt));
        //ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.dealRfAmt, refundAmt(glblMsg.dealOrigGrsAmt));
        //// END 2016/08/05 J.Kim [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.dealRfAmt, refundAmt(glblMsg.dealRmngBalGrsAmt));
        // END 2016/08/09 T.Tsuchida [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.dealCcyCd, glblMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.rfExchRate, glblMsg.exchRate);
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.funcOrigGrsAmt, refundAmt(glblMsg.funcOrigGrsAmt));
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.funcRmngBalGrsAmt, refundAmt(glblMsg.funcRmngBalGrsAmt));
        // START 2016/08/09 T.Tsuchida [QC#12657,MOD]
        //// START 2016/08/05 J.Kim [QC#12657,MOD]
        //// ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.funcRfAmt, refundAmt(glblMsg.funcRmngBalGrsAmt));
        //ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.funcRfAmt, refundAmt(glblMsg.funcOrigGrsAmt));
        //// END 2016/08/05 J.Kim [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.funcRfAmt, refundAmt(glblMsg.funcRmngBalGrsAmt));
        // END 2016/08/09 T.Tsuchida [QC#12657,MOD]
        // add start 2022/08/29 QC#57417-1
        ZYPEZDItemValueSetter.setValue(arRfRqstDtlTMsg.arRcptRfRsnCd, rsnCd);
        // add end 2022/08/29 QC#57417-1
        S21FastTBLAccessor.insert(arRfRqstDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arRfRqstDtlTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"AR_RF_RQST_DTL" });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_ON_1);
        // START 2022/08/31 [QC#60510,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_SU, ZYPConstant.FLG_ON_1);
        // END 2022/08/31 [QC#60510,ADD]

        return true;
    }

    private static BigDecimal refundAmt(EZDSBigDecimalItem item) {
        if (ZYPCommonFunc.hasValue(item)) {
            return item.getValue().negate();
        }
        return null;
    }

    /**
     * method name:getuserId <dd>Method explanation:I acquire userId.
     * @return userId
     */
    private static String getUserId() {
        // Get UserProfile
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = profileService.getContextUserInfo();
        return userInfo.getUserId();

    }
    // END 2016/07/25 J.Kim [QC#9476,ADD]

    // START 2017/12/13 E.Kameishi [QC#22096, ADD]
    public static boolean callCrPrflUpdtAPI(NFCL3030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();
        
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", arRcptTMsg.rcptNum.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (1 > outMsg.length()) {
            return false;
        }
        BigDecimal arTrxBalPk = outMsg.no(0).arTrxBalPk.getValue();
        
        inMsg.clear();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg arTrxBal = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (arTrxBal == null) {
            return false;
        }

        // START 2018/05/09 [QC#25856, MOD]
        if (ZYPCommonFunc.hasValue(arTrxBal.billToCustCd)) {
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
            ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, arTrxBal.billToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
            ZYPEZDItemValueSetter.setValue(paramMsg.sellToCustCd, arTrxBal.billToCustAcctCd);
        }
        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, ZYPDateUtil.getSalesDate());
        // END 2018/05/09 [QC#25856, MOD]

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        
        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                bizMsg.setMessageInfo(msgId);
                return false;
            }
        }
        return true;
    }
    // END 2017/12/13 E.Kameishi [QC#22096, ADD]

    // START 201/02/05 [QC#21372,ADD]
    /**
     * get Customer Code/Name Location
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public static boolean getCustomer(String glblCmpyCd, NFCL3030CMsg bizMsg) {
        // START 2020/07/10 R.Kurahashi [QC#56012-2,ADD]
        if(!ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H.getValue())) {
            bizMsg.payerCustCd_H.setErrorInfo(1, ZZM9000E, new String[] {"Customer Name" });
            bizMsg.dsAcctNm_H.clear();
            bizMsg.locNum_H.clear();
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            bizMsg.invNum_H.clear();
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
            return false;
        }
        // END 2020/07/10 R.Kurahashi [QC#56012-2,ADD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", bizMsg.payerCustCd_H.getValue());
        // START 2020/07/10 R.Kurahashi [QC#56012-2,DEL]
        //ssmParam.put("dsAcctNm", bizMsg.dsAcctNm_H.getValue());
        // END 2020/07/10 R.Kurahashi [QC#56012-2,DEL]
        ssmParam.put("locNum", bizMsg.locNum_H.getValue());
        // 2019/09/02 QC#53097 Del Start
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2019/09/02 QC#53097 Del End

        S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().getCustomer(bizMsg, ssmParam);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        // START 2020/07/10 R.Kurahashi [QC#56012-2,ADD]
        if (resultList.size() == 0 && ZYPCommonFunc.hasValue(bizMsg.locNum_H.getValue())) {
            ssmParam.remove("locNum");
            ssmResult = NFCL3030Query.getInstance().getCustomer(bizMsg, ssmParam);
            resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            bizMsg.locNum_H.clear();
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            bizMsg.invNum_H.clear();
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
        }
        // END 2020/07/10 R.Kurahashi [QC#56012-2,ADD]
        
        if (resultList.size() == 0) {
            // START 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
            //bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0883E);
            //bizMsg.dsAcctNm_H.setErrorInfo(1, NFCM0883E);
            //bizMsg.locNum_H.setErrorInfo(1, NFCM0883E);
            bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0763E);
            bizMsg.dsAcctNm_H.clear();
            bizMsg.locNum_H.clear();
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            bizMsg.invNum_H.clear();
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
            // END 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
            return false;
        } else if (resultList.size() > 1) {
            // START 2018/05/09 [QC#25856, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.locNum_H)) {
                Map<String, Object> resultMap = resultList.get(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.payerCustCd_H, (String) resultMap.get("DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, (String) resultMap.get("DS_ACCT_NM"));
                return true;
            }
            // END 2018/05/09 [QC#25856, ADD]
            bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0857E, new String[] {"Customer information" });
            bizMsg.dsAcctNm_H.setErrorInfo(1, NFCM0857E, new String[] {"Customer information" });
            bizMsg.locNum_H.setErrorInfo(1, NFCM0857E, new String[] {"Customer information" });
            return false;
        } else {
            Map<String, Object> resultMap = resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.payerCustCd_H, (String) resultMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, (String) resultMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H, (String) resultMap.get("LOC_NUM"));
        }
        return true;
    }

    /**
     * get Account Name like search
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     */
    public static boolean getDsAcctNm_Like(String glblCmpyCd, NFCL3030CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)) {
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            String beforeDsAcctNm = bizMsg.dsAcctNm_H.getValue();
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
            bizMsg.dsAcctNm_H.clear();

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("sellToCustCd", bizMsg.payerCustCd_H.getValue());
            ssmParam.put("rowNum", "10");

            S21SsmEZDResult dsAcct = NFCL3030Query.getInstance().getDsAcctNm_Like(bizMsg, ssmParam);
            if (dsAcct.isCodeNormal()) {
                List<Map<String, Object>> dsAcctNmList = (List<Map<String, Object>>) dsAcct.getResultObject();
                if (dsAcctNmList.size() == 0) {
                    bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0763E);
                    return false;
                } else if (dsAcctNmList.size() > 1) {
                    bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0857E, new String[] {"Customer information" });
                    return false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, (String) dsAcctNmList.get(0).get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.payerCustCd_H, (String) dsAcctNmList.get(0).get("SELL_TO_CUST_CD"));
                    // START 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
                    bizMsg.locNum_H.clear();
                    // END 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
                    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
                    if (!dsAcctNmList.get(0).get("DS_ACCT_NM").equals(beforeDsAcctNm)) {
                        bizMsg.invNum_H.clear();
                    }
                    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
                }
            } else {
                bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0763E);
                return false;
            }
        } else {
            bizMsg.payerCustCd_H.setErrorInfo(1, ZZM9000E, new String[] {"Customer Name" });
        }
        return true;
    }
    // END 201/02/05 [QC#21372,ADD]

    // START 2018/10/09 T.Ogura [QC#28166,ADD]
    /**
     * setAppBtnInactiveFlg
     * @param bizMsg NFCL3030CMsg
     */
    public static void setAppBtnInactiveFlg(NFCL3030CMsg bizMsg) {
        BigDecimal applyingRefundCount = (BigDecimal) NFCL3030Query.getInstance().getApplyingRefundCount(bizMsg).getResultObject();
        if (ZYPCommonFunc.hasValue(applyingRefundCount) && BigDecimal.ZERO.compareTo(applyingRefundCount) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_AR, ZYPConstant.FLG_OFF_0);
            // START 2022/04/26 K.Suzuki [QC#59333,ADD]
            return;
            // END   2022/04/26 K.Suzuki [QC#59333,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_AR, ZYPConstant.FLG_ON_1);
        }
        // START 2022/04/26 K.Suzuki [QC#59333,ADD]
        BigDecimal applyingOnAccountRefundCount = (BigDecimal) NFCL3030Query.getInstance().getApplyingOnAccountRefundCount(bizMsg).getResultObject();
        if (ZYPCommonFunc.hasValue(applyingOnAccountRefundCount) && BigDecimal.ZERO.compareTo(applyingOnAccountRefundCount) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_AR, ZYPConstant.FLG_OFF_0);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_AR, ZYPConstant.FLG_ON_1);
        }
        // END   2022/04/26 K.Suzuki [QC#59333,ADD]
    }
    // END   2018/10/09 T.Ogura [QC#28166,ADD]

    // START 2018/11/29 Y.Matsui [QC#29479,ADD]
    /**
     * Add Receipt Type to pull-down list if needed
     * @param bizMsg NFCL3030CMsg
     */
    public static void addtPulldownListArRcptTrxTp(NFCL3030CMsg bizMsg) {
        String arRcptTrxTpCd = bizMsg.arRcptTrxTpCd_H.getValue();
        boolean exists = false;
        for (int i = 0; i < bizMsg.arRcptTrxTpCd_LC.length(); i++) {
            if (!ZYPCommonFunc.hasValue((bizMsg.arRcptTrxTpCd_LC.no(i)))) {
                break;
            }
            if (arRcptTrxTpCd.equals(bizMsg.arRcptTrxTpCd_LC.no(i).getValue())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            String arRcptTrxTpNm = ZYPCodeDataUtil.getName(AR_RCPT_TRX_TP.class, bizMsg.glblCmpyCd.getValue(), arRcptTrxTpCd);
            ZYPPulldownValueSetter.insertFirstData(arRcptTrxTpCd, bizMsg.arRcptTrxTpCd_LC, arRcptTrxTpNm, bizMsg.arRcptTrxTpNm_LD);
        }
    }
    // END 2018/11/29 Y.Matsui [QC#29479,ADD]
    // START 2020/09/04 R.Kurahashi [QC#56012-3,ADD]
    public static String getArRcptRefTxt(String payerCustCd, String usserId, String arRcptRefTxt) {
        String retArRcptRefTxt = "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MMDDYYYY);
        Calendar cal = Calendar.getInstance();
        if (ZYPCommonFunc.hasValue(arRcptRefTxt)) {
            retArRcptRefTxt = arRcptRefTxt + ",";
        }

        retArRcptRefTxt = retArRcptRefTxt + payerCustCd + "-" + usserId + "-" + sdf.format(cal.getTime());

        if (retArRcptRefTxt.length() > 50) {
            retArRcptRefTxt = retArRcptRefTxt.substring(retArRcptRefTxt.length() - 50);
        }

        return retArRcptRefTxt;
    }
    
    public static void deleteArCashApp(String glblCmpyCd, NFCL3030CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().getArCashAppList(glblCmpyCd, bizMsg.rcptNum_H.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map> ssmResultList = (List<Map>) ssmResult.getResultObject();
            AR_CASH_APPTMsg tMsgDel;
            for (int i = 0; i < ssmResultList.size(); i++) {
                tMsgDel = new AR_CASH_APPTMsg();
                tMsgDel.glblCmpyCd.setValue(glblCmpyCd);
                tMsgDel.arCashAppPk.setValue((BigDecimal)ssmResultList.get(i).get("AR_CASH_APP_PK"));
                tMsgDel.arCashAppSqNum.setValue(ssmResultList.get(i).get("AR_CASH_APP_SQ_NUM").toString());
                
                EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDel);
                
            }
        }
    }
    // END 2020/09/04 R.Kurahashi [QC#56012-3,ADD]

    // START 2022/08/04 D.Mamaril [QC#60376,ADD]
    /**
     * Check If Receipt Contains Refunded On Account
     * @param glblCmpyCd String
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public static boolean checkOnAccountRefund(String glblCmpyCd, NFCL3030CMsg bizMsg) {

        BigDecimal applyingOnAccountRefundCount = (BigDecimal) NFCL3030Query.getInstance().getApplyingOnAccountRefundCount(bizMsg).getResultObject();
        BigDecimal onAccountRefundCount = (BigDecimal) NFCL3030Query.getInstance().getOnAccountRefundCount(bizMsg).getResultObject();
        BigDecimal refundCount = applyingOnAccountRefundCount.add(onAccountRefundCount);

        if (BigDecimal.ZERO.compareTo(refundCount) < 0) {
            return true;
        }

        return false;
    }
   // END 2022/08/04 D.Mamaril [QC#60376,ADD]

    // START 2022/08/31 [QC#60510,ADD]
    /**
     * getCreditCardRefundArRfRqst
     * @param bizMsg NFCL3030CMsg
     */
    public static void getCreditCardRefundArRfRqst(NFCL3030CMsg bizMsg) {
        BigDecimal resultMap = (BigDecimal) NFCL3030Query.getInstance().getCreditCardRefundArRfRqstInfo(bizMsg).getResultObject();
        if (ZYPCommonFunc.hasValue(resultMap) && BigDecimal.ZERO.compareTo(resultMap) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_SU, ZYPConstant.FLG_ON_1);
        }
    }

    /**
     * getArRfRqstPk
     * @param bizMsg NFCL3030CMsg
     */
    public static void getCreditCardRefundArRfRqstPk(NFCL3030CMsg bizMsg) {
        BigDecimal resultMap = (BigDecimal) NFCL3030Query.getInstance().getCreditCardRefundArRfRqstPk(bizMsg).getResultObject();
        if (ZYPCommonFunc.hasValue(resultMap) && BigDecimal.ZERO.compareTo(resultMap) < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_ON_1);
        }
    }
// END 2022/08/31 [QC#60510,ADD]
}
