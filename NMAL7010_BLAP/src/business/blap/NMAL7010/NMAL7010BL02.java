/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7010;

import static business.blap.NMAL7010.constant.NMAL7010Constant.DEF_GRS_PRC_PCT;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0050E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0163E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8020E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8190E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NZZM0000E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NZZM0002I;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_CUST_AUDC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RTRN_FEE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import business.blap.NMAL7010.common.NMAL7010CommonLogic;
import business.blap.NMAL7010.constant.NMAL7010Constant;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.PRC_LIST_TPTMsg;
import business.file.NMAL7010F01FMsg;
import business.file.NMAL7010F02FMsg;
import business.file.NMAL7010F03FMsg;
import business.file.NMAL7010F04FMsg;
import business.file.NMAL7010F05FMsg;
import business.file.NMAL7010F06FMsg;
import business.file.NMAL7010F07FMsg;
import business.file.NMAL7010F08FMsg;
import business.file.NMAL7010F09FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_MDL_SEG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_ADDL_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DISP_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_EQUIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_IN_OUT_RG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LEASE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_BAND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PGM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SLS_VIS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_TIER_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_ZONE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TERM_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TIER_SVC_OFFER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SEG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NMAL7010BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/03/22   Fujitsu         Y.Kanefusa      Update          QC#4767
 * 2016/05/27   Fujitsu         W.Honda         Update          QC#8505
 * 2016/07/11   Fujitsu         W.Honda         Update          QC#10987
 * 2016/08/26   Fujitsu         R.Nakamura      Update          QC#3934
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2017/11/13   Fujitsu         A.Kosai         Update          QC#20203(Sol#257)
 * 2018/04/06   Fujitsu         R.Nakamura      Update          QC#22556
 * 2018/05/08   Fujitsu         T.Noguchi       Update          QC#20269
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 * 2018/12/18   Fujitsu         K.Kato          Update          QC#29661
 * 2019/03/01   Fujitsu         C.Hara          Update          QC#30114
 *</pre>
 */
public class NMAL7010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;
            NMAL7010SMsg glblMsg = (NMAL7010SMsg) sMsg;

            // Mod Start 2016/08/26 QC#3934
            if ("NMAL7010_INIT".equals(screenAplID)) {
                doProcess_NMAL7010_INIT(bizMsg, glblMsg);
                NMAL7010CommonLogic.setColData(bizMsg, glblMsg);
            
            } else if ("NMAL7010Scrn00_Add_SubPrc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Add_SubPrc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Add_CustAudc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Add_CustAudc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Add_PrcList".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Add_PrcList(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Add_QtyDisc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Add_QtyDisc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Add_TrxAudc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Add_TrxAudc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_CMN_Clear(bizMsg, glblMsg);
                NMAL7010CommonLogic.setColData(bizMsg, glblMsg);
                NMAL7010CommonLogic.clearFilter(bizMsg);

            } else if ("NMAL7010Scrn00_CMN_Reset".equals(screenAplID)) {
                NMAL7010CommonLogic.clearFilter(bizMsg);
                doProcess_NMAL7010Scrn00_CMN_Reset(bizMsg, glblMsg);
                NMAL7010CommonLogic.setColData(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_CMN_Download".equals(screenAplID)) {
                // QC#4486 Add
                doProcess_NMAL7010Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_DetailApply".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_DetailApply(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_OnChange_PrcDispRecTp".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnChange_PrcDispRecTp(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_OnChange_PrcListTp".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnChange_PrcListTp(bizMsg, glblMsg);
                NMAL7010CommonLogic.setColData(bizMsg, glblMsg);
                NMAL7010CommonLogic.clearFilter(bizMsg);

            } else if ("NMAL7010Scrn00_OnChange_QtyDisc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnChange_QtyDisc(bizMsg, glblMsg);

            // Add Start 2018/04/06 QC#22556
            } else if ("NMAL7010Scrn00_OnChange_PrcQlfyTp".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnChange_PrcQlfyTp(bizMsg, glblMsg);

            // Add Start 2018/04/06 QC#22556
            } else if ("NMAL7010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Search_PrcList".equals(screenAplID)) {
                NMAL7010CommonLogic.clearFilter(bizMsg);
                doProcess_NMAL7010Scrn00_Search_PrcList(bizMsg, glblMsg, false);

             } else if ("NMAL7010Scrn00_SelectAll".equals(screenAplID)) {
                 doProcess_NMAL7010Scrn00_SelectAll(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_TAB_PrcListVal".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_TAB_PrcListVal(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_TAB_TrxDrv".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_TAB_TrxDrv(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_UnselectAll(bizMsg, glblMsg);

            // QC#8505 2016/05/26 Del start
//            } else if ("NMAL7010Scrn00_Upload_CustAudc".equals(screenAplID)) {
//                doProcess_NMAL7010Scrn00_Upload_CustAudc(bizMsg, glblMsg);
//
//            } else if ("NMAL7010Scrn00_Upload_PrcList".equals(screenAplID)) {
//                doProcess_NMAL7010Scrn00_Upload_PrcList(bizMsg, glblMsg);
            // QC#8505 2016/05/26 Del start

            } else if ("NMAL7010Scrn00_OpenWin_QtyDisc".equals(screenAplID)) {
                // QC#2174 Add
                doProcess_NMAL7010Scrn00_OpenWin_QtyDisc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_TBLColumnSort".equals(screenAplID)) {
                // QC#4582 Add
                doProcess_NMAL7010Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NMAL7010_NMAL6800".equals(screenAplID)) {
                doProcess_NMAL7010_NMAL6800(bizMsg, glblMsg);

            } else if ("NMAL7010_NMAL7110".equals(screenAplID)) {
                doProcess_NMAL7010_NMAL7110(bizMsg, glblMsg);

            } else if ("NMAL7010_NWAL1130".equals(screenAplID)) {
                doProcess_NMAL7010_NWAL1130(bizMsg, glblMsg);

            } else if ("NMAL7010_NMAL7030".equals(screenAplID)) {
                // QC#2174 Add
                doProcess_NMAL7010_NMAL7030(bizMsg, glblMsg);

            } else if ("NMAL7010_NMAL7040".equals(screenAplID)) {
                doProcess_NMAL7010_NMAL7040(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_OnBlur_OpenWin_QualifierValueItem".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnBlur_OpenWin_QualifierValueItem(bizMsg, glblMsg);
                
            } else if ("NMAL7010Scrn00_OnBlur_OpenWin_PrcListMdse".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnBlur_OpenWin_PrcListMdse(bizMsg, glblMsg);
                
            } else if ("NMAL7010Scrn00_OnBlur_OpenWin_Mdse".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_OnBlur_OpenWin_Mdse(bizMsg, glblMsg);

            // 2017/11/13 QC#20203(Sol#257) Add Start
            } else if ("NMAL7010Scrn00_Copy_PrcList".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Copy_PrcList(bizMsg, glblMsg);

            // 2017/11/13 QC#20203(Sol#257) Add End

            // 2018/05/08 QC#20269 Add Start
            } else if ("NMAL7010Scrn00_DwldAsTemplate_PrcList".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_DwldAsTemplate_PrcList(bizMsg, glblMsg);
            // 2018/05/08 QC#20269 Add End

            // 2018/12/18 QC#29661 Add Start
            } else if ("NMAL7010Scrn00_MassUpd_PrcList".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_MassUpd_PrcList(bizMsg, glblMsg);
            // 2018/12/18 QC#29661 Add End
            } else {
                return;
            }
            // Mod End 2016/08/26 QC#3934
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010_INIT(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(glblMsg.E);
        ZYPTableUtil.clear(glblMsg.F);
        ZYPTableUtil.clear(glblMsg.G);
        ZYPTableUtil.clear(glblMsg.H);
        ZYPTableUtil.clear(glblMsg.I);
        ZYPTableUtil.clear(glblMsg.J);
        ZYPTableUtil.clear(glblMsg.Q);

        // QC#10987 2016/07/12 Add start
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_H1, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_H1, ZYPConstant.FLG_ON_Y);
        }
        // QC#10987 2016/07/12 Add end

        // Create Pull Down.
        createPullDown(bizMsg);

        GLBL_CMPYTMsg iTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(iTMsg.glblCmpyCd, getGlobalCompanyCode());
        GLBL_CMPYTMsg oTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByKey(iTMsg);
        if (oTMsg == null) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {getGlobalCompanyCode(), "Global Company"});
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.stdCcyCd, oTMsg.stdCcyCd);

        CCYTMsg ccyInTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyInTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ccyInTMsg.ccyCd, bizMsg.stdCcyCd);
        CCYTMsg ccyOutTMsg = (CCYTMsg) ZYPCodeDataUtil.findByKey(ccyInTMsg);
        if (ccyOutTMsg == null) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {getGlobalCompanyCode(), "Currency"});
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccyOutTMsg.aftDeclPntDigitNum);

        NMAL7010CommonLogic.getNumConst(getGlobalCompanyCode(), bizMsg, glblMsg);

        // Add #4767 2016/03/22 Start
        NMAL7010CommonLogic.getProductLevelName(getGlobalCompanyCode(), bizMsg, glblMsg);
        // Add #4767 2016/03/22 End

        if (!ZYPCommonFunc.hasValue(bizMsg.prcDispRecTpCd_DH) && ZYPCommonFunc.hasValue(bizMsg.prcDispRecTpCd_L1.no(0))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd_DH, bizMsg.prcDispRecTpCd_L1.no(0));
        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK))  {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_H1, bizMsg.prcCatgCd_BK);
            doProcess_NMAL7010Scrn00_Search_PrcList(bizMsg, glblMsg, false);
        } else {
            // Default Value Set.
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_H1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd_DH, PRC_DISP_REC_TP.ACTIVE_ONLY);

            ZYPEZDItemValueSetter.setValue(bizMsg.allowNewAggrContrFlg_CB, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.allowNewFleetContrFlg_CB, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.allowAddAggrContrFlg_CB, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.allowAddFleetContrFlg_CB, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.grsPrcPct_CB, DEF_GRS_PRC_PCT);

            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_PRC_LIST_CUST_AUDC);

            bizMsg.xxTabProt_D1.clear();

            // QC#10987 2016/07/12 Del start
//            ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpCd_H1, bizMsg.prcListTpCd_L1.no(0).getValue());
            bizMsg.prcListTpCd_H1.clear();
            // QC#10987 2016/07/12 Del end

            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, BigDecimal.ZERO);

            // QC#10987 2016/07/12 Mod start
//            PRC_LIST_TPTMsg inTMsg = new PRC_LIST_TPTMsg();
//            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(inTMsg.prcListTpCd, bizMsg.prcListTpCd_H1);
//            PRC_LIST_TPTMsg outTMsg = (PRC_LIST_TPTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
//            if (outTMsg == null) {
//                bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.prcListTpCd_H1.getValue(), "Price List Type"});
//                return;
//            }
//
//            ZYPEZDItemValueSetter.setValue(bizMsg.prcListStruTpCd_H1, outTMsg.prcListStruTpCd);
            bizMsg.prcListStruTpCd_H1.clear();
            // QC#10987 2016/07/12 Mod end
            bizMsg.prcListStruTpCd_BK.clear();
            NMAL7010CommonLogic.setDplyTab(bizMsg);
        }

        NMAL7010CommonLogic.clearScrollPosition(bizMsg);
    }

    // QC#2175
    /**
     * Add_SubPrc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Add_SubPrc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        if (bizMsg.W.getValidCount() >= bizMsg.W.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.W.length())});
            return;
        }

        int curIdx = bizMsg.W.getValidCount();
        
        ZYPEZDItemValueSetter.setValue(bizMsg.W.no(curIdx).effFromDt_SW, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(bizMsg.W.no(curIdx).subPrcPrtyNum_SW, getNextSubPricePriorityNumber(bizMsg));

        bizMsg.W.setValidCount(curIdx + 1);
    }

    /**
     * Add_CustAudc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Add_CustAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        if (bizMsg.X.getValidCount() >= bizMsg.X.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.X.length())});
            return;
        }

        int curIdx = bizMsg.X.getValidCount();

        bizMsg.X.no(curIdx).defPrcListFlg_CX.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.X.no(curIdx).effFromDt_CX, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        bizMsg.X.setValidCount(curIdx + 1);

    }

    /**
     * Add_PrcList Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Add_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        if (!addPrcList(bizMsg, glblMsg)) {
            return;
        }

        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.clearScrollPosition(bizMsg);
    }

    private boolean addPrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        int maxCnt = 0;
        int rowNum = 0;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.A.length();
            rowNum = glblMsg.A.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.B.length();
            rowNum = glblMsg.B.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.C.length();
            rowNum = glblMsg.C.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.D.length();
            rowNum = glblMsg.D.getValidCount();
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.E.length();
            rowNum = glblMsg.E.getValidCount();
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.F.length();
            rowNum = glblMsg.F.getValidCount();
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.G.length();
            rowNum = glblMsg.G.getValidCount();
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.H.length();
            rowNum = glblMsg.H.getValidCount();
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            maxCnt = glblMsg.I.length();
            rowNum = glblMsg.I.getValidCount();
        }
        if (rowNum >= maxCnt) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(maxCnt)});
            return false;
        }

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(rowNum).prcQlfyTpCd_PA, bizMsg.prcQlfyTpCd_LA.no(0));
//            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(rowNum).prcTermUomCd_PA, PRC_TERM_UOM.MTH);
            glblMsg.A.no(rowNum).openMktFlg_PA.clear();
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(rowNum).effFromDt_PA, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(rowNum).delFlg_PA, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(rowNum).xxModeCd_A1, NMAL7010Constant.MODE_NEW);
            glblMsg.A.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.A, rowNum);

        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(rowNum).baseAmt_PB, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(rowNum).effFromDt_PB, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(rowNum).delFlg_PB, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(rowNum).xxModeCd_B1, NMAL7010Constant.MODE_NEW);
            glblMsg.B.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.B.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.B, rowNum);

        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(rowNum).effFromDt_PC, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(rowNum).delFlg_PC, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(rowNum).xxModeCd_C1, NMAL7010Constant.MODE_NEW);
            glblMsg.C.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.C.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.C, rowNum);

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(rowNum).effFromDt_PD, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(rowNum).delFlg_PD, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(rowNum).xxModeCd_D1, NMAL7010Constant.MODE_NEW);
            glblMsg.D.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.D.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.D, rowNum);

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(rowNum).effFromDt_PE, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(rowNum).delFlg_PE, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(rowNum).xxModeCd_E1, NMAL7010Constant.MODE_NEW);
            glblMsg.E.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.E.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.E, rowNum);

        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(rowNum).effFromDt_PF, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(rowNum).delFlg_PF, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(rowNum).xxModeCd_F1, NMAL7010Constant.MODE_NEW);
            glblMsg.F.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.F.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.F, rowNum);

        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(rowNum).effFromDt_PG, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(rowNum).delFlg_PG, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(rowNum).xxModeCd_G1, NMAL7010Constant.MODE_NEW);
            glblMsg.G.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.G.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.G, rowNum);

        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {

            ZYPEZDItemValueSetter.setValue(glblMsg.H.no(rowNum).effFromDt_PH, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.H.no(rowNum).delFlg_PH, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.H.no(rowNum).xxModeCd_H1, NMAL7010Constant.MODE_NEW);
            glblMsg.H.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.H.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.H, rowNum);

        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, bizMsg.xxDtlCnt.getValue().add(BigDecimal.ONE));

            ZYPEZDItemValueSetter.setValue(glblMsg.I.no(rowNum).prcQlfyTpCd_PI, bizMsg.prcQlfyTpCd_LI.no(0));
            ZYPEZDItemValueSetter.setValue(glblMsg.I.no(rowNum).prcBreakFlg_PI, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(glblMsg.I.no(rowNum).effFromDt_PI, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.I.no(rowNum).delFlg_PI, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.I.no(rowNum).xxDtlCnt_PI, bizMsg.xxDtlCnt);
            glblMsg.I.setValidCount(rowNum + 1);
            // bizMsg.xxPageShowOfNum.setValue(glblMsg.I.getValidCount());
            NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, bizMsg.I, rowNum);

            ZYPEZDItemValueSetter.setValue(glblMsg.I.no(rowNum).xxDtlCnt_PI, bizMsg.xxDtlCnt);

            if (!ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_LI)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_LI, BigDecimal.ZERO);
            }
        }
        glblMsg.xxTotCnt_SM.setValue(glblMsg.xxTotCnt_SM.getValueInt() + 1); // 2019/03/01 QC#30114 Add

        return true;
    }

    /**
     * Add_QtyDisc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Add_QtyDisc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (bizMsg.J.getValidCount() >= bizMsg.J.length()
                || glblMsg.J.getValidCount() >= glblMsg.J.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(glblMsg.J.length())});
            return;
        }

        int rowNum = glblMsg.J.getValidCount();
        ZYPEZDItemValueSetter.setValue(glblMsg.J.no(rowNum).delFlg_PJ, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(glblMsg.J.no(rowNum).prcListQtyDiscPk_PJ, bizMsg.I.no(bizMsg.xxRadioBtn_LI.getValueInt()).prcListQtyDiscPk_PI);
        ZYPEZDItemValueSetter.setValue(glblMsg.J.no(rowNum).xxDtlCnt_PJ, bizMsg.I.no(bizMsg.xxRadioBtn_LI.getValueInt()).xxDtlCnt_PI);
        ZYPEZDItemValueSetter.setValue(glblMsg.J.no(rowNum).xxSortNum_PJ, (new BigDecimal(rowNum)).add(BigDecimal.ONE));

        glblMsg.J.setValidCount(rowNum + 1);

        NMAL7010CommonLogic.loadQtyDtlToCMsg(bizMsg, bizMsg.J, glblMsg.J);
    }

    /**
     * Add_TrxAudc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Add_TrxAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        if (bizMsg.Y.getValidCount() >= bizMsg.Y.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.Y.length())});
            return;
        }

        int curIdx = bizMsg.Y.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(curIdx).effFromDt_TY, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        bizMsg.Y.setValidCount(curIdx + 1);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_CMN_Clear(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        // QC#4763 re-create PrcListTp pulldown
        NMAL7010CommonLogic.createPullDownForPrcListTp(bizMsg, null);

        // Header
        bizMsg.prcCatgCd_H1.clear();
        bizMsg.prcCatgCd_H2.clear();
        bizMsg.prcCatgCd_BK.clear();
        bizMsg.prcCatgNm_H1.clear();
        bizMsg.prcCatgNm_H2.clear();
        bizMsg.prcListDplyNm_H1.clear();
        bizMsg.prcCatgDescTxt_H1.clear();
        // QC#10987 2016/07/12 Mod start
//        ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpCd_H1, bizMsg.prcListTpCd_L1.no(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_H1, ZYPConstant.FLG_ON_Y);
        bizMsg.prcListTpCd_H1.clear();
        // QC#10987 2016/07/12 Mod end
        bizMsg.custRgtnReqFlg_H1.clear();
        bizMsg.prcSlsVisTpCd_H1.clear();
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
        bizMsg.actvFlg_H1.clear();
        bizMsg.prcContrNum_H1.clear();
        bizMsg.prcContrNm_H1.clear();
        bizMsg.prcListGrpCd_H1.clear();

        // Price List Audience
        // QC#8505 2016/05/26 Del start
//        bizMsg.xxFileData_HC.clear();
        // QC#8505 2016/05/26 Del end
        ZYPTableUtil.clear(bizMsg.X);
        ZYPTableUtil.clear(bizMsg.Y);

        // Tool Bar
        ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd_DH, bizMsg.prcDispRecTpCd_L1.no(0));
        // QC#8505 2016/05/26 Del start
//        bizMsg.xxFileData_DH.clear();
        // QC#8505 2016/05/26 Del end
        bizMsg.effThruDt_DH.clear();

        // Show Criteria
        bizMsg.prcQlfyTpCd_D1.clear();
        bizMsg.prcQlfyValTxt_D1.clear();
        // 2018/11/27 QC#29319 Add Start
        bizMsg.xxPrcQlfyValSrchTxt_D1.clear();
        // 2018/11/27 QC#29319 Add End

        bizMsg.mdlNm_D1.clear();
        bizMsg.prcMtrPkgNm_D1.clear();

        bizMsg.mdlNm_D2.clear();
        bizMsg.prcMtrPkgNm_D2.clear();

        bizMsg.mdlNm_D3.clear();
        bizMsg.prcMtrPkgNm_D3.clear();

        bizMsg.prcPgmTpCd_DH.clear();
        bizMsg.prcEquipTpCd_DH.clear();
        bizMsg.mdlNm_D4.clear();

        bizMsg.svcSegCd_DH.clear();
        bizMsg.prcInOutRgCd_DH.clear();

        bizMsg.mdlNm_D5.clear();

        bizMsg.prcQlfyTpCd_D2.clear();
        bizMsg.prcQlfyValTxt_D2.clear();

        // Price List Value
        // QC#2175
        ZYPTableUtil.clear(bizMsg.W);
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(bizMsg.H);
        ZYPTableUtil.clear(bizMsg.I);
        ZYPTableUtil.clear(bizMsg.J);
        ZYPTableUtil.clear(bizMsg.Q);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(glblMsg.E);
        ZYPTableUtil.clear(glblMsg.F);
        ZYPTableUtil.clear(glblMsg.G);
        ZYPTableUtil.clear(glblMsg.H);
        ZYPTableUtil.clear(glblMsg.I);
        ZYPTableUtil.clear(glblMsg.J);
        ZYPTableUtil.clear(glblMsg.Q);

        // Transaction Drivers
        // Equipment
        bizMsg.notToExcdFlg_GA.clear();
        bizMsg.custBidNum_GA.clear();
        bizMsg.allwPrcDevnFlg_GA.clear();

        bizMsg.prcLeaseTpCd_CA.clear();
        bizMsg.leaseRtrnFeeInclFlg_CA.clear();
        bizMsg.addlShpgFeeInclFlg_CA.clear();
        bizMsg.reloInclFlg_CA.clear();
        bizMsg.hardDriveEraseInclFlg_CA.clear();
        bizMsg.hardDriveRmvInclFlg_CA.clear();
        bizMsg.notExcdContrPrcFlg_CA.clear();
        bizMsg.spclCsmpExclArNm_CA.clear();
        bizMsg.somEligTrxTpCd_CA.clear();
        bizMsg.leaseRatePrcCatgCd_CA.clear();
        bizMsg.prcCatgNm_CA.clear();

        // Service
        bizMsg.notToExcdFlg_GB.clear();
        bizMsg.allwPrcDevnFlg_GB.clear();

        bizMsg.wavBookPrcFlg_CB.clear();
        bizMsg.wavAllFlg_CB.clear();
        bizMsg.allowNewAggrContrFlg_CB.clear();
        bizMsg.allowNewFleetContrFlg_CB.clear();
        bizMsg.allowNewAggrContrFlg_CB.clear();
        bizMsg.allowAddFleetContrFlg_CB.clear();
        bizMsg.allowSvcToDclnFlg_CB.clear();
        bizMsg.grsPrcPct_CB.clear();

        // Service Tier
        bizMsg.prcTierSvcOfferCd_CC.clear();
        bizMsg.allowSvcToDclnFlg_CC.clear();

        // Initial Set.
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_PRC_LIST_CUST_AUDC);

        // QC#10987 2016/07/12 Del start
//        PRC_LIST_TPTMsg inTMsg = new PRC_LIST_TPTMsg();
//        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(inTMsg.prcListTpCd, bizMsg.prcListTpCd_H1);
//        PRC_LIST_TPTMsg outTMsg = (PRC_LIST_TPTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
//        if (outTMsg == null) {
//            bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.prcListTpCd_H1.getValue(), "Price List Type"});
//        }

//        ZYPEZDItemValueSetter.setValue(bizMsg.prcListStruTpCd_H1, outTMsg.prcListStruTpCd);
        bizMsg.prcListStruTpCd_H1.clear();
        // QC#10987 2016/07/12 Del end
        bizMsg.prcListStruTpCd_BK.clear();

        glblMsg.xxTotCnt_SM.clear();
        bizMsg.xxAbendMsgTxt_H1.clear();

        bizMsg.xxTabProt_D1.clear();
        NMAL7010CommonLogic.setDplyTab(bizMsg);

        NMAL7010CommonLogic.clearScrollPosition(bizMsg);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_CMN_Reset(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        doProcess_NMAL7010_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_CMN_Submit(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        // doProcess_NMAL7010Scrn00_Search_PrcList(bizMsg, glblMsg, true);

        // QC#4763 re-create PrcListTp pulldown
        NMAL7010CommonLogic.createPullDownForPrcListTp(bizMsg, bizMsg.prcListStruTpCd_H1.getValue());

        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
    }

    /**
     * Detail Apply
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_DetailApply(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        doProcess_NMAL7010Scrn00_Search_PrcList(bizMsg, glblMsg, false);
    }

    /**
     * OnChange_PrcDispRecTp Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_OnChange_PrcDispRecTp(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        doProcess_NMAL7010Scrn00_Search_PrcList(bizMsg, glblMsg, false);
    }

    /**
     * OnChange_PrcListTp Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_OnChange_PrcListTp(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        // QC#10987 2016/07/11 Mod start
//        PRC_LIST_TPTMsg inTMsg = new PRC_LIST_TPTMsg();
//        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(inTMsg.prcListTpCd, bizMsg.prcListTpCd_H1);
//        PRC_LIST_TPTMsg outTMsg = (PRC_LIST_TPTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.prcListStruTpCd_H1, outTMsg.prcListStruTpCd);
        if (!ZYPCommonFunc.hasValue(bizMsg.prcListTpCd_H1)) {
            bizMsg.prcListStruTpCd_H1.clear();
        } else {
            PRC_LIST_TPTMsg inTMsg = new PRC_LIST_TPTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.prcListTpCd, bizMsg.prcListTpCd_H1);
            PRC_LIST_TPTMsg outTMsg = (PRC_LIST_TPTMsg) ZYPCodeDataUtil.findByKey(inTMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.prcListStruTpCd_H1, outTMsg.prcListStruTpCd);
        }
        // QC#10987 2016/07/11 Mod end
        glblMsg.xxTotCnt_SM.clear();
        bizMsg.xxAbendMsgTxt_H1.clear();

        NMAL7010CommonLogic.setDplyTab(bizMsg);
        if (!TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(glblMsg.A);
            ZYPTableUtil.clear(bizMsg.Q);
            ZYPTableUtil.clear(glblMsg.Q);
        }
        if (!TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.B);
            ZYPTableUtil.clear(glblMsg.B);
        }
        if (!TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.C);
            ZYPTableUtil.clear(glblMsg.C);
        }
        if (!TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.D);
            ZYPTableUtil.clear(glblMsg.D);
        }
        if (!TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.E);
            ZYPTableUtil.clear(glblMsg.E);
        }
        if (!TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.F);
            ZYPTableUtil.clear(glblMsg.F);
        }
        if (!TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.G);
            ZYPTableUtil.clear(glblMsg.G);
        }
        if (!TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.H);
            ZYPTableUtil.clear(glblMsg.H);
        }
        if (!TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.I);
            ZYPTableUtil.clear(glblMsg.I);
            ZYPTableUtil.clear(bizMsg.J);
            ZYPTableUtil.clear(glblMsg.J);
        }
    }

    /**
     * OnChange_QtyDisc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_OnChange_QtyDisc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.loadQtyDtlToCMsg(bizMsg, bizMsg.J, glblMsg.J);
    }

    // Add Start 2018/04/06 QC#22556
    /**
     * OnChange_QtyDisc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_OnChange_PrcQlfyTp(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        NMAL7010CommonLogic.createPullDownForUOM(bizMsg, idx);
    }
    // Add End 2018/04/06 QC#22556

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_PageNext(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.A, glblMsg.A);
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.B, glblMsg.B);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.C, glblMsg.C);
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.D, glblMsg.D);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.E, glblMsg.E);
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.F, glblMsg.F);
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.G, glblMsg.G);
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.H, glblMsg.H);
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.I, glblMsg.I);

            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_LI, BigDecimal.ZERO);
            NMAL7010CommonLogic.loadQtyDtlToCMsg(bizMsg, bizMsg.J, glblMsg.J);
        }

        NMAL7010CommonLogic.clearScrollPosition(bizMsg);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_PagePrev(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        // bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.A));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.A, glblMsg.A);
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.B));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.B, glblMsg.B);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.C));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.C, glblMsg.C);
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.D));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.D, glblMsg.D);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.E));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.E, glblMsg.E);
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.F));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.F, glblMsg.F);
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.G));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.G, glblMsg.G);
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.H));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.H, glblMsg.H);
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - NMAL7010CommonLogic.getPerPageSize(glblMsg, bizMsg.I));
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.I, glblMsg.I);

            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_LI, BigDecimal.ZERO);
            NMAL7010CommonLogic.loadQtyDtlToCMsg(bizMsg, bizMsg.J, glblMsg.J);
        }

        NMAL7010CommonLogic.clearScrollPosition(bizMsg);
    }

    /**
     * Search_PrcList Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param afterSubmit boolean
     */
    private void doProcess_NMAL7010Scrn00_Search_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean afterSubmit) {

        // Header
        bizMsg.prcCatgNm_H1.clear();
        bizMsg.prcListDplyNm_H1.clear();
        bizMsg.prcCatgDescTxt_H1.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpCd_H1, bizMsg.prcListTpCd_L1.no(0));
        bizMsg.custRgtnReqFlg_H1.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.prcSlsVisTpCd_H1, bizMsg.prcSlsVisTpCd_L1.no(0));
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
        bizMsg.actvFlg_H1.clear();
        bizMsg.prcContrNum_H1.clear();
        bizMsg.prcContrNm_H1.clear();
        bizMsg.prcListGrpCd_H1.clear();

        bizMsg.xxAbendMsgTxt_H1.clear();
        glblMsg.xxTotCnt_SM.clear();

        // Header & Transaction Drivers
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().searchPrcListHdr(bizMsg);
        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            if (!afterSubmit) {
                bizMsg.setMessageInfo(NZZM0000E);
            }
            bizMsg.setCommitSMsg(true);
            prcListTblClear(bizMsg, glblMsg);
            return;
        }

        // QC#4763 re-create PrcListTp pulldown
        NMAL7010CommonLogic.createPullDownForPrcListTp(bizMsg, bizMsg.prcListStruTpCd_BK.getValue());

        // Sub Price List
        // QC#2175
        ZYPTableUtil.clear(bizMsg.W);
        ssmResult = NMAL7010Query.getInstance().searchSubPrcList(bizMsg, new String[] {bizMsg.prcCatgCd_H1.getValue()});

        // Tab Set.
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_PRC_LIST_CUST_AUDC);
        NMAL7010CommonLogic.setDplyTab(bizMsg);
        NMAL7010CommonLogic.clearOtherTabData(bizMsg, glblMsg);

        // Clear
        // Price List Audience
        // QC#8505 2016/05/26 Del start
//        bizMsg.xxFileData_HC.clear();
        // QC#8505 2016/05/26 Del end
        ZYPTableUtil.clear(bizMsg.X);
        ZYPTableUtil.clear(bizMsg.Y);

        // Price List Audience
        // Customer(No Record OK)
        ssmResult = NMAL7010Query.getInstance().searchPrcListCustAudc(bizMsg);

        // Transaction(No Record OK)
        ssmResult = NMAL7010Query.getInstance().searchPrcListTrxAudc(bizMsg);

        // QC#8505 2016/05/26 Del start
//        bizMsg.xxFileData_DH.clear();
        // QC#8505 2016/05/26 Del end
        bizMsg.effThruDt_DH.clear();

        prcListTblClear(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_LI, BigDecimal.ZERO);

        // Price List Value(No Record OK)
        ssmResult = NMAL7010Query.getInstance().searchPrcListVal(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setCommitSMsg(true);
            return;
        }

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcFmlaPk_PA)) {
                    NMAL7010CommonLogic.fmlaApiCall(glblMsg.A.no(i), getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(), bizMsg.stdCcyCd.getValue());
                }
            }

        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ssmResult = NMAL7010Query.getInstance().searchPrcListQtyDtl(bizMsg, glblMsg);
            if (ssmResult.isCodeNotFound()) {

                bizMsg.setCommitSMsg(true);
            }
        }

        bizMsg.xxPageShowFromNum.setValue(1);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, BigDecimal.ZERO);
        NMAL7010CommonLogic.loadHeaderToSMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.clearScrollPosition(bizMsg);
    }

    private void doProcess_NMAL7010Scrn00_SelectAll(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.selectUnselect(bizMsg.xxDplyTab_D1.getValue(), glblMsg, ZYPConstant.FLG_ON_Y);
        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
    }

    /**
     * TAB_PrcListVal Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_TAB_PrcListVal(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTabProt_D1, ZYPConstant.FLG_ON_Y);
        NMAL7010CommonLogic.setDplyTab(bizMsg);
    }

    /**
     * TAB_TrxDrv Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_TAB_TrxDrv(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        bizMsg.xxTabProt_D1.clear();
        NMAL7010CommonLogic.setDplyTab(bizMsg);
    }

    /**
     * UnselectAll Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_UnselectAll(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.selectUnselect(bizMsg.xxDplyTab_D1.getValue(), glblMsg, ZYPConstant.FLG_OFF_N);
        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
    }

    // QC#8505 2016/05/26 Del start
//    /**
//     * Upload_CustAudc Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NMAL7010Scrn00_Upload_CustAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
//
//        /** For duplicate check */
//        String path = bizMsg.xxFileData_HC.getTempFilePath();
//        NMAL7010F00FMsg fMsg = new NMAL7010F00FMsg();
//        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
//        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
//
//        try {
//
//            if (!NMAL7010CheckLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
//                return;
//            }
//            fMsg.clear();
//
//            int status = -1;
//            int idx = bizMsg.X.getValidCount();
//            int length = bizMsg.X.length();
//            int upCnt = 0;
//
//            while ((status = mappedFile.read()) != 1) {
//
//                idx++;
//                upCnt++;
//
//                if (idx > length) {
//
//                    bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(length)});
//                    idx = length;
//                    break;
//                }
//
//                if (NMAL7010CheckLogic.validateCustAudc(bizMsg, status, idx, fMsg, upCnt, length)) {
//                    return;
//                }
//
//                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).prcCustAudcCatgCd_X1, PRC_CUST_AUDC_CATG.ACCT_NUM);
//                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).xxScrItem30Txt_X1, fMsg.dsAcctNum.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).xxRecNmTxt_X1, fMsg.dsAcctNm.getValue());
//
//                fMsg.clear();
//            }
//
//            if (idx == 0) {
//
//                bizMsg.setMessageInfo(NMAM8193E);
//            }
//
//            bizMsg.X.setValidCount(idx);
//
//        } finally {
//
//            mappedFile.close();
//            bizMsg.xxFileData_HC.deleteTempFile();
//        }
//    }

//    /**
//     * Upload_PrcList Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NMAL7010Scrn00_Upload_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
//        /** For duplicate check */
//        String path = bizMsg.xxFileData_DH.getTempFilePath();
//        EZDFMsg fMsg = null;
//        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
//
//        int length = 0;
//        int idx = 0;
//        int lengthQty = 0;
//        int idxQty = 0;
//
//        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.A.length();
//            idx = glblMsg.A.getValidCount();
//            fMsg = new NMAL7010F01FMsg();
//        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.B.length();
//            idx = glblMsg.B.getValidCount();
//            fMsg = new NMAL7010F02FMsg();
//        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.C.length();
//            idx = glblMsg.C.getValidCount();
//            fMsg = new NMAL7010F03FMsg();
//        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.D.length();
//            idx = glblMsg.D.getValidCount();
//            fMsg = new NMAL7010F04FMsg();
//        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.E.length();
//            idx = glblMsg.E.getValidCount();
//            fMsg = new NMAL7010F05FMsg();
//        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.F.length();
//            idx = glblMsg.F.getValidCount();
//            fMsg = new NMAL7010F06FMsg();
//        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.G.length();
//            idx = glblMsg.G.getValidCount();
//            fMsg = new NMAL7010F07FMsg();
//        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            length = glblMsg.H.length();
//            idx = glblMsg.H.getValidCount();
//            fMsg = new NMAL7010F08FMsg();
//        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            lengthQty = glblMsg.I.length();
//            idxQty = glblMsg.I.getValidCount();
//            length = glblMsg.J.length();
//            idx = glblMsg.J.getValidCount();
//            fMsg = new NMAL7010F09FMsg();
//        }
//
//        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
//
//        try {
//
//            if (!NMAL7010CheckLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
//                return;
//            }
//            fMsg.clear();
//
//            int status = -1;
//            int upCnt = 0;
//            String beforeKey = "";
//            StringBuilder currentKey = new StringBuilder();
//            String hdrFlg;
//
//            while ((status = mappedFile.read()) != 1) {
//
//                upCnt++;
//
//                if (idx >= length) {
//                    bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(length)});
//                    idx = length;
//                    break;
//                }
//
//                if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F01FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_EQUIP)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListEquip(bizMsg, glblMsg.A.no(idx), (NMAL7010F01FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F02FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_SVC)) {
//                        return;
//                    }
//
//                    if  (!setUploadPrcListSvc(bizMsg, glblMsg.B.no(idx), (NMAL7010F02FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F03FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_SVC_TIER)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListSvcTier(bizMsg, glblMsg.C.no(idx), (NMAL7010F03FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F04FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_SVC_SPEC_CHRG)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListSvcSpecChrg(bizMsg, glblMsg.D.no(idx), (NMAL7010F04FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F05FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_SPLY_PGM)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListSplyPgm(bizMsg, glblMsg.E.no(idx), (NMAL7010F05FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F06FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_LEASE_RATE)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListLeaseRate(bizMsg, glblMsg.F.no(idx), (NMAL7010F06FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F07FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_LEASE_RTRN_FEES)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListLeaseRtrnFees(bizMsg, glblMsg.G.no(idx), (NMAL7010F07FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F08FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_TRADE_IN)) {
//                        return;
//                    }
//
//                    if (!setUploadPrcListTraeIn(bizMsg, glblMsg.H.no(idx), (NMAL7010F08FMsg) fMsg)) {
//                        return;
//                    }
//
//                } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                    if (idxQty >= lengthQty) {
//                        bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(idxQty)});
//                        idx = length;
//                        break;
//                    }
//
//                    if (NMAL7010CheckLogic.validatePrcList(bizMsg, status, idx, (NMAL7010F09FMsg) fMsg, upCnt, length, CSV_UPLOAD_COLUMN_QTY_DISC)) {
//                        return;
//                    }
//
//                    currentKey.setLength(0);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).prcQlfyTpNm_PI.getValue()).append(COMMA);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).prcQlfyValTxt_PI.getValue()).append(COMMA);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).qtyDiscPrcAmt_PI.getValue().toString()).append(COMMA);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).pkgUomNm_PI.getValue()).append(COMMA);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).prcBreakFlg_PI.getValue()).append(COMMA);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).effFromDt_PI.getValue()).append(COMMA);
//                    currentKey.append(((NMAL7010F09FMsg) fMsg).effThruDt_PI.getValue());
//
//                    if (beforeKey.equals(currentKey.toString())) {
//                        hdrFlg = ZYPConstant.FLG_OFF_N;
//                    } else {
//                        hdrFlg = ZYPConstant.FLG_ON_Y;
//                        beforeKey = currentKey.toString();
//                    }
//
//                    if (!setUploadPrcListQtyDisc(bizMsg, glblMsg.I.no(idxQty), glblMsg.J.no(idx), (NMAL7010F09FMsg) fMsg, hdrFlg, idx)) {
//                        return;
//                    }
//
//                    if (ZYPConstant.FLG_ON_Y.equals(hdrFlg)) {
//                        idxQty++;
//                    }
//                }
//
//                idx++;
//                fMsg.clear();
//            }
//
//            if (idx == 0) {
//
//                bizMsg.setMessageInfo(NMAM8193E);
//            }
//
//            if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.A.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.B.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.C.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.D.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.E.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.F.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.G.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.H.setValidCount(idx);
//            } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                glblMsg.I.setValidCount(idxQty);
//                glblMsg.J.setValidCount(idx);
//                if (!ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_LI)) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_LI, BigDecimal.ZERO);
//                }
//            }
//
//            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
//
//        } finally {
//
//            mappedFile.close();
//            bizMsg.xxFileData_HC.deleteTempFile();
//        }
//    }
    // QC#8505 2016/05/26 Del start

    /**
     * NMAL6800 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010_NMAL6800(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        EZDCStringItem ezdStrItemMdse = null;
        int idx = bizMsg.xxCellIdx.getValueInt();

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            // Mod Start 2018/07/17 QC#20267
            if(ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg_H2.getValue())){
                ezdStrItemMdse = bizMsg.prcQlfyValTxt_B;
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).prcQlfyValTxt_PA, bizMsg.prcQlfyValTxt_B);
            } else {
                ezdStrItemMdse = bizMsg.A.no(idx).prcQlfyValTxt_PA;
            }
            // Mod End 2018/07/17 QC#20267
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            return;
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            return;
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            return;
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            return;
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = bizMsg.I.no(idx).prcQlfyValTxt_PI;
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getMdseInfo(ezdStrItemMdse.getValue());
        if (ssmResult.isCodeNotFound()) {
            ezdStrItemMdse.setErrorInfo(1, NMAM0163E, new String[] {ezdStrItemMdse.getValue(), "Value" });
            return;
        }
        Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            // Mod Start 2018/07/17 QC#20267
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mnfItemCd_PA, (String) rsltMap.get("MNF_ITEM_CD"));
            // Mod End 2018/07/17 QC#20267
            // Mod Start 2016/10/17 QC#15193
//            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaMdseTpCd_PA, (String) rsltMap.get("COA_MDSE_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaMdseTpNm_PA, (String) rsltMap.get("COA_MDSE_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaProjCd_PA, (String) rsltMap.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaProjNm_PA, (String) rsltMap.get("COA_MDSE_TP_NM"));
            // Mod End 2016/10/17 QC#15193
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mdseItemTpNm_PA, (String) rsltMap.get("MDSE_ITEM_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaProdNm_PA, (String) rsltMap.get("COA_PROD_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).t_MdlNm_PA, (String) rsltMap.get("T_MDL_NM"));
            // Add #4767 2016/03/22 Start
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P0, (String) rsltMap.get("ZEROTH_PROD_CTRL"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P1, (String) rsltMap.get("FIRST_PROD_CTRL"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P2, (String) rsltMap.get("SCD_PROD_CTRL"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P3, (String) rsltMap.get("THIRD_PROD_CTRL"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P4, (String) rsltMap.get("FRTH_PROD_CTRL"));
            // Add #4767 2016/03/22 End

            // Add Start 2018/04/09 QC#22556
            NMAL7010CommonLogic.createPullDownForUOM(bizMsg, idx);
            // Add End 2018/04/09 QC#22556
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            // Mod Start 2016/10/17 QC#15193
//            ZYPEZDItemValueSetter.setValue(bizMsg.I.no(idx).coaMdseTpNm_PI, (String) rsltMap.get("COA_MDSE_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.I.no(idx).coaProjNm_PI, (String) rsltMap.get("COA_MDSE_TP_NM"));
            // Mod End 2016/10/17 QC#15193
            ZYPEZDItemValueSetter.setValue(bizMsg.I.no(idx).mdseItemTpNm_PI, (String) rsltMap.get("MDSE_ITEM_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.I.no(idx).coaProdNm_PI, (String) rsltMap.get("COA_PROD_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.I.no(idx).t_MdlNm_PI, (String) rsltMap.get("T_MDL_NM"));
        }
    }

    /**
     * NMAL7110 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010_NMAL7110(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        //
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010_NWAL1130(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        if ("OpenWin_PrcFormula".equals(bizMsg.xxScrEventNm_DH.getValue())) {
            if (bizMsg.xxCellIdx.getValueInt() >= 0) {
                NMAL7010CommonLogic.fmlaApiCall(bizMsg.A.no(bizMsg.xxCellIdx.getValueInt()), getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(), bizMsg.stdCcyCd.getValue());
            }
//        } else if ("OpenWin_MtrPkg".equals(bizMsg.xxScrEventNm_DH.getValue())) {
//            int idx = bizMsg.xxCellIdx.getValueInt();
//            if (idx < 0) {
//                return;
//            }
//
//            EZDCStringItem mdlNmItem = null;
//            EZDCBigDecimalItem mtrPkgPkItem = null; 
//
//            if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                mdlNmItem = bizMsg.B.no(idx).mdlNm_PB;
//                mtrPkgPkItem = bizMsg.B.no(idx).prcMtrPkgPk_PB;
//            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                mdlNmItem = bizMsg.C.no(idx).mdlNm_PC;
//                mtrPkgPkItem = bizMsg.C.no(idx).prcMtrPkgPk_PC;
//            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//                mdlNmItem = bizMsg.E.no(idx).mdlNm_PE;
//                mtrPkgPkItem = bizMsg.E.no(idx).prcMtrPkgPk_PE;
//            }
//
//            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getBllgMtr(mdlNmItem.getValue(), mtrPkgPkItem.getValue());
//            if (ssmResult.isCodeNotFound()) {
//                bizMsg.setMessageInfo(NMAM0163E, new String[] {mtrPkgPkItem.getValue().toString(), "Meter Package Billing Meter"});
//            }
//            List<Map<String, Object>> bllgMtrList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            boolean isFirst = true;
//            for (Map<String, Object> rsltMap : bllgMtrList) {
//                if (isExistsBllgMtrPk((BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"), bizMsg, glblMsg)) {
//                    if (isFirst) {
//                        bizMsg.B.no(idx).prcMtrPkgPk_PB.setErrorInfo(1, NMAM0072E, new String[] {"Billing Counter"});
//                    }
//                }
//                if (isFirst) {
//                    setBllgMtrCMsg(bizMsg, idx, rsltMap);
//                } else {
//                    addPrcList(bizMsg, glblMsg);
//                    setBllgMtrSMsg(bizMsg, glblMsg, rsltMap);
//                }
//            }
        }
    }

//    private boolean isExistsBllgMtrPk(BigDecimal prcMtrPkgBllgMtrPk, NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
//        if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcMtrPkgBllgMtrPk_PB)) {
//                    if (glblMsg.B.no(i).prcMtrPkgBllgMtrPk_PB.getValue().equals(prcMtrPkgBllgMtrPk)) {
//                        return true;
//                    }
//                }
//            }
//        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcMtrPkgBllgMtrPk_PC)) {
//                    if (glblMsg.C.no(i).prcMtrPkgBllgMtrPk_PC.getValue().equals(prcMtrPkgBllgMtrPk)) {
//                        return true;
//                    }
//                }
//            }
//        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcMtrPkgBllgMtrPk_PE)) {
//                    if (glblMsg.E.no(i).prcMtrPkgBllgMtrPk_PE.getValue().equals(prcMtrPkgBllgMtrPk)) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

//    private void setBllgMtrCMsg(NMAL7010CMsg bizMsg, int idx,  Map<String, Object> rsltMap) {
//        if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mtrLbCd_PB, (String) rsltMap.get("MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mtrLbDescTxt_PB, (String) rsltMap.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).prcMtrPkgBllgMtrPk_PB, (BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mdseCd_PB, (String) rsltMap.get("MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mdseCd_PB, (String) rsltMap.get("MDSE_NM"));
//
//        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).mtrLbCd_PC, (String) rsltMap.get("MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).mtrLbDescTxt_PC, (String) rsltMap.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).prcMtrPkgBllgMtrPk_PC, (BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).mdseCd_PC, (String) rsltMap.get("MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).mdseCd_PC, (String) rsltMap.get("MDSE_NM"));
//
//        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(idx).mtrLbCd_PE, (String) rsltMap.get("MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(idx).mtrLbDescTxt_PE, (String) rsltMap.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(idx).prcMtrPkgBllgMtrPk_PE, (BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(idx).mdseCd_PE, (String) rsltMap.get("MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(idx).mdseCd_PE, (String) rsltMap.get("MDSE_NM"));
//        }
//    }

//    private void setBllgMtrSMsg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, Map<String, Object> rsltMap) {
//        int idx = 0;
//        if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            idx = glblMsg.B.getValidCount();
//            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).mtrLbCd_PB, (String) rsltMap.get("MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).mtrLbDescTxt_PB, (String) rsltMap.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).prcMtrPkgBllgMtrPk_PB, (BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).mdseCd_PB, (String) rsltMap.get("MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).mdseCd_PB, (String) rsltMap.get("MDSE_NM"));
//
//        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            idx = glblMsg.C.getValidCount();
//            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(idx).mtrLbCd_PC, (String) rsltMap.get("MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(idx).mtrLbDescTxt_PC, (String) rsltMap.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(idx).prcMtrPkgBllgMtrPk_PC, (BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(idx).mdseCd_PC, (String) rsltMap.get("MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(idx).mdseCd_PC, (String) rsltMap.get("MDSE_NM"));
//
//        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
//            idx = glblMsg.E.getValidCount();
//            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(idx).mtrLbCd_PE, (String) rsltMap.get("MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(idx).mtrLbDescTxt_PE, (String) rsltMap.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(idx).prcMtrPkgBllgMtrPk_PE, (BigDecimal) rsltMap.get("PRC_MTR_PKG_BLLG_MTR_PK"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(idx).mdseCd_PE, (String) rsltMap.get("MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(idx).mdseCd_PE, (String) rsltMap.get("MDSE_NM"));
//        }
//    }

    private void createPullDown(NMAL7010CMsg bizMsg) {
        // Header
        ZYPCodeDataUtil.createPulldownList(PRC_LIST_TP.class, bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_SLS_VIS_TP.class, bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_LIST_GRP.class, bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1);

        // Customer Audience
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCustAudcCatgAll();
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }
        List<Map<String, String>> rsltCustList = (List<Map<String, String>>) ssmResult.getResultObject();
        int idx01 = 0;
        int idx02 = 0;
        int idx03 = 0;
        for (Map<String, String> rsltMap : rsltCustList) {
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("PRC_CUST_AUDC_CATG_FLG_01"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCustAudcCatgCd_L1.no(idx01), (String) rsltMap.get("PRC_CUST_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCustAudcCatgDescTxt_L1.no(idx01), (String) rsltMap.get("PRC_CUST_AUDC_CATG_DESC_TXT"));
                idx01++;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("PRC_CUST_AUDC_CATG_FLG_02"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCustAudcCatgCd_L2.no(idx02), (String) rsltMap.get("PRC_CUST_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCustAudcCatgDescTxt_L2.no(idx02), (String) rsltMap.get("PRC_CUST_AUDC_CATG_DESC_TXT"));
                idx02++;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("PRC_CUST_AUDC_CATG_FLG_03"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCustAudcCatgCd_L3.no(idx03), (String) rsltMap.get("PRC_CUST_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCustAudcCatgDescTxt_L3.no(idx03), (String) rsltMap.get("PRC_CUST_AUDC_CATG_DESC_TXT"));
                idx03++;
            }
        }

        // Transaction Audience
        ssmResult = NMAL7010Query.getInstance().getTrxAudcCatgAll();
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }
        List<Map<String, String>> rslTrxtList = (List<Map<String, String>>) ssmResult.getResultObject();
        idx01 = 0;
        idx02 = 0;
        for (Map<String, String> rsltMap : rslTrxtList) {
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("PRC_TRX_AUDC_CATG_FLG_01"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTrxAudcCatgCd_L1.no(idx01), (String) rsltMap.get("PRC_TRX_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTrxAudcCatgDescTxt_L1.no(idx01), (String) rsltMap.get("PRC_TRX_AUDC_CATG_DESC_TXT"));
                idx01++;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("PRC_TRX_AUDC_CATG_FLG_02"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTrxAudcCatgCd_L2.no(idx02), (String) rsltMap.get("PRC_TRX_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTrxAudcCatgDescTxt_L2.no(idx02), (String) rsltMap.get("PRC_TRX_AUDC_CATG_DESC_TXT"));
                idx02++;
            }
        }

        // Detail
        ZYPCodeDataUtil.createPulldownList(PRC_DISP_REC_TP.class, bizMsg.prcDispRecTpCd_L1, bizMsg.prcDispRecTpDescTxt_L1);

        // Equipment
        ssmResult = NMAL7010Query.getInstance().getPrcQlfyTpAll();
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }
        List<Map<String, String>> rslQlfytList = (List<Map<String, String>>) ssmResult.getResultObject();
        idx01 = 0;
        idx02 = 0;
        for (Map<String, String> rsltMap : rslQlfytList) {
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("EQUIP_DISP_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcQlfyTpCd_LA.no(idx01), (String) rsltMap.get("PRC_QLFY_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcQlfyTpDescTxt_LA.no(idx01), (String) rsltMap.get("PRC_QLFY_TP_DESC_TXT"));
                idx01++;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("QTY_DISC_DISP_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcQlfyTpCd_LI.no(idx02), (String) rsltMap.get("PRC_QLFY_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcQlfyTpDescTxt_LI.no(idx02), (String) rsltMap.get("PRC_QLFY_TP_DESC_TXT"));
                idx02++;
            }
        }

        // Del Start 2018/04/06 QC#22556
//        ZYPCodeDataUtil.createPulldownList(PKG_UOM.class, bizMsg.pkgUomCd_LA, bizMsg.pkgUomDescTxt_LA);
        // Del End 2018/04/06 QC#22556
        ZYPCodeDataUtil.createPulldownList(PRC_TERM_UOM.class, bizMsg.prcTermUomCd_LA, bizMsg.prcTermUomDescTxt_LA);

        ZYPCodeDataUtil.createPulldownList(PRC_LEASE_TP.class, bizMsg.prcLeaseTpCd_LA, bizMsg.prcLeaseTpDescTxt_LA);

        // Service
        ZYPCodeDataUtil.createPulldownList(PRC_RATE_TP.class, bizMsg.prcRateTpCd_LB, bizMsg.prcRateTpDescTxt_LB);
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_PLN_TP.class, bizMsg.prcSvcPlnTpCd_LB, bizMsg.prcSvcPlnTpDescTxt_LB);
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_CONTR_TP.class, bizMsg.prcSvcContrTpCd_LB, bizMsg.prcSvcContrTpDescTxt_LB);

        MTR_LBTMsg inMtrTMsg = new MTR_LBTMsg();
        ZYPEZDItemValueSetter.setValue(inMtrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMtrTMsg.mtrLbTpCd, MTR_LB_TP.BILLING_METER);
        MTR_LBTMsgArray mtrTMsgArray = (MTR_LBTMsgArray) ZYPCodeDataUtil.findByCondition(inMtrTMsg);

//        Map<String, String> tMsgKeys = new HashMap<String, String>();
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE,   "mtrLbCd");
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "mtrLbNm");
//        ZYPPulldownValueSetter.set(mtrTMsgArray, tMsgKeys, bizMsg.mtrLbCd_LB, bizMsg.mtrLbDescTxt_LB);

//        ZYPCodeDataUtil.createPulldownList(PRC_LIST_BAND.class, bizMsg.prcListBandCd_LB, bizMsg.prcListBandDescTxt_LB); // 2018/11/17 QC#29147 Del
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_ZONE.class, bizMsg.prcSvcZoneCd_LB, bizMsg.prcSvcZoneDescTxt_LB);

        // Service Tiers
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_PLN_TP.class, bizMsg.prcSvcPlnTpCd_LC, bizMsg.prcSvcPlnTpDescTxt_LC);
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_CONTR_TP.class, bizMsg.prcSvcContrTpCd_LC, bizMsg.prcSvcContrTpDescTxt_LC);
//        ZYPPulldownValueSetter.set(mtrTMsgArray, tMsgKeys, bizMsg.mtrLbCd_LC, bizMsg.mtrLbDescTxt_LC);
//        ZYPCodeDataUtil.createPulldownList(PRC_LIST_BAND.class, bizMsg.prcListBandCd_LC, bizMsg.prcListBandDescTxt_LC); // 2018/11/17 QC#29147 Del
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_TIER_TP.class, bizMsg.prcSvcTierTpCd_LC, bizMsg.prcSvcTierTpDescTxt_LC);
        ZYPCodeDataUtil.createPulldownList(PRC_TIER_SVC_OFFER.class, bizMsg.prcTierSvcOfferCd_LC, bizMsg.prcTierSvcOfferDescTxt_LC);

        // Service Special Charges
        ZYPCodeDataUtil.createPulldownList(PRC_ADDL_CHRG_TP.class, bizMsg.prcAddlChrgTpCd_LD, bizMsg.prcAddlChrgTpDescTxt_LD);
        ZYPCodeDataUtil.createPulldownList(MKT_MDL_SEG.class, bizMsg.mktMdlSegCd_LD, bizMsg.mktMdlSegDescTxt_LD);

        // Supply Program
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_PLN_TP.class, bizMsg.prcSvcPlnTpCd_LE, bizMsg.prcSvcPlnTpDescTxt_LE);
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_CONTR_TP.class, bizMsg.prcSvcContrTpCd_LE, bizMsg.prcSvcContrTpDescTxt_LE);
//        ZYPPulldownValueSetter.set(mtrTMsgArray, tMsgKeys, bizMsg.mtrLbCd_LE, bizMsg.mtrLbDescTxt_LE);
//        ZYPCodeDataUtil.createPulldownList(PRC_LIST_BAND.class, bizMsg.prcListBandCd_LE, bizMsg.prcListBandDescTxt_LE); // 2018/11/17 QC#29147 Del
        ZYPCodeDataUtil.createPulldownList(PRC_SVC_ZONE.class, bizMsg.prcSvcZoneCd_LE, bizMsg.prcSvcZoneDescTxt_LE);

        // Lease Rates
        ZYPCodeDataUtil.createPulldownList(PRC_PGM_TP.class, bizMsg.prcPgmTpCd_LF, bizMsg.prcPgmTpDescTxt_LF);
        ZYPCodeDataUtil.createPulldownList(PRC_EQUIP_TP.class, bizMsg.prcEquipTpCd_LF, bizMsg.prcEquipTpDescTxt_LF);

        // Lease Return Fees
        ZYPCodeDataUtil.createPulldownList(SVC_SEG.class, bizMsg.svcSegCd_LG, bizMsg.svcSegDescTxt_LG);
        ZYPCodeDataUtil.createPulldownList(PRC_IN_OUT_RG.class, bizMsg.prcInOutRgCd_LG, bizMsg.prcInOutRgDescTxt_LG);

        // Trade Ins(No pulldown)

        // Quantity Discount
        ZYPCodeDataUtil.createPulldownList(PKG_UOM.class, bizMsg.pkgUomCd_LI, bizMsg.pkgUomDescTxt_LI);
        ZYPCodeDataUtil.createPulldownList(PKG_UOM.class, bizMsg.pkgUomCd_LJ, bizMsg.pkgUomDescTxt_LJ);

        // SOM Transaction Type Eligible
        ZYPCodeDataUtil.createPulldownList("SOM_ELIG_TRX_TP", bizMsg.somEligTrxTpCd_LA, bizMsg.somEligTrxTpDescTxt_LA);
    }

    private boolean setUploadPrcListEquip(NMAL7010CMsg bizMsg, NMAL7010_ASMsg aSMsg, NMAL7010F01FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(aSMsg.prcListEquipConfigNum_PA, fMsg.prcListEquipConfigNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.prcListEquipConfigNm_PA, fMsg.prcListEquipConfigNm);
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_QLFY_TP.class.getSimpleName(), fMsg.prcQlfyTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcQlfyTpNm.getValue(), "Price Qualifier Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(aSMsg.prcQlfyTpCd_PA, (ssmResult.getResultObject().toString()));
        ZYPEZDItemValueSetter.setValue(aSMsg.prcQlfyValTxt_PA, fMsg.prcQlfyValTxt);
        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PKG_UOM.class.getSimpleName(), fMsg.pkgUomNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.pkgUomNm.getValue(), "Package UOM"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(aSMsg.pkgUomCd_PA, (ssmResult.getResultObject().toString()));
        ZYPEZDItemValueSetter.setValue(aSMsg.prcListEquipPrcAmt_PA, fMsg.prcListEquipPrcAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.minPrcAmt_PA, fMsg.minPrcAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.maxPrcAmt_PA, fMsg.maxPrcAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.prcTermAot_PA, fMsg.prcTermAot);
        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_TERM_UOM.class.getSimpleName(), fMsg.prcTermUomNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcTermUomNm.getValue(), "Price Term UOM"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(aSMsg.prcTermUomCd_PA, (ssmResult.getResultObject().toString()));
        ZYPEZDItemValueSetter.setValue(aSMsg.mlyPmtAmt_PA, fMsg.mlyPmtAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.minLeasePmtAmt_PA, fMsg.minLeasePmtAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.maxLeasePmtAmt_PA, fMsg.maxLeasePmtAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.prcFmlaNm_PA, fMsg.prcFmlaNm);
        ZYPEZDItemValueSetter.setValue(aSMsg.openMktFlg_PA, fMsg.openMktFlg);
        ZYPEZDItemValueSetter.setValue(aSMsg.quotRevAmt_PA, fMsg.quotRevAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.compCd_PA, fMsg.compCd);
        ZYPEZDItemValueSetter.setValue(aSMsg.effFromDt_PA, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(aSMsg.effThruDt_PA, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(aSMsg.delFlg_PA, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListSvc(NMAL7010CMsg bizMsg, NMAL7010_BSMsg bSMsg, NMAL7010F02FMsg fMsg) {

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_RATE_TP.class.getSimpleName(), fMsg.prcRateTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcRateTpNm.getValue(), "Price Rate Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bSMsg.prcRateTpCd_PB, (ssmResult.getResultObject().toString()));
        ZYPEZDItemValueSetter.setValue(bSMsg.mdlNm_PB, fMsg.mdlNm);
        ZYPEZDItemValueSetter.setValue(bSMsg.prcMtrPkgNm_PB, fMsg.prcMtrPkgNm);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_PLN_TP.class.getSimpleName(), fMsg.prcSvcPlnTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcPlnTpNm.getValue(), "Price Service Plan Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bSMsg.prcSvcPlnTpCd_PB, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_CONTR_TP.class.getSimpleName(), fMsg.prcSvcContrTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcContrTpNm.getValue(), "Price Service Contract Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bSMsg.prcSvcContrTpCd_PB, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(MTR_LB.class.getSimpleName(), fMsg.mtrLbNm.getValue(), ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.mtrLbNm.getValue(), "Meter Label"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bSMsg.mtrLbCd_PB, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(bSMsg.minCopyVolCnt_PB, fMsg.minCopyVolCnt);
        ZYPEZDItemValueSetter.setValue(bSMsg.maxCopyVolCnt_PB, fMsg.maxCopyVolCnt);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_LIST_BAND.class.getSimpleName(), fMsg.prcListBandNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcListBandNm.getValue(), "Price List Band"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bSMsg.prcListBandCd_PB, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(bSMsg.baseAmt_PB, fMsg.baseAmt);
        ZYPEZDItemValueSetter.setValue(bSMsg.minBaseAmt_PB, fMsg.minBaseAmt);
        ZYPEZDItemValueSetter.setValue(bSMsg.maxBaseAmt_PB, fMsg.maxBaseAmt);
        ZYPEZDItemValueSetter.setValue(bSMsg.cpcAmtRate_PB, fMsg.cpcAmtRate);
        ZYPEZDItemValueSetter.setValue(bSMsg.minCpcAmtRate_PB, fMsg.minCpcAmtRate);
        ZYPEZDItemValueSetter.setValue(bSMsg.maxCpcAmtRate_PB, fMsg.maxCpcAmtRate);
        ZYPEZDItemValueSetter.setValue(bSMsg.termFromMthAot_PB, fMsg.termFromMthAot);
        ZYPEZDItemValueSetter.setValue(bSMsg.termThruMthAot_PB, fMsg.termThruMthAot);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_ZONE.class.getSimpleName(), fMsg.prcSvcZoneNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcZoneNm.getValue(), "Price Service Zone"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bSMsg.prcSvcZoneCd_PB, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(bSMsg.mdseCd_PB, fMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(bSMsg.quotRevAmt_PB, fMsg.quotRevAmt);
        ZYPEZDItemValueSetter.setValue(bSMsg.compCd_PB, fMsg.compCd);
        ZYPEZDItemValueSetter.setValue(bSMsg.effFromDt_PB, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(bSMsg.effThruDt_PB, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(bSMsg.delFlg_PB, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListSvcTier(NMAL7010CMsg bizMsg, NMAL7010_CSMsg cSMsg, NMAL7010F03FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(cSMsg.mdlNm_PC, fMsg.mdlNm);
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_TIER_TP.class.getSimpleName(), fMsg.prcSvcTierTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcTierTpNm.getValue(), "Price Service Tier Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cSMsg.prcSvcTierTpCd_PC, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(cSMsg.prcMtrPkgNm_PC, fMsg.prcMtrPkgNm);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_PLN_TP.class.getSimpleName(), fMsg.prcSvcPlnTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcPlnTpNm.getValue(), "Price Service Plan Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cSMsg.prcSvcPlnTpCd_PC, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_CONTR_TP.class.getSimpleName(), fMsg.prcSvcContrTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcContrTpNm.getValue(), "Price Service Contract Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cSMsg.prcSvcContrTpCd_PC, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(MTR_LB.class.getSimpleName(), fMsg.mtrLbNm.getValue(), ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.mtrLbNm.getValue(), "Meter Label"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cSMsg.mtrLbCd_PC, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(cSMsg.avgCopyVolCnt_PC, fMsg.avgCopyVolCnt);
        ZYPEZDItemValueSetter.setValue(cSMsg.minCopyVolCnt_PC, fMsg.minCopyVolCnt);
        ZYPEZDItemValueSetter.setValue(cSMsg.maxCopyVolCnt_PC, fMsg.maxCopyVolCnt);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_LIST_BAND.class.getSimpleName(), fMsg.prcListBandNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcListBandNm.getValue(), "Price List Band"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cSMsg.prcListBandCd_PC, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(cSMsg.baseAmt_PC, fMsg.baseAmt);
        ZYPEZDItemValueSetter.setValue(cSMsg.minBaseAmt_PC, fMsg.minBaseAmt);
        ZYPEZDItemValueSetter.setValue(cSMsg.maxBaseAmt_PC, fMsg.maxBaseAmt);
        ZYPEZDItemValueSetter.setValue(cSMsg.cpcAmtRate_PC, fMsg.cpcAmtRate);
        ZYPEZDItemValueSetter.setValue(cSMsg.minCpcAmtRate_PC, fMsg.minCpcAmtRate);
        ZYPEZDItemValueSetter.setValue(cSMsg.maxCpcAmtRate_PC, fMsg.maxCpcAmtRate);
        ZYPEZDItemValueSetter.setValue(cSMsg.termFromMthAot_PC, fMsg.termFromMthAot);
        ZYPEZDItemValueSetter.setValue(cSMsg.termThruMthAot_PC, fMsg.termThruMthAot);
        ZYPEZDItemValueSetter.setValue(cSMsg.mdseCd_PC, fMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(cSMsg.effFromDt_PC, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(cSMsg.effThruDt_PC, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(cSMsg.delFlg_PC, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListSvcSpecChrg(NMAL7010CMsg bizMsg, NMAL7010_DSMsg dSMsg, NMAL7010F04FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(dSMsg.mdseCd_PD, fMsg.mdseCd);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_ADDL_CHRG_TP.class.getSimpleName(), fMsg.prcAddlChrgTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcAddlChrgTpNm.getValue(), "Price Additional Charge Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(dSMsg.prcAddlChrgTpCd_PD, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(MKT_MDL_SEG.class.getSimpleName(), fMsg.mktMdlSegNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.mktMdlSegNm.getValue(), "Item Segment"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(dSMsg.mktMdlSegCd_PD, (ssmResult.getResultObject().toString()));
        ZYPEZDItemValueSetter.setValue(dSMsg.mdlNm_PD, fMsg.mdlNm);
        ZYPEZDItemValueSetter.setValue(dSMsg.addlChrgPrcAmt_PD, fMsg.addlChrgPrcAmt);
        ZYPEZDItemValueSetter.setValue(dSMsg.effFromDt_PD, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(dSMsg.effThruDt_PD, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(dSMsg.delFlg_PD, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListSplyPgm(NMAL7010CMsg bizMsg, NMAL7010_ESMsg eSMsg, NMAL7010F05FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(eSMsg.mdlNm_PE, fMsg.mdlNm);
        ZYPEZDItemValueSetter.setValue(eSMsg.prcMtrPkgNm_PE, fMsg.prcMtrPkgNm);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_PLN_TP.class.getSimpleName(), fMsg.prcSvcPlnTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcPlnTpNm.getValue(), "Price Service Plan Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(eSMsg.prcSvcPlnTpCd_PE, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_CONTR_TP.class.getSimpleName(), fMsg.prcSvcContrTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcContrTpNm.getValue(), "Price Service Contract Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(eSMsg.prcSvcContrTpCd_PE, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(MTR_LB.class.getSimpleName(), fMsg.mtrLbNm.getValue(), ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.mtrLbNm.getValue(), "Meter Label"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(eSMsg.mtrLbCd_PE, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(eSMsg.minCopyVolCnt_PE, fMsg.minCopyVolCnt);
        ZYPEZDItemValueSetter.setValue(eSMsg.maxCopyVolCnt_PE, fMsg.maxCopyVolCnt);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_LIST_BAND.class.getSimpleName(), fMsg.prcListBandNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcListBandNm.getValue(), "Price List Band"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(eSMsg.prcListBandCd_PE, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(eSMsg.totBaseAmt_PE, fMsg.totBaseAmt);
        ZYPEZDItemValueSetter.setValue(eSMsg.splyBaseAmt_PE, fMsg.splyBaseAmt);
        ZYPEZDItemValueSetter.setValue(eSMsg.cpcAmtRate_PE, fMsg.cpcAmtRate);
        ZYPEZDItemValueSetter.setValue(eSMsg.minCpcAmtRate_PE, fMsg.minCpcAmtRate);
        ZYPEZDItemValueSetter.setValue(eSMsg.maxCpcAmtRate_PE, fMsg.maxCpcAmtRate);
        ZYPEZDItemValueSetter.setValue(eSMsg.termFromMthAot_PE, fMsg.termFromMthAot);
        ZYPEZDItemValueSetter.setValue(eSMsg.termThruMthAot_PE, fMsg.termThruMthAot);
        ZYPEZDItemValueSetter.setValue(eSMsg.mdseCd_PE, fMsg.mdseCd);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_SVC_ZONE.class.getSimpleName(), fMsg.prcSvcZoneNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcSvcZoneNm.getValue(), "Price Service Zone"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(eSMsg.prcSvcZoneCd_PE, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(eSMsg.splyAgmtPlnNm_PE, fMsg.splyAgmtPlnNm);
        ZYPEZDItemValueSetter.setValue(eSMsg.effFromDt_PE, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(eSMsg.effThruDt_PE, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(eSMsg.delFlg_PE, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListLeaseRate(NMAL7010CMsg bizMsg, NMAL7010_FSMsg fSMsg, NMAL7010F06FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(fSMsg.prcLeaseCmpyAbbrNm_PF, fMsg.prcLeaseCmpyAbbrNm);
        ZYPEZDItemValueSetter.setValue(fSMsg.dsAcctNm_PF, fMsg.dsAcctNm);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_PGM_TP.class.getSimpleName(), fMsg.prcPgmTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcPgmTpNm.getValue(), "Price Program Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(fSMsg.prcPgmTpCd_PF, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_EQUIP_TP.class.getSimpleName(), fMsg.prcEquipTpNm.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcEquipTpNm.getValue(), "Price Equipment Type"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(fSMsg.prcEquipTpCd_PF, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(fSMsg.mdlNm_PF, fMsg.mdlNm);

        ZYPEZDItemValueSetter.setValue(fSMsg.minTotFinAmt_PF, fMsg.minTotFinAmt);
        ZYPEZDItemValueSetter.setValue(fSMsg.maxTotFinAmt_PF, fMsg.maxTotFinAmt);
        ZYPEZDItemValueSetter.setValue(fSMsg.termFromMthAot_PF, fMsg.termFromMthAot);
        ZYPEZDItemValueSetter.setValue(fSMsg.termThruMthAot_PF, fMsg.termThruMthAot);
        ZYPEZDItemValueSetter.setValue(fSMsg.leaseRate_PF, fMsg.leaseRate);
        ZYPEZDItemValueSetter.setValue(fSMsg.effFromDt_PF, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(fSMsg.effThruDt_PF, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(fSMsg.delFlg_PF, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListLeaseRtrnFees(NMAL7010CMsg bizMsg, NMAL7010_GSMsg gSMsg, NMAL7010F07FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(gSMsg.prcLeaseCmpyAbbrNm_PG, fMsg.prcLeaseCmpyAbbrNm);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(SVC_SEG.class.getSimpleName(), fMsg.svcSegNm.getValue(), ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.svcSegNm.getValue(), "Service Segment"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(gSMsg.svcSegCd_PG, (ssmResult.getResultObject().toString()));

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_IN_OUT_RG.class.getSimpleName(), fMsg.prcInOutRgNm.getValue(), ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcInOutRgNm.getValue(), "Price In or Out of Region"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(gSMsg.prcInOutRgCd_PG, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(gSMsg.dstMileAmt_PG, fMsg.dstMileAmt);
        ZYPEZDItemValueSetter.setValue(gSMsg.rtrnFeeAmt_PG, fMsg.rtrnFeeAmt);
        ZYPEZDItemValueSetter.setValue(gSMsg.effFromDt_PG, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(gSMsg.effThruDt_PG, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(gSMsg.delFlg_PG, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListTraeIn(NMAL7010CMsg bizMsg, NMAL7010_HSMsg hSMsg, NMAL7010F08FMsg fMsg) {

        ZYPEZDItemValueSetter.setValue(hSMsg.mdlNm_PH, fMsg.mdlNm);
        ZYPEZDItemValueSetter.setValue(hSMsg.tiAmt_PH, fMsg.tiAmt);
        ZYPEZDItemValueSetter.setValue(hSMsg.mtrRngReqFlg_PH, fMsg.mtrRngReqFlg);
        ZYPEZDItemValueSetter.setValue(hSMsg.fromMtrCopyVolCnt_PH, fMsg.fromMtrCopyVolCnt);
        ZYPEZDItemValueSetter.setValue(hSMsg.thruMtrCopyVolCnt_PH, fMsg.thruMtrCopyVolCnt);
        ZYPEZDItemValueSetter.setValue(hSMsg.effFromDt_PH, fMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(hSMsg.effThruDt_PH, fMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(hSMsg.delFlg_PH, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private boolean setUploadPrcListQtyDisc(NMAL7010CMsg bizMsg, NMAL7010_ISMsg iSMsg, NMAL7010_JSMsg jSMsg, NMAL7010F09FMsg fMsg, String hdrFlg, int qtyDtlIdx) {

        S21SsmEZDResult ssmResult;
        if (ZYPConstant.FLG_ON_Y.equals(hdrFlg)) {
            ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PRC_QLFY_TP.class.getSimpleName(), fMsg.prcQlfyTpNm_PI.getValue(), ZYPConstant.FLG_OFF_N);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.prcQlfyTpNm_PI.getValue(), "Price Qualifier Type"});
                return false;
            }
            ZYPEZDItemValueSetter.setValue(iSMsg.prcQlfyTpCd_PI, (ssmResult.getResultObject().toString()));
            ZYPEZDItemValueSetter.setValue(iSMsg.prcQlfyValTxt_PI, fMsg.prcQlfyValTxt_PI);
            ZYPEZDItemValueSetter.setValue(iSMsg.qtyDiscPrcAmt_PI, fMsg.qtyDiscPrcAmt_PI);

            ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PKG_UOM.class.getSimpleName(), fMsg.pkgUomNm_PI.getValue(), ZYPConstant.FLG_OFF_N);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.pkgUomNm_PI.getValue(), "Package UOM"});
                return false;
            }
            ZYPEZDItemValueSetter.setValue(iSMsg.pkgUomCd_PI, (ssmResult.getResultObject().toString()));
            ZYPEZDItemValueSetter.setValue(iSMsg.prcBreakFlg_PI, fMsg.prcBreakFlg_PI);
            ZYPEZDItemValueSetter.setValue(iSMsg.effFromDt_PI, fMsg.effFromDt_PI);
            ZYPEZDItemValueSetter.setValue(iSMsg.effThruDt_PI, fMsg.effThruDt_PI);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, bizMsg.xxDtlCnt.getValue().add(BigDecimal.ONE));
            ZYPEZDItemValueSetter.setValue(iSMsg.xxDtlCnt_PI, bizMsg.xxDtlCnt);
            ZYPEZDItemValueSetter.setValue(iSMsg.delFlg_PI, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(jSMsg.qtyDiscDtlQty_PJ, fMsg.qtyDiscDtlQty_PJ);

        ssmResult = NMAL7010Query.getInstance().getCodeTableCodeValue(PKG_UOM.class.getSimpleName(), fMsg.pkgUomNm_PJ.getValue(), ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {fMsg.pkgUomNm_PJ.getValue(), "Package UOM"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(jSMsg.pkgUomCd_PJ, (ssmResult.getResultObject().toString()));

        ZYPEZDItemValueSetter.setValue(jSMsg.prcBreakAmt_PJ, fMsg.prcBreakAmt_PJ);
        ZYPEZDItemValueSetter.setValue(jSMsg.xxDtlCnt_PJ, bizMsg.xxDtlCnt);
        ZYPEZDItemValueSetter.setValue(jSMsg.xxSortNum_PJ, (new BigDecimal(qtyDtlIdx)).add(BigDecimal.ONE));
        ZYPEZDItemValueSetter.setValue(jSMsg.delFlg_PJ, ZYPConstant.FLG_OFF_N);

        return true;
    }

    private void prcListTblClear(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(glblMsg.A);
            ZYPTableUtil.clear(bizMsg.Q);
            ZYPTableUtil.clear(glblMsg.Q);
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.B);
            ZYPTableUtil.clear(glblMsg.B);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.C);
            ZYPTableUtil.clear(glblMsg.C);
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.D);
            ZYPTableUtil.clear(glblMsg.D);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.E);
            ZYPTableUtil.clear(glblMsg.E);
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.F);
            ZYPTableUtil.clear(glblMsg.F);
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.G);
            ZYPTableUtil.clear(glblMsg.G);
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.H);
            ZYPTableUtil.clear(glblMsg.H);
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.clear(bizMsg.I);
            ZYPTableUtil.clear(glblMsg.I);
            ZYPTableUtil.clear(bizMsg.J);
            ZYPTableUtil.clear(glblMsg.J);
        }
    }
    
    private BigDecimal getNextSubPricePriorityNumber(NMAL7010CMsg bizMsg) {
        BigDecimal max = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.W.no(i).subPrcPrtyNum_SW)) {
                if (bizMsg.W.no(i).subPrcPrtyNum_SW.getValue().compareTo(max) > 0) {
                    max = bizMsg.W.no(i).subPrcPrtyNum_SW.getValue();
                }
            }
        }
        max = max.add(BigDecimal.ONE);
        if (max.intValue() > 99) {
            return null;
        }
        return max;
    }

    // QC#2174 Add Start
    private void doProcess_NMAL7010Scrn00_OpenWin_QtyDisc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        if (bizMsg.xxCellIdx_QH.getValue() == null) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }

        ZYPTableUtil.clear(bizMsg.Q);

        int idx = bizMsg.xxCellIdx_QH.getValueInt();

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(idx).xxYesNoCd_PA.getValue())) {
            return;
        }

        getQtyDiscList(bizMsg, glblMsg, bizMsg.A.no(idx).prcListEquipPk_PA.getValue());

    }

    private void doProcess_NMAL7010_NMAL7030(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        int idx = bizMsg.xxCellIdx_QH.getValueInt();
        BigDecimal prcListEquipPk = bizMsg.A.no(idx).prcListEquipPk_PA.getValue();

        int qcSize = bizMsg.Q.getValidCount();
        int qsSize = glblMsg.Q.getValidCount();

        if (qcSize < 1 && qsSize < 1) {
            return;
        }

        List<Integer> idxList = new ArrayList<Integer>();
        List<NMAL7010_QSMsg> qsMsgList = new ArrayList<NMAL7010_QSMsg>();

        for (int i = 0; i < qsSize; i++) {

            if (!ZYPCommonFunc.hasValue(glblMsg.Q.no(i).prcListEquipPk_QP)) {
                continue;
            }

            if (prcListEquipPk.compareTo(glblMsg.Q.no(i).prcListEquipPk_QP.getValue()) == 0) {
                NMAL7010_QSMsg qsMsg = (NMAL7010_QSMsg) glblMsg.Q.no(i).clone();
                qsMsgList.add(qsMsg);
                idxList.add(i);
            }
        }

        if (qsSize - idxList.size() + qcSize > glblMsg.Q.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(glblMsg.Q.length())});
            return;
        }

        if (idxList.size() > 0) {
            ZYPTableUtil.deleteRows(glblMsg.Q, idxList);
        }

        Map<String, NMAL7010_QSMsg> cacheMap = new HashMap<String, NMAL7010_QSMsg>();
        for (int i = 0; i < qsMsgList.size(); i++) {
            NMAL7010_QSMsg qsMsg = qsMsgList.get(i);
            if (ZYPCommonFunc.hasValue(qsMsg.prcListEquipDtlPk_QP)) {
                cacheMap.put(qsMsg.prcListEquipDtlPk_QP.getValue().toString(), qsMsg);
            }
        }

        NMAL7010SMsg newSMsg = new NMAL7010SMsg();
        EZDMsg.copy(bizMsg.Q, null, newSMsg.Q, null);

        for (int i = 0; i < newSMsg.Q.getValidCount(); i++) {
            NMAL7010_QSMsg newQsMsg = newSMsg.Q.no(i);
            BigDecimal prcListEquipDtlPk = newQsMsg.prcListEquipDtlPk_QP.getValue();

            if (ZYPCommonFunc.hasValue(prcListEquipDtlPk) && cacheMap.containsKey(prcListEquipDtlPk.toString())) {
                NMAL7010_QSMsg qsMsg = cacheMap.get(prcListEquipDtlPk.toString());

                if (isChanged(newQsMsg, qsMsg)) {
                    newQsMsg.xxModeCd_QP.setValue(NMAL7010Constant.MODE_MODIFY);
                } else {
                    newQsMsg.xxModeCd_QP.clear();
                }
            } else {
                newQsMsg.xxModeCd_QP.setValue(NMAL7010Constant.MODE_NEW);
            }

            qsSize = glblMsg.Q.getValidCount();
            EZDMsg.copy(newQsMsg, null, glblMsg.Q.no(qsSize), null);
            glblMsg.Q.setValidCount(qsSize + 1);
        }
    }
    private boolean isChanged(NMAL7010_QSMsg newMsg, NMAL7010_QSMsg qsMsg) {
        if (!isEqual(newMsg.qtyDiscDtlQty_QP.getValue(), qsMsg.qtyDiscDtlQty_QP.getValue())) {
            return true;
        } else if (!isEqual(newMsg.prcBreakAmt_QP.getValue(), qsMsg.prcBreakAmt_QP.getValue())) {
            return true;
        } else if (!isEqual(newMsg.delFlg_QP.getValue(), qsMsg.delFlg_QP.getValue())) {
            return true;
        } else if (NMAL7010Constant.MODE_MODIFY.equals(qsMsg.xxModeCd_QP.getValue())) {
            return true;
        }
        return false;
    }

    private void getQtyDiscList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, BigDecimal prcListEquipPk) {

        int cnt = 0;
        int sizeQS = glblMsg.Q.getValidCount();
        for (int i = 0; i < sizeQS; i++) {
            if (prcListEquipPk != null && isEqual(prcListEquipPk, glblMsg.Q.no(i).prcListEquipPk_QP.getValue())) {
                EZDMsg.copy(glblMsg.Q.no(i), null, bizMsg.Q.no(cnt), null);
                cnt++;
            }
        }
        bizMsg.Q.setValidCount(cnt);

        if (cnt > 0) {
            // sort (1:delFlg, 2:Qty, 3:ezuptime)
            NMAL7010CommonLogic.sortQtyDisc(bizMsg);
            return;
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().searchPrcListEquipDtl(bizMsg, prcListEquipPk);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        int sizeQC = bizMsg.Q.getValidCount();
        for (int i = 0; i < sizeQC; i++) {
            EZDMsg.copy(bizMsg.Q.no(i), null, glblMsg.Q.no(sizeQS), null);
            sizeQS++;
        }
        glblMsg.Q.setValidCount(sizeQS);
    }

    private boolean isEqual(BigDecimal val1, BigDecimal val2) {

        if (val1 != null && val2 != null) {
            return val1.compareTo(val2) == 0;
        } else if (val1 == null && val2 != null) {
            return false;
        } else if (val1 != null && val2 == null) {
            return false;
        }
        return true;
    }

    private boolean isEqual(String val1, String val2) {
        
        if (val1 != null && val2 != null) {
            return val1.equals(val2);
        } else if (val1 == null && val2 != null) {
            return false;
        } else if (val1 != null && val2 == null) {
            return false;
        }
        return true;
    }
    // QC#2174 End

    /**
     * Filter
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010_NMAL7040(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        doProcess_NMAL7010Scrn00_Search_PrcList(bizMsg, glblMsg, false);
    }

    private void doProcess_NMAL7010Scrn00_CMN_Download(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CMsg hdrMsg = new NMAL7010CMsg();
        ZYPEZDItemValueSetter.setValue(hdrMsg.prcCatgCd_H1, bizMsg.prcCatgCd_BK.getValue());

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().searchPrcListHdr(hdrMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        NMAL7010Query.getInstance().downloadPrcListVal(bizMsg, glblMsg, hdrMsg);
    }

    /**
     * TBLColumnSort
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010Scrn00_TBLColumnSort(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if (NMAL7010Constant.TBL_A.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.A, glblMsg.A);

        } else if (NMAL7010Constant.TBL_B.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.B, glblMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.B.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.B, glblMsg.B);

        } else if (NMAL7010Constant.TBL_C.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.C, glblMsg.C.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.C.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.C, glblMsg.C);

        } else if (NMAL7010Constant.TBL_D.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.D, glblMsg.D.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.D.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.D, glblMsg.D);

        } else if (NMAL7010Constant.TBL_E.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.E, glblMsg.E.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.E.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.E, glblMsg.E);

        } else if (NMAL7010Constant.TBL_F.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.F, glblMsg.F.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.F.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.F, glblMsg.F);

        } else if (NMAL7010Constant.TBL_G.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.G, glblMsg.G.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.G.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.G, glblMsg.G);

        } else if (NMAL7010Constant.TBL_H.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.H, glblMsg.H.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.H.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.H, glblMsg.H);
        }
    }

    // Add Start 2016/08/26 QC#3934
    /**
     * OnBlur_OpenWin_QualifierValueItem Event
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010Scrn00_OnBlur_OpenWin_QualifierValueItem(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        // Mod Start 2018/01/17 QC#20267
        NMAL7010CommonLogic.checkValue(bizMsg, getGlobalCompanyCode());
        // Mod End 2018/01/17 QC#20267

    }

    /**
     * OnBlur_OpenWin_PrcListMdse Event
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010Scrn00_OnBlur_OpenWin_PrcListMdse(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.checkPriceMdse(bizMsg);

    }

    /**
     * OnBlur_OpenWin_Mdse Event
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010Scrn00_OnBlur_OpenWin_Mdse(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.checkMdse(bizMsg);

    }
    // Add End 2016/08/26 QC#3934

    // 2017/11/13 QC#20203(Sol#257) Add Start
    /**
     * Copy_PrcList Event
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010Scrn00_Copy_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectedRows = new ArrayList<Integer>();
        EZDCMsgArray cMsgArray = null;
        EZDSMsgArray sMsgArray = null;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_SA", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.A;
            sMsgArray = glblMsg.A;

        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_SB", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.B;
            sMsgArray = glblMsg.B;

        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_SC", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.C;
            sMsgArray = glblMsg.C;

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_SD", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.D;
            sMsgArray = glblMsg.D;

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.E, "xxChkBox_SE", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.E;
            sMsgArray = glblMsg.E;

        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.F, "xxChkBox_SF", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.F;
            sMsgArray = glblMsg.F;

        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.G, "xxChkBox_SG", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.G;
            sMsgArray = glblMsg.G;

        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.H, "xxChkBox_SH", ZYPConstant.CHKBOX_ON_Y);
            cMsgArray = bizMsg.H;
            sMsgArray = glblMsg.H;

        }

        if (selectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8190E);
            return;
        }

        int maxCnt = sMsgArray.length();
        int rowNum = 0;

        for (int idx : selectedRows) {
            rowNum = sMsgArray.getValidCount();

            if (rowNum >= maxCnt) {
                bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(maxCnt)});
                return;
            }

            copyPrcList(bizMsg, glblMsg, idx, rowNum);
            sMsgArray.setValidCount(rowNum + 1);
        }

        NMAL7010CommonLogic.setPageFrom(bizMsg, glblMsg, cMsgArray, rowNum);

        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.clearScrollPosition(bizMsg);

    }

    private void copyPrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, int fromIdx, int toIdx) {

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.A.no(fromIdx), null, glblMsg.A.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(toIdx).effFromDt_PA, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(toIdx).delFlg_PA, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(toIdx).xxModeCd_A1, NMAL7010Constant.MODE_NEW);
            glblMsg.A.no(toIdx).prcListEquipPk_PA.clear();
            glblMsg.A.no(toIdx).xxChkBox_SA.clear();
            glblMsg.A.no(toIdx).xxYesNoCd_PA.clear();

            glblMsg.A.no(fromIdx).xxChkBox_SA.clear();

        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.B.no(fromIdx), null, glblMsg.B.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(toIdx).effFromDt_PB, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(toIdx).delFlg_PB, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(toIdx).xxModeCd_B1, NMAL7010Constant.MODE_NEW);
            glblMsg.B.no(toIdx).prcListSvcPk_PB.clear();
            glblMsg.B.no(toIdx).xxChkBox_SB.clear();

            glblMsg.B.no(fromIdx).xxChkBox_SB.clear();

        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.C.no(fromIdx), null, glblMsg.C.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(toIdx).effFromDt_PC, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(toIdx).delFlg_PC, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(toIdx).xxModeCd_C1, NMAL7010Constant.MODE_NEW);
            glblMsg.C.no(toIdx).prcListSvcTierPk_PC.clear();
            glblMsg.C.no(toIdx).xxChkBox_SC.clear();

            glblMsg.C.no(fromIdx).xxChkBox_SC.clear();

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.D.no(fromIdx), null, glblMsg.D.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(toIdx).effFromDt_PD, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(toIdx).delFlg_PD, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(toIdx).xxModeCd_D1, NMAL7010Constant.MODE_NEW);
            glblMsg.D.no(toIdx).prcListAddlChrgPk_PD.clear();
            glblMsg.D.no(toIdx).xxChkBox_SD.clear();

            glblMsg.D.no(fromIdx).xxChkBox_SD.clear();

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.E.no(fromIdx), null, glblMsg.E.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(toIdx).effFromDt_PE, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(toIdx).delFlg_PE, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.E.no(toIdx).xxModeCd_E1, NMAL7010Constant.MODE_NEW);
            glblMsg.E.no(toIdx).prcListSplyPgmPk_PE.clear();
            glblMsg.E.no(toIdx).xxChkBox_SE.clear();

            glblMsg.E.no(fromIdx).xxChkBox_SE.clear();

        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.F.no(fromIdx), null, glblMsg.F.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(toIdx).effFromDt_PF, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(toIdx).delFlg_PF, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.F.no(toIdx).xxModeCd_F1, NMAL7010Constant.MODE_NEW);
            glblMsg.F.no(toIdx).prcListLeaseRatePk_PF.clear();
            glblMsg.F.no(toIdx).xxChkBox_SF.clear();

            glblMsg.F.no(fromIdx).xxChkBox_SF.clear();

        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.G.no(fromIdx), null, glblMsg.G.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(toIdx).effFromDt_PG, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(toIdx).delFlg_PG, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.G.no(toIdx).xxModeCd_G1, NMAL7010Constant.MODE_NEW);
            glblMsg.G.no(toIdx).prcListLeaseRtrnPk_PG.clear();
            glblMsg.G.no(toIdx).xxChkBox_SG.clear();

            glblMsg.G.no(fromIdx).xxChkBox_SG.clear();

        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            EZDMsg.copy(glblMsg.H.no(fromIdx), null, glblMsg.H.no(toIdx), null);

            ZYPEZDItemValueSetter.setValue(glblMsg.H.no(toIdx).effFromDt_PH, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            ZYPEZDItemValueSetter.setValue(glblMsg.H.no(toIdx).delFlg_PH, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(glblMsg.H.no(toIdx).xxModeCd_H1, NMAL7010Constant.MODE_NEW);
            glblMsg.H.no(toIdx).prcListTiValPk_PH.clear();
            glblMsg.H.no(toIdx).xxChkBox_SH.clear();

            glblMsg.H.no(fromIdx).xxChkBox_SH.clear();

        }
    }
    // 2017/11/13 QC#20203(Sol#257) Add End

    // 2018/05/08 QC#20269 Add Start
    /**
     * DwldAsTemplate_PrcList Event
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    private void doProcess_NMAL7010Scrn00_DwldAsTemplate_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        NMAL7010CMsg hdrMsg = new NMAL7010CMsg();
        ZYPEZDItemValueSetter.setValue(hdrMsg.prcCatgCd_H1, bizMsg.prcCatgCd_BK.getValue());

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().searchPrcListHdrAsTemplate(hdrMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        List<Map<String, Object>> prcList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (prcList.size() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(hdrMsg.prcCatgNm_H1, (String) prcList.get(0).get("PRC_CATG_NM"));
        ZYPEZDItemValueSetter.setValue(hdrMsg.prcListTpDescTxt_L1.no(0), (String) prcList.get(0).get("PRC_LIST_TP_DESC_TXT"));

        NMAL7010Query.getInstance().downloadAsTemplatePrcListVal(bizMsg, glblMsg, hdrMsg);
    }
    // 2018/05/08 QC#20269 Add End

    // 2018/12/18 QC#29661 Add Start
    private void doProcess_NMAL7010Scrn00_MassUpd_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7010CommonLogic.updateEffDateTo(bizMsg.xxDplyTab_D1.getValue(), glblMsg, bizMsg);
        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
    }
    // 2018/12/18 QC#29661 Add Start

}
