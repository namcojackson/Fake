/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6730;

import static business.blap.NMAL6730.constant.NMAL6730Constant.DEF_DPLY_COA_INFO_APP_FUNC_ID;
import static business.blap.NMAL6730.constant.NMAL6730Constant.MESSAGE_KIND_ERR;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0072E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0176E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0177E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0208E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0291E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0306E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0836E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8111E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8121E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8177E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8178E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8179E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8180E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8333I;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8409E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8638E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8682E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8093E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NZZM0003E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.TAB_FINANCIAL;
import static business.blap.NMAL6730.constant.NMAL6730Constant.TAB_INV_BLLG;
import static business.blap.NMAL6730.constant.NMAL6730Constant.MLY_MAX_LATE_FEE_RATE;
import static business.blap.NMAL6730.constant.NMAL6730Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6730.common.NMAL6730CommonLogic;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CUST_BLLG_VCLETMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DS_ACCT_REF_ATTRBTMsg;
import business.db.DS_CUST_INV_RULETMsg;
import business.db.DS_CUST_INV_RULE_RCPNTTMsg;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_RULE_RCPNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/02/18   Fujitsu         C.Tanaka        Update          QC#2440
 * 2016/02/23   Fujitsu         T.Murai         Update          QC#4332
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#4337
 * 2016/05/02   SRAA            Y.Chen          Update          QC#4324
 * 2016/05/06   SRAA            Y.Chen          Update          QC#6184, QC#6659
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/08/12   SRAA            Y.Chen          Update          QC#12469
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2017/02/15   Fujitsu         K.Ishizuka      Update          QC#17533
 * 2017/02/24   Fujitsu         H.Nagashima     Update          QC#17252
 * 2017/08/25   CITS            T.Tokutomi      Update          QC#19869
 * 2017/09/07   Hitachi         J.Kim           Update          QC#20495
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/04/19   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/05/08   Fujitsu         H.Nagashima     Update          QC#23604
 * 2018/06/25   Hitachi         U.Kim           Update          QC#26703
 * 2018/08/01   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2019/05/13   Fujitsu         C.Hara          Update          QC#50113
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 *</pre>
 */
public class NMAL6730BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;
        NMAL6730SMsg sharedMsg = (NMAL6730SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6730Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * false err
     * @param cMsg
     * @param workFlg
     * @return
     */
    private boolean updateFinancialTabCheck(NMAL6730CMsg cMsg) {

        if (updateHeaderCheck(cMsg)) {
            return true;
        } else {
            if (updateFinancialInfoCheck(cMsg)) {
                return true;
            }
            return false;
        }
    }

    /**
     * false err
     * @param cMsg
     * @param workFlg
     * @return
     */
    private boolean updateInvBllgTabCheck(NMAL6730CMsg cMsg) {

        if (updateHeaderCheck(cMsg)) {
            return true;
        } else {
            if (updateBillingCycleInfoCheck(cMsg)) {
                return true;
            }
            return false;
        }
    }

    // Mod End #4332 2016/02/23

    private boolean updateInvoiceSourceListCheck(NMAL6730CMsg cMsg, int cnt, NMAL6730SMsg sMsg) {
        for (int j = 0; j < sMsg.A.getValidCount(); j++) {
            if (cMsg.A.no(cnt).dsCustInvRulePk_A1.getValue().compareTo(sMsg.A.no(j).dsCustInvRulePk_A1.getValue()) == 0) {
                if (!cMsg.A.no(cnt).custInvSrcCd_P1.getValue().equals(sMsg.A.no(j).custInvSrcCd_P1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).custBllgTpCd_P1.getValue().equals(sMsg.A.no(j).custBllgTpCd_P1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).custConslTermCd_P1.getValue().equals(sMsg.A.no(j).custConslTermCd_P1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).custBllgVcleCd_P1.getValue().equals(sMsg.A.no(j).custBllgVcleCd_P1.getValue())) {
                    return true;
                }
                // QC#7781
                if (!cMsg.A.no(cnt).xxGenlFldAreaTxt_A1.getValue().equals(sMsg.A.no(j).xxGenlFldAreaTxt_A1.getValue())) {
                    return true;
                }
                //                if (!ZYPCommonFunc.hasValue(cMsg.A.no(cnt).ctacPsnPk_A1)) {
                //                    if (ZYPCommonFunc.hasValue(sMsg.A.no(cnt).ctacPsnPk_A1)) {
                //                        return true;
                //                    }
                //                } else {
                //                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(j).ctacPsnPk_A1)) {
                //                        return true;
                //                    }
                //                    if (cMsg.A.no(cnt).ctacPsnPk_A1.getValue().compareTo(sMsg.A.no(j).ctacPsnPk_A1.getValue()) != 0) {
                //                        return true;
                //                    }
                //                }
                if (!cMsg.A.no(cnt).xxGenlFldAreaTxt_A2.getValue().equals(sMsg.A.no(j).xxGenlFldAreaTxt_A2.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).custEmlMsgTxt_A1.getValue().equals(sMsg.A.no(j).custEmlMsgTxt_A1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).defInvGrpCd_P1.getValue().equals(sMsg.A.no(j).defInvGrpCd_P1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).invGrpNum_A1.getValue().equals(sMsg.A.no(j).invGrpNum_A1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).ezUpTime_A1.getValue().equals(sMsg.A.no(j).ezUpTime_A1.getValue())) {
                    return true;
                }
                if (!cMsg.A.no(cnt).ezUpTimeZone_A1.getValue().equals(sMsg.A.no(j).ezUpTimeZone_A1.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean updateBillingCycleInfoCheck(NMAL6730CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal billToCustPk = cMsg.billToCustPk_H1.getValue();
        NMAL6730SMsg sMsg = new NMAL6730SMsg();

        S21SsmEZDResult res = NMAL6730Query.getInstance().getBillingCycleInfo(glblCmpyCd, billToCustPk, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            if (!cMsg.defBaseTpCd_P1.getValue().equals(sMsg.defBaseTpCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.defBaseCycleCd_P1.getValue().equals(sMsg.defBaseCycleCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.defUsgTpCd_P1.getValue().equals(sMsg.defUsgTpCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.defUsgCycleCd_P1.getValue().equals(sMsg.defUsgCycleCd_P1.getValue())) {
                return true;
            }
            if (!convFlgItem(cMsg.dsBillTgtrFlg_I1.getValue()).equals(convFlgItem(sMsg.dsBillTgtrFlg_I1.getValue()))) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    private boolean updateFinancialInfoCheck(NMAL6730CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String billToCustCd = cMsg.billToCustCd_H1.getValue();
        NMAL6730SMsg sMsg = new NMAL6730SMsg();

        S21SsmEZDResult res = NMAL6730Query.getInstance().getFinancialInfo(glblCmpyCd, billToCustCd, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {

            if (!cMsg.ccyCd_P1.getValue().equals(sMsg.ccyCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.custCrRtgCd_P1.getValue().equals(sMsg.custCrRtgCd_P1.getValue())) {
                return true;
            }
            //QC#17252 mod Start
            //            if (!ZYPCommonFunc.hasValue(cMsg.crLimitAmt_F1)) {
            //                if (ZYPCommonFunc.hasValue(sMsg.crLimitAmt_F1)) {
            //                    return true;
            //                }
            //            } else {
            //                if (cMsg.crLimitAmt_F1.getValue().compareTo(sMsg.crLimitAmt_F1.getValue()) != 0) {
            //                    return true;
            //                }
            //            }
            if (!isEqualBigDecimal(cMsg.crLimitAmt_F1, sMsg.crLimitAmt_F1)) {
                return true;
            }
            //QC#17252 mod End
            if (!cMsg.crChkReqTpCd_P1.getValue().equals(sMsg.crChkReqTpCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.crRiskClsCd_P1.getValue().equals(sMsg.crRiskClsCd_P1.getValue())) {
                return true;
            }
            // Add Start #22095 2018/01/25
            if (!cMsg.contrCrRiskClsCd_P1.getValue().equals(sMsg.contrCrRiskClsCd_P1.getValue())) {
                return true;
            }
            // Add End   #22095 2018/01/25
            if (!cMsg.pmtTermCashDiscCd_P1.getValue().equals(sMsg.pmtTermCashDiscCd_P1.getValue())) {
                return true;
            }
            if (!convFlgItem(cMsg.ovrdPmtTermFlg_F1.getValue()).equals(convFlgItem(sMsg.ovrdPmtTermFlg_F1.getValue()))) {
                return true;
            }
            if (!convFlgItem(cMsg.cashOrCcReqFlg_F1.getValue()).equals(convFlgItem(sMsg.cashOrCcReqFlg_F1.getValue()))) {
                return true;
            }
            // QC#4324
            if (!convFlgItem(cMsg.custHardHldFlg_F1.getValue()).equals(convFlgItem(sMsg.custHardHldFlg_F1.getValue()))) {
                return true;
            }
            if (!cMsg.remId_F1.getValue().equals(sMsg.remId_F1.getValue())) {
                return true;
            }
            if (!convFlgItem(cMsg.arStmtFlg_F1.getValue()).equals(convFlgItem(sMsg.arStmtFlg_F1.getValue()))) {
                return true;
            }
            // Add Start #23882 2018/04/20
            if (!cMsg.sendCrBalStmtFlg_F1.getValue().equals(sMsg.sendCrBalStmtFlg_F1.getValue())) {
                return true;
            }
            // Add End   #23882 2018/04/20
            if (!cMsg.arStmtIssCycleCd_P1.getValue().equals(sMsg.arStmtIssCycleCd_P1.getValue())) {
                return true;
            }
            // START 2018/01/16 [QC#21734, DEL]
            // if (!convFlgItem(cMsg.dunFlg_F1.getValue()).equals(convFlgItem(sMsg.dunFlg_F1.getValue()))) {
            //     return true;
            // }
            // END   2018/01/16 [QC#21734, DEL]
            if (!cMsg.cltCustTpCd_F1.getValue().equals(sMsg.cltCustTpCd_F1.getValue())) {
                return true;
            }
            if (!cMsg.cltCustTpNm_F1.getValue().equals(sMsg.cltCustTpNm_F1.getValue())) {
                return true;
            }
            if (!cMsg.cltPtfoCd_F1.getValue().equals(sMsg.cltPtfoCd_F1.getValue())) {
                return true;
            }
            if (!cMsg.cltPtfoNm_F1.getValue().equals(sMsg.cltPtfoNm_F1.getValue())) {
                return true;
            }
            if (!cMsg.dsCltAcctStsCd_P1.getValue().equals(sMsg.dsCltAcctStsCd_P1.getValue())) {
                return true;
            }
            if (!convFlgItem(cMsg.lateFeeFlg_F1.getValue()).equals(convFlgItem(sMsg.lateFeeFlg_F1.getValue()))) {
                return true;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.lateFeeAmt_F1)) {
                if (ZYPCommonFunc.hasValue(sMsg.lateFeeAmt_F1)) {
                    return true;
                }
            } else {
                if (cMsg.lateFeeAmt_F1.getValue().compareTo(sMsg.lateFeeAmt_F1.getValue()) != 0) {
                    return true;
                }
            }
            // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
            if (!isEqualBigDecimal(cMsg.mlyLateFeeRate_F1, sMsg.mlyLateFeeRate_F1)) {
                return true;
            }
            // End 2023/2/02 S.Nakatani [QC#60811, ADD]
            // Del Start 2018/08/01 QC#27222
//            if (!cMsg.dsCustTaxCd_F1.getValue().equals(sMsg.dsCustTaxCd_F1.getValue())) {
//                return true;
//            }
//            if (!cMsg.dsCustTaxCalcCd_P1.getValue().equals(sMsg.dsCustTaxCalcCd_P1.getValue())) {
//                return true;
//            }
            // Mod Start 2018/08/21 QC#27222-2 Uncomment
            if (!convFlgItem(cMsg.dsTaxExemFlg_F1.getValue()).equals(convFlgItem(sMsg.dsTaxExemFlg_F1.getValue()))) {
                return true;
            }
            if (!cMsg.dsExemExprDt_F1.getValue().equals(sMsg.dsExemExprDt_F1.getValue())) {
                return true;
            }
            // Mod End 2018/08/21 QC#27222-2
            // Del End 2018/08/01 QC#27222
            if (!cMsg.dsTaxGrpExemCd_P1.getValue().equals(sMsg.dsTaxGrpExemCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.dsTaxPrntTpCd_P1.getValue().equals(sMsg.dsTaxPrntTpCd_P1.getValue())) {
                return true;
            }
            if (!cMsg.ezUpTime_F1.getValue().equals(sMsg.ezUpTime_F1.getValue())) {
                return true;
            }
            if (!cMsg.ezUpTimeZone_F1.getValue().equals(sMsg.ezUpTimeZone_F1.getValue())) {
                return true;
            }
            // START 2018/04/17 E.Kameishi [QC#21037, ADD]
            if (!cMsg.autoCashHrchCd_P1.getValue().equals(sMsg.autoCashHrchCd_P1.getValue())) {
                return true;
            }
            // END 2018/04/17 E.Kameishi [QC#21037, ADD]
        } else {
            return true;
        }
        return false;
    }

    private String convFlgItem(String item) {
        if (!ZYPCommonFunc.hasValue(item)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return item;
    }

    private boolean updateHeaderCheck(NMAL6730CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String billToCustCd = cMsg.billToCustCd_H1.getValue();
        NMAL6730SMsg sMsg = new NMAL6730SMsg();

        S21SsmEZDResult res = NMAL6730Query.getInstance().getBillToCustInfoHeader(glblCmpyCd, billToCustCd, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            if (!cMsg.glblCmpyCd_H1.getValue().equals(sMsg.glblCmpyCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaChCd_BK.getValue().equals(sMsg.coaChCd_BK.getValue())) {
                return true;
            }
            // QC#9448
            //            if (!cMsg.coaAfflCd_BK.getValue().equals(sMsg.coaAfflCd_BK.getValue())) {
            //                return true;
            //            }
            if (!cMsg.dsAcctNum_H1.getValue().equals(sMsg.dsAcctNum_H1.getValue())) {
                return true;
            }
            if (!cMsg.dsAcctNm_H1.getValue().equals(sMsg.dsAcctNm_H1.getValue())) {
                return true;
            }
            if (!cMsg.xxAllLineAddr_H1.getValue().equals(sMsg.xxAllLineAddr_H1.getValue())) {
                return true;
            }
            if (!cMsg.firstLineAddr_H1.getValue().equals(sMsg.firstLineAddr_H1.getValue())) {
                return true;
            }
            if (!cMsg.scdLineAddr_H1.getValue().equals(sMsg.scdLineAddr_H1.getValue())) {
                return true;
            }
            if (!cMsg.thirdLineAddr_H1.getValue().equals(sMsg.thirdLineAddr_H1.getValue())) {
                return true;
            }
            if (!cMsg.frthLineAddr_H1.getValue().equals(sMsg.frthLineAddr_H1.getValue())) {
                return true;
            }
            if (!cMsg.ctyAddr_H1.getValue().equals(sMsg.ctyAddr_H1.getValue())) {
                return true;
            }
            if (!cMsg.stCd_H1.getValue().equals(sMsg.stCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.postCd_H1.getValue().equals(sMsg.postCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.locNum_H1.getValue().equals(sMsg.locNum_H1.getValue())) {
                return true;
            }
            if (!cMsg.billToCustCd_H1.getValue().equals(sMsg.billToCustCd_H1.getValue())) {
                return true;
            }
            if (cMsg.billToCustPk_H1.getValue().compareTo(sMsg.billToCustPk_H1.getValue()) != 0) {
                return true;
            }
            if (!cMsg.coaChCd_H1.getValue().equals(sMsg.coaChCd_H1.getValue())) {
                return true;
            }
            // QC#9448
            //            if (!cMsg.coaAfflCd_H1.getValue().equals(sMsg.coaAfflCd_H1.getValue())) {
            //                return true;
            //            }
            if (!cMsg.coaCmpyCd_H1.getValue().equals(sMsg.coaCmpyCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaBrCd_H1.getValue().equals(sMsg.coaBrCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaCcCd_H1.getValue().equals(sMsg.coaCcCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaAcctCd_H1.getValue().equals(sMsg.coaAcctCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaProdCd_H1.getValue().equals(sMsg.coaProdCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaProjCd_H1.getValue().equals(sMsg.coaProjCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.coaExtnCd_H1.getValue().equals(sMsg.coaExtnCd_H1.getValue())) {
                return true;
            }
            if (!cMsg.ezUpTime_H1.getValue().equals(sMsg.ezUpTime_H1.getValue())) {
                return true;
            }
            if (!cMsg.ezUpTimeZone_H1.getValue().equals(sMsg.ezUpTimeZone_H1.getValue())) {
                return true;
            }
            //            if (!cMsg.ezUpTime_H2.getValue().equals(sMsg.ezUpTime_H2.getValue())) {
            //                return true;
            //            }
            //            if (!cMsg.ezUpTimeZone_H2.getValue().equals(sMsg.ezUpTimeZone_H2.getValue())) {
            //                return true;
            //            }
            if (!cMsg.rgtnStsCd_H1.getValue().equals(sMsg.rgtnStsCd_H1.getValue())) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    // Add Start #4332 2016/02/23
    /**
     * Method name: doProcess_NMAL6730Scrn00_Linkage_CMN_Submit <dd>
     * The method explanation: submit button event
     * @param bizMsg NMAL6730CMsg
     * @param sharedMsg NMAL6730SMsg
     */
    private void doProcess_NMAL6730Scrn00_CMN_Submit(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        if (!checkInputChange(cMsg, sMsg)) {
            cMsg.setMessageInfo(NMAM8333I);
            return;
        }

        boolean chgFlg = false;
        if (updateFinancialTabCheck(cMsg)) {
            chgFlg = true;

            if (!masterCheckForHeader(cMsg)) {
                return;
            }
            if (!inputCheckForFinancial(cMsg)) {
                cMsg.xxDplyTab.setValue(TAB_FINANCIAL);
                return;
            }

            if (!masterCheckForFinancial(cMsg)) {
                cMsg.xxDplyTab.setValue(TAB_FINANCIAL);
                return;
            }
            saveBillToCust(cMsg);
            if (MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
                return;
            }
            //QC#17252 mod
            // Mod Start #22095 2018/01/25
            //if (ZYPCommonFunc.hasValue(cMsg.ccyCd_P1) && ZYPCommonFunc.hasValue(cMsg.crChkReqTpCd_P1) && ZYPCommonFunc.hasValue(cMsg.crRiskClsCd_P1) && ZYPCommonFunc.hasValue(cMsg.pmtTermCashDiscCd_P1)) {
            if (ZYPCommonFunc.hasValue(cMsg.ccyCd_P1) && ZYPCommonFunc.hasValue(cMsg.crChkReqTpCd_P1) && ZYPCommonFunc.hasValue(cMsg.crRiskClsCd_P1) && ZYPCommonFunc.hasValue(cMsg.contrCrRiskClsCd_P1) && ZYPCommonFunc.hasValue(cMsg.pmtTermCashDiscCd_P1)) {
            // Mod End   #22095 2018/01/25
                saveCustCrPrfl(cMsg);
                if (MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
                    return;
                }
                if (!updateArAmount(cMsg)) {
                    return;
                }
            } else {
                if (!deleteCustCrPrfl(cMsg)) {
                    return;
                }
            }
        }

        if (!inputCheckForInvBllg(cMsg)) {
            cMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return;
        }
        if (!masterCheckForInvBllg(cMsg)) {
            cMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return;
        }
        if (updateInvBllgTabCheck(cMsg)) {
            //chgFlg = true;
            saveBillToCustByInvBllgTab(cMsg, chgFlg);
            if (MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
                return;
            }
        }

        saveDsCustInvRule(cMsg);
        if (MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
            return;
        }

        // Delete DS_CUST_INV_RULE
        int cnt = sMsg.B.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6730_BSMsg bsMsg = sMsg.B.no(i);
            DS_CUST_INV_RULETMsg dsCustInvRuleTMsg = NMAL6730CommonLogic.findDsCustInvRuleForUpdate(getGlobalCompanyCode(), bsMsg.dsCustInvRulePk_B1.getValue());
            if (dsCustInvRuleTMsg == null) {
                String[] args = {"DS_CUST_INV_RULE" };
                cMsg.setMessageInfo(NMAM8111E, args);
                return;
            }
            if (!ZYPDateUtil.isSameTimeStamp(bsMsg.ezUpTime_B1.getValue(), bsMsg.ezUpTimeZone_B1.getValue(), dsCustInvRuleTMsg.ezUpTime.getValue(), dsCustInvRuleTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }

            EZDTBLAccessor.logicalRemove(dsCustInvRuleTMsg);
            if (!RTNCD_NORMAL.equals(dsCustInvRuleTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_INV_RULE" });
                return;
            }
        }

        if (!createOrUpdateDsAcctRefAttrb(cMsg, sMsg, getGlobalCompanyCode())) {
            return;
        }
    }

    private void saveCustCrPrfl(NMAL6730CMsg cMsg) {
        CUST_CR_PRFLTMsg custCrPrflTMsg = new CUST_CR_PRFLTMsg();
        custCrPrflTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        custCrPrflTMsg.billToCustPk.setValue(cMsg.billToCustPk_H1.getValue());

        custCrPrflTMsg = (CUST_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(custCrPrflTMsg);
        if (custCrPrflTMsg == null) {
            custCrPrflTMsg = new CUST_CR_PRFLTMsg();

            setCustCrPrfl(custCrPrflTMsg, cMsg, true);

            // S21FastTBLAccessor.insert(custCrPrflTMsg);
            EZDTBLAccessor.create(custCrPrflTMsg); // MOD S21_NA #17533
            if (!RTNCD_NORMAL.equals(custCrPrflTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {"CUST_CR_PRFL" });
                return;
            }
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_F1.getValue(), cMsg.ezUpTimeZone_F1.getValue(), custCrPrflTMsg.ezUpTime.getValue(), custCrPrflTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }
            // START 2017/09/07 J.Kim [QC#20495,MOD]
            // setCustCrPrfl(custCrPrflTMsg, cMsg, true);
            setCustCrPrfl(custCrPrflTMsg, cMsg, false);
            // END 2017/09/07 J.Kim [QC#20495,MOD]

            S21FastTBLAccessor.update(custCrPrflTMsg);
            if (!RTNCD_NORMAL.equals(custCrPrflTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {"CUST_CR_PRFL" });
                return;
            }
        }
    }

    private boolean updateArAmount(NMAL6730CMsg cMsg) {

        if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(cMsg.rgtnStsCd_H1.getValue()) || RGTN_STS.TERMINATED.equals(cMsg.rgtnStsCd_H1.getValue())) {

            //            if (ZYPCommonFunc.hasValue(cMsg.crLimitAmt_F1) && cMsg.crLimitAmt_F1.getValue().compareTo(BigDecimal.ZERO) > 0) {
            if (!ZYPCommonFunc.hasValue(cMsg.crLimitAmt_F1) || (ZYPCommonFunc.hasValue(cMsg.crLimitAmt_F1) && cMsg.crLimitAmt_F1.getValue().compareTo(BigDecimal.ZERO) > 0)) { //QC#17252 mod
                String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_H1.getValue());

                if (!NMAL6730CommonLogic.callCreditProfileUpdateApi(cMsg, slsDt)) {
                    return false;
                }
            }
        }

        return true;

    }

    private void setCustCrPrfl(CUST_CR_PRFLTMsg custCrPrflTMsg, NMAL6730CMsg cMsg, boolean isCreate) {

        if (isCreate) {
            custCrPrflTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_H1.getValue());
            custCrPrflTMsg.billToCustPk.setValue(cMsg.billToCustPk_H1.getValue());
            custCrPrflTMsg.billToCustCd.setValue(cMsg.billToCustCd_H1.getValue());

            custCrPrflTMsg.invDtChkReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            custCrPrflTMsg.crRvwDtChkReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            custCrPrflTMsg.psnGtdFlg.setValue(ZYPConstant.FLG_OFF_N);
            custCrPrflTMsg.ovdWsFlg.setValue(ZYPConstant.FLG_OFF_N);
            custCrPrflTMsg.ovdPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
            custCrPrflTMsg.srCrCardFlg.setValue(ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.inProcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.arBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.sesnCrLimitAmt, BigDecimal.ZERO);
            // START 2017/09/07 J.Kim [QC#20495,DEL]
            // String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_H1.getValue());
            // ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crLimitChngDt, slsDt);
            // END 2017/09/07 J.Kim [QC#20495,DEL]

            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.cashOnDelyReqFlg, ZYPConstant.FLG_OFF_N);
        }

        // START 2017/09/07 J.Kim [QC#20495,ADD]
        if (!isEqualBigDecimal(cMsg.crLimitAmt_F1, custCrPrflTMsg.crLimitAmt)) {
            String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crLimitChngDt, slsDt);
            BigDecimal addAmt = add(custCrPrflTMsg.inProcAmt.getValue(), custCrPrflTMsg.arBalAmt.getValue());
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crBalAmt, subtract(cMsg.crLimitAmt_F1.getValue(), addAmt));
        }
        // END 2017/09/07 J.Kim [QC#20495,ADD]

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.cashOrCcReqFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.cashOrCcReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.cashOrCcReqFlg, ZYPConstant.FLG_OFF_N);
        }
        custCrPrflTMsg.ccyCd.setValue(cMsg.ccyCd_P1.getValue());
        custCrPrflTMsg.crLimitAmt.setValue(cMsg.crLimitAmt_F1.getValue());
        custCrPrflTMsg.crChkReqTpCd.setValue(cMsg.crChkReqTpCd_P1.getValue());
        custCrPrflTMsg.crRiskClsCd.setValue(cMsg.crRiskClsCd_P1.getValue());
        // START 2018/01/26 H.Ikeda [QC#22095,ADD]
        custCrPrflTMsg.contrCrRiskClsCd.setValue(cMsg.contrCrRiskClsCd_P1.getValue());
        // END 2018/01/26 H.Ikeda [QC#22095,ADD]

        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.custCrRtgCd, cMsg.custCrRtgCd_P1);
        if (ZYPCommonFunc.hasValue(cMsg.ovrdPmtTermFlg_F1)) {
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.ovrdPmtTermFlg, cMsg.ovrdPmtTermFlg_F1);
        } else {
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.ovrdPmtTermFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.pmtTermCashDiscCd, cMsg.pmtTermCashDiscCd_P1);
        // QC#4324
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.custHardHldFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.custHardHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.custHardHldFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2018/04/16 E.Kameishi [QC#21037, ADD]
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.autoCashHrchCd, cMsg.autoCashHrchCd_P1);
        // END 2018/04/16 E.Kameishi [QC#21037, ADD]
    }

    private void saveBillToCust(NMAL6730CMsg cMsg) {
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        billToCustTMsg.billToCustPk.setValue(cMsg.billToCustPk_H1.getValue());

        billToCustTMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKey(billToCustTMsg);
        if (billToCustTMsg == null) {
            String[] args = {"BILL_TO_CUST" };
            cMsg.setMessageInfo(NMAM8111E, args);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H1.getValue(), cMsg.ezUpTimeZone_H1.getValue(), billToCustTMsg.ezUpTime.getValue(), billToCustTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.arStmtFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
        }

        // START 2018/04/19 H.Ikeda [QC#23382, ADD]
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.sendCrBalStmtFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        // END   2018/04/19 H.Ikeda [QC#23382, ADD]

        ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtIssDay, NMAL6730CommonLogic.getArStmtIssDay(getGlobalCompanyCode(), cMsg.arStmtIssCycleCd_P1.getValue()));
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.remId, cMsg.remId_F1);

        // QC#9448
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaChCd, cMsg.coaChCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAfflCd, cMsg.coaAfflCd_H1);

        setBillToCustByFinancialTab(billToCustTMsg, cMsg);

        S21FastTBLAccessor.update(billToCustTMsg);
        if (!RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
            return;
        }
    }

    private void setBillToCustByFinancialTab(BILL_TO_CUSTTMsg billToCustTMsg, NMAL6730CMsg cMsg) {

        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaChCd, cMsg.coaChCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAfflCd, cMsg.coaAfflCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaCmpyCd, cMsg.coaCmpyCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaBrCd, cMsg.coaBrCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaCcCd, cMsg.coaCcCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAcctCd, cMsg.coaAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaProdCd, cMsg.coaProdCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaProjCd, cMsg.coaProjCd_H1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaExtnCd, cMsg.coaExtnCd_H1);

        // START 2018/01/16 [QC#21734, MOD]
        // if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dunFlg_F1.getValue())) {
        //     ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunFlg, ZYPConstant.FLG_ON_Y);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunFlg, ZYPConstant.FLG_OFF_N);
        // }
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunFlg, ZYPConstant.FLG_ON_Y);
        // END   2018/01/16 [QC#21734, MOD]
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.cltCustTpCd, cMsg.cltCustTpCd_F1);

        ZYPEZDItemValueSetter.setValue(billToCustTMsg.cltPtfoPk, cMsg.cltPtfoPk_F1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCltAcctStsCd, cMsg.dsCltAcctStsCd_P1);

        // 2019/05/13 QC#50113 Mod Start

        // if (ZYPCommonFunc.hasValue(cMsg.lateFeeAmt_F1) && BigDecimal.ZERO.compareTo(cMsg.lateFeeAmt_F1.getValue()) < 0) {
        //     ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, ZYPConstant.FLG_ON_Y);
        //     ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeAmt, cMsg.lateFeeAmt_F1);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
        //     ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeAmt, BigDecimal.ZERO);
        // }
        if (ZYPCommonFunc.hasValue(cMsg.lateFeeFlg_F1)) {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.lateFeeAmt_F1)) {
               ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeAmt, cMsg.lateFeeAmt_F1);
        } else {
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeAmt, BigDecimal.ZERO);
        }
        // 2019/05/13 QC#50113 Mod End
        // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.mlyLateFeeRate, cMsg.mlyLateFeeRate_F1);
        // End 2023/2/02 S.Nakatani [QC#60811, ADD]

        // Del Start 2018/08/01 QC#27222
//        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCustTaxCd, cMsg.dsCustTaxCd_F1);
//        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCustTaxCalcCd, cMsg.dsCustTaxCalcCd_P1);
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsTaxExemFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsExemExprDt, cMsg.dsExemExprDt_F1);
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/08/01 QC#27222
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxGrpExemCd, cMsg.dsTaxGrpExemCd_P1);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxPrntTpCd, cMsg.dsTaxPrntTpCd_P1);

    }

    // Mod Start #4332 2016/02/23
    private boolean saveDsCustInvRule(NMAL6730CMsg cMsg) {
        // private void saveDsCustInvRule(NMAL6730CMsg cMsg, boolean chkFlg) {
        // Mod End #4332 2016/02/23
        boolean chgFlg = false;
        String glblCmpyCd = getGlobalCompanyCode();
        String billToCustCd = cMsg.billToCustCd_H1.getValue();
        String locNum = cMsg.locNum_H1.getValue();
        NMAL6730SMsg sMsg = new NMAL6730SMsg();
        NMAL6730Query.getInstance().getInvoiceSourceList(glblCmpyCd, billToCustCd, locNum, sMsg);
        // QC#7781
        NMAL6730CommonLogic.getInvoiceSourceListInternalReview(cMsg, sMsg, getGlobalCompanyCode());
        NMAL6730CommonLogic.getInvoiceSourceListExternalContact(cMsg, sMsg, getGlobalCompanyCode());

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            DS_CUST_INV_RULETMsg dsCustInvRule = new DS_CUST_INV_RULETMsg();

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).dsCustInvRulePk_A1)) {
                if (updateInvoiceSourceListCheck(cMsg, i, sMsg)) {
                    dsCustInvRule.glblCmpyCd.setValue(getGlobalCompanyCode());
                    dsCustInvRule.dsCustInvRulePk.setValue(cMsg.A.no(i).dsCustInvRulePk_A1.getValue());
                    dsCustInvRule = (DS_CUST_INV_RULETMsg) S21FastTBLAccessor.findByKey(dsCustInvRule);

                    dsCustInvRule.custInvSrcCd.setValue(cMsg.A.no(i).custInvSrcCd_P1.getValue());
                    dsCustInvRule.custBllgTpCd.setValue(cMsg.A.no(i).custBllgTpCd_P1.getValue());
                    dsCustInvRule.custConslTermCd.setValue(cMsg.A.no(i).custConslTermCd_P1.getValue());
                    dsCustInvRule.custBllgVcleCd.setValue(cMsg.A.no(i).custBllgVcleCd_P1.getValue());
                    // QC#7781
                    // dsCustInvRule.itrlRvwPsnCd.setValue(cMsg.A.no(i).itrlRvwPsnCd_A1.getValue());
                    // dsCustInvRule.ctacPsnPk.setValue(cMsg.A.no(i).ctacPsnPk_A1.getValue());
                    dsCustInvRule.custEmlMsgTxt.setValue(cMsg.A.no(i).custEmlMsgTxt_A1.getValue());
                    dsCustInvRule.defInvGrpCd.setValue(cMsg.A.no(i).defInvGrpCd_P1.getValue());
                    dsCustInvRule.invGrpNum.setValue(cMsg.A.no(i).invGrpNum_A1.getValue());

                    S21FastTBLAccessor.update(dsCustInvRule);
                    if (!RTNCD_NORMAL.equals(dsCustInvRule.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_INV_RULE" });
                        // Mod Start #4332 2016/02/23
                        //return;
                        return true;
                        // Mod End #4332 2016/02/23
                    }

                    // QC#7781
                    if (!updateInternalEmailReview(cMsg, dsCustInvRule.dsCustInvRulePk.getValue(), cMsg.A.no(i).xxGenlFldAreaTxt_A1.getValue())) {
                        return true;
                    }
                    if (!updateExternalEmailContact(cMsg, dsCustInvRule.dsCustInvRulePk.getValue(), cMsg.A.no(i).xxGenlFldAreaTxt_A2.getValue())) {
                        return true;
                    }

                    chgFlg = true;
                } else {
                    chgFlg = false;
                }
            } else {
                dsCustInvRule.glblCmpyCd.setValue(getGlobalCompanyCode());
                dsCustInvRule.dsCustInvRulePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CUST_INV_RULE_SQ"));
                dsCustInvRule.locNum.setValue(cMsg.locNum_H1.getValue());
                dsCustInvRule.billToCustCd.setValue(cMsg.billToCustCd_H1.getValue());
                dsCustInvRule.custInvSrcCd.setValue(cMsg.A.no(i).custInvSrcCd_P1.getValue());
                dsCustInvRule.custBllgTpCd.setValue(cMsg.A.no(i).custBllgTpCd_P1.getValue());
                dsCustInvRule.custConslTermCd.setValue(cMsg.A.no(i).custConslTermCd_P1.getValue());
                dsCustInvRule.custBllgVcleCd.setValue(cMsg.A.no(i).custBllgVcleCd_P1.getValue());
                // QC#7781
                // dsCustInvRule.itrlRvwPsnCd.setValue(cMsg.A.no(i).itrlRvwPsnCd_A1.getValue());
                // dsCustInvRule.ctacPsnPk.setValue(cMsg.A.no(i).ctacPsnPk_A1.getValue());
                dsCustInvRule.custEmlMsgTxt.setValue(cMsg.A.no(i).custEmlMsgTxt_A1.getValue());
                dsCustInvRule.defInvGrpCd.setValue(cMsg.A.no(i).defInvGrpCd_P1.getValue());
                dsCustInvRule.invGrpNum.setValue(cMsg.A.no(i).invGrpNum_A1.getValue());

                S21FastTBLAccessor.insert(dsCustInvRule);
                if (!RTNCD_NORMAL.equals(dsCustInvRule.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {"DS_CUST_INV_RULE" });
                    // Mod Start #4332 2016/02/23
                    return true;
                    // Mod End #4332 2016/02/23
                }

                // QC#7781
                if (!insertInternalEmailReview(cMsg, dsCustInvRule.dsCustInvRulePk.getValue(), cMsg.A.no(i).xxGenlFldAreaTxt_A1.getValue())) {
                    return true;
                }
                if (!insertExternalEmailContact(cMsg, dsCustInvRule.dsCustInvRulePk.getValue(), cMsg.A.no(i).xxGenlFldAreaTxt_A2.getValue())) {
                    return true;
                }

                chgFlg = true;
            }
        }
        // Mod Start #4332 2016/02/23
        //        if (infoFlg) {
        //            cMsg.setMessageInfo(NMAM8333I);
        //        }
        return chgFlg;
        // Mod End #4332 2016/02/23
    }

    // QC#7781
    private boolean updateInternalEmailReview(NMAL6730CMsg cMsg, BigDecimal dsCustInvRulePk, String psnCdList) {
        List<String> listExstPsnCd = new ArrayList<String>();
        List<BigDecimal> listExstRcpntPk = new ArrayList<BigDecimal>();
        S21SsmEZDResult result = NMAL6730Query.getInstance().getInvoiceSourceListInternalReview(dsCustInvRulePk);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
            for (Map<String, Object> map : listMap) {
                listExstPsnCd.add((String) map.get("PSN_CD"));
                listExstRcpntPk.add((BigDecimal) map.get("DS_CUST_INV_RULE_RCPNT_PK"));
            }
        }

        String[] psnCdArray = NMAL6730CommonLogic.splitByComma(psnCdList);
        List<String> listNewPsnCd = Arrays.asList(psnCdArray);

        for (int i = 0; i < listExstPsnCd.size(); i++) {
            String exstPsnCd = listExstPsnCd.get(i);
            BigDecimal dsCustInvRuleRcpntPk = listExstRcpntPk.get(i);
            if (!listNewPsnCd.contains(exstPsnCd)) {
                if (!removeInternalEmailReview(cMsg, dsCustInvRuleRcpntPk)) {
                    return false;
                }
            }
        }

        for (String newPsnCd : listNewPsnCd) {
            if (!listExstPsnCd.contains(newPsnCd)) {
                if (!insertInternalEmailReview(cMsg, dsCustInvRulePk, newPsnCd)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean insertInternalEmailReview(NMAL6730CMsg cMsg, BigDecimal dsCustInvRulePk, String psnCdList) {
        String[] psnCdArray = NMAL6730CommonLogic.splitByComma(psnCdList);
        for (String psnCd : psnCdArray) {
            DS_CUST_INV_RULE_RCPNTTMsg tMsg = new DS_CUST_INV_RULE_RCPNTTMsg();
            tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
            tMsg.dsCustInvRuleRcpntPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_INV_RULE_RCPNT_SQ));
            tMsg.dsCustInvRulePk.setValue(dsCustInvRulePk);
            tMsg.invRuleRcpntTpCd.setValue(INV_RULE_RCPNT_TP.INTERNAL);
            tMsg.psnCd.setValue(psnCd);
            S21FastTBLAccessor.insert(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {"DS_CUST_INV_RULE_RCPNT" });
                return false;
            }
        }
        return true;
    }

    private boolean removeInternalEmailReview(NMAL6730CMsg cMsg, BigDecimal dsCustInvRuleRcpntPk) {
        DS_CUST_INV_RULE_RCPNTTMsg tMsg = new DS_CUST_INV_RULE_RCPNTTMsg();
        tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsg.dsCustInvRuleRcpntPk.setValue(dsCustInvRuleRcpntPk);
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0208E, new String[] {"DS_CUST_INV_RULE_RCPNT" });
            return false;
        }
        return true;
    }

    private boolean updateExternalEmailContact(NMAL6730CMsg cMsg, BigDecimal dsCustInvRulePk, String ctacPsnPkList) {
        List<String> listExstCtacPsnPk = new ArrayList<String>();
        List<BigDecimal> listExstRcpntPk = new ArrayList<BigDecimal>();
        S21SsmEZDResult result = NMAL6730Query.getInstance().getInvoiceSourceListExternalContact(dsCustInvRulePk);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
            for (Map<String, Object> map : listMap) {
                listExstCtacPsnPk.add(((BigDecimal) map.get("CTAC_PSN_PK")).toPlainString());
                listExstRcpntPk.add((BigDecimal) map.get("DS_CUST_INV_RULE_RCPNT_PK"));
            }
        }

        String[] ctacPsnPkArray = NMAL6730CommonLogic.splitByComma(ctacPsnPkList);
        List<String> listNewCtacPsnPk = Arrays.asList(ctacPsnPkArray);

        for (int i = 0; i < listExstCtacPsnPk.size(); i++) {
            String exstCtacPsnPk = listExstCtacPsnPk.get(i);
            BigDecimal dsCustInvRuleRcpntPk = listExstRcpntPk.get(i);
            if (!listNewCtacPsnPk.contains(exstCtacPsnPk)) {
                if (!removeExternalEmailContact(cMsg, dsCustInvRuleRcpntPk)) {
                    return false;
                }
            }
        }

        for (String newCtacPsnPk : listNewCtacPsnPk) {
            if (!listExstCtacPsnPk.contains(newCtacPsnPk)) {
                if (!insertExternalEmailContact(cMsg, dsCustInvRulePk, newCtacPsnPk)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean insertExternalEmailContact(NMAL6730CMsg cMsg, BigDecimal dsCustInvRulePk, String ctacPsnPkList) {
        String[] ctacPsnPkCdArray = NMAL6730CommonLogic.splitByComma(ctacPsnPkList);
        for (String ctacPsnPk : ctacPsnPkCdArray) {
            DS_CUST_INV_RULE_RCPNTTMsg tMsg = new DS_CUST_INV_RULE_RCPNTTMsg();
            tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
            tMsg.dsCustInvRuleRcpntPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_INV_RULE_RCPNT_SQ));
            tMsg.dsCustInvRulePk.setValue(dsCustInvRulePk);
            tMsg.invRuleRcpntTpCd.setValue(INV_RULE_RCPNT_TP.EXTERNAL);
            tMsg.ctacPsnPk.setValue(new BigDecimal(ctacPsnPk));
            S21FastTBLAccessor.insert(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {"DS_CUST_INV_RULE_RCPNT" });
                return false;
            }
        }
        return true;
    }

    private boolean removeExternalEmailContact(NMAL6730CMsg cMsg, BigDecimal dsCustInvRuleRcpntPk) {
        return removeInternalEmailReview(cMsg, dsCustInvRuleRcpntPk);
    }

    private void saveBillToCustByInvBllgTab(NMAL6730CMsg cMsg, boolean chgFlg) {
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        billToCustTMsg.billToCustPk.setValue(cMsg.billToCustPk_H1.getValue());

        billToCustTMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKey(billToCustTMsg);
        if (billToCustTMsg == null) {
            billToCustTMsg = new BILL_TO_CUSTTMsg();
            billToCustTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            billToCustTMsg.billToCustPk.setValue(cMsg.billToCustPk_H1.getValue());

            billToCustTMsg.defBaseTpCd.setValue(cMsg.defBaseTpCd_P1.getValue());
            billToCustTMsg.defBaseCycleCd.setValue(cMsg.defBaseCycleCd_P1.getValue());
            billToCustTMsg.defUsgTpCd.setValue(cMsg.defUsgTpCd_P1.getValue());
            billToCustTMsg.defUsgCycleCd.setValue(cMsg.defUsgCycleCd_P1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.dsBillTgtrFlg_I1)) {
                billToCustTMsg.dsBillTgtrFlg.setValue(cMsg.dsBillTgtrFlg_I1.getValue());
            } else {
                billToCustTMsg.dsBillTgtrFlg.setValue(ZYPConstant.FLG_OFF_N);
            }

            // START 2018/04/19 H.Ikeda [QC#23382, ADD]
            billToCustTMsg.sendCrBalStmtFlg.setValue(ZYPConstant.FLG_OFF_N);
            // END   2018/04/19 H.Ikeda [QC#23382, ADD]
            
            S21FastTBLAccessor.insert(billToCustTMsg);
            if (!RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {"BILL_TO_CUST" });
                return;
            }
        } else {

            if (!chgFlg) {
                if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H1.getValue(), cMsg.ezUpTimeZone_H1.getValue(), billToCustTMsg.ezUpTime.getValue(), billToCustTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }
            billToCustTMsg.defBaseTpCd.setValue(cMsg.defBaseTpCd_P1.getValue());
            billToCustTMsg.defBaseCycleCd.setValue(cMsg.defBaseCycleCd_P1.getValue());
            billToCustTMsg.defUsgTpCd.setValue(cMsg.defUsgTpCd_P1.getValue());
            billToCustTMsg.defUsgCycleCd.setValue(cMsg.defUsgCycleCd_P1.getValue());

            if (ZYPCommonFunc.hasValue(cMsg.dsBillTgtrFlg_I1)) {
                billToCustTMsg.dsBillTgtrFlg.setValue(cMsg.dsBillTgtrFlg_I1.getValue());
            } else {
                billToCustTMsg.dsBillTgtrFlg.setValue(ZYPConstant.FLG_OFF_N);
            }

            S21FastTBLAccessor.update(billToCustTMsg);
            if (!RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                return;
            }
        }
    }

    private boolean inputCheckForInvBllg(NMAL6730CMsg cMsg) {
        boolean rtnFlg = true;
        String glblCmpyCd = getGlobalCompanyCode();
        int cnt = cMsg.A.getValidCount();
        Map<String, NMAL6730_ACMsg> map = new HashMap<String, NMAL6730_ACMsg>();

        if (ZYPCommonFunc.hasValue(cMsg.defBaseCycleCd_P1)) {
            if (!ZYPCommonFunc.hasValue(cMsg.defBaseTpCd_P1)) {
                String[] args = {"Base Cycle", "Base Adv/Arrears" };
                cMsg.defBaseTpCd_P1.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.defBaseTpCd_P1)) {
            if (!ZYPCommonFunc.hasValue(cMsg.defBaseCycleCd_P1)) {
                String[] args = {"Base Adv/Arrears", "Base Cycle" };
                cMsg.defBaseCycleCd_P1.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.defUsgCycleCd_P1)) {
            if (!ZYPCommonFunc.hasValue(cMsg.defUsgTpCd_P1)) {
                String[] args = {"Usage Cycle", "Usage Adv/Arrears" };
                cMsg.defUsgTpCd_P1.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.defUsgTpCd_P1)) {
            if (!ZYPCommonFunc.hasValue(cMsg.defUsgCycleCd_P1)) {
                String[] args = {"Usage Adv/Arrears", "Usage Cycle" };
                cMsg.defUsgCycleCd_P1.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }

        for (int i = 0; i < cnt; i++) {
            NMAL6730_ACMsg acMsg = cMsg.A.no(i);

            String custInvSrcCd = acMsg.custInvSrcCd_P1.getValue();
            if (map.containsKey(custInvSrcCd)) {
                String[] args = {"Invoice Source" };
                acMsg.custInvSrcCd_P1.setErrorInfo(1, NMAM0072E, args);
                rtnFlg = false;
            } else {
                map.put(custInvSrcCd, acMsg);
            }

            String custBllgVcleCd = acMsg.custBllgVcleCd_P1.getValue();
            if (CUST_BLLG_VCLE.EMAIL_ONLY.equals(custBllgVcleCd) || CUST_BLLG_VCLE.PRINT_AND_EMAIL.equals(custBllgVcleCd)) {
                // QC#7781
                if (NMAL6730CommonLogic.splitByComma(acMsg.xxGenlFldAreaTxt_A2.getValue()).length == 0) {
                    String[] args = {"Bill Vehicle", "EMAIL", "External Email Contact" };
                    acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM8177E, args);
                    rtnFlg = false;
                }
            }

            if (ZYPCommonFunc.hasValue(acMsg.defInvGrpCd_P1)) {
                if (!ZYPCommonFunc.hasValue(acMsg.invGrpNum_A1)) {
                    String[] args = {"Default Grouping", "Invoice Group#" };
                    acMsg.invGrpNum_A1.setErrorInfo(1, NMAM8178E, args);
                    rtnFlg = false;
                }

                if (!CUST_BLLG_TP.CONSOLIDATED.equals(acMsg.custBllgTpCd_P1.getValue())) {
                    String[] args = {ZYPCodeDataUtil.getName(CUST_BLLG_TP.class, glblCmpyCd, CUST_BLLG_TP.CONSOLIDATED), "Default Grouping" };
                    acMsg.custBllgTpCd_P1.setErrorInfo(1, NMAM8179E, args);
                    rtnFlg = false;

                }
            }

            if (CUST_BLLG_TP.CONSOLIDATED.equals(acMsg.custBllgTpCd_P1.getValue())) {
                if (!ZYPCommonFunc.hasValue(acMsg.custConslTermCd_P1)) {
                    String[] args = {"Bill Type", "Consolidated", "Consolidated Term" };
                    acMsg.custConslTermCd_P1.setErrorInfo(1, NMAM8180E, args);
                    rtnFlg = false;
                }
                // QC#6184
                //                if (!ZYPCommonFunc.hasValue(acMsg.defInvGrpCd_P1)) {
                //                    String[] args = {"Bill Type", "Consolidated", "Default Grouping" };
                //                    acMsg.defInvGrpCd_P1.setErrorInfo(1, NMAM8180E, args);
                //                    rtnFlg = false;
                //                }
                if (!ZYPCommonFunc.hasValue(acMsg.invGrpNum_A1)) {
                    String[] args = {"Bill Type", "Consolidated", "Invoice Group#" };
                    acMsg.invGrpNum_A1.setErrorInfo(1, NMAM8180E, args);
                    rtnFlg = false;
                }
            }

            // QC#6659
            if (CUST_BLLG_TP.DAILY.equals(acMsg.custBllgTpCd_P1.getValue())) {
                if (ZYPCommonFunc.hasValue(acMsg.custConslTermCd_P1)) {
                    acMsg.custConslTermCd_P1.setErrorInfo(1, NMAM8409E, new String[] {"Bill Type 'Daily'", "Consolidated Term" });
                    rtnFlg = false;
                }
                if (ZYPCommonFunc.hasValue(acMsg.defInvGrpCd_P1)) {
                    acMsg.defInvGrpCd_P1.setErrorInfo(1, NMAM8409E, new String[] {"Bill Type 'Daily'", "Default Grouping" });
                    rtnFlg = false;
                }
                if (ZYPCommonFunc.hasValue(acMsg.invGrpNum_A1)) {
                    acMsg.invGrpNum_A1.setErrorInfo(1, NMAM8409E, new String[] {"Bill Type 'Daily'", "Invoice Group#" });
                    rtnFlg = false;
                }
            }

            if (ZYPCommonFunc.hasValue(acMsg.custBllgTpCd_P1) && ZYPCommonFunc.hasValue(acMsg.custBllgVcleCd_P1)) {
                CUST_BLLG_VCLETMsg tMsg = new CUST_BLLG_VCLETMsg();
                tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                tMsg.custBllgVcleCd.setValue(acMsg.custBllgVcleCd_P1.getValue());
                tMsg = (CUST_BLLG_VCLETMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    if (CUST_BLLG_TP.CONSOLIDATED.equals(acMsg.custBllgTpCd_P1.getValue())) {
                        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.conslBllgAvalFlg.getValue())) {
                            acMsg.custBllgVcleCd_P1.setErrorInfo(1, NMAM0306E, new String[] {"Bill Type", "Bill Vehicle" });
                            rtnFlg = false;
                        }
                    } else if (CUST_BLLG_TP.DAILY.equals(acMsg.custBllgTpCd_P1.getValue())) {
                        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.dlyBllgAvalFlg.getValue())) {
                            acMsg.custBllgVcleCd_P1.setErrorInfo(1, NMAM0306E, new String[] {"Bill Type", "Bill Vehicle" });
                            rtnFlg = false;
                        }
                    }
                }
            }

            // QC#23604 add Start
            // Check Bill Type and Default Invoice Group relation
            if (!CUST_INV_SRC.SERVICE_CONTRACT.equals(custInvSrcCd)) {
                // START 2018/06/25 U.Kim [QC#26703,MOD]
                // if (DEF_INV_GRP.IB_CONTROL_FIELDS.equals(acMsg.defInvGrpCd_P1.getValue())) {
                //     String[] args = {"Invoce Grouping 'IB CONTROL FIELDS'", "Invoice Source", "not SERVICE CONTRACT" };
                //     acMsg.defInvGrpCd_P1.setErrorInfo(1, NMAM8682E, args);
                //     rtnFlg = false;
                // }
                if (DEF_INV_GRP.IB_CONTROL_FIELDS1.equals(acMsg.defInvGrpCd_P1.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS2.equals(acMsg.defInvGrpCd_P1.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS3.equals(acMsg.defInvGrpCd_P1.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS4.equals(acMsg.defInvGrpCd_P1.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS5.equals(acMsg.defInvGrpCd_P1.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS6.equals(acMsg.defInvGrpCd_P1.getValue())) {
                    String[] args = {"Invoce Grouping 'IB CONTROL FIELDS'", "Invoice Source", "not SERVICE CONTRACT" };
                    acMsg.defInvGrpCd_P1.setErrorInfo(1, NMAM8682E, args);
                    rtnFlg = false;
                }
                // END 2018/06/25 U.Kim [QC#26703,MOD]
            }
            // QC#23604 add End

            // Check same value
            if (ZYPCommonFunc.hasValue(acMsg.invGrpNum_A1)) {
                for (int j = 0; j < cnt; j++) {
                    NMAL6730_ACMsg chkacMsg = cMsg.A.no(j);
                    if (acMsg.invGrpNum_A1.getValue().equals(chkacMsg.invGrpNum_A1.getValue())) {
                        if (!acMsg.custBllgTpCd_P1.getValue().equals(chkacMsg.custBllgTpCd_P1.getValue())) {
                            String[] args = {"Bill Type" };
                            acMsg.custBllgTpCd_P1.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.custBllgTpCd_P1.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }
                        if (!acMsg.custConslTermCd_P1.getValue().equals(chkacMsg.custConslTermCd_P1.getValue())) {
                            String[] args = {"Consolidated Term" };
                            acMsg.custConslTermCd_P1.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.custConslTermCd_P1.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }
                        if (!acMsg.custBllgVcleCd_P1.getValue().equals(chkacMsg.custBllgVcleCd_P1.getValue())) {
                            String[] args = {"Bill Vehicle" };
                            acMsg.custBllgVcleCd_P1.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.custBllgVcleCd_P1.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }
                        // QC#7781
                        if (!acMsg.xxGenlFldAreaTxt_A1.getValue().equals(chkacMsg.xxGenlFldAreaTxt_A1.getValue())) {
                            String[] args = {"Internal Email Review" };
                            acMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }
                        //                        if (ZYPCommonFunc.hasValue(acMsg.ctacPsnPk_A1) && ZYPCommonFunc.hasValue(chkacMsg.ctacPsnPk_A1)) {
                        //                            if (acMsg.ctacPsnPk_A1.getValue().compareTo(chkacMsg.ctacPsnPk_A1.getValue()) != 0) {
                        //                                String[] args = {"External Email Review" };
                        //                                acMsg.ctacPsnPk_A1.setErrorInfo(1, NMAM0291E, args);
                        //                                chkacMsg.ctacPsnPk_A1.setErrorInfo(1, NMAM0291E, args);
                        //                                rtnFlg = false;
                        //                            }
                        //                        } else if (!ZYPCommonFunc.hasValue(acMsg.ctacPsnPk_A1) && ZYPCommonFunc.hasValue(chkacMsg.ctacPsnPk_A1) || ZYPCommonFunc.hasValue(acMsg.ctacPsnPk_A1) && !ZYPCommonFunc.hasValue(chkacMsg.ctacPsnPk_A1)) {
                        //                            String[] args = {"External Email Review" };
                        //                            acMsg.ctacPsnPk_A1.setErrorInfo(1, NMAM0291E, args);
                        //                            chkacMsg.ctacPsnPk_A1.setErrorInfo(1, NMAM0291E, args);
                        //                            rtnFlg = false;
                        //                        }
                        if (!acMsg.xxGenlFldAreaTxt_A2.getValue().equals(chkacMsg.xxGenlFldAreaTxt_A2.getValue())) {
                            String[] args = {"External Email Contact" };
                            acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }

                        if (!acMsg.custEmlMsgTxt_A1.getValue().equals(chkacMsg.custEmlMsgTxt_A1.getValue())) {
                            String[] args = {"Custom Email Subject" };
                            acMsg.custEmlMsgTxt_A1.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.custEmlMsgTxt_A1.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }
                        if (!acMsg.defInvGrpCd_P1.getValue().equals(chkacMsg.defInvGrpCd_P1.getValue())) {
                            String[] args = {"Default Grouping" };
                            acMsg.defInvGrpCd_P1.setErrorInfo(1, NMAM0291E, args);
                            chkacMsg.defInvGrpCd_P1.setErrorInfo(1, NMAM0291E, args);
                            rtnFlg = false;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < cMsg.K.getValidCount(); i++) {
            NMAL6730_KCMsg kcMsg = cMsg.K.no(i);
            if (!ZYPCommonFunc.hasValue(kcMsg.bllgAttrbNm_K1)) {
                //ZZM9000E=0,[@] field is mandatory.
                kcMsg.bllgAttrbNm_K1.setErrorInfo(1, ZZM9000E, new String[] {"Billing Attribute Name" });
                rtnFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(kcMsg.custEffLvlCd_K3)) {
                //ZZM9000E=0,[@] field is mandatory.
                kcMsg.custEffLvlCd_K3.setErrorInfo(1, ZZM9000E, new String[] {"Effective Level" });
                rtnFlg = false;
            }
        }

        return rtnFlg;
    }

    private boolean inputCheckForFinancial(NMAL6730CMsg cMsg) {
        boolean rtnFlg = true;
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.arStmtFlg_F1.getValue()) && !ZYPCommonFunc.hasValue(cMsg.arStmtIssCycleCd_P1)) {

            cMsg.arStmtIssCycleCd_P1.setErrorInfo(1, NMAM0836E, new String[] {"Statement Issue Day" });
            rtnFlg = false;

        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.lateFeeFlg_F1.getValue())) {
            // 2019/05/13 QC#50113 Mod Start
            // if (!ZYPCommonFunc.hasValue(cMsg.lateFeeAmt_F1) || cMsg.lateFeeAmt_F1.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            if (!ZYPCommonFunc.hasValue(cMsg.lateFeeAmt_F1)) {
            // 2019/05/13 QC#50113 Mod End
                cMsg.lateFeeAmt_F1.setErrorInfo(1, NMAM0836E, new String[] {"Minimum Balance to calculate Late Fee" });
                rtnFlg = false;

            }
        }
        // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
        if (ZYPCommonFunc.hasValue(cMsg.mlyLateFeeRate_F1)) {
            BigDecimal mlyMaxlateFeeRate = ZYPCodeDataUtil.getNumConstValue(MLY_MAX_LATE_FEE_RATE, getGlobalCompanyCode());
            if (cMsg.mlyLateFeeRate_F1.getValue().compareTo(BigDecimal.ZERO) < 0 || cMsg.mlyLateFeeRate_F1.getValue().compareTo(mlyMaxlateFeeRate) > 0) {
                cMsg.mlyLateFeeRate_F1.setErrorInfo(1, NMAM8093E, new String[] {"Monthly Late Fee Rate", "0", mlyMaxlateFeeRate.toString() });
                rtnFlg = false;
            }
        }
        // End 2023/2/02 S.Nakatani [QC#60811, ADD]
        // QC#12469
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.custHardHldFlg_F1.getValue())) {
            if (!CR_CHK_REQ_TP.HOLD.equals(cMsg.crChkReqTpCd_P1.getValue())) {
                cMsg.crChkReqTpCd_P1.setErrorInfo(1, NMAM8638E);
                rtnFlg = false;
            }
        }
        return rtnFlg;
    }

    private boolean masterCheckForHeader(NMAL6730CMsg cMsg) {
        boolean rtnFlg = true;

        if (ZYPCommonFunc.hasValue(cMsg.coaChCd_H1) && NMAL6730CommonLogic.findCoaCh(cMsg.glblCmpyCd_H1.getValue(), cMsg.coaChCd_H1.getValue()) == null) {
            cMsg.coaChCd_H1.setErrorInfo(1, NMAM8121E, new String[] {"Sales Channel" });
            rtnFlg = false;
        }

        // QC#9448
        //        if (ZYPCommonFunc.hasValue(cMsg.coaAfflCd_H1) && NMAL6730CommonLogic.findCoaAffl(cMsg.glblCmpyCd_H1.getValue(), cMsg.coaAfflCd_H1.getValue()) == null) {
        //            cMsg.coaAfflCd_H1.setErrorInfo(1, NMAM8121E, new String[] {"Intercompany" });
        //            rtnFlg = false;
        //        }
        //        if (!rtnFlg) {
        //            cMsg.coaAfflCd_H1.setErrorInfo(1, NMAM8121E, new String[] {"Intercompany" });
        //        }

        // QC#19869 GL Code Combination Check
        if(isGLCombinationError(cMsg)){
            rtnFlg = false;
        }

        return rtnFlg;
    }

    /**
     * QC#19869 Add method
     * isGLCombinationError
     * @param cMsg NMAL6730CMsg
     * @return true:Error / false:Success
     */
    private boolean isGLCombinationError(NMAL6730CMsg cMsg) {
        boolean combinationError = false;

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.appFuncId, DEF_DPLY_COA_INFO_APP_FUNC_ID);

        tMsg = (DEF_DPLY_COA_INFOTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.cmbnApiCallFlg.getValue())) {
                NFZC102001 nfzc102001 = new NFZC102001();
                NFZC102001PMsg paramMsg = new NFZC102001PMsg();

                paramMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxArcsApiChkFlg.setValue("");

                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaCmpyDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaCmpyCd, cMsg.coaCmpyCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaAfflDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaAfflCd, cMsg.coaAfflCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaBrDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaBrCd, cMsg.coaBrCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaCcDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaCcCd, cMsg.coaCcCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaAcctDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaAcctCd, cMsg.coaAcctCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaProdDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaProdCd, cMsg.coaProdCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaChDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaChCd, cMsg.coaChCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaProjDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaProjCd, cMsg.coaProjCd_H1);
                }
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.coaExtnDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue( paramMsg.coaExtnCd, cMsg.coaExtnCd_H1);
                }
                paramMsg.resrcObjNm.setValue(DEF_DPLY_COA_INFO_APP_FUNC_ID);

                nfzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    cMsg.setMessageInfo(msgId, msgPrms);

                    if ("E".equals(cMsg.getMessageKind())) {
                        combinationError = true;
                        break;
                    }
                }
            }
        }

        return combinationError;
    }

    private boolean masterCheckForFinancial(NMAL6730CMsg cMsg) {
        boolean rtnFlg = true;

        if (!NMAL6730CommonLogic.getCltCustTpNm(cMsg)) {
            rtnFlg = false;
        }

        if (!NMAL6730CommonLogic.getCltPtfoNm(cMsg)) {
            rtnFlg = false;
        }

        if (!NMAL6730CommonLogic.chkRemId(cMsg)) {
            rtnFlg = false;
        }
        if (!NMAL6730CommonLogic.coaCmbnCheck(cMsg, getGlobalCompanyCode())) {
            rtnFlg = false;
        }

        return rtnFlg;
    }

    private boolean masterCheckForInvBllg(NMAL6730CMsg cMsg) {
        boolean rtnFlg = true;
        int cnt = cMsg.A.getValidCount();

        for (int i = 0; i < cnt; i++) {
            NMAL6730_ACMsg acMsg = cMsg.A.no(i);
            if (!NMAL6730CommonLogic.chkContact(cMsg, acMsg)) {
                rtnFlg = false;
            }
            if (!NMAL6730CommonLogic.chkBillVehicle(cMsg, acMsg)) {
                rtnFlg = false;
            }
            if (!NMAL6730CommonLogic.chkResource(acMsg)) {
                rtnFlg = false;
            }
        }

        return rtnFlg;
    }

    private boolean deleteCustCrPrfl(NMAL6730CMsg cMsg) {
        CUST_CR_PRFLTMsg custCrPrflTMsg = new CUST_CR_PRFLTMsg();
        custCrPrflTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        custCrPrflTMsg.billToCustPk.setValue(cMsg.billToCustPk_H1.getValue());

        custCrPrflTMsg = (CUST_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(custCrPrflTMsg);
        if (custCrPrflTMsg != null) {
            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_F1.getValue(), cMsg.ezUpTimeZone_F1.getValue(), custCrPrflTMsg.ezUpTime.getValue(), custCrPrflTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return false;
            }
            EZDTBLAccessor.logicalRemove(custCrPrflTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(custCrPrflTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {"CUST_CR_PRFL" });
                return false;
            }
        }
        return true;
    }

    private boolean checkInputChange(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        if (!cMsg.coaChCd_H1.getValue().equals(sMsg.coaChCd_H1.getValue())) {
            return true;
        }
        if (!cMsg.ccyCd_P1.getValue().equals(sMsg.ccyCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.custCrRtgCd_P1.getValue().equals(sMsg.custCrRtgCd_P1.getValue())) {
            return true;
        }
        if (!isEqualBigDecimal(cMsg.crLimitAmt_F1, sMsg.crLimitAmt_F1)) {
            return true;
        }
        if (!cMsg.crChkReqTpCd_P1.getValue().equals(sMsg.crChkReqTpCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.crRiskClsCd_P1.getValue().equals(sMsg.crRiskClsCd_P1.getValue())) {
            return true;
        }
        // START 2018/01/26 H.Ikeda [QC#22095,ADD]
        if (!cMsg.contrCrRiskClsCd_P1.getValue().equals(sMsg.contrCrRiskClsCd_P1.getValue())) {
            return true;
        }
        // END 2018/01/26 H.Ikeda [QC#22095,ADD]
        if (!cMsg.pmtTermCashDiscCd_P1.getValue().equals(sMsg.pmtTermCashDiscCd_P1.getValue())) {
            return true;
        }
        if (!isEqualFlag(cMsg.ovrdPmtTermFlg_F1, sMsg.ovrdPmtTermFlg_F1)) {
            return true;
        }
        if (!isEqualFlag(cMsg.cashOrCcReqFlg_F1, sMsg.cashOrCcReqFlg_F1)) {
            return true;
        }
        // QC#4324
        if (!isEqualFlag(cMsg.custHardHldFlg_F1, sMsg.custHardHldFlg_F1)) {
            return true;
        }
        if (!cMsg.remId_F1.getValue().equals(sMsg.remId_F1.getValue())) {
            return true;
        }
        if (!isEqualFlag(cMsg.arStmtFlg_F1, sMsg.arStmtFlg_F1)) {
            return true;
        }

        // START 2018/04/20 [QC#23882, ADD]
        if (!isEqualFlag(cMsg.sendCrBalStmtFlg_F1, sMsg.sendCrBalStmtFlg_F1)) {
             return true;
         }
        // END   2018/04/20 [QC#23882, ADD]

        if (!cMsg.arStmtIssCycleCd_P1.getValue().equals(sMsg.arStmtIssCycleCd_P1.getValue())) {
            return true;
        }
        // START 2018/01/16 [QC#21734, DEL]
        // if (!isEqualFlag(cMsg.dunFlg_F1, sMsg.dunFlg_F1)) {
        //     return true;
        // }
        // END   2018/01/16 [QC#21734, DEL]
        if (!cMsg.cltCustTpCd_F1.getValue().equals(sMsg.cltCustTpCd_F1.getValue())) {
            return true;
        }
        if (!cMsg.cltPtfoCd_F1.getValue().equals(sMsg.cltPtfoCd_F1.getValue())) {
            return true;
        }
        if (!cMsg.dsCltAcctStsCd_P1.getValue().equals(sMsg.dsCltAcctStsCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.dsCltAcctStsCd_P1.getValue().equals(sMsg.dsCltAcctStsCd_P1.getValue())) {
            return true;
        }
        if (!isEqualFlag(cMsg.lateFeeFlg_F1, sMsg.lateFeeFlg_F1)) {
            return true;
        }
        if (!isEqualBigDecimal(cMsg.lateFeeAmt_F1, sMsg.lateFeeAmt_F1)) {
            return true;
        }
        // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
        if (!isEqualBigDecimal(cMsg.mlyLateFeeRate_F1, sMsg.mlyLateFeeRate_F1)) {
            return true;
        }
        // End 2023/2/02 S.Nakatani [QC#60811, ADD]
        // Del Start 2018/08/01 QC#27222
//        if (!cMsg.dsCustTaxCd_F1.getValue().equals(sMsg.dsCustTaxCd_F1.getValue())) {
//            return true;
//        }
//        if (!cMsg.dsCustTaxCalcCd_P1.getValue().equals(sMsg.dsCustTaxCalcCd_P1.getValue())) {
//            return true;
//        }
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        if (!isEqualFlag(cMsg.dsTaxExemFlg_F1, sMsg.dsTaxExemFlg_F1)) {
            return true;
        }
        if (!cMsg.dsExemExprDt_F1.getValue().equals(sMsg.dsExemExprDt_F1.getValue())) {
            return true;
        }
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/08/01 QC#27222
        if (!cMsg.dsTaxGrpExemCd_P1.getValue().equals(sMsg.dsTaxGrpExemCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.dsTaxPrntTpCd_P1.getValue().equals(sMsg.dsTaxPrntTpCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.defBaseTpCd_P1.getValue().equals(sMsg.defBaseTpCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.defBaseCycleCd_P1.getValue().equals(sMsg.defBaseCycleCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.defUsgTpCd_P1.getValue().equals(sMsg.defUsgTpCd_P1.getValue())) {
            return true;
        }
        if (!cMsg.defUsgCycleCd_P1.getValue().equals(sMsg.defUsgCycleCd_P1.getValue())) {
            return true;
        }
        if (!isEqualFlag(cMsg.dsBillTgtrFlg_I1, sMsg.dsBillTgtrFlg_I1)) {
            return true;
        }

        if (cMsg.A.getValidCount() != sMsg.C.getValidCount()) {
            return true;
        }
        // START 2018/04/17 E.Kameishi [QC#21037, ADD]
        if (!cMsg.autoCashHrchCd_P1.getValue().equals(sMsg.autoCashHrchCd_P1.getValue())) {
            return true;
        }
        // END 2018/04/17 E.Kameishi [QC#21037, ADD]
        NMAL6730_ACMsg aCMsg;
        NMAL6730_CSMsg cSMsg;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            aCMsg = cMsg.A.no(i);
            cSMsg = sMsg.C.no(i);
            if (!isEqualBigDecimal(aCMsg.dsCustInvRulePk_A1, cSMsg.dsCustInvRulePk_A1)) {
                return true;
            }
            if (!aCMsg.custInvSrcCd_P1.getValue().equals(cSMsg.custInvSrcCd_P1.getValue())) {
                return true;
            }
            if (!aCMsg.custBllgTpCd_P1.getValue().equals(cSMsg.custBllgTpCd_P1.getValue())) {
                return true;
            }
            if (!aCMsg.custConslTermCd_P1.getValue().equals(cSMsg.custConslTermCd_P1.getValue())) {
                return true;
            }
            if (!aCMsg.custBllgVcleCd_P1.getValue().equals(cSMsg.custBllgVcleCd_P1.getValue())) {
                return true;
            }
            // QC#7781
            if (!aCMsg.xxGenlFldAreaTxt_A1.getValue().equals(cSMsg.xxGenlFldAreaTxt_A1.getValue())) {
                return true;
            }
            //            if (!isEqualBigDecimal(aCMsg.ctacPsnPk_A1, cSMsg.ctacPsnPk_A1)) {
            //                return true;
            //            }
            if (!aCMsg.xxGenlFldAreaTxt_A2.getValue().equals(cSMsg.xxGenlFldAreaTxt_A2.getValue())) {
                return true;
            }
            if (!aCMsg.custEmlMsgTxt_A1.getValue().equals(cSMsg.custEmlMsgTxt_A1.getValue())) {
                return true;
            }
            if (!aCMsg.defInvGrpCd_P1.getValue().equals(cSMsg.defInvGrpCd_P1.getValue())) {
                return true;
            }
            if (!aCMsg.invGrpNum_A1.getValue().equals(cSMsg.invGrpNum_A1.getValue())) {
                return true;
            }
        }

        if (sMsg.T.getValidCount() > 0) {
            return true;
        }
        for (int i = 0; i < cMsg.K.getValidCount(); i++) {
            NMAL6730_KCMsg kCMsg = cMsg.K.no(i);
            NMAL6730_KSMsg kSMsg = sMsg.K.no(i);
            if (!isEqualBigDecimal(kCMsg.dsAcctRefAttrbPk_K1, kSMsg.dsAcctRefAttrbPk_K1)) {
                return true;
            }
            if (!kCMsg.bllgAttrbNm_K1.getValue().equals(kSMsg.bllgAttrbNm_K1.getValue())) {
                return true;
            }
            if (!kCMsg.bllgAttrbValTxt_K1.getValue().equals(kSMsg.bllgAttrbValTxt_K1.getValue())) {
                return true;
            }
            if (!isEqualFlag(kCMsg.bllgAttrbEnblFlg_K1, kSMsg.bllgAttrbEnblFlg_K1)) {
                return true;
            }
            if (!isEqualFlag(kCMsg.bllgAttrbReqFlg_K1, kSMsg.bllgAttrbReqFlg_K1)) {
                return true;
            }
            if (!kCMsg.custEffLvlCd_K3.getValue().equals(kSMsg.custEffLvlCd_K3.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqualBigDecimal(EZDCBigDecimalItem item1, EZDSBigDecimalItem item2) {

        if (ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return false;
        } else if (!ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2) && item1.getValue().compareTo(item2.getValue()) != 0) {
            return false;
        }
        return true;
    }

    private boolean isEqualFlag(EZDCStringItem item1, EZDSStringItem item2) {

        String flg1 = "N";
        String flg2 = "N";
        if (ZYPCommonFunc.hasValue(item1)) {
            flg1 = item1.getValue();
        }
        if (ZYPCommonFunc.hasValue(item2)) {
            flg2 = item2.getValue();
        }

        if (!flg1.equals(flg2)) {
            return false;
        }
        return true;
    }

    /**
     * @param bizMsg NMAL6730CMsg
     * @param sharedMsg NMAL6730SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean createOrUpdateDsAcctRefAttrb(NMAL6730CMsg bizMsg, NMAL6730SMsg sharedMsg, String glblCmpyCd) {

        // Delete DS_ACCT_REF_ATTRB
        int cnt = sharedMsg.T.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6730_TSMsg tsMsg = sharedMsg.T.no(i);
            DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg = NMAL6730CommonLogic.findDsAcctRefAttrbForUpdate(glblCmpyCd, tsMsg.dsAcctRefAttrbPk_T1.getValue());
            if (dsAcctRefAttrbTMsg == null) {
                String[] args = {"DS_ACCT_REF_ATTRB" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(tsMsg.ezUpTime_T1.getValue(), tsMsg.ezUpTimeZone_T1.getValue(), dsAcctRefAttrbTMsg.ezUpTime.getValue(), dsAcctRefAttrbTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsAcctRefAttrbTMsg);
            if (!RTNCD_NORMAL.equals(dsAcctRefAttrbTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_REF_ATTRB" });
                return false;
            }
        }
        cnt = bizMsg.K.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6730_KCMsg kcMsg = bizMsg.K.no(i);
            if (ZYPCommonFunc.hasValue(kcMsg.dsAcctRefAttrbPk_K1)) {

                DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg = NMAL6730CommonLogic.findDsAcctRefAttrbForUpdate(glblCmpyCd, kcMsg.dsAcctRefAttrbPk_K1.getValue());
                if (dsAcctRefAttrbTMsg == null) {
                    String[] args = {"DS_ACCT_REF_ATTRB" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(kcMsg.ezUpTime_K1.getValue(), kcMsg.ezUpTimeZone_K1.getValue(), dsAcctRefAttrbTMsg.ezUpTime.getValue(), dsAcctRefAttrbTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_ACCT_REF_ATTRBTMsg beforeDsAcctRefAttrbTMsg = new DS_ACCT_REF_ATTRBTMsg();
                DS_ACCT_REF_ATTRBTMsg.copy(dsAcctRefAttrbTMsg, null, beforeDsAcctRefAttrbTMsg, null);
                setDsAcctRefAttrbData(dsAcctRefAttrbTMsg, bizMsg, kcMsg, false);
                //Change check
                if (changeFieldsCheckForDsAcctRefAttrb(dsAcctRefAttrbTMsg, beforeDsAcctRefAttrbTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsAcctRefAttrbTMsg);
                    if (!RTNCD_NORMAL.equals(dsAcctRefAttrbTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_REF_ATTRB" });
                        return false;
                    }

                }

            } else {
                DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg = new DS_ACCT_REF_ATTRBTMsg();
                setDsAcctRefAttrbData(dsAcctRefAttrbTMsg, bizMsg, kcMsg, true);

                S21FastTBLAccessor.create(dsAcctRefAttrbTMsg);
                if (!RTNCD_NORMAL.equals(dsAcctRefAttrbTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_REF_ATTRB" });
                    return false;
                }
            }
        }

        return true;
    }

    private void setDsAcctRefAttrbData(DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg, NMAL6730CMsg bizMsg, NMAL6730_KCMsg kcMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.dsAcctRefAttrbPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_REF_ATTRB_SQ));
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.locNum, bizMsg.locNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbNm, kcMsg.bllgAttrbNm_K1);
        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbValTxt, kcMsg.bllgAttrbValTxt_K1);
        if (ZYPConstant.FLG_ON_Y.equals(kcMsg.bllgAttrbEnblFlg_K1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbEnblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbEnblFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(kcMsg.bllgAttrbReqFlg_K1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbReqFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.custEffLvlCd, kcMsg.custEffLvlCd_K3);
        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.dsAcctRefAttrbNum, kcMsg.dsAcctRefAttrbNum_K1);
    }

    private boolean changeFieldsCheckForDsAcctRefAttrb(DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg, DS_ACCT_REF_ATTRBTMsg beforeDsAcctRefAttrbTMsg) {

        if (isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbNm.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbNm.getValue()) || isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbValTxt.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbValTxt.getValue())
                || isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbEnblFlg.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbEnblFlg.getValue())
                || isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbReqFlg.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbReqFlg.getValue()) || isNotEquals(dsAcctRefAttrbTMsg.custEffLvlCd.getValue(), beforeDsAcctRefAttrbTMsg.custEffLvlCd.getValue())
                || isNotEquals(dsAcctRefAttrbTMsg.dsAcctRefAttrbNum.getValue(), beforeDsAcctRefAttrbTMsg.dsAcctRefAttrbNum.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isNotEquals(String orig, String backUp) {
        if (!nvl(orig).equals(nvl(backUp))) {
            return true;
        }
        return false;
    }

    private String nvl(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }

    // START 2017/09/07 J.Kim [QC#20495,ADD]
    private boolean isEqualBigDecimal(EZDCBigDecimalItem item1, EZDTBigDecimalItem item2) {

        if (ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return false;
        } else if (!ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2) && item1.getValue().compareTo(item2.getValue()) != 0) {
            return false;
        }
        return true;
    }

    private BigDecimal add(BigDecimal big1, BigDecimal big2) {
        if (big1 == null) {
            big1 = BigDecimal.ZERO;
        }
        if (big2 == null) {
            big2 = BigDecimal.ZERO;
        }
        return big1.add(big2);
    }

    private BigDecimal subtract(BigDecimal big1, BigDecimal big2) {
        if (big1 == null) {
            big1 = BigDecimal.ZERO;
        }
        if (big2 == null) {
            big2 = BigDecimal.ZERO;
        }
        return big1.subtract(big2);
    }
    // END 2017/09/07 J.Kim [QC#20495,ADD]
}
