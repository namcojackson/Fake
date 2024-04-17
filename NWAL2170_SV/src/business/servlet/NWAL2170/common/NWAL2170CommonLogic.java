/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2170.common;

import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_ADD_MNT_SHELL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_ADD_MNT_SHELL_DTL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_DEL_MNT_SHELL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_DEL_MNT_SHELL_DTL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_OPEN_WIN_MASS_APPLY;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_OPEN_WIN_SVC_PRC;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_RESET_PRC;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_SEARCH;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_BIZ_SEARCH_SHELL_ITEM;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_APL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_APR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_RST;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_SUB;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_STS_ACTIVE;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_STS_INACTIVE;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_CTY_ADDR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_FIRST_LINE_ADDR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_FRTH_LINE_ADDR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_POST_CD;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_SCD_LINE_ADDR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_ST_CD;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.IX_POP_PRM_THIRD_LINE_ADDR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.ROLE_UPD;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.SCRN_ID_00;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.STATUS_CD_ACTIVE;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDGUIAttribute;
import business.servlet.NWAL2170.NWAL2170BMsg;
import business.servlet.NWAL2170.NWAL2170_ABMsg;
import business.servlet.NWAL2170.NWAL2170_ABMsgArray;
import business.servlet.NWAL2170.NWAL2170_BBMsg;
import business.servlet.NWAL2170.constant.NWAL2170Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL2170CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2016/03/10   Fujitsu         M.Yamada        Update          QC#5089
 * 2016/07/05   Fujitsu         T.Ishii         Update          S21_NA#9283
 * 2016/08/02   Fujitsu         M.Yamada        Update          QC#11721
 * 2016/08/24   Fujitsu         M.Yamada        Update          QC#13057
 * 2016/11/07   Fujitsu         M.Yamada        Update          QC#14283
 * 2017/05/30   Fujitsu         S.Ohki          Update          RS#8233
 * 2018/04/04   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/04/04   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NWAL2170CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2170BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL2170BMsg scrnMsg) {
        boolean hasUpdateRole = NWAL2170CommonLogic.hasUpdateRole(scrnMsg);

        int status = BTN_STS_INACTIVE;
        if (hasUpdateRole //
                && !ORD_HDR_STS.CLOSED.equals(scrnMsg.ordHdrStsCd.getValue()) //
                && !ORD_HDR_STS.CANCELLED.equals(scrnMsg.ordHdrStsCd.getValue())) {
            status = BTN_STS_ACTIVE;
        }
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], BTN_STS_INACTIVE, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], status, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], BTN_STS_ACTIVE, null);

    }

    /**
     * Set Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2170BMsg
     * @param scrnAMsgAry NWAL2170_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL2170BMsg scrnMsg, NWAL2170_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2170BMsg
     * @param scrnAMsgAry NWAL2170_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL2170BMsg scrnMsg, NWAL2170_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL2170BMsg
     * @param scrnAMsgAry NWAL2170_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL2170BMsg scrnMsg, NWAL2170_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2170BMsg
     * @return  if the user has update role then return true.
     * </pre>
     */
    public static boolean hasUpdateRole(NWAL2170BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
            if (ROLE_UPD.equals(scrnMsg.P.no(i).xxFuncId_P.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * </pre>
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2170BMsg </pre>
     */
    public static void initBizItemProp(S21CommonHandler handler, NWAL2170BMsg scrnMsg) {
        scrnMsg.xxPageCd.setInputProtected(true);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem50Txt)) {
            setBtnProp(handler, BTN_BIZ_SEARCH, BTN_STS_INACTIVE);
            scrnMsg.xxScrItem50Txt.setInputProtected(true);

            protectLineItemForEntry(handler, scrnMsg);
        } else {
            setBtnProp(handler, BTN_BIZ_SEARCH, BTN_STS_ACTIVE);
            scrnMsg.xxScrItem50Txt.setInputProtected(false);
            scrnMsg.setFocusItem(scrnMsg.xxScrItem50Txt);

            protectLineItem(handler, scrnMsg.A.no(0));
        }
        if (!hasUpdateRole(scrnMsg) //
                || ORD_HDR_STS.CLOSED.equals(scrnMsg.ordHdrStsCd.getValue()) //
                || ORD_HDR_STS.CANCELLED.equals(scrnMsg.ordHdrStsCd.getValue())) {
            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], false);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], false);

            handler.setButtonEnabled(BTN_BIZ_SEARCH_SHELL_ITEM[0], false);
            // 2018/05/18 QC#22482 Del Start
//            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], false);
            // 2018/05/18 QC#22482 Del End
            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL_DTL[0], false);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0], false);
            handler.setButtonEnabled(BTN_BIZ_RESET_PRC[0], false);
        }
    }

    /**
     * <pre>
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2170BMsg
     * </pre>
     */
    public static void protectLineItemForEntry(S21CommonHandler handler, NWAL2170BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg aScrnMsg = scrnMsg.A.no(i);
            // 2018/05/07 QC#22482 Mod Start
//            protectLineItem(handler, aScrnMsg);
            protectLineItem(handler, aScrnMsg, i);
            // 2018/05/07 QC#22482 Mod End
            if (!hasUpdateRole(scrnMsg) //
                    || !isActiveSts(scrnMsg)) {
                continue;
            }

            boolean isImport = isImport(scrnMsg);
            aScrnMsg.xxChkBox_A.setInputProtected(isImport);
            aScrnMsg.dsImptSvcMdseCd.setInputProtected(isImport);
            aScrnMsg.mdseDescShortTxt.setInputProtected(isImport);
            aScrnMsg.billWithEquipFlg.setInputProtected(isImport);
            aScrnMsg.baseBllgCycleCd.setInputProtected(isImport);
            aScrnMsg.usgBllgCycleCd.setInputProtected(isImport);
            aScrnMsg.billByTpCd.setInputProtected(isImport);
            // 2018/05/07 QC#22482 Add Start
            aScrnMsg.manContrOvrdFlg.setInputProtected(isImport);
            aScrnMsg.manContrOvrdRsnNm.setInputProtected(isImport);
            aScrnMsg.manContrOvrdCmntTxt.setInputProtected(isImport);
            // 2018/05/07 QC#22482 Add End

            boolean isProtect = ZYPConstant.FLG_ON_Y.equals(aScrnMsg.applyEquipBillToFlg.getValue());
            aScrnMsg.dsAcctNm.setInputProtected(isProtect);
            aScrnMsg.dsAcctNum.setInputProtected(isProtect);
            aScrnMsg.soldToCustLocCd.setInputProtected(isProtect);

            aScrnMsg.applyEquipBillToFlg.setInputProtected(false);
            aScrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);

            aScrnMsg.prcSvcContrTpCd.setInputProtected(isImport);
            aScrnMsg.prcSvcPlnTpCd.setInputProtected(isImport);
            aScrnMsg.fromPerMthNum.setInputProtected(isImport);
            aScrnMsg.toPerMthNum.setInputProtected(isImport);
            aScrnMsg.dsContrCatgCd.setInputProtected(isImport);

            aScrnMsg.dsImptSvcMdseCd_LK.setInputProtected(isImport);
            aScrnMsg.dsAcctNum_LK.setInputProtected(isProtect);
            aScrnMsg.shipToCustLocCd_LK.setInputProtected(isProtect);
            aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(isImport);

            aScrnMsg.dsContrNum.setInputProtected(isImport);
            aScrnMsg.dsContrNum_LK.setInputProtected(isImport);

            aScrnMsg.xxChkBox_BH.setInputProtected(isImport);

            handler.setButtonEnabled(BTN_CMN_SAV[0], true);

            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_SEARCH_SHELL_ITEM[0], !isImport);
            // 2018/05/07 QC#22482 Mod Start
//            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], !isImport);
            if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.manContrOvrdFlg.getValue())) {
                handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], i, false);
            } else {
                handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], i, true);
            }
            // 2018/05/07 QC#22482 Mod End
            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL_DTL[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_RESET_PRC[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_MASS_APPLY[0], !isImport);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL2170_BBMsg bScrnMsg = scrnMsg.B.no(i);
            NWAL2170_ABMsg aScrnMsg = getAScrnMsg(scrnMsg, scrnMsg.B.no(i));
            protectLineItem(bScrnMsg);
            if (!hasUpdateRole(scrnMsg) //
                    || !isActiveSts(scrnMsg) //
                    || CR_REBIL.CREDIT.equals(bScrnMsg.crRebilCd.getValue())) {
                continue;
            }

            if (SHPG_STS.ARRIVED.compareTo(bScrnMsg.shpgStsCd.getValue()) <= 0) {
                continue;
            }

            boolean isImport = isImport(scrnMsg);
            bScrnMsg.xxChkBox_B.setInputProtected(isImport);
            bScrnMsg.mtrReadMethCd.setInputProtected(isImport);
            bScrnMsg.custIssPoNum.setInputProtected(isImport);
            bScrnMsg.custIssPoDt.setInputProtected(isImport);
            // 2018/04/04 QC#20162 Add Start
            bScrnMsg.dsPoExprDt.setInputProtected(isImport);
            // 2018/04/04 QC#20162 Add End
        }

    }

    private static boolean isActiveSts(NWAL2170BMsg scrnMsg) {
        if (ORD_HDR_STS.CLOSED.equals(scrnMsg.ordHdrStsCd.getValue()) //
                || ORD_HDR_STS.CANCELLED.equals(scrnMsg.ordHdrStsCd.getValue())) {
            return false;
        }
        if (IMPT_STS.SUCCESS.equals(scrnMsg.imptStsCd.getValue()) //
                || IMPT_STS.REJECT.equals(scrnMsg.imptStsCd.getValue()) //
                || IMPT_STS.NOT_PROCESSED.equals(scrnMsg.imptStsCd.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * isImport
     * @param scrnMsg
     * @return if import process then return true.
     */
    public static boolean isImport(NWAL2170BMsg scrnMsg) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(scrnMsg.xxPageCd.getValue());
    }

    private static NWAL2170_ABMsg getAScrnMsg(NWAL2170BMsg scrnMsg, NWAL2170_BBMsg bScrnMsg) {
        if (!ZYPCommonFunc.hasValue(bScrnMsg.dsImptSvcLineNum_B)) {
            return null;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aScrnMsg.dsImptSvcLineNum)) {
                continue;
            }
            if (bScrnMsg.dsImptSvcLineNum_B.getValue().compareTo(aScrnMsg.dsImptSvcLineNum.getValue()) == 0) {
                return aScrnMsg;
            }
        }
        return null;
    }

    private static void protectLineItem(NWAL2170_BBMsg bScrnMsg) {
        bScrnMsg.xxChkBox_B.setInputProtected(true);
        bScrnMsg.dsOrdPosnNum.setInputProtected(true);
        bScrnMsg.t_MdlNm.setInputProtected(true);
        bScrnMsg.mdlId.setInputProtected(true);
        bScrnMsg.dplyLineNum.setInputProtected(true);
        bScrnMsg.mdseCd.setInputProtected(true);
        bScrnMsg.xxShipToAcctNmSrchTxt.setInputProtected(true);
        bScrnMsg.mtrReadMethCd.setInputProtected(true);
        bScrnMsg.custIssPoNum.setInputProtected(true);
        bScrnMsg.custIssPoDt.setInputProtected(true);
        // 2018/04/04 QC#20162 Add Start
        bScrnMsg.dsPoExprDt.setInputProtected(true);
        // 2018/04/04 QC#20162 Add End
        // 2018/04/04 QC#10374 Add Start
        bScrnMsg.xxFreqCycleCnt.setInputProtected(true);
        bScrnMsg.baseBllgCycleDescTxt.setInputProtected(true);
        bScrnMsg.xxDealAmt_TT.setInputProtected(true);
        bScrnMsg.xxDealAmt_EQ.setInputProtected(true);
        bScrnMsg.xxDealAmt_AC.setInputProtected(true);
        bScrnMsg.xxDealAmt_AD.setInputProtected(true);
        // 2018/04/04 QC#10374 Add End
        // 2018/04/04 QC#10374 Mod Start
//        bScrnMsg.xxTotAmt_LN.setInputProtected(true);
//        bScrnMsg.xxTotAmt_SV.setInputProtected(true);
//        bScrnMsg.xxTotAmt_EQ.setInputProtected(true);
//        bScrnMsg.xxTotAmt_AD.setInputProtected(true);
        bScrnMsg.xxTermAmt_TT.setInputProtected(true);
        bScrnMsg.xxTermAmt_EQ.setInputProtected(true);
        bScrnMsg.xxTermAmt_AC.setInputProtected(true);
        bScrnMsg.xxTermAmt_AD.setInputProtected(true);
        // 2018/04/04 QC#10374 Mod End
    }

    /**
     * <pre>
     * @param handler   S21CommonHandler
     * @param aScrnMsg  NWAL2170_ABMsg
     * </pre>
     */
    public static void protectLineItem(S21CommonHandler handler, NWAL2170_ABMsg aScrnMsg) {

        // 2018/05/07 QC#22482 Mod Start
//        aScrnMsg.xxChkBox_A.setInputProtected(true);
//        aScrnMsg.dsImptSvcLineNum.setInputProtected(true);
//        aScrnMsg.dsImptSvcMdseCd.setInputProtected(true);
//        aScrnMsg.mdseDescShortTxt.setInputProtected(true);
//        aScrnMsg.prcSvcContrTpCd.setInputProtected(true);
//        aScrnMsg.prcSvcPlnTpCd.setInputProtected(true);
//        aScrnMsg.billWithEquipFlg.setInputProtected(true);
//        aScrnMsg.fromPerMthNum.setInputProtected(true);
//        aScrnMsg.toPerMthNum.setInputProtected(true);
//        aScrnMsg.shpgIntvlMthNum.setInputProtected(true);
//        aScrnMsg.fixTermInMthAot.setInputProtected(true);
//        aScrnMsg.dsContrCatgCd.setInputProtected(true);
//        aScrnMsg.baseBllgCycleCd.setInputProtected(true);
//        aScrnMsg.usgBllgCycleCd.setInputProtected(true);
//        aScrnMsg.billByTpCd.setInputProtected(true);
//        aScrnMsg.xxTotAmt_S.setInputProtected(true);
//        aScrnMsg.dsAcctNm.setInputProtected(true);
//        aScrnMsg.dsAcctNum.setInputProtected(true);
//        aScrnMsg.xxBillToAcctNmSrchTxt.setInputProtected(true);
//        aScrnMsg.soldToCustLocCd.setInputProtected(true);
//        aScrnMsg.applyEquipBillToFlg.setInputProtected(true);
//        aScrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);
//
//        aScrnMsg.dsImptSvcMdseCd_LK.setInputProtected(true);
//        aScrnMsg.dsAcctNum_LK.setInputProtected(true);
//        aScrnMsg.shipToCustLocCd_LK.setInputProtected(true);
//        aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(true);
//
//        aScrnMsg.dsContrNum.setInputProtected(true);
//        aScrnMsg.dsContrNum_LK.setInputProtected(true);
//
//        aScrnMsg.xxChkBox_BH.setInputProtected(true);
        protectLineItem(aScrnMsg);
        // 2018/05/07 QC#22482 Mod End

        handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], false);
        handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], false);
        handler.setButtonEnabled(BTN_BIZ_SEARCH_SHELL_ITEM[0], false);
        handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], false);
        handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL_DTL[0], false);
        handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0], false);
        handler.setButtonEnabled(BTN_BIZ_RESET_PRC[0], false);
        handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_MASS_APPLY[0], false);
    }

    // 2018/05/07 QC#22482 Add Start
    private static void protectLineItem(NWAL2170_ABMsg aScrnMsg) {

        aScrnMsg.xxChkBox_A.setInputProtected(true);
        aScrnMsg.dsImptSvcLineNum.setInputProtected(true);
        aScrnMsg.dsImptSvcMdseCd.setInputProtected(true);
        aScrnMsg.mdseDescShortTxt.setInputProtected(true);
        aScrnMsg.prcSvcContrTpCd.setInputProtected(true);
        aScrnMsg.prcSvcPlnTpCd.setInputProtected(true);
        aScrnMsg.billWithEquipFlg.setInputProtected(true);
        aScrnMsg.fromPerMthNum.setInputProtected(true);
        aScrnMsg.toPerMthNum.setInputProtected(true);
        aScrnMsg.shpgIntvlMthNum.setInputProtected(true);
        aScrnMsg.fixTermInMthAot.setInputProtected(true);
        aScrnMsg.dsContrCatgCd.setInputProtected(true);
        aScrnMsg.baseBllgCycleCd.setInputProtected(true);
        aScrnMsg.usgBllgCycleCd.setInputProtected(true);
        aScrnMsg.billByTpCd.setInputProtected(true);
        aScrnMsg.xxTotAmt_S.setInputProtected(true);
        aScrnMsg.dsAcctNm.setInputProtected(true);
        aScrnMsg.dsAcctNum.setInputProtected(true);
        aScrnMsg.xxBillToAcctNmSrchTxt.setInputProtected(true);
        aScrnMsg.soldToCustLocCd.setInputProtected(true);
        aScrnMsg.applyEquipBillToFlg.setInputProtected(true);
        aScrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);

        aScrnMsg.dsImptSvcMdseCd_LK.setInputProtected(true);
        aScrnMsg.dsAcctNum_LK.setInputProtected(true);
        aScrnMsg.shipToCustLocCd_LK.setInputProtected(true);
        aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(true);

        aScrnMsg.dsContrNum.setInputProtected(true);
        aScrnMsg.dsContrNum_LK.setInputProtected(true);

        aScrnMsg.xxChkBox_BH.setInputProtected(true);

        aScrnMsg.manContrOvrdFlg.setInputProtected(true);
        aScrnMsg.manContrOvrdRsnNm.setInputProtected(true);
        aScrnMsg.manContrOvrdCmntTxt.setInputProtected(true);
    }

    public static void protectLineItem(S21CommonHandler handler, NWAL2170_ABMsg aScrnMsg, int idx) {

        protectLineItem(aScrnMsg);

        handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], false);
        handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], false);
        handler.setButtonEnabled(BTN_BIZ_SEARCH_SHELL_ITEM[0], false);
        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.manContrOvrdFlg.getValue())) {
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], idx, false);
        } else {
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], idx, true);
        }
        handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL_DTL[0], false);
        handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0], false);
        handler.setButtonEnabled(BTN_BIZ_RESET_PRC[0], false);
        handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_MASS_APPLY[0], false);
    }
    // 2018/05/07 QC#22482 Add End

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL2170BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.refCpoOrdNum);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            // 2018/05/07 QC#22482 Add Start
            if (ZYPConstant.FLG_ON_Y.equals(scrnLineMsg.manContrOvrdFlg.getValue())) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A);
            scrnMsg.addCheckItem(scrnLineMsg.dsImptSvcLineNum);
            scrnMsg.addCheckItem(scrnLineMsg.mdseDescShortTxt);
            scrnMsg.addCheckItem(scrnLineMsg.dsImptSvcMdseCd);
            scrnMsg.addCheckItem(scrnLineMsg.prcSvcContrTpCd);
            scrnMsg.addCheckItem(scrnLineMsg.prcSvcPlnTpCd);
            scrnMsg.addCheckItem(scrnLineMsg.billWithEquipFlg);
            scrnMsg.addCheckItem(scrnLineMsg.fromPerMthNum);
            scrnMsg.addCheckItem(scrnLineMsg.toPerMthNum);
            scrnMsg.addCheckItem(scrnLineMsg.dsContrCatgCd);
            scrnMsg.addCheckItem(scrnLineMsg.dsAcctNm);
            scrnMsg.addCheckItem(scrnLineMsg.dsAcctNum);
            scrnMsg.addCheckItem(scrnLineMsg.soldToCustLocCd);
            scrnMsg.addCheckItem(scrnLineMsg.baseBllgCycleCd);
            scrnMsg.addCheckItem(scrnLineMsg.usgBllgCycleCd);
            scrnMsg.addCheckItem(scrnLineMsg.billByTpCd);
            scrnMsg.addCheckItem(scrnLineMsg.dsContrNum);
            scrnMsg.addCheckItem(scrnLineMsg.cpoSvcAgmtVerNum);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL2170_BBMsg scrnLineMsg = scrnMsg.B.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B);

            if (ZYPCommonFunc.hasValue(scrnLineMsg.dsImptSvcLineNum_B)) {
                if (hasUsgBllgCycle(scrnLineMsg.dsImptSvcLineNum_B.getValue(), scrnMsg)) {
                    scrnMsg.addCheckItem(scrnLineMsg.mtrReadMethCd);
                }
                scrnMsg.addCheckItem(scrnLineMsg.custIssPoNum);
                scrnMsg.addCheckItem(scrnLineMsg.custIssPoDt);
            }
        }
    }

    private static boolean hasUsgBllgCycle(BigDecimal dsImptSvcLineNum, NWAL2170BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aScrnMsg.dsImptSvcLineNum)) {
                continue;
            }
            if (dsImptSvcLineNum.compareTo(aScrnMsg.dsImptSvcLineNum.getValue()) == 0) {
                return ZYPCommonFunc.hasValue(aScrnMsg.usgBllgCycleCd);
            }
        }
        return true;
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     * @param ixSelRow selected index number
     */
    public static void addCheckItemBizLayerErr(NWAL2170BMsg scrnMsg, int ixSelRow) {

        BigDecimal dsImptSvcLineNum = scrnMsg.A.no(ixSelRow).dsImptSvcLineNum.getValue();
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(scrnLineMsg.dsImptSvcLineNum) //
                    || dsImptSvcLineNum.compareTo(scrnLineMsg.dsImptSvcLineNum.getValue()) != 0) {
                continue;
            }
            if (ixSelRow == i) {
                scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A);
                scrnMsg.addCheckItem(scrnLineMsg.dsImptSvcMdseCd);
                scrnMsg.addCheckItem(scrnLineMsg.prcSvcContrTpCd);
                scrnMsg.addCheckItem(scrnLineMsg.prcSvcPlnTpCd);
                scrnMsg.addCheckItem(scrnLineMsg.billWithEquipFlg);
                scrnMsg.addCheckItem(scrnLineMsg.fromPerMthNum);
                scrnMsg.addCheckItem(scrnLineMsg.toPerMthNum);
                scrnMsg.addCheckItem(scrnLineMsg.dsContrCatgCd);
                scrnMsg.addCheckItem(scrnLineMsg.dsAcctNum);
                scrnMsg.addCheckItem(scrnLineMsg.soldToCustLocCd);
                scrnMsg.addCheckItem(scrnLineMsg.baseBllgCycleCd);
                scrnMsg.addCheckItem(scrnLineMsg.usgBllgCycleCd);
                scrnMsg.addCheckItem(scrnLineMsg.billByTpCd);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL2170_BBMsg scrnLineMsg = scrnMsg.B.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B);
            if (hasUsgBllgCycle(scrnLineMsg.dsImptSvcLineNum_B.getValue(), scrnMsg)) {
                scrnMsg.addCheckItem(scrnLineMsg.mtrReadMethCd);
            }
            scrnMsg.addCheckItem(scrnLineMsg.custIssPoNum);
            scrnMsg.addCheckItem(scrnLineMsg.custIssPoDt);
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2170BMsg
     * @return  parameters array of NWAL2180
     * </pre>
     */
    public static Object[] getParamNWAL2180(NWAL2170BMsg scrnMsg) {
        // scrnMsg.W.clear();

        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        NWAL2170_ABMsg aScrnMsg = scrnMsg.A.no(ixSelRow);
        List<Object> paramList = new ArrayList<Object>();

        paramList.add(aScrnMsg.cpoSvcDtlPk);
        paramList.add(scrnMsg.xxPageCd);

        return paramList.toArray(new Object[0]);
    }

    /**
     * <pre>
     * @param aScrnMsg  NWAL2170_ABMsg
     * @param scrnMsg   NWAL2170BMsg
     * </pre>
     */
    public static void checkMandatoryForSvcPricing(NWAL2170_ABMsg aScrnMsg, NWAL2170BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.manContrOvrdFlg.getValue())) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(aScrnMsg.dsAcctNm)) {
            aScrnMsg.dsAcctNm.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.dsAcctNm.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.dsAcctNum)) {
            aScrnMsg.dsAcctNum.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.dsAcctNum.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.soldToCustLocCd)) {
            aScrnMsg.soldToCustLocCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.soldToCustLocCd.getNameForMessage() });
        }

    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2170BMsg
     * @param ixSelRow  ixSelRow
     * @return number of Maintenance Shell Count
     * </pre>
     */
    public static int getMaintenanceShellCnt(NWAL2170BMsg scrnMsg, int ixSelRow) {
        BigDecimal dsImptSvcLineNum = scrnMsg.A.no(ixSelRow).dsImptSvcLineNum.getValue();
        int cnt = 0;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsImptSvcLineNum_B)) {
                continue;
            }
            if (dsImptSvcLineNum.compareTo(scrnMsg.B.no(i).dsImptSvcLineNum_B.getValue()) == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2170BMsg
     * </pre>
     */
    public static void mntBillAsEquipCtrl(NWAL2170BMsg scrnMsg) {
        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        NWAL2170_ABMsg aScrnMsg = scrnMsg.A.no(ixSelRow);
        boolean isProtect = ZYPConstant.CHKBOX_ON_Y.equals(aScrnMsg.applyEquipBillToFlg.getValue());
        aScrnMsg.dsAcctNm.setInputProtected(isProtect);
        aScrnMsg.dsAcctNum.setInputProtected(isProtect);
        aScrnMsg.soldToCustLocCd.setInputProtected(isProtect);

        aScrnMsg.dsAcctNum_LK.setInputProtected(isProtect);
        aScrnMsg.shipToCustLocCd_LK.setInputProtected(isProtect);

        scrnMsg.setFocusItem(aScrnMsg.xxChkBox_BH);
    }

    /**
     * <pre>
     * @param aScrnMsg   NWAL2170_ABMsg
     * @param scrnMsg   NWAL2170BMsg
     * </pre>
     */
    public static void checkMandatoryForSave(NWAL2170_ABMsg aScrnMsg, NWAL2170BMsg scrnMsg) {
        checkMandatoryForSvcPricing(aScrnMsg, scrnMsg);
    }

    /**
     * <pre>
     * @param scrnMsg   scrnMsg
     *  id        element's id of html.
     *  attr      attribute of style sheet
     *  val       value of style sheet
     * </pre>
     */
    public static void setGuiStyle(NWAL2170BMsg scrnMsg) {
        // 2018/05/07 QC#22482 Del Start
//        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        // 2018/05/07 QC#22482 Del End

        BigDecimal dsImptSvcLineNum = BigDecimal.ZERO;
        int ixLine = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg aCurScrnMsg = scrnMsg.A.no(i);
            dsImptSvcLineNum = aCurScrnMsg.dsImptSvcLineNum.getValue();

            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID_00, "shellHeader" + i);
            guiAttr.setStyleAttribute("background-color", "#ffffbb");
            scrnMsg.addGUIAttribute(guiAttr);
            ixLine = 0;

            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(j).dsImptSvcLineNum_B) //
                        || dsImptSvcLineNum.compareTo(scrnMsg.B.no(j).dsImptSvcLineNum_B.getValue()) != 0) {
                    continue;
                }
                EZDGUIAttribute lineBLAttr = new EZDGUIAttribute(SCRN_ID_00, "lineBL" + dsImptSvcLineNum + "#" + ixLine);
                EZDGUIAttribute lineBRAttr = new EZDGUIAttribute(SCRN_ID_00, "lineBR" + dsImptSvcLineNum + "#" + ixLine);
                if ((ixLine + 1) % 2 == 0) {
                    lineBLAttr.setStyleAttribute("background-color", EZDGUIAttribute.pEvenNumberBGcolor);
                    lineBRAttr.setStyleAttribute("background-color", EZDGUIAttribute.pEvenNumberBGcolor);
                } else {
                    lineBLAttr.setStyleAttribute("background-color", EZDGUIAttribute.pOddNumberBGcolor);
                    lineBRAttr.setStyleAttribute("background-color", EZDGUIAttribute.pOddNumberBGcolor);
                }
                scrnMsg.addGUIAttribute(lineBLAttr);
                scrnMsg.addGUIAttribute(lineBRAttr);
                ixLine++;
            }
        }
    }

    /**
     * Get Param NMAL6760 For Bill To
     * @param scrnMsg NWAL2170BMsg
     * @return Param NMAL6760 For Bill To
     */
    public static Object[] getParamNMAL6760ForBillTo(NWAL2170BMsg scrnMsg) {

        // clear
        scrnMsg.W.clear();
        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();

        int ixP = 1;
        scrnMsg.W.no(ixP++).xxPopPrm_W.setValue(STATUS_CD_ACTIVE);
        scrnMsg.W.no(ixP++).xxPopPrm_W.setValue(DISP_HRCH_ACCT_CD_BILL);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNum); // Bill To Acct Code
        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNm); // Bill To Acct Name
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(IX_POP_PRM_FIRST_LINE_ADDR).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(IX_POP_PRM_CTY_ADDR).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(IX_POP_PRM_ST_CD).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(IX_POP_PRM_POST_CD).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W); // Active Only
        paramList.add(scrnMsg.W.no(2).xxPopPrm_W); // Bill To's Only
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.A.no(ixSelRow).soldToCustLocCd);
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(IX_POP_PRM_SCD_LINE_ADDR).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(IX_POP_PRM_THIRD_LINE_ADDR).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(IX_POP_PRM_FRTH_LINE_ADDR).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixP).xxPopPrm_W); // no used

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    // 2018/04/11 QC#10374 Add Start
    public static Object[] getParamNWAL2370(NWAL2170BMsg scrnMsg) {

        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        NWAL2170_BBMsg bScrnMsg = scrnMsg.B.no(ixSelRow);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(bScrnMsg.xxModeCd_B);
        paramList.add(null); //dsContrPk
        paramList.add(bScrnMsg.cpoSvcConfigRefPk);

        return paramList.toArray(new Object[0]);
    }
    // 2018/04/11 QC#10374 Add End

    // 2018/05/07 QC#22482 Add Start
    public static void setScrnManOvrdDspCtrl(NWAL2170BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setScrnManOvrdDspCtrl(scrnMsg, i);
        }
    }

    public static void setScrnManOvrdDspCtrl(NWAL2170BMsg scrnMsg, int i) {

        NWAL2170_ABMsg aBMsg = scrnMsg.A.no(i);

        String dspVal = "";
        if (!ZYPConstant.FLG_ON_Y.equals(aBMsg.manContrOvrdFlg.getValue())) {
            dspVal = "none";
        }

        EZDGUIAttribute guiAttr_Rsn = new EZDGUIAttribute(SCRN_ID_00, "manOvrdRsn" + i);
        guiAttr_Rsn.setStyleAttribute("display", dspVal);
        scrnMsg.addGUIAttribute(guiAttr_Rsn);

        EZDGUIAttribute guiAttr_Cmnt = new EZDGUIAttribute(SCRN_ID_00, "manOvrdCmnt" + i);
        guiAttr_Cmnt.setStyleAttribute("display", dspVal);
        scrnMsg.addGUIAttribute(guiAttr_Cmnt);
    }
    // 2018/05/07 QC#22482 Add End

}
