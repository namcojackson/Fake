/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7010;

import static business.blap.NMAL7010.common.NMAL7010CommonLogic.toBigDecimal;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_CX;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PA;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PB;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PD;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PF;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PG;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PH;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PI;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_PJ;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_SW;
import static business.blap.NMAL7010.constant.NMAL7010Constant.CHK_TY;
import static business.blap.NMAL7010.constant.NMAL7010Constant.EXTN_KEY;
import static business.blap.NMAL7010.constant.NMAL7010Constant.MAX_SORT_NUM;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8054E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8571E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8664E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8665E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NZZM0003E;
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
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7010.common.NMAL7010CheckLogic;
import business.blap.NMAL7010.common.NMAL7010CommonLogic;
import business.blap.NMAL7010.constant.NMAL7010Constant;
import business.db.DS_PRC_CATG_SUBTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CUST_AUDCTMsg;
import business.db.PRC_EQUIP_CONTR_ATTRBTMsg;
import business.db.PRC_GENL_CONTR_ATTRBTMsg;
import business.db.PRC_LIST_ADDL_CHRGTMsg;
import business.db.PRC_LIST_EQUIPTMsg;
import business.db.PRC_LIST_EQUIP_DTLTMsg;
import business.db.PRC_LIST_LEASE_RATETMsg;
import business.db.PRC_LIST_LEASE_RTRNTMsg;
import business.db.PRC_LIST_QTY_DISCTMsg;
import business.db.PRC_LIST_QTY_DISC_DTLTMsg;
import business.db.PRC_LIST_SPLY_PGMTMsg;
import business.db.PRC_LIST_SVCTMsg;
import business.db.PRC_LIST_SVC_TIERTMsg;
import business.db.PRC_LIST_TI_VALTMsg;
import business.db.PRC_SVC_CONTR_ATTRBTMsg;
import business.db.PRC_SVC_TIER_CONTR_ATTRBTMsg;
import business.db.PRC_TRX_AUDCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CUST_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TRX_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7010BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/04/22   SRA             E.Inada         Update          QC#7080
 * 2016/06/23   SRAA            K.Aratani       Update          QC#10653
 * 2016/06/23   Fujitsu         R.Nakamura      Update          QC#17503
 * 2017/02/14   Fujitsu         H.Nagashima     Update          QC#17501
 * 2018/02/27   Fujitsu         A.Kosai         Update          QC#24422
 * 2019/02/05   Fujitsu         C.Hara          Update          QC#30114
 * 2019/03/01   Fujitsu         C.Hara          Update          QC#30114
 *</pre>
 */
public class NMAL7010BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;
            NMAL7010SMsg glblMsg = (NMAL7010SMsg) sMsg;

            if ("NMAL7010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NMAL7010CMsg) cMsg, (NMAL7010SMsg) sMsg);

            } else if ("NMAL7010Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NMAL7010CMsg) cMsg, (NMAL7010SMsg) sMsg);

            } else if ("NMAL7010Scrn00_Del_SubPrc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Del_SubPrc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Del_CustAudc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Del_CustAudc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Del_PrcList".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Del_PrcList(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Del_QtyDisc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Del_QtyDisc(bizMsg, glblMsg);

            } else if ("NMAL7010Scrn00_Del_TrxAudc".equals(screenAplID)) {
                doProcess_NMAL7010Scrn00_Del_TrxAudc(bizMsg, glblMsg);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_CMN_Submit(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // for error handling
        NMAL7010CommonLogic.setDplyTab(bizMsg);

        boolean isSuccessCmn = false;
        boolean isSuccessPrcList = false;

        // Common Check.
        isSuccessCmn = NMAL7010CheckLogic.checkCommon(bizMsg, getGlobalCompanyCode());

        // Each of the Check
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListEquip(bizMsg, glblMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate());
            if (!isSuccessPrcList) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_EQUIP);
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListSvc(bizMsg, glblMsg, getGlobalCompanyCode());
            if (!isSuccessPrcList) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_SVC);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListSvcTier(bizMsg, glblMsg, getGlobalCompanyCode());
            if (!isSuccessPrcList) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_SVC_TIER);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListSvcSpecChrg(bizMsg, glblMsg, getGlobalCompanyCode());
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListSplyPgm(bizMsg, glblMsg, getGlobalCompanyCode());
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListLeaseRate(bizMsg, glblMsg, getGlobalCompanyCode());
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListLeaseRtrnFee(bizMsg, glblMsg, getGlobalCompanyCode());
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListTradeIn(bizMsg, glblMsg, getGlobalCompanyCode());
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccessPrcList = NMAL7010CheckLogic.checkPrcListQtyDisc(bizMsg, glblMsg, getGlobalCompanyCode());
            if (isSuccessPrcList) {
                isSuccessPrcList = NMAL7010CheckLogic.checkPrcListQtyDiscDtl(bizMsg, glblMsg, getGlobalCompanyCode());
            }
        }

        if (!isSuccessCmn || !isSuccessPrcList) {
            NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
            return;
        }

        ///////////////////
        // Insert/Update //
        ///////////////////
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_H1, bizMsg.prcCatgCd_BK);
        } else {
            // Get New Price ID
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_H1, ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY));
        }

        // Common Insert/Update
        //// Price Category(Price List)
        if (!submitPrcCatg(bizMsg, glblMsg)) {
            return;
        }

        //// Sub Price List
        if (!submitSubPrcCatg(bizMsg, glblMsg)) {
            return;
        }
        //// Price Customer Audience
        if (!submitCustAudc(bizMsg, glblMsg)) {
            return;
        }
        //// Price Transaction Audience
        if (!submitTrxAudc(bizMsg, glblMsg)) {
            return;
        }

        // Each of the Insert/Update
        //// Price Contract
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            if (!submitPrcGenContr(bizMsg, glblMsg, false)) {
                return;
            }
            if (!submitPrcEquipContr(bizMsg, glblMsg, false)) {
                return;
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            if (!submitPrcGenContr(bizMsg, glblMsg, false)) {
                return;
            }
            if (!submitPrcSvcContr(bizMsg, glblMsg, false)) {
                return;
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            if (!submitPrcSvcTierContr(bizMsg, glblMsg, false)) {
                return;
            }
        }

        //// price List
        boolean isSuccess = false;
        //QC#10653
    	List<String> mdseCdDealConfigList = new ArrayList<String>();
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
        	//QC#10653
            isSuccess = submitPrcListEquip(bizMsg, glblMsg, false, mdseCdDealConfigList);
            // QC#2174 Add Start
            if (isSuccess) {
                isSuccess = submitPrcListEquipDtl(bizMsg, glblMsg);
            }
            // QC#2174 End
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListSvc(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListSvcTier(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListSvcSpecChrg(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListSplyPgm(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListLeaseRate(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListLeaseRtrnFee(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListTradeIn(bizMsg, glblMsg, false);
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            isSuccess = submitPrcListQtyDisc(bizMsg, glblMsg, false);
            if (isSuccess) {
                isSuccess = submitPrcListQtyDiscDtl(bizMsg, glblMsg, false);
            }
        }

        // Other Table Delete
        if (isSuccess
                && ZYPCommonFunc.hasValue(bizMsg.prcListStruTpCd_BK)
                && !bizMsg.prcListStruTpCd_H1.getValue().equals(bizMsg.prcListStruTpCd_BK.getValue())) {
        	//QC#10653
            isSuccess = otherTblDelete(bizMsg, glblMsg, mdseCdDealConfigList);
        }

        if (!isSuccess) {
            return;
        }
        
        //QC#10653
        if (isSuccess && !mdseCdDealConfigList.isEmpty()) {
        	NMAL7010CommonLogic.invokeMasterDataMessaging(mdseCdDealConfigList);
        }

        // Header Msg
        NMAL7010CommonLogic.loadHeaderToCMsg(bizMsg, glblMsg);
        // backup
        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_BK, bizMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_H2, bizMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_H2, bizMsg.prcCatgNm_H1.getValue());
        }
    }
    
    // QC#2175
    /**
     * Del_SubPrc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Del_SubPrc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.W, CHK_SW, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.W.no(idx).dsPrcCatgSubPk_SW)) {
                continue;
            }

            DS_PRC_CATG_SUBTMsg tMsg = new DS_PRC_CATG_SUBTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.dsPrcCatgSubPk, bizMsg.W.no(idx).dsPrcCatgSubPk_SW);
            tMsg = (DS_PRC_CATG_SUBTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.W.no(idx).ezUpTime_SW.getValue(), bizMsg.W.no(idx).ezUpTimeZone_SW.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                bizMsg.setMessageInfo(NMAM8665E, toArray(tMsg.getTableName(), String.valueOf(tMsg.dsPrcCatgSubPk.getValue())));
                //QC#17501 mod End
                return;
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.W, selectRows);
    }

    /**
     * Del_CustAudc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Del_CustAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.X, CHK_CX, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        // QC#4761 Delete
//        if (selectRows.size() == bizMsg.X.getValidCount()
//                && ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            bizMsg.setMessageInfo(NMAM0074E, new String[] {"Price Customer Audience", "atleast one row"});
//            return;
//        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.X.no(idx).prcCustAudcPk_CX)) {
                continue;
            }

            PRC_CUST_AUDCTMsg tMsg = new PRC_CUST_AUDCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcCustAudcPk, bizMsg.X.no(idx).prcCustAudcPk_CX);
            tMsg = (PRC_CUST_AUDCTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.X.no(idx).ezUpTime_CX.getValue(), bizMsg.X.no(idx).ezUpTimeZone_CX.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                bizMsg.setMessageInfo(NMAM8665E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcCustAudcPk.getValue())));
                // 2017/02/14 QC#17501 mod End
                return;
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.X, selectRows);
    }

    /**
     * Del_PrcList Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Del_PrcList(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectRows = new ArrayList<Integer>();
        String keyPk = null;
        int validCount = 0;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.A, CHK_PA, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.A.getValidCount();
            keyPk = "prcListEquipPk";
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.B, CHK_PB, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.B.getValidCount();
            keyPk = "prcListSvcPk";
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.C, CHK_PC, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.C.getValidCount();
            keyPk = "prcListSvcTierPk";
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.D, CHK_PD, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.D.getValidCount();
            keyPk = "prcListAddlChrgPk";
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.E, CHK_PE, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.E.getValidCount();
            keyPk = "prcListSplyPgmPk";
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.F, CHK_PF, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.F.getValidCount();
            keyPk = "prcListLeaseRatePk";
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.G, CHK_PG, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.G.getValidCount();
            keyPk = "prcListLeaseRtrnPk";
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.H, CHK_PH, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.H.getValidCount();
            keyPk = "prcListTiValPk";
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(glblMsg.I, CHK_PI, ZYPConstant.FLG_ON_Y);
            validCount = glblMsg.I.getValidCount();
            keyPk = "prcListQtyDiscPk";
        }

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        // Del Start 2017/02/13 QC#17503
//        if (selectRows.size() == validCount
//                && ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            bizMsg.setMessageInfo(NMAM0074E, new String[] {"Price List Val", "atleast one row"});
//            return;
//        }
        // Del End 2017/02/13 QC#17503

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int idx : selectRows) {
                if (!NMAL7010CheckLogic.checkPrcFmlaRelation(glblMsg.A.no(idx))) {
                    continue;
                }
            }
        }

        BigDecimal prcListPk = null;
        String delFlg = null;
        String ezUptime = null;
        String ezUpTimeZone = null;
        EZDCMsgArray cMsgArray = null;
        EZDSMsgArray sMsgArray = null;

        for (int idx : selectRows) {

            EZDTMsg tMsg = null;
            if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.A.no(idx).prcListEquipPk_PA.getValue();
                delFlg = glblMsg.A.no(idx).delFlg_PA.getValue();
                ezUptime = glblMsg.A.no(idx).ezUpTime_PA.getValue();
                ezUpTimeZone = glblMsg.A.no(idx).ezUpTimeZone_PA.getValue();
                cMsgArray = bizMsg.A;
                sMsgArray = glblMsg.A;
                tMsg = new PRC_LIST_EQUIPTMsg();
            } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.B.no(idx).prcListSvcPk_PB.getValue();
                delFlg = glblMsg.B.no(idx).delFlg_PB.getValue();
                ezUptime = glblMsg.B.no(idx).ezUpTime_PB.getValue();
                ezUpTimeZone = glblMsg.B.no(idx).ezUpTimeZone_PB.getValue();
                cMsgArray = bizMsg.B;
                sMsgArray = glblMsg.B;
                tMsg = new PRC_LIST_SVCTMsg();
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.C.no(idx).prcListSvcTierPk_PC.getValue();
                delFlg = glblMsg.C.no(idx).delFlg_PC.getValue();
                ezUptime = glblMsg.C.no(idx).ezUpTime_PC.getValue();
                ezUpTimeZone = glblMsg.C.no(idx).ezUpTimeZone_PC.getValue();
                cMsgArray = bizMsg.C;
                sMsgArray = glblMsg.C;
                tMsg = new PRC_LIST_SVC_TIERTMsg();
            } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.D.no(idx).prcListAddlChrgPk_PD.getValue();
                delFlg = glblMsg.D.no(idx).delFlg_PD.getValue();
                ezUptime = glblMsg.D.no(idx).ezUpTime_PD.getValue();
                ezUpTimeZone = glblMsg.D.no(idx).ezUpTimeZone_PD.getValue();
                cMsgArray = bizMsg.D;
                sMsgArray = glblMsg.D;
                tMsg = new PRC_LIST_ADDL_CHRGTMsg();
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.E.no(idx).prcListSplyPgmPk_PE.getValue();
                delFlg = glblMsg.E.no(idx).delFlg_PE.getValue();
                ezUptime = glblMsg.E.no(idx).ezUpTime_PE.getValue();
                ezUpTimeZone = glblMsg.E.no(idx).ezUpTimeZone_PE.getValue();
                cMsgArray = bizMsg.E;
                sMsgArray = glblMsg.E;
                tMsg = new PRC_LIST_SPLY_PGMTMsg();
            } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.F.no(idx).prcListLeaseRatePk_PF.getValue();
                delFlg = glblMsg.F.no(idx).delFlg_PF.getValue();
                ezUptime = glblMsg.F.no(idx).ezUpTime_PF.getValue();
                ezUpTimeZone = glblMsg.F.no(idx).ezUpTimeZone_PF.getValue();
                cMsgArray = bizMsg.F;
                sMsgArray = glblMsg.F;
                tMsg = new PRC_LIST_LEASE_RATETMsg();
            } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.G.no(idx).prcListLeaseRtrnPk_PG.getValue();
                delFlg = glblMsg.G.no(idx).delFlg_PG.getValue();
                ezUptime = glblMsg.G.no(idx).ezUpTime_PG.getValue();
                ezUpTimeZone = glblMsg.G.no(idx).ezUpTimeZone_PG.getValue();
                cMsgArray = bizMsg.G;
                sMsgArray = glblMsg.G;
                tMsg = new PRC_LIST_LEASE_RTRNTMsg();
            } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.H.no(idx).prcListTiValPk_PH.getValue();
                delFlg = glblMsg.H.no(idx).delFlg_PH.getValue();
                ezUptime = glblMsg.H.no(idx).ezUpTime_PH.getValue();
                ezUpTimeZone = glblMsg.H.no(idx).ezUpTimeZone_PH.getValue();
                cMsgArray = bizMsg.H;
                sMsgArray = glblMsg.H;
                tMsg = new PRC_LIST_TI_VALTMsg();
            } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
                prcListPk = glblMsg.I.no(idx).prcListQtyDiscPk_PI.getValue();
                delFlg = glblMsg.I.no(idx).delFlg_PI.getValue();
                ezUptime = glblMsg.I.no(idx).ezUpTime_PI.getValue();
                ezUpTimeZone = glblMsg.I.no(idx).ezUpTimeZone_PI.getValue();
                cMsgArray = bizMsg.I;
                sMsgArray = glblMsg.I;
                tMsg = new PRC_LIST_QTY_DISCTMsg();
            }
            if (!ZYPCommonFunc.hasValue(prcListPk)) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(delFlg)) {
                continue;
            }

            tMsg.setValue("glblCmpyCd", -1, getGlobalCompanyCode());
            tMsg.setValue(keyPk, -1, prcListPk);
            tMsg = EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(ezUptime, ezUpTimeZone, tMsg.getValueString("ezUpTime", -1), tMsg.getValueString("ezUpTimeZone", -1))) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }
            tMsg.setValue("delFlg", -1, ZYPConstant.FLG_ON_Y);
            if (!submitTbl(tMsg, true)) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(prcListPk)));
                // 2017/02/14 QC#17501 mod End
                return;
            }
        }

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.A, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.A.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.B, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.B.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.B.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.C, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.C.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.C.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.D, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.D.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.D.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.E, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.E.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.E.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.F, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.F.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.F.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.G, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.G.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.G.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.H, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.H.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.H.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPTableUtil.deleteRows(glblMsg.I, selectRows);
            // 2019/03/01 QC#30114 Add Start
            if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.I.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.I.getValidCount() - 1));
            }
            // 2019/03/01 QC#30114 Add End
        }
        glblMsg.xxTotCnt_SM.setValue(glblMsg.xxTotCnt_SM.getValueInt()-selectRows.size()); // 2019/02/05 QC#30114 Add
        // bizMsg.xxPageShowFromNum.setValue(1); // 2019/02/05 QC#30114 Del
        NMAL7010CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, cMsgArray, sMsgArray);
    }

    /**
     * Del_QtyDisc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Del_QtyDisc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        NMAL7010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(glblMsg.J, CHK_PJ, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(glblMsg.J.no(idx).prcListQtyDiscDtlPk_PJ)) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.J.no(idx).delFlg_PJ.getValue())) {
                continue;
            }

            PRC_LIST_QTY_DISC_DTLTMsg tMsg = new PRC_LIST_QTY_DISC_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcListQtyDiscDtlPk, bizMsg.J.no(idx).prcListQtyDiscDtlPk_PJ);
            tMsg = (PRC_LIST_QTY_DISC_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.J.no(idx).ezUpTime_PJ.getValue(), glblMsg.J.no(idx).ezUpTimeZone_PJ.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            if (!submitTbl(tMsg, true)) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListQtyDiscDtlPk.getValue())));
                // 2017/02/14 QC#17501 mod End
                return;
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.J, selectRows);
        bizMsg.xxRadioBtn_LI.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowFromNum.setValue(1);
        NMAL7010CommonLogic.loadQtyDtlToCMsg(bizMsg, bizMsg.J, glblMsg.J);
    }

    /**
     * Del_TrxAudc Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7010Scrn00_Del_TrxAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.Y, CHK_TY, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.Y.no(idx).prcTrxAudcPk_TY)) {
                continue;
            }

            PRC_TRX_AUDCTMsg tMsg = new PRC_TRX_AUDCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcTrxAudcPk, bizMsg.Y.no(idx).prcTrxAudcPk_TY);
            tMsg = (PRC_TRX_AUDCTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.Y.no(idx).ezUpTime_TY.getValue(), bizMsg.Y.no(idx).ezUpTimeZone_TY.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                bizMsg.setMessageInfo(NMAM8665E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcTrxAudcPk.getValue())));
                // 2017/02/14 QC#17501 mod End
                return;
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.Y, selectRows);
    }

    //QC#10653
    private boolean otherTblDelete(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, List<String> mdseCdDealConfigList) {
        boolean isSuccess = false;
        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListEquip(bizMsg, glblMsg, true, mdseCdDealConfigList);
            if (!PRC_LIST_STRU_TP.SERVICE.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
                isSuccess = submitPrcGenContr(bizMsg, glblMsg, true);
            }
            if (isSuccess) {
                isSuccess = submitPrcEquipContr(bizMsg, glblMsg, true);
            }
        }
        if (PRC_LIST_STRU_TP.SERVICE.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListSvc(bizMsg, glblMsg, true);
            if (!PRC_LIST_STRU_TP.EQUIPMENT.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
                isSuccess = submitPrcGenContr(bizMsg, glblMsg, true);
            }
            if (isSuccess) {
                isSuccess = submitPrcSvcContr(bizMsg, glblMsg, true);
            }
        }
        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListSvcTier(bizMsg, glblMsg, true);
            if (isSuccess) {
                isSuccess = submitPrcSvcTierContr(bizMsg, glblMsg, true);
            }
        }
        if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListSvcSpecChrg(bizMsg, glblMsg, true);
        }
        if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListSplyPgm(bizMsg, glblMsg, true);
        }
        if (PRC_LIST_STRU_TP.LEASE_RATES.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListLeaseRate(bizMsg, glblMsg, true);
        }
        if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListLeaseRtrnFee(bizMsg, glblMsg, true);
        }
        if (PRC_LIST_STRU_TP.TRADE_INS.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListTradeIn(bizMsg, glblMsg, true);
        }
        if (PRC_LIST_STRU_TP.QTY_DISCOUNT.equals(bizMsg.prcListStruTpCd_BK.getValue())) {
            isSuccess = submitPrcListQtyDisc(bizMsg, glblMsg, true);
            if (isSuccess) {
                isSuccess = submitPrcListQtyDiscDtl(bizMsg, glblMsg, true);
            }
        }
        return isSuccess;
    }

    private boolean submitPrcCatg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            tMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime_H1.getValue(), glblMsg.ezUpTimeZone_H1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        } else {
            BigDecimal maxSortNum = getPrcCatgMaxSortNum(bizMsg);
            if (maxSortNum == null) {
                maxSortNum = BigDecimal.ONE;
            }
            if (maxSortNum.intValue() >= MAX_SORT_NUM.intValue()) {
                maxSortNum = MAX_SORT_NUM;
            } else {
                maxSortNum = maxSortNum.add(BigDecimal.ONE);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgSortNum, maxSortNum);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgNm, bizMsg.prcCatgNm_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgDescTxt, bizMsg.prcCatgDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, bizMsg.stdCcyCd);

        ZYPEZDItemValueSetter.setValue(tMsg.prcListTpCd, bizMsg.prcListTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListDplyNm, bizMsg.prcListDplyNm_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.actvFlg_H1)) {
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, bizMsg.actvFlg_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.custRgtnReqFlg_H1)) {
            ZYPEZDItemValueSetter.setValue(tMsg.custRgtnReqFlg, bizMsg.custRgtnReqFlg_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.custRgtnReqFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrNum, bizMsg.prcContrNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListGrpCd, bizMsg.prcListGrpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcSlsVisTpCd, bizMsg.prcSlsVisTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);

        if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK))) {
            // 2017/02/14 QC#17501 mod Start
//            bizMsg.setMessageInfo(NMAM8020E);
            if(ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
                bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
            } else {
                bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
            }
            // 2017/02/14 QC#17501 mod End
            return false;
        }
        setEzuptime(glblMsg.ezUpTime_H1, glblMsg.ezUpTimeZone_H1, tMsg.ezUpTime, tMsg.ezUpTimeZone);

        return true;
    }

    private boolean submitSubPrcCatg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        // QC#2175
        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
            DS_PRC_CATG_SUBTMsg tMsg = new DS_PRC_CATG_SUBTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.W.no(i).dsPrcCatgSubPk_SW)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsPrcCatgSubPk, bizMsg.W.no(i).dsPrcCatgSubPk_SW);
                tMsg = (DS_PRC_CATG_SUBTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.W.no(i).ezUpTime_SW.getValue(), glblMsg.W.no(i).ezUpTimeZone_SW.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.dsPrcCatgSubPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_TRX_AUDC_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.subPrcCatgCd, bizMsg.W.no(i).subPrcCatgCd_SW);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.W.no(i).effFromDt_SW);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.W.no(i).effThruDt_SW);
            ZYPEZDItemValueSetter.setValue(tMsg.subPrcPrtyNum, bizMsg.W.no(i).subPrcPrtyNum_SW);

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.W.no(i).dsPrcCatgSubPk_SW))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(bizMsg.W.no(i).dsPrcCatgSubPk_SW)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.dsPrcCatgSubPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.dsPrcCatgSubPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.W.no(i).ezUpTime_SW, glblMsg.W.no(i).ezUpTimeZone_SW, tMsg.ezUpTime, tMsg.ezUpTimeZone);
            setPrimaryKey(glblMsg.W.no(i).dsPrcCatgSubPk_SW, tMsg.dsPrcCatgSubPk);
        }
        return true;
    }

    private boolean submitCustAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            PRC_CUST_AUDCTMsg tMsg = new PRC_CUST_AUDCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcPk_CX)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcCustAudcPk, bizMsg.X.no(i).prcCustAudcPk_CX);
                tMsg = (PRC_CUST_AUDCTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.X.no(i).ezUpTime_CX.getValue(), glblMsg.X.no(i).ezUpTimeZone_CX.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcCustAudcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CUST_AUDC_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            tMsg.lineBizTpCd_01.clear();
            tMsg.dsAcctNum_01.clear();
            tMsg.coaChCd_01.clear();
            tMsg.dsAcctGrpCd_01.clear();
            tMsg.coaBrCd_01.clear();
            tMsg.csmpNum_01.clear();
            tMsg.prcGrpPk_01.clear();

            ZYPEZDItemValueSetter.setValue(tMsg.pubFlg_01, ZYPConstant.FLG_OFF_N);
            if (PRC_CUST_AUDC_CATG.PUBLIC.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).pubFlg_CX)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.pubFlg_01, bizMsg.X.no(i).pubFlg_CX);
                }
            } else if (PRC_CUST_AUDC_CATG.PUBLIC_LOB.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd_01, bizMsg.X.no(i).xxScrItem30Txt_X1);
            } else if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_01, bizMsg.X.no(i).xxScrItem30Txt_X1);
            } else if (PRC_CUST_AUDC_CATG.ACCT_CHANNEL.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaChCd_01, bizMsg.X.no(i).xxScrItem30Txt_X1);
            } else if (PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd_01, bizMsg.X.no(i).xxScrItem30Txt_X1);
            } else if (PRC_CUST_AUDC_CATG.BRANCH.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd_01, bizMsg.X.no(i).xxScrItem30Txt_X1);
            } else if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum_01, bizMsg.X.no(i).xxScrItem30Txt_X1);
            } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk_01, toBigDecimal(bizMsg.X.no(i).xxScrItem30Txt_X1.getValue()));
            }

            tMsg.dsAcctNum_02.clear();
            tMsg.dsAcctGrpCd_02.clear();
            tMsg.coaBrCd_02.clear();
            tMsg.csmpNum_02.clear();
            tMsg.prcGrpPk_02.clear();
            if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X2.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_02, bizMsg.X.no(i).xxScrItem30Txt_X2);
            } else if (PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X2.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd_02, bizMsg.X.no(i).xxScrItem30Txt_X2);
            } else if (PRC_CUST_AUDC_CATG.BRANCH.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X2.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd_02, bizMsg.X.no(i).xxScrItem30Txt_X2);
            } else if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X2.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum_02, bizMsg.X.no(i).xxScrItem30Txt_X2);
            } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X2.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk_02, toBigDecimal(bizMsg.X.no(i).xxScrItem30Txt_X2.getValue()));
            }

            tMsg.dsAcctNum_03.clear();
            tMsg.dsAcctGrpCd_03.clear();
            tMsg.coaBrCd_03.clear();
            tMsg.csmpNum_03.clear();
            tMsg.prcGrpPk_03.clear();
            if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X3.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_03, bizMsg.X.no(i).xxScrItem30Txt_X3);
            } else if (PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X3.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd_03, bizMsg.X.no(i).xxScrItem30Txt_X3);
            } else if (PRC_CUST_AUDC_CATG.BRANCH.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X3.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd_03, bizMsg.X.no(i).xxScrItem30Txt_X3);
            } else if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X3.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum_03, bizMsg.X.no(i).xxScrItem30Txt_X3);
            } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X3.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk_03, toBigDecimal(bizMsg.X.no(i).xxScrItem30Txt_X3.getValue()));
            }
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).reqFlg_CX)) {
                ZYPEZDItemValueSetter.setValue(tMsg.reqFlg, bizMsg.X.no(i).reqFlg_CX);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.reqFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.X.no(i).effFromDt_CX);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.X.no(i).effThruDt_CX);
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).defPrcListFlg_CX)) {
                ZYPEZDItemValueSetter.setValue(tMsg.defPrcListFlg, bizMsg.X.no(i).defPrcListFlg_CX);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.defPrcListFlg, ZYPConstant.FLG_OFF_N);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcPk_CX))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcPk_CX)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcCustAudcPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcCustAudcPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.X.no(i).ezUpTime_CX, glblMsg.X.no(i).ezUpTimeZone_CX, tMsg.ezUpTime, tMsg.ezUpTimeZone);
            setPrimaryKey(glblMsg.X.no(i).prcCustAudcPk_CX, tMsg.prcCustAudcPk);
        }
        return true;
    }

    private boolean submitTrxAudc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            PRC_TRX_AUDCTMsg tMsg = new PRC_TRX_AUDCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.Y.no(i).prcTrxAudcPk_TY)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcTrxAudcPk, bizMsg.Y.no(i).prcTrxAudcPk_TY);
                tMsg = (PRC_TRX_AUDCTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.Y.no(i).ezUpTime_TY.getValue(), glblMsg.Y.no(i).ezUpTimeZone_TY.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcTrxAudcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_TRX_AUDC_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            tMsg.prcGrpPk.clear();
            tMsg.dsOrdCatgCd.clear();
            tMsg.dsOrdTpCd_01.clear();
            if (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk, toBigDecimal(bizMsg.Y.no(i).xxScrItem30Txt_Y1.getValue()));
            } else if (PRC_TRX_AUDC_CATG.ORDER_CATEGORY.equals(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, bizMsg.Y.no(i).xxScrItem30Txt_Y1);
            } else if (PRC_TRX_AUDC_CATG.ORDER_REASON.equals(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd_01, bizMsg.Y.no(i).xxScrItem30Txt_Y1);
            }

            tMsg.dsOrdTpCd_02.clear();
            if (PRC_TRX_AUDC_CATG.ORDER_REASON.equals(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y2.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd_02, bizMsg.Y.no(i).xxScrItem30Txt_Y2);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.Y.no(i).effFromDt_TY);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.Y.no(i).effThruDt_TY);

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.Y.no(i).prcTrxAudcPk_TY))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(bizMsg.Y.no(i).prcTrxAudcPk_TY)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcTrxAudcPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcTrxAudcPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.Y.no(i).ezUpTime_TY, glblMsg.Y.no(i).ezUpTimeZone_TY, tMsg.ezUpTime, tMsg.ezUpTimeZone);
            setPrimaryKey(glblMsg.Y.no(i).prcTrxAudcPk_TY, tMsg.prcTrxAudcPk);
        }
        return true;
    }

    private boolean submitPrcGenContr(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        PRC_GENL_CONTR_ATTRBTMsg tMsg = new PRC_GENL_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
        boolean updFlg = ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK);

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            tMsg = (PRC_GENL_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                if (bizMsg.prcListStruTpCd_H1.getValue().equals(bizMsg.prcListStruTpCd_BK.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    updFlg = false;
                    tMsg = new PRC_GENL_CONTR_ATTRBTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
                }
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime_G1.getValue(), glblMsg.ezUpTimeZone_G1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        }

        if (delFlg) {
            if (!removeTbl(tMsg)) {
                return false;
            }
        } else {
            if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.notToExcdFlg_GA)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.notToExcdFlg, bizMsg.notToExcdFlg_GA);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.notToExcdFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.custBidNum, bizMsg.custBidNum_GA);
                // QC#7080
                if (ZYPCommonFunc.hasValue(bizMsg.allwPrcDevnFlg_GA)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.allwPrcDevnFlg, bizMsg.allwPrcDevnFlg_GA);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.allwPrcDevnFlg, ZYPConstant.FLG_OFF_N);
                }
            } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.notToExcdFlg_GB)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.notToExcdFlg, bizMsg.notToExcdFlg_GB);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.notToExcdFlg, ZYPConstant.FLG_OFF_N);
                }
                tMsg.custBidNum.clear();
                // QC#7080
                if (ZYPCommonFunc.hasValue(bizMsg.allwPrcDevnFlg_GB)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.allwPrcDevnFlg, bizMsg.allwPrcDevnFlg_GB.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.allwPrcDevnFlg, ZYPConstant.FLG_OFF_N);
                }
            }

            if (!submitTbl(tMsg, updFlg)) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(updFlg) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.ezUpTime_G1, glblMsg.ezUpTimeZone_G1, tMsg.ezUpTime, tMsg.ezUpTimeZone);
        }
        return true;
    }

    private boolean submitPrcEquipContr(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        PRC_EQUIP_CONTR_ATTRBTMsg tMsg = new PRC_EQUIP_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
        boolean updFlg = ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK);

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            tMsg = (PRC_EQUIP_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                if (bizMsg.prcListStruTpCd_H1.getValue().equals(bizMsg.prcListStruTpCd_BK.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    updFlg = false;
                    tMsg = new PRC_EQUIP_CONTR_ATTRBTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
                }
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime_CA.getValue(), glblMsg.ezUpTimeZone_CA.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        }
        if (delFlg) {
            if (!removeTbl(tMsg)) {
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcLeaseTpCd, bizMsg.prcLeaseTpCd_CA);
            if (ZYPCommonFunc.hasValue(bizMsg.leaseRtrnFeeInclFlg_CA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.leaseRtrnFeeInclFlg, bizMsg.leaseRtrnFeeInclFlg_CA);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.addlShpgFeeInclFlg_CA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.addlShpgFeeInclFlg, bizMsg.addlShpgFeeInclFlg_CA);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.addlShpgFeeInclFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.reloInclFlg_CA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.reloInclFlg, bizMsg.reloInclFlg_CA);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.reloInclFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.hardDriveEraseInclFlg_CA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.hardDriveEraseInclFlg, bizMsg.hardDriveEraseInclFlg_CA);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.hardDriveRmvInclFlg_CA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.hardDriveRmvInclFlg, bizMsg.hardDriveRmvInclFlg_CA);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.notExcdContrPrcFlg_CA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.notExcdContrPrcFlg, bizMsg.notExcdContrPrcFlg_CA);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.notExcdContrPrcFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.spclCsmpExclArNm, bizMsg.spclCsmpExclArNm_CA);
            // QC#7080
            ZYPEZDItemValueSetter.setValue(tMsg.somEligTrxTpCd, bizMsg.somEligTrxTpCd_CA.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.leaseRatePrcCatgCd, bizMsg.leaseRatePrcCatgCd_CA.getValue());

            if (!submitTbl(tMsg, updFlg)) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(updFlg) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.ezUpTime_CA, glblMsg.ezUpTimeZone_CA, tMsg.ezUpTime, tMsg.ezUpTimeZone);
        }

        return true;
    }
    private boolean submitPrcSvcContr(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        PRC_SVC_CONTR_ATTRBTMsg tMsg = new PRC_SVC_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
        boolean updFlg = ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK);

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            tMsg = (PRC_SVC_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                if (bizMsg.prcListStruTpCd_H1.getValue().equals(bizMsg.prcListStruTpCd_BK.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    updFlg = false;
                    tMsg = new PRC_SVC_CONTR_ATTRBTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
                }
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime_CB.getValue(), glblMsg.ezUpTimeZone_CB.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        }

        if (delFlg) {
            if (!removeTbl(tMsg)) {
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            if (ZYPCommonFunc.hasValue(bizMsg.wavBookPrcFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.wavBookPrcFlg, bizMsg.wavBookPrcFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.wavBookPrcFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.wavAllFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.wavAllFlg, bizMsg.wavAllFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.wavAllFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.allowNewAggrContrFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.allowNewAggrContrFlg, bizMsg.allowNewAggrContrFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.allowNewAggrContrFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.allowNewFleetContrFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.allowNewFleetContrFlg, bizMsg.allowNewFleetContrFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.allowNewFleetContrFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.allowAddAggrContrFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.allowAddAggrContrFlg, bizMsg.allowAddAggrContrFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.allowAddAggrContrFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.allowAddFleetContrFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.allowAddFleetContrFlg, bizMsg.allowAddFleetContrFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.allowAddFleetContrFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.allowSvcToDclnFlg_CB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.allowSvcToDclnFlg, bizMsg.allowSvcToDclnFlg_CB);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.grsPrcPct, bizMsg.grsPrcPct_CB);

            if (!submitTbl(tMsg, updFlg)) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(updFlg) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.ezUpTime_CB, glblMsg.ezUpTimeZone_CB, tMsg.ezUpTime, tMsg.ezUpTimeZone);
        }

        return true;
    }

    private boolean submitPrcSvcTierContr(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        PRC_SVC_TIER_CONTR_ATTRBTMsg tMsg = new PRC_SVC_TIER_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
        boolean updFlg = ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK);

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            tMsg = (PRC_SVC_TIER_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                if (bizMsg.prcListStruTpCd_H1.getValue().equals(bizMsg.prcListStruTpCd_BK.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    updFlg = false;
                    tMsg = new PRC_SVC_TIER_CONTR_ATTRBTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
                }
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime_CC.getValue(), glblMsg.ezUpTimeZone_CC.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        }

        if (delFlg) {
            if (!removeTbl(tMsg)) {
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcTierSvcOfferCd, bizMsg.prcTierSvcOfferCd_CC);
            if (ZYPCommonFunc.hasValue(bizMsg.allowSvcToDclnFlg_CC)) {
                ZYPEZDItemValueSetter.setValue(tMsg.allowSvcToDclnFlg, bizMsg.allowSvcToDclnFlg_CC);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
            }

            if (!submitTbl(tMsg, updFlg)) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(updFlg) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), tMsg.prcCatgCd.getValue()));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.ezUpTime_CC, glblMsg.ezUpTimeZone_CC, tMsg.ezUpTime, tMsg.ezUpTimeZone);
        }
        return true;
    }

    //QC#10653
    private boolean submitPrcListEquip(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg, List<String> mdseCdList) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).delFlg_PA.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxModeCd_A1)) {
                continue;
            }
            PRC_LIST_EQUIPTMsg tMsg = new PRC_LIST_EQUIPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPk_PA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipPk, glblMsg.A.no(i).prcListEquipPk_PA);
                tMsg = (PRC_LIST_EQUIPTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(i).ezUpTime_PA.getValue(), glblMsg.A.no(i).ezUpTimeZone_PA.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }

            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.A.no(i).delFlg_PA);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipConfigNum, glblMsg.A.no(i).prcListEquipConfigNum_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipConfigNm, glblMsg.A.no(i).prcListEquipConfigNm_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcQlfyTpCd, glblMsg.A.no(i).prcQlfyTpCd_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcQlfyValTxt, glblMsg.A.no(i).prcQlfyValTxt_PA);
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).pkgUomCd_A2)) {  // QC#7958
                    ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, glblMsg.A.no(i).pkgUomCd_A2); // QC#7958
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, glblMsg.A.no(i).pkgUomCd_PA);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipPrcAmt, glblMsg.A.no(i).prcListEquipPrcAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.minPrcAmt, glblMsg.A.no(i).minPrcAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.maxPrcAmt, glblMsg.A.no(i).maxPrcAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcTermUomCd, glblMsg.A.no(i).prcTermUomCd_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcTermAot, glblMsg.A.no(i).prcTermAot_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.custBidQty, glblMsg.A.no(i).custBidQty_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.mlyPmtAmt, glblMsg.A.no(i).mlyPmtAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.minLeasePmtAmt, glblMsg.A.no(i).minLeasePmtAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.maxLeasePmtAmt, glblMsg.A.no(i).maxLeasePmtAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaPk, glblMsg.A.no(i).prcFmlaPk_PA);
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).openMktFlg_PA)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.openMktFlg, glblMsg.A.no(i).openMktFlg_PA);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.openMktFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.quotRevAmt, glblMsg.A.no(i).quotRevAmt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.compCd, glblMsg.A.no(i).compCd_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.A.no(i).effFromDt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.A.no(i).effThruDt_PA);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.A.no(i).delFlg_PA);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPk_PA))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPk_PA)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListEquipPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListEquipPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            
            //QC#10653
            if (PRC_QLFY_TP.ITEM_CODE.equals(tMsg.prcQlfyTpCd.getValue())
            		&& mdseCdList != null
            		&& !mdseCdList.contains(tMsg.prcQlfyValTxt.getValue())) {
                // 2018/02/27 QC#24422 Mod Start
//            	mdseCdList.add(tMsg.prcQlfyValTxt.getValue());
                String mdseCd = NMAL7010CommonLogic.getMdseCd(tMsg.glblCmpyCd.getValue(), tMsg.prcQlfyValTxt.getValue());
                mdseCdList.add(mdseCd);
                // 2018/02/27 QC#24422 Mod End
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListEquipPk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListSvc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.B.no(i).delFlg_PB.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).xxModeCd_B1)) {
                continue;
            }

            PRC_LIST_SVCTMsg tMsg = new PRC_LIST_SVCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListSvcPk_PB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListSvcPk, glblMsg.B.no(i).prcListSvcPk_PB);
                tMsg = (PRC_LIST_SVCTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.B.no(i).ezUpTime_PB.getValue(), glblMsg.B.no(i).ezUpTimeZone_PB.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListSvcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SVC_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRateTpCd, glblMsg.B.no(i).prcRateTpCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcListMdseCd, glblMsg.B.no(i).prcListMdseCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, glblMsg.B.no(i).mdlId_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, glblMsg.B.no(i).mdlNm_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcMtrPkgPk, glblMsg.B.no(i).prcMtrPkgPk_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcPlnTpCd, glblMsg.B.no(i).prcSvcPlnTpCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcContrTpCd, glblMsg.B.no(i).prcSvcContrTpCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, glblMsg.B.no(i).mtrLbCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.minCopyVolCnt, glblMsg.B.no(i).minCopyVolCnt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.maxCopyVolCnt, glblMsg.B.no(i).maxCopyVolCnt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcListBandCd, glblMsg.B.no(i).prcListBandCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.baseAmt, glblMsg.B.no(i).baseAmt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.minBaseAmt, glblMsg.B.no(i).minBaseAmt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.maxBaseAmt, glblMsg.B.no(i).maxBaseAmt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.cpcAmtRate, glblMsg.B.no(i).cpcAmtRate_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.minCpcAmtRate, glblMsg.B.no(i).minCpcAmtRate_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.maxCpcAmtRate, glblMsg.B.no(i).maxCpcAmtRate_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.termFromMthAot, glblMsg.B.no(i).termFromMthAot_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.termThruMthAot, glblMsg.B.no(i).termThruMthAot_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcZoneCd, glblMsg.B.no(i).prcSvcZoneCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, glblMsg.B.no(i).mdseCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcMtrPkgBllgMtrPk, glblMsg.B.no(i).prcMtrPkgBllgMtrPk_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.quotRevAmt, glblMsg.B.no(i).quotRevAmt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.compCd, glblMsg.B.no(i).compCd_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.B.no(i).effFromDt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.B.no(i).effThruDt_PB);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.B.no(i).delFlg_PB);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListSvcPk_PB))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListSvcPk_PB)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListSvcPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListSvcPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListSvcPk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListSvcTier(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(i).delFlg_PC.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.C.no(i).xxModeCd_C1)) {
                continue;
            }

            PRC_LIST_SVC_TIERTMsg tMsg = new PRC_LIST_SVC_TIERTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcListSvcTierPk_PC)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListSvcTierPk, glblMsg.C.no(i).prcListSvcTierPk_PC);
                tMsg = (PRC_LIST_SVC_TIERTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.C.no(i).ezUpTime_PC.getValue(), glblMsg.C.no(i).ezUpTimeZone_PC.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListSvcTierPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SVC_TIER_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcTierTpCd, glblMsg.C.no(i).prcSvcTierTpCd_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, glblMsg.C.no(i).mdlId_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, glblMsg.C.no(i).mdlNm_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.prcMtrPkgPk, glblMsg.C.no(i).prcMtrPkgPk_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcPlnTpCd, glblMsg.C.no(i).prcSvcPlnTpCd_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcContrTpCd, glblMsg.C.no(i).prcSvcContrTpCd_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, glblMsg.C.no(i).mtrLbCd_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.avgCopyVolCnt, glblMsg.C.no(i).avgCopyVolCnt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.minCopyVolCnt, glblMsg.C.no(i).minCopyVolCnt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.maxCopyVolCnt, glblMsg.C.no(i).maxCopyVolCnt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.prcListBandCd, glblMsg.C.no(i).prcListBandCd_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.baseAmt, glblMsg.C.no(i).baseAmt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.minBaseAmt, glblMsg.C.no(i).minBaseAmt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.maxBaseAmt, glblMsg.C.no(i).maxBaseAmt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.cpcAmtRate, glblMsg.C.no(i).cpcAmtRate_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.minCpcAmtRate, glblMsg.C.no(i).minCpcAmtRate_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.maxCpcAmtRate, glblMsg.C.no(i).maxCpcAmtRate_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.termFromMthAot, glblMsg.C.no(i).termFromMthAot_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.termThruMthAot, glblMsg.C.no(i).termThruMthAot_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, glblMsg.C.no(i).mdseCd_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.prcMtrPkgBllgMtrPk, glblMsg.C.no(i).prcMtrPkgBllgMtrPk_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.C.no(i).effFromDt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.C.no(i).effThruDt_PC);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.C.no(i).delFlg_PC);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcListSvcTierPk_PC))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcListSvcTierPk_PC)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListSvcTierPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListSvcTierPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListSvcTierPk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListSvcSpecChrg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.D.no(i).delFlg_PD.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).xxModeCd_D1)) {
                continue;
            }

            PRC_LIST_ADDL_CHRGTMsg tMsg = new PRC_LIST_ADDL_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).prcListAddlChrgPk_PD)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListAddlChrgPk, glblMsg.D.no(i).prcListAddlChrgPk_PD);
                tMsg = (PRC_LIST_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.D.no(i).ezUpTime_PD.getValue(), glblMsg.D.no(i).ezUpTimeZone_PD.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListAddlChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_ADDL_CHRG_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, glblMsg.D.no(i).mdseCd_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.prcAddlChrgTpCd, glblMsg.D.no(i).prcAddlChrgTpCd_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.mktMdlSegCd, glblMsg.D.no(i).mktMdlSegCd_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, glblMsg.D.no(i).mdlId_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, glblMsg.D.no(i).mdlNm_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.addlChrgPrcAmt, glblMsg.D.no(i).addlChrgPrcAmt_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.D.no(i).effFromDt_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.D.no(i).effThruDt_PD);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.D.no(i).delFlg_PD);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.D.no(i).prcListAddlChrgPk_PD))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.D.no(i).prcListAddlChrgPk_PD)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListAddlChrgPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListAddlChrgPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListAddlChrgPk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListSplyPgm(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.E.no(i).delFlg_PE.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).xxModeCd_E1)) {
                continue;
            }

            PRC_LIST_SPLY_PGMTMsg tMsg = new PRC_LIST_SPLY_PGMTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcListSplyPgmPk_PE)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListSplyPgmPk, glblMsg.E.no(i).prcListSplyPgmPk_PE);
                tMsg = (PRC_LIST_SPLY_PGMTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.E.no(i).ezUpTime_PE.getValue(), glblMsg.E.no(i).ezUpTimeZone_PE.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListSplyPgmPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SPLY_PGM_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, glblMsg.E.no(i).mdlId_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, glblMsg.E.no(i).mdlNm_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.prcMtrPkgPk, glblMsg.E.no(i).prcMtrPkgPk_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcPlnTpCd, glblMsg.E.no(i).prcSvcPlnTpCd_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcContrTpCd, glblMsg.E.no(i).prcSvcContrTpCd_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, glblMsg.E.no(i).mtrLbCd_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.minCopyVolCnt, glblMsg.E.no(i).minCopyVolCnt_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.maxCopyVolCnt, glblMsg.E.no(i).maxCopyVolCnt_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.prcListBandCd, glblMsg.E.no(i).prcListBandCd_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.totBaseAmt, glblMsg.E.no(i).totBaseAmt_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.splyBaseAmt, glblMsg.E.no(i).splyBaseAmt_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.cpcAmtRate, glblMsg.E.no(i).cpcAmtRate_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.minCpcAmtRate, glblMsg.E.no(i).minCpcAmtRate_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.maxCpcAmtRate, glblMsg.E.no(i).maxCpcAmtRate_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.termFromMthAot, glblMsg.E.no(i).termFromMthAot_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.termThruMthAot, glblMsg.E.no(i).termThruMthAot_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, glblMsg.E.no(i).mdseCd_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.prcMtrPkgBllgMtrPk, glblMsg.E.no(i).prcMtrPkgBllgMtrPk_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.prcSvcZoneCd, glblMsg.E.no(i).prcSvcZoneCd_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.splyAgmtPlnPk, glblMsg.E.no(i).splyAgmtPlnPk_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.E.no(i).effFromDt_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.E.no(i).effThruDt_PE);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.E.no(i).delFlg_PE);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcListSplyPgmPk_PE))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcListSplyPgmPk_PE)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListSplyPgmPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListSplyPgmPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListSplyPgmPk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListLeaseRate(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.F.no(i).delFlg_PF.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.F.no(i).xxModeCd_F1)) {
                continue;
            }

            PRC_LIST_LEASE_RATETMsg tMsg = new PRC_LIST_LEASE_RATETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).prcListLeaseRatePk_PF)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListLeaseRatePk, glblMsg.F.no(i).prcListLeaseRatePk_PF);
                tMsg = (PRC_LIST_LEASE_RATETMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.F.no(i).ezUpTime_PF.getValue(), glblMsg.F.no(i).ezUpTimeZone_PF.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListLeaseRatePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_LEASE_RATE_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcLeaseCmpyAbbrNm, glblMsg.F.no(i).prcLeaseCmpyAbbrNm_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, glblMsg.F.no(i).dsAcctNum_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, glblMsg.F.no(i).dsAcctNm_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.prcPgmTpCd, glblMsg.F.no(i).prcPgmTpCd_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.prcEquipTpCd, glblMsg.F.no(i).prcEquipTpCd_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, glblMsg.F.no(i).mdlId_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, glblMsg.F.no(i).mdlNm_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.minTotFinAmt, glblMsg.F.no(i).minTotFinAmt_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.maxTotFinAmt, glblMsg.F.no(i).maxTotFinAmt_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.termFromMthAot, glblMsg.F.no(i).termFromMthAot_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.termThruMthAot, glblMsg.F.no(i).termThruMthAot_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.leaseRate, glblMsg.F.no(i).leaseRate_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.F.no(i).effFromDt_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.F.no(i).effThruDt_PF);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.F.no(i).delFlg_PF);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.F.no(i).prcListLeaseRatePk_PF))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.F.no(i).prcListLeaseRatePk_PF)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListLeaseRatePk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListLeaseRatePk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListLeaseRatePk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListLeaseRtrnFee(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.G.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.G.no(i).delFlg_PG.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.G.no(i).xxModeCd_G1)) {
                continue;
            }

            PRC_LIST_LEASE_RTRNTMsg tMsg = new PRC_LIST_LEASE_RTRNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.G.no(i).prcListLeaseRtrnPk_PG)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListLeaseRtrnPk, glblMsg.G.no(i).prcListLeaseRtrnPk_PG);
                tMsg = (PRC_LIST_LEASE_RTRNTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.G.no(i).ezUpTime_PG.getValue(), glblMsg.G.no(i).ezUpTimeZone_PG.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListLeaseRtrnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_LEASE_RTRN_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcLeaseCmpyAbbrNm, glblMsg.G.no(i).prcLeaseCmpyAbbrNm_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.svcSegCd, glblMsg.G.no(i).svcSegCd_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.prcInOutRgCd, glblMsg.G.no(i).prcInOutRgCd_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.dstMileAmt, glblMsg.G.no(i).dstMileAmt_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnFeeAmt, glblMsg.G.no(i).rtrnFeeAmt_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.G.no(i).effFromDt_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.G.no(i).effThruDt_PG);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.G.no(i).delFlg_PG);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.G.no(i).prcListLeaseRtrnPk_PG))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.G.no(i).prcListLeaseRtrnPk_PG)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListLeaseRtrnPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListLeaseRtrnPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListLeaseRtrnPk.getValue(), i);
        }
        return true;
    }
    private boolean submitPrcListTradeIn(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.H.no(i).delFlg_PH.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.H.no(i).xxModeCd_H1)) {
                continue;
            }

            PRC_LIST_TI_VALTMsg tMsg = new PRC_LIST_TI_VALTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).prcListTiValPk_PH)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListTiValPk, glblMsg.H.no(i).prcListTiValPk_PH);
                tMsg = (PRC_LIST_TI_VALTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.H.no(i).ezUpTime_PH.getValue(), glblMsg.H.no(i).ezUpTimeZone_PH.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListTiValPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_TI_VAL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, glblMsg.H.no(i).mdlId_PH);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, glblMsg.H.no(i).mdlNm_PH);
                ZYPEZDItemValueSetter.setValue(tMsg.tiAmt, glblMsg.H.no(i).tiAmt_PH);
                if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).mtrRngReqFlg_PH)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.mtrRngReqFlg, glblMsg.H.no(i).mtrRngReqFlg_PH);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.mtrRngReqFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.fromMtrCopyVolCnt, glblMsg.H.no(i).fromMtrCopyVolCnt_PH);
                ZYPEZDItemValueSetter.setValue(tMsg.thruMtrCopyVolCnt, glblMsg.H.no(i).thruMtrCopyVolCnt_PH);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.H.no(i).effFromDt_PH);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.H.no(i).effThruDt_PH);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.H.no(i).delFlg_PH);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.H.no(i).prcListTiValPk_PH))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.H.no(i).prcListTiValPk_PH)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListTiValPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListTiValPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            NMAL7010Query.getInstance().reloadPrcListLine(bizMsg, glblMsg, tMsg.prcCatgCd.getValue(), tMsg.prcListTiValPk.getValue(), i);
        }
        return true;
    }

    private boolean submitPrcListQtyDisc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.I.no(i).delFlg_PI.getValue())) {
                continue;
            }

            PRC_LIST_QTY_DISCTMsg tMsg = new PRC_LIST_QTY_DISCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcListQtyDiscPk_PI)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListQtyDiscPk, bizMsg.I.no(i).prcListQtyDiscPk_PI);
                tMsg = (PRC_LIST_QTY_DISCTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.I.no(i).ezUpTime_PI.getValue(), glblMsg.I.no(i).ezUpTimeZone_PI.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListQtyDiscPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_QTY_DISC_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
                setSMsgQtyDiscDtl(glblMsg, glblMsg.I.no(i).xxDtlCnt_PI.getValue(), tMsg.prcListQtyDiscPk.getValue());
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcQlfyTpCd, glblMsg.I.no(i).prcQlfyTpCd_PI);
                ZYPEZDItemValueSetter.setValue(tMsg.prcQlfyValTxt, glblMsg.I.no(i).prcQlfyValTxt_PI);
                ZYPEZDItemValueSetter.setValue(tMsg.qtyDiscPrcAmt, glblMsg.I.no(i).qtyDiscPrcAmt_PI);
                ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, glblMsg.I.no(i).pkgUomCd_PI);
                if (ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcBreakFlg_PI)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcBreakFlg, glblMsg.I.no(i).prcBreakFlg_PI);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcBreakFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.I.no(i).effFromDt_PI);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.I.no(i).effThruDt_PI);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.I.no(i).delFlg_PI);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcListQtyDiscPk_PI))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListQtyDiscPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListQtyDiscPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
        }
        return true;
    }

    private void setSMsgQtyDiscDtl(NMAL7010SMsg glblMsg, BigDecimal xxDtlCnt, BigDecimal prcListQtyDiscPk) {
        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
            if (xxDtlCnt.equals(glblMsg.J.no(i).xxDtlCnt_PJ.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.J.no(i).prcListQtyDiscPk_PJ, prcListQtyDiscPk);
            }
        }
    }

    private boolean submitPrcListQtyDiscDtl(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, boolean delFlg) {
        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.J.no(i).delFlg_PJ.getValue())) {
                continue;
            }

            PRC_LIST_QTY_DISC_DTLTMsg tMsg = new PRC_LIST_QTY_DISC_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.J.no(i).prcListQtyDiscDtlPk_PJ)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListQtyDiscDtlPk, glblMsg.J.no(i).prcListQtyDiscDtlPk_PJ);
                tMsg = (PRC_LIST_QTY_DISC_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.J.no(i).ezUpTime_PJ.getValue(), glblMsg.J.no(i).ezUpTimeZone_PJ.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListQtyDiscDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_QTY_DISC_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            }

            if (delFlg) {
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListQtyDiscPk, glblMsg.J.no(i).prcListQtyDiscPk_PJ);
                ZYPEZDItemValueSetter.setValue(tMsg.qtyDiscDtlQty, glblMsg.J.no(i).qtyDiscDtlQty_PJ);
                ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, glblMsg.J.no(i).pkgUomCd_PJ);
                ZYPEZDItemValueSetter.setValue(tMsg.prcBreakAmt, glblMsg.J.no(i).prcBreakAmt_PJ);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.J.no(i).delFlg_PJ);
            }

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(glblMsg.J.no(i).prcListQtyDiscDtlPk_PJ))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(ZYPCommonFunc.hasValue(glblMsg.J.no(i).prcListQtyDiscDtlPk_PJ)) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListQtyDiscDtlPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListQtyDiscDtlPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
        }
        return true;
    }

    // QC#2174 Add Start
    private boolean submitPrcListEquipDtl(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        for (int i = 0; i < glblMsg.Q.getValidCount(); i++) {

            PRC_LIST_EQUIP_DTLTMsg tMsg = new PRC_LIST_EQUIP_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());

            if (NMAL7010Constant.MODE_MODIFY.equals(glblMsg.Q.no(i).xxModeCd_QP.getValue())) {

                if (ZYPCommonFunc.hasValue(glblMsg.Q.no(i).prcListEquipDtlPk_QP)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipDtlPk, glblMsg.Q.no(i).prcListEquipDtlPk_QP);
                    tMsg = (PRC_LIST_EQUIP_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

                    if (tMsg == null) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    } else {
                        if (!ZYPDateUtil.isSameTimeStamp(glblMsg.Q.no(i).ezUpTime_QP.getValue(), glblMsg.Q.no(i).ezUpTimeZone_QP.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                    }
                } else {
                    continue;
                }

            } else if (NMAL7010Constant.MODE_NEW.equals(glblMsg.Q.no(i).xxModeCd_QP.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(NMAL7010Constant.SQ_PRC_LIST_EQUIP_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd_H1);
            } else {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipPk, glblMsg.Q.no(i).prcListEquipPk_QP.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.qtyDiscDtlQty, glblMsg.Q.no(i).qtyDiscDtlQty_QP.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.prcBreakAmt, glblMsg.Q.no(i).prcBreakAmt_QP.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.delFlg, glblMsg.Q.no(i).delFlg_QP.getValue());

            if (!submitTbl(tMsg, NMAL7010Constant.MODE_MODIFY.equals(glblMsg.Q.no(i).xxModeCd_QP.getValue()))) {
                // 2017/02/14 QC#17501 mod Start
//                bizMsg.setMessageInfo(NMAM8020E);
                if(NMAL7010Constant.MODE_MODIFY.equals(glblMsg.Q.no(i).xxModeCd_QP.getValue())) {
                    bizMsg.setMessageInfo(NMAM8664E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListEquipDtlPk.getValue())));
                } else {
                    bizMsg.setMessageInfo(NMAM8571E, toArray(tMsg.getTableName(), String.valueOf(tMsg.prcListEquipDtlPk.getValue())));
                }
                // 2017/02/14 QC#17501 mod End
                return false;
            }
            setEzuptime(glblMsg.Q.no(i).ezUpTime_QP, glblMsg.Q.no(i).ezUpTimeZone_QP, tMsg.ezUpTime, tMsg.ezUpTimeZone);
            setPrimaryKey(glblMsg.Q.no(i).prcListEquipDtlPk_QP, tMsg.prcListEquipDtlPk);
        }
        return true;
    }
    // QC#2174 End

    private BigDecimal getPrcCatgMaxSortNum(NMAL7010CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcCatgMaxSortNum(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return BigDecimal.ZERO;
        }

        return (BigDecimal) ssmResult.getResultObject();
    }

    private boolean logicalDelTbl(EZDTMsg inTMsg) {
        EZDTBLAccessor.logicalRemove(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean removeTbl(EZDTMsg inTMsg) {
        EZDTBLAccessor.remove(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean submitTbl(EZDTMsg inTMsg, boolean isExists) {
        if (isExists) {
            EZDTBLAccessor.update(inTMsg);
        } else {
            EZDTBLAccessor.insert(inTMsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private void setEzuptime(EZDSStringItem ezuptime, EZDSStringItem ezuptimezone, EZDTStringItem tMsgEzuptime, EZDTStringItem tMsgEzuptimezone) {
        ZYPEZDItemValueSetter.setValue(ezuptime, tMsgEzuptime.getValue());
        ZYPEZDItemValueSetter.setValue(ezuptimezone, tMsgEzuptimezone.getValue());
    }

    private void setPrimaryKey(EZDSBigDecimalItem pk, EZDTBigDecimalItem tMsgPk) {
        if (!ZYPCommonFunc.hasValue(pk)) {
            ZYPEZDItemValueSetter.setValue(pk, tMsgPk.getValue());
        }
    }

    // 2017/02/14 QC#17501 add Start
    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }
    // 2017/02/14 QC#17501 add End

}
