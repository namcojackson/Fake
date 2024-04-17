/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0330.common;

import static business.blap.NSBL0330.constant.NSBL0330Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import business.blap.NSBL0330.NSBL0330CMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/23   Hitachi         K.Yamada        Update          QC#5857
 *</pre>
 */
public class NSBL0330CommonLogic {

    /**
     * checkMandatory
     * @param cMsg NSBL0330CMsg
     * @return boolean
     */
    public static boolean checkMandatory(NSBL0330CMsg cMsg) {

        if (!hasValue(cMsg.orgCd_I)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Organization Code" });
            return false;
        }

        // START 2016/03/23 K.Yamada [QC#5857, DEL]
//        if (!hasValue(cMsg.orgLayerNum_I)) {
//            cMsg.setMessageInfo(ZZM9000E, new String[] {"Organization Layer Number" });
//            return false;
//        }
        // END 2016/03/23 K.Yamada [QC#5857, DEL]

        if (!hasValue(cMsg.svcMgrTpCd_I)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Manager Type Code" });
            return false;
        }

        if (!hasValue(cMsg.svcRqstDownTpCd_I)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Request Down Type Code" });
            return false;
        }

        if (!hasValue(cMsg.svcRqstCritTpCd_I)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Request Criteria Type Code" });
            return false;
        }

        String svcRqstCritTpCd = cMsg.svcRqstCritTpCd_I.getValue();
        if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
            if (cMsg.I.getValidCount() == 0 || !hasValue(cMsg.I.no(0).dsSvcCallTpCd_I)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {"DS Service Call Type Code" });
                return false;
            }
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
            if (cMsg.I.getValidCount() == 0 || !hasValue(cMsg.I.no(0).fsrSvcTaskStsRelnPk_I)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {"FSR Service Task Status Relation PK" });
                return false;
            }
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
            if (cMsg.I.getValidCount() == 0 || !hasValue(cMsg.I.no(0).techCd_I)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {"Technician Code" });
                return false;
            }
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.CANNEL)) {
            if (cMsg.I.getValidCount() == 0 || !hasValue(cMsg.I.no(0).svcCallSrcTpCd_I)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Call Source Type Code" });
                return false;
            }
        } else {
            cMsg.setMessageInfo(ZZZM9026E, new String[] {"Service Request Criteria Type Code" });
            return false;
        }

        return true;
    }

    /**
     * createSuffix
     * @param idx int
     * @return String
     */
    public static String createSuffix(int idx) {
        return UNDER_BAR.concat(leftPad(String.valueOf(idx), SFX_LEN, STR_ZERO));
    }

    /**
     * setInitPagenationInfoW
     * @param cMsg NSBL0330CMsg
     * @param listSize int
     */
    public static void setInitPagenationInfoW(NSBL0330CMsg cMsg, int listSize) {
        setValue(cMsg.xxPageShowFromNum_W, BigDecimal.ONE);
        if (listSize < cMsg.xxPageShowTotNum_W.getValueInt()) {
            setValue(cMsg.xxPageShowToNum_W, new BigDecimal(listSize));
        } else {
            setValue(cMsg.xxPageShowToNum_W, cMsg.xxPageShowTotNum_W.getValue());
        }
        setValue(cMsg.xxPageShowOfNum_W, new BigDecimal(listSize));
    }

    /**
     * setInitPagenationInfoH
     * @param cMsg NSBL0330CMsg
     * @param listSize int
     */
    public static void setInitPagenationInfoH(NSBL0330CMsg cMsg, int listSize) {
        setValue(cMsg.xxPageShowFromNum_H, BigDecimal.ONE);
        if (listSize < DRILL_DOWN_LIST_SIZE) {
            setValue(cMsg.xxPageShowToNum_H, new BigDecimal(listSize));
        } else {
            setValue(cMsg.xxPageShowToNum_H, new BigDecimal(DRILL_DOWN_LIST_SIZE));
        }
        setValue(cMsg.xxPageShowOfNum_H, new BigDecimal(listSize));
    }

    /**
     * setPageNextPagenationInfoH
     * @param cMsg NSBL0330CMsg
     * @param listSize int
     */
    public static void setPageNextPagenationInfoH(NSBL0330CMsg cMsg, int listSize) {
        setValue(cMsg.xxPageShowFromNum_H, cMsg.xxPageShowToNum_H.getValue().add(BigDecimal.ONE));
        BigDecimal xxPageShowToNum = cMsg.xxPageShowToNum_H.getValue().add(new BigDecimal(DRILL_DOWN_LIST_SIZE));
        if (xxPageShowToNum.compareTo(cMsg.xxPageShowOfNum_H.getValue()) < 0) {
            setValue(cMsg.xxPageShowToNum_H, xxPageShowToNum);
        } else {
            setValue(cMsg.xxPageShowToNum_H, cMsg.xxPageShowOfNum_H);
        }
        setValue(cMsg.xxPageShowOfNum_H, new BigDecimal(listSize));
    }

    /**
     * setPagePrevPagenationInfoH
     * @param cMsg NSBL0330CMsg
     * @param listSize int
     */
    public static void setPagePrevPagenationInfoH(NSBL0330CMsg cMsg, int listSize) {
        setValue(cMsg.xxPageShowToNum_H, cMsg.xxPageShowFromNum_H.getValue().subtract(BigDecimal.ONE));
        BigDecimal xxPageShowFromNum = cMsg.xxPageShowFromNum_H.getValue().subtract(new BigDecimal(DRILL_DOWN_LIST_SIZE));
        if (xxPageShowFromNum.compareTo(BigDecimal.ZERO) > 0) {
            setValue(cMsg.xxPageShowFromNum_H, xxPageShowFromNum);
        } else {
            setValue(cMsg.xxPageShowFromNum_H, BigDecimal.ONE);
        }
        setValue(cMsg.xxPageShowOfNum_H, new BigDecimal(listSize));
    }

    /**
     * setNextMgrPagenationInfoH
     * @param cMsg NSBL0330CMsg
     */
    public static void setNextMgrPagenationInfoH(NSBL0330CMsg cMsg) {
        setValue(cMsg.xxPageShowFromNum_W, cMsg.xxPageShowToNum_W.getValue().add(BigDecimal.ONE));
        BigDecimal xxPageShowToNum = cMsg.xxPageShowToNum_W.getValue().add(cMsg.xxPageShowTotNum_W.getValue());
        if (xxPageShowToNum.compareTo(cMsg.xxPageShowOfNum_W.getValue()) < 0) {
            setValue(cMsg.xxPageShowToNum_W, xxPageShowToNum);
        } else {
            setValue(cMsg.xxPageShowToNum_W, cMsg.xxPageShowOfNum_W);
        }
    }

    /**
     * setPrevMgrPagenationInfoH
     * @param cMsg NSBL0330CMsg
     */
    public static void setPrevMgrPagenationInfoH(NSBL0330CMsg cMsg) {
        setValue(cMsg.xxPageShowToNum_W, cMsg.xxPageShowFromNum_W.getValue().subtract(BigDecimal.ONE));
        BigDecimal xxPageShowFromNum = cMsg.xxPageShowFromNum_W.getValue().subtract(cMsg.xxPageShowTotNum_W.getValue());
        if (xxPageShowFromNum.compareTo(BigDecimal.ZERO) > 0) {
            setValue(cMsg.xxPageShowFromNum_W, xxPageShowFromNum);
        } else {
            setValue(cMsg.xxPageShowFromNum_W, BigDecimal.ONE);
        }
    }
}
