/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1520.common;

import static business.blap.NWAL1520.constant.NWAL1520Constant.FUNC_BILLING_HLD_REL;
import static business.blap.NWAL1520.constant.NWAL1520Constant.FUNC_CREDIT_HLD_REL;
import static business.blap.NWAL1520.constant.NWAL1520Constant.FUNC_CUST_REGU_HLD_REL;
import static business.blap.NWAL1520.constant.NWAL1520Constant.FUNC_DELIVERY_HLD_REL;
import static business.blap.NWAL1520.constant.NWAL1520Constant.FUNC_SALES_HLD_REL;
import static business.blap.NWAL1520.constant.NWAL1520Constant.*;
import business.blap.NWAL1520.NWAL1520CMsg;
import business.blap.NWAL1520.NWAL1520SMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * NWAL1520CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Fujitsu         A.Suda          Create          N/A
 * 2016/04/27   Fujitsu         M.Yamada        Update          S21_NA#7159
 *</pre>
 */
public class NWAL1520CommonLogic {

    /**
     * initial message
     * @param bizMsg NWAL1520CMsg
     * @param shareMsg NWAL1520SMsg
     */
    public static void initMsg(NWAL1520CMsg bizMsg, NWAL1520SMsg shareMsg) {
        // clear message.

        bizMsg.cpoOrdNum_V.clear();
        bizMsg.configCatgCd_V.clear();
        bizMsg.condSqlTxt_V.clear();
        bizMsg.hldRsnDescTxt_V.clear();
        bizMsg.hldApplyRsnDescTxt_V.clear();
        bizMsg.hldApplyRsnCd_V.clear();
        bizMsg.hldUntilDt_V.clear();
        ZYPTableUtil.clear(bizMsg.V);

        bizMsg.cpoOrdNum_A.clear();
        bizMsg.hldDtlTxt_A.clear();
        bizMsg.configCatgCd_A.clear();
        bizMsg.condSqlTxt_A.clear();
        bizMsg.hldRsnDescTxt_A.clear();
        bizMsg.hldApplyRsnDescTxt_A.clear();
        bizMsg.hldApplyRsnCd_A.clear();
        bizMsg.hldUntilDt_A.clear();

        ZYPTableUtil.clear(bizMsg.L);
        bizMsg.V.setValidCount(0);
        bizMsg.L.setValidCount(0);

        bizMsg.hldRelRsnDescTxt_RH.clear();
        bizMsg.hldRelRsnCd_RH.clear();
        bizMsg.relMemoTxt_RH.clear();

        shareMsg.clear();
        shareMsg.V.setValidCount(0);
        shareMsg.L.setValidCount(0);

        clearErrorInfo(bizMsg, shareMsg);

    }

    /**
     * initial table message
     * @param bizMsg NWAL1520CMsg
     * @param shareMsg NWAL1520SMsg
     */
    public static void initTblMsg(NWAL1520CMsg bizMsg, NWAL1520SMsg shareMsg) {
        // clear message.

        ZYPTableUtil.clear(bizMsg.V);
        ZYPTableUtil.clear(bizMsg.L);
        bizMsg.V.setValidCount(0);
        bizMsg.L.setValidCount(0);

        shareMsg.clear();
        shareMsg.V.setValidCount(0);
        shareMsg.L.setValidCount(0);

    }

    /**
     * clearErrorInfo
     * @param bizMsg NWAL1520CMsg
     * @param shareMsg NWAL1520SMsg
     */
    public static void clearErrorInfo(NWAL1520CMsg bizMsg, NWAL1520SMsg shareMsg) {
        bizMsg.clearErrorInfo();
        bizMsg.L.clearErrorInfo();
        bizMsg.V.clearErrorInfo();
        shareMsg.clearErrorInfo();
        shareMsg.L.clearErrorInfo();
        shareMsg.V.clearErrorInfo();
    }

    public static boolean isReleaseFunc(NWAL1520CMsg bizMsg, String relFuncTpCd) {

        if (HLD_TP.SALES_HOLD.equals(relFuncTpCd)) {

            return hasFuncSalesHldRel(bizMsg);

        } else if (HLD_TP.CREDIT_HOLD.equals(relFuncTpCd)) {

            return hasFuncCreditHldRel(bizMsg);

        } else if (HLD_TP.CUSTOMS_AND_REGULATORY_HOLD.equals(relFuncTpCd)) {

            return hasFuncCustReguHldRel(bizMsg);

        } else if (HLD_TP.BILLING_HOLD.equals(relFuncTpCd)) {

            return hasFuncBillingHldRel(bizMsg);

        } else if (HLD_TP.DELIVERY_HOLD.equals(relFuncTpCd)) {

            return hasFuncDeliveryHldRel(bizMsg);

        } else if (HLD_TP.WORKFLOW_HOLD.equals(relFuncTpCd)) {
            return hasFuncWorkFlowHldRel(bizMsg);

        }

        return false;
    }

    /**
     * <pre>
     * Has sales hold release function.
     * </pre>
     * @param bizMsg Business Msg
     * @return true:has sales hold release function / false:has NOT sales hold release function
     */
    public static boolean hasFuncSalesHldRel(NWAL1520CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_SALES_HLD_REL.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Has credit hold release function.
     * </pre>
     * @param bizMsg Business Msg
     * @return true:has credit hold release function / false:has NOT credit hold release function
     */
    public static boolean hasFuncCreditHldRel(NWAL1520CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_CREDIT_HLD_REL.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Has customs regulatory hold release function.
     * </pre>
     * @param bizMsg Business Msg
     * @return true:has customs regulatory hold release function / false:has NOT customs regulatory hold release function
     */
    public static boolean hasFuncCustReguHldRel(NWAL1520CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_CUST_REGU_HLD_REL.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Has Billing hold release function.
     * </pre>
     * @param bizMsg Business Msg
     * @return true:has billing hold release function / false:has NOT billing hold release function
     */
    public static boolean hasFuncBillingHldRel(NWAL1520CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_BILLING_HLD_REL.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Has Delivery hold release function.
     * </pre>
     * @param bizMsg Business Msg
     * @return true:has delivery hold release function / false:has NOT delivery hold release function
     */
    public static boolean hasFuncDeliveryHldRel(NWAL1520CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_DELIVERY_HLD_REL.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasFuncWorkFlowHldRel(NWAL1520CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_WORKFLOW_HLD_REL.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * isViewOnlyFunc
     * @param bizMsg    NWAL1520CMsg
     * @param relFuncTpCd   hold type
     * @return
     */
    public static boolean isViewOnlyFunc(NWAL1520CMsg bizMsg, String relFuncTpCd) {
        if (!HLD_TP.DELIVERY_HOLD.equals(relFuncTpCd) //
                && !HLD_TP.BILLING_HOLD.equals(relFuncTpCd)) {
            return false;
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (FUNC_VIEW.equals(bizMsg.B.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }
}
