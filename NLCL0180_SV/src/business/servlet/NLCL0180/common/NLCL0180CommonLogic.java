/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NLCL0180.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NLCL0180.NLCL0180CMsg;
import business.servlet.NLCL0180.NLCL0180BMsg;
import business.servlet.NLCL0180.constant.NLCL0180Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 * 09/28/2009   Fujitsu         FAP)Y.Furuta        Update          N/A
 * 05/22/2013   Fujitsu         T.Tozuka            Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLCL0180CommonLogic implements NLCL0180Constant {

    /**
     * @return bizMsg NLCL0180CMsg
     */
    public static NLCL0180CMsg setRequestData_NLCL0180_INIT() {

        NLCL0180CMsg bizMsg;

        bizMsg = new NLCL0180CMsg();
        bizMsg.setBusinessID("NLCL0180");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void transMsgCheck(NLCL0180BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.addCheckItem(scrnMsg.whCd_LK);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.addCheckItem(scrnMsg.whCd_P1);
        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.addCheckItem(scrnMsg.locNm_P1);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.arvDt);
        scrnMsg.addCheckItem(scrnMsg.vndCd_LK);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.vndNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_LK);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.mdseNm);
        scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H1);
        scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H2);
        scrnMsg.A.setCheckParam(new String[] {"XX_CHK_BOX_A1", "ITEM_LINE_NUM_A1", "MDSE_CD_A1", "MDSE_NM_A1", "XX_RQST_QTY_A1", "XX_RQST_QTY_A2" }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.addCheckItem(scrnMsg.xxTblNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxTblCdColNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxTblNmColNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxTblSortColNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxScrNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxHdrCdLbNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxHdrNmLbNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxDtlCdLbNm_VE);
        scrnMsg.addCheckItem(scrnMsg.xxDtlNmLbNm_VE);
        scrnMsg.addCheckItem(scrnMsg.rgtnStsCd_VE);
        scrnMsg.addCheckItem(scrnMsg.xxSrchFuncCd_VE);
        scrnMsg.addCheckItem(scrnMsg.rgtnStsCd_MD);
        scrnMsg.addCheckItem(scrnMsg.xxSrchFuncCd_MD);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL0180BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NLCL0180BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("Display_VendorName", "Display_VendorName", ">>", 1, null);
        scrnAppli.setButtonProperties("Display_MDSEName", "Display_MDSEName", ">>", 1, null);
        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties("Add_Dtail_Line", "Add_Dtail_Line", "Add", 1, null);
            scrnAppli.setButtonProperties("btn1", "", "Save", 1, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
            scrnAppli.setButtonProperties("btn3", "CMN_Apply", "Apply", 1, null);
            scrnAppli.setButtonProperties("btn4", "", "Approve", 1, null);
            scrnAppli.setButtonProperties("btn5", "", "Reject", 1, null);
            scrnAppli.setButtonProperties("btn7", "", "Delete", 1, null);
        } else {
            scrnAppli.setButtonProperties("Add_Dtail_Line", "Add_Dtail_Line", "Add", 0, null);
            scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
            scrnAppli.setButtonProperties("btn3", "CMN_Apply", "Apply", 0, null);
            scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
            scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
            scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        }
        // QC#16256 add end
        scrnAppli.setButtonProperties("btn6", "", "Download", 1, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        clearVisibility(scrnMsg);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180_INIT(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);

        scrnAppli.setButtonConfirmMsg("CMN_Submit", "ZZM8102I", new String[] {"Submit" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Clear", "ZZM8102I", new String[] {"Clear" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Reset", "ZZM8102I", new String[] {"Reset" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Return", "ZZM8102I", new String[] {"Return" }, 0);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180_INIT(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.whCd_P1);
        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    private static void clearVisibility(NLCL0180BMsg scrnMsg) {

        EZDGUIAttribute xxbtn1 = new EZDGUIAttribute("NLCL0180Scrn00", "btn1");
        xxbtn1.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn1);

        EZDGUIAttribute xxbtn2 = new EZDGUIAttribute("NLCL0180Scrn00", "btn2");
        xxbtn2.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn2);

        EZDGUIAttribute xxbtn3 = new EZDGUIAttribute("NLCL0180Scrn00", "btn3");
        xxbtn3.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn3);

        EZDGUIAttribute xxbtn4 = new EZDGUIAttribute("NLCL0180Scrn00", "btn4");
        xxbtn4.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn4);

        EZDGUIAttribute xxbtn5 = new EZDGUIAttribute("NLCL0180Scrn00", "btn5");
        xxbtn5.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn5);

        EZDGUIAttribute xxbtn6 = new EZDGUIAttribute("NLCL0180Scrn00", "btn6");
        xxbtn6.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn6);

        EZDGUIAttribute xxbtn7 = new EZDGUIAttribute("NLCL0180Scrn00", "btn7");
        xxbtn7.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn7);

        EZDGUIAttribute xxbtn8 = new EZDGUIAttribute("NLCL0180Scrn00", "btn8");
        xxbtn8.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn8);

        EZDGUIAttribute xxbtn9 = new EZDGUIAttribute("NLCL0180Scrn00", "btn9");
        xxbtn9.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn9);

        EZDGUIAttribute xxbtn10 = new EZDGUIAttribute("NLCL0180Scrn00", "btn10");
        xxbtn10.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn10);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180_NMAL6050(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180_NMAL6050(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.vndCd);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    // 2013/05/22 R-OM025 Inventory Transaction Add Start
    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180_NPAL1010(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180_NPAL1010(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.whCd_P1);

        scrnMsg.locNm_P1.setInputProtected(true);
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }
    // 2013/05/22 R-OM025 Inventory Transaction Add End

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void checkInput_NLCL0180Scrn00_Add_Dtail_Line(NLCL0180BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H1);
        scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H2);
    }

    /**
     * @return bizMsg NLCL0180CMsg
     */
    public static NLCL0180CMsg setRequestData_NLCL0180Scrn00_Add_Dtail_Line() {

        NLCL0180CMsg bizMsg;

        bizMsg = new NLCL0180CMsg();
        bizMsg.setBusinessID("NLCL0180");
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void setScrnItemValue_NLCL0180Scrn00_Add_Dtail_Line(NLCL0180BMsg scrnMsg) {

        scrnMsg.mdseCd.clear();
        scrnMsg.mdseNm.clear();
        scrnMsg.xxRqstQty_H1.clear();
        scrnMsg.xxRqstQty_H2.clear();
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_Add_Dtail_Line(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_Add_Dtail_Line(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.mdseCd);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void checkInput_NLCL0180Scrn00_CMN_Apply(NLCL0180BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.whCd_P1);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.arvDt);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
    }

    /**
     * @return bizMsg NLCL0180CMsg
     */
    public static NLCL0180CMsg setRequestData_NLCL0180Scrn00_CMN_Apply() {

        NLCL0180CMsg bizMsg;

        bizMsg = new NLCL0180CMsg();
        bizMsg.setBusinessID("NLCL0180");
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_CMN_Apply(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_CMN_Apply(NLCL0180BMsg scrnMsg) {

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.whCd_P1);
        scrnMsg.setMessageInfo("NLCM0008I", null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void setScrnItemValue_NLCL0180Scrn00_CMN_Clear(NLCL0180BMsg scrnMsg) {

        scrnMsg.mdseCd.clear();
        scrnMsg.mdseNm.clear();
        scrnMsg.xxRqstQty_H1.clear();
        scrnMsg.xxRqstQty_H2.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_CMN_Clear(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_CMN_Clear(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.mdseCd);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void setScrnItemValue_NLCL0180Scrn00_CMN_Reset(NLCL0180BMsg scrnMsg) {

        scrnMsg.whCd_P1.clear();
        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.clear();
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.apVndInvNum.clear();
        scrnMsg.vndCd.clear();
        scrnMsg.vndNm.clear();
        scrnMsg.mdseCd.clear();
        scrnMsg.mdseNm.clear();
        scrnMsg.xxRqstQty_H1.clear();
        scrnMsg.xxRqstQty_H2.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_CMN_Reset(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_CMN_Reset(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.whCd_P1);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void checkInput_NLCL0180Scrn00_CMN_Submit(NLCL0180BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.whCd_P1);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.arvDt);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
    }

    /**
     * @return bizMsg NLCL0180CMsg
     */
    public static NLCL0180CMsg setRequestData_NLCL0180Scrn00_CMN_Submit() {

        NLCL0180CMsg bizMsg;

        bizMsg = new NLCL0180CMsg();
        bizMsg.setBusinessID("NLCL0180");
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_CMN_Submit(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "CMN_Apply", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void busBtnControl_NLCL0180Scrn00_CMN_Submit(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled("Display_VendorName", false);
        scrnAppli.setButtonEnabled("Display_MDSEName", false);
        scrnAppli.setButtonEnabled("Add_Dtail_Line", false);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_CMN_Submit(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.whCd_P1);

        scrnMsg.whCd_P1.setInputProtected(true);
        scrnMsg.apVndInvNum.setInputProtected(true);
        scrnMsg.arvDt.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.whCd_LK.setInputProtected(true);
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.vndCd_LK.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseCd_LK.setInputProtected(true);
        scrnMsg.mdseCd.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
        scrnMsg.xxRqstQty_H1.setInputProtected(true);
        scrnMsg.xxRqstQty_H2.setInputProtected(true);

        scrnMsg.setMessageInfo("ZZM8100I", null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void checkInput_NLCL0180Scrn00_Display_MDSEName(NLCL0180BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
    }

    /**
     * @return bizMsg NLCL0180CMsg
     */
    public static NLCL0180CMsg setRequestData_NLCL0180Scrn00_Display_MDSEName() {

        NLCL0180CMsg bizMsg;

        bizMsg = new NLCL0180CMsg();
        bizMsg.setBusinessID("NLCL0180");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_Display_MDSEName(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_Display_MDSEName(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.mdseCd);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void checkInput_NLCL0180Scrn00_Display_VendorName(NLCL0180BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.vndCd);
    }

    /**
     * @return bizMsg NLCL0180CMsg
     */
    public static NLCL0180CMsg setRequestData_NLCL0180Scrn00_Display_VendorName() {

        NLCL0180CMsg bizMsg;

        bizMsg = new NLCL0180CMsg();
        bizMsg.setBusinessID("NLCL0180");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_Display_VendorName(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_Display_VendorName(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.vndCd);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_Open_NMAL6050(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);

    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_Open_NMAL6050(NLCL0180BMsg scrnMsg) {

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);

    }

    // 2013/05/22 R-OM025 Inventory Transaction Add Start
    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_Open_NPAL1010(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);

    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_Open_NPAL1010(NLCL0180BMsg scrnMsg) {

        scrnMsg.locNm_P1.setInputProtected(true);
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);

    }
    // 2013/05/22 R-OM025 Inventory Transaction Add End

    /**
     * @param scrnMsg NLCL0180BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NLCL0180Scrn00_Open_NMAL6050(NLCL0180BMsg scrnMsg) {

        Object[] params = new Object[12];
        params[0] = scrnMsg.xxTblNm_VE;
        params[1] = scrnMsg.xxTblCdColNm_VE;
        params[2] = scrnMsg.xxTblNmColNm_VE;
        params[3] = scrnMsg.xxTblSortColNm_VE;
        params[4] = scrnMsg.xxScrNm_VE;
        params[5] = scrnMsg.xxHdrCdLbNm_VE;
        params[6] = scrnMsg.xxHdrNmLbNm_VE;
        params[7] = scrnMsg.xxDtlCdLbNm_VE;
        params[8] = scrnMsg.xxDtlNmLbNm_VE;
        params[9] = scrnMsg.vndCd;
        params[10] = scrnMsg.vndNm_OT;

        return params;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180Scrn00_OpenWin_NMAL6030(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);

    }

    /**
     * @param scrnMsg NLCL0180BMsg
     * @return Object[]
     */
    public static Object[] otherBusConnectTo_NLCL0180Scrn00_OpenWin_NMAL6030(NLCL0180BMsg scrnMsg) {

        Object[] params = new Object[2];
        params[0] = scrnMsg.mdseCd;
        params[1] = scrnMsg.mdseNm_OT;

        return params;
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180Scrn00_OpenWin_NMAL6030(NLCL0180BMsg scrnMsg) {

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);

    }

    /**
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL0180_NMAL6030(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void scrnItemControl_NLCL0180_NMAL6030(NLCL0180BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.mdseCd);

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_P1.setInputProtected(true);
        // 2013/05/22 R-OM025 Inventory Transaction Add End
        scrnMsg.arvDt.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    public static void checkInputSubmit(NLCL0180BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxRqstQty_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxRqstQty_A2);
            } else {
            }
        }

        scrnMsg.putErrorScreen();

        if (scrnMsg.vndCd.isError()) {
            scrnMsg.vndNm.clear();
        } else {
        }

        execDelDetai(scrnMsg);

        NLCL0180CommonLogic.checkInput_NLCL0180Scrn00_CMN_Submit(scrnMsg);

        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.mdseCd.clearErrorInfo();
            scrnMsg.mdseCd.setErrorInfo(1, "NLCM0010E");
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.putErrorScreen();
        } else {
        }

        EZDMessageInfo errMsg = scrnMsg.getErrorInfo("mdseCd");

        if ((errMsg != null && errMsg.getErrorKbn() != 0) && !"ZZM9000E".equals(errMsg.getCode())) {

            scrnMsg.mdseCd.clearErrorInfo();
            scrnMsg.mdseCd.setErrorInfo(1, "NLCM0015E");
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.putErrorScreen();

        } else if (!scrnMsg.mdseCd.isClear()) {

            scrnMsg.mdseCd.clearErrorInfo();
            scrnMsg.mdseCd.setErrorInfo(1, "NLCM0015E");
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.putErrorScreen();

        } else {
        }

        errMsg = scrnMsg.getErrorInfo("xxRqstQty_H1");

        if ((errMsg != null && errMsg.getErrorKbn() != 0) && !"ZZM9000E".equals(errMsg.getCode())) {

            scrnMsg.xxRqstQty_H1.clearErrorInfo();
            scrnMsg.xxRqstQty_H1.setErrorInfo(1, "NLCM0015E");
            scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H1);
            scrnMsg.putErrorScreen();

        } else if (!scrnMsg.xxRqstQty_H1.isClear()) {

            scrnMsg.xxRqstQty_H1.clearErrorInfo();
            scrnMsg.xxRqstQty_H1.setErrorInfo(1, "NLCM0015E");
            scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H1);
            scrnMsg.putErrorScreen();

        } else {
        }

        errMsg = scrnMsg.getErrorInfo("xxRqstQty_H2");

        if ((errMsg != null && errMsg.getErrorKbn() != 0) && !"ZZM9000E".equals(errMsg.getCode())) {

            scrnMsg.xxRqstQty_H2.clearErrorInfo();
            scrnMsg.xxRqstQty_H2.setErrorInfo(1, "NLCM0015E");
            scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H2);
            scrnMsg.putErrorScreen();

        } else if (!scrnMsg.xxRqstQty_H2.isClear()) {

            scrnMsg.xxRqstQty_H2.clearErrorInfo();
            scrnMsg.xxRqstQty_H2.setErrorInfo(1, "NLCM0015E");
            scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H2);
            scrnMsg.putErrorScreen();

        } else {
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (new BigDecimal(0).compareTo(scrnMsg.A.no(i).xxRqstQty_A1.getValue()) == 0 && new BigDecimal(0).compareTo(scrnMsg.A.no(i).xxRqstQty_A2.getValue()) == 0) {
                scrnMsg.A.no(i).xxRqstQty_A1.setErrorInfo(1, "NLCM0060E");
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxRqstQty_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxRqstQty_A2);
                scrnMsg.putErrorScreen();
            } else {
            }
        }

        scrnMsg.mdseNm.clear();
    }

    /**
     * @param scrnMsg NLCL0180BMsg
     */
    private static void execDelDetai(NLCL0180BMsg scrnMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
        } else {

            ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);

        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).itemLineNum_A1.setValue(Integer.toString(i + 1));
        }

        S21TableColorController tblColor = new S21TableColorController("NLCL0180Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end
}
