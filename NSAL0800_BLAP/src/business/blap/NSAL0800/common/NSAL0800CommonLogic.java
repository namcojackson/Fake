/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0800.common;

import static business.blap.NSAL0800.constant.NSAL0800Constant.*;

import java.util.List;

import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0800.NSAL0800CMsg;
import business.blap.NSAL0800.NSAL0800Query;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_INV_TGTR_TPTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/06/16   Hitachi         K.Kojima        Update          QC#19230
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */

public class NSAL0800CommonLogic {

    /**
     * isExistsSellToCust
     * @param glblCmpyCd String
     * @param cMsg NSAL0800CMsg
     * @return true:sellToCust exist, false sellToCust don't exist.
     */
    public static boolean isExistsSellToCust(String glblCmpyCd, NSAL0800CMsg cMsg) {
        SELL_TO_CUSTTMsgArray list = getSellToCustList(glblCmpyCd, cMsg);
        if (list.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * getSellToCustList
     * @param glblCmpyCd String
     * @param cMsg NSAL0800CMsg
     * @return SELL_TO_CUSTTMsgArray
     */
    public static SELL_TO_CUSTTMsgArray getSellToCustList(String glblCmpyCd, NSAL0800CMsg cMsg) {
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("100");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", cMsg.leaseCmpyCd_A1.getValue());
        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // QC#18799 UPD START
    /**
     * getDsContrIntfcDefRule
     * @param glblCmpyCd String
     * @param contrIntfcSrcTpCd String
     * @return DS_CONTR_INTFC_DEF_RULETMsg
     */
    public static DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRule(String glblCmpyCd, NSAL0800CMsg cMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg inMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, cMsg.contrIntfcSrcTpCd_HS.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrClsCd, cMsg.dsContrClsCd_HS.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.svcLineBizCd, cMsg.svcLineBizCd_HS.getValue());
        return (DS_CONTR_INTFC_DEF_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }
    // QC#18799 UPD END

    // START 2017/05/26 Y.Osawa [QC#18560, ADD]
    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END   2017/05/26 Y.Osawa [QC#18560, ADD]

    // START 2017/06/16 K.Kojima [QC#19230,ADD]
    public static String switchFlg(String flg) {
        if (ZYPCommonFunc.hasValue(flg)) {
            if (ZYPConstant.FLG_ON_Y.equals(flg)) {
                return ZYPConstant.FLG_OFF_N;
            }
        }
        return ZYPConstant.FLG_ON_Y;
    }

    public static String switchFlg(EZDCStringItem flg) {
        if (ZYPCommonFunc.hasValue(flg)) {
            if (ZYPConstant.FLG_ON_Y.equals(flg.getValue())) {
                return ZYPConstant.FLG_OFF_N;
            }
        }
        return ZYPConstant.FLG_ON_Y;
    }
    // END 2017/06/16 K.Kojima [QC#19230,ADD]

    // QC#18799 ADD START
    public static void createSvcLineBizPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        S21SsmEZDResult result = NSAL0800Query.getInstance().getSvcLineBizCdList();
        if (result.isCodeNormal()) {
            List<String> svcLineBizCdList = (List<String>) result.getResultObject();
            int i = 0;
            for (String svcLineBizCd : svcLineBizCdList) {
                ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), svcLineBizCd);
                ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), svcLineBizCd);
                i++;
            }
        }
    }

    public static void createDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        valueItemArray.clear();
        dispItemArray.clear();
        for (int i = 0; i < 28; i++) {
            ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), i + "");
            ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), i + "");
        }
        ZYPEZDItemValueSetter.setValue(valueItemArray.no(28), LAST_DAY_OF_A_MONTH);
        ZYPEZDItemValueSetter.setValue(dispItemArray.no(28), LAST_DAY);
    }
    // QC#18799 ADD END

    // START 2022/03/22 [QC#59683, ADD]
    public static DS_INV_TGTR_TPTMsg getDsInvTgtrTp(String glblCmpyCd, String dsInvTgtrTPCd) {
        DS_INV_TGTR_TPTMsg tMsg = new DS_INV_TGTR_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTgtrTpCd, dsInvTgtrTPCd);
        tMsg = (DS_INV_TGTR_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg;
    }
    // END   2022/03/22 [QC#59683, ADD]
}
