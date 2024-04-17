/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7180.common;

import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_APL;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_APR;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_RST;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_NEW_GROUP;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_SEARCH;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.BTN_VIEW_GROUP;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.SCRN_ID_00;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7180.NMAL7180BMsg;
import business.servlet.NMAL7180.NMAL7180_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7180CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#7512
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 *</pre>
 */
public class NMAL7180CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7180BMsg
     */
    public static final void controlScreen(EZDCommonHandler handler, NMAL7180BMsg scrnMsg) {
        controlScreenFields(scrnMsg);
        setCmnBtnProp(handler, scrnMsg);
        setBizBtnProp(handler, scrnMsg);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL7180BMsg
     */
    public static void setCmnBtnProp(EZDCommonHandler handler, NMAL7180BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL7180BMsg
     */
    public static void setBizBtnProp(EZDCommonHandler handler, NMAL7180BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        // QC#7512 2016/04/26 Mod start
        handler.setButtonProperties(BTN_NEW_GROUP[0], BTN_NEW_GROUP[1], BTN_NEW_GROUP[2], 1, null);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_VIEW_GROUP[0], BTN_VIEW_GROUP[1], BTN_VIEW_GROUP[2], 1, null);
//            handler.setButtonProperties(BTN_NEW_GROUP[0], BTN_NEW_GROUP[1], BTN_NEW_GROUP[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_VIEW_GROUP[0], BTN_VIEW_GROUP[1], BTN_VIEW_GROUP[2], 0, null);
//            handler.setButtonProperties(BTN_NEW_GROUP[0], BTN_NEW_GROUP[1], BTN_NEW_GROUP[2], 0, null);
        }
        // QC#7512 2016/04/26 Mod end
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7180BMsg
     */
    public static final void controlScreenFields(NMAL7180BMsg scrnMsg) {
        // Header
        scrnMsg.prcGrpNm.setInputProtected(false);
        scrnMsg.prcGrpDescTxt.setInputProtected(false);
        scrnMsg.prcGrpTpCd.setInputProtected(false);
        scrnMsg.prcGrpTrgtTpCd.setInputProtected(false);
        if (ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtAttrbCd_P.no(0).getValue())
                && ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtAttrbDescTxt_P.no(0).getValue())) {
            scrnMsg.prcGrpTrgtAttrbCd.setInputProtected(false);
        } else {
            scrnMsg.prcGrpTrgtAttrbCd.setInputProtected(true);
        }
        scrnMsg.prcGrpFromTxt.setInputProtected(false);
        scrnMsg.prcGrpThruTxt.setInputProtected(false);
        scrnMsg.actvFlg.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.xxRadioBtn.setInputProtected(false);
        }
        scrnMsg.prcGrpTrxTpCd.setInputProtected(false);
    }

    /**
     * <pre>
     * The header item is clear.
     * </pre>
     * @param scrnMsg NMAL7180BMsg
     */
    public static final void clearHeaderItem(NMAL7180BMsg scrnMsg) {
        // Header
        scrnMsg.prcGrpNm.clear();
        scrnMsg.prcGrpDescTxt.clear();
        scrnMsg.prcGrpTpCd.clear();
        scrnMsg.prcGrpTrgtTpCd.clear();
        scrnMsg.prcGrpTrgtAttrbCd.clear();
        scrnMsg.prcGrpTrgtAttrbCd_P.clear();
        scrnMsg.prcGrpTrgtAttrbDescTxt_P.clear();
        scrnMsg.prcGrpFromTxt.clear();
        scrnMsg.prcGrpThruTxt.clear();
        scrnMsg.actvFlg.clear();
        scrnMsg.effFromDt.clear();
        scrnMsg.effThruDt.clear();
        scrnMsg.prcGrpTrxTpCd.clear();
    }

    /**
     * <pre>
     * The header item is addCheck.
     * </pre>
     * @param scrnMsg NMAL7180BMsg
     */
    public static final void addCheckHeaderItem(NMAL7180BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
        scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrgtTpCd);
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtTpCd)) {
            scrnMsg.prcGrpTrgtAttrbCd.clearErrorInfo();
        }
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrgtAttrbCd);
        scrnMsg.addCheckItem(scrnMsg.prcGrpFromTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpThruTxt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrxTpCd);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7180BMsg
     * @param scrnAMsgAry NMAL7180_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7180BMsg scrnMsg, NMAL7180_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7180BMsg
     * @param scrnAMsgAry NMAL7180_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7180BMsg scrnMsg, NMAL7180_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7180BMsg
     * @param scrnAMsgAry NMAL7180_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7180BMsg scrnMsg, NMAL7180_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }
}
