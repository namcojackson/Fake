/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760.common;

import static business.servlet.NMAL6760.constant.NMAL6760Constant.BTN_08_CLR_GUARD;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.BTN_08_CLR_LABEL;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.BTN_08_CLR_NAME;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.BTN_10_CLS_GUARD;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.BTN_10_CLS_LABEL;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.BTN_10_CLS_NAME;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.NMAM0288E;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.NMAM8667E;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.SCRN_ID_00;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.WILDCARD;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6760.NMAL6760BMsg;
import business.servlet.NMAL6760.NMAL6760_CBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL6760CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/06   Fujitsu         N.Sugiura       Update          QC#6248
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19574
 *</pre>
 */
public class NMAL6760CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_08_CLR_NAME, BTN_08_CLR_GUARD, BTN_08_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);

    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL6760BMsg
     * @param scrnCMsgAry NMAL6760_CBMsgArray
     * @param tblId String id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL6760BMsg scrnMsg, NMAL6760_CBMsgArray scrnCMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnCMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL6760BMsg
     * @param scrnCMsgAry NMAL6760_CBMsgArray
     * @param tblId String id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL6760BMsg scrnMsg, NMAL6760_CBMsgArray scrnCMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnCMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnCMsgAry, grpRows);
    }

    /**
     * Control Detail Screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6710BMsg
     */
    public static void controlDetailScreenFields(EZDCommonHandler handler, NMAL6760BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).dsAcctRelnTpNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).dsAcctNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxAllLineAddr_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxYesNoCd_C1.setInputProtected(true);
            scrnMsg.C.no(i).dsAcctTpNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxCtlNm_C1.setInputProtected(true);
        }

        scrnMsg.xxChkBox_02.setInputProtected(!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_02.getValue()));
        scrnMsg.xxChkBox_03.setInputProtected(!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_03.getValue()));
        scrnMsg.dsAcctTpCd_DP.setInputProtected(!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_CT.getValue()));

        scrnMsg.xxAcctSrchDplyRelnCd_D4.setInputProtected(ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_RA.getValue()));
        scrnMsg.dsAcctNm_RT.setInputProtected(ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_RA.getValue()));
        scrnMsg.dsAcctNum_RT.setInputProtected(ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_RA.getValue()));
        scrnMsg.xxAllLineAddr_RT.setInputProtected(ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_RA.getValue()));
    }
    /**
     * Clear search criteria fields
     * @param scrnMsg NMAL6760BMsg
     */
    public static void clearSrchCrit(NMAL6760BMsg scrnMsg) {

        scrnMsg.dsAcctNm_01.clear();

        scrnMsg.dsLocNm_01.clear();
        scrnMsg.locNum_01.clear();
        scrnMsg.xxAllLineAddr_01.clear();
        scrnMsg.ctyAddr_01.clear();
        scrnMsg.stCd_DP.clear();
        scrnMsg.postCd_01.clear();
        scrnMsg.dbaNm_01.clear();
        scrnMsg.dsAcctLegalNm_01.clear();
        scrnMsg.dsAcctGrpCd_DP.clear();
        scrnMsg.dsAcctGrpDescTxt_DP.clear();
        scrnMsg.dsAcctClsCd_DP.clear();
        scrnMsg.dsAcctDunsNum_01.clear();
        scrnMsg.dsUltDunsNum_01.clear();
        scrnMsg.dsCustSicDescTxt_01.clear();
        scrnMsg.ctacPsnLastNm_01.clear();
        scrnMsg.ctacPsnFirstNm_01.clear();
        scrnMsg.ctacPsnTelNum_01.clear();
        // START 2017/08/07 T.Tsuchida [QC#19574,ADD]
        scrnMsg.ctacPsnEmlAddr_01.clear();
        // END 2017/08/07 T.Tsuchida [QC#19574,ADD]
        scrnMsg.dsXrefAcctTpCd_DP.clear();
        scrnMsg.dsXrefAcctCd_01.clear();
        scrnMsg.dsXtrnlRefTxt_01.clear();
        scrnMsg.dsAcctItrlFlg_C1.clear();
        scrnMsg.xxChkBox_01.clear();
        scrnMsg.xxAcctSrchDplyRelnCd_D4.clear();
        scrnMsg.dsAcctNum_RT.clear();
        scrnMsg.dsAcctNm_RT.clear();
        scrnMsg.xxAllLineAddr_RT.clear();
        scrnMsg.billToCustCd_01.clear();
        scrnMsg.shipToCustCd_01.clear();

        if (!(ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_HC) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_HC.getValue()))) {
            scrnMsg.xxAcctSrchDplyHrchCd_D3.clear();
        }
        if (!(ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_AC) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_AC.getValue()))) {
            scrnMsg.dsAcctNum_01.clear();
        }
        if (!(ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_SC) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_SC.getValue()))) {
            scrnMsg.xxAcctSrchStsCd_D2.clear();
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_02.getValue())) {
            scrnMsg.xxChkBox_02.clear();
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_03.getValue())) {
            scrnMsg.xxChkBox_03.clear();
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_CT.getValue())) {
            scrnMsg.dsAcctTpCd_DP.clear();
        }
    }

    /**
     * 
     * @param scrnMsg NMAL6710BMsg
     */
    public static void checkMandatorySearchCondition(NMAL6760BMsg scrnMsg) {
        List<EZDBItem> list = new ArrayList<EZDBItem>();
        list.add(scrnMsg.dsAcctNm_01);
        list.add(scrnMsg.xxAllLineAddr_01);
        list.add(scrnMsg.ctyAddr_01);
        list.add(scrnMsg.stCd_DP);
        list.add(scrnMsg.postCd_01);
        list.add(scrnMsg.dsAcctNum_01);
        list.add(scrnMsg.locNum_01);
        list.add(scrnMsg.dbaNm_01);
        list.add(scrnMsg.dsAcctLegalNm_01);
        list.add(scrnMsg.dsAcctGrpCd_DP);
        list.add(scrnMsg.dsAcctClsCd_DP);
        list.add(scrnMsg.dsLocNm_01);

        list.add(scrnMsg.dsAcctDunsNum_01);
        list.add(scrnMsg.dsUltDunsNum_01);
        list.add(scrnMsg.dsCustSicDescTxt_01);
        list.add(scrnMsg.ctacPsnLastNm_01);
        list.add(scrnMsg.ctacPsnFirstNm_01);
        list.add(scrnMsg.ctacPsnTelNum_01);
        // START 2017/08/07 T.Tsuchida [QC#19574,ADD]
        list.add(scrnMsg.ctacPsnEmlAddr_01);
        // END 2017/08/07 T.Tsuchida [QC#19574,ADD]
        list.add(scrnMsg.dsXrefAcctTpCd_DP);
        list.add(scrnMsg.dsXrefAcctCd_01);
        list.add(scrnMsg.billToCustCd_01);
        list.add(scrnMsg.shipToCustCd_01);

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
    public static void checkWildCardOnly(NMAL6760BMsg scrnMsg) {
        List<EZDBStringItem> list = new ArrayList<EZDBStringItem>();
        list.add(scrnMsg.dsAcctNm_01);
        list.add(scrnMsg.xxAllLineAddr_01);
        list.add(scrnMsg.ctyAddr_01);
        list.add(scrnMsg.postCd_01);
        list.add(scrnMsg.dsAcctNum_01);
        list.add(scrnMsg.locNum_01);
        list.add(scrnMsg.dbaNm_01);
        list.add(scrnMsg.dsAcctLegalNm_01);
        list.add(scrnMsg.dsAcctGrpCd_DP);
        list.add(scrnMsg.dsLocNm_01);
        list.add(scrnMsg.dsAcctDunsNum_01);
        list.add(scrnMsg.dsUltDunsNum_01);
        list.add(scrnMsg.dsCustSicDescTxt_01);
        list.add(scrnMsg.ctacPsnLastNm_01);
        list.add(scrnMsg.ctacPsnFirstNm_01);
        list.add(scrnMsg.ctacPsnTelNum_01);
        // START 2017/08/07 T.Tsuchida [QC#19574,ADD]
        list.add(scrnMsg.ctacPsnEmlAddr_01);
        // END 2017/08/07 T.Tsuchida [QC#19574,ADD]
        list.add(scrnMsg.dsXrefAcctCd_01);
        list.add(scrnMsg.billToCustCd_01);
        list.add(scrnMsg.shipToCustCd_01);

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
}