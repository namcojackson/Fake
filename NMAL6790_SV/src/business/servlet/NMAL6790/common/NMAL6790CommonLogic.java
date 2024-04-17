/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790.common;

import static business.servlet.NMAL6790.constant.NMAL6790Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.BTN_10_CLO_GUARD;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.BTN_10_CLO_LABEL;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.BTN_10_CLO_NAME;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.NMAM0288E;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.NMAM8667E;
import static business.servlet.NMAL6790.constant.NMAL6790Constant.WILDCARD;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6790.NMAL6790BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/04/18   Hitachi         T.Tomita        Update          QC#6218
 * 2017/02/21   Fujitsu         K.Ishizuka      Update          QC#17610
 *</pre>
 */
public class NMAL6790CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6790BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6790BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(BTN_08_CLE_NAME, true);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLO_NAME, BTN_10_CLO_GUARD, BTN_10_CLO_LABEL, 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6790BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6790BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
        controlScreenContactPointFields(handler, scrnMsg);
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6790BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NMAL6790BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_H1.getValue())) {

            scrnMsg.ctacTpCd_H1.setInputProtected(true);
            scrnMsg.dsCtacPsnTtlCd_H1.setInputProtected(true);
            scrnMsg.dsCtacPsnDeptCd_H1.setInputProtected(true);
            scrnMsg.svcCtacTpCd_H1.setInputProtected(true);
            scrnMsg.xxChkBox_H1.setInputProtected(true);
            scrnMsg.xxChkBox_H2.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);
            scrnMsg.serNum_H1.setInputProtected(true);
            scrnMsg.dsCtacPntValTxt_H2.setInputProtected(true);
            scrnMsg.ctacPsnFirstNm_H1.setInputProtected(true);
            scrnMsg.ctacPsnLastNm_H1.setInputProtected(true);
            scrnMsg.dsLocNm_H1.setInputProtected(true);
            scrnMsg.dsCtacPntValTxt_H1.setInputProtected(true);
            scrnMsg.xxDplyByItemNm_H1.setInputProtected(true);
        }
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6790BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6790BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ctacPsnLastNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacPsnFirstNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsLocNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyByItemNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPsnDeptNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPsnTtlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacPsnCmntTxt_A1.setInputProtected(true);
        }
    }

    /**
     * controlScreenContactPointFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6790BMsg
     */
    private static final void controlScreenContactPointFields(EZDCommonHandler handler, NMAL6790BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            // add start 2016/04/18 CSA Defect#6218
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_B2.getValue())) {
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
            } else {
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
            }
            // add end 2016/04/18 CSA Defect#6218
            scrnMsg.B.no(i).dsCtacPntTpNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntValTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPsnExtnNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B2.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B3.setInputProtected(true);
        }
    }
    
     // ADD START S21_NA #17610
    /**
     * check Null Field
     * @param scrnMsg NMAL6790BMsg
     */
    public static void checkMandatorySearchCondition(NMAL6790BMsg scrnMsg) {
        List<EZDBItem> list = new ArrayList<EZDBItem>();
        list.add(scrnMsg.ctacTpCd_H1);
        list.add(scrnMsg.dsCtacPntValTxt_H2);
        list.add(scrnMsg.dsAcctNm_H1);
        list.add(scrnMsg.ctacPsnFirstNm_H1);
        list.add(scrnMsg.dsCtacPntValTxt_H1);
        list.add(scrnMsg.dsAcctNum_H1);
        list.add(scrnMsg.ctacPsnLastNm_H1);
        list.add(scrnMsg.dsCtacPsnTtlCd_H1);
        list.add(scrnMsg.locNum_H1);
        list.add(scrnMsg.dsLocNm_H1);
        list.add(scrnMsg.dsCtacPsnDeptCd_H1);
        list.add(scrnMsg.serNum_H1);
        list.add(scrnMsg.xxDplyByItemNm_H1);
        list.add(scrnMsg.svcCtacTpCd_H1);

        boolean hasSearchCondition = false;
        for (EZDBItem item : list) {
            if (ZYPCommonFunc.hasValue(item)) {
                hasSearchCondition = true;
                break;
            }
        }
        if (!hasSearchCondition) {
            for (EZDBItem item : list) {
                item.setErrorInfo(1, NMAM0288E);
                scrnMsg.addCheckItem(item);
            }
            scrnMsg.putErrorScreen();
        }
    }
    
    /**
     * check fields input only wild card
     * @param scrnMsg NMAL6710BMsg
     */
    public static void checkWildCardOnly(NMAL6790BMsg scrnMsg) {
        
        List<EZDBStringItem> list = new ArrayList<EZDBStringItem>();
        list.add(scrnMsg.dsCtacPntValTxt_H2);
        list.add(scrnMsg.dsAcctNm_H1);
        list.add(scrnMsg.ctacPsnFirstNm_H1);
        list.add(scrnMsg.dsCtacPntValTxt_H1);
        list.add(scrnMsg.dsAcctNum_H1);
        list.add(scrnMsg.ctacPsnLastNm_H1);
        list.add(scrnMsg.locNum_H1);
        list.add(scrnMsg.dsLocNm_H1);
        list.add(scrnMsg.serNum_H1);
        list.add(scrnMsg.xxDplyByItemNm_H1);

        boolean hasWildCardOnly = false;
        for (EZDBStringItem item : list) {
            if (WILDCARD.equals(item.getValue())) {
                item.setErrorInfo(1, NMAM8667E);
                scrnMsg.addCheckItem(item);
                hasWildCardOnly = true;
            }
        }

        if (hasWildCardOnly) {
            scrnMsg.putErrorScreen();
        }
    }
    // ADD END S21_NA QC#17610

}
