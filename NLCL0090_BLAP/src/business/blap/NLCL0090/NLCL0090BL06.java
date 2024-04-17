/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0090;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0090.common.NLCL0090CommonLogic;
import business.blap.NLCL0090.constant.NLCL0090Constant;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLXC023001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC023001.NLXC023001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/25   Fujitsu         FXS)KF.Qian     Create          N/A
 * 2010/03/26   Fujitsu         S.Yoshida       Update          Def.854
 * 2010/04/15   Fujitsu         S.Yoshida       Update          Def.5017
 * 2013/05/23   Fujitsu         F.Saito         Update          R-OM025 Inventory Transaction
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 * 12/02/2019   CITS            K.Ogino         Update          QC#54823
 * </pre>
 */
public class NLCL0090BL06 extends S21BusinessHandler implements NLCL0090Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NLCL0090Scrn00_CMN_Submit
            if ("NLCL0090Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_CMN_Submit((NLCL0090CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0090Scrn00_CMN_Submit(NLCL0090CMsg bizMsg) {

        if (!chkTableWhole(bizMsg)) {

            return;
        }

        String outgetBatProcDate = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        String outgetSalesDate = ZYPDateUtil.getSalesDate();

        String invtyOrdNum = execInventoryOrder(bizMsg);

        if (invtyOrdNum == null) {

            return;
        }

        if (!divAllocation(bizMsg, invtyOrdNum, outgetBatProcDate, outgetSalesDate)) {

            return;
        }

        if (!execMachMstrUpdate(bizMsg, invtyOrdNum)) {

            return;
        }

        if (!execShippingPlanUpdate(bizMsg, invtyOrdNum)) {

            return;
        }

        List<String> shpgPlnNumList = getShpgPlnNum(bizMsg, invtyOrdNum);

        if (shpgPlnNumList == null) {

            return;
        }

        String soNum = execShippingOrder(bizMsg, shpgPlnNumList);

        if (soNum == null) {

            return;
        }

        List<String> rwsNumList = execRWSCreation(bizMsg, soNum);

        if (rwsNumList == null) {

            return;
        }

        if (!execRwsSerial(bizMsg, rwsNumList)) {

            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_BK, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.soNum, soNum);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean chkTableWhole(NLCL0090CMsg bizMsg) {

        if (!NLCL0090CommonLogic.isLocCdCheck(bizMsg, getUserProfileService(), getContextUserInfo())) {

            return false;
        }

        if (!NLCL0090CommonLogic.findAvalInvtyAppId(bizMsg)) {

            return false;
        }

        ArrayList<BigDecimal> configIndexList = new ArrayList<BigDecimal>();
        boolean isChkInvtyAndSer = true;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).dispFlg_DF.getValue())) {

                if (!NLCL0090CommonLogic.findInvtyInfoForRecChk(bizMsg, bizMsg.A.no(i).mdseCd_DF, bizMsg.A.no(i).invtyAvalQty_DF, bizMsg.A.no(i).invtyQty_DF)) {

                    isChkInvtyAndSer = false;
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).instlBaseCtrlFlg_DF.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).shpgSerTakeFlg_DF.getValue())) {

                if (!NLCL0090CommonLogic.checkSerialAddFrom(bizMsg, i)) {

                    isChkInvtyAndSer = false;
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).svcConfigMstrPk_DF)) {

                configIndexList.add(BigDecimal.valueOf(i));
            }
        }

        if (!configIndexList.isEmpty() && configIndexList.size() > 1) {

            for (BigDecimal index : configIndexList){

                bizMsg.A.no(index.intValue()).serNum_DF.setErrorInfo(1, NLCM0166E);
                bizMsg.setMessageInfo(ZZM9037E);
            }

            isChkInvtyAndSer = false;
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).instlBaseCtrlFlg_DT.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).shpgSerTakeFlg_DT.getValue())) {

                if (!NLCL0090CommonLogic.checkSerialAddTo(bizMsg, bizMsg.B.no(i).mdseCd_DT, bizMsg.B.no(i).serNum_DT)) {

                    isChkInvtyAndSer = false;
                }
            }
        }

        if (!isChkInvtyAndSer) {

            return false;
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return String
     */
    private String execInventoryOrder(NLCL0090CMsg bizMsg) {

        int invtyLineNum = FIRST_TRX_LINE_NUM;
        String invtyOrdLineNum = null;

        List<NLZC003001PMsg> invtyOrdUpdApiPMsgList = new ArrayList<NLZC003001PMsg>();

        invtyOrdUpdApiPMsgList.add(setInventoryOrderParam(bizMsg, null, null, null, NLZC003001.DT_TP_HDR));

        NLZC003001PMsg invtyOrdUpdApiDtlPMsg = new NLZC003001PMsg();
        int serIndex = 0;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            String mdseCdDf = bizMsg.A.no(i).mdseCd_DF.getValue();
            BigDecimal invtyQtyDf = bizMsg.A.no(i).invtyQty_DF.getValue();

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).dispFlg_DF.getValue())) {

                if (i > 0) {

                    invtyOrdUpdApiDtlPMsg.serialInfoList.setValidCount(serIndex);
                    invtyOrdUpdApiPMsgList.add(invtyOrdUpdApiDtlPMsg);
                    invtyOrdUpdApiDtlPMsg = new NLZC003001PMsg();
                    serIndex = 0;
                }

                invtyOrdUpdApiDtlPMsg = setInventoryOrderParam(bizMsg, mdseCdDf, invtyQtyDf, Integer.toString(invtyLineNum), NLZC003001.DT_TP_DTL);
                ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiDtlPMsg.svcConfigMstrPk, bizMsg.A.no(i).svcConfigMstrPk_DF.getValue());
                invtyOrdLineNum = invtyOrdUpdApiDtlPMsg.invtyOrdLineNum.getValue();
                invtyLineNum++;
            }

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).shpgSerTakeFlg_DF.getValue())) {

                ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiDtlPMsg.serialInfoList.no(serIndex).invtyOrdLineNum, invtyOrdLineNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiDtlPMsg.serialInfoList.no(serIndex).serNum, bizMsg.A.no(i).serNum_DF.getValue());
                serIndex++;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyOrdLineNum_OD, invtyOrdLineNum);
        }

        invtyOrdUpdApiDtlPMsg.serialInfoList.setValidCount(serIndex);
        invtyOrdUpdApiPMsgList.add(invtyOrdUpdApiDtlPMsg);
        invtyOrdUpdApiDtlPMsg = new NLZC003001PMsg();
        serIndex = 0;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            String mdseCdDt = bizMsg.B.no(i).mdseCd_DT.getValue();
            BigDecimal invtyQtyDt = bizMsg.B.no(i).invtyQty_DT.getValue();
            invtyQtyDt = invtyQtyDt.multiply(new BigDecimal(-1));

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).dispFlg_DT.getValue())) {

                if (i > 0) {

                    invtyOrdUpdApiDtlPMsg.serialInfoList.setValidCount(serIndex);
                    invtyOrdUpdApiPMsgList.add(invtyOrdUpdApiDtlPMsg);
                    invtyOrdUpdApiDtlPMsg = new NLZC003001PMsg();
                    serIndex = 0;
                }

                invtyOrdUpdApiDtlPMsg = setInventoryOrderParam(bizMsg, mdseCdDt, invtyQtyDt, Integer.toString(invtyLineNum), NLZC003001.DT_TP_DTL);
                invtyOrdLineNum = invtyOrdUpdApiDtlPMsg.invtyOrdLineNum.getValue();
                invtyLineNum++;
            }

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).shpgSerTakeFlg_DT.getValue())) {

                ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiDtlPMsg.serialInfoList.no(serIndex).invtyOrdLineNum, invtyOrdLineNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiDtlPMsg.serialInfoList.no(serIndex).serNum, bizMsg.B.no(i).serNum_DT.getValue());
                serIndex++;
            }
        }

        invtyOrdUpdApiDtlPMsg.serialInfoList.setValidCount(serIndex);
        invtyOrdUpdApiPMsgList.add(invtyOrdUpdApiDtlPMsg);

        NLZC003001 invtyOrdUpdApi = new NLZC003001();
        invtyOrdUpdApi.execute(invtyOrdUpdApiPMsgList, ONBATCH_TYPE.ONLINE);

        for (NLZC003001PMsg invtyOrdUpdApiPMsg : invtyOrdUpdApiPMsgList) {

            if (!S21ApiUtil.getXxMsgIdList(invtyOrdUpdApiPMsg).isEmpty()) {

                for (int j = 0; j < invtyOrdUpdApiPMsg.xxMsgIdList.length(); j++) {

                    String msgId = invtyOrdUpdApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                    if (ZYPCommonFunc.hasValue(msgId)) {

                        bizMsg.setMessageInfo(msgId, null);

                        if (msgId.endsWith("E")) {

                            return null;
                        }
                    }
                }
            }
        }

        return invtyOrdUpdApiPMsgList.get(0).invtyOrdNum.getValue();
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param mdseCd
     * @param invtyQty
     * @param lineNum
     * @param dtTpCd
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg setInventoryOrderParam(NLCL0090CMsg bizMsg, String mdseCd, BigDecimal invtyQty, String lineNum, String dtTpCd) {

        NLZC003001PMsg invtyOrdUpdApiPMsg = new NLZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.xxDtTpCd, dtTpCd);
        ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyOrdTpCd, INVTY_ORD_TP.ITEM_CHANGE);

        if (NLZC003001.DT_TP_HDR.equals(dtTpCd)) {

            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyLocCd, bizMsg.rtlWhCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.locStsCd, bizMsg.locStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.firstInvtyOrdCmntTxt, bizMsg.firstInvtyOrdCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.scdInvtyOrdCmntTxt, bizMsg.scdInvtyOrdCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.thirdInvtyOrdCmntTxt, bizMsg.thirdInvtyOrdCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);

        } else {

            lineNum = ZYPCommonFunc.leftPad(lineNum, LINE_NUM_LENGTH, SPARE_VALUE);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyOrdLineNum, lineNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyLocCd, bizMsg.invtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.stkStsCd, bizMsg.stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyLocCd_D1, bizMsg.invtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.locStsCd_D1, bizMsg.locStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.ordQty, invtyQty);
            ZYPEZDItemValueSetter.setValue(invtyOrdUpdApiPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
        }

        return invtyOrdUpdApiPMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param procDate
     * @param slsDate
     * @return boolean
     */
    private boolean divAllocation(NLCL0090CMsg bizMsg, String invtyOrdNum, String procDate, String slsDate) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).dispFlg_DF.getValue())) {

                NWZC107001PMsg allocApiPMsg = new NWZC107001PMsg();

                ZYPEZDItemValueSetter.setValue(allocApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxCd, TRX.ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.invtyLocCd, bizMsg.invtyLocCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.locStsCd, bizMsg.locStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.stkStsCd, bizMsg.stkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxOrdTs, procDate);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxHdrNum, invtyOrdNum);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineNum, bizMsg.A.no(i).invtyOrdLineNum_OD.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.mdseCd, bizMsg.A.no(i).mdseCd_DF.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstQty, bizMsg.A.no(i).invtyQty_DF.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.slsDt, slsDate);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.rsdDt, slsDate);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxUnitPrc, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxSlsAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.billToCustCd, bizMsg.invtyLocCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.sellToCustCd, bizMsg.invtyLocCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipToCustCd, bizMsg.invtyLocCd.getValue());
                ZYPEZDItemValueSetter.setValue(allocApiPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);

                NWZC107001 allocApi = new NWZC107001();
                allocApi.execute(allocApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!S21ApiUtil.getXxMsgIdList(allocApiPMsg).isEmpty()) {

                    for (int j = 0; j < allocApiPMsg.xxMsgIdList.length(); j++) {

                        String msgId = allocApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            bizMsg.setMessageInfo(msgId, null);

                            if (msgId.endsWith("E")) {

                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param invtyOrdNum
     * @return boolean
     */
    private boolean execMachMstrUpdate(NLCL0090CMsg bizMsg, String invtyOrdNum) {

        List<NSZC001001PMsg> svcMachMstrApiPMsgList = new ArrayList<NSZC001001PMsg>();
        // QC#54823
        boolean isAllocErr = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            String invtyOrdLineNum = bizMsg.A.no(i).invtyOrdLineNum_OD.getValue();

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).instlBaseCtrlFlg_DF.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).shpgSerTakeFlg_DF.getValue())) {

                    if (!chkMachMstrTimeStamp(bizMsg, i)) {

                        return false;
                    }

                    svcMachMstrApiPMsgList.add(setSvcMachMstrApiPMsg(bizMsg, invtyOrdNum, invtyOrdLineNum, bizMsg.A.no(i).svcMachMstrPk_DF.getValue()));

                } else {

                    S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().getNonSerSvcMachMstrList(bizMsg, bizMsg.A.no(i).mdseCd_DF.getValue(), bizMsg.A.no(i).invtyQty_DF.getValueInt());

                    if (ssmResult.isCodeNormal()) {

                        ArrayList<Map<String, Object>> nonSerSvcMachMstrList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                        if (nonSerSvcMachMstrList != null && !nonSerSvcMachMstrList.isEmpty()) {

                            for (Map<String, Object> nonSerSvcMachMstrMap : nonSerSvcMachMstrList) {

                                svcMachMstrApiPMsgList.add(setSvcMachMstrApiPMsg(bizMsg, invtyOrdNum, invtyOrdLineNum, (BigDecimal) nonSerSvcMachMstrMap.get("SVC_MACH_MSTR_PK")));
                            }
                        }
                    // QC#54823
                    } else {
                        bizMsg.A.no(i).mdseCd_DF.setErrorInfo(1, NLCM0238E);
                        isAllocErr = true;
                    }
                }
            }
        }
        // QC#54823
        if (!isAllocErr && svcMachMstrApiPMsgList != null && !svcMachMstrApiPMsgList.isEmpty()) {

            NSZC001001 svcMachMstrApi = new NSZC001001();
            svcMachMstrApi.execute(svcMachMstrApiPMsgList, ONBATCH_TYPE.ONLINE);

            for (NSZC001001PMsg svcMachMstrApiPMsg : svcMachMstrApiPMsgList) {

                if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {

                    for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.length(); j++) {

                        String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            bizMsg.setMessageInfo(msgId, null);

                            if (msgId.endsWith("E")) {

                                return false;
                            }
                        }
                    }
                }
            }
        }

        // QC#54823
        if (isAllocErr) {
            return false;
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param invtyOrdNum String
     * @param invtyOrdLineNum String
     * @param svcMachMstrPk BigDecimal
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg setSvcMachMstrApiPMsg(NLCL0090CMsg bizMsg, String invtyOrdNum, String invtyOrdLineNum, BigDecimal svcMachMstrPk) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, bizMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, invtyOrdLineNum);

        return svcMachMstrApiPMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param index int
     * @return boolean
     */
    private boolean chkMachMstrTimeStamp(NLCL0090CMsg bizMsg, int index) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, bizMsg.A.no(index).svcMachMstrPk_DF.getValue());
        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(svcMachMstrTMsg);

        if (svcMachMstrTMsg != null) {

            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(index).ezUpTime_IB.getValue(), bizMsg.A.no(index).ezUpTimeZone_IB.getValue(),
                    svcMachMstrTMsg.ezUpTime.getValue(), svcMachMstrTMsg.ezUpTimeZone.getValue())) {

                bizMsg.A.no(index).serNum_DF.setErrorInfo(1, NLBM0009E);
                bizMsg.setMessageInfo(NLBM0009E);
                return false;
            }

        } else {

            bizMsg.A.no(index).serNum_DF.setErrorInfo(1, NLZM2278E, new String[] {"Serial" });
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param invtyOrdNum
     * @return boolean
     */
    private boolean execShippingPlanUpdate(NLCL0090CMsg bizMsg, String invtyOrdNum) {

        List<NWZC003001PMsg> shpgPlnUpdApiPMsgList = new ArrayList<NWZC003001PMsg>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).dispFlg_DF.getValue())) {

                NWZC003001PMsg shpgPlnUpdApiPMsg = new NWZC003001PMsg();

                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxHdrNum, invtyOrdNum);
                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxLineNum, bizMsg.A.no(i).invtyOrdLineNum_OD.getValue());
                ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.avalSoQty, bizMsg.A.no(i).invtyQty_DF.getValue());
                shpgPlnUpdApiPMsgList.add(shpgPlnUpdApiPMsg);
            }
        }

        NWZC003001 shpgPlnUpdApi = new NWZC003001();
        shpgPlnUpdApi.execute(shpgPlnUpdApiPMsgList, ONBATCH_TYPE.ONLINE);

        for (NWZC003001PMsg shpgPlnUpdApiPMsg : shpgPlnUpdApiPMsgList) {

            if (!S21ApiUtil.getXxMsgIdList(shpgPlnUpdApiPMsg).isEmpty()) {

                for (int j = 0; j < shpgPlnUpdApiPMsg.xxMsgIdList.length(); j++) {

                    String msgId = shpgPlnUpdApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                    if (ZYPCommonFunc.hasValue(msgId)) {

                        bizMsg.setMessageInfo(msgId, null);

                        if (msgId.endsWith("E")) {

                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param invtyOrdNum
     * @return ArrayList
     */
    private List<String> getShpgPlnNum(NLCL0090CMsg bizMsg, String invtyOrdNum) {

        List<NLXC023001PMsg> getShpgPlnNumPMsgList = new ArrayList<NLXC023001PMsg>();
        List<String> shpgPlnNumList = new ArrayList<String>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).dispFlg_DF.getValue())) {

                NLXC023001PMsg getShpgPlnNumPMsg = new NLXC023001PMsg();

                ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
                ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxHdrNum, invtyOrdNum);
                ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxLineNum, bizMsg.A.no(i).invtyOrdLineNum_OD.getValue());
                ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
                getShpgPlnNumPMsgList.add(getShpgPlnNumPMsg);
            }
        }

        NLXC023001 getShpgPlnNumApi = new NLXC023001();
        getShpgPlnNumApi.execute(getShpgPlnNumPMsgList, ONBATCH_TYPE.ONLINE);

        for (NLXC023001PMsg getShpgPlnNumPMsg : getShpgPlnNumPMsgList) {

            if (!S21ApiUtil.getXxMsgIdList(getShpgPlnNumPMsg).isEmpty()) {

                for (int j = 0; j < getShpgPlnNumPMsg.xxMsgIdList.length(); j++) {

                    String msgId = getShpgPlnNumPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                    if (ZYPCommonFunc.hasValue(msgId)) {

                        bizMsg.setMessageInfo(msgId, null);

                        if (msgId.endsWith("E")) {

                            return null;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < getShpgPlnNumPMsgList.size(); i++) {

            String shpgPlnNum = getShpgPlnNumPMsgList.get(i).shpgPlnNum.getValue();
            shpgPlnNumList.add(shpgPlnNum);
        }

        return shpgPlnNumList;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param shpgPlnNumList List<String>
     * @return String
     */
    private String execShippingOrder(NLCL0090CMsg bizMsg, List<String> shpgPlnNumList) {

        List<NLZC205001PMsg> soApiPMsgList = new ArrayList<NLZC205001PMsg>();

        for (int i = 0; i < shpgPlnNumList.size(); i++) {

            NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_NEW);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, NLXSceConst.SCE_ORD_TP_CD_IC);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgPlnNum, (String) shpgPlnNumList.get(i));

            soApiPMsgList.add(soApiPMsg);
        }

        NLZC205001 soApi = new NLZC205001();
        soApi.execute(soApiPMsgList, ONBATCH_TYPE.ONLINE);

        for (NLZC205001PMsg soApiPMsg : soApiPMsgList) {

            if (!S21ApiUtil.getXxMsgIdList(soApiPMsg).isEmpty()) {

                for (int j = 0; j < soApiPMsg.xxMsgIdList.length(); j++) {

                    String msgId = soApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                    if (ZYPCommonFunc.hasValue(msgId)) {

                        if ("NLZM2320W".equals(msgId) || "NLZM2321W".equals(msgId)) {

                            continue;
                        }

                        bizMsg.setMessageInfo(msgId, null);

                        if (msgId.endsWith("E")) {

                            return null;
                        }
                    }
                }
            }
        }

        return soApiPMsgList.get(0).soNum.getValue();
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param soNum String
     * @return List<String>
     */
    private List<String> execRWSCreation(NLCL0090CMsg bizMsg, String soNum) {

        NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();
        List<String> rwsNumList = new ArrayList<String>();

        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, soNum);

        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsApiPMsg, ONBATCH_TYPE.ONLINE);

        for (int i = 0; i < rwsApiPMsg.xxMsgIdList.length(); i++) {

            String msgId = rwsApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();

            if (ZYPCommonFunc.hasValue(msgId)) {

                bizMsg.setMessageInfo(msgId, null);

                if (msgId.endsWith("E")) {

                    return null;
                }
            }
        }

        for (int i = 0; i < rwsApiPMsg.RWSNumList.getValidCount(); i++) {

            String rwsNum = rwsApiPMsg.RWSNumList.no(i).rwsNum.getValue();
            rwsNumList.add(rwsNum);
        }

        return rwsNumList;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param rwsNumList List<String>
     * @return boolean
     */
    private boolean execRwsSerial(NLCL0090CMsg bizMsg, List<String> rwsNumList) {

        List<NLZC304001PMsg> rwsSerApiPMsgList = new ArrayList<NLZC304001PMsg>();

        for (String rwsNum : rwsNumList) {

            S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().searchRwsSerial(bizMsg.glblCmpyCd.getValue(), rwsNum);

            if (ssmResult.isCodeNormal()) {

                ArrayList<Map<String, Object>> rwsSerialList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                if (rwsSerialList != null && !rwsSerialList.isEmpty()) {

                    NLZC304001PMsg rwsSerApiPMsg = new NLZC304001PMsg();

                    ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.rwsNum, rwsNum);

                    int i = 0;

                    for (; i < rwsSerialList.size(); i++) {

                        ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.SerialList.no(i).rwsDtlLineNum, (String) rwsSerialList.get(i).get("RWS_DTL_LINE_NUM"));
                        ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.SerialList.no(i).serNum, (String) rwsSerialList.get(i).get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.SerialList.no(i).mdseCd, (String) rwsSerialList.get(i).get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.SerialList.no(i).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                        ZYPEZDItemValueSetter.setValue(rwsSerApiPMsg.SerialList.no(i).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    }

                    rwsSerApiPMsg.SerialList.setValidCount(i);
                    rwsSerApiPMsgList.add(rwsSerApiPMsg);
                }
            }
        }

        if (rwsSerApiPMsgList != null && !rwsSerApiPMsgList.isEmpty()) {

            NLZC304001 rwsSerialApi = new NLZC304001();
            rwsSerialApi.execute(rwsSerApiPMsgList, ONBATCH_TYPE.ONLINE);

            for (NLZC304001PMsg rwsSerApiPMsg : rwsSerApiPMsgList) {

                if (!S21ApiUtil.getXxMsgIdList(rwsSerApiPMsg).isEmpty()) {

                    for (int j = 0; j < rwsSerApiPMsg.xxMsgIdList.length(); j++) {

                        String msgId = rwsSerApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            bizMsg.setMessageInfo(msgId, null);

                            if (msgId.endsWith("E")) {

                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
