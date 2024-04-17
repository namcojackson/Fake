/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1660.common;

import static business.blap.NWAL1660.constant.NWAL1660Constant.LEN_ORD_TAKE_MDSE;
import static business.blap.NWAL1660.constant.NWAL1660Constant.MODE_REFERENCE;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0325E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0557E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0631E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0732E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0733E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0734E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0735E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.NWAM0736E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.TOT_WT_SCALE;
import static business.blap.NWAL1660.constant.NWAL1660Constant.ZZM9000E;
import static business.blap.NWAL1660.constant.NWAL1660Constant.TAX_JURISDICTION_ST;
import static business.blap.NWAL1660.constant.NWAL1660Constant.TAX_JURISDICTION_CNTY;
import static business.blap.NWAL1660.constant.NWAL1660Constant.TAX_JURISDICTION_CTY;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWAL1660.NWAL1660CMsg;
import business.blap.NWAL1660.NWAL1660Query;
import business.blap.NWAL1660.NWAL1660_ACMsg;
import business.blap.NWAL1660.NWAL1660_ACMsgArray;
import business.blap.NWAL1660.NWAL1660_BCMsg;
import business.blap.NWAL1660.NWAL1660_BCMsgArray;
import business.blap.NWAL1660.NWAL1660_CCMsg;
import business.blap.NWAL1660.NWAL1660_CCMsgArray;
import business.blap.NWAL1660.NWAL1660_LCMsg;
import business.blap.NWAL1660.NWAL1660_LCMsgArray;
import business.blap.NWAL1660.constant.NWAL1660Constant;
import business.db.CCYTMsg;
import business.db.FRT_CONDTMsg;
import business.db.PRC_RULE_ADJ_TPTMsg;
import business.parts.NWXC014001PMsg;
import business.parts.NWXC014002PMsg;
import business.parts.NWXC014004PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC170001PMsg;
import business.parts.NWZC170001_xxMsgIdListPMsg;

import com.canon.cusa.s21.api.NWX.NWXC014001.NWXC014001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC170001.NWZC170001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1660CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 * 2015/12/16   Fujitsu         Y.Kanefusa      Update          #1090
 * 2016/01/12   Fujitsu         M.Nakamura      Update          #2998
 * 2016/03/01   Fujitsu         M.Nakamura      Update          #4375
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/08/23   Fujitsu         Y.Kanefusa      Update          S21_NA#6098
 * 2016/09/01   Fujitsu         Y.Kanefusa      Update          S21_NA#14031
 * 2016/09/07   Fujitsu         R.Nakamura      Update          #11614
 * 2016/10/18   Fujitsu         Y.Kanefusa      Update          S21_NA#15308
 * 2017/08/24   Fujitsu         H.Ikeda         Update          QC#20655
 * 2017/09/27   CITS            T.Tokutomi      Update          QC#21396
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/05/23   Fujitsu         N.Sugiura       Update          S21_NA#21841(Sol#495)
 * 2018/06/07   Fujitsu         Y.Kanefusa      Update          S21_NA#22965-2
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2019/07/29   Fujitsu         R.Nakamura      Update          S21_NA#51742
 *</pre>
 */
public class NWAL1660CommonLogic {

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    public static void createPullDownList(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        NWXC014001PMsg pMsg = new NWXC014001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_PM, NWXC014001.PROCESS_MODE_MANUAL);
        // QC#22965-2 2018/04/11 Add Start
        BigDecimal xxTotUnitNetWt = BigDecimal.ZERO;
        if(NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())){
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_PL, NWXC014001.PROCESS_LVL_HEADER);
            xxTotUnitNetWt = bizMsg.xxTotUnitNetWt_P.getValue();
        }else{
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_PL, NWXC014001.PROCESS_LVL_LINE);
            xxTotUnitNetWt = getTotWt(glblCmpyCd, bizMsg);
        }
        // QC#22965-2 2018/04/11 Add End
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_MT, NWXC014001.MODIFY_TYPE_ALL);

        // QC#9437 2016/06/21 Add Start
        // ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, ZYPDateUtil.getSalesDate());
        // QC#9437 2016/06/21 Add End
        ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, bizMsg.prcCalcDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcRuleTrxCatgCd, PRC_RULE_TRX_CATG.ORDER);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd_P);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, bizMsg.custIssPoNum);
        //ZYPEZDItemValueSetter.setValue(pMsg.xxTotUnitNetWt, getTotWt(glblCmpyCd, bizMsg));
        ZYPEZDItemValueSetter.setValue(pMsg.xxTotUnitNetWt, xxTotUnitNetWt);
        ZYPEZDItemValueSetter.setValue(pMsg.totQty, bizMsg.inEachQty_P);
        ZYPEZDItemValueSetter.setValue(pMsg.dsPmtMethCd, bizMsg.dsPmtMethCd);
        ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);

        NWXC014002PMsg p2Msg = pMsg.NWXC014002PMsg.no(0);
        ZYPEZDItemValueSetter.setValue(p2Msg.trxLineNum, bizMsg.trxLineNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.trxLineSubNum, bizMsg.trxLineSubNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.configCatgCd, bizMsg.configCatgCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.prcBaseDt, bizMsg.prcBaseDt); // QC#9437 2016/06/21 Add
        ZYPEZDItemValueSetter.setValue(p2Msg.shipToCustCd, bizMsg.shipToCustCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsAcctNum_SH, bizMsg.shipToCustAcctCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsAcctNum_BL, bizMsg.billToCustAcctCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.prcCatgCd, bizMsg.prcCatgCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ccyCd, bizMsg.ccyCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.csmpNum, bizMsg.csmpNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.dlrRefNum, bizMsg.dlrRefNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.prcContrNum, bizMsg.prcContrNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.coaBrCd, bizMsg.coaBrCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.mdseCd, bizMsg.mdseCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.pkgUomCd, bizMsg.uomCd_P);
        BigDecimal unitPrcAmt = getUnitPrcAmt(bizMsg);
        if (!ZYPCommonFunc.hasValue(bizMsg.inEachQty_P.getValue())) {
            bizMsg.inEachQty_P.setValue(BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(p2Msg.entCpoDtlDealSlsAmt //
                , unitPrcAmt.multiply(bizMsg.inEachQty_P.getValue()));
        ZYPEZDItemValueSetter.setValue(p2Msg.entDealNetUnitPrcAmt, unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsOrdLineCatgCd, bizMsg.dsCpoLineCatgCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ordQty, bizMsg.ordCustUomQty_P);
        if (!ZYPCommonFunc.hasValue(p2Msg.ordQty.getValue())) {
            p2Msg.ordQty.setValue(BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(p2Msg.invQty, bizMsg.invQty_P);
        if (!ZYPCommonFunc.hasValue(p2Msg.invQty.getValue())) {
            p2Msg.invQty.setValue(BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(p2Msg.unitNetWt, getTotWt(glblCmpyCd, bizMsg));
        ZYPEZDItemValueSetter.setValue(p2Msg.rtrnRsnCd, bizMsg.rtrnRsnCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.mdlId, bizMsg.mdlId_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.frtCondCd, bizMsg.frtCondCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.stCd, bizMsg.shipToStCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ctryCd, bizMsg.shipToCtryCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.postCd, bizMsg.shipToPostCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd_P);

        FRT_CONDTMsg fcTMsg = getFrtCond(glblCmpyCd, bizMsg.frtCondCd_P.getValue());
        if (fcTMsg != null) {
            ZYPEZDItemValueSetter.setValue(p2Msg.frtChrgToCd, fcTMsg.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(p2Msg.frtChrgMethCd, fcTMsg.frtChrgMethCd);
        }

        ZYPEZDItemValueSetter.setValue(p2Msg.coaExtnCd, bizMsg.coaExtnCd_P);

        if (MODE_REFERENCE.equals(bizMsg.xxModeCd.getValue()) //
                || BigDecimal.ZERO.compareTo(bizMsg.invQty_P.getValue()) < 0) {
            ZYPEZDItemValueSetter.setValue(p2Msg.xxPrcCloFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(p2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        }
        pMsg.NWXC014002PMsg.setValidCount(1);
        EZDMsg.copy(bizMsg.L, "PL", p2Msg.NWXC014003PMsg, "");

        new NWXC014001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            bizMsg.setMessageInfo(//
                    pMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
            return;
        }
        if (S21ApiUtil.isXxMsgId(pMsg.NWXC014002PMsg.no(0))) {
            bizMsg.setMessageInfo(//
                    pMsg.NWXC014002PMsg.no(0).xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {pMsg.NWXC014002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , pMsg.NWXC014002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , pMsg.NWXC014002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
            return;
        }

        int ixA = 0;
        int ixB = 0;
        for (int i = 0; i < pMsg.NWXC014004PMsg.getValidCount(); i++) {
            NWXC014004PMsg ruleMsg = pMsg.NWXC014004PMsg.no(i);
            if (PRC_RULE_ADJ_TP.SET_PRICE.equals(ruleMsg.prcRuleAdjTpCd.getValue())) {
                continue;
            }
            if (PRC_FMLA_TP.CUSTOM_PRICE.equals(ruleMsg.prcFmlaTpCd.getValue())) {
                continue;
            }

            if (PRC_RULE_MOD_TP.CHARGES.equals(ruleMsg.prcRuleModTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.specCondPrcPk_BL.no(ixB), ruleMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleDtlTxt_BL.no(ixB), ruleMsg.prcRuleDtlChrgNm);
                ixB++;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.specCondPrcPk_AL.no(ixA), ruleMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleDtlTxt_AL.no(ixA), ruleMsg.prcRuleDtlChrgNm);
                ixA++;
            }
        }
    }

    private static BigDecimal getUnitPrcAmt(NWAL1660CMsg bizMsg) {
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWAL1660_LCMsg lBizMsg = bizMsg.L.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(lBizMsg.prcDtlGrpCd_PL.getValue())) {
                return lBizMsg.unitPrcAmt_PL.getValue();
            }
        }
        return BigDecimal.ZERO;
    }

    private static FRT_CONDTMsg getFrtCond(String glblCmpyCd, String frtCondCd) {
        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, frtCondCd);

        return (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    private static BigDecimal getTotWt(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getInPondWt(glblCmpyCd, bizMsg.mdseCd_P.getValue());
        if (!rslt.isCodeNormal()) {
            return BigDecimal.ZERO;
        }
        BigDecimal wt = (BigDecimal) rslt.getResultObject();
        if (!ZYPCommonFunc.hasValue(bizMsg.inEachQty_P)) {
            return BigDecimal.ZERO;
        }
        return bizMsg.inEachQty_P.getValue().multiply(wt).setScale(TOT_WT_SCALE, RoundingMode.HALF_UP);
    }

    // 2017/08/24 S21_NA#20655 ADD Start
    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param bizMsg        NWAL1660CMsg
     * @param smryLineFlg   String
     * </pre>
     */
    public static void getPriceInfoDel(String glblCmpyCd, NWAL1660CMsg bizMsg, String smryLineFlg, List<BigDecimal> delIdRows) {
        // set item from Screen to calcBase;
        setBMsgToCalcPrice(glblCmpyCd, bizMsg);

        NWZC157001PMsg pMsg = createPricingApiPMsg(glblCmpyCd, bizMsg, smryLineFlg);
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            bizMsg.setMessageInfo(//
                    pMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
        }
        if (S21ApiUtil.isXxMsgId(pMsg.NWZC157002PMsg.no(0))) {
            bizMsg.setMessageInfo(//
                    pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
        }
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        BigDecimal charges;
        if (pMsg.NWZC157004PMsg.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, pMsg.NWZC157004PMsg.no(0).xxTotNetPrcAmt.getValue());
            if (ZYPCommonFunc.hasValue(pMsg.NWZC157004PMsg.no(0).xxTotSpclChrgAmt.getValue())) {
                charges = pMsg.NWZC157004PMsg.no(0).xxTotFrtAmt.getValue().subtract(pMsg.NWZC157004PMsg.no(0).xxTotSpclChrgAmt.getValue());
            } else {
                charges = pMsg.NWZC157004PMsg.no(0).xxTotFrtAmt.getValue();
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, charges);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, pMsg.NWZC157004PMsg.no(0).xxTotTaxAmt.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxLineTotPrcAmt, pMsg.NWZC157004PMsg.no(0).xxTotAmt.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotBaseAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotDiscAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotSpclPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotSpclPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotNetDiscAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotNetDiscAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitGrsPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotNetPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxGrsAmt_P, pMsg.NWZC157004PMsg.no(0).xxGrsAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitFrtAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitFrtAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotFrtAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitFrtAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitSpclChrgAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotSpclChrgAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotFrtSubAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotFrtSubAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitRestkFeeAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitRestkFeeAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotRestkFeeAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotRestkFeeAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotNetPrcAmt_P2, pMsg.NWZC157004PMsg.no(0).xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitPrc_P, pMsg.NWZC157004PMsg.no(0).xxTotAmt);
        }
        if (delIdRows == null || delIdRows.size() == 0) {
            editBizMsgPriceInfo(glblCmpyCd, bizMsg, pMsg);
        } else {
            editBizMsgPriceInfoDel(glblCmpyCd, bizMsg, pMsg, delIdRows);
        }
        editOtherBizMsg(bizMsg, pMsg);
    }
    // 2017/08/24 S21_NA#20655 ADD End

    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param bizMsg        NWAL1660CMsg
     * @param smryLineFlg   String
     * </pre>
     */
    public static void getPriceInfo(String glblCmpyCd, NWAL1660CMsg bizMsg, String smryLineFlg) {
        // set item from Screen to calcBase;
        setBMsgToCalcPrice(glblCmpyCd, bizMsg);

        NWZC157001PMsg pMsg = createPricingApiPMsg(glblCmpyCd, bizMsg, smryLineFlg);
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            bizMsg.setMessageInfo(//
                    pMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
        }
        if (S21ApiUtil.isXxMsgId(pMsg.NWZC157002PMsg.no(0))) {
            bizMsg.setMessageInfo(//
                    pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , pMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
        }
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        BigDecimal charges;
        if (pMsg.NWZC157004PMsg.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, pMsg.NWZC157004PMsg.no(0).xxTotNetPrcAmt.getValue());
            if (ZYPCommonFunc.hasValue(pMsg.NWZC157004PMsg.no(0).xxTotSpclChrgAmt.getValue())) {
                charges = pMsg.NWZC157004PMsg.no(0).xxTotFrtAmt.getValue().subtract(pMsg.NWZC157004PMsg.no(0).xxTotSpclChrgAmt.getValue());
            } else {
                charges = pMsg.NWZC157004PMsg.no(0).xxTotFrtAmt.getValue();
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, charges);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, pMsg.NWZC157004PMsg.no(0).xxTotTaxAmt.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxLineTotPrcAmt, pMsg.NWZC157004PMsg.no(0).xxTotAmt.getValue());
            // Add 2016/03/01 QC#4375 Start
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotBaseAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotDiscAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotSpclPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotSpclPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotNetDiscAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotNetDiscAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitGrsPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotNetPrcAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxGrsAmt_P, pMsg.NWZC157004PMsg.no(0).xxGrsAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitFrtAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitFrtAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotFrtAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitFrtAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitSpclChrgAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotSpclChrgAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotFrtSubAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotFrtSubAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitRestkFeeAmt_P, pMsg.NWZC157004PMsg.no(0).xxUnitRestkFeeAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotRestkFeeAmt_P, pMsg.NWZC157004PMsg.no(0).xxTotRestkFeeAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotNetPrcAmt_P2, pMsg.NWZC157004PMsg.no(0).xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitPrc_P, pMsg.NWZC157004PMsg.no(0).xxTotAmt);
            // Add 2016/03/01 QC#4375 End

        }
        editBizMsgPriceInfo(glblCmpyCd, bizMsg, pMsg);
        editOtherBizMsg(bizMsg, pMsg);
    }

    // QC#22965 2018/04/11 Add Start
    public static void setBMsgToCalcPriceForHeader(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        NWAL1660_LCMsg lBizMsg = null;
        List<NWAL1660_LCMsg> changeList = new ArrayList<NWAL1660_LCMsg>();

        BigDecimal netPrice = getUnitPrcAmt(bizMsg);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(aBizMsg.applyAutoFlg_A.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(aBizMsg.ovrdFlg_A.getValue())) {
                lBizMsg = getLBizMsg(bizMsg.L, aBizMsg.specCondPrcPk_A.getValue());
                if (lBizMsg == null) {
                    lBizMsg = bizMsg.L.no(bizMsg.L.getValidCount());
                    bizMsg.L.setValidCount(bizMsg.L.getValidCount() + 1);
                } else{
                    if(compareData(lBizMsg, aBizMsg)){
                        continue;
                    }
                }
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, PRC_DTL_GRP.DISCOUNT);
                // QC#22965-2 2018/06/05 Add Start
                if (ZYPConstant.FLG_OFF_N.equals(aBizMsg.applyAutoFlg_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.prcCondManDelFlg_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                // QC#22965-2 2018/06/05 Add End
                if (!ZYPCommonFunc.hasValue(lBizMsg.autoPrcAmtRate_PL)) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.PCT.equals(aBizMsg.prcCondUnitCd_A.getValue()) 
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), divide(aBizMsg.manPrcAmtRate_A.getValue(), NWAL1660Constant.PCT_100))){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.AMT.equals(aBizMsg.prcCondUnitCd_A.getValue())
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), aBizMsg.unitPrcAmt_A.getValue())){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManDelFlg_PL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                // QC#22965-2 2018/06/05 Del Start
                //if (ZYPConstant.FLG_OFF_N.equals(aBizMsg.applyAutoFlg_A.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_ON_Y);
                //} else {
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_OFF_N);
                //}
                //if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.prcCondManDelFlg_A.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                //}else{
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                //}
                // QC#22965-2 2018/06/05 Del End
                ZYPEZDItemValueSetter.setValue(lBizMsg.pkgUomCd_PL, bizMsg.uomCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondUnitCd_PL, aBizMsg.prcCondUnitCd_A);
                if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManEntryFlg_PL.getValue())) {
                    if (PRC_COND_UNIT.PCT.equals(aBizMsg.prcCondUnitCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, divide(aBizMsg.manPrcAmtRate_A.getValue(), NWAL1660Constant.PCT_100));
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, BigDecimal.ZERO);
                    } else {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, aBizMsg.unitPrcAmt_A.getValue());
                    }
                }
                ZYPEZDItemValueSetter.setValue(lBizMsg.autoPrcCcyCd_PL, bizMsg.ccyCd.getValue());
                ZYPEZDItemValueSetter.setValue(lBizMsg.specCondPrcPk_PL, aBizMsg.specCondPrcPk_A.getValue());
                if(ZYPCommonFunc.hasValue(lBizMsg.unitPrcAmt_PL)){
                    netPrice = netPrice.subtract(lBizMsg.unitPrcAmt_PL.getValue());
                }

                NWAL1660_LCMsg data = new NWAL1660_LCMsg();
                EZDMsg.copy(lBizMsg, "PL", data, "PL");
                changeList.add(data);
            }
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(bBizMsg.applyAutoFlg_B.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(bBizMsg.ovrdFlg_B.getValue())) {
                lBizMsg = getLBizMsg(bizMsg.L, bBizMsg.specCondPrcPk_B.getValue());
                if (lBizMsg == null) {
                    lBizMsg = bizMsg.L.no(bizMsg.L.getValidCount());
                    bizMsg.L.setValidCount(bizMsg.L.getValidCount() + 1);
                }else{
                    if(compareData(lBizMsg, bBizMsg)){
                        continue;
                    }
                }
                //ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, PRC_DTL_GRP.FREIGHT);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, getPrcDtlGrpCd(glblCmpyCd, bBizMsg.prcRuleAdjTpCd_B.getValue()));
                // QC#22965 2018/06/05 Add Start
                if (ZYPConstant.FLG_OFF_N.equals(bBizMsg.applyAutoFlg_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.prcCondManDelFlg_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                // QC#22965 2018/06/05 Add End
                if (!ZYPCommonFunc.hasValue(lBizMsg.autoPrcAmtRate_PL)) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.PCT.equals(bBizMsg.prcCondUnitCd_B.getValue()) 
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), divide(bBizMsg.manPrcAmtRate_B.getValue(), NWAL1660Constant.PCT_100))){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.AMT.equals(bBizMsg.prcCondUnitCd_B.getValue())
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), bBizMsg.unitPrcAmt_B.getValue())){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManDelFlg_PL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                // QC#22965 2018/06/05 Del Start
                //if (ZYPConstant.FLG_OFF_N.equals(bBizMsg.applyAutoFlg_B.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_ON_Y);
                //} else {
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_OFF_N);
                //}
                ////if(isNewFreightData){
                ////    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                ////} 
                //if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.prcCondManDelFlg_B.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                //}else{
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                //}
                // QC#22965 2018/06/05 Del End
                ZYPEZDItemValueSetter.setValue(lBizMsg.pkgUomCd_PL, bizMsg.uomCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondUnitCd_PL, bBizMsg.prcCondUnitCd_B);
                if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManEntryFlg_PL.getValue())) {
                    if (PRC_COND_UNIT.PCT.equals(bBizMsg.prcCondUnitCd_B.getValue())) {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, divide(bBizMsg.manPrcAmtRate_B.getValue(), NWAL1660Constant.PCT_100));
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, BigDecimal.ZERO);
                    } else {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, bBizMsg.unitPrcAmt_B.getValue());
                    }
                }
                ZYPEZDItemValueSetter.setValue(lBizMsg.autoPrcCcyCd_PL, bizMsg.ccyCd.getValue());
                ZYPEZDItemValueSetter.setValue(lBizMsg.specCondPrcPk_PL, bBizMsg.specCondPrcPk_B.getValue());

                NWAL1660_LCMsg data = new NWAL1660_LCMsg();
                EZDMsg.copy(lBizMsg, "PL", data, "PL");
                changeList.add(data);
            }

        }

        // Set Parameters
        ZYPTableUtil.clear(bizMsg.L);
        int i = 0;
        for (NWAL1660_LCMsg data : changeList) {
            EZDMsg.copy(data, "PL", bizMsg.L.no(i), "PL");
            i++;
        }
        bizMsg.L.setValidCount(i);
    }
    // QC#22965 2018/04/11 Add End
    private static void setBMsgToCalcPrice(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        NWAL1660_LCMsg lBizMsg = null;
        BigDecimal netPrice = getUnitPrcAmt(bizMsg);
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd, bizMsg.ccyCd_P.getValue());
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(aBizMsg.applyAutoFlg_A.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(aBizMsg.ovrdFlg_A.getValue())) {
                lBizMsg = getLBizMsg(bizMsg.L, aBizMsg.specCondPrcPk_A.getValue());
                if (lBizMsg == null) {
                    lBizMsg = bizMsg.L.no(bizMsg.L.getValidCount());
                    bizMsg.L.setValidCount(bizMsg.L.getValidCount() + 1);
                }
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, PRC_DTL_GRP.DISCOUNT);
                if (!ZYPCommonFunc.hasValue(lBizMsg.autoPrcAmtRate_PL)) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                // QC#6098 2016/08/23 Mod Start
//              } else if (!isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), aBizMsg.unitPrcAmt_A.getValue())) {
//                  ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.PCT.equals(aBizMsg.prcCondUnitCd_A.getValue()) 
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), divide(aBizMsg.manPrcAmtRate_A.getValue(), NWAL1660Constant.PCT_100))){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.AMT.equals(aBizMsg.prcCondUnitCd_A.getValue())
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), aBizMsg.unitPrcAmt_A.getValue())){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                // QC#6098 2016/08/23 Mod End
                } else if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManDelFlg_PL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.FLG_OFF_N.equals(aBizMsg.applyAutoFlg_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_OFF_N);
                }
               // QC#22965 2018/04/11 Add Start
                if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.prcCondManDelFlg_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                }else{
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                }
               // QC#22965 2018/04/11 Add End
                ZYPEZDItemValueSetter.setValue(lBizMsg.pkgUomCd_PL, bizMsg.uomCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondUnitCd_PL, aBizMsg.prcCondUnitCd_A);
                if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManEntryFlg_PL.getValue())) {
                    if (PRC_COND_UNIT.PCT.equals(aBizMsg.prcCondUnitCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, divide(aBizMsg.manPrcAmtRate_A.getValue(), NWAL1660Constant.PCT_100));
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, multiply(lBizMsg.manPrcAmtRate_PL.getValue(), netPrice).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP));
                    } else {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, aBizMsg.unitPrcAmt_A.getValue());
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, aBizMsg.unitPrcAmt_A.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, multiply(lBizMsg.unitPrcAmt_PL.getValue(), bizMsg.inEachQty.getValue()));
                }
                ZYPEZDItemValueSetter.setValue(lBizMsg.autoPrcCcyCd_PL, bizMsg.ccyCd.getValue());
                ZYPEZDItemValueSetter.setValue(lBizMsg.specCondPrcPk_PL, aBizMsg.specCondPrcPk_A.getValue());
                // QC#15308 2016/10/18 Mod Start
                //netPrice = netPrice.subtract(lBizMsg.unitPrcAmt_PL.getValue());
                if(ZYPCommonFunc.hasValue(lBizMsg.unitPrcAmt_PL)){
                    netPrice = netPrice.subtract(lBizMsg.unitPrcAmt_PL.getValue());
                }
                // QC#15308 2016/10/18 Mod End
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcRuleApplyFlg_PL, ZYPConstant.FLG_ON_Y);
                // QC#9700  2018/09/03 Add End
            }
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
            // QC#21396 2017/09/27 Mod Start
            boolean isNewFreightData = false;
            // QC#21396 2017/09/27 Mod End   
            if (ZYPConstant.FLG_OFF_N.equals(bBizMsg.applyAutoFlg_B.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(bBizMsg.ovrdFlg_B.getValue())) {
                lBizMsg = getLBizMsg(bizMsg.L, bBizMsg.specCondPrcPk_B.getValue());
                if (lBizMsg == null) {
                    // QC#21396 2017/09/27 Mod Start
                    isNewFreightData = true;
                    // QC#21396 2017/09/27 Mod End
                    lBizMsg = bizMsg.L.no(bizMsg.L.getValidCount());
                    bizMsg.L.setValidCount(bizMsg.L.getValidCount() + 1);
                }
                // QC#21841 2018/05/23 Mod Start
                // ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, PRC_DTL_GRP.FREIGHT);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, getPrcDtlGrpCd(glblCmpyCd, bBizMsg.prcRuleAdjTpCd_B.getValue()));
                // QC#21841 2018/05/23 Mod End

                if (!ZYPCommonFunc.hasValue(lBizMsg.autoPrcAmtRate_PL)) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                // QC#6098 2016/08/23 Mod Start
//              } else if (!isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), bBizMsg.unitPrcAmt_B.getValue())) {
//                  ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.PCT.equals(bBizMsg.prcCondUnitCd_B.getValue()) 
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), divide(bBizMsg.manPrcAmtRate_B.getValue(), NWAL1660Constant.PCT_100))){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else if (PRC_COND_UNIT.AMT.equals(bBizMsg.prcCondUnitCd_B.getValue())
                        && !isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), bBizMsg.unitPrcAmt_B.getValue())){
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                // QC#6098 2016/08/23 Mod End
                } else if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManDelFlg_PL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.FLG_OFF_N.equals(bBizMsg.applyAutoFlg_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                // QC#21396 2017/09/27 Mod Start
                //if(isNewFreightData){
                //    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                //} 
                if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.prcCondManDelFlg_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                }else{
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_OFF_N);
                }
                // QC#21396 2017/09/27 Mod End
                ZYPEZDItemValueSetter.setValue(lBizMsg.pkgUomCd_PL, bizMsg.uomCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondUnitCd_PL, bBizMsg.prcCondUnitCd_B);
                if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManEntryFlg_PL.getValue())) {
                    if (PRC_COND_UNIT.PCT.equals(bBizMsg.prcCondUnitCd_B.getValue())) {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, divide(bBizMsg.manPrcAmtRate_B.getValue(), NWAL1660Constant.PCT_100));
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, multiply(lBizMsg.manPrcAmtRate_PL.getValue(), netPrice).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP));
                    } else {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, bBizMsg.unitPrcAmt_B.getValue());
                        ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, bBizMsg.unitPrcAmt_B.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, multiply(lBizMsg.unitPrcAmt_PL.getValue(), bizMsg.inEachQty.getValue()));
                }
                ZYPEZDItemValueSetter.setValue(lBizMsg.autoPrcCcyCd_PL, bizMsg.ccyCd.getValue());
                ZYPEZDItemValueSetter.setValue(lBizMsg.specCondPrcPk_PL, bBizMsg.specCondPrcPk_B.getValue());
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcRuleApplyFlg_PL, ZYPConstant.FLG_ON_Y);
                // QC#9700  2018/09/03 Add End
            }
        }
    }

    private static void editOtherBizMsg(NWAL1660CMsg bizMsg, NWZC157001PMsg pMsg) {
        bizMsg.L.clear();
        int ix = 0;
        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(i);
            for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);
                NWAL1660_LCMsg lBizMsg = bizMsg.L.no(ix);

                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondTpCd_PL, p3Msg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondTpDescTxt_PL, p3Msg.prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcDtlGrpCd_PL, p3Msg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcJrnlGrpCd_PL, p3Msg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCatgCd_PL, p3Msg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManEntryFlg_PL, p3Msg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManAddFlg_PL, p3Msg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, p3Msg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(lBizMsg.pkgUomCd_PL, p3Msg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondUnitCd_PL, p3Msg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcCalcMethCd_PL, p3Msg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.autoPrcCcyCd_PL, p3Msg.autoPrcCcyCd);
                ZYPEZDItemValueSetter.setValue(lBizMsg.autoPrcAmtRate_PL, p3Msg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(lBizMsg.manPrcAmtRate_PL, p3Msg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(lBizMsg.maxPrcAmtRate_PL, p3Msg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(lBizMsg.minPrcAmtRate_PL, p3Msg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(lBizMsg.calcPrcAmtRate_PL, p3Msg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(lBizMsg.unitPrcAmt_PL, p3Msg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(lBizMsg.dsMdsePrcPk_PL, p3Msg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(lBizMsg.specCondPrcPk_PL, p3Msg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(lBizMsg.frtPerWtPk_PL, p3Msg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(lBizMsg.ordPrcCalcBasePk_PL, p3Msg.ordPrcCalcBasePk);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcRuleApplyFlg_PL, p3Msg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(lBizMsg.prcRulePrcdPk_PL, p3Msg.prcRulePrcdPk);
                // QC#9700  2018/09/03 Add End
                ix++;
            }
        }
        bizMsg.L.setValidCount(ix);
    }

    // QC#20655 2017/08/24 Add Start
    private static void editBizMsgPriceInfoDel(String glblCmpyCd, NWAL1660CMsg bizMsg, NWZC157001PMsg pMsg, List<BigDecimal> delIdRows) {
        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(i);
            boolean delFlg = false;
            for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);
                // QC#9700  2018/09/03 Add Start
                if(S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, p3Msg.prcRuleApplyFlg.getValue())){
                    continue;
                }
                // QC#9700  2018/09/03 Add End
                for (int k = 0; k < delIdRows.size(); k++) {
                    if (p3Msg.specCondPrcPk.getValue().compareTo(delIdRows.get(k)) == 0 ) {
                        delFlg = true;
                    }
                }

                if (isSellPrice(p3Msg.prcDtlGrpCd.getValue())) {
                    editBizMsgSellPrice(glblCmpyCd, bizMsg, p3Msg);
                } else if (isCharges(p3Msg.prcDtlGrpCd.getValue())) {
                    editBizMsgCharges(glblCmpyCd, bizMsg, p3Msg, delFlg);
                  // QC#21841 2018/05/23 Del Start
//                } else if (isTax(p3Msg.prcDtlGrpCd.getValue())) {
//                    editBizMsgTax(bizMsg.C, p3Msg);
                  // QC#21841 2018/05/23 Del End
                }
            }
            editBizMsgListPrice(bizMsg);
        }
        editBizMsgTax(bizMsg.C, pMsg); // QC#21841 2018/05/23 Add
    }
    // QC#20655 2017/08/24 Add Start

    private static void editBizMsgPriceInfo(String glblCmpyCd, NWAL1660CMsg bizMsg, NWZC157001PMsg pMsg) {
        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(i);

            for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);
                // QC#9700  2018/09/03 Add Start
                if(S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, p3Msg.prcRuleApplyFlg.getValue())){
                    continue;
                }
                // QC#9700  2018/09/03 Add End

                if (isSellPrice(p3Msg.prcDtlGrpCd.getValue())) {
                    editBizMsgSellPrice(glblCmpyCd, bizMsg, p3Msg);
                } else if (isCharges(p3Msg.prcDtlGrpCd.getValue())) {
                    // QC#20655 2017/08/24 Mod Start
                    editBizMsgCharges(glblCmpyCd, bizMsg, p3Msg, false);
                    // QC#20655 2017/08/24 Mod End
                  // QC#21841 2018/05/23 Del Start
//                } else if (isTax(p3Msg.prcDtlGrpCd.getValue())) {
//                    editBizMsgTax(bizMsg.C, p3Msg);
                  // QC#21841 2018/05/23 Del End
                }
            }
            editBizMsgListPrice(bizMsg);
        }
        editBizMsgTax(bizMsg.C, pMsg); // QC#21841 2018/05/23 Add
    }

// QC#21841 2018/05/23 Mod Start
//    private static void editBizMsgTax(NWAL1660_CCMsgArray cBizMsgAry, NWZC157003PMsg p3Msg) {
//
//        int i = cBizMsgAry.getValidCount();
//        NWAL1660_CCMsg taxBizMsg = cBizMsgAry.no(i);
//
//        ZYPEZDItemValueSetter.setValue(taxBizMsg.prcCondTpDescTxt_C, p3Msg.prcCondTpDescTxt);
//        ZYPEZDItemValueSetter.setValue(taxBizMsg.autoPrcAmtRate_C, p3Msg.autoPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(taxBizMsg.calcPrcAmtRate_C, p3Msg.calcPrcAmtRate);
//
//        cBizMsgAry.setValidCount(++i);
//
//    }
    private static void editBizMsgTax(NWAL1660_CCMsgArray cBizMsgAry, NWZC157001PMsg pMsg) {

        BigDecimal stateRate = BigDecimal.ZERO;
        BigDecimal stateAmt = BigDecimal.ZERO;
        BigDecimal countyRate = BigDecimal.ZERO;
        BigDecimal countyAmt = BigDecimal.ZERO;
        BigDecimal cityRate = BigDecimal.ZERO;
        BigDecimal cityAmt = BigDecimal.ZERO;
        
        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(i);

            for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);

                if (isTax(p3Msg.prcDtlGrpCd.getValue())) {
                    // State
                    if (PRC_COND_TP.CHARGE_TAX1.equals(p3Msg.prcCondTpCd.getValue())
                            || PRC_COND_TP.FREIGHT_TAX1.equals(p3Msg.prcCondTpCd.getValue())
                            || PRC_COND_TP.ITEM_TAX1.equals(p3Msg.prcCondTpCd.getValue())) {
                        if (stateRate.compareTo(p3Msg.autoPrcAmtRate.getValue()) < 0) {
                            stateRate = p3Msg.autoPrcAmtRate.getValue();
                        }
                        stateAmt = stateAmt.add(p3Msg.calcPrcAmtRate.getValue());
                    }
                    // County
                    if (PRC_COND_TP.CHARGE_TAX2.equals(p3Msg.prcCondTpCd.getValue())
                            || PRC_COND_TP.FREIGHT_TAX2.equals(p3Msg.prcCondTpCd.getValue())
                            || PRC_COND_TP.ITEM_TAX2.equals(p3Msg.prcCondTpCd.getValue())) {
                        if (countyRate.compareTo(p3Msg.autoPrcAmtRate.getValue()) < 0) {
                            countyRate = p3Msg.autoPrcAmtRate.getValue();
                        }
                        countyAmt = countyAmt.add(p3Msg.calcPrcAmtRate.getValue());
                    }
                    // City
                    if (PRC_COND_TP.CHARGE_TAX3.equals(p3Msg.prcCondTpCd.getValue())
                            || PRC_COND_TP.FREIGHT_TAX3.equals(p3Msg.prcCondTpCd.getValue())
                            || PRC_COND_TP.ITEM_TAX3.equals(p3Msg.prcCondTpCd.getValue())) {
                        if (cityRate.compareTo(p3Msg.autoPrcAmtRate.getValue()) < 0) {
                            cityRate = p3Msg.autoPrcAmtRate.getValue();
                        }
                        cityAmt = cityAmt.add(p3Msg.calcPrcAmtRate.getValue());
                    }
                }
            }
        }
        int i = cBizMsgAry.getValidCount();
        NWAL1660_CCMsg taxBizMsg;
        if (!stateAmt.equals(BigDecimal.ZERO)) {
            taxBizMsg = cBizMsgAry.no(i);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.prcCondTpDescTxt_C, TAX_JURISDICTION_ST);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.autoPrcAmtRate_C, stateRate);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.calcPrcAmtRate_C, stateAmt);
            cBizMsgAry.setValidCount(++i);
        }
        if (!countyAmt.equals(BigDecimal.ZERO)) {
            taxBizMsg = cBizMsgAry.no(i);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.prcCondTpDescTxt_C, TAX_JURISDICTION_CNTY);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.autoPrcAmtRate_C, countyRate);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.calcPrcAmtRate_C, countyAmt);
            cBizMsgAry.setValidCount(++i);
        }
        if (!cityAmt.equals(BigDecimal.ZERO)) {
            taxBizMsg = cBizMsgAry.no(i);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.prcCondTpDescTxt_C, TAX_JURISDICTION_CTY);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.autoPrcAmtRate_C, cityRate);
            ZYPEZDItemValueSetter.setValue(taxBizMsg.calcPrcAmtRate_C, cityAmt);
            cBizMsgAry.setValidCount(++i);
        }
    }
// QC#21841 2018/05/23 Mod End

    // QC#20655 2017/08/24 Mod Start
    private static void editBizMsgCharges(String glblCmpyCd, NWAL1660CMsg bizMsg, NWZC157003PMsg p3Msg, boolean delFlg) {
    //private static void editBizMsgCharges(String glblCmpyCd, NWAL1660CMsg bizMsg, NWZC157003PMsg p3Msg) {
    // QC#20655 2017/08/23 Mod End
        NWAL1660_BCMsgArray bBizMsgAry = bizMsg.B;
        int i = bBizMsgAry.getValidCount();
        NWAL1660_BCMsg bBizMsg = bBizMsgAry.no(i);

        ZYPEZDItemValueSetter.setValue(bBizMsg.specCondPrcPk_B, p3Msg.specCondPrcPk);

        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getPrcRuleInfo(glblCmpyCd, bizMsg, bBizMsg.specCondPrcPk_B.getValue());
        if (rslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            // QC#20655 2017/08/24 Mod Start
            if (ZYPConstant.FLG_ON_Y.equals(p3Msg.prcCondManDelFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondManDelFlg_B, ZYPConstant.FLG_ON_Y);
            } else if (delFlg) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondManDelFlg_B, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondManDelFlg_B, ZYPConstant.FLG_OFF_N);
            }
            // QC#20655 2017/08/24 Mod End
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleNm_B, (String) rsltMap.get("PRC_RULE_NM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleHdrPk_B, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleCatgCd_B, (String) rsltMap.get("PRC_RULE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleCatgDescTxt_B, (String) rsltMap.get("PRC_RULE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlPk_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlChrgNm_B, (String) rsltMap.get("PRC_RULE_DTL_CHRG_NM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAttrbCd_B, (String) rsltMap.get("PRC_RULE_ATTRB_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAttrbDescTxt_B, (String) rsltMap.get("PRC_RULE_ATTRB_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjTpCd_B, (String) rsltMap.get("PRC_RULE_ADJ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjTpDescTxt_B, (String) rsltMap.get("PRC_RULE_ADJ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.applyAutoFlg_B, (String) rsltMap.get("APPLY_AUTO_FLG"));

            ZYPEZDItemValueSetter.setValue(bBizMsg.prcFmlaPk_B, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlRate_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlTxt_B, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.ovrdFlg_B, (String) rsltMap.get("OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcMaxAmtRate_B, (BigDecimal) rsltMap.get("PRC_MAX_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcMinAmtRate_B, (BigDecimal) rsltMap.get("PRC_MIN_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleModTpCd_B, (String) rsltMap.get("PRC_RULE_MOD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjLvlCd_B, (String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"));
            if (PRC_COND_UNIT.PCT.equals(p3Msg.prcCondUnitCd.getValue())) {
                if (ZYPCommonFunc.hasValue(p3Msg.manPrcAmtRate)) {
                    ZYPEZDItemValueSetter.setValue(bBizMsg.manPrcAmtRate_B, multiply(p3Msg.manPrcAmtRate.getValue(), NWAL1660Constant.PCT_100));
                // QC#6098 2016/08/23 Add Start
                }else{
                    ZYPEZDItemValueSetter.setValue(bBizMsg.manPrcAmtRate_B, multiply(p3Msg.autoPrcAmtRate.getValue(), NWAL1660Constant.PCT_100));
                // QC#6098 2016/08/23 Add End
                }
                ZYPEZDItemValueSetter.setValue(bBizMsg.unitPrcAmt_B, p3Msg.unitPrcAmt);
            } else {
                bBizMsg.manPrcAmtRate_B.clear();
                ZYPEZDItemValueSetter.setValue(bBizMsg.unitPrcAmt_B, p3Msg.unitPrcAmt);
            }
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondUnitCd_B, p3Msg.prcCondUnitCd);
        }

        bBizMsgAry.setValidCount(++i);
    }
    // QC#22965 2018/04/11 Add Start
    private static void editBizMsgCharges(String glblCmpyCd, NWAL1660CMsg bizMsg, NWAL1660_LCMsg blMsg) {
        NWAL1660_BCMsgArray bBizMsgAry = bizMsg.B;
        int i = bBizMsgAry.getValidCount();
        NWAL1660_BCMsg bBizMsg = bBizMsgAry.no(i);

        ZYPEZDItemValueSetter.setValue(bBizMsg.specCondPrcPk_B, blMsg.specCondPrcPk_PL);

        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getPrcRuleInfo(glblCmpyCd, bizMsg, bBizMsg.specCondPrcPk_B.getValue());
        if (rslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            if (!PRC_RULE_ADJ_LVL.HEADER.equals((String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"))) {
                return;
            }

            if (ZYPConstant.FLG_ON_Y.equals(blMsg.prcCondManDelFlg_PL.getValue())) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondManDelFlg_B, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondManDelFlg_B, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleNm_B, (String) rsltMap.get("PRC_RULE_NM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleHdrPk_B, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleCatgCd_B, (String) rsltMap.get("PRC_RULE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleCatgDescTxt_B, (String) rsltMap.get("PRC_RULE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlPk_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlChrgNm_B, (String) rsltMap.get("PRC_RULE_DTL_CHRG_NM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAttrbCd_B, (String) rsltMap.get("PRC_RULE_ATTRB_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAttrbDescTxt_B, (String) rsltMap.get("PRC_RULE_ATTRB_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjTpCd_B, (String) rsltMap.get("PRC_RULE_ADJ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjTpDescTxt_B, (String) rsltMap.get("PRC_RULE_ADJ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.applyAutoFlg_B, (String) rsltMap.get("APPLY_AUTO_FLG"));

            ZYPEZDItemValueSetter.setValue(bBizMsg.prcFmlaPk_B, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlRate_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlTxt_B, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.ovrdFlg_B, (String) rsltMap.get("OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcMaxAmtRate_B, (BigDecimal) rsltMap.get("PRC_MAX_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcMinAmtRate_B, (BigDecimal) rsltMap.get("PRC_MIN_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleModTpCd_B, (String) rsltMap.get("PRC_RULE_MOD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjLvlCd_B, (String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"));
            if (PRC_COND_UNIT.PCT.equals(blMsg.prcCondUnitCd_PL.getValue())) {
                if (ZYPCommonFunc.hasValue(blMsg.manPrcAmtRate_PL)) {
                    ZYPEZDItemValueSetter.setValue(bBizMsg.manPrcAmtRate_B, multiply(blMsg.manPrcAmtRate_PL.getValue(), NWAL1660Constant.PCT_100));
                    // QC#6098 2016/08/23 Add Start
                } else {
                    ZYPEZDItemValueSetter.setValue(bBizMsg.manPrcAmtRate_B, multiply(blMsg.autoPrcAmtRate_PL.getValue(), NWAL1660Constant.PCT_100));
                    // QC#6098 2016/08/23 Add End
                }
                ZYPEZDItemValueSetter.setValue(bBizMsg.unitPrcAmt_B, blMsg.calcPrcAmtRate_PL);
            } else {
                bBizMsg.manPrcAmtRate_B.clear();
                ZYPEZDItemValueSetter.setValue(bBizMsg.unitPrcAmt_B, blMsg.calcPrcAmtRate_PL);
            }
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondUnitCd_B, blMsg.prcCondUnitCd_PL);
        }

        bBizMsgAry.setValidCount(++i);
    }

    // QC#22965 2018/04/11 Add End

    private static void editBizMsgSellPrice(String glblCmpyCd, NWAL1660CMsg bizMsg, NWZC157003PMsg p3Msg) {
        NWAL1660_ACMsgArray aBizMsgAry = bizMsg.A;
        int i = aBizMsgAry.getValidCount();
        NWAL1660_ACMsg sellPrcBizMsg = aBizMsgAry.no(i);

        ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.specCondPrcPk_A, p3Msg.specCondPrcPk);
        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getPrcRuleInfo(glblCmpyCd, bizMsg, p3Msg.specCondPrcPk.getValue());
        if (rslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();

            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondManDelFlg_A, p3Msg.prcCondManDelFlg);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleNm_A, (String) rsltMap.get("PRC_RULE_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleHdrPk_A, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleCatgCd_A, (String) rsltMap.get("PRC_RULE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleCatgDescTxt_A, (String) rsltMap.get("PRC_RULE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlPk_A, (BigDecimal) rsltMap.get("PRC_RULE_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlChrgNm_A, (String) rsltMap.get("PRC_RULE_DTL_CHRG_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAttrbCd_A, (String) rsltMap.get("PRC_RULE_ATTRB_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAttrbDescTxt_A, (String) rsltMap.get("PRC_RULE_ATTRB_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjTpCd_A, (String) rsltMap.get("PRC_RULE_ADJ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjTpDescTxt_A, (String) rsltMap.get("PRC_RULE_ADJ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.applyAutoFlg_A, (String) rsltMap.get("APPLY_AUTO_FLG"));

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcFmlaPk_A, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlRate_A, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlTxt_A, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.ovrdFlg_A, (String) rsltMap.get("OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMaxAmtRate_A, ((BigDecimal) rsltMap.get("PRC_MAX_AMT_RATE")));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMinAmtRate_A, (BigDecimal) rsltMap.get("PRC_MIN_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleModTpCd_A, (String) rsltMap.get("PRC_RULE_MOD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjLvlCd_A, (String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"));
            if (PRC_COND_UNIT.PCT.equals(p3Msg.prcCondUnitCd.getValue())) {
                if (ZYPCommonFunc.hasValue(p3Msg.manPrcAmtRate)) {
                    ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.manPrcAmtRate_A, multiply(p3Msg.manPrcAmtRate.getValue(), NWAL1660Constant.PCT_100));
                // QC#6098 2016/08/23 Add Start
                }else{
                    ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.manPrcAmtRate_A, multiply(p3Msg.autoPrcAmtRate.getValue(), NWAL1660Constant.PCT_100));
                // QC#6098 2016/08/23 Add End
                }
                ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.unitPrcAmt_A, p3Msg.unitPrcAmt);
            } else {
                sellPrcBizMsg.manPrcAmtRate_A.clear();
                ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.unitPrcAmt_A, p3Msg.unitPrcAmt);
            }
            aBizMsg.funcUnitListPrcAmt_A.clear();
            aBizMsg.funcNetSellPrcAmt_A.clear();
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondUnitCd_A, p3Msg.prcCondUnitCd);
        }
        aBizMsgAry.setValidCount(++i);
    }

    // QC#22965 2018/04/11 Add Start
    private static void editBizMsgSellPrice(String glblCmpyCd, NWAL1660CMsg bizMsg, NWAL1660_LCMsg blMsg) {
        NWAL1660_ACMsgArray aBizMsgAry = bizMsg.A;
        int i = aBizMsgAry.getValidCount();
        NWAL1660_ACMsg sellPrcBizMsg = aBizMsgAry.no(i);

        ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.specCondPrcPk_A, blMsg.specCondPrcPk_PL);
        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getPrcRuleInfo(glblCmpyCd, bizMsg, blMsg.specCondPrcPk_PL.getValue());
        if (rslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            if(!PRC_RULE_ADJ_LVL.HEADER.equals((String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"))){
                return;
            }

            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondManDelFlg_A, blMsg.prcCondManDelFlg_PL);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleNm_A, (String) rsltMap.get("PRC_RULE_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleHdrPk_A, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleCatgCd_A, (String) rsltMap.get("PRC_RULE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleCatgDescTxt_A, (String) rsltMap.get("PRC_RULE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlPk_A, (BigDecimal) rsltMap.get("PRC_RULE_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlChrgNm_A, (String) rsltMap.get("PRC_RULE_DTL_CHRG_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAttrbCd_A, (String) rsltMap.get("PRC_RULE_ATTRB_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAttrbDescTxt_A, (String) rsltMap.get("PRC_RULE_ATTRB_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjTpCd_A, (String) rsltMap.get("PRC_RULE_ADJ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjTpDescTxt_A, (String) rsltMap.get("PRC_RULE_ADJ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.applyAutoFlg_A, (String) rsltMap.get("APPLY_AUTO_FLG"));

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcFmlaPk_A, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlRate_A, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlTxt_A, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.ovrdFlg_A, (String) rsltMap.get("OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMaxAmtRate_A, ((BigDecimal) rsltMap.get("PRC_MAX_AMT_RATE")));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMinAmtRate_A, (BigDecimal) rsltMap.get("PRC_MIN_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleModTpCd_A, (String) rsltMap.get("PRC_RULE_MOD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjLvlCd_A, (String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"));
            if (PRC_COND_UNIT.PCT.equals(blMsg.prcCondUnitCd_PL.getValue())) {
                if (ZYPCommonFunc.hasValue(blMsg.manPrcAmtRate_PL)) {
                    ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.manPrcAmtRate_A, multiply(blMsg.manPrcAmtRate_PL.getValue(), NWAL1660Constant.PCT_100));
                }else{
                    ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.manPrcAmtRate_A, multiply(blMsg.autoPrcAmtRate_PL.getValue(), NWAL1660Constant.PCT_100));
                }
                ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.unitPrcAmt_A, blMsg.calcPrcAmtRate_PL);
            } else {
                sellPrcBizMsg.manPrcAmtRate_A.clear();
                ZYPEZDItemValueSetter.setValue(sellPrcBizMsg.unitPrcAmt_A, blMsg.calcPrcAmtRate_PL);
            }
            aBizMsg.funcUnitListPrcAmt_A.clear();
            aBizMsg.funcNetSellPrcAmt_A.clear();
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondUnitCd_A, blMsg.prcCondUnitCd_PL);
        }
        aBizMsgAry.setValidCount(++i);
    }
    // QC#22965 2018/04/11 Add End

    private static void editBizMsgListPrice(NWAL1660CMsg bizMsg) {
        BigDecimal unitPrcAmt = getBasePrcAmt(bizMsg);
        if (unitPrcAmt == null) {
            return;
        }

        // List Price
        if (bizMsg.A.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).funcUnitListPrcAmt_A, unitPrcAmt);
        }
        // Final Sell Price
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(aBizMsg.unitPrcAmt_A)) {
                unitPrcAmt = unitPrcAmt.subtract(aBizMsg.unitPrcAmt_A.getValue());
                ZYPEZDItemValueSetter.setValue(aBizMsg.funcNetSellPrcAmt_A, unitPrcAmt);
            }else{
                ZYPEZDItemValueSetter.setValue(aBizMsg.funcNetSellPrcAmt_A, unitPrcAmt);
            }
        }
    }

    private static boolean isTax(String prcDtlGrpCd) {
        return PRC_DTL_GRP.TAX.equals(prcDtlGrpCd);
    }

    private static boolean isCharges(String prcDtlGrpCd) {
        return PRC_DTL_GRP.FREIGHT.equals(prcDtlGrpCd) //
                || PRC_DTL_GRP.SPECIAL_CHARGE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.RESTOCKING_FEE.equals(prcDtlGrpCd)
                // QC#21841 2018/05/23 Add Start
                || PRC_DTL_GRP.HANDLING_FEE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.FUEL_SURCHARGE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.SHIPPING_FEE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.RESTOCKING_FEE.equals(prcDtlGrpCd); // QC#27479 2018/08/03 Add
                // QC#21841 2018/05/23 Add End
           }

    private static boolean isSellPrice(String prcDtlGrpCd) {
        return PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrpCd);
    }

    private static NWZC157001PMsg createPricingApiPMsg(String glblCmpyCd, NWAL1660CMsg bizMsg, String smryLineFlg) {
        NWZC157001PMsg pMsg = new NWZC157001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.RECALC);
        // QC#9437 2016/06/21 Mod Start
        // ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, ZYPDateUtil.getSalesDate());
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd_P);
        ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxSmryLineFlg, smryLineFlg);

        NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(0);
        ZYPEZDItemValueSetter.setValue(p2Msg.trxLineNum, bizMsg.trxLineNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.trxLineSubNum, bizMsg.trxLineSubNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.configCatgCd, bizMsg.configCatgCd_P);
        // QC#9437 2016/06/21 Add Start
        ZYPEZDItemValueSetter.setValue(p2Msg.prcBaseDt, bizMsg.prcBaseDt);
        // QC#9437 2016/06/21 Add End
        ZYPEZDItemValueSetter.setValue(p2Msg.billToCustCd, bizMsg.billToCustCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.shipToCustCd, bizMsg.shipToCustCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsAcctNum_SH, bizMsg.shipToCustAcctCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsAcctNum_BL, bizMsg.billToCustAcctCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.prcCatgCd, bizMsg.prcCatgCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.csmpNum, bizMsg.csmpNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.dlrRefNum, bizMsg.dlrRefNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.prcContrNum, bizMsg.prcContrNum_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.coaBrCd, bizMsg.coaBrCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ccyCd, bizMsg.ccyCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.mdseCd, bizMsg.mdseCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.pkgUomCd, bizMsg.uomCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.entCpoDtlDealSlsAmt, getLineAmt(bizMsg));
        ZYPEZDItemValueSetter.setValue(p2Msg.dsOrdLineCatgCd, bizMsg.dsCpoLineCatgCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ordQty, bizMsg.inEachQty_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ordCustUomQty, bizMsg.ordCustUomQty);
        ZYPEZDItemValueSetter.setValue(p2Msg.invQty, bizMsg.invQty_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.rtrnRsnCd, bizMsg.rtrnRsnCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.mdlId, bizMsg.mdlId_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.firstLineAddr_SH, bizMsg.shipToFirstLineAddr_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.scdLineAddr_SH, bizMsg.shipToScdLineAddr_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ctyAddr_SH, bizMsg.shipToCtyAddr_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.stCd_SH, bizMsg.shipToStCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.cntyNm_SH, bizMsg.shipToCntyNm_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.postCd_SH, bizMsg.shipToPostCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.ctryCd_SH, bizMsg.shipToCtryCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.slsRepOrSlsTeamTocCd, bizMsg.slsRepOrSlsTeamTocCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.rtlWhCd, bizMsg.rtlWhCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.frtCondCd, bizMsg.frtCondCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd_P);
        ZYPEZDItemValueSetter.setValue(p2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p2Msg.coaExtnCd, bizMsg.coaExtnCd_P);

        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(i);
            NWAL1660_LCMsg blMsg = bizMsg.L.no(i);

            ZYPEZDItemValueSetter.setValue(p3Msg.trxLineNum, bizMsg.trxLineNum_P);
            ZYPEZDItemValueSetter.setValue(p3Msg.trxLineSubNum, bizMsg.trxLineSubNum_P);
            ZYPEZDItemValueSetter.setValue(p3Msg.configCatgCd, bizMsg.configCatgCd_P);

            ZYPEZDItemValueSetter.setValue(p3Msg.prcCondTpCd, blMsg.prcCondTpCd_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcCondTpDescTxt, blMsg.prcCondTpDescTxt_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcDtlGrpCd, blMsg.prcDtlGrpCd_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcCatgCd, blMsg.prcCatgCd_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcCondManEntryFlg, blMsg.prcCondManEntryFlg_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcCondManAddFlg, blMsg.prcCondManAddFlg_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcCondManDelFlg, blMsg.prcCondManDelFlg_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.pkgUomCd, blMsg.pkgUomCd_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcCondUnitCd, blMsg.prcCondUnitCd_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.autoPrcCcyCd, blMsg.autoPrcCcyCd_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.autoPrcAmtRate, blMsg.autoPrcAmtRate_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.maxPrcAmtRate, blMsg.maxPrcAmtRate_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.minPrcAmtRate, blMsg.minPrcAmtRate_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.manPrcAmtRate, blMsg.manPrcAmtRate_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.calcPrcAmtRate, blMsg.calcPrcAmtRate_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.unitPrcAmt, blMsg.unitPrcAmt_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.specCondPrcPk, blMsg.specCondPrcPk_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.ordPrcCalcBasePk, blMsg.ordPrcCalcBasePk_PL);
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(p3Msg.prcRuleApplyFlg, blMsg.prcRuleApplyFlg_PL);
            ZYPEZDItemValueSetter.setValue(p3Msg.prcRulePrcdPk, blMsg.prcRulePrcdPk_PL);
            // QC#9700  2018/09/03 Add End
        }
        // ADD START 2015/12/16 #1090
        p2Msg.NWZC157003PMsg.setValidCount(bizMsg.L.getValidCount());
        pMsg.NWZC157002PMsg.setValidCount(1);
        // ADD END 2015/12/15 #1090
        return pMsg;
    }

    private static BigDecimal getLineAmt(NWAL1660CMsg bizMsg) {
        BigDecimal listPrice = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWAL1660_LCMsg lBMsg = bizMsg.L.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(lBMsg.prcDtlGrpCd_PL.getValue())) {
                listPrice = listPrice.add(lBMsg.unitPrcAmt_PL.getValue().multiply(bizMsg.ordCustUomQty.getValue()));
            }else if (PRC_COND_TP.MANUAL_PRICE.equals(lBMsg.prcCondTpCd_PL.getValue())){
                listPrice = listPrice.add(lBMsg.unitPrcAmt_PL.getValue().multiply(bizMsg.ordCustUomQty.getValue()));
            }
        }
        return listPrice;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * @return boolean
     * </pre>
     */
    public static boolean checkEssential(String glblCmpyCd, NWAL1660CMsg bizMsg) {

        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd, bizMsg.ccyCd_P.getValue());

        BigDecimal fraction = new BigDecimal("0.1").pow(aftDeclPntDigitNum);
        BigDecimal minAmt = BigDecimal.TEN.pow(NWAL1660Constant.MAX_AMOUNT_DIGITS).multiply(NWAL1660Constant.MINUS);
        BigDecimal maxAmt = BigDecimal.TEN.pow(NWAL1660Constant.MAX_AMOUNT_DIGITS);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);

            if (PRC_COND_UNIT.PCT.equals(aBizMsg.prcCondUnitCd_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(aBizMsg.manPrcAmtRate_A)) {
                    aBizMsg.manPrcAmtRate_A.setErrorInfo(1, ZZM9000E, new String[] {"Adjusted %" });
                    return true;
                }
                BigDecimal rate = aBizMsg.manPrcAmtRate_A.getValue();
                if (NWAL1660Constant.PCT_M100.compareTo(rate) > 0) {
                    aBizMsg.manPrcAmtRate_A.setErrorInfo(1, NWAM0735E, null);
                    return true;
                }
                if (NWAL1660Constant.PCT_100.compareTo(rate) < 0) {
                    aBizMsg.manPrcAmtRate_A.setErrorInfo(1, NWAM0736E, null);
                    return true;
                }
            } else {
                if (!ZYPCommonFunc.hasValue(aBizMsg.unitPrcAmt_A)) {
                    aBizMsg.unitPrcAmt_A.setErrorInfo(1, ZZM9000E, new String[] {"Adjustment Value" });
                    return true;
                }

                if (minAmt.compareTo(aBizMsg.unitPrcAmt_A.getValue()) >= 0) {
                    aBizMsg.unitPrcAmt_A.setErrorInfo(1, NWAM0732E, null);
                    return true;
                }

                if (!checkFraction(aBizMsg.unitPrcAmt_A.getValue(), fraction)) {
                    aBizMsg.unitPrcAmt_A.setErrorInfo(1, NWAM0733E, null);
                    return true;
                }

                if (maxAmt.compareTo(aBizMsg.unitPrcAmt_A.getValue()) <= 0) {
                    aBizMsg.unitPrcAmt_A.setErrorInfo(1, NWAM0734E, null);
                    return true;
                }
            }
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);

            if (PRC_COND_UNIT.PCT.equals(bBizMsg.prcCondUnitCd_B.getValue())) {
                if (!ZYPCommonFunc.hasValue(bBizMsg.manPrcAmtRate_B)) {
                    bBizMsg.manPrcAmtRate_B.setErrorInfo(1, ZZM9000E, new String[] {"Adjusted %" });
                    return true;
                }
                BigDecimal rate = bBizMsg.manPrcAmtRate_B.getValue();
                if (NWAL1660Constant.PCT_M100.compareTo(rate) > 0) {
                    bBizMsg.manPrcAmtRate_B.setErrorInfo(1, NWAM0735E, null);
                    return true;
                }
                if (NWAL1660Constant.PCT_100.compareTo(rate) < 0) {
                    bBizMsg.manPrcAmtRate_B.setErrorInfo(1, NWAM0736E, null);
                    return true;
                }
            } else {

                if (!ZYPCommonFunc.hasValue(bBizMsg.unitPrcAmt_B)) {
                    bBizMsg.unitPrcAmt_B.setErrorInfo(1, ZZM9000E, new String[] {"Adjustment Value" });
                    return true;
                }
                if (minAmt.compareTo(bBizMsg.unitPrcAmt_B.getValue()) >= 0) {
                    bBizMsg.unitPrcAmt_B.setErrorInfo(1, NWAM0732E, null);
                    return true;
                }

                if (!checkFraction(bBizMsg.unitPrcAmt_B.getValue(), fraction)) {
                    bBizMsg.unitPrcAmt_B.setErrorInfo(1, NWAM0733E, null);
                    return true;
                }

                if (maxAmt.compareTo(bBizMsg.unitPrcAmt_B.getValue()) <= 0) {
                    bBizMsg.unitPrcAmt_B.setErrorInfo(1, NWAM0734E, null);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check fraction.
     * @param target BigDecimal
     * @param divisor BigDecimal
     * @return return false if failed
     */
    private static boolean checkFraction(BigDecimal target, BigDecimal divisor) {
        return target.remainder(divisor).compareTo(BigDecimal.ZERO.setScale(target.scale())) == 0;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    public static void addSellPrice(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getPrcRuleInfo(glblCmpyCd, bizMsg, bizMsg.specCondPrcPk_AS.getValue());
        if (rslt.isCodeNotFound()) {
            bizMsg.specCondPrcPk_AS.setErrorInfo(1, NWAM0325E, new String[] {"Spec Cond Price PK" });
            return;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();

        int ix = bizMsg.A.getValidCount();
        NWAL1660_ACMsg aBizMsg = bizMsg.A.no(ix);

        ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondManDelFlg_A, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleNm_A, (String) rsltMap.get("PRC_RULE_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleHdrPk_A, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleCatgCd_A, (String) rsltMap.get("PRC_RULE_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleCatgDescTxt_A, (String) rsltMap.get("PRC_RULE_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlPk_A, (BigDecimal) rsltMap.get("PRC_RULE_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlChrgNm_A, (String) rsltMap.get("PRC_RULE_DTL_CHRG_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAttrbCd_A, (String) rsltMap.get("PRC_RULE_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAttrbDescTxt_A, (String) rsltMap.get("PRC_RULE_ATTRB_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjTpCd_A, (String) rsltMap.get("PRC_RULE_ADJ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjTpDescTxt_A, (String) rsltMap.get("PRC_RULE_ADJ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.applyAutoFlg_A, (String) rsltMap.get("APPLY_AUTO_FLG"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.specCondPrcPk_A, (BigDecimal) rsltMap.get("SPEC_COND_PRC_PK"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcFmlaPk_A, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlRate_A, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleDtlTxt_A, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.ovrdFlg_A, (String) rsltMap.get("OVRD_FLG"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcMaxAmtRate_A, (BigDecimal) rsltMap.get("PRC_MAX_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcMinAmtRate_A, (BigDecimal) rsltMap.get("PRC_MIN_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleModTpCd_A, (String) rsltMap.get("PRC_RULE_MOD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRuleAdjLvlCd_A, (String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"));
        if (PRC_RULE_ATTRB.PERCENT.equals(aBizMsg.prcRuleAttrbCd_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondUnitCd_A, PRC_COND_UNIT.PCT);
            ZYPEZDItemValueSetter.setValue(aBizMsg.manPrcAmtRate_A, multiply(aBizMsg.prcRuleDtlRate_A.getValue(), NWAL1660Constant.PCT_100));// QC#15308 2016/10/18 Add 
        } else {
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCondUnitCd_A, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(aBizMsg.unitPrcAmt_A, new BigDecimal(aBizMsg.prcRuleDtlTxt_A.getValue())); // QC#15308 2016/10/18 Add 
        }
        //aBizMsg.manPrcAmtRate_A.clear();
        //aBizMsg.unitPrcAmt_A.clear();
        aBizMsg.funcUnitListPrcAmt_A.clear();
        aBizMsg.funcNetSellPrcAmt_A.clear();

        bizMsg.A.setValidCount(++ix);
    }

    //    /**
    //     * Check Maximum Amount or Rate.
    //     * @param amtRate BigDecimal
    //     * @param max BigDecimal
    //     * @return isExceedsMax boolean
    //     */
    //    private static boolean isExceedsMax(BigDecimal amtRate, BigDecimal max) {
    //        boolean isExceedsMax = false;
    //        if (ZYPCommonFunc.hasValue(max) && !max.equals(BigDecimal.ZERO)) {
    //            if (amtRate.compareTo(max) > 0) {
    //                isExceedsMax = true;
    //            }
    //        }
    //        return isExceedsMax;
    //    }
    //
    //    /**
    //     * Check Minimum Amount or Rate.
    //     * @param amtRate BigDecimal
    //     * @param min BigDecimal
    //     * @return isLowerThanMin boolean
    //     */
    //    private static boolean isLowerThanMin(BigDecimal amtRate, BigDecimal min) {
    //        boolean isLowerThanMin = false;
    //        if (ZYPCommonFunc.hasValue(min) && !min.equals(BigDecimal.ZERO)) {
    //            if (amtRate.compareTo(min) < 0) {
    //                isLowerThanMin = true;
    //            }
    //        }
    //        return isLowerThanMin;
    //    }

//    private static BigDecimal getSpecCondPrcPkForSellPrice(BigDecimal prcRuleDtlPk, NWAL1660CMsg bizMsg) {
//        for (int i = 0; i < bizMsg.prcRuleDtlPk_AL.length(); i++) {
//
//            if (bizMsg.prcRuleDtlPk_AL.no(i).getValue().compareTo(prcRuleDtlPk) == 0) {
//                return bizMsg.U.no(i).specCondPrcPk_AL.getValue();
//            }
//        }
//        return null;
//    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    public static void addCharges(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getPrcRuleInfo(glblCmpyCd, bizMsg, bizMsg.specCondPrcPk_BS.getValue());
        if (rslt.isCodeNotFound()) {
            bizMsg.specCondPrcPk_BS.setErrorInfo(1, NWAM0325E, new String[] {"Spec Cond Price PK(2)" });
            return;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();

        int ix = bizMsg.B.getValidCount();
        NWAL1660_BCMsg bBizMsg = bizMsg.B.no(ix);


        ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondManDelFlg_B, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleNm_B, (String) rsltMap.get("PRC_RULE_NM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleHdrPk_B, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleCatgCd_B, (String) rsltMap.get("PRC_RULE_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleCatgDescTxt_B, (String) rsltMap.get("PRC_RULE_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlPk_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlChrgNm_B, (String) rsltMap.get("PRC_RULE_DTL_CHRG_NM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAttrbCd_B, (String) rsltMap.get("PRC_RULE_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAttrbDescTxt_B, (String) rsltMap.get("PRC_RULE_ATTRB_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjTpCd_B, (String) rsltMap.get("PRC_RULE_ADJ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjTpDescTxt_B, (String) rsltMap.get("PRC_RULE_ADJ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.applyAutoFlg_B, (String) rsltMap.get("APPLY_AUTO_FLG"));

        ZYPEZDItemValueSetter.setValue(bBizMsg.specCondPrcPk_B, (BigDecimal) rsltMap.get("SPEC_COND_PRC_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcFmlaPk_B, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlRate_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlTxt_B, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.ovrdFlg_B, (String) rsltMap.get("OVRD_FLG"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcMaxAmtRate_B, (BigDecimal) rsltMap.get("PRC_MAX_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcMinAmtRate_B, (BigDecimal) rsltMap.get("PRC_MIN_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleModTpCd_B, (String) rsltMap.get("PRC_RULE_MOD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleAdjLvlCd_B, (String) rsltMap.get("PRC_RULE_ADJ_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcFmlaPk_B, (BigDecimal) rsltMap.get("PRC_FMLA_PK"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlRate_B, (BigDecimal) rsltMap.get("PRC_RULE_DTL_RATE"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.prcRuleDtlTxt_B, (String) rsltMap.get("PRC_RULE_DTL_TXT"));
        if (PRC_RULE_ATTRB.PERCENT.equals(bBizMsg.prcRuleAttrbCd_B.getValue())) {
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondUnitCd_B, PRC_COND_UNIT.PCT);
            ZYPEZDItemValueSetter.setValue(bBizMsg.manPrcAmtRate_B, multiply(bBizMsg.prcRuleDtlRate_B.getValue(), NWAL1660Constant.PCT_100)); // QC#15308 2016/10/18 Add
        } else {
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCondUnitCd_B, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(bBizMsg.unitPrcAmt_B, new BigDecimal(bBizMsg.prcRuleDtlTxt_B.getValue())); // QC#15308 2016/10/18 Add
        }
        //bBizMsg.manPrcAmtRate_B.clear();
        //bBizMsg.unitPrcAmt_B.clear();


        bizMsg.B.setValidCount(++ix);
    }

//    private static BigDecimal getSpecCondPrcPkForCharges(BigDecimal prcRuleDtlPk, NWAL1660CMsg bizMsg) {
//        for (int i = 0; i < bizMsg.prcRuleDtlPk_BL.length(); i++) {
//
//            if (bizMsg.prcRuleDtlPk_BL.no(i).getValue().compareTo(prcRuleDtlPk) == 0) {
//                return bizMsg.V.no(i).specCondPrcPk_BL.getValue();
//            }
//        }
//        return null;
//    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    public static void deriveDefaultAmtForSellPrice(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        //get last row
        int ix = bizMsg.A.getValidCount() - 1;
        NWAL1660_ACMsg aBizMsg = bizMsg.A.no(ix);

        if (!ZYPCommonFunc.hasValue(aBizMsg.prcFmlaPk_A) //
                || BigDecimal.ZERO.compareTo(aBizMsg.prcFmlaPk_A.getValue()) == 0) {
            return;
        }

        NWZC170001PMsg pMsg = getFormulaApiPMsgForSellPrice(glblCmpyCd, bizMsg, aBizMsg);
        new NWZC170001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            NWZC170001_xxMsgIdListPMsg msgInfo = pMsg.xxMsgIdList.no(0);
            bizMsg.setMessageInfo(//
                    msgInfo.xxMsgId.getValue() //
                    , new String[] {msgInfo.xxMsgPrmTxt_0.getValue() //
                            , msgInfo.xxMsgPrmTxt_1.getValue() //
                            , msgInfo.xxMsgPrmTxt_2.getValue() //
                    });
        }
        aBizMsg.manPrcAmtRate_A.clear();
        ZYPEZDItemValueSetter.setValue(aBizMsg.unitPrcAmt_A, pMsg.xxDiscPrcAmt);
    }

    private static NWZC170001PMsg getFormulaApiPMsgForSellPrice(String glblCmpyCd, NWAL1660CMsg bizMsg, NWAL1660_ACMsg aBizMsg) {
        NWZC170001PMsg pMsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC170001.MODE_BASE);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcFmlaPk, aBizMsg.prcFmlaPk_A);
        ZYPEZDItemValueSetter.setValue(pMsg.ccyCd, bizMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, bizMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordTakeMdseCd //
                , S21StringUtil.subStringByLength(bizMsg.mdseCd.getValue(), 0, LEN_ORD_TAKE_MDSE));
        ZYPEZDItemValueSetter.setValue(pMsg.basePrcAmt, getBasePrcAmt(bizMsg));

        return pMsg;
    }

    private static BigDecimal getBasePrcAmt(NWAL1660CMsg bizMsg) {
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWAL1660_LCMsg lBizMsg = bizMsg.L.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(lBizMsg.prcDtlGrpCd_PL.getValue())) {
                return lBizMsg.unitPrcAmt_PL.getValue();
            }
        }
        return null;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    public static void deriveDefaultAmtForCharges(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        //get last row
        int ix = bizMsg.B.getValidCount() - 1;
        NWAL1660_BCMsg bBizMsg = bizMsg.B.no(ix);
        if (!ZYPCommonFunc.hasValue(bBizMsg.prcFmlaPk_B) //
                || BigDecimal.ZERO.compareTo(bBizMsg.prcFmlaPk_B.getValue()) == 0) {
            return;
        }

        NWZC170001PMsg pMsg = getFormulaApiPMsgForCharges(glblCmpyCd, bizMsg, bBizMsg);
        new NWZC170001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            NWZC170001_xxMsgIdListPMsg msgInfo = pMsg.xxMsgIdList.no(0);
            bizMsg.setMessageInfo(//
                    msgInfo.xxMsgId.getValue() //
                    , new String[] {msgInfo.xxMsgPrmTxt_0.getValue() //
                            , msgInfo.xxMsgPrmTxt_1.getValue() //
                            , msgInfo.xxMsgPrmTxt_2.getValue() //
                    });
            return;
        }

        bBizMsg.manPrcAmtRate_B.clear();
        ZYPEZDItemValueSetter.setValue(bBizMsg.unitPrcAmt_B, pMsg.xxDiscPrcAmt);
    }

    private static NWZC170001PMsg getFormulaApiPMsgForCharges(String glblCmpyCd, NWAL1660CMsg bizMsg, NWAL1660_BCMsg bBizMsg) {
        NWZC170001PMsg pMsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC170001.MODE_BASE);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcFmlaPk, bBizMsg.prcFmlaPk_B);
        ZYPEZDItemValueSetter.setValue(pMsg.ccyCd, bizMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, bizMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordTakeMdseCd //
                , S21StringUtil.subStringByLength(bizMsg.mdseCd.getValue(), 0, LEN_ORD_TAKE_MDSE));
        ZYPEZDItemValueSetter.setValue(pMsg.basePrcAmt, getBasePrcAmt(bizMsg));

        return pMsg;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    public static void setParamToBizMsg(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum, bizMsg.dsOrdPosnNum_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLineNum //
                , NWXC150001DsCheck.editDtlLineNum(null, bizMsg.dsCpoLineNum.getValue(), bizMsg.dsCpoLineSubNum.getValue()));
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, bizMsg.mdseCd_P);
        // Mod Start 2016/09/07 QC#11614
//        ZYPEZDItemValueSetter.setValue(bizMsg.mdseNm, getMdseNm(glblCmpyCd, bizMsg.mdseCd.getValue()));
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt, getMdseNm(glblCmpyCd, bizMsg.mdseCd.getValue()));
        // Mod End 2016/09/07 QC#11614

        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, bizMsg.xxSubTotCalcPrcAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, bizMsg.xxTotChrgPrcAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, bizMsg.xxTotTaxAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLineTotPrcAmt, bizMsg.xxLineTotPrcAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, bizMsg.ccyCd_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty, bizMsg.ordCustUomQty_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd, bizMsg.uomCd_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty, bizMsg.inEachQty_P);

        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt, bizMsg.dealSvcRevTrnsfAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt, bizMsg.dealSvcCostTrnsfAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt, bizMsg.dealManFlAdjAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt, bizMsg.dealManRepRevAdjAmt_P);
    }

    private static String getMdseNm(String glblCmpyCd, String mdseCd) {
        S21SsmEZDResult rslt = NWAL1660Query.getInstance().getMdseNm(glblCmpyCd, mdseCd);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        return (String) rslt.getResultObject();
    }

    /**
     * <pre>
     * @param specCondPrcPk specCondPrcPk
     * @param lBizMsgAry    NWAL1660_LCMsgArray
     * @return  ManAddFlg
     * </pre>
     */
    public static String getManAddFlg(BigDecimal specCondPrcPk, NWAL1660_LCMsgArray lBizMsgAry) {
        for (int i = 0; i < lBizMsgAry.getValidCount(); i++) {
            NWAL1660_LCMsg lBizMsg = lBizMsgAry.no(i);
            if (specCondPrcPk.compareTo(lBizMsg.specCondPrcPk_PL.getValue()) == 0) {
                return lBizMsg.prcCondManAddFlg_PL.getValue();
            }
        }
        return null;
    }

    /**
     * <pre>
     * @param lBizMsgAry    NWAL1660_LCMsgArray
     * @param specCondPrcPk specCondPrcPk
     * @return  NWAL1660_LCMsg
     * </pre>
     */
    public static NWAL1660_LCMsg getLBizMsg(NWAL1660_LCMsgArray lBizMsgAry, BigDecimal specCondPrcPk) {
        for (int i = 0; i < lBizMsgAry.getValidCount(); i++) {
            NWAL1660_LCMsg lBizMsg = lBizMsgAry.no(i);
            // QC#6098 2016/08/23 Add Start
            if(PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(lBizMsg.prcDtlGrpCd_PL.getValue())){
                continue;
            }
            if(PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(lBizMsg.prcDtlGrpCd_PL.getValue())){
                continue;
            }
            if(PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(lBizMsg.prcDtlGrpCd_PL.getValue())){
                continue;
            }
            // QC#6098 2016/08/23 Add Start
            if (specCondPrcPk.compareTo(lBizMsg.specCondPrcPk_PL.getValue()) == 0) {
                return lBizMsg;
            }
        }
        return null;
    }

    /**
     * <pre>
     * @param lBizMsgAry    NWAL1660_LCMsgArray
     * @param specCondPrcPk specCondPrcPk
     * @return  NWAL1660_LCMsg
     * </pre>
     */
    public static int getLBizMsgIdx(NWAL1660_LCMsgArray lBizMsgAry, BigDecimal specCondPrcPk) {
        for (int i = 0; i < lBizMsgAry.getValidCount(); i++) {
            NWAL1660_LCMsg lBizMsg = lBizMsgAry.no(i);
            if (specCondPrcPk.compareTo(lBizMsg.specCondPrcPk_PL.getValue()) == 0) {
                return i;
            }
        }
        return -1;
    }
    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * </pre>
     */
    // Mod Start 2019/07/29 QC#51742
//    public static void checkForClose(String glblCmpyCd, NWAL1660CMsg bizMsg) {
    public static boolean checkForClose(String glblCmpyCd, NWAL1660CMsg bizMsg) {
        if (NWAL1660CommonLogic.checkEssential(glblCmpyCd, bizMsg)) {
//            return;
            return true;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
            NWAL1660_LCMsg lBizMsg = getLBizMsg(bizMsg.L, aBizMsg.specCondPrcPk_A.getValue());

            if (checkCloseForSellPrice(aBizMsg, lBizMsg)) {
//                return;
                return true;
            }
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
            NWAL1660_LCMsg lBizMsg = getLBizMsg(bizMsg.L, bBizMsg.specCondPrcPk_B.getValue());

            if (checkCloseForCharges(bBizMsg, lBizMsg)) {
//                return;
                return true;
            }
        }

        return false;
    }
    // Mod End 2019/07/29 QC#51742

    private static boolean checkCloseForCharges(NWAL1660_BCMsg bBizMsg, NWAL1660_LCMsg lBizMsg) {
        boolean rtn = false;
        if(lBizMsg == null){
            return rtn;
        }
        if (ZYPCommonFunc.hasValue(bBizMsg.unitPrcAmt_B)) {
            if (ZYPCommonFunc.hasValue(lBizMsg.minPrcAmtRate_PL)) {
                if (lBizMsg.minPrcAmtRate_PL.getValue().compareTo(//
                        bBizMsg.unitPrcAmt_B.getValue()) > 0) {
                    bBizMsg.unitPrcAmt_B.setErrorInfo(1, NWAM0631E);
                    rtn = true;
                }
            }
            if (ZYPCommonFunc.hasValue(lBizMsg.maxPrcAmtRate_PL)) {
                if (lBizMsg.maxPrcAmtRate_PL.getValue().compareTo(//
                        bBizMsg.unitPrcAmt_B.getValue()) < 0) {
                    bBizMsg.unitPrcAmt_B.setErrorInfo(1, NWAM0557E);
                    rtn = true;
                }
            }
        }
        if (ZYPCommonFunc.hasValue(bBizMsg.manPrcAmtRate_B)) {
            if (ZYPCommonFunc.hasValue(lBizMsg.minPrcAmtRate_PL)) {
                if (lBizMsg.minPrcAmtRate_PL.getValue().compareTo(//
                        bBizMsg.manPrcAmtRate_B.getValue()) > 0) {
                    bBizMsg.manPrcAmtRate_B.setErrorInfo(1, NWAM0631E);
                    rtn = true;
                }
            }
            if (ZYPCommonFunc.hasValue(lBizMsg.maxPrcAmtRate_PL)) {
                if (lBizMsg.maxPrcAmtRate_PL.getValue().compareTo(//
                        bBizMsg.manPrcAmtRate_B.getValue()) < 0) {
                    bBizMsg.manPrcAmtRate_B.setErrorInfo(1, NWAM0557E);
                    rtn = true;
                }
            }
        }
        return rtn;
    }

    private static boolean checkCloseForSellPrice(NWAL1660_ACMsg aBizMsg, NWAL1660_LCMsg lBizMsg) {
        boolean rtn = false;
        if(lBizMsg == null){
            return rtn;
        }
        if (ZYPCommonFunc.hasValue(aBizMsg.unitPrcAmt_A)) {
            if (ZYPCommonFunc.hasValue(lBizMsg.minPrcAmtRate_PL)) {
                if (lBizMsg.minPrcAmtRate_PL.getValue().compareTo(//
                        aBizMsg.unitPrcAmt_A.getValue()) > 0) {
                    aBizMsg.unitPrcAmt_A.setErrorInfo(1, NWAM0631E);
                    rtn = true;
                }
            }
            if (ZYPCommonFunc.hasValue(lBizMsg.maxPrcAmtRate_PL)) {
                if (lBizMsg.maxPrcAmtRate_PL.getValue().compareTo(//
                        aBizMsg.unitPrcAmt_A.getValue()) < 0) {
                    aBizMsg.unitPrcAmt_A.setErrorInfo(1, NWAM0557E);
                    rtn = true;
                }
            }
        }
        if (ZYPCommonFunc.hasValue(aBizMsg.manPrcAmtRate_A)) {
            if (ZYPCommonFunc.hasValue(lBizMsg.minPrcAmtRate_PL)) {
                if (lBizMsg.minPrcAmtRate_PL.getValue().compareTo(//
                        aBizMsg.manPrcAmtRate_A.getValue()) > 0) {
                    aBizMsg.manPrcAmtRate_A.setErrorInfo(1, NWAM0631E);
                    rtn = true;
                }
            }
            if (ZYPCommonFunc.hasValue(lBizMsg.maxPrcAmtRate_PL)) {
                if (lBizMsg.maxPrcAmtRate_PL.getValue().compareTo(//
                        aBizMsg.manPrcAmtRate_A.getValue()) < 0) {
                    aBizMsg.manPrcAmtRate_A.setErrorInfo(1, NWAM0557E);
                    rtn = true;
                }
            }
        }
        return rtn;
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

    private static BigDecimal multiply(BigDecimal arg0, BigDecimal arg1) {
        if (arg0 == null) {
            arg0 = BigDecimal.ZERO;
        }
        if (arg1 == null) {
            arg1 = BigDecimal.ZERO;
        }
        return arg0.multiply(arg1);
    }

    private static BigDecimal divide(BigDecimal arg0, BigDecimal arg1) {
        if (arg0 == null) {
            arg0 = BigDecimal.ZERO;
        }
        if (arg1 == null) {
            arg1 = BigDecimal.ZERO;
        }
        return arg0.divide(arg1);
    }

    private static boolean isEquals(BigDecimal arg0, BigDecimal arg1) {
        if (arg0 == null) {
            arg0 = BigDecimal.ZERO;
        }
        if (arg1 == null) {
            arg1 = BigDecimal.ZERO;
        }
        if (arg0.compareTo(arg1) == 0) {
            return true;
        }
        return false;
    }

    // QC#22965 2018/04/11 Add Start
    public static void setParamToBizMsgForHeaderLevel(String glblCmpyCd, NWAL1660CMsg bizMsg){
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, bizMsg.xxSubTotCalcPrcAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, bizMsg.xxTotChrgPrcAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, bizMsg.xxTotTaxAmt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLineTotPrcAmt, bizMsg.xxLineTotPrcAmt_P);

        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWAL1660_LCMsg blMsg = bizMsg.L.no(i);

            // QC#9700 2018/09/03 Add Start
            if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, blMsg.prcRuleApplyFlg_PL.getValue())) {
                continue;
            }
            // QC#9700 2018/09/03 Add End
            if (isSellPrice(blMsg.prcDtlGrpCd_PL.getValue())) {
                editBizMsgSellPrice(glblCmpyCd, bizMsg, blMsg);
            } else if (isCharges(blMsg.prcDtlGrpCd_PL.getValue())) {
                editBizMsgCharges(glblCmpyCd, bizMsg, blMsg);
            }
        }
    }

    private static boolean compareData(NWAL1660_LCMsg lBizMsg, NWAL1660_ACMsg aBizMsg) {
        String prcCondManDelFlg = ZYPConstant.FLG_OFF_N;
        BigDecimal manPrcAmtRate = null;
        if (ZYPCommonFunc.hasValue(aBizMsg.prcCondManDelFlg_A)) {
            prcCondManDelFlg = aBizMsg.prcCondManDelFlg_A.getValue();
        }
        if (!S21StringUtil.isEquals(lBizMsg.prcCondManDelFlg_PL.getValue(), prcCondManDelFlg)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(lBizMsg.manPrcAmtRate_PL)) {
            return false;
        }
        if (PRC_COND_UNIT.PCT.equals(aBizMsg.prcCondUnitCd_A.getValue())) {
            manPrcAmtRate = divide(aBizMsg.manPrcAmtRate_A.getValue(), NWAL1660Constant.PCT_100);
            if (!isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), manPrcAmtRate)) {
                return false;
            }
        } else {
            if (!isEquals(lBizMsg.calcPrcAmtRate_PL.getValue(), aBizMsg.unitPrcAmt_A.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareData(NWAL1660_LCMsg lBizMsg, NWAL1660_BCMsg bBizMsg) {
        String prcCondManDelFlg = ZYPConstant.FLG_OFF_N;
        BigDecimal manPrcAmtRate = null;
        if(ZYPCommonFunc.hasValue(bBizMsg.prcCondManDelFlg_B)){
            prcCondManDelFlg = bBizMsg.prcCondManDelFlg_B.getValue();
        }
        if(ZYPCommonFunc.hasValue(lBizMsg.manPrcAmtRate_PL)){
            return false;
        }
        if (!S21StringUtil.isEquals(lBizMsg.prcCondManDelFlg_PL.getValue(), prcCondManDelFlg)) {
            return false;
        }
        if(ZYPCommonFunc.hasValue(lBizMsg.manPrcAmtRate_PL)){
            return false;
        }
        if (PRC_COND_UNIT.PCT.equals(bBizMsg.prcCondUnitCd_B.getValue())) {
            manPrcAmtRate = divide(bBizMsg.manPrcAmtRate_B.getValue(), NWAL1660Constant.PCT_100);
            if (!isEquals(lBizMsg.autoPrcAmtRate_PL.getValue(), manPrcAmtRate)) {
                return false;
            }
        } else {
            if (!isEquals(lBizMsg.calcPrcAmtRate_PL.getValue(), bBizMsg.unitPrcAmt_B.getValue())) {
                return false;
            }
        }
        return true;
    }
    // QC#22965 2018/04/11 Add End
    // QC#21841 2018/05/23 Add Start
    private static String getPrcDtlGrpCd(String glblCmpyCd, String prcRuleAdjTpCd) {
        PRC_RULE_ADJ_TPTMsg prcRuleAdjTpTMsg = new PRC_RULE_ADJ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prcRuleAdjTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcRuleAdjTpTMsg.prcRuleAdjTpCd, prcRuleAdjTpCd);
        prcRuleAdjTpTMsg = (PRC_RULE_ADJ_TPTMsg) S21CacheTBLAccessor.findByKey(prcRuleAdjTpTMsg);
        if (prcRuleAdjTpTMsg != null) {
            return prcRuleAdjTpTMsg.prcDtlGrpCd.getValue();
        }
        return "";
    }
    // QC#21841 2018/05/23 Add End
}
