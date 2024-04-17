/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6740.common;

import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM0052E;
import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM8681E;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_ACCT;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_AFFL;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_BR;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_CC;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_CH;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_CMPY;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_EXTN;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_PROD;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_ELEMENT_LENGTH_PROJ;
import static business.blap.NMAL6740.constant.NMAL6740Constant.SEGMENT_TOKEN_LIST_SIZE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import business.blap.NMAL6740.NMAL6740CMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_CHTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.SHIP_TO_CUSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 * 2018/04/16   Fujitsu         M.Ohno          Update          QC#24635
 * 2018/04/24   Fujitsu         M.Ohno          Update          QC#24635-2
 *</pre>
 */
public class NMAL6740CommonLogic {

    /**
     * To get a record from SHIP_TO_CUST by SHIP_TO_CUST_PK
     * @param glblCmpyCd String
     * @param shipToCustPk BigDecimal
     * @return SHIP_TO_CUSTTMsg
     */
    public static SHIP_TO_CUSTTMsg findDsShipToCustForUpdate(String glblCmpyCd, BigDecimal shipToCustPk) {
        SHIP_TO_CUSTTMsg prmTMsg = new SHIP_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.shipToCustPk, shipToCustPk);
        return (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * To get a record from COA_AFFL by COA_AFFL_CD
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
     * To get a record from COA_CH by COA_CH_CD
     * @param glblCmpyCd String
     * @param coaChCd String
     * @return COA_CHTMsg
     */
    public static COA_CHTMsg findCoaCh(String glblCmpyCd, String coaChCd) {
        COA_CHTMsg prmTMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaChCd, coaChCd);
        return (COA_CHTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
    }

    // 2018/04/16 QC#24635 add start
    public static void createExpenseAccount(NMAL6740CMsg cMsg, String glblCmpyCd, boolean createFlg) {
        DEF_DPLY_COA_INFOTMsg defTMsg = new DEF_DPLY_COA_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(defTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defTMsg.appFuncId, "NMAL6740001");

        defTMsg = (DEF_DPLY_COA_INFOTMsg)S21FastTBLAccessor.findByKey(defTMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.coaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaCmpyCd, defTMsg.coaCmpyCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaBrCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaBrCd, defTMsg.coaBrCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaCcCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaCcCd, defTMsg.coaCcCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaAcctCd, defTMsg.coaAcctCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaProdCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd, defTMsg.coaProdCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaChCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaChCd, defTMsg.coaChCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaExtnCd, defTMsg.coaExtnCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaProjCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd, defTMsg.coaProjCd);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd, defTMsg.coaAfflCd);
        }

        if (createFlg) {
            StringBuilder acctNum = new StringBuilder();
            acctNum.append(cMsg.coaCmpyCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaBrCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaCcCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaAcctCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaProdCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaChCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaAfflCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaProjCd.getValue());
            acctNum.append(".");
            acctNum.append(cMsg.coaExtnCd.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt, acctNum.toString());
        }
    }
    // 2018/04/16 QC#24635 add end

    //2018/04/24 QC#24635-2 add start
    public static boolean inputCheckForExpenseAccount(NMAL6740CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            bizMsg.coaCmpyCd.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaCcCd.clear();
            bizMsg.coaAcctCd.clear();
            bizMsg.coaProdCd.clear();
            bizMsg.coaChCd.clear();
            bizMsg.coaAfflCd.clear();
            bizMsg.coaProjCd.clear();
            bizMsg.coaExtnCd.clear();
            return true;
        }

        String[] list = bizMsg.xxScrItem130Txt.getValue().split(Pattern.quote("."), -1);
        List<String> tokenList = new ArrayList<String>();
        for (String val : list) {
            tokenList.add(val);
        }

        if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM0052E, new String[] {"Segment"});
            return false;
        }

        if (!validateSegmentElement(tokenList.get(0), SEGMENT_ELEMENT_LENGTH_CMPY)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Company" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(1), SEGMENT_ELEMENT_LENGTH_BR)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Branch" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(2), SEGMENT_ELEMENT_LENGTH_CC)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Cost Center" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(3), SEGMENT_ELEMENT_LENGTH_ACCT)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Account" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(4), SEGMENT_ELEMENT_LENGTH_PROD)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Product" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(5), SEGMENT_ELEMENT_LENGTH_CH)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Channel" });
            return false;
        }
        if (!validateSegmentElement(tokenList.get(6), SEGMENT_ELEMENT_LENGTH_AFFL)) {

            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Affiliate" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(7), SEGMENT_ELEMENT_LENGTH_PROJ)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Project" });
            return false;
        }

        if (!validateSegmentElement(tokenList.get(8), SEGMENT_ELEMENT_LENGTH_EXTN)) {
            bizMsg.xxScrItem130Txt.setErrorInfo(1, NMAM8681E, new String[] {"Extension" });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd, tokenList.get(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, tokenList.get(1));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd, tokenList.get(2));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd, tokenList.get(3));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd, tokenList.get(4));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd, tokenList.get(5));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd, tokenList.get(6));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd, tokenList.get(7));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, tokenList.get(8));

        return true;
    }

    private static boolean validateSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }
    //2018/04/24 QC#24635-2 add end
}
