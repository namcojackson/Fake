/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/10/2010   Fujitsu         K.Tajima        Create          N/A
 * 08/05/2010   Fujitsu         R.Nakamura      Update          QC#9078
 *</pre>
 */
package business.servlet.NWXL0010.common;

import parts.common.EZDBItem;
import parts.common.EZDBItemArray;
import parts.common.EZDBMsgArray;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NWXL0010.NWXL0010CMsg;
import business.servlet.NWXL0010.NWXL0010BMsg;
import business.servlet.NWXL0010.NWXL0010Scrn00_NewReport;
import business.servlet.NWXL0010.NWXL0010Scrn01_CMN_Delete;
import business.servlet.NWXL0010.constant.NWXL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class NWXL0010CommonLogic implements NWXL0010Constant {

    public static void addCheckAllItems(NWXL0010BMsg bMsg, String[][] baseContents) {

        for (int i = 0; i < baseContents.length; i++) {

            String itemKey = baseContents[i][0];

            Object bMsgField;
            try {
                bMsgField = bMsg.getClass().getField(itemKey).get(bMsg);
            } catch (Exception e) {
                continue;
            }

            if (bMsgField instanceof EZDBItem) {
                bMsg.addCheckItem((EZDBItem) bMsgField);

            } else if (bMsgField instanceof EZDBItemArray) {
                // nothing to do.

            } else if (bMsgField instanceof EZDBMsgArray) {
                // nothing to do.

            } else {
                // nothing to do.
            }
        }
    }
    
    public static boolean hasError(NWXL0010CMsg bizMsg) {
        return bizMsg != null && "E".equals(bizMsg.getMessageKind());
    }

    public static boolean hasModificationFunction() {
        S21UserProfileService userProfSv = S21UserProfileServiceFactory.getInstance().getService();
        return userProfSv.isFunctionGranted(userProfSv.getContextUserInfo().getUserId(), FuncId.MODIFICATION.getId());
    }

    public static NWXL0010CMsg newCMsgForSearch(NWXL0010BMsg bMsg) {

        final NWXL0010CMsg cMsg = new NWXL0010CMsg();
        cMsg.setBusinessID(MY_BIZ_ID);
        cMsg.setFunctionCode("20");
        EZDMsg.copy(bMsg, null, cMsg, null);

        return cMsg;
    }

    public static NWXL0010CMsg newCMsgForUpdate(NWXL0010BMsg bMsg) {

        final NWXL0010CMsg cMsg = new NWXL0010CMsg();
        cMsg.setBusinessID(MY_BIZ_ID);
        cMsg.setFunctionCode("40");
        EZDMsg.copy(bMsg, null, cMsg, null);

        return cMsg;
    }

    public static void setGuiAttr(ScrnId scrnId, NWXL0010BMsg bMsg, EZDCommonHandler handler) {

        bMsg.clearAllGUIAttribute(scrnId.toString());
        bMsg.setInputProtected(false);

        switch (scrnId) {

            case NWXL0010Scrn00:

                // set Common buttons.
                handler.setButtonProperties("btn1",  "CMN_Save",     "Save",     0, null);
                handler.setButtonProperties("btn2",  "CMN_Submit",   "Submit",   0, null);
                handler.setButtonProperties("btn3",  "CMN_Apply",    "Apply",    0, null);
                handler.setButtonProperties("btn4",  "CMN_Approve",  "Approve",  0, null);
                handler.setButtonProperties("btn5",  "CMN_Reject",   "Reject",   0, null);
                if (bMsg.A.getValidCount() > 0) {
                    handler.setButtonProperties("btn6",  "CMN_Download", "Download", 1, null);
                } else {
                    handler.setButtonProperties("btn6",  "CMN_Download", "Download", 0, null);
                }
                handler.setButtonProperties("btn7",  "CMN_Delete",   "Delete",   0, null);
                handler.setButtonProperties("btn8",  "CMN_Clear",    "Clear",    1, null);
                handler.setButtonProperties("btn9",  "CMN_Reset",    "Reset",    0, null);
                handler.setButtonProperties("btn10", "CMN_Return",   "Return",   1, null);

                if (hasModificationFunction()) {
                    handler.setButtonEnabled("NewReport", true);
                    handler.setButtonEnabled("DownloadHistory", true);
                } else {
                    handler.setButtonEnabled("NewReport", true);
                    handler.setButtonEnabled("DownloadHistory", false);
                }

                // set Table BG-color.
                new S21TableColorController(scrnId.toString(), bMsg).setAlternateRowsBG("A", bMsg.A);
                break;

            case NWXL0010Scrn01:

                // _CMN_Delete
                if (NWXL0010Scrn01_CMN_Delete.class.getSimpleName().equals(bMsg.xxScrEventNm.getValue())) {
 
                    bMsg.setInputProtected(true);
                    
                    // set Common buttons.
                    handler.setButtonProperties("btn1",  "CMN_Save",     "Save",     0, null);
                    handler.setButtonProperties("btn2",  "CMN_Submit",   "Submit",   0, null);
                    handler.setButtonProperties("btn3",  "CMN_Apply",    "Apply",    0, null);
                    handler.setButtonProperties("btn4",  "CMN_Approve",  "Approve",  0, null);
                    handler.setButtonProperties("btn5",  "CMN_Reject",   "Reject",   0, null);
                    handler.setButtonProperties("btn6",  "CMN_Download", "Download", 0, null);
                    handler.setButtonProperties("btn7",  "CMN_Delete",   "Delete",   0, null);
                    handler.setButtonProperties("btn8",  "CMN_Clear",    "Clear",    0, null);
                    handler.setButtonProperties("btn9",  "CMN_Reset",    "Reset",    0, null);
                    handler.setButtonProperties("btn10", "CMN_Return",   "Return",   1, null);
                    
                } else {
                    
                    // is New Entry Mode?
                    final boolean isNewEntryMode = NWXL0010Scrn00_NewReport.class.getSimpleName().equals(bMsg.xxScrEventNm.getValue());
                    
                    bMsg.rptSqlId_01.setInputProtected(!isNewEntryMode);

                    // set Common buttons.
                    handler.setButtonProperties("btn1", "CMN_Save",     "Save",     0, null);
                    handler.setButtonProperties("btn2", "CMN_Submit",   "Submit",   1, null);
                    handler.setButtonProperties("btn3", "CMN_Apply",    "Apply",    0, null);
                    handler.setButtonProperties("btn4", "CMN_Approve",  "Approve",  0, null);
                    handler.setButtonProperties("btn5", "CMN_Reject",   "Reject",   0, null);
                    handler.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
                    if (isNewEntryMode) {
                        handler.setButtonProperties("btn7", "CMN_Delete", "Delete", 0, null);
                        handler.setButtonProperties("btn8", "CMN_Clear",  "Clear",  1, null);
                    } else {
                        handler.setButtonProperties("btn7", "CMN_Delete", "Delete", 1, null);
                        handler.setButtonProperties("btn8", "CMN_Clear",  "Clear",  0, null);
                    }
                    handler.setButtonProperties("btn9",  "CMN_Reset",   "Reset",    0, null);
                    handler.setButtonProperties("btn10", "CMN_Return",  "Return",   1, null);
                }
                break;
                
            case NWXL0010Scrn02:
                
                // set Common buttons.
                handler.setButtonProperties("btn1",  "CMN_Save",     "Save",     0, null);
                handler.setButtonProperties("btn2",  "CMN_Submit",   "Submit",   0, null);
                handler.setButtonProperties("btn3",  "CMN_Apply",    "Apply",    0, null);
                handler.setButtonProperties("btn4",  "CMN_Approve",  "Approve",  0, null);
                handler.setButtonProperties("btn5",  "CMN_Reject",   "Reject",   0, null);
                handler.setButtonProperties("btn6",  "CMN_Download", "Download", 0, null);
                handler.setButtonProperties("btn7",  "CMN_Delete",   "Delete",   0, null);
                handler.setButtonProperties("btn8",  "CMN_Clear",    "Clear",    0, null);
                handler.setButtonProperties("btn9",  "CMN_Reset",    "Reset",    0, null);
                handler.setButtonProperties("btn10", "CMN_Return",   "Return",   1, null);
                
                // set Table BG-color.
                new S21TableColorController(scrnId.toString(), bMsg).setAlternateRowsBG("B", bMsg.B);
                break;

            default:
                break;
        }
    }
    
}
