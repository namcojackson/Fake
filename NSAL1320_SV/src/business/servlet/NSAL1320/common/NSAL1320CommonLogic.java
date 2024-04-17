/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320.common;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_ADD_MNT_SHELL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_ADD_MNT_SHELL_DTL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_DEL_MNT_SHELL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_DEL_MNT_SHELL_DTL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_OPEN_WIN_MASS_APPLY;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_OPEN_WIN_SVC_PRC;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_RESET_PRC;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_SEARCH;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_BIZ_SEARCH_SHELL_ITEM;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_APL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_APR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_CLR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_DEL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_DWL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_RJT;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_RST;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_RTN;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_SAV;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_SUB;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_STS_ACTIVE;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_STS_INACTIVE;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_CTY_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_FIRST_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_FRTH_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_POST_CD;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_SCD_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_ST_CD;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_THIRD_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.NEWLINE;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.NSAM0629E;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.PRM_CNT;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.ROLE_UPD;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.SCRN_ID_00;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.STATUS_CD_ACTIVE;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.USAGE_CD_BILL_TO_ONLY;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.ZZM9000E;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.NSAM0654E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.NSAL1320_ABMsg;
import business.servlet.NSAL1320.NSAL1320_ABMsgArray;
import business.servlet.NSAL1320.NSAL1320_BBMsg;
import business.servlet.NSAL1320.NSAL1320_QBMsg;
import business.servlet.NSAL1320.constant.NSAL1320Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NSAL1320CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/14   Hitachi         K.Kitachi       Update          QC#18844
 * 2017/08/18   Hitachi         Y.Takeno        Update          QC#20651
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#21919
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/07/02   Hitachi         T.Tomita        Update          QC#26738
 * 2018/07/18   Hitachi         K.Kitachi       Update          QC#26589
 * 2018/09/18   CITS            T.Wada          Update          QC#28326
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 * 2024/03/12   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NSAL1320CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NSAL1320BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NSAL1320BMsg scrnMsg) {
        boolean hasUpdateRole = NSAL1320CommonLogic.hasUpdateRole(scrnMsg);

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
     * @param scrnMsg NSAL1320BMsg
     * @param scrnAMsgAry NSAL1320_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NSAL1320BMsg scrnMsg, NSAL1320_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NSAL1320BMsg
     * @param scrnAMsgAry NSAL1320_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NSAL1320BMsg scrnMsg, NSAL1320_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NSAL1320BMsg
     * @param scrnAMsgAry NSAL1320_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NSAL1320BMsg scrnMsg, NSAL1320_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * @return  if the user has update role then return true.
     * </pre>
     */
    public static boolean hasUpdateRole(NSAL1320BMsg scrnMsg) {
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
     * @param scrnMsg NSAL1320BMsg </pre>
     */
    public static void initBizItemProp(S21CommonHandler handler, NSAL1320BMsg scrnMsg) {
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
        // Del Start 2024/03/12 QC#63638
        // Add Start 2018/07/02 QC#26738
        // if
        // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue()))
        // {
        // // QC#28326 Add Start
        // handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], false);
        // // QC#28326 Add End
        // handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], false);
        // handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0],
        // false);
        // }
        // Add End 2018/07/02 QC#26738
        // Del End 2024/03/12 QC#63638
        if (!hasUpdateRole(scrnMsg) //
                || ORD_HDR_STS.CLOSED.equals(scrnMsg.ordHdrStsCd.getValue()) //
                || ORD_HDR_STS.CANCELLED.equals(scrnMsg.ordHdrStsCd.getValue())) {
            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], false);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], false);

            handler.setButtonEnabled(BTN_BIZ_SEARCH_SHELL_ITEM[0], false);
            // 2018/05/18 QC#22482 Del Start
            // handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0],
            // false);
            // 2018/05/18 QC#22482 Del End
            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL_DTL[0], false);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0], false);
            handler.setButtonEnabled(BTN_BIZ_RESET_PRC[0], false);
        }
    }

    /**
     * <pre>
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL1320BMsg
     * </pre>
     */
    public static void protectLineItemForEntry(S21CommonHandler handler, NSAL1320BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(i);
            // 2018/05/07 QC#22482 Mod Start
            // protectLineItem(handler, aScrnMsg);
            protectLineItem(handler, aScrnMsg, i);
            // 2018/05/07 QC#22482 Mod End
            if (!hasUpdateRole(scrnMsg) //
                    || !isActiveSts(scrnMsg)) {
                continue;
            }
            // Add Start 2024/03/12 QC#63638
            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], true);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], true);
            // Add End 2024/03/12 QC#63638
            if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.contrAvalFlg_A.getValue())) {
                continue;
            }

            boolean isImport = isImport(scrnMsg);
            aScrnMsg.xxChkBox_A.setInputProtected(isImport);
            aScrnMsg.svcPgmMdseCd.setInputProtected(isImport);
            aScrnMsg.mdseDescShortTxt.setInputProtected(isImport);
            aScrnMsg.billWithEquipFlg.setInputProtected(isImport);
            aScrnMsg.baseBllgCycleCd.setInputProtected(isImport);
            aScrnMsg.usgBllgCycleCd.setInputProtected(isImport);
            // START 2018/11/15 [QC#28638, ADD]
            aScrnMsg.fixTermInMthAot.setInputProtected(isImport);
            // END 2018/11/15 [QC#28638, ADD]
            aScrnMsg.billByTpCd.setInputProtected(isImport);
            // 2018/05/07 QC#22482 Add Start
            aScrnMsg.manContrOvrdFlg.setInputProtected(isImport);
            aScrnMsg.svcMemoRsnDescTxt_LK.setInputProtected(isImport);
            aScrnMsg.svcMemoRsnDescTxt.setInputProtected(isImport);
            aScrnMsg.svcCmntTxt.setInputProtected(isImport);
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

            aScrnMsg.svcPgmMdseCd_LK.setInputProtected(isImport);
            aScrnMsg.dsAcctNum_LK.setInputProtected(isProtect);
            aScrnMsg.shipToCustLocCd_LK.setInputProtected(isProtect);
            aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(isImport);

            aScrnMsg.dsContrNum_AD.setInputProtected(isImport);
            aScrnMsg.dsContrNum_LK.setInputProtected(isImport);

            aScrnMsg.xxChkBox_BH.setInputProtected(isImport);

            // START 2017/10/24 [QC#21556, ADD]
            // 2018/05/17 QC#22482 Mod Start
            // protectLineItemForAddContr(scrnMsg, aScrnMsg);
            protectLineItemForAddContr(handler, scrnMsg, aScrnMsg, i);
            // 2018/05/17 QC#22482 Mod End
            // END 2017/10/24 [QC#21556, ADD]

            handler.setButtonEnabled(BTN_CMN_SAV[0], true);

            handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0], !isImport);
            handler.setButtonEnabled(BTN_BIZ_SEARCH_SHELL_ITEM[0], !isImport);
            // 2018/05/07 QC#22482 Mod Start
            // handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0],
            // !isImport);
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
            // Del Start 2024/03/12 QC#63638
            // Add Start 2018/07/01 QC#26738
            // if
            // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue()))
            // {
            // // QC#28326 Add Start
            // handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL[0],
            // false);
            // // QC#28326 Add End
            // handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL[0],
            // false);
            // handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0],
            // false);
            // }
            // Add End 2018/07/01 QC#26738
            // Del End 2024/03/12 QC#63638
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(i);
            NSAL1320_ABMsg aScrnMsg = getAScrnMsg(scrnMsg, scrnMsg.B.no(i));
            protectLineItem(bScrnMsg);
            if (!hasUpdateRole(scrnMsg) //
                    || !isActiveSts(scrnMsg) //
                    || CR_REBIL.CREDIT.equals(bScrnMsg.crRebilCd.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.contrAvalFlg_A.getValue())) {
                continue;
            }
            if (SHPG_STS.ARRIVED.compareTo(bScrnMsg.shpgStsCd.getValue()) <= 0) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(bScrnMsg.contrAvalFlg.getValue())) {
                continue;
            }

            boolean isImport = isImport(scrnMsg);
            bScrnMsg.xxChkBox_B.setInputProtected(isImport);
            bScrnMsg.mtrReadMethCd.setInputProtected(isImport);
            bScrnMsg.custIssPoNum.setInputProtected(isImport);
            bScrnMsg.custIssPoDt.setInputProtected(isImport);
            // 2018/04/16 QC#20162 Add Start
            bScrnMsg.dsPoExprDt.setInputProtected(isImport);
            // 2018/04/16 QC#20162 Add End
        }

        // Add Start 2024/03/12 QC#63638
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(i);
            boolean isShippedLine = false;
            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(j);
                if (aScrnMsg.shellLineNum.getValue().equals(bScrnMsg.shellLineNum_B.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(bScrnMsg.shipFlg.getValue())) {
                        isShippedLine = true;
                        break;
                    }
                }
            }
            if (isShippedLine) {
                handler.setButtonEnabled(BTN_BIZ_ADD_MNT_SHELL_DTL[0], i, false);
                handler.setButtonEnabled(BTN_BIZ_DEL_MNT_SHELL_DTL[0], i, false);
            }
        }
        // Add End 2024/03/12 QC#63638
    }

    private static boolean isActiveSts(NSAL1320BMsg scrnMsg) {
        if (ORD_HDR_STS.CLOSED.equals(scrnMsg.ordHdrStsCd.getValue()) //
                || ORD_HDR_STS.CANCELLED.equals(scrnMsg.ordHdrStsCd.getValue())) {
            return false;
        }
        // if (IMPT_STS.SUCCESS.equals(scrnMsg.imptStsCd.getValue())
        // //
        // || IMPT_STS.REJECT.equals(scrnMsg.imptStsCd.getValue()) //
        // ||
        // IMPT_STS.NOT_PROCESSED.equals(scrnMsg.imptStsCd.getValue()))
        // {
        // return false;
        // }
        return true;
    }

    /**
     * isImport
     * @param scrnMsg
     * @return if import process then return true.
     */
    public static boolean isImport(NSAL1320BMsg scrnMsg) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(scrnMsg.xxPageCd.getValue());
    }

    private static NSAL1320_ABMsg getAScrnMsg(NSAL1320BMsg scrnMsg, NSAL1320_BBMsg bScrnMsg) {
        if (!ZYPCommonFunc.hasValue(bScrnMsg.shellLineNum_B)) {
            return null;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aScrnMsg.shellLineNum)) {
                continue;
            }
            if (bScrnMsg.shellLineNum_B.getValue().compareTo(aScrnMsg.shellLineNum.getValue()) == 0) {
                return aScrnMsg;
            }
        }
        return null;
    }

    private static void protectLineItem(NSAL1320_BBMsg bScrnMsg) {
        bScrnMsg.xxChkBox_B.setInputProtected(true);
        bScrnMsg.dsOrdPosnNum.setInputProtected(true);
        bScrnMsg.t_MdlNm.setInputProtected(true);
        bScrnMsg.mdlId.setInputProtected(true);
        bScrnMsg.dplyLineNum.setInputProtected(true);
        bScrnMsg.mdseCd.setInputProtected(true);
        bScrnMsg.xxGenlFldAreaTxt_SH.setInputProtected(true);
        // 2018/04/25 QC#21919 Add Start
        bScrnMsg.svcContrBrDescTxt.setInputProtected(true);
        // 2018/04/25 QC#21919 Add End
        bScrnMsg.mtrReadMethCd.setInputProtected(true);
        bScrnMsg.custIssPoNum.setInputProtected(true);
        bScrnMsg.custIssPoDt.setInputProtected(true);
        // 2018/04/16 QC#20162 Add Start
        bScrnMsg.dsPoExprDt.setInputProtected(true);
        // 2018/04/16 QC#20162 Add End
        // 2018/04/16 QC#10374 Mod Start
        // bScrnMsg.xxTotAmt_LN.setInputProtected(true);
        // bScrnMsg.xxTotAmt_SV.setInputProtected(true);
        // bScrnMsg.xxTotAmt_EQ.setInputProtected(true);
        // bScrnMsg.xxTotAmt_AD.setInputProtected(true);
        bScrnMsg.xxFreqCycleCnt.setInputProtected(true);
        bScrnMsg.baseBllgCycleDescTxt.setInputProtected(true);
        bScrnMsg.xxDealAmt_TT.setInputProtected(true);
        bScrnMsg.xxDealAmt_EQ.setInputProtected(true);
        bScrnMsg.xxDealAmt_AC.setInputProtected(true);
        bScrnMsg.xxDealAmt_AD.setInputProtected(true);
        bScrnMsg.xxTermAmt_TT.setInputProtected(true);
        bScrnMsg.xxTermAmt_EQ.setInputProtected(true);
        bScrnMsg.xxTermAmt_AC.setInputProtected(true);
        bScrnMsg.xxTermAmt_AD.setInputProtected(true);
        // 2018/04/16 QC#10374 Mod End
    }

    /**
     * <pre>
     * @param handler   S21CommonHandler
     * @param aScrnMsg  NSAL1320_ABMsg
     * </pre>
     */
    public static void protectLineItem(S21CommonHandler handler, NSAL1320_ABMsg aScrnMsg) {

        // 2018/05/07 QC#22482 Mod Start
        // aScrnMsg.xxChkBox_A.setInputProtected(true);
        // aScrnMsg.shellLineNum.setInputProtected(true);
        // aScrnMsg.svcPgmMdseCd.setInputProtected(true);
        // aScrnMsg.mdseDescShortTxt.setInputProtected(true);
        // aScrnMsg.prcSvcContrTpCd.setInputProtected(true);
        // aScrnMsg.prcSvcPlnTpCd.setInputProtected(true);
        // aScrnMsg.billWithEquipFlg.setInputProtected(true);
        // aScrnMsg.fromPerMthNum.setInputProtected(true);
        // aScrnMsg.toPerMthNum.setInputProtected(true);
        // aScrnMsg.termMthAot.setInputProtected(true);
        // aScrnMsg.fixTermInMthAot.setInputProtected(true);
        // aScrnMsg.dsContrCatgCd.setInputProtected(true);
        // aScrnMsg.baseBllgCycleCd.setInputProtected(true);
        // aScrnMsg.usgBllgCycleCd.setInputProtected(true);
        // aScrnMsg.billByTpCd.setInputProtected(true);
        // aScrnMsg.xxTotAmt_S.setInputProtected(true);
        // aScrnMsg.dsAcctNm.setInputProtected(true);
        // aScrnMsg.dsAcctNum.setInputProtected(true);
        // aScrnMsg.xxGenlFldAreaTxt_BI.setInputProtected(true);
        // aScrnMsg.soldToCustLocCd.setInputProtected(true);
        // aScrnMsg.applyEquipBillToFlg.setInputProtected(true);
        // aScrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);
        //
        // aScrnMsg.svcPgmMdseCd_LK.setInputProtected(true);
        // aScrnMsg.dsAcctNum_LK.setInputProtected(true);
        // aScrnMsg.shipToCustLocCd_LK.setInputProtected(true);
        // aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(true);
        //
        // aScrnMsg.dsContrNum_AD.setInputProtected(true);
        // aScrnMsg.dsContrNum_LK.setInputProtected(true);
        //
        // aScrnMsg.xxChkBox_BH.setInputProtected(true);
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
    private static void protectLineItem(NSAL1320_ABMsg aScrnMsg) {

        aScrnMsg.xxChkBox_A.setInputProtected(true);
        aScrnMsg.shellLineNum.setInputProtected(true);
        aScrnMsg.svcPgmMdseCd.setInputProtected(true);
        aScrnMsg.mdseDescShortTxt.setInputProtected(true);
        aScrnMsg.prcSvcContrTpCd.setInputProtected(true);
        aScrnMsg.prcSvcPlnTpCd.setInputProtected(true);
        aScrnMsg.billWithEquipFlg.setInputProtected(true);
        aScrnMsg.fromPerMthNum.setInputProtected(true);
        aScrnMsg.toPerMthNum.setInputProtected(true);
        aScrnMsg.termMthAot.setInputProtected(true);
        aScrnMsg.fixTermInMthAot.setInputProtected(true);
        aScrnMsg.dsContrCatgCd.setInputProtected(true);
        aScrnMsg.baseBllgCycleCd.setInputProtected(true);
        aScrnMsg.usgBllgCycleCd.setInputProtected(true);
        aScrnMsg.billByTpCd.setInputProtected(true);
        aScrnMsg.xxTotAmt_S.setInputProtected(true);
        aScrnMsg.dsAcctNm.setInputProtected(true);
        aScrnMsg.dsAcctNum.setInputProtected(true);
        aScrnMsg.xxGenlFldAreaTxt_BI.setInputProtected(true);
        aScrnMsg.soldToCustLocCd.setInputProtected(true);
        aScrnMsg.applyEquipBillToFlg.setInputProtected(true);
        aScrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);

        aScrnMsg.svcPgmMdseCd_LK.setInputProtected(true);
        aScrnMsg.dsAcctNum_LK.setInputProtected(true);
        aScrnMsg.shipToCustLocCd_LK.setInputProtected(true);
        aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(true);

        aScrnMsg.dsContrNum_AD.setInputProtected(true);
        aScrnMsg.dsContrNum_LK.setInputProtected(true);

        aScrnMsg.xxChkBox_BH.setInputProtected(true);

        aScrnMsg.manContrOvrdFlg.setInputProtected(true);
        aScrnMsg.svcMemoRsnDescTxt_LK.setInputProtected(true);
        aScrnMsg.svcMemoRsnDescTxt.setInputProtected(true);
        aScrnMsg.svcCmntTxt.setInputProtected(true);
    }

    public static void protectLineItem(S21CommonHandler handler, NSAL1320_ABMsg aScrnMsg, int idx) {

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

    // 2018/05/07 QC#22482 Add Start

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NSAL1320BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.refCpoOrdNum);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            // 2018/05/07 QC#22482 Add Start
            if (ZYPConstant.FLG_ON_Y.equals(scrnLineMsg.manContrOvrdFlg.getValue())) {
                scrnMsg.addCheckItem(scrnLineMsg.svcMemoRsnDescTxt);
                scrnMsg.addCheckItem(scrnLineMsg.svcCmntTxt);
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A);
            scrnMsg.addCheckItem(scrnLineMsg.shellLineNum);
            scrnMsg.addCheckItem(scrnLineMsg.mdseDescShortTxt);
            scrnMsg.addCheckItem(scrnLineMsg.svcPgmMdseCd);
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
            // START 2018/11/15 [QC#28638, ADD]
            scrnMsg.addCheckItem(scrnLineMsg.fixTermInMthAot);
            // END 2018/11/15 [QC#28638, ADD]
            scrnMsg.addCheckItem(scrnLineMsg.billByTpCd);
            scrnMsg.addCheckItem(scrnLineMsg.dsContrNum_AD);
            scrnMsg.addCheckItem(scrnLineMsg.cpoSvcAgmtVerNum);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg scrnLineMsg = scrnMsg.B.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B);

            if (ZYPCommonFunc.hasValue(scrnLineMsg.shellLineNum_B)) {
                if (hasUsgBllgCycle(scrnLineMsg.shellLineNum_B.getValue(), scrnMsg)) {
                    scrnMsg.addCheckItem(scrnLineMsg.mtrReadMethCd);
                }
                scrnMsg.addCheckItem(scrnLineMsg.custIssPoNum);
                scrnMsg.addCheckItem(scrnLineMsg.custIssPoDt);
                // 2018/04/16 QC#20162 Add Start
                scrnMsg.addCheckItem(scrnLineMsg.dsPoExprDt);
                // 2018/04/16 QC#20162 Add End
            }
        }
    }

    private static boolean hasUsgBllgCycle(BigDecimal cpoSvcLineNum, NSAL1320BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aScrnMsg.shellLineNum)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(aScrnMsg.shellLineNum.getValue()) == 0) {
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
    public static void addCheckItemBizLayerErr(NSAL1320BMsg scrnMsg, int ixSelRow) {

        BigDecimal cpoSvcLineNum = scrnMsg.A.no(ixSelRow).shellLineNum.getValue();
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(scrnLineMsg.shellLineNum) //
                    || cpoSvcLineNum.compareTo(scrnLineMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            if (ixSelRow == i) {
                scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A);
                scrnMsg.addCheckItem(scrnLineMsg.svcPgmMdseCd);
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
                // START 2018/11/15 [QC#28638, ADD]
                scrnMsg.addCheckItem(scrnLineMsg.fixTermInMthAot);
                // END 2018/11/15 [QC#28638, ADD]
                scrnMsg.addCheckItem(scrnLineMsg.billByTpCd);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg scrnLineMsg = scrnMsg.B.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B);
            if (hasUsgBllgCycle(scrnLineMsg.shellLineNum_B.getValue(), scrnMsg)) {
                scrnMsg.addCheckItem(scrnLineMsg.mtrReadMethCd);
            }
            scrnMsg.addCheckItem(scrnLineMsg.custIssPoNum);
            scrnMsg.addCheckItem(scrnLineMsg.custIssPoDt);
            // 2018/04/16 QC#20162 Add Start
            scrnMsg.addCheckItem(scrnLineMsg.dsPoExprDt);
            // 2018/04/16 QC#20162 Add End
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * @return  parameters array of NMAL6780 for Customer
     * </pre>
     */
    public static Object[] getParamNMAL6780ForCustomer(NSAL1320BMsg scrnMsg) {
        scrnMsg.W.clear();

        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        int ixPrm = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(ixPrm).xxPopPrm_W, USAGE_CD_BILL_TO_ONLY);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W);
        paramList.add(scrnMsg.A.no(ixSelRow).soldToCustLocCd);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNm);
        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNum);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(ixPrm).xxPopPrm_W, ZYPConstant.FLG_ON_Y);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * @return  parameters array of NMAL6780 for Location
     * </pre>
     */
    public static Object[] getParamNMAL6780ForLocation(NSAL1320BMsg scrnMsg) {
        scrnMsg.W.clear();

        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        int ixPrm = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(ixPrm).xxPopPrm_W, USAGE_CD_BILL_TO_ONLY);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W);
        paramList.add(scrnMsg.A.no(ixSelRow).soldToCustLocCd);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 1
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 2
        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNum);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 3
        // LocNum
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 4
        // LocNm
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 5
        // First
        // Reference
        // Comment
        // Text
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 6
        // Second
        // Reference
        // Comment
        // Text
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 7
        // FirstLineAddr
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 8
        // ScdLineAddr
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 9
        // ThirdLineAddr
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 10
        // FrthLineAddr
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 11 CtyAddr
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 12
        // CountyNm
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 13
        // ProvinceNm
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 14 StCd
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // 15 PostCd
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 16
        // CountryCd
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W); // no used 17
        // RelatedAcctNm
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(ixPrm).xxPopPrm_W, ZYPConstant.FLG_ON_Y);
        paramList.add(scrnMsg.W.no(ixPrm++).xxPopPrm_W);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * @return  parameters array of NSAL1330
     * </pre>
     */
    public static Object[] getParamNSAL1330(NSAL1320BMsg scrnMsg) {
        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(ixSelRow);
        List<Object> paramList = new ArrayList<Object>();

        paramList.add(aScrnMsg.dsContrPk_A);
        paramList.add(scrnMsg.refCpoOrdNum);
        paramList.add(aScrnMsg.shellLineNum);

        return paramList.toArray(new Object[0]);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * @return  parameters array of NSAL1350
     * </pre>
     */
    public static Object[] getParamNSAL1350(NSAL1320BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.Q);
        List<Object> params = new ArrayList<Object>();

        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_I, scrnMsg.refCpoOrdNum);
        if ("OpenWin_ShellConfigSelection".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPTableUtil.clear(scrnMsg.I);
            scrnMsg.addAsryFlg_I.clear();
            scrnMsg.dsContrNum_I.clear();
        } else {
            int ix = scrnMsg.xxCellIdx.getValueInt();
            ZYPEZDItemValueSetter.setValue(scrnMsg.shellLineNum_I, scrnMsg.A.no(ix).shellLineNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.addAsryFlg_I, scrnMsg.A.no(ix).addAsryFlg);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum_I, scrnMsg.A.no(ix).dsContrNum_AD);
            BigDecimal cpoSvcLineNum = scrnMsg.shellLineNum_I.getValue();
            int ixI = 0;
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).shellLineNum_B) //
                        && cpoSvcLineNum.compareTo(scrnMsg.B.no(i).shellLineNum_B.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(ixI).cpoDtlLineNum_I, scrnMsg.B.no(i).cpoDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(ixI).cpoDtlLineSubNum_I, scrnMsg.B.no(i).cpoDtlLineSubNum);
                    ixI++;
                }
            }
            scrnMsg.I.setValidCount(ixI);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSfxKeyTxt_I, "I");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSfxKeyTxt_Q, "Q");

        params.add(scrnMsg.cpoOrdNum_I);
        params.add(scrnMsg.shellLineNum_I);
        params.add(scrnMsg.addAsryFlg_I);
        params.add(scrnMsg.dsContrNum_I);
        params.add(scrnMsg.xxSfxKeyTxt_I);
        params.add(scrnMsg.I);
        params.add(scrnMsg.xxSfxKeyTxt_Q);
        params.add(scrnMsg.Q);

        return params.toArray(new Object[0]);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * </pre>
     */
    public static void setReturnParamNSAL1350(NSAL1320BMsg scrnMsg) {
        if (scrnMsg.Q.getValidCount() == 0) {
            return;
        }
        if (scrnMsg.B.getValidCount() + scrnMsg.Q.getValidCount() > scrnMsg.B.length()) {
            scrnMsg.setMessageInfo(NSAM0629E);
            return;
        }
        BigDecimal shellNum = null;
        NSAL1320_ABMsg hdAScrnMsg = new NSAL1320_ABMsg();
        boolean isAddForDetail = scrnMsg.xxScrEventNm.getValue().endsWith("ForDetail");
        if (isAddForDetail) {
            int ix = scrnMsg.xxCellIdx.getValueInt();
            hdAScrnMsg = scrnMsg.A.no(ix);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(ix).addAsryFlg) && scrnMsg.Q.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).addAsryFlg, scrnMsg.Q.no(0).addAsryFlg_Q);
            }
            shellNum = hdAScrnMsg.shellLineNum.getValue();

        } else {

            ZYPEZDItemValueSetter.setValue(hdAScrnMsg.xxSelFlg_CI, ZYPConstant.FLG_OFF_N);

            shellNum = getMaxShellNum(scrnMsg).add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(hdAScrnMsg.shellLineNum, shellNum);

            int ix = scrnMsg.A.getValidCount();
            NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(ix);
            EZDMsg.copy(hdAScrnMsg, null, aScrnMsg, null);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(ix).addAsryFlg) && scrnMsg.Q.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).addAsryFlg, scrnMsg.Q.no(0).addAsryFlg_Q);
            }
            scrnMsg.A.setValidCount(ix + 1);
        }
        String dsOrdPosnNum = scrnMsg.Q.no(0).dsOrdPosnNum_Q.getValue();

        int ixB = scrnMsg.B.getValidCount();
        if (isAddForDetail) {
            ixB = getAddIxForDetail(scrnMsg);
        }
        List<NSAL1320_BBMsg> bScrnMsgShiftList = new ArrayList<NSAL1320_BBMsg>();
        for (int i = ixB; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg bScrnMsg = new NSAL1320_BBMsg();
            EZDMsg.copy(scrnMsg.B.no(i), null, bScrnMsg, null);
            bScrnMsgShiftList.add(bScrnMsg);
        }

        List<NSAL1320_BBMsg> wScrnMsgList = new ArrayList<NSAL1320_BBMsg>();
        for (int i = 0; i < scrnMsg.Q.getValidCount(); i++) {
            if (ixB >= scrnMsg.B.length()) {
                break;
            }
            NSAL1320_QBMsg qScrnMsg = scrnMsg.Q.no(i);
            if (!dsOrdPosnNum.equals(qScrnMsg.dsOrdPosnNum_Q.getValue())) {
                // shellNum = shellNum.add(BigDecimal.ONE);
                dsOrdPosnNum = qScrnMsg.dsOrdPosnNum_Q.getValue();
            }
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(ixB);

            setDetailMsg(shellNum, hdAScrnMsg, qScrnMsg, bScrnMsg);

            ixB++;
        }
        for (NSAL1320_BBMsg wScrnMsg : wScrnMsgList) {
            if (ixB >= scrnMsg.B.length()) {
                break;
            }
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(ixB++);
            EZDMsg.copy(wScrnMsg, null, bScrnMsg, null);
        }
        for (NSAL1320_BBMsg wScrnMsg : bScrnMsgShiftList) {
            if (ixB >= scrnMsg.B.length()) {
                break;
            }
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(ixB++);
            EZDMsg.copy(wScrnMsg, null, bScrnMsg, null);
        }
        scrnMsg.B.setValidCount(ixB);
    }

    private static int getAddIxForDetail(NSAL1320BMsg scrnMsg) {
        int ixA = scrnMsg.xxCellIdx.getValueInt();
        BigDecimal cpoSvcLineNum = scrnMsg.A.no(ixA).shellLineNum.getValue();
        boolean isSameLineNum = false;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bScrnMsg.shellLineNum_B)) {
                return i;
            }
            if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) == 0) {
                isSameLineNum = true;
            }
            if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) < 0) {
                return i;
            }
            if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) != 0) {
                if (isSameLineNum) {
                    return i;
                }
                continue;
            }
        }
        return scrnMsg.B.getValidCount();
    }

    /**
     * <pre>
     * @param shellNum  shellNum
     * @param hdAScrnMsg 
     * @param qScrnMsg  NSAL1320_QBMsg(popup output area)
     * @param bScrnMsg  NSAL1320_BBMsg(screen detail area)
     * </pre>
     */
    private static void setDetailMsg(BigDecimal shellNum, NSAL1320_ABMsg hdAScrnMsg, NSAL1320_QBMsg qScrnMsg, NSAL1320_BBMsg bScrnMsg) {
        bScrnMsg.clear();
        if (hdAScrnMsg != null) {
            EZDMsg.copy(hdAScrnMsg, null, bScrnMsg, null);
        }

        ZYPEZDItemValueSetter.setValue(bScrnMsg.shellLineNum_B, shellNum);

        bScrnMsg.mtrReadMethCd.clear();
        bScrnMsg.custIssPoNum.clear();
        bScrnMsg.custIssPoDt.clear();
        // 2018/04/16 QC#20162 Add Start
        bScrnMsg.dsPoExprDt.clear();
        // 2018/04/16 QC#20162 Add End
        // 2018/04/16 QC#10374 Mod Start
        // ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTotAmt_AD,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTotAmt_EQ,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTotAmt_LN,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTotAmt_SV,
        // BigDecimal.ZERO);
        bScrnMsg.xxFreqCycleCnt.clear();
        bScrnMsg.baseBllgCycleDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxDealAmt_TT, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxDealAmt_EQ, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxDealAmt_AC, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxDealAmt_AD, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTermAmt_TT, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTermAmt_EQ, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTermAmt_AC, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxTermAmt_AD, BigDecimal.ZERO);
        // 2018/04/16 QC#10374 Mod End

        ZYPEZDItemValueSetter.setValue(bScrnMsg.dsOrdPosnNum, qScrnMsg.dsOrdPosnNum_Q);
        // 2018/04/16 QC#10374 Add Start
        ZYPEZDItemValueSetter.setValue(bScrnMsg.dsOrdPosnNum_BX, qScrnMsg.dsOrdPosnNum_Q);
        // 2018/04/16 QC#10374 Add End
        ZYPEZDItemValueSetter.setValue(bScrnMsg.mdlId, qScrnMsg.mdlId_Q);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.t_MdlNm, qScrnMsg.t_MdlNm_Q);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.dplyLineNum, qScrnMsg.dplyLineNum_Q);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.cpoDtlLineNum, qScrnMsg.cpoDtlLineNum_Q);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.cpoDtlLineSubNum, qScrnMsg.cpoDtlLineSubNum_Q);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.mdseCd, qScrnMsg.mdseCd_Q);
        ZYPEZDItemValueSetter.setValue(bScrnMsg.xxGenlFldAreaTxt_SH, qScrnMsg.xxGenlFldAreaTxt_Q);
        // 2024/03/12 QC#63638 Add Start
        ZYPEZDItemValueSetter.setValue(bScrnMsg.shpgStsCd, qScrnMsg.shpgStsCd_Q);
        // 2024/03/12 QC#63638 Add End
    }

    private static BigDecimal getMaxShellNum(NSAL1320BMsg scrnMsg) {
        BigDecimal shellNum = BigDecimal.ZERO;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (shellNum.compareTo(scrnMsg.A.no(i).shellLineNum.getValue()) < 0) {
                shellNum = scrnMsg.A.no(i).shellLineNum.getValue();
            }
        }
        return shellNum;
    }

    /**
     * <pre>
     * @param aScrnMsg  NSAL1320_ABMsg
     * @param scrnMsg   NSAL1320BMsg
     * </pre>
     */
    public static void checkMandatoryForSvcPricing(NSAL1320_ABMsg aScrnMsg, NSAL1320BMsg scrnMsg) {

        // 2018/05/07 QC#22482 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.manContrOvrdFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(aScrnMsg.svcCmntTxt)) {
                aScrnMsg.svcCmntTxt.setErrorInfo(1, NSAM0654E, new String[] {aScrnMsg.svcCmntTxt.getNameForMessage() });
            }
            return;
        }
        // 2018/05/07 QC#22482 Add End

        if (!ZYPCommonFunc.hasValue(aScrnMsg.mdseDescShortTxt)) {
            aScrnMsg.svcPgmMdseCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.mdseDescShortTxt.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.svcPgmMdseCd)) {
            aScrnMsg.svcPgmMdseCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.svcPgmMdseCd.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcSvcContrTpCd)) {
            aScrnMsg.prcSvcContrTpCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.prcSvcContrTpCd.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.fromPerMthNum)) {
            aScrnMsg.fromPerMthNum.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.fromPerMthNum.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.toPerMthNum)) {
            aScrnMsg.toPerMthNum.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.toPerMthNum.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.dsContrCatgCd)) {
            aScrnMsg.dsContrCatgCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.dsContrCatgCd.getNameForMessage() });
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

        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcSvcPlnTpCd)) {
            aScrnMsg.prcSvcPlnTpCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.prcSvcPlnTpCd.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.billWithEquipFlg)) {
            aScrnMsg.billWithEquipFlg.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.billWithEquipFlg.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.baseBllgCycleCd)) {
            aScrnMsg.baseBllgCycleCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.baseBllgCycleCd.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.billByTpCd)) {
            aScrnMsg.billByTpCd.setErrorInfo(1, ZZM9000E, new String[] {aScrnMsg.billByTpCd.getNameForMessage() });
        }

        if (!ZYPCommonFunc.hasValue(aScrnMsg.shellLineNum)) {
            return;
        }
        BigDecimal cpoSvcLineNum = aScrnMsg.shellLineNum.getValue();
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bScrnMsg.shellLineNum_B)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) != 0) {
                continue;
            }
            // START 2017/08/18 [QC#20651, DEL]
            // if (ZYPCommonFunc.hasValue(aScrnMsg.usgBllgCycleCd)) {
            // if (!ZYPCommonFunc.hasValue(bScrnMsg.mtrReadMethCd)) {
            // bScrnMsg.mtrReadMethCd.setErrorInfo(1, ZZM9000E, new
            // String[] {bScrnMsg.mtrReadMethCd.getNameForMessage()
            // });
            // }
            // }
            // START 2017/08/18 [QC#20651, DEL]
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * @param ixSelRow  ixSelRow
     * @return number of Maintenance Shell Count
     * </pre>
     */
    public static int getMaintenanceShellCnt(NSAL1320BMsg scrnMsg, int ixSelRow) {
        BigDecimal cpoSvcLineNum = scrnMsg.A.no(ixSelRow).shellLineNum.getValue();
        int cnt = 0;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).shellLineNum_B)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(scrnMsg.B.no(i).shellLineNum_B.getValue()) == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1320BMsg
     * </pre>
     */
    public static void mntBillAsEquipCtrl(NSAL1320BMsg scrnMsg) {
        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(ixSelRow);
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
     * @param aScrnMsg   NSAL1320_ABMsg
     * @param scrnMsg   NSAL1320BMsg
     * </pre>
     */
    public static void checkMandatoryForSave(NSAL1320_ABMsg aScrnMsg, NSAL1320BMsg scrnMsg) {
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
    public static void setGuiStyle(NSAL1320BMsg scrnMsg) {
        // 2018/05/07 QC#22482 Del Start
        // scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        // 2018/05/07 QC#22482 Del End

        BigDecimal cpoSvcLineNum = BigDecimal.ZERO;
        int ixLine = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1320_ABMsg aCurScrnMsg = scrnMsg.A.no(i);
            cpoSvcLineNum = aCurScrnMsg.shellLineNum.getValue();

            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID_00, "shellHeader" + i);
            guiAttr.setStyleAttribute("background-color", "#ffffbb");
            scrnMsg.addGUIAttribute(guiAttr);
            ixLine = 0;

            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(j).shellLineNum_B) //
                        || cpoSvcLineNum.compareTo(scrnMsg.B.no(j).shellLineNum_B.getValue()) != 0) {
                    continue;
                }
                EZDGUIAttribute lineBLAttr = new EZDGUIAttribute(SCRN_ID_00, "lineBL" + cpoSvcLineNum + "#" + ixLine);
                EZDGUIAttribute lineBRAttr = new EZDGUIAttribute(SCRN_ID_00, "lineBR" + cpoSvcLineNum + "#" + ixLine);
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
     * check all / unCheck all
     * @param scrnMsg NSAL1320BMsg
     */
    public static void checkConfigHeader(NSAL1320BMsg scrnMsg) {
        int ix = scrnMsg.xxCellIdx.getValueInt();
        BigDecimal cpoSvcLineNum = scrnMsg.A.no(ix).shellLineNum.getValue();
        if (!ZYPCommonFunc.hasValue(cpoSvcLineNum)) {
            return;
        }
        String chkVal = scrnMsg.A.no(ix).xxChkBox_BH.getValue();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1320_BBMsg bBizMsg = scrnMsg.B.no(i);

            if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.contrAvalFlg.getValue()) //
                    || SHPG_STS.ARRIVED.compareTo(bBizMsg.shpgStsCd.getValue()) <= 0) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(bBizMsg.shellLineNum_B.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxChkBox_B, chkVal);
            }
        }

    }

    /**
     * @param scrnMsg NSAL1320BMsg
     * @param ixA ixA
     * @return Object[]
     */
    public static Object[] getNWAL1130Prm(NSAL1320BMsg scrnMsg, int ixA) {
        scrnMsg.O.clear(); // parameter area for Common BIG Popup

        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Search Statement (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        int i = 0;
        Object[] params = new Object[PRM_CNT];

        params[i++] = "";
        params[i++] = "Shell Item Popup";
        params[i++] = getSelectSQL();
        params[i++] = getSearchConditionSetting(//
                scrnMsg //
                , scrnMsg.A.no(ixA).svcPgmMdseCd.getValue() //
                , scrnMsg.A.no(ixA).mdseDescShortTxt.getValue());
        params[i++] = getDisplayColumnSetting();
        params[i++] = getSortSetting();
        params[i] = scrnMsg.O;
        return params;
    }

    private static List<Object> getSearchConditionSetting(NSAL1320BMsg scrnMsg, String mdseCd, String mdseNm) {
        String eventNm = scrnMsg.xxScrEventNm.getValue();
        if (eventNm.endsWith("OnBlur_DeriveFromSvcPgmNm")) {
            mdseCd = "";
        } else if (eventNm.endsWith("OnBlur_DeriveFromSvcPgmCd")) {
            mdseNm = "";
            // START 2017/06/14 K.Kitachi [QC#18844, ADD]
        } else if (!ZYPCommonFunc.hasValue(mdseCd) && !ZYPCommonFunc.hasValue(mdseNm)) {
            // END 2017/06/14 K.Kitachi [QC#18844, ADD]
            mdseCd = "%";
            mdseNm = "";
        }

        List<Object> whereList = new ArrayList<Object>();

        // {"display name", "column name(physical)", value, use LIKE
        // flag}
        Object[] whereObj1 = {"Shell Item", "MDSE_CD", mdseCd, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Description", "MDSE_NM", mdseNm, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private static List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        // {"display name", "column name(physical)", column size,
        // selectable flag}
        Object[] colObj1 = {"Shell Item", "MDSE_CD", new BigDecimal("16"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Description", "MDSE_NM", new BigDecimal("50"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private static List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        // {"column name(physical)", sort order(ASC/DESC)}
        Object[] sortObj1 = {"MDSE_CD", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private static String getSelectSQL() {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT");
        // S21_NA#9283 modify start
        sb.append(NEWLINE).append("     M.EZCANCELFLAG                                            ");
        sb.append(NEWLINE).append("    ,M.GLBL_CMPY_CD                                            ");
        sb.append(NEWLINE).append("    ,NVL(OTM.ORD_TAKE_MDSE_CD, M.MDSE_CD) AS MDSE_CD           ");
        sb.append(NEWLINE).append("    ,RTRIM(M.MDSE_DESC_SHORT_TXT) AS MDSE_NM                   ");
        sb.append(NEWLINE).append("FROM                                                           ");
        sb.append(NEWLINE).append("     ITEM_TP_VAL_SET ITVS                                      ");
        sb.append(NEWLINE).append("    ,MDSE            M                                         ");
        sb.append(NEWLINE).append("    ,ORD_TAKE_MDSE   OTM                                       ");
        sb.append(NEWLINE).append("WHERE                                                          ");
        sb.append(NEWLINE).append("        ITVS.EZCANCELFLAG       = '0'                          ");
        sb.append(NEWLINE).append("    AND ITVS.ITEM_TP_CTX_TP_CD  = 'CPO_SVC_MDSE_QLFY_ITEM_TP'  ");
        sb.append(NEWLINE).append("    AND ITVS.EZCANCELFLAG       = M.EZCANCELFLAG               ");
        sb.append(NEWLINE).append("    AND ITVS.GLBL_CMPY_CD       = M.GLBL_CMPY_CD               ");
        sb.append(NEWLINE).append("    AND ITVS.MDSE_ITEM_TP_CD    = M.MDSE_ITEM_TP_CD            ");
        sb.append(NEWLINE).append("    AND M.SVC_COV_TMPL_TP_CD IS NOT NULL                       ");
        sb.append(NEWLINE).append("    AND M.RGTN_STS_CD           = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        sb.append(NEWLINE).append("    AND M.EZCANCELFLAG          = OTM.EZCANCELFLAG(+)          ");
        sb.append(NEWLINE).append("    AND M.GLBL_CMPY_CD          = OTM.GLBL_CMPY_CD(+)          ");
        sb.append(NEWLINE).append("    AND M.MDSE_CD               = OTM.MDSE_CD(+)               ");
        // S21_NA#9283 modify end

        String sql = sb.toString();
        return sql;
    }

    /**
     * Get Param NMAL6760 For Bill To
     * @param scrnMsg NSAL1320BMsg
     * @return Param NMAL6760 For Bill To
     */
    public static Object[] getParamNMAL6760ForBillTo(NSAL1320BMsg scrnMsg) {

        // clear
        scrnMsg.W.clear();
        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();

        int ixP = 1;
        scrnMsg.W.no(ixP++).xxPopPrm_W.setValue(STATUS_CD_ACTIVE);
        scrnMsg.W.no(ixP++).xxPopPrm_W.setValue(DISP_HRCH_ACCT_CD_BILL);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNum); // Bill To
        // Acct Code
        paramList.add(scrnMsg.A.no(ixSelRow).dsAcctNm); // Bill To
        // Acct Name
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

    public static Object[] getNSAL0110Prm(NSAL1320BMsg scrnMsg, int ixA) {

        List<Object> paramList = new ArrayList<Object>(12);
        paramList.add(scrnMsg.A.no(ixA).dsContrNum_AD); // dsContrNum
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        paramList.add(scrnMsg.A.no(ixA).dsAcctNum); // dsAcctNum
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(ixA).serNum_A)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ixA).serNum_A, scrnMsg.serNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ixA).mdseCd_A, scrnMsg.mdseCd_H);
        }
        paramList.add(scrnMsg.A.no(ixA).serNum_A); // serial#
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        paramList.add(scrnMsg.W.no(1).xxPopPrm_W);
        paramList.add(scrnMsg.A.no(ixA).dsContrPk_AD); // dsContrPk
        paramList.add(scrnMsg.A.no(ixA).dsContrDtlPk_AD); // dsContrDtlPk

        return paramList.toArray(new Object[paramList.size()]);
    }

    // START 2017/10/24 [QC#21556, ADD]
    /**
     * protectLineItemForAddContr
     * @param scrnMsg NSAL1320BMsg
     * @param aScrnMsg NSAL1320_ABMsg
     */
    // 2018/05/17 QC#22482 Mod Start
    // public static void protectLineItemForAddContr(NSAL1320BMsg
    // scrnMsg, NSAL1320_ABMsg aScrnMsg) {
    public static void protectLineItemForAddContr(S21CommonHandler handler, NSAL1320BMsg scrnMsg, NSAL1320_ABMsg aScrnMsg, int idx) {
        // 2018/05/17 QC#22482 Mod Start

        if (!ZYPCommonFunc.hasValue(aScrnMsg.dsContrPk_AD)) {
            return;
        }

        // START 2018/07/18 K.Kitachi [QC#26589, MOD]
        if (DS_CONTR_CATG.FLEET.equals(aScrnMsg.dsContrCatgCd.getValue())) {
            aScrnMsg.billWithEquipFlg.setInputProtected(true);
            aScrnMsg.svcPgmMdseCd_LK.setInputProtected(true);
            aScrnMsg.mdseDescShortTxt.setInputProtected(true);
            aScrnMsg.svcPgmMdseCd.setInputProtected(true);
            aScrnMsg.prcSvcContrTpCd.setInputProtected(true);
            aScrnMsg.baseBllgCycleCd.setInputProtected(true);
            aScrnMsg.usgBllgCycleCd.setInputProtected(true);
            aScrnMsg.shipToCustLocCd_LK.setInputProtected(true);
            aScrnMsg.soldToCustLocCd.setInputProtected(true);
        }
        if (DS_CONTR_CATG.AGGREGATE.equals(aScrnMsg.dsContrCatgCd.getValue())) {
            if (ZYPConstant.FLG_OFF_N.equals(aScrnMsg.invSeptBaseUsgFlg.getValue())) {
                aScrnMsg.baseBllgCycleCd.setInputProtected(true);
            }
            aScrnMsg.usgBllgCycleCd.setInputProtected(true);
            aScrnMsg.shipToCustLocCd_LK.setInputProtected(true);
            aScrnMsg.soldToCustLocCd.setInputProtected(true);
        }
        aScrnMsg.billByTpCd.setInputProtected(true);

        aScrnMsg.dsAcctNm.setInputProtected(true);
        aScrnMsg.dsAcctNum.setInputProtected(true);

        aScrnMsg.applyEquipBillToFlg.setInputProtected(true);
        aScrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);

        aScrnMsg.prcSvcPlnTpCd.setInputProtected(true);
        aScrnMsg.dsContrCatgCd.setInputProtected(true);

        aScrnMsg.dsAcctNum_LK.setInputProtected(true);
        aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(true);
        // END 2018/07/18 K.Kitachi [QC#26589, MOD]

        // 2018/05/17 QC#22482 Add Start
        aScrnMsg.manContrOvrdFlg.setInputProtected(true);
        aScrnMsg.svcMemoRsnDescTxt_LK.setInputProtected(true);
        aScrnMsg.svcMemoRsnDescTxt.setInputProtected(true);
        aScrnMsg.svcCmntTxt.setInputProtected(true);

        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.manContrOvrdFlg.getValue())) {
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], idx, false);
        } else {
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], idx, true);
        }
        // 2018/05/17 QC#22482 Add End
    }

    // 2018/05/17 QC#22482 Mod Start
    // public static void unprotectLineItemForAddContr(NSAL1320BMsg
    // scrnMsg, NSAL1320_ABMsg aScrnMsg) {
    public static void unprotectLineItemForAddContr(S21CommonHandler handler, NSAL1320BMsg scrnMsg, NSAL1320_ABMsg aScrnMsg, int idx) {
        // 2018/05/17 QC#22482 Mod Start

        if (ZYPCommonFunc.hasValue(aScrnMsg.dsContrPk_AD)) {
            return;
        }

        if (!hasUpdateRole(scrnMsg) //
                || !isActiveSts(scrnMsg)) {
            return;
        }
        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.contrAvalFlg_A.getValue())) {
            return;
        }

        aScrnMsg.svcPgmMdseCd.setInputProtected(false);
        aScrnMsg.mdseDescShortTxt.setInputProtected(false);
        if (!DS_CONTR_CATG.FLEET.equals(aScrnMsg.dsContrCatgCd.getValue())) {
            aScrnMsg.billWithEquipFlg.setInputProtected(false);
        }
        aScrnMsg.baseBllgCycleCd.setInputProtected(false);
        aScrnMsg.usgBllgCycleCd.setInputProtected(false);
        aScrnMsg.billByTpCd.setInputProtected(false);

        aScrnMsg.dsAcctNm.setInputProtected(false);
        aScrnMsg.dsAcctNum.setInputProtected(false);
        aScrnMsg.soldToCustLocCd.setInputProtected(false);

        aScrnMsg.applyEquipBillToFlg.setInputProtected(false);

        aScrnMsg.prcSvcContrTpCd.setInputProtected(false);
        aScrnMsg.prcSvcPlnTpCd.setInputProtected(false);
        aScrnMsg.dsContrCatgCd.setInputProtected(false);

        aScrnMsg.svcPgmMdseCd_LK.setInputProtected(false);
        aScrnMsg.dsAcctNum_LK.setInputProtected(false);
        aScrnMsg.shipToCustLocCd_LK.setInputProtected(false);
        aScrnMsg.cpoSvcAgmtVerNum_LK.setInputProtected(false);

        // 2018/05/17 QC#22482 Add Start
        aScrnMsg.manContrOvrdFlg.setInputProtected(false);
        aScrnMsg.svcMemoRsnDescTxt_LK.setInputProtected(false);
        aScrnMsg.svcMemoRsnDescTxt.setInputProtected(false);
        aScrnMsg.svcCmntTxt.setInputProtected(false);

        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.manContrOvrdFlg.getValue())) {
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], idx, false);
        } else {
            handler.setButtonEnabled(BTN_BIZ_OPEN_WIN_SVC_PRC[0], idx, true);
        }
        // 2018/05/17 QC#22482 Add End
    }

    // END 2017/10/24 [QC#21556, ADD]
    // 2018/04/16 QC#10374 Add Start
    public static Object[] getParamNWAL2370(NSAL1320BMsg scrnMsg) {

        int ixSelRow = scrnMsg.xxCellIdx.getValueInt();
        NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(ixSelRow);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(bScrnMsg.xxModeCd_B);
        paramList.add(bScrnMsg.dsContrDtlPk_B);
        paramList.add(null);

        return paramList.toArray(new Object[0]);
    }

    // 2018/04/16 QC#10374 Add End
    // 2018/05/07 QC#22482 Add Start
    public static void setScrnManOvrdDspCtrl(NSAL1320BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setScrnManOvrdDspCtrl(scrnMsg, i);
        }
    }

    public static void setScrnManOvrdDspCtrl(NSAL1320BMsg scrnMsg, int i) {

        NSAL1320_ABMsg aBMsg = scrnMsg.A.no(i);

        String dspVal = "";
        if (!ZYPConstant.FLG_ON_Y.equals(aBMsg.manContrOvrdFlg.getValue())) {
            dspVal = "none";
        }

        EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID_00, "manOvrd_" + i);
        guiAttr.setStyleAttribute("display", dspVal);
        scrnMsg.addGUIAttribute(guiAttr);
    }
    // 2018/05/07 QC#22482 Add End
}
