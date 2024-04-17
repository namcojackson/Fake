/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700.common;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_ADD_LINE;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_APL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_APR;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_RST;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_OPENWIN_AJE;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_OPENWIN_LOV;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_REMOVE_LINE;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_AJE;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_ARTRAN;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_BILLTOACCOUNT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_BILLTO_LOCATION;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_CARRIER;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_PRICELIST;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.BTN_SEARCH_SERVICE_PRICE_LIST;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.DB_FLAG_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.SCRN_ID_00;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1700.NWAL1700BMsg;
import business.servlet.NWAL1700.NWAL1700Bean;
import business.servlet.NWAL1700.NWAL1700_ABMsg;
import business.servlet.NWAL1700.NWAL1700_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NWAL1700CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/06   Fujitsu         M.Suzuki        Create          S21_NA#6353
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#12143
 * 2016/08/10   Fujitsu         M.Yamada        Update          QC#11418
 * 2017/03/07   Fujitsu         W.Honda         Update          QC#16855
 * 2017/09/11   Fujitsu         T.Murai         Update          QC#16346(Sol#373)
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWAL1700CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param userProfileService Event S21UserProfileService
     */
    public static void initCmnBtnProp(S21CommonHandler handler, S21UserProfileService userProfileService) {

        if (NWAL1700CommonLogic.isFullUser(userProfileService) || NWAL1700CommonLogic.isInsertUser(userProfileService)) {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
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
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1700BMsg
     * @param scrnAMsgAry NWAL1700_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1700BMsg scrnMsg, NWAL1700_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1700BMsg
     * @param scrnAMsgAry NWAL1700_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1700BMsg scrnMsg, NWAL1700_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1700BMsg
     * @param scrnAMsgAry NWAL1700_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1700BMsg scrnMsg, NWAL1700_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isFullUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNCTION_FULL);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isInsertUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNCTION_INSERT);
    }

    /**
     * addCheckItemLineCategory
     * @param scrnMsg NWAL1700BMsg
     */
    public static void addCheckItemLineCategory(NWAL1700BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).lineProcDfnSortNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdLineCatgCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ordProcTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).primLineCatgFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rmaPrimLineCatgFlg_A); // Add 2017/09/11 QC#16346
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ajeAcctBatCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ajeAcctBatDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdLineDrctnNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).revFcstCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).actvFlg_A);
        }
    }

    /**
     * addCheckItemDefaults
     * @param scrnMsg NWAL1700BMsg
     */
    public static void addCheckItemDefaults(NWAL1700BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.defPrcCatgCd);
        scrnMsg.addCheckItem(scrnMsg.prcCatgDescTxt_PR);
        scrnMsg.addCheckItem(scrnMsg.defMaintPrcCatgCd);
        scrnMsg.addCheckItem(scrnMsg.prcCatgDescTxt_SP);
        scrnMsg.addCheckItem(scrnMsg.revFcstCd);
        scrnMsg.addCheckItem(scrnMsg.frtCondCd);
        scrnMsg.addCheckItem(scrnMsg.invPrintStyleCd);
        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        scrnMsg.addCheckItem(scrnMsg.trtyGrpTpTxt);
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)
        // Add 2020/04/24 QC#56638 Start
        scrnMsg.addCheckItem(scrnMsg.baseLocTxt);
        // Add 2020/04/24 QC#56638 End
        scrnMsg.addCheckItem(scrnMsg.defBillToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.defBillToCustCd);
        scrnMsg.addCheckItem(scrnMsg.locDescTxt);
        scrnMsg.addCheckItem(scrnMsg.defInstlTpCd);
        scrnMsg.addCheckItem(scrnMsg.defShpgSvcLvlCd);
        scrnMsg.addCheckItem(scrnMsg.defCarrSvcLvlCd);
        scrnMsg.addCheckItem(scrnMsg.carrSvcLvlDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dropShipAvalFlg);
    }

    /**
     * addCheckItemAccounting
     * @param scrnMsg NWAL1700BMsg
     */
    public static void addCheckItemAccounting(NWAL1700BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsInvTpCd);
        // 2016/04/04 S21_NA#6353 MOD Start ----------
        // Mod Start 2016/08/04 QC#12143
        //        scrnMsg.addCheckItem(scrnMsg.dsInvTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsInvTpNm);
        // Mod End 2016/08/04 QC#12143
        // 2016/04/04 S21_NA#6353 MOD End ------------
        scrnMsg.addCheckItem(scrnMsg.autoCancOrdFlg);
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NWAL1700BMsg
     */
    public static void addCheckItemHeader(NWAL1700BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpNm);
        scrnMsg.addCheckItem(scrnMsg.ordProcTpCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdRsnGrpCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
    }

    /**
     * addCheckItemWorkFlow
     * @param scrnMsg NWAL1700BMsg
     */
    public static void addCheckItemWorkFlow(NWAL1700BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.vldApvlNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.diChkNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.prftApvlNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.crApvlNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.assetNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.outOfWhNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.splyAbuseNodePrflCd);
        // add start 2023/04/25 QC#61337
        scrnMsg.addCheckItem(scrnMsg.manPrcNodePrflCd);
        // add end 2023/04/25 QC#61337
    }

    /**
     * setInitScreenFullandInsert
     * @param bMsg EZDBMsg
     */
    public static void setInitScreenFullandInsert(EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        // Header
        scrnMsg.dsOrdCatgCd.setInputProtected(false);
        scrnMsg.dsOrdTpNm.setInputProtected(false);
        scrnMsg.ordProcTpCd.setInputProtected(false);
        scrnMsg.dsOrdRsnGrpCd.setInputProtected(false);
        scrnMsg.dsOrdTpDescTxt.setInputProtected(false);
        scrnMsg.lineBizTpCd.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.actvFlg.setInputProtected(false);

        // Workflow Control
        scrnMsg.vldApvlNodeUsgFlg.setInputProtected(false);
        scrnMsg.vldApvlNodePrflCd.setInputProtected(false);
        scrnMsg.diChkNodeUsgFlg.setInputProtected(false);
        scrnMsg.diChkNodePrflCd.setInputProtected(false);
        scrnMsg.prftApvlNodeUsgFlg.setInputProtected(false);
        scrnMsg.prftApvlNodePrflCd.setInputProtected(false);
        scrnMsg.crApvlNodeUsgFlg.setInputProtected(false);
        scrnMsg.crApvlNodePrflCd.setInputProtected(false);
        scrnMsg.assetNodeUsgFlg.setInputProtected(false);
        scrnMsg.assetNodePrflCd.setInputProtected(false);
        scrnMsg.outOfWhNodeUsgFlg.setInputProtected(false);
        scrnMsg.outOfWhNodePrflCd.setInputProtected(false);
        scrnMsg.splyAbuseNodeUsgFlg.setInputProtected(false);
        scrnMsg.splyAbuseNodePrflCd.setInputProtected(false);

        // ProcessingRules
        // Accounting
        scrnMsg.dsInvTpCd.setInputProtected(false);
        // 2016/04/04 S21_NA#6353 MOD Start --------
        // Mod Start 2016/08/04 QC#12143 
        //        scrnMsg.dsInvTpDescTxt.setInputProtected(true);
        scrnMsg.dsInvTpNm.setInputProtected(true);
        // Mod End 2016/08/04 QC#12143
        // 2016/04/04 S21_NA#6353 MOD End ----------
        scrnMsg.autoCancOrdFlg.setInputProtected(false);

        // Defaults
        scrnMsg.defPrcCatgCd.setInputProtected(false);
        scrnMsg.prcCatgDescTxt_PR.setInputProtected(true);
        scrnMsg.defMaintPrcCatgCd.setInputProtected(false);
        scrnMsg.prcCatgDescTxt_SP.setInputProtected(true);
        scrnMsg.revFcstCd.setInputProtected(false);
        scrnMsg.frtCondCd.setInputProtected(false);
        scrnMsg.invPrintStyleCd.setInputProtected(false);
        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        scrnMsg.trtyGrpTpTxt.setInputProtected(false);
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)
        // Add 2020/04/24 QC#56638 Start
        scrnMsg.baseLocTxt.setInputProtected(false);
        // Add 2020/04/24 QC#56638 End

        scrnMsg.defBillToCustAcctCd.setInputProtected(false);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.defBillToCustCd.setInputProtected(false);
        scrnMsg.locDescTxt.setInputProtected(true);
        scrnMsg.defInstlTpCd.setInputProtected(false);
        scrnMsg.defShpgSvcLvlCd.setInputProtected(false);
        scrnMsg.defCarrSvcLvlCd.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.dropShipAvalFlg.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            scrnMsg.A.no(i).lineProcDfnSortNum_A.setInputProtected(false);
            scrnMsg.A.no(i).dsOrdLineCatgCd_A.setInputProtected(false);
            scrnMsg.A.no(i).ordProcTpCd_A.setInputProtected(false);
            scrnMsg.A.no(i).primLineCatgFlg_A.setInputProtected(false);
            scrnMsg.A.no(i).rmaPrimLineCatgFlg_A.setInputProtected(true); // Add 2017/09/11 QC#16346
            scrnMsg.A.no(i).ajeAcctBatCd_A.setInputProtected(false);
            scrnMsg.A.no(i).ajeAcctBatDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineDrctnCd_A.setInputProtected(false);
            scrnMsg.A.no(i).dsOrdLineDrctnNm_A.setInputProtected(false);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(false);
            scrnMsg.A.no(i).revFcstCd_A.setInputProtected(false);
            scrnMsg.A.no(i).actvFlg_A.setInputProtected(false);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcListTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(false);
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).omXtrnlSysDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).bizAppId_D.setInputProtected(true);
            scrnMsg.D.no(i).xxChkBox_D.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).locRoleTpDescTxt_E.setInputProtected(true);
            scrnMsg.E.no(i).xxChkBox_E.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).ordCatgCtxTpCd_F.setInputProtected(true);
            scrnMsg.F.no(i).dsOrdCatgDescTxt_F.setInputProtected(true);
            scrnMsg.F.no(i).dsOrdTpDescTxt_F.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).ordLineCtxTpCd_G.setInputProtected(true);
            scrnMsg.G.no(i).dsOrdLineCatgDescTxt_G.setInputProtected(true);
        }
    }

    /**
     * setEditScreenFullandInsert
     * @param bMsg EZDBMsg
     * @param function String
     * @param handle EZDCommonHandler
     */
    public static void setEditScreenFullandInsert(EZDBMsg bMsg, String function, EZDCommonHandler handle) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        // Header
        scrnMsg.dsOrdCatgCd.setInputProtected(true);

        if (FUNCTION_FULL.equals(function)) {
            scrnMsg.dsOrdTpNm.setInputProtected(false);
            scrnMsg.ordProcTpCd.setInputProtected(false);
            scrnMsg.effFromDt.setInputProtected(false);
        } else {
            scrnMsg.dsOrdTpNm.setInputProtected(true);
            scrnMsg.ordProcTpCd.setInputProtected(true);
            scrnMsg.effFromDt.setInputProtected(true);
        }
        scrnMsg.dsOrdRsnGrpCd.setInputProtected(false);
        scrnMsg.dsOrdTpDescTxt.setInputProtected(false);
        scrnMsg.lineBizTpCd.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);

        // Workflow Control
        scrnMsg.vldApvlNodeUsgFlg.setInputProtected(false);
        scrnMsg.vldApvlNodePrflCd.setInputProtected(false);
        scrnMsg.diChkNodeUsgFlg.setInputProtected(false);
        scrnMsg.diChkNodePrflCd.setInputProtected(false);
        scrnMsg.prftApvlNodeUsgFlg.setInputProtected(false);
        scrnMsg.prftApvlNodePrflCd.setInputProtected(false);
        scrnMsg.crApvlNodeUsgFlg.setInputProtected(false);
        scrnMsg.crApvlNodePrflCd.setInputProtected(false);
        scrnMsg.assetNodeUsgFlg.setInputProtected(false);
        scrnMsg.assetNodePrflCd.setInputProtected(false);
        scrnMsg.outOfWhNodeUsgFlg.setInputProtected(false);
        scrnMsg.outOfWhNodePrflCd.setInputProtected(false);
        scrnMsg.splyAbuseNodeUsgFlg.setInputProtected(false);
        scrnMsg.splyAbuseNodePrflCd.setInputProtected(false);

        // ProcessingRules
        // Accounting
        if (FUNCTION_FULL.equals(function)) {
            scrnMsg.dsInvTpCd.setInputProtected(false);
        } else {
            scrnMsg.dsInvTpCd.setInputProtected(true);
        }
        // 2016/04/04 S21_NA#6353 MOD Start ----------
        // Mod Start 2016/08/04 QC#12143
        //        scrnMsg.dsInvTpDescTxt.setInputProtected(true);
        scrnMsg.dsInvTpNm.setInputProtected(true);
        // Mod End 2016/08/04 QC#12143
        // 2016/04/04 S21_NA#6353 MOD Start ----------
        scrnMsg.autoCancOrdFlg.setInputProtected(false);

        // Defaults
        scrnMsg.defPrcCatgCd.setInputProtected(false);
        scrnMsg.prcCatgDescTxt_PR.setInputProtected(true);
        scrnMsg.defMaintPrcCatgCd.setInputProtected(false);
        scrnMsg.prcCatgDescTxt_SP.setInputProtected(true);
        scrnMsg.revFcstCd.setInputProtected(false);
        scrnMsg.frtCondCd.setInputProtected(false);
        scrnMsg.invPrintStyleCd.setInputProtected(false);
        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        scrnMsg.trtyGrpTpTxt.setInputProtected(false);
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)
        // Add 2020/04/24 QC#56638 Start
        scrnMsg.baseLocTxt.setInputProtected(false);
        // Add 2020/04/24 QC#56638 End

        scrnMsg.defBillToCustAcctCd.setInputProtected(false);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.defBillToCustCd.setInputProtected(false);
        scrnMsg.locDescTxt.setInputProtected(true);
        scrnMsg.defInstlTpCd.setInputProtected(false);
        scrnMsg.defShpgSvcLvlCd.setInputProtected(false);
        scrnMsg.defCarrSvcLvlCd.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.dropShipAvalFlg.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            setEditScreenFullandInsertForLineCatgAsgn(function, scrnMsg, i);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcListTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).omXtrnlSysDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).bizAppId_D.setInputProtected(true);
            scrnMsg.D.no(i).xxChkBox_D.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).locRoleTpDescTxt_E.setInputProtected(true);
            scrnMsg.E.no(i).xxChkBox_E.setInputProtected(false);

        }

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).ordCatgCtxTpCd_F.setInputProtected(true);
            scrnMsg.F.no(i).dsOrdCatgDescTxt_F.setInputProtected(true);
            scrnMsg.F.no(i).dsOrdTpDescTxt_F.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).ordLineCtxTpCd_G.setInputProtected(true);
            scrnMsg.G.no(i).dsOrdLineCatgDescTxt_G.setInputProtected(true);
        }

        if (FUNCTION_INSERT.equals(function)) {
            handle.setButtonEnabled(BTN_SEARCH_AJE, false);
            handle.setButtonEnabled(BTN_OPENWIN_AJE, false);
            handle.setButtonEnabled(BTN_SEARCH_ARTRAN, false);
            scrnMsg.xxLinkAncr_AR.setInputProtected(true);
        }
    }

    /**
     * @param function String
     * @param scrnMsg NWAL1700BMsg
     * @param i int
     */
    public static void setEditScreenFullandInsertForLineCatgAsgn(String function, NWAL1700BMsg scrnMsg, int i) {
        NWAL1700_ABMsg aScrnMsg = scrnMsg.A.no(i);

        if (!ZYPCommonFunc.hasValue(aScrnMsg.xxRowId_A) //
                || DB_FLAG_INSERT.equals(aScrnMsg.xxRowId_A.getValue())) {
            aScrnMsg.dsOrdLineCatgCd_A.setInputProtected(false);
            aScrnMsg.ordProcTpCd_A.setInputProtected(false);
            aScrnMsg.ajeAcctBatCd_A.setInputProtected(false);
            aScrnMsg.effFromDt_A.setInputProtected(false);
        } else {
            aScrnMsg.dsOrdLineCatgCd_A.setInputProtected(true);
            if (FUNCTION_FULL.equals(function)) {
                aScrnMsg.ordProcTpCd_A.setInputProtected(false);
                aScrnMsg.ajeAcctBatCd_A.setInputProtected(false);
                aScrnMsg.effFromDt_A.setInputProtected(false);
            } else {
                aScrnMsg.ordProcTpCd_A.setInputProtected(true);
                aScrnMsg.ajeAcctBatCd_A.setInputProtected(true);
                aScrnMsg.effFromDt_A.setInputProtected(true);
            }
        }

        aScrnMsg.primLineCatgFlg_A.setInputProtected(false);
        changeRmaPrimProtect(aScrnMsg, false); // Add 2017/09/11 QC#16346
        aScrnMsg.ajeAcctBatDescTxt_A.setInputProtected(true);
        aScrnMsg.dsOrdLineDrctnCd_A.setInputProtected(false);
        aScrnMsg.dsOrdLineDrctnNm_A.setInputProtected(true);
        aScrnMsg.effThruDt_A.setInputProtected(false);
        aScrnMsg.revFcstCd_A.setInputProtected(false);
        aScrnMsg.actvFlg_A.setInputProtected(false);
    }

    /**
     * setInitScreenRead
     * @param bMsg EZDBMsg
     * @param handle EZDCommonHandler
     */
    public static void setInitScreenRead(EZDBMsg bMsg, EZDCommonHandler handle) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        // Header
        scrnMsg.dsOrdCatgCd.setInputProtected(true);
        scrnMsg.dsOrdTpNm.setInputProtected(true);
        scrnMsg.ordProcTpCd.setInputProtected(true);
        scrnMsg.dsOrdRsnGrpCd.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt.setInputProtected(true);
        scrnMsg.lineBizTpCd.setInputProtected(true);
        scrnMsg.effFromDt.setInputProtected(true);
        scrnMsg.effThruDt.setInputProtected(true);
        scrnMsg.actvFlg.setInputProtected(true);

        // Workflow Control
        scrnMsg.vldApvlNodeUsgFlg.setInputProtected(true);
        scrnMsg.vldApvlNodePrflCd.setInputProtected(true);
        scrnMsg.diChkNodeUsgFlg.setInputProtected(true);
        scrnMsg.diChkNodePrflCd.setInputProtected(true);
        scrnMsg.prftApvlNodeUsgFlg.setInputProtected(true);
        scrnMsg.prftApvlNodePrflCd.setInputProtected(true);
        scrnMsg.crApvlNodeUsgFlg.setInputProtected(true);
        scrnMsg.crApvlNodePrflCd.setInputProtected(true);
        scrnMsg.assetNodeUsgFlg.setInputProtected(true);
        scrnMsg.assetNodePrflCd.setInputProtected(true);
        scrnMsg.outOfWhNodeUsgFlg.setInputProtected(true);
        scrnMsg.outOfWhNodePrflCd.setInputProtected(true);
        scrnMsg.splyAbuseNodeUsgFlg.setInputProtected(true);
        scrnMsg.splyAbuseNodePrflCd.setInputProtected(true);

        // ProcessingRules
        // Accounting
        scrnMsg.dsInvTpCd.setInputProtected(true);
        // 2016/04/04 S21_NA#6353 MOD Start ----------
        // Mod Start 2016/08/04 QC#12143
        //        scrnMsg.dsInvTpDescTxt.setInputProtected(true);
        scrnMsg.dsInvTpNm.setInputProtected(true);
        // Mod End 2016/08/04 QC#12143
        // 2016/04/04 S21_NA#6353 MOD Start ----------
        scrnMsg.autoCancOrdFlg.setInputProtected(true);

        // Defaults
        scrnMsg.defPrcCatgCd.setInputProtected(true);
        scrnMsg.prcCatgDescTxt_PR.setInputProtected(true);
        scrnMsg.defMaintPrcCatgCd.setInputProtected(true);
        scrnMsg.prcCatgDescTxt_SP.setInputProtected(true);
        scrnMsg.revFcstCd.setInputProtected(true);
        scrnMsg.frtCondCd.setInputProtected(true);
        scrnMsg.invPrintStyleCd.setInputProtected(true);
        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        scrnMsg.trtyGrpTpTxt.setInputProtected(true);
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)
        // Add 2020/04/24 QC#56638 Start
        scrnMsg.baseLocTxt.setInputProtected(true);
        // Add 2020/04/24 QC#56638 End
        scrnMsg.defBillToCustAcctCd.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.defBillToCustCd.setInputProtected(true);
        scrnMsg.locDescTxt.setInputProtected(true);
        scrnMsg.defInstlTpCd.setInputProtected(true);
        scrnMsg.defShpgSvcLvlCd.setInputProtected(true);
        scrnMsg.defCarrSvcLvlCd.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.dropShipAvalFlg.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            scrnMsg.A.no(i).lineProcDfnSortNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineCatgCd_A.setInputProtected(true);
            scrnMsg.A.no(i).ordProcTpCd_A.setInputProtected(true);
            scrnMsg.A.no(i).primLineCatgFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).rmaPrimLineCatgFlg_A.setInputProtected(true); // Add 2017/09/11 QC#16346
            scrnMsg.A.no(i).ajeAcctBatCd_A.setInputProtected(true);
            scrnMsg.A.no(i).ajeAcctBatDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineDrctnCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineDrctnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            scrnMsg.A.no(i).revFcstCd_A.setInputProtected(true);
            scrnMsg.A.no(i).actvFlg_A.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcListTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).omXtrnlSysDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).bizAppId_D.setInputProtected(true);
            scrnMsg.D.no(i).xxChkBox_D.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).locRoleTpDescTxt_E.setInputProtected(true);
            scrnMsg.E.no(i).xxChkBox_E.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).ordCatgCtxTpCd_F.setInputProtected(true);
            scrnMsg.F.no(i).dsOrdCatgDescTxt_F.setInputProtected(true);
            scrnMsg.F.no(i).dsOrdTpDescTxt_F.setInputProtected(true);

        }

        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).ordLineCtxTpCd_G.setInputProtected(true);
            scrnMsg.G.no(i).dsOrdLineCatgDescTxt_G.setInputProtected(true);

        }

        handle.setButtonEnabled(BTN_ADD_LINE, false);
        handle.setButtonEnabled(BTN_OPENWIN_LOV, false);
        handle.setButtonEnabled(BTN_SEARCH_AJE, false);
        handle.setButtonEnabled(BTN_OPENWIN_AJE, false);
        handle.setButtonEnabled(BTN_SEARCH_ARTRAN, false);
        handle.setButtonEnabled(BTN_SEARCH_BILLTOACCOUNT, false);
        handle.setButtonEnabled(BTN_SEARCH_PRICELIST, false);
        handle.setButtonEnabled(BTN_SEARCH_CARRIER, false);
        handle.setButtonEnabled(BTN_REMOVE_LINE, false);
        handle.setButtonEnabled(BTN_SEARCH_SERVICE_PRICE_LIST, false);
        handle.setButtonEnabled(BTN_SEARCH_BILLTO_LOCATION, false);
        scrnMsg.xxLinkAncr_AR.setInputProtected(true);
        scrnMsg.xxLinkAncr_CS.setInputProtected(true);
    }

    /**
     * setFocusRule
     * @param scrnMsg NWAL1700BMsg
     */
    public static void setFocusRule(NWAL1700BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCRN_ID_00, NWAL1700Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);
        ZYPGUIFocusRule fRule;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // mdseGrpCd_01
            fRule = new ZYPGUIFocusRule(NWAL1700Bean.xxChkBox_A + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NWAL1700Bean.actvFlg_A + "#" + (i - 1));
            }
            tblFocusRule.addRule(fRule);

            // mdseGrpCd_02
            fRule = new ZYPGUIFocusRule(NWAL1700Bean.ordProcTpCd_A + "#" + i);
            fRule.setNextId(NWAL1700Bean.primLineCatgFlg_A + "#" + i);
            tblFocusRule.addRule(fRule);

            // mdseGrpCd_03
            fRule = new ZYPGUIFocusRule(NWAL1700Bean.primLineCatgFlg_A + "#" + i);
            fRule.setPrevId(NWAL1700Bean.ordProcTpCd_A + "#" + i);
            tblFocusRule.addRule(fRule);

            // mdseGrpCd_05
            fRule = new ZYPGUIFocusRule(NWAL1700Bean.actvFlg_A + "#" + i);
            if ((i + 1) != scrnMsg.A.length()) {
                fRule.setNextId(NWAL1700Bean.xxChkBox_A + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);
        }
    }

    /**
     * setInitScreenValue
     * @param scrnMsg NWAL1700BMsg
     */
    //    public static void setInitScreenValue(NWAL1700BMsg scrnMsg) {
    //        scrnMsg.vldApvlNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        scrnMsg.diChkNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        scrnMsg.prftApvlNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        scrnMsg.crApvlNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        scrnMsg.assetNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        scrnMsg.outOfWhNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        scrnMsg.splyAbuseNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
    //        String slsDt = ZYPDateUtil.getSalesDate();
    //        scrnMsg.effFromDt.setValue(slsDt);
    //    }
    /**
     * clearMandantoryCheckLineCategory
     * @param scrnMsg NWAL1700BMsg
     */
    public static void clearMandantoryCheckLineCategory(NWAL1700BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (scrnMsg.A.no(i).dsOrdLineCatgCd_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("dsOrdLineCatgCd_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).dsOrdLineCatgCd_A.clearErrorInfo();
                }
            }

            if (scrnMsg.A.no(i).ordProcTpCd_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("ordProcTpCd_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).ordProcTpCd_A.clearErrorInfo();
                }
            }

            if (scrnMsg.A.no(i).ajeAcctBatCd_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("ajeAcctBatCd_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).ajeAcctBatCd_A.clearErrorInfo();
                }
            }

            if (scrnMsg.A.no(i).effFromDt_A.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_A.clearErrorInfo();
                }
            }
        }
    }

    // Add Start 2017/09/11 QC#16346
    /**
     * changeRmaPrimProtect
     * @param aMsg NWAL1700_ABMsg
     * @param changeFlg boolean
     */
    public static void changeRmaPrimProtect(NWAL1700_ABMsg aMsg, boolean changeFlg) {

        if (DS_ORD_LINE_DRCTN.INBOUND.equals(aMsg.dsOrdLineDrctnCd_A.getValue())) {
            aMsg.rmaPrimLineCatgFlg_A.setInputProtected(false);
        } else {
            if (changeFlg) {
                aMsg.rmaPrimLineCatgFlg_A.clear();
            }
            aMsg.rmaPrimLineCatgFlg_A.setInputProtected(true);
        }
    }
    // Add End 2017/09/11 QC#16346

}
