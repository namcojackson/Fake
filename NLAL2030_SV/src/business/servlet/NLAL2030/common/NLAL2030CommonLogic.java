/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030.common;

import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLAL2030.NLAL2030BMsg;
import business.servlet.NLAL2030.NLAL2030Bean;
import business.servlet.NLAL2030.NLAL2030_ABMsg;
import business.servlet.NLAL2030.NLAL2030_BBMsg;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLAL2030CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 05/19/2016   CSAI            Y.Imazu         Update          QC#8476
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7981
 * 02/25/2019   CITS            K.Ogino         Update          QC#30456
 * 10/08/2022   CITS            K.Iwamoto       Update          QC#60364
 * 02/22/2023   Hitachi         TZ.Win          Update          QC#61161
 * 02/28/2023   Hitachi         TZ.Win          Update          QC#61160
 *</pre>
 */
public class NLAL2030CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_SAV[0], NLAL2030Constant.BTN_CMN_SAV[1], NLAL2030Constant.BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_SUB[0], NLAL2030Constant.BTN_CMN_SUB[1], NLAL2030Constant.BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_APL[0], NLAL2030Constant.BTN_CMN_APL[1], NLAL2030Constant.BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_APR[0], NLAL2030Constant.BTN_CMN_APR[1], NLAL2030Constant.BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_RJT[0], NLAL2030Constant.BTN_CMN_RJT[1], NLAL2030Constant.BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_DWL[0], NLAL2030Constant.BTN_CMN_DWL[1], NLAL2030Constant.BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_DEL[0], NLAL2030Constant.BTN_CMN_DEL[1], NLAL2030Constant.BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_CLR[0], NLAL2030Constant.BTN_CMN_CLR[1], NLAL2030Constant.BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_RST[0], NLAL2030Constant.BTN_CMN_RST[1], NLAL2030Constant.BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(NLAL2030Constant.BTN_CMN_RTN[0], NLAL2030Constant.BTN_CMN_RTN[1], NLAL2030Constant.BTN_CMN_RTN[2], 1, null);
    }

    /**
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL2030BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NLAL2030BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(NLAL2030Constant.SCRN_ID_00);

        // Header
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.whNm.setInputProtected(true);
        scrnMsg.rtlSwhNm.setInputProtected(true);
        scrnMsg.carrNm.setInputProtected(true);

        boolean hasUpdateFuncId = hasUpdateFuncId();

        // Detail (Order Tab)
        if (NLAL2030Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.A.getValidCount()) {

            if (hasUpdateFuncId) {

                handler.setButtonProperties(NLAL2030Constant.BTN_CREATE[0], NLAL2030Constant.BTN_CREATE[1], NLAL2030Constant.BTN_CREATE[2], 1, null);
            }

            // QC#18461-Sol#085
            handler.setButtonProperties(NLAL2030Constant.BTN_CMN_SUB[0], NLAL2030Constant.BTN_CMN_SUB[1], NLAL2030Constant.BTN_CMN_SUB[2], 0, null);

            handler.setButtonProperties(NLAL2030Constant.BTN_SELECT_ALL[0], NLAL2030Constant.BTN_SELECT_ALL[1], NLAL2030Constant.BTN_SELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_UNSELECT_ALL[0], NLAL2030Constant.BTN_UNSELECT_ALL[1], NLAL2030Constant.BTN_UNSELECT_ALL[2], 1, null);

            scrnMsg.rwsRefNum_AP.setInputProtected(false);
            scrnMsg.imptInvBolNum_AP.setInputProtected(false);

            setRowsBGWithClear(scrnMsg, scrnMsg.A, NLAL2030Bean.A);

            String preTrxOrdNum = "";
            String preSceOrdTpNm = "";
            String preDsOrdPosnNum = "";

            for (int i = 0; i < scrnMsg.A.length(); i++) {

                // Clear GUI Attribute
                clearGUIAttribute(scrnMsg, NLAL2030Bean.A, i);

                if (i >= scrnMsg.A.getValidCount()) {

                    continue;
                }

                NLAL2030_ABMsg scrnMsgLine = scrnMsg.A.no(i);

                if (hasUpdateFuncId) {

                    // RWS can be created manually
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.rwsOpenFlg_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.rwsFlg_A1.getValue())) {
                        // QC#30456
                        if (SCE_ORD_TP.RETURN.equals(scrnMsgLine.sceOrdTpCd_A1.getValue()) && ZYPCommonFunc.hasValue(scrnMsgLine.svcConfigMstrPk_A1)) {
                            scrnMsgLine.xxChkBox_A2.setInputProtected(true);
                        } else {
                            scrnMsgLine.xxChkBox_A2.setInputProtected(false);
                        }

                    } else {

                        scrnMsgLine.xxChkBox_A2.setInputProtected(true);
                        scrnMsgLine.xxChkBox_A2.clear();
                    }

                } else {

                    scrnMsgLine.xxChkBox_A2.setInputProtected(true);
                    scrnMsgLine.xxChkBox_A2.clear();
                }

                scrnMsgLine.xxChkBox_A1.setInputProtected(false);
                scrnMsgLine.sceOrdTpNm_A1.setInputProtected(true);
                scrnMsgLine.fromLocCd_A1.setInputProtected(true);
                scrnMsgLine.dsAcctNm_A1.setInputProtected(true);
                scrnMsgLine.rtlWhNm_A1.setInputProtected(true);
                scrnMsgLine.svcConfigMstrPk_A1.setInputProtected(true);
                scrnMsgLine.mdseCd_A1.setInputProtected(true);
                scrnMsgLine.flipMdseCd_A1.setInputProtected(true);
                scrnMsgLine.mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsgLine.rwsQty_A1.setInputProtected(true);
                scrnMsgLine.xxQty10Num_A1.setInputProtected(true);
                scrnMsgLine.serNum_A1.setInputProtected(true);
                scrnMsgLine.aslMdseCd_A1.setInputProtected(true);
                scrnMsgLine.rtlSwhCd_A1.setInputProtected(true);
                if (1 < scrnMsgLine.rwsQty_A1.getValueInt() && ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.serNumTakeFlg_A1.getValue())) {

                    handler.setButtonEnabled(NLAL2030Constant.BTN_SER_NUM[0], i, true);

                } else {

                    handler.setButtonEnabled(NLAL2030Constant.BTN_SER_NUM[0], i, false);
                }

                // Visibility
                if (preTrxOrdNum.equals(scrnMsgLine.trxOrdNum_A1.getValue()) && preSceOrdTpNm.equals(scrnMsgLine.sceOrdTpNm_A1.getValue())
                        && isSameVal(preDsOrdPosnNum, scrnMsgLine.dsOrdPosnNum_A1.getValue())) {

                    setVisibilityTbl(scrnMsg, NLAL2030Bean.A, i);
                }

                preTrxOrdNum = scrnMsgLine.trxOrdNum_A1.getValue();
                preSceOrdTpNm = scrnMsgLine.sceOrdTpNm_A1.getValue();
                preDsOrdPosnNum = scrnMsgLine.dsOrdPosnNum_A1.getValue();
            }

        // Detail (RWS List Tab)
        } else if (NLAL2030Constant.TAB_ID_RWS.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.B.getValidCount()) {

            if (hasUpdateFuncId) {

                handler.setButtonProperties(NLAL2030Constant.BTN_CANCEL[0], NLAL2030Constant.BTN_CANCEL[1], NLAL2030Constant.BTN_CANCEL[2], 1, null);
                // QC#18461-Sol#085
                scrnMsg.xxLinkAncr_AP.setInputProtected(false);
                handler.setButtonEnabled(NLAL2030Constant.BTN_APPLY_WH_NM, true);
                // START 2023/02/28 TZ.Win [QC#61160, ADD]
                scrnMsg.xxLinkAncr_AS.setInputProtected(false);
                handler.setButtonEnabled(NLAL2030Constant.BTN_APPLY_SWH_NM, true);
                // END 2023/02/28 TZ.Win [QC#61160, ADD]
                handler.setButtonEnabled(NLAL2030Constant.BTN_APPLY, true);
                handler.setButtonProperties(NLAL2030Constant.BTN_CMN_SUB[0], NLAL2030Constant.BTN_CMN_SUB[1], NLAL2030Constant.BTN_CMN_SUB[2], 1, null);
            }

            handler.setButtonProperties(NLAL2030Constant.BTN_PRINT[0], NLAL2030Constant.BTN_PRINT[1], NLAL2030Constant.BTN_PRINT[2], 1, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_SELECT_ALL[0], NLAL2030Constant.BTN_SELECT_ALL[1], NLAL2030Constant.BTN_SELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_UNSELECT_ALL[0], NLAL2030Constant.BTN_UNSELECT_ALL[1], NLAL2030Constant.BTN_UNSELECT_ALL[2], 1, null);

            scrnMsg.rwsRefNum_AP.setInputProtected(true);
            scrnMsg.imptInvBolNum_AP.setInputProtected(true);

            // QC#18461-Sol#085
            scrnMsg.rtlWhNm_AP.setInputProtected(true);
            // START 2023/02/28 TZ.Win [QC#61160, ADD]
            scrnMsg.rtlSwhNm_AP.setInputProtected(true);
            // END 2023/02/28 TZ.Win [QC#61160, ADD]

            setRowsBGWithClear(scrnMsg, scrnMsg.B, NLAL2030Bean.B);

            String preRwsNum = "";

            for (int i = 0; i < scrnMsg.B.length(); i++) {

                // Clear GUI Attribute
                clearGUIAttribute(scrnMsg, NLAL2030Bean.B, i);

                if (i >= scrnMsg.B.getValidCount()) {

                    continue;
                }

                NLAL2030_BBMsg scrnMsgLine = scrnMsg.B.no(i);

                if (hasUpdateFuncId) {

                    scrnMsgLine.xxChkBox_B1.setInputProtected(false);

                } else {

                    scrnMsgLine.xxChkBox_B1.setInputProtected(true);
                }

                scrnMsgLine.rwsStsDescTxt_B1.setInputProtected(true);
                scrnMsgLine.rtlWhNm_B1.setInputProtected(true);
                scrnMsgLine.rwsRefNum_B1.setInputProtected(true);
                scrnMsgLine.imptInvBolNum_B1.setInputProtected(true);
                scrnMsgLine.sceOrdTpNm_B1.setInputProtected(true);
                scrnMsgLine.fromLocCd_B1.setInputProtected(true);
                scrnMsgLine.dsAcctNm_B1.setInputProtected(true);
                scrnMsgLine.svcConfigMstrPk_B1.setInputProtected(true);
                scrnMsgLine.mdseCd_B1.setInputProtected(true);
                scrnMsgLine.flipMdseCd_B1.setInputProtected(true);
                scrnMsgLine.mdseDescShortTxt_B1.setInputProtected(true);
                scrnMsgLine.rwsQty_B1.setInputProtected(true);
                scrnMsgLine.xxQty10Num_B1.setInputProtected(true);
                scrnMsgLine.serNum_B1.setInputProtected(true);
                //QC#60364 K.Iwamoto 2022/10/08 DEL START
                //scrnMsgLine.rtlSwhCd_B1.setInputProtected(true);
                //QC#60364 K.Iwamoto 2022/10/08 DEL END
                scrnMsgLine.xxRtlWhSrchTxt_B1.setInputProtected(true);
                // START 2023/02/22 TZ.Win [QC#61161, ADD]
                scrnMsgLine.xxCratDt_B1.setInputProtected(true);
                // END 2023/02/22 TZ.Win [QC#61161, ADD]

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.serNumTakeFlg_B1.getValue()) && 1 < scrnMsgLine.rwsQty_B1.getValueInt()) {

                    handler.setButtonEnabled(NLAL2030Constant.BTN_SER_NUM[0], i, true);

                } else {

                    handler.setButtonEnabled(NLAL2030Constant.BTN_SER_NUM[0], i, false);
                }

                // Visibility
                if (preRwsNum.equals(scrnMsgLine.rwsNum_B1.getValue())) {

                    setVisibilityTbl(scrnMsg, NLAL2030Bean.B, i);
                }

                preRwsNum = scrnMsgLine.rwsNum_B1.getValue();

                // QC#18461-Sol#085
                if(hasUpdateFuncId && ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.xxDplyCtrlFlg_WH.getValue())){
                    scrnMsgLine.rtlWhCd_B1.setInputProtected(false);
                    handler.setButtonEnabled(NLAL2030Constant.BTN_RWS_WH_POPUP, i, true);
                    handler.setButtonEnabled(NLAL2030Constant.BTN_RWS_WH_NM, i, true);
                } else {
                    scrnMsgLine.rtlWhCd_B1.setInputProtected(true);
                    handler.setButtonEnabled(NLAL2030Constant.BTN_RWS_WH_POPUP, i, false);
                    handler.setButtonEnabled(NLAL2030Constant.BTN_RWS_WH_NM, i, false);
                }
                if(hasUpdateFuncId && ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.xxDplyCtrlFlg_TP.getValue())){
                    scrnMsgLine.thirdPtyDspTpCd_B1.setInputProtected(false);
                } else {
                    scrnMsgLine.thirdPtyDspTpCd_B1.setInputProtected(true);
                }
            }

        } else {

            handler.setButtonProperties(NLAL2030Constant.BTN_CREATE[0], NLAL2030Constant.BTN_CREATE[1], NLAL2030Constant.BTN_CREATE[2], 0, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_PRINT[0], NLAL2030Constant.BTN_PRINT[1], NLAL2030Constant.BTN_PRINT[2], 0, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_CANCEL[0], NLAL2030Constant.BTN_CANCEL[1], NLAL2030Constant.BTN_CANCEL[2], 0, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_SELECT_ALL[0], NLAL2030Constant.BTN_SELECT_ALL[1], NLAL2030Constant.BTN_SELECT_ALL[2], 0, null);
            handler.setButtonProperties(NLAL2030Constant.BTN_UNSELECT_ALL[0], NLAL2030Constant.BTN_UNSELECT_ALL[1], NLAL2030Constant.BTN_UNSELECT_ALL[2], 0, null);
            scrnMsg.rwsRefNum_AP.setInputProtected(true);
            scrnMsg.imptInvBolNum_AP.setInputProtected(true);
            // QC#18461-Sol#085 Add.
            scrnMsg.xxLinkAncr_AP.setInputProtected(true);
            handler.setButtonEnabled(NLAL2030Constant.BTN_APPLY_WH_NM, false);
            // START 2023/02/28 TZ.Win [QC#61160, ADD]
            scrnMsg.xxLinkAncr_AS.setInputProtected(true);
            handler.setButtonEnabled(NLAL2030Constant.BTN_APPLY_SWH_NM, false);
            // END 2023/02/28 TZ.Win [QC#61160, ADD]
            handler.setButtonEnabled(NLAL2030Constant.BTN_APPLY, false);
            handler.setButtonProperties(NLAL2030Constant.BTN_CMN_SUB[0], NLAL2030Constant.BTN_CMN_SUB[1], NLAL2030Constant.BTN_CMN_SUB[2], 0, null);
        }

        // Focus
        if (NLAL2030Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.A.getValidCount()) {

            scrnMsg.setFocusItem(scrnMsg.trxOrdNum);

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.B.getValidCount()) {

            scrnMsg.setFocusItem(scrnMsg.trxOrdNum);

        } else {

            scrnMsg.setFocusItem(scrnMsg.trxOrdNum);
        }
    }

    /**
     * isSameVal
     * @param val1 String
     * @param val2 String
     * @return boolean
     */
    private static boolean isSameVal(String val1, String val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.equals(val2)) {

            return true;
        }

        return false;
    }

    /**
     * clearGUIAttribute
     * @param scrnMsg NLAL2030BMsg
     * @param tblNm String
     * @param i int
     */
    private static void clearGUIAttribute(NLAL2030BMsg scrnMsg, String tblNm, int i) {

        if (NLAL2030Bean.A.equals(tblNm)) {

            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "xxChkBox_A1" + i);
            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "sceOrdTpNm_A1" + i);
            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "Open_OrdEntry" + i);

        } else if (NLAL2030Bean.B.equals(tblNm)) {

            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "xxChkBox_B1" + i);
            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "rwsStsDescTxt_B1" + i);
            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "Open_RcvEntry" + i);
            // START 2023/02/22 TZ.Win [QC#61161, ADD]
            scrnMsg.clearGUIAttribute(NLAL2030Constant.SCRN_ID_00, "xxCratDt_B1" + i);
            // END 2023/02/22 TZ.Win [QC#61161, ADD]
        }
    }

    /**
     * setVisibilityTbl
     * @param scrnMsg NLAL2030BMsg
     * @param tblNm String
     * @param i int
     */
    private static void setVisibilityTbl(NLAL2030BMsg scrnMsg, String tblNm, int i) {

        if (NLAL2030Bean.A.equals(tblNm)) {

            setVisibility(scrnMsg, "xxChkBox_A1", i);
            setVisibility(scrnMsg, "sceOrdTpNm_A1", i);
            setVisibility(scrnMsg, "Open_OrdEntry", i);

        } else if (NLAL2030Bean.B.equals(tblNm)) {

            setVisibility(scrnMsg, "xxChkBox_B1", i);
            setVisibility(scrnMsg, "rwsStsDescTxt_B1", i);
            setVisibility(scrnMsg, "Open_RcvEntry", i);
            // START 2023/02/22 TZ.Win [QC#61161, ADD]
            setVisibility(scrnMsg, "xxCratDt_B1", i);
            // END 2023/02/22 TZ.Win [QC#61161, ADD]
        }
    }

    /**
     * setVisibility
     * @param scrnMsg NLAL2030BMsg
     * @param ezdAttrNm String
     * @param i int
     */
    private static void setVisibility(NLAL2030BMsg scrnMsg, String ezdAttrNm, int i) {

        EZDGUIAttribute ezdAttr = new EZDGUIAttribute(NLAL2030Constant.SCRN_ID_00, ezdAttrNm + i);
        ezdAttr.setVisibility(false);
        scrnMsg.addGUIAttribute(ezdAttr);
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
     * @param scrnMsg     NLAL2030BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NLAL2030BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NLAL2030BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NLAL2030BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(NLAL2030Constant.SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NLAL2030BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NLAL2030BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(NLAL2030Constant.SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void checkInputHeader(NLAL2030BMsg scrnMsg) {

        // Header
        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.trxOrdNum) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd) && !ZYPCommonFunc.hasValue(scrnMsg.fromLocCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.sceOrdTpCd) && !ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rwsRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.serNum) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.whInEtaDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.whInEtaDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.imptInvBolNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.carrCd) && !ZYPCommonFunc.hasValue(scrnMsg.rwsNum) && !ZYPCommonFunc.hasValue(scrnMsg.rwsStsCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.flipMdseCd) && !ZYPCommonFunc.hasValue(scrnMsg.whCd)) {

            scrnMsg.trxOrdNum.setErrorInfo(1, "NLAM1286E");
            scrnMsg.mdseCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.fromLocCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.sceOrdTpCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.svcConfigMstrPk.setErrorInfo(1, "NLAM1286E");
            scrnMsg.rtlWhCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.rwsRefNum.setErrorInfo(1, "NLAM1286E");
            scrnMsg.serNum.setErrorInfo(1, "NLAM1286E");
            scrnMsg.rtlSwhCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.whInEtaDt_FR.setErrorInfo(1, "NLAM1286E");
            scrnMsg.whInEtaDt_TO.setErrorInfo(1, "NLAM1286E");
            scrnMsg.imptInvBolNum.setErrorInfo(1, "NLAM1286E");
            scrnMsg.carrCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.rwsNum.setErrorInfo(1, "NLAM1286E");
            scrnMsg.rwsStsCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.flipMdseCd.setErrorInfo(1, "NLAM1286E");
            scrnMsg.whCd.setErrorInfo(1, "NLAM1286E");
        }

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.whInEtaDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.whInEtaDt_TO)) {

            if (0 < scrnMsg.whInEtaDt_FR.getValue().compareTo(scrnMsg.whInEtaDt_TO.getValue())) {

                scrnMsg.whInEtaDt_FR.setErrorInfo(1, "NLZM2277E", new String[]{"ETA Date(Through)", "ETA Date(From)"});
                scrnMsg.whInEtaDt_TO.setErrorInfo(1, "NLZM2277E", new String[]{"ETA Date(Through)", "ETA Date(From)"});
            }
        }

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemHeader(NLAL2030BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.trxOrdNum);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.fromLocCd);
        scrnMsg.addCheckItem(scrnMsg.sceOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rwsRefNum);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.whInEtaDt_FR);
        scrnMsg.addCheckItem(scrnMsg.whInEtaDt_TO);
        scrnMsg.addCheckItem(scrnMsg.imptInvBolNum);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.rwsNum);
        scrnMsg.addCheckItem(scrnMsg.rwsStsCd);
        scrnMsg.addCheckItem(scrnMsg.flipMdseCd);
        scrnMsg.addCheckItem(scrnMsg.whCd);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemOrder(NLAL2030BMsg scrnMsg) {

        // Create RWS
        scrnMsg.addCheckItem(scrnMsg.rwsRefNum_AP);
        scrnMsg.addCheckItem(scrnMsg.imptInvBolNum_AP);
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemOrderCheckBox
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemOrderCheckBox(NLAL2030BMsg scrnMsg) {

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemRws(NLAL2030BMsg scrnMsg) {

        // Detail
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NLAL2030Constant.BIZ_ID);

        if (funcList == null || funcList.isEmpty()) {

            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains(NLAL2030Constant.FUNC_ID_UPD)) {

            return true;
        }

        return false;
    }
}
