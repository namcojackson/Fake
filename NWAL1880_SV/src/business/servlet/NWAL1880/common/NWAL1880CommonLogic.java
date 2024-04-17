/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880.common;

import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_APL;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_APR;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_RST;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.BTN_CMN_RTN;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.SCRN_ID_00;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.ALL_REF_AUTH;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.EVENT_NAME_SERACH_ORDER;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.REF_ONLY_SELF_RGTN_AUTH;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.REF_ONLY_TEAM_AUTH;
import business.blap.NWAL1880.NWAL1880CMsg;
import business.servlet.NWAL1880.NWAL1880BMsg;
import business.servlet.NWAL1880.NWAL1880_ABMsgArray;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1880CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1880CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1880BMsg
     * @param scrnAMsgAry NWAL1880_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1880BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1880BMsg
     * @param scrnAMsgAry NWAL1880_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1880BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1880BMsg
     * @param scrnAMsgAry NWAL1880_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1880BMsg scrnMsg, NWAL1880_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * initialize value of GUI on NWAL1880Scrn00.
     * @param scrnMsg NWAL1880BMsg
     */
    public static void initGuiValueScrn00(NWAL1880BMsg scrnMsg) {

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Order Team
        //++++++++++++++++++++++++++++++++++++++++
        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            funcList.add(scrnMsg.H.no(i).xxFuncId.getValue());
        }
        if (funcList.contains(ALL_REF_AUTH)) {
            //Team
            scrnMsg.xxOrdTeamSrchTxt.clear();
            //Zone
            scrnMsg.xxOrdZnSrchTxt.clear();
            //Created By
            scrnMsg.xxCratByUsrSrchTxt.clear();
        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {
            //Created By
            scrnMsg.xxCratByUsrSrchTxt.clear();
        }

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Display Option
        //++++++++++++++++++++++++++++++++++++++++
        // Display by label
        scrnMsg.dplyByItemLbNm.clear();
        // Display By
        scrnMsg.xxScrItem50Txt.clear();

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);
        ZYPTableUtil.clear(scrnMsg.E);
        ZYPTableUtil.clear(scrnMsg.F);
        ZYPTableUtil.clear(scrnMsg.G);

    }

    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1880BMsg
     */
    public static void setProtectByAuthority(S21CommonHandler handler, NWAL1880BMsg scrnMsg) {

        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            funcList.add(scrnMsg.H.no(i).xxFuncId.getValue());
        }

        // Team/Zone
        if (funcList.contains(ALL_REF_AUTH)) {

            handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, true);
            scrnMsg.xxOrdTeamSrchTxt_LK.setInputProtected(false);
            scrnMsg.xxOrdZnSrchTxt_LK.setInputProtected(false);
            scrnMsg.xxCratByUsrSrchTxt_LK.setInputProtected(false);
            scrnMsg.xxOrdTeamSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxOrdZnSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxCratByUsrSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {

            scrnMsg.xxOrdTeamSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt.setInputProtected(true);
            scrnMsg.xxCratByUsrSrchTxt.setInputProtected(false);
            scrnMsg.xxOrdTeamSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxCratByUsrSrchTxt_LK.setInputProtected(false);
            scrnMsg.xxOrdTeamSrchTxt_LK.clear();
            scrnMsg.xxOrdZnSrchTxt_LK.clear();
            scrnMsg.xxCratByUsrSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

            if (hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, true);
            } else {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, false);
            }

        } else if (funcList.contains(REF_ONLY_SELF_RGTN_AUTH)) {

            scrnMsg.xxOrdTeamSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt.setInputProtected(true);
            scrnMsg.xxCratByUsrSrchTxt.setInputProtected(true);
            scrnMsg.xxCratByUsrSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdTeamSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxCratByUsrSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdTeamSrchTxt_LK.clear();
            scrnMsg.xxOrdZnSrchTxt_LK.clear();
            scrnMsg.xxCratByUsrSrchTxt_LK.clear();

            if (hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, true);
            } else {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, false);
            }

        } else {
            handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, false);
        }

    }

    /**
     * setAppFracDigit
     * @param scrnMsg NWAL1880BMsg
     */
    public static void setAppFracDigit(NWAL1880BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).xxBllgHldAmt_E1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1880BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.xxOrdTeamSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdZnSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCratByUsrSrchTxt);

    }

    /**
     * check error in EZDCMsg. Does EZDCMsg have Error?
     * @param cMsg NWAL1880CMsg
     * @return true/has Error, false/don't have Error.
     */
    public static boolean hasMsgError(NWAL1880CMsg cMsg) {
        return cMsg != null && "E".equals(cMsg.getMessageKind());
    }

    /**
     * initialize attribute of GUI on NWAL1880Scrn00.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1880BMsg
     */
    public static void initGuiAttrScrnRslt(S21CommonHandler handler, NWAL1880BMsg scrnMsg) {

        // ++++++++++++++++++++++++++++++
        // + Result Area
        // ++++++++++++++++++++++++++++++
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxDplyByItemNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxEntCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCrHldCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPrftCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxVldCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAllOtherCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBookCnt_A1.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).xxDplyByItemNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxInvdCnt_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxInvdCnt_B2.setInputProtected(true);
            scrnMsg.B.no(i).xxInvdCnt_B3.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).xxDplyByItemNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C2.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C3.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C4.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C5.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C6.setInputProtected(true);
            scrnMsg.C.no(i).xxAgingCnt_C7.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxDplyByItemNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).xxCrRebilCnt_D1.setInputProtected(true);
            scrnMsg.D.no(i).xxCrRebilCnt_D2.setInputProtected(true);
            scrnMsg.D.no(i).xxCrRebilCnt_D3.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.E.no(i).xxDplyByItemNm_E1.setInputProtected(true);
            scrnMsg.E.no(i).xxBllgHldCnt_E1.setInputProtected(true);
            scrnMsg.E.no(i).xxBllgHldAmt_E1.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            scrnMsg.F.no(i).xxDplyByItemNm_F1.setInputProtected(true);
            scrnMsg.F.no(i).xxRvwDealConfigCnt_F1.setInputProtected(true);
            scrnMsg.F.no(i).xxRvwSomCnt_F1.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            scrnMsg.G.no(i).xxDplyByItemNm_G1.setInputProtected(true);
            scrnMsg.G.no(i).xxAcptDealConfigCnt_G1.setInputProtected(true);
            scrnMsg.G.no(i).xxAcptSomCnt_G1.setInputProtected(true);
        }
    }

}
