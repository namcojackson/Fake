/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NLCL0180;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NLCL0180.common.NLCL0180CommonLogic;
import business.blap.NLCL0180.constant.NLCL0180Constant;
import business.db.MDSETMsg;
import business.db.SPLY_VTMsg;
import business.db.SPLY_VTMsgArray;
import business.parts.NLZC002001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 * 09/22/2009   Fujitsu         S.Yoshida           Update          N/A
 * 09/28/2009   Fujitsu         FAP)Y.Furuta        Update          N/A
 * 05/22/2013   Fujitsu         T.Tozuka            Update          R-OM025 Inventory Transaction
 * </pre>
 */
public class NLCL0180BL06 extends S21BusinessHandler implements NLCL0180Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0180Scrn00_Add_Dtail_Line".equals(screenAplID)) {
                doProcess_NLCL0180Scrn00_Add_Dtail_Line(cMsg, sMsg);

            } else if ("NLCL0180Scrn00_CMN_Apply".equals(screenAplID)) {
                doProcess_NLCL0180Scrn00_CMN_Apply(cMsg, sMsg);

            } else if ("NLCL0180Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0180Scrn00_CMN_Submit(cMsg, sMsg);

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
    private void doProcess_NLCL0180Scrn00_Add_Dtail_Line(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_Add_Dtail_Line================================START", this);

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        bizMsg.mdseNm.clear();

        MDSETMsg outMdseInfo = NLCL0180CommonLogic.getMdseInfo(bizMsg);

        if (outMdseInfo != null) {

            bizMsg.mdseNm.setValue(outMdseInfo.mdseNm.getValue());

            if (ZYPConstant.FLG_OFF_N.equals(outMdseInfo.invtyCtrlFlg.getValue())) {

                bizMsg.mdseCd.setErrorInfo(1, "NLCM0063E", new String[] {MSG_ARG_MDSE_CD });

                return;
            }

            if (RGTN_STS.TERMINATED.equals(outMdseInfo.rgtnStsCd.getValue()) || RGTN_STS.PENDING_COMPLETION.equals(outMdseInfo.rgtnStsCd.getValue())) {

                bizMsg.mdseCd.setErrorInfo(1, "NLCM0065E", new String[] {MSG_ARG_MDSE_CD });

                return;
            }

        } else {

            bizMsg.mdseCd.setErrorInfo(1, "NLCM0002E", null);
            return;
        }

        int addPoint = bizMsg.A.getValidCount();
        bizMsg.A.no(addPoint).itemLineNum_A1.setValue(String.valueOf(addPoint + 1));
        bizMsg.A.no(addPoint).mdseCd_A1.setValue(bizMsg.mdseCd.getValue());
        bizMsg.A.no(addPoint).mdseNm_A1.setValue(bizMsg.mdseNm.getValue());
        bizMsg.A.no(addPoint).xxRqstQty_A1.setValue(bizMsg.xxRqstQty_H1.getValue());
        bizMsg.A.no(addPoint).xxRqstQty_A2.setValue(bizMsg.xxRqstQty_H2.getValue());

        bizMsg.A.setValidCount(addPoint + 1);

        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_Add_Dtail_Line================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0180Scrn00_CMN_Apply(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_CMN_Apply================================START", this);

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        isSubmitApplyCheck(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_CMN_Apply================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0180Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_CMN_Submit================================START", this);

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        if (!isSubmitApplyCheck(bizMsg)) {
            return;
        }

        List<NLZC002001PMsg> alzc002001ListGood = new ArrayList<NLZC002001PMsg>();
        List<NLZC002001PMsg> alzc002001ListDefective = new ArrayList<NLZC002001PMsg>();
        NLZC002001PMsg outInvtyUpdateGood = null;
        NLZC002001PMsg outInvtyUpdateDefective = null;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (BigDecimal.ZERO.compareTo(bizMsg.A.no(i).xxRqstQty_A1.getValue()) < ZERO_INT) {
                outInvtyUpdateGood = getInventoryUpdateApi(bizMsg, i, STK_STS.GOOD);

                alzc002001ListGood.add(outInvtyUpdateGood);

            }

            if (BigDecimal.ZERO.compareTo(bizMsg.A.no(i).xxRqstQty_A2.getValue()) < ZERO_INT) {
                outInvtyUpdateDefective = getInventoryUpdateApi(bizMsg, i, STK_STS.DEFECTIVE);

                alzc002001ListDefective.add(outInvtyUpdateDefective);

            }
        }

        NLZC002001 alzc002001 = new NLZC002001();
        if (!alzc002001ListGood.isEmpty()) {

            alzc002001.execute(alzc002001ListGood, ONBATCH_TYPE.ONLINE);

            boolean errResult = isErrorCodeAlzc0020(alzc002001ListGood, bizMsg);

            if (!errResult) {
                return;
            }

        }

        alzc002001 = new NLZC002001();
        if (!alzc002001ListDefective.isEmpty()) {

            alzc002001.execute(alzc002001ListDefective, ONBATCH_TYPE.ONLINE);

            boolean errResult = isErrorCodeAlzc0020(alzc002001ListDefective, bizMsg);

            if (!errResult) {
                return;
            }
        }

        EZDDebugOutput.println(1, "doProcess_NLCL0180Scrn00_CMN_Submit================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return VNDTMsg
     */
    private SPLY_VTMsg getVndInfo(NLCL0180CMsg bizMsg) {

        SPLY_VTMsg inSplyVTMsg = new SPLY_VTMsg();

        SPLY_VTMsgArray outSplyVTMsg = NLCL0180CommonLogic.findVndListForRecChk(bizMsg, inSplyVTMsg);

        if (outSplyVTMsg.length() == 0) {

            return null;
        } else {

            return outSplyVTMsg.no(0);
        }

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean isSubmitApplyCheck(NLCL0180CMsg bizMsg) {

        bizMsg.vndNm.clear();

        SPLY_VTMsg outVndInfo = getVndInfo(bizMsg);

        if (outVndInfo != null) {

            bizMsg.vndNm.setValue(outVndInfo.splyNm.getValue());
            if (RGTN_STS.TERMINATED.equals(outVndInfo.rgtnStsCd.getValue()) || RGTN_STS.PENDING_COMPLETION.equals(outVndInfo.rgtnStsCd.getValue())) {

                bizMsg.vndCd.setErrorInfo(1, "NLCM0065E", new String[] {MSG_ARG_VENDOR_CD });
                return false;

            }

        } else {

            bizMsg.vndCd.setErrorInfo(1, "NLCM0066E", null);
            return false;
        }


        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        // Do location check
        bizMsg.locNm_P1.clear();

        // Get Security Attribute
        List<S21DataSecurityAttributeData> securityAttr = getUserProfileService().getDataSecurityAttributeDataListFor(
                                                                getContextUserInfo().getUserId(),
                                                                BUSINESS_ID,
                                                                S21DataSecurityAttributeData.NAME_WAREHOUSE);
        // Get Location Info
        NMXC100001EnableWHData locInfo = NLCL0180CommonLogic.getInvtyLocInfo(bizMsg, securityAttr);

        // NG LocCd
        if (locInfo == null) {
            bizMsg.whCd_P1.setErrorInfo(1, "NLCM0004E");
            return false;
        } else if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            bizMsg.whCd_P1.setErrorInfo(1, locInfo.getXxMsgId());
            return false;
        }

        // Set Location Name
        ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P1, locInfo.getInvtyLocNm());

        // 2013/05/22 R-OM025 Inventory Transaction Add End
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed. bizMsg NLCL0180CMsg globalMsg
     * @param NLCL0180CMsg
     * @param int index
     * @param String stkSts
     * @return NLZC002001PMsg
     */
    private NLZC002001PMsg getInventoryUpdateApi(NLCL0180CMsg bizMsg, int index, String stkSts) {

        NLZC002001PMsg alzc002001PMsg = new NLZC002001PMsg();

        alzc002001PMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        alzc002001PMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        alzc002001PMsg.trxCd.setValue(TRX.PURCHASE_STOCK_IN);
        alzc002001PMsg.trxRsnCd.setValue(TRX_RSN.PURCHASE_STOCK_IN);
        alzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_ITM_ENTY_SP);
        alzc002001PMsg.mdseCd.setValue(bizMsg.A.no(index).mdseCd_A1.getValue());
        alzc002001PMsg.invtyLocCd.setValue(bizMsg.whCd_P1.getValue());
        alzc002001PMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        alzc002001PMsg.stkStsCd.setValue(stkSts);

        if (STK_STS.GOOD.equals(stkSts)) {
            alzc002001PMsg.xxRqstQty.setValue(bizMsg.A.no(index).xxRqstQty_A1.getValue());
        } else {
            alzc002001PMsg.xxRqstQty.setValue(bizMsg.A.no(index).xxRqstQty_A2.getValue());
        }

        alzc002001PMsg.invtyTrxDt.setValue(bizMsg.arvDt.getValue());
        alzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INVTY);
        alzc002001PMsg.sysSrcCd.setValue(SYS_SRC_CD);
        alzc002001PMsg.invtyTrxSlpNum.setValue(bizMsg.apVndInvNum.getValue());
        alzc002001PMsg.vndCd.setValue(bizMsg.vndCd.getValue());
        alzc002001PMsg.vndNm.setValue(bizMsg.vndNm.getValue());

        return alzc002001PMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param List alzc0020List
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean isErrorCodeAlzc0020(List alzc0020List, NLCL0180CMsg bizMsg) {
        String msgId = "";

        for (int i = 0; i < alzc0020List.size(); i++) {
            NLZC002001PMsg alzc002001PMsg = (NLZC002001PMsg) alzc0020List.get(i);

            if (!S21ApiUtil.getXxMsgIdList(alzc002001PMsg).isEmpty()) {
                for (int j = 0; j < alzc002001PMsg.xxMsgIdList.length(); j++) {
                    msgId = alzc002001PMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        bizMsg.setMessageInfo(msgId, null);
                        if (msgId.endsWith("E")) {
                            return false;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return true;
    }
}
