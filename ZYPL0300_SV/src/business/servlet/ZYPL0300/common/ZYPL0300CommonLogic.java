/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.ZYPL0300.common;

import java.io.Serializable;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.ZYPL0300.ZYPL0300CMsg;
import business.servlet.ZYPL0300.ZYPL0300BMsg;
import business.servlet.ZYPL0300.constant.ZYPL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZYPL0300CommonLogic implements ZYPL0300Constant {

    /**
     * set back-ground color of html table by alternate.
     * @param scrnId screen id
     * @param tableId html id of table tag
     * @param scrnMsg ZYPL0300BMsg
     */
    public static void setAlternateRowsBG(String scrnId, String tableId, ZYPL0300BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(scrnId, scrnMsg);
        tblColor.setAlternateRowsBG(tableId, (EZDBMsgArray) scrnMsg.getMsgData(tableId));
    }

    /**
     * create EZDCMsg to call search BLAP.
     * @param bMsg ZYPL0300BMsg
     * @return ZYPL0300CMsg
     */
    public static ZYPL0300CMsg createCMsgForSearch(ZYPL0300BMsg scrnMsg) {

        ZYPL0300CMsg bizMsg = new ZYPL0300CMsg();
        bizMsg.setBusinessID("ZYPL0300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * create EZDCMsg to call update BLAP.
     * @param bMsg ZYPL0300BMsg
     * @return ZYPL0300CMsg
     */
    public static ZYPL0300CMsg createCMsgForUpdate(ZYPL0300BMsg scrnMsg) {

        ZYPL0300CMsg bizMsg = new ZYPL0300CMsg();
        bizMsg.setBusinessID("ZYPL0300");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * set gui attributes.
     * @param ezdHandler EZDCommonHandler
     * @param ezdParam ezd parameters from called screen
     * @param scrnMsg ZYPL0300BMsg
     */
    public static void setGUIComponentsProperties(EZDCommonHandler ezdHandler, Serializable ezdParam, ZYPL0300BMsg scrnMsg) {

        // Display-Mode : Modification
        if (isModificationMode(getDisplayMode(ezdParam))) {

            createModificationMode(ezdHandler, scrnMsg);

            if (scrnMsg.A.getValidCount() < scrnMsg.numConstVal_I1.getValueInt()) {
                ezdHandler.setButtonEnabled(BTN_UPLOAD[0], true);
            } else {
                ezdHandler.setButtonEnabled(BTN_UPLOAD[0], false);
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.attDataDescTxt_I.setInputProtected(true);
            }

            boolean hasRecords = scrnMsg.A.getValidCount() > 0;
            ezdHandler.setButtonEnabled(BTN_SELECT_ALL[0], hasRecords);
            ezdHandler.setButtonEnabled(BTN_UN_SELECT_ALL[0], hasRecords);
            ezdHandler.setButtonEnabled(BTN_DELETE[0], hasRecords);
            ezdHandler.setButtonConfirmMsg(BTN_DELETE[0], BTN_DELETE[3], new String[] {BTN_DELETE[2] }, 0);

            // Display-Mode : Upload-Only
        } else if (isUploadOnlyMode(getDisplayMode(ezdParam))) {

            createUploadOnlyMode(ezdHandler, scrnMsg);

            if (scrnMsg.A.getValidCount() < scrnMsg.numConstVal_I1.getValueInt()) {
                ezdHandler.setButtonEnabled(BTN_UPLOAD[0], true);
            } else {
                ezdHandler.setButtonEnabled(BTN_UPLOAD[0], false);
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.attDataDescTxt_I.setInputProtected(true);
            }

            // Display-Mode : Read-Only
        } else if (isReadOnlyMode(getDisplayMode(ezdParam))) {
            createReadOnlyMode(ezdHandler, scrnMsg);

        } else {
            createReadOnlyMode(ezdHandler, scrnMsg);
        }

    }

    /**
     * validate ezd parameters from called screen.
     * 
     * <pre>
     * [0]:String:display mode
     * [1]:String:business application id
     * [2]:String:attachments grouping text
     * [3]:String:attachments limit (This is key of [NUM_CONST] table.)
     * [4]:String:authorize file extentions (This is key of [VAR_CHAR_CONST ] table.)
     * [5]:String:authorize file volume(size) (This is key of [NUM_CONST] table.)
     * </pre>
     * 
     * @param scrnMsg ZYPL0300BMsg
     * @param ezdParam ezd parameters from called screen
     * @throws S21AbendException
     */
    public static void checkScrnTransitionParameters(ZYPL0300BMsg scrnMsg, Serializable ezdParam) throws S21AbendException {

        final ZYPL0300CommonLogic myInstance = new ZYPL0300CommonLogic();

        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ checkScrnTransitionParameters:START +++++++++++++++++++++++++", myInstance);

        // [0]:display mode
        String displayMode = getDisplayMode(ezdParam);
        EZDDebugOutput.println(1, "[0]:display mode = " + displayMode, myInstance);

        // [1]:business application id
        String ezBusinessID = getEzBusinessID(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[1]:business application id = " + ezBusinessID, myInstance);

        // [2]:attachments grouping text
        String attachmentsGrpTxt = getAttachmentsGrpTxt(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[2]:attachments grouping text = " + attachmentsGrpTxt, myInstance);

        if (!DISPLAY_MODE_READ_ONLY.equals(displayMode)) {

            // [3]:attachments limit (This is key of
            // [NUM_CONST] table.)
            String attachmentsLimitNum = getAttachmentsLimitNum(scrnMsg, ezdParam);
            EZDDebugOutput.println(1, "[3]:attachments limit (This is key of [NUM_CONST] table.) = " + attachmentsLimitNum, myInstance);

            // [4]:authorize file extentions (This is key of
            // [VAR_CHAR_CONST ] table.)
            String authorizeFIleEx = getAuthorizeFileEx(scrnMsg, ezdParam);
            EZDDebugOutput.println(1, "[4]:authorize file extentions (This is key of [VAR_CHAR_CONST ] table.) = " + authorizeFIleEx, myInstance);

            // [5]:authorize file volume(size) (This is key of
            // [NUM_CONST] table.)
            String authorizeFileVol = getAuthorizeFileVol(scrnMsg, ezdParam);
            EZDDebugOutput.println(1, "[5]:authorize file volume(size) (This is key of [NUM_CONST] table.) = " + authorizeFileVol, myInstance);
        }

        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ checkScrnTransitionParameters:E N D +++++++++++++++++++++++++", myInstance);
    }

    /**
     * get "display mode" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return Read-Only-Mode or Modification-Mode
     */
    public static String getDisplayMode(Serializable ezdParam) {
        return getParameter(ezdParam, PARAM_DISPLAY_MODE);
    }

    /**
     * get "business application id" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return business application id
     */
    public static String getEzBusinessID(ZYPL0300BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.ezBusinessID_I;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, PARAM_EZBUSINESS_ID));
        }
        return ezdItem.getValue();
    }

    /**
     * get "attachments grouping text" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return attachments grouping text
     */
    public static String getAttachmentsGrpTxt(ZYPL0300BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.attDataGrpTxt_I;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, PARAM_ATT_DATA_GRP_TXT));
        }
        return ezdItem.getValue();
    }

    /**
     * get "authorize file extentions (This is key of [VAR_CHAR_CONST ]
     * table.)" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return authorize file extentions (This is key of
     * [VAR_CHAR_CONST ] table.)
     */
    public static String getAttachmentsLimitNum(ZYPL0300BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.numConstNm_I1;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, PARAM_ATT_LIMIT_NUM));
        }
        return ezdItem.getValue();
    }

    /**
     * get "authorize file extentions (This is key of [VAR_CHAR_CONST ]
     * table.)" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return authorize file extentions (This is key of
     * [VAR_CHAR_CONST ] table.)
     */
    public static String getAuthorizeFileEx(ZYPL0300BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.varCharConstNm_I2;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, PARAM_AUTHORIZE_FILE_EXT));
        }
        return ezdItem.getValue();
    }

    /**
     * get "authorize file volume(size) (This is key of [NUM_CONST]
     * table.)" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @returnauthorize file volume(size) (This is key of [NUM_CONST]
     * table.)
     */
    public static String getAuthorizeFileVol(ZYPL0300BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.numConstNm_I2;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, PARAM_ATT_DATA_VOL));
        }
        return ezdItem.getValue();
    }

    private static String getParameter(Serializable ezdParam, int getIndex) {

        Object[] params = getArgForSubScreen(ezdParam);

        if (params.length > getIndex) {
            if (params[getIndex] instanceof String) {
                return (String) params[getIndex];
            } else {
                throw new S21AbendException("Illegal Argument. Parameter[" + getIndex + "] must be 'String'. But... -> [" + params[getIndex] + "]");
            }
        } else {
            throw new S21AbendException("Illegal Argument. Parameter length must be '6'. But... -> [" + params.length + "]");
        }
    }

    private static boolean isModificationMode(String displayMode) {
        return DISPLAY_MODE_MODIFICATION.equals(displayMode);
    }

    private static boolean isUploadOnlyMode(String displayMode) {
        return DISPLAY_MODE_UPLOAD_ONLY.equals(displayMode);
    }

    private static boolean isReadOnlyMode(String displayMode) {
        return DISPLAY_MODE_READ_ONLY.equals(displayMode);
    }

    private static void createReadOnlyMode(EZDCommonHandler ezdHandler, ZYPL0300BMsg scrnMsg) {

        scrnMsg.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).attDataNm.setInputProtected(false);
            scrnMsg.A.no(i).attDataDescTxt_FG.setInputProtected(false);
        }

        // Buttons
        ezdHandler.setButtonEnabled(BTN_UPLOAD[0], false);
        ezdHandler.setButtonEnabled(BTN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(BTN_UN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(BTN_DELETE[0], false);

        // Common Buttons
        ezdHandler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    private static void createModificationMode(EZDCommonHandler ezdHandler, ZYPL0300BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.attDataDescTxt_O.setInputProtected(true);

        // Buttons
        ezdHandler.setButtonEnabled(BTN_UPLOAD[0], true);
        ezdHandler.setButtonEnabled(BTN_SELECT_ALL[0], true);
        ezdHandler.setButtonEnabled(BTN_UN_SELECT_ALL[0], true);
        ezdHandler.setButtonEnabled(BTN_DELETE[0], true);

        // Common Buttons
        ezdHandler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    private static void createUploadOnlyMode(EZDCommonHandler ezdHandler, ZYPL0300BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.attDataDescTxt_O.setInputProtected(true);

        // Buttons
        ezdHandler.setButtonEnabled(BTN_UPLOAD[0], true);
        ezdHandler.setButtonEnabled(BTN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(BTN_UN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(BTN_DELETE[0], false);

        // Common Buttons
        ezdHandler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    private static Object[] getArgForSubScreen(Serializable ezdParams) {
        if (ezdParams instanceof Object[]) {
            return (Object[]) ezdParams;
        } else {
            throw new S21AbendException("Illegal Argument. Parameter must be Object[]. But... -> [" + ezdParams + "]");
        }
    }

}
