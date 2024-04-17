package com.canon.cusa.s21.api.NSZ.NSZC071001;

import java.math.BigDecimal;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

import business.db.TECH_UPD_INFOTMsg;
import business.db.TECH_UPD_INFO_MULT_VALTMsg;
import business.parts.NSZC071001PMsg;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.api.NSZ.NSZC071001.constant.NSZC071001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Technician Resource Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2015   Hitachi         T.Harada        Create
 * </pre>
 */
public class NSZC071001 extends S21ApiCommonBase {
    /**
     * Constructor
     */
    public NSZC071001() {
        super();
    }

    /**
     * Technician Resource Update API
     * @param pMsg {@link NSZC071001PMsg}
     * @param onBatchType Online Batch Type
     */
    public void execute(NSZC071001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap)) {
            doProcess(msgMap, pMsg);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC071001PMsg pMsg = (NSZC071001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.techPsnCd, NSZM0702E);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC071001PMsg pMsg) {
        executeInsert(msgMap, pMsg);
    }

    private void executeInsert(S21ApiMessageMap msgMap, NSZC071001PMsg pMsg) {
        BigDecimal techUpdInfoSQ = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_UPD_INFO_SQ);
        createTechUpdInfo(msgMap, pMsg, techUpdInfoSQ);
        createTechUpdInfoMultVal(msgMap, pMsg, techUpdInfoSQ);
    }

    private void createTechUpdInfo(S21ApiMessageMap msgMap, NSZC071001PMsg pMsg, BigDecimal techUpdInfoSQ) {
        TECH_UPD_INFOTMsg techUpdInfo = new TECH_UPD_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(techUpdInfo.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techUpdInfoPk, techUpdInfoSQ);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techPsnCd, pMsg.techPsnCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techPsnNm, pMsg.techPsnNm);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techCalTxt, pMsg.techCalTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.engnrTpTxt, pMsg.engnrTpTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.actvFlg, defaultFlgY(pMsg.actvFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(techUpdInfo.trvlPcyTxt, pMsg.trvlPcyTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.disptPcyTxt, pMsg.disptPcyTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcLineBizCd, pMsg.svcLineBizCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcZnCd, pMsg.svcZnCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcRgCd, pMsg.svcRgCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcBrCd, pMsg.svcBrCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcTeamTxt, pMsg.svcTeamTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcBrMgrPsnNm, pMsg.svcBrMgrPsnNm);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcTrtyMgrPsnNm, pMsg.svcTrtyMgrPsnNm);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcTeamMgrPsnNm, pMsg.svcTeamMgrPsnNm);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.postCd, pMsg.postCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.ctryCd, pMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcLttdNum, pMsg.svcLttdNum);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.svcLgtdNum, pMsg.svcLgtdNum);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techEfncyTxt, pMsg.techEfncyTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.lunchBreakDurnTxt, pMsg.lunchBreakDurnTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techCellPhoNum, pMsg.techCellPhoNum);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.physInvtyFlg, defaultFlgY(pMsg.physInvtyFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techBizTxt, pMsg.techBizTxt);
        ZYPEZDItemValueSetter.setValue(techUpdInfo.techEmlAddr, pMsg.techEmlAddr);
        S21ApiTBLAccessor.insert(techUpdInfo);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(techUpdInfo.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0648E);
        }
    }

    private void createTechUpdInfoMultVal(S21ApiMessageMap msgMap, NSZC071001PMsg pMsg, BigDecimal techUpdInfoSQ) {
        for (int i = 0; i < pMsg.MultiValueList.getValidCount(); i++) {
            TECH_UPD_INFO_MULT_VALTMsg techUpdInfoMultVal = new TECH_UPD_INFO_MULT_VALTMsg();
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdInfoMultValPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_UPD_INFO_MULT_VAL_SQ));
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdInfoPk, techUpdInfoSQ);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdLvlTxt, pMsg.MultiValueList.no(i).techUpdLvlTxt);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_01, pMsg.MultiValueList.no(i).techUpdAttrbTxt_01);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_02, pMsg.MultiValueList.no(i).techUpdAttrbTxt_02);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_03, pMsg.MultiValueList.no(i).techUpdAttrbTxt_03);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_04, pMsg.MultiValueList.no(i).techUpdAttrbTxt_04);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_05, pMsg.MultiValueList.no(i).techUpdAttrbTxt_05);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_06, pMsg.MultiValueList.no(i).techUpdAttrbTxt_06);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_07, pMsg.MultiValueList.no(i).techUpdAttrbTxt_07);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_08, pMsg.MultiValueList.no(i).techUpdAttrbTxt_08);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_09, pMsg.MultiValueList.no(i).techUpdAttrbTxt_09);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdAttrbTxt_10, pMsg.MultiValueList.no(i).techUpdAttrbTxt_10);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_01, pMsg.MultiValueList.no(i).techUpdValTxt_01);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_02, pMsg.MultiValueList.no(i).techUpdValTxt_02);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_03, pMsg.MultiValueList.no(i).techUpdValTxt_03);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_04, pMsg.MultiValueList.no(i).techUpdValTxt_04);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_05, pMsg.MultiValueList.no(i).techUpdValTxt_05);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_06, pMsg.MultiValueList.no(i).techUpdValTxt_06);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_07, pMsg.MultiValueList.no(i).techUpdValTxt_07);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_08, pMsg.MultiValueList.no(i).techUpdValTxt_08);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_09, pMsg.MultiValueList.no(i).techUpdValTxt_09);
            ZYPEZDItemValueSetter.setValue(techUpdInfoMultVal.techUpdValTxt_10, pMsg.MultiValueList.no(i).techUpdValTxt_10);
            S21ApiTBLAccessor.insert(techUpdInfoMultVal);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(techUpdInfoMultVal.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0648E);
            }
        }
    }

    private String defaultFlgY(String inFlg) {
        if (!hasValue(inFlg)) {
            return FLG_ON_Y;
        }
        if (!hasValue(inFlg.trim())) {
            return FLG_ON_Y;
        }
        return inFlg;
    }
}
