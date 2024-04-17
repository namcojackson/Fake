/**
 * <pre> * Copyright(c)2010 Canon USA Inc. All rights reserved. * </pre>
 */
package business.blap.NLCL1020;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NLCL1020.common.NLCL1020CommonLogic;
import business.blap.NLCL1020.constant.NLCL1020Constant;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 *</pre>
 */
public class NLCL1020BL06 extends S21BusinessHandler implements SCE_ORD_TP {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

                // NLCL1020Scrn00_CMN_Submit
            if (NLCL1020Constant.NLCL1020SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_CMN_Submit(cMsg, sMsg);

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
    private void doProcess_NLCL1020Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_CMN_Submit================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        if (!NLCL1020CommonLogic.isSubmitApplyCheck(bizMsg)) {
            return;
        }
        // ----------------------------------------------------
        // Inventory Order API(NLZC0030)
        // ----------------------------------------------------
        if (!successExecNLZC0030(bizMsg)) {
            return;
        }
        // ----------------------------------------------------
        // Inventory Update API(NLZC0020)
        // ----------------------------------------------------
        if (!successExecNLZC0020(bizMsg)) {
            return;
        }
        // ----------------------------------------------------
        NLCL1020CommonLogic.setCurrentAvailableDetail(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_CMN_Submit================================END", this);
    }

    private boolean successExecNLZC0030(NLCL1020CMsg bizMsg) {

        List<NLZC003001PMsg> dLZC0030PMsgList = getNLZC0030PMsgList(bizMsg);

        new NLZC003001().execute(dLZC0030PMsgList, ONBATCH_TYPE.ONLINE);

        if (hasErrNLZC0030(bizMsg, dLZC0030PMsgList)) {
            return false;
        }

        NLZC003001PMsg dLZC003001PMsg = (NLZC003001PMsg) dLZC0030PMsgList.get(0);
        bizMsg.invtyOrdNum.setValue(dLZC003001PMsg.invtyOrdNum.getValue());
        return true;
    }

    private boolean successExecNLZC0020(NLCL1020CMsg bizMsg) {

        List<NLZC002001PMsg> dLZC0020PMsgListFr = new ArrayList<NLZC002001PMsg>();
        List<NLZC002001PMsg> dLZC0020PMsgListTo = new ArrayList<NLZC002001PMsg>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            dLZC0020PMsgListFr.add(getNLZC0020PMsg(bizMsg, i, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT));
            dLZC0020PMsgListTo.add(getNLZC0020PMsg(bizMsg, i, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN));
        }
        if (hasErrNLZC0020(bizMsg, dLZC0020PMsgListFr)) {
            return false;
        }
        if (hasErrNLZC0020(bizMsg, dLZC0020PMsgListTo)) {
            return false;
        }
        return true;
    }

    private boolean hasErrNLZC0020(NLCL1020CMsg bizMsg, List<NLZC002001PMsg> dLZC0020PMsgList) {

        new NLZC002001().execute(dLZC0020PMsgList, ONBATCH_TYPE.ONLINE);
        for (int i = 0; i < dLZC0020PMsgList.size(); i++) {
            NLZC002001PMsg pMsg = (NLZC002001PMsg) dLZC0020PMsgList.get(i);
            for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                String msgCd = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgCd) && msgCd.endsWith("E")) {
                    // Has error
                    bizMsg.setMessageInfo(msgCd);
                    bizMsg.xxErrFlg_RG.setValue(NLCL1020Constant.ERRFLAG_RG1);
                    return true;
                }
            }
        }
        return false;
    }

    private NLZC002001PMsg getNLZC0020PMsg(NLCL1020CMsg bizMsg, int index, String trxRsnCd) {

        NLZC002001PMsg pMsg = new NLZC002001PMsg();

        if (TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {
            // TRX_RSN: 44
            // Out bound
            // Stock Out
            pMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OTBD);
            pMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            // From Wh
            // 10/20/2015 mod start
            // pMsg.invtyLocCd.setValue(bizMsg.invtyLocCd_FR.getValue()); 
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.fromRtlWhCd.getValue(), bizMsg.fromRtlSwhCd.getValue()));
            // 10/20/2015 mod end
            // Ship Conf
            pMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            // Ship To
            // 10/20/2015 mod start
            // pMsg.shipToCustCd.setValue(bizMsg.invtyLocCd_TO.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.toRtlWhCd.getValue(), bizMsg.toRtlSwhCd.getValue()));
            // 10/20/2015 mod end
            pMsg.shipToCustNm.setValue(bizMsg.shipToCustNm.getValue());
        } else {
            // TRX_RSN: 45
            // In bound
            // Stock In
            pMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            pMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            // To Wh
            // 10/20/2015 mod start
            // pMsg.invtyLocCd.setValue(bizMsg.invtyLocCd_TO.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.toRtlWhCd.getValue(), bizMsg.toRtlSwhCd.getValue()));
            // 10/20/2015 mod end
            // Recive Conf
            pMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_RCV_CONF);
        }

        pMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        pMsg.mdseCd.setValue(bizMsg.A.no(index).mdseCd_A1.getValue());

        pMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        pMsg.stkStsCd.setValue(bizMsg.A.no(index).stkStsCd_AP.getValue());
        pMsg.xxRqstQty.setValue(bizMsg.A.no(index).invtyAvalQty_AI.getValue());
        pMsg.trxCd.setValue(TRX.MOVEMENT);
        pMsg.trxRsnCd.setValue(trxRsnCd);
        pMsg.invtyTrxDt.setValue(ZYPDateUtil.getSalesDate());
        pMsg.sysSrcCd.setValue(SYS_SRC.S21_LOGISTICS);

        pMsg.invtyOrdNum.setValue(bizMsg.invtyOrdNum.getValue());
        pMsg.invtyOrdLineNum.setValue(getLineNum(bizMsg.A.no(index).invtyOrdLineNum_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyTrxSlpNum, bizMsg.invtyOrdNum.getValue()); // 10/20/2015 add

        // 10/20/2015 mod start
        // pMsg.shipFromLocCustCd.setValue(bizMsg.invtyLocCd_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromLocCustCd, NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.fromRtlWhCd.getValue(), bizMsg.fromRtlSwhCd.getValue()));
        // 10/20/2015 mod end

        pMsg.uomCd.setValue(PKG_UOM.EACH);

        return pMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param lineNumLis List<String>
     * @return List<NLZC003001PMsg>
     */
    private List<NLZC003001PMsg> getNLZC0030PMsgList(NLCL1020CMsg bizMsg) {

        List<NLZC003001PMsg> dLZC0030List = new ArrayList<NLZC003001PMsg>();
        NLZC003001PMsg pMsg = new NLZC003001PMsg();

        pMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        pMsg.xxDtTpCd.setValue(NLZC003001.DT_TP_HDR);

        pMsg.invtyOrdTpCd.setValue(INVTY_ORD_TP.INTERNAL_DC_TRANSFER);

        // 10/20/2015 mod start
        // pMsg.invtyLocCd.setValue(bizMsg.invtyLocCd_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, bizMsg.fromRtlWhCd.getValue());
        // 10/20/2015 mod end

        pMsg.locStsCd.setValue(bizMsg.locStsCd_P1.getValue());
        pMsg.trxCd.setValue(TRX.MOVEMENT);
        pMsg.trxRsnCd.setValue(TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);

        pMsg.tocCd.clear();
        pMsg.shpgSvcLvlCd.setValue(SHPG_SVC_LVL.GROUND_SERVICE);
        pMsg.dcTrnsfRddDt.clear();
        pMsg.dcTrnsfRsdDt.setValue(ZYPDateUtil.getSalesDate());
        pMsg.prodCtrlCd.clear();
        pMsg.dsplTpCd.clear();
        pMsg.insClmNumTxt.clear();
        pMsg.fromInvtyOrdNum.clear();

        pMsg.dmgClmCpltFlg.setValue("N");
        pMsg.invtyOrdStsCd.setValue(INVTY_ORD_STS.CLOSED);
        pMsg.firstInvtyOrdCmntTxt.setValue(bizMsg.firstInvtyOrdCmntTxt.getValue());
        pMsg.scdInvtyOrdCmntTxt.setValue(bizMsg.scdInvtyOrdCmntTxt.getValue());
        pMsg.thirdInvtyOrdCmntTxt.setValue(bizMsg.thirdInvtyOrdCmntTxt.getValue());
        pMsg.frthInvtyOrdCmntTxt.clear();
        pMsg.trxSrcTpCd.setValue(TRX_SRC_TP.WAREHOUSE_TRANSFER);
        pMsg.sysSrcCd.setValue(SYS_SRC.S21_LOGISTICS);

        pMsg.xxProcTpCd.setValue(NLZC003001.PROC_TP_CRAT);

        dLZC0030List.add(pMsg);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            pMsg = new NLZC003001PMsg();
            pMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            pMsg.invtyOrdLineNum.setValue(getLineNum(bizMsg.A.no(i).invtyOrdLineNum_A1.getValue()));
            pMsg.mdseCd.setValue(bizMsg.A.no(i).mdseCd_A1.getValue());
            pMsg.stkStsCd.setValue(bizMsg.A.no(i).stkStsCd_AP.getValue());
            // 10/20/2015 mod start
            // pMsg.invtyLocCd_D1.setValue(bizMsg.invtyLocCd_TO.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.fromRtlWhCd.getValue(), bizMsg.fromRtlSwhCd.getValue()));
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd_D1, NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.toRtlWhCd.getValue(), bizMsg.toRtlSwhCd.getValue()));
            // 10/20/2015 mod end
            pMsg.locStsCd_D1.setValue(bizMsg.locStsCd_P1.getValue());
            pMsg.toStkStsCd.setValue(bizMsg.A.no(i).stkStsCd_AP.getValue());
            pMsg.ordQty.setValue(bizMsg.A.no(i).invtyAvalQty_AI.getValue());
            pMsg.invtyOrdDtlStsCd.setValue(INVTY_ORD_STS.CLOSED);

            pMsg.xxProcTpCd.setValue(NLZC003001.PROC_TP_CRAT);
            pMsg.xxDtTpCd.setValue(NLZC003001.DT_TP_DTL);
            pMsg.invtyOrdTpCd.setValue(INVTY_ORD_TP.INTERNAL_DC_TRANSFER);

            dLZC0030List.add(pMsg);
        }
        return dLZC0030List;
    }

    private String getLineNum(String lineParam) {
        String lineNum = ZYPCommonFunc.leftPad(lineParam, NLCL1020Constant.MAXLENGTH, NLCL1020Constant.PADDING_STR);
        if (ZYPCommonFunc.hasValue(lineNum)) {
            return lineNum;
        } else {
            return "";
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param NLZC0030List NLZC003001PMsgArray
     * @return boolean
     */
    private boolean hasErrNLZC0030(NLCL1020CMsg bizMsg, List<NLZC003001PMsg> dlzc0030List) {

        for (int i = 0; i < dlzc0030List.size(); i++) {
            NLZC003001PMsg dlzc003001PMsg = dlzc0030List.get(i);
            if (!S21ApiUtil.getXxMsgIdList(dlzc003001PMsg).isEmpty()) {

                for (int j = 0; j < dlzc003001PMsg.xxMsgIdList.length(); j++) {
                    String msgId = dlzc003001PMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {

                        EZDDebugOutput.println(1, "isDlzc0030Result--### Error ###--[Inventory Order API(NLZC0030)]Abnormal results : " + msgId, this);
                        if (msgId.endsWith("E")) {
                            bizMsg.setMessageInfo(msgId);
                            bizMsg.xxErrFlg_RG.setValue(NLCL1020Constant.ERRFLAG_RG1);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
