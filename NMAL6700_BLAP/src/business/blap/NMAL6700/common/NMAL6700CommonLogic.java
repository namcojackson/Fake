/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6700.common;

import static business.blap.NMAL6700.constant.NMAL6700Constant.CONST_EXTERNAL_DEF_COA_AFFL_CD;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CONST_ITRL_NON_AFFL_ACCT_NUM;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CONST_SPLITTER;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0072E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0289E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0292E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0295E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0304E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0305E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0306E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8075E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8121E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8180E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NZZM0000E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.ZZM9001E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.blap.NMAL6700.NMAL6700Query;
import business.blap.NMAL6700.NMAL6700SMsg;
import business.blap.NMAL6700.NMAL6700_CCMsg;
import business.blap.NMAL6700.NMAL6700_CSMsg;
import business.blap.NMAL6700.NMAL6700_CSMsgArray;
import business.blap.NMAL6700.NMAL6700_DCMsg;
import business.blap.NMAL6700.NMAL6700_ECMsg;
import business.blap.NMAL6700.NMAL6700_FCMsg;
import business.blap.NMAL6700.NMAL6700_GCMsg;
import business.blap.NMAL6700.NMAL6700_JCMsg;
import business.blap.NMAL6700.NMAL6700_KCMsg;
import business.blap.NMAL6700.NMAL6700_KCMsgArray;
import business.blap.NMAL6700.NMAL6700_MCMsg;
import business.blap.NMAL6700.NMAL6700_SCMsg;
import business.blap.NMAL6700.constant.NMAL6700Constant;
import business.db.ACCT_LOCTMsg;
import business.db.AR_STMT_ISS_CYCLETMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CLT_CUST_TPTMsg;
import business.db.CMPYTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_CHTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.CUST_BLLG_VCLETMsg;
import business.db.DS_ACCT_CARRTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_ACCT_GRP_ASGTMsg;
import business.db.DS_ACCT_INAC_RSNTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_PROSTMsgArray;
import business.db.DS_ACCT_REF_ATTRBTMsg;
import business.db.DS_ACCT_RELNTMsg;
import business.db.DS_BANK_ACCTTMsg;
import business.db.DS_CTAC_PSN_RELNTMsg;
import business.db.DS_CUST_BANK_ACCT_RELNTMsg;
import business.db.DS_CUST_INV_RULETMsg;
import business.db.DS_CUST_SHPG_DEFTMsg;
import business.db.DS_CUST_SPCL_HDLGTMsg;
import business.db.DS_CUST_SPCL_MSGTMsg;
import business.db.DS_CUST_TRX_RULETMsg;
import business.db.DS_SPCL_HDLG_TPTMsg;
import business.db.DS_SPCL_HDLG_TPTMsgArray;
import business.db.INV_RCPNTTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PMT_TERM_CASH_DISCTMsgArray;
import business.db.PRIN_CUSTTMsg;
import business.db.PROS_PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.S21_PSNTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SVC_ACCS_PMITTMsg;
import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Update          N/A
 * 2017/11/16   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 * 2017/12/11   Fujitsu         Hd.Sugawara     Update          QC#20357
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/02/14   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/03/16   Fujitsu         Hd.Sugawara     Update          QC#20357-1
 * 2018/03/20   Fujitsu         Hd.Sugawara     Update          QC#25000
 * 2018/04/27   Fujitsu         Hd.Sugawara     Update          QC#24779
 * 2018/07/13   Fujitsu         M.Ishii         Update          QC#26613
 * 2018/12/10   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/01/07   Fujitsu         Hd.Sugawara     Update          QC#29749
 * 2019/01/17   Fujitsu         M.Ohno          Update          QC#29371 SRNO13
 * 2019/02/13   Fujitsu         R.Nakamura      Update          QC#30293
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2020/12/14   CITS            J.Evangelista   Update          QC#57937
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 *</pre>
 */
public class NMAL6700CommonLogic {

    /**
     * createDsAcctItrlFlgPulldownList
     * @param cMsg NMAL6700CMsg
     */
    public static void createDsAcctItrlFlgPulldownList(NMAL6700CMsg cMsg) {
        cMsg.dsAcctItrlFlg_H1.clear();
        cMsg.xxCtlNm_H2.clear();

        cMsg.dsAcctItrlFlg_H1.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.dsAcctItrlFlg_H1.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxCtlNm_H2.no(0).setValue("INTERNAL");
        cMsg.xxCtlNm_H2.no(1).setValue("EXTERNAL");
    }

    /**
     * createDReasonPulldownList
     * @param cMsg NMAL6700CMsg
     */
    public static void createReasonPulldownList(NMAL6700CMsg cMsg) {
        S21SsmEZDResult result = NMAL6700Query.getInstance().getReasonList(cMsg);

        if (result.isCodeNotFound()) {
            return;
        }

        List<DS_ACCT_INAC_RSNTMsg> dsAcctInacRsn = (List) result.getResultObject();
        cMsg.dsAcctInacRsnCd_H1.clear();
        cMsg.dsAcctInacRsnNm_H2.clear();
        for (int i = 0; i < dsAcctInacRsn.size(); i++) {
            cMsg.dsAcctInacRsnCd_H1.no(i).setValue(dsAcctInacRsn.get(i).dsAcctInacRsnCd.getValue());
            cMsg.dsAcctInacRsnNm_H2.no(i).setValue(dsAcctInacRsn.get(i).dsAcctInacRsnNm.getValue());
        }
    }

    /**
     * createDsAcctRelnTpPulldownList
     * @param ccMsg NMAL6700_CCMsg
     */
    public static void createDsAcctRelnTpPulldownList(NMAL6700_CCMsg ccMsg) {
        ccMsg.dsAcctRelnTpCd_C1.clear();
        ccMsg.dsAcctRelnTpNm_C2.clear();
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_RELN_TP.class, ccMsg.dsAcctRelnTpCd_C1, ccMsg.dsAcctRelnTpNm_C2);

    }

    /**
     * @param glblCmpyCd String
     * @param getArStmtIssCd String
     * @return String
     */
    public static String getArStmtIssDay(String glblCmpyCd, String getArStmtIssCd) {
        String arStmtIssDay = "";
        if (ZYPCommonFunc.hasValue(getArStmtIssCd)) {
            AR_STMT_ISS_CYCLETMsg arStmtIssCycleTMsg = (AR_STMT_ISS_CYCLETMsg) ZYPCodeDataUtil.findByCode(AR_STMT_ISS_CYCLE.class, glblCmpyCd, getArStmtIssCd);
            if (arStmtIssCycleTMsg != null) {
                arStmtIssDay = arStmtIssCycleTMsg.arStmtIssDay.getValue();
            }
        }

        return arStmtIssDay;
    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void copyBizToShareC(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        shareMsg.xxDplyTab.setValue(bizMsg.xxDplyTab.getValue());

        int pageFrom = bizMsg.xxPageShowFromNum_C1.getValueInt() - 1;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (pageFrom + i < shareMsg.C.length()) {
                EZDMsg.copy(bizMsg.C.no(i), null, shareMsg.C.no(pageFrom + i), null);
            } else {
                break;
            }
        }

    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void copyBizToShareA(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        shareMsg.xxDplyTab.setValue(bizMsg.xxDplyTab.getValue());

        int pageFrom = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (pageFrom + i < shareMsg.A.length()) {
                EZDMsg.copy(bizMsg.A.no(i), null, shareMsg.A.no(pageFrom + i), null);
            } else {
                break;
            }
        }

    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void copyBizToShareE(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        shareMsg.xxDplyTab.setValue(bizMsg.xxDplyTab.getValue());

        int pageFrom = bizMsg.xxPageShowFromNum_M1.getValueInt() - 1;
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            if (pageFrom + i < shareMsg.E.length()) {
                EZDMsg.copy(bizMsg.E.no(i), null, shareMsg.E.no(pageFrom + i), null);
            } else {
                break;
            }
        }

    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void copyCMsgToSMsg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum_M1.getValueInt() - 1);
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param fromCnt From Count
     */
    public static void copyCMsgToSMsg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.E.getValidCount()); i++) {
            EZDMsg.copy(cMsg.E.no(cMsgCnt), null, sMsg.E.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * copyCMsgToSMsgJ
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void copyCMsgToSMsgJ(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        copyCMsgToSMsgJ(cMsg, sMsg, cMsg.xxPageShowFromNum_J1.getValueInt() - 1);
    }

    /**
     * copyCMsgToSMsgJ
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param fromCnt From Count
     */
    public static void copyCMsgToSMsgJ(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.J.getValidCount()); i++) {
            EZDMsg.copy(cMsg.J.no(cMsgCnt), null, sMsg.J.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void copyBizToShareJ(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        shareMsg.xxDplyTab.setValue(bizMsg.xxDplyTab.getValue());

        int pageFrom = bizMsg.xxPageShowFromNum_J1.getValueInt() - 1;
        for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
            if (pageFrom + i < shareMsg.J.length()) {
                EZDMsg.copy(bizMsg.J.no(i), null, shareMsg.J.no(pageFrom + i), null);
            } else {
                break;
            }
        }

    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void copyBizToShareI(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        shareMsg.xxDplyTab.setValue(bizMsg.xxDplyTab.getValue());

        int pageFrom = bizMsg.xxPageShowFromNum_I1.getValueInt() - 1;
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            if (pageFrom + i < shareMsg.I.length()) {
                EZDMsg.copy(bizMsg.I.no(i), null, shareMsg.I.no(pageFrom + i), null);
            } else {
                break;
            }
        }
    }

    /**
     * findDsAcctCarrForUpdate
     * @param glblCmpyCd String
     * @param dsAcctCarrPk BigDecimal
     * @return DS_ACCT_CARRTMsg
     */
    public static DS_ACCT_CARRTMsg findDsAcctCarrForUpdate(String glblCmpyCd, BigDecimal dsAcctCarrPk) {
        DS_ACCT_CARRTMsg prmTMsg = new DS_ACCT_CARRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctCarrPk, dsAcctCarrPk);
        return (DS_ACCT_CARRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findDsBankAcctForUpdate
     * @param glblCmpyCd String
     * @param dsBankAcctPk BigDecimal
     * @return DS_BANK_ACCTTMsg
     */
    public static DS_BANK_ACCTTMsg findDsBankAcctForUpdate(String glblCmpyCd, BigDecimal dsBankAcctPk) {
        DS_BANK_ACCTTMsg prmTMsg = new DS_BANK_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsBankAcctPk, dsBankAcctPk);
        return (DS_BANK_ACCTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * copy shareMsg to bizMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     * @param idx int
     */
    public static void copyShareToBizC(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg, int idx) {
        int pageFrom = bizMsg.xxPageShowFromNum_C1.getValueInt();
        int i = 0;
        for (i = 0; i < bizMsg.C.length(); i++) {
            if (pageFrom + i <= shareMsg.C.getValidCount()) {
                EZDMsg.copy(shareMsg.C.no(pageFrom + i - 1), null, bizMsg.C.no(i), null);
            } else {
                break;
            }
        }
        bizMsg.C.setValidCount(i);

    }

    /**
     * viewCurrentPage
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void viewCurrentPageC(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        // copy data from SMsg onto CMsg
        ZYPTableUtil.clear(bizMsg.C);

        int pagenationFrom = bizMsg.xxPageShowFromNum_C1.getValueInt();

        while (pagenationFrom > shareMsg.C.getValidCount()) {
            pagenationFrom = pagenationFrom - bizMsg.C.length();
        }
        if (pagenationFrom < 1) {
            // set value to pagenation items
            bizMsg.xxPageShowFromNum_C1.setValue(0);
            bizMsg.xxPageShowToNum_C1.setValue(0);
            bizMsg.xxPageShowOfNum_C1.setValue(0);
        } else {
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.C.length(); i++) {
                if (i <= shareMsg.C.getValidCount()) {
                    EZDMsg.copy(shareMsg.C.no(i - 1), null, bizMsg.C.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.C.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            bizMsg.xxPageShowFromNum_C1.setValue(pagenationFrom);
            bizMsg.xxPageShowToNum_C1.setValue(pagenationFrom + bizMsg.C.getValidCount() - 1);
            bizMsg.xxPageShowOfNum_C1.setValue(shareMsg.C.getValidCount());
        }
    }

    /**
     * viewLastPage
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void viewLastPageC(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        int size = shareMsg.C.getValidCount();
        if (bizMsg.C.length() > 0) {
            bizMsg.xxPageShowFromNum_C1.setValue(((int) (size / bizMsg.C.length())) * bizMsg.C.length() + 1);
        }

        viewCurrentPageC(bizMsg, shareMsg);
    }

    /**
     * findPrinCustForUpdate
     * @param glblCmpyCd String
     * @param prinCustPk BigDecimal
     * @return PRIN_CUSTTMsg
     */
    public static PRIN_CUSTTMsg findPrinCustForUpdate(String glblCmpyCd, BigDecimal prinCustPk) {
        PRIN_CUSTTMsg prmTMsg = new PRIN_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prinCustPk, prinCustPk);
        return (PRIN_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findSellToCustForUpdate
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return SELL_TO_CUSTTMsg
     */
    public static SELL_TO_CUSTTMsg findSellToCustForUpdate(String glblCmpyCd, String sellToCustCd) {
        SELL_TO_CUSTTMsg prmTMsg = new SELL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.sellToCustCd, sellToCustCd);
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray resultArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByConditionForUpdate(prmTMsg);
        if (resultArray.getValidCount() > 0) {
            return (SELL_TO_CUSTTMsg) resultArray.get(0);
        }
        return null;
    }

    /**
     * findPtyLocWrkForUpdate
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @return PTY_LOC_WRKTMsg
     */
    public static PTY_LOC_WRKTMsg findPtyLocWrkForUpdate(String glblCmpyCd, BigDecimal ptyLocPk) {
        PTY_LOC_WRKTMsg prmTMsg = new PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.ptyLocPk, ptyLocPk);
        return (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findProsPtyLocWrkForUpdate
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @return PTY_LOC_WRKTMsg
     */
    public static PROS_PTY_LOC_WRKTMsg findProsPtyLocWrkForUpdate(String glblCmpyCd, BigDecimal ptyLocPk) {
        PROS_PTY_LOC_WRKTMsg prmTMsg = new PROS_PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.ptyLocPk, ptyLocPk);
        return (PROS_PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findCmpyForUpdate
     * @param glblCmpyCd String
     * @param cmpyPk BigDecimal
     * @return CMPYTMsg
     */
    public static CMPYTMsg findCmpyForUpdate(String glblCmpyCd, BigDecimal cmpyPk) {
        CMPYTMsg prmTMsg = new CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cmpyPk, cmpyPk);
        return (CMPYTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findAcctLocForUpdate
     * @param glblCmpyCd String
     * @param acctLocPk BigDecimal
     * @return ACCT_LOCTMsg
     */
    public static ACCT_LOCTMsg findAcctLocForUpdate(String glblCmpyCd, BigDecimal acctLocPk) {
        ACCT_LOCTMsg prmTMsg = new ACCT_LOCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.acctLocPk, acctLocPk);
        return (ACCT_LOCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findBillToCustForUpdate
     * @param glblCmpyCd String
     * @param billToCustPk BigDecimal
     * @return BILL_TO_CUSTTMsg
     */
    public static BILL_TO_CUSTTMsg findBillToCustForUpdate(String glblCmpyCd, BigDecimal billToCustPk) {
        BILL_TO_CUSTTMsg prmTMsg = new BILL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.billToCustPk, billToCustPk);
        return (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findInvRcpntForUpdate
     * @param glblCmpyCd String
     * @param billToCustPk BigDecimal
     * @return INV_RCPNTTMsg
     */
    public static INV_RCPNTTMsg findInvRcpntForUpdate(String glblCmpyCd, BigDecimal billToCustPk) {
        INV_RCPNTTMsg prmTMsg = new INV_RCPNTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.custPk, billToCustPk);
        return (INV_RCPNTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findShipToCustForUpdate
     * @param glblCmpyCd String
     * @param shipToCustPk BigDecimal
     * @return SHIP_TO_CUSTTMsg
     */
    public static SHIP_TO_CUSTTMsg findShipToCustForUpdate(String glblCmpyCd, BigDecimal shipToCustPk) {
        SHIP_TO_CUSTTMsg prmTMsg = new SHIP_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.shipToCustPk, shipToCustPk);
        return (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctProsPk BigDecimal
     * @return DS_ACCT_PROSTMsg
     */
    public static DS_ACCT_PROSTMsg findDsAcctProsForUpdate(String glblCmpyCd, BigDecimal dsAcctProsPk) {
        DS_ACCT_PROSTMsg prmTMsg = new DS_ACCT_PROSTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctProsPk, dsAcctProsPk);
        return (DS_ACCT_PROSTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctRelnPk BigDecimal
     * @return DS_ACCT_RELNTMsg
     */
    public static DS_ACCT_RELNTMsg findDsAcctRelnForUpdate(String glblCmpyCd, BigDecimal dsAcctRelnPk) {
        DS_ACCT_RELNTMsg prmTMsg = new DS_ACCT_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctRelnPk, dsAcctRelnPk);
        return (DS_ACCT_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctGrpAsgPk BigDecimal
     * @return DS_ACCT_GRP_ASGTMsg
     */
    public static DS_ACCT_GRP_ASGTMsg findDsAcctGrpAsgForUpdate(String glblCmpyCd, BigDecimal dsAcctGrpAsgPk) {
        DS_ACCT_GRP_ASGTMsg prmTMsg = new DS_ACCT_GRP_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctGrpAsgPk, dsAcctGrpAsgPk);
        return (DS_ACCT_GRP_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findSvcAccsPmitForUpdate
     * @param glblCmpyCd String
     * @param svcAccsPmitPk BigDecimal
     * @return SVC_ACCS_PMITTMsg
     */
    public static SVC_ACCS_PMITTMsg findSvcAccsPmitForUpdate(String glblCmpyCd, BigDecimal svcAccsPmitPk) {
        SVC_ACCS_PMITTMsg prmTMsg = new SVC_ACCS_PMITTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcAccsPmitPk, svcAccsPmitPk);
        return (SVC_ACCS_PMITTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param ctacPsnPk BigDecimal
     * @return CTAC_PSNTMsg
     */
    public static CTAC_PSNTMsg findCtacPsnForUpdate(String glblCmpyCd, BigDecimal ctacPsnPk) {
        CTAC_PSNTMsg prmTMsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.ctacPsnPk, ctacPsnPk);
        return (CTAC_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsCtacPsnRelnPk BigDecimal
     * @return DS_CTAC_PSN_RELNTMsg
     */
    public static DS_CTAC_PSN_RELNTMsg findDsCtacPsnRelnForUpdate(String glblCmpyCd, BigDecimal dsCtacPsnRelnPk) {
        DS_CTAC_PSN_RELNTMsg prmTMsg = new DS_CTAC_PSN_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
        return (DS_CTAC_PSN_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsCustTrxRulePk BigDecimal
     * @return DS_CUST_TRX_RULETMsg
     */
    public static DS_CUST_TRX_RULETMsg findDsCustTrxRuleForUpdate(String glblCmpyCd, BigDecimal dsCustTrxRulePk) {
        DS_CUST_TRX_RULETMsg prmTMsg = new DS_CUST_TRX_RULETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustTrxRulePk, dsCustTrxRulePk);
        return (DS_CUST_TRX_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsCustSpclHdlgPk BigDecimal
     * @return DS_CUST_SPCL_HDLGTMsg
     */
    public static DS_CUST_SPCL_HDLGTMsg findDsCustSpclHdlgForUpdate(String glblCmpyCd, BigDecimal dsCustSpclHdlgPk) {
        DS_CUST_SPCL_HDLGTMsg prmTMsg = new DS_CUST_SPCL_HDLGTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustSpclHdlgPk, dsCustSpclHdlgPk);
        return (DS_CUST_SPCL_HDLGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsCustBankAcctRelnPk BigDecimal
     * @return DS_CUST_BANK_ACCT_RELNTMsg
     */
    public static DS_CUST_BANK_ACCT_RELNTMsg findDsCustBankAcctRelnForUpdate(String glblCmpyCd, BigDecimal dsCustBankAcctRelnPk) {
        DS_CUST_BANK_ACCT_RELNTMsg prmTMsg = new DS_CUST_BANK_ACCT_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustBankAcctRelnPk, dsCustBankAcctRelnPk);
        return (DS_CUST_BANK_ACCT_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsCustSpclMsgPk BigDecimal
     * @param isLock if true then lock the record.
     * @return DS_CUST_SPCL_MSGTMsg
     */
    public static DS_CUST_SPCL_MSGTMsg findDsCustSpclMsgForUpdate(String glblCmpyCd, BigDecimal dsCustSpclMsgPk, boolean isLock) {
        DS_CUST_SPCL_MSGTMsg prmTMsg = new DS_CUST_SPCL_MSGTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustSpclMsgPk, dsCustSpclMsgPk);
        // QC#18961
        if (isLock) {
            return (DS_CUST_SPCL_MSGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
        }
        return (DS_CUST_SPCL_MSGTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsCustInvRulePk BigDecimal
     * @return DS_CUST_INV_RULETMsg
     */
    public static DS_CUST_INV_RULETMsg findDsCustInvRuleForUpdate(String glblCmpyCd, BigDecimal dsCustInvRulePk) {
        DS_CUST_INV_RULETMsg prmTMsg = new DS_CUST_INV_RULETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustInvRulePk, dsCustInvRulePk);
        return (DS_CUST_INV_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctRefAttrbPk BigDecimal
     * @return DS_ACCT_REF_ATTRBTMsg
     */
    public static DS_ACCT_REF_ATTRBTMsg findDsAcctRefAttrbForUpdate(String glblCmpyCd, BigDecimal dsAcctRefAttrbPk) {
        DS_ACCT_REF_ATTRBTMsg prmTMsg = new DS_ACCT_REF_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctRefAttrbPk, dsAcctRefAttrbPk);
        return (DS_ACCT_REF_ATTRBTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return DS_ACCT_CR_PRFLTMsg
     */
    public static DS_ACCT_CR_PRFLTMsg findDsAcctCrPrflForUpdate(String glblCmpyCd, String dsAcctNum) {
        DS_ACCT_CR_PRFLTMsg prmTMsg = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctNum, dsAcctNum);
        return (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param coaChCd String
     * @return COA_CHTMsg
     */
    public static COA_CHTMsg findCoaCh(String glblCmpyCd, String coaChCd) {
        COA_CHTMsg prmTMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaChCd, coaChCd);
        return (COA_CHTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param cltCustTp String
     * @return CLT_CUST_TPTMsg
     */
    public static CLT_CUST_TPTMsg findCltCustTp(String glblCmpyCd, String cltCustTp) {
        CLT_CUST_TPTMsg prmTMsg = new CLT_CUST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cltCustTpCd, cltCustTp);
        return (CLT_CUST_TPTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param coaAfflCd String
     * @return COA_AFFLTMsg
     */
    public static COA_AFFLTMsg findCoaAffl(String glblCmpyCd, String coaAfflCd) {
        COA_AFFLTMsg prmTMsg = new COA_AFFLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaAfflCd, coaAfflCd);
        return (COA_AFFLTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param custBllgVcleCd String
     * @return CUST_BLLG_VCLETMsg
     */
    public static CUST_BLLG_VCLETMsg findCustBllgVcle(String glblCmpyCd, String custBllgVcleCd) {
        CUST_BLLG_VCLETMsg prmTMsg = new CUST_BLLG_VCLETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.custBllgVcleCd, custBllgVcleCd);
        return (CUST_BLLG_VCLETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /** 
     * @param glblCmpyCd String
     * @param sellToCustPk BigDecimal
     * @return SELL_TO_CUSTTMsg
     */
    public static SELL_TO_CUSTTMsg findSellToCustForUpdate(String glblCmpyCd, BigDecimal sellToCustPk) {
        SELL_TO_CUSTTMsg prmTMsg = new SELL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.sellToCustPk, sellToCustPk);
        return (SELL_TO_CUSTTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * getTreeList
     * 
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param inActiveFlag boolean
     * @param showAllAddress boolean
     * @return List
     */
    public static List getTreeList(String glblCmpyCd, String parentDsAcctNum, boolean inActiveFlag, boolean showAllAddress) {
        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getTreeList(glblCmpyCd, parentDsAcctNum, inActiveFlag, showAllAddress);
        List resultList = (List) ssmResult.getResultObject();
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return resultList;
    }


    /**
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param targetDsAcctNum String
     * @return List
     */
    public static List getRelatedTreeList(String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum) {
        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getRelatedTreeList(glblCmpyCd, parentDsAcctNum, targetDsAcctNum);
        List resultList = (List) ssmResult.getResultObject();
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return resultList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param showAddressFlg String
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getLeefList(String glblCmpyCd, String dsAcctNum, String showAddressFlg) {
        S21SsmEZDResult ssmResult = null;
        if (ZYPConstant.FLG_ON_Y.equals(showAddressFlg)) {
            ssmResult = NMAL6700Query.getInstance().getLeefList(glblCmpyCd, dsAcctNum);
        } else {
            ssmResult = NMAL6700Query.getInstance().getLeefListInvisibleAddr(glblCmpyCd, dsAcctNum);
        }
        List resultList = (List) ssmResult.getResultObject();
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return resultList;
    }

    /**
     * clear HeaderInfo
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void clearHeaderInfo(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        cMsg.sellToCustPk_H1.clear();
        sMsg.dsAcctNm_H1.clear();
        sMsg.xxChkBox_H1.clear();
        sMsg.dsAcctTpCd_H3.clear();
        sMsg.dsAcctItrlFlg_H3.clear();
        sMsg.dsAcctClsCd_H3.clear();
        sMsg.coaAfflCd_H1.clear();
        sMsg.coaAfflNm_H1.clear();
        sMsg.coaChCd_H1.clear();
        sMsg.coaChNm_H1.clear();
        sMsg.dsAcctDlrCd_H3.clear();
        sMsg.xxChkBox_H2.clear();

        sMsg.dsAcctLegalNm_H1.clear();
        sMsg.dbaNm_H1.clear();
        sMsg.dsAcctDunsNm_H1.clear();
        sMsg.dsAcctAltNm_H1.clear();
        sMsg.dsXtrnlRefTxt_H1.clear();
        sMsg.dsDataSrcTxt_H1.clear();

        sMsg.ezUpTime_H1.clear();
        sMsg.ezUpTimeZone_H1.clear();

        ZYPTableUtil.clear(sMsg.A);

        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    /**
     * getCurrLine
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     * @param idx int
     * @return int
     */
    public static int getCurrLine(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg, int idx) {
        int pagenationFrom = (idx / bizMsg.C.length()) * bizMsg.C.length() + 1;
        bizMsg.xxPageShowFromNum_C1.setValue(pagenationFrom);
        copyShareToBizC(bizMsg, shareMsg, idx);
        bizMsg.xxPageShowToNum_C1.setValue(pagenationFrom + bizMsg.C.getValidCount() - 1);

        int curr = idx - pagenationFrom + 1;
        if (curr >= 0 && curr < bizMsg.C.length()) {
            return curr;
        }
        return -1;
    }

    /**
     * <pre>
     * setSpecificPageData
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     * @param specificIdx Specification Index
     */
    public static void setSpecificPageData(NMAL6700CMsg bizMsg, NMAL6700SMsg sMsg, int specificIdx) {

        int page = specificIdx / bizMsg.C.length();
        int pageFrom = page * bizMsg.C.length();
        int i = pageFrom;
        for (; i < pageFrom + bizMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(i), null, bizMsg.C.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        bizMsg.C.setValidCount(i - pageFrom);

        bizMsg.xxPageShowFromNum_C1.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum_C1.setValue(pageFrom + bizMsg.C.getValidCount());
    }

    /**
     * <pre>
     * createInvRulePulldownList
     * </pre>
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     */
    public static void createInvRulePulldownList(NMAL6700_GCMsg gcMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(CUST_INV_SRC.class, gcMsg.custInvSrcCd_G1, gcMsg.custInvSrcNm_G2);
        ZYPCodeDataUtil.createPulldownList(CUST_BLLG_TP.class, gcMsg.custBllgTpCd_G1, gcMsg.custBllgTpNm_G2);
        // 2019/01/17 QC#29371 SRNO#13 Mod Start
//        ZYPCodeDataUtil.createPulldownList(CUST_BLLG_VCLE.class, gcMsg.custBllgVcleCd_G1, gcMsg.custBllgVcleNm_G2);
        ZYPCodeDataUtil.createPulldownList(CUST_BLLG_VCLE.class, gcMsg.custBllgVcleCd_G1, gcMsg.custBllgVcleDescTxt_G2);
        // 2019/01/17 QC#29371 SRNO#13 Mod End
        ZYPCodeDataUtil.createPulldownList(DEF_INV_GRP.class, gcMsg.defInvGrpCd_G1, gcMsg.defInvGrpNm_G2);
        gcMsg.custEffLvlCd_G1.clear();
        gcMsg.custEffLvlCd_G1.no(0).setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        gcMsg.custEffLvlCd_G1.no(1).setValue(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        gcMsg.custEffLvlNm_G2.clear();
        gcMsg.custEffLvlNm_G2.no(0).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_ONLY));
        gcMsg.custEffLvlNm_G2.no(1).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_AND_CHILDREN));
    }
    /**
     * getPmtTermCashDisc
     * @param cMsg NMAL6700CMsg
     * @param glblCmpyCd String
     * @return pmtTermCashDiscTMsgArray PMT_TERM_CASH_DISCTMsgArray
     */
    public static PMT_TERM_CASH_DISCTMsgArray getPmtTermCashDisc(NMAL6700CMsg cMsg, String glblCmpyCd) {
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        pmtTermCashDiscTMsg.setSQLID("002");
        pmtTermCashDiscTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        pmtTermCashDiscTMsg.setConditionValue("pmtTermConslFlg01", ZYPConstant.FLG_ON_Y);

        PMT_TERM_CASH_DISCTMsgArray pmtTermCashDiscTMsgArray = (PMT_TERM_CASH_DISCTMsgArray) EZDTBLAccessor.findByCondition(pmtTermCashDiscTMsg);

        return pmtTermCashDiscTMsgArray;
    }

    /**
     * @param jcMsg NMAL6700_JCMsg
     * @param glblCmpyCd String
     */
    public static void createMsgNotePulldownList(NMAL6700_JCMsg jcMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, jcMsg.lineBizTpCd_J1, jcMsg.lineBizTpNm_J2);
        ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, jcMsg.dsBizAreaCd_J1, jcMsg.dsBizAreaNm_J2);
        ZYPCodeDataUtil.createPulldownList(DS_CUST_MSG_TP.class, jcMsg.dsCustMsgTpCd_J1, jcMsg.dsCustMsgTpNm_J2);

        jcMsg.custEffLvlCd_J1.clear();
        jcMsg.custEffLvlCd_J1.no(0).setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        jcMsg.custEffLvlCd_J1.no(1).setValue(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        jcMsg.custEffLvlNm_J2.clear();
        jcMsg.custEffLvlNm_J2.no(0).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_ONLY));
        jcMsg.custEffLvlNm_J2.no(1).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_AND_CHILDREN));
    }

    /**
     * @param fcMsg NMAL6700_FCMsg
     * @param glblCmpyCd String
     */
    public static void createTransactionRulePulldownList(NMAL6700_FCMsg fcMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(DS_TRX_RULE_TP.class, fcMsg.dsTrxRuleTpCd_F1, fcMsg.dsTrxRuleTpNm_F2);

        fcMsg.custEffLvlCd_F1.clear();
        fcMsg.custEffLvlCd_F1.no(0).setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        fcMsg.custEffLvlCd_F1.no(1).setValue(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        fcMsg.custEffLvlNm_F2.clear();
        fcMsg.custEffLvlNm_F2.no(0).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_ONLY));
        fcMsg.custEffLvlNm_F2.no(1).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_AND_CHILDREN));

    }

    /**
     * @param scMsg NMAL6700_SCMsg
     * @param glblCmpyCd String
     */
    public static void createSpecialHandlingPulldownList(NMAL6700_SCMsg scMsg, String glblCmpyCd) {

        scMsg.dsSpclHdlgTpCd_S1.clear();
        scMsg.dsSpclHdlgTpNm_S2.clear();
        scMsg.dsSpclHdlgTpValCd_S1.clear();
        scMsg.dsSpclHdlgTpValNm_S2.clear();

        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getDsSpclHdlgTpCdList(glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            List<DS_SPCL_HDLG_TPTMsg> dsSpclHdlgTpList = (List) ssmResult.getResultObject();
            for (int i = 0; i < dsSpclHdlgTpList.size(); i++) {
                scMsg.dsSpclHdlgTpCd_S1.no(i).setValue(dsSpclHdlgTpList.get(i).dsSpclHdlgTpCd.getValue());
                scMsg.dsSpclHdlgTpNm_S2.no(i).setValue(dsSpclHdlgTpList.get(i).dsSpclHdlgTpNm.getValue());
            }
        }
        scMsg.custEffLvlCd_S1.clear();
        scMsg.custEffLvlCd_S1.no(0).setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        scMsg.custEffLvlCd_S1.no(1).setValue(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        scMsg.custEffLvlNm_S2.clear();
        scMsg.custEffLvlNm_S2.no(0).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_ONLY));
        scMsg.custEffLvlNm_S2.no(1).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_AND_CHILDREN));

    }
    /**
     * @param kcMsg NMAL6700_KCMsg
     * @param glblCmpyCd String
     */
    public static void createAttributePulldownList(NMAL6700_KCMsg kcMsg, String glblCmpyCd) {

        kcMsg.custEffLvlCd_K1.clear();
        kcMsg.custEffLvlCd_K1.no(0).setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        kcMsg.custEffLvlCd_K1.no(1).setValue(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        kcMsg.custEffLvlNm_K2.clear();
        kcMsg.custEffLvlNm_K2.no(0).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_ONLY));
        kcMsg.custEffLvlNm_K2.no(1).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_AND_CHILDREN));

    }

    /**
     * @param cMsgValue BigDecimalItem
     * @param sMsgValue BigDecimalItem
     * @return boolean
     */
    public static boolean equalBigDecimal(EZDCBigDecimalItem cMsgValue, EZDCBigDecimalItem  sMsgValue) {
        if (ZYPCommonFunc.hasValue(cMsgValue)) {
            if (ZYPCommonFunc.hasValue(sMsgValue)) {
                if (cMsgValue.getValue().compareTo(sMsgValue.getValue()) == 0) {
                    return true;
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(sMsgValue)) {
                return true;
            }
        }
        return false;
    }

    // START 2017/09/07 J.Kim [QC#20495,ADD]
    /**
     * @param tMsgValue BigDecimalItem
     * @param tbfMsgValue BigDecimalItem
     * @return boolean
     */
    public static boolean equalBigDecimal(EZDTBigDecimalItem tMsgValue, EZDTBigDecimalItem tbfMsgValue) {
        if (ZYPCommonFunc.hasValue(tMsgValue) && !ZYPCommonFunc.hasValue(tbfMsgValue)) {
            return false;
        } else if (!ZYPCommonFunc.hasValue(tMsgValue) && ZYPCommonFunc.hasValue(tbfMsgValue)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(tMsgValue) && ZYPCommonFunc.hasValue(tbfMsgValue) && tMsgValue.getValue().compareTo(tbfMsgValue.getValue()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * @param big1 BigDecimal
     * @param big2 BigDecimal
     * @return boolean
     */
    public static BigDecimal add(BigDecimal big1, BigDecimal big2) {
        if (big1 == null) {
            big1 = BigDecimal.ZERO;
        }
        if (big2 == null) {
            big2 = BigDecimal.ZERO;
        }
        return big1.add(big2);
    }

    /**
     * @param big1 BigDecimal
     * @param big2 BigDecimal
     * @return boolean
     */
    public static BigDecimal subtract(BigDecimal big1, BigDecimal big2) {
        if (big1 == null) {
            big1 = BigDecimal.ZERO;
        }
        if (big2 == null) {
            big2 = BigDecimal.ZERO;
        }
        return big1.subtract(big2);
    }
    // END 2017/09/07 J.Kim [QC#20495,ADD]

    /**
     * @param tsStr String
     * @return String
     */
    public static String formatDt(String tsStr) {
        if (tsStr == null || tsStr.length() < 8) {
            return "";
        }
        return ZYPDateUtil.formatEzd8ToDisp(tsStr.substring(0, 8), true);
    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @return boolean
     */
    public static boolean getCltCustTpNm(String glblCmpyCd, NMAL6700CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cltCustTpCd_U1)) {
            CLT_CUST_TPTMsg cltCustTpTMsg = null;
            cltCustTpTMsg = findCltCustTp(glblCmpyCd, cMsg.cltCustTpCd_U1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.cltCustTpCd_U1)
                    && findCltCustTp(glblCmpyCd, cMsg.cltCustTpCd_U1.getValue()) == null) {
                cMsg.cltCustTpCd_U1.setErrorInfo(1, NMAM8121E, new String[] {"Collection Customer Type" });
                return false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpNm_U1, cltCustTpTMsg.cltCustTpNm);
                return true;
            }
        }
        return true;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @return boolean
     */
    public static boolean getCltPtfoNm(NMAL6700CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cltPtfoCd_U1)) {
            S21SsmEZDResult result = NMAL6700Query.getInstance().getCltPtfoNm(cMsg);

            if (result.isCodeNormal()) {
                // 2018/07/13 QC#26613 Mod Start
//                String cltPtfoNm = (String) ((Map) result.getResultObject()).get("CLT_PTFO_NM");
                String cltPtfoNm = (String) ((Map) result.getResultObject()).get("CLT_PTFO_DESC_TXT");
                // 2018/07/13 QC#26613 Mod End
                BigDecimal cltPtfoPk = (BigDecimal) ((Map) result.getResultObject()).get("CLT_PTFO_PK");
                // 2018/07/13 QC#26613 Mod Start
//                ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoNm_U1, cltPtfoNm);
                ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoDescTxt_U1, cltPtfoNm);
                // 2018/07/13 QC#26613 Mod End
                ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoPk_U1, cltPtfoPk);
                return true;
            } else {
                cMsg.cltPtfoCd_U1.setErrorInfo(1, NMAM8121E, new String[] {"Default  Collector" });
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * copyFromSMsgOntoCmsg
     * </pre>
     * 
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void copyFromSMsgOntoCmsg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.A.getValidCount()), sMsg.A.getValidCount());
    }

    /**
     * <pre>
     * setPageNum
     * </pre>
     * 
     * @param cMsg NMAL6700CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NMAL6700CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum_A1.setValue(fromCnt);
        cMsg.xxPageShowToNum_A1.setValue(toCnt);
        cMsg.xxPageShowOfNum_A1.setValue(allCnt);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int pagenationFrom) {
        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * <pre>
     * copyFromSMsgOntoCmsgJ
     * </pre>
     * 
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void copyFromSMsgOntoCmsgJ(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_J1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.J.length(); i++) {
            if (i < sMsg.J.getValidCount()) {

                EZDMsg.copy(sMsg.J.no(i), null, cMsg.J.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.J.setValidCount(i - pagenationFrom);
        setPageNumJ(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.J.getValidCount()), sMsg.J.getValidCount());
    }

    /**
     * <pre>
     * setPageNumJ
     * </pre>
     * 
     * @param cMsg NMAL6700CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNumJ(NMAL6700CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum_J1.setValue(fromCnt);
        cMsg.xxPageShowToNum_J1.setValue(toCnt);
        cMsg.xxPageShowOfNum_J1.setValue(allCnt);
    }

    /**
     * setPagenationJ <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param pagenationFrom int
     */
    public static void setPagenationJ(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int pagenationFrom) {
        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.J.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.J.getValidCount()) {
                EZDMsg.copy(cMsg.J.get(cnt - pagenationFrom), null, sMsg.J.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param oldDisplayStartRowNum int
     * @param newDisplayStartRowNum int
     */
    public static void changePage_TabContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int oldDisplayStartRowNum, int newDisplayStartRowNum) {
        // Save current page to sMsg
        savePageToSMsg_TabContacts(cMsg, sMsg, oldDisplayStartRowNum - 1);

        // Display next page from sMsg
        int sMsgStartIndexOfNextPage = newDisplayStartRowNum - 1;
        getPageFromSMsg_TabContacts(cMsg, sMsg, sMsgStartIndexOfNextPage);

        // Set new page number
        cMsg.xxPageShowFromNum_D1.setValue(sMsgStartIndexOfNextPage + 1);
        cMsg.xxPageShowToNum_D1.setValue(sMsgStartIndexOfNextPage + cMsg.D.getValidCount());
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param startIndexOfSMsg int
     */
    public static void savePageToSMsg_TabContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            EZDMsg.copy(cMsg.D.no(i), null, sMsg.D.no(startIndexOfSMsg + i), null);
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabAddress(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.A);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.A.length()) * cMsg.A.length();

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.A.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(startIndexOfSMsg + i), null, cMsg.A.no(i), null);
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(copiedRows);

        // Set page number
        if (sMsg.A.getValidCount() > 0) {
            int totalRecordCount = sMsg.A.getValidCount();
            int maxRowPerPage = cMsg.A.length();

            // Record count
            cMsg.xxPageShowFromNum_A1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_A1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_A1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_A1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_A1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_A1.clear();
            cMsg.xxPageShowFromNum_A1.clear();
            cMsg.xxPageShowToNum_A1.clear();
            cMsg.xxPageShowCurNum_A1.clear();
            cMsg.xxPageShowOfNum_A1.clear();
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.D);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.D.length()) * cMsg.D.length();
        if (startIndexOfSMsg >= 20 && startIndexOfSMsg % sMsg.D.getValidCount() == 0) {
            startIndexOfSMsg = startIndexOfSMsg - 20;
        }

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.D.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.D.getValidCount()) {
                EZDMsg.copy(sMsg.D.no(startIndexOfSMsg + i), null, cMsg.D.no(i), null);
                NMAL6700_DCMsg dcMsg = cMsg.D.no(i);
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.D.setValidCount(copiedRows);

        // Set page number
        if (sMsg.D.getValidCount() > 0) {
            int totalRecordCount = sMsg.D.getValidCount();
            int maxRowPerPage = cMsg.D.length();

            // Record count
            cMsg.xxPageShowFromNum_D1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_D1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_D1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_D1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_D1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_D1.clear();
            cMsg.xxPageShowFromNum_D1.clear();
            cMsg.xxPageShowToNum_D1.clear();
            cMsg.xxPageShowCurNum_D1.clear();
            cMsg.xxPageShowOfNum_D1.clear();
        }
    }
    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param oldDisplayStartRowNum int
     * @param newDisplayStartRowNum int
     */
    public static void changePage_TabRelnShips(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int oldDisplayStartRowNum, int newDisplayStartRowNum) {
        // Save current page to sMsg
        savePageToSMsg_TabRelnShips(cMsg, sMsg, oldDisplayStartRowNum - 1);

        // Display next page from sMsg
        int sMsgStartIndexOfNextPage = newDisplayStartRowNum - 1;
        getPageFromSMsg_TabRelnShips(cMsg, sMsg, sMsgStartIndexOfNextPage);

        // Set new page number
        cMsg.xxPageShowFromNum_C1.setValue(sMsgStartIndexOfNextPage + 1);
        cMsg.xxPageShowToNum_C1.setValue(sMsgStartIndexOfNextPage + cMsg.D.getValidCount());
    }
    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param startIndexOfSMsg int
     */
    public static void savePageToSMsg_TabRelnShips(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            EZDMsg.copy(cMsg.C.no(i), null, sMsg.C.no(startIndexOfSMsg + i), null);
        }
    }
    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabRelnShips(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.C);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.C.length()) * cMsg.C.length();

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.C.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(startIndexOfSMsg + i), null, cMsg.C.no(i), null);
                NMAL6700_CCMsg ccMsg = cMsg.C.no(i);
                NMAL6700CommonLogic.createDsAcctRelnTpPulldownList(ccMsg);
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(copiedRows);

        // Set page number
        if (sMsg.C.getValidCount() > 0) {
            int totalRecordCount = sMsg.C.getValidCount();
            int maxRowPerPage = cMsg.C.length();

            // Record count
            cMsg.xxPageShowFromNum_C1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_C1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_C1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_C1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_C1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_C1.clear();
            cMsg.xxPageShowFromNum_C1.clear();
            cMsg.xxPageShowToNum_C1.clear();
            cMsg.xxPageShowCurNum_C1.clear();
            cMsg.xxPageShowOfNum_C1.clear();
        }
    }

    /**
     * <pre>
     * copyFromSMsgOntoCmsgI
     * </pre>
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void copyFromSMsgOntoCmsgI(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_I1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.I.length(); i++) {
            if (i < sMsg.I.getValidCount()) {

                EZDMsg.copy(sMsg.I.no(i), null, cMsg.I.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.I.setValidCount(i - pagenationFrom);
        setPageNumI(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.I.getValidCount()), sMsg.I.getValidCount());
    }

    /**
     * <pre>
     * setPageNumI
     * </pre>
     * @param cMsg NMAL6700CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNumI(NMAL6700CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum_I1.setValue(fromCnt);
        cMsg.xxPageShowToNum_I1.setValue(toCnt);
        cMsg.xxPageShowOfNum_I1.setValue(allCnt);
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean chkContact(NMAL6700CMsg cMsg, NMAL6700_GCMsg gcMsg, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(gcMsg.xxGenlFldAreaTxt_G2)) {
            String ctacPsnPkList = gcMsg.xxGenlFldAreaTxt_G2.getValue();
            String[] ctacPsnPkArray = NMAL6700CommonLogic.splitByComma(ctacPsnPkList);

            // check max count
            if (ctacPsnPkArray.length > NMAL6700Constant.MAX_CUST_INV_RULE_RCPNT_CNT) {
                gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM0289E);
                return false;
            }

            // check format
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (!isNumber(ctacPsnPk)) {
                    gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM8075E, new String[] {"single byte numeric character, and can be separated by comma." });
                    return false;
                }
            }

            // check max length
            CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (ctacPsnPk.length() > tMsg.getAttr("ctacPsnPk").getDigit()) {
                    gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, ZZM9001E, new String[] {"External Email Contact" });
                    return false;
                }
            }

            // check duplicate
            List<String> list = new ArrayList<String>();
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (list.contains(ctacPsnPk)) {
                    gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM0072E, new String[] {"External Email Contact" });
                    return false;
                } else {
                    list.add(ctacPsnPk);
                }
            }

            // check master
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (ZYPCommonFunc.hasValue(ctacPsnPk)) {
                    if (!chkContact(cMsg, gcMsg, glblCmpyCd, new BigDecimal(ctacPsnPk))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isNumber(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     * @param ctacPsnPk BigDecimal
     * @return boolean
     */
    public static boolean chkContact(NMAL6700CMsg cMsg, NMAL6700_GCMsg gcMsg, String glblCmpyCd, BigDecimal ctacPsnPk) {

        CTAC_PSNTMsg ctacPsnTMsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnPk, ctacPsnPk);
        ctacPsnTMsg = (CTAC_PSNTMsg) S21FastTBLAccessor.findByKey(ctacPsnTMsg);
        if (ctacPsnTMsg == null) {
            gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM8121E, new String[] {"External Email Contact" });
            return false;
        }
        S21SsmEZDResult result = NMAL6700Query.getInstance().countContact(cMsg, gcMsg, ctacPsnPk);
        BigDecimal count = BigDecimal.ZERO;
        if (!result.isCodeNotFound()) {
            count = (BigDecimal) result.getResultObject();
        }

        if (BigDecimal.ZERO.compareTo(count) == 0) {
            gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM0304E);
            return false;
        }

        return true;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean chkBillVehicle(NMAL6700CMsg cMsg, NMAL6700_GCMsg gcMsg, String glblCmpyCd) {

        boolean rtnFlg = true;
        if (ZYPCommonFunc.hasValue(gcMsg.custBllgVcleCd_G3)) {
            CUST_BLLG_VCLETMsg custBllgVcleTMsg = new CUST_BLLG_VCLETMsg();
            custBllgVcleTMsg = NMAL6700CommonLogic.findCustBllgVcle(glblCmpyCd, gcMsg.custBllgVcleCd_G3.getValue());
            if (custBllgVcleTMsg == null) {
                gcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM8121E, new String[] {"Bill Vehicle" });
                return false;
            } else {

                if (ZYPConstant.FLG_ON_Y.equals(custBllgVcleTMsg.invSpclBillFlg.getValue())
                        && !CUST_BLLG_TP.CONSOLIDATED.equals(gcMsg.custBllgTpCd_G3.getValue())) {
                    String[] args = {ZYPCodeDataUtil.getName(CUST_BLLG_VCLE.class, glblCmpyCd, gcMsg.custBllgVcleCd_G3.getValue())};
                    gcMsg.custBllgTpCd_G3.setErrorInfo(1, NMAM0292E, args);
                    gcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM0292E, args);
                    rtnFlg = false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(custBllgVcleTMsg.itrlRvwFlg.getValue())
                        && splitByComma(gcMsg.xxGenlFldAreaTxt_G1.getValue()).length == 0) {
                    String[] args = {"Bill Vehicle" , ZYPCodeDataUtil.getName(CUST_BLLG_VCLE.class, glblCmpyCd, gcMsg.custBllgVcleCd_G3.getValue()), "Internal Email Review" };
                    gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM8180E, args);
                    rtnFlg = false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(custBllgVcleTMsg.emlEligFlg.getValue())
                        && splitByComma(gcMsg.xxGenlFldAreaTxt_G2.getValue()).length == 0) {
                    String[] args = {"Bill Vehicle" , ZYPCodeDataUtil.getName(CUST_BLLG_VCLE.class, glblCmpyCd, gcMsg.custBllgVcleCd_G3.getValue()), "External Email Review" };
                    gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM8180E, args);
                    rtnFlg = false;
                }
            }
        }

        return rtnFlg;
    }

    /**
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean chkResource(NMAL6700_GCMsg gcMsg, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(gcMsg.xxGenlFldAreaTxt_G1)) {
            String psnCdList = gcMsg.xxGenlFldAreaTxt_G1.getValue();
            String[] psnCdArray = NMAL6700CommonLogic.splitByComma(psnCdList);

            // check max count
            if (psnCdArray.length > NMAL6700Constant.MAX_CUST_INV_RULE_RCPNT_CNT) {
                gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM0289E);
                return false;
            }

            // check max length
            S21_PSNTMsg tMsg = new S21_PSNTMsg();
            for (String psnCd : psnCdArray) {
                if (psnCd.length() > tMsg.getAttr("psnCd").getDigit()) {
                    gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, ZZM9001E, new String[] {"Internal Email Review" });
                    return false;
                }
            }

            // check duplicate
            List<String> list = new ArrayList<String>();
            for (String psnCd : psnCdArray) {
                if (list.contains(psnCd)) {
                    gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM0072E, new String[] {"Internal Email Review" });
                    return false;
                } else {
                    list.add(psnCd);
                }
            }

            // check master
            for (String psnCd : psnCdArray) {
                if (ZYPCommonFunc.hasValue(psnCd)) {
                    if (!chkResource(gcMsg, glblCmpyCd, psnCd)) {
                        gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM8121E, new String[] {"Internal Email Review" });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     * @param psnCd String
     * @return boolean
     */
    public static boolean chkResource(NMAL6700_GCMsg gcMsg, String glblCmpyCd, String psnCd) {

        String mailCheckFlg;
        if (ZYPCommonFunc.hasValue(gcMsg.xxGenlFldAreaTxt_G1)) {
            mailCheckFlg = ZYPConstant.FLG_OFF_N;
            S21SsmEZDResult result = NMAL6700Query.getInstance().countResource(gcMsg, glblCmpyCd, mailCheckFlg, psnCd);
            BigDecimal count = BigDecimal.ZERO;
            if (!result.isCodeNotFound()) {
                count = (BigDecimal) result.getResultObject();
            }

            if (BigDecimal.ZERO.compareTo(count) == 0) {
                gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM8121E, new String[] {"Internal Email Review" });
                return false;
            }
            mailCheckFlg = ZYPConstant.FLG_ON_Y;
            result = NMAL6700Query.getInstance().countResource(gcMsg, glblCmpyCd, mailCheckFlg, psnCd);
            count = BigDecimal.ZERO;
            if (!result.isCodeNotFound()) {
                count = (BigDecimal) result.getResultObject();
            }

            if (BigDecimal.ZERO.compareTo(count) == 0) {
                gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM0305E);
                return false;
            }
        }

        return true;
    }

    // Del Start 2019/08/07 QC#52385
//    /**
//     * @param bizMsg NMAL6700CMsg
//     * @param glblCmpyCd String
//     * @param dsAcctNum String
//     * @return NMZC610001PMsg
//     */
//    public static NMZC610001PMsg callCustomerInfomationGetApi(NMAL6700CMsg bizMsg, String glblCmpyCd, String dsAcctNum) {
//
//        NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_RELATED_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxChildRelnFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, dsAcctNum);
//        // Add Start 2018/03/20 QC#25000
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd_SB, NMZC610001Constant.RELATED_BILL_SHIP_ALL_RGTN_STS_CD);
//        // Add End 2018/03/20 QC#25000
//        new NMZC610001().execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
//        return custInfoGetApiPMsg;
//    }
    // Del End 2019/08/07 QC#52385

    /**
     * @param csmsgArray NMAL6700_CSMsgArray
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean chkNestRelation(NMAL6700_CSMsgArray csmsgArray, String dsAcctNum, String glblCmpyCd) {

        boolean normalEndFlg = true;
        S21SsmEZDResult result = NMAL6700Query.getInstance().getNestRelationParent(dsAcctNum, glblCmpyCd);
        List parentList = null;
        int parentListCount = 0;
        if (result.isCodeNormal()) {
            parentList = (List) result.getResultObject();
            parentListCount = parentList.size();
        }
        result = NMAL6700Query.getInstance().getNestRelationChild(dsAcctNum, glblCmpyCd);
        List childList = null;
        int childListCount = 0;
        if (result.isCodeNormal()) {
            childList = (List) result.getResultObject();
            childListCount = childList.size();
        }
        for (int j = 0; j < csmsgArray.getValidCount(); j++) {
            NMAL6700_CSMsg csMsg  = csmsgArray.no(j);
            if (!ZYPCommonFunc.hasValue(csMsg.dsAcctRelnPk_C1)
                    && DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {

                for (int i = 0; i < parentListCount; i++) {
                    Map map = (Map) parentList.get(i);
                    String chkDsAcctNum = (String) map.get("DS_ACCT_NUM");
                    if (csMsg.dsAcctNum_C1.getValue().equals(chkDsAcctNum)) {
                        csMsg.xxChkBox_C1.setErrorInfo(1, NMAM0295E);
                        normalEndFlg = false;
                    }
                }
                for (int i = 0; i < childListCount; i++) {
                    Map map = (Map) childList.get(i);
                    String chkDsAcctNum = (String) map.get("DS_ACCT_NUM");
                    if (csMsg.dsAcctNum_C1.getValue().equals(chkDsAcctNum)) {
                        csMsg.xxChkBox_C1.setErrorInfo(1, NMAM0295E);
                        normalEndFlg = false;
                    }
                }
            }
        }

        return normalEndFlg;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabAddress(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (sMsg.A.no(i).xxChkBox_AP.isError()) {
                getPageFromSMsg_TabAddress(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabRelnShips(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (sMsg.C.no(i).effFromDt_C1.isError() || sMsg.C.no(i).xxChkBox_C1.isError()
                    || sMsg.C.no(i).dsAcctRelnTpCd_C3.isError() || sMsg.C.no(i).dsAcctNum_C1.isError()
                    || sMsg.C.no(i).xxChkBox_CB.isError() || sMsg.C.no(i).xxChkBox_CS.isError()
                    || sMsg.C.no(i).xxChkBox_CR.isError() 
                    ) {
                getPageFromSMsg_TabRelnShips(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            if (sMsg.D.no(i).dsPrimLocFlg_D1.isError()) {
                getPageFromSMsg_TabContacts(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            if (sMsg.E.no(i).dsBizAreaCd_E3.isError() || sMsg.E.no(i).dsAcctGrpCd_E3.isError()
                    || sMsg.E.no(i).dsAcctGrpDescTxt_E3.isError() || sMsg.E.no(i).effFromDt_E1.isError()
                    || sMsg.E.no(i).effThruDt_E1.isError()) {
                getPageFromSMsg_TabMarketing(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.I.getValidCount(); i++) {
            if (sMsg.I.no(i).effFromDt_I1.isError() || sMsg.I.no(i).effThruDt_I1.isError()) {
                getPageFromSMsg_TabBankAcct(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.J.getValidCount(); i++) {
            if (sMsg.J.no(i).lineBizTpCd_J3.isError() || sMsg.J.no(i).dsBizAreaCd_J3.isError()
                    || sMsg.J.no(i).dsCustMsgTpCd_J3.isError() || sMsg.J.no(i).custEffLvlCd_J3.isError()
                    // Mod Start 2018/04/27 QC#24779
                    //|| sMsg.J.no(i).effThruDt_J1.isError()) {
                    || sMsg.J.no(i).effThruDt_J1.isError() || sMsg.J.no(i).dsCustMsgTxt_J1.isError()) {
                // Mod End 2018/04/27 QC#24779
                getPageFromSMsg_TabMsgNote(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * getPageFromSMsg_TabMarketing
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.E);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.E.length()) * cMsg.E.length();
        if (startIndexOfSMsg > sMsg.E.getValidCount()
                || (startIndexOfSMsg >= 20 && startIndexOfSMsg % sMsg.E.getValidCount() == 0)) {
            startIndexOfSMsg = startIndexOfSMsg - 20;
        }

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.E.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.E.getValidCount()) {
                EZDMsg.copy(sMsg.E.no(startIndexOfSMsg + i), null, cMsg.E.no(i), null);
                NMAL6700_ECMsg ecMsg = cMsg.E.no(i);
                // mod start 2023/07/18 QC#61421
                //ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, ecMsg.dsBizAreaCd_E1, ecMsg.dsBizAreaNm_E2);
                createDsBizAreaPulldownForMarketingTab(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode(), ecMsg);
                // mod end 2023/07/18 QC#61421
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.E.setValidCount(copiedRows);

        // Set page number
        if (sMsg.E.getValidCount() > 0) {
            int totalRecordCount = sMsg.E.getValidCount();
            int maxRowPerPage = cMsg.E.length();

            // Record count
            cMsg.xxPageShowFromNum_M1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_M1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_M1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_M1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_M1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_M1.clear();
            cMsg.xxPageShowFromNum_M1.clear();
            cMsg.xxPageShowToNum_M1.clear();
            cMsg.xxPageShowCurNum_M1.clear();
            cMsg.xxPageShowOfNum_M1.clear();
        }
    }

    /**
     * getPageFromSMsg_TabMarketing
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.I);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.I.length()) * cMsg.I.length();
        if (startIndexOfSMsg > sMsg.I.getValidCount()
                || (startIndexOfSMsg >= cMsg.I.length() && startIndexOfSMsg % sMsg.I.getValidCount() == 0)) {
            startIndexOfSMsg = startIndexOfSMsg - cMsg.I.length();
        }

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.I.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.I.getValidCount()) {
                // START 2020/12/14 J.Evangelista [QC#57937,ADD]
                EZDMsg.copy(sMsg.I.no(startIndexOfSMsg + i), null, cMsg.I.no(i), null);
                // END   2020/12/14 J.Evangelista [QC#57937,ADD]
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.I.setValidCount(copiedRows);

        // Set page number
        if (sMsg.I.getValidCount() > 0) {
            int totalRecordCount = sMsg.I.getValidCount();
            int maxRowPerPage = cMsg.I.length();

            // Record count
            cMsg.xxPageShowFromNum_I1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_I1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_I1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_I1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_I1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_I1.clear();
            cMsg.xxPageShowFromNum_I1.clear();
            cMsg.xxPageShowToNum_I1.clear();
            cMsg.xxPageShowCurNum_I1.clear();
            cMsg.xxPageShowOfNum_I1.clear();
        }
    }

    /**
     * getPageFromSMsg_TabMsgNote
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.J);
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        int startIndexOfSMsg = (indexOfSMsg / cMsg.J.length()) * cMsg.J.length();
        if (startIndexOfSMsg > sMsg.J.getValidCount()
                || (startIndexOfSMsg >= cMsg.J.length() && startIndexOfSMsg % sMsg.J.getValidCount() == 0)) {
            startIndexOfSMsg = startIndexOfSMsg - cMsg.J.length();
        }
        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.J.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.J.getValidCount()) {
                EZDMsg.copy(sMsg.J.no(startIndexOfSMsg + i), null, cMsg.J.no(i), null);
                NMAL6700_JCMsg jcMsg = cMsg.J.no(i);
                NMAL6700CommonLogic.createMsgNotePulldownList(jcMsg, glblCmpyCd);
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.J.setValidCount(copiedRows);

        // Set page number
        if (sMsg.J.getValidCount() > 0) {
            int totalRecordCount = sMsg.J.getValidCount();
            int maxRowPerPage = cMsg.J.length();

            // Record count
            cMsg.xxPageShowFromNum_J1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_J1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_J1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_J1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_J1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_J1.clear();
            cMsg.xxPageShowFromNum_J1.clear();
            cMsg.xxPageShowToNum_J1.clear();
            cMsg.xxPageShowCurNum_J1.clear();
            cMsg.xxPageShowOfNum_J1.clear();
        }
    }

    /**
     * callCreditProfileUpdateApi
     * @param bizMsg NMAL6700CMsg
     * @param slsDt String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean callCreditProfileUpdateApi(NMAL6700CMsg bizMsg, String slsDt, String glblCmpyCd) {
        // Credit Profile Update (Balance)
        boolean rtnFlg = true;
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();

        paramMsg.xxModeCd.setValue(NFZC202001.MODE_CUST_ACCT);
        paramMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramMsg.sellToCustCd.setValue(bizMsg.dsAcctNum_H1.getValue());
        paramMsg.procDt.setValue(slsDt);

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                bizMsg.crLimitAmt_U1.setErrorInfo(1, msgId);
                bizMsg.setMessageInfo(msgId);
                rtnFlg = false;
            }
        }
        return rtnFlg;
    }

    /**
     * getDefaultDate
     * @return String
     */
    public static String getDefaultDate() {
     String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        return slsDt;
    }

    /**
     * getPsnNm
     * @param  psnCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getPsnNm(String psnCd, String glblCmpyCd) {

        S21_PSNTMsg psnMsg = new S21_PSNTMsg();
        psnMsg.glblCmpyCd.setValue(glblCmpyCd);
        psnMsg.psnCd.setValue(psnCd);

        psnMsg = (S21_PSNTMsg) S21CacheTBLAccessor.findByKey(psnMsg);
        if (psnMsg != null) {
            String firstNm = psnMsg.psnFirstNm.getValue();
            String lastNm = psnMsg.psnLastNm.getValue();

            if (ZYPCommonFunc.hasValue(firstNm)) {
                return firstNm.concat(" ").concat(lastNm).trim();
            }
        }
        return "";
    }

    /**
     * getCtacPsnNm
     * @param psnPk BigDecimal
     * @param glblCmpyCd String
     * @return String
     */
    public static String getCtacPsnNm(BigDecimal psnPk, String glblCmpyCd) {

        CTAC_PSNTMsg psnMsg = new CTAC_PSNTMsg();
        psnMsg.glblCmpyCd.setValue(glblCmpyCd);
        psnMsg.ctacPsnPk.setValue(psnPk);

        psnMsg = (CTAC_PSNTMsg) S21CacheTBLAccessor.findByKey(psnMsg);
        if (psnMsg != null) {
            String firstNm = psnMsg.ctacPsnFirstNm.getValue();
            String lastNm = psnMsg.ctacPsnLastNm.getValue();

            if (ZYPCommonFunc.hasValue(firstNm)) {
                return firstNm.concat(" ").concat(lastNm).trim();
            }
        }
        return "";
    }

    /**
     * getNamePsnAndCtac
     * @param sMsg NMAL6700SMsg
     * @param glblCmpyCd String
     */
    public static void getNamePsnAndCtac(NMAL6700SMsg sMsg, String glblCmpyCd) {
        getInvoiceSourceListInternalReview(sMsg, glblCmpyCd);
        getInvoiceSourceListExternalContact(sMsg, glblCmpyCd);
    }

    /**
     * getNamePsnAndCtac
     * @param sMsg NMAL6700SMsg
     * @param glblCmpyCd String
     */
    public static void getInvoiceSourceListInternalReview(NMAL6700SMsg sMsg, String glblCmpyCd) {
        for (int i = 0; i < sMsg.G.getValidCount(); i++) {
            List<String> listPsnCd = new ArrayList<String>();
            List<String> listPsnNm = new ArrayList<String>();
            S21SsmEZDResult result = NMAL6700Query.getInstance().getInvoiceSourceListInternalReview(sMsg.G.no(i).dsCustInvRulePk_G1.getValue());
            if (result.isCodeNormal()) {
                List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
                for (Map<String, Object> map : listMap) {
                    String psnCd = (String) map.get("PSN_CD");
                    String psnNm = NMAL6700CommonLogic.getPsnNm(psnCd, glblCmpyCd);
                    listPsnCd.add(psnCd);
                    listPsnNm.add(psnNm);
                }
            }
            String psnCdList = NMAL6700CommonLogic.combineByComma(listPsnCd);
            String psnNmList = NMAL6700CommonLogic.combineByComma(listPsnNm);
            ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxGenlFldAreaTxt_G1, psnCdList);
            ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxCustInvRuleRcpntTxt_G1, psnNmList);
        }
    }

    /**
     * getNamePsnAndCtac
     * @param sMsg NMAL6700SMsg
     * @param glblCmpyCd String
     */
    public static void getInvoiceSourceListExternalContact(NMAL6700SMsg sMsg, String glblCmpyCd) {
        for (int i = 0; i < sMsg.G.getValidCount(); i++) {
            List<String> listCtacPsnPk = new ArrayList<String>();
            List<String> listCtacPsnNm = new ArrayList<String>();
            S21SsmEZDResult result = NMAL6700Query.getInstance().getInvoiceSourceListExternalContact(sMsg.G.no(i).dsCustInvRulePk_G1.getValue());
            if (result.isCodeNormal()) {
                List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
                for (Map<String, Object> map : listMap) {
                    String ctacPsnPk = ((BigDecimal) map.get("CTAC_PSN_PK")).toPlainString();
                    String ctacPsnNm = NMAL6700CommonLogic.getCtacPsnNm(new BigDecimal(ctacPsnPk), glblCmpyCd);
                    listCtacPsnPk.add(ctacPsnPk);
                    listCtacPsnNm.add(ctacPsnNm);
                }
            }
            String ctacPsnPkList = NMAL6700CommonLogic.combineByComma(listCtacPsnPk);
            String ctacPsnNmList = NMAL6700CommonLogic.combineByComma(listCtacPsnNm);
            ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxGenlFldAreaTxt_G2, ctacPsnPkList);
            ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxCustInvRuleRcpntTxt_G2, ctacPsnNmList);
        }
    }

    /**
     * Method name: getCltPtfoNm
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void getCltPtfoNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        S21SsmEZDResult result = NMAL6700Query.getInstance().getCltPtfoNm(cMsg);

        if (result.isCodeNormal()) {
            // 2018/07/13 QC#26613 Mod Start
//            String cltPtfoNm = (String) ((Map) result.getResultObject()).get("CLT_PTFO_NM");
            String cltPtfoNm = (String) ((Map) result.getResultObject()).get("CLT_PTFO_DESC_TXT");
            // 2018/07/13 QC#26613 Mod End
            BigDecimal cltPtfoPk = (BigDecimal) ((Map) result.getResultObject()).get("CLT_PTFO_PK");
            // 2018/07/13 QC#26613 Mod Start
//            ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoNm_U1, cltPtfoNm);
            ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoDescTxt_U1, cltPtfoNm);
            // 2018/07/13 QC#26613 Mod End
            ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoPk_U1, cltPtfoPk);
        } else {
            cMsg.cltPtfoCd_U1.setErrorInfo(1, NZZM0000E);
        }
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void setTemplateForCredit(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.ccyCd_U3) && !ZYPCommonFunc.hasValue(cMsg.custCrRtgCd_U3)
                && !ZYPCommonFunc.hasValue(cMsg.crLimitAmt_U1) && !ZYPCommonFunc.hasValue(cMsg.crChkReqTpCd_U3)
                && !ZYPCommonFunc.hasValue(cMsg.crRiskClsCd_U3) && !ZYPCommonFunc.hasValue(cMsg.pmtTermCashDiscCd_U3)
                && !ZYPCommonFunc.hasValue(cMsg.cltPtfoCd_U1)) {
            S21SsmEZDResult res = NMAL6700Query.getInstance().getTemplateForCredit(cMsg, sMsg);
            if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.ccyCd_U3, sMsg.ccyCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.custCrRtgCd_U3, sMsg.custCrRtgCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.crLimitAmt_U1, sMsg.crLimitAmt_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.crChkReqTpCd_U3, sMsg.crChkReqTpCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.crRiskClsCd_U3, sMsg.crRiskClsCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscCd_U3, sMsg.pmtTermCashDiscCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoCd_U1, sMsg.cltPtfoCd_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.ovrdPmtTermFlg_U1, sMsg.ovrdPmtTermFlg_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.cashOrCcReqFlg_U1, sMsg.cashOrCcReqFlg_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.custHardHldFlg_U1, sMsg.custHardHldFlg_U1);
                // START 2018/01/25 H.ikeda [QC#22095,ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.contrCrRiskClsCd_U3, sMsg.contrCrRiskClsCd_U3);
                // End 2018/01/25 H.ikeda [QC#22095,ADD]
                // Add Start 2019/02/13 QC#30293
                ZYPEZDItemValueSetter.setValue(cMsg.arStmtFlg_U1, sMsg.arStmtFlg_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.sendCrBalStmtFlg_U1, sMsg.sendCrBalStmtFlg_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.arStmtIssCycleCd_U3, sMsg.arStmtIssCycleCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpCd_U1, sMsg.cltCustTpCd_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.dsCltAcctStsCd_U3, sMsg.dsCltAcctStsCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.lateFeeFlg_U1, sMsg.lateFeeFlg_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.lateFeeAmt_U1, sMsg.lateFeeAmt_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.dsTaxExemFlg_U1, sMsg.dsTaxExemFlg_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.dsExemExprDt_U1, sMsg.dsExemExprDt_U1);
                ZYPEZDItemValueSetter.setValue(cMsg.dsTaxPrntTpCd_U3, sMsg.dsTaxPrntTpCd_U3);
                ZYPEZDItemValueSetter.setValue(cMsg.autoCashHrchCd_U3, sMsg.autoCashHrchCd_U3);
                String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
                getCltCustTpNm(glblCmpyCd, cMsg);
                // Add End 2019/02/13 QC#30293
                getCltPtfoNm(cMsg, sMsg);
            }
        }
    }

    /**
     * getDsAcctNm
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getDsAcctNm(String dsAcctNum, String glblCmpyCd) {

        SELL_TO_CUSTTMsg exSellToCustTMsg = new SELL_TO_CUSTTMsg();
        exSellToCustTMsg.setSQLID("001");
        exSellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        exSellToCustTMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        SELL_TO_CUSTTMsgArray resultArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(exSellToCustTMsg);

        if (resultArray == null || resultArray.getValidCount() == 0) {
            DS_ACCT_PROSTMsg exDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            exDsAcctProsTMsg.setSQLID("001");
            exDsAcctProsTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            exDsAcctProsTMsg.setConditionValue("dsAcctNum01", dsAcctNum);
            DS_ACCT_PROSTMsgArray resultArrayPros = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(exDsAcctProsTMsg);
            if (resultArrayPros != null && resultArrayPros.getValidCount() > 0) {
                return resultArrayPros.no(0).dsAcctNm.getValue();
            }
        } else {
            return resultArray.no(0).dsAcctNm.getValue();
        }

        return "";
    }

    /**
     * getDsSpclHdlgTp
     * @param dsSpclHdlgTpCd String
     * @param glblCmpyCd String
     * @return DS_SPCL_HDLG_TPTMsgArray
     */
    public static DS_SPCL_HDLG_TPTMsgArray getDsSpclHdlgTp(String dsSpclHdlgTpCd, String glblCmpyCd) {
        DS_SPCL_HDLG_TPTMsg dsSpclHdlgTpTMsg = new DS_SPCL_HDLG_TPTMsg();
        dsSpclHdlgTpTMsg.setSQLID("001");
        dsSpclHdlgTpTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsSpclHdlgTpTMsg.setConditionValue("dsSpclHdlgTpCd01", dsSpclHdlgTpCd);
        DS_SPCL_HDLG_TPTMsgArray resultArray = (DS_SPCL_HDLG_TPTMsgArray) EZDTBLAccessor.findByCondition(dsSpclHdlgTpTMsg);

        return resultArray;
    }

    /**
     * chkActiveAccount
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return String
     */
    public static boolean chkActiveAccount(String dsAcctNum, String glblCmpyCd) {

        String activeFlg = "";
        SELL_TO_CUSTTMsg exSellToCustTMsg = new SELL_TO_CUSTTMsg();
        exSellToCustTMsg.setSQLID("001");
        exSellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        exSellToCustTMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        SELL_TO_CUSTTMsgArray resultArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(exSellToCustTMsg);

        if (resultArray == null || resultArray.getValidCount() == 0) {
            DS_ACCT_PROSTMsg exDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            exDsAcctProsTMsg.setSQLID("001");
            exDsAcctProsTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            exDsAcctProsTMsg.setConditionValue("dsAcctNum01", dsAcctNum);
            DS_ACCT_PROSTMsgArray resultArrayPros = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(exDsAcctProsTMsg);
            if (resultArrayPros != null && resultArrayPros.getValidCount() > 0) {
                activeFlg = resultArrayPros.no(0).dsAcctActvCustFlg.getValue();
            }
        } else {
            activeFlg = resultArray.no(0).dsAcctActvCustFlg.getValue();
        }

        return ZYPConstant.FLG_ON_Y.equals(activeFlg);
    }

    /**
     * getDefCoaAfflCd
     * @return String
     */
    public static String getDefCoaAfflCd() {

        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String constString = ZYPCodeDataUtil.getVarCharConstValue(CONST_EXTERNAL_DEF_COA_AFFL_CD, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(constString)) {
            return constString;
        } else {
            return "";
        }
    }

    /**
     * getDefCoaChCd
     * @return String
     */
    public static String getDefCoaChCd() {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getDefCoa(glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            List list = (List) ssmResult.getResultObject();
            if (list != null && list.size() > 0) {
                Map map = (Map) list.get(0);
                String coaChCd = (String) map.get("COA_CH_CD");
                return coaChCd;
            }
        }
        return "";
    }

    /**
     * getAddAttributeNum
     * @param kcMsgArray NMAL6700_KCMsgArray
     * @return int
     */
    public static int getAddAttributeNum(NMAL6700_KCMsgArray kcMsgArray) {

        for (int i = 0; i < kcMsgArray.getValidCount(); i++) {
            if (Integer.valueOf(kcMsgArray.no(i).dsAcctRefAttrbNum_K1.getValue()) == i + 1) {
                continue;
            }
            return i + 1;
        }
        return kcMsgArray.getValidCount() + 1;
    }

    /**
     * setNewAttribute
     * @param kcMsgArray NMAL6700_KCMsgArray
     * @param newMsg NMAL6700_KCMsg
     * @param addNum int
     */
    public static void setNewAttribute(NMAL6700_KCMsgArray kcMsgArray, NMAL6700_KCMsg newMsg, int addNum) {

        int i = kcMsgArray.getValidCount();
        for (; i >= addNum; i--) {
            EZDMsg.copy(kcMsgArray.no(i - 1), null, kcMsgArray.no(i), null);
        }
        EZDMsg.copy(newMsg, null, kcMsgArray.no(i), null);
        kcMsgArray.setValidCount(kcMsgArray.getValidCount() + 1);
    }

    /**
     * getParentNode
     * 
     * @param glblCmpyCd String
     * @param targetDsAcctNum String
     * @param inActiveFlag boolean
     * @param showAllAddress boolean
     * @return List
     */
    public static List getParentNode(String glblCmpyCd, String targetDsAcctNum, boolean inActiveFlag, boolean showAllAddress) {
        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getParentNode(glblCmpyCd, targetDsAcctNum, inActiveFlag, showAllAddress);
        List resultList = (List) ssmResult.getResultObject();
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return resultList;
    }

    // Del Start 2017/11/16 QC#17322(Sol#174)
    ///**
    // * getTreeListLeaseReln
    // * 
    // * @param cMsg NMAL6700CMsg
    // * @param glblCmpyCd String
    // * @param parentDsAcctNum String
    // * @param targetDsAcctNum String
    // * @param inActiveFlag boolean
    // * @return List
    // */
    // public static List getTreeListLeaseReln(NMAL6700CMsg cMsg, String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum, boolean inActiveFlag) {
    //     S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getTreeListLeaseReln(cMsg, glblCmpyCd, parentDsAcctNum, targetDsAcctNum, inActiveFlag);
    //         List resultList = (List) ssmResult.getResultObject();
    //         if (!ssmResult.isCodeNormal()) {
    //             return null;
    //         }
    //         return resultList;
    // }
    // Del End 2017/11/16 QC#17322(Sol#174)

     /**
      * getParentData
      * 
      * @param glblCmpyCd String
      * @param targetDsAcctNum String
      * @return List
      */
     public static List getParentData(String glblCmpyCd, String targetDsAcctNum) {
         S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getParentData(glblCmpyCd, targetDsAcctNum);
         List resultList = (List) ssmResult.getResultObject();
         if (!ssmResult.isCodeNormal()) {
             return null;
         }
         return resultList;
     }

     /**
      * getParentData
      * 
      * @param cMsg NMAL6700CMsg
      * @param glblCmpyCd String
      * @param dsAcctNum String
      * @param inActiveFlag boolean
      * @param showAllAddress boolean
      * @return List
      */
     public static List getTreeListLocation(NMAL6700CMsg cMsg, String glblCmpyCd, String dsAcctNum, boolean inActiveFlag, boolean showAllAddress) {
         S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getTreeListLocation(cMsg, glblCmpyCd, dsAcctNum, inActiveFlag, showAllAddress);
             List resultList = (List) ssmResult.getResultObject();
             if (!ssmResult.isCodeNormal()) {
                 return null;
             }
             return resultList;
     }

    private static String combineByComma(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String str : list) {
            if (sb.length() > 0) {
                sb.append(NMAL6700Constant.CHAR_COMMA);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * @param str String
     * @return String[]
     */
    public static String[] splitByComma(String str) {
        String[] array = str.split(NMAL6700Constant.CHAR_COMMA);
        List<String> list = new ArrayList<String>();
        for (String s : array) {
            if (ZYPCommonFunc.hasValue(s)) {
                list.add(s);
            }
        }
        return list.toArray(new String[] {});
    }

    // Add Start 2017/12/11 QC#20357
    // Mod Start 2019/08/07 QC#52385
    ///**
    // * @param cMsg NMAL6700CMsg
    // * @param sMsg NMAL6700SMsg
    // * @param custInfoGetApiPMsg NMZC610001PMsg
    // * @return boolean
    // */
    //public static boolean checkRelationAndDafaultBillShip(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, NMZC610001PMsg custInfoGetApiPMsg) {
    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param relCustInfoDefBillShip List<Map<String, Object>>
     * @return boolean
     */
    public static boolean checkRelationAndDafaultBillShip(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, List<Map<String, Object>> relCustInfoDefBillShip) {
        // Mod End 2019/08/07 QC#52385
        boolean normalEndFlg = true;

        // Related Account Number list for Bill To Flg : OFF
        List<String> relatedAccoutNumListBillTo = new ArrayList<String>();
        // Related Account Number list for Ship To Flg : OFF
        List<String> relatedAccoutNumListShipTo = new ArrayList<String>();
        // Related Account Number list for already checked
        List<String> checkedRelatedAccoutNumList = new ArrayList<String>();

        // Check delete relation and Default Bill To / Default Ship To
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            String relatedAccountNum = sMsg.C.no(i).dsAcctNum_C1.getValue();

            if (checkedRelatedAccoutNumList.contains(relatedAccountNum)) {
                continue;
            }

            String billToCustCd = null;
            String shipToCustCd = null;

            // Get Bill To Code / Ship To Code
            // Mod Start 2019/08/07 QC#52385
            //for (int j = 0; j < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); j++) {
            //    NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(j);
            //    if (NMAL6700CommonLogic.hasValueAndEquals(relatedAccountNum, inPmsg.relnDsAcctNum.getValue())) {
            //        billToCustCd = inPmsg.billToCustCd.getValue();
            //        shipToCustCd = inPmsg.shipToCustCd.getValue();
            for (int j = 0; j < relCustInfoDefBillShip.size(); j++) {
                Map<String, Object> result = relCustInfoDefBillShip.get(j);
                String relnDsAcctNum = (String) result.get("R_RELN_DS_ACCT_NUM");

                if (NMAL6700CommonLogic.hasValueAndEquals(relatedAccountNum, relnDsAcctNum)) {
                    billToCustCd = (String) result.get("R_BILL_TO_CUST_CD");
                    shipToCustCd = (String) result.get("R_SHIP_TO_CUST_CD");
                    // Mod End 2019/08/07 QC#52385

                    // Del Start 2018/03/16 QC#20357-1
                    //break;
                //}
            //}
            // Del End 2018/03/16 QC#20357-1

            // If Bill To flag : OFF
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_CB.getValue()) &&
            // If used for Default Bill To
                    NMAL6700CommonLogic.isDafaultBillTo(cMsg, billToCustCd) &&
                    // If all of Bill To flag are OFF
                    !NMAL6700CommonLogic.isCorrectBillTo(sMsg, relatedAccountNum)) {
                relatedAccoutNumListBillTo.add(relatedAccountNum);
            }

            // If Ship To flag : OFF
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_CS.getValue()) &&
            // If used for Default Ship To
                    NMAL6700CommonLogic.isDafaultShipTo(cMsg, shipToCustCd) &&
                    // If all of Ship To flag are OFF
                    !NMAL6700CommonLogic.isCorrectShipTo(sMsg, relatedAccountNum)) {
                relatedAccoutNumListShipTo.add(relatedAccountNum);
            }

                // Add Start 2018/03/16 QC#20357-1
                }
            }
            // Add End 2018/03/16 QC#20357-1

            checkedRelatedAccoutNumList.add(relatedAccountNum);
        }

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NMAL6700_CSMsg chkCsMsg = sMsg.C.no(i);
            String relatedAccountNum = chkCsMsg.dsAcctNum_C1.getValue();
            if (relatedAccoutNumListBillTo.contains(relatedAccountNum)) {
                chkCsMsg.xxChkBox_CB.setErrorInfo(1, NMAM0306E, new String[] {"Bill To", "Default Bill To" });
                normalEndFlg = false;
            }
            if (relatedAccoutNumListShipTo.contains(relatedAccountNum)) {
                chkCsMsg.xxChkBox_CS.setErrorInfo(1, NMAM0306E, new String[] {"Ship To", "Default Ship To" });
                normalEndFlg = false;
            }
        }

        return normalEndFlg;
    }

    /**
     * @param sMsg NMAL6700SMsg
     * @param relatedAccountNum String
     * @return boolean
     */
    public static boolean isCorrectBillTo(NMAL6700SMsg sMsg, String relatedAccountNum) {
        boolean result = false;

        int cnt = sMsg.C.getValidCount();
        for (int i = 0; i < cnt; i++) {
            String checkRelAccountNum = sMsg.C.no(i).dsAcctNum_C1.getValue();

            // Check same Related Account Number
            if (NMAL6700CommonLogic.hasValueAndEquals(relatedAccountNum, checkRelAccountNum)) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue()) && //
                        ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_CB.getValue())) {
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * @param sMsg NMAL6700SMsg
     * @param relatedAccountNum String
     * @return boolean
     */
    public static boolean isCorrectShipTo(NMAL6700SMsg sMsg, String relatedAccountNum) {
        boolean result = false;

        int cnt = sMsg.C.getValidCount();
        for (int i = 0; i < cnt; i++) {
            String checkRelAccountNum = sMsg.C.no(i).dsAcctNum_C1.getValue();

            // Check same Related Account Number
            if (NMAL6700CommonLogic.hasValueAndEquals(relatedAccountNum, checkRelAccountNum)) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue()) && //
                        ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_CS.getValue())) {
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param billToCustCd String
     * @return boolean
     */
    public static boolean isDafaultBillTo(NMAL6700CMsg cMsg, String billToCustCd) {
        boolean result = false;

        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            String defaultBillToCd = cMsg.F.no(i).dsDefBillToCd_F1.getValue();

            if (NMAL6700CommonLogic.hasValueAndEquals(billToCustCd, defaultBillToCd)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param billToCustCd String
     * @return boolean
     */
    public static boolean isDafaultShipTo(NMAL6700CMsg cMsg, String shipToCustCd) {
        boolean result = false;

        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            String defaultShipToCd = cMsg.F.no(i).dsDefShipToCd_F1.getValue();

            if (NMAL6700CommonLogic.hasValueAndEquals(shipToCustCd, defaultShipToCd)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static boolean hasValueAndEquals(String str1, String str2) {
        if (!ZYPCommonFunc.hasValue(str1)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(str2)) {
            return false;
        }
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }
    // Add End 2017/12/11 QC#20357

    // 2018/02/14 QC#20297(Sol#379) add start
    /**
     * getPageFromSMsg_TabMsgNote
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param indexOfSMsg int
     */
    public static void getPageFromSMsg_TabShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.M);
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        int startIndexOfSMsg = (indexOfSMsg / cMsg.M.length()) * cMsg.M.length();
        if (startIndexOfSMsg > sMsg.M.getValidCount()
                || (startIndexOfSMsg >= cMsg.M.length() && startIndexOfSMsg % sMsg.M.getValidCount() == 0)) {
            startIndexOfSMsg = startIndexOfSMsg - cMsg.M.length();
        }
        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.M.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.M.getValidCount()) {
                EZDMsg.copy(sMsg.M.no(startIndexOfSMsg + i), null, cMsg.M.no(i), null);
                NMAL6700_MCMsg mcMsg = cMsg.M.no(i);
                NMAL6700CommonLogic.createShippingPulldownList(mcMsg, glblCmpyCd);
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.M.setValidCount(copiedRows);

        // Set page number
        if (sMsg.M.getValidCount() > 0) {
            int totalRecordCount = sMsg.M.getValidCount();
            int maxRowPerPage = cMsg.M.length();

            // Record count
            cMsg.xxPageShowFromNum_M2.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_M2.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_M2.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_M2.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_M2.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_M2.clear();
            cMsg.xxPageShowFromNum_M2.clear();
            cMsg.xxPageShowToNum_M2.clear();
            cMsg.xxPageShowCurNum_M2.clear();
            cMsg.xxPageShowOfNum_M2.clear();
        }
    }

    /**
     * @param mcMsg NMAL6700_JCMsg
     * @param glblCmpyCd String
     */
    public static void createShippingPulldownList(NMAL6700_MCMsg mcMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, mcMsg.lineBizTpCd_M1, mcMsg.lineBizTpNm_M2);
        // mod start 2023/07/18 QC#61421
        //ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, mcMsg.dsBizAreaCd_M1, mcMsg.dsBizAreaNm_M2);
        createDsBizAreaPulldownForShippingTab(glblCmpyCd, mcMsg);
        // mod end 2023/07/18 QC#61421
        createFrtCondPulldown(glblCmpyCd, mcMsg);
        createShpgSvcLvlPulldown(glblCmpyCd, mcMsg);

        mcMsg.custEffLvlCd_M1.clear();
        mcMsg.custEffLvlCd_M1.no(0).setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        mcMsg.custEffLvlCd_M1.no(1).setValue(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        mcMsg.custEffLvlNm_M2.clear();
        mcMsg.custEffLvlNm_M2.no(0).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_ONLY));
        mcMsg.custEffLvlNm_M2.no(1).setValue(ZYPCodeDataUtil.getName(CUST_EFF_LVL.class, glblCmpyCd, CUST_EFF_LVL.ACCOUNT_AND_CHILDREN));
    }
    
    /**
     * copyCMsgToSMsgJ
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void copyCMsgToSMsgM(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        copyCMsgToSMsgM(cMsg, sMsg, cMsg.xxPageShowFromNum_M2.getValueInt() - 1);
    }

    /**
     * copyCMsgToSMsgJ
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param fromCnt From Count
     */
    public static void copyCMsgToSMsgM(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.M.getValidCount()); i++) {
            EZDMsg.copy(cMsg.M.no(cMsgCnt), null, sMsg.M.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NMAL6700CMsg
     * @param shareMsg NMAL6700SMsg
     */
    public static void copyBizToShareM(NMAL6700CMsg bizMsg, NMAL6700SMsg shareMsg) {

        shareMsg.xxDplyTab.setValue(bizMsg.xxDplyTab.getValue());

        int pageFrom = bizMsg.xxPageShowFromNum_M2.getValueInt() - 1;
        for (int i = 0; i < bizMsg.M.getValidCount(); i++) {
            if (pageFrom + i < shareMsg.M.length()) {
                EZDMsg.copy(bizMsg.M.no(i), null, shareMsg.M.no(pageFrom + i), null);
            } else {
                break;
            }
        }

    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    public static void showFirstErrorPage_TabShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        for (int i = 0; i < sMsg.M.getValidCount(); i++) {
            // 2018/12/10 QC#29315 Mod Start
//            if (sMsg.M.no(i).lineBizTpCd_M3.isError() || sMsg.M.no(i).dsBizAreaCd_M3.isError()
//                    || sMsg.M.no(i).frtCondCd_M3.isError() || sMsg.M.no(i).custEffLvlCd_M3.isError() || sMsg.M.no(i).shpgSvcLvlCd_M3.isError() 
//                    || sMsg.M.no(i).effThruDt_M1.isError()) {
            // 2023/01/13 QC#60860 Mod Start
//            if (sMsg.M.no(i).xxChkBox_MD.isError() || sMsg.M.no(i).lineBizTpCd_M3.isError() || sMsg.M.no(i).dsBizAreaCd_M3.isError() || sMsg.M.no(i).frtCondCd_M3.isError() || sMsg.M.no(i).shpgSvcLvlCd_M3.isError()
//                    || sMsg.M.no(i).vndCd_M3.isError() || sMsg.M.no(i).dsCarrAcctNum_M1.isError() || sMsg.M.no(i).effFromDt_M1.isError() || sMsg.M.no(i).effThruDt_M1.isError() || sMsg.M.no(i).custEffLvlCd_M3.isError()) {
            if (sMsg.M.no(i).xxChkBox_MD.isError() || sMsg.M.no(i).lineBizTpCd_M3.isError() || sMsg.M.no(i).dsBizAreaCd_M3.isError() || sMsg.M.no(i).frtCondCd_M3.isError() || sMsg.M.no(i).shpgSvcLvlCd_M3.isError()
                    || sMsg.M.no(i).carrNm_M3.isError() || sMsg.M.no(i).dsCarrAcctNum_M1.isError() || sMsg.M.no(i).effFromDt_M1.isError() || sMsg.M.no(i).effThruDt_M1.isError() || sMsg.M.no(i).custEffLvlCd_M3.isError()) {
            // 2023/01/13 QC#60860 Mod End
            // 2018/12/10 QC#29315 Mod End
                getPageFromSMsg_TabShipping(cMsg, sMsg, i);
                break;
            }
        }
    }

    /**
     * @param glblCmpyCd String
     * @param dsCustShpgDefPk BigDecimal
     * @param isLock if true then lock the record.
     * @return DS_CUST_SHPG_DEFTMsg
     */
    public static DS_CUST_SHPG_DEFTMsg findDsCustShpgDefForUpdate(String glblCmpyCd, BigDecimal dsCustShpgDefPk, boolean isLock) {
        DS_CUST_SHPG_DEFTMsg prmTMsg = new DS_CUST_SHPG_DEFTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustShpgDefPk, dsCustShpgDefPk);
        // QC#18961
        if (isLock) {
            return (DS_CUST_SHPG_DEFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
        }
        return (DS_CUST_SHPG_DEFTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    // 2018/12/10 QC#29315 Add Start
    /**
     * @param glblCmpyCd String
     * @param dsAcctCarrPk BigDecimal
     * @param isLock if true then lock the record.
     * @return DS_ACCT_CARRTMsg
     */
    public static DS_ACCT_CARRTMsg findDsAcctCarrForUpdate(String glblCmpyCd, BigDecimal dsAcctCarrPk, boolean isLock) {
        DS_ACCT_CARRTMsg prmTMsg = new DS_ACCT_CARRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctCarrPk, dsAcctCarrPk);

        if (isLock) {
            return (DS_ACCT_CARRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
        }
        return (DS_ACCT_CARRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // 2018/12/10 QC#29315 Add End

    public static void createFrtCondPulldown(String glblCmpyCd, NMAL6700_MCMsg mcMsg) {
        mcMsg.frtCondCd_M1.clear();
        mcMsg.frtCondNm_M2.clear();
        mcMsg.shpgSvcLvlCd_M1.clear();
        mcMsg.shpgSvcLvlNm_M2.clear();

        if (ZYPCommonFunc.hasValue(mcMsg.lineBizTpCd_M3)) {
            S21SsmEZDResult res = NMAL6700Query.getInstance().getFrtCondSvcReln(glblCmpyCd, mcMsg.lineBizTpCd_M3.getValue(), null);

            if (res.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();

                for (int i=0; i < resultList.size(); i++) {
                    Map<String, Object> result = resultList.get(i); 
                    ZYPEZDItemValueSetter.setValue(mcMsg.frtCondCd_M1.no(i), (String) result.get("FRT_COND_CD"));
                    ZYPEZDItemValueSetter.setValue(mcMsg.frtCondNm_M2.no(i), (String) result.get("FRT_COND_NM"));
                }
            }
        }
    }

    public static void createShpgSvcLvlPulldown(String glblCmpyCd, NMAL6700_MCMsg mcMsg) {
        mcMsg.shpgSvcLvlCd_M1.clear();
        mcMsg.shpgSvcLvlNm_M2.clear();

        if (ZYPCommonFunc.hasValue(mcMsg.frtCondCd_M3)) {
            S21SsmEZDResult res = NMAL6700Query.getInstance().getFrtCondSvcReln(glblCmpyCd, mcMsg.lineBizTpCd_M3.getValue(), mcMsg.frtCondCd_M3.getValue());

            if (res.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();

                for (int i=0; i < resultList.size(); i++) {
                    Map<String, Object> result = resultList.get(i); 
                    ZYPEZDItemValueSetter.setValue(mcMsg.shpgSvcLvlCd_M1.no(i), (String) result.get("SHPG_SVC_LVL_CD"));
                    ZYPEZDItemValueSetter.setValue(mcMsg.shpgSvcLvlNm_M2.no(i), (String) result.get("SHPG_SVC_LVL_NM"));
                }
            }
        }
    }
    // 2018/02/14 QC#20297(Sol#379) add end

    // Add Start 2019/01/07 QC#29749
    /**
     * getItrlNonAfflAcct
     * @param glblCmpyCd String
     * @return ArrayList<String>
     */
    public static ArrayList<String> getItrlNonAfflAcct(String glblCmpyCd) {
        ArrayList<String> result = new ArrayList<String>();

        String constString = ZYPCodeDataUtil.getVarCharConstValue(CONST_ITRL_NON_AFFL_ACCT_NUM, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constString)) {
            String[] array = constString.split(CONST_SPLITTER);
            for (String s : array) {
                if (ZYPCommonFunc.hasValue(s)) {
                    result.add(s);
                }
            }
        }

        return result;
    }
    // Add End 2019/01/07 QC#29749

    // Add Start 2019/08/07 QC#52385
    /**
     * @param cMsg NMAL6700CMsg
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getRelatedCustomerInfoForDefaultBillShip(NMAL6700CMsg cMsg, //
            String glblCmpyCd, String dsAcctNum) {

        S21SsmEZDResult res = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<String> defBillToList = new ArrayList<String>();
        List<String> defShipToList = new ArrayList<String>();

        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            String defaultBillToCd = cMsg.F.no(i).dsDefBillToCd_F1.getValue();
            String defaultShipToCd = cMsg.F.no(i).dsDefShipToCd_F1.getValue();

            if (ZYPCommonFunc.hasValue(defaultBillToCd) && !defBillToList.contains(defaultBillToCd)) {
                defBillToList.add(defaultBillToCd);
            }
            if (ZYPCommonFunc.hasValue(defaultShipToCd) && !defShipToList.contains(defaultShipToCd)) {
                defShipToList.add(defaultShipToCd);
            }
        }

        if (defBillToList.size() == 0 && defShipToList.size() == 0) {
            return resultList;
        }

        res = NMAL6700Query.getInstance().getRelatedBillShipFromChildReln(glblCmpyCd, dsAcctNum, defBillToList, defShipToList);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> searchResult = (List<Map<String, Object>>) res.getResultObject();
            resultList.addAll(searchResult);
        }

        res = NMAL6700Query.getInstance().getRelatedBillShipFromCurrent(glblCmpyCd, dsAcctNum, defBillToList, defShipToList);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> searchResult = (List<Map<String, Object>>) res.getResultObject();
            resultList.addAll(searchResult);
        }

        res = NMAL6700Query.getInstance().getRelatedBillShipFromParentReln(glblCmpyCd, dsAcctNum, defBillToList, defShipToList);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> searchResult = (List<Map<String, Object>>) res.getResultObject();
            resultList.addAll(searchResult);
        }

        return resultList;
    }
    // Add End 2019/08/07 QC#52385
    // 2023/01/13 QC#60860 Add Start
    public static NMAL6700SMsg vndCdHasValue(String glblCmpyCd, NMAL6700SMsg sharedMsg) {
        int cnt = sharedMsg.M.getValidCount();
        for (int i = 0; i < cnt; i++) {
            sharedMsg.M.no(i).vndCd_M3.clear();
            String vndCd = NMAL6700Query.getInstance().getVndCd(glblCmpyCd, sharedMsg.M.no(i).carrNm_M3.getValue());
            if (ZYPCommonFunc.hasValue(vndCd)) {
                sharedMsg.M.no(i).vndCd_M3.setValue(vndCd);
            }
        }
        return sharedMsg;
    }
    // 2023/01/13 QC#60860 Add End

    // add start 2023/07/18 QC#61421
    public static void createDsBizAreaPulldownForMarketingTab(String glblCmpyCd, NMAL6700_ECMsg ecMsg) {
        ecMsg.dsBizAreaCd_E1.clear();
        ecMsg.dsBizAreaNm_E2.clear();
        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsBizArea(glblCmpyCd, ZYPConstant.FLG_OFF_N);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> result = resultList.get(i);
                ZYPEZDItemValueSetter.setValue(ecMsg.dsBizAreaCd_E1.no(i), (String) result.get("DS_BIZ_AREA_CD"));
                ZYPEZDItemValueSetter.setValue(ecMsg.dsBizAreaNm_E2.no(i), (String) result.get("DS_BIZ_AREA_NM"));
            }
        }
    }

    public static void createDsBizAreaPulldownForShippingTab(String glblCmpyCd, NMAL6700_MCMsg mcMsg) {
        mcMsg.dsBizAreaCd_M1.clear();
        mcMsg.dsBizAreaNm_M2.clear();
        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsBizArea(glblCmpyCd, ZYPConstant.FLG_OFF_N);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> result = resultList.get(i);
                ZYPEZDItemValueSetter.setValue(mcMsg.dsBizAreaCd_M1.no(i), (String) result.get("DS_BIZ_AREA_CD"));
                ZYPEZDItemValueSetter.setValue(mcMsg.dsBizAreaNm_M2.no(i), (String) result.get("DS_BIZ_AREA_NM"));
            }
        }
    }
    // add end 2023/07/18 QC#61421
}
