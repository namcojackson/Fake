/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830.common;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.BIZ_ID;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_APL;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_APR;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_RST;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.BTN_MASS_APPLY;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.HEADER_STS_NM_ENTERED;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.LINE_STS_NM_ON_LOAN;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_0;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_1;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_10;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_15;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_2;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_20;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_3;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_30;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_4;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_5;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_50;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_6;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_7;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.IDX_12;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.NWAM0812E;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.ORD_LINE_VAL_SET_KEY;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.RADIO_VAL_NEW_ORD;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.SCRN_ID_00;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.NWAL1830T020;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.ZZSM4300E;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.PERCENT;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDFieldErrorException;
import parts.common.EZDGUIAttribute;
import business.servlet.NWAL1830.NWAL1830BMsg;
import business.servlet.NWAL1830.NWAL1830_ABMsg;
import business.servlet.NWAL1830.NWAL1830_BBMsg;
import business.servlet.NWAL1830.NWAL1830_BBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL1830CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/30   Fujitsu         Y.Taoka         Update          S21_NA#7861,S21_NA#8393
 * 2016/10/21   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2017/09/21   Fujitsu         M.Ohno          Update          S21_NA#21308
 * 2017/10/17   Fujitsu         R.Nakamaura     Update          S21_NA#21507
 * 2018/10/18   Fujitsu         K.Ishizuka      Update          S21_NA#28712
 * 2018/11/05   Fujitsu         C.Hara          Update          S21_NA#29087
 *</pre>
 */
public class NWAL1830CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NWAL1830BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL1830BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        if (active(scrnMsg) && 0 < scrnMsg.A.getValidCount()) {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        }
    }

    /**
     * Set Common Button properties.
     * 
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
     * 
     * @param scrnMsg     NWAL1830BMsg
     * @param scrnAMsgAry NWAL1830_BBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1830BMsg scrnMsg, NWAL1830_BBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1830BMsg
     * @param scrnAMsgAry NWAL1830_BBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1830BMsg scrnMsg, NWAL1830_BBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1830BMsg
     * @param scrnAMsgAry NWAL1830_BBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1830BMsg scrnMsg, NWAL1830_BBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1830BMsg scrnMsg) {

        // Order Line
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd_OL);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd_OL);
        scrnMsg.addCheckItem(scrnMsg.slsRepTocNm_OL);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_OL);

        // Config Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWAL1830_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A1);
        }
        // Line Detail
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            NWAL1830_BBMsg scrnLineMsg = scrnMsg.B.no(i);
            scrnMsg.addCheckItem(scrnLineMsg.dsOrdLineCatgCd_BA);
            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B1);
            scrnMsg.addCheckItem(scrnLineMsg.rtlWhNm_B1);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException(ZZSM4300E, new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains(NWAL1830T020)) {
            return true;
        }
        return false;
    }


    /**
     * setControlField
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1830BMsg
     */
    public static void setControlField(S21CommonHandler handler, NWAL1830BMsg scrnMsg) {
        setGuiStyle(scrnMsg);
        // Order Header
        scrnMsg.cpoOrdNum_OH.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_OH.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_OH.setInputProtected(true);
        scrnMsg.dsOrdRsnDescTxt_OH.setInputProtected(true);
        scrnMsg.ordDt_OH.setInputProtected(true);
        scrnMsg.ordHdrStsNm_OH.setInputProtected(true);
        scrnMsg.aquNum_OH.setInputProtected(true);
        scrnMsg.loanPerDaysAot_OH.setInputProtected(true);
        scrnMsg.slsRepTocNm_OH.setInputProtected(true);
        scrnMsg.coaBrCd_OH.setInputProtected(true);
        scrnMsg.coaBrDescTxt_OH.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_OH.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_OH.setInputProtected(true);

        // Order Line
        if (active(scrnMsg)) {
            // Action
            scrnMsg.dsOrdLineCatgCd_OL.setInputProtected(false);
            scrnMsg.xxChkBox_OL.setInputProtected(false);
            handler.setButtonEnabled(BTN_MASS_APPLY, true);
            scrnMsg.xxRadioBtn_OL.setInputProtected(false);
            setControlFieldRadioBtn(scrnMsg);

        } else {
            scrnMsg.dsOrdLineCatgCd_OL.setInputProtected(true);
            scrnMsg.xxChkBox_OL.setInputProtected(true);
            handler.setButtonEnabled(BTN_MASS_APPLY, false);
            scrnMsg.xxRadioBtn_OL.setInputProtected(true);
            scrnMsg.dsOrdCatgCd_OL.setInputProtected(true);
            scrnMsg.dsOrdTpCd_OL.setInputProtected(true);
            scrnMsg.slsRepTocNm_OL.setInputProtected(true);
            scrnMsg.slsRepTocNm_LK.clear();
            scrnMsg.cpoOrdNum_OL.setInputProtected(true);
            scrnMsg.cpoOrdNum_LK.clear();
        }

        // Config Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWAL1830_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            if (active(scrnMsg)) {
                scrnLineMsg.xxChkBox_A1.setInputProtected(false);
            } else {
                scrnLineMsg.xxChkBox_A1.setInputProtected(true);
            }
            scrnLineMsg.svcConfigMstrPk_A1.setInputProtected(true);
            scrnLineMsg.mdlNm_A1.setInputProtected(true);
            scrnLineMsg.shipToCustLocAddr_A1.setInputProtected(true);
            scrnLineMsg.billToCustLocAddr_A1.setInputProtected(true);
        }

        // Line Detail
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            NWAL1830_BBMsg scrnLineMsg = scrnMsg.B.no(i);
            scrnLineMsg.xxScrItem50Txt_B1.setInputProtected(true);
            scrnLineMsg.dplyLineNum_B1.setInputProtected(true);
            scrnLineMsg.mdseCd_B1.setInputProtected(true);
            scrnLineMsg.mdseDescShortTxt_B1.setInputProtected(true);
            scrnLineMsg.ordQty_B1.setInputProtected(true);
            scrnLineMsg.shipQty_B1.setInputProtected(true);
            scrnLineMsg.mdlNm_B1.setInputProtected(true);
            scrnLineMsg.ordLineStsDescTxt_B1.setInputProtected(true);
            scrnLineMsg.dsOrdLineCatgDescTxt_B1.setInputProtected(true);
            scrnLineMsg.coaMdseTpDescTxt_B1.setInputProtected(true);
            scrnLineMsg.coaProdDescTxt_B1.setInputProtected(true);
            scrnLineMsg.serNum_B1.setInputProtected(true);
            scrnLineMsg.procFlg_B1.setInputProtected(true);
            scrnLineMsg.cpoOrdNum_B2.setInputProtected(true);
            scrnLineMsg.dplyLineNum_B2.setInputProtected(true);
            scrnLineMsg.ordLineStsDescTxt_B2.setInputProtected(true);

            if (active(scrnMsg)) {

                scrnLineMsg.dsOrdLineCatgCd_BA.setInputProtected(false);
                scrnLineMsg.xxChkBox_B1.setInputProtected(false);
                setControlFieldWh(scrnMsg, scrnLineMsg);

            } else {
                scrnLineMsg.dsOrdLineCatgCd_BA.setInputProtected(true);
                scrnLineMsg.xxChkBox_B1.setInputProtected(true);
                scrnLineMsg.rtlWhNm_B1.setInputProtected(true);
                scrnLineMsg.rtlWhNm_LK.clear();
            }
        }
    }

    /**
     * getActionInbdOutbd
     * @param scrnMsg NWAL1830BMsg
     * @param scrnLineMsg NWAL1830_BBMsg
     * @return String
     */
    public static String getActionInbdOutbd(NWAL1830BMsg scrnMsg, NWAL1830_BBMsg scrnLineMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (scrnLineMsg.dsOrdLineCatgCd_BA.getValue().equals(scrnMsg.C.no(i).dsOrdLineCatgCd.getValue())) {
                return scrnMsg.C.no(i).dsOrdLineDrctnCd.getValue();
            }
        }
        return null;
    }

    /**
     * setControlFieldRadioBtn
     * @param scrnMsg NWAL1830BMsg
     */
    public static void setControlFieldRadioBtn(NWAL1830BMsg scrnMsg) {
        scrnMsg.cpoOrdNum_OL.setInputProtected(true);
        if (RADIO_VAL_NEW_ORD.intValue() == scrnMsg.xxRadioBtn_OL.getValueInt()) {
            // New Order is No
            scrnMsg.dsOrdCatgCd_OL.setInputProtected(false);
            scrnMsg.dsOrdTpCd_OL.setInputProtected(false);
            scrnMsg.slsRepTocNm_OL.setInputProtected(false);
            scrnMsg.slsRepTocNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.cpoOrdNum_LK.clear();
        } else {
            // Existing Order is No
            scrnMsg.dsOrdCatgCd_OL.setInputProtected(true);
            scrnMsg.dsOrdTpCd_OL.setInputProtected(true);
            scrnMsg.slsRepTocNm_OL.setInputProtected(true);
            scrnMsg.slsRepTocNm_LK.clear();
            scrnMsg.cpoOrdNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * setControlFieldWh
     * @param scrnMsg NWAL1830BMsg
     * @param scrnLineMsg NWAL1830_BBMsg
     */
    public static void setControlFieldWh(NWAL1830BMsg scrnMsg, NWAL1830_BBMsg scrnLineMsg) {
        if (ZYPCommonFunc.hasValue(scrnLineMsg.dsOrdLineCatgCd_BA) && DS_ORD_LINE_DRCTN.INBOUND.equals(getActionInbdOutbd(scrnMsg, scrnLineMsg))) {
            // Active
            scrnLineMsg.rtlWhNm_B1.setInputProtected(false);
            scrnLineMsg.rtlWhNm_LK.setValue(ZYPConstant.FLG_ON_Y);

        } else {
            // In-Active
            scrnLineMsg.rtlWhNm_B1.setInputProtected(true);
            scrnLineMsg.rtlWhNm_LK.clear();
        }
    }

    /**
     * active
     * @param scrnMsg NWAL1830BMsg
     * @return boolean
     */
    public static boolean active(NWAL1830BMsg scrnMsg) {
        if (!hasUpdateFuncId()) {
            return false;
        } else if (scrnMsg.xxScrStsTxt_CL.getValue().equals(scrnMsg.ordHdrStsNm_OH.getValue())) {
            return false;
        } else if (scrnMsg.A.getValidCount() == 0) {
            return false;
        }
        return true;
    }


    /**
     * setGuiStyle
     * @param scrnMsg NWAL1830BMsg
     */
    public static void setGuiStyle(NWAL1830BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        int index = 0;
        String preDsOrdPosnNum = "";
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1830_BBMsg scrnLineMsg = scrnMsg.B.no(i);
            String dsOrdPosnNum = scrnLineMsg.dsOrdPosnNum_B1.getValue();
            if (preDsOrdPosnNum.equals(dsOrdPosnNum)) {
                index++;
            } else {
                index = 0;
            }
            EZDGUIAttribute leftBAttr = new EZDGUIAttribute(SCRN_ID_00, "leftB" + "#" + dsOrdPosnNum + "_" + index);
            EZDGUIAttribute rightBAttr = new EZDGUIAttribute(SCRN_ID_00, "rightB" + "#" + dsOrdPosnNum + "_" + index);
            if (index % 2 != 0) {
                leftBAttr.setStyleAttribute("background-color", EZDGUIAttribute.pEvenNumberBGcolor);
                rightBAttr.setStyleAttribute("background-color", EZDGUIAttribute.pEvenNumberBGcolor);
            } else {
                leftBAttr.setStyleAttribute("background-color", EZDGUIAttribute.pOddNumberBGcolor);
                rightBAttr.setStyleAttribute("background-color", EZDGUIAttribute.pOddNumberBGcolor);
            }
            scrnMsg.addGUIAttribute(leftBAttr);
            scrnMsg.addGUIAttribute(rightBAttr);
            preDsOrdPosnNum = dsOrdPosnNum;
        }
    }

    /**
     * inputCheckConvEntry
     * @param scrnMsg NWAL1830BMsg
     * @param beforeBLAP  boolean
     */
    public static void inputCheckConvEntry(NWAL1830BMsg scrnMsg, boolean beforeBLAP) {
        boolean existCheckOn = false;
        boolean existLoanSell = false;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1830_BBMsg scrnLineMsg = scrnMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnLineMsg.xxChkBox_B1.getValue())) {
                // Check-On:Conversion Entry
                if (!ZYPCommonFunc.hasValue(scrnLineMsg.dsOrdLineCatgCd_BA) && ZYPConstant.FLG_ON_Y.equals(scrnLineMsg.xxDplyCtrlFlg_B1.getValue())) {
                    // Action is null --> Error
                    scrnMsg.addCheckItem(scrnLineMsg.dsOrdLineCatgCd_BA);

                } else if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(getActionInbdOutbd(scrnMsg, scrnLineMsg))) {
                    existLoanSell = true;

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(getActionInbdOutbd(scrnMsg, scrnLineMsg))
                        && LINE_STS_NM_ON_LOAN.equals(scrnLineMsg.dsOrdLineCatgDescTxt_B1.getValue()) ) { // 2017/09/21 S21_NA#21308 add
                    // Check entered WH
                    scrnMsg.addCheckItem(scrnLineMsg.rtlWhNm_B1);
                }
                existCheckOn = true;
            }
        }
        if (!existCheckOn && beforeBLAP) {
            scrnMsg.setMessageInfo(NWAM0812E);
            throw new EZDFieldErrorException();
        }
        if (existLoanSell) {
            // Madatory Check
            if (RADIO_VAL_NEW_ORD.equals(scrnMsg.xxRadioBtn_OL.getValue())) {
                // Entry Conversion with New Order
                scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd_OL);
                scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd_OL);
                scrnMsg.addCheckItem(scrnMsg.slsRepTocNm_OL);
            } else {
                // Entry Conversion with Existing Order
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_OL);
                //2018/11/05 S21_NA#29087 Add Start
                if(!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_OL)){
                    scrnMsg.cpoOrdNum_OL.setErrorInfo(1, ZZM9000E, new String[]{"Order Number"});
                    scrnMsg.putErrorScreen();
                }
                //2018/11/05 S21_NA#29087 Add End
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NWAL1830BMsg
     */
    public static void addCheckItem(NWAL1830BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.slsRepTocNm_OL);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1830_BBMsg scrnLineMsg = scrnMsg.B.no(i);
            scrnMsg.addCheckItem(scrnLineMsg.dsOrdLineCatgCd_BA);
            scrnMsg.addCheckItem(scrnLineMsg.rtlWhNm_B1);
            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_B1);
        }
    }

    /**
     * getParamNWAL1130ForWh
     * @param scrnMsg NWAL1830BMsg
     * @param selectIndex  int
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForWh(NWAL1830BMsg scrnMsg, int selectIndex) {

        String rtlWhNm = null;
        rtlWhNm = scrnMsg.B.no(selectIndex).rtlWhNm_B1.getValue();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "P";
        params[IDX_1] = "Ship From Warehouse Search";
        params[IDX_2] = getSlctTblNmForWh(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Warehouse Name";
        whereArray[IDX_1] = "UPPER(RTL_WH_NM)";
        whereArray[IDX_2] = rtlWhNm;
        // 2016/05/30 S21_NA#8393 Add Start
        if (!ZYPCommonFunc.hasValue(rtlWhNm)) {
            whereArray[IDX_2] = PERCENT;
        }
        // 2016/05/30 S21_NA#8393 Add Start
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Warehouse Code";
        columnArray0[IDX_1] = "RTL_WH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Warehouse Name";
        columnArray1[IDX_1] = "RTL_WH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_WH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * getSlctTblNmForWh
     * @param scrnMsg NWAL1830BMsg
     * @return String
     */
    private static String getSlctTblNmForWh(NWAL1830BMsg scrnMsg) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String slsDt = scrnMsg.slsDt.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd_OH.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DISTINCT ");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_WH_CD");
        sb.append("   ,WH.RTL_WH_NM ");
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/18 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/18 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }
        return sb.toString();
    }

    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NWAL1830BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NWAL1830BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "P";
        params[IDX_1] = "Sales Rep Search";

        params[IDX_2] = getSlctTblNmForSlsRep(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        // 2016/05/30 S21_NA#7861 Mod Start
        whereArray0[IDX_0] = "Resource Number";
        whereArray0[IDX_1] = "PSN_NUM";
        // 2016/05/30 S21_NA#8393 Add Start
        if (!ZYPCommonFunc.hasValue(scrnMsg.slsRepTocNm_OL)) {
            whereArray0[IDX_2] = PERCENT;
        }
        // 2016/05/30 S21_NA#8393 Add End
        // 2016/05/30 S21_NA#7861 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Name";
        whereArray1[IDX_1] = "TOC_NM";
        whereArray1[IDX_2] = scrnMsg.slsRepTocNm_OL.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Resource Number"; // 2016/05/30 S21_NA#7861 Mod End
        columnArray0[IDX_1] = "PSN_NUM"; // 2016/05/30 S21_NA#7861 Mod End
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_12);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Name";
        columnArray1[IDX_1] = "TOC_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Line of Business";
        columnArray2[IDX_1] = "LINE_BIZ_TP_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Branch";
        columnArray3[IDX_1] = "COA_BR_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role";
        columnArray4[IDX_1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "TOC_CD";
        columnArray5[IDX_1] = "TOC_CD";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_0);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM"; // 2016/05/30 S21_NA#7861 Mod 
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * getSlctTblNmForSlsRep
     * @param scrnMsg NWAL1830BMsg
     * @return String
     */
    private static String getSlctTblNmForSlsRep(NWAL1830BMsg scrnMsg) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM"); // 2016/05/30 S21_NA#7861 Mod
//        sb.append("   ,SP.PSN_CD");
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO "); // 2016/05/30 S21_NA#7861 Add
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");
        // 2016/05/30 S21_NA#7861 Add Start
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        // 2016/10/21 S21_NA#14607 Add Start
        sb.append("    AND SO.RGTN_STS_CD        != '").append(RGTN_STS.TERMINATED).append("'");
        // 2016/10/21 S21_NA#14607 Add End
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        // 2016/05/30 S21_NA#7861 Add End

        return sb.toString();
    }


    /**
     * Get Param NWAL1130 For Order Search
     * @param scrnMsg NWAL1830BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForOrderSearch(NWAL1830BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "P";
        params[IDX_1] = "Order Search";

        params[IDX_2] = getSlctTblNmForOrderSearch(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Order Number";
        whereArray0[IDX_1] = "CPO_ORD_NUM";
        // 2016/05/30 S21_NA#8393 Mod Start
        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_OL)) {
            whereArray0[IDX_2] = PERCENT;
        } else {
            whereArray0[IDX_2] = scrnMsg.cpoOrdNum_OL.getValue();
        }
        // 2016/05/30 S21_NA#8393 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Order Category";
        whereArray1[IDX_1] = "DS_ORD_CATG_DESC_TXT";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Order Reason";
        // Mod Start 2017/10/17 QC#21507
//        whereArray2[IDX_1] = "DS_ORD_RSN_DESC_TXT";
        whereArray2[IDX_1] = "DS_ORD_TP_DESC_TXT";
        // Mod End 2017/10/17 QC#21507
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Number";
        columnArray0[IDX_1] = "CPO_ORD_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Category";
        columnArray1[IDX_1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Order Reason";
        columnArray2[IDX_1] = "DS_ORD_TP_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Order Status";
        columnArray3[IDX_1] = "ORD_HDR_STS_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_15);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Bill To Name";
        columnArray4[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Ship To Name";
        columnArray5[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CPO_ORD_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * getSlctTblNmForOrderSearch
     * @param scrnMsg NWAL1830BMsg
     * @return String
     */
    private static String getSlctTblNmForOrderSearch(NWAL1830BMsg scrnMsg) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("       DC.CPO_ORD_NUM ");
        sb.append("     , STS.ORD_HDR_STS_NM ");
        sb.append("     , DC.DS_ORD_CATG_DESC_TXT ");
        sb.append("     , DC.DS_ORD_TP_DESC_TXT ");
        sb.append("     , DC.BILL_TO_CUST_ACCT_NM ");
        sb.append("     , DC.SHIP_TO_CUST_ACCT_NM ");
        sb.append("     , DC.GLBL_CMPY_CD ");
        sb.append("     , DC.EZCANCELFLAG ");
        sb.append("FROM                                                           ");
        sb.append("        CPO_V         DC                                       ");
        sb.append("      , (                                                      ");
        sb.append("          SELECT                                               ");
        sb.append("            DOS.CPO_ORD_NUM                                    ");
        sb.append("           ,DOS.ORD_HDR_STS_NM                                 ");
        sb.append("          FROM                                                 ");
        sb.append("            DS_ORD_STS_V  DOS                                  ");
        sb.append("          WHERE                                                ");
        sb.append("            DOS.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("          AND DOS.EZCANCELFLAG      = '0'                      ");
        sb.append("          GROUP BY DOS.CPO_ORD_NUM                             ");
        sb.append("           ,DOS.ORD_HDR_STS_NM                                 ");
        sb.append("        ) STS                                                  ");
        sb.append("      , (                                                      ");
        sb.append("          SELECT                                               ");
        sb.append("            C.DS_ORD_CATG_CD                                   ");
        sb.append("           ,B.DS_ORD_TP_CD                                     ");
        sb.append("          FROM                                                 ");
        sb.append("            DS_ORD_LINE_PROC_DFN A                             ");
        sb.append("           ,DS_ORD_TP B                                        ");
        sb.append("           ,DS_ORD_CATG C                                      ");
        sb.append("           ,ORD_LINE_VAL_SET D                                 ");
        sb.append("           ,DS_ORD_LINE_CATG E                                 ");
        sb.append("          WHERE                                                ");
        sb.append("                A.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("          AND   A.EZCANCELFLAG      = '0'                      ");
        sb.append("          AND   A.GLBL_CMPY_CD      = B.GLBL_CMPY_CD           ");
        sb.append("          AND   A.DS_ORD_TP_CD      = B.DS_ORD_TP_CD           ");
        sb.append("          AND   B.EZCANCELFLAG      = '0'                      ");
        sb.append("          AND   B.GLBL_CMPY_CD      = C.GLBL_CMPY_CD           ");
        sb.append("          AND   B.DS_ORD_CATG_CD    = C.DS_ORD_CATG_CD         ");
        sb.append("          AND   C.EZCANCELFLAG      = '0'                      ");
        sb.append("          AND   A.GLBL_CMPY_CD      = D.GLBL_CMPY_CD           ");
        sb.append("          AND   A.DS_ORD_LINE_CATG_CD = D.DS_ORD_LINE_CATG_CD  ");
        sb.append("          AND   D.ORD_LINE_CTX_TP_CD  = '").append(ORD_LINE_VAL_SET_KEY).append("'");
        sb.append("          AND   D.EZCANCELFLAG        = '0'                    ");
        sb.append("          AND   D.GLBL_CMPY_CD        = E.GLBL_CMPY_CD         ");
        sb.append("          AND   D.DS_ORD_LINE_CATG_CD = E.DS_ORD_LINE_CATG_CD  ");
        sb.append("          AND   E.DS_ORD_LINE_DRCTN_CD = '").append(DS_ORD_LINE_DRCTN.OUTBOUND).append("'");
        sb.append("          AND   E.EZCANCELFLAG         = '0'                   ");
        sb.append("        ) TP                                                   ");
        sb.append("WHERE                                                          ");
        sb.append("    DC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("AND DC.EZCANCELFLAG = '0'                                      ");
        sb.append("AND DC.CPO_ORD_NUM = STS.CPO_ORD_NUM                           ");
        sb.append("AND STS.ORD_HDR_STS_NM = '").append(HEADER_STS_NM_ENTERED).append("'");
        sb.append("AND DC.DS_ORD_CATG_CD = TP.DS_ORD_CATG_CD                      ");
        sb.append("AND DC.DS_ORD_TP_CD = TP.DS_ORD_TP_CD                          ");

        return sb.toString();
    }
}
