/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0160.common;

import java.math.BigDecimal;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0160.NFDL0160CMsg;
import business.blap.NFDL0160.NFDL0160Query;
import business.blap.NFDL0160.NFDL0160_ACMsg;
import business.db.AR_ADJ_TPTMsg;
import business.db.CLT_PTFOTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/06/03   Fujitsu         S.Yoshida       Update          CSA QC#8887
 * 2017/01/05   Fujitsu         T.Murai         Update          QC#16295
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 *</pre>
 */
public class NFDL0160CommonLogic {

    /**
     * Get screen Enter RelCltPtfoNm is Exist DB Data ->return PK
     * @param scrnRelCltPtfoPk BigDecimal
     * @param scrnRelCltPtfoNm String
     * @return BigDecimal
     */
    public static BigDecimal getRelCltPtfoNmIsExistDBData(BigDecimal scrnRelCltPtfoPk, String scrnRelCltPtfoNm) {

        BigDecimal errorData = BigDecimal.ONE.negate();

        S21SsmEZDResult res = NFDL0160Query.getInstance().chkCltPtfoNm(scrnRelCltPtfoPk, scrnRelCltPtfoNm);
        if (!res.isCodeNotFound() && !res.isCodeNormal()) {
            // if Error data -> return -1
            return errorData;
        }

        List<BigDecimal> resCltPtfoPkList = (List<BigDecimal>) res.getResultObject();
        if (resCltPtfoPkList.size() == 1) {
            return resCltPtfoPkList.get(0);
        }

        // if Error data -> return -1
        return errorData;
    }

    /**
     * isNotEquals
     * @param orig BigDecimal
     * @param backUp BigDecimal
     * @return boolean
     */
    public static boolean isNotEquals(BigDecimal orig, BigDecimal backUp) {
        if (orig == null) {
            if (backUp == null) {
                return false;
            } else {
                return true;
            }
        } else {
            if (backUp == null) {
                return true;
            }
        }
        if (orig.compareTo(backUp) != 0) {
            return true;
        }
        return false;
    }

    /**
     * set update data
     * @param bizMsg Business Component Interface Message
     * @param inMsg NFDL0160_ACMsg
     * @param tMsg CLT_PTFOTMsg
     */
    public static void setUpdRegistDataToCLT_PTFOTMsg(NFDL0160CMsg bizMsg, NFDL0160_ACMsg inMsg, CLT_PTFOTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoNm, inMsg.cltPtfoNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoDescTxt, inMsg.cltPtfoDescTxt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCorNm, inMsg.cltPtfoCorNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnCd, inMsg.cltPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnNm, inMsg.cltPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltStmtPhoNum, inMsg.cltStmtPhoNum_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltStmtFaxNum, inMsg.cltStmtFaxNum_A);
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpCd, inMsg.arAdjTpCd_A);
        // START 2018/02/27 J.Kim [QC#20944,DEL]
        // ZYPEZDItemValueSetter.setValue(tMsg.writeOffApvlLimitAmt, inMsg.writeOffApvlLimitAmt_A);
        // END 2018/02/27 J.Kim [QC#20944,DEL]
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstEquipPsnCd, inMsg.cltCrAnlstEquipPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstEquipPsnNm, inMsg.cltCrAnlstEquipPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSvcPsnCd, inMsg.cltCrAnlstSvcPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSvcPsnNm, inMsg.cltCrAnlstSvcPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSplyPsnCd, inMsg.cltCrAnlstSplyPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSplyPsnNm, inMsg.cltCrAnlstSplyPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.relCltPtfoPk, inMsg.relCltPtfoPk_A);
        if (ZYPConstant.FLG_ON_Y.equals(inMsg.cltPtfoStsFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoStsFlg, inMsg.cltPtfoStsFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoStsFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * set insert data
     * @param bizMsg Business Component Interface Message
     * @param inMsg NFDL0160_ACMsg
     * @param tMsg CLT_PTFOTMsg
     * @param glblCmpyCd String
     */
    public static void setInsRegistDataToCLT_PTFOTMsg(NFDL0160CMsg bizMsg, NFDL0160_ACMsg inMsg, CLT_PTFOTMsg tMsg, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        tMsg.cltPtfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLT_PTFO_SQ));
        // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCd, createNewCltPtfoCd(tMsg.cltPtfoPk.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCd, tMsg.cltPtfoPk.getValue().toString());
        // END 2017/08/21 T.Tsuchida [QC#19573,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoNm, inMsg.cltPtfoNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoDescTxt, inMsg.cltPtfoDescTxt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCorNm, inMsg.cltPtfoCorNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnCd, inMsg.cltPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnNm, inMsg.cltPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltStmtPhoNum, inMsg.cltStmtPhoNum_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltStmtFaxNum, inMsg.cltStmtFaxNum_A);
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpCd, inMsg.arAdjTpCd_A);
        // START 2018/02/27 J.Kim [QC#20944,MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.writeOffApvlLimitAmt, inMsg.writeOffApvlLimitAmt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.writeOffApvlLimitAmt, BigDecimal.ZERO);// TODO
        // END 2018/02/27 J.Kim [QC#20944,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstEquipPsnCd, inMsg.cltCrAnlstEquipPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstEquipPsnNm, inMsg.cltCrAnlstEquipPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSvcPsnCd, inMsg.cltCrAnlstSvcPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSvcPsnNm, inMsg.cltCrAnlstSvcPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSplyPsnCd, inMsg.cltCrAnlstSplyPsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSplyPsnNm, inMsg.cltCrAnlstSplyPsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.relCltPtfoPk, inMsg.relCltPtfoPk_A);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoStsFlg, inMsg.cltPtfoStsFlg_A);
    }

    // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
//    /**
//     *createNewCltPtfoCd
//     * @param cltPtfoPk BigDecimal
//     * @return String newCltPtfoCd
//     */
//    public static String createNewCltPtfoCd(BigDecimal cltPtfoPk) {
//
//        if (!ZYPCommonFunc.hasValue(cltPtfoPk)) {
//            // if Error data
//            return "00000001";
//        }
//
//        CLT_PTFOTMsg tMsg = new CLT_PTFOTMsg();
//        EZDItemAttribute atr = tMsg.getAttr("cltPtfoCd");
//        int cltPtfoCdDig = atr.getDigit();
//
//        String newCltPtfoCd = ZYPCommonFunc.leftPad(cltPtfoPk.toString(), 8, "0");
//
//        int length = newCltPtfoCd.length();
//        if (cltPtfoCdDig >= length) {
//            // notOver 8digits
//            return newCltPtfoCd;
//        }
//
//        // create CLT_PTFO_CD 8digits
//        return newCltPtfoCd.substring(length - 8, length);
//    }
    // END 2017/08/21 T.Tsuchida [QC#19573,MOD]

    // START 2017/01/05 [QC#16295, ADD]
    /**
     * get AR_ADJ_TP.AR_ADJ_TP_DESC_TXT by Code
     * @param adjTpCd String
     * @param glblCmpyCd String
     * @return arAdjTpDescTxt
     */
    public static String getArAdjTpDescTxtByCd(String adjTpCd, String glblCmpyCd) {

        AR_ADJ_TPTMsg arAdjTpTmsg = new AR_ADJ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(arAdjTpTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arAdjTpTmsg.arAdjTpCd, adjTpCd);

        AR_ADJ_TPTMsg result = (AR_ADJ_TPTMsg) EZDTBLAccessor.findByKey(arAdjTpTmsg);

        if (result == null) {
            return null;
        }

        return result.arAdjTpDescTxt.getValue();

    }
    // END   2017/01/05 [QC#16295, ADD]

}
