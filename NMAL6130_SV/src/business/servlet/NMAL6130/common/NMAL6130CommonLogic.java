/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NMAL6130.common;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL6130.NMAL6130CMsg;
import business.servlet.NMAL6130.NMAL6130BMsg;
import business.servlet.NMAL6130.constant.NMAL6130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NMAL6130CommonLogic implements NMAL6130Constant {

    /**
     * set back-ground color of html table by alternate.
     * @param scrnId screen id
     * @param tableId html id of table tag
     * @param scrnMsg NMAL6130BMsg
     */
    public static void setAlternateRowsBG(String scrnId, String tableId, NMAL6130BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(scrnId, scrnMsg);
        tblColor.setAlternateRowsBG(tableId, (EZDBMsgArray) scrnMsg.getMsgData(tableId));
    }

    /**
     * create EZDCMsg to call search BLAP.
     * @param bMsg NMAL6130BMsg
     * @return NMAL6130CMsg
     */
    public static NMAL6130CMsg createCMsgForSearch(NMAL6130BMsg scrnMsg) {

        NMAL6130CMsg bizMsg = new NMAL6130CMsg();
        bizMsg.setBusinessID("NMAL6130");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * create EZDCMsg to call update BLAP.
     * @param bMsg NMAL6130BMsg
     * @return NMAL6130CMsg
     */
    public static NMAL6130CMsg createCMsgForUpdate(NMAL6130BMsg scrnMsg) {

        NMAL6130CMsg bizMsg = new NMAL6130CMsg();
        bizMsg.setBusinessID("NMAL6130");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * set gui attributes.
     * @param ezdHandler EZDCommonHandler
     * @param ezdParam ezd parameters from called screen
     * @param scrnMsg NMAL6130BMsg
     */
    public static void setGUIComponentsProperties(EZDCommonHandler ezdHandler, Serializable ezdParam, NMAL6130BMsg scrnMsg) {

        // Display-Mode : Modification
        if (isModificationMode(getDisplayMode(ezdParam))) {

            createModificationMode(ezdHandler, scrnMsg);

            if (scrnMsg.A.getValidCount() < scrnMsg.numConstVal_I1.getValueInt()) {
                ezdHandler.setButtonEnabled(BTN_UPLOAD[0], true);
            } else {
                ezdHandler.setButtonEnabled(BTN_UPLOAD[0], false);
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.mstrAttDataDescTxt_I.setInputProtected(true);
                scrnMsg.mstrActvCd_I1.setInputProtected(true);
                scrnMsg.mstrActvCd_I2.setInputProtected(true);
                scrnMsg.mstrActvNm_I2.setInputProtected(true);
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
                scrnMsg.mstrAttDataDescTxt_I.setInputProtected(true);
                scrnMsg.mstrActvCd_I1.setInputProtected(true);
                scrnMsg.mstrActvCd_I2.setInputProtected(true);
                scrnMsg.mstrActvNm_I2.setInputProtected(true);
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
     * @param scrnMsg NMAL6130BMsg
     * @param ezdParam ezd parameters from called screen
     * @throws S21AbendException
     */
    public static void checkScrnTransitionParameters(NMAL6130BMsg scrnMsg, Serializable ezdParam) throws S21AbendException {

        final NMAL6130CommonLogic myInstance = new NMAL6130CommonLogic();

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

        // [6]:company code
        BigDecimal cmpyPk = getCmpyPk(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[6]:company code = " + cmpyPk, myInstance);
        
        // [7]:party location pk
        BigDecimal ptyLocPk = getPtyLocPk(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[7]:party location pk = " + ptyLocPk, myInstance);
        
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
    public static String getEzBusinessID(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.mstrBizId_I;

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
    public static String getAttachmentsGrpTxt(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.mstrAttDataGrpTxt_I;

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
    public static String getAttachmentsLimitNum(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

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
    public static String getAuthorizeFileEx(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

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
    public static String getAuthorizeFileVol(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.numConstNm_I2;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, PARAM_ATT_DATA_VOL));
        }
        return ezdItem.getValue();
    }

   /**
    * get "company pk" from ezd parameters.
    * @param ezdParam ezd parameters from called screen
    * @return attachments grouping text
    */
   public static BigDecimal getCmpyPk(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

       EZDBBigDecimalItem ezdItem = scrnMsg.cmpyPk_I;

       if (!ZYPCommonFunc.hasValue(ezdItem)) {
           ezdItem.setValue(getParameterBigDecimal(ezdParam, PARAM_CMPY_PK));
       }
       return ezdItem.getValue();
   }

   /**
    * get "party location pk" from ezd parameters.
    * @param ezdParam ezd parameters from called screen
    * @return attachments grouping text
    */
   public static BigDecimal getPtyLocPk(NMAL6130BMsg scrnMsg, Serializable ezdParam) {

       EZDBBigDecimalItem ezdItem = scrnMsg.ptyLocPk_I;

       if (!ZYPCommonFunc.hasValue(ezdItem)) {
           ezdItem.setValue(getParameterBigDecimal(ezdParam, PARAM_PTY_LOC_PK));
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

    private static BigDecimal getParameterBigDecimal(Serializable ezdParam, int getIndex) {

        Object[] params = getArgForSubScreen(ezdParam);

        if (params.length > getIndex) {
            if (params[getIndex] instanceof BigDecimal) {
                return (BigDecimal) params[getIndex];
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

    private static void createReadOnlyMode(EZDCommonHandler ezdHandler, NMAL6130BMsg scrnMsg) {

        scrnMsg.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mstrAttDataNm.setInputProtected(false);
            scrnMsg.A.no(i).mstrAttDataDescTxt_FG.setInputProtected(false);
            scrnMsg.A.no(i).mstrActvNm.setInputProtected(true);
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

    private static void createModificationMode(EZDCommonHandler ezdHandler, NMAL6130BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.mstrAttDataDescTxt_O.setInputProtected(true);

    	for (int i = 0,length = scrnMsg.A.getValidCount(); i < length; i++) {
            scrnMsg.A.no(i).mstrActvNm.setInputProtected(true);
    	}

        // Buttons
        ezdHandler.setButtonEnabled(BTN_UPLOAD[0], true);
        ezdHandler.setButtonEnabled(BTN_SELECT_ALL[0], true);
        ezdHandler.setButtonEnabled(BTN_UN_SELECT_ALL[0], true);
        ezdHandler.setButtonEnabled(BTN_DELETE[0], true);

        // Common Buttons
        ezdHandler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    private static void createUploadOnlyMode(EZDCommonHandler ezdHandler, NMAL6130BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.mstrAttDataDescTxt_O.setInputProtected(true);

    	for (int i = 0,length = scrnMsg.A.getValidCount(); i < length; i++) {
            scrnMsg.A.no(i).mstrActvNm.setInputProtected(true);        		
    	}
        
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
