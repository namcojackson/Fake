/**
 * <pre>Copyright(c)2010 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1020.common;

import static business.servlet.NLCL1020.constant.NLCL1020Constant.BTN_ADD_LINE;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.BTN_DELETE_LINE;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_0;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_1;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_10;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_11;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_2;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_3;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_4;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_5;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_6;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_7;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_8;
import static business.servlet.NLCL1020.constant.NLCL1020Constant.IDX_9;

import java.util.List;

import parts.common.EZDBDateItem;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMessageInfo;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NLCL1020.NLCL1020CMsg;
import business.servlet.NLCL1020.NLCL1020BMsg;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 11/19/2013   Fujitsu         N.Sugiua        Update          2852(WDS)
 * 03/02/2016   Hitachi         T.Tomita        Update          QC#3586
 * 03/11/2019   Fujitsu         T.Ogura         Update          QC#30705
 * 03/26/2019   Fujitsu         T.Ogura         Update          QC#30124
 *</pre>
 */
public class NLCL1020CommonLogic {

    /**
     * trans Msg Check
     * @param scrnMsg NLCL1020BMsg
     */
    public static void transMsgCheck(NLCL1020BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.invtyOrdNum);
        // 10/19/2015 mod start
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_FR);
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_TO);
        scrnMsg.addCheckItem(scrnMsg.fromRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.fromRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlSwhCd);
        // 10/19/2015 mod end
        scrnMsg.addCheckItem(scrnMsg.locStsCd_P1);
        scrnMsg.addCheckItem(scrnMsg.firstInvtyOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.scdInvtyOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.thirdInvtyOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.xxMdseCdAncr);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_HD);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_PH);
        scrnMsg.addCheckItem(scrnMsg.invtyAvalQty_HI);
        scrnMsg.addCheckItem(scrnMsg.invtyAvalQty_HO);
        scrnMsg.A.setCheckParam(new String[] {"xxChkBox_AD", "invtyOrdLineNum_A1", "mdseCd_A1", "mdseNm_A1", "stkStsCd_AP", "invtyAvalQty_AI", "invtyAvalQty_AO" }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.addCheckItem(scrnMsg.xxSrchFuncCd_OT);
        scrnMsg.addCheckItem(scrnMsg.rgtnStsCd_OT);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_TP);
        scrnMsg.addCheckItem(scrnMsg.xxErrFlg_RG);

        resetSubmitDoubleCheck(scrnMsg);
    }

    /**
     * initialize
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL1020BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        int readyOnly = 1;
        if (isReadOnly(scrnMsg)) {
            readyOnly = 0;
        }

        scrnAppli.setButtonProperties(NLCL1020Constant.SEARCH_BTN, NLCL1020Constant.SEARCH_BTN, "Search", 1, null);
        scrnAppli.setButtonProperties("Display_MDSEName", "Display_MDSEName", ">>", readyOnly, null);
        scrnAppli.setButtonProperties("Display_AvalQty", "Display_AvalQty", ">>", readyOnly, null);
        // START 2019/03/11 T.Ogura [QC#30705,MOD]
//        scrnAppli.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
        scrnAppli.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], readyOnly, null);
        if (readyOnly == 1) {
            scrnAppli.setButtonEnabled(BTN_ADD_LINE[1], true);
        } else {
            scrnAppli.setButtonEnabled(BTN_ADD_LINE[1], false);
        }
        // END   2019/03/11 T.Ogura [QC#30705,MOD]
        scrnAppli.setButtonProperties(BTN_DELETE_LINE, BTN_DELETE_LINE, "Delete", readyOnly, null);    // 2019/03/26 T.Ogura [QC#30124,ADD]
        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", readyOnly, null);
        scrnAppli.setButtonProperties("btn3", "CMN_Apply", "Apply", readyOnly, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7", "", "Detele", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        resetSubmitDoubleCheck(scrnMsg);

        clearVisibility(scrnMsg);

        initSecurityCheck(scrnAppli, scrnMsg);
    }

    /**
     * Init Security Check
     * @param scrnAppli EZDCommonHandler
     */
    private static void initSecurityCheck(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NLCL1020Constant.BUSINESS_ID);

        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate DC Transfer(Loss)Entry(NLCL1020). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        if (!funcList.contains(NLCL1020Constant.FUNC_NLCL1020T010) && !funcList.contains(NLCL1020Constant.FUNC_NLCL1020T020)) {
            throw new S21AbendException("You can't operate DC Transfer(Loss)Entry(NLCL1020). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
    }

    /**
     * Clear Visibility
     * @param scrnMsg NLCL1020BMsg
     */
    private static void clearVisibility(NLCL1020BMsg scrnMsg) {

        EZDGUIAttribute xxbtn1 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn1");
        xxbtn1.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn1);

        EZDGUIAttribute xxbtn2 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn2");
        xxbtn2.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn2);

        EZDGUIAttribute xxbtn3 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn3");
        xxbtn3.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn3);

        EZDGUIAttribute xxbtn4 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn4");
        xxbtn4.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn4);

        EZDGUIAttribute xxbtn5 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn5");
        xxbtn5.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn5);

        EZDGUIAttribute xxbtn6 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn6");
        xxbtn6.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn6);

        EZDGUIAttribute xxbtn7 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn7");
        xxbtn7.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn7);

        EZDGUIAttribute xxbtn8 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn8");
        xxbtn8.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn8);

        EZDGUIAttribute xxbtn9 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn9");
        xxbtn9.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn9);

        EZDGUIAttribute xxbtn10 = new EZDGUIAttribute(NLCL1020Constant.SCREEN_ID, "btn10");
        xxbtn10.setVisibility(true);
        scrnMsg.addGUIAttribute(xxbtn10);
    }

    // START 2016/03/02 T.Tomita [QC#3586, MOD]
    /**
     * screen Item Control NLCL1020_NMAL6800
     * @param scrnMsg NLCL1020BMsg
     */
    public static void scrnItemControl_NLCL1020_NMAL6800(NLCL1020BMsg scrnMsg) {

        scrnMsg.mdseDescShortTxt_TP.clear();
        scrnMsg.setFocusItem(scrnMsg.mdseCd_HD);

        scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
        scrnMsg.invtyAvalQty_HO.setInputProtected(true);

        if (scrnMsg.A.getValidCount() > 0) {
            // 10/19/2015 mod start
            // scrnMsg.invtyLocCd_FR.setInputProtected(true);
            // scrnMsg.invtyLocCd_TO.setInputProtected(true);
            scrnMsg.fromRtlWhCd.setInputProtected(true);
            scrnMsg.fromRtlSwhCd.setInputProtected(true);
            scrnMsg.toRtlWhCd.setInputProtected(true);
            scrnMsg.toRtlSwhCd.setInputProtected(true);
            // 10/19/2015 mod end
        }
    }
    // END 2016/03/02 T.Tomita [QC#3586, MOD]

    /**
     * Check Input NLCL1020Scrn00_Add_Dtaill_Line
     * @param scrnMsg NLCL1020BMsg
     */
    public static void checkInput_NLCL1020Scrn00_Add_Dtaill_Line(NLCL1020BMsg scrnMsg) {

        // 10/19/2015 mod start
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_FR);
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_TO);
        scrnMsg.addCheckItem(scrnMsg.fromRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.fromRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlSwhCd);
        // 10/19/2015 mod end
        scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_PH);
        scrnMsg.addCheckItem(scrnMsg.invtyAvalQty_HI);

        resetSubmitDoubleCheck(scrnMsg);
    }

    /**
     * Check Input NLCL1020Scrn00_CMN_Apply
     * @param scrnMsg NLCL1020BMsg
     */
    public static void checkInput_NLCL1020Scrn00_CMN_Apply(NLCL1020BMsg scrnMsg) {

        // 10/19/2015 mod start
        // scrnMsg.invtyLocCd_FR.clearErrorInfo();
        // scrnMsg.invtyLocCd_TO.clearErrorInfo();
        scrnMsg.fromRtlWhCd.clearErrorInfo();
        scrnMsg.fromRtlSwhCd.clearErrorInfo();
        scrnMsg.toRtlWhCd.clearErrorInfo();
        scrnMsg.toRtlSwhCd.clearErrorInfo();
        // 10/19/2015 mod end

        // 10/19/2015 mod start
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_FR);
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_TO);
        scrnMsg.addCheckItem(scrnMsg.fromRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.fromRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlSwhCd);
        // 10/19/2015 mod end

        resetSubmitDoubleCheck(scrnMsg);
    }

    /**
     * Screen Item Control NLCL1020Scrn00_CMN_Apply
     * @param scrnMsg NLCL1020BMsg
     */
    public static void scrnItemControl_NLCL1020Scrn00_CMN_Apply(NLCL1020BMsg scrnMsg) {

        // 10/19/2015 mod start
        // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_TO);
        scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlSwhCd);
        // 10/19/2015 mod end

        // 10/19/2015 mod start
        // scrnMsg.invtyLocNm_FR.setInputProtected(true);
        // scrnMsg.invtyLocNm_TO.setInputProtected(true);
        scrnMsg.locNm_FR.setInputProtected(true);
        scrnMsg.locNm_TO.setInputProtected(true);
        // 10/19/2015 mod end
        scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
        scrnMsg.invtyAvalQty_HO.setInputProtected(true);

        if (scrnMsg.A.getValidCount() > 0) {
            // 10/19/2015 mod start
            // scrnMsg.invtyLocCd_FR.setInputProtected(true);
            // scrnMsg.invtyLocCd_TO.setInputProtected(true);
            scrnMsg.fromRtlWhCd.setInputProtected(true);
            scrnMsg.fromRtlSwhCd.setInputProtected(true);
            scrnMsg.toRtlWhCd.setInputProtected(true);
            scrnMsg.toRtlSwhCd.setInputProtected(true);
            // 10/19/2015 mod end
        }
        setAlternateRowsBG(scrnMsg);
        scrnMsg.setMessageInfo(NLCL1020Constant.NLCM0008I);
    }

    /**
     * Screen Item Control NLCL1020 INIT
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL1020BMsg
     */
    public static void scrnItemControl_NLCL1020_INIT(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        if (isReadOnly(scrnMsg)) {
            scrnMsg.mdseCd_HD.setInputProtected(true);
            scrnMsg.stkStsCd_PH.setInputProtected(true);
            scrnMsg.invtyAvalQty_HI.setInputProtected(true);
        }
        // 10/19/2015 mod start
        // scrnMsg.invtyLocNm_FR.setInputProtected(true);
        // scrnMsg.invtyLocNm_TO.setInputProtected(true);
        scrnMsg.locNm_FR.setInputProtected(true);
        scrnMsg.locNm_TO.setInputProtected(true);
        // 10/19/2015 mod end
        scrnMsg.locStsCd_P1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
        scrnMsg.invtyAvalQty_HO.setInputProtected(true);

        for (int i = 0; i < scrnMsg.stkStsCd_HH.length(); i++) {
            if (STK_STS.GOOD.equals(scrnMsg.stkStsCd_HH.no(i).getValue())) {
                scrnMsg.stkStsCd_PH.setValue(STK_STS.GOOD);
                break;
            }
        }
//        setDownLoadBtn(scrnAppli, scrnMsg);
    }

    /**
     * set Screen ItemValue NLCL1020 INIT
     * @param scrnMsg NLCL1020BMsg
     */
    public static void setScrnItemValue_NLCL1020_INIT(NLCL1020BMsg scrnMsg) {
        // MOD START 11/19/2013 Defect#2852 
        // scrnMsg.xxMdseCdAncr.setValue(NLCL1020Constant.MDSE_CD);
        // Convert
        EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseCdAncr, converter.convLabel2i18nLabel("", NLCL1020Constant.MDSE_CD));
        // MOD END 11/19/2013 Defect#2852

        by_Init_Add_Clear_Reset(scrnMsg, false);
        by_Init_Reset(scrnMsg);
        clearList(scrnMsg);

        // 10/19/2015 mod start
        // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
        scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
        // 10/19/2015 mod end
    }

    /**
     * set Screen Item Value DLCL102 CMN_Clear
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL1020BMsg
     */
    public static void setScrnItemValue_NLCL1020Scrn00_CMN_Clear(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        // START 2019/03/11 T.Ogura [QC#30705,ADD]
        by_Init_Reset(scrnMsg);
        // END   2019/03/11 T.Ogura [QC#30705,ADD]
        by_Clear_Reset(scrnAppli, scrnMsg);
        clearList(scrnMsg);

        // START 2019/03/11 T.Ogura [QC#30705,MOD]
//        scrnMsg.setFocusItem(scrnMsg.mdseCd_HD);
        scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
        // END   2019/03/11 T.Ogura [QC#30705,MOD]
    }

    /**
     * set Screen Item Value NLCL1020 CMN_Reset
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL1020BMsg
     */
    public static void setScrnItemValue_NLCL1020Scrn00_CMN_Reset(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        by_Init_Reset(scrnMsg);
        by_Clear_Reset(scrnAppli, scrnMsg);
        clearList(scrnMsg);

        // 10/19/2015 mod start
        // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
        scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
        // 10/19/2015 mod end
    }

    /**
     * Set Screen Item Value NLCL1020Scrn00_Add_Dtaill_Line
     * @param scrnMsg NLCL1020BMsg
     */
    public static void setScrnItemValue_NLCL1020Scrn00_Add_Dtaill_Line(NLCL1020BMsg scrnMsg) {

        by_Init_Add_Clear_Reset(scrnMsg, true);
        setAlternateRowsBG(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.mdseCd_HD);
    }

    // Called By:
    // 1. Init
    // 2. Reset
    /**
     * by Init Reset
     * @param scrnMsg NLCL1020BMsg
     */
    private static void by_Init_Reset(NLCL1020BMsg scrnMsg) {

        // 10/19/2015 mod start
        // scrnMsg.invtyLocCd_FR.clear();
        // scrnMsg.invtyLocNm_FR.clear();
        scrnMsg.fromRtlWhCd.clear();
        scrnMsg.fromRtlSwhCd.clear();
        scrnMsg.locNm_FR.clear();
        // 10/19/2015 mod end
        scrnMsg.locRoleTpCd_FR.clear();
        // 10/19/2015 mod start
        // scrnMsg.invtyLocCd_TO.clear();
        // scrnMsg.invtyLocNm_TO.clear();
        scrnMsg.toRtlWhCd.clear();
        scrnMsg.toRtlSwhCd.clear();
        scrnMsg.locNm_TO.clear();
        // 10/19/2015 mod end
        scrnMsg.locRoleTpCd_TO.clear();
        scrnMsg.firstInvtyOrdCmntTxt.clear();
        scrnMsg.scdInvtyOrdCmntTxt.clear();
        scrnMsg.thirdInvtyOrdCmntTxt.clear();
        scrnMsg.mdseDescShortTxt_TP.clear();
        scrnMsg.xxErrFlg_RG.clear();
    }

    // Called By:
    // 1. Clear
    // 2. Reset
    /**
     * by Clear Reset
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NLCL1020BMsg
     */
    private static void by_Clear_Reset(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        scrnMsg.locStsCd_P1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
        scrnMsg.invtyAvalQty_HO.setInputProtected(true);

        by_Init_Add_Clear_Reset(scrnMsg, false);
        // Enable Search
        enableSearch(scrnAppli, scrnMsg, true);

//        setDownLoadBtn(scrnAppli, scrnMsg);
    }

    // Called By:
    // 1. Init
    // 2. Add
    // 3. Delete
    // 4. Clear
    // 5. Reset
    /**
     * by Init Add/Delete/Clear/Reset
     * @param scrnMsg NLCL1020BMsg
     * @param toAdd   boolean
     */
    private static void by_Init_Add_Clear_Reset(NLCL1020BMsg scrnMsg, boolean toAdd) {

        scrnMsg.mdseCd_HD.clear();
        scrnMsg.mdseDescShortTxt_HD.clear();
        scrnMsg.stkStsCd_PH.clear();
        scrnMsg.invtyAvalQty_HI.clear();
        scrnMsg.invtyAvalQty_HO.clear();
        scrnMsg.invtyOrdNum.clear();

        // 10/19/2015 mod start
        // scrnMsg.invtyLocNm_FR.setInputProtected(true);
        // scrnMsg.invtyLocNm_TO.setInputProtected(true);
        scrnMsg.locNm_FR.setInputProtected(true);
        scrnMsg.locNm_TO.setInputProtected(true);
        // 10/19/2015 mod end

        if (isReadOnly(scrnMsg)) {
            scrnMsg.mdseCd_HD.setInputProtected(true);
            scrnMsg.stkStsCd_PH.setInputProtected(true);
            scrnMsg.invtyAvalQty_HI.setInputProtected(true);
        }

        if (toAdd) {
            // Called By: Add
            scrnMsg.xxLinkAncr_FR.setInputProtected(true);
            // 10/19/2015 mod start
            // scrnMsg.invtyLocCd_FR.setInputProtected(true);
            scrnMsg.fromRtlWhCd.setInputProtected(true);
            scrnMsg.fromRtlSwhCd.setInputProtected(true);
            // 10/19/2015 mod end
            scrnMsg.xxLinkAncr_TO.setInputProtected(true);
            // 10/19/2015 mod start
            // scrnMsg.invtyLocCd_TO.setInputProtected(true);
            scrnMsg.toRtlWhCd.setInputProtected(true);
            scrnMsg.toRtlSwhCd.setInputProtected(true);
            // 10/19/2015 mod end
            scrnMsg.locStsCd_P1.setInputProtected(true);
            scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
            scrnMsg.invtyAvalQty_HO.setInputProtected(true);
        }

        // START 2019/03/26 T.Ogura [QC#30124,ADD]
        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.xxLinkAncr_FR.setInputProtected(false);
            scrnMsg.fromRtlWhCd.setInputProtected(false);
            scrnMsg.fromRtlSwhCd.setInputProtected(false);
            scrnMsg.xxLinkAncr_TO.setInputProtected(false);
            scrnMsg.toRtlWhCd.setInputProtected(false);
            scrnMsg.toRtlSwhCd.setInputProtected(false);
        }
        // END   2019/03/26 T.Ogura [QC#30124,ADD]

        for (int i = 0; i < scrnMsg.stkStsCd_HH.length(); i++) {
            if (STK_STS.GOOD.equals(scrnMsg.stkStsCd_HH.no(i).getValue())) {
                scrnMsg.stkStsCd_PH.setValue(STK_STS.GOOD);
                break;
            }
        }
    }

    // Called By:
    // 1. Init
    // 2. Clear
    // 3. Reset
    /**
     * Clear List
     * @param scrnMsg NLCL1020BMsg
     */
    private static void clearList(NLCL1020BMsg scrnMsg) {
        scrnMsg.invtyOrdNum.clear();
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * Common Btn Control NLCL1020_INIT
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLCL1020_INIT(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Detele", 0, null);

        scrnAppli.setButtonConfirmMsg("CMN_Submit", "ZZM8102I", new String[] {"Submit" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Apply", "ZZM8102I", new String[] {"Apply" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Download", "ZZM8102I", new String[] {"Download" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Clear", "ZZM8102I", new String[] {"Clear" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Reset", "ZZM8102I", new String[] {"Reset" }, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Return", "ZZM8102I", new String[] {"Return" }, 0);
    }

    /**
     * screen Item Control For Submit/Search/Init
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL1020BMsg
     */
    public static void scrnItemControl_For_Submit_Or_Search(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg) {

        // Submit
        scrnAppli.setButtonEnabled("btn2", false);
        // Apply
        scrnAppli.setButtonEnabled("btn3", false);

        scrnAppli.setButtonEnabled("Display_TOCName", false);
//        scrnAppli.setButtonEnabled("Search_Display_Trx", false);    // 2019/03/11 T.Ogura [QC#30705,DEL]
        scrnAppli.setButtonEnabled("Display_MDSEName", false);
        scrnAppli.setButtonEnabled("Display_AvalQty", false);
        // START 2019/03/11 T.Ogura [QC#30705,MOD]
//        scrnAppli.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
        scrnAppli.setButtonEnabled(BTN_ADD_LINE[1], false);
        // END   2019/03/11 T.Ogura [QC#30705,MOD]
        scrnAppli.setButtonEnabled(BTN_DELETE_LINE, false);    // 2019/03/26 T.Ogura [QC#30124,ADD]
        scrnMsg.xxLinkAncr_FR.setInputProtected(true);
        scrnMsg.xxLinkAncr_TO.setInputProtected(true);
        scrnMsg.locStsCd_P1.setInputProtected(true);
        scrnMsg.invtyOrdNum.setInputProtected(true);
        // 10/19/2015 mod start
        // scrnMsg.invtyLocCd_FR.setInputProtected(true);
        // scrnMsg.invtyLocNm_FR.setInputProtected(true);
        // scrnMsg.invtyLocCd_TO.setInputProtected(true);
        // scrnMsg.invtyLocNm_TO.setInputProtected(true);
        scrnMsg.fromRtlWhCd.setInputProtected(true);
        scrnMsg.fromRtlSwhCd.setInputProtected(true);
        scrnMsg.locNm_FR.setInputProtected(true);
        scrnMsg.toRtlWhCd.setInputProtected(true);
        scrnMsg.toRtlSwhCd.setInputProtected(true);
        scrnMsg.locNm_TO.setInputProtected(true);
        // 10/19/2015 mod end
        scrnMsg.firstInvtyOrdCmntTxt.setInputProtected(true);
        scrnMsg.scdInvtyOrdCmntTxt.setInputProtected(true);
        scrnMsg.thirdInvtyOrdCmntTxt.setInputProtected(true);
        scrnMsg.xxMdseCdAncr.setInputProtected(true);
        scrnMsg.mdseCd_HD.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
        scrnMsg.stkStsCd_PH.setInputProtected(true);
        scrnMsg.invtyAvalQty_HI.setInputProtected(true);
        scrnMsg.invtyAvalQty_HO.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).xxChkBox_AD.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).stkStsCd_AP.setInputProtected(true);
            scrnMsg.A.no(i).invtyAvalQty_AI.setInputProtected(true);
        }
        setAlternateRowsBG(scrnMsg);

        scrnMsg.setMessageInfo(NLCL1020Constant.ZZM8100I);
        // Disable Search
        enableSearch(scrnAppli, scrnMsg, false);
//        setDownLoadBtn(scrnAppli, scrnMsg);

        // 10/19/2015 mod start
        // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
        scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
        // 10/19/2015 mod end
    }

    /**
     * @param scrnMsg NLCL1020BMsg
     */
    public static void scrnItemControl_NLCL1020Scrn00_Display_MDSEName(NLCL1020BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.mdseCd_HD);

        scrnMsg.mdseDescShortTxt_HD.setInputProtected(true);
        scrnMsg.invtyAvalQty_HO.setInputProtected(true);

        if (scrnMsg.A.getValidCount() > 0) {
            // 10/19/2015 mod start
            // scrnMsg.invtyLocCd_FR.setInputProtected(true);
            // scrnMsg.invtyLocCd_TO.setInputProtected(true);
            scrnMsg.fromRtlWhCd.setInputProtected(true);
            scrnMsg.fromRtlSwhCd.setInputProtected(true);
            scrnMsg.toRtlWhCd.setInputProtected(true);
            scrnMsg.toRtlSwhCd.setInputProtected(true);
            // 10/19/2015 mod end
        }
    }

    // START 2016/03/02 T.Tomita [QC#3586, MOD]
    /**
     * @param scrnMsg NLCL1020BMsg
     * @return Object[]
     */
    public static Object[] otherBizConnectTo_NLCL1020Scrn00_OpenWin_NMAL6800(NLCL1020BMsg scrnMsg) {

        clearPopPrm(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.mdseCd_HD);
        Object[] params = new Object[IDX_7];
        params[IDX_0] = scrnMsg.xxPopPrm_00;
        params[IDX_1] = scrnMsg.xxPopPrm_01;
        params[IDX_2] = scrnMsg.xxPopPrm_02;
        params[IDX_3] = scrnMsg.xxPopPrm_03;
        params[IDX_4] = scrnMsg.xxPopPrm_04;
        params[IDX_5] = scrnMsg.xxPopPrm_05;
        params[IDX_6] = scrnMsg.xxPopPrm_06;

        return params;
    }

    private static void clearPopPrm(NLCL1020BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
    }
    // END 2016/03/02 T.Tomita [QC#3586, MOD]

    /**
     * @param scrnMsg NLCL1020BMsg
     * @return Object[]
     */
    public static Object[] otherBizConnectTo_NLCL1020Scrn00_OpenWin_NPAL1010_LocFrom(NLCL1020BMsg scrnMsg) {

        scrnMsg.invtyLocCd_FR.clear(); // 10/20/2015 add
        scrnMsg.invtyLocNm_FR.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocNm_TP, scrnMsg.locNm_FR.getValue()); // 10/20/2015 add
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg_FR, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_FR, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(scrnMsg.locNm_FR)) {
            scrnMsg.rtlWhNm_FR.clear(); // 10/20/2015 add
            scrnMsg.rtlSwhNm_FR.clear(); // 10/20/2015 add
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_FR, ZYPConstant.FLG_OFF_N); // 10/20/2015 add

        Object[] params = new Object[IDX_11];
        // 10/19/2015 mod start
        /*
        params[0] = scrnMsg.invtyLocCd_FR;
        params[1] = scrnMsg.invtyLocNm_FR;
        */
        params[IDX_0] = scrnMsg.invtyLocCd_FR;
        params[IDX_1] = scrnMsg.invtyLocNm_FR;
        // 10/19/2015 mod end
        params[IDX_2] = scrnMsg.xxLocRoleTpCdListTxt_FR;
        params[IDX_3] = scrnMsg.xxChkInpValFlg_FR;
        params[IDX_4] = scrnMsg.xxSelFlg_FR;
        params[IDX_5] = scrnMsg.locRoleTpCd_FR;
        params[IDX_6] = scrnMsg.fromRtlWhCd;  // 10/20/2015 add 
        params[IDX_7] = scrnMsg.rtlWhNm_FR;   // 10/20/2015 add 
        params[IDX_8] = scrnMsg.fromRtlSwhCd; // 10/20/2015 add 
        params[IDX_9] = scrnMsg.rtlSwhNm_FR;  // 10/20/2015 add 
        params[IDX_10] = scrnMsg.xxChkBox_FR; // 10/20/2015 add 

        return params;
    }

    /**
     * @param scrnMsg NLCL1020BMsg
     * @return Object[]
     */
    public static Object[] otherBizConnectTo_NLCL1020Scrn00_OpenWin_NPAL1010_LocTo(NLCL1020BMsg scrnMsg) {

        scrnMsg.invtyLocCd_TO.clear(); // 10/20/2015 add
        scrnMsg.invtyLocNm_TO.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocNm_TP, scrnMsg.locNm_TO.getValue()); // 10/20/2015 add
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg_TO, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_TO, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(scrnMsg.locNm_TO)) {
            scrnMsg.rtlWhNm_TO.clear(); // 10/20/2015 add
            scrnMsg.rtlSwhNm_TO.clear(); // 10/20/2015 add
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_TO, ZYPConstant.FLG_OFF_N); // 10/20/2015 add

        Object[] params = new Object[IDX_11];
        // 10/19/2015 mod start
        /*
        params[0] = scrnMsg.invtyLocCd_TO;
        params[1] = scrnMsg.invtyLocNm_TO;
         */
        params[IDX_0] = scrnMsg.invtyLocCd_TO;
        params[IDX_1] = scrnMsg.invtyLocNm_TO;
        // 10/19/2015 mod end
        params[IDX_2] = scrnMsg.xxLocRoleTpCdListTxt_TO;
        params[IDX_3] = scrnMsg.xxChkInpValFlg_TO;
        params[IDX_4] = scrnMsg.xxSelFlg_TO;
        params[IDX_5] = scrnMsg.locRoleTpCd_TO;
        params[IDX_6] = scrnMsg.toRtlWhCd;    // 10/20/2015 add 
        params[IDX_7] = scrnMsg.rtlWhNm_TO;   // 10/20/2015 add 
        params[IDX_8] = scrnMsg.toRtlSwhCd;   // 10/20/2015 add 
        params[IDX_9] = scrnMsg.rtlSwhNm_TO;  // 10/20/2015 add 
        params[IDX_10] = scrnMsg.xxChkBox_TO; // 10/20/2015 add 

        return params;
    }

    /**
     * @param scrnMsg NLCL1020BMsg
     * @param eventName String
     */
    // START 2019/03/11 T.Ogura [QC#30705,MOD]
//    public static void checkInputCMN_Apply(NLCL1020BMsg scrnMsg) {
    public static void checkInputCMN_Apply(NLCL1020BMsg scrnMsg, String eventName) {
    // END   2019/03/11 T.Ogura [QC#30705,MOD]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.clearErrorInfo();
        }

        // [DetailA] FW Check
        // -------------------------------------------------------
        List<Integer> unSelectedRows = ZYPTableUtil.getUnSelectedRows(scrnMsg.A, "xxChkBox_AD", ZYPConstant.CHKBOX_ON_Y);
        for (int i = 0; i < unSelectedRows.size(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(unSelectedRows.get(i)).stkStsCd_AP);
            scrnMsg.addCheckItem(scrnMsg.A.no(unSelectedRows.get(i)).invtyAvalQty_AI);
        }
        scrnMsg.putErrorScreen();

        // [DetailA] delete line
        // -------------------------------------------------------
        // START 2019/03/26 T.Ogura [QC#30124,DEL]
//        if ("CMN_Submit".equals(eventName)) {    // 2019/03/11 T.Ogura [QC#30705,ADD]
//            int dtlCnt = scrnMsg.A.getValidCount();
//            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_AD", ZYPConstant.CHKBOX_ON_Y);
//
//            if (!selectedRows.isEmpty()) {
//                int result = ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);
//                scrnMsg.A.setValidCount(dtlCnt - result);
//                // Line number
//                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                    scrnMsg.A.no(i).invtyOrdLineNum_A1.setValue(String.valueOf(i + 1));
//                }
//            }
//        }                                        // 2019/03/11 T.Ogura [QC#30705,ADD]
        // END   2019/03/26 T.Ogura [QC#30124,DEL]

        // Check the number of line items.
        // -------------------------------------------------------
        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.mdseCd_HD.clearErrorInfo();
            scrnMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0010E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
            scrnMsg.putErrorScreen();
        }

        // -------------------------------------------------------

        EZDMessageInfo errMsg = scrnMsg.getErrorInfo("mdseCd_HD");

        if (errMsg != null && NLCL1020Constant.ZZM9000E.equals(errMsg.getCode())) {

            scrnMsg.mdseCd_HD.clearErrorInfo();

        } else if (errMsg != null && errMsg.getErrorKbn() != 0) {

            scrnMsg.mdseCd_HD.clearErrorInfo();
            scrnMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0015E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);

        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_HD)) {

            scrnMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0015E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
        }

        errMsg = scrnMsg.getErrorInfo("invtyAvalQty_HI");

        if (errMsg != null && NLCL1020Constant.ZZM9000E.equals(errMsg.getCode())) {

            scrnMsg.invtyAvalQty_HI.clearErrorInfo();

        } else if (errMsg != null && errMsg.getErrorKbn() != 0) {

            scrnMsg.invtyAvalQty_HI.clearErrorInfo();
            scrnMsg.invtyAvalQty_HI.setErrorInfo(1, NLCL1020Constant.NLCM0015E);
            scrnMsg.addCheckItem(scrnMsg.invtyAvalQty_HI);

        } else if (ZYPCommonFunc.hasValue(scrnMsg.invtyAvalQty_HI)) {

            scrnMsg.invtyAvalQty_HI.setErrorInfo(1, NLCL1020Constant.NLCM0015E);
            scrnMsg.addCheckItem(scrnMsg.invtyAvalQty_HI);

        }

        scrnMsg.mdseDescShortTxt_HD.clear();
        scrnMsg.invtyAvalQty_HO.clear();
        scrnMsg.invtyOrdNum.clear();

        scrnMsg.putErrorScreen();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.clearErrorInfo();
        }

        String mdseCdA1i;
        String stkStsCdAPi;
        String mdseCdA1j;
        String stkStsCdAPj;

        for (int i = 0; i < scrnMsg.A.getValidCount() - 1; i++) {
            boolean errFlg = false;
            mdseCdA1i = scrnMsg.A.no(i).mdseCd_A1.getValue();
            stkStsCdAPi = scrnMsg.A.no(i).stkStsCd_AP.getValue();

            for (int j = i + 1; j < scrnMsg.A.getValidCount(); j++) {

                mdseCdA1j = scrnMsg.A.no(j).mdseCd_A1.getValue();
                stkStsCdAPj = scrnMsg.A.no(j).stkStsCd_AP.getValue();

                if (mdseCdA1i.equals(mdseCdA1j) && stkStsCdAPi.equals(stkStsCdAPj)) {

                    if (!errFlg) {
                        scrnMsg.A.no(i).mdseDescShortTxt_A1.setErrorInfo(1, "NLCM0019E");
                        scrnMsg.A.no(i).stkStsCd_AP.setErrorInfo(1, "NLCM0019E");
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseDescShortTxt_A1);
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).stkStsCd_AP);
                        errFlg = true;
                    }
                    scrnMsg.A.no(j).mdseDescShortTxt_A1.setErrorInfo(1, "NLCM0019E");
                    scrnMsg.A.no(j).stkStsCd_AP.setErrorInfo(1, "NLCM0019E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(j).mdseDescShortTxt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(j).stkStsCd_AP);
                }
            }
            if (errFlg) {
                scrnMsg.putErrorScreen();
            }
        }
    }

    /**
     * Check date.
     * @param glblCmpyCd Global Company Code
     * @param dateItem EZDBDateItem
     * @return boolean
     */
    public static boolean checkDate(String glblCmpyCd, EZDBDateItem dateItem) {

        String date = dateItem.getValue();
        if (!ZYPCommonFunc.hasValue(date)) {
            return true;
        }

        // Check input Date.
        if (!ZYPDateUtil.isBusinessDay(glblCmpyCd, date) || ZYPDateUtil.isPastDate(date)) {
            dateItem.setErrorInfo(1, NLCL1020Constant.NLCM0041E);
            return false;
        }

        return true;
    }

    /**
     * Check get Available Quantity parameters.
     * @param scrnMsg NLCL1020BMsg
     */
    public static void checkGetAvalQtyParams(NLCL1020BMsg scrnMsg) {

        // Location From
        // 10/19/2015 mod start
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_FR);
        scrnMsg.addCheckItem(scrnMsg.fromRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.fromRtlSwhCd);
        // 10/19/2015 mod end
        // Location Status
        scrnMsg.addCheckItem(scrnMsg.locStsCd_P1);
        // Merchandise Code
        scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
        // Stock Status
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_PH);
    }

    /**
     * Set Request Data20
     * @param scrnMsg NLCL1020BMsg
     * @return bizMsg NLCL1020CMsg
     */
    public static NLCL1020CMsg setRequestData20(NLCL1020BMsg scrnMsg) {

        NLCL1020CMsg bizMsg = new NLCL1020CMsg();
        bizMsg.setBusinessID(NLCL1020Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        resetSubmitDoubleCheck(scrnMsg);
        return bizMsg;
    }

    /**
     * Set Request Data40
     * @param scrnMsg NLCL1020BMsg
     * @return bizMsg NLCL1020CMsg
     */
    public static NLCL1020CMsg setRequestData40(NLCL1020BMsg scrnMsg) {

        NLCL1020CMsg bizMsg = new NLCL1020CMsg();
        bizMsg.setBusinessID(NLCL1020Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        return bizMsg;
    }

    /**
     * Set Alternate RowsBG
     * @param scrnMsg
     */
    private static void setAlternateRowsBG(NLCL1020BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(NLCL1020Constant.SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Need Double Check
     * @param scrnMsg NLCL1020BMsg
     * @return boolean
     */
    public static boolean needDoubleCheck(NLCL1020BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String xxChkBox = scrnMsg.A.no(i).xxChkBox_AD.getValue();
            if (ZYPCommonFunc.hasValue(xxChkBox) && xxChkBox.equals("Y")) {
                if (scrnMsg.xxYesNoCd.isClear()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Reset Submit Double Check
     * @param scrnMsg NLCL1020BMsg
     */
    public static void resetSubmitDoubleCheck(NLCL1020BMsg scrnMsg) {
        scrnMsg.xxYesNoCd.clear();
    }

    /**
     * Enable Search
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL1020BMsg
     * @param enable boolean
     */
    public static void enableSearch(EZDCommonHandler scrnAppli, NLCL1020BMsg scrnMsg, boolean enable) {

        if (enable) {
            scrnMsg.invtyOrdNum.setInputProtected(false);
            scrnAppli.setButtonEnabled(NLCL1020Constant.SEARCH_BTN, true);
        } else {
            scrnMsg.invtyOrdNum.setInputProtected(true);
            scrnAppli.setButtonEnabled(NLCL1020Constant.SEARCH_BTN, false);
        }
    }

    /**
     * Is Read Only
     * @param scrnMsg NLCL1020BMsg
     * @return boolean
     */
    public static boolean isReadOnly(NLCL1020BMsg scrnMsg) {
        if (scrnMsg.xxFuncId.getValue().equals(NLCL1020Constant.INQUIRY_ONLY)) {
            return true;
        }
        return false;
    }

}
