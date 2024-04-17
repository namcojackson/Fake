/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2010.common;

import static business.blap.NWAL2010.constant.NWAL2010Constant.BTN_RETURN;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2010.NWAL2010CMsg;
import business.blap.NWAL2010.NWAL2010SMsg;
import business.blap.NWAL2010.NWAL2010_ASMsg;
import business.db.CR_CARD_TPTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * NWAL2010CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2010CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * getDsCrCardTMsg
     * @param bizMsg NWAL2010CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static DS_CR_CARDTMsg getDsCrCardTMsg(NWAL2010CMsg bizMsg, String glblCmpyCd) {

        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.dsCrCardPk, bizMsg.dsCrCardPk.getValue());
        DS_CR_CARDTMsg dsCrCardRslt = (DS_CR_CARDTMsg) S21FastTBLAccessor.findByKey(dsCrCardTMsg);

        if (dsCrCardRslt == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(dsCrCardRslt.getReturnCode())) {
            return null;
        }

        return dsCrCardRslt;
    }

    /**
     * setCrCardCustRefNum
     * @param glblCmpyCd String
     * @param crCardTpCd String
     * @return crCardTpCd
     */
    public static String getCrCardTpDescTxt(String glblCmpyCd, String crCardTpCd) {

        CR_CARD_TPTMsg crCardTpTMsg = new CR_CARD_TPTMsg();
        crCardTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        crCardTpTMsg.crCardTpCd.setValue(crCardTpCd);
        CR_CARD_TPTMsg crCardTpResult = (CR_CARD_TPTMsg) S21FastTBLAccessor.findByKey(crCardTpTMsg);

        if (crCardTpResult == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(crCardTpResult.getReturnCode())) {
            return null;
        }

        return crCardTpResult.crCardTpDescTxt.getValue();
    }

    /**
     * getDsCrCard
     * @param glblCmpyCd String
     * @param dsCrCard BigDecimal
     * @return boolean
     */
    public static DS_CR_CARDTMsgArray getDsCrCard(NWAL2010CMsg bizMsg, String glblCmpyCd) {

        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        dsCrCardTMsg.setSQLID("001");
        dsCrCardTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCrCardTMsg.setConditionValue("crCardCustRefNum01", bizMsg.crCardCustRefNum.getValue());
        DS_CR_CARDTMsgArray dsCrCardResult = (DS_CR_CARDTMsgArray) EZDTBLAccessor.findByCondition(dsCrCardTMsg);

        if (dsCrCardResult.length() == 0) {
            bizMsg.setMessageInfo("ZZZM9001E");
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return null;
        }

        return dsCrCardResult;
    }

    /**
     * setCrCardList
     * @param glblCmpyCd String
     * @param dsCrCard BigDecimal
     */
//    public static void setCrCardList(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg, DS_CR_CARDTMsgArray dsCrCardResult) {
//
//        int queryResCnt = dsCrCardResult.getValidCount();
//        if (queryResCnt > glblMsg.A.length()) {
//            bizMsg.setMessageInfo("");
//            queryResCnt = glblMsg.A.length();
//        }
//
//        int cnt = 0;
//        for (; cnt < queryResCnt; cnt++) {
//            DS_CR_CARDTMsg dsCrCard = dsCrCardResult.no(cnt);
//            NWAL2010_ASMsg asMsg = glblMsg.A.no(cnt);
//            ZYPEZDItemValueSetter.setValue(asMsg.crCardCustRefNum_A, dsCrCard.crCardCustRefNum);
//            ZYPEZDItemValueSetter.setValue(asMsg.crCardExprYrMth_A, dsCrCard.crCardExprYrMth);
//            ZYPEZDItemValueSetter.setValue(asMsg.crCardHldNm_A, dsCrCard.crCardHldNm);
//            ZYPEZDItemValueSetter.setValue(asMsg.crCardLastDigitNum_A, dsCrCard.crCardLastDigitNum);
//            ZYPEZDItemValueSetter.setValue(asMsg.crCardTpDescTxt_A, dsCrCard.crCardTpCd);
//            ZYPEZDItemValueSetter.setValue(asMsg.crCardValidFlg_A, dsCrCard.crCardValidFlg);
//            ZYPEZDItemValueSetter.setValue(asMsg.ctryCd_A, dsCrCard.ctryCd);
//            ZYPEZDItemValueSetter.setValue(asMsg.firstLineAddr_A, dsCrCard.firstLineAddr);
//            ZYPEZDItemValueSetter.setValue(asMsg.scdLineAddr_A, dsCrCard.scdLineAddr);
//            ZYPEZDItemValueSetter.setValue(asMsg.postCd_A, dsCrCard.postCd);
//            ZYPEZDItemValueSetter.setValue(asMsg.stCd_A, dsCrCard.stCd);
//        }
//        glblMsg.A.setValidCount(cnt);
//    }

    /**
     * setNewCrCardLine
     * @param glblCmpyCd String
     * @param dsCrCard BigDecimal
     */
//    public static void setNewCrCardLine(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg, String glblCmpyCd) {
//
//        // Insert one row to the last
//        int idx = glblMsg.A.getValidCount();
//        bizMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
//        NWAL2010_ASMsg asMsg = glblMsg.A.no(idx);
//
//        DS_CR_CARDTMsg dsCrCardRslt = NWAL2010CommonLogic.getDsCrCardTMsg(bizMsg, glblCmpyCd);
//        if (dsCrCardRslt == null) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(asMsg.crCardCustRefNum_A, dsCrCardRslt.crCardCustRefNum);
//        ZYPEZDItemValueSetter.setValue(asMsg.crCardExprYrMth_A, dsCrCardRslt.crCardExprYrMth);
//        ZYPEZDItemValueSetter.setValue(asMsg.crCardHldNm_A, dsCrCardRslt.crCardHldNm);
//        ZYPEZDItemValueSetter.setValue(asMsg.crCardLastDigitNum_A, dsCrCardRslt.crCardLastDigitNum);
//        ZYPEZDItemValueSetter.setValue(asMsg.crCardValidFlg_A, dsCrCardRslt.crCardValidFlg);
//        ZYPEZDItemValueSetter.setValue(asMsg.ctryCd_A, dsCrCardRslt.ctryCd);
//        ZYPEZDItemValueSetter.setValue(asMsg.ctyAddr_A, dsCrCardRslt.ctyAddr);
//        ZYPEZDItemValueSetter.setValue(asMsg.firstLineAddr_A, dsCrCardRslt.firstLineAddr);
//        ZYPEZDItemValueSetter.setValue(asMsg.postCd_A, dsCrCardRslt.postCd);
//        ZYPEZDItemValueSetter.setValue(asMsg.scdLineAddr_A, dsCrCardRslt.scdLineAddr);
//        ZYPEZDItemValueSetter.setValue(asMsg.stCd_A, dsCrCardRslt.stCd);
//
//        String crCardTpDescTxt = NWAL2010CommonLogic.getCrCardTpDescTxt(glblCmpyCd, dsCrCardRslt.crCardTpCd.getValue());
//        ZYPEZDItemValueSetter.setValue(asMsg.crCardTpDescTxt_A, crCardTpDescTxt);
//    }

    /**
     * setCrCardList
     * @param glblCmpyCd String
     * @param dsCrCard BigDecimal
     */
    public static boolean callCrCardValidApi(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg, String glblCmpyCd, String custRefNum, BigDecimal crCardTrxPk) {
        NWZC203001 crCardValidApi = new NWZC203001();
        NWZC203001PMsg crCardValidApiPMsg = setCardValidApiPMsg(bizMsg, glblMsg, glblCmpyCd, custRefNum, crCardTrxPk);
        crCardValidApi.execute(crCardValidApiPMsg, ONBATCH_TYPE.ONLINE);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxErrFlg, ZYPConstant.FLG_OFF_N);
        if (crCardValidApiPMsg.xxMsgIdList.getValidCount() > 0) {
            String xxMsgId = crCardValidApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            bizMsg.setMessageInfo(xxMsgId);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }

//        ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum, crCardValidApiPMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_SL, crCardValidApiPMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_SL, crCardValidApiPMsg.dsCrCardPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_CR, crCardValidApiPMsg.dsCrCardPk);

        return true;
    }

    /**
     * set Credit Card API PMsg.
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     */
    public static NWZC203001PMsg setCardValidApiPMsg(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg, String glblCmpyCd, String custRefNum, BigDecimal crCardTrxPk) {

        NWZC203001PMsg crCardValidApiPMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardCustRefNum, custRefNum);
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.sellToCustCd, bizMsg.sellToCustCd);

        if (BTN_RETURN.equals(bizMsg.xxScrEventNm.getValue())) {
            return setCardValidApiPMsgForCmnReturn(crCardValidApiPMsg, bizMsg, glblMsg, crCardTrxPk);
        } else {
            return setCardValidApiPMsgForInsertNewCredit(crCardValidApiPMsg, bizMsg, glblMsg);
        }
    }

    /**
     * set Credit Card API PMsg for Insert New Credit Function.
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     */
    public static NWZC203001PMsg setCardValidApiPMsgForInsertNewCredit(NWZC203001PMsg crCardValidApiPMsg, NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_MSTR);
//        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcCustRefNum, bizMsg.crCardCustRefNum_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardCustRefNum, bizMsg.crCardCustRefNum_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAcctNum, bizMsg.xxPmtcAcctNum_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcExprDtTxt, bizMsg.crCardExprYrMth_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsZipTxt, bizMsg.postCd_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsAddr_01, bizMsg.firstLineAddr_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsAddr_02, bizMsg.scdLineAddr_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsCtyTxt, bizMsg.ctyAddr_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsStCd, bizMsg.stCd_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsNm, bizMsg.crCardHldNm_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxPmtcAvsCtryCdTxt, bizMsg.ctryCd_C.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardTpCd, bizMsg.crCardTpCd_C.getValue());
        return crCardValidApiPMsg;
    }

    /**
     * set Credit Card API PMsg for CMN_Return
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     * @return NWZC203001PMsg
     */
    public static NWZC203001PMsg setCardValidApiPMsgForCmnReturn(NWZC203001PMsg crCardValidApiPMsg, NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg, BigDecimal crCardTrxPk) {

        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardTrxTpCd, bizMsg.crCardTrxTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.firstTrxInfoTxt, bizMsg.firstTrxInfoTxt.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.scdTrxInfoTxt, bizMsg.scdTrxInfoTxt.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.thirdTrxInfoTxt, bizMsg.thirdTrxInfoTxt.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.frthTrxInfoTxt, bizMsg.frthTrxInfoTxt.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.fifthTrxInfoTxt, bizMsg.fifthTrxInfoTxt.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.firstTrxInfoNum, bizMsg.firstTrxInfoNum.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.scdTrxInfoNum, bizMsg.scdTrxInfoNum.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.thirdTrxInfoNum, bizMsg.thirdTrxInfoNum.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.frthTrxInfoNum, bizMsg.frthTrxInfoNum.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.fifthTrxInfoNum, bizMsg.fifthTrxInfoNum.getValue());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardTrxPk, crCardTrxPk);
        return crCardValidApiPMsg;
    }

    /**
     * Update the global Message.
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     */
    public static void updateGlblMsg(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * Method name: copySmsgToCmsg
     * @param cMsg NWAL2010CMsg
     * @param sMsg NWAL2010SMsg
     * @param startIndex int
     * @return int
     */
    public static int copySmsgToCmsg(NWAL2010CMsg cMsg, NWAL2010SMsg sMsg, int startIndex) {

        ZYPTableUtil.clear(cMsg.A);

        int idx = startIndex;
        for (; idx < startIndex + cMsg.A.length(); idx++) {
            if (idx < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(idx), null, cMsg.A.no(idx - startIndex), null);
            } else {
                break;
            }
        }

        return idx;
    }

    /**
     * Method name: addCrCardInfo
     * @param cMsg NWAL2010CMsg
     * @param sMsg NWAL2010SMsg
     */
    public static void addCrCardInfo(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg, String glblCmpyCd) {

        int newValidCount = glblMsg.A.getValidCount();
        glblMsg.A.setValidCount(newValidCount + 1);

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).crCardCustRefNum_A, bizMsg.crCardCustRefNum_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).crCardHldNm_A, bizMsg.crCardHldNm_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).crCardLastDigitNum_A, bizMsg.xxPmtcAcctNum_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).crCardValidFlg_A,ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).crCardExprYrMth_A, bizMsg.crCardExprYrMth_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).firstLineAddr_A, bizMsg.firstLineAddr_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).scdLineAddr_A, bizMsg.scdLineAddr_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).ctyAddr_A, bizMsg.ctyAddr_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).stCd_A, bizMsg.stCd_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).postCd_A, bizMsg.postCd_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).ctryCd_A, bizMsg.ctryCd_C);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).dsCrCardPk_A, bizMsg.dsCrCardPk_CR);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(newValidCount).crCardTpDescTxt_A, getCrCardTpDescTxt(glblCmpyCd, bizMsg.crCardTpCd_C.getValue()));
    }

    /**
     * Insert Row Move Last Page
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     */
    public static void insertRowMoveLastPage(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        // Add Record
        int specificIdx = glblMsg.A.getValidCount() - 1;
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        int page = specificIdx / bizMsg.A.length();
        int pageFrom = page * bizMsg.A.length();
        int i = pageFrom;
        for (; i < pageFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pageFrom);

        bizMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pageFrom + bizMsg.A.getValidCount());
        
    }
}
