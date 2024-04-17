/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1550;

import static business.blap.NWAL1550.constant.NWAL1550Constant.DI_CHK_HLD_FLG_OFF;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0010E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0142E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0189E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWZM1840E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.OPEN_FLG_OFF;
import static business.blap.NWAL1550.constant.NWAL1550Constant.ZZZM9004E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.DS_CPO_DI_CHK_RSLT_DTLTMsg;
import business.db.DS_CPO_DI_CHK_RSLT_HDRTMsg;
import business.parts.NWZC033001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL1550BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1550BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1550CMsg bizMsg = (NWAL1550CMsg) cMsg;

            if ("NWAL1550Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1550Scrn00_CMN_Submit(bizMsg);

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
     */
    private void doProcess_NWAL1550Scrn00_CMN_Submit(NWAL1550CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1550Query.getInstance().getCpoDtlV(bizMsg, OPEN_FLG_OFF);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(NWAM0142E);
            return;
        }

        // Version existence check
        ssmResult = NWAL1550Query.getInstance().getRsltHdrByVerNum(bizMsg);
        int rsltHdrVerNumCount = (Integer) ssmResult.getResultObject();

        if (rsltHdrVerNumCount == 0) {
            bizMsg.diChkVrsnNum_SL.setErrorInfo(1, NWZM1840E);
            bizMsg.setMessageInfo(NWAM0010E);
            return;
        }

        // Check the latest version
        ssmResult = NWAL1550Query.getInstance().getRsltHdrMaxVerNum(bizMsg);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.diChkVrsnNum_SL.setErrorInfo(1, NWZM1840E);
            bizMsg.setMessageInfo(NWAM0010E);
            return;
        }

        Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
        BigDecimal maxNum = (BigDecimal) resultMap.get("MAX_NUM");
        BigDecimal dsCpoDiChkRsltHdrPk = (BigDecimal) resultMap.get("DS_CPO_DI_CHK_RSLT_HDR_PK");

        if (maxNum.compareTo(bizMsg.diChkVrsnNum_BK.getValue()) != 0) {
            bizMsg.diChkVrsnNum_SL.setErrorInfo(1, NWZM1840E);
            bizMsg.setMessageInfo(NWAM0010E);
            return;
        }

        // Get the updated data, and performs exclusive check, and
        // exclusive control
        List<DS_CPO_DI_CHK_RSLT_DTLTMsg> updRsltDtlMsglist = new ArrayList<DS_CPO_DI_CHK_RSLT_DTLTMsg>();
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        String userId = userProfSvc.getContextUserInfo().getUserId();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (DI_CHK_DTL_STS.WARNING.equals(bizMsg.A.no(i).diChkDtlStsCd_A.getValue())
                && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A.getValue())) {

                DS_CPO_DI_CHK_RSLT_DTLTMsg rsltDtlTMsg = new DS_CPO_DI_CHK_RSLT_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rsltDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(rsltDtlTMsg.dsCpoDiChkRsltHdrPk, dsCpoDiChkRsltHdrPk);
                ZYPEZDItemValueSetter.setValue(rsltDtlTMsg.dsCpoDiChkRsltDtlPk, bizMsg.A.no(i).dsCpoDiChkRsltDtlPk_A);

                DS_CPO_DI_CHK_RSLT_DTLTMsg updRsltDtlTMsg = (DS_CPO_DI_CHK_RSLT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rsltDtlTMsg);
                if (!ZYPDateUtil.isSameTimeStamp(updRsltDtlTMsg.ezUpTime.getValue(), updRsltDtlTMsg.ezUpTimeZone.getValue(), bizMsg.A.no(i).ezUpTime_A.getValue(), bizMsg.A.no(i).ezUpTimeZone_A.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(updRsltDtlTMsg.diChkDtlStsCd, DI_CHK_DTL_STS.ACCEPTED);
                ZYPEZDItemValueSetter.setValue(updRsltDtlTMsg.diChkDtlAcptDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(updRsltDtlTMsg.diChkDtlAcptPsnCd, userId);
                updRsltDtlMsglist.add(updRsltDtlTMsg);
            }
        }

        // Update DS_CPO_DI_CHK_RSLT_DTL
        DS_CPO_DI_CHK_RSLT_DTLTMsg[] tMsg = (DS_CPO_DI_CHK_RSLT_DTLTMsg[]) updRsltDtlMsglist.toArray(new DS_CPO_DI_CHK_RSLT_DTLTMsg[0]);
        S21FastTBLAccessor.update(tMsg);

        // Check for all of the error / warning has been canceled
        ssmResult = NWAL1550Query.getInstance().getRsltDtlStsCd(dsCpoDiChkRsltHdrPk);
        int rsltDtlStsCount = (Integer) ssmResult.getResultObject();

        if (rsltDtlStsCount > 0) {
            return;
        }

        // Update the status to "Accepted"
        DS_CPO_DI_CHK_RSLT_HDRTMsg rsltHdrTMsg = new DS_CPO_DI_CHK_RSLT_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rsltHdrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rsltHdrTMsg.dsCpoDiChkRsltHdrPk, dsCpoDiChkRsltHdrPk);
        DS_CPO_DI_CHK_RSLT_HDRTMsg updRsltHdrTMsg = (DS_CPO_DI_CHK_RSLT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rsltHdrTMsg);

        ZYPEZDItemValueSetter.setValue(updRsltHdrTMsg.diChkStsCd, DI_CHK_DTL_STS.ACCEPTED);
        ZYPEZDItemValueSetter.setValue(updRsltHdrTMsg.diChkHdrAcptDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(updRsltHdrTMsg.diChkHdrAcptPsnCd, userId);

        S21FastTBLAccessor.update(updRsltHdrTMsg);

        // To release the Hold
        CPOTMsg dsCpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        CPOTMsg updDsCpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsCpoTMsg);

        ZYPEZDItemValueSetter.setValue(updDsCpoTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(updDsCpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(updDsCpoTMsg.diChkHldFlg, DI_CHK_HLD_FLG_OFF);

        S21FastTBLAccessor.update(updDsCpoTMsg);

        // Get the PK of HOLD
        ssmResult = NWAL1550Query.getInstance().getHldPk(bizMsg);
        List<BigDecimal> hldPkList = (List<BigDecimal>) ssmResult.getResultObject();

        NWZC033001 api = new NWZC033001();
        for (BigDecimal hldPk : hldPkList) {

            NWZC033001PMsg pMsg = new NWZC033001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(pMsg.hldRelRsnCd, HLD_RSN.DI_CHECK_HOLD); // QC#17907 2017/03/09 Add
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
            ZYPEZDItemValueSetter.setValue(pMsg.hldPk, hldPk);

            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {

                List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);

                if (!errList.isEmpty()) {
                    for (String xxMsgId : errList) {
                        if (xxMsgId.endsWith("E")) {
                            bizMsg.setMessageInfo(NWAM0189E, new String[] {xxMsgId });
                            return;
                        }
                    }
                }
            }
        }
    }
}
