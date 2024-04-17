package business.blap.NPAL1080;

import static business.blap.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_COLUMN_PRNT_VND_NM;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_COLUMN_RTL_SWH_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_DEST_WH_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_PRCH_REEQ_SRC_TP_Cd;
import static business.blap.NPAL1080.constant.NPAL1080Constant.FUNC_ID_SPEC_UPDATE;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0076E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0080E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1080.common.NPAL1080CommonLogic;
import business.blap.NPAL1080.constant.NPAL1080Constant;
import business.db.CNTYTMsg;
import business.db.CTRYTMsg;
import business.db.SHIP_TO_CUSTTMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 * 11/22/2016   CITS            K.Ogino         Update          QC#8295
 * 12/19/2016   CITS            K.Ogino         Update          QC#15324
 * 02/14/2017   CITS            M.Naito         Update          QC#17456
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 12/26/2017   CITS            K.Ogino         Update          QC#21908
 * 02/26/2018   CITS            S.Katsuma       Update          QC#24312
 * 05/25/2018   CITS            Y.Iwasaki       Update          SOL#135(QC#2366)
 * 07/09/2018   CITS            K.Ogino         Update          QC#24918
 * 08/20/2018   CITS            T.Tokutomi      Update          QC#26662
 * 10/29/2018   CITS            M.Naito         Update          QC#28965
 * 11/08/2018   CITS            T.Tokutomi      Update          QC#29020
 * 12/17/2018   CITS            T.Tokutomi      Update          QC#29299
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 * 02/14/2020   CITS            K.Ogino         Update          QC#55710
 * 03/05/2020   Fujitsu         R.Nakamura      Update          QC#56103
 * 03/12/2020   Fujitsu         R.Nakamura      Update          QC#56104
 * 2020/11/12   CITS            K.Ogino         Update          QC#57659
 * 2021/06/18   CITS            J.Evangelista   Update          QC#58876
 * 2023/03/17   Hitachi         T.Kuroda        Update          QC#61204
 * 2023/05/11   CITS            R.Kurahashi     Update          QC#61128
 * 2023/10/20   Hitachi         G.Quan          Update          QC#61494
 *</pre>
 */
public class NPAL1080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (("NPAL1080Scrn00_Search".equals(screenAplID)) || ("NPAL1080_NLAL2030".equals(screenAplID)) || ("NPAL1080_NLBL2020".equals(screenAplID)) || ("NPAL1080_NPAL1500".equals(screenAplID))) {
                doProcess_NPAL1080Scrn00_Search((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if (("NPAL1080_INIT".equals(screenAplID)) || ("NPAL1080Scrn00_CMN_Reset".equals(screenAplID))) {
                doProcess_NPAL1080_INIT((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if (("NPAL1080Scrn00_AddLine".equals(screenAplID)) || ("NPAL1080Scrn00_CMN_AddLine".equals(screenAplID))) {
                doProcess_NPAL1080Scrn00_AddLine((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_PageNext((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_PagePrev((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_Copy".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_Copy((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080_NMAL6760".equals(screenAplID)) {
                doProcess_NPAL1080_NMAL6760((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_OpenWin_Wh_Supplier".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_OpenWin_Wh_Supplier((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080_NPAL1010".equals(screenAplID)) {
                doProcess_NPAL1080_NPAL1010((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_CMN_Clear((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if (("NPAL1080Scrn00_CMN_Save".equals(screenAplID)) || ("NPAL1080Scrn00_HeaderCancel".equals(screenAplID)) || ("NPAL1080Scrn00_PRFreeze".equals(screenAplID)) || ("NPAL1080Scrn00_HeaderClose".equals(screenAplID))) {
                doProcess_NPAL1080Scrn00_Search((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if (("NPAL1080Scrn00_CMN_Submit".equals(screenAplID)) && !"W".equals(cMsg.getMessageKind())) {
                doProcess_NPAL1080Scrn00_Search((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_LineCancel".equals(screenAplID)) {
                if (!ZYPCommonFunc.hasValue(((NPAL1080SMsg) sMsg).xxEdtModeFlg)) {
                    doProcess_NPAL1080Scrn00_Search((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
                }
            } else if ("NPAL1080Scrn00_OnChange_RequestType".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_OnChange_RequestType((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_OnChange_RequestTime".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_OnChange_RequestTime((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080_NMAL6050".equals(screenAplID)) {
                doProcess_NPAL1080_NMAL6050((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_GetItemName".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_GetItemName((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_GetTechLocInfo".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_GetTechLocInfo((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_GetWhOrSupplierName".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_GetWhOrSupplierName((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080Scrn00_SearchTechnician".equals(screenAplID)) {
                doProcess_SearchTechnician((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if ("NPAL1080_NWAL1130".equals(screenAplID)) {
                doProcess_NPAL1080_NWAL1130((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            // START 2018/10/29 M.Naito [QC#28965,ADD]
            } else if ("NPAL1080Scrn00_OnChange_RequestLineType".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_OnChange_RequestLineType((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            // END 2018/10/29 M.Naito [QC#28965,ADD]

            } else if("NPAL1080_NMAL6800".equals(screenAplID)){
                // QC#29299 Add.
                doProcess_NPAL1080_NMAL6800((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            // QC#57659
            } else if("NPAL1080Scrn00_OnChange_RequestServiceLevel".equals(screenAplID)){
                // QC#29299 Add.
                doProcess_NPAL1080Scrn00_OnChange_RequestServiceLevel((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            // START 2023/03/17 T.Kuroda [QC#61204, ADD]
            } else if("NPAL1080Scrn00_Upload".equals(screenAplID)){
                doProcess_NPAL1080Scrn00_Upload((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);

            } else if("NPAL1080Scrn00_OnClick_TemplateDownload".equals(screenAplID)){
                doProcess_NPAL1080Scrn00_TemplateDownload((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            // END   2023/03/17 T.Kuroda [QC#61204, ADD]

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080_INIT(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        String prchReqNum = null;
        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_H1)) {
            prchReqNum = cMsg.prchReqNum_H1.getValue();
        }
        // ACMsg clear, ASMsg clear
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.clear();
        cMsg.A.setValidCount(0);
        sMsg.clear();
        sMsg.A.setValidCount(0);

        String globalCompanyCode = getGlobalCompanyCode();

        // QC#15324
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt_H1, ZYPDateUtil.getSalesDate(globalCompanyCode));
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt_H1, ZYPDateUtil.getSalesDate(globalCompanyCode));
        String fullName = getUserProfileService().getContextUserInfo().getFullName();
        if (ZYPCommonFunc.hasValue(fullName)) {
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_H1, fullName);
        } else {
            cMsg.fullPsnNm_H1.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByPsnCd_H1, getUserProfileService().getContextUserInfo().getUserId());
        getDefaultDocumentSourceTypeName(cMsg, sMsg, globalCompanyCode);

        NPAL1080CommonLogic.createPullDown(cMsg, sMsg, globalCompanyCode);
        NPAL1080CommonLogic.initPullDownData(sMsg, globalCompanyCode);
        if (prchReqNum != null) {
            cMsg.prchReqNum_H1.setValue(prchReqNum);
            NPAL1080CommonLogic.searchDetail(cMsg, sMsg, globalCompanyCode, false);
        }
    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_Search(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        //Reset warning flag
        sMsg.xxBtnFlg_H1.clear();
        NPAL1080CommonLogic.searchDetail(cMsg, sMsg, getGlobalCompanyCode(), false);
    }

    /**
     * <pre>
     * Clear Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_CMN_Clear(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        cMsg.prchReqNum_H1.clear();
        doProcess_NPAL1080_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * Serch Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_OpenWin_Wh_Supplier(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        cMsg.xxPopPrm_P2.setValue(NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(), BUSINESS_APPLICATION_ID, cMsg.prchReqRecTpCd_H2.getValue()));
    }

    /**
     * <pre>
     * AddLine Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_AddLine(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        //Reset warning flag
        sMsg.xxBtnFlg_H1.clear();
        // check max count
        int index = sMsg.A.getValidCount();
        if (index == sMsg.A.length()) {
            return;
        } else {
            NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            // Find ReqRecType
            sMsg.prchReqRecTpCd_H2.setValue(NPAL1080CommonLogic.findRequisitionRecordTypeCode(sMsg.prchReqTpCd_SE.getValue(), getGlobalCompanyCode()));
            // create prchReqLineNum
            int num = 0;
            for (int i = 0; i < index; i++) {
                int temp = Integer.valueOf(sMsg.A.no(i).prchReqLineNum_D1.getValue());
                if (num < temp) {
                    num = temp;
                }
            }
            // New Line Flag
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxNewRowNum, new BigDecimal(-1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_D1, String.format("%03d", (num + 1)));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineSubNum_D1, BigDecimal.ZERO);

            // add row
            setAddLine(sMsg, index);

            // clear old data
            sMsg.A.no(index).xxChkBox_D1.clear();
            // START 2017/10/25 S.Katsuma QC#21896 ADD
            // Line Type
//            sMsg.A.no(index).prchReqLineTpCd_SE.clear();
            // END 2017/10/25 S.Katsuma QC#21896 ADD
            // Mdse Code
            sMsg.A.no(index).mdseCd_D1.clear();
            // Mdse Name
            sMsg.A.no(index).mdseDescShortTxt_D1.clear();
            // Req QTY
            sMsg.A.no(index).prchReqQty_D1.clear();
            
            // QC#29299 Add Emergency WH.
            // Procurement Type
            // WH /Supplier WH Name
            // SWH
            if (NPAL1080CommonLogic.isSourceEmergencyWh(getGlobalCompanyCode(), sMsg.prchReqTpCd_SE.getValue())) {
                NPAL1080CommonLogic.setEmergencyWh(getGlobalCompanyCode(), sMsg, index);
            } else {
                NPAL1080CommonLogic.findDefaultWh(getGlobalCompanyCode(), sMsg, index);
            }
            // DEST_INVTY_LOC_CD
            sMsg.A.no(index).destInvtyLocCd_D1.clear();
            // Line Status
            sMsg.A.no(index).prchReqLineStsDescTxt_D1.clear();
            // SO Status
            sMsg.A.no(index).dsSoLineStsDescTxt_D1.clear();
            // RWS Status
            sMsg.A.no(index).rwsStsDescTxt_D1.clear();
            // Fulfilled Qty
            sMsg.A.no(index).shipQty_D1.clear();
            // Received Qty
            sMsg.A.no(index).rwsPutAwayQty_D1.clear();
            // Cancel QTY
            sMsg.A.no(index).prchReqCancQty_D1.clear();
            // PO#
            sMsg.A.no(index).poOrdNum_AC.clear();
            // SO# Mod QC#21908.
            sMsg.A.no(index).vndSoNum_AC.clear();
            // Add Start 2020/03/12 QC#56104
            // Alt SO#
            sMsg.A.no(index).rwsRefNum_AC.clear();
            // Add End 2020/03/12 QC#56104
            // START 2021/06/18 J.Evangelista [QC#58876,ADD]
            sMsg.A.no(index).xxScrItem20Txt_AC.clear();
            // END 2021/06/18 J.Evangelista [QC#58876,ADD]
            // RWS#
            sMsg.A.no(index).rwsNum_AC.clear();
            // Insourced Qty
            sMsg.A.no(index).prchReqInsrcQty_D1.clear();
            // Insourced Flag
            sMsg.A.no(index).insrcFlg_D1.clear();
            // PO Flag
            sMsg.A.no(index).poCratFlg_D1.clear();

            // QC#26662 Update 2018/08/20. Add Function of special Update mode.
            if (isSpecialUpdate() && PRCH_REQ_APVL_STS.APPROVED.equals(sMsg.prchReqApvlStsCd_H1.getValue())) {
                String frzCmnt = ZYPCodeDataUtil.getVarCharConstValue("NPAL1080_SPCL_FRZ_CMNT", getGlobalCompanyCode());

                // Freeze Flag
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqFrzFlg_D1, ZYPConstant.FLG_ON_Y);
                // Line Comment
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineCmntTxt_D1, frzCmnt);
            } else {
                // Freeze Flag
                sMsg.A.no(index).prchReqFrzFlg_D1.clear();
                // Line Comment
                sMsg.A.no(index).prchReqLineCmntTxt_D1.clear();
            }

            // Ref#
            sMsg.A.no(index).xxPopPrm_D1.clear();
            // Actual Service Level
            sMsg.A.no(index).shpgSvcLvlCd_D1.clear();
            // QC#24918
            sMsg.A.no(index).shpgSvcLvlDescTxt_D1.clear();
            // Tracking Number
            sMsg.A.no(index).proNum_D1.clear();

            sMsg.A.no(index).prchReqLineStsCd_D1.clear();

            // START 2018/02/26 S.Katsuma [QC#24312,MOD]
            sMsg.A.no(index).prchReqBalQty_D1.clear();
            // END 2018/02/26 S.Katsuma [QC#24312,MOD]

            sMsg.A.setValidCount(index + 1);
            // Show last page.
            cMsg.xxPageShowFromNum_D2.setValue(-1);

            // QC#29020 Update. get Address process methodized.
            NPAL1080CommonLogic.setUpAddress4sMsg(getGlobalCompanyCode(), sMsg);

            NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Copy Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_Copy(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        //Reset warning flag
        sMsg.xxBtnFlg_H1.clear();
        NPAL1080CommonLogic.searchDetail(cMsg, sMsg, getGlobalCompanyCode(), true);
        NPAL1080CommonLogic.getRequestReceivingDateAndTimePulldownListByRequisitionType(sMsg, getGlobalCompanyCode());

        String globalCompanyCode = getGlobalCompanyCode();

        // QC#15324
        if (!ZYPCommonFunc.hasValue(sMsg.prchReqCratDt_H1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt_H1, ZYPDateUtil.getSalesDate(globalCompanyCode));
        }
        if (!ZYPCommonFunc.hasValue(sMsg.rqstRcvDt_H1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt_H1, ZYPDateUtil.getSalesDate(globalCompanyCode));
        }
        if (!ZYPCommonFunc.hasValue(sMsg.fullPsnNm_H1)) {
            String fullName = getUserProfileService().getContextUserInfo().getFullName();
            if (ZYPCommonFunc.hasValue(fullName)) {
                ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm_H1, fullName);
            } else {
                cMsg.fullPsnNm_H1.clear();
            }
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByPsnCd_H1, getUserProfileService().getContextUserInfo().getUserId());
        }
        if (!ZYPCommonFunc.hasValue(sMsg.prchReqSrcTpDescTxt_H1)) {
            getDefaultDocumentSourceTypeName(cMsg, sMsg, globalCompanyCode);
        }

        // START 2017/10/25 S.Katsuma QC#21896 ADD
        // change detail Line Type Data
        // QC#55710
        if (PRCH_REQ_TP.TECH_RETURN.equals(cMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(cMsg.prchReqTpCd_SE.getValue())) {
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                // clear pulldown data
                sMsg.A.no(index).prchReqLineTpCd_CD.clear();
                sMsg.A.no(index).prchReqLineTpNm_DI.clear();

                setPrchReqLineTpCd(sMsg, index);
                NPAL1080CommonLogic.findDefaultWh(getGlobalCompanyCode(), sMsg, index);
            }
        }
        // END 2017/10/25 S.Katsuma QC#21896 ADD

        // WHO BEGIN
        for (int index = 0; index < sMsg.A.getValidCount(); ++index) {
            sMsg.A.no(index).xxRecHistCratTs_D1.clear();
            sMsg.A.no(index).xxRecHistCratByNm_D1.clear();
            sMsg.A.no(index).xxRecHistUpdTs_D1.clear();
            sMsg.A.no(index).xxRecHistUpdByNm_D1.clear();
            sMsg.A.no(index).xxRecHistTblNm_D1.clear();
            // Add Start 2020/03/05 QC#56103
            sMsg.A.no(index).prchReqNum_D1.clear();
            // Add End 2020/03/05 QC#56103
        }
        sMsg.xxRecHistCratTs_H1.clear();
        sMsg.xxRecHistCratByNm_H1.clear();
        sMsg.xxRecHistUpdTs_H1.clear();
        sMsg.xxRecHistUpdByNm_H1.clear();
        sMsg.xxRecHistTblNm_H1.clear();
        // WHO END

        sMsg.prchReqCmntTxt_H1.clear();
        sMsg.techExpRqstFlg.clear();
        sMsg.hazMatFlg.clear();

        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_PagePrev(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum_D2.setValue(cMsg.xxPageShowFromNum_D2.getValueInt() - cMsg.A.length());
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_PageNext(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum_D2.setValue(cMsg.xxPageShowFromNum_D2.getValueInt() + cMsg.A.length());
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * NPAL1010 Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080_NPAL1010(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        //QC#7386 find findTechnician Code / Name
        if(ZYPCommonFunc.hasValue(cMsg.xxPopPrm_P6)){
            NPAL1080CommonLogic.findTechnician(cMsg, getGlobalCompanyCode(), cMsg.xxPopPrm_P6.getValue());
        }

        // QC#29020 Add
        NPAL1080CommonLogic.setUpAddress4cMsg(getGlobalCompanyCode(), cMsg);

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_DEST_WH_CD, cMsg.xxPopPrm_P6);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findCarrNm(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            if (resultList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, (String) (resultList.get(0)).get("CARR_NM"));
            }
        }
    }

    /**
     * <pre>
     * NMAL6010 Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080_NMAL6760(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        String globalCompanyCode = getGlobalCompanyCode();
        SHIP_TO_CUSTTMsg data = NPAL1080CommonLogic.getShipToCust(cMsg.shipToCustCd_H1.getValue(), globalCompanyCode);
        if (data != null) {
            // Name
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_H2, data.locNm);
            // Additional Name
            ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_H2, data.addlLocNm);
            // Address
            ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_H2, data.firstLineAddr.getValue() + data.scdLineAddr.getValue() + data.thirdLineAddr.getValue() + data.frthLineAddr.getValue());
            // Post Code
            ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_H2, data.postCd);
            // City
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_H2, data.ctyAddr);
            // County
            CNTYTMsg cnty = NPAL1080CommonLogic.getCnty(data.cntyPk.getValue(), globalCompanyCode);
            if (cnty != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_H2, cnty.cntyNm);
            }
            // State
            ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_H2, data.stCd);
            // Province
            ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_H2, data.provNm);
            // Country
            CTRYTMsg ctry = NPAL1080CommonLogic.getCtry(data.ctryCd.getValue(), globalCompanyCode);
            if (ctry != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_H2, ctry.ctryNm);
            }
        }

        // Get Ship To Customer Name
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put("shipToCustCd", cMsg.shipToCustCd_H1);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findCustomerName(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            if (resultList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) (resultList.get(0)).get("DS_ACCT_NM"));
            }
        }
    }

    /**
     * doProcess_NPAL1080Scrn00_OnChange_RequestType
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_OnChange_RequestType(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        
        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        //QC:7389
        sMsg.dsCondConstCd_SE.clear();
        sMsg.dsCondConstCd_CD.clear();
        sMsg.rqstRcvDtTxt_DI.clear();
        sMsg.D.clear();

        NPAL1080CommonLogic.getRequestReceivingDateAndTimePulldownListByRequisitionType(sMsg, getGlobalCompanyCode());

        // change detail Line Type Data
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            // clear pulldown data
            sMsg.A.no(index).prchReqLineTpCd_CD.clear();
            sMsg.A.no(index).prchReqLineTpNm_DI.clear();

            // START 2017/10/25 S.Katsuma QC#21896 ADD
            setPrchReqLineTpCd(sMsg, index);
            NPAL1080CommonLogic.findDefaultWh(getGlobalCompanyCode(), sMsg, index);
            // END 2017/10/25 S.Katsuma QC#21896 ADD
        }
        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * doProcess_NPAL1080Scrn00_OnChange_RequestTime
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * QC:7389
     */
    private void doProcess_NPAL1080Scrn00_OnChange_RequestTime(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        
        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        
        // START 2023/10/20 G.Quan [QC#61494, MOD]
        //NPAL1080CommonLogic.defaultRequestDateByCalcualtionSetting(sMsg, getGlobalCompanyCode());
        NPAL1080CommonLogic.defaultRequestDateByCalcualtionSetting(sMsg, getGlobalCompanyCode(), false);
        // END 2023/10/20 G.Quan [QC#61494, MOD]
        
        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
    
    /**
     * doProcess_NPAL1080Scrn00_GetItemName
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_GetItemName(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        int idx = cMsg.xxNum_D1.getValueInt();

        // initialize
        cMsg.A.no(idx).mdseDescShortTxt_D1.clear();

        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        idx = NPAL1080CommonLogic.convertIndexFromCmsgOntoSmsg(cMsg, sMsg, idx);
        // START 2017/10/25 S.Katsuma QC#21896 ADD
        NPAL1080CommonLogic.getItemName(cMsg, sMsg, getGlobalCompanyCode(), idx);
        // QC#55710
        if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
            if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                if (!ZYPCommonFunc.hasValue(sMsg.rqstTechTocCd_H1)) {
                    sMsg.rqstTechTocCd_H1.setErrorInfo(1, "ZZZM9025E", new String[] {"Technician Code" });
                    cMsg.setMessageInfo("ZZZM9025E", new String[] {"Technician Code" });
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.destRtlWhCd_H1)) {
                    sMsg.destRtlWhCd_H1.setErrorInfo(1, "ZZZM9025E", new String[] {"Tech WH" });
                    cMsg.setMessageInfo("ZZZM9025E", new String[] {"Tech WH" });
                    return;
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(idx).prntVndCd_D1) || !ZYPCommonFunc.hasValue(sMsg.A.no(idx).locNm_D1)) {
                    NPAL1080CommonLogic.findDefaultWhForTechSWHR(getGlobalCompanyCode(), sMsg, idx);
                }
            }
        } else {
            // QC#61128 Delete Start
            //// QC#29299 Add get Default Procurement source.
            //if (!NPAL1080CommonLogic.isSourceEmergencyWh(getGlobalCompanyCode(), sMsg.prchReqTpCd_SE.getValue())) {
            //    if (!NPAL1080CommonLogic.getProcurmentSource(getGlobalCompanyCode(), sMsg, idx)) {
            //        // Error.
            //        return;
            //    }
            //}
            // QC#61128 Delete End
        
        }
        // END 2017/10/25 S.Katsuma QC#21896 ADD

        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * doProcess_NPAL1080Scrn00_GetWhOrSupplierName
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_GetWhOrSupplierName(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        int idx = cMsg.xxNum_D1.getValueInt();

        // initialize
        cMsg.A.no(idx).prntVndNm_D1.clear();

        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        idx = NPAL1080CommonLogic.convertIndexFromCmsgOntoSmsg(cMsg, sMsg, idx);
        // Get Parent Vendor name when Procurement Type is "Supplier"
        if (PROCR_TP.SUPPLIER.equals(sMsg.A.no(idx).procrTpCd_SE.getValue())) {

            S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getPrntVndNm(getGlobalCompanyCode(), sMsg.A.no(idx).prntVndCd_D1.getValue());

            if (ssmResult.isCodeNormal()) {

                List<Map> resultList = (List<Map>) ssmResult.getResultObject();

                if (resultList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).prntVndNm_D1, (String) resultList.get(0).get(DB_COLUMN_PRNT_VND_NM));
                    sMsg.A.no(idx).locNm_D1.clear();
                }
            } else {
                sMsg.A.no(idx).prntVndCd_D1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(idx).prntVndCd_D1.getValue() });
                cMsg.setMessageInfo(NPAM0076E, new String[] {"WH/Supplier" });
            }

            // Get Warehouse name when Procurement Type is "Warehouse"
        } else {
            S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getWhNm(getGlobalCompanyCode(), sMsg.A.no(idx).prntVndCd_D1.getValue());

            if (ssmResult.isCodeNormal()) {

                List<Map> resultList = (List<Map>) ssmResult.getResultObject();

                if (resultList.size() == 1) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).prntVndNm_D1, (String) resultList.get(0).get(DB_COLUMN_RTL_WH_NM));
                    // START 2017/10/25 S.Katsuma QC#21896 ADD
                    if (!sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).locNm_D1, (String) resultList.get(0).get(DB_COLUMN_RTL_SWH_CD));
                    }
                    // END 2017/10/25 S.Katsuma QC#21896 ADD
                }
            } else {
                sMsg.A.no(idx).prntVndCd_D1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(idx).prntVndCd_D1.getValue() });
                cMsg.setMessageInfo(NPAM0076E, new String[] {"WH/Supplier" });
            }
        }

        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * get Tech Loc Info(carrier and Default WH) Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_GetTechLocInfo(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // Check Technician Location
        if (!NPAL1080CommonLogic.checkWhCode(cMsg, getGlobalCompanyCode())) {
            cMsg.destRtlWhCd_H1.setErrorInfo(1, NPAM0080E, new String[] {cMsg.destRtlWhCd_H1.getValue() });
            return;
        }

        // QC#29020 Add.
        NPAL1080CommonLogic.setUpAddress4sMsg(getGlobalCompanyCode(), sMsg);

        // set preferred carrier
        NPAL1080CommonLogic.getPrfCarrCd(getGlobalCompanyCode(), sMsg);

        // set Default WH when the column is blank
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2017/10/25 S.Katsuma QC#21896 ADD
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                // clear pulldown data
                sMsg.A.no(i).prchReqLineTpCd_CD.clear();
                sMsg.A.no(i).prchReqLineTpNm_DI.clear();

                setPrchReqLineTpCd(sMsg, i);
                NPAL1080CommonLogic.findDefaultWh(getGlobalCompanyCode(), sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).prntVndCd_D1) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).locNm_D1)) {

                    NPAL1080CommonLogic.findDefaultWh(getGlobalCompanyCode(), sMsg, i);

                }
            }
            // END 2017/10/25 S.Katsuma QC#21896 ADD
        }

        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        //QC#7386 find findTechnician Code / Name
        NPAL1080CommonLogic.findTechnician(cMsg, getGlobalCompanyCode(), cMsg.destRtlWhCd_H1.getValue());
    }

    /**
     * <pre>
     * Set add Line data
     * </pre>
     * @param bizMsg Business Msg
     */
    private void setAddLine(NPAL1080SMsg sMsg, int index) {
        // Line Type
        // START 2017/10/25 S.Katsuma QC#21896 ADD
        setPrchReqLineTpCd(sMsg, index);
        // END 2017/10/25 S.Katsuma QC#21896 ADD

        // Source Type
        for (int i = 0; i < sMsg.procrTpCd_D1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.procrTpCd_D1.no(i))) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_CD.no(i), sMsg.procrTpCd_D1.no(i));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpNm_DI.no(i), sMsg.procrTpNm_D1.no(i));
        }
        // SetDefault
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, PROCR_TP.WAREHOUSE);
    }

    /**
     * <pre>
     * NMAL6050 Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080_NMAL6050(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        cMsg = NPAL1080CommonLogic.findTechWhCode(cMsg, getGlobalCompanyCode());

    }

    /**
     * Search Technician
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_SearchTechnician(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1080CommonLogic.setTechTocCodeAndName(cMsg, glblCmpyCd);

        cMsg = NPAL1080CommonLogic.findTechWhCode(cMsg, getGlobalCompanyCode());

        // Create Param
        if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd_H1)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_DEST_WH_CD, cMsg.destRtlWhCd_H1.getValue());
            // Execute
            S21SsmEZDResult result = NPAL1080Query.getInstance().findCarrNm(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultList = (List<Map>) result.getResultObject();

                if (resultList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, (String) (resultList.get(0)).get("CARR_NM"));
                }
            } else {
                cMsg.carrNm_H1.clear();
            }

            // QC#29020 Add.
            NPAL1080CommonLogic.setUpAddress4cMsg(getGlobalCompanyCode(), cMsg);

        } else {
            cMsg.carrNm_H1.clear();
        }
    }

    /**
     * <pre>
     * NWAL1130 Event
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080_NWAL1130(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        cMsg = NPAL1080CommonLogic.findTechWhCode(cMsg, getGlobalCompanyCode());

        // Create Param
        if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd_H1)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_DEST_WH_CD, cMsg.destRtlWhCd_H1.getValue());
            // Execute
            S21SsmEZDResult result = NPAL1080Query.getInstance().findCarrNm(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultList = (List<Map>) result.getResultObject();

                if (resultList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, (String) (resultList.get(0)).get("CARR_NM"));
                }
            } else {
                cMsg.carrNm_H1.clear();
            }
            
            // QC#29020 Add.
            NPAL1080CommonLogic.setUpAddress4cMsg(getGlobalCompanyCode(), cMsg);

        } else {
            cMsg.carrNm_H1.clear();
        }
    }

    /**
     * Get Default Document Source Type Name Add QC#15324
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void getDefaultDocumentSourceTypeName(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REEQ_SRC_TP_Cd, PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getDocumentSourceTypeName(ssmParam);

        if (result.isCodeNormal()) {
            String prchReqSrcTpDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpDescTxt_H1, prchReqSrcTpDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt_H1, prchReqSrcTpDescTxt);
            // QC#17456
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpCd_H1, PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY);
        }
    }

    // START 2017/10/25 S.Katsuma QC#21896 ADD
    /**
     * setPrchReqLineTypeCd
     * @param cMsg
     * @param sMsg
     * @param index
     */
    // START 2023/03/22 T.Kuroda [QC#61204, MOD]
    public static void setPrchReqLineTpCd(NPAL1080SMsg sMsg, int index) {
//    private void setPrchReqLineTpCd(NPAL1080SMsg sMsg, int index) {
    // END   2023/03/22 T.Kuroda [QC#61204, MOD]
        // QC#55710
        if (!(PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()))) {
            for (int i = 0; i < sMsg.prchReqLineTpCd_D2.length(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_D2.no(i))) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(i), sMsg.prchReqLineTpCd_D2.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(i), sMsg.prchReqLineTpNm_D2.no(i));
            }
            // Line Type
            sMsg.A.no(index).prchReqLineTpCd_SE.clear();
        } else {
            int prchReqLineTpCd_CDIdx = 0;
            for (int i = 0; i < sMsg.prchReqLineTpCd_D1.length(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_D1.no(i))) {
                    break;
                }
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) && sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_G)) {
                    if (sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.LOCAL_RETURN) || sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.DEFECTIVE_RETURN)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                        prchReqLineTpCd_CDIdx++;
                    }
                // QC#55710
                } else if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) && sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                    if (sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.USED_LOCAL_RETURN)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                        prchReqLineTpCd_CDIdx++;
                    }
                } else if (PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                    if (sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.LOCAL_RETURN)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                        prchReqLineTpCd_CDIdx++;
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                    prchReqLineTpCd_CDIdx++;
                }
            }
            // Line Type
            if (PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_SE, PRCH_REQ_LINE_TP.LOCAL_RETURN);
            } else if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_G)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_SE, PRCH_REQ_LINE_TP.LOCAL_RETURN);
            } else if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_SE, PRCH_REQ_LINE_TP.USED_LOCAL_RETURN);
            } else {
                sMsg.A.no(index).prchReqLineTpCd_SE.clear();
            }
        }
    }
    // END 2017/10/25 S.Katsuma QC#21896 ADD

    // QC#26662 Update 2018/08/20. Add Function of special Update mode. 
    // START 2023/03/22 T.Kuroda [QC#61204, ADD]
    public boolean isSpecialUpdate(){
//    private boolean isSpecialUpdate(){
    // END   2023/03/22 T.Kuroda [QC#61204, ADD]
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);

        if(funcList.contains(FUNC_ID_SPEC_UPDATE)){
            return true;
        } else {
            return false;
        }
    }

    // START 2018/10/29 M.Naito [QC#28965,ADD]
    /**
     * doProcess_NPAL1080Scrn00_OnChange_RequestLineType
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_OnChange_RequestLineType(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        int idx = cMsg.xxNum_D1.getValueInt();

        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        idx = NPAL1080CommonLogic.convertIndexFromCmsgOntoSmsg(cMsg, sMsg, idx);
        // QC#55710
        if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
            NPAL1080CommonLogic.findDefaultWh(getGlobalCompanyCode(), sMsg, idx);
        }

        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
    // END 2018/10/29 M.Naito [QC#28965,ADD]

    // QC#29299 Add method.
    /**
     * doProcess_NPAL1080_NMAL6800
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080_NMAL6800(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        // QC#61128 Delete Start
        //int idx = NPAL1080CommonLogic.convertIndexFromCmsgOntoSmsg(cMsg, sMsg, cMsg.xxNum_D1.getValueInt());

        //if (!NPAL1080CommonLogic.isSourceEmergencyWh(getGlobalCompanyCode(), sMsg.prchReqTpCd_SE.getValue())) {
        //    NPAL1080CommonLogic.getProcurmentSource(getGlobalCompanyCode(), sMsg, idx);
        //}
        // QC#61128 Delete End

        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    // QC#57659
    private void doProcess_NPAL1080Scrn00_OnChange_RequestServiceLevel(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        // Copy cMsg => sMsg
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        String shpgSvcLvlCd = sMsg.shpgSvcLvlCd_SE.getValue();
        String prchReqTp = sMsg.prchReqTpCd_SE.getValue();

        if (ZYPCommonFunc.hasValue(prchReqTp) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTp) && SHPG_SVC_LVL.SCHD_DELIVERY.equals(shpgSvcLvlCd)) {

            NPAL1080CommonLogic.getRequestReceivingDateAndTimePulldownListByRequisitionType(sMsg, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SE, shpgSvcLvlCd);
        // START 2023/10/20 G.Quan [QC#61494, ADD]
        } else if (ZYPCommonFunc.hasValue(prchReqTp) &&(PRCH_REQ_TP.RUSH.equals(prchReqTp) || PRCH_REQ_TP.STANDARD.equals(prchReqTp) || PRCH_REQ_TP.MIN_MAX.equals(prchReqTp))) {
            NPAL1080CommonLogic.defaultRequestDateByCalcualtionSetting(sMsg, getGlobalCompanyCode(), true);
        }
        // END 2023/10/20 G.Quan [QC#61494, ADD]

        // Copy sMsg => cMsg
        NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    // START 2023/03/17 T.Kuroda [QC#61204, ADD]
    /**
     * Upload
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_Upload(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        NPAL1080CommonLogic.upload(cMsg, sMsg, getGlobalCompanyCode());

    }

    /**
     * Template Download
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    private void doProcess_NPAL1080Scrn00_TemplateDownload(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        NPAL1080CommonLogic.templateDownload(cMsg, sMsg, getGlobalCompanyCode());

    }
    // END   2023/03/17 T.Kuroda [QC#61204, ADD]

}
