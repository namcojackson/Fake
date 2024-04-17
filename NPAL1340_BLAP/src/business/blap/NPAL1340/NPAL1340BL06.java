/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1340;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1340.common.NPAL1340CommonLogic;
import business.blap.NPAL1340.constant.NPAL1340Constant;
import business.db.DS_PO_TPTMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NPZC131001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC131001.NPZC131001;
import com.canon.cusa.s21.api.NPZ.NPZC131001.constant.NPZC131001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 09/20/2017   CITS            T.Tokutomi      Update          QC#21191
 *</pre>
 */
public class NPAL1340BL06 extends S21BusinessHandler implements NPAL1340Constant {

    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            NPAL1340CMsg bizMsg = (NPAL1340CMsg) cMsg;
            NPAL1340SMsg globalMsg = (NPAL1340SMsg) sMsg;

            if ("NPAL1340Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_CMN_Submit(bizMsg, globalMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID :" + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340Scrn00_CMN_Submit(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_CMN_Submit================================START", this);

        NPAL1340CommonLogic.copyPage(bizMsg, globalMsg);

        int chkRlsCnt = 0;
        int chkCanCnt = 0;
        boolean errFlg = false;
        int firstErrLineNum = -1;

        // Release Line
        List<Integer> releaseList = new ArrayList<Integer>();
        // Cancel Line
        List<Integer> cancelList = new ArrayList<Integer>();

        String oldCpoDtlLineNum = "";

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (globalMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                chkRlsCnt++;
                releaseList.add(i);
            }

            if (globalMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                chkCanCnt++;
                cancelList.add(i);
            }

            if (globalMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                if (globalMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                    globalMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NPAM1477E", new String[] {});
                    globalMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, "NPAM1477E", new String[] {});
                    errFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).poRcvQty_A1)) {
                    globalMsg.A.no(i).poRcvQty_A1.setErrorInfo(1, "NFCM0504E", new String[] {"Release Qty" });
                    errFlg = true;
                } else {
                    if (globalMsg.A.no(i).poRcvQty_A1.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        globalMsg.A.no(i).poRcvQty_A1.setErrorInfo(1, "NWFM0165E", new String[] {"Release Qty" });
                        errFlg = true;
                    }
                    if (globalMsg.A.no(i).poRcvQty_A1.getValue().compareTo(BigDecimal.ZERO) < 0) {
                        globalMsg.A.no(i).poRcvQty_A1.setErrorInfo(1, "NPAM0051E", new String[] {"Release Qty" });
                        errFlg = true;
                    }
                    if (globalMsg.A.no(i).poRcvQty_A1.getValue().compareTo(globalMsg.A.no(i).poBalQty_A1.getValue()) > 0) {
                        globalMsg.A.no(i).poRcvQty_A1.setErrorInfo(1, "NPAM1422E", new String[] {});
                        errFlg = true;
                    }
                    if (globalMsg.A.no(i).poRcvQty_A1.getValue().compareTo(NPAL1340CommonLogic.getSerialCount(globalMsg.N.no(i))) < 0) {
                        globalMsg.A.no(i).xxScrItem20Txt_A1.setErrorInfo(1, "NPAM1423E", new String[] {});
                        errFlg = true;
                    }
                }

                // QC#21191
                if(ZYPCommonFunc.hasValue(globalMsg.A.no(i).carrNm_A1) && !NPAL1340CommonLogic.setCarrierCd(getGlobalCompanyCode(), globalMsg.A.no(i))){
                    globalMsg.A.no(i).carrNm_A1.setErrorInfo(1, "NPAM0076E", new String[] {"Carrier" });
                    errFlg = true;
                }

                // QC#21191
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).carrCd_A1) && !NPAL1340CommonLogic.isExistCarrier(getGlobalCompanyCode(), globalMsg.A.no(i).carrCd_A1.getValue())) {
                    globalMsg.A.no(i).carrNm_A1.setErrorInfo(1, "NPAM0076E", new String[] {"Carrier" });
                    errFlg = true;
                }

                if (errFlg) {
                    if (firstErrLineNum < 0) {
                        firstErrLineNum = i;
                    }
                }
            }

            // Check Set Item Line
            if ("Yes".equals(globalMsg.A.no(i).xxScrItem8Txt_A2.getValue()) && !oldCpoDtlLineNum.equals(globalMsg.A.no(i).cpoDtlLineNum_A1.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue()) || ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A2.getValue())) {
                    List<Integer> setItemLine = ZYPTableUtil.getSelectedRows(globalMsg.A, "cpoDtlLineNum_A1", globalMsg.A.no(i).cpoDtlLineNum_A1.getValue());

                    int rlsCnt = 0;
                    int canCnt = 0;

                    for (int setNum : setItemLine) {
                        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(setNum).xxChkBox_A1.getValue())) {
                            rlsCnt++;
                        } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(setNum).xxChkBox_A2.getValue())) {
                            canCnt++;
                        }
                    }

                    if (setItemLine.size() == rlsCnt || setItemLine.size() == canCnt) {
                        oldCpoDtlLineNum = globalMsg.A.no(i).cpoDtlLineNum_A1.getValue();
                    } else {

                        for (int setNum : setItemLine) {
                            globalMsg.A.no(setNum).xxChkBox_A1.setErrorInfo(1, "NPAM1581E", new String[] {});
                            globalMsg.A.no(setNum).xxChkBox_A2.setErrorInfo(1, "NPAM1581E", new String[] {});
                        }

                        errFlg = true;
                    }
                }
            }
        }

        if (chkRlsCnt > 0) {
            if (!ZYPCommonFunc.hasValue(bizMsg.vndInvNum_H2)) {
                bizMsg.vndInvNum_H2.setErrorInfo(1, "NPAM0009E", new String[] {});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.invDt_H2)) {
                bizMsg.invDt_H2.setErrorInfo(1, "NPAM0009E", new String[] {});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.spTotFuncFrtAmt_H2)) {
                bizMsg.spTotFuncFrtAmt_H2.setErrorInfo(1, "NPAM0009E", new String[] {});
                errFlg = true;
            } else if (bizMsg.spTotFuncFrtAmt_H2.getValue().compareTo(BigDecimal.ZERO) < 0) {
                bizMsg.spTotFuncFrtAmt_H2.setErrorInfo(1, "NPAM0051E", new String[] {"Freight Charge" });
                errFlg = true;
            }
        }

        if (chkRlsCnt + chkCanCnt == 0) {
            bizMsg.setMessageInfo("NZZM0011E");
            errFlg = true;
        }

        if (errFlg) {
            if (firstErrLineNum < 0) {
                firstErrLineNum = 0;
            }
            NPAL1340CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrLineNum);
            return;
        }

        String slsDt = ZYPDateUtil.getSalesDate();
        String sceOrdTpCd = SCE_ORD_TP.DOMESTIC_PO_EXISTS;

        if (!releaseList.isEmpty() || !cancelList.isEmpty()) {

            int rowNum = 0;
            if (!releaseList.isEmpty()) {
                rowNum = releaseList.get(0);
            } else if (!cancelList.isEmpty()) {
                rowNum = cancelList.get(0);
            }

            POTMsg inPoMsg = new POTMsg();
            ZYPEZDItemValueSetter.setValue(inPoMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inPoMsg.poOrdNum, bizMsg.poOrdNum_H2);
            POTMsg outPoMsg = (POTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inPoMsg);
            if (outPoMsg == null) {
                bizMsg.setMessageInfo("NPAM0006E");
                return;
            } else if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(rowNum).ezUpTime_PO.getValue(), globalMsg.A.no(rowNum).ezUpTimeZone_PO.getValue(), outPoMsg.ezUpTime.getValue(), outPoMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NPAM0006E");
                return;
            }else {
                DS_PO_TPTMsg inDsPoTpMsg = new DS_PO_TPTMsg();
                ZYPEZDItemValueSetter.setValue(inDsPoTpMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inDsPoTpMsg.dsPoTpCd, outPoMsg.dsPoTpCd);
                DS_PO_TPTMsg outDsPoTpMsg = (DS_PO_TPTMsg) S21CacheTBLAccessor.findByKey(inDsPoTpMsg);
                sceOrdTpCd = outDsPoTpMsg.sceOrdTpCd.getValue();
            }
        }

        if (!releaseList.isEmpty()) {
            NPZC131001PMsg pMsg = new NPZC131001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC131001Constant.MODE_CREATE);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, bizMsg.poOrdNum_H2);
            ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, bizMsg.vndInvNum_H2);
            ZYPEZDItemValueSetter.setValue(pMsg.etaDt, bizMsg.invDt_H2);
            ZYPEZDItemValueSetter.setValue(pMsg.shipDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.vndInvNum, bizMsg.vndInvNum_H2);
            ZYPEZDItemValueSetter.setValue(pMsg.frtAmt, bizMsg.spTotFuncFrtAmt_H2);
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sceOrdTpCd);

            int rlsIdx = 0;
            int serIdx = 0;

            for (int i : releaseList) {

                PO_DTLTMsg inPoDtlMsg = new PO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(inPoDtlMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inPoDtlMsg.poOrdNum, bizMsg.poOrdNum_H2);
                ZYPEZDItemValueSetter.setValue(inPoDtlMsg.poOrdDtlLineNum, globalMsg.A.no(i).poOrdDtlLineNum_A1);
                PO_DTLTMsg outPoDtlMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inPoDtlMsg);
                if (outPoDtlMsg == null) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                } else if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_PD.getValue(), globalMsg.A.no(i).ezUpTimeZone_PD.getValue(), outPoDtlMsg.ezUpTime.getValue(), outPoDtlMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                }

                SHPG_PLNTMsg inShpgPlnMsg = new SHPG_PLNTMsg();
                ZYPEZDItemValueSetter.setValue(inShpgPlnMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inShpgPlnMsg.shpgPlnNum, globalMsg.A.no(i).shpgPlnNum_A1);
                SHPG_PLNTMsg outShpgPlnMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inShpgPlnMsg);
                if (outShpgPlnMsg == null) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                } else if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_SP.getValue(), globalMsg.A.no(i).ezUpTimeZone_SP.getValue(), outShpgPlnMsg.ezUpTime.getValue(), outShpgPlnMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                }

                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(rlsIdx).poOrdDtlLineNum, globalMsg.A.no(i).origPoOrdDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(rlsIdx).mdseCd, globalMsg.A.no(i).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(rlsIdx).stkStsCd, STK_STS.GOOD);
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(rlsIdx).shipQty, globalMsg.A.no(i).poRcvQty_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(rlsIdx).carrCd, globalMsg.A.no(i).carrCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(rlsIdx).proNum, globalMsg.A.no(i).proNum_A1);

                ArrayList<EZDSStringItem> serList = NPAL1340CommonLogic.getArraySerialNum(globalMsg, i);
                for (EZDSStringItem serial : serList) {
                    if (ZYPCommonFunc.hasValue(serial)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(serIdx).poOrdDtlLineNum, globalMsg.A.no(i).origPoOrdDtlLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(serIdx).mdseCd, globalMsg.A.no(i).mdseCd_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(serIdx).serNum, serial);
                        serIdx++;
                    }
                }
                rlsIdx++;
            }
            pMsg.ordDtlInfo.setValidCount(rlsIdx);
            pMsg.serNumList.setValidCount(serIdx);

            NPZC131001 npzc131001 = new NPZC131001();
            npzc131001.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);

                    if (msgId.endsWith("E")) {
                        return;
                    }
                }
            }
        }

        if (!cancelList.isEmpty()) {
            NPZC131001PMsg pMsg = new NPZC131001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC131001Constant.MODE_CANCEL);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, bizMsg.poOrdNum_H2);
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sceOrdTpCd);

            int canIdx = 0;
            List<PO_DTLTMsg> poDtlList = new ArrayList<PO_DTLTMsg>();

            for (int i : cancelList) {

                PO_DTLTMsg inPoDtlMsg = new PO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(inPoDtlMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inPoDtlMsg.poOrdNum, bizMsg.poOrdNum_H2);
                ZYPEZDItemValueSetter.setValue(inPoDtlMsg.poOrdDtlLineNum, globalMsg.A.no(i).poOrdDtlLineNum_A1);
                PO_DTLTMsg outPoDtlMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inPoDtlMsg);
                if (outPoDtlMsg == null) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                } else if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_PD.getValue(), globalMsg.A.no(i).ezUpTimeZone_PD.getValue(), outPoDtlMsg.ezUpTime.getValue(), outPoDtlMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                }

                SHPG_PLNTMsg inShpgPlnMsg = new SHPG_PLNTMsg();
                ZYPEZDItemValueSetter.setValue(inShpgPlnMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inShpgPlnMsg.shpgPlnNum, globalMsg.A.no(i).shpgPlnNum_A1);
                SHPG_PLNTMsg outShpgPlnMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inShpgPlnMsg);
                if (outShpgPlnMsg == null) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                } else if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_SP.getValue(), globalMsg.A.no(i).ezUpTimeZone_SP.getValue(), outShpgPlnMsg.ezUpTime.getValue(), outShpgPlnMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo("NPAM0006E");
                    return;
                }

                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(canIdx).poOrdDtlLineNum, globalMsg.A.no(i).origPoOrdDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(canIdx).mdseCd, globalMsg.A.no(i).mdseCd_A1);
                canIdx++;

                if (ZYPCommonFunc.hasValue(bizMsg.poOrdDtlCmntTxt_H2)) {
                    PO_DTLTMsg dtlInMsg = new PO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(dtlInMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(dtlInMsg.poOrdNum, bizMsg.poOrdNum_H2);
                    ZYPEZDItemValueSetter.setValue(dtlInMsg.poOrdDtlLineNum, globalMsg.A.no(i).poOrdDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(dtlInMsg.poOrdDtlCmntTxt, bizMsg.poOrdDtlCmntTxt_H2);
                    poDtlList.add(dtlInMsg);
                }
            }

            pMsg.ordDtlInfo.setValidCount(canIdx);
            NPZC131001 npzc131001 = new NPZC131001();
            npzc131001.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);

                    if (msgId.endsWith("E")) {
                        return;
                    }
                }
            }

            for (PO_DTLTMsg dtlInMsg : poDtlList) {
                String[] updtList = new String[] {"poOrdDtlCmntTxt" };
                S21ApiTBLAccessor.updateSelectionField(dtlInMsg, updtList);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlInMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NLAM1134E");
                }
            }

        }

        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_CMN_Submit================================END", this);
    }
}
