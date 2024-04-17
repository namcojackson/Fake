/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200.common;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_HEADER;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_ADDL_HEADER;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import parts.common.EZDBItem;
import parts.common.EZDMessageInfo;

import business.servlet.NWAL2200.NWAL2200BMsg;
import business.servlet.NWAL2200.NWAL2200_ABMsg;
import business.servlet.NWAL2200.NWAL2200_BBMsg;
import business.servlet.NWAL2200.NWAL2200_CBMsg;
import business.servlet.NWAL2200.NWAL2200_DBMsg;
import business.servlet.NWAL2200.NWAL2200_EBMsg;

/**
 *<pre>
 * NWAL2200CommonLogicForError
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 11/28/2016   Fujitsu         T.Ishii         Update          S21_NA#15796
 *</pre>
 */
public class NWAL2200CommonLogicForError {

    /**
     * setErrorItem
     * @param scrnMsg NWAL2200BMsg
     * @param xxDplyTab xxDplyTab
     */
    public static void setErrorItem(NWAL2200BMsg scrnMsg, String xxDplyTab) {

        String focusTab = null;
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {

            boolean hasBeenErrorMapped = false;

            NWAL2200_EBMsg error = scrnMsg.E.no(i);
            BigDecimal dsImptOrdDtlPk = error.dsImptOrdDtlPk_EL.getValue();
            BigDecimal dsImptOrdRtrnDtlPk = error.dsImptOrdRtrnDtlPk_EL.getValue();
            BigDecimal dsImptOrdConfigPk = error.dsImptOrdConfigPk_EL.getValue();
            String messageCode = error.dsImptOrdErrMsgId_EL.getValue();
            String message = error.dsImptOrdErrTxt_EL.getValue();
            if (dsImptOrdDtlPk != null && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG))) {

                // line level
                if (focusTab == null) {

                    hasBeenErrorMapped = setErrorItemLine(scrnMsg, messageCode, message);

                    // move first error tab
                    focusTab = TAB_LINE_CONFIG;
                }
            } else if (dsImptOrdRtrnDtlPk != null && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_RMA))) {

                // RMA level
                if (focusTab == null) {

                    hasBeenErrorMapped = setErrorItemRMA(scrnMsg, messageCode, message);

                    // move first error tab
                    focusTab = TAB_RMA;
                }
            } else if (dsImptOrdConfigPk != null && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG) || S21StringUtil.isEquals(xxDplyTab, TAB_RMA))) {

                // Configuration level
                if (focusTab == null) {

                    // move first error tab
                    if (S21StringUtil.isEquals(error.configCatgCd_EL.getValue(), CONFIG_CATG.OUTBOUND) && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG))) {

                        hasBeenErrorMapped = setErrorItemConfigOutbound(scrnMsg, messageCode, message);
                        focusTab = TAB_LINE_CONFIG;
                    } else if ((xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_RMA))) {

                        hasBeenErrorMapped = setErrorItemConfigInbound(scrnMsg, messageCode, message);
                        focusTab = TAB_RMA;
                    }
                }
            } else {

                if ((xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_HEADER))) {

                    // Header level
                    hasBeenErrorMapped = setErrorItemHeader(scrnMsg, messageCode, message);
                    if (focusTab == null) {

                        // move first error tab
                        focusTab = TAB_HEADER;
                    }
                }
                if ((xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_ADDL_HEADER))) {

                    // Additional Header level
                    hasBeenErrorMapped = setErrorItemAddlHeader(scrnMsg, messageCode, message);
                    if (focusTab == null) {

                        // move first error tab
                        focusTab = TAB_ADDL_HEADER;
                    }
                }
            }
            if (!hasBeenErrorMapped && focusTab != null) {

                setDefaultErrorItemField(error, scrnMsg, focusTab);
            }
        }
        if (focusTab != null) {

            // move first error tab
            scrnMsg.xxDplyTab.setValue(focusTab);
        }

        if (scrnMsg.E.getValidCount() > 0) {

            EZDMessageInfo messageInfo = scrnMsg.getMessageInfo();
            if (messageInfo == null || messageInfo.getMessageType() != EZDMessageInfo.MSGTYPE_ERROR) {
                // scrnMsg.setMessageInfo("NWAM2200W");
            }
        }
    }

    private static boolean setErrorItemHeader(NWAL2200BMsg scrnMsg, String messageCode, String message) {

        boolean hasBeenErrorMapped = false;
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        NWAL2200CommonLogicForScreenFields.setItemPropertyForHeader(itemProperties, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setItemPropertyForHeaderTab(itemProperties, scrnMsg);
        hasBeenErrorMapped = NWAL2200CommonLogicForScreenFields.setErrorItem(itemProperties, scrnMsg, messageCode, message);

        return hasBeenErrorMapped;
    }

    private static boolean setErrorItemAddlHeader(NWAL2200BMsg scrnMsg, String messageCode, String message) {

        boolean hasBeenErrorMapped = false;
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        //NWAL2200CommonLogicForScreenFields.setItemPropertyForHeader(itemProperties, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setItemPropertyForHeaderTab(itemProperties, scrnMsg);
        hasBeenErrorMapped = NWAL2200CommonLogicForScreenFields.setErrorItem(itemProperties, scrnMsg, messageCode, message);

        return hasBeenErrorMapped;
    }

    private static boolean setErrorItemConfigOutbound(NWAL2200BMsg scrnMsg, String messageCode, String message) {

        boolean hasBeenErrorMapped = false;
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        //NWAL2200CommonLogicForScreenFields.setItemPropertyForHeader(itemProperties, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setItemPropertyForConfigTab(itemProperties, scrnMsg);
        hasBeenErrorMapped = NWAL2200CommonLogicForScreenFields.setErrorItem(itemProperties, scrnMsg, messageCode, message);

        return hasBeenErrorMapped;
    }

    private static boolean setErrorItemConfigInbound(NWAL2200BMsg scrnMsg, String messageCode, String message) {

        boolean hasBeenErrorMapped = false;
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        NWAL2200CommonLogicForScreenFields.setItemPropertyForHeader(itemProperties, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setItemPropertyForRMATab(itemProperties, scrnMsg);
        hasBeenErrorMapped = NWAL2200CommonLogicForScreenFields.setErrorItem(itemProperties, scrnMsg, messageCode, message);

        return hasBeenErrorMapped;
    }

    private static boolean setErrorItemLine(NWAL2200BMsg scrnMsg, String messageCode, String message) {

        boolean hasBeenErrorMapped = false;
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        //NWAL2200CommonLogicForScreenFields.setItemPropertyForHeader(itemProperties, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setItemPropertyForConfigTab(itemProperties, scrnMsg);
        hasBeenErrorMapped = NWAL2200CommonLogicForScreenFields.setErrorItem(itemProperties, scrnMsg, messageCode, message);

        return hasBeenErrorMapped;
    }

    private static boolean setErrorItemRMA(NWAL2200BMsg scrnMsg, String messageCode, String message) {

        boolean hasBeenErrorMapped = false;
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        //NWAL2200CommonLogicForScreenFields.setItemPropertyForHeader(itemProperties, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setItemPropertyForRMATab(itemProperties, scrnMsg);
        hasBeenErrorMapped = NWAL2200CommonLogicForScreenFields.setErrorItem(itemProperties, scrnMsg, messageCode, message);

        return hasBeenErrorMapped;
    }

    private static void setDefaultErrorItemField(NWAL2200_EBMsg error, NWAL2200BMsg scrnMsg, String focusTab) {

        String xxDplyTab = focusTab;
        if (S21StringUtil.isEmpty(xxDplyTab)) {
            xxDplyTab = scrnMsg.xxDplyTab.getValue();
        }
        BigDecimal dsImptOrdDtlPk = error.dsImptOrdDtlPk_EL.getValue();
        BigDecimal dsImptOrdRtrnDtlPk = error.dsImptOrdRtrnDtlPk_EL.getValue();
        BigDecimal dsImptOrdConfigPk = error.dsImptOrdConfigPk_EL.getValue();
        String messageCode = error.dsImptOrdErrMsgId_EL.getValue();
        String[] messageParameters = NWAL2200CommonLogicForScreenFields.getMessageParameters(messageCode, error.dsImptOrdErrTxt_EL.getValue());

        int errorCode = 1;
        if (messageCode.endsWith("E")) {

            errorCode = 1;
        } else if (messageCode.endsWith("W")) {

            errorCode = 2;
        } else {

            errorCode = 1;
        }
        if (dsImptOrdDtlPk != null && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG))) {

            // line level
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                NWAL2200_BBMsg line = scrnMsg.B.no(i);
                if (NWAL2200CommonLogic.compareBigDecimal(line.dsImptOrdDtlPk_LL.getValue(), dsImptOrdDtlPk) == 0) {

                    line.xxChkBox_LL.setErrorInfo(errorCode, messageCode, messageParameters);
                    break;
                }
            }

        } else if (dsImptOrdRtrnDtlPk != null && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_RMA))) {

            // RMA level
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

                NWAL2200_DBMsg rma = scrnMsg.D.no(i);
                if (NWAL2200CommonLogic.compareBigDecimal(rma.dsImptOrdRtrnDtlPk_RL.getValue(), dsImptOrdRtrnDtlPk) == 0) {

                    rma.xxChkBox_RL.setErrorInfo(errorCode, messageCode, messageParameters);
                    break;
                }
            }
        } else if (dsImptOrdConfigPk != null && (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG) || S21StringUtil.isEquals(xxDplyTab, TAB_RMA))) {

            // Configuration level
            if (S21StringUtil.isEquals(error.configCatgCd_EL.getValue(), CONFIG_CATG.OUTBOUND)) {

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                    NWAL2200_ABMsg config = scrnMsg.A.no(i);
                    if (NWAL2200CommonLogic.compareBigDecimal(config.dsImptOrdConfigPk_LC.getValue(), dsImptOrdConfigPk) == 0) {

                        config.xxChkBox_LC.setErrorInfo(errorCode, messageCode, messageParameters);
                        break;
                    }
                }
            } else {

                for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

                    NWAL2200_CBMsg config = scrnMsg.C.no(i);
                    if (NWAL2200CommonLogic.compareBigDecimal(config.dsImptOrdConfigPk_RC.getValue(), dsImptOrdConfigPk) == 0) {

                        config.xxChkBox_RC.setErrorInfo(errorCode, messageCode, messageParameters);
                        break;
                    }
                }
            }
        } else if ((xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_HEADER))) {

            // header
            scrnMsg.setMessageInfo(messageCode, messageParameters);
        } else if ((xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_ADDL_HEADER))) {

            // additional tab
            scrnMsg.setMessageInfo(messageCode, messageParameters);
        }
    }
}
