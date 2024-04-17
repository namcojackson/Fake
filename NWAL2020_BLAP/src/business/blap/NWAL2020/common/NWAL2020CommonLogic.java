/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2020.common;

import java.math.BigDecimal;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2020.NWAL2020CMsg;
import business.blap.NWAL2020.NWAL2020SMsg;
import business.db.CR_CARD_TPTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * NWAL2020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020CommonLogic {

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
    public static DS_CR_CARDTMsgArray getDsCrCard(NWAL2020CMsg bizMsg, String glblCmpyCd) {

        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        dsCrCardTMsg.setSQLID("001");
        dsCrCardTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCrCardTMsg.setConditionValue("crCardCustRefNum01", bizMsg.crCardCustRefNum.getValue());
        DS_CR_CARDTMsgArray dsCrCardResult = (DS_CR_CARDTMsgArray) EZDTBLAccessor.findByCondition(dsCrCardTMsg);

        return dsCrCardResult;
    }

    /**
     * setCrCardList
     * @param glblCmpyCd String
     * @param dsCrCard BigDecimal
     */
    public static boolean callCrCardValidApi(NWAL2020CMsg bizMsg, NWAL2020SMsg glblMsg, String glblCmpyCd, String custRefNum, BigDecimal crCardTrxPk) {
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

        ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_SL, crCardValidApiPMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_SL, crCardValidApiPMsg.dsCrCardPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_CR, crCardValidApiPMsg.dsCrCardPk);

        return true;
    }

    /**
     * set Credit Card API PMsg.
     * @param bizMsg NWAL2020CMsg
     * @param glblMsg NWAL2020SMsg
     */
    public static NWZC203001PMsg setCardValidApiPMsg(NWAL2020CMsg bizMsg, NWAL2020SMsg glblMsg, String glblCmpyCd, String custRefNum, BigDecimal crCardTrxPk) {

        NWZC203001PMsg crCardValidApiPMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.crCardCustRefNum, custRefNum);
        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.sellToCustCd, bizMsg.sellToCustCd);

        return setCardValidApiPMsgForInsertNewCredit(crCardValidApiPMsg, bizMsg, glblMsg);
    }

    /**
     * set Credit Card API PMsg for Insert New Credit Function.
     * @param bizMsg NWAL2020CMsg
     * @param glblMsg NWAL2020SMsg
     */
    public static NWZC203001PMsg setCardValidApiPMsgForInsertNewCredit(NWZC203001PMsg crCardValidApiPMsg, NWAL2020CMsg bizMsg, NWAL2020SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(crCardValidApiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_MSTR);
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
     * Method name: addCrCardInfo
     * @param cMsg NWAL2020CMsg
     * @param sMsg NWAL2020SMsg
     */
    public static void addCrCardInfo(NWAL2020CMsg bizMsg, NWAL2020SMsg glblMsg, String glblCmpyCd) {

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
}
