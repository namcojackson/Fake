package business.servlet.NLBL3090.common;

import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_ADD_NEW_COORD;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_ADD_NEW_ST;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_APPLY;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_APPROVE;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_CLEAR;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_DELETE;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_DOWNLOAD;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_REJECT;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_RESET;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_RETURN;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_SAVE;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BTN_CMN_BTN_SUBMIT;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.FUNCTION_UPDATE;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_0;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_1;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_10;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_11;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_2;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_3;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_4;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_5;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_6;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_7;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_8;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.IDX_9;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.LEN_20;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.LEN_60;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.NSBM0007E;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.SCREEN_ID;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.SEARCH_MGRNM;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.SEARCH_SUPVNM;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.SEARCH_WH;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.TAB_ASSIGN;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.ZZM9037E;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.ZZSM4100E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3090.NLBL3090BMsg;
import business.servlet.NLBL3090.NLBL3090_ABMsg;
import business.servlet.NLBL3090.NLBL3090_BBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 * 04/12/2016   CSAI            D.Fukaya        Update          QC#6088
 * 06/08/2016   CSAI            D.Fukaya        Update          QC#9468
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NLBL3090CommonLogic {

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPLY[0], BTN_CMN_BTN_APPLY[1], BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPROVE[0], BTN_CMN_BTN_APPROVE[1], BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_REJECT[0], BTN_CMN_BTN_REJECT[1], BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DOWNLOAD[0], BTN_CMN_BTN_DOWNLOAD[1], BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DELETE[0], BTN_CMN_BTN_DELETE[1], BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RETURN[0], BTN_CMN_BTN_RETURN[1], BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NLBL3090BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    }

    /**
     * @param handler S21CommonHandler
     * @param scrnMsg NLBL3090BMsg
     */
    public static void control(S21CommonHandler handler, NLBL3090BMsg scrnMsg) {
        // Common
        handler.setButtonEnabled(SEARCH_WH, true);
        disable(scrnMsg.rtlWhNm_H1);
        disable(scrnMsg.psnCd_H1);
        disable(scrnMsg.fullPsnNm_H1);
        disable(scrnMsg.fullPsnNm_HM);
        disable(scrnMsg.fullPsnNm_HS);
        disable(scrnMsg.fullPsnNm_HC);

        // QC#16256 add start
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        }
        // QC#16256 add end

        if (TAB_ASSIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            enable(scrnMsg.mgrPsnCd_H1);
            enable(scrnMsg.mgrPsnCd_H2);
            handler.setButtonEnabled(SEARCH_MGRNM, true);
            enable(scrnMsg.supvPsnCd_H1);
            enable(scrnMsg.supvPsnCd_H2);
            handler.setButtonEnabled(SEARCH_SUPVNM, true);

            if (scrnMsg.A.getValidCount() > 0) {
                disable(scrnMsg.rtlWhCd_H1);
                disable(scrnMsg.rtlWhCd_H2);
            } else {
                enable(scrnMsg.rtlWhCd_H1);
                enable(scrnMsg.rtlWhCd_H2);
            }

            handler.setButtonProperties(BTN_CMN_BTN_ADD_NEW_COORD[0], BTN_CMN_BTN_ADD_NEW_COORD[1], BTN_CMN_BTN_ADD_NEW_COORD[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_ADD_NEW_ST[0], BTN_CMN_BTN_ADD_NEW_ST[1], BTN_CMN_BTN_ADD_NEW_ST[2], 0, null);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NLBL3090_ABMsg abMsg = scrnMsg.A.no(i);
                disable(abMsg.fullPsnNm_AM);
                disable(abMsg.fullPsnNm_AS);
                disable(abMsg.fullPsnNm_AC);
                disable(abMsg.stCd_A);
                disable(abMsg.effFromDt_A);
                disable(abMsg.effThruDt_A);
                disable(abMsg.carrCd_A);
                disable(abMsg.locNm_A);
                disable(abMsg.carrCtacEmlAddr_A);
                disable(abMsg.carrCtacTelNum_A);
            }
            controlAssignTable(scrnMsg);
        } else {
            if (scrnMsg.B.getValidCount() > 0) {
                disable(scrnMsg.rtlWhCd_H1);
                disable(scrnMsg.rtlWhCd_H2);
                handler.setButtonProperties(BTN_CMN_BTN_ADD_NEW_ST[0], BTN_CMN_BTN_ADD_NEW_ST[1], BTN_CMN_BTN_ADD_NEW_ST[2], 1, null);
            } else {
                enable(scrnMsg.rtlWhCd_H1);
                enable(scrnMsg.rtlWhCd_H2);
                handler.setButtonProperties(BTN_CMN_BTN_ADD_NEW_ST[0], BTN_CMN_BTN_ADD_NEW_ST[1], BTN_CMN_BTN_ADD_NEW_ST[2], 0, null);
            }

            handler.setButtonProperties(BTN_CMN_BTN_ADD_NEW_COORD[0], BTN_CMN_BTN_ADD_NEW_COORD[1], BTN_CMN_BTN_ADD_NEW_COORD[2], 1, null);

            disable(scrnMsg.mgrPsnCd_H1);
            disable(scrnMsg.mgrPsnCd_H2);
            handler.setButtonEnabled(SEARCH_MGRNM, false);
            disable(scrnMsg.supvPsnCd_H1);
            disable(scrnMsg.supvPsnCd_H2);
            handler.setButtonEnabled(SEARCH_SUPVNM, false);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NLBL3090_BBMsg bbMsg = scrnMsg.B.no(i);
                disable(bbMsg.fullPsnNm_BC);
                disable(bbMsg.locNm_B);
            }
            controlCoordinationTable(scrnMsg);
        }
    }

    private static void controlAssignTable(NLBL3090BMsg scrnMsg) {
        int mgrPsnCd = -1;
        int supvPsnCd = -1;
        String schdCoordPsnCd = null;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLBL3090_ABMsg abMsg = scrnMsg.A.no(i);
            if (i == 0) {
                mgrPsnCd = abMsg.xxNewRowNum_A2.getValueInt();
                supvPsnCd = abMsg.xxNewRowNum_A3.getValueInt();
                schdCoordPsnCd = abMsg.schdCoordPsnCd_AC.getValue();
                continue;
            }
            if (abMsg.xxNewRowNum_A1.getValueInt() < 0) {
                // New Row
                if (!ZYPCommonFunc.hasValue(abMsg.xxNewRowNum_A2)) {
                    mgrPsnCd = -1;
                } else if (!ZYPCommonFunc.hasValue(abMsg.supvPsnCd_AS)) {
                    supvPsnCd = -1;
                } else if (!ZYPCommonFunc.hasValue(abMsg.schdCoordPsnCd_AC)) {
                    schdCoordPsnCd = null;
                }
            }
            if ((mgrPsnCd != -1) && (mgrPsnCd == abMsg.xxNewRowNum_A2.getValueInt())) {
                controlAssignTableManager(scrnMsg, i, false);
                if ((supvPsnCd != -1) && (supvPsnCd == abMsg.xxNewRowNum_A3.getValueInt())) {
                    controlAssignTableSupervisor(scrnMsg, i, false);
                    if ((schdCoordPsnCd != null) && (schdCoordPsnCd.equals(abMsg.schdCoordPsnCd_AC.getValue()))) {
                        controlAssignTableCoordinator(scrnMsg, i, false);
                    } else {
                        controlAssignTableCoordinator(scrnMsg, i, true);
                        schdCoordPsnCd = abMsg.schdCoordPsnCd_AC.getValue();
                    }
                } else {
                    controlAssignTableSupervisor(scrnMsg, i, true);
                    controlAssignTableCoordinator(scrnMsg, i, true);
                    supvPsnCd = abMsg.xxNewRowNum_A3.getValueInt();
                    schdCoordPsnCd = abMsg.schdCoordPsnCd_AC.getValue();
                }
            } else {
                controlAssignTableManager(scrnMsg, i, true);
                controlAssignTableSupervisor(scrnMsg, i, true);
                controlAssignTableCoordinator(scrnMsg, i, true);
                mgrPsnCd = abMsg.xxNewRowNum_A2.getValueInt();
                supvPsnCd = abMsg.xxNewRowNum_A3.getValueInt();
                schdCoordPsnCd = abMsg.schdCoordPsnCd_AC.getValue();
            }
        }
    }

    private static void controlAssignTableManager(NLBL3090BMsg scrnMsg, int index, boolean visibility) {
        controlItem(scrnMsg, "xxChkBox_AM" + index, visibility);
        controlItem(scrnMsg, "mgrPsnCd_AM" + index, visibility);
        controlItem(scrnMsg, "btn_AM" + index, visibility);
        controlItem(scrnMsg, "fullPsnNm_AM" + index, visibility);
    }

    private static void controlAssignTableSupervisor(NLBL3090BMsg scrnMsg, int index, boolean visibility) {
        controlItem(scrnMsg, "xxChkBox_AS" + index, visibility);
        controlItem(scrnMsg, "supvPsnCd_AS" + index, visibility);
        controlItem(scrnMsg, "btn_AS" + index, visibility);
        controlItem(scrnMsg, "fullPsnNm_AS" + index, visibility);
    }

    private static void controlAssignTableCoordinator(NLBL3090BMsg scrnMsg, int index, boolean visibility) {
        controlItem(scrnMsg, "xxChkBox_AC" + index, visibility);
        controlItem(scrnMsg, "schdCoordPsnCd_AC" + index, visibility);
        controlItem(scrnMsg, "btn_AC" + index, visibility);
        controlItem(scrnMsg, "fullPsnNm_AC" + index, visibility);
    }

    private static void controlCoordinationTable(NLBL3090BMsg scrnMsg) {
        int schdCoordPsnCd = -1;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NLBL3090_BBMsg bbMsg = scrnMsg.B.no(i);
            if (i == 0) {
                schdCoordPsnCd = bbMsg.xxNewRowNum_B2.getValueInt();
                continue;
            }
            if (bbMsg.xxNewRowNum_B1.getValueInt() < 0) {
                // New Row
                if (!ZYPCommonFunc.hasValue(bbMsg.schdCoordPsnCd_BC)) {
                    schdCoordPsnCd = -1;
                }
            }
            if ((schdCoordPsnCd != -1) && (schdCoordPsnCd == bbMsg.xxNewRowNum_B2.getValueInt())) {
                controlItem(scrnMsg, "xxChkBox_BC" + i, false);
                controlItem(scrnMsg, "schdCoordPsnCd_BC" + i, false);
                controlItem(scrnMsg, "btn_BC" + i, false);
                controlItem(scrnMsg, "fullPsnNm_BC" + i, false);
            } else {
                controlItem(scrnMsg, "xxChkBox_BC" + i, true);
                controlItem(scrnMsg, "schdCoordPsnCd_BC" + i, true);
                controlItem(scrnMsg, "btn_BC" + i, true);
                controlItem(scrnMsg, "fullPsnNm_BC" + i, true);
                schdCoordPsnCd = bbMsg.xxNewRowNum_B2.getValueInt();
            }
        }
    }

    private static void controlItem(NLBL3090BMsg scrnMsg, String id, boolean visibility) {
        EZDGUIAttribute attr = new EZDGUIAttribute(SCREEN_ID, id);
        attr.setVisibility(visibility);
        scrnMsg.addGUIAttribute(attr);
    }

    private static void enable(EZDBItem item) {
        item.setInputProtected(false);
    }

    private static void disable(EZDBItem item) {
        item.setInputProtected(true);
    }

    /**
     * Add Serch item check
     * @param scrnMsg NLBL3090BMsg
     */
    public static void addCheckSearchItem(NLBL3090BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.mgrPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.supvPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd_H1);
    }

    /**
     * chkManagerCheck
     * @param scrnMsg NLBL3090BMsg
     */
    public static void chkManagerCheck(NLBL3090BMsg scrnMsg) {
        boolean chkBoxOn = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_AM)) {
                chkBoxOn = true;
            }
        }

        if (!chkBoxOn) {
            // Detail Check
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_AM.setErrorInfo(1, NSBM0007E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AM);
            }
            scrnMsg.setMessageInfo(ZZM9037E, null, 1);
        }
    }

    /**
     * chkSupervisorCheck
     * @param scrnMsg NLBL3090BMsg
     */
    public static void chkSupervisorCheck(NLBL3090BMsg scrnMsg) {
        boolean chkBoxOn = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_AS)) {
                chkBoxOn = true;
            }
        }

        if (!chkBoxOn) {
            // Detail Check
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_AS.setErrorInfo(1, NSBM0007E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AS);
            }
            scrnMsg.setMessageInfo(ZZM9037E, null, 1);
        }
    }

    /**
     * chkAssignCoordinatorCheck
     * @param scrnMsg NLBL3090BMsg
     */
    public static void chkAssignCoordinatorCheck(NLBL3090BMsg scrnMsg) {
        boolean chkBoxOn = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_AC)) {
                chkBoxOn = true;
            }
        }

        if (!chkBoxOn) {
            // Detail Check
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_AC.setErrorInfo(1, NSBM0007E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AC);
            }
            scrnMsg.setMessageInfo(ZZM9037E, null, 1);
        }
    }

    /**
     * chkCoordinationCoordinatorCheck
     * @param scrnMsg NLBL3090BMsg
     */
    public static void chkCoordinationCoordinatorCheck(NLBL3090BMsg scrnMsg) {
        boolean chkBoxOn = false;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).xxChkBox_BC)) {
                chkBoxOn = true;
            }
        }

        if (!chkBoxOn) {
            // Detail Check
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxChkBox_BC.setErrorInfo(1, NSBM0007E);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_BC);
            }
            scrnMsg.setMessageInfo(ZZM9037E, null, 1);
        }
    }

    /**
     * chkStateCheck
     * @param scrnMsg NLBL3090BMsg
     */
    public static void chkStateCheck(NLBL3090BMsg scrnMsg) {
        boolean chkBoxOn = false;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).xxChkBox_BS)) {
                chkBoxOn = true;
            }
        }

        if (!chkBoxOn) {
            // Detail Check
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxChkBox_BS.setErrorInfo(1, NSBM0007E);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_BS);
            }
            scrnMsg.setMessageInfo(ZZM9037E, null, 1);
        }
    }

    /**
     * addCheckTableItem
     * @param scrnMsg NLBL3090BMsg
     */
    public static void addCheckHeader(NLBL3090BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mgrPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.supvPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd_H1);
    }

    /**
     * addCheckTableItem
     * @param scrnMsg NLBL3090BMsg
     */
    public static void addCheckTableItem(NLBL3090BMsg scrnMsg) {

        if (TAB_ASSIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mgrPsnCd_AM);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).supvPsnCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).schdCoordPsnCd_AC);
            }
        } else {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).schdCoordPsnCd_BC);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).stCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).carrCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).carrCtacEmlAddr_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).carrCtacTelNum_B);

                // check From To date
                if ((ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effFromDt_B)) && (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effThruDt_B))) {
                    if (scrnMsg.B.no(i).effThruDt_B.getValue().compareTo(scrnMsg.B.no(i).effFromDt_B.getValue()) < 0) {
                        scrnMsg.B.no(i).effFromDt_B.setErrorInfo(1, ZZSM4100E);
                        scrnMsg.B.no(i).effThruDt_B.setErrorInfo(1, ZZSM4100E);
                    }
                }
            }
        }
    }

    /**
     * Set Manager/Supervisor Popup param
     * @param scrnMsg NLBL3090BMsg
     * @param bMsg NLBL3090_ABMsg
     * @param isManager boolean
     * @return Manager/Supervisor Popup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForS21PersonPopup(NLBL3090BMsg scrnMsg, NLBL3090_ABMsg bMsg, boolean isManager) {

        if (isManager) {
            scrnMsg.xxScrEventNm_X.setValue("OpenWin_S21PersonM");
        } else {
            scrnMsg.xxScrEventNm_X.setValue("OpenWin_S21PersonS");
        }
        scrnMsg.P.clear();

        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "Person Search Popup";
        params[IDX_2] = "S21_PSN_V";

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Person Code";
        whereArray1[IDX_1] = "PSN_CD";
        if (isManager) {
            whereArray1[IDX_2] = bMsg.mgrPsnCd_AM.getValue();
        } else {
            whereArray1[IDX_2] = bMsg.supvPsnCd_AS.getValue();
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Person Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Person Code";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(LEN_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Person Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(LEN_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * Set Manager/Supervisor Popup param
     * @param scrnMsg NLBL3090BMsg
     * @param EZDBStringItem cdItem
     * @param eventNm String
     * @return Popup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForS21PersonPopupAtHeaderLevel(NLBL3090BMsg scrnMsg, EZDBStringItem cdItem, String eventNm) {

        scrnMsg.xxScrEventNm_X.setValue(eventNm);
        scrnMsg.P.clear();

        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "Person Search Popup";
        params[IDX_2] = "S21_PSN_V";

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Person Code";
        whereArray1[IDX_1] = "PSN_CD";
        whereArray1[IDX_2] = cdItem.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Person Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Person Code";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(LEN_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Person Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(LEN_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * Set Coordinator Popup param
     * @param scrnMsg NLBL3090BMsg
     * @param abMsg NLBL3090_ABMsg
     * @param bbMsg NLBL3090_BBMsg
     * @param isAssign boolean
     * @return CoordinatorPopup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForCoordinatorPopup(NLBL3090BMsg scrnMsg, NLBL3090_ABMsg abMsg, NLBL3090_BBMsg bbMsg, boolean isAssign) {

        scrnMsg.xxScrEventNm_X.setValue("OpenWin_CdPerson");
        scrnMsg.P.clear();

        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "Person Search Popup";
        params[IDX_2] = "S21_PSN_V";

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Person Code";
        whereArray1[IDX_1] = "PSN_CD";
        if (isAssign) {
            whereArray1[IDX_2] = abMsg.schdCoordPsnCd_AC.getValue();
        } else {
            whereArray1[IDX_2] = bbMsg.schdCoordPsnCd_BC.getValue();
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Person Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Person Code";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(LEN_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Person Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(LEN_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * Set Carrier Popup param
     * @param scrnMsg NLBL3090BMsg
     * @param bbMsg NLBL3090_BBMsg
     * @return Carrier Popup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForCarrierPopup(NLBL3090BMsg scrnMsg, NLBL3090_BBMsg bbMsg) {

        scrnMsg.xxScrEventNm_X.setValue("OpenWin_Carrier");
        scrnMsg.P.clear();

        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "Carrier Search Popup";
        params[IDX_2] = "OTBD_CARR_V";

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Carrier Code";
        whereArray1[IDX_1] = "CARR_CD";
        whereArray1[IDX_2] = bbMsg.carrCd_B.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Carrier Name";
        whereArray2[IDX_1] = "CARR_NM";
        whereArray2[IDX_2] = bbMsg.locNm_B.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Carrier Code";
        columnArray1[IDX_1] = "CARR_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(LEN_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Carrier Name";
        columnArray2[IDX_1] = "CARR_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(LEN_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CARR_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NLBL3090BMsg
     * @return Object[]
     */
    public static Object[] setParamForStatePopup(NLBL3090BMsg scrnMsg, NLBL3090_BBMsg bbMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, "ST");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, "ST_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, "ST_DESC_TXT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, "ST_SORT_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, "State Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, "State Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, "State Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, "State Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, "State Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1, bbMsg.stCd_B);
        
        Object[] params = new Object[IDX_11];
        params[IDX_0] = scrnMsg.xxTblNm_P1;
        params[IDX_1] = scrnMsg.xxTblCdColNm_P1;
        params[IDX_2] = scrnMsg.xxTblNmColNm_P1;
        params[IDX_3] = scrnMsg.xxTblSortColNm_P1;
        params[IDX_4] = scrnMsg.xxScrNm_P1;
        params[IDX_5] = scrnMsg.xxHdrCdLbNm_P1;
        params[IDX_6] = scrnMsg.xxHdrNmLbNm_P1;
        params[IDX_7] = scrnMsg.xxDtlCdLbNm_P1;
        params[IDX_8] = scrnMsg.xxDtlNmLbNm_P1;
        params[IDX_9] = scrnMsg.xxCondCd_P1;
        params[IDX_10] = scrnMsg.xxCondNm_P1;

        return params;
    }

//    /**
//     * Set Warehouse Popup param
//     * @param scrnMsg NLBL3090BMsg
//     * @return Carrier Popup Param (NWAL1130) Object[]
//     */
//    public static Object[] setParamForWarehousePopup(NLBL3090BMsg scrnMsg) {
//
//        scrnMsg.xxScrEventNm_X.setValue("OpenWin_Wh");
//        scrnMsg.P.clear();
//
//        Object[] params = new Object[IDX_7];
//
//        params[IDX_0] = "";
//        params[IDX_1] = "Warehouse Search Popup";
//        params[IDX_2] = "RTL_WH";
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Warehouse Code";
//        whereArray1[IDX_1] = "RTL_WH_CD";
//        whereArray1[IDX_2] = scrnMsg.rtlWhCd_H1.getValue();
//        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        whereList.add(whereArray1);
//
//        Object[] whereArray2 = new Object[IDX_4];
//        whereArray2[IDX_0] = "Warehouse Name";
//        whereArray2[IDX_1] = "RTL_WH_NM";
//        whereArray2[IDX_2] = scrnMsg.rtlWhNm_H1.getValue();
//        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray2);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Warehouse Code";
//        columnArray1[IDX_1] = "RTL_WH_CD";
//        columnArray1[IDX_2] = BigDecimal.valueOf(LEN_20);
//        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray1);
//
//        Object[] columnArray2 = new Object[IDX_4];
//        columnArray2[IDX_0] = "Warehouse Name";
//        columnArray2[IDX_1] = "RTL_WH_NM";
//        columnArray2[IDX_2] = BigDecimal.valueOf(LEN_60);
//        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray2);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "RTL_WH_CD";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        params[IDX_5] = sortConditionList;
//
//        params[IDX_6] = scrnMsg.P;
//
//        return params;
//    }
    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLBL3090BMsg
     */
    public static void setInitParamForLocationPopup(NLBL3090BMsg scrnMsg) {

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
    }

    /**
     * The method explanation: set parameter for Location Popup
     * @param scrnMsg NLBL3090BMsg
     * @return Object[]
     */
    public static Object[] setParamForLocationPopup(NLBL3090BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, ZYPConstant.FLG_ON_Y);

        Object[] params = new Object[14];

        params[0] = scrnMsg.xxPopPrm_01;
        params[1] = scrnMsg.xxPopPrm_02;
        params[2] = scrnMsg.xxPopPrm_03;
        params[3] = scrnMsg.xxPopPrm_04;
        params[4] = scrnMsg.xxPopPrm_05;
        params[5] = scrnMsg.xxPopPrm_06;
        params[6] = scrnMsg.rtlWhCd_H1;
        params[7] = scrnMsg.xxPopPrm_08;
        params[8] = scrnMsg.xxPopPrm_09;
        params[9] = scrnMsg.xxPopPrm_10;
        params[10] = scrnMsg.xxPopPrm_11;
        params[11] = scrnMsg.xxPopPrm_12;
        params[12] = scrnMsg.xxPopPrm_13;
        params[13] = scrnMsg.xxPopPrm_14;

        return params;
    }

    /**
     * Input check
     * @param scrnMsg NLBL3090BMsg
     */
    public static void checkInput(NLBL3090BMsg scrnMsg) {
        NLBL3090CommonLogic.addCheckSearchItem(scrnMsg);

        if (TAB_ASSIGN.equals(scrnMsg.xxDplyTab.getValue())) {

            int mngRowNum = 0;
            int supvRowNum = 0;
            int schdCoordRowNum = 0;
            String prevMngPsnCd = "";
            String prevSupvPsnCd = "";
            String prevSchdCoordPsnCd = "";

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // set manager person code and supervisor person code
                // if its are invisible on detail list.
                if (mngRowNum == scrnMsg.A.no(i).xxNewRowNum_A2.getValueInt()) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).mgrPsnCd_AM, prevMngPsnCd);

                    if (supvRowNum == scrnMsg.A.no(i).xxNewRowNum_A3.getValueInt()) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).supvPsnCd_AS, prevSupvPsnCd);

                        if (schdCoordRowNum == scrnMsg.A.no(i).xxNewRowNum_A4.getValueInt()) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).schdCoordPsnCd_AC, prevSchdCoordPsnCd);
                        } else {
                            schdCoordRowNum = scrnMsg.A.no(i).xxNewRowNum_A4.getValueInt();
                            prevSchdCoordPsnCd = scrnMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                        }
                    } else {
                        supvRowNum = scrnMsg.A.no(i).xxNewRowNum_A3.getValueInt();
                        prevSupvPsnCd = scrnMsg.A.no(i).supvPsnCd_AS.getValue();

                        schdCoordRowNum = scrnMsg.A.no(i).xxNewRowNum_A4.getValueInt();
                        prevSchdCoordPsnCd = scrnMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                    }

                } else {
                    mngRowNum = scrnMsg.A.no(i).xxNewRowNum_A2.getValueInt();
                    prevMngPsnCd = scrnMsg.A.no(i).mgrPsnCd_AM.getValue();

                    supvRowNum = scrnMsg.A.no(i).xxNewRowNum_A3.getValueInt();
                    prevSupvPsnCd = scrnMsg.A.no(i).supvPsnCd_AS.getValue();

                    schdCoordRowNum = scrnMsg.A.no(i).xxNewRowNum_A4.getValueInt();
                    prevSchdCoordPsnCd = scrnMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                }
            }

        } else {

            int coordRowNum = 0;
            String prevCoordPsnCd = "";

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                // set coordinator person code if it is invisible on
                // detail list.
                if (coordRowNum == scrnMsg.B.no(i).xxNewRowNum_B2.getValueInt()) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).schdCoordPsnCd_BC, prevCoordPsnCd);
                } else {
                    coordRowNum = scrnMsg.B.no(i).xxNewRowNum_B2.getValueInt();
                    prevCoordPsnCd = scrnMsg.B.no(i).schdCoordPsnCd_BC.getValue();
                }
            }
        }
        NLBL3090CommonLogic.addCheckTableItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
