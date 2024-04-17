/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0020;

import static business.blap.NPBL0020.constant.NPBL0020Constant.DB_PARAM_CMSG;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DB_PARAM_PRCH_REQ_NUM;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DB_PARAM_PRCH_REQ_TP_CD;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DEF_PRCH_REQ_TP_AOT_DAYS;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DESTINATION_SUB_WAREHOUSE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DESTINATION_SUB_WAREHOUSE_FOR_LINE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DESTINATION_WAREHOUSE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DESTINATION_WAREHOUSE_FOR_LINE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_DPLY_VND_NM;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_RTL_WH_NM_LINE_DW;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_RTL_WH_NM_LINE_SW;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SHIP_TO_CUST_CD;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SHIP_TO_LOC_NM;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_GET_ADDRESS;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_ADD_EXISTING_CONFIG;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_ADD_NEW_CONFIG;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_ADD_NEW_LINE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_CLEAR;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_RESET;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_SAVE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_SUBMIT;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_COPY;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_DEST_SWH_H;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_DEST_WH_H;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_ITEM_INFO;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_SHIP_TO_CUST_H;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_SHIP_TO_SPLY_H;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_SRC_SWH_H;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_GET_SRC_WH_H;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_HEADER_CANCEL;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_HEADER_CLOSE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_INIT;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_LINE_CANCEL;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_LINE_CLOSE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_MOVE_TO_ON_HAND_INV;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_NMAL6760;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_NMAL6800;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_NPAL1010;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_NPAL1360;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_NWAL1130;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_ON_CHANGE_PR_TYPE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_OPENWIN_SERENT;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_OPEN_WIN_ACCOUNT;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_PAGE_NEXT;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_PAGE_PREV;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_SEARCH;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_TEMPLATE_DOWNLOAD;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_UPLOAD;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_OPEN_WIN_SHIP_TO_ADDR;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_TAB_DETAIL;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_TAB_HEADER;
import static business.blap.NPBL0020.constant.NPBL0020Constant.IDX_12;
import static business.blap.NPBL0020.constant.NPBL0020Constant.NPAM1361E;
import static business.blap.NPBL0020.constant.NPBL0020Constant.NPBM0001E;
import static business.blap.NPBL0020.constant.NPBL0020Constant.SOURCE_SUB_WAREHOUSE_FOR_LINE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPBL0020.common.NPBL0020CommonLogic;
import business.db.PRCH_REQ_TPTMsg;

import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 * 04/21/2016   CITS            K.Ogino         Update          QC#7045
 * 04/22/2016   CITS            K.Ogino         Update          QC#6875
 * 04/25/2016   CITS            K.Ogino         Update          QC#7055
 * 04/25/2016   CITS            K.Ogino         Update          QC#7051
 * 06/10/2016   CSAI            D.Fukaya        Update          QC#9053
 * 06/10/2016   CSAI            D.Fukaya        Update          QC#9048
 * 06/24/2016   CSAI            D.Fukaya        Update          QC#9292
 * 06/27/2016   CSAI            D.Fukaya        Update          QC#9294
 * 07/26/2016   CITS            K.Ogino         Update          QC#9300
 * 07/29/2016   CITS            K.Ogino         Update          QC#8288
 * 08/03/2016   CITS            K.Ogino         Update          QC#12517
 * 01/10/2017   CITS            K.Ogino         Update          QC#16296
 * 01/13/2017   CITS            R.Shimamoto     Update          QC#17098
 * 02/09/2017   CITS            K.Ogino         Update          QC#17483
 * 06/26/2017   CITS            K.Ogino         Update          QC#19076
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 09/26/2017   CITS            K.Ogino         Update          QC#21288,QC#21132,#21259
 * 10/12/2017   CITS            K.Ogino         Update          QC#21682
 * 11/14/2017   CITS            K.Ogino         Update          QC#22345
 * 11/15/2017   CITS            K.Ogino         Update          QC#22608
 * 11/16/2017   CITS            K.Ogino         Update          QC#22346
 * 03/27/2018   CITS            T.Wada          Update          QC#22517
 * 04/03/2018   CITS            S.Katsuma       Update          QC#23521,25063
 * 05/25/2018   CITS            S.Katsuma       Update          QC#25893
 * 06/11/2018   CITS            S.Katsuma       Update          QC#26193
 * 12/12/2018   Fujitsu         S.Takami        Update          QC#29456
 * 12/20/2018   CITS            K.Kameoka       Update          QC#29456
 * 03/08/2019   CITS            K.Ogino         Update          QC#30618
 * 03/20/2019   Fujitsu         T.Ogura         Update          QC#30769
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 * 07/04/2023   Hitachi         T.Kuroda        Update          QC#61440
 *</pre>
 */
public class NPBL0020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPBL0020_INIT.equals(screenAplID)) {
                doProcess_NPBL0020_INIT((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_CMN_RESET.equals(screenAplID)) {
                doProcess_NPBL0020_CMN_Reset((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NPBL0020_CMN_Clear((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_ON_CHANGE_PR_TYPE.equals(screenAplID)) {
                doProcess_OnChangePRType((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_ADD_NEW_LINE.equals(screenAplID)) {
                doProcess_AddNewLine((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_ADD_NEW_CONFIG.equals(screenAplID)) {
                doProcess_AddNewConfig((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_ADD_EXISTING_CONFIG.equals(screenAplID)) {
                doProcess_AddExistingConfig((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_CMN_SAVE.equals(screenAplID)) {
                doProcess_CMN_Save((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_NWAL1130.equals(screenAplID)) {
                doProcess_NWAL1130((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_NPAL1010.equals(screenAplID)) {
                doProcess_NPAL1010((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_OPEN_WIN_ACCOUNT.equals(screenAplID)) {
                doProcess_OpenWinAccount((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_UPLOAD.equals(screenAplID)) {
                doProcess_Upload((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_TEMPLATE_DOWNLOAD.equals(screenAplID)) {
                doProcess_TemplateDownload((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_HEADER_CANCEL.equals(screenAplID)) {
                doProcess_HeaderCancel((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_HEADER_CLOSE.equals(screenAplID)) {
                doProcess_HeaderClose((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            // QC#56867 Mod
            } else if (EVENT_NM_NPBL0020_LINE_CANCEL.equals(screenAplID) || EVENT_NM_NPBL0020_LINE_CLOSE.equals(screenAplID)) {
                if (!ZYPCommonFunc.hasValue(((NPBL0020SMsg) sMsg).xxNum)) {
                    doProcess_LineCancelOrClose((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
                }
            } else if (EVENT_NM_NPBL0020_COPY.equals(screenAplID)) {
                doProcess_Copy((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_NPAL1360.equals(screenAplID)) {
                doProcess_Search((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_MOVE_TO_ON_HAND_INV.equals(screenAplID)) {
                doProcess_MoveTo_OnHandInv((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_OPENWIN_SERENT.equals(screenAplID)) {
                doProcess_OpenWin_SerEnt((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_GET_ITEM_INFO.equals(screenAplID)) {
                doProcess_GetItemInfo((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_GET_SRC_WH_H.equals(screenAplID)) {
                doProcess_Get_RtlWh((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg, true);
            } else if (EVENT_NM_NPBL0020_GET_DEST_WH_H.equals(screenAplID)) {
                doProcess_Get_RtlWh((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg, false);
            } else if (EVENT_NM_NPBL0020_GET_SRC_SWH_H.equals(screenAplID)) {
                doProcess_Get_RtlSwh((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg, true);
            } else if (EVENT_NM_NPBL0020_GET_DEST_SWH_H.equals(screenAplID)) {
                doProcess_Get_RtlSwh((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg, false);
            } else if (EVENT_NM_NPBL0020_GET_SHIP_TO_SPLY_H.equals(screenAplID)) {
                doProcess_Get_ShipToSply((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_GET_SHIP_TO_CUST_H.equals(screenAplID)) {
                doProcess_Get_ShipToCust((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_OPEN_WIN_SHIP_TO_ADDR.equals(screenAplID)) {
                doProcess_OpenWin_ShipToAddr((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_NMAL6760.equals(screenAplID)) {
                doProcess_NMAL6760((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_TAB_HEADER.equals(screenAplID) || EVENT_NM_TAB_DETAIL.equals(screenAplID)) {
                doProecss_Tab((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            // QC#22517
            } else if ((EVENT_NM_GET_ADDRESS.equals(screenAplID))) {
                doProcess_GetAddress((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            // 2018/12/12 QC#29456 Add Start
            } else if (EVENT_NM_NPBL0020_NMAL6800.equals(screenAplID)) {
                doProcess_NMAL6800((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
                // 2018/12/12 QC#29456 Add End
            }

            // START 2023/07/04 T.Kuroda [QC#61440, ADD]
            if (!EVENT_NM_NPBL0020_CMN_SAVE.equals(screenAplID)
                    && !EVENT_NM_NPBL0020_CMN_SUBMIT.equals(screenAplID)
                    && !EVENT_NM_NPBL0020_PAGE_NEXT.equals(screenAplID)
                    && !EVENT_NM_NPBL0020_PAGE_PREV.equals(screenAplID)) {
                NPBL0020CommonLogic.setSaveWrnSkipFlg((NPBL0020CMsg) cMsg, ZYPConstant.FLG_OFF_N);
                NPBL0020CommonLogic.setSubmitWrnSkipFlg((NPBL0020CMsg) cMsg, ZYPConstant.FLG_OFF_N);
            }
            // END 2023/07/04 T.Kuroda [QC#61440, ADD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_NPBL0020_INIT(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        // QC#9300
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.getCcyCd(cMsg, sMsg, glblCmpyCd);
        NPBL0020CommonLogic.createPullDownRequestedShipMethod(cMsg, sMsg, glblCmpyCd);
        NPBL0020CommonLogic.createPullDownReturnReason(cMsg, sMsg, glblCmpyCd);
        NPBL0020CommonLogic.createPullDownStockStatus(cMsg, sMsg, glblCmpyCd);
        NPBL0020CommonLogic.getLineStatusName(cMsg, sMsg, glblCmpyCd);
        NPBL0020CommonLogic.getConfigTypeName(cMsg, sMsg, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        NPBL0020CommonLogic.getDefaultHeaderStatusName(cMsg, sMsg, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
        NPBL0020CommonLogic.getDefaultApprovalStatusName(cMsg, sMsg, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        // QC#22346 Start
        if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {

            PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, cMsg.prchReqTpCd_SL.getValue());
            tMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.defPrchReqTpDaysNum)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defDaysAot)) {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                } else {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                }
            }

        }

        if (!ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {
            BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(defDaysAot)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            }
        }
        // QC#22346 End

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);
        NPBL0020CommonLogic.getDefaultDocumentSourceTypeName(cMsg, sMsg, glblCmpyCd);
        if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
            String fullName = getUserProfileService().getContextUserInfo().getFullName();
            if (ZYPCommonFunc.hasValue(fullName)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, fullName);
            } else {
                cMsg.fullPsnNm.clear();
            }
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByPsnCd, getUserProfileService().getContextUserInfo().getUserId());
        }

        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_IP) && !ZYPCommonFunc.hasValue(cMsg.poOrdNum)) {
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, cMsg.prchReqNum_IP);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_HD, cMsg.prchReqNum_IP);
            NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        } else if (ZYPCommonFunc.hasValue(cMsg.poOrdNum)) {
            NPBL0020CommonLogic.createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd, false);
            if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_IP)) {
                // QC#9300
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_CMSG, cMsg);
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_PRCH_REQ_NUM, cMsg.prchReqNum_IP.getValue());
                S21SsmEZDResult result = NPBL0020Query.getInstance().getPrchReqSts(ssmParam);
                if (result.isCodeNormal()) {
                    if (PRCH_REQ_STS.CANCELLED.equals((String) result.getResultObject())) {
                        NPBL0020CommonLogic.getPO(cMsg, sMsg, glblCmpyCd);
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, cMsg.prchReqNum_IP);
                        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_HD, cMsg.prchReqNum_IP);
                        NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
                    }
                } else {
                    NPBL0020CommonLogic.getPO(cMsg, sMsg, glblCmpyCd);
                }

            } else {
                NPBL0020CommonLogic.getPO(cMsg, sMsg, glblCmpyCd);
            }
        } else {
            NPBL0020CommonLogic.createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd, true);
        }
        // QC#17362
        if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && !ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd)) {
                // getDefaultVendorWH
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("prntVndCd", cMsg.prntVndCd.getValue());
                ssmParam.put("vndCd", cMsg.vndCd.getValue());
                S21SsmEZDResult result = NPBL0020Query.getInstance().getDefaultVendorWh(ssmParam);
                if (result.isCodeNormal()) {
                    List<Map<String, String>> vndWhInfoList = (List<Map<String, String>>) result.getResultObject();
                    if (vndWhInfoList.size() != 0) {
                        Map<String, String> vndWhInfo = vndWhInfoList.get(0);
                        if (vndWhInfo != null) {
                            if (ZYPCommonFunc.hasValue(vndWhInfo.get("RTL_WH_CD")) 
                                    && ZYPCommonFunc.hasValue(vndWhInfo.get("RTL_SWH_CD"))) {
                                ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, (String) vndWhInfo.get("RTL_WH_CD"));
                                String salsesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
                                NPBL0020CommonLogic.setRtlWh(cMsg, sMsg, glblCmpyCd, salsesDate, false, true);
                                NPBL0020CommonLogic.setRtlSwh(cMsg, sMsg, glblCmpyCd, salsesDate, false, true);
                            }
                        }
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, cMsg.prchReqTpCd_SL.getValue());

            S21SsmEZDResult result = NPBL0020Query.getInstance().getRequisitionType(ssmParam);

            if (result.isCodeNormal()) {
                String defPrchReqLineTpCd = (String) result.getResultObject();
                NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, defPrchReqLineTpCd);
                ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
                ZYPEZDItemValueSetter.setValue(cMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
            } else {
                NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, null);
            }
        }
        

        // START 2023/07/04 T.Kuroda [QC#61440, ADD]
        NPBL0020CommonLogic.setSaveWrnSkipFlg(cMsg, ZYPConstant.FLG_OFF_N);
        NPBL0020CommonLogic.setSubmitWrnSkipFlg(cMsg, ZYPConstant.FLG_OFF_N);
        // END 2023/07/04 T.Kuroda [QC#61440, ADD]
    }

    /**
     * Init
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_NPBL0020_CMN_Clear(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        String prchReqNumIP = null;
        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_IP)) {
            prchReqNumIP = cMsg.prchReqNum_IP.getValue();
            cMsg.prchReqNum_IP.clear();
        }
        doProcess_NPBL0020_INIT(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_IP, prchReqNumIP);
    }

    /**
     * Init
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_NPBL0020_CMN_Reset(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        doProcess_NPBL0020_INIT(cMsg, sMsg);
    }

    /**
     * Search
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_Search(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        boolean noResult = NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        if (noResult) {
            if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
                String fullName = getUserProfileService().getContextUserInfo().getFullName();
                if (ZYPCommonFunc.hasValue(fullName)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, fullName);
                } else {
                    cMsg.fullPsnNm.clear();
                }
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByPsnCd, getUserProfileService().getContextUserInfo().getUserId());
            }
        }

    }

    /**
     * On Change PRType
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_OnChangePRType(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.changePRType(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd));

    }

    /**
     * Add New Line
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_AddNewLine(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        boolean isSuccess = true;

        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            isSuccess = NPBL0020CommonLogic.setShipToAddress(cMsg, sMsg, glblCmpyCd, cMsg.vndCd.getValue());
        }

        if (isSuccess) {
            NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), false);
            NPBL0020CommonLogic.addNewLine(cMsg, sMsg, glblCmpyCd);
        }

    }

    /**
     * Add New Config
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_AddNewConfig(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), false);
        NPBL0020CommonLogic.addNewConfig(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Add Existing Config
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_AddExistingConfig(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        boolean isSuccess = true;

        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            isSuccess = NPBL0020CommonLogic.setShipToAddress(cMsg, sMsg, glblCmpyCd, cMsg.vndCd.getValue());
        }

        if (isSuccess) {
            NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), false);
            NPBL0020CommonLogic.addExistingConfig(cMsg, sMsg, glblCmpyCd);
        }

    }

    /**
     * Return NWAL1130
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_NWAL1130(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {

            NPBL0020CommonLogic.setShipToAddress(cMsg, sMsg, glblCmpyCd, cMsg.vndCd.getValue());

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dplyVndNm_A1, cMsg.dplyVndNm);

                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.vndCd.getValue());

                if (record != null) {

                    NPBL0020CommonLogic.setShipToAddrLineSMsg(sMsg, record, i);

                }
            }
        }

        NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
    }

    /**
     * Return NPAL1010
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_NPAL1010(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        if (DESTINATION_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()) //
                || DESTINATION_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()) //
                || DESTINATION_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()) //
                || SOURCE_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()) //
                || DESTINATION_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {

            if (!ZYPCommonFunc.hasValue(cMsg.carrNm)) {
                NPBL0020CommonLogic.setCarrier(cMsg, sMsg, glblCmpyCd);
            }
        }

        NPBL0020CommonLogic.setWarehouseCategory(cMsg, sMsg, glblCmpyCd);

        Map<String, String> record = null;
        if ((PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()))
                && (DESTINATION_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()) || DESTINATION_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()))) {

            if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL) && sMsg.A.getValidCount() == 0) {

                record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue());

                if (record != null) {
                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);
                } else {
                    NPBL0020CommonLogic.clearAddress(cMsg);
                }
            }

        } else if ((PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()))
                && (DESTINATION_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()) || DESTINATION_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue()))) {

            int index = cMsg.xxNum.getValueInt();

            if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {

                if (RTL_WH_CATG.SHOWROOM.equals(cMsg.A.no(index).rtlWhCatgCd_A1.getValue()) || RTL_WH_CATG.SHOWROOM.equals(cMsg.A.no(index).rtlWhCatgCd_A2.getValue())) {

                    record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).destRtlWhCd_A1.getValue());

                } else {

                    record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).destRtlWhCd_A1.getValue());

                }

                if (record != null) {

                    NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);

                }
            }

        } else if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(cMsg.vndCd)) {

            if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL) && sMsg.A.getValidCount() == 0) {

                record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.srcRtlWhCd.getValue());

                if (record != null) {

                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);

                } else {
                    NPBL0020CommonLogic.clearAddress(cMsg);
                }
            }
        }

        if (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) // 
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
            
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, cMsg.rtlWhNm_SW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, cMsg.rtlWhCatgCd_SW);
                if (!PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, cMsg.destRtlWhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, cMsg.rtlWhNm_DW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A2, cMsg.rtlWhCatgCd_DW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
            }
        }

        NPBL0020CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        NPBL0020CommonLogic.multipleCheckWh(sMsg, cMsg, glblCmpyCd);
    }

    /**
     * Open Window Account
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_OpenWinAccount(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        if (NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
            String glblCmpyCd = getGlobalCompanyCode();

            int indexC = cMsg.xxNum.getValueInt();
            int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
            int indexS = pageShowFromNum + indexC;
            boolean check = true;

            // QC#22376 Modify.Check Box is not clear.
            NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

            if (NPBL0020CommonLogic.checkForOpenAccountPopup(cMsg, sMsg, glblCmpyCd, indexS)) {
                cMsg.xxNum.setValue(indexC);
                check = NPBL0020CommonLogic.getAccount(cMsg, sMsg, glblCmpyCd, indexS, false, true);
            }
            if (check) {
                NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
            }
        }
        // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
    }

    /**
     * Upload
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_Upload(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.upload(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Template Download
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_TemplateDownload(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.templateDownload(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Header Cancel
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_HeaderCancel(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        if (ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            String glblCmpyCd = getGlobalCompanyCode();
            NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
    }

    /**
     * Header Close
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_HeaderClose(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        if (ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            String glblCmpyCd = getGlobalCompanyCode();
            NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
    }

    /**
     * Line Cancel. Mod QC#56867
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_LineCancelOrClose(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        if (ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            String glblCmpyCd = getGlobalCompanyCode();
            sMsg.xxNum.clear();
            NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }

    }

    /**
     * Copy
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_Copy(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);

        // change requisition type. screen entry mode.
        NPBL0020CommonLogic.createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd, true);

        cMsg.prchReqNum.clear();
        cMsg.prchReqNum_HD.clear();
        sMsg.prchReqNum.clear();
        sMsg.prchReqNum_HD.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsDescTxt, "");
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsDescTxt, "");
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        
        //QC#29456 Add Start
        ZYPEZDItemValueSetter.setValue(cMsg.chrgAcctEdtblFlg, NPBL0020CommonLogic.getChrgAcctEdtblFlg(cMsg.prchReqTpCd_SL.getValue(), glblCmpyCd));
        //QC#29456 Add End
                
        if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
            // QC#22346 Start
            PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, cMsg.prchReqTpCd_SL.getValue());
            tMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.defPrchReqTpDaysNum)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defDaysAot)) {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                } else {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                }
            }
            // QC#22346 End
        }
        // QC#19076
        if (PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {
            // QC#22346 Start
            PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, cMsg.prchReqTpCd_SL.getValue());
            tMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.defPrchReqTpDaysNum)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defDaysAot)) {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                } else {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                }
            }
            // QC#22346 End

            if (PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue())) {
                cMsg.fullPsnNm.clear();
                cMsg.prchReqRqstByPsnCd.clear();
            }
        } else {
            String fullName = getUserProfileService().getContextUserInfo().getFullName();
            if (ZYPCommonFunc.hasValue(fullName)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, fullName);
            } else {
                cMsg.fullPsnNm.clear();
            }
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, getUserProfileService().getContextUserInfo().getUserId());
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt, "");
        cMsg.trxRefNum.clear();
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        cMsg.mrpPlnNm.clear();
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        cMsg.prchReqCmntTxt.clear();
        cMsg.prchReqSavedFlg.clear();
        cMsg.openStsFlg.clear();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // QC#30618
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).configTpCd_A1) && !CONFIG_TP.EXISTING.equals(cMsg.A.no(i).configTpCd_A1.getValue())) {
                cMsg.A.no(i).xxLogDtlTxt_A1.clear();
                cMsg.A.no(i).xxLogDtlTxt_A2.clear();
            }
            cMsg.A.no(i).soNum_A1.clear();
            cMsg.A.no(i).rwsNum_A1.clear();
            cMsg.A.no(i).prchReqLineCmntTxt_A1.clear();
            cMsg.A.no(i).prchReqLineStsCd_A1.clear();
            cMsg.A.no(i).prchReqRelDtTmTs_A1.clear();
            cMsg.A.no(i).openStsFlg_A2.clear();
            cMsg.A.no(i).prchReqLineStsDescTxt_A1.clear();
            // QC#22345 Start
            cMsg.A.no(i).cpoOrdNum_A1.clear();
            // QC#22345 End
            // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
            if (!NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
                cMsg.A.no(i).xxScrItem50Txt_A1.clear();
            }
            // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
            // START 2018/05/25 S.Katsuma [QC#25893,ADD]
            cMsg.A.no(i).shipQty_A1.clear();
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {
                cMsg.A.no(i).entDealNetUnitPrcAmt_A1.clear();
                cMsg.A.no(i).entPoDtlDealNetAmt_A1.clear();
            }
            // END 2018/05/25 S.Katsuma [QC#25893,ADD]
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // QC#30618
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) && !CONFIG_TP.EXISTING.equals(sMsg.A.no(i).configTpCd_A1.getValue())) {
                sMsg.A.no(i).xxLogDtlTxt_A1.clear();
                sMsg.A.no(i).xxLogDtlTxt_A2.clear();
            }
            sMsg.A.no(i).soNum_A1.clear();
            sMsg.A.no(i).rwsNum_A1.clear();
            sMsg.A.no(i).prchReqLineCmntTxt_A1.clear();
            sMsg.A.no(i).prchReqLineStsCd_A1.clear();
            sMsg.A.no(i).prchReqRelDtTmTs_A1.clear();
            sMsg.A.no(i).openStsFlg_A2.clear();
            sMsg.A.no(i).prchReqLineStsDescTxt_A1.clear();
            // QC#22345 Start
            sMsg.A.no(i).cpoOrdNum_A1.clear();
            // QC#22345 End
            // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
            if (!NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
                sMsg.A.no(i).xxScrItem50Txt_A1.clear();
            }
            // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
            // START 2018/05/25 S.Katsuma [QC#25893,ADD]
            sMsg.A.no(i).shipQty_A1.clear();
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {
                sMsg.A.no(i).entDealNetUnitPrcAmt_A1.clear();
                sMsg.A.no(i).entPoDtlDealNetAmt_A1.clear();
            }
            // END 2018/05/25 S.Katsuma [QC#25893,ADD]
        }

    }

    /**
     * Page Next
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_PageNext(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        // QC#22376 Modify. Check box is not clear.
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        String glblCmpyCd = getGlobalCompanyCode();
        NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowToNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * Page Prev
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_PagePrev(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        // QC#22376 Modify. Check box is not clear.
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        String glblCmpyCd = getGlobalCompanyCode();
        NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length();
        int i = pagenationFrom - 1;
        for (int k = 0; i < pagenationFrom - 1 + cMsg.A.length(); i++, k++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(k), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom + 1);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * CMN_Save
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_CMN_Save(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // START 2023/07/04 T.Kuroda [QC#61440, MOD]
        // NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_01.getValue())) {
            NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
        NPBL0020CommonLogic.setSubmitWrnSkipFlg(cMsg, ZYPConstant.FLG_OFF_N);
        // END 2023/07/04 T.Kuroda [QC#61440, MOD]

    }

    /**
     * CMN_Submit
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_CMN_Submit(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // START 2023/07/04 T.Kuroda [QC#61440, ADD]
        // NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_02.getValue())) {
            NPBL0020CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
        NPBL0020CommonLogic.setSaveWrnSkipFlg(cMsg, ZYPConstant.FLG_OFF_N);
        // END 2023/07/04 T.Kuroda [QC#61440, MOD]

    }

    /**
     * Search
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_MoveTo_OnHandInv(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.setMoveToOnHandInv(cMsg, glblCmpyCd);

    }

    /**
     * CMN_Save
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_OpenWin_SerEnt(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        int indexC = cMsg.xxNum.getValueInt();
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + indexC;

        // QC# 22376 modify. check box is not clear.
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);
        NPBL0020CommonLogic.checkForOpenSerialEntryPopup(cMsg, sMsg, glblCmpyCd, indexS);
        NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
    }

    /**
     * GetItemInfo
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_GetItemInfo(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        int index = cMsg.xxNum.getValueInt();
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        NPBL0020_ACMsg acMsg = cMsg.A.no(index);
        NPBL0020CommonLogic.checkItemNumberScrn(acMsg, glblCmpyCd);

        // START 2018/06/11 S.Katsuma [QC#26193,ADD]
        if (ZYPConstant.FLG_OFF_N.equals(acMsg.shpgSerTakeFlg_A1.getValue())) {
            acMsg.xxLogDtlTxt_A1.clear();
        }
        // END 2018/06/11 S.Katsuma [QC#26193,ADD]

        // START 2018/05/25 S.Katsuma [QC#25893,ADD]
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(acMsg.entDealNetUnitPrcAmt_A1)) {
                if (ZYPCommonFunc.hasValue(acMsg.srcRtlWhCd_A1) && ZYPCommonFunc.hasValue(acMsg.srcRtlSwhCd_A1)) {
                    NLXC001001GetInventoryItemCostBean nlxc001001Bean = NPBL0020CommonLogic.getInvtyItemCost(glblCmpyCd,
                            acMsg.srcRtlWhCd_A1.getValue(),
                            acMsg.srcRtlSwhCd_A1.getValue(),
                            acMsg.mdseCd_A1.getValue(),
                            acMsg.prchReqDispQty_A1.getValue());
                    if (!nlxc001001Bean.getErrList().isEmpty()) {
                        cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                    } else {
                        ZYPEZDItemValueSetter.setValue(acMsg.entDealNetUnitPrcAmt_A1, nlxc001001Bean.getUnitPrcAmt());
                    }
                }
            }
        }
        // END 2018/05/25 S.Katsuma [QC#25893,ADD]

        // 2018/12/12 QC#29456 Add Start
        if (!acMsg.mdseCd_A1.isError()) {
            int glblIndex = getGlblIndex(cMsg);
            NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);
            NPBL0020CommonLogic.getAccountForce(cMsg, sMsg, glblCmpyCd, glblIndex, false, false);
            EZDMsg.copy(sMsg.A.no(glblIndex), null, cMsg.A.no(index), null);
        }
        // 2018/12/12 QC#29456 Add End
    }

    /**
     * Get_RtlWh
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param isSrcWh boolean
     */
    private void doProcess_Get_RtlWh(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, boolean isSrcWh) {

        String glblCmpyCd = getGlobalCompanyCode();
        String salsesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        NPBL0020CommonLogic.setRtlWh(cMsg, sMsg, glblCmpyCd, salsesDate, isSrcWh, true);
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);
        
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, cMsg.rtlWhNm_SW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, cMsg.rtlWhCatgCd_SW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, cMsg.destRtlWhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, cMsg.rtlWhNm_DW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A2, cMsg.rtlWhCatgCd_DW);
            }
        }

        if (sMsg.A.getValidCount() > 0) {

            NPBL0020CommonLogic.multipleCheckWh(sMsg, cMsg, glblCmpyCd);
        }

        NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
    }

    /**
     * Get_SrcSwhH
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param isSrcWh boolean
     */
    private void doProcess_Get_RtlSwh(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, boolean isSrcSwh) {

        String glblCmpyCd = getGlobalCompanyCode();
        String salsesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        NPBL0020CommonLogic.setRtlSwh(cMsg, sMsg, glblCmpyCd, salsesDate, isSrcSwh, true);
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        if (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, cMsg.rtlWhNm_DW);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
            }
        }

        if (sMsg.A.getValidCount() > 0) {

            NPBL0020CommonLogic.multipleCheckWh(sMsg, cMsg, glblCmpyCd);
        }

        NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
    }

    /**
     * Get_ShipToCust
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param isSrcWh isSrcSwh
     */
    private void doProcess_Get_ShipToCust(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

        if (ZYPCommonFunc.hasValue(dsAcctnm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
        } else {
            cMsg.shipToCustCd_EO.setErrorInfo(1, NPAM1361E, new String[] {"(" + cMsg.shipToCustCd_EO.getValue() + ")" });
        }

        NPBL0020CommonLogic.multipleCheckShipTo(sMsg, cMsg, glblCmpyCd);
    }

    /**
     * Get_ShipToSply
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param isSrcWh isSrcSwh
     */
    private void doProcess_Get_ShipToSply(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);
        NPBL0020CommonLogic.setShiptoSply(cMsg, sMsg, glblCmpyCd, true);
        NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
    }

    /**
     * doProcess_OpenWin_ShipToAddr
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_OpenWin_ShipToAddr(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        int index = cMsg.xxNum.getValueInt();

        // Search Condition Code
        if (ZYPCommonFunc.hasValue(cMsg.A.no(index).destRtlWhCd_A1)) {
            Map<String, String> record = null;
            if ((PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) //
                    && (RTL_WH_CATG.SHOWROOM.equals(cMsg.A.no(index).rtlWhCatgCd_A1.getValue()) //
                    || RTL_WH_CATG.SHOWROOM.equals(cMsg.A.no(index).rtlWhCatgCd_A2.getValue()))) {
                record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).destRtlWhCd_A1.getValue());
            } else {
                record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).destRtlWhCd_A1.getValue());
            }
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.A.no(index).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
            }
        } else if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) //
                && ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.vndCd.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.vndCd.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD });
            }
        } else if (ZYPCommonFunc.hasValue(cMsg.A.no(index).shipToCustCd_E1)) {
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).shipToCustCd_E1.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.A.no(index).shipToLocNm_E1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
            }
        } else if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) && ZYPCommonFunc.hasValue(cMsg.A.no(index).srcRtlWhCd_A1)) {
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).srcRtlWhCd_A1.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.A.no(index).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
            }
        } else if (ZYPCommonFunc.hasValue(cMsg.A.no(index).rtlWhNm_A2)) {
            int count = NPBL0020CommonLogic.checkDestRtlWhFromName(glblCmpyCd, cMsg.A.no(index).rtlWhNm_A2, cMsg.A.no(index).destRtlWhCd_A1, cMsg.A.no(index).rtlWhCatgCd_A2);
            if (count == 0) {
                cMsg.A.no(index).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                return;
            }
            if (count > 1) {
                cMsg.A.no(index).rtlWhNm_A2.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                return;
            }

            Map<String, String> record = null;
            if ((PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) //
                    && (RTL_WH_CATG.SHOWROOM.equals(cMsg.A.no(index).rtlWhCatgCd_A1.getValue()) //
                    || RTL_WH_CATG.SHOWROOM.equals(cMsg.A.no(index).rtlWhCatgCd_A2.getValue()))) {
                record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).destRtlWhCd_A1.getValue());
            } else {
                record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).destRtlWhCd_A1.getValue());
            }
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.A.no(index).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
            }
        } else if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) //
                && ZYPCommonFunc.hasValue(cMsg.dplyVndNm)) {
            int count = NPBL0020CommonLogic.checkShipToSupplierFromNameForLine(glblCmpyCd, cMsg.dplyVndNm.getValue(), cMsg.A.no(index).vndCd_A1, cMsg.A.no(index).prntVndCd_A1);
            if (count == 0) {
                cMsg.dplyVndNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                return;
            } else if (count > 1) {
                cMsg.dplyVndNm.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                return;
            }
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).vndCd_A1.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.dplyVndNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD });
            }
            return;
        } else if (ZYPCommonFunc.hasValue(cMsg.A.no(index).shipToLocNm_E1)) {
            int count = NPBL0020CommonLogic.getShipToCustCd(glblCmpyCd, cMsg.A.no(index).shipToCustCd_E1, cMsg.A.no(index).shipToLocNm_E1);
            if (count == 0) {
                cMsg.A.no(index).shipToLocNm_E1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                return;
            }
            if (count > 1) {
                cMsg.A.no(index).shipToLocNm_E1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                return;
            }
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).shipToCustCd_E1.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.A.no(index).shipToLocNm_E1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
            }
            return;
        } else if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {
            int count = NPBL0020CommonLogic.checkDestRtlWhFromName(glblCmpyCd, cMsg.A.no(index).rtlWhNm_A1, cMsg.A.no(index).srcRtlWhCd_A1, cMsg.A.no(index).rtlWhCatgCd_A1);
            if (count == 0) {
                cMsg.A.no(index).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                return;
            }
            if (count > 1) {
                cMsg.A.no(index).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                return;
            }

            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).srcRtlWhCd_A1.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else {
                cMsg.A.no(index).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
            }
            return;
        } else {
            if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
                cMsg.A.no(index).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
            } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
                cMsg.A.no(index).shipToLocNm_E1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
            } else {
                cMsg.vndCd.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD });
            }
            cMsg.setMessageInfo(ZZZM9025E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD });
        }

        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);
            NPBL0020CommonLogic.setShiptoSply(cMsg, sMsg, glblCmpyCd, true);
            NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
        }

        // QC#21682 Add
        if ((PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue()) // 
                || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) // 
                && ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {

            String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

            if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
            }
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(index).shipToCustCd_E1.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrLine(cMsg, record, index);
            } else if (!ZYPCommonFunc.hasValue(cMsg.vndCd)) {
                NPBL0020CommonLogic.clearAddress(cMsg);
            }
        }
        // QC#21682 End
    }

    /**
     * doProcess_NMAL6760
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_NMAL6760(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        int indexC = cMsg.xxNum.getValueInt();
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + indexC;
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL.getValue())) {

            if (indexC > -1) {

                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.A.no(indexC).shipToCustCd_E1.getValue());

                if (record != null) {

                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);
                    if (ZYPCommonFunc.hasValue(cMsg.A.no(indexC).configTpCd_A1)) {
                        // Config line
                        for (int i = indexS + 1; i < sMsg.A.getValidCount(); i++) {
                            if (sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                                break;
                            }
                            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxScrItem50Txt_A1)) {
                                cMsg.xxNum.setValue(i);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_E1, sMsg.A.no(indexS).shipToCustCd_E1);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToLocNm_E1, sMsg.A.no(indexS).shipToLocNm_E1);
                                NPBL0020CommonLogic.getAccount(cMsg, sMsg, glblCmpyCd, i, false, false);
                            }
                        }
                    // 2018/12/12 QC#29456 Mod Start
                    // } else if (!ZYPCommonFunc.hasValue(sMsg.A.no(indexS).xxScrItem50Txt_A1)) {
                    } else {
                        // Item Line
//                        NPBL0020CommonLogic.getAccount(cMsg, sMsg, glblCmpyCd, indexS, false, false);
                        NPBL0020CommonLogic.getAccountForce(cMsg, sMsg, glblCmpyCd, indexS, false, false);
                        // 2018/12/12 QC#29456 Mod End
                    }

                } else {
                    NPBL0020CommonLogic.clearAddress(cMsg);
                }

            } else {

                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

                if (record != null) {

                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);

                } else {
                    NPBL0020CommonLogic.clearAddress(cMsg);
                }
            }

            NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
            NPBL0020CommonLogic.multipleCheckShipTo(sMsg, cMsg, glblCmpyCd);
        }
    }

    // QC#22517 Add
    /**
     * doProcess_GetAddress
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_GetAddress(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        List<Map<String, Object>> list = null;
        String postCd = cMsg.shipToPostCd.getValue();

        S21SsmEZDResult res = NPBL0020Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NPBL0020Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            cMsg.shipToPostCd.setErrorInfo(1, "NMAM0039E", new String[] {"Ship to Location Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr, listCtyAddr.get(0));
            } else {
                cMsg.shipToCtyAddr.clear();
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd, listStCd.get(0));
            } else {
                cMsg.shipToStCd.clear();
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm, listCntyNm.get(0));
            } else {
                cMsg.shipToCntyNm.clear();
            }
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        }
    }
    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }
    /**
     * doProecss_TabHeader
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProecss_Tab(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), false);

        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        NPBL0020CommonLogic.allMultipleCheckTabAction(sMsg, glblCmpyCd);

        NPBL0020CommonLogic.setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

        NPBL0020CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    // 2018/12/12 QC#29456 Add Start
    private void doProcess_NMAL6800(NPBL0020CMsg bizMsg, NPBL0020SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        int index = bizMsg.xxNum.getValueInt();
        int glblIndex = getGlblIndex(bizMsg);
        NPBL0020CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg, false);
        NPBL0020CommonLogic.getAccountForce(bizMsg, glblMsg, glblCmpyCd, glblIndex, false, false);
        EZDMsg.copy(glblMsg.A.no(glblIndex), null, bizMsg.A.no(index), null);
    }

    private int getGlblIndex(NPBL0020CMsg bizMsg) {

        int indexC = bizMsg.xxNum.getValueInt();
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + indexC;

        return indexS;
    }
    // 2018/12/12 QC#29456 Add End

}
