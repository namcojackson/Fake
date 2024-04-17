/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7020;

import static business.blap.NMAL7020.constant.NMAL7020Constant.NMAM0009E;
import static business.blap.NMAL7020.constant.NMAL7020Constant.NMAM0072E;
import static business.blap.NMAL7020.constant.NMAL7020Constant.NMAM0176E;
import static business.blap.NMAL7020.constant.NMAL7020Constant.NMAM8061E;
import static business.blap.NMAL7020.constant.NMAL7020Constant.NMAM8186E;
import static business.blap.NMAL7020.constant.NMAL7020Constant.NMAM8447E;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_EQUIP;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_EQUIP_LEASE_RATES;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_EQUIP_LEASE_RETURN;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_EQUIP_SUPPLY;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_EQUIP_TRADE;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_SERVICE;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_SERVICE_SPECIAL;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_STS_SERVICE_TIERS;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_ADDL_CHRG;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_EQUIP;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_LEASE_RATE;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_LEASE_RTRN;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_SPLY;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_SVC;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_SVC_TIER;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_LIST_TABLE_TI_VAL;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.COPY_PRC_LIST_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COPY_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EFF_APPLY_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PCT_AMT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7020BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7020CMsg bizMsg = (NMAL7020CMsg) cMsg;

            if ("NMAL7020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7020Scrn00_CMN_Submit(bizMsg);

            } else {
                return;
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
    private void doProcess_NMAL7020Scrn00_CMN_Submit(NMAL7020CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL7020Query.getInstance().checkExistPrcCatgNm(bizMsg.prcCatgNm_O.getValue());
        if (ssmResult.isCodeNotFound()) {
            bizMsg.prcCatgNm_O.setErrorInfo(1, NMAM8186E, new String[] {bizMsg.prcCatgNm_O.getValue() });
            return;
        }
        boolean returnFlag = false;

        returnFlag = isExistPrcCatgNm(bizMsg, returnFlag);
        String listType = NMAL7020Query.getInstance().getPriceListType(bizMsg);
        returnFlag = checkPricelistType(bizMsg, returnFlag, listType);
        returnFlag = isHeaderDetailPeriodOver(bizMsg, listType, returnFlag);

        if (returnFlag) {
            return;
        }

        String slsDt = ZYPDateUtil.getSalesDate();
        COPY_PRC_LIST_RQSTTMsg copyRsltTpTmsg = new COPY_PRC_LIST_RQSTTMsg();
        setCopyPrcListRqstValue(bizMsg, slsDt, copyRsltTpTmsg);

        EZDTBLAccessor.create(copyRsltTpTmsg);
        if (!RTNCD_NORMAL.equals(copyRsltTpTmsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {"COPY_PRC_LIST_RQST" });
            return;
        }
    }

    private boolean isExistPrcCatgNm(NMAL7020CMsg bizMsg, boolean returnFlag) {
        S21SsmEZDResult ssmResult;
        ssmResult = NMAL7020Query.getInstance().checkExistPrcCatgNm(bizMsg.prcCatgNm_N.getValue());
        if (!ssmResult.isCodeNotFound()) {
            bizMsg.prcCatgNm_N.setErrorInfo(1, NMAM0072E, new String[] {bizMsg.prcCatgNm_N.getValue() });
            returnFlag = true;
        }

        ssmResult = NMAL7020Query.getInstance().checkExistRqst(bizMsg.prcCatgNm_N.getValue());
        if (!ssmResult.isCodeNotFound()) {
            bizMsg.prcCatgNm_N.setErrorInfo(1, NMAM0072E, new String[] {bizMsg.prcCatgNm_N.getValue() });
            returnFlag = true;
        }
        return returnFlag;
    }

    private boolean checkPricelistType(NMAL7020CMsg bizMsg, boolean returnFlag, String listType) {
        if (!ZYPCommonFunc.hasValue(listType)) {
            bizMsg.prcCatgNm_O.setErrorInfo(1, NMAM0009E, new String[] {bizMsg.prcCatgNm_O.getValue() });
            returnFlag = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcCalcTpCd) && ZYPCommonFunc.hasValue(bizMsg.prcPctAmtTpCd) && ZYPCommonFunc.hasValue(bizMsg.copyPrcAmtRate) && !PRC_LIST_STS_EQUIP.equals(listType)) {
            bizMsg.prcCatgNm_O.setErrorInfo(1, NMAM8447E);
            returnFlag = true;
        }
        return returnFlag;
    }

    private boolean isHeaderDetailPeriodOver(NMAL7020CMsg bizMsg, String listType, boolean returnFlag) {

        String tableName = "";
        if (PRC_LIST_STS_EQUIP.equals(listType)) {
            tableName = PRC_LIST_TABLE_EQUIP;
        } else if (PRC_LIST_STS_SERVICE.equals(listType)) {
            tableName = PRC_LIST_TABLE_SVC;
        } else if (PRC_LIST_STS_SERVICE_TIERS.equals(listType)) {
            tableName = PRC_LIST_TABLE_SVC_TIER;
        } else if (PRC_LIST_STS_SERVICE_SPECIAL.equals(listType)) {
            tableName = PRC_LIST_TABLE_ADDL_CHRG;
        } else if (PRC_LIST_STS_EQUIP_SUPPLY.equals(listType)) {
            tableName = PRC_LIST_TABLE_SPLY;
        } else if (PRC_LIST_STS_EQUIP_LEASE_RATES.equals(listType)) {
            tableName = PRC_LIST_TABLE_LEASE_RATE;
        } else if (PRC_LIST_STS_EQUIP_LEASE_RETURN.equals(listType)) {
            tableName = PRC_LIST_TABLE_LEASE_RTRN;
        } else if (PRC_LIST_STS_EQUIP_TRADE.equals(listType)) {
            tableName = PRC_LIST_TABLE_TI_VAL;
        }

        S21SsmEZDResult priceListPeriodResult = NMAL7020Query.getInstance().getPriceListPeriod(bizMsg.prcCatgNm_O.getValue(), tableName);
        Map<String, Object> priceListPeriodMap = (Map<String, Object>) priceListPeriodResult.getResultObject();

        String headerFrom = (String) priceListPeriodMap.get("HEADER_FROM");
        String detailFrom = (String) priceListPeriodMap.get("DETAIL_FROM");


        if (EFF_APPLY_LVL_TP.HEADER_ONLY.equals(bizMsg.effApplyLvlTpCd.getValue())) {
            headerFrom = bizMsg.effFromDt.getValue();

        } else if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(bizMsg.effApplyLvlTpCd.getValue())) {
            detailFrom = bizMsg.effFromDt.getValue();

        } else if (EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(bizMsg.effApplyLvlTpCd.getValue())) {
            headerFrom = bizMsg.effFromDt.getValue();
            detailFrom = bizMsg.effFromDt.getValue();
        }

        if (ZYPCommonFunc.hasValue(detailFrom)) {
            if (headerFrom.compareTo(detailFrom) > 0) {
                if (ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
                    bizMsg.effFromDt.setErrorInfo(1, NMAM8061E, new String[] {"Effective Date From(Price List Value Level)", "Effective Date From(Header Level)" });
                } else {
                    bizMsg.prcCatgNm_O.setErrorInfo(1, NMAM8061E, new String[] {"Effective Date From(Price List Value Level)", "Effective Date From(Header Level)" });
                }
                returnFlag = true;

            }
        }

        return returnFlag;
    }

    private void setCopyPrcListRqstValue(NMAL7020CMsg bizMsg, String slsDt, COPY_PRC_LIST_RQSTTMsg copyRsltTpTmsg) {
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal copyprcListRqstpk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.COPY_PRC_LIST_RQST_SQ);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyPrcListRqstPk, copyprcListRqstpk);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.oldPrcListNm, bizMsg.prcCatgNm_O);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.newPrcListNm, bizMsg.prcCatgNm_N);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.newPrcCatgDescTxt, bizMsg.newPrcCatgDescTxt);

        if (ZYPCommonFunc.hasValue(bizMsg.actvFlg)) {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.actvFlg, bizMsg.actvFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.mainPrcListInfoFlg)) {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.mainPrcListInfoFlg, bizMsg.mainPrcListInfoFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.mainPrcListInfoFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcListAccsTpFlg)) {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.prcListAccsTpFlg, bizMsg.prcListAccsTpFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.prcListAccsTpFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.copyAttrbTrxFlg)) {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyAttrbTrxFlg, bizMsg.copyAttrbTrxFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyAttrbTrxFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.copyPrcFlg)) {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyPrcFlg, bizMsg.copyPrcFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyPrcFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.prcCalcTpCd, bizMsg.prcCalcTpCd);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.prcPctAmtTpCd, bizMsg.prcPctAmtTpCd);

        if (PRC_PCT_AMT_TP.PERCENT.equals(bizMsg.prcPctAmtTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyPrcAmtRate, bizMsg.copyPrcAmtRate.getValue().divide(new BigDecimal(100)));
        } else {
            ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyPrcAmtRate, bizMsg.copyPrcAmtRate);
        }

        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.effFromDt, bizMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.effToDt, bizMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.applyLvlEffDtTpCd, bizMsg.effApplyLvlTpCd);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.copyRsltTpCd, COPY_RSLT_TP.SUBMITTED);
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.rqstUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(copyRsltTpTmsg.rqstCratDt, slsDt);
    }

}
