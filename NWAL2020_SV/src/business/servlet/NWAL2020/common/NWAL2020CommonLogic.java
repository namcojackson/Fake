/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2020.common;

import static business.servlet.NWAL2020.constant.NWAL2020Constant.AUTH_UPDATE;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.BIZ_ID;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.BTN_CMN_CLO;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.BTN_INSERT_NEW_CARDS;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.MODE_READ_ONLY;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.SCRN_ID_00;

import java.util.List;

import parts.common.EZDBMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2020.NWAL2020BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL2020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLO[0], BTN_CMN_CLO[1], BTN_CMN_CLO[2], 1, null);
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     */
    public static void protectCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLO[0], BTN_CMN_CLO[1], BTN_CMN_CLO[2], 1, null);
    }

    /**
     * Set Credit Card Area properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2020BMsg
     */
    public static void setCrCardProp(S21CommonHandler handler, NWAL2020BMsg scrnMsg) {

        List<String> funcList = getFunctionList();

        if (MODE_READ_ONLY.equals(scrnMsg.xxModeCd_LK.getValue())) {
            handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, false);

        } else if (funcList.contains(AUTH_UPDATE)) {
            handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, true);

        }
    }

    /**
     * Table has an id attribute of the argument row background color, change the argument groupRows
     * every alternate line groups.
     * @param scrnMsg NWAL2020BMsg
     */
    public static void setBgColor(NWAL2020BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * validateCreditCard
     * @param scrnMsg Screen Msg
     */
    public static void validateCreditCard(NWAL2020BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.crCardCustRefNum_C);
        scrnMsg.addCheckItem(scrnMsg.crCardExprYrMth_C);
        scrnMsg.addCheckItem(scrnMsg.crCardHldNm_C);
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_C);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_C);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_C);
        scrnMsg.addCheckItem(scrnMsg.postCd_C);
        scrnMsg.addCheckItem(scrnMsg.stCd_C);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_C);

        scrnMsg.putErrorScreen();
    }

    /**
     * Set Error Field
     * @param handler Event Handler
     * @param scrnMsg Screen Msg
     */
    public static void setErrorField(S21CommonHandler handler, NWAL2020BMsg scrnMsg) {

        NWAL2020CommonLogic.protectCmnBtnProp(handler);
        handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, false);
    }

    /**
     * get Function List
     * @return Function List
     */
    private static List<String> getFunctionList() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate this screen(" + BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        return funcList;
    }

    /**
     * createCreditCardField
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param bMsg EZDBMsg
     */
    public static void createCreditCardField(S21CommonHandler handler, EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxRqstNumSrchTxt_T)) {
            scrnMsg.setMessageInfo("ZZSM0001E", new String[] {scrnMsg.xxRqstNumSrchTxt_T.getValue() });
        }
        scrnMsg.xxRqstNumSrchTxt_T.clear();

        NWAL2020TceppsRequestResponce tr = new NWAL2020TceppsRequestResponce();
        tr.loadRequestParams(scrnMsg, ctx.getHttpServletRequest().getRequestURL().toString());

        setCrCardProp(handler, scrnMsg);
    }

    /**
     * Clear search criteria fields
     * @param scrnMsg NWAL2020BMsg
     */
    public static void clearSrchCrit(NWAL2020BMsg scrnMsg) {
        scrnMsg.crCardCustRefNum_C.clear();
        scrnMsg.crCardExprYrMth_C.clear();
        scrnMsg.crCardHldNm_C.clear();
        scrnMsg.firstLineAddr_C.clear();
        scrnMsg.scdLineAddr_C.clear();
        scrnMsg.ctyAddr_C.clear();
        scrnMsg.postCd_C.clear();
        scrnMsg.stCd_C.clear();
        scrnMsg.ctryCd_C.clear();
    }
}
