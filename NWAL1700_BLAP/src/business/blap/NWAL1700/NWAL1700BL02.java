/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1700;

import static business.blap.NWAL1700.constant.NWAL1700Constant.CHK_A;
import static business.blap.NWAL1700.constant.NWAL1700Constant.CREDIT_APPROVAL;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DB_FLAG_DELETE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DB_FLAG_EXIST;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DB_FLAG_INSERT;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DI_CHECK_REQUIRED;
import static business.blap.NWAL1700.constant.NWAL1700Constant.FIXED_ASSET;
import static business.blap.NWAL1700.constant.NWAL1700Constant.LEVEL_H;
import static business.blap.NWAL1700.constant.NWAL1700Constant.LEVEL_L;
import static business.blap.NWAL1700.constant.NWAL1700Constant.LOC_GRP_CD_CUSA;
import static business.blap.NWAL1700.constant.NWAL1700Constant.MODE_CREATE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.MODE_EDIT;
import static business.blap.NWAL1700.constant.NWAL1700Constant.OUT_OF_WAREHOUSE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.PROFITABILITY;
import static business.blap.NWAL1700.constant.NWAL1700Constant.SUPPLY_ABUSE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.VALIDATION_APPROVAL;
import static business.blap.NWAL1700.constant.NWAL1700Constant.ZZM9000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1700.common.NWAL1700CommonLogic;
import business.db.LOC_ROLE_TPTMsg;
import business.db.LOC_ROLE_TPTMsgArray;
import business.db.PRC_LIST_TPTMsg;
import business.db.PRC_LIST_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRINT_STYLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PROC_NODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ISTL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1700BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#12143
 * 2016/08/10   Fujitsu         M.Yamada        Update          QC#11418
 * 2017/03/07   Fujitsu         W.Honda         Update          QC#16855
 * 2017/09/21   Fujitsu         T.Murai         Update          QC#16346(Sol#373)
 * 2017/12/14   Fujitsu         Mz.Takahashi    Update          QC#19804(Sol349)
 * 2018/11/08   Fujitsu         H.Kumagai       Update          QC#29127
 * 2018/12/13   Fujitsu         C.Hara          Update          QC#29576
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWAL1700BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1700CMsg bizMsg = (NWAL1700CMsg) cMsg;
            NWAL1700SMsg glblMsg = (NWAL1700SMsg) sMsg;

            if ("NWAL1700_INIT".equals(screenAplID)) {
                doProcess_NWAL1700_INIT(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Add_Line".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Add_Line(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Remove_Line".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Remove_Line(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_AJE".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_AJE(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_ARTran".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_ARTran(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_BillToAccount".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_BillToAccount(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_BillToLocation".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_BillToLocation(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_PriceList".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_PriceList(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_ServicePriceList".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_ServicePriceList(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_Search_Carrier".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_Search_Carrier(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_OnChange_LineCategory".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_OnChange_LineCategory(bizMsg, glblMsg);

            } else if ("NWAL1700Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700_INIT(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        createPulldown(bizMsg);
        EZDMsg.copy(bizMsg, null, glblMsg, null);

        // Price List type
        PRC_LIST_TPTMsg tp = new PRC_LIST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tp.glblCmpyCd, getGlobalCompanyCode());
        // PRC_LIST_TPTMsgArray resultList = (PRC_LIST_TPTMsgArray)
        // S21CodeTableAccessor.findByCondition(tp);
        PRC_LIST_TPTMsgArray resultList = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(tp);

        for (int i = 0; i < resultList.length(); i++) {
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            if (i >= glblMsg.B.length()) {
                break;
            }
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            tp = (PRC_LIST_TPTMsg) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcListTpDescTxt_B, tp.prcListTpDescTxt.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcListTpCd_B, tp.prcListTpCd.getValue());
        }
        glblMsg.B.setValidCount(resultList.length());

        // SystemName
        S21SsmEZDResult systemNameResultList = NWAL1700Query.getInstance().getSystemName(bizMsg);
        List<Map<String, String>> systenNamelist = (List<Map<String, String>>) systemNameResultList.getResultObject();
        for (int i = 0; i < systenNamelist.size(); i++) {
            Map<String, String> mapData = systenNamelist.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).omXtrnlSysDescTxt_C, mapData.get("OM_XTRNL_SYS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).omXtrnlSysCd_C, mapData.get("OM_XTRNL_SYS_CD"));
        }
        glblMsg.C.setValidCount(systenNamelist.size());

        // Screen
        S21SsmEZDResult screenResultList = NWAL1700Query.getInstance().getScreenList(bizMsg);
        List<Map<String, String>> screenList = (List<Map<String, String>>) screenResultList.getResultObject();
        for (int i = 0; i < screenList.size(); i++) {
            Map<String, String> mapData = screenList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).bizAppId_D, mapData.get("BIZ_APP_ID"));
        }
        glblMsg.D.setValidCount(screenList.size());

        // Location Role
        LOC_ROLE_TPTMsg locRole = new LOC_ROLE_TPTMsg();
        locRole.locGrpCd.setValue(LOC_GRP_CD_CUSA);
        // 2018/12/13 QC#29576 Add Start
        locRole.glblCmpyCd.setValue(getGlobalCompanyCode());
        // 2018/12/13 QC#29576 Add End
        LOC_ROLE_TPTMsgArray locRoleResultList = (LOC_ROLE_TPTMsgArray) S21CodeTableAccessor.findByCondition(locRole);

        for (int i = 0; i < locRoleResultList.length(); i++) {
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            if (i >= glblMsg.E.length()) {
                break;
            }
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            locRole = (LOC_ROLE_TPTMsg) locRoleResultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).locRoleTpCd_E, locRole.locRoleTpCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).locRoleTpDescTxt_E, locRole.locRoleTpDescTxt);
        }
        glblMsg.E.setValidCount(locRoleResultList.length());

        if (MODE_EDIT.equals(bizMsg.xxModeCd.getValue())) {
            // Edit
            searchScreenValue(bizMsg, glblMsg);
        }
        EZDMsg.copy(glblMsg, null, bizMsg, null);

        if (MODE_CREATE.equals(bizMsg.xxModeCd.getValue())) {
            NWAL1700CommonLogic.setInitScreenValue(bizMsg);
            NWAL1700CommonLogic.createNewDetailLine(bizMsg);
        }
    }

    private void searchScreenValue(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        // Header
        S21SsmEZDResult headerResult = NWAL1700Query.getInstance().getHeader(bizMsg);
        Map<String, Object> headerMap = (Map<String, Object>) headerResult.getResultObject();

        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdCatgCd, (String) headerMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd, (String) headerMap.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpNm, (String) headerMap.get("DS_ORD_TP_NM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ordProcTpCd, (String) headerMap.get("ORD_PROC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdRsnGrpCd, (String) headerMap.get("DS_ORD_RSN_GRP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpDescTxt, (String) headerMap.get("DS_ORD_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.lineBizTpCd, (String) headerMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.effFromDt, (String) headerMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.effThruDt, (String) headerMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.actvFlg, (String) headerMap.get("ACTV_FLG"));

        //2016/04/04 S21_NA#6620 MOD Start ------------
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpNoteTxt, (String) headerMap.get("DS_ORD_TP_NOTE_TXT"));
        //2016/04/04 S21_NA#6620 MOD End --------------

        // hidden
        // -----------2016/04/04 S21_NA#5919 Add Start
        S21SsmEZDResult getOrderProcTpResult = NWAL1700Query.getInstance().getOrderProcTp(glblMsg.ordProcTpCd.getValue());
        Map<String, Object> getOrderProcTp = (Map<String, Object>) getOrderProcTpResult.getResultObject();
        String revRecogMethCd = (String) getOrderProcTp.get("REV_RECOG_METH_CD");
        String cpoOrdtpCd = (String) getOrderProcTp.get("CPO_ORD_TP_CD");
        ZYPEZDItemValueSetter.setValue(glblMsg.revRecogMethCd, revRecogMethCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.cpoOrdTpCd, cpoOrdtpCd);
        // -----------2016/04/04 S21_NA#5919 Add End

        // Workflow Control
        ZYPEZDItemValueSetter.setValue(glblMsg.vldApvlNodeUsgFlg, (String) headerMap.get("VLD_APVL_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.vldApvlNodePrflCd, (String) headerMap.get("VLD_APVL_NODE_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.diChkNodeUsgFlg, (String) headerMap.get("DI_CHK_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.diChkNodePrflCd, (String) headerMap.get("DI_CHK_NODE_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prftApvlNodeUsgFlg, (String) headerMap.get("PRFT_APVL_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prftApvlNodePrflCd, (String) headerMap.get("PRFT_APVL_NODE_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.crApvlNodeUsgFlg, (String) headerMap.get("CR_APVL_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.crApvlNodePrflCd, (String) headerMap.get("CR_APVL_NODE_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.assetNodeUsgFlg, (String) headerMap.get("ASSET_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.assetNodePrflCd, (String) headerMap.get("ASSET_NODE_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.outOfWhNodeUsgFlg, (String) headerMap.get("OUT_OF_WH_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.outOfWhNodePrflCd, (String) headerMap.get("OUT_OF_WH_NODE_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAbuseNodeUsgFlg, (String) headerMap.get("SPLY_ABUSE_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAbuseNodePrflCd, (String) headerMap.get("SPLY_ABUSE_NODE_PRFL_CD"));
        // add start 2023/04/25 QC#61337
        ZYPEZDItemValueSetter.setValue(glblMsg.manPrcNodeUsgFlg, (String) headerMap.get("MAN_PRC_NODE_USG_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.manPrcNodePrflCd, (String) headerMap.get("MAN_PRC_NODE_PRFL_CD"));
        // add end 2023/04/25 QC#61337

        // Accounting
        ZYPEZDItemValueSetter.setValue(glblMsg.dsInvTpCd, (String) headerMap.get("DS_INV_TP_CD"));
        // 2016/04/04 S21_NA#6353 MOD Start ------------
        // Mod Start 2016/08/04 QC#12143
        //        ZYPEZDItemValueSetter.setValue(glblMsg.dsInvTpDescTxt, (String) headerMap.get("DS_INV_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dsInvTpNm, (String) headerMap.get("DS_INV_TP_NM"));
        // Mod End 2016/08/04 QC#12143
        // 2016/04/04 S21_NA#6353 MOD Start ------------
        ZYPEZDItemValueSetter.setValue(glblMsg.autoCancOrdFlg, (String) headerMap.get("AUTO_CANC_ORD_FLG"));

        // Defaults
        ZYPEZDItemValueSetter.setValue(glblMsg.defPrcCatgCd, (String) headerMap.get("DEF_PRC_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcCatgDescTxt_PR, (String) headerMap.get("PRICE_PRC_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.defMaintPrcCatgCd, (String) headerMap.get("DEF_MAINT_PRC_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcCatgDescTxt_SP, (String) headerMap.get("SPRICE_PRC_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.revFcstCd, (String) headerMap.get("DEF_REV_FCST_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.frtCondCd, (String) headerMap.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.invPrintStyleCd, (String) headerMap.get("INV_PRINT_STYLE_CD"));

        
        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        ZYPEZDItemValueSetter.setValue(glblMsg.trtyGrpTpTxt, (String) headerMap.get("TRTY_GRP_TP_TXT"));
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)

        // Add 2020/04/24 QC#56638 Start
        ZYPEZDItemValueSetter.setValue(glblMsg.baseLocTxt, (String) headerMap.get("BASE_LOC_TXT"));
        // Add 2020/04/24 QC#56638 End

        ZYPEZDItemValueSetter.setValue(glblMsg.defBillToCustAcctCd, (String) headerMap.get("DEF_BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dsAcctNm, (String) headerMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.defBillToCustCd, (String) headerMap.get("DEF_BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.locDescTxt, (String) headerMap.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.defInstlTpCd, (String) headerMap.get("DEF_INSTL_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.defShpgSvcLvlCd, (String) headerMap.get("DEF_SHPG_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.defCarrSvcLvlCd, (String) headerMap.get("DEF_CARR_SVC_LVL_CD"));
        // 2016/04/04 S21_NA#6353 Add Start ----------
        ZYPEZDItemValueSetter.setValue(glblMsg.carrSvcLvlDescTxt, (String) headerMap.get("CARR_SVC_LVL_DESC_TXT"));
        // 2016/04/04 S21_NA#6353 Add End ------------
        ZYPEZDItemValueSetter.setValue(glblMsg.dropShipAvalFlg, (String) headerMap.get("DROP_SHIP_AVAL_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime, (String) headerMap.get("OT_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone, (String) headerMap.get("OT_EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_TP, (String) headerMap.get("TP_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_TP, (String) headerMap.get("TP_EZUPTIMEZONE"));

        // Line
        S21SsmEZDResult lineResult = NWAL1700Query.getInstance().getLineCategory(bizMsg);
        List<Map> line = (List<Map>) lineResult.getResultObject();
        int recordcount = 0;
        for (int i = 0; i < line.size(); i++) {

            Map mapData = line.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).lineProcDfnSortNum_A, (BigDecimal) mapData.get("LINE_PROC_DFN_SORT_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdLineCatgCd_A, (String) mapData.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ordProcTpCd_A, (String) mapData.get("ORD_PROC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).primLineCatgFlg_A, (String) mapData.get("PRIM_LINE_CATG_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).rmaPrimLineCatgFlg_A, (String) mapData.get("RMA_PRIM_LINE_CATG_FLG")); // Add 2017/09/11 S21_NA#16346
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ajeAcctBatCd_A, (String) mapData.get("AJE_ACCT_BAT_CD"));
            // 2016/04/04 S21_NA#6353 MOD Start --------
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ajeAcctBatDescTxt_A, (String) mapData.get("AJE_ACCT_BAT_DESC_TXT"));
            // 2016/04/04 S21_NA#6353 MOD End ----------
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdLineDrctnCd_A, (String) mapData.get("DS_ORD_LINE_DRCTN_CD")); // Add 2017/09/11 S21_NA#16346
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdLineDrctnNm_A, (String) mapData.get("DS_ORD_LINE_DRCTN_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effFromDt_A, (String) mapData.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effThruDt_A, (String) mapData.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).revFcstCd_A, (String) mapData.get("DEF_REV_FCST_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).actvFlg_A, (String) mapData.get("ACTV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRowId_A, DB_FLAG_EXIST + recordcount);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_A, (String) mapData.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_A, (String) mapData.get("EZUPTIMEZONE"));
            recordcount++;
            // -----------2016/04/04 S21_NA#5919 Mod Start
            //            if (recordcount >= glblMsg.A.length()) {
            //                bizMsg.setMessageInfo(NZZM0001W);
            //                break;
            //            }
            // -----------2016/04/04 S21_NA#5919 MOD Start
        }
        glblMsg.A.setValidCount(recordcount);

        // Price List Type
        S21SsmEZDResult priceListResult = NWAL1700Query.getInstance().getPriceListType(bizMsg);
        List<Map<String, String>> priceList = (List<Map<String, String>>) priceListResult.getResultObject();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            boolean ckFlag = false;
            for (int j = 0; j < priceList.size(); j++) {
                Map<String, String> mapData = priceList.get(j);
                String pltPrcListTpCd = (String) mapData.get("PLT_PRC_LIST_TP_CD");
                // String pltPrcListTpNm = (String)
                // mapData.get("PLT_PRC_LIST_TP_NM");2016/04/04
                // S21_NA#5919 MOD
                String plrPrcListCd = (String) mapData.get("PLR_PRC_LIST_TP_CD");
                String plrDsOrdTpCd = (String) mapData.get("PLR_DS_ORD_TP_CD");
                if (glblMsg.B.no(i).prcListTpCd_B.getValue().equals(pltPrcListTpCd)) {
                    if (ZYPCommonFunc.hasValue(plrPrcListCd) || ZYPCommonFunc.hasValue(plrDsOrdTpCd)) {
                        glblMsg.B.no(i).xxChkBox_B.setValue(ZYPConstant.CHKBOX_ON_Y);
                        ckFlag = true;
                        // 2016/04/04 S21_NA#5919 MOD Start
                        break;
                        // 2016/04/04 S21_NA#5919 MOD End
                    }
                }
            }
            if (!ckFlag) {
                glblMsg.B.no(i).xxChkBox_B.setValue(ZYPConstant.FLG_OFF_N);
            }
        }

        // System
        S21SsmEZDResult publishResult = NWAL1700Query.getInstance().getPublishToExternalSource(bizMsg);
        List<Map> publish = (List<Map>) publishResult.getResultObject();
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            boolean ckFlag = false;
            for (int j = 0; j < publish.size(); j++) {
                Map mapData = publish.get(j);
                String oxsSysCd = (String) mapData.get("OXS_OM_XTRNL_SYS_CD");
                // String oxsSysNm = (String)
                // mapData.get("OXS_OM_XTRNL_SYS_NM");2016/04/04
                // S21_NA#5919 MOD Start
                String xsrOrdTpcd = (String) mapData.get("XSR_DS_ORD_TP_CD");
                String xsrOmXt = (String) mapData.get("XSR_OM_XTRNL_SYS_CD");
                if (glblMsg.C.no(i).omXtrnlSysCd_C.getValue().equals(oxsSysCd)) {
                    if (ZYPCommonFunc.hasValue(xsrOrdTpcd) || ZYPCommonFunc.hasValue(xsrOmXt)) {
                        glblMsg.C.no(i).xxChkBox_C.setValue(ZYPConstant.CHKBOX_ON_Y);
                        ckFlag = true;
                        // 2016/04/04 S21_NA#5919 MOD Start
                        break;
                        // 2016/04/04 S21_NA#5919 MOD End
                    }
                }
            }
            if (!ckFlag) {
                glblMsg.C.no(i).xxChkBox_C.setValue(ZYPConstant.FLG_OFF_N);
            }
        }

        S21SsmEZDResult orderEntoryResult = NWAL1700Query.getInstance().getOrderEntryScreen(bizMsg);
        List<Map> orderEntory = (List<Map>) orderEntoryResult.getResultObject();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            boolean ckFlag = false;
            for (int j = 0; j < orderEntory.size(); j++) {
                Map mapData = orderEntory.get(j);
                String oesAppId = (String) mapData.get("OES_BIZ_APP_ID");
                String adoDsOrd = (String) mapData.get("ADO_DS_ORD_TP_CD");
                String adoBizApp = (String) mapData.get("ADO_BIZ_APP_ID");
                if (glblMsg.D.no(i).bizAppId_D.getValue().equals(oesAppId)) {
                    if (ZYPCommonFunc.hasValue(adoDsOrd) || ZYPCommonFunc.hasValue(adoBizApp)) {
                        glblMsg.D.no(i).xxChkBox_D.setValue(ZYPConstant.CHKBOX_ON_Y);
                        ckFlag = true;
                        // 2016/04/04 S21_NA#5919 MOD Start
                        break;
                        // 2016/04/04 S21_NA#5919 MOD End
                    }
                }
            }
            if (!ckFlag) {
                glblMsg.D.no(i).xxChkBox_D.setValue(ZYPConstant.FLG_OFF_N);
            }
        }

        S21SsmEZDResult locationRoleResult = NWAL1700Query.getInstance().getLocationRole(bizMsg);
        List<Map> locationRole = (List<Map>) locationRoleResult.getResultObject();
        for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
            boolean ckFlag = false;
            for (int j = 0; j < locationRole.size(); j++) {
                Map mapData = locationRole.get(j);
                String lrtRoleTpCd = (String) mapData.get("LRT_LOC_ROLE_TP_CD");
                // String locRoleTpNm = (String)
                // mapData.get("LOC_ROLE_TP_NM"); 2016/04/04
                // S21_NA#5919 MOD Start
                String lrrDsOrdTpCd = (String) mapData.get("LRR_DS_ORD_TP_CD");
                String lrrLocRoletpCd = (String) mapData.get("LRR_LOC_ROLE_TP_CD");
                if (glblMsg.E.no(i).locRoleTpCd_E.getValue().equals(lrtRoleTpCd)) {
                    if (ZYPCommonFunc.hasValue(lrrDsOrdTpCd) || ZYPCommonFunc.hasValue(lrrLocRoletpCd)) {
                        glblMsg.E.no(i).xxChkBox_E.setValue(ZYPConstant.CHKBOX_ON_Y);
                        ckFlag = true;
                        // 2016/04/04 S21_NA#5919 MOD Start
                        break;
                        // 2016/04/04 S21_NA#5919 MOD End
                    }
                }
            }
            if (!ckFlag) {
                glblMsg.E.no(i).xxChkBox_E.setValue(ZYPConstant.FLG_OFF_N);
            }
        }

        // Order Category Value Set
        S21SsmEZDResult categoryValueSetResultList = NWAL1700Query.getInstance().getCategoryValueSet(bizMsg);
        List<Map> categoryValueList = (List<Map>) categoryValueSetResultList.getResultObject();
        for (int i = 0; i < categoryValueList.size(); i++) {
            Map mapData = categoryValueList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(i).ordCatgCtxTpCd_F, (String) mapData.get("ORD_CATG_CTX_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(i).dsOrdCatgDescTxt_F, (String) mapData.get("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(i).dsOrdTpDescTxt_F, (String) mapData.get("DS_ORD_TP_DESC_TXT"));
        }
        glblMsg.F.setValidCount(categoryValueList.size());

        // Line Value Set
        // 2018/11/08 Update Start QC#29127
        String slsDt = ZYPDateUtil.getSalesDate();
        S21SsmEZDResult lineValueSetResultList = NWAL1700Query.getInstance().getLineValueSet(glblMsg, slsDt);
        // 2018/11/08 Update End QC#29127
        List<Map> lineValueList = (List<Map>) lineValueSetResultList.getResultObject();
        for (int i = 0; i < lineValueList.size(); i++) {
            Map mapData = lineValueList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(i).ordLineCtxTpCd_G, (String) mapData.get("ORD_LINE_CTX_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(i).dsOrdLineCatgDescTxt_G, (String) mapData.get("DS_ORD_LINE_CATG_DESC_TXT"));
        }
        glblMsg.G.setValidCount(lineValueList.size());
    }

    private void createPulldown(NWAL1700CMsg bizMsg) {
        // Category
        ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, bizMsg.dsOrdCatgCd_P, bizMsg.dsOrdCatgDescTxt_P);

        //2016/04/04 S21_NA#6536 MOD Start --------------
        // WorkFlow(Header)
        S21SsmEZDResult workFlowPulldownList = NWAL1700Query.getInstance().getWorkFlowPulldownList(LEVEL_H);
        //2016/04/04 S21_NA#6536 MOD End --------------
        if (workFlowPulldownList.isCodeNormal()) {

            List<Map> workFlowList = (List<Map>) workFlowPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.ordProcTpCd_P, bizMsg.ordProcTpDescTxt_P, workFlowList, "ORD_PROC_TP_CD", "ORD_PROC_TP_DESC_TXT");
        }

        //2016/04/04 S21_NA#6536 MOD Start --------------
        // WorkFlow(Line)
        workFlowPulldownList = NWAL1700Query.getInstance().getWorkFlowPulldownList(LEVEL_L);
        if (workFlowPulldownList.isCodeNormal()) {

            List<Map> workFlowList = (List<Map>) workFlowPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.ordProcTpCd_PL, bizMsg.ordProcTpDescTxt_PL, workFlowList, "ORD_PROC_TP_CD", "ORD_PROC_TP_DESC_TXT");
        }
        //2016/04/04 S21_NA#6536 MOD End --------------

        // Sub Reason
        S21SsmEZDResult subReasonPulldownList = NWAL1700Query.getInstance().getSubReasonPulldownList();
        if (subReasonPulldownList.isCodeNormal()) {

            List<Map> subReasonList = (List<Map>) subReasonPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.dsOrdRsnGrpCd_P, bizMsg.dsOrdRsnGrpDescTxt_P, subReasonList, "DS_ORD_RSN_GRP_CD", "DS_ORD_RSN_GRP_DESC_TXT");
        }

        // Line of Business
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_P, bizMsg.lineBizTpDescTxt_P);

        // Validation Approval
        S21SsmEZDResult workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(VALIDATION_APPROVAL);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.vldApvlNodePrflCd_PV, bizMsg.ordProcNodePrflNm_PV, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // DI
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(DI_CHECK_REQUIRED);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.diChkNodePrflCd_PD, bizMsg.ordProcNodePrflNm_PD, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // Profitability
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(PROFITABILITY);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.prftApvlNodePrflCd_PP, bizMsg.ordProcNodePrflNm_PP, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // Credit Approval
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(CREDIT_APPROVAL);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.crApvlNodePrflCd_PC, bizMsg.ordProcNodePrflNm_PC, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // Fixed Asset
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(FIXED_ASSET);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.assetNodePrflCd_PF, bizMsg.ordProcNodePrflNm_PF, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // Out of Wh
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(OUT_OF_WAREHOUSE);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.outOfWhNodePrflCd_PO, bizMsg.ordProcNodePrflNm_PO, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // Supply Abuse
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(SUPPLY_ABUSE);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.splyAbuseNodePrflCd_PS, bizMsg.ordProcNodePrflNm_PS, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        // add start 2023/04/25 QC#61337
        // Manual Price Approval
        workFlowControlsPulldownList = NWAL1700Query.getInstance().getWorkFlowControlsPulldownList(ORD_PROC_NODE.MAN_PRC_APPROVAL);
        if (workFlowControlsPulldownList.isCodeNormal()) {

            List<Map> workFlowControlsList = (List<Map>) workFlowControlsPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.manPrcNodePrflCd_PM, bizMsg.ordProcNodePrflNm_PM, workFlowControlsList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }
        // add end 2023/04/25 QC#61337

        // Forecasting Code
        S21SsmEZDResult forecastingPulldownList = NWAL1700Query.getInstance().getForecastingPulldownList();
        if (forecastingPulldownList.isCodeNormal()) {
            List<Map> forecastingList = (List<Map>) forecastingPulldownList.getResultObject();
            NWAL1700CommonLogic.createPulldownList(bizMsg.revFcstCd_P, bizMsg.revFcstDescTxt_P, forecastingList, "REV_FCST_CD", "REV_FCST_DESC_TXT");
        }

        // Freight Terms
        ZYPCodeDataUtil.createPulldownList(FRT_COND.class, bizMsg.frtCondCd_P, bizMsg.frtCondDescTxt_P);

        // invoice Print Style
        ZYPCodeDataUtil.createPulldownList(INV_PRINT_STYLE.class, bizMsg.invPrintStyleCd_P, bizMsg.invPrintStyleDescTxt_P);

        // Del Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        // Territory Group Type
        //S21SsmEZDResult trtyGrpTpPulldownList = NWAL1700Query.getInstance().getTrtyGrpTpPulldownList();
        //if (trtyGrpTpPulldownList.isCodeNormal()) {
        //    List<Map> trtyGrpTpList = (List<Map>) trtyGrpTpPulldownList.getResultObject();
        //    NWAL1700CommonLogic.createPulldownList(bizMsg.trtyGrpTpCd_P, bizMsg.trtyGrpTpDescTxt_P, trtyGrpTpList, "TRTY_GRP_TP_CD", "TRTY_GRP_TP_DESC_TXT");
        //}
        // Add End 2017/03/07 QC#16855
        // Del End 2017/12/14 QC#19804(Sol349)

        // Install Type
        ZYPCodeDataUtil.createPulldownList(SVC_ISTL_TP.class, bizMsg.defInstlTpCd_P, bizMsg.svcIstlTpDescTxt_P);

        // Shipping Service Level
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, bizMsg.defShpgSvcLvlCd_P, bizMsg.shpgSvcLvlDescTxt_P);

        // Line category
        S21SsmEZDResult lineCategoryPulldownList = NWAL1700Query.getInstance().getLineCategoryPulldownList();
        if (lineCategoryPulldownList.isCodeNormal()) {

            List<Map> lineCategoryList = (List<Map>) lineCategoryPulldownList.getResultObject();
            // NWAL1700CommonLogic.createPulldownList(bizMsg.dsOrdLineCatgCd_PL,
            // bizMsg.dsOrdLineCatgNm_PL, lineCategoryList,
            // "DS_ORD_LINE_CATG_CD", "DS_ORD_LINE_CATG_DESC_TXT");
            NWAL1700CommonLogic.createPulldownList(bizMsg.dsOrdLineCatgCd_PL, bizMsg.dsOrdLineCatgDescTxt_PL, lineCategoryList, "DS_ORD_LINE_CATG_CD", "DS_ORD_LINE_CATG_DESC_TXT");
        }

        // Add 2020/04/24 QC#56638 Start
        // Salesrep Defaulting
        String varChar = ZYPCodeDataUtil.getVarCharConstValue("NWAL1700_BASE_LOC_TXT", getGlobalCompanyCode());
        String[] varStrs = null;
        if (ZYPCommonFunc.hasValue(varChar)) {
            varStrs = varChar.split(",");
        } else {
            varStrs = new String[] {"Ship To Location", "Sold To Location" };
        }
        for (int i = 0; i < varStrs.length; i++) {
            if (i >= bizMsg.baseLocTxt_PC.length()) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.baseLocTxt_PC.no(i), varStrs[i]);
            ZYPEZDItemValueSetter.setValue(bizMsg.baseLocTxt_PD.no(i), varStrs[i]);
        }
        // Add 2020/04/24 QC#56638
    }

    /**
     * Add_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Add_Line(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        NWAL1700CommonLogic.createNewDetailLine(bizMsg);
    }

    private void doProcess_NWAL1700Scrn00_CMN_Clear(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        NWAL1700CommonLogic.clearMsg(bizMsg);
        doProcess_NWAL1700_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_CMN_Reset(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        if (MODE_CREATE.equals(bizMsg.xxModeCd.getValue())) {
            NWAL1700CommonLogic.clearMsg(bizMsg);
        }
        doProcess_NWAL1700_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_CMN_Save(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        searchScreenValue(bizMsg, glblMsg);
        EZDMsg.copy(glblMsg, null, bizMsg, null);
    }

    /**
     * Remove_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Remove_Line(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, FLG_ON_Y);
        for (int idx : selectRows) {
            String recordType = bizMsg.A.no(idx).xxRowId_A.getValue();
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            if (ZYPCommonFunc.hasValue(recordType)) {
                if (DB_FLAG_INSERT.equals(recordType)) {
                    continue;
                }
            } else {
                continue;
            }
            // 2016/04/04 S21_NA#5919 MOD End --------------

            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                String glbalRecordType = glblMsg.A.no(j).xxRowId_A.getValue();
                if (recordType.equals(glbalRecordType)) {
                    glblMsg.A.no(j).xxRowId_A.setValue(DB_FLAG_DELETE);
                }
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
    }

    /**
     * Search_AJE Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_AJE(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        // 2016/04/04 S21_NA#6354 Add Start --------------
        int selectIndex = bizMsg.xxRowNum.getValueInt();
        NWAL1700CommonLogic.searchAJE(bizMsg, this.getGlobalCompanyCode(), selectIndex);
        // 2016/04/04 S21_NA#6354 Add End --------------
    }

    /**
     * Search_ARTran Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_ARTran(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        NWAL1700CommonLogic.searchARTran(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * Search_BillToAccount Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_BillToAccount(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        // 2016/04/04 S21_NA#6354 Add Start --------------
        if (!ZYPCommonFunc.hasValue(bizMsg.defBillToCustAcctCd.getValue())) {
            // bizMsg.dsAcctNm.clear();
            bizMsg.defBillToCustAcctCd.setErrorInfo(1, ZZM9000E, new String[] {"Bill To Account #" });
            return;
        }
        // 2016/04/04 S21_NA#6354 Add End --------------
        NWAL1700CommonLogic.searchBillToAccount(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * Search_BillToLocation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_BillToLocation(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        // 2016/04/04 S21_NA#6354 Add Start --------------
        if (!ZYPCommonFunc.hasValue(bizMsg.defBillToCustCd.getValue())) {
            // bizMsg.locDescTxt.clear();
            bizMsg.defBillToCustCd.setErrorInfo(1, ZZM9000E, new String[] {"Bill To Location" });
            return;
        }
        // 2016/04/04 S21_NA#6354 Add End --------------
        NWAL1700CommonLogic.searchBillToLocation(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * Search_PriceList Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_PriceList(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        // 2016/04/04 S21_NA#6354 Add Start --------------
        if (!ZYPCommonFunc.hasValue(bizMsg.defPrcCatgCd.getValue())) {
            // bizMsg.prcCatgDescTxt_PR.clear();
            bizMsg.defPrcCatgCd.setErrorInfo(1, ZZM9000E, new String[] {"Price List" });
            return;
        }
        // 2016/04/04 S21_NA#6354 Add End --------------
        NWAL1700CommonLogic.searchPriceList(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * Search_ServicePriceList Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_ServicePriceList(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        // 2016/04/04 S21_NA#6354 Add Start --------------
        if (!ZYPCommonFunc.hasValue(bizMsg.defMaintPrcCatgCd.getValue())) {
            // bizMsg.prcCatgDescTxt_SP.clear();
            bizMsg.defMaintPrcCatgCd.setErrorInfo(1, ZZM9000E, new String[] {"Service Price List" });
            return;
        }
        // 2016/04/04 S21_NA#6354 Add End --------------
        NWAL1700CommonLogic.searchServicePriceList(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * Serach_Carrier Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_Search_Carrier(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        // 2016/04/04 S21_NA#6354 Add Start ------------
        if (!ZYPCommonFunc.hasValue(bizMsg.defCarrSvcLvlCd.getValue())) {
            // bizMsg.carrSvcLvlDescTxt.clear();
            bizMsg.defCarrSvcLvlCd.setErrorInfo(1, ZZM9000E, new String[] {"Carrier Service Level" });
            return;
        }
        // 2016/04/04 S21_NA#6354 Add End --------------
        NWAL1700CommonLogic.searchCarrier(bizMsg, this.getGlobalCompanyCode());
    }

    private void doProcess_NWAL1700Scrn00_OnChange_LineCategory(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        int selectIndex = bizMsg.xxRowNum.getValueInt();
        Map<String, String> resultMap = NWAL1700Query.getInstance().getTransactionType(bizMsg.A.no(selectIndex).dsOrdLineCatgCd_A.getValue());

        if (resultMap == null) {
            bizMsg.A.no(selectIndex).dsOrdLineDrctnCd_A.clear();
            bizMsg.A.no(selectIndex).dsOrdLineDrctnNm_A.clear();
            return;
        }
        String lineCatgCd = resultMap.get("DS_ORD_LINE_DRCTN_CD");
        String lineCatgNm = resultMap.get("DS_ORD_LINE_DRCTN_NM");
        if (ZYPCommonFunc.hasValue(lineCatgCd)) {
            bizMsg.A.no(selectIndex).dsOrdLineDrctnCd_A.setValue(lineCatgCd);
            bizMsg.A.no(selectIndex).dsOrdLineDrctnNm_A.setValue(lineCatgNm);
        } else {
            bizMsg.A.no(selectIndex).dsOrdLineDrctnCd_A.clear();
            bizMsg.A.no(selectIndex).dsOrdLineDrctnNm_A.clear();
        }
    }
}
