/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010.common;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0010.NSAL0010BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/11   Hitachi         T.Tomita        Update          QC#569
 * 2015/11/12   Hitachi         T.Tomita        Update          QC#566
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2015/11/24   Hitachi         T.Tomita        Update          QC#948
 * 2015/11/25   Hitachi         T.Tomita        Update          QC#952
 * 2015/12/03   Hitachi         K.Yamada        Update          CSA QC#950
 * 2015/12/08   Hitachi         K.Kasai         Update          QC#1644
 * 2016/01/04   Hitachi         T.Tomita        Update          QC#1029
 * 2016/01/12   Hitachi         T.Tomita        Update          QC#2866
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/02/09   Hitachi         A.Kohinata      Update          QC#947
 * 2016/02/22   Hitachi         T.Tomita        Update          QC#969
 * 2016/02/25   Hitachi         T.Tomita        Update          QC#942
 * 2016/03/30   Hitachi         T.Tomita        Update          QC#6092
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4889
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4953
 * 2016/04/08   Hitachi         M.Gotou         Update          QC#4905
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#7832
 * 2016/05/12   Hitachi         M.Gotou         Update          QC#7856
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/06/29   Hitachi         T.Tomita        Update          QC#6999
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/10/13   Hitachi         T.Tomita        Update          CSA QC#14734
 * 2016/12/13   Hitachi         K.Ochiai        Update          QC#16563
 * 2017/01/17   Hitachi         N.Arai          Update          QC#14614
 * 2017/01/16   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/01/27   Hitachi         K.Kojima        Update          QC#16600
 * 2017/03/07   Hitachi         T.Tomita        Update          QC#17990
 * 2017/07/20   Hitachi         U.Kim           Update          QC#19995
 * 2017/08/03   Hitachi         U.Kim           Update          QC#19995
 * 2017/08/22   Fujitsu         N.Sugiura       Update          QC#8598
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23753
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#26298
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 * 2018/09/28   Hitachi         K.Kitachi       Update          QC#27788
 * 2018/10/09   Hitachi         K.Kitachi       Update          QC#28703
 * 2019/11/28   Hitachi         K.Kitachi       Update          QC#53162
 * 2020/04/10   Hitachi         K.Sakurai       Update          QC#56456
 * 2020/11/16   CITS            R.Shimamoto     Update          QC#57983
 * 2023/07/10   Hitachi         Y.Nagasawa      Update          QC#61524
 * 2023/07/31   Hitachi         Y.nagasawa      Update          QC#61632
 * 2023/10/06   Hitachi         K.ishizuka      Update          QC#54186
 *</pre>
 */
public class NSAL0010CommonLogic {
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0010BMsg
     * @param userId String
     * @param isSearch boolean
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0010BMsg scrnMsg, String userId, boolean isSearch) {
        initCommonButton(userProfileService, handler);
        controlScreenFields(handler, scrnMsg, userId, isSearch, userProfileService);

    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     */
    private static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        // START 2017/01/16 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2017/01/16 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (functionIds.contains(FUNC_ID_UPDATE) || functionIds.contains(FUNC_ID_SER_CHANGE)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0010BMsg
     * @param userId String
     * @param isSearch boolean
     * @param userProfileService S21UserProfileService
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0010BMsg scrnMsg, String userId, boolean isSearch, S21UserProfileService userProfileService) {
        controlScreenHeaderFields(handler, scrnMsg, userId, isSearch, userProfileService);
    }

    /**
     * controlScreenHeaderFields
     * @param EZDCommonHandler handler
     * @param NPAL0110BMsg scrnMsg
     * @param String userId
     * @param userProfileService S21UserProfileService
     * @return
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0010BMsg scrnMsg, String userId, boolean isSearch, S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        // START 2015/11/12 T.Tomita [QC#566, MOD]
        scrnMsg.setInputProtected(false);
        // START 2015/11/25 T.Tomita [QC#952, ADD]
        scrnMsg.svcMachMstrPk_H1.setInputProtected(true);
        // END 2015/11/25 T.Tomita [QC#952, ADD]
        scrnMsg.mktMdlNm_H1.setInputProtected(true);
        scrnMsg.t_MdlNm_H1.setInputProtected(true);
        scrnMsg.custMachCtrlNum_H1.setInputProtected(true);
        // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        scrnMsg.swLicId_H1.setInputProtected(true);
        // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        // START 2016/02/26 T.Tomita [QC#942 ADD]
        // Stock Status and Location Status
        if (functionIds.contains(FUNC_ID_LGSC)) {
            // display
            ZYPEZDItemValueSetter.setValue(scrnMsg.stkStsNm_TL, STK_STS_TITLE_NM);
            setVisibilityItem(scrnMsg, "stkStsCd_H3", true);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locStsNm_TL, LOC_STS_TITLE_NM);
            setVisibilityItem(scrnMsg, "svcMachMstrLocStsCd_H3", true);
        } else {
            // no display
            scrnMsg.stkStsNm_TL.clear();
            setVisibilityItem(scrnMsg, "stkStsCd_H3", false);
            scrnMsg.locStsNm_TL.clear();
            setVisibilityItem(scrnMsg, "svcMachMstrLocStsCd_H3", false);
        }
        // END 2016/02/26 T.Tomita [QC#942 ADD]
        scrnMsg.xxChkBox_H1.setInputProtected(true);
        scrnMsg.serNum_H2.setInputProtected(true);
        // START 2016/06/10 [QC#9591, MOD]
        scrnMsg.xxYesNoNm_H1.setInputProtected(true);
        scrnMsg.iwrCondDescTxt_H1.setInputProtected(true);
        scrnMsg.lastUpdDt_H1.setInputProtected(true);
        // END   2016/06/10 [QC#9591, MOD]
        scrnMsg.iwrRgtnDt_H1.setInputProtected(true);
        scrnMsg.iwrDeinsDt_H1.setInputProtected(true);
        scrnMsg.xxLocRoleTpCdListTxt_M1.setInputProtected(true);
        // START 2015/11/24 T.Tomita [QC#948, MOD]
        scrnMsg.dsAcctNm_M1.setInputProtected(true);
        // START 2016/05/16 T.Tomita [QC#4578, DEL]
//        scrnMsg.xxComnScrColValTxt_M1.setInputProtected(true);
        // END 2016/05/16 T.Tomita [QC#4578, DEL]
        scrnMsg.xxLocRoleTpCdListTxt_M2.setInputProtected(true);
        scrnMsg.dsAcctNm_M2.setInputProtected(true);
        scrnMsg.xxComnScrColValTxt_M2.setInputProtected(true);
        scrnMsg.xxLocRoleTpCdListTxt_M3.setInputProtected(true);
        scrnMsg.dsAcctNm_M3.setInputProtected(true);
        scrnMsg.xxComnScrColValTxt_M3.setInputProtected(true);
        // END 2015/11/24 T.Tomita [QC#948, MOD]

        // START 2015/11/16 T.Tomita [QC#647, ADD]
        // Addl Attrb Tab
        scrnMsg.iwrCondCd_DS.setInputProtected(true);
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.E.no(i).techNm_E.setInputProtected(true);
        }
        // START 2015/11/16 T.Tomita [QC#647, ADD]

        // add start 2016/02/09 QC#947
        scrnMsg.techNm_D1.setInputProtected(true);
        scrnMsg.techNm_D2.setInputProtected(true);
        // add end 2016/02/09 QC#947

        String dplyTab = scrnMsg.xxDplyTab_01.getValue();
        if (Arrays.asList(NOT_DOWNLOAD_TAB).contains(dplyTab)) {
            handler.setButtonEnabled(BTN_CMN_BLANK6[0], false);
        } else {
            handler.setButtonEnabled(BTN_CMN_BLANK6[0], true);
        }
        if (Arrays.asList(NOT_SUBMIT_TAB).contains(dplyTab)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        } else {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
        }

        if (!TAB_MACH_CONFIG.equals(dplyTab)) {
            setHeaderProcectForNotConfigTab(handler, scrnMsg, true);
        } else {
            setHeaderProcectForNotConfigTab(handler, scrnMsg, false);
            // START 2015/11/16 T.Tomita [QC#647, MOD]
            // START 2016/02/26 T.Tomita [QC#942, MOD]
            if (ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1)) {
                scrnMsg.serNum_AC.setInputProtected(true);
                scrnMsg.serNum_H1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SEARCH[0], false);
                scrnMsg.mdseCd_AC.setInputProtected(true);
                scrnMsg.mdseCd_H1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME[0], false);
                // START 2016/05/12 M.Gotou [QC#7856, ADD]
                if (functionIds.contains(FUNC_ID_MDSE_EDIT)) {
                    scrnMsg.mdseCd_AC.setInputProtected(false);
                    scrnMsg.mdseCd_H1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME[0], true);
                }
                // END 2016/05/12 M.Gotou [QC#7856, ADD]
            }
            // END 2016/02/26 T.Tomita [QC#942, MOD]
            if (SVC_MACH_TP.MACHINE.equals(scrnMsg.svcMachTpCd_H1.getValue())) {
                handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], true);
                handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], true);
                handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], true);
            } else if (SVC_MACH_TP.ACCESSORY.equals(scrnMsg.svcMachTpCd_H1.getValue())) {
                handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], false);
                handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], false);
                handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], false);
            }
         // START 2017/01/17 N.Arai [QC#14614, MOD]
            // int configCnt = scrnMsg.A.getValidCount();
            int configCnt = 0;
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPageShowOfNum_A)) {
                configCnt = scrnMsg.xxPageShowOfNum_A.getValue().intValue();
            }
         // END 2017/01/17 N.Arai [QC#14614, MOD]
            // mod start 2016/03/30 CSA Defect#6092
            if (configCnt == 0) {
                handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], true);
                handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], false);
                handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], false);
            } else if (configCnt == CONFIG_LIST_MAX_SIZE) {
                handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], false);
                handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], true);
                handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], false);
            } else {
                int machCnt = scrnMsg.xxTotCnt_A.getValueInt();
                if (machCnt >= 2) {
                    handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], false);
                } else {
                    handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], true);
                }
                handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], true);
                handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], true);
            }
            // mod end 2016/03/30 CSA Defect#6092
            // END 2015/11/16 T.Tomita [QC#647, MOD]
            // START 2016/10/13 T.Tomita [QC#14734, ADD]
            if (SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(scrnMsg.svcMachMstrStsCd_H3.getValue())) {
                handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], false);
            }
            // END 2016/10/13 T.Tomita [QC#14734, ADD]
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1)) {
            handler.setButtonEnabled(BTN_OPEN_WIN_ATTACH[0], false);
            handler.setButtonEnabled(BTN_MOVE_WIN_CONFIG_HIST[0], false);
            // ADD START 2015/12/08 K.Kasai [QC1644]
            handler.setButtonEnabled(BTN_MOVE_WIN_MTR_READ_HIST[0], false);
            // ADD END 2015/12/08 K.Kasai [QC1644]
            setProtectTabLink(handler, scrnMsg, true);
        } else {
            handler.setButtonEnabled(BTN_OPEN_WIN_ATTACH[0], true);
            handler.setButtonEnabled(BTN_MOVE_WIN_CONFIG_HIST[0], true);
            // ADD START 2015/12/08 K.Kasai [QC1644]
            handler.setButtonEnabled(BTN_MOVE_WIN_MTR_READ_HIST[0], true);
            // ADD END 2015/12/08 K.Kasai [QC1644]
            setProtectTabLink(handler, scrnMsg, false);
        }

        //TODO delete following statements after completion development transition screen.
        // DEL START 2015/12/08 K.Kasai [QC1644]
//        handler.setButtonEnabled(BTN_MOVE_WIN_MTR_READ_HIST[0], false);
        // DEL END 2015/12/08 K.Kasai [QC1644]
        // START 2016/06/29 T.Tomita [QC#6999, MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.serNum_H1)) {
            handler.setButtonEnabled(BTN_MOVE_WIN_UPLOAD_CONTACT[0], true);
        } else {
            handler.setButtonEnabled(BTN_MOVE_WIN_UPLOAD_CONTACT[0], false);
        }
        // END 2016/06/29 T.Tomita [QC#6999, MOD]
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            scrnMsg.K.no(i).fsrNum_KA.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            scrnMsg.G.no(i).locNm_GB.setInputProtected(true);
            scrnMsg.G.no(i).shipToLocNm_GC.setInputProtected(true);
        }

        // START 2016/02/22 T.Tomita [QC#969, ADD]
        // contact tab insert row
        handler.setButtonEnabled(BTN_INSERT_CONTACT[0], false);
        if (ZYPCommonFunc.hasValue(scrnMsg.curLocAcctNum_M3)) {
            handler.setButtonEnabled(BTN_INSERT_CONTACT[0], true);
        }
        // END 2016/02/22 T.Tomita [QC#969, ADD]

        // roll type
        // Header
        if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE)) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue())) {
            scrnMsg.mdseCd_AC.setInputProtected(true);
            scrnMsg.mdseCd_H1.setInputProtected(true);
            scrnMsg.svcMachQty_H1.setInputProtected(true);
            scrnMsg.istlDt_H1.setInputProtected(true);
            scrnMsg.startDt_H1.setInputProtected(true);
            scrnMsg.svcMachRmvDt_H1.setInputProtected(true);
            scrnMsg.svcMachMstrStsCd_H3.setInputProtected(true);
            scrnMsg.svcMachUsgStsCd_H3.setInputProtected(true);
            scrnMsg.prntSerNum_AC.setInputProtected(true);
            scrnMsg.prntSerNum_H1.setInputProtected(true);
            scrnMsg.custMachCtrlNum_H1.setInputProtected(true);
            // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
            scrnMsg.swLicId_H1.setInputProtected(true);
            // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
            scrnMsg.svcMachTrxTpCd_H3.setInputProtected(true);
            scrnMsg.xxChkBox_H1.setInputProtected(true);
            // START 2018/05/28 K.Kitachi [QC#26298, ADD]
            scrnMsg.wtyAutoCratFlg_H1.setInputProtected(true);
            // END 2018/05/28 K.Kitachi [QC#26298, ADD]
            // START 2016/05/16 T.Tomita [QC#4578, MOD]
            scrnMsg.ownrAcctNum_M1.setInputProtected(true);
//            scrnMsg.ownrLocNum_M1.setInputProtected(true);
            scrnMsg.billToAcctNum_M2.setInputProtected(true);
            scrnMsg.indBillToLocNum_M2.setInputProtected(true);
            scrnMsg.curLocAcctNum_M3.setInputProtected(true);
            scrnMsg.indCurLocNum_M3.setInputProtected(true);
            // END 2016/05/16 T.Tomita [QC#4578, MOD]
            scrnMsg.stkStsCd_H3.setInputProtected(true);

            handler.setButtonEnabled(BTN_OPEN_WIN_OWNER_ACCT[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_BILL_TO_CUST[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_CUR_LOC[0], false);

        }
        if (!functionIds.contains(FUNC_ID_SER_CHANGE)) {
            scrnMsg.xxChkBox_H1.setInputProtected(true);
        }

        //Mach Config Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcConfigMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         // scrnMsg.A.no(i).mdseNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
         // END 2016/09/21 N.Arai [QC#11616, MOD]
            scrnMsg.A.no(i).mktMdlNm_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).istlDt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcMachMstrStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcMachMstrPk_AA.setInputProtected(false);
            scrnMsg.A.no(i).svcMachMstrPk_A2.setInputProtected(true);
            scrnMsg.A.no(i).rddDt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcMachRmvDt_A.setInputProtected(false);

             if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE)) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue())) {
                scrnMsg.A.no(i).svcMachMstrPk_AA.setInputProtected(true);
                scrnMsg.A.no(i).svcMachRmvDt_A.setInputProtected(true);
                handler.setButtonEnabled(OPENWIN_MACHIDA, i, false);
                continue;
            }
            // START 2019/11/28 K.Kitachi [QC#53162, DEL]
//            if (SVC_MACH_TP.ACCESSORY.equals(scrnMsg.svcMachTpCd_H1.getValue())) {
//                scrnMsg.A.no(i).svcMachRmvDt_A.setInputProtected(true);
//            }
            // END 2019/11/28 K.Kitachi [QC#53162, DEL]
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcConfigMstrPk_A)) {
                scrnMsg.A.no(i).svcMachRmvDt_A.setInputProtected(true);
                handler.setButtonEnabled(OPENWIN_MACHIDA, i, true);
            } else {
                handler.setButtonEnabled(OPENWIN_MACHIDA, i, false);
            }
            // START 2020/04/10 K.Sakurai [QC#56456, ADD]
            if (SVC_MACH_MSTR_STS.CREATED.equals(scrnMsg.svcMachMstrStsCd_H3.getValue()) || 
                    SVC_MACH_MSTR_STS.REMOVED.equals(scrnMsg.svcMachMstrStsCd_H3.getValue())) {
                scrnMsg.A.no(i).svcMachRmvDt_A.setInputProtected(true);
            }
            // END 2020/04/10 K.Sakurai [QC#56456, ADD]
        }

        //Solution Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).svcSlnSq_B.setInputProtected(true);
            scrnMsg.B.no(i).svcConfigMstrPk_B.setInputProtected(true);
            scrnMsg.B.no(i).mdseCd_B.setInputProtected(true);
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         //  scrnMsg.B.no(i).mdseNm_B.setInputProtected(true);
            scrnMsg.B.no(i).mdseDescShortTxt_B.setInputProtected(true);
         // END 2016/09/21 N.Arai [QC#11616, MOD]
            scrnMsg.B.no(i).t_MdlNm_B.setInputProtected(true);
            scrnMsg.B.no(i).svcMachMstrPk_B2.setInputProtected(true);
            scrnMsg.B.no(i).istlDt_B.setInputProtected(true);
            // START 2018/10/09 K.Kitachi [QC#28703, MOD]
//            scrnMsg.B.no(i).locNm_B1.setInputProtected(true);
//            scrnMsg.B.no(i).locNm_B2.setInputProtected(true);
//            scrnMsg.B.no(i).locNm_B3.setInputProtected(true);
            scrnMsg.B.no(i).dsAcctNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsAcctNm_B2.setInputProtected(true);
            scrnMsg.B.no(i).dsAcctNm_B3.setInputProtected(true);
            // END 2018/10/09 K.Kitachi [QC#28703, MOD]
        }
        // START 2015/11/11 T.Tomita [QC#569, MOD]
         if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE))
              || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue()) || scrnMsg.B.getValidCount() == 0) {
            scrnMsg.xxRadioBtn_B.setInputProtected(true);
            handler.setButtonEnabled(BTN_OPEN_WIN_LINK_NEW_CONFIG[0], false);
            handler.setButtonEnabled(BTN_REMOVE_CONFIG[0], false);
        } else {
            scrnMsg.xxRadioBtn_B.setInputProtected(false);
            handler.setButtonEnabled(BTN_OPEN_WIN_LINK_NEW_CONFIG[0], true);
            handler.setButtonEnabled(BTN_REMOVE_CONFIG[0], true);
        }
        // END 2015/11/11 T.Tomita [QC#569, MOD]

        //Contacts Tab
         if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE)) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue())) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).ctacPsnLastNm_C.setInputProtected(true);
                scrnMsg.C.no(i).ctacPsnFirstNm_C.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPntTpCd_CS.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPntValTxt_C.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPsnExtnNum_C.setInputProtected(true);
                scrnMsg.C.no(i).svcCtacTpCd_CS.setInputProtected(true);
                // START 2018/09/28 K.Kitachi [QC#27788, MOD]
//                // START 2017/08/22 N.Sugiura [QC#8598, DEL]
                scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
//                // END 2017/08/22 N.Sugiura [QC#8598, DEL]
                // END 2018/09/28 K.Kitachi [QC#27788, MOD]
                scrnMsg.C.no(i).effFromDt_C.setInputProtected(true);
                scrnMsg.C.no(i).effThruDt_C.setInputProtected(true);
            }
            scrnMsg.xxRadioBtn_C.setInputProtected(true);
            handler.setButtonEnabled(BTN_OPEN_WIN_CONTACT[0], false);
            handler.setButtonEnabled(BTN_INACTIVE_CONTACT[0], false);
            handler.setButtonEnabled(BTN_INSERT_CONTACT[0], false);
            handler.setButtonEnabled(BTN_DELETE_CONTACT[0], false);
        }
        // START 2017/08/22 N.Sugiura [QC#8598, ADD]
        // START 2018/09/28 K.Kitachi [QC#27788, DEL]
//        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
//        }
        // END 2018/09/28 K.Kitachi [QC#27788, DEL]
        // END 2017/08/22 N.Sugiura [QC#8598, ADD]

        //Add Attrb
         if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE)) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue())) {
            scrnMsg.ctrlFldTxt_01.setInputProtected(true);
            scrnMsg.ctrlFldTxt_02.setInputProtected(true);
            scrnMsg.ctrlFldTxt_03.setInputProtected(true);
            scrnMsg.ctrlFldTxt_04.setInputProtected(true);
            scrnMsg.ctrlFldTxt_05.setInputProtected(true);
            scrnMsg.ctrlFldTxt_06.setInputProtected(true);
            scrnMsg.prcContrNum_D.setInputProtected(true);
            scrnMsg.corpAdvtgStsCd_DS.setInputProtected(true);
            // START 2016/12/13 K.Ochiai [QC#16563, MOD]
            scrnMsg.custIssPoNum_D.setInputProtected(true);
            // END 2016/12/13 K.Ochiai [QC#16563, MOD]
            scrnMsg.dsPoExprDt_D.setInputProtected(true);
            scrnMsg.xxChkBox_D1.setInputProtected(true);
            scrnMsg.xxChkBox_D2.setInputProtected(true);
            scrnMsg.xxChkBox_D3.setInputProtected(true);
            scrnMsg.svcBrCd_D.setInputProtected(true);
            scrnMsg.svcBrCd_DA.setInputProtected(true);
            scrnMsg.xxChkBox_D4.setInputProtected(true);
            scrnMsg.iwrCondCd_DS.setInputProtected(true);
            scrnMsg.svcNtwkConnStsCd_DS.setInputProtected(true);
            scrnMsg.sldByLineBizTpCd_DS.setInputProtected(true);
            // START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//          scrnMsg.svcByLineBizTpCd_DS.setInputProtected(true);
            scrnMsg.istlBySvcPrvdPtyCd_DS.setInputProtected(true);
            scrnMsg.svcBySvcPrvdPtyCd_DS.setInputProtected(true);
            // END 2023/10/06 K.Ishizuka [QC#54186, MOD]
            scrnMsg.prfTechCd_DA.setInputProtected(true);
            scrnMsg.prfTechCd_D.setInputProtected(true);
            scrnMsg.reqTechCd_DA.setInputProtected(true);
            scrnMsg.reqTechCd_D.setInputProtected(true);
            scrnMsg.svcLicCnt_D.setInputProtected(true);
            scrnMsg.finBrCd_D.setInputProtected(true);
            scrnMsg.xxChkBox_D5.setInputProtected(true);
            scrnMsg.enetCmntTxt_01.setInputProtected(true);
            scrnMsg.enetCmntTxt_02.setInputProtected(true);
            scrnMsg.bizHrsSunFromTm_D.setInputProtected(true);
            scrnMsg.bizHrsSunToTm_D.setInputProtected(true);
            scrnMsg.bizHrsMonFriFromTm_D.setInputProtected(true);
            scrnMsg.bizHrsMonFriToTm_D.setInputProtected(true);
            scrnMsg.bizHrsSatFromTm_D.setInputProtected(true);
            scrnMsg.bizHrsSatToTm_D.setInputProtected(true);
            scrnMsg.ezUpTime_D.setInputProtected(true);
            scrnMsg.ezUpTimeZone_D.setInputProtected(true);
            scrnMsg.xxRadioBtn_E.setInputProtected(true);

            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                scrnMsg.E.no(i).nonPrfTechCd_E.setInputProtected(true);
                scrnMsg.E.no(i).techNm_E.setInputProtected(true);
                scrnMsg.E.no(i).effThruDt_E.setInputProtected(true);
            }
        }

        // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
        //Current Loc
//        if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE)) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue())) {
//            scrnMsg.locNm_F.setInputProtected(true);
//            scrnMsg.addlLocNm_F.setInputProtected(true);
//            scrnMsg.ctryCd_FS.setInputProtected(true);
//            scrnMsg.firstLineAddr_F.setInputProtected(true);
//            scrnMsg.scdLineAddr_F.setInputProtected(true);
//            scrnMsg.thirdLineAddr_F.setInputProtected(true);
//            scrnMsg.frthLineAddr_F.setInputProtected(true);
//            scrnMsg.svcMachFlNm_F.setInputProtected(true);
//            scrnMsg.postCd_F.setInputProtected(true);
//            scrnMsg.ctyAddr_F.setInputProtected(true);
//            scrnMsg.stCd_F.setInputProtected(true);
//            scrnMsg.cntyNm_F.setInputProtected(true);
//            scrnMsg.provNm_F.setInputProtected(true);
//        }
        // END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

        //Sales History
        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
         // START 2017/02/06 N.Arai [QC#17197, MOD]
            // scrnMsg.G.no(i).cpoOrdTs_G.setInputProtected(true);
            scrnMsg.G.no(i).cpoOrdDt_G.setInputProtected(true);
         // END 2017/02/06 N.Arai [QC#17197, MOD]
            scrnMsg.G.no(i).dsOrdCatgDescTxt_G.setInputProtected(true);
            scrnMsg.G.no(i).dsOrdTpDescTxt_G.setInputProtected(true);
            scrnMsg.G.no(i).dsOrdLineCatgDescTxt_G.setInputProtected(true);
            // START 2015/01/12 T.Tomita [QC#2866, ADD]
            // scrnMsg.G.no(i).shipToLocNm_G.setInputProtected(true);
            // END 2015/01/12 T.Tomita [QC#2866, ADD]
            scrnMsg.G.no(i).shipToAddr_G.setInputProtected(true);
        }

        //IB History
        for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
            scrnMsg.I.no(i).trxRgtnDt_I.setInputProtected(true);
            // START 2016/04/04 M.Gotou [QC#4953, MOD]
//            scrnMsg.I.no(i).updFldTxt_I.setInputProtected(true);
            scrnMsg.I.no(i).machHistAttrbLongNm_I.setInputProtected(true);
            // END 2016/04/04 M.Gotou [QC#4953, MOD]
            scrnMsg.I.no(i).oldValTxt_I.setInputProtected(true);
            scrnMsg.I.no(i).newValTxt_I.setInputProtected(true);
            scrnMsg.I.no(i).updUsrId_I.setInputProtected(true);
        }

        //Contract Summary
        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            scrnMsg.J.no(i).dsContrCtrlStsNm_J.setInputProtected(true);
            scrnMsg.J.no(i).contrEffFromDt_J.setInputProtected(true);
            scrnMsg.J.no(i).contrEffThruDt_J.setInputProtected(true);
            scrnMsg.J.no(i).dsContrCatgDescTxt_J.setInputProtected(true);
            scrnMsg.J.no(i).xxChkBox_J.setInputProtected(true);
            scrnMsg.J.no(i).bllgCycleDescTxt_J.setInputProtected(true);
            scrnMsg.J.no(i).mtrReadMethDescTxt_J.setInputProtected(true);
            scrnMsg.J.no(i).svcContrRefCmntTxt_J.setInputProtected(true);
            scrnMsg.J.no(i).svcContrBrDescTxt_J.setInputProtected(true);
        }

        //Service Call History
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            //START 2017/07/20 U.Kim [QC#19995, ADD]
            scrnMsg.K.no(i).svcTaskNum_K.setInputProtected(true);
            //START 2017/08/03 U.Kim [QC#19995, MOD]
            //scrnMsg.K.no(i).dsSvcCallTpDescTxt_K.setInputProtected(true);
            scrnMsg.K.no(i).xxScrItem54Txt_K.setInputProtected(true);
            //END 2017/08/03 U.Kim [QC#19995, MOD]
            //END 2017/07/20 U.Kim [QC#19995, ADD]
            scrnMsg.K.no(i).locNm_K.setInputProtected(true);
            scrnMsg.K.no(i).svcTaskStsNm_K.setInputProtected(true);
            scrnMsg.K.no(i).svcTaskRcvDt_K.setInputProtected(true);
            scrnMsg.K.no(i).svcPblmTpDescTxt_K.setInputProtected(true);
            scrnMsg.K.no(i).svcPblmCrctTpDescTxt_K.setInputProtected(true);
            scrnMsg.K.no(i).svcTaskSchdDt_K.setInputProtected(true);
            scrnMsg.K.no(i).svcTaskCloDt_K.setInputProtected(true);
            scrnMsg.K.no(i).xxFullNm_K.setInputProtected(true);
        }

        //Invoice History
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            scrnMsg.L.no(i).sysSrcDescTxt_L.setInputProtected(true);
            scrnMsg.L.no(i).locNm_L1.setInputProtected(true);
            scrnMsg.L.no(i).locNm_L2.setInputProtected(true);
            scrnMsg.L.no(i).invTpDescTxt_L.setInputProtected(true);
            scrnMsg.L.no(i).invTotDealNetAmt_L.setInputProtected(true);
            scrnMsg.L.no(i).dealRmngBalGrsAmt_L.setInputProtected(true);
            scrnMsg.L.no(i).xxApplyGrsAmt_L.setInputProtected(true);
            scrnMsg.L.no(i).netDueDt_L.setInputProtected(true);
            scrnMsg.L.no(i).cpoOrdNum_L.setInputProtected(true);
            scrnMsg.L.no(i).invDt_L.setInputProtected(true);
            scrnMsg.L.no(i).acctDt_L.setInputProtected(true);

            scrnMsg.L.no(i).invTotDealNetAmt_L.setAppFracDigit(2);
            scrnMsg.L.no(i).dealRmngBalGrsAmt_L.setAppFracDigit(2);
            scrnMsg.L.no(i).xxApplyGrsAmt_L.setAppFracDigit(2);
        }

        //button
         if ((!functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE)) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.svcMachMaintAvalFlg.getValue())) {
            // Header
            handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_OWNER_ACCT[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_BILL_TO_CUST[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_CUR_LOC[0], false);
            handler.setButtonEnabled(BTN_MOVE_WIN_CONFIG_HIST[0], false);
            handler.setButtonEnabled(BTN_MOVE_WIN_MTR_READ_HIST[0], false);
            handler.setButtonEnabled(BTN_MOVE_WIN_UPLOAD_CONTACT[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_ATTACH[0], false);

            // Mach Config Tab
            handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], false);
            handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], false);
            handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], false);

            // Solution Tab
            handler.setButtonEnabled(BTN_OPEN_WIN_LINK_NEW_CONFIG[0], false);
            handler.setButtonEnabled(BTN_REMOVE_CONFIG[0], false);

            // Contact Tab
            handler.setButtonEnabled(BTN_INACTIVE_CONTACT[0], false);
            handler.setButtonEnabled(BTN_INSERT_CONTACT[0], false);
            handler.setButtonEnabled(BTN_DELETE_CONTACT[0], false);

            // Addl Attrb Tab
            handler.setButtonEnabled(BTN_INSERT_DONOTSENDTECH[0], false);
            handler.setButtonEnabled(BTN_DELETE_DONOTSENDTECH[0], false);

            // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
            // Current Loc Tab
            // handler.setButtonEnabled(BTN_SELECT_POSTALCODE[0], false);
            // ENDT 2023/07/10 Y.Nagasawa [QC#61524, DEL]

            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        // END 2015/11/12 T.Tomita [QC#566, MOD]

        // START 2018/02/19 M.Kidokoro [QC#23753, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1)) {
            if (!functionIds.isEmpty()) {
                handler.setButtonEnabled(BTN_MOVE_WIN_CONFIG_HIST[0], true);
                handler.setButtonEnabled(BTN_OPEN_WIN_ATTACH[0], true);
                handler.setButtonEnabled(BTN_MOVE_WIN_MTR_READ_HIST[0], true);
            }
        } else {
            handler.setButtonEnabled(BTN_MOVE_WIN_CONFIG_HIST[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_ATTACH[0], false);
            handler.setButtonEnabled(BTN_MOVE_WIN_MTR_READ_HIST[0], false);
        }
        // END 2018/02/19 M.Kidokoro [QC#23753, ADD]
        
        // START 2020/04/10 K.Sakurai [QC#56456, ADD]
        if (SVC_MACH_MSTR_STS.CREATED.equals(scrnMsg.svcMachMstrStsCd_H3.getValue()) || 
                SVC_MACH_MSTR_STS.REMOVED.equals(scrnMsg.svcMachMstrStsCd_H3.getValue())) {
            handler.setButtonEnabled(BTN_INSERT_PARENT_LINE[0], false);
            handler.setButtonEnabled(BTN_DELETE_MACHINE_LINE[0], false);
            handler.setButtonEnabled(BTN_INSERT_CHILD_LINE[0], false);
        }
        // END 2020/04/10 K.Sakurai [QC#56456, ADD]

        // START 2020/11/16 R.Shimamoto [QC#57983, ADD]
        scrnMsg.finBrCd_D.setInputProtected(true);
        // END 2020/11/16 R.Shimamoto [QC#57983, ADD]
    }

    /**
     * Check item attribute
     * @param scrnMsg NSAL0010BMsg
     */
    public static void checkInputItemsAttribute(NSAL0010BMsg scrnMsg) {
        if (TAB_MACH_CONFIG.equals(scrnMsg.xxDplyTab_01.getValue())) {
            addCheckItem_Header(scrnMsg);
            addCheckItem_TabDetail(scrnMsg);
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab_01.getValue())) {
            addCheckItem_Contacts(scrnMsg);
        } else if (TAB_ADDL_ATTRB.equals(scrnMsg.xxDplyTab_01.getValue())) {
            addCheckItem_Addl(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * Add check items - Header
     * @param scrnMsg NSAL0010BMsg
     */
    private static void addCheckItem_Header(NSAL0010BMsg scrnMsg) {
        // START 2015/11/16 T.Tomita [QC#647, DEL]
        // scrnMsg.addCheckItem(scrnMsg.serNum_H1);
        // END 2015/11/16 T.Tomita [QC#647, DEL]
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.startDt_H1);
        // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        scrnMsg.addCheckItem(scrnMsg.swLicId_H1);
        // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        
    }

    /**
     * Add check items - TAB Detail
     * @param scrnMsg NSAL0010BMsg
     */
    private static void addCheckItem_TabDetail(NSAL0010BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcMachRmvDt_A);
        }
    }

    private static void addCheckItem_Contacts(NSAL0010BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).ctacPsnLastNm_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).ctacPsnFirstNm_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).dsCtacPntTpCd_CS);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).dsCtacPntValTxt_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).dsCtacPsnExtnNum_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).svcCtacTpCd_CS);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C);
        }
    }

    private static void addCheckItem_Addl(NSAL0010BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.ctrlFldTxt_01);
        scrnMsg.addCheckItem(scrnMsg.ctrlFldTxt_02);
        scrnMsg.addCheckItem(scrnMsg.ctrlFldTxt_03);
        scrnMsg.addCheckItem(scrnMsg.ctrlFldTxt_04);
        scrnMsg.addCheckItem(scrnMsg.ctrlFldTxt_05);
        scrnMsg.addCheckItem(scrnMsg.ctrlFldTxt_06);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum_D);
        scrnMsg.addCheckItem(scrnMsg.corpAdvtgStsCd_DS);
        // START 2016/12/13 K.Ochiai [QC#16563, MOD]
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_D);
        // END 2016/12/13 K.Ochiai [QC#16563, MOD]
        scrnMsg.addCheckItem(scrnMsg.dsPoExprDt_D);
        scrnMsg.addCheckItem(scrnMsg.svcBrCd_D);
        scrnMsg.addCheckItem(scrnMsg.iwrCondCd_DS);
        scrnMsg.addCheckItem(scrnMsg.svcNtwkConnStsCd_DS);
        scrnMsg.addCheckItem(scrnMsg.sldByLineBizTpCd_DS);
        // START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//      scrnMsg.addCheckItem(scrnMsg.svcByLineBizTpCd_DS);
        scrnMsg.addCheckItem(scrnMsg.istlBySvcPrvdPtyCd_DS);
        scrnMsg.addCheckItem(scrnMsg.svcBySvcPrvdPtyCd_DS);
        // END 2023/10/06 K.Ishizuka [QC#54186, MOD]
        scrnMsg.addCheckItem(scrnMsg.prfTechCd_D);
        scrnMsg.addCheckItem(scrnMsg.reqTechCd_D);
        scrnMsg.addCheckItem(scrnMsg.svcLicCnt_D);
        scrnMsg.addCheckItem(scrnMsg.finBrCd_D);
        scrnMsg.addCheckItem(scrnMsg.enetCmntTxt_01);
        scrnMsg.addCheckItem(scrnMsg.enetCmntTxt_02);
        scrnMsg.addCheckItem(scrnMsg.bizHrsSunFromTm_D);
        scrnMsg.addCheckItem(scrnMsg.bizHrsSunToTm_D);
        scrnMsg.addCheckItem(scrnMsg.bizHrsMonFriFromTm_D);
        scrnMsg.addCheckItem(scrnMsg.bizHrsMonFriToTm_D);
        // START 2016/04/08 M.Gotou [QC#4905 ADD]
        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_D1);
        scrnMsg.addCheckItem(scrnMsg.xxSvcToHourMnTxt_D1);
        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_D2);
        scrnMsg.addCheckItem(scrnMsg.xxSvcToHourMnTxt_D2);
        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_D3);
        scrnMsg.addCheckItem(scrnMsg.xxSvcToHourMnTxt_D3);
        // END 2016/04/08 M.Gotou [QC#4905 ADD]

        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).nonPrfTechCd_E);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).effThruDt_E);
        }
    }

    private static void setHeaderProcectForNotConfigTab(EZDCommonHandler handler, NSAL0010BMsg scrnMsg, boolean cntrlFlg) {
        scrnMsg.serNum_AC.setInputProtected(cntrlFlg);
        scrnMsg.serNum_H1.setInputProtected(cntrlFlg);
        scrnMsg.mdseCd_AC.setInputProtected(cntrlFlg);
        scrnMsg.mdseCd_H1.setInputProtected(cntrlFlg);
        scrnMsg.svcMachQty_H1.setInputProtected(cntrlFlg);
        scrnMsg.istlDt_H1.setInputProtected(cntrlFlg);
        scrnMsg.startDt_H1.setInputProtected(cntrlFlg);
        scrnMsg.svcMachRmvDt_H1.setInputProtected(cntrlFlg);
        scrnMsg.svcMachMstrStsCd_H3.setInputProtected(cntrlFlg);
        scrnMsg.svcMachUsgStsCd_H3.setInputProtected(cntrlFlg);
        scrnMsg.prntSerNum_AC.setInputProtected(cntrlFlg);
        scrnMsg.prntSerNum_H1.setInputProtected(cntrlFlg);
        scrnMsg.custMachCtrlNum_H1.setInputProtected(cntrlFlg);
        // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        scrnMsg.swLicId_H1.setInputProtected(cntrlFlg);
        // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        scrnMsg.svcMachTrxTpCd_H3.setInputProtected(cntrlFlg);
        scrnMsg.xxChkBox_H1.setInputProtected(cntrlFlg);
        // START 2018/05/28 K.Kitachi [QC#26298, ADD]
        scrnMsg.wtyAutoCratFlg_H1.setInputProtected(cntrlFlg);
        // END 2018/05/28 K.Kitachi [QC#26298, ADD]
        // START 2018/08/23 K.Kitachi [QC#27773, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.stdWtyFlg_H1.getValue())) {
            scrnMsg.wtyAutoCratFlg_H1.clear();
            scrnMsg.wtyAutoCratFlg_H1.setInputProtected(true);
        }
        // END 2018/08/23 K.Kitachi [QC#27773, ADD]
        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        scrnMsg.ownrAcctNum_M1.setInputProtected(cntrlFlg);
//        scrnMsg.ownrLocNum_M1.setInputProtected(cntrlFlg);
        scrnMsg.billToAcctNum_M2.setInputProtected(cntrlFlg);
        scrnMsg.indBillToLocNum_M2.setInputProtected(cntrlFlg);
        scrnMsg.curLocAcctNum_M3.setInputProtected(cntrlFlg);
        scrnMsg.indCurLocNum_M3.setInputProtected(cntrlFlg);
        // END 2016/05/16 T.Tomita [QC#4578, MOD]
        scrnMsg.stkStsCd_H3.setInputProtected(cntrlFlg);
        // START 2016/02/26 T.Tomita [QC#942 ADD]
        scrnMsg.svcMachMstrLocStsCd_H3.setInputProtected(cntrlFlg);
        // END 2016/02/26 T.Tomita [QC#942 ADD]

        handler.setButtonEnabled(BTN_SEARCH[0], !cntrlFlg);
        handler.setButtonEnabled(BTN_OPEN_WIN_OWNER_ACCT[0], !cntrlFlg);
        handler.setButtonEnabled(BTN_OPEN_WIN_BILL_TO_CUST[0], !cntrlFlg);
        handler.setButtonEnabled(BTN_OPEN_WIN_CUR_LOC[0], !cntrlFlg);
        handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME[0], !cntrlFlg);
    }

    private static void setProtectTabLink(EZDCommonHandler handler, NSAL0010BMsg scrnMsg, boolean cntrlFlg) {
        // add start 2017/03/07 CSA Defect#17990
        scrnMsg.xxTabProt_11.setInputProtected(cntrlFlg);
        // add end 2017/03/07 CSA Defect#17990
        scrnMsg.xxTabProt_21.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_31.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_41.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_51.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_61.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_71.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_81.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_91.setInputProtected(cntrlFlg);
        scrnMsg.xxTabProt_A1.setInputProtected(cntrlFlg);
    }


    /**
     * clear Popup Parameter
     * @param scrnMsg NSAL0010BMsg
     */
    public static void clearPopupParameter(NSAL0010BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        // START 2016/01/04 T.Tomita [QC#1029, ADD]
        scrnMsg.xxPopPrm_23.clear();
        // END 2016/01/04 T.Tomita [QC#1029, ADD]
        // add start 2015/12/03 CSA Defect#950
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondCd_P.clear();
        scrnMsg.xxCondNm_P.clear();
        // add end 2015/12/03 CSA Defect#950
        // add start 2016/02/09 QC#947
        ZYPTableUtil.clear(scrnMsg.Z);
        // add end 2016/02/09 QC#947
    }

    /**
     * <pre>
     * Check addCheckItem return UPDATE
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NSAL0010BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.serNum_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachQty_H1);
        scrnMsg.addCheckItem(scrnMsg.istlDt_H1);
        scrnMsg.addCheckItem(scrnMsg.startDt_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachRmvDt_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachMstrStsCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcMachUsgStsCd_H3);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_H3);
        // START 2016/02/26 T.Tomita [QC#942 ADD]
        scrnMsg.addCheckItem(scrnMsg.svcMachMstrLocStsCd_H3);
        // END 2016/02/26 T.Tomita [QC#942 ADD]
        scrnMsg.addCheckItem(scrnMsg.prntSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachTrxTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.serNum_H2);
        // START 2018/05/28 K.Kitachi [QC#26298, ADD]
        scrnMsg.addCheckItem(scrnMsg.wtyAutoCratFlg_H1);
        // END 2018/05/28 K.Kitachi [QC#26298, ADD]
        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        scrnMsg.addCheckItem(scrnMsg.ownrAcctNum_M1);
//        scrnMsg.addCheckItem(scrnMsg.ownrLocNum_M1);
        scrnMsg.addCheckItem(scrnMsg.billToAcctNum_M2);
        scrnMsg.addCheckItem(scrnMsg.indBillToLocNum_M2);
        scrnMsg.addCheckItem(scrnMsg.curLocAcctNum_M3);
        scrnMsg.addCheckItem(scrnMsg.indCurLocNum_M3);
        // END 2016/05/16 T.Tomita [QC#4578, MOD]

        // START 2015/11/16 T.Tomita [QC#647, ADD]
        // Machine Configuration Tab
        if (TAB_MACH_CONFIG.equals(scrnMsg.xxDplyTab_01.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcMachRmvDt_A);
            }
        }
        // END 2015/11/16 T.Tomita [QC#647, ADD]
    }

    // START 2015/11/16 T.Tomita [QC#647, ADD]
    /**
     * get Scrn Tm
     * @param scrnMsg NSAL0010BMsg
     */
    public static void getScrnTm(NSAL0010BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_D1);
        scrnMsg.addCheckItem(scrnMsg.xxSvcToHourMnTxt_D1);
        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_D2);
        scrnMsg.addCheckItem(scrnMsg.xxSvcToHourMnTxt_D2);
        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_D3);
        scrnMsg.addCheckItem(scrnMsg.xxSvcToHourMnTxt_D3);

        // Business Hour Sun
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_D1)
                && (!ZYPCommonFunc.isNumberCheck(scrnMsg.xxSvcFromHourMnTxt_D1.getValue().replace(CLN, "")) || !ZYPCommonFunc.isCheckTime(scrnMsg.xxSvcFromHourMnTxt_D1.getValue().replace(CLN, "")))) {
            scrnMsg.xxSvcFromHourMnTxt_D1.setErrorInfo(1, "NSAM0040E", new String[] {"Business Hour Sun" });
            return;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_D1)
                && (!ZYPCommonFunc.isNumberCheck(scrnMsg.xxSvcToHourMnTxt_D1.getValue().replace(CLN, "")) || !ZYPCommonFunc.isCheckTime(scrnMsg.xxSvcToHourMnTxt_D1.getValue().replace(CLN, "")))) {
            scrnMsg.xxSvcToHourMnTxt_D1.setErrorInfo(1, "NSAM0040E", new String[] {"Business Hour Sun" });
            return;
        }
        scrnMsg.bizHrsSunFromTm_D.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_D1.getValue()));
        scrnMsg.bizHrsSunToTm_D.setValue(getTm(scrnMsg.xxSvcToHourMnTxt_D1.getValue()));

        // Business Hour Mon-Fri
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_D2)
                && (!ZYPCommonFunc.isNumberCheck(scrnMsg.xxSvcFromHourMnTxt_D2.getValue().replace(CLN, "")) || !ZYPCommonFunc.isCheckTime(scrnMsg.xxSvcFromHourMnTxt_D2.getValue().replace(CLN, "")))) {
            scrnMsg.xxSvcFromHourMnTxt_D2.setErrorInfo(1, "NSAM0040E", new String[] {"Business Hour Mon-Fri" });
            return;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_D2)
                && (!ZYPCommonFunc.isNumberCheck(scrnMsg.xxSvcToHourMnTxt_D2.getValue().replace(CLN, "")) || !ZYPCommonFunc.isCheckTime(scrnMsg.xxSvcToHourMnTxt_D2.getValue().replace(CLN, "")))) {
            scrnMsg.xxSvcToHourMnTxt_D2.setErrorInfo(1, "NSAM0040E", new String[] {"Business Hour Mon-Fri" });
            return;
        }
        scrnMsg.bizHrsMonFriFromTm_D.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_D2.getValue()));
        scrnMsg.bizHrsMonFriToTm_D.setValue(getTm(scrnMsg.xxSvcToHourMnTxt_D2.getValue()));

        // Business Hour Sat
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_D3)
                && (!ZYPCommonFunc.isNumberCheck(scrnMsg.xxSvcFromHourMnTxt_D3.getValue().replace(CLN, "")) || !ZYPCommonFunc.isCheckTime(scrnMsg.xxSvcFromHourMnTxt_D3.getValue().replace(CLN, "")))) {
            scrnMsg.xxSvcFromHourMnTxt_D3.setErrorInfo(1, "NSAM0040E", new String[] {"Business Hour Sat" });
            return;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcToHourMnTxt_D3)
                && (!ZYPCommonFunc.isNumberCheck(scrnMsg.xxSvcToHourMnTxt_D3.getValue().replace(CLN, "")) || !ZYPCommonFunc.isCheckTime(scrnMsg.xxSvcToHourMnTxt_D3.getValue().replace(CLN, "")))) {
            scrnMsg.xxSvcToHourMnTxt_D3.setErrorInfo(1, "NSAM0040E", new String[] {"Business Hour Sat" });
            return;
        }
        scrnMsg.bizHrsSatFromTm_D.setValue(getTm(scrnMsg.xxSvcFromHourMnTxt_D3.getValue()));
        scrnMsg.bizHrsSatToTm_D.setValue(getTm(scrnMsg.xxSvcToHourMnTxt_D3.getValue()));
    }

    /**
     * set Scrn Tm
     * @param scrnMsg NSAL0010BMsg
     */
    public static void setScrnTm(NSAL0010BMsg scrnMsg) {
        scrnMsg.xxSvcFromHourMnTxt_D1.setValue(setTm(scrnMsg.bizHrsSunFromTm_D.getValue()));
        scrnMsg.xxSvcToHourMnTxt_D1.setValue(setTm(scrnMsg.bizHrsSunToTm_D.getValue()));
        scrnMsg.xxSvcFromHourMnTxt_D2.setValue(setTm(scrnMsg.bizHrsMonFriFromTm_D.getValue()));
        scrnMsg.xxSvcToHourMnTxt_D2.setValue(setTm(scrnMsg.bizHrsMonFriToTm_D.getValue()));
        scrnMsg.xxSvcFromHourMnTxt_D3.setValue(setTm(scrnMsg.bizHrsSatFromTm_D.getValue()));
        scrnMsg.xxSvcToHourMnTxt_D3.setValue(setTm(scrnMsg.bizHrsSatToTm_D.getValue()));
    }

    /**
     * get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(CLN, "");
        }
        return tm;
    }

    /**
     * set Time
     * @param tm time
     * @return time
     */
    public static String setTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(tm);
            if (strBuf.length() > 2) {
                strBuf.insert(2, CLN);
            }
            return strBuf.toString();
        }
        return tm;
    }
    // END 2015/11/16 T.Tomita [QC#647, ADD]

    // START 2016/02/04 T.Tomita [QC#3312, ADD]
    // START 2016/05/12 T.Tomita [QC#7832, MOD]
    // START 2017/01/27 K.Kojima [QC#16600,MOD]
    /**
     * set Config Search Pop Up Input Param
     * @param scrnMsg NSAL0010BMsg
     * @param mode String
     * @param serNumSetFlag boolean
     * @return params
     */
    public static Object[] setConfigSearchPopUpInputParam(NSAL0010BMsg scrnMsg, String mode, boolean serNumSetFlag) {
        // END 2017/01/27 K.Kojima [QC#16600,MOD]
        // START 2016/04/04 M.Gotou [QC#4889, MOD]
        scrnMsg.xxModeCd_O.clear();
        scrnMsg.mdlNm_O.clear();
        scrnMsg.svcConfigMstrPk_O.clear();
        ZYPTableUtil.clear(scrnMsg.O);
        ZYPTableUtil.clear(scrnMsg.Q);
        Object[] params = new Object[PARAM_LENGTH_NSAL1240];
        if (OPENWIN_MACHIDA.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_O, MODE_03);
            params[PARAM_INDEX_0] = scrnMsg.xxModeCd_O;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_VD, mode);
        // START 2017/01/27 K.Kojima [QC#16600,ADD]
        if (serNumSetFlag) {
            params[PARAM_INDEX_02] = scrnMsg.serNum_H1;
        }
        // END 2017/01/27 K.Kojima [QC#16600,ADD]
        params[PARAM_INDEX_26] = scrnMsg.mdlNm_O;
        params[PARAM_INDEX_29] = scrnMsg.svcConfigMstrPk_O;
        params[PARAM_INDEX_30] = scrnMsg.O;
        params[PARAM_INDEX_31] = scrnMsg.xxModeCd_VD;
        params[PARAM_INDEX_32] = scrnMsg.Q;
        return params;
        // END 2016/04/04 M.Gotou [QC#4889, MOD]
    }
    // END 2016/05/12 T.Tomita [QC#7832, MOD]
    // END 2016/02/04 T.Tomita [QC#3312, ADD]

    // START 2016/05/12 T.Tomita [QC#7832, ADD]
    /**
     * set Config Search Pop Up Input Param
     * @param scrnMsg NSAL0010BMsg
     * @return params
     */
    public static Object[] setConfigSearchPopUpInputParam(NSAL0010BMsg scrnMsg) {
        // START 2017/01/27 K.Kojima [QC#16600,MOD]
        // return setConfigSearchPopUpInputParam(scrnMsg, MODE_01);
        return setConfigSearchPopUpInputParam(scrnMsg, MODE_01, false);
        // END 2017/01/27 K.Kojima [QC#16600,MOD]
    }

    // END 2016/05/12 T.Tomita [QC#7832, ADD]

    // START 2017/01/27 K.Kojima [QC#16600,ADD]
    /**
     * set Config Search Pop Up Input Param
     * @param scrnMsg NSAL0010BMsg
     * @param mode String
     * @return params
     */
    public static Object[] setConfigSearchPopUpInputParam(NSAL0010BMsg scrnMsg, String mode) {
        return setConfigSearchPopUpInputParam(scrnMsg, mode, false);
    }
    // END 2017/01/27 K.Kojima [QC#16600,ADD]

    // START 2016/02/26 T.Tomita [QC#942, ADD]
    private static void setVisibilityItem(NSAL0010BMsg scrnMsg, String itemId, boolean visible) {
        EZDGUIAttribute xxItem = new EZDGUIAttribute(SCREEN_ID, itemId);
        xxItem.setVisibility(visible);
        scrnMsg.addGUIAttribute(xxItem);
    }
    // END 2016/02/26 T.Tomita [QC#942, ADD]

    // START 2018/02/19 M.Kidokoro [QC#23753, ADD]
    public static List<String> getFunctionCodeList(S21UserProfileService userProfileService) {
        return userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
    }
    // END 2018/02/19 M.Kidokoro [QC#23753, ADD]
}
