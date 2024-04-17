/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270.common;

import static business.servlet.NPAL1270.constant.NPAL1270Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1270.NPAL1270BMsg;
import business.servlet.NPAL1270.NPAL1270_QBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1270 PO Requisition List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   CITS            R Shimamoto     Create          N/A
 * 02/28/2016   CITS            K.Ogino         Update          QC#4610
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 01/29/2018   CITS            T.Gotoda        Update          QC#22521
 * 06/26/2018   CITS            Y.Iwasaki       Update          QC#26548
 * 12/10/2018   Fujitsu         S.Ohki          Update          QC#29523
 * 01/18/2019   Fujitsu         S.Ohki          Update          QC#29973
 * 01/31/2019   CITS            K.Ogino         Update          QC#29963
 * 09/20/2019   CITS            R.Shimamoto     Update          QC#52362
 * 02/01/2023   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1270CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1270BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1270BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);
        scrnMsg.srchOptPk_SL.setInputProtected(false);
        scrnMsg.srchOptUsrId_U1.setInputProtected(false);

        // Search Condition
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsCd_SL.setInputProtected(false);
        scrnMsg.prchReqApvlStsCd_SL.setInputProtected(false);
        scrnMsg.prchReqTpCd_PD.setInputProtected(false);
        scrnMsg.prchReqStsCd_PD.setInputProtected(false);
        scrnMsg.prchReqApvlStsCd_PD.setInputProtected(false);
        scrnMsg.prchReqCratDt_FR.setInputProtected(false);
        scrnMsg.prchReqCratDt_TO.setInputProtected(false);
        scrnMsg.prchReqSrcTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqSrcTpCd_PD.setInputProtected(false);
        scrnMsg.trxRefNum.setInputProtected(false);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(false);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.prchReqCratByPsnCd.setInputProtected(false);
        scrnMsg.prchGrpCd_SL.setInputProtected(false);
        scrnMsg.prchGrpCd_PD.setInputProtected(false);
        scrnMsg.shipToCustCd.setInputProtected(false);
        scrnMsg.rqstRcvDt_FR.setInputProtected(false);
        scrnMsg.rqstRcvDt_TO.setInputProtected(false);
        // START 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.rqstShipDt_FR.setInputProtected(false);
        scrnMsg.rqstShipDt_TO.setInputProtected(false);
        // END 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_PD.setInputProtected(false);
        scrnMsg.carrCd.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        scrnMsg.prntVndNm.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.vndNm.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(false);
        scrnMsg.destRtlSwhCd.setInputProtected(false);
        scrnMsg.shipToCustCd.setInputProtected(false);
        scrnMsg.poOrdNum.setInputProtected(false);
        scrnMsg.coaMdseTpCd.setInputProtected(false);
        scrnMsg.coaProjDescTxt.setInputProtected(true);
        scrnMsg.coaProdCd.setInputProtected(false);
        scrnMsg.coaProdNm.setInputProtected(true);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SET_SUPPLIER_NAME, true);
        handler.setButtonEnabled(BTN_SET_ITEM_DESCRIPTION, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_NEW, true);


        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1270BMsg
     */
    public static void setTableScreen(NPAL1270BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prchReqNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqApvlStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqCratDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rqstRcvTm_A1.setInputProtected(true);
            // START 2023/02/01 S.Dong [QC#60966, ADD]
            scrnMsg.A.no(i).rqstShipDt_A1.setInputProtected(true);
            // END 2023/02/01 S.Dong [QC#60966, ADD]
            scrnMsg.A.no(i).prchReqSrcTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).trxRefNum_A1.setInputProtected(true);
            //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            scrnMsg.A.no(i).mrpPlnNm_A1.setInputProtected(true);
            //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            scrnMsg.A.no(i).prchReqCratByPsnCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchGrpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prntVndCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).prntVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).destRtlWhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).poOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            //01/18/2019 S.Ohki Mod QC#29973 START
//            scrnMsg.A.no(i).prchReqQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
            //01/18/2019 S.Ohki Mod QC#29973 END

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqRelDtTmTs_A1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxDtTm_A2, ZYPDateUtil.formatEzd8ToDisp(scrnMsg.A.no(i).prchReqRelDtTmTs_A1.getValue(), true));
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rqstRcvTm_A1)) {
                String rqstRcvTm = scrnMsg.A.no(i).rqstRcvTm_A1.getValue();
                String hh = rqstRcvTm.substring(0, 2);
                String mi = rqstRcvTm.substring(2, 4);
                int hhInt = Integer.parseInt(hh);
                String xxDtTm = "";
                if (hhInt > 12) {
                    if (hhInt >= 22) {
                        xxDtTm = (hhInt - 12) + ":" + mi + " PM";
                    } else {
                        xxDtTm = "0" + (hhInt - 12) + ":" + mi + " PM";
                    }
                } else {
                    if (hhInt >= 10) {
                        xxDtTm = hhInt + ":" + mi + " AM";
                    } else {
                        xxDtTm = "0" + (hhInt) + ":" + mi + " AM";
                    }
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxDtTm_A1, xxDtTm);
            }

        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1270BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1270BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_NEW, true);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NPAL1270BMsg scrnMsg) {
        // QC#10621 update start
        scrnMsg.srchOptNm_TX.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);
        scrnMsg.prchReqNum.setNameForMessage(DISPLAY_NM_REQ_NUM);
        scrnMsg.prchReqTpCd_SL.setNameForMessage(DISPLAY_NM_REQ_TP_CD);
        scrnMsg.prchReqStsCd_SL.setNameForMessage(DISPLAY_NM_REQ_STS_CD);
        scrnMsg.prchReqCratDt_FR.setNameForMessage(DISPLAY_NM_REQ_CRAT_DT_FR);
        scrnMsg.prchReqCratDt_TO.setNameForMessage(DISPLAY_NM_REQ_CRAT_DT_TO);
        scrnMsg.prchReqSrcTpCd_SL.setNameForMessage(DISPLAY_NM_REQ_SRC_TP_CD);
        scrnMsg.trxRefNum.setNameForMessage(DISPLAY_NM_TRX_REF_NUM);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setNameForMessage(DISPLAY_NM_MRP_PLN_NM);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.prchReqCratByPsnCd.setNameForMessage(DISPLAY_NM_REQ_CRAT_BY_PSN_CD);
        scrnMsg.prchGrpCd_SL.setNameForMessage(DISPLAY_NM_GRP_CD);
        scrnMsg.shipToCustCd.setNameForMessage(DISPLAY_NM_SHIP_TO_CUST_CD);
        scrnMsg.rqstRcvDt_FR.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_FR);
        scrnMsg.rqstRcvDt_TO.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_TO);
        // START 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.rqstShipDt_FR.setNameForMessage(DISPLAY_NM_RQST_SHIP_DT_FR);
        scrnMsg.rqstShipDt_TO.setNameForMessage(DISPLAY_NM_RQST_SHIP_DT_TO);
        // END 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.prchReqLineStsCd_SL.setNameForMessage(DISPLAY_NM_REQ_LINE_STS_CD);
        scrnMsg.shpgSvcLvlCd_SL.setNameForMessage(DISPLAY_NM_SHPG_SVC_LVL_CD);
        scrnMsg.carrCd.setNameForMessage(DISPLAY_NM_CARR_CD);
        scrnMsg.prntVndCd.setNameForMessage(DISPLAY_NM_PRNT_VND_CD);
        scrnMsg.vndCd.setNameForMessage(DISPLAY_NM_VND_CD);
        scrnMsg.destRtlWhCd.setNameForMessage(DISPLAY_NM_DEST_RTL_WH_CD);
        scrnMsg.destRtlSwhCd.setNameForMessage(DISPLAY_NM_DEST_RTL_SWH_CD);
        scrnMsg.poOrdNum.setNameForMessage(DISPLAY_NM_PO_ORD_NUM);
        scrnMsg.prchReqApvlStsCd_SL.setNameForMessage(DISPLAY_NM_REQ_APVL_STS_CD);
        scrnMsg.fullPsnNm.setNameForMessage(DISPLAY_NM_REQ_CRAT_BY_PSN_CD);
        scrnMsg.carrNm.setNameForMessage(DISPLAY_NM_CARR_CD);
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_NM_ITEM_NUMBER);
        scrnMsg.prntVndNm.setNameForMessage(DISPLAY_NM_PRNT_VND_NM);
        scrnMsg.vndNm.setNameForMessage(DISPLAY_NM_VND_NM);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_DEST_RTL_WH_NM);
        scrnMsg.rtlSwhNm.setNameForMessage(DISPLAY_NM_DEST_RTL_SWH_NM);
        scrnMsg.shipToLocNm.setNameForMessage(DISPLAY_NM_SHIP_TO_CUST_NM);
        // QC#10621 update end
        scrnMsg.coaMdseTpCd.setNameForMessage(DISPLAY_NM_COA_MDSE_TP_CD);
        scrnMsg.coaProdCd.setNameForMessage(DISPLAY_NM_COA_PROD_CD);
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     */
    public static void setInitParamForItemPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();

        // warehouse Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_MDSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_MDSE);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     * @return Object[]
     */
    public static Object[] setParamForItemPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, ALL_ITEM);

        int paramCount = 0;
        Object[] params = new Object[25];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * To Array for Popup params
     * @param q NPAL1270_QBMsgArray
     * @param size Array Size
     * @return Object[]
     */
    public static Object[] toArray_popup(NPAL1270_QBMsgArray q, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = q.no(i).xxPopPrm;
        }
        return param;
    }

    /**
     * The method explanation: set parameter to call Merchandise Type popup.
     * @param scrnMsg NPAL1270BMsg
     * @return Object[]
     */
    public static Object[] setParamForMdseTpPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_MerchandiseType");

        ZYPTableUtil.clear(scrnMsg.Q);
        scrnMsg.Q.no(0).xxPopPrm.setValue("COA_PROJ");
        scrnMsg.Q.no(1).xxPopPrm.setValue("COA_PROJ_CD");
        scrnMsg.Q.no(2).xxPopPrm.setValue("COA_PROJ_NM");
        scrnMsg.Q.no(3).xxPopPrm.setValue("COA_PROJ_CD");
        scrnMsg.Q.no(4).xxPopPrm.setValue("Look Up Merchandise Type");
        scrnMsg.Q.no(5).xxPopPrm.setValue("Merchandise Type");
        scrnMsg.Q.no(6).xxPopPrm.setValue("Description");
        scrnMsg.Q.no(7).xxPopPrm.setValue("Merchandise Type");
        scrnMsg.Q.no(8).xxPopPrm.setValue("Description");
        scrnMsg.Q.no(9).xxPopPrm.setValue(scrnMsg.coaMdseTpCd.getValue());
        scrnMsg.Q.no(10).xxPopPrm.setValue(scrnMsg.coaProjDescTxt.getValue());
        scrnMsg.Q.no(29).xxPopPrm.setValue("NPAL1270Scrn00_OpenWin_MerchandiseType");
        Object[] params = toArray_popup(scrnMsg.Q, 11);

        return params;
    }

    /**
     * The method explanation: set parameter to call Product Code popup.
     * @param scrnMsg NPAL1270BMsg
     * @return Object[]
     */
    public static Object[] setParamForProdCdPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_ProductCode");

        ZYPTableUtil.clear(scrnMsg.Q);
        scrnMsg.Q.no(0).xxPopPrm.setValue("COA_PROD");
        scrnMsg.Q.no(1).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.Q.no(2).xxPopPrm.setValue("COA_PROD_NM");
        scrnMsg.Q.no(3).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.Q.no(4).xxPopPrm.setValue("Look Up Product Code");
        scrnMsg.Q.no(5).xxPopPrm.setValue("Product Code");
        scrnMsg.Q.no(6).xxPopPrm.setValue("Product Name");
        scrnMsg.Q.no(7).xxPopPrm.setValue("Product Code");
        scrnMsg.Q.no(8).xxPopPrm.setValue("Product Name");
        scrnMsg.Q.no(9).xxPopPrm.setValue(scrnMsg.coaProdCd.getValue());
        scrnMsg.Q.no(10).xxPopPrm.setValue(scrnMsg.coaProdNm.getValue());
        scrnMsg.Q.no(29).xxPopPrm.setValue("NPAL1270Scrn00_OpenWin_ProductCode");
        Object[] params =toArray_popup(scrnMsg.Q, 11);

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NPAL1270BMsg scrnMsg) {

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
        scrnMsg.xxScrEventNm_P1.clear();

        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Carrier");

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_CARR);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1,
        // scrnMsg.carrCd_AC);
        if (ZYPCommonFunc.hasValue(scrnMsg.carrNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondNm_P1, LIKE_SEARCH_CHAR + scrnMsg.carrNm.getValue() + LIKE_SEARCH_CHAR);
        }

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.xxTblNm_P1;
        params[paramCount++] = scrnMsg.xxTblCdColNm_P1;
        params[paramCount++] = scrnMsg.xxTblNmColNm_P1;
        params[paramCount++] = scrnMsg.xxTblSortColNm_P1;
        params[paramCount++] = scrnMsg.xxScrNm_P1;
        params[paramCount++] = scrnMsg.xxHdrCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxHdrNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxCondCd_P1;
        params[paramCount++] = scrnMsg.xxCondNm_P1;


        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     * @param String glblCmpyCd
     * @return Object[]
     */
    public static Object[] setParamForRequesterPopup(NPAL1270BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.P.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Requester");

        // QC:11661 START
        // QC#13136 MOD START
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Buyer Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("   AP.USR_NM ");
        // START 2018/12/10 S.Ohki [QC#29523,MOD]
//        sb.append("  ,AP.FIRST_NM || (NVL2(AP.MID_NM, ' '||AP.MID_NM||' ', ' ')) || AP.LAST_NM FULL_PSN_NM ");
        sb.append("  ,AP.FIRST_NM || ' ' || AP.LAST_NM FULL_PSN_NM ");
        // END 2018/12/10 S.Ohki [QC#29523,MOD]
        sb.append("  ,AP.EZCANCELFLAG ");
        sb.append("  ,AP.GLBL_CMPY_CD ");
        sb.append("FROM ");
        sb.append("   AUTH_PSN AP ");
        sb.append("WHERE");
        sb.append("    AP.GLBL_CMPY_CD   = " + "'" + glblCmpyCd + "'");
        sb.append(" AND AP.EZCANCELFLAG  = '0'");
        //QC:11661
        sb.append("  UNION");
        sb.append(" SELECT");
        sb.append("   S.PSN_CD AS USR_NM ");
        sb.append("  ,S.FULL_PSN_NM ");
        sb.append("  ,S.EZCANCELFLAG ");
        sb.append("  ,S.GLBL_CMPY_CD ");
        sb.append("FROM ");
        sb.append("   SCE_SYS_USR_LIST S ");
        sb.append("WHERE");
        sb.append("    S.GLBL_CMPY_CD   = " + "'" + glblCmpyCd + "'");
        sb.append(" AND S.EZCANCELFLAG  = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Person Code";
        whereArray1[IDX_1] = "USR_NM";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Person Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm)) {
            whereArray2[IDX_2] = "%" + scrnMsg.fullPsnNm.getValue() + "%";
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Person Code";
        columnArray1[IDX_1] = "USR_NM";
        columnArray1[IDX_2] = new BigDecimal(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Person Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = new BigDecimal(IDX_62);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "USR_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
        // QC#13136 MOD END
        // QC:11661 END
        
//        Object[] params = new Object[7];
//
//        params[0] = "";
//        params[1] = SCR_NM_FOR_BUYER;
//        params[2] = TBL_NM_FOR_BUYER;
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] whereArray1 = new Object[4];
//        whereArray1[0] = HDR_CD_LB_NM_FOR_BUYER;
//        whereArray1[1] = TBL_CD_COL_NM_FOR_BUYER;
//        whereArray1[2] = "";
//        whereArray1[3] = ZYPConstant.FLG_OFF_N;
//        whereList.add(whereArray1);
//
//        Object[] whereArray2 = new Object[4];
//        whereArray2[0] = HDR_NM_LB_NM_FOR_BUYER;
//        whereArray2[1] = TBL_NM_COL_NM_FOR_BUYER;
//        whereArray2[2] = scrnMsg.fullPsnNm.getValue();
//        whereArray2[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray2);
//
//        params[3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//
//        Object[] columnArray1 = new Object[4];
//        columnArray1[0] = DTL_CD_LB_NM_FOR_BUYER;
//        columnArray1[1] = TBL_CD_COL_NM_FOR_BUYER;
//        columnArray1[2] = BigDecimal.valueOf(20);
//        columnArray1[3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray1);
//
//        Object[] columnArray2 = new Object[4];
//        columnArray2[0] = DTL_NM_LB_NM_FOR_BUYER;
//        columnArray2[1] = TBL_NM_COL_NM_FOR_BUYER;
//        columnArray2[2] = BigDecimal.valueOf(60);
//        columnArray2[3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray2);
//
//        params[4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//
//        Object[] sortConditionArray1 = new Object[2];
//        sortConditionArray1[0] = TBL_SORT_COL_NM_FOR_BUYER;
//        sortConditionArray1[1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        params[5] = sortConditionList;
//
//        params[6] = scrnMsg.P;
//
//
//        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     * @return Object[]
     */
    public static Object[] setParamForSupplierPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.P.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Supplier");

        Object[] params = new Object[7];

        params[0] = "";
        params[1] = SCR_NM_FOR_SUPPLIER;
        params[2] = TBL_NM_FOR_SUPPLIER;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = HDR_CD1_LB_NM_FOR_SUPPLIER;
        whereArray1[1] = TBL_CD_COL1_NM_FOR_SUPPLIER;
        whereArray1[2] = scrnMsg.prntVndCd.getValue();
        whereArray1[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = HDR_NM1_LB_NM_FOR_SUPPLIER;
        whereArray2[1] = TBL_NM_COL1_NM_FOR_SUPPLIER;
        whereArray2[2] = scrnMsg.prntVndNm.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = HDR_CD2_LB_NM_FOR_SUPPLIER;
        whereArray3[1] = TBL_CD_COL2_NM_FOR_SUPPLIER;
        whereArray3[2] = scrnMsg.vndCd.getValue();
        whereArray3[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = HDR_NM2_LB_NM_FOR_SUPPLIER;
        whereArray4[1] = TBL_NM_COL2_NM_FOR_SUPPLIER;
        whereArray4[2] = scrnMsg.vndNm.getValue();
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = DTL_CD1_LB_NM_FOR_SUPPLIER;
        columnArray1[1] = TBL_CD_COL1_NM_FOR_SUPPLIER;
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = HDR_NM1_LB_NM_FOR_SUPPLIER;
        columnArray2[1] = TBL_NM_COL1_NM_FOR_SUPPLIER;
        columnArray2[2] = BigDecimal.valueOf(30);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = DTL_CD2_LB_NM_FOR_SUPPLIER;
        columnArray3[1] = TBL_CD_COL2_NM_FOR_SUPPLIER;
        columnArray3[2] = BigDecimal.valueOf(15);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = HDR_NM2_LB_NM_FOR_SUPPLIER;
        columnArray4[1] = TBL_NM_COL2_NM_FOR_SUPPLIER;
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "PRNT_VND_CD, VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;

        params[6] = scrnMsg.P;


        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1270BMsg
     * @return ShipToCustPopup Param (NMAL6760) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();
        scrnMsg.xxPopPrm_PO.clear();
        scrnMsg.xxPopPrm_PP.clear();

        // 2019/09/20 QC#52362 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.shipToLocNm);
        String shipToCustLocNm = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm)) {
        	shipToCustLocNm = scrnMsg.shipToLocNm.getValue();
        	if (shipToCustLocNm.length() == 60) {
        		shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
        	}
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, shipToCustLocNm);
        // 2019/09/20 QC#52362 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PG, scrnMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, SHIP_TO_ONLY_CD);

        int paramCount = 0;
        Object[] params = new Object[26];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;
        params[paramCount++] = scrnMsg.xxPopPrm_PB;
        params[paramCount++] = scrnMsg.xxPopPrm_PC;
        params[paramCount++] = scrnMsg.xxPopPrm_PD;
        params[paramCount++] = scrnMsg.xxPopPrm_PE;
        params[paramCount++] = scrnMsg.xxPopPrm_PF;
        params[paramCount++] = scrnMsg.xxPopPrm_PG;
        params[paramCount++] = scrnMsg.xxPopPrm_PH;
        params[paramCount++] = scrnMsg.xxPopPrm_PI;
        params[paramCount++] = scrnMsg.xxPopPrm_PJ;
        params[paramCount++] = scrnMsg.xxPopPrm_PK;
        params[paramCount++] = scrnMsg.xxPopPrm_PL;
        params[paramCount++] = scrnMsg.xxPopPrm_PM;
        params[paramCount++] = scrnMsg.xxPopPrm_PN;
        params[paramCount++] = scrnMsg.xxPopPrm_PO;
        params[paramCount++] = scrnMsg.xxPopPrm_PP;

        return params;
    }

    /**
     * Set Warehouse Popup param
     * @param scrnMsg NPAL1270BMsg
     * @return Location Popup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForWarehousePopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        // QC#29963
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, LOC_ROLE_TP_CDLIST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.rtlSwhNm);

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     */
    public static void setInitParamForSupplierPopup(NPAL1270BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();

        // vender Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_PRNT_VND);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_PRNT_VND);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1270BMsg
     * @param select int
     * @return Object[]
     */
    public static Object[] setParamForPoReqEntry(NPAL1270BMsg scrnMsg, int select) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_S1, scrnMsg.A.no(select).prchReqNum_A1);

        int paramCount = 0;
        Object[] params = new Object[1];
        params[paramCount++] = scrnMsg.prchReqNum_S1;

        return params;
    }

    /**
     * check Item
     * @param scrnMsg NPAL1270BMsg
     */
    public static void commonAddCheckItem(NPAL1270BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);

    }

    /**
     * clear table
     * @param scrnMsg NPAL1270BMsg
     */
    public static void clearTable(NPAL1270BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }
}
