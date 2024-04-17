/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1160;

import static business.blap.NPAL1160.constant.NPAL1160Constant.AHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.BHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_FILE_NAME_LIMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_FILE_NAME_LOC;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_FILE_NAME_MBR;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_FILE_NAME_TEAM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_FILE_NAME_TRX;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_LOC;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_MBR;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_RTL_SWH_PRTY_LOC_FLG;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_APVLLIMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_LOCATION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TEAM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TRANSACTION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TECHMIN;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TECHTHRHD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_COL_CLEAR;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_COL_SAVE;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_DOWNLOAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_RESET;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_SUBMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_COPY_ROW;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_DELETE_ROW;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_INIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_INSERT_ROW;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_NWAL1130_AFTER;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_PAGE_NEXT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_PAGE_PREV;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_PAGE_JUMP;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_SEARCH_APPROVALLIMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_SEARCH_MEMBER;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_SEARCH_TEAM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_SEARCH_TEAMLOCATION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_SEARCH_TRANSACTION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_APVLLIMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_LOCATION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_MEMBER;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_TEAM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_TECHMIN;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_TECHTHRHD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TAB_TRANSACTION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_TEMPFILE;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_UPLOAD_MEMBER;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_UPLOAD_WH;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EXTN_CSV;
import static business.blap.NPAL1160.constant.NPAL1160Constant.FETCH_SIZE;
import static business.blap.NPAL1160.constant.NPAL1160Constant.KHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.LHEAD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NMAM0038I;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1199E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1361E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1160.common.NPAL1160CommonLogic;
import business.blap.NPAL1160.constant.NPAL1160Constant;
import business.db.APVL_TEAMTMsg;
import business.db.APVL_TEAM_LOCTMsg;
import business.file.NPAL1160F00FMsg;
import business.file.NPAL1160F01FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 03/23/2016   CITS            K.Ogino         Update          QC#5272
 * 03/25/2016   CITS            K.Ogino         Update          QC#5272
 * 05/10/2016   CSAI            D.Fukaya        Update          QC#6854
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8416
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8473
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8539
 * 11/29/2016   CITS            K.Kameoka       Update          QC#16149
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 01/17/2018   CITS            S.Katsuma       Update          QC#22206
 * 05/17/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPAL1160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String glblCmpyCd = getGlobalCompanyCode();

            if (EVENT_NM_NPAL1160_INIT.equals(screenAplID) || EVENT_NM_NPAL1160_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1160_INIT((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_TEAM);
            } else if (EVENT_NM_NPAL1160_SEARCH_TEAM.equals(screenAplID)) {
                doProcess_SearchTeam((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_TEAM);
            } else if (EVENT_NM_NPAL1160_TAB_TEAM.equals(screenAplID)) {
                doProcess_TabTeam((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_TEAM);
            } else if (EVENT_NM_NPAL1160_TAB_MEMBER.equals(screenAplID)) {
                doProcess_TabMember((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_MEMBER);
            } else if (EVENT_NM_NPAL1160_SEARCH_MEMBER.equals(screenAplID)) {
                doProcess_SearchMember((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_MEMBER);
            } else if (EVENT_NM_NPAL1160_TAB_TRANSACTION.equals(screenAplID)) {
                doProcess_TabTransaction((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_TRANSACTION);
            } else if (EVENT_NM_NPAL1160_SEARCH_TRANSACTION.equals(screenAplID)) {
                doProcess_SearchTransaction((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_TRANSACTION);
            } else if (EVENT_NM_NPAL1160_TAB_LOCATION.equals(screenAplID)) {
                doProcess_TabLocation((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_LOCATION);
            } else if (EVENT_NM_NPAL1160_SEARCH_TEAMLOCATION.equals(screenAplID)) {
                doProcess_SearchLocation((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_LOCATION);
            } else if (EVENT_NM_NPAL1160_SEARCH_APPROVALLIMIT.equals(screenAplID)) {
                doProcess_SearchApprovalLimit((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_APVLLIMIT);
            } else if (EVENT_NM_NPAL1160_TAB_APVLLIMIT.equals(screenAplID)) {
                doProcess_TabApprovalLimit((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DISPLAY_TAB_NM_APVLLIMIT);
            } else if (EVENT_NM_NPAL1160_TAB_TECHTHRHD.equals(screenAplID)) {
                doProcess_TabTechThreshold((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, EVENT_NM_NPAL1160_TAB_TECHTHRHD);
            }
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            else if (EVENT_NM_NPAL1160_TAB_TECHMIN.equals(screenAplID)) {
                doProcess_TabTechApvlMin((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, EVENT_NM_NPAL1160_TAB_TECHMIN);
            }
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
            else if (EVENT_NM_NPAL1160_INSERT_ROW.equals(screenAplID)) {
                doProcess_NPAL1160Scrn00_AddNewLine((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, glblCmpyCd);
            } else if (EVENT_NM_NPAL1160_DELETE_ROW.equals(screenAplID)) {
                doProcess_NPAL1160Scrn00_DeleteRow((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, glblCmpyCd);
            } else if (EVENT_NM_NPAL1160_COPY_ROW.equals(screenAplID)) {
                doProcess_NPAL1160Scrn00_CopyRow((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, glblCmpyCd);
            } else if (EVENT_NM_NPAL1160_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_PAGE_JUMP.equals(screenAplID)) {
                doProcess_PageJump((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1160Scrn00_CMN_Download((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_NWAL1130_AFTER.equals(screenAplID)) {
                doProcess_OpenWin_TeamAfter((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1160Scrn00_CMN_Submit((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, glblCmpyCd);
                preferredViewSetting((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, ((NPAL1160CMsg) cMsg).xxDplyTab.getValue());
            } else if (EVENT_NM_NPAL1160_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_UPLOAD_MEMBER.equals(screenAplID)) {
                doProcess_NPAL1160_MemberImport((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_UPLOAD_WH.equals(screenAplID)) {
                doProcess_NPAL1160_LocationImport((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_TEMPFILE.equals(screenAplID)) {
                doProcess_NPAL1160_UploadTemplate((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_NPAL1160_INIT(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        NPAL1160CommonLogic.clearScreenItem(cMsg, sMsg);

        String glblCmpyCd = getGlobalCompanyCode();

        // Team
        NPAL1160CommonLogic.createPullDownHierarchyType(cMsg, sMsg, glblCmpyCd);

        // Member
        NPAL1160CommonLogic.createPullDownPosition(cMsg, sMsg, glblCmpyCd);

        // Transaction
        NPAL1160CommonLogic.createPullDownPlanningGroup(cMsg, sMsg, glblCmpyCd);
        NPAL1160CommonLogic.createPullDownTransaction(cMsg, sMsg, glblCmpyCd);

        // ApvlLimit
        NPAL1160CommonLogic.createPullDownHierarchyTypeByAL(cMsg, sMsg, glblCmpyCd);
        NPAL1160CommonLogic.createPullDownPlanningGroupByAL(cMsg, sMsg, glblCmpyCd);
        NPAL1160CommonLogic.createPullDownPositionByAL(cMsg, sMsg, glblCmpyCd);
        NPAL1160CommonLogic.createPullDownTransactionByAL(cMsg, sMsg, glblCmpyCd);
        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        NPAL1160CommonLogic.createPullDownRequestTypeByAL(cMsg, sMsg, glblCmpyCd);
        // END 2023/08/29 M.Kikushima [QC#61590, ADD]
    }

    private void doProcess_TabTeam(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        // ZYPTableUtil.clear(sMsg.A);
        // ZYPTableUtil.clear(cMsg.A);

        // START 2018/01/17 S.Katsuma [QC#22206,ADD]
        if (cMsg.A.getValidCount() > 0) {
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
        // END 2018/01/17 S.Katsuma [QC#22206,ADD]
    }

    /**
     * Search Team
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_SearchTeam(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1160CommonLogic.searchTeam(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Tab Member
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_TabMember(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue()) && !NPAL1160CommonLogic.checkMultipleSelectionAtTeamTab(cMsg, sMsg)) {
            return;
        }

        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.B);

        int chkCount = 0;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if ((ZYPCommonFunc.hasValue(cMsg.A.no(i).apvlTeamPk_A1)) && (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue()))) {

                ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamPk_HD, cMsg.A.no(i).apvlTeamPk_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.apvlTeamNm_MT, cMsg.A.no(i).apvlTeamNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamNm_MT, cMsg.A.no(i).apvlTeamNm_A1);

                chkCount++;
                break;
            }
        }
        if (chkCount == 0) {
            sMsg.apvlTeamPk_HD.clear();
            cMsg.apvlTeamNm_MT.clear();
            sMsg.apvlTeamNm_MT.clear();
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(cMsg.A);
        }

        String glblCmpyCd = getGlobalCompanyCode();
        if (ZYPCommonFunc.hasValue(sMsg.apvlTeamPk_HD)) {
            NPAL1160CommonLogic.searchMemberForPK(cMsg, sMsg, glblCmpyCd, sMsg.apvlTeamPk_HD.getValue());
        }

    }

    /**
     * Search Member
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_SearchMember(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1160CommonLogic.searchMember(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Tab Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_TabTransaction(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue()) && !NPAL1160CommonLogic.checkMultipleSelectionAtTeamTab(cMsg, sMsg)) {
            return;
        }

        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(cMsg.C);

        int chkCount = 0;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if ((ZYPCommonFunc.hasValue(cMsg.A.no(i).apvlTeamPk_A1)) && (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue()))) {

                ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamPk_HD, cMsg.A.no(i).apvlTeamPk_A1);

                ZYPEZDItemValueSetter.setValue(cMsg.apvlTeamNm_ST, cMsg.A.no(i).apvlTeamNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamNm_ST, cMsg.A.no(i).apvlTeamNm_A1);

                chkCount++;
                break;
            }
        }
        if (chkCount == 0) {
            sMsg.apvlTeamPk_HD.clear();
            cMsg.apvlTeamNm_ST.clear();
            sMsg.apvlTeamNm_ST.clear();
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(cMsg.A);
        }

        String glblCmpyCd = getGlobalCompanyCode();
        if (ZYPCommonFunc.hasValue(sMsg.apvlTeamPk_HD)) {

            NPAL1160CommonLogic.searchTransactionForPK(cMsg, sMsg, glblCmpyCd, sMsg.apvlTeamPk_HD.getValue());
        }

    }

    /**
     * Search Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_SearchTransaction(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1160CommonLogic.searchTransaction(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Tab Location
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_TabLocation(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue()) && !NPAL1160CommonLogic.checkMultipleSelectionAtTeamTab(cMsg, sMsg)) {
            return;
        }

        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(cMsg.D);

        int chkCount = 0;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if ((ZYPCommonFunc.hasValue(cMsg.A.no(i).apvlTeamPk_A1)) && (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue()))) {

                ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamPk_HD, cMsg.A.no(i).apvlTeamPk_A1);

                ZYPEZDItemValueSetter.setValue(cMsg.apvlTeamNm_LT, cMsg.A.no(i).apvlTeamNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamNm_LT, cMsg.A.no(i).apvlTeamNm_A1);
                chkCount++;
                break;
            }
        }
        if (chkCount == 0) {
            sMsg.apvlTeamPk_HD.clear();
            cMsg.apvlTeamNm_LT.clear();
            sMsg.apvlTeamNm_LT.clear();
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(cMsg.A);
        }

        String glblCmpyCd = getGlobalCompanyCode();
        if (ZYPCommonFunc.hasValue(sMsg.apvlTeamPk_HD)) {

            NPAL1160CommonLogic.searchLocationForPK(cMsg, sMsg, glblCmpyCd, sMsg.apvlTeamPk_HD.getValue());
        }

    }

    /**
     * Search Location
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_SearchLocation(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1160CommonLogic.searchLocation(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Tab Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_TabApprovalLimit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue()) && !NPAL1160CommonLogic.checkMultipleSelectionAtTeamTab(cMsg, sMsg)) {
            return;
        }

        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(cMsg.E);

    }

    /**
     * Tab Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_TabTechThreshold(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1160CommonLogic.searchTechThreshold(cMsg, sMsg, getGlobalCompanyCode());
    }

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Tab Tech Minimum
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_TabTechApvlMin(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1160CommonLogic.searchTechApvlMin(cMsg, sMsg, getGlobalCompanyCode());
    }
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * Search Approval Limit
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_SearchApprovalLimit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1160CommonLogic.searchApprovalLimit(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Page Next
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_PageNext(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.B.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.C.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.D.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.E.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.K.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.L.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    }

    /**
     * Page Prev
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_PagePrev(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.B.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.C.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.D.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.E.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.K.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {

            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.L.length());
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    }

    private void doProcess_NPAL1160Scrn00_AddNewLine(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {

            // check max count
            int index = sMsg.A.getValidCount();
            if (index == sMsg.A.length()) {
                // QC#16149 Start
                cMsg.setMessageInfo(NPAM1199E);
                // QC#16149 End
                return;
            } else {
                NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

                // add row
                NPAL1160CommonLogic.addNewLineA(cMsg, sMsg, glblCmpyCd, index);

                sMsg.A.setValidCount(index + 1);
                // Show last page.
                cMsg.xxPageShowFromNum.setValue(-1);

                NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            // check max count
            int index = sMsg.B.getValidCount();
            if (index == sMsg.B.length()) {
                // QC#16149 Start
                cMsg.setMessageInfo(NPAM1199E);
                // QC#16149 End
                return;
            } else {
                NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

                if (ZYPCommonFunc.hasValue(cMsg.apvlTeamNm_MT)) {
                    BigDecimal apvlTeamPK = NPAL1160CommonLogic.searchTeamPKByName(cMsg, sMsg, glblCmpyCd, cMsg.apvlTeamNm_MT.getValue());
                    if (apvlTeamPK.compareTo(BigDecimal.ZERO) == 0) {
                        cMsg.apvlTeamNm_MT.setErrorInfo(1, NPAM1361E, new String[] {"Team Name" });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamPk_HD, apvlTeamPK);
                }

                // add row
                NPAL1160CommonLogic.addNewLineB(cMsg, sMsg, glblCmpyCd, index);

                sMsg.B.setValidCount(index + 1);
                // Show last page.
                cMsg.xxPageShowFromNum.setValue(-1);

                NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            // check max count
            int index = sMsg.C.getValidCount();
            if (index == sMsg.C.length()) {
                // QC#16149 Start
                cMsg.setMessageInfo(NPAM1199E);
                // QC#16149 End
                return;
            } else {
                NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

                if (ZYPCommonFunc.hasValue(cMsg.apvlTeamNm_ST)) {
                    BigDecimal apvlTeamPK = NPAL1160CommonLogic.searchTeamPKByName(cMsg, sMsg, glblCmpyCd, cMsg.apvlTeamNm_ST.getValue());
                    if (apvlTeamPK.compareTo(BigDecimal.ZERO) == 0) {
                        cMsg.apvlTeamNm_ST.setErrorInfo(1, NPAM1361E, new String[] {"Team Name" });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamPk_HD, apvlTeamPK);
                }

                // add row
                NPAL1160CommonLogic.addNewLineC(cMsg, sMsg, glblCmpyCd, index);

                sMsg.C.setValidCount(index + 1);
                // Show last page.
                cMsg.xxPageShowFromNum.setValue(-1);

                NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            // check max count
            int index = sMsg.D.getValidCount();
            if (index == sMsg.D.length()) {
                // QC#16149 Start
                cMsg.setMessageInfo(NPAM1199E);
                // QC#16149 End
                return;
            } else {
                NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

                if (ZYPCommonFunc.hasValue(cMsg.apvlTeamNm_LT)) {
                    BigDecimal apvlTeamPK = NPAL1160CommonLogic.searchTeamPKByName(cMsg, sMsg, glblCmpyCd, cMsg.apvlTeamNm_LT.getValue());
                    if (apvlTeamPK.compareTo(BigDecimal.ZERO) == 0) {
                        cMsg.apvlTeamNm_LT.setErrorInfo(1, NPAM1361E, new String[] {"Team Name" });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.apvlTeamPk_HD, apvlTeamPK);
                }

                // add row
                NPAL1160CommonLogic.addNewLineD(cMsg, sMsg, glblCmpyCd, index);

                sMsg.D.setValidCount(index + 1);
                // Show last page.
                cMsg.xxPageShowFromNum.setValue(-1);

                NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            // check max count
            int index = sMsg.E.getValidCount();
            if (index == sMsg.E.length()) {
                // QC#16149 Start
                cMsg.setMessageInfo(NPAM1199E);
                // QC#16149 End
                return;
            } else {
                NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

                // add row
                NPAL1160CommonLogic.addNewLineE(cMsg, sMsg, glblCmpyCd, index);

                sMsg.E.setValidCount(index + 1);
                // Show last page.
                cMsg.xxPageShowFromNum.setValue(-1);

                NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }

        }

    }

    /**
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     */
    private void doProcess_NPAL1160Scrn00_CopyRow(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            // QC#16149 Start
            // check max count
            int index = sMsg.A.getValidCount();
            if (index == sMsg.A.length()) {
                cMsg.setMessageInfo(NPAM1199E);
                return;
            } else {
                // QC#16149 End
                NPAL1160CommonLogic.copyLineA(cMsg, sMsg, glblCmpyCd);
            }
        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            // QC#16149 Start
            // check max count
            int index = sMsg.B.getValidCount();
            if (index == sMsg.B.length()) {
                cMsg.setMessageInfo(NPAM1199E);
                return;
            } else {
                // QC#16149 End
                NPAL1160CommonLogic.copyLineB(cMsg, sMsg, glblCmpyCd);
            }
        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            // QC#16149 Start
            // check max count
            int index = sMsg.C.getValidCount();
            if (index == sMsg.C.length()) {
                cMsg.setMessageInfo(NPAM1199E);
                return;
            } else {
                // QC#16149 End
                NPAL1160CommonLogic.copyLineC(cMsg, sMsg, glblCmpyCd);
            }
        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            // QC#16149 Start
            // check max count
            int index = sMsg.D.getValidCount();
            if (index == sMsg.D.length()) {
                cMsg.setMessageInfo(NPAM1199E);
                return;
            } else {
                // QC#16149 End
                NPAL1160CommonLogic.copyLineD(cMsg, sMsg, glblCmpyCd);
            }
        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            // QC#16149 Start
            // check max count
            int index = sMsg.E.getValidCount();
            if (index == sMsg.E.length()) {
                cMsg.setMessageInfo(NPAM1199E);
                return;
            } else {
                // QC#16149 End
                NPAL1160CommonLogic.copyLineE(cMsg, sMsg, glblCmpyCd);
            }
        }
    }

    /**
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     */
    private void doProcess_NPAL1160Scrn00_CMN_Submit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_SearchTeam(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_SearchMember(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_SearchTransaction(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_SearchLocation(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_SearchApprovalLimit(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_TabTechThreshold(cMsg, sMsg);

        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {
            doProcess_TabTechApvlMin(cMsg, sMsg);

        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]
    }

    /**
     * <pre>
     * Delete_Row Event
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_NPAL1160Scrn00_DeleteRow(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE A
            NPAL1160CommonLogic.deleteLineA(cMsg, sMsg, glblCmpyCd);

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE B
            NPAL1160CommonLogic.deleteLineB(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE C
            NPAL1160CommonLogic.deleteLineC(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE D
            NPAL1160CommonLogic.deleteLineD(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE E
            NPAL1160CommonLogic.deleteLineE(cMsg, sMsg);

        }

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NPAL1160Scrn00_CMN_Download(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1160Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1160Query.getInstance().getClass());

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            String statementId = null;

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());

            if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_TEAM), EXTN_CSV);
                statementId = "searchTeam";

            } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_MBR), EXTN_CSV);
                statementId = "searchMember";

            } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_TRX), EXTN_CSV);
                statementId = "searchTransaction";

            } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_LOC), EXTN_CSV);

                ssmParam.put(DB_PARAM_RTL_SWH_PRTY_LOC_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

                statementId = "searchLocation";

            } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_LIMIT), EXTN_CSV);
                statementId = "searchApprovalLimit";
            }

            ssmParam.put(DB_PARAM_CMSG, cMsg);

            stmtSelect = ssmLLClient.createPreparedStatement(statementId, ssmParam, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NMAM0038I, null);
                return;
            }
            NPAL1160CommonLogic.writeCsvFileInfo(cMsg, rs, getGlobalCompanyCode());
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * Team PK Search By Team Name
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_OpenWin_TeamAfter(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String selectTeamName = cMsg.P.no(0).xxComnScrColValTxt.getValue();

        BigDecimal teamPK = NPAL1160CommonLogic.searchTeamPKByName(cMsg, sMsg, glblCmpyCd, selectTeamName);

        if (teamPK.compareTo(BigDecimal.ZERO) != 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.apvlTeamPk_P1, teamPK);
        }
    }

    /**
     * Member IMPORT
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_NPAL1160_MemberImport(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);

        String glblCmpyCd = getGlobalCompanyCode();

        String path = cMsg.xxFileData_UP.getTempFilePath();

        NPAL1160F00FMsg fMsg = new NPAL1160F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        int count = 0;

        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo("ZYEM0004E");
            }
            fMsg.clear();
            int status = -1;
            int i = -1;

            while ((status = mappedFile.read()) != 1) {
                count++;
                if (!validateMemberUploadFile(status, count, cMsg, sMsg, fMsg)) {
                    if ("E".equals(cMsg.getMessageKind())) {
                        break;
                    }
                } else {

                    if ("E".equals(cMsg.getMessageKind())) {
                        break;
                    }
                    // set import file to sMsg work
                    i++;

                    sMsg.B.setValidCount(i);

                    APVL_TEAMTMsg outMsg = null;

                    if (ZYPCommonFunc.hasValue(fMsg.apvlTeamPk_B)) {
                        // Get Team Name
                        APVL_TEAMTMsg inMsg = new APVL_TEAMTMsg();
                        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPk, fMsg.apvlTeamPk_B);
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

                        outMsg = (APVL_TEAMTMsg) S21ApiTBLAccessor.findByKey(inMsg);

                    }

                    if (!ZYPCommonFunc.hasValue(fMsg.apvlTeamMbrPk_B)) {
                        // Insert Record
                        // copy from FMsg to SMsg work
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPk_B1, fMsg.apvlTeamPk_B);
                        if (outMsg != null) {
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamNm_B1, outMsg.apvlTeamNm);
                        }

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPosnTpCd_BS, fMsg.apvlTeamPosnTpCd_B);
                        NPAL1160CommonLogic.setPosition(glblCmpyCd, sMsg.B.no(i));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).psnCd_B1, fMsg.psnCd_B);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fullPsnNm_B1, fMsg.fullPsnNm_B);

                    } else {

                        // PK Check
                        BigDecimal getPk = NPAL1160CommonLogic.searchMemberUpload(cMsg, sMsg, glblCmpyCd, fMsg.apvlTeamMbrPk_B.getValue());

                        if (getPk.compareTo(BigDecimal.ZERO) != 0) {
                            // Update Record
                            // copy from FMsg to SMsg work
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamMbrPk_B1, getPk);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPk_B1, fMsg.apvlTeamPk_B);
                            if (outMsg != null) {
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamNm_B1, outMsg.apvlTeamNm);
                            }
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPosnTpCd_BS, fMsg.apvlTeamPosnTpCd_B);
                            NPAL1160CommonLogic.setPosition(glblCmpyCd, sMsg.B.no(i));
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).psnCd_B1, fMsg.psnCd_B);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fullPsnNm_B1, fMsg.fullPsnNm_B);

                        } else {
                            // Insert Record
                            // copy from FMsg to SMsg work
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPk_B1, fMsg.apvlTeamPk_B);
                            if (outMsg != null) {
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamNm_B1, outMsg.apvlTeamNm);
                            }
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPosnTpCd_BS, fMsg.apvlTeamPosnTpCd_B);
                            NPAL1160CommonLogic.setPosition(glblCmpyCd, sMsg.B.no(i));
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).psnCd_B1, fMsg.psnCd_B);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fullPsnNm_B1, fMsg.fullPsnNm_B);
                        }

                    }

                    sMsg.B.setValidCount(i + 1);
                }
            }
        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
        if (sMsg.B.getValidCount() > cMsg.B.length()) {
            cMsg.xxPageShowToNum.setValue(cMsg.B.length());
        } else {
            cMsg.xxPageShowToNum.setValue(sMsg.B.getValidCount());
        }

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
      * validateAndCopyToSMsg_UPLOAD
      * </pre>
     * @param int status
     * @param int count
     * @param NPAL1070CMsg cMsg
     * @param NPAL1070_ASMsgArray aSMsg
     * @param NPAL1070F00FMsg FileMsg
     */
    private boolean validateMemberUploadFile(int status, int count, NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, NPAL1160F00FMsg fMsg) {
        if (count > sMsg.B.length()) {
            cMsg.setMessageInfo("NPAM1199E");
            return false;
        }
        if (status == 1000) {
            cMsg.setMessageInfo("NMAM0052E", new String[] {"CSV" });
            return false;
        }

        if (cMsg.getMessageInfo() != null) {
            return false;
        }

        return true;
    }

    /**
     * Location IMPORT
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_NPAL1160_LocationImport(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.D);

        String glblCmpyCd = getGlobalCompanyCode();

        String path = cMsg.xxFileData_UP.getTempFilePath();

        NPAL1160F01FMsg fMsg = new NPAL1160F01FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        int count = 0;

        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo("ZYEM0004E");
            }
            fMsg.clear();
            int status = -1;
            int i = -1;

            while ((status = mappedFile.read()) != 1) {
                count++;
                if (!validateLocationUploadFile(status, count, cMsg, sMsg, fMsg)) {
                    if ("E".equals(cMsg.getMessageKind())) {
                        break;
                    }
                } else {

                    if ("E".equals(cMsg.getMessageKind())) {
                        break;
                    }
                    // set import file to sMsg work
                    i++;
                    sMsg.D.setValidCount(i);

                    APVL_TEAMTMsg outMsg = null;
                    String getTeamName = "";

                    if (ZYPCommonFunc.hasValue(fMsg.apvlTeamPk_D)) {
                        // Get Team Name
                        APVL_TEAMTMsg inMsg = new APVL_TEAMTMsg();
                        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPk, fMsg.apvlTeamPk_D);
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

                        outMsg = (APVL_TEAMTMsg) S21ApiTBLAccessor.findByKey(inMsg);
                        if (outMsg != null) {
                            getTeamName = outMsg.apvlTeamNm.getValue();
                        }
                    }

                    if (!ZYPCommonFunc.hasValue(fMsg.apvlTeamLocPk_D)) {
                        // Insert Record
                        // copy from FMsg to SMsg work
                        ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamPk_D1, fMsg.apvlTeamPk_D);
                        ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamNm_D1, getTeamName);
                        ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhCd_D1, fMsg.rtlWhCd_D);

                    } else {
                        // PK Check
                        APVL_TEAM_LOCTMsg inChMsg = new APVL_TEAM_LOCTMsg();
                        ZYPEZDItemValueSetter.setValue(inChMsg.apvlTeamLocPk, fMsg.apvlTeamLocPk_D);
                        ZYPEZDItemValueSetter.setValue(inChMsg.glblCmpyCd, glblCmpyCd);

                        APVL_TEAM_LOCTMsg getPkMsg = (APVL_TEAM_LOCTMsg) S21ApiTBLAccessor.findByKey(inChMsg);
                        if (getPkMsg != null) {
                            BigDecimal getPk = getPkMsg.apvlTeamLocPk.getValue();
                            if (getPk.compareTo(BigDecimal.ZERO) != 0) {
                                // Update Record
                                // copy from FMsg to SMsg work
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamLocPk_D1, getPk);
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamPk_D1, fMsg.apvlTeamPk_D);
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamNm_D1, getTeamName);
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhCd_D1, fMsg.rtlWhCd_D);

                            } else {
                                // Insert Record
                                // copy from FMsg to SMsg work
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamPk_D1, fMsg.apvlTeamPk_D);
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamNm_D1, getTeamName);
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhCd_D1, fMsg.rtlWhCd_D);
                            }
                        } else {
                            // Insert Record
                            // copy from FMsg to SMsg work
                            ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamPk_D1, fMsg.apvlTeamPk_D);
                            if (outMsg != null) {
                                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamNm_D1, outMsg.apvlTeamNm);
                            }
                            ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhCd_D1, fMsg.rtlWhCd_D);
                        }

                    }

                    sMsg.D.setValidCount(i + 1);
                }
            }
        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowOfNum.setValue(sMsg.D.getValidCount());
        if (sMsg.D.getValidCount() > cMsg.B.length()) {
            cMsg.xxPageShowToNum.setValue(cMsg.D.length());
        } else {
            cMsg.xxPageShowToNum.setValue(sMsg.D.getValidCount());
        }

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
       * validateAndCopyToSMsg_UPLOAD
       * </pre>
     * @param int status
     * @param int count
     * @param NPAL1070CMsg cMsg
     * @param NPAL1070_ASMsgArray aSMsg
     * @param NPAL1070F00FMsg FileMsg
     */
    private boolean validateLocationUploadFile(int status, int count, NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, NPAL1160F01FMsg fMsg) {
        if (count > sMsg.D.length()) {
            cMsg.setMessageInfo("NPAM1199E");
            return false;
        }
        if (status == 1000) {
            cMsg.setMessageInfo("NMAM0052E", new String[] {"CSV" });
            return false;
        }

        if (cMsg.getMessageInfo() != null) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Template file for Upload
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    private void doProcess_NPAL1160_UploadTemplate(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160F00FMsg fMsg = new NPAL1160F00FMsg();

            cMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_MBR), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_UP.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_MBR);
            csvOutFile.close();

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160F01FMsg fMsg = new NPAL1160F01FMsg();

            cMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_LOC), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_UP.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_LOC);
            csvOutFile.close();

        }
    }

    /**
     * Tab Preferred View Settings
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param tabName String
     */
    private void preferredViewSetting(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String tabName) {
        if (DISPLAY_TAB_NM_TEAM.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, AHEAD);
        } else if (DISPLAY_TAB_NM_MEMBER.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, BHEAD);
        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, CHEAD);
        } else if (DISPLAY_TAB_NM_LOCATION.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, DHEAD);
        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, EHEAD);
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, KHEAD);
        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(tabName)) {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, LHEAD);
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        else {
            ZYPGUITableColumn.getColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, AHEAD);
        }
    }

    /**
     * doProcess_PageJump
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_PageJump(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.A.length(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.B.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.B.length(); i++) {
                if (i < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.B.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.B.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.C.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.C.length(); i++) {
                if (i < sMsg.C.getValidCount()) {
                    EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.C.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.C.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.D.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.D.length(); i++) {
                if (i < sMsg.D.getValidCount()) {
                    EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.D.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.D.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.E.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.E.length(); i++) {
                if (i < sMsg.E.getValidCount()) {
                    EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.E.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.E.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.K.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.K.length(); i++) {
                if (i < sMsg.K.getValidCount()) {
                    EZDMsg.copy(sMsg.K.no(i), null, cMsg.K.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.K.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.K.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(cMsg.L.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) + 1));

            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.L.length(); i++) {
                if (i < sMsg.L.getValidCount()) {
                    EZDMsg.copy(sMsg.L.no(i), null, cMsg.L.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.L.setValidCount(i - pagenationFrom);

            // set value to pagination items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.L.getValidCount());
            // copy data from SMsg to CMsg
            NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

        
    }
}
