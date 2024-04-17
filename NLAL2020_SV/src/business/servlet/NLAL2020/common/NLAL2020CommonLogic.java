/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLAL2020.NLAL2020BMsg;
import business.servlet.NLAL2020.NLAL2020_ABMsg;
import business.servlet.NLAL2020.NLAL2020_ABMsgArray;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLAL2020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 05/19/2016   CSAI            Y.Imazu         Update          QC#8471
 * 05/23/2016   CSAI            Y.Imazu         Update          QC#8530
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7981
 * 09/27/2016   CITS            K.Ogino         Update          QC#12919
 * 04/13/2016   CITS            T.Tokutomi      Update          Merge DS
 * 06/28/2017   CITS            M.Naito         Update          QC#19621
 * 12/14/2018   CITS            T.Tokutomi      Update          QC#29283
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 08/31/2023   Hitachi         TZ.Win          Update          QC#61792
 *</pre>
 */
public class NLAL2020CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // Common
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_SAV[0], NLAL2020Constant.BTN_CMN_SAV[1], NLAL2020Constant.BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_SUB[0], NLAL2020Constant.BTN_CMN_SUB[1], NLAL2020Constant.BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_APL[0], NLAL2020Constant.BTN_CMN_APL[1], NLAL2020Constant.BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_APR[0], NLAL2020Constant.BTN_CMN_APR[1], NLAL2020Constant.BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_RJT[0], NLAL2020Constant.BTN_CMN_RJT[1], NLAL2020Constant.BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_DWL[0], NLAL2020Constant.BTN_CMN_DWL[1], NLAL2020Constant.BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_DEL[0], NLAL2020Constant.BTN_CMN_DEL[1], NLAL2020Constant.BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_CLR[0], NLAL2020Constant.BTN_CMN_CLR[1], NLAL2020Constant.BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_RST[0], NLAL2020Constant.BTN_CMN_RST[1], NLAL2020Constant.BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_CMN_RTN[0], NLAL2020Constant.BTN_CMN_RTN[1], NLAL2020Constant.BTN_CMN_RTN[2], 1, null);

        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        handler.setButtonConfirmMsgEx("Receive", null, null, 1);
        // END 2023/08/31 TZ.Win [QC#61792,ADD]

        // Detail Bottom
        handler.setButtonProperties(NLAL2020Constant.BTN_MTR_READ[0], NLAL2020Constant.BTN_MTR_READ[1], NLAL2020Constant.BTN_MTR_READ[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_RCV[0], NLAL2020Constant.BTN_RCV[1], NLAL2020Constant.BTN_RCV[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_RWS_CLS[0], NLAL2020Constant.BTN_RWS_CLS[1], NLAL2020Constant.BTN_RWS_CLS[2], 0, null);

        // Detail Header
        handler.setButtonProperties(NLAL2020Constant.BTN_SELECT_ALL[0], NLAL2020Constant.BTN_SELECT_ALL[1], NLAL2020Constant.BTN_SELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLAL2020Constant.BTN_UNSELECT_ALL[0], NLAL2020Constant.BTN_UNSELECT_ALL[1], NLAL2020Constant.BTN_UNSELECT_ALL[2], 0, null);
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
     * @param scrnMsg     NLAL2020BMsg
     * @param scrnAMsgAry NLAL2020_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NLAL2020BMsg scrnMsg, NLAL2020_ABMsgArray scrnAMsgAry, String tblId) {

        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NLAL2020BMsg
     * @param scrnAMsgAry NLAL2020_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NLAL2020BMsg scrnMsg, NLAL2020_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(NLAL2020Constant.SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NLAL2020BMsg
     * @param scrnAMsgAry NLAL2020_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NLAL2020BMsg scrnMsg, NLAL2020_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(NLAL2020Constant.SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NLAL2020BMsg
     */
    public static void addCheckItemHeader(NLAL2020BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.rwsRefNum_H);
        scrnMsg.addCheckItem(scrnMsg.rwsNum_H);
        scrnMsg.addCheckItem(scrnMsg.bolNum_H);
        scrnMsg.addCheckItem(scrnMsg.rwsStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.srcOrdNum_H);
        scrnMsg.addCheckItem(scrnMsg.sceOrdTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_H);
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        scrnMsg.addCheckItem(scrnMsg.shipToRtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.shipLocCd_H);
        scrnMsg.addCheckItem(scrnMsg.whInEtaDt_FR);
        scrnMsg.addCheckItem(scrnMsg.whInEtaDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CL);
        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_WW);
        // END 2023/08/31 TZ.Win [QC#61792,ADD]
    }

    /**
     * inputCheckHeader
     * @param scrnMsg NLAL2020BMsg
     */
    public static void inputCheckHeader(NLAL2020BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.rwsRefNum_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.rwsNum_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bolNum_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.srcOrdNum_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.serNum_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToRtlWhCd_H) //
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H) 
                && !ZYPCommonFunc.hasValue(scrnMsg.shipLocCd_H)) {

            scrnMsg.rwsRefNum_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.bolNum_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.rwsNum_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.srcOrdNum_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.svcConfigMstrPk_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.serNum_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.shipToRtlWhCd_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.rtlWhCd_H.setErrorInfo(1, "NLZM2276E");
            scrnMsg.shipLocCd_H.setErrorInfo(1, "NLZM2276E");
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.whInEtaDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.whInEtaDt_TO)) {

            if (0 < scrnMsg.whInEtaDt_FR.getValue().compareTo(scrnMsg.whInEtaDt_TO.getValue())) {

                scrnMsg.whInEtaDt_FR.setErrorInfo(1, "NLZM2277E", new String[]{"ETA Date(Through)", "ETA Date(From)"});
                scrnMsg.whInEtaDt_TO.setErrorInfo(1, "NLZM2277E", new String[]{"ETA Date(Through)", "ETA Date(From)"});
            }
        }

        addCheckItemHeader(scrnMsg);
    }

    /**
     * chkRwsBalQtyForSerEntry
     * @param scrnMsgALine NLAL2020_ABMsg
     */
    public static void chkRwsBalQtyForSerEntry(NLAL2020_ABMsg scrnMsgALine) {

        if (!ZYPCommonFunc.hasValue(scrnMsgALine.poBalQty_A1)) {

            scrnMsgALine.poBalQty_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Receiving Qty" });

        } else if (0 == scrnMsgALine.poBalQty_A1.getValueInt()) {

            scrnMsgALine.poBalQty_A1.setErrorInfo(1, "NLZM2277E", new String[] {"Receiving Qty", "0" });

        } else if (scrnMsgALine.poBalQty_A2.getValueInt() < scrnMsgALine.poBalQty_A1.getValueInt()) {

            scrnMsgALine.poBalQty_A1.setErrorInfo(1, "NLZM2316E", new String[] {"Receiving Qty", scrnMsgALine.poBalQty_A2.getValue().toString() });
        }
    }

    /**
     * addCheckItemLine
     * @param scrnMsg NLAL2020BMsg
     */
    public static void addCheckItemLine(NLAL2020BMsg scrnMsg) {

        S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum,
                scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NLAL2020_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A1);
            scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A2);
            scrnMsg.addCheckItem(scrnLineMsg.rtlWhCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.invtyStkStsCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.poBalQty_A1);
            scrnMsg.addCheckItem(scrnLineMsg.serNum_A1);
        }
    }

    /**
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL2020BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NLAL2020BMsg scrnMsg) {

        boolean hasUpdateFuncId = hasUpdateFuncId(scrnMsg);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NLAL2020_ABMsg scrnMsgLine = scrnMsg.A.no(i);

            //QC#29283 Update.
            if (isLineUpdate(scrnMsgLine)) {
                scrnMsgLine.xxChkBox_A1.setInputProtected(false);
                scrnMsgLine.xxChkBox_A2.setInputProtected(false);

                scrnMsgLine.rwsStsDescTxt_A1.setInputProtected(true);
                scrnMsgLine.shipToRtlWhCd_A1.setInputProtected(true);
                scrnMsgLine.rtlWhNm_A1.setInputProtected(true);
                scrnMsgLine.origMdseCd_A1.setInputProtected(true);
                scrnMsgLine.flipMdseCd_A1.setInputProtected(true);
                scrnMsgLine.mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsgLine.rwsQty_A1.setInputProtected(true);
                scrnMsgLine.rwsStkQty_A1.setInputProtected(true);
                scrnMsgLine.rtlWhCd_A1.setInputProtected(true);
                scrnMsgLine.rmaSlsWhNm_A1.setInputProtected(true);
                scrnMsgLine.invtyStkStsCd_A1.setInputProtected(true);
                scrnMsgLine.poBalQty_A1.setInputProtected(true);
                scrnMsgLine.serNum_A1.setInputProtected(true);
                scrnMsgLine.svcConfigMstrPk_A1.setInputProtected(true);
                scrnMsgLine.rtlSwhCd_A1.setInputProtected(true);
                scrnMsgLine.shipLocCd_A1.setInputProtected(true);
                scrnMsgLine.shipFromAcctNm_A1.setInputProtected(true);
                scrnMsgLine.fromLocCtyAddr_A1.setInputProtected(true);
                scrnMsgLine.rwsRefNum_A1.setInputProtected(true);
                scrnMsgLine.imptInvBolNum_A1.setInputProtected(true);
                scrnMsgLine.sceOrdTpNm_A1.setInputProtected(true);
                scrnMsgLine.srcOrdNum_A1.setInputProtected(true);
                scrnMsgLine.svcLvlDescTxt_A1.setInputProtected(true);
                scrnMsgLine.carrNm_A1.setInputProtected(true);

                // Serial Button
                handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, false);

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.serNumTakeFlg_A1.getValue())) {

                    if (ZYPCommonFunc.hasValue(scrnMsgLine.poBalQty_A1) && 1 < scrnMsgLine.poBalQty_A1.getValueInt()) {

                        handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, true);

                    } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.rwsOpenFlg_A1.getValue()) && ZYPCommonFunc.hasValue(scrnMsgLine.rwsStkQty_A1) && 1 < scrnMsgLine.rwsStkQty_A1.getValueInt()) {

                        handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, true);

                    }
                }

                // Received WH
                handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY[0], i, false);
                handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY_NAME[0], i, false);

                // RWS Header Close or Not have the permission for
                // update
                // QC#19621
                if (!hasUpdateFuncId) {

                    scrnMsgLine.xxChkBox_A1.setInputProtected(true);
                    scrnMsgLine.xxChkBox_A2.setInputProtected(true);
                    scrnMsgLine.xxChkBox_A1.clear();
                    scrnMsgLine.xxChkBox_A2.clear();
                    continue;

                }

                // Stock Status
                scrnMsgLine.invtyStkStsCd_A1.setInputProtected(false);

                // Qty
                scrnMsgLine.poBalQty_A1.setInputProtected(false);

                if (ZYPCommonFunc.hasValue(scrnMsgLine.poBalQty_A2) && 1 == scrnMsgLine.poBalQty_A2.getValueInt()) {

                    scrnMsgLine.poBalQty_A1.setInputProtected(true);
                }

                // Serial#
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.serNumTakeFlg_A1.getValue()) //
                        && ZYPCommonFunc.hasValue(scrnMsgLine.poBalQty_A1) && 1 == scrnMsgLine.poBalQty_A1.getValueInt()) {

                    scrnMsgLine.serNum_A1.setInputProtected(false);

                    if (ZYPConstant.FLG_OFF_N.equals(scrnMsgLine.xxEdtModeFlg_A1.getValue())) {
                        scrnMsgLine.serNum_A1.setInputProtected(true);
                    }
                }

                // Not allowed partial line receive
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.xxPutAwayFlg_A1.getValue())) {

                    scrnMsgLine.invtyStkStsCd_A1.setInputProtected(true);
                    scrnMsgLine.poBalQty_A1.setInputProtected(true);
                    scrnMsgLine.xxChkBox_A2.setInputProtected(true);
                }

                // QC#55313
                if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(scrnMsgLine.sceOrdTpCd_A1.getValue())) {
                    scrnMsgLine.invtyStkStsCd_A1.setInputProtected(true);
                }

                // Tech
                if (LOC_TP.TECHNICIAN.equals(scrnMsgLine.toLocTpCd_A1.getValue())) {

                    scrnMsgLine.invtyStkStsCd_A1.setInputProtected(true);
                    scrnMsgLine.rtlWhCd_A1.setInputProtected(false);
                    handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY[0], i, true);
                    handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY_NAME[0], i, true);
                }
            } else {
                // Line Full protect
                scrnMsgLine.setInputProtected(true);

                scrnMsgLine.xxChkBox_A1.setInputProtected(false);

                // Serial Button
                handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, false);

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.serNumTakeFlg_A1.getValue())) {

                    if (ZYPCommonFunc.hasValue(scrnMsgLine.poBalQty_A1) && 1 < scrnMsgLine.poBalQty_A1.getValueInt()) {

                        handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, true);

                    } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.rwsOpenFlg_A1.getValue()) && ZYPCommonFunc.hasValue(scrnMsgLine.rwsStkQty_A1) && 1 < scrnMsgLine.rwsStkQty_A1.getValueInt()) {

                        handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, true);

                    }
                }

                // Received WH
                handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY[0], i, false);
                handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY_NAME[0], i, false);
            }
        }

        // Detail Bottom
        if (0 < scrnMsg.A.getValidCount() && hasUpdateFuncId) {

            handler.setButtonProperties(NLAL2020Constant.BTN_MTR_READ[0], NLAL2020Constant.BTN_MTR_READ[1], NLAL2020Constant.BTN_MTR_READ[2], 1, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_RCV[0], NLAL2020Constant.BTN_RCV[1], NLAL2020Constant.BTN_RCV[2], 1, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_RWS_CLS[0], NLAL2020Constant.BTN_RWS_CLS[1], NLAL2020Constant.BTN_RWS_CLS[2], 1, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_SELECT_ALL[0], NLAL2020Constant.BTN_SELECT_ALL[1], NLAL2020Constant.BTN_SELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_UNSELECT_ALL[0], NLAL2020Constant.BTN_UNSELECT_ALL[1], NLAL2020Constant.BTN_UNSELECT_ALL[2], 1, null);
        }

        scrnMsg.rtlWhNm_H.setInputProtected(true);
        scrnMsg.rmaSlsWhNm_H.setInputProtected(true);
        scrnMsg.dsAcctNm_H.setInputProtected(true);

        setVisibility(handler, scrnMsg);
    }

    /**
     * isLineUpdate
     * @param scrnMsgLine NLAL2020_ABMsg
     * @return true(update Ok)
     */
    private static boolean isLineUpdate(NLAL2020_ABMsg scrnMsgLine) {

        if(ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.rwsOpenFlg_A1.getValue())){
            if(BigDecimal.ZERO.compareTo(scrnMsgLine.poBalQty_A1.getValue()) == 0 //
                    ){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     *  setVisibilityDeliveries
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL2020BMsg
     */
    public static void setVisibility(EZDCommonHandler handler, NLAL2020BMsg scrnMsg) {

        String preRwsNum = "";

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            // Clear
            scrnMsg.clearGUIAttribute(NLAL2020Constant.SCRN_ID_00, "OnChange_ChkBoxRMANum" + i);
            scrnMsg.clearGUIAttribute(NLAL2020Constant.SCRN_ID_00, "rwsNum_A1" + i);

            if (i >= scrnMsg.A.getValidCount()) {

                continue;
            }

            // Set Visibility
            NLAL2020_ABMsg scrnMsgLine = scrnMsg.A.no(i);

            if (preRwsNum.equals(scrnMsgLine.rwsNum_A1.getValue())) {

                // Check Box
                EZDGUIAttribute chkBoxRws = new EZDGUIAttribute(NLAL2020Constant.SCRN_ID_00, "OnChange_ChkBoxRMANum" + i);
                chkBoxRws.setVisibility(false);
                scrnMsg.addGUIAttribute(chkBoxRws);

                // RWS Number
                EZDGUIAttribute rwsNum = new EZDGUIAttribute(NLAL2020Constant.SCRN_ID_00, "rwsNum_A1" + i);
                rwsNum.setVisibility(false);
                scrnMsg.addGUIAttribute(rwsNum);
            }

            preRwsNum = scrnMsgLine.rwsNum_A1.getValue();
        }
    }

    /**
     * hasUpdateFuncId
     * @param scrnMsg NLAL2020BMsg
     * @return boolean
     */
    public static boolean hasUpdateFuncId(NLAL2020BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NLAL2020Constant.BIZ_ID);

        if (funcList == null || funcList.isEmpty()) {

            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains(NLAL2020Constant.FUNC_ID_UPDATE)) {

            return true;
        }

        return false;
    }

    /**
     * controlWarning
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL2020BMsg
     * @param userId String
     */
    public static final void controlWarning(S21UserProfileService userProfileService, EZDCommonHandler handler, NLAL2020BMsg scrnMsg, String userId) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg.getValue())) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                NLAL2020_ABMsg scrnMsgLine = scrnMsg.A.no(i);
                scrnMsgLine.rtlWhCd_A1.setInputProtected(true);
                scrnMsgLine.invtyStkStsCd_A1.setInputProtected(true);
                scrnMsgLine.poBalQty_A1.setInputProtected(true);
                scrnMsgLine.serNum_A1.setInputProtected(true);
                handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY[0], i, false);
                handler.setButtonEnabled(NLAL2020Constant.BTN_RCV_BY_NAME[0], i, false);
                handler.setButtonEnabled(NLAL2020Constant.BTN_SER_NUM[0], i, false);
            }

            // START 2023/08/31 TZ.Win [QC#61792,ADD]
            if(scrnMsg.getMessageCode().equals("NLAM1356W")) {
                handler.setButtonConfirmMsgEx("Receive", "NLAM1356W", null, 1);
            }
            // END 2023/08/31 TZ.Win [QC#61792,ADD]

            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_SAV[0], NLAL2020Constant.BTN_CMN_SAV[1], NLAL2020Constant.BTN_CMN_SAV[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_SUB[0], NLAL2020Constant.BTN_CMN_SUB[1], NLAL2020Constant.BTN_CMN_SUB[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_APL[0], NLAL2020Constant.BTN_CMN_APL[1], NLAL2020Constant.BTN_CMN_APL[2], 1, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_APR[0], NLAL2020Constant.BTN_CMN_APR[1], NLAL2020Constant.BTN_CMN_APR[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_RJT[0], NLAL2020Constant.BTN_CMN_RJT[1], NLAL2020Constant.BTN_CMN_RJT[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_DWL[0], NLAL2020Constant.BTN_CMN_DWL[1], NLAL2020Constant.BTN_CMN_DWL[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_DEL[0], NLAL2020Constant.BTN_CMN_DEL[1], NLAL2020Constant.BTN_CMN_DEL[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_CLR[0], NLAL2020Constant.BTN_CMN_CLR[1], NLAL2020Constant.BTN_CMN_CLR[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_RST[0], NLAL2020Constant.BTN_CMN_RST[1], NLAL2020Constant.BTN_CMN_RST[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_CMN_RTN[0], NLAL2020Constant.BTN_CMN_RTN[1], NLAL2020Constant.BTN_CMN_RTN[2], 0, null);

            handler.setButtonProperties(NLAL2020Constant.BTN_MTR_READ[0], NLAL2020Constant.BTN_MTR_READ[1], NLAL2020Constant.BTN_MTR_READ[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_RCV[0], NLAL2020Constant.BTN_RCV[1], NLAL2020Constant.BTN_RCV[2], 1, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_RWS_CLS[0], NLAL2020Constant.BTN_RWS_CLS[1], NLAL2020Constant.BTN_RWS_CLS[2], 0, null);

            handler.setButtonProperties(NLAL2020Constant.BTN_SELECT_ALL[0], NLAL2020Constant.BTN_SELECT_ALL[1], NLAL2020Constant.BTN_SELECT_ALL[2], 0, null);
            handler.setButtonProperties(NLAL2020Constant.BTN_UNSELECT_ALL[0], NLAL2020Constant.BTN_UNSELECT_ALL[1], NLAL2020Constant.BTN_UNSELECT_ALL[2], 0, null);
        }
    }


    /**
     * The method explanation: Clear GUI Attribute.
     * @param scrnMsg NLAL2020BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NLAL2020BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(NLAL2020Constant.SCRN_ID_00, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NLAL2020Constant.SCRN_ID_00);
    }
}
