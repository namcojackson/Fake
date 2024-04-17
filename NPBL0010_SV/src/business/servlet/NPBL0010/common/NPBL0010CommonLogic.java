/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010.common;


import static business.servlet.NPBL0010.constant.NPBL0010Constant.ASC;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BIZ_APP_ID;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_1;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_10;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_2;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_3;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_4;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_5;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_6;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_7;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_8;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_CMN_BTN_9;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_DELETE_SEARCH;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_NEW;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_SAVE_SEARCH;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.BTN_SEARCH;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DECIMAL_POINT_USD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_CARR_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_DEST_RTL_SWH_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_DEST_RTL_WH_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_DPLY_VND_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_DS_ACCT_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_FULL_PSN_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_MRP_PLN_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_PRCH_REQ_CRAT_DT_RF;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_PRCH_REQ_CRAT_DT_RT;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_PRCH_REQ_NUM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_RQST_RCV_DT_NF;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_RQST_RCV_DT_NT;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_RTL_SWH_NM_DS;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_RTL_SWH_NM_SS;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_RTL_WH_NM_DW;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_RTL_WH_NM_SW;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_SHIP_TO_CUST_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_SRC_RTL_SWH_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_TOTAL_COST;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_TOTAL_COST_VR;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_TRX_REF_NUM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_UNIT_PRICE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_UNIT_PRICE_VR;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DISPLAY_NM_VND_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DTL_CD_LB_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DTL_NM_LB_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.FUNCTION_UPDATE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.HDR_CD_LB_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.HDR_NM_LB_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.HTML_ID_ACCOUNT_BUTTON;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.HTML_ID_REQUEST_NUMBER_LINK;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_0;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_1;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_2;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_20;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_3;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_4;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_5;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_6;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_62;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_7;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SCRN_ID;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SCR_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SUP_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SUP_CD_COL;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SUP_SITE_CD;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SUP_SITE_CD_COL;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SUP_SITE_NM;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SUP_SITE_NM_COL;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TABLE_NM_PO_VND_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_CD_COL_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_NM_COL_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_SORT_COL_NM_FOR_OTBD_CARR_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.WIDTH_20;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.WIDTH_30;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPBL0010.NPBL0010BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/13/2016   CITS       Makoto Okigami       Create          N/A
 * 04/05/2016   CITS       K.Ogino              Update          N/A
 * 01/17/2017   CITS       T.Kikuhara           Update          QC#16256
 * 08/10/2017   CITS       H.Naoi               Update          Sol#097(QC#18398)
 * 03/09/2020   Fujitsu    R.Nakamura           Update          QC#55940
 *</pre>
 */
public class NPBL0010CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0010BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPBL0010BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptPk_SL.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);

        // Search Condition
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_PD.setInputProtected(false);
        scrnMsg.prchReqTpDescTxt_PD.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsCd_PD.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt_PD.setInputProtected(false);
        scrnMsg.prchReqStsCd_SL.setInputProtected(false);
        scrnMsg.prchReqApvlStsCd_PD.setInputProtected(false);
        scrnMsg.prchReqApvlStsDescTxt_PD.setInputProtected(false);
        scrnMsg.prchReqApvlStsCd_SL.setInputProtected(false);
        scrnMsg.prchReqCratDt_RF.setInputProtected(false);
        scrnMsg.prchReqCratDt_RT.setInputProtected(false);
        scrnMsg.rqstRcvDt_NF.setInputProtected(false);
        scrnMsg.rqstRcvDt_NT.setInputProtected(false);
        scrnMsg.prchReqSrcTpCd_PD.setInputProtected(false);
        scrnMsg.prchReqSrcTpDescTxt_PD.setInputProtected(false);
        scrnMsg.prchReqSrcTpCd_SL.setInputProtected(false);
        scrnMsg.trxRefNum.setInputProtected(false);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(false);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_PD.setInputProtected(false);
        scrnMsg.shpgSvcLvlDescTxt_PD.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        // Add Start 2020/03/23 QC#55940
        scrnMsg.mdseCd.setInputProtected(false);
        // Add End 2020/03/23 QC#55940
        scrnMsg.xxRadioBtn.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_DW.setInputProtected(false);
        scrnMsg.destRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_DS.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Detail Label
        scrnMsg.xxComnScrColLbTxt_L1.setValue(DISPLAY_NM_UNIT_PRICE);
        scrnMsg.xxComnScrColLbTxt_L2.setValue(DISPLAY_NM_TOTAL_COST);

        // Detail Table
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).prchReqTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqApvlStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqSrcTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndRtrnRsnDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDsMultMsgDplyTxt_A2.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(true);
        }

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_NEW, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
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
        } else {
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
        }
        // QC#16256 add end

    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NPBL0010BMsg scrnMsg) {
        // Search Options
        scrnMsg.srchOptNm_TX.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);

        // Search Condition
        scrnMsg.prchReqNum.setNameForMessage(DISPLAY_NM_PRCH_REQ_NUM);
        scrnMsg.prchReqCratDt_RF.setNameForMessage(DISPLAY_NM_PRCH_REQ_CRAT_DT_RF);
        scrnMsg.prchReqCratDt_RT.setNameForMessage(DISPLAY_NM_PRCH_REQ_CRAT_DT_RT);
        scrnMsg.rqstRcvDt_NF.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_NF);
        scrnMsg.rqstRcvDt_NT.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_NT);
        scrnMsg.trxRefNum.setNameForMessage(DISPLAY_NM_TRX_REF_NUM);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setNameForMessage(DISPLAY_NM_MRP_PLN_NM);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setNameForMessage(DISPLAY_NM_FULL_PSN_NM);
        scrnMsg.carrNm.setNameForMessage(DISPLAY_NM_CARR_NM);
        // Add Start 2020/03/23 QC#55940
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_NM_MDSE_CD);
        // Add End 2020/03/23 QC#55940
        scrnMsg.srcRtlWhCd.setNameForMessage(DISPLAY_NM_SRC_RTL_WH_CD);
        scrnMsg.rtlWhNm_SW.setNameForMessage(DISPLAY_NM_RTL_WH_NM_SW);
        scrnMsg.srcRtlSwhCd.setNameForMessage(DISPLAY_NM_SRC_RTL_SWH_CD);
        scrnMsg.rtlSwhNm_SS.setNameForMessage(DISPLAY_NM_RTL_SWH_NM_SS);
        scrnMsg.destRtlWhCd.setNameForMessage(DISPLAY_NM_DEST_RTL_WH_CD);
        scrnMsg.rtlWhNm_DW.setNameForMessage(DISPLAY_NM_RTL_WH_NM_DW);
        scrnMsg.destRtlSwhCd.setNameForMessage(DISPLAY_NM_DEST_RTL_SWH_CD);
        scrnMsg.rtlSwhNm_DS.setNameForMessage(DISPLAY_NM_RTL_SWH_NM_DS);
        scrnMsg.vndCd.setNameForMessage(DISPLAY_NM_VND_CD);
        scrnMsg.dplyVndNm.setNameForMessage(DISPLAY_NM_DPLY_VND_NM);
        scrnMsg.shipToCustCd_EO.setNameForMessage(DISPLAY_NM_SHIP_TO_CUST_CD);
        scrnMsg.shipToLocNm_EO.setNameForMessage(DISPLAY_NM_DS_ACCT_NM);
    }

    /**
     * Get Param NWAL1130 For Requester
     * @param scrnMsg NPBL0010BMsg
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForRequesterPopup(NPBL0010BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Requester Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("   AP.USR_NM ");
        sb.append("  ,AP.FIRST_NM || (NVL2(AP.MID_NM, ' '||AP.MID_NM||' ', ' ')) || AP.LAST_NM FULL_PSN_NM ");
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
        ZYPTableUtil.clear(scrnMsg.R);
        params[IDX_6] = scrnMsg.R;
        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     */
    public static void setInitParamForCarrierPopup(NPBL0010BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_OTBD_CARR_V);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NPBL0010BMsg scrnMsg) {

        scrnMsg.carrCd.clear();

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P1;
        params[1] = scrnMsg.xxTblCdColNm_P1;
        params[2] = scrnMsg.xxTblNmColNm_P1;
        params[3] = scrnMsg.xxTblSortColNm_P1;
        params[4] = scrnMsg.xxScrNm_P1;
        params[5] = scrnMsg.xxHdrCdLbNm_P1;
        params[6] = scrnMsg.xxHdrNmLbNm_P1;
        params[7] = scrnMsg.xxDtlCdLbNm_P1;
        params[8] = scrnMsg.xxDtlNmLbNm_P1;
        params[9] = scrnMsg.carrCd;
        params[10] = scrnMsg.carrNm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     */
    public static void setInitParamForSrcWarehousePopup(NPBL0010BMsg scrnMsg) {

        scrnMsg.P.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     * @return Object[]
     */
    public static Object[] setParamForSrcWarehousePopup(NPBL0010BMsg scrnMsg) {

        Object[] params = new Object[12];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.srcRtlWhCd;
        params[7] = scrnMsg.rtlWhNm_SW;
        params[8] = scrnMsg.srcRtlSwhCd;
        params[9] = scrnMsg.rtlSwhNm_SS;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     */
    public static void setInitParamForDestWarehousePopup(NPBL0010BMsg scrnMsg) {

        scrnMsg.P.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     * @return Object[]
     */
    public static Object[] setParamForDestWarehousePopup(NPBL0010BMsg scrnMsg) {

        Object[] params = new Object[12];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.destRtlWhCd;
        params[7] = scrnMsg.rtlWhNm_DW;
        params[8] = scrnMsg.destRtlSwhCd;
        params[9] = scrnMsg.rtlSwhNm_DS;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     */
    public static void setInitParamForAccountPopup(NPBL0010BMsg scrnMsg) {

        scrnMsg.P.clear();

    }

    // Add Start 2020/03/09 QC#55940
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     * @return Object[]
     */
    public static Object[] setParamForMdsePopup(NPBL0010BMsg scrnMsg) {

        // Initialization of subscreen delivery information
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();

        // Making of subscreen delivery information
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.mdseCd);

        Object[] params = new Object[7];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;

        return params;
    }
    // Add End 2020/03/09 QC#55940

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0010BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setParamForAccountPopup(NPBL0010BMsg scrnMsg, int eventRowIndex) {

        scrnMsg.P.no(0).xxPopPrm.setValue(BIZ_APP_ID);

        // QC#12768 2016/11/1 Mod start
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaAfflCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaBrCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaCcCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaAcctCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaProdCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaChCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaProjCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.A.no(eventRowIndex).coaExtnCd_A1);

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        // QC#12768 2016/11/1 Mod end

        return params;
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPBL0010BMsg
     */
    public static void setTableScreen(NPBL0010BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        // set label
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.xxComnScrColLbTxt_L1.setValue(DISPLAY_NM_UNIT_PRICE_VR);
            scrnMsg.xxComnScrColLbTxt_L2.setValue(DISPLAY_NM_TOTAL_COST_VR);
        } else {
            scrnMsg.xxComnScrColLbTxt_L1.setValue(DISPLAY_NM_UNIT_PRICE);
            scrnMsg.xxComnScrColLbTxt_L2.setValue(DISPLAY_NM_TOTAL_COST);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (scrnMsg.A.no(i).prchReqNum_A1.isClear()) {
                EZDGUIAttribute ancher = new EZDGUIAttribute(SCRN_ID, HTML_ID_REQUEST_NUMBER_LINK + i);
                ancher.setVisibility(false);
                scrnMsg.addGUIAttribute(ancher);
            }
            if (scrnMsg.A.no(i).prchReqLineNum_A1.isClear()) {
                EZDGUIAttribute button = new EZDGUIAttribute(SCRN_ID, HTML_ID_ACCOUNT_BUTTON + i);
                button.setVisibility(false);
                scrnMsg.addGUIAttribute(button);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDsMultMsgDplyTxt_A2)) {
                EZDGUIAttribute button = new EZDGUIAttribute(SCRN_ID, HTML_ID_ACCOUNT_BUTTON + i);
                button.setVisibility(false);
                scrnMsg.addGUIAttribute(button);
            }

            scrnMsg.A.no(i).prchReqTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqApvlStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqSrcTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndRtrnRsnDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDsMultMsgDplyTxt_A2.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDsMultMsgDplyTxt_A1.setInputProtected(true);

            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(DECIMAL_POINT_USD);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setAppFracDigit(DECIMAL_POINT_USD);
        }
    }

    /**
     * check Item
     * @param scrnMsg NPBL0010BMsg
     */
    public static void commonAddCheckItem(NPBL0010BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);

        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqApvlStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_RF);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_RT);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_NF);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_NT);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.trxRefNum);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
        scrnMsg.addCheckItem(scrnMsg.carrNm);
        // Add Start 2020/03/23 QC#55940
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        // Add End 2020/03/23 QC#55940
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_SW);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_SS);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_DS);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_EO);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm_EO);
    }

    /**
     * clear table
     * @param scrnMsg NPBL0010BMsg
     */
    public static void clearTable(NPBL0010BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * setSupplierSearchParam
     * @param scrnMsg NPBL0010BMsg
     * @param glblCmpyCd String
     * @param pupupScrnNm String
     * @return Ship From / To Location Parameter
     */
    public static Object[] setSupplierSearchParam(NPBL0010BMsg scrnMsg, String glblCmpyCd, String pupupScrnNm) {

        // Parameter Clear
        scrnMsg.R.clear();
        scrnMsg.P.clear();

        // Paramter Set
        int paramCount = 0;
        Object[] param = new Object[IDX_7];
        param[paramCount++] = "";
        param[paramCount++] = pupupScrnNm;
        param[paramCount++] = TABLE_NM_PO_VND_V;

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_3);
        Object[] whereArray1 = new Object[] {SUP_CD, SUP_CD_COL, null, ZYPConstant.FLG_ON_Y };
        Object[] whereArray2 = new Object[] {SUP_SITE_CD, SUP_SITE_CD_COL, scrnMsg.vndCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereArray3 = new Object[] {SUP_SITE_NM, SUP_SITE_NM_COL, scrnMsg.dplyVndNm.getValue(), ZYPConstant.FLG_ON_Y };
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        param[paramCount++] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_3);
        Object[] columnArray1 = new Object[] {SUP_CD, SUP_CD_COL, WIDTH_20, ZYPConstant.FLG_OFF_N };
        Object[] columnArray2 = new Object[] {SUP_SITE_CD, SUP_SITE_CD_COL, WIDTH_20, ZYPConstant.FLG_ON_Y };
        Object[] columnArray3 = new Object[] {SUP_SITE_NM, SUP_SITE_NM_COL, WIDTH_30, ZYPConstant.FLG_OFF_N };
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        param[paramCount++] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>(IDX_2);
        Object[] sortConditionArray1 = new Object[] {SUP_CD_COL, ASC };
        Object[] sortConditionArray2 = new Object[] {SUP_SITE_CD_COL, ASC };
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);

        param[paramCount++] = sortList;
        param[paramCount++] = scrnMsg.R;

        return param;

    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
