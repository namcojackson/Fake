/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6720.common;

import static business.blap.NMAL6720.constant.NMAL6720Constant.DEF_DPLY_COA_INFO_APP_FUNC_ID;
import static business.blap.NMAL6720.constant.NMAL6720Constant.FIVE_DIGIT_POST_WITHOUT_CNTY_ERR;
import static business.blap.NMAL6720.constant.NMAL6720Constant.FIVE_DIGIT_POST_WITH_CNTY_ERR;
import static business.blap.NMAL6720.constant.NMAL6720Constant.IN_ACTIVE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MAX_DT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.ORIG_POST_WITHOUT_CNTY_ERR;
import static business.blap.NMAL6720.constant.NMAL6720Constant.ORIG_POST_WITH_CNTY_ERR;
import static business.blap.NMAL6720.constant.NMAL6720Constant.SUCCESS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_MSG_NOTE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_SHIPPING;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.blap.NMAL6720.NMAL6720Query;
import business.blap.NMAL6720.NMAL6720SMsg;
import business.blap.NMAL6720.NMAL6720_ICMsg;
import business.blap.NMAL6720.NMAL6720_ISMsg;
import business.db.ACCT_LOCTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_RELNTMsg;
import business.db.PRIN_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.STTMsg;
import business.db.TECH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824
 * 2016/05/18   Fujitsu         N.Sugiura       Update          Unit Test#193
 * 2016/07/05   Fujitsu         C.Tanaka        Update          QC#10677
 * 2016/07/19   Fujitsu         R.Nakamura      Update          QC#11729
 * 2016/10/20   Fujitsu         C.Yokoi         Update          QC#14720
 * 2017/11/27   Fujitsu         A.Kosai         Update          QC#20828
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/03/26   Fujitsu         K.Ishizuka      Update          QC#23935
 * 2018/07/24   Fujitsu         W.Honda         Update          QC#27169
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/02/19   Fujitsu         R.Nakamura      Update          QC#30293
 * 2019/04/16   Fujitsu         Hd.Sugawara     Update          QC#31114
 * 2019/05/08   Fujitsu         T.Noguchi       Update          QC#50101
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 *</pre>
 */
public class NMAL6720CommonLogic {

    /**
     * findPrinCust
     * @param glblCmpyCd String
     * @param prinCustPk BigDecimal
     * @return PRIN_CUSTTMsg
     */
    public static PRIN_CUSTTMsg findPrinCust(String glblCmpyCd, BigDecimal prinCustPk) {
        PRIN_CUSTTMsg prmTMsg = new PRIN_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prinCustPk, prinCustPk);
        return (PRIN_CUSTTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * findTechMstr
     * @param glblCmpyCd String
     * @param techCd String
     * @return TECH_MSTRTMsg
     */
    public static TECH_MSTRTMsg findTechMstr(String glblCmpyCd, String techCd) {
        TECH_MSTRTMsg prmTMsg = new TECH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.techTocCd, techCd);
        return (TECH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * findDsAcctProsForUpdate
     * @param glblCmpyCd String
     * @param dsAcctProsPk BigDecimal
     * @return DS_ACCT_PROSTMsg
     */
    public static DS_ACCT_PROSTMsg findDsAcctProsForUpdate(String glblCmpyCd, BigDecimal dsAcctProsPk) {
        DS_ACCT_PROSTMsg prmTMsg = new DS_ACCT_PROSTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctProsPk, dsAcctProsPk);
        return (DS_ACCT_PROSTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
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
        return (PRIN_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    /**
     * findDsAcctRelnForUpdate
     * @param glblCmpyCd String
     * @param dsAcctRelnPk BigDecimal
     * @return DS_ACCT_RELNTMsg
     */
    public static DS_ACCT_RELNTMsg findDsAcctRelnForUpdate(String glblCmpyCd, BigDecimal dsAcctRelnPk) {
        DS_ACCT_RELNTMsg prmTMsg = new DS_ACCT_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctRelnPk, dsAcctRelnPk);
        return (DS_ACCT_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
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
        return (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
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
        return (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    /**
     * clear HeaderInfo
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     */
    public static void clearHeaderInfo(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        sMsg.firstLineAddr_H1.clear();
        sMsg.scdLineAddr_H1.clear();
        sMsg.thirdLineAddr_H1.clear();
        sMsg.frthLineAddr_H1.clear();

        sMsg.ctyAddr_H1.clear();
        sMsg.stCd_P1.clear();
        sMsg.postCd_H1.clear();
        sMsg.cntyNm_H1.clear();
        sMsg.provNm_H1.clear();

        sMsg.dunsNum_H1.clear();
        sMsg.dsUltDunsNum_H1.clear();

        ZYPTableUtil.clear(sMsg.A);

        clearCMsg(cMsg);
    }

    /**
     * clearCMsg
     * @param cMsg NMAL6720CMsg
     */
    public static void clearCMsg(NMAL6720CMsg cMsg) {

        cMsg.dsAcctNum_P1.clear();

        cMsg.ctryCd_H1.clear();

        cMsg.firstLineAddr_H1.clear();
        cMsg.scdLineAddr_H1.clear();
        cMsg.thirdLineAddr_H1.clear();
        cMsg.frthLineAddr_H1.clear();

        cMsg.ctyAddr_H1.clear();
        cMsg.stCd_H1.clear();
        cMsg.postCd_H1.clear();
        cMsg.cntyNm_H1.clear();
        cMsg.provNm_H1.clear();

        cMsg.dunsNum_H1.clear();
        cMsg.dsUltDunsNum_H1.clear();

        ZYPTableUtil.clear(cMsg.A);
    }

    /**
     * The message set to PMsg is set to the message map.<br>
     * @param cMsg NMAL6720CMsg
     * @param pMsg PMsg
     * @return true:Error message none false:There is an error
     * message.
     */
    public static boolean setMessage(NMAL6720CMsg cMsg, EZDPMsg pMsg) {

        List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);

        if (!errList.isEmpty()) {
            for (String xxMsgId : errList) {
                cMsg.setMessageInfo(xxMsgId);
            }
            return false;
        }
        return true;
    }

    /**
     * Get Account
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @param dsAcctTpCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getAcct(String dsAcctNum, String glblCmpyCd, String dsAcctTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsAcctNum", dsAcctNum);
        queryParam.put("dsAcctTpCd", dsAcctTpCd);

        S21SsmEZDResult ssmResult = NMAL6720Query.getInstance().getAcct(queryParam);
        return ssmResult;
    }

    /**
     * Check Active Account
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean isAcctActive(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsAcctNum", dsAcctNum);

        S21SsmEZDResult ssmResult = NMAL6720Query.getInstance().checkActiveAcct(queryParam);
        if (ssmResult.isCodeNormal()) {
            if ((Integer) ssmResult.getResultObject() > 0) {
                return true;
            }
        }
        return false;

    }

    /**
     * copyFromSMsgOntoCmsg
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     * @param tab String
     * @param startIdx int
     */
    public static void copyFromSMsgOntoCmsg(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, String tab, int startIdx) {

        EZDCMsgArray cMsgArray = cMsg.C;
        EZDSMsgArray sMsgArray = sMsg.C;
        EZDCBigDecimalItem fromNum = cMsg.xxPageShowFromNum_C;
        EZDCBigDecimalItem toNum = cMsg.xxPageShowToNum_C;
        EZDCBigDecimalItem ofNum = cMsg.xxPageShowOfNum_C;
        if (TAB_MSG_NOTE.equals(tab)) {
            cMsgArray = cMsg.E;
            sMsgArray = sMsg.E;
            fromNum = cMsg.xxPageShowFromNum_E;
            toNum = cMsg.xxPageShowToNum_E;
            ofNum = cMsg.xxPageShowOfNum_E;
        } else if (TAB_SHIPPING.equals(tab)) {
            cMsgArray = cMsg.I;
            sMsgArray = sMsg.I;
            fromNum = cMsg.xxPageShowFromNum_I;
            toNum = cMsg.xxPageShowToNum_I;
            ofNum = cMsg.xxPageShowOfNum_I;
        }

        ZYPTableUtil.clear(cMsgArray);
        fromNum.clear();
        toNum.clear();
        ofNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int pagenationFrom = (startIdx / maxDisplayRows) * maxDisplayRows;

        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {

                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsgArray.setValidCount(i - pagenationFrom);

        fromNum.setValue(pagenationFrom + 1);
        toNum.setValue(pagenationFrom + cMsgArray.getValidCount());
        ofNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * copyCurrentPageToSMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param fromNum EZDCBigDecimalItem
     */
    public static void copyCurrentPageToSMsg(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, EZDCBigDecimalItem fromNum) {
        int fromIdx = fromNum.getValueInt() - 1;
        for (int i = 0; i < cMsgArray.getValidCount(); i++) {
            EZDMsg.copy(cMsgArray.get(i), null, sMsgArray.get(fromIdx + i), null);
        }
    }

    /**
     * findByKeySt
     * @param glblCmpyCd String
     * @param stCd String
     * @return STTMsg
     */
    public static STTMsg findByKeySt(String glblCmpyCd, String stCd) {
        STTMsg tmsg = new STTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.stCd.setValue(stCd);
        STTMsg result = (STTMsg) S21FastTBLAccessor.findByKey(tmsg);
        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    /**
     * Copy Account List Input to SMsg
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     */
    public static void copyAcctListInput(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (cMsg.A.getValidCount() == sMsg.A.getValidCount()) {
            EZDMsg.copy(cMsg.A, null, sMsg.A, null);
        } else {
            int cnt = 0;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!IN_ACTIVE.equals(sMsg.A.no(i).dsAcctStsNm_A1.getValue())) {
                    EZDMsg.copy(cMsg.A.no(cnt), null, sMsg.A.no(i), null);
                    cnt++;
                }
            }
        }
    }

    /**
     * setRgtnCdBillTo
     * @param cMsg NMAL6720CMsg
     * @param rgtnStsCd EZDTStringItem
     * @return boolean
     */
    public static boolean setRgtnCdBillTo(NMAL6720CMsg cMsg, EZDTStringItem rgtnStsCd) {

        String slsDate = ZYPDateUtil.getSalesDate();
        String fromDate = cMsg.effFromDt_BI.getValue();
        String thruDate = MAX_DT;
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
            thruDate = cMsg.effThruDt_BI.getValue();
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI)) {
            if (ZYPCommonFunc.hasValue(cMsg.billToCustPk_BI)) {
                if (ZYPDateUtil.compare(slsDate, fromDate) < 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                } else if (ZYPDateUtil.compare(slsDate, thruDate) > 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.TERMINATED);
                } else if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 //
                        && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                }
            } else {
                if (ZYPDateUtil.compare(slsDate, fromDate) < 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                } else if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 //
                        && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.billToCustPk_BI)) {
                if (ZYPDateUtil.compare(slsDate, fromDate) < 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                } else if (ZYPDateUtil.compare(slsDate, thruDate) > 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.TERMINATED);
                } else if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 //
                        && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctRelnBillToFlg_BI, ZYPConstant.CHKBOX_ON_Y);
                }
            }
        }

        return true;
    }

    /**
     * setRgtnCdShipTo
     * @param cMsg NMAL6720CMsg
     * @param rgtnStsCd EZDTStringItem
     * @return boolean
     */
    public static boolean setRgtnCdShipTo(NMAL6720CMsg cMsg, EZDTStringItem rgtnStsCd) {

        String slsDate = ZYPDateUtil.getSalesDate();
        String fromDate = cMsg.effFromDt_SH.getValue();
        String thruDate = MAX_DT;
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
            thruDate = cMsg.effThruDt_SH.getValue();
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH)) {
            if (ZYPCommonFunc.hasValue(cMsg.shipToCustPk_SH)) {
                if (ZYPDateUtil.compare(slsDate, fromDate) < 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                } else if (ZYPDateUtil.compare(slsDate, thruDate) > 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.TERMINATED);
                } else if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 //
                        && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                }
            } else {
                if (ZYPDateUtil.compare(slsDate, fromDate) < 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                } else if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 //
                        && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.shipToCustPk_SH)) {
                if (ZYPDateUtil.compare(slsDate, fromDate) < 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                } else if (ZYPDateUtil.compare(slsDate, thruDate) > 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.TERMINATED);
                } else if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 //
                        && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                    ZYPEZDItemValueSetter.setValue(rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctRelnShipToFlg_SH, ZYPConstant.CHKBOX_ON_Y);
                }
            }
        }

        return true;
    }

    /**
     * checkCustomerAccount
     * @param dsAcctTpCd String
     * @return boolean
     */
    public static boolean checkCustomerAccount(String dsAcctTpCd) {
        if (DS_ACCT_TP.CUSTOMER.equals(dsAcctTpCd)) {
            return true;
        }
        return false;
    }

    // 2017/11/27 QC#20828 Add Start
    public static boolean isMergeProsLocToCust(NMAL6720CMsg cMsg) {

        if (checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {
            return false;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (checkCustomerAccount(cMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                return true;
            }
        }

        return false;
    }

    public static String getMergeToAcctCd (NMAL6720CMsg cMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (checkCustomerAccount(cMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                return cMsg.A.no(i).dsAcctNum_A1.getValue();
            }
        }

        return null;
    }
    // 2017/11/27 QC#20828 Add End

    // 2018/02/19 QC#20297(Sol#379) add start
    public static void createFrtCondPulldown(String glblCmpyCd, NMAL6720_ICMsg icMsg) {
        icMsg.frtCondCd_I1.clear();
        icMsg.frtCondNm_I1.clear();
        icMsg.shpgSvcLvlCd_I1.clear();
        icMsg.shpgSvcLvlNm_I1.clear();

        if (ZYPCommonFunc.hasValue(icMsg.lineBizTpCd_P3)) {
            S21SsmEZDResult res = NMAL6720Query.getInstance().getFrtCondSvcReln(glblCmpyCd, icMsg.lineBizTpCd_P3.getValue(), null);

            if (res.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();

                for (int i=0; i < resultList.size(); i++) {
                    Map<String, Object> result = resultList.get(i); 
                    ZYPEZDItemValueSetter.setValue(icMsg.frtCondCd_I1.no(i), (String) result.get("FRT_COND_CD"));
                    ZYPEZDItemValueSetter.setValue(icMsg.frtCondNm_I1.no(i), (String) result.get("FRT_COND_NM"));
                }
            }
        }
    }

    public static void createShpgSvcLvlPulldown(String glblCmpyCd, NMAL6720_ICMsg icMsg) {
        icMsg.shpgSvcLvlCd_I1.clear();
        icMsg.shpgSvcLvlNm_I1.clear();

        if (ZYPCommonFunc.hasValue(icMsg.frtCondCd_P1)) {
            S21SsmEZDResult res = NMAL6720Query.getInstance().getFrtCondSvcReln(glblCmpyCd, icMsg.lineBizTpCd_P3.getValue(), icMsg.frtCondCd_P1.getValue());

            if (res.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();

                for (int i=0; i < resultList.size(); i++) {
                    Map<String, Object> result = resultList.get(i); 
                    ZYPEZDItemValueSetter.setValue(icMsg.shpgSvcLvlCd_I1.no(i), (String) result.get("SHPG_SVC_LVL_CD"));
                    ZYPEZDItemValueSetter.setValue(icMsg.shpgSvcLvlNm_I1.no(i), (String) result.get("SHPG_SVC_LVL_NM"));
                }
            }
        }
    }
    // 2018/02/19 QC#20297(Sol#379) add END
    // 2018/12/13 QC#29315 Add Start
    public static void createCarrierPulldown(String glblCmpyCd, NMAL6720_ICMsg icMsg, List<Map<String, Object>> resPullDownList){
        int cnt = resPullDownList.size();
        if (cnt > icMsg.vndCd_I1.length()) {
            cnt = icMsg.vndCd_I1.length();
        }
        for (int i = 0; i < cnt; i++) {
            Map<String, Object> carr = resPullDownList.get(i);
            ZYPEZDItemValueSetter.setValue(icMsg.vndCd_I1.no(i), (String) carr.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(icMsg.locNm_I2.no(i), (String) carr.get("LOC_NM"));
        }
    }

    public static void createCarrierPulldown(String glblCmpyCd, NMAL6720_ISMsg isMsg, List<Map<String, Object>> resPullDownList){
        int cnt = resPullDownList.size();
        if (cnt > isMsg.vndCd_I1.length()) {
            cnt = isMsg.vndCd_I1.length();
        }
        for (int i = 0; i < cnt; i++) {
            Map<String, Object> carr = resPullDownList.get(i);
            ZYPEZDItemValueSetter.setValue(isMsg.vndCd_I1.no(i), (String) carr.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(isMsg.locNm_I2.no(i), (String) carr.get("LOC_NM"));
        }
    }
    // 2018/12/13 QC#29315 Add End
    // 2018/03/26 S21_NA#23935 Add Start
    public static void setCoaDefInfo(String glblCmpyCd, NMAL6720CMsg cMsg, BILL_TO_CUSTTMsg billToCustTMsg){
        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.appFuncId, DEF_DPLY_COA_INFO_APP_FUNC_ID);

        tMsg = (DEF_DPLY_COA_INFOTMsg) EZDTBLAccessor.findByKey(tMsg);
        
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaChCd, tMsg.coaChCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaCmpyCd, tMsg.coaCmpyCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAfflCd, tMsg.coaAfflCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaBrCd, tMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaCcCd, tMsg.coaCcCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAcctCd, tMsg.coaAcctCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaProdCd, tMsg.coaProdCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaProjCd, tMsg.coaProjCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaExtnCd, tMsg.coaExtnCd);

        }
    }
    // 2018/03/26 S21_NA#23935 Add End

    // 2018/07/24 S21_NA#27169 Add Start
    public static List<Map<String, Object>> getAddress(Map<String, Object> paramWithCnty, Map<String, Object> paramWithoutCnty, EZDCStringItem rsltCd){
        ZYPEZDItemValueSetter.setValue(rsltCd, SUCCESS);

        S21SsmEZDResult getAddrByOrignalPostCdWithCntyRes = NMAL6720Query.getInstance().getAddrByPost(paramWithCnty);

        if (getAddrByOrignalPostCdWithCntyRes.isCodeNormal()) {
            List<Map<String, Object>> AddrByOrignalPostCdWithCntyList = (List<Map<String, Object>>) getAddrByOrignalPostCdWithCntyRes.getResultObject();
            if (AddrByOrignalPostCdWithCntyList.size() > 1) {
                ZYPEZDItemValueSetter.setValue(rsltCd, ORIG_POST_WITH_CNTY_ERR);
            }
            return AddrByOrignalPostCdWithCntyList;
        }

        S21SsmEZDResult getAddrByOrignalPostCdWithoutCntyRes = NMAL6720Query.getInstance().getAddrByPost(paramWithoutCnty);
        if (getAddrByOrignalPostCdWithoutCntyRes.isCodeNormal()) {
            List<Map<String, Object>> AddrByOrignalPostCdWithoutCntyList = (List<Map<String, Object>>) getAddrByOrignalPostCdWithoutCntyRes.getResultObject();
            if (AddrByOrignalPostCdWithoutCntyList.size() > 1) {
                ZYPEZDItemValueSetter.setValue(rsltCd, ORIG_POST_WITHOUT_CNTY_ERR);
            }
            return AddrByOrignalPostCdWithoutCntyList;
        }

        String postCd = (String) paramWithCnty.get("postCd");
        if (postCd != null && postCd.length() > 5) {
            paramWithCnty.put("postCd", postCd.substring(0, 5));

            S21SsmEZDResult getAddrByFiveDigitPostCdWithCntyRes = NMAL6720Query.getInstance().getAddrByPost(paramWithCnty);
            if (getAddrByFiveDigitPostCdWithCntyRes.isCodeNormal()) {
                List<Map<String, Object>> AddrByFiveDigitPostCdWithCntyList = (List<Map<String, Object>>) getAddrByFiveDigitPostCdWithCntyRes.getResultObject();
                if (AddrByFiveDigitPostCdWithCntyList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(rsltCd, FIVE_DIGIT_POST_WITH_CNTY_ERR);
                }
                return AddrByFiveDigitPostCdWithCntyList;
            }

            paramWithoutCnty.put("postCd", postCd.substring(0, 5));

            S21SsmEZDResult getAddrByFiveDigitPostCdWithoutCntyRes = NMAL6720Query.getInstance().getAddrByPost(paramWithoutCnty);
            if (getAddrByFiveDigitPostCdWithoutCntyRes.isCodeNormal()) {
                List<Map<String, Object>> AddrByFiveDigitPostCdWithoutCntyList = (List<Map<String, Object>>) getAddrByFiveDigitPostCdWithoutCntyRes.getResultObject();
                if (AddrByFiveDigitPostCdWithoutCntyList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(rsltCd, FIVE_DIGIT_POST_WITHOUT_CNTY_ERR);
                }
                return AddrByFiveDigitPostCdWithoutCntyList;
            }
        }

        return null;
    }
    // 2018/07/24 S21_NA#27169 Add End

    // Add Start 2019/02/19 QC#30293
    public static void setTemplateForFinancials(String glblCmpyCd, BILL_TO_CUSTTMsg billToCustTMsg) {

        S21SsmEZDResult res = NMAL6720Query.getInstance().getTemplateForFinancials(glblCmpyCd);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> billToFinancialTempList = (List<Map<String, Object>>) res.getResultObject();
            Map<String, Object> billToFinancialTemp = billToFinancialTempList.get(0);

            ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtFlg, (String) billToFinancialTemp.get("AR_STMT_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.sendCrBalStmtFlg, (String) billToFinancialTemp.get("SEND_CR_BAL_STMT_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtIssDay, (String) billToFinancialTemp.get("AR_STMT_ISS_DAY"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.cltCustTpCd, (String) billToFinancialTemp.get("CLT_CUST_TP_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.cltPtfoPk, (BigDecimal) billToFinancialTemp.get("CLT_PTFO_PK"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCltAcctStsCd, (String) billToFinancialTemp.get("DS_CLT_ACCT_STS_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, (String) billToFinancialTemp.get("LATE_FEE_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeAmt, (BigDecimal) billToFinancialTemp.get("LATE_FEE_AMT"));
            // Add Start 2019/04/16 QC#31114
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCustTaxCalcCd, (String) billToFinancialTemp.get("DS_CUST_TAX_CALC_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsExemExprDt, (String) billToFinancialTemp.get("DS_EXEM_EXPR_DT"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxExemFlg, (String) billToFinancialTemp.get("DS_TAX_EXEM_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxGrpExemCd, (String) billToFinancialTemp.get("DS_TAX_GRP_EXEM_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxPrntTpCd, (String) billToFinancialTemp.get("DS_TAX_PRNT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunFlg, (String) billToFinancialTemp.get("DUN_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.remId, (String) billToFinancialTemp.get("REM_ID"));
            // Add End 2019/04/16 QC#31114
        }

        return;
    }
    // Add End 2019/02/19 QC#30293

    // 2019/05/08 QC#50101 Add Start
    /**
     * setOffPrinCustFlg
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     * @param glblCmpyCd String
     * @return void
     */
    public static void setOffPrinCustFlg(NMAL6720CMsg cMsg, String glblCmpyCd) {

        if (ZYPConstant.FLG_OFF_N.equals(cMsg.prinCustFlg_PR.getValue())) {
            return;
        }

        // Add Start 2019/05/23 QC#50101
        String salesDate = ZYPDateUtil.getSalesDate();
        boolean isExistInProcess = false;
        boolean isExistActive = false;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            String fromDate = cMsg.A.no(i).effFromDt_A1.getValue();
            String thruDate = MAX_DT;

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1)) {
                thruDate = cMsg.A.no(i).effThruDt_A1.getValue();
            }

            if(ZYPDateUtil.compare(salesDate, fromDate) < 0){
                isExistInProcess = true;
            }
            if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 >= ZYPDateUtil.compare(salesDate, thruDate)) {
                isExistActive = true;
            }
        }
        if(!isExistInProcess && !isExistActive) {
            // If Inactive only, change primary off.
            ZYPEZDItemValueSetter.setValue(cMsg.prinCustFlg_PR, ZYPConstant.FLG_OFF_N);
        }
        // Add End 2019/05/23 QC#50101

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ACCT_LOCTMsg acctLocTMsg = new ACCT_LOCTMsg();
            ZYPEZDItemValueSetter.setValue(acctLocTMsg.glblCmpyCd, glblCmpyCd);
            // Del Start 2019/05/23 QC#50101
            //String salesDate = ZYPDateUtil.getSalesDate();
            // Del End 2019/05/23 QC#50101
            String fromDate = cMsg.A.no(i).effFromDt_A1.getValue();
            String thruDate = MAX_DT;
            // Add Start 2019/05/23 QC#50101
            String dsAcctNum = cMsg.dsAcctNum_H1.getValue();
            String relAcctNum = cMsg.A.no(i).dsAcctNum_A1.getValue();
            // Add End 2019/05/23 QC#50101

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1)) {
                thruDate = cMsg.A.no(i).effThruDt_A1.getValue();
            }

            if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg) &&
                    !NMAL6720CommonLogic.checkCustomerAccount(cMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).acctLocPk_A1)) {
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.acctLocPk, cMsg.A.no(i).acctLocPk_A1.getValue());

                acctLocTMsg = (ACCT_LOCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(acctLocTMsg);
                if (acctLocTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(acctLocTMsg.getReturnCode())) {
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(i).ezUpTime_A1.getValue(), cMsg.A.no(i).ezUpTimeZone_A1.getValue(), acctLocTMsg.ezUpTime.getValue(), acctLocTMsg.ezUpTimeZone.getValue())) {
                    return;
                }

                if (!(RGTN_STS.TERMINATED.equals(acctLocTMsg.rgtnStsCd.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue()))) {

                    if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                        // nothing
                    } else {

                        if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(acctLocTMsg.rgtnStsCd.getValue())) {
                            // nothing
                            // Mod Start 2019/05/23 QC#50101
                        //} else if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 > ZYPDateUtil.compare(salesDate, thruDate)) {
                        } else if (!dsAcctNum.equals(relAcctNum) && //
                                (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 >= ZYPDateUtil.compare(salesDate, thruDate))) {
                            // Mod End 2019/05/23 QC#50101
                            ZYPEZDItemValueSetter.setValue(cMsg.prinCustFlg_PR, ZYPConstant.FLG_OFF_N); // Active Account Change.
                        }
                    }
                }
            } else {
                // Mod Start 2019/05/23 QC#50101
                //if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 > ZYPDateUtil.compare(salesDate, thruDate)) {
                if (!dsAcctNum.equals(relAcctNum) && //
                        (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 >= ZYPDateUtil.compare(salesDate, thruDate))) {
                    // Mod End 2019/05/23 QC#50101
                    ZYPEZDItemValueSetter.setValue(cMsg.prinCustFlg_PR, ZYPConstant.FLG_OFF_N); // Active Account Change.
                }
            }
        }
    }
    // 2019/05/08 QC#50101 Add End

    // Add Start 2019/05/23 QC#50101
    /**
     * getActiveRelatedAcctNum
     * @param cMsg NMAL6720CMsg
     * @param salesDate String
     * @return String
     */
    public static String getActiveRelatedAcctNum(NMAL6720CMsg cMsg, String salesDate) {
        String relAcctNum = null;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            String fromDate = cMsg.A.no(i).effFromDt_A1.getValue();
            String thruDate = MAX_DT;

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1)) {
                thruDate = cMsg.A.no(i).effThruDt_A1.getValue();
            }

            if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 >= ZYPDateUtil.compare(salesDate, thruDate)) {
                relAcctNum = cMsg.A.no(i).dsAcctNum_A1.getValue();
                break;
            }
        }

        return relAcctNum;
    }
    // Add End 2019/05/23 QC#50101

    // Add Start 2019/08/07 QC#52385
    /**
     * @param cMsg NMAL6720CMsg
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getRelatedCustomerInfoForDefaultBillShip(NMAL6720CMsg cMsg, //
            String glblCmpyCd, String dsAcctNum) {

        S21SsmEZDResult res = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<String> defBillToList = new ArrayList<String>();
        List<String> defShipToList = new ArrayList<String>();

        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            String defaultBillToCd = cMsg.D.no(i).dsDefBillToCd_D1.getValue();
            String defaultShipToCd = cMsg.D.no(i).dsDefShipToCd_D1.getValue();

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

        res = NMAL6720Query.getInstance().getRelatedBillShipFromChildReln(glblCmpyCd, dsAcctNum, defBillToList, defShipToList);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> searchResult = (List<Map<String, Object>>) res.getResultObject();
            resultList.addAll(searchResult);
        }

        res = NMAL6720Query.getInstance().getRelatedBillShipFromCurrent(glblCmpyCd, dsAcctNum, defBillToList, defShipToList);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> searchResult = (List<Map<String, Object>>) res.getResultObject();
            resultList.addAll(searchResult);
        }

        res = NMAL6720Query.getInstance().getRelatedBillShipFromParentReln(glblCmpyCd, dsAcctNum, defBillToList, defShipToList);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> searchResult = (List<Map<String, Object>>) res.getResultObject();
            resultList.addAll(searchResult);
        }

        return resultList;
    }
    // Add End 2019/08/07 QC#52385
    // 2023/01/13 QC#60860 Add Start
    public static NMAL6720SMsg vndCdHasValue(String glblCmpyCd, NMAL6720SMsg sharedMsg) {
        int cnt = sharedMsg.I.getValidCount();
        for (int i = 0; i < cnt; i++) {
            String vndCd = NMAL6720Query.getInstance().getVndCd(glblCmpyCd, sharedMsg.I.no(i).carrNm_I3.getValue());
            sharedMsg.I.no(i).vndCd_I3.clear();
            if (ZYPCommonFunc.hasValue(vndCd)) {
                sharedMsg.I.no(i).vndCd_I3.setValue(vndCd);
            }
        }
        return sharedMsg;
    }
    // 2023/01/13 QC#60860 Add End

    // add start 2023/07/18 QC#61421
    public static void createDsBizAreaPulldownForShippingTab(String glblCmpyCd, NMAL6720CMsg cMsg) {
        cMsg.dsBizAreaCd_I1.clear();
        cMsg.dsBizAreaNm_I1.clear();
        S21SsmEZDResult res = NMAL6720Query.getInstance().getDsBizArea(glblCmpyCd, ZYPConstant.FLG_OFF_N);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> result = resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.dsBizAreaCd_I1.no(i), (String) result.get("DS_BIZ_AREA_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsBizAreaNm_I1.no(i), (String) result.get("DS_BIZ_AREA_NM"));
            }
        }
    }
    // add end 2023/07/18 QC#61421
}
