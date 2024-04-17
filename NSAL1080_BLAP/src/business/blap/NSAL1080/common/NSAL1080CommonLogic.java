/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1080.common;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL1080.NSAL1080CMsg;
import business.db.CONSL_BILL_LINETMsg;
import business.db.CONSL_BILL_LINETMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBILTMsgArray;
import business.db.SVC_INVTMsg;
import business.db.SVC_INVTMsgArray;

/**
 *<pre>
 * Credit Rebill Main Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1080CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1080CMsg
     */
    public static void clearMsg(NSAL1080CMsg cMsg) {

        cMsg.custIncdtId.clear();
        cMsg.svcCrRebilStsDescTxt.clear();
        cMsg.svcCrRebilDescTxt.clear();
        cMsg.svcCrRebilPk.clear();
        cMsg.svcInvNum.clear();
        cMsg.dsContrNum.clear();
        cMsg.conslBillPk.clear();
        cMsg.serNum.clear();
        cMsg.bllgPerFromDt.clear();
        cMsg.bllgPerToDt.clear();
    }

    /**
     * get SVC_CR_REBI
     * @param cMsg NSAL1080CMsg
     * @return SVC_CR_REBILTMsgArray
     */
    public static SVC_CR_REBILTMsgArray getSvcCrRebil(NSAL1080CMsg cMsg) {

        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("custIncdtId01", cMsg.custIncdtId.getValue());
        return (SVC_CR_REBILTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * exists SVC_INV
     * @param cMsg NSAL1080CMsg
     * @return boolean
     */
    public static boolean existsSvcInv(NSAL1080CMsg cMsg) {

        SVC_INVTMsg inMsg = new SVC_INVTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcInvNum01", cMsg.svcInvNum.getValue());
        SVC_INVTMsgArray tMsgList = (SVC_INVTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgList.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    /**
     * exists DS_CONTR
     * @param cMsg NSAL1080CMsg
     * @return boolean
     */
    public static boolean existsDsContr(NSAL1080CMsg cMsg) {

        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        inMsg.setSQLID("201");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrNum01", cMsg.dsContrNum.getValue());
        inMsg.setConditionValue("dsContrStsCd01", DS_CONTR_STS.ORDER);
        DS_CONTRTMsgArray tMsgList = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgList.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    /**
     * exists CONSL_BILL_LINE
     * @param cMsg NSAL1080CMsg
     * @return boolean
     */
    public static boolean existsConslBillLine(NSAL1080CMsg cMsg) {

        CONSL_BILL_LINETMsg inMsg = new CONSL_BILL_LINETMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("conslBillPk01", cMsg.conslBillPk.getValue());
        CONSL_BILL_LINETMsgArray tMsgList = (CONSL_BILL_LINETMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgList.getValidCount() == 0) {
            return false;
        }
        return true;
    }
}
