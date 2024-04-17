/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770.common;

import static business.servlet.NMAL6770.constant.NMAL6770Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.BTN_10_CLO_GUARD;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.BTN_10_CLO_LABEL;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.BTN_10_CLO_NAME;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.NMAM0288E;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.NMAM8667E;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.WILDCARD;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6770.NMAL6770BMsg;
import business.servlet.NMAL6770.constant.NMAL6770Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2015/10/01   Fujitsu         C.Tanaka        Update          CSA
 * 2017/02/21   Fujitsu         K.Ishizuka      Update          QC#17610
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2018/02/28   Fujitsu         A.Kosai         Update          QC#21075
 *</pre>
 */
public class NMAL6770CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6770BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6770BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6770BMsg
     */
    public static void initButton(EZDCommonHandler handler, NMAL6770BMsg scrnMsg) {
        handler.setButtonEnabled(BTN_08_CLE_NAME, true);
        
        // QC#7781
        if (NMAL6770Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            if (scrnMsg.B.getValidCount() == scrnMsg.B.length()) {
                handler.setButtonEnabled(NMAL6770Constant.BTN_ADD_SEL, false);
            } else {
                handler.setButtonEnabled(NMAL6770Constant.BTN_ADD_SEL, true);
            }
            if (scrnMsg.B.getValidCount() == 0) {
                handler.setButtonEnabled(NMAL6770Constant.BTN_DEL_SEL, false);
            } else {
                handler.setButtonEnabled(NMAL6770Constant.BTN_DEL_SEL, true);
            }
        }
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
     * @param scrnMsg NMAL6770BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6770BMsg scrnMsg) {
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6770BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6770BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyByItemNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPsnTtlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacPsnFirstNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacPsnLastNm_A1.setInputProtected(true);
            // Mod Start 2017/12/06 QC#21897
            //scrnMsg.A.no(i).ctacTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacTpDescTxt_A1.setInputProtected(true);
            // Mod End 2017/12/06 QC#21897
            scrnMsg.A.no(i).dsCtacPsnExtnNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A2.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A3.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A4.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A5.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacPsnCmntTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).billToCustCd_A1.setInputProtected(true);
            // 2018/02/28 S21_NA#21075 Add Start
            scrnMsg.A.no(i).shipToCustCd_A1.setInputProtected(true);
            // 2018/02/28 S21_NA#21075 Add End
        }
        
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).dsAcctNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsAcctNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).locNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxDplyByItemNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPsnTtlNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).ctacPsnFirstNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).ctacPsnLastNm_B1.setInputProtected(true);
            // Mod Start 2017/12/06 QC#21897
            //scrnMsg.B.no(i).ctacTpNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).ctacTpDescTxt_B1.setInputProtected(true);
            // Mod End 2017/12/06 QC#21897
            scrnMsg.B.no(i).dsCtacPsnExtnNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntValTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntValTxt_B2.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntValTxt_B3.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntValTxt_B4.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntValTxt_B5.setInputProtected(true);
            scrnMsg.B.no(i).dsCtacPntTpNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).ctacPsnCmntTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).billToCustCd_B1.setInputProtected(true);
            // 2018/02/28 S21_NA#21075 Add Start
            scrnMsg.B.no(i).shipToCustCd_B1.setInputProtected(true);
            // 2018/02/28 S21_NA#21075 Add End
        }
    }
    
 // ADD START S21_NA #17610
    /**
     * check Null Field
     * @param scrnMsg NMAL6790BMsg
     */
    public static void checkMandatorySearchCondition(NMAL6770BMsg scrnMsg) {
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
        list.add(scrnMsg.billToCustCd_H1);
        // 2018/02/28 S21_NA#21075 Add Start
        list.add(scrnMsg.shipToCustCd_H1);
        // 2018/02/28 S21_NA#21075 Add End

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
    public static void checkWildCardOnly(NMAL6770BMsg scrnMsg) {
        
        List<EZDBStringItem> list = new ArrayList<EZDBStringItem>();
        list.add(scrnMsg.dsCtacPntValTxt_H2);
        list.add(scrnMsg.dsAcctNm_H1);
        list.add(scrnMsg.ctacPsnFirstNm_H1);
        list.add(scrnMsg.dsCtacPntValTxt_H1);
        list.add(scrnMsg.dsAcctNum_H1);
        list.add(scrnMsg.ctacPsnLastNm_H1);
        list.add(scrnMsg.locNum_H1);
        list.add(scrnMsg.dsLocNm_H1);
        list.add(scrnMsg.billToCustCd_H1);
        // 2018/02/28 S21_NA#21075 Add Start
        list.add(scrnMsg.shipToCustCd_H1);
        // 2018/02/28 S21_NA#21075 Add End

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
