/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410.common;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_APPLY;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_APPROVE;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_CLEAR;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_DELETE;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_DOWNLOAD;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_REJECT;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_RESET;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_RETURN;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_SAVE;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_CMN_BTN_SUBMIT;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.FUNC_ID_SUBMIT;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.MODE_COMP_VIEW;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.MODE_EDIT;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.MODE_INIT;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.MODE_IN_PROCESS;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.MODE_READ_ONLY;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.SCREEN_ID;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.TAB_CONF;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.TAB_PARTS;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.TAB_TASK;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_ADD_CONFIG;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_ADD_PARTS;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_ADD_TASK;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_ADD_CONFIG_NAME;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_ADD_PARTS_NAME;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BTN_ADD_TASK_NAME;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BUTTON_DISABLED_STR;

import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1410.NPAL1410BMsg;
import business.servlet.NPAL1410.NPAL1410_ABMsg;
import business.servlet.NPAL1410.NPAL1410_BBMsg;
import business.servlet.NPAL1410.NPAL1410_CBMsg;
import business.servlet.NPAL1410.NPAL1410_DBMsg;
import business.servlet.NPAL1410.NPAL1410_EBMsg;
import business.servlet.NPAL1410.NPAL1410_XBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS             Yasushi Nomura  Create          N/A
 * 10/04/2016  CITS             T.Wada          Update          QC#13807
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 * 01/24/2018   CITS            T.Wada          Update          QC#23072
 * 07/13/2018   CITS            T.Hakodate      Update          QC#26868
 *</pre>
 */
public class NPAL1410CommonLogic {

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPLY[0], BTN_CMN_BTN_APPLY[1], BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPROVE[0], BTN_CMN_BTN_APPROVE[1], BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_REJECT[0], BTN_CMN_BTN_REJECT[1], BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DOWNLOAD[0], BTN_CMN_BTN_DOWNLOAD[1], BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DELETE[0], BTN_CMN_BTN_DELETE[1], BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RETURN[0], BTN_CMN_BTN_RETURN[1], BTN_CMN_BTN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_CONFIG[0], BTN_ADD_CONFIG[1], BTN_ADD_CONFIG[2], 1, null);
    }

    /**
     * Init
     * @param scrnMsg NPAL1410BMsg
     */
    public static void commonInit(NPAL1410BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A_Right", scrnMsg.A);
        tblColor.clearRowsBG("B_Right", scrnMsg.B);
        tblColor.clearRowsBG("C_Right", scrnMsg.C);
        tblColor.clearRowsBG("D_Right", scrnMsg.D);
        //QC#18675 ADD START
        tblColor.clearRowsBG("E_Right", scrnMsg.E);
        //QC#18675 ADD END
        if (0 < scrnMsg.A.getValidCount() && TAB_CONF.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG("A_Right", scrnMsg.A);
        }
        if (0 < scrnMsg.B.getValidCount() && TAB_PARTS.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG("B_Right", scrnMsg.B);
        }
        if (0 < scrnMsg.C.getValidCount() && TAB_PARTS.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG("C_Right", scrnMsg.C);
        }
        if (0 < scrnMsg.D.getValidCount() && TAB_TASK.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG("D_Right", scrnMsg.D);
        }
        //QC#18675 ADD START
        if (0 < scrnMsg.A.getValidCount() && "SoRws".equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG("E_Right", scrnMsg.E);
        }
        //QC#18675 ADD END
    }

    /**
     * @param scrnMsg NPAL1410BMsg
     */
    public static void setAppFracDigit(NPAL1410BMsg scrnMsg) {
        int dig = scrnMsg.aftDeclPntDigitNum_HH.getValueInt();
        scrnMsg.rmnfPrtUsgCostAmt_H1.setAppFracDigit(dig);
        scrnMsg.rmnfTotCostAmt_H2.setAppFracDigit(dig);
        scrnMsg.rmnfTotLborCostAmt_H1.setAppFracDigit(dig);
        scrnMsg.rmnfMachCostAmt_H1.setAppFracDigit(dig);
        scrnMsg.assetRecovCostAmt_H1.setAppFracDigit(dig);
        scrnMsg.rmnfTotCostAmt_H1.setAppFracDigit(dig);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).thisMthTotStdCostAmt_B1.setAppFracDigit(dig);
            scrnMsg.B.no(i).assetRecovCostAmt_B1.setAppFracDigit(dig);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).thisMthTotStdCostAmt_C1.setAppFracDigit(dig);
            scrnMsg.C.no(i).assetRecovCostAmt_C1.setAppFracDigit(dig);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).rmnfPrtUsgCostAmt_D1.setAppFracDigit(dig);
            scrnMsg.D.no(i).rmnfLborCostAmt_D1.setAppFracDigit(dig);
            scrnMsg.D.no(i).rmnfTotCostAmt_D1.setAppFracDigit(dig);
        }
    }

    /**
     * Do addCheckItem for items
     * @param scrnMsg NPAL1410BMsg
     */
    public static void addCheckItem(NPAL1410BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rmnfRtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.ownrTechTocCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rmnfMainUnitSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.whLoctrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rmnfRtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rmnfStartDt_H1);
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdCmntTxt_H1);
        if (TAB_CONF.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).rmnfToCmptMdseCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).rmnfToCmptSerNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).srcRtlSwhCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).stkStsCd_A1);
            }
        } else if (TAB_PARTS.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).prtRqstQty_B1);
            }
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseCd_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).prtRqstQty_C1);
            }
        }
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NPAL1410BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    }

    private static void enable(EZDBItem item) {
        item.setInputProtected(false);
    }

    private static void disable(EZDBItem item) {
        item.setInputProtected(true);
    }

    private static void enable(S21CommonHandler handler, String itemName) {
        handler.setButtonEnabled(itemName, true);
    }

    private static void disable(S21CommonHandler handler, String itemName) {
        handler.setButtonEnabled(itemName, false);
    }

    /**
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1410BMsg
     * @param funcList List<String>
     */
    public static void control(S21CommonHandler handler, NPAL1410BMsg scrnMsg, List<String> funcList) {
        String mode;
        if (MODE_COMP_VIEW.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            mode = MODE_COMP_VIEW;
        } else {
            boolean isReadOnly = true;
            for (String function : funcList) {
                if (FUNC_ID_SUBMIT.equals(function)) {
                    isReadOnly = false;
                    break;
                }
            }
            if (isReadOnly) {
                mode = MODE_READ_ONLY;
            } else {
                if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfOrdStsCd_HH)) {
                    mode = MODE_INIT;
                } else if (RMNF_ORD_STS.SAVED.equals(scrnMsg.rmnfOrdStsCd_HH.getValue())) {
                    mode = MODE_EDIT;
                } else if (RMNF_ORD_STS.SO_CANCELLED.equals(scrnMsg.rmnfOrdStsCd_HH.getValue())) {
                    mode = MODE_EDIT;
                } else if (RMNF_ORD_STS.IN_PROCESS.equals(scrnMsg.rmnfOrdStsCd_HH.getValue())) {
                    mode = MODE_IN_PROCESS;
                // QC#23072
                } else if (RMNF_ORD_STS.COMPLETED.equals(scrnMsg.rmnfOrdStsCd_HH.getValue())) {
                    mode = MODE_IN_PROCESS;
                } else {
                    mode = MODE_READ_ONLY;
                }
            }
        }
        // Common Header
        enable(scrnMsg.rmnfOrdNum_H1);
        enable(handler, "Search");
        disable(scrnMsg.rtlWhNm_H1);
        disable(scrnMsg.techNm_H1);
        disable(scrnMsg.rmnfOrdStsNm_H1);
        disable(scrnMsg.rmnfEndDt_H1);
        disable(scrnMsg.t_MdlNm_H1);
        disable(scrnMsg.rmnfPrtUsgCostAmt_H1);
        disable(scrnMsg.rmnfTotCostAmt_H2);
        disable(scrnMsg.rmnfTotLborCostAmt_H1);
        disable(scrnMsg.rmnfMachCostAmt_H1);
        disable(scrnMsg.assetRecovCostAmt_H1);
        disable(scrnMsg.rmnfTotCostAmt_H1);
        disable(scrnMsg.rmnfRtlSwhCd_H1);
        // Config Tab
        disable(scrnMsg.svcConfigMstrPk_A1);
        //QC#18675 ADD START
        disable(scrnMsg.t_MdlNm_H2);
        disable(scrnMsg.rwsNum_A1);
        disable(scrnMsg.soNum_A1);
        //QC#18675 ADD END
        disable(scrnMsg.t_MdlNm_A1);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1410_ABMsg abMsg = scrnMsg.A.no(i);
            disable(abMsg.mdseDescShortTxt_A1);
            disable(abMsg.mdseDescShortTxt_A2);
        }
        // Parts Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1410_BBMsg bbMsg = scrnMsg.B.no(i);
            disable(bbMsg.xxChkBox_B1);
            disable(bbMsg.mdseCd_B1);
            disable(bbMsg.mdseDescShortTxt_B1);
            disable(bbMsg.thisMthTotStdCostAmt_B1);
            disable(bbMsg.invtyAvalQty_B1);
            disable(bbMsg.procrTpNm_B1);
            disable(bbMsg.dplyVndNm_B1);
            disable(bbMsg.assetRecovCostAmt_B1);
            disable(bbMsg.rmnfPrtQty_B1);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NPAL1410_CBMsg cbMsg = scrnMsg.C.no(i);
            disable(cbMsg.mdseDescShortTxt_C1);
            disable(cbMsg.thisMthTotStdCostAmt_C1);
            disable(cbMsg.invtyAvalQty_C1);
            disable(cbMsg.procrTpNm_C1);
            disable(cbMsg.dplyVndNm_C1);
            disable(cbMsg.assetRecovCostAmt_C1);
            disable(cbMsg.rmnfPrtQty_C1);
        }
        // Task Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1410_DBMsg dbMsg = scrnMsg.D.no(i);
            enable(dbMsg.xxPopPrm_D1);
            disable(dbMsg.rmnfTaskDescTxt_D1);
            disable(dbMsg.techTocCd_D1);
            disable(dbMsg.techNm_D1);
            disable(dbMsg.rmnfLborAot_D1);
            disable(dbMsg.rmnfPrtUsgCostAmt_D1);
            disable(dbMsg.rmnfLborCostAmt_D1);
            disable(dbMsg.rmnfTotCostAmt_D1);
        }
        //QC#18675 ADD START
        // SoRws Tab
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            NPAL1410_EBMsg ebMsg = scrnMsg.E.no(i);
            disable(ebMsg.rwsNum_E1);
            disable(ebMsg.sceOrdTpNm_E1);
            disable(ebMsg.rtlWhNm_E1);
            disable(ebMsg.rtlSwhNm_E1);
            disable(ebMsg.shpgStsDescTxt_E1);
            disable(ebMsg.xxCratDt_E1);
        }
        //QC#18675 ADD END
        // Footer
        disable(handler, BTN_CMN_BTN_APPLY[0]);
        disable(handler, BTN_CMN_BTN_APPROVE[0]);
        disable(handler, BTN_CMN_BTN_REJECT[0]);
        disable(handler, BTN_CMN_BTN_DOWNLOAD[0]);
        disable(handler, BTN_CMN_BTN_DELETE[0]);
        enable(handler, BTN_CMN_BTN_CLEAR[0]);
        enable(handler, BTN_CMN_BTN_RESET[0]);
        enable(handler, BTN_CMN_BTN_RETURN[0]);

        if (MODE_INIT.equals(mode)) {
            controlForInit(handler, scrnMsg);
        } else if (MODE_EDIT.equals(mode)) {
            controlForEdit(handler, scrnMsg);
        } else if (MODE_IN_PROCESS.equals(mode)) {
            controlForInProcess(handler, scrnMsg);
        } else if (MODE_COMP_VIEW.equals(mode)) {
            controlForCompletionView(handler, scrnMsg);
        } else {
            controlForReadonly(handler, scrnMsg);
        }
        setF11Button(handler, scrnMsg);
    }

    private static void controlForReadonly(S21CommonHandler handler, NPAL1410BMsg scrnMsg) {
        // Header(Button)
        disable(handler, "getConfig");
        disable(handler, "Create");
        disable(handler, "Completion");
        disable(handler, "Cancel");
        disable(handler, "GetWhName");
        disable(handler, "GetTechName");
        enable(handler, "Attachments");
        // Header
        disable(scrnMsg.rmnfRtlWhCd_AC);
        disable(scrnMsg.rmnfRtlWhCd_H1);
        disable(scrnMsg.ownrTechTocCd_AC);
        disable(scrnMsg.ownrTechTocCd_H1);
        disable(scrnMsg.rmnfMainUnitSerNum_AC);
        disable(scrnMsg.rmnfMainUnitSerNum_H1);
        disable(scrnMsg.rmnfOrdTpCd_SE);
        disable(scrnMsg.whLoctrCd_AC);
        disable(scrnMsg.whLoctrCd_H1);
        disable(scrnMsg.rmnfRtlSwhCd_AC);
        disable(scrnMsg.rmnfRtlSwhCd_H1);
        disable(scrnMsg.rmnfStartDt_H1);
        disable(scrnMsg.rmnfOrdCmntTxt_H1);
        // Config Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_CO", i, false);
            handler.setButtonEnabled("GetItemName_CO", i, false);
            handler.setButtonEnabled("OpenWin_Item_CC", i, false);
            handler.setButtonEnabled("GetItemName_CC", i, false);
            handler.setButtonEnabled("OpenWin_Serial_CO", i, false);
            NPAL1410_ABMsg abMsg = scrnMsg.A.no(i);
            disable(abMsg.rmvConfigFlg_A1);
            disable(abMsg.mdseCd_A1);
            disable(abMsg.serNum_A1);
            disable(abMsg.rmnfToCmptMdseCd_A1);
            disable(abMsg.rmnfToCmptSerNum_A1);
            disable(abMsg.srcRtlSwhCd_A1);
            disable(abMsg.stkStsCd_A1);
        }
        disable(handler, "AddComponent");
        disable(handler, "DeleteComponent");
        disable(handler, "OpenWin_SWH");
        // Parts Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1410_BBMsg bbMsg = scrnMsg.B.no(i);
            disable(bbMsg.prtRqstQty_B1);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_P", i, false);
            handler.setButtonEnabled("GetItemName_P", i, false);
            NPAL1410_CBMsg cbMsg = scrnMsg.C.no(i);
            disable(cbMsg.xxChkBox_C1);
            disable(cbMsg.mdseCd_C1);
            disable(cbMsg.prtRqstQty_C1);
        }
        disable(handler, "AddParts");
        disable(handler, "DeleteParts");
        // Task Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1410_DBMsg dbMsg = scrnMsg.D.no(i);
            disable(dbMsg.xxChkBox_D1);
        }
        disable(handler, "OpenWin_TaskEntryNew");
        disable(handler, "DeleteTask");
        // Footer
        disable(handler, BTN_CMN_BTN_SAVE[0]);
        disable(handler, BTN_CMN_BTN_SUBMIT[0]);
    }

    private static void controlForInit(S21CommonHandler handler, NPAL1410BMsg scrnMsg) {
        // Header(Button)
        enable(handler, "getConfig");
        disable(handler, "Create");
        disable(handler, "Completion");
        disable(handler, "Cancel");
        enable(handler, "GetWhName");
        enable(handler, "GetTechName");
        disable(handler, "Attachments");

        // Header
        enable(scrnMsg.rmnfRtlWhCd_AC);
        enable(scrnMsg.rmnfRtlWhCd_H1);
        enable(scrnMsg.ownrTechTocCd_AC);
        enable(scrnMsg.ownrTechTocCd_H1);
        enable(scrnMsg.rmnfMainUnitSerNum_AC);
        enable(scrnMsg.rmnfMainUnitSerNum_H1);
        enable(scrnMsg.rmnfOrdTpCd_SE);
        enable(scrnMsg.whLoctrCd_AC);
        enable(scrnMsg.whLoctrCd_H1);
        disable(scrnMsg.rmnfRtlSwhCd_AC);
        disable(scrnMsg.rmnfRtlSwhCd_H1);
        enable(scrnMsg.rmnfStartDt_H1);
        enable(scrnMsg.rmnfOrdCmntTxt_H1);
        // Config Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1410_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(abMsg.addConfigFlg_A1.getValue())) {
                handler.setButtonEnabled("OpenWin_Item_CO", i, true);
                handler.setButtonEnabled("GetItemName_CO", i, true);
                handler.setButtonEnabled("OpenWin_Serial_CO", i, true);
                enable(abMsg.mdseCd_A1);
                enable(abMsg.serNum_A1);
                // QC14013
                if (ZYPConstant.FLG_ON_Y.equals(abMsg.mainUnitLineFlg_A1.getValue())) {
                    disable(abMsg.rmvConfigFlg_A1);
                } else {
                    enable(abMsg.rmvConfigFlg_A1);
                }
            } else {
                handler.setButtonEnabled("OpenWin_Item_CO", i, false);
                handler.setButtonEnabled("GetItemName_CO", i, false);
                handler.setButtonEnabled("OpenWin_Serial_CO", i, false);
                disable(abMsg.mdseCd_A1);
                disable(abMsg.serNum_A1);
                // QC14013
                if (ZYPConstant.FLG_ON_Y.equals(abMsg.mainUnitLineFlg_A1.getValue())) {
                    disable(abMsg.rmvConfigFlg_A1);
                } else {
                    enable(abMsg.rmvConfigFlg_A1);
                }
            }
            handler.setButtonEnabled("OpenWin_Item_CC", i, true);
            handler.setButtonEnabled("GetItemName_CC", i, true);
            enable(abMsg.rmnfToCmptMdseCd_A1);
            enable(abMsg.rmnfToCmptSerNum_A1);
            enable(abMsg.srcRtlSwhCd_A1);
            enable(abMsg.stkStsCd_A1);
        }
        enable(handler, "AddComponent");
        enable(handler, "DeleteComponent");
        enable(handler, "OpenWin_SWH");

        // Parts Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1410_BBMsg bbMsg = scrnMsg.B.no(i);
            //QC#13882
            disable(bbMsg.prtRqstQty_B1);
            // QC#26868 ADD END
            if (ZYPConstant.FLG_ON_1.equals(bbMsg.rmnfPrtRqstProcCd_B1.getValue())) {
                disable(bbMsg.xxChkBox_B1);
            } else {
                enable(bbMsg.xxChkBox_B1);
            }
            // QC#26868 ADD END
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_P", i, true);
            handler.setButtonEnabled("GetItemName_P", i, true);
            NPAL1410_CBMsg cbMsg = scrnMsg.C.no(i);
            enable(cbMsg.xxChkBox_C1);
            enable(cbMsg.mdseCd_C1);
            enable(cbMsg.prtRqstQty_C1);
        }
        enable(handler, "AddParts");
        enable(handler, "DeleteParts");
        // Task Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1410_DBMsg dbMsg = scrnMsg.D.no(i);
            enable(dbMsg.xxChkBox_D1);
        }
        disable(handler, "OpenWin_TaskEntryNew");
        disable(handler, "DeleteTask");
        // Footer
        enable(handler, BTN_CMN_BTN_SAVE[0]);
        disable(handler, BTN_CMN_BTN_SUBMIT[0]);
    }

    private static void controlForEdit(S21CommonHandler handler, NPAL1410BMsg scrnMsg) {
        // Header(Button)
        enable(handler, "getConfig");
        enable(handler, "Create");
        disable(handler, "Completion");
        enable(handler, "Cancel");
        enable(handler, "GetWhName");
        enable(handler, "GetTechName");
        enable(handler, "Attachments");

        // Header
        enable(scrnMsg.rmnfRtlWhCd_AC);
        enable(scrnMsg.rmnfRtlWhCd_H1);
        enable(scrnMsg.ownrTechTocCd_AC);
        enable(scrnMsg.ownrTechTocCd_H1);
        disable(scrnMsg.rmnfMainUnitSerNum_AC);
        disable(scrnMsg.rmnfMainUnitSerNum_H1);
        enable(scrnMsg.rmnfOrdTpCd_SE);
        enable(scrnMsg.whLoctrCd_AC);
        enable(scrnMsg.whLoctrCd_H1);
        disable(scrnMsg.rmnfRtlSwhCd_AC);
        disable(scrnMsg.rmnfRtlSwhCd_H1);
        enable(scrnMsg.rmnfStartDt_H1);
        enable(scrnMsg.rmnfOrdCmntTxt_H1);
        // Config Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1410_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(abMsg.addConfigFlg_A1.getValue())) {
                handler.setButtonEnabled("OpenWin_Item_CO", i, true);
                handler.setButtonEnabled("GetItemName_CO", i, true);
                handler.setButtonEnabled("OpenWin_Serial_CO", i, true);
                enable(abMsg.mdseCd_A1);
                enable(abMsg.serNum_A1);
                if (ZYPConstant.FLG_ON_Y.equals(abMsg.mainUnitLineFlg_A1.getValue())) {
                    disable(abMsg.rmvConfigFlg_A1);
                } else {
                    enable(abMsg.rmvConfigFlg_A1);
                }
            } else {
                handler.setButtonEnabled("OpenWin_Item_CO", i, false);
                handler.setButtonEnabled("GetItemName_CO", i, false);
                handler.setButtonEnabled("OpenWin_Serial_CO", i, false);
                disable(abMsg.mdseCd_A1);
                disable(abMsg.serNum_A1);

                if (ZYPConstant.FLG_ON_Y.equals(abMsg.mainUnitLineFlg_A1.getValue())) {
                    disable(abMsg.rmvConfigFlg_A1);
                } else {
                    enable(abMsg.rmvConfigFlg_A1);
                }

            }
            //enable(abMsg.rmvConfigFlg_A1);
            handler.setButtonEnabled("OpenWin_Item_CC", i, true);
            handler.setButtonEnabled("GetItemName_CC", i, true);
            enable(abMsg.rmnfToCmptMdseCd_A1);
            enable(abMsg.rmnfToCmptSerNum_A1);
            enable(abMsg.srcRtlSwhCd_A1);
            enable(abMsg.stkStsCd_A1);
        }
        enable(handler, "AddComponent");
        enable(handler, "DeleteComponent");
        enable(handler, "OpenWin_SWH");
        // Parts Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1410_BBMsg bbMsg = scrnMsg.B.no(i);
            disable(bbMsg.prtRqstQty_B1);
            // QC#26868 ADD END
            if (ZYPConstant.FLG_ON_1.equals(bbMsg.rmnfPrtRqstProcCd_B1.getValue())) {
                disable(bbMsg.xxChkBox_B1);
            } else {
                enable(bbMsg.xxChkBox_B1);
            }
            // QC#26868 ADD END
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_P", i, true);
            handler.setButtonEnabled("GetItemName_P", i, true);
            NPAL1410_CBMsg cbMsg = scrnMsg.C.no(i);
            if (ZYPConstant.FLG_ON_1.equals(cbMsg.rmnfPrtRqstProcCd_C1.getValue())) {
                disable(cbMsg.xxChkBox_C1);
                disable(cbMsg.mdseCd_C1);
                disable(cbMsg.prtRqstQty_C1);
            } else {
                enable(cbMsg.xxChkBox_C1);
                enable(cbMsg.mdseCd_C1);
                enable(cbMsg.prtRqstQty_C1);

            }
        }
        enable(handler, "AddParts");
        enable(handler, "DeleteParts");

        // Task Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1410_DBMsg dbMsg = scrnMsg.D.no(i);
            enable(dbMsg.xxChkBox_D1);
        }
        disable(handler, "OpenWin_TaskEntryNew");
        disable(handler, "DeleteTask");
        // Footer
        enable(handler, BTN_CMN_BTN_SAVE[0]);
        disable(handler, BTN_CMN_BTN_SUBMIT[0]);
    }

    private static void controlForInProcess(S21CommonHandler handler, NPAL1410BMsg scrnMsg) {
        // Header(Button)
        disable(handler, "getConfig");
        enable(handler, "Create");
        enable(handler, "Completion");
        enable(handler, "Cancel");
        disable(handler, "GetWhName");
        disable(handler, "GetTechName");
        enable(handler, "Attachments");

        // Header
        disable(scrnMsg.rmnfRtlWhCd_AC);
        disable(scrnMsg.rmnfRtlWhCd_H1);
        disable(scrnMsg.ownrTechTocCd_AC);
        disable(scrnMsg.ownrTechTocCd_H1);
        disable(scrnMsg.rmnfMainUnitSerNum_AC);
        disable(scrnMsg.rmnfMainUnitSerNum_H1);
        disable(scrnMsg.rmnfOrdTpCd_SE);
        disable(scrnMsg.whLoctrCd_AC);
        disable(scrnMsg.whLoctrCd_H1);
        disable(scrnMsg.rmnfRtlSwhCd_AC);
        disable(scrnMsg.rmnfRtlSwhCd_H1);
        disable(scrnMsg.rmnfStartDt_H1);
        enable(scrnMsg.rmnfOrdCmntTxt_H1);
        // Config Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_CO", i, false);
            handler.setButtonEnabled("GetItemName_CO", i, false);
            handler.setButtonEnabled("OpenWin_Item_CC", i, false);
            handler.setButtonEnabled("GetItemName_CC", i, false);
            handler.setButtonEnabled("OpenWin_Serial_CO", i, false);
            NPAL1410_ABMsg abMsg = scrnMsg.A.no(i);
            disable(abMsg.rmvConfigFlg_A1);
            disable(abMsg.mdseCd_A1);
            disable(abMsg.serNum_A1);
            disable(abMsg.rmnfToCmptMdseCd_A1);
            disable(abMsg.rmnfToCmptSerNum_A1);
            disable(abMsg.srcRtlSwhCd_A1);
            disable(abMsg.stkStsCd_A1);
        }
        disable(handler, "AddComponent");
        disable(handler, "DeleteComponent");
        disable(handler, "OpenWin_SWH");
        // Parts Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1410_BBMsg bbMsg = scrnMsg.B.no(i);
            disable(bbMsg.prtRqstQty_B1);
            // QC#26868 ADD END
            if (ZYPConstant.FLG_ON_1.equals(bbMsg.rmnfPrtRqstProcCd_B1.getValue())) {
                disable(bbMsg.xxChkBox_B1);
            } else {
                enable(bbMsg.xxChkBox_B1);
            }
            // QC#26868 ADD END
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NPAL1410_CBMsg cbMsg = scrnMsg.C.no(i);
            if (cbMsg.xxNewRowNum_C1.getValueInt() < 0) {
                handler.setButtonEnabled("OpenWin_Item_P", i, true);
                handler.setButtonEnabled("GetItemName_P", i, true);
                enable(cbMsg.xxChkBox_C1);
                enable(cbMsg.mdseCd_C1);
                enable(cbMsg.prtRqstQty_C1);
            } else {

                if (ZYPConstant.FLG_ON_1.equals(cbMsg.rmnfPrtRqstProcCd_C1.getValue())) {
                    handler.setButtonEnabled("OpenWin_Item_P", i, false);
                    handler.setButtonEnabled("GetItemName_P", i, false);
                    disable(cbMsg.xxChkBox_C1);
                    disable(cbMsg.mdseCd_C1);
                    disable(cbMsg.prtRqstQty_C1);
                } else {
                    enable(cbMsg.xxChkBox_C1);
                    enable(cbMsg.mdseCd_C1);
                    enable(cbMsg.prtRqstQty_C1);

                }

            }
        }
        enable(handler, "AddParts");
        enable(handler, "DeleteParts");
        // Task Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1410_DBMsg dbMsg = scrnMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(dbMsg.xxPopPrm_DT)) {
                disable(dbMsg.xxChkBox_D1);
            } else {
                enable(dbMsg.xxChkBox_D1);
            }
        }
        enable(handler, "OpenWin_TaskEntryNew");
        enable(handler, "DeleteTask");
        // Footer
        disable(handler, BTN_CMN_BTN_SAVE[0]);
        disable(handler, BTN_CMN_BTN_SUBMIT[0]);
    }

    private static void controlForCompletionView(S21CommonHandler handler, NPAL1410BMsg scrnMsg) {
        // Header(Button)
        disable(handler, "getConfig");
        disable(handler, "Create");
        disable(handler, "Completion");
        enable(handler, "Cancel");
        disable(handler, "GetWhName");
        disable(handler, "GetTechName");
        enable(handler, "Attachments");

        // Header
        disable(scrnMsg.rmnfOrdNum_H1);
        disable(handler, "Search");
        disable(scrnMsg.rmnfRtlWhCd_AC);
        disable(scrnMsg.rmnfRtlWhCd_H1);
        disable(scrnMsg.ownrTechTocCd_AC);
        disable(scrnMsg.ownrTechTocCd_H1);
        disable(scrnMsg.rmnfMainUnitSerNum_AC);
        disable(scrnMsg.rmnfMainUnitSerNum_H1);
        disable(scrnMsg.rmnfOrdTpCd_SE);
        disable(scrnMsg.whLoctrCd_AC);
        disable(scrnMsg.whLoctrCd_H1);
        disable(scrnMsg.rmnfRtlSwhCd_AC);
        disable(scrnMsg.rmnfRtlSwhCd_H1);
        disable(scrnMsg.rmnfStartDt_H1);
        disable(scrnMsg.rmnfOrdCmntTxt_H1);
        // Config Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_CO", i, false);
            handler.setButtonEnabled("GetItemName_CO", i, false);
            handler.setButtonEnabled("OpenWin_Item_CC", i, false);
            handler.setButtonEnabled("GetItemName_CC", i, false);
            handler.setButtonEnabled("OpenWin_Serial_CO", i, false);
            NPAL1410_ABMsg abMsg = scrnMsg.A.no(i);
            disable(abMsg.rmvConfigFlg_A1);
            disable(abMsg.mdseCd_A1);
            disable(abMsg.serNum_A1);
            disable(abMsg.rmnfToCmptMdseCd_A1);
            disable(abMsg.rmnfToCmptSerNum_A1);
            disable(abMsg.srcRtlSwhCd_A1);
            disable(abMsg.stkStsCd_A1);
        }
        disable(handler, "AddComponent");
        disable(handler, "DeleteComponent");
        disable(handler, "OpenWin_SWH");
        // Parts Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1410_BBMsg bbMsg = scrnMsg.B.no(i);
            disable(bbMsg.prtRqstQty_B1);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            handler.setButtonEnabled("OpenWin_Item_P", i, false);
            handler.setButtonEnabled("GetItemName_P", i, false);
            NPAL1410_CBMsg cbMsg = scrnMsg.C.no(i);
            disable(cbMsg.xxChkBox_C1);
            disable(cbMsg.mdseCd_C1);
            disable(cbMsg.prtRqstQty_C1);
        }
        disable(handler, "AddParts");
        disable(handler, "DeleteParts");
        // Task Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1410_DBMsg dbMsg = scrnMsg.D.no(i);
            disable(dbMsg.xxChkBox_D1);
            disable(dbMsg.xxPopPrm_D1);
        }
        disable(handler, "OpenWin_TaskEntryNew");
        disable(handler, "DeleteTask");
        // Footer
        disable(handler, BTN_CMN_BTN_SAVE[0]);
        enable(handler, BTN_CMN_BTN_SUBMIT[0]);
        disable(handler, BTN_CMN_BTN_CLEAR[0]);
        disable(handler, BTN_CMN_BTN_RESET[0]);
        disable(handler, BTN_CMN_BTN_RETURN[0]);
    }

    /**
     * toArray_popupForZYPL0310
     * @param p NPAL1410_XBMsgArray
     * @param size int
     * @return Object[] 
     */
    public static Object[] toArray_popupForZYPL0310(NPAL1410_XBMsgArray p, int size) {

        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

    /**
     * setF11Button
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1410BMsg
     */
    public static void setF11Button(S21CommonHandler handler,NPAL1410BMsg scrnMsg){
        if (TAB_CONF.equals(scrnMsg.xxDplyTab.getValue())){
            handler.setButtonProperties(BTN_ADD_CONFIG[0], BTN_ADD_CONFIG[1], BTN_ADD_CONFIG[2], 1,null);
            if (BUTTON_DISABLED_STR.equals(handler.getButtonStatus(BTN_ADD_CONFIG_NAME).trim())) {
                disable(handler, BTN_ADD_CONFIG[0]);
            }
        } else if(TAB_PARTS.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonProperties(BTN_ADD_PARTS[0], BTN_ADD_PARTS[1], BTN_ADD_PARTS[2], 1,null);
            if (BUTTON_DISABLED_STR.equals(handler.getButtonStatus(BTN_ADD_PARTS_NAME).trim())) {
                disable(handler, BTN_ADD_PARTS[0]);
            }
        } else if (TAB_TASK.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonProperties(BTN_ADD_TASK[0], BTN_ADD_TASK[1], BTN_ADD_TASK[2], 1,null);
            if (BUTTON_DISABLED_STR.equals(handler.getButtonStatus(BTN_ADD_TASK_NAME).trim())) {
                disable(handler, BTN_ADD_TASK[0]);
            }
        }
    }
}
