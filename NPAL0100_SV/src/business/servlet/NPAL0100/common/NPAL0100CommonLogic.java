/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2009   Fujitsu         I.Kondo         Create          N/A
 * 03/29/2010   Fujitsu         H.Suganuma      Update          5246
 * 2011/10/27   CSAI            N.Sasaki        Update          362045
 * 2016/01/07   CSAI            K.Lee           Update          CSA Initial
 *</pre>
 */
package business.servlet.NPAL0100.common;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NPAL0100.NPAL0100CMsg;
import business.servlet.NPAL0100.NPAL0100BMsg;
import business.servlet.NPAL0100.NPAL0100_ABMsg;
import business.servlet.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NPAL0100CommonLogic implements NPAL0100Constant {

    /**
     * RequestParameter check & copy.
     * @param params RequestParameter from other BusinessID.
     * @param scrnMsg NPAL0100BMsg
     */
    public static void otherBusConnectFrom_NPAL0100_INIT(Object[] params, NPAL0100BMsg scrnMsg) {

        scrnMsg.xxErrFlg.setValue(ERROR_FLG_OFF);

        // Check Request Parameter
        checkRequestParamter(params, scrnMsg);

        if (ERROR_FLG_ON.equals(scrnMsg.xxErrFlg.getValue())) {
            return;
        }

        // Set Request Parameter to BMsg.
        ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, (EZDBStringItem) params[0]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, (EZDBStringItem) params[1]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNum, (EZDBStringItem) params[2]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoDt, (EZDBDateItem) params[3]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[4]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, (EZDBStringItem) params[5]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, (EZDBStringItem) params[6]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, (EZDBStringItem) params[7]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.poQty, (EZDBBigDecimalItem) params[8]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.invQty, (EZDBBigDecimalItem) params[9]);

        // Hidden Item
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoDtlLineNum, (EZDBStringItem) params[10]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoDtlLineSubNum, (EZDBStringItem) params[11]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shpgPlnNum, (EZDBStringItem) params[12]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum, (EZDBStringItem) params[13]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdDtlLineNum, (EZDBStringItem) params[14]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.poRcvNum, (EZDBStringItem) params[15]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.poRcvLineNum, (EZDBStringItem) params[16]);

        // table
        List<EZDBBigDecimalItem> qtyNumArray = (ArrayList<EZDBBigDecimalItem>) params[17];
        List<EZDBStringItem> serialNumArray = (ArrayList<EZDBStringItem>) params[18];
        List<EZDBStringItem> flgArray = (ArrayList<EZDBStringItem>) params[19];
        for (int i = 0; i < scrnMsg.poQty.getValueInt(); i++) {
            if (ZYPCommonFunc.hasValue(qtyNumArray.get(i))) {
                debugMsg("param[17][" + i + "](Qty Num)   =" + qtyNumArray.get(i).getValueInt());
                debugMsg("param[18][" + i + "](Serial Num)=" + serialNumArray.get(i).getValue());
                debugMsg("param[19][" + i + "](PROC_FLG)  =" + flgArray.get(i).getValue());
                debugMsg("------------------------------------------------------");
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxRowNum_B2, qtyNumArray.get(i));
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).serNum_B1, serialNumArray.get(i));
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxSetFlg_B1, flgArray.get(i));
            } else {
                break;
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_R1, (EZDBBigDecimalItem) params[20]);
        // New Entry Flg
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPgFlg_NW, (String) params[21]);

        //2016/1/7 K.Lee Start Add
        // Supplier Name
        if (params.length > 22) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, (EZDBStringItem) params[22]);
        }

        // PO Qualifier Code
        if (params.length > 23) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.poQlfyCd, (EZDBStringItem) params[23]);
        }

        // PO Source Name
        if (params.length > 24) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdSrcNm, (EZDBStringItem) params[24]);
        }
        //2016/1/7 K.Lee End Add

        int viewRowNum;
        if (scrnMsg.invQty.getValueInt() > MAX_NUM_INV_QTY) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0038I.toString());
            viewRowNum = scrnMsg.B.length();
        } else {
            viewRowNum = ((EZDBBigDecimalItem) params[9]).getValueInt();
        }

        for (int i = 0; i < viewRowNum; i++) {
            scrnMsg.B.no(i).xxRowNum_B1.setValue(i + 1);
        }

    }

    /**
     * Set request data to Bisiness Message.
     * @return NPAL0100CMsg
     */
    public static NPAL0100CMsg setRequestDataToBizMsg() {

        NPAL0100CMsg bizMsg;

        bizMsg = new NPAL0100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE);

        return bizMsg;
    }

    /**
     * Input data check for Apply.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NPAL0100BMsg
     */
    public static void checkInputForApply(EZDCommonHandler scrnAppli, NPAL0100BMsg scrnMsg) {
        scrnMsg.xxErrFlg.clear();
        // check SerialNum Empty when Edit Button disabled & Cancel
        // Button effective.
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ITEM_STATUS_DISABLED.equals(scrnAppli.getButtonStatus(BTN_NM.Edit.toString(), i)) && ITEM_STATUS_ENABLED.equals(scrnAppli.getButtonStatus(BTN_NM.Cancel.toString(), i))) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum_A1)) {
                    scrnMsg.setMessageInfo(MESSAGE_CD.ZZM9000E.toString(), new String[] {scrnMsg.A.no(i).serNum_A1.getNameForMessage() });
                    throw new EZDFieldErrorException();
                }
            }
        }
    }

    /**
     * Set Request data for Apply
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NPAL0100BMsg
     */
    public static void setRequestDataForApply(EZDCommonHandler scrnAppli, NPAL0100BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ITEM_STATUS_DISABLED.equals(scrnAppli.getButtonStatus(BTN_NM.Edit.toString(), i))) {

                if (ITEM_STATUS_DISABLED.equals(scrnAppli.getButtonStatus(BTN_NM.Cancel.toString(), i)) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum_A1)) {
                    // new
                    scrnMsg.A.no(i).xxSetFlg_A1.setValue(PROC_FLG_NEW);
                } else if ("".equals(scrnAppli.getButtonStatus(BTN_NM.Cancel.toString(), i)) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum_A1)) {
                    // update
                    scrnMsg.A.no(i).xxSetFlg_A1.setValue(PROC_FLG_UPDATE);
                } else {
                    // other
                    scrnMsg.A.no(i).xxSetFlg_A1.setValue(PROC_FLG_OTHER);
                }
            }
        }
    }

    /**
     * Check Request Parameter
     * @param params RequestParameter
     * @param scrnMsg NPAL0100BMsg
     */
    private static void checkRequestParamter(Object[] params, NPAL0100BMsg scrnMsg) {

        // VND_CD
        if (isInvalidItemData(params[0])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_VND_CD });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[0](VND_CD)=" + ((EZDBStringItem) params[0]).getValue());
        }
        // VND_NM
        if (isInvalidItemData(params[1])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_VND_NM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[1](VND_NM)=" + ((EZDBStringItem) params[1]).getValue());
        }
        // CUST_ISS_PO_NUM
// Defect[5246] MOD START
//        if (isInvalidItemData(params[2])) {

//2016/1/7 K.Lee Start Del
//        if (null == params[2]) {
//// Defect[5246] MOD END
//            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_CUST_ISS_PO_NUM });
//            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
//            return;
//        } else {
//            debugMsg("param[2](CUST_ISS_PO_NUM)=" + ((EZDBStringItem) params[2]).getValue());
//        }
//        // CUST_ISS_PO_DT
//        if (isInvalidItemData(params[3])) {
//            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_CUST_ISS_PO_DT });
//            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
//            return;
//        } else {
//            debugMsg("param[3](CUST_ISS_PO_DT)=" + ((EZDBDateItem) params[3]).getValue());
//        }
//2016/1/7 K.Lee End Del

        // CPO_ORD_NUM
        if (isInvalidItemData(params[4])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_CPO_ORD_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[4](CPO_ORD_NUM)=" + ((EZDBStringItem) params[4]).getValue());
        }
        // BILL_TO_CUST_CD
        if (isInvalidItemData(params[5])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_BILL_TO_CUST_CD });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[5](BILL_TO_CUST_CD)=" + ((EZDBStringItem) params[5]).getValue());
        }
        // MDSE_CD
        if (isInvalidItemData(params[6])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_MDSE_CD });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[6](MDSE_CD)=" + ((EZDBStringItem) params[6]).getValue());
        }
        // MDSE_NM
        if (isInvalidItemData(params[7])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_MDSE_DESC_SHORT_TXT });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[7](MDSE_DESC_SHORT_TXT)=" + ((EZDBStringItem) params[7]).getValue());
        }
        // PO_QTY
        if (isInvalidItemData(params[8])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_PO_QTY });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[8](PO_QTY)=" + ((EZDBBigDecimalItem) params[8]).getValue());
        }
        // INV_QTY
        if (isInvalidItemData(params[9])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_INV_QTY });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[9](INV_QTY)=" + ((EZDBBigDecimalItem) params[9]).getValue());
        }
        // CPO_DTL_LINE_NUM
        if (isInvalidItemData(params[10])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_CPO_DTL_LINE_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[10](CPO_DTL_LINE_NUM)=" + ((EZDBStringItem) params[10]).getValue());
        }
        // CPO_DTL_LINE_SUB_NUM
        if (isInvalidItemData(params[11])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_CPO_DTL_LINE_SUB_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[11](CPO_DTL_LINE_SUB_NUM)=" + ((EZDBStringItem) params[11]).getValue());
        }
// delete 12/10/2009
//        // SHPG_PLN_NUM
//        if (isInvalidItemData(params[12])) {
//            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_SHPG_PLN_NUM });
//            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
//            return;
//        } else {
//            debugMsg("param[12](SHPG_PLN_NUM)=" + ((EZDBStringItem) params[12]).getValue());
//        }
        // PO_ORD_NUM
        if (isInvalidItemData(params[13])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_PO_ORD_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[13](PO_ORD_NUM)=" + ((EZDBStringItem) params[13]).getValue());
        }
        // PO_ORD_DTL_LINE_NUM
        if (isInvalidItemData(params[14])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_PO_ORD_DTL_LINE_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[14](PO_ORD_DTL_LINE_NUM)=" + ((EZDBStringItem) params[14]).getValue());
        }
        // PO_RCV_NUM
        if (null == params[15]) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_PO_RCV_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[15](PO_RCV_NUM)=" + ((EZDBStringItem) params[15]).getValue());
        }
        // PO_RCV_LINE_NUM
        if (null == params[16]) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_PO_RCV_LINE_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[16](PO_RCV_LINE_NUM)=" + ((EZDBStringItem) params[16]).getValue());
        }
        // Qty Num Array
        if (null == params[17]) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_QTY_NUM });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        }
        // Serial Num Array
        if (null == params[18]) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_SERIAL_NUM_ARRAY });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        }
        // PROC_FLG Array
        if (null == params[19]) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {REQUEST_PARAM_PROC_FLG_ARRAY });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        }

        // Serial # List size
        if (isInvalidItemData(params[20])) {
            scrnMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {SCRN_ITEM_NM_SER_NUM_LIST_SIZE });
            scrnMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return;
        } else {
            debugMsg("param[20](Serial # List size)=" + ((EZDBBigDecimalItem) params[20]).getValue());
        }

    }

    /**
     * If item(EZDBStringItem or EZDBDateItem) is Null or
     * Empty(getValue length equal zero), return true.</br> If
     * item(EZDBBigDecimalItem) is Zero or Negative Value, return
     * true.
     * @param item Object(EZDBItem)
     * @return true:Null or Empty/false:Not Null and Not Empty
     */
    private static boolean isInvalidItemData(Object item) {

        if (null == item) {
            return true;
        }

        if (item instanceof EZDBStringItem && ((EZDBStringItem) item).isClear()) {
            return true;
        } else if (item instanceof EZDBDateItem && ((EZDBDateItem) item).isClear()) {
            return true;
        } else if (item instanceof EZDBBigDecimalItem) {
            if (((EZDBBigDecimalItem) item).isClear() || ((EZDBBigDecimalItem) item).getValueInt() < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initialize Screen Item & Button.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NPAL0100BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NPAL0100BMsg scrnMsg) {

        initializeInputProtect(scrnAppli, scrnMsg);

        // button
        scrnAppli.setButtonProperties(BTN_PAGE_PREV[0], BTN_PAGE_PREV[1], BTN_PAGE_PREV[2], 1, null);
        scrnAppli.setButtonProperties(BTN_PAGE_NEXT[0], BTN_PAGE_NEXT[1], BTN_PAGE_NEXT[2], 1, null);
        scrnAppli.setButtonProperties(BTN_APPLY[0], BTN_APPLY[1], BTN_APPLY[2], 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN1[0], BTN_CMN_BTN1[1], BTN_CMN_BTN1[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN3[0], BTN_CMN_BTN3[1], BTN_CMN_BTN3[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN4[0], BTN_CMN_BTN4[1], BTN_CMN_BTN4[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN5[0], BTN_CMN_BTN5[1], BTN_CMN_BTN5[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN6[0], BTN_CMN_BTN6[1], BTN_CMN_BTN6[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN7[0], BTN_CMN_BTN7[1], BTN_CMN_BTN7[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN9[0], BTN_CMN_BTN9[1], BTN_CMN_BTN9[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);

        initializeCommon(scrnAppli, scrnMsg);
    }

    /**
     * Initialize Screen Item & Button(Error happend).
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NPAL0100BMsg
     */
    public static void initializeForError(EZDCommonHandler scrnAppli, NPAL0100BMsg scrnMsg) {

        initializeInputProtect(scrnAppli, scrnMsg);

        // button
        scrnAppli.setButtonProperties(BTN_PAGE_PREV[0], BTN_PAGE_PREV[1], BTN_PAGE_PREV[2], 0, null);
        scrnAppli.setButtonProperties(BTN_PAGE_NEXT[0], BTN_PAGE_NEXT[1], BTN_PAGE_NEXT[2], 0, null);
        scrnAppli.setButtonProperties(BTN_APPLY[0], BTN_APPLY[1], BTN_APPLY[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN1[0], BTN_CMN_BTN1[1], BTN_CMN_BTN1[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN3[0], BTN_CMN_BTN3[1], BTN_CMN_BTN3[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN4[0], BTN_CMN_BTN4[1], BTN_CMN_BTN4[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN5[0], BTN_CMN_BTN5[1], BTN_CMN_BTN5[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN6[0], BTN_CMN_BTN6[1], BTN_CMN_BTN6[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN7[0], BTN_CMN_BTN7[1], BTN_CMN_BTN7[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_BTN9[0], BTN_CMN_BTN9[1], BTN_CMN_BTN9[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);

        scrnAppli.setButtonConfirmMsg(BTN_CMN_CLOSE[1], MESSAGE_CD.NZZM0004W.toString(), null, 0);

        initializeCommon(scrnAppli, scrnMsg);
    }

    private static void initializeInputProtect(EZDCommonHandler scrnAppli, NPAL0100BMsg scrnMsg) {

        // release InputProtect
        scrnMsg.setInputProtected(false);
        scrnAppli.setButtonEnabled(BTN_NM.Edit.toString(), true);
        scrnAppli.setButtonEnabled(BTN_NM.Cancel.toString(), true);
    }

    /**
     * Def# 362045 TODO Enable/Disable Submit button
     * @param handler EZDCommonHandler
     * @param enable boolean
     */
    public static void enableSubmitButton(EZDCommonHandler handler, NPAL0100BMsg scrnMsg, boolean enable) {
        
        // Can be submitted if relased (balance qty is NOT 0)
        String newEntFlg = scrnMsg.xxPgFlg_NW.getValue();
        // Flag is Y (balance qty is NOT 0)
        if (ZYPConstant.FLG_ON_Y.equals(newEntFlg)) {
            // Cannot submit when invoice is NOT released
            enable = false;
        }
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], enable);
    }

    /**
     * Def# 362045 TODO Set non editable
     * @param handler EZDCommonHandler
     * @param enable boolean
     */
    public static void afterSubmit(EZDCommonHandler handler, NPAL0100BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(true);
            handler.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 0, null);
            handler.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 0, null);
        }
        // Disable Apply
        handler.setButtonEnabled(BTN_APPLY[0], false);
        // Disable Clear
        handler.setButtonEnabled(BTN_CMN_CLEAR[0], false);
    }

    private static void initializeCommon(EZDCommonHandler scrnAppli, NPAL0100BMsg scrnMsg) {

        // record
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            
            // Def# 362045 TODO
            if( scrnMsg.eventId.getValue().equals(INIT)) {
                // In init: 
                // (1) Serial protected
                // (2) Edit enabled
                // (3) Cancel disabled
                if(!ZYPCommonFunc.hasValue(((NPAL0100_ABMsg)scrnMsg.A.no(i)).serNum_A1) ) {
                    (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(false);
                    scrnAppli.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 0, null);
                    scrnAppli.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 0, null);
                } else {
                    (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(true);
                    scrnAppli.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 0, null);
                    scrnAppli.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 1, null);
                }
            } else {
                if (PROC_FLG_UPDATE.equals(((NPAL0100_ABMsg) scrnMsg.A.no(i)).xxSetFlg_A1.getValue())) {
                    (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(false);
                    scrnAppli.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 1, null);
                    scrnAppli.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 0, null);
                } else if (PROC_FLG_NEW.equals(((NPAL0100_ABMsg) scrnMsg.A.no(i)).xxSetFlg_A1.getValue())) {
                    (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(false);
                    scrnAppli.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 0, null);
                    scrnAppli.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 0, null);
                } else if (ZYPCommonFunc.hasValue(((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1)) {
                    // ex)ser num has data, but dont change.
                    (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(true);
                    scrnAppli.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 0, null);
                    scrnAppli.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 1, null);
                } else {
                    (((NPAL0100_ABMsg) scrnMsg.A.no(i)).serNum_A1).setInputProtected(false);
                    scrnAppli.setButtonProperties(BTN_CANCEL[0], i, BTN_CANCEL[1], BTN_CANCEL[2], 0, null);
                    scrnAppli.setButtonProperties(BTN_EDIT[0], i, BTN_EDIT[1], BTN_EDIT[2], 0, null);
                }
            }
        }
        scrnAppli.setButtonConfirmMsg(BTN_CMN_CLOSE[1], MESSAGE_CD.NZZM0004W.toString(), null, 0);

        // set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_ID_A, scrnMsg.A);
    }

    /**
     * Copy return data.
     * @param scrnMsg NPAL0100BMsg
     * @param argsParams request data array
     */
    public static void otherBusConnectToReturn_NPAL0100Scrn00_CMN_Close(NPAL0100BMsg scrnMsg, Object[] argsParams) {

        Object[] params = argsParams;

        List<EZDBBigDecimalItem> qtyNumList = (ArrayList<EZDBBigDecimalItem>) params[17];
        for (int i = 0; i < qtyNumList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(qtyNumList.get(i), scrnMsg.B.no(i).xxRowNum_B2);
        }
        List<EZDBStringItem> serNumList = (ArrayList<EZDBStringItem>) params[18];
        for (int i = 0; i < serNumList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(serNumList.get(i), scrnMsg.B.no(i).serNum_B1);
        }
        List<EZDBStringItem> procFlgList = (ArrayList<EZDBStringItem>) params[19];
        for (int i = 0; i < procFlgList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(procFlgList.get(i), scrnMsg.B.no(i).xxSetFlg_B1);
        }

        int countSerialNum = 0;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).serNum_B1)) {
                countSerialNum++;
            } else {
                // because serial num is sorted.
                break;
            }
        }

        // set serial num list size.
        ((EZDBBigDecimalItem) params[20]).setValue(countSerialNum);
    }

    private static void debugMsg(String msg) {
        EZDDebugOutput.println(1, msg, null);
    }
}
