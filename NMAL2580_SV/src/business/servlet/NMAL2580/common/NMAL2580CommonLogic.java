/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2580.common;

import static business.servlet.NMAL2580.constant.NMAL2580Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2580.constant.NMAL2580Constant.BTN_CMN_CLS;
import static business.servlet.NMAL2580.constant.NMAL2580Constant.BTN_PAGE_NEXT;
import static business.servlet.NMAL2580.constant.NMAL2580Constant.BTN_PAGE_PREV;
import static business.servlet.NMAL2580.constant.NMAL2580Constant.BTN_SEARCH;
import static business.servlet.NMAL2580.constant.NMAL2580Constant.SCRN_ID_00;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2580.NMAL2580BMsg;
import business.servlet.NMAL2580.NMAL2580_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL2580CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2017/12/08   Fujitsu         N.Sugiura       Update          QC#21692
 *</pre>
 */
public class NMAL2580CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // TODO [Template] if popup screen then use below.
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2580BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2580BMsg scrnMsg) {

        int pageNum = scrnMsg.xxPageShowToNum.getValueInt();
        int allPageNum = scrnMsg.xxPageShowOfNum.getValueInt();

        setProtectButton(handler, pageNum, allPageNum);
        scrnProtectHeader(scrnMsg);
        scrnProtectDetail(scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param pageNum int
     * @param allPageNum int
     */
    public static void setProtectButton(EZDCommonHandler handler, int pageNum, int allPageNum) {

        handler.setButtonEnabled(BTN_SEARCH, true);

        if (pageNum > 1) {
            handler.setButtonEnabled(BTN_PAGE_PREV, true);
        } else {
            handler.setButtonEnabled(BTN_PAGE_PREV, false);
        }
        if (pageNum < allPageNum) {
            handler.setButtonEnabled(BTN_PAGE_NEXT, true);
        } else {
            handler.setButtonEnabled(BTN_PAGE_NEXT, false);
        }
    }

    /**
     * controlScreenFields
     * @param scrnMsg NMAL2580BMsg
     */
    public static final void scrnProtectHeader(NMAL2580BMsg scrnMsg) {

        scrnMsg.trtyUpdRqstHdrPk.setInputProtected(false);
        scrnMsg.rqstUsrId.setInputProtected(false);
        scrnMsg.fill103Txt.setInputProtected(false);
        scrnMsg.rqstCratSysTpCd.setInputProtected(false);
        scrnMsg.rqstRsltTpCd.setInputProtected(false);
        scrnMsg.rqstRsltCmntTxt.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effToDt.setInputProtected(false);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

    }

    /**
     * scrnProtect
     * @param scrnMsg NMAL2580BMsg
     */
    public static void scrnProtectDetail(NMAL2580BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).trtyUpdRqstHdrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).rqstUsrId_A.setInputProtected(true);
            scrnMsg.A.no(i).fill103Txt_A.setInputProtected(true);
            scrnMsg.A.no(i).rqstDtTmTsTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).rqstCratSysTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).rqstRsltTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).rqstRsltCmntTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).massUpdRsnCmntTxt_A.setInputProtected(true); // Add 2017/12/08 QC#21692
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2580BMsg
     * @param scrnAMsgAry NMAL2580_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2580BMsg scrnMsg, NMAL2580_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2580BMsg
     * @param scrnAMsgAry NMAL2580_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2580BMsg scrnMsg, NMAL2580_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL2580BMsg
     * @param scrnAMsgAry NMAL2580_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2580BMsg scrnMsg, NMAL2580_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

}
