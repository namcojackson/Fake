package business.servlet.ZYPL0310.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.ZYPL0310.ZYPL0310CMsg;
import business.servlet.ZYPL0310.ZYPL0310BMsg;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZYPL0310CommonLogic {

    /**
     * set back-ground color of html table by alternate.
     * @param scrnId screen id
     * @param tableId html id of table tag
     * @param scrnMsg ZYPL0310BMsg
     */
    public static void setAlternateRowsBG(String scrnId, String tableId, ZYPL0310BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(scrnId, scrnMsg);
        //tblColor.setAlternateRowsBG(tableId, (EZDBMsgArray) scrnMsg.getMsgData(tableId));
        tblColor.setAlternateRowsBG(tableId+"_LeftTBL", (EZDBMsgArray) scrnMsg.getMsgData(tableId));
        tblColor.setAlternateRowsBG(tableId+"_RightTBL", (EZDBMsgArray) scrnMsg.getMsgData(tableId));
    }

    /**
     * create EZDCMsg to call search BLAP.
     * @param bMsg ZYPL0310BMsg
     * @return ZYPL0310CMsg
     */
    public static ZYPL0310CMsg createCMsgForSearch(ZYPL0310BMsg scrnMsg) {

        ZYPL0310CMsg bizMsg = new ZYPL0310CMsg();
        bizMsg.setBusinessID("ZYPL0310");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * create EZDCMsg to call update BLAP.
     * @param bMsg ZYPL0310BMsg
     * @return ZYPL0310CMsg
     */
    public static ZYPL0310CMsg createCMsgForUpdate(ZYPL0310BMsg scrnMsg) {

        ZYPL0310CMsg bizMsg = new ZYPL0310CMsg();
        bizMsg.setBusinessID("ZYPL0310");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * set gui attributes.
     * @param ezdHandler EZDCommonHandler
     * @param ezdParam ezd parameters from called screen
     * @param scrnMsg ZYPL0310BMsg
     */
    public static void setGUIComponentsProperties(EZDCommonHandler ezdHandler, Serializable ezdParam, ZYPL0310BMsg scrnMsg) {

        // Display-Mode : Modification
        if (isModificationMode(getDisplayMode(ezdParam))) {

            createModificationMode(ezdHandler, scrnMsg);

            if (scrnMsg.A.getValidCount() < scrnMsg.numConstVal_I1.getValueInt()) {
                ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], true);
            } else {
                ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], false);
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.xxAttDataCmntTxt_I.setInputProtected(true);
                scrnMsg.attDataTpCd.setInputProtected(true);
                scrnMsg.attDocTpCd.setInputProtected(true);
            }

            boolean hasRecords = scrnMsg.A.getValidCount() > 0;
            ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_SELECT_ALL[0], hasRecords);
            ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UN_SELECT_ALL[0], hasRecords);
            ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_DELETE[0], hasRecords);
            ezdHandler.setButtonConfirmMsg(ZYPL0310Constant.BTN_DELETE[0], ZYPL0310Constant.BTN_DELETE[3], new String[] {ZYPL0310Constant.BTN_DELETE[2] }, 0);

            // Display-Mode : Upload-Only
        } else if (isUploadOnlyMode(getDisplayMode(ezdParam))) {

            createUploadOnlyMode(ezdHandler, scrnMsg);

            if (scrnMsg.A.getValidCount() < scrnMsg.numConstVal_I1.getValueInt()) {
                ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], true);
            } else {
                ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], false);
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.xxAttDataCmntTxt_I.setInputProtected(true);
                scrnMsg.attDataTpCd.setInputProtected(true);
                scrnMsg.attDocTpCd.setInputProtected(true);
            }

            // Display-Mode : Read-Only
        } else if (isReadOnlyMode(getDisplayMode(ezdParam))) {
            createReadOnlyMode(ezdHandler, scrnMsg);

        } else {
            createReadOnlyMode(ezdHandler, scrnMsg);
        }

        scrnMsg.bizAppNm.setInputProtected(true);
        scrnMsg.attDataKeyTxt.setInputProtected(true);

        if(scrnMsg.A.getValidCount() > 0){
            // Add Start 2019/05/09 QC#50015
            ArrayList<String> attDocTpCdList = null;
            if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem130Txt)) {
                String[] attDocTpCdArray = scrnMsg.xxScrItem130Txt.getValue().split(",");
                attDocTpCdList = new ArrayList(Arrays.asList(attDocTpCdArray));
            }
            // Add End 2019/05/09 QC#50015

            for(int i=0; i < scrnMsg.A.getValidCount(); i++){
                // Add Start 2019/05/09 QC#50015
                if (null != attDocTpCdList && !attDocTpCdList.contains(scrnMsg.A.no(i).attDocTpCd_AI.getValue())) {
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
                }
                // Add End 2019/05/09 QC#50015

                scrnMsg.A.no(i).attDataTpCd_AI.setInputProtected(true);
                scrnMsg.A.no(i).attDocTpCd_AI.setInputProtected(true);
            }
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
     * @param scrnMsg ZYPL0310BMsg
     * @param ezdParam ezd parameters from called screen
     * @throws S21AbendException
     */
    public static void checkScrnTransitionParameters(ZYPL0310BMsg scrnMsg, Serializable ezdParam) throws S21AbendException {

        final ZYPL0310CommonLogic myInstance = new ZYPL0310CommonLogic();

        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ checkScrnTransitionParameters:START +++++++++++++++++++++++++", myInstance);

        // [0]:display mode
        String displayMode = getDisplayMode(ezdParam);
        EZDDebugOutput.println(1, "[0]:display mode = " + displayMode, myInstance);

        // [1]:business application id
        String ezBusinessID = getEzBusinessID(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[1]:business application id = " + ezBusinessID, myInstance);
        S21InfoLogOutput.println("ZYPL0310Param[1]:business application id = " + ezBusinessID);

        // [2]:attachments grouping text
        String attachmentsGrpTxt = getAttachmentsGrpTxt(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[2]:attachments grouping text = " + attachmentsGrpTxt, myInstance);
        S21InfoLogOutput.println("ZYPL0310Param[2]:attachments grouping text = " + attachmentsGrpTxt);

        // [3]:function name
        String fnName = getFnName(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[3]:function name = " + fnName, myInstance);

        // [4]:primary key
        String primaryKey = getPrimaryKey(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[4]:primary key = " + primaryKey, myInstance);

        // [5]:document type list
        // List<String[]> docTypeList = getDocTypeList(scrnMsg, ezdParam);
        String docTypeCdTblNm = getDocTypeCdTblNm(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[5]:document type code table name = " + docTypeCdTblNm, myInstance);

        if (!ZYPL0310Constant.DISPLAY_MODE_READ_ONLY.equals(displayMode)) {

            // [6]:attachments limit (This is key of
            // [NUM_CONST] table.)
            String attachmentsLimitNum = getAttachmentsLimitNum(scrnMsg, ezdParam);
            EZDDebugOutput.println(1, "[6]:attachments limit (This is key of [NUM_CONST] table.) = " + attachmentsLimitNum, myInstance);

            // [7]:authorize file extentions (This is key of
            // [VAR_CHAR_CONST ] table.)
            String authorizeFIleEx = getAuthorizeFileEx(scrnMsg, ezdParam);
            EZDDebugOutput.println(1, "[7]:authorize file extentions (This is key of [VAR_CHAR_CONST ] table.) = " + authorizeFIleEx, myInstance);

            // [8]:authorize file volume(size) (This is key of
            // [NUM_CONST] table.)
            String authorizeFileVol = getAuthorizeFileVol(scrnMsg, ezdParam);
            EZDDebugOutput.println(1, "[8]:authorize file volume(size) (This is key of [NUM_CONST] table.) = " + authorizeFileVol, myInstance);
        }

        // [9]:business API ID for Therefore
        String bizApiId = getBizApiIdForTherefore(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[9]:Business Api ID = " + bizApiId, myInstance);

        // Add Start 2019/05/09 QC#50015
        // [10]:document type Constant value
        String docTypeConstVal = getDocTypeConstVal(scrnMsg, ezdParam);
        EZDDebugOutput.println(1, "[10]:document type constant value = " + docTypeConstVal, myInstance);
        // Add End 2019/05/09 QC#50015

        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ checkScrnTransitionParameters:E N D +++++++++++++++++++++++++", myInstance);
    }

    /**
     * get "display mode" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return Read-Only-Mode or Modification-Mode
     */
    public static String getDisplayMode(Serializable ezdParam) {
        return getParameter(ezdParam, ZYPL0310Constant.PARAM_DISPLAY_MODE);
    }

    /**
     * get "business application id" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return business application id
     */
    public static String getEzBusinessID(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.ezBusinessID_I;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, ZYPL0310Constant.PARAM_EZBUSINESS_ID));
        }
        return ezdItem.getValue();
    }

    /**
     * get "attachments grouping text" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return attachments grouping text
     */
    public static String getAttachmentsGrpTxt(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.attDataGrpTxt_I;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, ZYPL0310Constant.PARAM_ATT_DATA_GRP_TXT));
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
    public static String getAttachmentsLimitNum(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.numConstNm_I1;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            String itemValue = getOptionalParameter(ezdParam, ZYPL0310Constant.PARAM_ATT_LIMIT_NUM);
            if(ZYPCommonFunc.hasValue(itemValue)){
                ezdItem.setValue(itemValue);
            } else {
                ezdItem.clear();
            }
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
    public static String getAuthorizeFileEx(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.varCharConstNm_I2;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            String itemValue = getOptionalParameter(ezdParam, ZYPL0310Constant.PARAM_AUTHORIZE_FILE_EXT);
            if(ZYPCommonFunc.hasValue(itemValue)){
                ezdItem.setValue(itemValue);
            } else {
                ezdItem.clear();
            }
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
    public static String getAuthorizeFileVol(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.numConstNm_I2;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            String itemValue = getOptionalParameter(ezdParam, ZYPL0310Constant.PARAM_ATT_DATA_VOL);
            if(ZYPCommonFunc.hasValue(itemValue)){
                ezdItem.setValue(itemValue);
            } else {
                ezdItem.clear();
            }
        }
        return ezdItem.getValue();
    }

    /**
     * get "function name" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return function name
     */
    public static String getFnName(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.bizAppNm;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, ZYPL0310Constant.PARAM_FUNCTION_NAME));
        }
        return ezdItem.getValue();
    }

    /**
     * get "primary key" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return primary key
     */
    public static String getPrimaryKey(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.attDataKeyTxt;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameter(ezdParam, ZYPL0310Constant.PARAM_PRIMARY_KEY));
        }
        return ezdItem.getValue();
    }

    /**
     * get "document type list" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return document type list
     */
    public static String getDocTypeCdTblNm(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.cdTblNm;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            String itemValue = getOptionalParameter(ezdParam, ZYPL0310Constant.PARAM_DOC_TYPE_CD_TBL_NM);
            if(ZYPCommonFunc.hasValue(itemValue)){
                ezdItem.setValue(itemValue);
            } else {
                ezdItem.setValue(ZYPL0310Constant.ATT_DATA_DOC_TYPE_CD_TBL_NM);
            }
        }
        return ezdItem.getValue();
    }

    public static String getBizApiIdForTherefore(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.ezBusinessAplID;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            String itemValue = getOptionalParameter(ezdParam, ZYPL0310Constant.PARAM_BIZ_API_ID);
            if(ZYPCommonFunc.hasValue(itemValue)){
                ezdItem.setValue(itemValue);
            } else {
                ezdItem.clear();
            }
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
            throw new S21AbendException("Illegal Argument. Parameter length must be maximum '9'. But... -> [" + params.length + "]");
        }
    }

    private static String getOptionalParameter(Serializable ezdParam, int getIndex) {

        Object[] params = getArgForSubScreen(ezdParam);

        if (params.length > getIndex) {
            if(params[getIndex] == null){
                return null;
            }
            if (params[getIndex] instanceof String) {
                return (String) params[getIndex];
            } else {
                throw new S21AbendException("Illegal Argument. Parameter[" + getIndex + "] must be 'String'. But... -> [" + params[getIndex] + "]");
            }
        } else {
            return null;
        }
    }

    // Add Start 2019/05/09 QC#50015
    public static String getDocTypeConstVal(ZYPL0310BMsg scrnMsg, Serializable ezdParam) {

        EZDBStringItem ezdItem = scrnMsg.xxScrItem130Txt;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            String itemValue = getOptionalParameter(ezdParam, ZYPL0310Constant.PARAM_DOC_TYPE_CONST_VAL);
            if(ZYPCommonFunc.hasValue(itemValue)){
                ezdItem.setValue(itemValue);
            }
        }
        return ezdItem.getValue();
    }
    // Add End 2019/05/09 QC#50015

    private static boolean isModificationMode(String displayMode) {
        return ZYPL0310Constant.DISPLAY_MODE_MODIFICATION.equals(displayMode);
    }

    private static boolean isUploadOnlyMode(String displayMode) {
        return ZYPL0310Constant.DISPLAY_MODE_UPLOAD_ONLY.equals(displayMode);
    }

    private static boolean isReadOnlyMode(String displayMode) {
        return ZYPL0310Constant.DISPLAY_MODE_READ_ONLY.equals(displayMode);
    }

    private static void createReadOnlyMode(EZDCommonHandler ezdHandler, ZYPL0310BMsg scrnMsg) {

        scrnMsg.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).attDataNm.setInputProtected(false);
            scrnMsg.A.no(i).attDataDescTxt_FG.setInputProtected(false);
        }

        // Buttons
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], false);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_DELETE[0], false);

        // Common Buttons
        ezdHandler.setButtonProperties(ZYPL0310Constant.BTN_CMN_CLEAR[0], ZYPL0310Constant.BTN_CMN_CLEAR[1], ZYPL0310Constant.BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(ZYPL0310Constant.BTN_CMN_CLOSE[0], ZYPL0310Constant.BTN_CMN_CLOSE[1], ZYPL0310Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    private static void createModificationMode(EZDCommonHandler ezdHandler, ZYPL0310BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.xxAttDataCmntTxt_O.setInputProtected(true);

        // Buttons
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], true);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_SELECT_ALL[0], true);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UN_SELECT_ALL[0], true);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_DELETE[0], true);

        // Common Buttons
        ezdHandler.setButtonProperties(ZYPL0310Constant.BTN_CMN_CLEAR[0], ZYPL0310Constant.BTN_CMN_CLEAR[1], ZYPL0310Constant.BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(ZYPL0310Constant.BTN_CMN_CLOSE[0], ZYPL0310Constant.BTN_CMN_CLOSE[1], ZYPL0310Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    private static void createUploadOnlyMode(EZDCommonHandler ezdHandler, ZYPL0310BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.xxAttDataCmntTxt_O.setInputProtected(true);

        // Buttons
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UPLOAD[0], true);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_UN_SELECT_ALL[0], false);
        ezdHandler.setButtonEnabled(ZYPL0310Constant.BTN_DELETE[0], false);

        // Common Buttons
        ezdHandler.setButtonProperties(ZYPL0310Constant.BTN_CMN_CLEAR[0], ZYPL0310Constant.BTN_CMN_CLEAR[1], ZYPL0310Constant.BTN_CMN_CLEAR[2], 0, null);
        ezdHandler.setButtonProperties(ZYPL0310Constant.BTN_CMN_CLOSE[0], ZYPL0310Constant.BTN_CMN_CLOSE[1], ZYPL0310Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    private static Object[] getArgForSubScreen(Serializable ezdParams) {
        if (ezdParams instanceof Object[]) {
            return (Object[]) ezdParams;
        } else {
            throw new S21AbendException("Illegal Argument. Parameter must be Object[]. But... -> [" + ezdParams + "]");
        }
    }

    public static void createDataTypePulldown(ZYPL0310BMsg scrnMsg){
        scrnMsg.xxAttDataTpNm_OT.no(0).setValue(ZYPL0310Constant.ATT_DATA_TYPE_FILE);
        scrnMsg.xxAttDataTpNm_OT.no(1).setValue(ZYPL0310Constant.ATT_DATA_TYPE_NOTE);
        scrnMsg.xxAttDataTpNm_OT.no(2).setValue(ZYPL0310Constant.ATT_DATA_TYPE_URL);
        scrnMsg.xxAttDataTpNm_OT.no(3).setValue(ZYPL0310Constant.ATT_DATA_TYPE_THEREFORE);

        scrnMsg.attDataTpCd_OT.no(0).setValue(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE);
        scrnMsg.attDataTpCd_OT.no(1).setValue(ZYPL0310Constant.ATT_DATA_TYPE_CODE_NOTE);
        scrnMsg.attDataTpCd_OT.no(2).setValue(ZYPL0310Constant.ATT_DATA_TYPE_CODE_URL);
        scrnMsg.attDataTpCd_OT.no(3).setValue(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE);

    }

    public static Boolean isUploadedThreforeDocId(ZYPL0310BMsg scrnMsg){
        int thereforeDocId = scrnMsg.docMgtDocId.getValueInt();
        if(thereforeDocId > 0){
            for(int i=0; i<scrnMsg.A.getValidCount(); i++){
                if(scrnMsg.A.no(i).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)
                        && thereforeDocId == Integer.valueOf(scrnMsg.A.no(i).attDataNm.getValue())){
                    return true;
                }
            }
        }
        return false;
    }
}
