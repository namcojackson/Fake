/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC116001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDPItem;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC116001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * <pre>
 * Update Machine Attribute from External System API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/11   Hitachi         K.Kitachi       Create          QC#18056
 * </pre>
 */
public class NSZC116001 extends S21ApiCommonBase {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    private static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    private static final String NSZM0002E = "NSZM0002E";

    /**
     * Service machine master is not found.
     */
    private static final String NSZM0006E = "NSZM0006E";

    /**
     * Input parameter is machine cannot be identified.
     */
    private static final String NSZM1117E = "NSZM1117E";

    /**
     * Constructor
     */
    public NSZC116001() {
        super();
    }

    /**
     * execute Update Machine Attribute from External System API.
     * @param param NSZC116001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC116001PMsg param, final ONBATCH_TYPE onBatTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            updateMachineAttribute(msgMap, onBatTp);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC116001PMsg pMsg = (NSZC116001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.slsDt, NSZM0002E);

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

    private void updateMachineAttribute(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatTp) {
        NSZC116001PMsg pMsg = (NSZC116001PMsg) msgMap.getPmsg();
        SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg = null;
        SVC_MACH_MSTRTMsgArray exstSvcMachMstrTMsgArray = null;
        if (hasValue(pMsg.svcMachMstrPk)) {
            exstSvcMachMstrTMsg = findSvcMachMstrTMsgByPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (exstSvcMachMstrTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
        } else if (hasValue(pMsg.serNum)) {
            if (hasValue(pMsg.mdseCd)) {
                exstSvcMachMstrTMsgArray = findSvcMachMstrTMsgArrayBySerMdse(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue(), pMsg.mdseCd.getValue());
            } else {
                exstSvcMachMstrTMsgArray = findSvcMachMstrTMsgArrayBySer(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue());
            }
            if (exstSvcMachMstrTMsgArray.getValidCount() == 0) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
            if (exstSvcMachMstrTMsgArray.getValidCount() > 1) {
                msgMap.addXxMsgId(NSZM1117E);
                return;
            }
            exstSvcMachMstrTMsg = exstSvcMachMstrTMsgArray.no(0);
        } else {
            msgMap.addXxMsgId(NSZM1117E);
            return;
        }

        NSZC001001PMsg nszc001001PMsg = createNSZC001001PMsg(pMsg, exstSvcMachMstrTMsg);
        NSZC001001 nszc001001API = new NSZC001001();
        nszc001001API.execute(nszc001001PMsg, onBatTp);

        if (S21ApiUtil.isXxMsgId(nszc001001PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc001001PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
            }
        }
    }

    private SVC_MACH_MSTRTMsg findSvcMachMstrTMsgByPk(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(prmTMsg);
    }

    private SVC_MACH_MSTRTMsgArray findSvcMachMstrTMsgArrayBySerMdse(String glblCmpyCd, String serNum, String mdseCd) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("serNum01", serNum);
        prmTMsg.setConditionValue("mdseCd01", mdseCd);
        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }

    private SVC_MACH_MSTRTMsgArray findSvcMachMstrTMsgArrayBySer(String glblCmpyCd, String serNum) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("serNum01", serNum);
        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }

    private NSZC001001PMsg createNSZC001001PMsg(NSZC116001PMsg pMsg, SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg) {
        NSZC001001PMsg nszc001001PMsg = new NSZC001001PMsg();
        setValue(nszc001001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(nszc001001PMsg.slsDt, pMsg.slsDt);
        setValue(nszc001001PMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
        setValue(nszc001001PMsg.svcMachMstrPk, exstSvcMachMstrTMsg.svcMachMstrPk);
        setValue(nszc001001PMsg.prfTechCd, selectValue(pMsg.prfTechCd.getValue(), exstSvcMachMstrTMsg.prfTechCd.getValue()));
        setValue(nszc001001PMsg.custIssPoNum, selectValue(pMsg.custIssPoNum.getValue(), exstSvcMachMstrTMsg.custIssPoNum.getValue()));
        setValue(nszc001001PMsg.iwrCondCd, selectValue(pMsg.iwrCondCd.getValue(), exstSvcMachMstrTMsg.iwrCondCd.getValue()));
        setValue(nszc001001PMsg.dsKeyAcctFlg, selectValue(pMsg.dsKeyAcctFlg.getValue(), exstSvcMachMstrTMsg.dsKeyAcctFlg.getValue()));
        setValue(nszc001001PMsg.svcByLineBizTpCd, selectValue(pMsg.svcByLineBizTpCd.getValue(), exstSvcMachMstrTMsg.svcByLineBizTpCd.getValue()));
        setValue(nszc001001PMsg.bizHrsSunFromTm, selectValue(pMsg.bizHrsSunFromTm.getValue(), exstSvcMachMstrTMsg.bizHrsSunFromTm.getValue()));
        setValue(nszc001001PMsg.bizHrsSunToTm, selectValue(pMsg.bizHrsSunToTm.getValue(), exstSvcMachMstrTMsg.bizHrsSunToTm.getValue()));
        setValue(nszc001001PMsg.bizHrsMonFriFromTm, selectValue(pMsg.bizHrsMonFriFromTm.getValue(), exstSvcMachMstrTMsg.bizHrsMonFriFromTm.getValue()));
        setValue(nszc001001PMsg.bizHrsMonFriToTm, selectValue(pMsg.bizHrsMonFriToTm.getValue(), exstSvcMachMstrTMsg.bizHrsMonFriToTm.getValue()));
        setValue(nszc001001PMsg.bizHrsSatFromTm, selectValue(pMsg.bizHrsSatFromTm.getValue(), exstSvcMachMstrTMsg.bizHrsSatFromTm.getValue()));
        setValue(nszc001001PMsg.bizHrsSatToTm, selectValue(pMsg.bizHrsSatToTm.getValue(), exstSvcMachMstrTMsg.bizHrsSatToTm.getValue()));
        setValue(nszc001001PMsg.fldSvcBrCd, selectValue(pMsg.fldSvcBrCd.getValue(), exstSvcMachMstrTMsg.fldSvcBrCd.getValue()));
        setValue(nszc001001PMsg.reqTechCd, selectValue(pMsg.reqTechCd.getValue(), exstSvcMachMstrTMsg.reqTechCd.getValue()));
        setValue(nszc001001PMsg.ctrlFldTxt_01, selectValue(pMsg.ctrlFldTxt_01.getValue(), exstSvcMachMstrTMsg.ctrlFldTxt_01.getValue()));
        setValue(nszc001001PMsg.ctrlFldTxt_02, selectValue(pMsg.ctrlFldTxt_02.getValue(), exstSvcMachMstrTMsg.ctrlFldTxt_02.getValue()));
        setValue(nszc001001PMsg.ctrlFldTxt_03, selectValue(pMsg.ctrlFldTxt_03.getValue(), exstSvcMachMstrTMsg.ctrlFldTxt_03.getValue()));
        setValue(nszc001001PMsg.ctrlFldTxt_04, selectValue(pMsg.ctrlFldTxt_04.getValue(), exstSvcMachMstrTMsg.ctrlFldTxt_04.getValue()));
        setValue(nszc001001PMsg.ctrlFldTxt_05, selectValue(pMsg.ctrlFldTxt_05.getValue(), exstSvcMachMstrTMsg.ctrlFldTxt_05.getValue()));
        setValue(nszc001001PMsg.ctrlFldTxt_06, selectValue(pMsg.ctrlFldTxt_06.getValue(), exstSvcMachMstrTMsg.ctrlFldTxt_06.getValue()));
        setValue(nszc001001PMsg.prcContrNum, selectValue(pMsg.prcContrNum.getValue(), exstSvcMachMstrTMsg.prcContrNum.getValue()));
        setValue(nszc001001PMsg.corpAdvtgStsCd, selectValue(pMsg.corpAdvtgStsCd.getValue(), exstSvcMachMstrTMsg.corpAdvtgStsCd.getValue()));
        setValue(nszc001001PMsg.dsPoExprDt, selectValue(pMsg.dsPoExprDt.getValue(), exstSvcMachMstrTMsg.dsPoExprDt.getValue()));
        setValue(nszc001001PMsg.hardDriveRmvInclFlg, selectValue(pMsg.hardDriveRmvInclFlg.getValue(), exstSvcMachMstrTMsg.hardDriveRmvInclFlg.getValue()));
        setValue(nszc001001PMsg.hardDriveEraseInclFlg, selectValue(pMsg.hardDriveEraseInclFlg.getValue(), exstSvcMachMstrTMsg.hardDriveEraseInclFlg.getValue()));
        setValue(nszc001001PMsg.leaseRtrnFeeInclFlg, selectValue(pMsg.leaseRtrnFeeInclFlg.getValue(), exstSvcMachMstrTMsg.leaseRtrnFeeInclFlg.getValue()));
        setValue(nszc001001PMsg.svcNtwkConnStsCd, selectValue(pMsg.svcNtwkConnStsCd.getValue(), exstSvcMachMstrTMsg.svcNtwkConnStsCd.getValue()));
        setValue(nszc001001PMsg.sldByLineBizTpCd, selectValue(pMsg.sldByLineBizTpCd.getValue(), exstSvcMachMstrTMsg.sldByLineBizTpCd.getValue()));
        setValue(nszc001001PMsg.svcLicCnt, selectValue(pMsg.svcLicCnt.getValue(), exstSvcMachMstrTMsg.svcLicCnt.getValue()));
        setValue(nszc001001PMsg.finBrCd, selectValue(pMsg.finBrCd.getValue(), exstSvcMachMstrTMsg.finBrCd.getValue()));
        setValue(nszc001001PMsg.svcMachMstrLocStsCd, selectValue(pMsg.svcMachMstrLocStsCd.getValue(), exstSvcMachMstrTMsg.svcMachMstrLocStsCd.getValue()));
        setValue(nszc001001PMsg.enetPlotFlg, selectValue(pMsg.enetPlotFlg.getValue(), exstSvcMachMstrTMsg.enetPlotFlg.getValue()));
        setValue(nszc001001PMsg.enetCmntTxt_01, selectValue(pMsg.enetCmntTxt_01.getValue(), exstSvcMachMstrTMsg.enetCmntTxt_01.getValue()));
        setValue(nszc001001PMsg.enetCmntTxt_02, selectValue(pMsg.enetCmntTxt_02.getValue(), exstSvcMachMstrTMsg.enetCmntTxt_02.getValue()));
        return nszc001001PMsg;
    }

    private String selectValue(String item1, String item2) {
        if (hasValue(item1)) {
            return item1;
        }
        return item2;
    }

    private BigDecimal selectValue(BigDecimal item1, BigDecimal item2) {
        if (hasValue(item1)) {
            return item1;
        }
        return item2;
    }
}
