/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170.common;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_APL;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_APR;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_CLR;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_DEL;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_DWL;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_RJT;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_RST;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_RTN;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_SAV;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_CMN_SUB;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.BTN_VIEW;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_INIT;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_INSERT_ROW;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_ITEM_DETAIL_SUPD_FM;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_ITEM_DETAIL_SUPD_TO;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.FUNC_ID_EDIT;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.HDR_LBL_DATE_STAGED_FROM;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.HDR_LBL_DATE_STAGED_TO;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.HDR_LBL_ITEM_CLASSIFICATION;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.HDR_LBL_ITEM_NUMBER;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.HDR_LBL_ITEM_TYPE;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM0041E;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM0185E;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM0288E;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.SCRN_ID_00;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_DATE_STAGED;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_ITEM_CLASSIFICATION;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_ITEM_DESCRIPTION;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_ITEM_NUMBER;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_ITEM_TYPE;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_SUPERSEDES;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.TBL_LBL_SUPERSEDES_DESCRIPTION;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.ZZSM4300E;

import java.util.List;

import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0170.NMAL0170BMsg;
import business.servlet.NMAL0170.NMAL0170_ABMsg;
import business.servlet.NMAL0170.NMAL0170_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL0170CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima          Create          N/A
 * 2016/01/04   Fujitsu         M.Nakamura       Update          QC#2528
 * 2016/02/23   CITS            S.Tanikawa       Update          QC#2669
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17512
 *</pre>
 */
public class NMAL0170CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null); // MOD S21_NA inactive to active #17512
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg Screen Message
     * @param eventName Event Name
     */
    public static void setCmnBtnProp(S21CommonHandler handler, NMAL0170BMsg scrnMsg, String eventName) {
        boolean isUpdate = hasUpdateFuncId();
        // UPDATE START 2016/03/01 QC#2669
        if (!isUpdate || EVENT_INIT.equals(eventName)) {
            // handler.setButtonProperties(BTN_INSERT_ROW, BTN_INSERT_ROW, BTN_INSERT_ROW, 0, null);
            handler.setButtonProperties(BTN_INSERT_ROW, BTN_INSERT_ROW, BTN_INSERT_ROW, 1, null);
            handler.setButtonProperties(BTN_DELETE_ROW, BTN_DELETE_ROW, BTN_DELETE_ROW, 0, null);
        }
        if (!isUpdate || EVENT_INIT.equals(eventName) || scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(BTN_VIEW, BTN_VIEW, BTN_VIEW, 0, null);
            return;
        } else {
            handler.setButtonProperties(BTN_VIEW, BTN_VIEW, BTN_VIEW, 1, null);
        }
        if (!isUpdate || EVENT_INSERT_ROW.equals(eventName)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        }
        
        // DEL START S21_NA #17512
        // if (scrnMsg.A.getValidCount() != 0 && scrnMsg.A.getValidCount() != scrnMsg.A.length()) {
//        if (scrnMsg.A.getValidCount() < scrnMsg.A.length()) {
//            handler.setButtonProperties(BTN_INSERT_ROW, BTN_INSERT_ROW, BTN_INSERT_ROW, 1, null);
//        } else {
//            handler.setButtonProperties(BTN_INSERT_ROW, BTN_INSERT_ROW, BTN_INSERT_ROW, 0, null);
//        }
        // DEL END S21_NA #17512

        if (scrnMsg.A.getValidCount() != 0) {
            handler.setButtonProperties(BTN_DELETE_ROW, BTN_DELETE_ROW, BTN_DELETE_ROW, 1, null);
        } else {
            handler.setButtonProperties(BTN_DELETE_ROW, BTN_DELETE_ROW, BTN_DELETE_ROW, 0, null);
        }

    }

    /**
     * Has Error for Card Item.
     * - Check all blank at Card Item. : Popup(NMAM0041E) / Message(NMAM0288E)
     * - Check vaild Date Stage from and Thru : Popup(NMAM0185E)
     *
     * @param scrnMsg NMAL0170BMsg
     */
    public static void hasErrorCardItem(NMAL0170BMsg scrnMsg) {
        // Blank Head Item.
        String itemNumber = scrnMsg.supdToMdseCd.getValue();
        String itemType = scrnMsg.mdseItemTpCd_H1.getValue();
        String itemClass = scrnMsg.mdseItemClsTpCd_H1.getValue();
        String dateFrom = scrnMsg.supdRelnStageDt_FM.getValue();
        String dateTo = scrnMsg.supdRelnStageDt_TO.getValue();

        String[] chkItemList = new String[] {itemNumber, itemType, itemClass, dateFrom, dateTo };

        if (hasValues(chkItemList, false)) {
            scrnMsg.supdToMdseCd.setErrorInfo(1, NMAM0041E, new String[] {HDR_LBL_ITEM_NUMBER });
            scrnMsg.mdseItemTpCd_H1.setErrorInfo(1, NMAM0041E, new String[] {HDR_LBL_ITEM_TYPE });
            scrnMsg.mdseItemClsTpCd_H1.setErrorInfo(1, NMAM0041E, new String[] {HDR_LBL_ITEM_CLASSIFICATION });
            scrnMsg.supdRelnStageDt_FM.setErrorInfo(1, NMAM0041E, new String[] {HDR_LBL_DATE_STAGED_FROM });
            scrnMsg.supdRelnStageDt_TO.setErrorInfo(1, NMAM0041E, new String[] {HDR_LBL_DATE_STAGED_TO });
            scrnMsg.setMessageInfo(NMAM0288E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.supdRelnStageDt_FM) && ZYPCommonFunc.hasValue(scrnMsg.supdRelnStageDt_TO) && ZYPDateUtil.compare(scrnMsg.supdRelnStageDt_FM.getValue(), scrnMsg.supdRelnStageDt_TO.getValue()) > 0) {
            // A past date cannot be entered into the "Date Effective From".
            scrnMsg.supdRelnStageDt_FM.setErrorInfo(1, NMAM0185E, new String[] {HDR_LBL_DATE_STAGED_FROM });
            scrnMsg.supdRelnStageDt_TO.setErrorInfo(1, NMAM0185E, new String[] {HDR_LBL_DATE_STAGED_TO });
        }
    }

    /**
     * Check has values.
     * if not equals condition, return false;
     * 
     * @param itemValues item value list.
     * @param condition  condition
     * @return all items equals conditon (true)
     */
    private static boolean hasValues(String[] itemValues, boolean condition) {
        for (String val : itemValues) {
            if (ZYPCommonFunc.hasValue(val) != condition) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set addCheckItem all.
     * @param scrnMsg NMAL0170BMsg
     */
    public static void scrnCheckItem(NMAL0170BMsg scrnMsg) {
        // Item Number
        scrnMsg.addCheckItem(scrnMsg.supdToMdseCd);
        // Item Type
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd_H1);
        // Item Classification
        scrnMsg.addCheckItem(scrnMsg.mdseItemClsTpCd_H1);
        // Date Stage From
        scrnMsg.addCheckItem(scrnMsg.supdRelnStageDt_FM);
        // Date Stage To
        scrnMsg.addCheckItem(scrnMsg.supdRelnStageDt_TO);

        // Detail Check Item
        //lineCheckItem(scrnMsg); // DEL S21_NA #17512
    }

    /**
     * Set addCheckItem for line.
     * @param scrnMsg NMAL0170BMsg
     */
    private static void lineCheckItem(NMAL0170BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL0170_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            // CheckBox
            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A1);
            // Item Number
            scrnMsg.addCheckItem(scrnLineMsg.supdToMdseCd_A1);

            // UPDATE START 2016/02/22 QC#2669
            // Item Description Supersede TO
            scrnMsg.addCheckItem(scrnLineMsg.mdseDescShortTxt_AT);
            // Supersedes
            scrnMsg.addCheckItem(scrnLineMsg.supdFromMdseCd_A1);
            // Item Description Supersede FROM
            scrnMsg.addCheckItem(scrnLineMsg.mdseDescShortTxt_AF);
            // Date Stage
            scrnMsg.addCheckItem(scrnLineMsg.supdRelnStageDt_A1);
            // Item Type
            // scrnMsg.addCheckItem(scrnLineMsg.mdseItemTpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.mdseItemTpNm_A1);
            // Item Classification
            // scrnMsg.addCheckItem(scrnLineMsg.mdseItemClsTpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.mdseItemClsTpNm_A1);
            // UPDATE END 2016/02/22 QC#2669
        }
    }

    /**
     * Set protect input field.
     * @param scrnMsg NMAL0170BMsg
     */
    public static void setScrnLineProtected(NMAL0170BMsg scrnMsg) {
        boolean isUpdate = hasUpdateFuncId();
       // boolean isLineBlock = scrnMsg.A.getValidCount() == scrnMsg.A.length(); // DEL S21_NA #17512

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL0170_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            // UPDATE START 2016/02/22 QC#2669
            // scrnLineMsg.mdseDescShortTxt_A1.setInputProtected(true);
            if (scrnLineMsg.supdRelnStagePk_A1.getValue() == null) {
                scrnLineMsg.supdToMdseCd_A1.setInputProtected(false);
                scrnLineMsg.supdFromMdseCd_A1.setInputProtected(false);
                scrnLineMsg.supdRelnStageDt_A1.setInputProtected(false);
            } else {
                scrnLineMsg.supdToMdseCd_A1.setInputProtected(true);
                scrnLineMsg.supdFromMdseCd_A1.setInputProtected(true);
                scrnLineMsg.supdRelnStageDt_A1.setInputProtected(true);
            }

            scrnLineMsg.mdseDescShortTxt_AT.setInputProtected(true);
            scrnLineMsg.mdseDescShortTxt_AF.setInputProtected(true);
            // ADD START 2016/01/04 QC#2528
            // scrnLineMsg.mdseItemTpCd_A1.setInputProtected(true);
            // scrnLineMsg.mdseItemClsTpCd_A1.setInputProtected(true);
            scrnLineMsg.mdseItemTpNm_A1.setInputProtected(true);
            scrnLineMsg.mdseItemClsTpNm_A1.setInputProtected(true);
            // ADD END 2016/01/04 QC#2528
            // UPDATE END 2016/02/22 QC#2669
            
            // DEL START S21_NA #17512
//            if (!isUpdate || isLineBlock) {
//                scrnLineMsg.xxChkBox_A1.setInputProtected(true);
//            }
            // DEL EMD S21_NA #17512
            if (isUpdate) {
                continue;
            }
            // Read Mode
            scrnLineMsg.supdToMdseCd_A1.setInputProtected(true);
            scrnLineMsg.supdFromMdseCd_A1.setInputProtected(true);
            scrnLineMsg.supdRelnStageDt_A1.setInputProtected(true);
            // DEL START 2016/01/04 QC#2528
            // scrnLineMsg.mdseItemTpCd_A1.setInputProtected(true);
            // scrnLineMsg.mdseItemClsTpCd_A1.setInputProtected(true);
            // DEL END 2016/01/04 QC#2528
            scrnLineMsg.supdRelnStagePk_A1.setInputProtected(true);

        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL0170BMsg
     * @param scrnAMsgAry NMAL0170_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL0170BMsg scrnMsg, NMAL0170_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL0170BMsg
     * @param scrnAMsgAry NMAL0170_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL0170BMsg scrnMsg, NMAL0170_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL0170BMsg
     * @param scrnAMsgAry NMAL0170_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL0170BMsg scrnMsg, NMAL0170_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this
            // application.
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        return funcList.contains(FUNC_ID_EDIT);
    }

    /**
     * Set Name for Message.
     * @param scrnMsg NMAL0170BMsg
     */
    public static void setNameForMessage(NMAL0170BMsg scrnMsg) {

        scrnMsg.supdToMdseCd.setNameForMessage(HDR_LBL_ITEM_NUMBER);
        scrnMsg.mdseItemClsTpCd_H1.setNameForMessage(HDR_LBL_ITEM_TYPE);
        scrnMsg.mdseItemTpCd_H1.setNameForMessage(HDR_LBL_ITEM_CLASSIFICATION);
        scrnMsg.supdRelnStageDt_FM.setNameForMessage(HDR_LBL_DATE_STAGED_FROM);
        scrnMsg.supdRelnStageDt_TO.setNameForMessage(HDR_LBL_DATE_STAGED_TO);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL0170_ABMsg lineMsg = scrnMsg.A.no(i);
            // update start 2016/02/22 QC#2669
            lineMsg.supdToMdseCd_A1.setNameForMessage(TBL_LBL_ITEM_NUMBER);
            // lineMsg.mdseDescShortTxt_A1.setNameForMessage(TBL_LBL_ITEM_DESCRIPTION);
            lineMsg.mdseDescShortTxt_AT.setNameForMessage(TBL_LBL_ITEM_DESCRIPTION);
            lineMsg.supdFromMdseCd_A1.setNameForMessage(TBL_LBL_SUPERSEDES);
            lineMsg.mdseDescShortTxt_AF.setNameForMessage(TBL_LBL_SUPERSEDES_DESCRIPTION);
            lineMsg.supdRelnStageDt_A1.setNameForMessage(TBL_LBL_DATE_STAGED);
            // lineMsg.mdseItemTpCd_A1.setNameForMessage(TBL_LBL_ITEM_TYPE);
            lineMsg.mdseItemTpNm_A1.setNameForMessage(TBL_LBL_ITEM_TYPE);
            // lineMsg.mdseItemClsTpCd_A1.setNameForMessage(TBL_LBL_ITEM_CLASSIFICATION);
            lineMsg.mdseItemClsTpNm_A1.setNameForMessage(TBL_LBL_ITEM_CLASSIFICATION);
            // update end 2016/02/22 QC#2669
        }
    }

    /**
     * setMdseInfoParam
     * @param scrnMsg NMAL0170BMsg
     * @param selectedNum int
     * @return Object[]
     */
    public static Object[] setMdseInfoParam(NMAL0170BMsg scrnMsg, int selectedNum) {

        // Parameter Clear
        scrnMsg.P.clear();

        if (EVENT_ITEM_DETAIL_SUPD_FM.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.A.no(selectedNum).supdFromMdseCd_A1);
        } else if (EVENT_ITEM_DETAIL_SUPD_TO.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.A.no(selectedNum).supdToMdseCd_A1);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, "10");

        // Parameter Set
        Object[] param = new Object[10];
        param[0] = scrnMsg.P.no(0).xxPopPrm_P;
        param[1] = scrnMsg.P.no(1).xxPopPrm_P;
        param[2] = scrnMsg.P.no(2).xxPopPrm_P;
        param[3] = scrnMsg.P.no(3).xxPopPrm_P;
        param[4] = scrnMsg.P.no(4).xxPopPrm_P;
        param[5] = scrnMsg.P.no(5).xxPopPrm_P;
        param[6] = scrnMsg.P.no(6).xxPopPrm_P;
        param[7] = scrnMsg.P.no(7).xxPopPrm_P;
        param[8] = scrnMsg.P.no(8).xxPopPrm_P;
        param[9] = scrnMsg.P.no(9).xxPopPrm_P;

        return param;
    }

    // ADD START 2016/02/24 QC#2669
    /**
     * setMdseInfoRetutnParam
     * @param scrnMsg NMAL0170BMsg
     * @param selectedNum int
     */
    public static void setMdseInfoRetutnParam(NMAL0170BMsg scrnMsg, int selectedNum) {
        boolean isUpdate = hasUpdateFuncId();

        if (scrnMsg.A.no(selectedNum).supdRelnStagePk_A1.getValue() == null && isUpdate) {
            if (EVENT_ITEM_DETAIL_SUPD_TO.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedNum).supdToMdseCd_A1, scrnMsg.P.no(0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedNum).mdseDescShortTxt_AT, scrnMsg.P.no(1).xxPopPrm_P);
                // ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedNum).mdseItemTpNm_A1, scrnMsg.P.no(2).xxPopPrm_P);
                // ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedNum).mdseItemClsTpNm_A1, scrnMsg.P.no(3).xxPopPrm_P);
            } else if (EVENT_ITEM_DETAIL_SUPD_FM.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedNum).supdFromMdseCd_A1, scrnMsg.P.no(0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedNum).mdseDescShortTxt_AF, scrnMsg.P.no(1).xxPopPrm_P);
            }
        }
    }

    /**
     * check input when submitted
     * @param ctx EZDApplicationContext
     * @param scrnMsg NMAL0170BMsg
     */
    public static void checkInput(EZDApplicationContext ctx, NMAL0170BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.A);
        if (scrnMsg.A.getValidCount() != 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (scrnMsg.A.no(i).supdRelnStagePk_A1.getValue() == null) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).supdToMdseCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).supdFromMdseCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).supdRelnStageDt_A1);
                }
            }
            scrnMsg.putErrorScreen();

            String msgCode = scrnMsg.getMessageCode();
            if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
                throw new EZDFieldErrorException();
            }
        }
    }
    // ADD END 2016/02/24 QC#2669
}
