/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2170.common;

import static business.blap.NWAL2170.constant.NWAL2170Constant.NWAM0181E;
import static business.blap.NWAL2170.constant.NWAL2170Constant.NWAM0729E;
import static business.blap.NWAL2170.constant.NWAL2170Constant.NWAM0989E;
import static business.blap.NWAL2170.constant.NWAL2170Constant.NWAM0990E;
import static business.blap.NWAL2170.constant.NWAL2170Constant.NZZM0000E;
import static business.blap.NWAL2170.constant.NWAL2170Constant.SQLID_GET_SELL_TO_CUST_FOR_CODE;
import static business.blap.NWAL2170.constant.NWAL2170Constant.SQLID_GET_SELL_TO_CUST_FOR_NAME;
import static business.blap.NWAL2170.constant.NWAL2170Constant.NWAL2370_IMPT_MODE_CD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2170.NWAL2170CMsg;
import business.blap.NWAL2170.NWAL2170Query;
import business.blap.NWAL2170.NWAL2170SMsg;
import business.blap.NWAL2170.NWAL2170_ACMsg;
import business.blap.NWAL2170.NWAL2170_ASMsg;
import business.blap.NWAL2170.NWAL2170_BCMsg;
import business.blap.NWAL2170.NWAL2170_FCMsg;
import business.blap.NWAL2170.NWAL2170_HCMsg;
import business.blap.NWAL2170.NWAL2170_KCMsg;
import business.blap.NWAL2170.NWAL2170_LCMsg;
import business.blap.NWAL2170.NWAL2170_RCMsg;
import business.blap.NWAL2170.NWAL2170_TCMsg;
import business.blap.NWAL2170.NWAL2170_UCMsg;
import business.blap.NWAL2170.constant.NWAL2170Constant;
import business.blap.NWAL2170.constant.NWAL2170Constant.XX_PAGE;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CCYTMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.DS_IMPT_SVC_PRCTMsgArray;
import business.db.DS_IMPT_SVC_USG_PRCTMsg;
import business.db.DS_IMPT_SVC_USG_PRCTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_TransactionRuleListPMsg;
import business.parts.NMZC610001_xxMsgIdListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL2170CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2016/02/03   Fujitsu         M.Yamada        Update          QC#2945
 * 2016/03/10   Fujitsu         M.Yamada        Update          QC#5089
 * 2016/04/25   Fujitsu         M.Yamada        Update          QC#7009
 * 2016/08/22   Fujitsu         M.Yamada        Update          QC#13365
 * 2016/09/28   Fujitsu         M.Yamada        Update          QC#8659
 * 2016/10/31   Fujitsu         M.Yamada        Update          QC#15648
 * 2016/12/27   Fujitsu         S.Iidaka        Update          QC#16141
 * 2017/02/13   Fujitsu         N.Aoyama        Update          QC#16575
 * 2017/05/30   Fujitsu         S.Ohki          Update          RS#8233
 * 2017/09/27   Fujitsu         H.Sugawara      Update          QC#21341
 * 2017/10/19   Fujitsu         R.Nakamura      Update          QC#21862
 * 2018/03/29   Fujitsu         S.Ohki          Update          QC#24303
 * 2018/04/04   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/04/04   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/08/03   Fujitsu         W.Honda         Update          QC#26325
 * 2018/08/28   Fujitsu         K.Ishizuka      Update          QC#27968
 * 2019/02/06   Fujitsu         K.Kato          Update          QC#30237
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL2170CommonLogic {

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param bizMsg        NWAL2170CMsg
     * </pre>
     */
    public static void createPulldownList(String glblCmpyCd, String slsDt, NWAL2170CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_CONTR_TP", bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_PLN_TP", bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);

        createBillWithEquipPulldown(bizMsg);
        createContractIndicatorPulldown(bizMsg);

        createBllgCyclePulldown(glblCmpyCd, slsDt, bizMsg);

        ZYPCodeDataUtil.createPulldownList("MTR_READ_METH", bizMsg.mtrReadMethCd_L, bizMsg.mtrReadMethDescTxt_L);
    }

    private static void createContractIndicatorPulldown(NWAL2170CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NWAL2170Query.getInstance().getContractIndicatorList();
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgDescTxt_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_DESC_TXT"));
            i++;
        }
    }

    /**
     * <pre>
     * createBillByTpPulldown
     * @param aBizMsg   NWAL2170_ACMsg
     * @param bizMsg    NWAL2170CMsg
     * </pre>
     */
    public static void createBillByTpPulldown(NWAL2170_ACMsg aBizMsg, NWAL2170CMsg bizMsg) {
        if (isImport(bizMsg)) {
            ZYPCodeDataUtil.createPulldownList("BILL_BY_TP", aBizMsg.billByTpCd_L, aBizMsg.billByTpDescTxt_L);
            return;
        }
    }

    private static void createBllgCyclePulldown(String glblCmpyCd, String slsDt, NWAL2170CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NWAL2170Query.getInstance().getBllgCycleList(glblCmpyCd, slsDt, bizMsg);
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2170_LCMsg lBizMsg = bizMsg.L.no(i);
            ZYPEZDItemValueSetter.setValue(lBizMsg.bllgCycleAot_L, (BigDecimal) rsltMap.get("BLLG_CYCLE_MTH_AOT"));

            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleCd_L.no(i), (String) rsltMap.get("BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleDescTxt_L.no(i), (String) rsltMap.get("BLLG_CYCLE_DESC_TXT"));

            i++;
        }
        bizMsg.L.setValidCount(i);
    }

    private static void createBillWithEquipPulldown(NWAL2170CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(1), ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(1), ZYPConstant.FLG_ON_Y);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * </pre>
     */
    public static void getInitData(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        bizMsg.xxPageCd_L.no(0).setValue(XX_PAGE.PAGE_IMPT.getCode());
        bizMsg.xxPageNm_L.no(0).setValue(XX_PAGE.PAGE_IMPT.getName());
        bizMsg.xxPageCd_L.no(1).setValue(XX_PAGE.PAGE_SHELL.getCode());
        bizMsg.xxPageNm_L.no(1).setValue(XX_PAGE.PAGE_SHELL.getName());
        // Add Start 2017/10/19 QC#21862
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        // Add End 2017/10/19 QC#21862

        bizMsg.A.clear();
        bizMsg.cpoSvcPk.clear();
        if (!ZYPCommonFunc.hasValue(bizMsg.xxScrItem50Txt)) {
            bizMsg.A.setValidCount(0);
            return;
        }
        if (isImport(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefNum, bizMsg.xxScrItem50Txt);
        }

        S21SsmEZDResult rsltSvc = NWAL2170Query.getInstance().getInitDataFromCpoSvc(glblCmpyCd, bizMsg);

        bizMsg.A.setValidCount(0);
        bizMsg.B.setValidCount(0);
        if (rsltSvc.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
            setSvcRsltToBizMsg(bizMsg, rsltList, glblCmpyCd);
            setSvcPrcInfoToBizMsg(glblCmpyCd, bizMsg);

        } else {
            bizMsg.xxScrItem50Txt.setErrorInfo(1, NZZM0000E);
            bizMsg.setMessageInfo(NZZM0000E);
        }
        List<Integer> ixL = new ArrayList<Integer>();
        BigDecimal dsImptSvcLineNum = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (dsImptSvcLineNum.compareTo(bizMsg.A.no(i).dsImptSvcLineNum.getValue()) != 0) {
                ixL.clear();
                dsImptSvcLineNum = bizMsg.A.no(i).dsImptSvcLineNum.getValue();
                setPriceInfoToParam(bizMsg, dsImptSvcLineNum);
                NWAL2170CommonLogic.calcAmt(bizMsg, dsImptSvcLineNum, ixL, glblCmpyCd);
            }

            NWAL2170CommonLogic.createBillByTpPulldown(bizMsg.A.no(i), bizMsg);
        }

    }

    /**
     * @param bizMsg NWAL2170CMsg
     * @return if import process then return true.
     */
    public static boolean isImport(NWAL2170CMsg bizMsg) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(bizMsg.xxPageCd.getValue());
    }

    private static void setPriceInfoToParam(NWAL2170CMsg bizMsg, BigDecimal dsImptSvcLineNum) {
        ZYPTableUtil.clear(bizMsg.R);
        int ixR = 0;
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            if (dsImptSvcLineNum.compareTo(bizMsg.F.no(i).dsImptSvcLineNum_F.getValue()) == 0) {
                EZDMsg.copy(bizMsg.F.no(i), "F", bizMsg.R.no(ixR++), "R");
            }
        }
        bizMsg.R.setValidCount(ixR);

        ZYPTableUtil.clear(bizMsg.S);
        int ixS = 0;
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            if (dsImptSvcLineNum.compareTo(bizMsg.G.no(i).dsImptSvcLineNum_G.getValue()) == 0) {
                EZDMsg.copy(bizMsg.G.no(i), "G", bizMsg.S.no(ixS++), "S");
            }
        }
        bizMsg.S.setValidCount(ixS);

        ZYPTableUtil.clear(bizMsg.T);
        int ixT = 0;
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (dsImptSvcLineNum.compareTo(bizMsg.H.no(i).dsImptSvcLineNum_H.getValue()) == 0) {
                EZDMsg.copy(bizMsg.H.no(i), "H", bizMsg.T.no(ixT++), "T");
            }
        }
        bizMsg.T.setValidCount(ixT);

        ZYPTableUtil.clear(bizMsg.U);
        int ixU = 0;
        for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).dsImptSvcLineNum_K) //
                    && dsImptSvcLineNum.compareTo(bizMsg.K.no(i).dsImptSvcLineNum_K.getValue()) == 0) {
                EZDMsg.copy(bizMsg.K.no(i), "K", bizMsg.U.no(ixU++), "U");
            }
        }
        bizMsg.U.setValidCount(ixU);
    }

    private static void setSvcPrcInfoToBizMsg(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        S21SsmEZDResult rsltSph = NWAL2170Query.getInstance().getSvcPrcHdr(glblCmpyCd, bizMsg);
        S21SsmEZDResult rsltAbp = NWAL2170Query.getInstance().getSvcAddlBasePrc(glblCmpyCd, bizMsg);
        S21SsmEZDResult rsltAcp = NWAL2170Query.getInstance().getSvcAddlChrgPrc(glblCmpyCd, bizMsg);

        if (rsltAbp.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAbp.getResultObject();
            setSvcPrcAbpRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.H.setValidCount(0);
        }

        if (rsltAcp.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAcp.getResultObject();
            setSvcPrcAcpRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.K.setValidCount(0);
        }

        if (rsltSph.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSph.getResultObject();
            setSvcPrcHdrRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.F.setValidCount(0);
        }
    }

    private static void setSvcPrcAcpRsltToBizMsg(NWAL2170CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int i = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2170_KCMsg kBizMsg = bizMsg.K.no(i);

            ZYPEZDItemValueSetter.setValue(kBizMsg.dsImptSvcLineNum_K, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.addlChrgMdseCd_K, (String) rsltMap.get("ADDL_CHRG_MDSE_CD"));
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(kBizMsg.addlChrgPrcDealAmt_K, (BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(kBizMsg.addlChrgPrcFuncAmt_K, (BigDecimal) rsltMap.get("ADDL_CHRG_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(kBizMsg.dealPrcListPrcAmt_K, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(kBizMsg.funcPrcListPrcAmt_K, (BigDecimal) rsltMap.get("FUNC_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.addlChrgPrcDealAmt_K, round((BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(kBizMsg.addlChrgPrcFuncAmt_K, round((BigDecimal) rsltMap.get("ADDL_CHRG_PRC_FUNC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(kBizMsg.dealPrcListPrcAmt_K, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(kBizMsg.funcPrcListPrcAmt_K, round((BigDecimal) rsltMap.get("FUNC_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21862
            ZYPEZDItemValueSetter.setValue(kBizMsg.cpoSvcAddlChrgPrcPk_K, (BigDecimal) rsltMap.get("CPO_SVC_ADDL_CHRG_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.cpoSvcDtlPk_K, (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.cpoOrdNum_K, bizMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(kBizMsg.cpoDtlLineNum_K, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.cpoDtlLineSubNum_K, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.addlChrgPrcCatgCd_K, (String) rsltMap.get("ADDL_CHRG_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.svcPrcCatgCd_K, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.billWithEquipInvdFlg_K, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(kBizMsg.crRebilCd_K, (String) rsltMap.get("CR_REBIL_CD"));
            // 2018/04/24 QC#10374 Add Start
            ZYPEZDItemValueSetter.setValue(kBizMsg.dsOrdPosnNum_K, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            // 2018/04/24 QC#10374 Add End

            i++;
        }
        bizMsg.K.setValidCount(i);
    }

    private static void setSvcPrcAbpRsltToBizMsg(NWAL2170CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int i = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2170_HCMsg hBizMsg = bizMsg.H.no(i);

            ZYPEZDItemValueSetter.setValue(hBizMsg.dsImptSvcLineNum_H, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.cpoSvcAddlBasePrcPk_H, (BigDecimal) rsltMap.get("CPO_SVC_ADDL_BASE_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.cpoSvcDtlPk_H, (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.cpoOrdNum_H, bizMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(hBizMsg.cpoDtlLineNum_H, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.cpoDtlLineSubNum_H, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.addlBasePrcCatgCd_H, (String) rsltMap.get("ADDL_BASE_PRC_CATG_CD"));
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(hBizMsg.addlBasePrcDealAmt_H, (BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(hBizMsg.addlBasePrcFuncAmt_H, (BigDecimal) rsltMap.get("ADDL_BASE_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(hBizMsg.dealPrcListPrcAmt_H, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(hBizMsg.funcPrcListPrcAmt_H, (BigDecimal) rsltMap.get("FUNC_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.addlBasePrcDealAmt_H, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(hBizMsg.addlBasePrcFuncAmt_H, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_FUNC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(hBizMsg.dealPrcListPrcAmt_H, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(hBizMsg.funcPrcListPrcAmt_H, round((BigDecimal) rsltMap.get("FUNC_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21862
            ZYPEZDItemValueSetter.setValue(hBizMsg.prcListEquipConfigNum_H, (BigDecimal) rsltMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.svcPrcCatgCd_H, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.billWithEquipInvdFlg_H, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(hBizMsg.crRebilCd_H, (String) rsltMap.get("CR_REBIL_CD"));
            // 2018/04/24 QC#10374 Add Start
            ZYPEZDItemValueSetter.setValue(hBizMsg.dsOrdPosnNum_H, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            // 2018/04/24 QC#10374 Add End

            i++;
        }
        bizMsg.H.setValidCount(i);
    }

    private static void setSvcPrcHdrRsltToBizMsg(NWAL2170CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int i = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2170_FCMsg fBizMsg = bizMsg.F.no(i);

            ZYPEZDItemValueSetter.setValue(fBizMsg.dsImptSvcLineNum_F, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.dsOrdPosnNum_F, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.cpoSvcPrcPk_F, (BigDecimal) rsltMap.get("CPO_SVC_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.cpoSvcDtlPk_F, (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.mdlId_F, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.maintPrcCatgCd_F, (String) rsltMap.get("MAINT_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.maintFlPrcCatgCd_F, (String) rsltMap.get("MAINT_FL_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.prcMtrPkgPk_F, (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK"));
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(fBizMsg.basePrcDealAmt_F, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(fBizMsg.basePrcFuncAmt_F, (BigDecimal) rsltMap.get("BASE_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(fBizMsg.dealPrcListPrcAmt_F, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(fBizMsg.funcPrcListPrcAmt_F, (BigDecimal) rsltMap.get("FUNC_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fBizMsg.basePrcDealAmt_F, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(fBizMsg.basePrcFuncAmt_F, round((BigDecimal) rsltMap.get("BASE_PRC_FUNC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(fBizMsg.dealPrcListPrcAmt_F, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(fBizMsg.funcPrcListPrcAmt_F, round((BigDecimal) rsltMap.get("FUNC_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21862
            ZYPEZDItemValueSetter.setValue(fBizMsg.prcTierSvcOfferCd_F, (String) rsltMap.get("PRC_TIER_SVC_OFFER_CD"));

            i++;
        }
        bizMsg.F.setValidCount(i);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param bizMsg NWAL2170CMsg
     * @param rsltMap rsltMap
     * @param aBizMsg NWAL2170_ACMsg
     */
    public static void setCpoRsltToABizMsg(//
            String glblCmpyCd, NWAL2170CMsg bizMsg, Map<String, Object> rsltMap, NWAL2170_ACMsg aBizMsg) {
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsImptSvcLineNum, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctCd, (String) rsltMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctNm, (String) rsltMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocCd, (String) rsltMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocAddr, (String) rsltMap.get("BILL_TO_CUST_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.shpgIntvlMthNum, (BigDecimal) rsltMap.get("TERM_MTH_NUM"));

        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, (String) rsltMap.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));

        ZYPEZDItemValueSetter.setValue(bizMsg.billByTpCd_P, aBizMsg.billByTpCd);

    }

    private static void setSvcRsltToBizMsg(NWAL2170CMsg bizMsg, List<Map<String, Object>> rsltList, String glblCmpyCd) {
        int ixA = 0;
        int ixB = 0;
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcPk, (BigDecimal) rsltList.get(0).get("CPO_SVC_PK"));
        if (!ZYPCommonFunc.hasValue(bizMsg.dsImptOrdPk_P)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdPk_P, (BigDecimal) rsltList.get(0).get("DS_IMPT_ORD_PK"));
        }
        // 2018/04/06 QC#10374 Add Start
        if (isRentalOrder(bizMsg.glblCmpyCd.getValue(), bizMsg.dsImptOrdPk_P.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2018/04/06 QC#10374 Add End
        // 2019/02/06 QC#30237 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltList.get(0).get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltList.get(0).get("DS_ORD_TP_CD"));
        // 2019/02/06 QC#30237 Add End
        BigDecimal cpoSvcLineNumPre = BigDecimal.ZERO;
        List<Integer> ixList = new ArrayList<Integer>(bizMsg.A.length());
        String dsPoReqFlg = ZYPConstant.FLG_OFF_N;

        NWAL2170_ACMsg aBizMsg = bizMsg.A.no(ixA);
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2170_BCMsg bBizMsg = bizMsg.B.no(ixB);

            if (cpoSvcLineNumPre.compareTo((BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM")) != 0) {
                cpoSvcLineNumPre = (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM");
                ixList.clear();
                aBizMsg = bizMsg.A.no(ixA);

                // Mod Start 2017/10/19 QC#21862
//                setSvcRsltToABizMsg(rsltMap, aBizMsg);
                setSvcRsltToABizMsg(rsltMap, aBizMsg, scale);
                // Mod End 2017/10/19 QC#21862
                dsPoReqFlg = getDefaultDsPoReqFlg(glblCmpyCd, bizMsg, aBizMsg);

                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(aBizMsg.ezUpTime_A, (String) rsltMap.get("EZUPTIME_DTL"));
                ZYPEZDItemValueSetter.setValue(aBizMsg.ezUpTimeZone_A, (String) rsltMap.get("EZUPTIMEZONE_DTL"));

                ixA++;
            }

            ixList.add(ixB);
            if (ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("CPO_SVC_CONFIG_REF_PK"))) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.dsImptSvcLineNum_B, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
            } else {
                continue;
            }

            // Mod Start 2017/10/19 QC#21862
//            setSvcRsltToBBizMsg(rsltMap, bBizMsg, dsPoReqFlg);
            setSvcRsltToBBizMsg(rsltMap, bBizMsg, dsPoReqFlg, scale);
            // Mod End 2017/10/19 QC#21862

            if (ZYPCommonFunc.hasValue((String) rsltMap.get("PRC_BASE_DT"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, (String) rsltMap.get("PRC_BASE_DT"));
            }
            if (ZYPCommonFunc.hasValue((String) rsltMap.get("DS_ORD_CATG_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));
            }
            if (ZYPCommonFunc.hasValue((String) rsltMap.get("DS_ORD_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.ordHdrStsCd, (String) rsltMap.get("ORD_HDR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.imptStsCd, (String) rsltMap.get("IMPT_STS_CD"));

            BigDecimal cycle = getBllgCycle(bizMsg, aBizMsg.baseBllgCycleCd.getValue());
            // Mod Start 2017/10/19 QC#21862
            // 2018/04/04 QC#10374 Mod Start
//            if (!ZYPCommonFunc.hasValue(bBizMsg.xxTotAmt_SV)) {
////                bBizMsg.xxTotAmt_SV.setValue(BigDecimal.ZERO);
//                bBizMsg.xxTotAmt_SV.setValue(round(BigDecimal.ZERO, scale));
//            }
//            if (!ZYPCommonFunc.hasValue(bBizMsg.xxTotAmt_EQ)) {
////                bBizMsg.xxTotAmt_EQ.setValue(BigDecimal.ZERO);
//                bBizMsg.xxTotAmt_EQ.setValue(round(BigDecimal.ZERO, scale));
//            }
//            if (!ZYPCommonFunc.hasValue(bBizMsg.xxTotAmt_AD)) {
////                bBizMsg.xxTotAmt_AD.setValue(BigDecimal.ZERO);
//                bBizMsg.xxTotAmt_AD.setValue(round(BigDecimal.ZERO, scale));
//            }
            if (!ZYPCommonFunc.hasValue(bBizMsg.xxTermAmt_EQ)) {
                bBizMsg.xxTermAmt_EQ.setValue(round(BigDecimal.ZERO, scale));
            }
            if (!ZYPCommonFunc.hasValue(bBizMsg.xxTermAmt_AC)) {
                bBizMsg.xxTermAmt_AC.setValue(round(BigDecimal.ZERO, scale));
            }
            if (!ZYPCommonFunc.hasValue(bBizMsg.xxTermAmt_AD)) {
                bBizMsg.xxTermAmt_AD.setValue(round(BigDecimal.ZERO, scale));
            }
            // 2018/04/04 QC#10374 Mod End
            BigDecimal multiplyValue = getMultiplyValue(aBizMsg, cycle);
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV, bBizMsg.xxTotAmt_SV.getValue().multiply(multiplyValue));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ, bBizMsg.xxTotAmt_EQ.getValue().multiply(multiplyValue));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD, bBizMsg.xxTotAmt_AD.getValue().multiply(multiplyValue));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN //
//                    , bBizMsg.xxTotAmt_SV.getValue() //
//                            .add(bBizMsg.xxTotAmt_EQ.getValue()) //
//                            .add(bBizMsg.xxTotAmt_AD.getValue()));
            // 2018/04/04 QC#10374 Mod Start
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV, multiply(bBizMsg.xxTotAmt_SV.getValue(), multiplyValue, scale));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ, multiply(bBizMsg.xxTotAmt_EQ.getValue(), multiplyValue, scale));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD, multiply(bBizMsg.xxTotAmt_AD.getValue(), multiplyValue, scale));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN //
//                    , round(bBizMsg.xxTotAmt_SV.getValue() //
//                            .add(bBizMsg.xxTotAmt_EQ.getValue()) //
//                            .add(bBizMsg.xxTotAmt_AD.getValue()), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ, multiply(bBizMsg.xxTermAmt_EQ.getValue(), multiplyValue, scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC, multiply(bBizMsg.xxTermAmt_AC.getValue(), multiplyValue, scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AD, multiply(bBizMsg.xxTermAmt_AD.getValue(), multiplyValue, scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_TT //
                    , round(bBizMsg.xxTermAmt_EQ.getValue() //
                            .add(bBizMsg.xxTermAmt_AC.getValue()) //
                            .add(bBizMsg.xxTermAmt_AD.getValue()), scale));
            // Mod End 2017/10/19 QC#21862
            // 2018/04/04 QC#10374 Mod End
            // 2018/04/04 QC#10374 Add Start
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxFreqCycleCnt, multiplyValue.setScale(0)); // 2018/08/28 S21_NA#27968
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxFreqCycleCnt, multiplyValue.setScale(0, BigDecimal.ROUND_UP));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxModeCd_B, NWAL2370_IMPT_MODE_CD);
            // 2018/04/04 QC#10374 Add End

            ixB++;
        }
        bizMsg.A.setValidCount(ixA);
        bizMsg.B.setValidCount(ixB);
    }

    private static String getDefaultDsPoReqFlg(String glblCmpyCd, NWAL2170CMsg bizMsg, NWAL2170_ACMsg aBizMsg) {
        String dsPoReqFlg = ZYPConstant.FLG_OFF_N;
        NMZC610001PMsg pMsg = new NMZC610001PMsg();

        if (callCustInfoGetApiForTransactionMode(glblCmpyCd, bizMsg, aBizMsg, pMsg)) {
            return dsPoReqFlg;
        }
        for (int i = 0; i < pMsg.TransactionRuleList.getValidCount(); i++) {
            NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(i);
            if (ZYPCommonFunc.hasValue(trPMsg.dsPoReqFlg)) {
                return trPMsg.dsPoReqFlg.getValue();
            }
        }
        return dsPoReqFlg;
    }

    /**
     * @param rsltMap Map<String, Object>
     * @param bBizMsg NWAL2170_BCMsg
     * @param dsPoReqFlg dsPoReqFlg
     * @param scale int
     */
    private static void setSvcRsltToBBizMsg(Map<String, Object> rsltMap, NWAL2170_BCMsg bBizMsg, String dsPoReqFlg, int scale) {
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsOrdPosnNum, (String) rsltMap.get("DS_ORD_POSN_NUM"));
        // 2018/04/11 QC#10374 Add Start
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsOrdPosnNum_BX, (String) rsltMap.get("DS_ORD_POSN_NUM"));
        // 2018/04/11 QC#10374 Add End
        ZYPEZDItemValueSetter.setValue(bBizMsg.mdlId, (BigDecimal) rsltMap.get("MDL_ID"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.t_MdlNm, (String) rsltMap.get("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.dplyLineNum, (String) rsltMap.get("DPLY_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineNum, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineSubNum, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxShipToAcctNmSrchTxt, (String) rsltMap.get("SHIP_TO_SRCH_TXT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.mtrReadMethCd, (String) rsltMap.get("MTR_READ_METH_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.cpoSvcConfigRefPk, (BigDecimal) rsltMap.get("CPO_SVC_CONFIG_REF_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoNum, (String) rsltMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoDt, (String) rsltMap.get("CUST_ISS_PO_DT"));
        // 2018/04/04 QC#20162 Add Start
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoExprDt, (String) rsltMap.get("DS_PO_EXPR_DT"));
        // 2018/04/04 QC#20162 Add End

        // Mod Start 2017/10/19 QC#21862
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN, (BigDecimal) rsltMap.get("LINE_TOT_AMT"));
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ, (BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"));
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD, (BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"));
        // 2018/04/04 QC#10374 Mod Start
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN, round((BigDecimal) rsltMap.get("LINE_TOT_AMT"), scale));
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"), scale));
//        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD, round((BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_TT, round((BigDecimal) rsltMap.get("LINE_TOT_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AD, round((BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"), scale));
        // 2018/04/04 QC#10374 Mod End
        // Mod End 2017/10/19 QC#21862

        ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoReqFlg, dsPoReqFlg);

        ZYPEZDItemValueSetter.setValue(bBizMsg.cpoSvcPrcPk, (BigDecimal) rsltMap.get("CPO_SVC_PRC_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.crRebilCd, (String) rsltMap.get("CR_REBIL_CD"));

        ZYPEZDItemValueSetter.setValue(bBizMsg.shpgStsCd, (String) rsltMap.get("SHPG_STS_CD"));

        // 2018/04/04 QC#10374 Add Start
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_TT, round((BigDecimal) rsltMap.get("LINE_TOT_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_EQ, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AC, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AD, round((BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(bBizMsg.baseBllgCycleDescTxt, (String) rsltMap.get("BASE_BLLG_CYCLE_DESC_TXT"));
        // 2018/04/04 QC#10374 Add End
    }

    /**
     * @param rsltMap Map<String, Object>
     * @param aBizMsg NWAL2170_ACMsg
     * @param scale int
     */
    private static void setSvcRsltToABizMsg(Map<String, Object> rsltMap, NWAL2170_ACMsg aBizMsg, int scale) {
        ZYPEZDItemValueSetter.setValue(aBizMsg.cpoSvcDtlPk, (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsImptSvcLineNum, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsImptSvcMdseCd, (String) rsltMap.get("CPO_SVC_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcContrTpCd, (String) rsltMap.get("PRC_SVC_CONTR_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcPlnTpCd, (String) rsltMap.get("PRC_SVC_PLN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billWithEquipFlg, (String) rsltMap.get("BILL_WITH_EQUIP_FLG"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.fromPerMthNum, (BigDecimal) rsltMap.get("FROM_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.toPerMthNum, (BigDecimal) rsltMap.get("TO_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.shpgIntvlMthNum, (BigDecimal) rsltMap.get("TERM_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.fixTermInMthAot, (BigDecimal) rsltMap.get("FIX_TERM_IN_MTH_AOT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.maxUplftPct, (BigDecimal) rsltMap.get("MAX_UPLFT_PCT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.cpoSvcLineActCd, (String) rsltMap.get("CPO_SVC_LINE_ACT_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.baseBllgCycleCd, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.usgBllgCycleCd, (String) rsltMap.get("USG_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billByTpCd, (String) rsltMap.get("BILL_BY_TP_CD"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, (String) rsltMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, (String) rsltMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxBillToAcctNmSrchTxt, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, (String) rsltMap.get("SOLD_TO_CUST_LOC_CD"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctCd, (String) rsltMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctNm, (String) rsltMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocAddr, (String) rsltMap.get("BILL_TO_CUST_LOC_ADDR"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdFlg, (String) rsltMap.get("MAN_CONTR_OVRD_FLG"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdRsnCd, (String) rsltMap.get("MAN_CONTR_OVRD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdCmntTxt, (String) rsltMap.get("MAN_CONTR_OVRD_CMNT_TXT"));
        // 2018/05/07 QC#22482 Add Start
        ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdRsnNm, (String) rsltMap.get("MAN_CONTR_OVRD_RSN_DESC_TXT"));
        // 2018/05/07 QC#22482 Add End

        ZYPEZDItemValueSetter.setValue(aBizMsg.applyEquipBillToFlg, (String) rsltMap.get("APPLY_EQUIP_BILL_TO_FLG"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrNum, (String) rsltMap.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.cpoSvcAgmtVerNum, (String) rsltMap.get("CPO_SVC_AGMT_VER_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.addAsryFlg, (String) rsltMap.get("ADD_ASRY_FLG"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.xxExstFlg_PR, (String) rsltMap.get("PRICE_XX_EXST_FLG"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_CI, ZYPConstant.FLG_OFF_N);

        // Mod Start 2017/10/19 QC#21862
//        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotAmt_S, (BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotAmt_S, round((BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"), scale));
        // Mod End 2017/10/19 QC#21862
    }

    /**
     * @param aBizMsg
     * @param cycle
     * @return BigDecimal multiplyValue
     */
    private static BigDecimal getMultiplyValue(NWAL2170_ACMsg aBizMsg, BigDecimal cycle) {
        BigDecimal multiplyValue = BigDecimal.ZERO;
        if (!ZYPCommonFunc.hasValue(aBizMsg.shpgIntvlMthNum)) {
            return multiplyValue;
        }
        if (!BLLG_CYCLE.CONTRACT_PERIOD.equals(aBizMsg.baseBllgCycleCd.getValue())) {
            // Mod Start 2017/09/27 QC#21341
            //multiplyValue = aBizMsg.shpgIntvlMthNum.getValue().divide(cycle);
            multiplyValue = aBizMsg.shpgIntvlMthNum.getValue().divide(cycle, 4, BigDecimal.ROUND_HALF_UP);
            // Mod End 2017/09/27 QC#21341
        }
        return multiplyValue;
    }

    private static BigDecimal getBllgCycle(NWAL2170CMsg bizMsg, String bllgCycleCd) {
        for (int i = 0; i < bizMsg.bllgCycleCd_L.length(); i++) {
            if (bllgCycleCd.equals(bizMsg.bllgCycleCd_L.no(i).getValue())) {
                return bizMsg.L.no(i).bllgCycleAot_L.getValue();
            }
        }
        return BigDecimal.ONE;
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param bizMsg NWAL2170CMsg
     * @param aBizMsg NWAL2170_ACMsg
     * @param pMsg NMZC610001PMsg
     */
    private static boolean callCustInfoGetApiForTransactionMode(//
            String glblCmpyCd, NWAL2170CMsg bizMsg, NWAL2170_ACMsg aBizMsg, NMZC610001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.CONTRACT);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, aBizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, aBizMsg.dsAcctNum);

        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg) && !isImport(bizMsg)) {
            NMZC610001_xxMsgIdListPMsg pErrMsg = pMsg.xxMsgIdList.no(0);
            bizMsg.setMessageInfo(//
                    pErrMsg.xxMsgId.getValue() //
                    , new String[] {//
                    pErrMsg.xxMsgPrmTxt_0.getValue() //
                            , pErrMsg.xxMsgPrmTxt_1.getValue() //
                            , pErrMsg.xxMsgPrmTxt_2.getValue() //
                            , pErrMsg.xxMsgPrmTxt_3.getValue() //
                            , pErrMsg.xxMsgPrmTxt_4.getValue() //
                    });
            return true;
        }
        return false;
    }

    private static NWAL2170_ACMsg getABizMsg(NWAL2170CMsg bizMsg, BigDecimal dsImptSvcLineNum) {
        if (!ZYPCommonFunc.hasValue(dsImptSvcLineNum)) {
            return new NWAL2170_ACMsg();
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2170_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsImptSvcLineNum)) {
                continue;
            }
            if (dsImptSvcLineNum.compareTo(aBizMsg.dsImptSvcLineNum.getValue()) == 0) {
                return aBizMsg;
            }
        }
        return new NWAL2170_ACMsg();
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2170CMsg
     * @param dsImptSvcLineNum BigDecimal
     * @param ixL           List<Integer>
     * @param glblCmpyCd    glblCmpyCd
     * </pre>
     */
    public static void calcAmt(NWAL2170CMsg bizMsg, BigDecimal dsImptSvcLineNum, List<Integer> ixL, String glblCmpyCd) {
        NWAL2170_ACMsg aBizMsg = getABizMsg(bizMsg, dsImptSvcLineNum);
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2170_BCMsg bBizMsg = bizMsg.B.no(i);
            if (dsImptSvcLineNum.compareTo(bBizMsg.dsImptSvcLineNum_B.getValue()) == 0) {
                // Mod Start 2017/10/19 QC#21862
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV, BigDecimal.ZERO);
                // 2018/04/04 QC#10374 Mod Start
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD, round(BigDecimal.ZERO, scale));
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ, round(BigDecimal.ZERO, scale));
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN, round(BigDecimal.ZERO, scale));
//                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV, round(BigDecimal.ZERO, scale));
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_TT, round(BigDecimal.ZERO, scale));
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ, round(BigDecimal.ZERO, scale));
                // 2018/08/03 QC#26325 Add Start
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_RT, round(BigDecimal.ZERO, scale));
                // 2018/08/03 QC#26325 Add End
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC, round(BigDecimal.ZERO, scale));
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AD, round(BigDecimal.ZERO, scale));
                // 2018/04/04 QC#10374 Mod End
                // 2018/04/04 QC#10374 Add Start
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_TT, round(BigDecimal.ZERO, scale));
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_EQ, round(BigDecimal.ZERO, scale));
                // 2018/08/03 QC#26325 Add Start
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_RT, round(BigDecimal.ZERO, scale));
                // 2018/08/03 QC#26325 Add End
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AC, round(BigDecimal.ZERO, scale));
                ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AD, round(BigDecimal.ZERO, scale));
                // 2018/04/04 QC#10374 Add End
                // Mod End 2017/10/19 QC#21862
                ixL.add(i);
            }
        }
        BigDecimal cycle = getBllgCycle(bizMsg, aBizMsg.baseBllgCycleCd.getValue());
        BigDecimal multiplyValue = getMultiplyValue(aBizMsg, cycle);
        // Service Pricing Header(Service)
        for (int r = 0; r < bizMsg.R.getValidCount(); r++) {
            NWAL2170_RCMsg rBizMsg = bizMsg.R.no(r);
            BigDecimal mdlId = rBizMsg.mdlId_R.getValue();
            BigDecimal cpoSvcPrcPk = rBizMsg.cpoSvcPrcPk_R.getValue();
            for (int a : ixL) {
                NWAL2170_BCMsg bBizMsg = bizMsg.B.no(a);

                // Mod Start 2017/10/19 QC#21862
                if (DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV //
//                            , bBizMsg.xxTotAmt_SV.getValue().add(rBizMsg.basePrcDealAmt_R.getValue().multiply(multiplyValue)));
                    // 2018/04/04 QC#10374 Mod Start
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV //
//                            , round(bBizMsg.xxTotAmt_SV.getValue().add(multiply(rBizMsg.basePrcDealAmt_R.getValue(), multiplyValue, scale)), scale));
                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ
                            , round(bBizMsg.xxTermAmt_EQ.getValue().add(multiply(rBizMsg.basePrcDealAmt_R.getValue(), multiplyValue, scale)), scale));
                    // 2018/04/04 QC#10374 Mod End
                    // 2018/04/04 QC#10374 Add Start
                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_EQ
                            , round(bBizMsg.xxDealAmt_EQ.getValue().add(rBizMsg.basePrcDealAmt_R.getValue()), scale));
                    // 2018/04/04 QC#10374 Add End
                    break;

                } else if (ZYPCommonFunc.hasValue(bBizMsg.mdlId) && ZYPCommonFunc.hasValue(bBizMsg.cpoSvcPrcPk) //
                        && mdlId.compareTo(bBizMsg.mdlId.getValue()) == 0 //
                        && cpoSvcPrcPk.compareTo(bBizMsg.cpoSvcPrcPk.getValue()) == 0 //
                        && ZYPCommonFunc.hasValue(rBizMsg.basePrcDealAmt_R)) {
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV //
//                            , bBizMsg.xxTotAmt_SV.getValue().add(rBizMsg.basePrcDealAmt_R.getValue().multiply(multiplyValue)));
                    // 2018/04/04 QC#10374 Mod Start
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV //
//                            , round(bBizMsg.xxTotAmt_SV.getValue().add(multiply(rBizMsg.basePrcDealAmt_R.getValue(), multiplyValue, scale)), scale));
                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ
                            , round(bBizMsg.xxTermAmt_EQ.getValue().add(multiply(rBizMsg.basePrcDealAmt_R.getValue(), multiplyValue, scale)), scale));
                    // 2018/04/04 QC#10374 Mod End
                    // 2018/04/04 QC#10374 Add Start
                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_EQ
                            , round(bBizMsg.xxDealAmt_EQ.getValue().add(rBizMsg.basePrcDealAmt_R.getValue()), scale));
                    // 2018/04/04 QC#10374 Add End
                }
                // Mod End 2017/10/19 QC#21862
            }
        }
        // Accessory Charge(Equip)
        for (int t = 0; t < bizMsg.T.getValidCount(); t++) {
            NWAL2170_TCMsg tBizMsg = bizMsg.T.no(t);
            String lineNumT //
            // 2018/04/24 QC#10374 Mod Start
//            = S21StringUtil.concatStrings(tBizMsg.cpoDtlLineNum_T.getValue(), ".", tBizMsg.cpoDtlLineSubNum_T.getValue());
            = S21StringUtil.concatStrings(tBizMsg.dsOrdPosnNum_T.getValue(), ".", tBizMsg.cpoDtlLineNum_T.getValue(), ".", tBizMsg.cpoDtlLineSubNum_T.getValue());
            // 2018/04/24 QC#10374 Mod End
            if (ZYPConstant.FLG_OFF_N.equals(aBizMsg.addAsryFlg.getValue())) {
                lineNumT = getBaseDtlLineNum(tBizMsg, bizMsg, glblCmpyCd);
            }
            for (int a : ixL) {
                NWAL2170_BCMsg bBizMsg = bizMsg.B.no(a);
                String lineNumA = S21StringUtil.concatStrings(//
                        // 2018/04/24 QC#10374 Mod Start
//                        bBizMsg.cpoDtlLineNum.getValue() //
                        bBizMsg.dsOrdPosnNum.getValue()
                        , "."
                        , bBizMsg.cpoDtlLineNum.getValue() //
                        // 2018/04/24 QC#10374 Mod End
                        , "." //
                        , bBizMsg.cpoDtlLineSubNum.getValue());
                if (lineNumT.equals(lineNumA) //
                        && ZYPCommonFunc.hasValue(tBizMsg.addlBasePrcDealAmt_T)) {
                    // Mod Start 2017/10/19 QC#21862
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ //
//                            , bBizMsg.xxTotAmt_EQ.getValue().add(tBizMsg.addlBasePrcDealAmt_T.getValue().multiply(multiplyValue)));
                    // 2018/04/04 QC#10374 Mod Start
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ //
//                            , round(bBizMsg.xxTotAmt_EQ.getValue().add(multiply(tBizMsg.addlBasePrcDealAmt_T.getValue(), multiplyValue, scale)), scale));
                    // Mod Start 2018/08/03 QC#26325
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC
//                            , round(bBizMsg.xxTermAmt_AC.getValue().add(multiply(tBizMsg.addlBasePrcDealAmt_T.getValue(), multiplyValue, scale)), scale));
                    // 2018/04/04 QC#10374 Mod End
                    // 2018/04/04 QC#10374 Add Start
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AC
//                            , round(bBizMsg.xxDealAmt_AC.getValue().add(tBizMsg.addlBasePrcDealAmt_T.getValue()), scale));
                    // 2018/04/04 QC#10374 Add End
                    if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals(tBizMsg.svcPrcCatgCd_T.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_RT
                                , round(bBizMsg.xxTermAmt_RT.getValue().add(multiply(tBizMsg.addlBasePrcDealAmt_T.getValue(), multiplyValue, scale)), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_RT
                                , round(bBizMsg.xxDealAmt_RT.getValue().add(tBizMsg.addlBasePrcDealAmt_T.getValue()), scale));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC
                                , round(bBizMsg.xxTermAmt_AC.getValue().add(multiply(tBizMsg.addlBasePrcDealAmt_T.getValue(), multiplyValue, scale)), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AC
                                , round(bBizMsg.xxDealAmt_AC.getValue().add(tBizMsg.addlBasePrcDealAmt_T.getValue()), scale));
                    }
                    // Mod End 2017/10/19 QC#21862
                    // Mod End 2018/08/03 QC#26325
                }
            }
        }
        // Additional Service Charge(Addl)
        for (int u = 0; u < bizMsg.U.getValidCount(); u++) {
            NWAL2170_UCMsg uBizMsg = bizMsg.U.no(u);
            String lineNumT = S21StringUtil.concatStrings(//
                    // 2018/04/24 QC#10374 Mod Start
//                    uBizMsg.cpoDtlLineNum_U.getValue() //
                    uBizMsg.dsOrdPosnNum_U.getValue()
                    , "."
                    , uBizMsg.cpoDtlLineNum_U.getValue() //
                    // 2018/04/24 QC#10374 Mod End
                    , "." //
                    , uBizMsg.cpoDtlLineSubNum_U.getValue());
            for (int a : ixL) {
                NWAL2170_BCMsg bBizMsg = bizMsg.B.no(a);
                String lineNumA = S21StringUtil.concatStrings(//
                        // 2018/04/24 QC#10374 Mod Start
//                        bBizMsg.cpoDtlLineNum.getValue() //
                        bBizMsg.dsOrdPosnNum.getValue()
                        , "."
                        , bBizMsg.cpoDtlLineNum.getValue() //
                        // 2018/04/24 QC#10374 Mod End
                        , "." //
                        , bBizMsg.cpoDtlLineSubNum.getValue());
                if (lineNumT.equals(lineNumA) //
                        && ZYPCommonFunc.hasValue(uBizMsg.addlChrgPrcDealAmt_U)) {
                    // Mod Start 2017/10/19 QC#21862
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD //
//                            , bBizMsg.xxTotAmt_AD.getValue().add(uBizMsg.addlChrgPrcDealAmt_U.getValue().multiply(multiplyValue)));
                    // 2018/04/04 QC#10374 Mod Start
//                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD //
//                            , round(bBizMsg.xxTotAmt_AD.getValue().add(multiply(uBizMsg.addlChrgPrcDealAmt_U.getValue(), multiplyValue, scale)), scale));
                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AD
                            , round(bBizMsg.xxTermAmt_AD.getValue().add(multiply(uBizMsg.addlChrgPrcDealAmt_U.getValue(), multiplyValue, scale)), scale));
                    // 2018/04/04 QC#10374 Mod End
                    // 2018/04/04 QC#10374 Add Start
                    ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AD
                            , round(bBizMsg.xxDealAmt_AD.getValue().add(uBizMsg.addlChrgPrcDealAmt_U.getValue()), scale));
                    // 2018/04/04 QC#10374 Add End
                    // Mod End 2017/10/19 QC#21862
                }
            }
        }
        BigDecimal totAmt = BigDecimal.ZERO;
        // line total
        for (int a : ixL) {
            BigDecimal lineAmt = BigDecimal.ZERO;
            NWAL2170_BCMsg bBizMsg = bizMsg.B.no(a);
            // 2018/04/04 QC#10374 Mod Start
//            lineAmt = bBizMsg.xxTotAmt_AD.getValue().add(bBizMsg.xxTotAmt_EQ.getValue().add(bBizMsg.xxTotAmt_SV.getValue()));
//            // Mod Start 2017/10/19 QC#21862
////            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN, lineAmt);
//            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN, round(lineAmt, scale));
            // Mod Start 2018/08/03 QC#26325
//            lineAmt = bBizMsg.xxTermAmt_AD.getValue().add(bBizMsg.xxTermAmt_AC.getValue().add(bBizMsg.xxTermAmt_EQ.getValue()));
            lineAmt = bBizMsg.xxTermAmt_AD.getValue().add(bBizMsg.xxTermAmt_AC.getValue()
                                                     .add(bBizMsg.xxTermAmt_EQ.getValue()
                                                     .add(bBizMsg.xxTermAmt_RT.getValue())));
            // Mod End 2018/08/03 QC#26325
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_TT, round(lineAmt, scale));
            // 2018/04/04 QC#10374 Mod End
            // 2018/04/04 QC#10374 Add Start
            // Mod Start 2018/08/03 QC#26325
//            BigDecimal dealAmt = bBizMsg.xxDealAmt_AD.getValue().add(bBizMsg.xxDealAmt_AC.getValue().add(bBizMsg.xxDealAmt_EQ.getValue()));
            BigDecimal dealAmt = bBizMsg.xxDealAmt_AD.getValue().add(bBizMsg.xxDealAmt_AC.getValue().add(bBizMsg.xxDealAmt_EQ.getValue().add(bBizMsg.xxDealAmt_RT.getValue())));
            // Mod End 2018/08/03 QC#26325
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_TT, round(dealAmt, scale));
            // 2018/04/04 QC#10374 Add End
            // Mod End 2017/10/19 QC#21862
            totAmt = totAmt.add(lineAmt);
        }
        // total
        if (isImport(bizMsg)) {
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotAmt_S, totAmt);
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotAmt_S, round(totAmt, scale));
            for (int a : ixL) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(a).xxTotAmt_S, totAmt);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(a).xxTotAmt_S, round(totAmt, scale));
            }
            // Mod End 2017/10/19 QC#21862
        }

    }

    private static String getBaseDtlLineNum(NWAL2170_TCMsg tBizMsg, NWAL2170CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult rslt = NWAL2170Query.getInstance().getBaseDtlLineNum(glblCmpyCd, bizMsg, tBizMsg);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();

        for (Map<String, Object> rsltMap : rsltList) {
            String cpoDtlLineNum = (String) rsltMap.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM");
            // 2018/04/24 QC#10374 Mod Start
//            return S21StringUtil.concatStrings(cpoDtlLineNum, ".", cpoDtlLineSubNum);
            String dsOrdPosnNum = (String) rsltMap.get("DS_ORD_POSN_NUM");
            return S21StringUtil.concatStrings(dsOrdPosnNum, ".", cpoDtlLineNum, ".", cpoDtlLineSubNum);
            // 2018/04/24 QC#10374 Mod End
        }
        return "";
    }

    /**
     * <pre>
     * derive mdseDescLongTxt, termMthNum
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @param glblMsg       NWAL2170SMsg
     * @return if error then return true.
     * </pre>
     */
    public static boolean deriveImptFromInputValue(String glblCmpyCd, NWAL2170CMsg bizMsg, NWAL2170SMsg glblMsg) {
        boolean hasErr = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2170_ACMsg aBizMsg = bizMsg.A.no(i);
            NWAL2170_ASMsg aGlblMsg = glblMsg.A.no(i);
            if (S21StringUtil.isEquals(aBizMsg.dsAcctNum.getValue(), aGlblMsg.dsAcctNum.getValue()) //
                    && S21StringUtil.isEquals(aBizMsg.soldToCustLocCd.getValue(), aGlblMsg.soldToCustLocCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum)) {
                hasErr = NWAL2170CommonLogic.deriveFromDsAcctNum(hasErr, aBizMsg.dsAcctNum, bizMsg);
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {
                hasErr = NWAL2170CommonLogic.deriveFromLocationCd(hasErr, aBizMsg.soldToCustLocCd, bizMsg);
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum) //
                    && ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {

                NMZC610001PMsg pMsg = new NMZC610001PMsg();
                if (callCustInfoGetApiForTransactionMode(glblCmpyCd, bizMsg, aBizMsg, pMsg)) {
                    hasErr = true;
                } else {
                    String dsPoReqFlg = ZYPConstant.FLG_OFF_N;
                    for (int ixP = 0; ixP < pMsg.TransactionRuleList.getValidCount(); ixP++) {
                        NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(ixP);
                        dsPoReqFlg = trPMsg.dsPoReqFlg.getValue();
                        break;
                    }
                    setDsPoReqFlg(bizMsg, aBizMsg, dsPoReqFlg);
                }

            }
        }
        return hasErr;
    }

    private static void setDsPoReqFlg(NWAL2170CMsg bizMsg, NWAL2170_ACMsg aBizMsg, String dsPoReqFlg) {
        BigDecimal dsImptSvcLineNum = aBizMsg.dsImptSvcLineNum.getValue();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(dsImptSvcLineNum)) {
                continue;
            }
            NWAL2170_BCMsg bBizMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bBizMsg.dsImptSvcLineNum_B)) {
                continue;
            }
            if (dsImptSvcLineNum.compareTo(bBizMsg.dsImptSvcLineNum_B.getValue()) != 0) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoReqFlg, dsPoReqFlg);
        }
    }

    private static boolean deriveFromLocationCd(boolean hasErr, EZDCStringItem soldToCustLocCd, NWAL2170CMsg bizMsg) {
        Integer cnt = getBillToCustCount(bizMsg, soldToCustLocCd, "Location Code");
        if (cnt == null || cnt == 0) {
            soldToCustLocCd.setErrorInfo(1, NWAM0181E, new String[] {"Location Code" });
            bizMsg.setMessageInfo(NWAM0181E);
            hasErr = true;
        }

        return hasErr;
    }

    private static boolean deriveFromDsAcctNum(boolean hasErr, EZDCStringItem dsAcctNumItem, NWAL2170CMsg bizMsg) {
        Integer cnt = getAcctCount(bizMsg, dsAcctNumItem, "Customer");
        if (cnt == null || cnt == 0) {
            dsAcctNumItem.setErrorInfo(1, NWAM0181E, new String[] {"Customer" });
            bizMsg.setMessageInfo(NWAM0181E);
            hasErr = true;
        }

        return hasErr;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @return  ExclusionCtrl result : if error then return true.
     * </pre>
     */
    public static boolean checkImptExclusionCtrl(String glblCmpyCd, NWAL2170CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2170_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.cpoSvcDtlPk)) {
                return false;
            }
            DS_IMPT_SVC_DTLTMsg tMsg = new DS_IMPT_SVC_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsImptSvcDtlPk, aBizMsg.cpoSvcDtlPk);

            tMsg = (DS_IMPT_SVC_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);
            if (tMsg == null) {
                bizMsg.setMessageInfo(NWAL2170Constant.NZZM0003E);
                return true;
            }

            if (!ZYPDateUtil.isSameTimeStamp(//
                    aBizMsg.ezUpTime_A.getValue(), aBizMsg.ezUpTimeZone_A.getValue() //
                    , tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NWAL2170Constant.NZZM0003E);
                return true;
            }
        }

        return false;
    }

    /**
     * @param bizMsg NWAL2170CMsg
     */
    public static void resetBizMsgBeforeInit(NWAL2170CMsg bizMsg) {
        List<String> functionIds = new ArrayList<String>();
        int cnt = 0;
        for (int i = 0; i < bizMsg.P.getValidCount(); i++) {
            functionIds.add(bizMsg.P.no(i).xxFuncId_P.getValue());
            cnt++;
        }
        bizMsg.P.setValidCount(cnt);
        String cpoOrdNum = bizMsg.xxScrItem50Txt_P.getValue();
        String xxPageCd = bizMsg.xxPageCd.getValue();

        bizMsg.clear();
        bizMsg.B.setValidCount(0);
        bizMsg.D.setValidCount(0);

        int ix = 0;
        for (String functionId : functionIds) {
            bizMsg.P.no(ix++).xxFuncId_P.setValue(functionId);
        }
        bizMsg.P.setValidCount(ix);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt_P, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageCd, xxPageCd);
    }

    /**
     * Get DS Account Customer Info
     * @param bizMsg NWAL2170CMsg
     * @param isCallNameFld Called Name Field
     * @param targetItem Target Item
     * @param msgParm Message Parameter
     * @param glblCmpyCd glblCmpyCd
     * @return DS Account Customer Info
     */
    public static SELL_TO_CUSTTMsg getSellToCustInfo(NWAL2170CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm, String glblCmpyCd) {

        SELL_TO_CUSTTMsgArray sellToCustTMsgArray = null;
        if (isCallNameFld) {
            sellToCustTMsgArray = getSellToCustForName(bizMsg, targetItem.getValue(), glblCmpyCd);
        } else {
            sellToCustTMsgArray = getSellToCustForCode(bizMsg, targetItem.getValue(), glblCmpyCd);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (sellToCustTMsgArray.getValidCount() == 0) {
            targetItem.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        } else if (sellToCustTMsgArray.getValidCount() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return sellToCustTMsgArray.no(0);
    }

    /**
     * Get Direct Sales Account Customer For Name
     * @param bizMsg NWAL2170CMsg
     * @param custNm Customer Name
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getSellToCustForName(NWAL2170CMsg bizMsg, String custNm, String glblCmpyCd) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        condition.setSQLID(SQLID_GET_SELL_TO_CUST_FOR_NAME);
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("dsAcctNm01", custNm);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Direct Sales Account Customer For Account
     * @param bizMsg NWAL2170CMsg
     * @param acctNum Account Number
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getSellToCustForCode(NWAL2170CMsg bizMsg, String acctNum, String glblCmpyCd) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        condition.setSQLID(SQLID_GET_SELL_TO_CUST_FOR_CODE);
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("sellToCustCd01", acctNum);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Common Process For Derive From Bill To
     * @param bizMsg NWAL2170CMsg
     * @param dsAcctCustTMsg DS_ACCT_CUSTTMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void cmnProcForDeriveFromBillTo(NWAL2170CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, String glblCmpyCd) {

        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), glblCmpyCd);
        if (nMZC610001PMsg == null) {
            return;
        }

        setBillToInfo(bizMsg, dsAcctCustTMsg, nMZC610001PMsg);
    }

    /**
     * Call Customer Information Get API For Default Mode
     * @param bizMsg NWAL2170CMsg
     * @param dsAcctNum Direct Sales Account Number
     * @param glblCmpyCd glblCmpyCd
     * @return NMZC610001PMsg
     */
    public static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL2170CMsg bizMsg, String dsAcctNum, String glblCmpyCd) {

        // QC#8659
        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(glblCmpyCd);
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(bizMsg, glblCmpyCd));
        paramBean.setDsAcctNum(dsAcctNum);
        paramBean.setXxMode(NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        paramBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return pMsg;

    }

    /**
     * Get Direct Sales Transaction Rule Type Code
     * @param bizMsg NWAL2170CMsg
     * @return Direct Sales Transaction Rule Type Code
     */
    private static String getDsTrxRuleTpCd(NWAL2170CMsg bizMsg, String glblCmpyCd) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return "";
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL2170CMsg
     * @param sellToCustTMsg DS_ACCT_CUSTTMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setBillToInfo(NWAL2170CMsg bizMsg, SELL_TO_CUSTTMsg sellToCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNm, sellToCustTMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNum, sellToCustTMsg.sellToCustCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).soldToCustLocCd, nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd);
        Map<String, String> billToInfo = NWAL2170CommonLogic.getBillToInfo(bizMsg, bizMsg.A.no(selectIndex).soldToCustLocCd, "Location Code");
        if (billToInfo == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).xxBillToAcctNmSrchTxt, billToInfo.get("BILL_TO_ADDR"));

    }

    /**
     * Get Bill To Info
     * @param bizMsg NWAL2170CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getBillToInfo(NWAL2170CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL2170Query.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (billToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return billToCustInfoList.get(0);
    }

    /**
     * Get Bill To Count
     * @param bizMsg NWAL2170CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    public static Integer getBillToCustCount(NWAL2170CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL2170Query.getInstance().getBillToCustCount(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        Integer cnt = (Integer) ssmResult.getResultObject();
        if (cnt == null) {
            return 0;
        }

        return cnt;
    }

    private static Integer getAcctCount(NWAL2170CMsg bizMsg, EZDCStringItem dsAcctNumItem, String msgParm) {
        S21SsmEZDResult ssmResult = NWAL2170Query.getInstance().getDsAcctCustCount(bizMsg, dsAcctNumItem.getValue());

        if (ssmResult.isCodeNotFound()) {
            dsAcctNumItem.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        Integer cnt = (Integer) ssmResult.getResultObject();
        if (cnt == null) {
            return 0;
        }

        return cnt;
    }

    /**
     * <pre>
     * @param aBizMsg   NWAL2170_ACMsg
     * @param bizMsg    NWAL2170CMsg
     * </pre>
     */
    public static void setBillToInfoToCustomer(NWAL2170_ACMsg aBizMsg, NWAL2170CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(aBizMsg.billByTpCd)) {
            aBizMsg.dsAcctNum.clear();
            aBizMsg.dsAcctNm.clear();
            aBizMsg.soldToCustLocCd.clear();
            aBizMsg.xxBillToAcctNmSrchTxt.clear();
            return;
        }
        S21SsmEZDResult ssmResult = NWAL2170Query.getInstance().getMntBillAsEquipInfo(aBizMsg, bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> rsList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (rsList == null || rsList.size() == 0) {
            return;
        }

        for (Map<String, String> rsMap : rsList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, rsMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, rsMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, rsMap.get("SOLD_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxBillToAcctNmSrchTxt, rsMap.get("BILL_TO_CUST_LOC_ADDR"));
            break;
        }
    }

    public static void updateImptCustInfo(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2170_ACMsg aBizMsg = bizMsg.A.no(i);
            BigDecimal dsImptSvcDtlPk = aBizMsg.cpoSvcDtlPk.getValue();
            DS_IMPT_SVC_DTLTMsg keyMsg = new DS_IMPT_SVC_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(keyMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(keyMsg.dsImptSvcDtlPk, dsImptSvcDtlPk);
            DS_IMPT_SVC_DTLTMsg tMsg = (DS_IMPT_SVC_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(keyMsg);
            if (tMsg == null) {
                continue;
            }
            if (S21StringUtil.isEquals(tMsg.sellToCustCd.getValue(), aBizMsg.dsAcctNum.getValue())//
                    && S21StringUtil.isEquals(tMsg.soldToCustLocCd.getValue(), aBizMsg.soldToCustLocCd.getValue())) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, aBizMsg.dsAcctNum);
            ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, aBizMsg.soldToCustLocCd);
            S21FastTBLAccessor.update(tMsg);
            if (!S21StringUtil.isEquals(tMsg.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                bizMsg.setMessageInfo(NWAM0729E, new String[] {tMsg.getTableName() });
                return;
            }

            // Add Start 2018/03/29 QC#24303
            // DS_IMPT_SVC_PRC
            DS_IMPT_SVC_PRCTMsg dsImptSvcPrcKeyMsg = new DS_IMPT_SVC_PRCTMsg();
            dsImptSvcPrcKeyMsg.setSQLID("001");
            dsImptSvcPrcKeyMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dsImptSvcPrcKeyMsg.setConditionValue("dsImptSvcDtlPk01", dsImptSvcDtlPk);
            DS_IMPT_SVC_PRCTMsgArray dsImptSvcPrcTMsgArray = (DS_IMPT_SVC_PRCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(dsImptSvcPrcKeyMsg);
            if (dsImptSvcPrcTMsgArray == null) {
                continue;
            }

            for (int j=0; j<dsImptSvcPrcTMsgArray.getValidCount(); j++) {
                DS_IMPT_SVC_PRCTMsg dsImptSvcPrcTMsg = (DS_IMPT_SVC_PRCTMsg) dsImptSvcPrcTMsgArray.get(j);

                if (S21StringUtil.isEquals(dsImptSvcPrcTMsg.billToCustCd.getValue(), aBizMsg.dsAcctNum.getValue())
                        && S21StringUtil.isEquals(dsImptSvcPrcTMsg.billToLocNum.getValue(), aBizMsg.soldToCustLocCd.getValue())) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(dsImptSvcPrcTMsg.billToCustCd, aBizMsg.dsAcctNum);
                ZYPEZDItemValueSetter.setValue(dsImptSvcPrcTMsg.billToLocNum, aBizMsg.soldToCustLocCd);
                S21FastTBLAccessor.update(dsImptSvcPrcTMsg);
                if (!S21StringUtil.isEquals(dsImptSvcPrcTMsg.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                    bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptSvcPrcTMsg.getTableName() });
                    return;
                }

                // DS_IMPT_SVC_USG_PRC
                DS_IMPT_SVC_USG_PRCTMsg dsImptSvcUsgPrcKeyMsg = new DS_IMPT_SVC_USG_PRCTMsg();
                dsImptSvcUsgPrcKeyMsg.setSQLID("001");
                dsImptSvcUsgPrcKeyMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                dsImptSvcUsgPrcKeyMsg.setConditionValue("dsImptSvcDtlPk01", dsImptSvcDtlPk);
                dsImptSvcUsgPrcKeyMsg.setConditionValue("dsImptSvcPrcPk01", dsImptSvcPrcTMsg.dsImptSvcPrcPk.getValue());
                DS_IMPT_SVC_USG_PRCTMsgArray dsImptSvcUsgPrcTMsgArray = (DS_IMPT_SVC_USG_PRCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(dsImptSvcUsgPrcKeyMsg);
                if (dsImptSvcUsgPrcTMsgArray == null) {
                    continue;
                }

                for (int k=0; k<dsImptSvcUsgPrcTMsgArray.getValidCount(); k++) {
                    DS_IMPT_SVC_USG_PRCTMsg dsImptSvcUsgPrcTMsg = (DS_IMPT_SVC_USG_PRCTMsg) dsImptSvcUsgPrcTMsgArray.get(k);

                    if (S21StringUtil.isEquals(dsImptSvcUsgPrcTMsg.billToCustCd.getValue(), aBizMsg.dsAcctNum.getValue())
                            && S21StringUtil.isEquals(dsImptSvcUsgPrcTMsg.billToLocNum.getValue(), aBizMsg.soldToCustLocCd.getValue())) {
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(dsImptSvcUsgPrcTMsg.billToCustCd, aBizMsg.dsAcctNum);
                    ZYPEZDItemValueSetter.setValue(dsImptSvcUsgPrcTMsg.billToLocNum, aBizMsg.soldToCustLocCd);
                    S21FastTBLAccessor.update(dsImptSvcUsgPrcTMsg);
                    if (!S21StringUtil.isEquals(dsImptSvcUsgPrcTMsg.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
                        bizMsg.setMessageInfo(NWAM0729E, new String[] {dsImptSvcUsgPrcTMsg.getTableName() });
                        return;
                    }
                }
            }
            // Add End 2018/03/29 QC#24303
        }
    }

    // Add Start 2017/10/19 QC#21862
    private static GLBL_CMPYTMsg getGlblCmpy(String glblCmpyCd) {
        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private static int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

    private static BigDecimal round(BigDecimal num, int digit) {
        if (num == null) {
            num = BigDecimal.ZERO;
        }
        return num.setScale(digit, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal multiply(BigDecimal num1, BigDecimal num2, int scale) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        return round(num1.multiply(num2), scale);
    }
    // Add End 2017/10/19 QC#21862
    // 2018/04/06 QC#10374 Add Start
    private static boolean isRentalOrder(String glblCmpyCd, BigDecimal dsImptOrdPk) {
        return NWAL2170Query.getInstance().getRentalOrderCnt(glblCmpyCd, dsImptOrdPk) > 0;
    }
    // 2018/04/06 QC#10374 Add End

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountOrLocation
     * @param bizMsg NWAL2170CMsg
     * @param glblMsg NWAL2170SMsg
     * @return boolean
     */
    public static boolean hasDeactivateAccountOrLocation(NWAL2170CMsg bizMsg, NWAL2170SMsg glblMsg) {

        boolean errorFlg = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            SELL_TO_CUSTTMsg lcSellToCustTMsg = new SELL_TO_CUSTTMsg();
            lcSellToCustTMsg = getSellToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).dsAcctNum.getValue());
            if (null != lcSellToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcSellToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).dsAcctNum.setErrorInfo(1, NWAM0990E, new String[] {bizMsg.A.no(i).dsAcctNum.getValue() });
                    errorFlg = true;
                }
            }

            BILL_TO_CUSTTMsg lcBillToCustTMsg = new BILL_TO_CUSTTMsg();
            lcBillToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).soldToCustLocCd.getValue());
            if (null != lcBillToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcBillToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).soldToCustLocCd.setErrorInfo(1, NWAM0989E, new String[] {bizMsg.A.no(i).soldToCustLocCd.getValue() });
                    errorFlg = true;
                }
            }
        }

        if (errorFlg) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * get Sell To Customer
     * @param glblCmpyCd Global Company Code
     * @param sellToCustCd Sell To Customer Code
     * @return SELL_TO_CUSTTMsg, if system can't find SELL_TO_CUST record, return null
     * </pre>
     */
    private static SELL_TO_CUSTTMsg getSellToCustByCondition(String glblCmpyCd, String sellToCustCd) {

        SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("sellToCustCd01", sellToCustCd);

        SELL_TO_CUSTTMsgArray tmsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * <pre>
     * get Bill To Customer
     * @param glblCmpyCd Global Company Code
     * @param billToCustCd Bill To Customer Code
     * @return BILL_TO_CUSTTMsg, if system can't find BILL_TO_CUST record, return null
     * </pre>
     */
    private static BILL_TO_CUSTTMsg getBillToCustByCondition(String glblCmpyCd, String billToCustCd) {

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("billToCustCd01", billToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }
    // 2023/11/06 QC#61924 Add End
}
