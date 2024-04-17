/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1250;

import static business.blap.NPAL1250.constant.NPAL1250Constant.BIZ_ID;
import static business.blap.NPAL1250.constant.NPAL1250Constant.NZZM0002I;
import static business.blap.NPAL1250.constant.NPAL1250Constant.NPAM1297E;
import static com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant.PROCESS_MODE_CUST_UPD;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1250.common.NPAL1250CommonLogic;
import business.db.SELL_TO_CUSTTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1250BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1250Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1250Scrn00_CMN_Submit((NPAL1250CMsg) cMsg, (NPAL1250SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * On Click Edit.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1250Scrn00_CMN_Submit(NPAL1250CMsg cMsg, NPAL1250SMsg sMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue(), BIZ_ID);

        List<NMZC001001PMsg> pMsgList = new ArrayList<NMZC001001PMsg>();

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {

            if ((ZYPCommonFunc.hasValue(cMsg.A.no(index).bigDealNum_A) && !cMsg.A.no(index).bigDealNum_A.getValue().equals(sMsg.A.no(index).bigDealNum_A.getValue()) || (!ZYPCommonFunc.hasValue(cMsg.A.no(index).bigDealNum_A))
                    && ZYPCommonFunc.hasValue(sMsg.A.no(index).bigDealNum_A))) {

                NMZC001001PMsg pMsg = new NMZC001001PMsg();
                // XX_MODE_CD
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, PROCESS_MODE_CUST_UPD);

                // Global Company Code
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);

                // Sales Date
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);

                // Account Number
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, cMsg.A.no(index).sellToCustCd_A);

                // Registration Status Code
                ZYPEZDItemValueSetter.setValue(pMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                // Get Lock for SELL_TO_CUST Table.
                SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();

                sellToCustTMsg.glblCmpyCd.setValue(glblCmpyCd);
                sellToCustTMsg.sellToCustPk.setValue(cMsg.A.no(index).dsAcctCustPk_A.getValue());

                sellToCustTMsg = (SELL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(sellToCustTMsg);

                if (sellToCustTMsg == null) {
                    cMsg.A.no(index).bigDealNum_A.setErrorInfo(1, NPAM1297E);
                    cMsg.setMessageInfo(NPAM1297E);
                    return;
                }

                if (!sellToCustTMsg.ezUpTime.getValue().equals(cMsg.A.no(index).ezUpTime_A3.getValue()) || !sellToCustTMsg.ezUpTimeZone.getValue().equals(cMsg.A.no(index).ezUpTimeZone_A3.getValue())) {
                    cMsg.A.no(index).bigDealNum_A.setErrorInfo(1, NPAM1297E);
                    cMsg.setMessageInfo(NPAM1297E);
                    return;
                }

                // Doing Business As Name
                ZYPEZDItemValueSetter.setValue(pMsg.dbaNm, sellToCustTMsg.dbaNm);

                // Account Name
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNm, sellToCustTMsg.dsAcctNm);

                // Internal / External
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctItrlFlg, sellToCustTMsg.dsAcctItrlFlg);

                // GL Classification code
                ZYPEZDItemValueSetter.setValue(pMsg.coaChCd, sellToCustTMsg.coaChCd);

                // GL Intercompany code
                ZYPEZDItemValueSetter.setValue(pMsg.coaAfflCd, sellToCustTMsg.coaAfflCd);

                // Dealer Code
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDlrCd, sellToCustTMsg.dsAcctDlrCd);

                // Account Legal Name
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctLegalNm, sellToCustTMsg.dsAcctLegalNm);

                // URL Address
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctUrl, sellToCustTMsg.dsAcctUrl);

                // Classification Code
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctClsCd, sellToCustTMsg.dsAcctClsCd);

                // External Reference
                ZYPEZDItemValueSetter.setValue(pMsg.dsXtrnlRefTxt, sellToCustTMsg.dsXtrnlRefTxt);

                // Source
                ZYPEZDItemValueSetter.setValue(pMsg.dsDataSrcTxt, sellToCustTMsg.dsDataSrcTxt);

                // Get Lock for SHIP_TO_CUST Table.
                SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();

                shipToCustTMsg.glblCmpyCd.setValue(glblCmpyCd);
                shipToCustTMsg.shipToCustPk.setValue(cMsg.A.no(index).shipToCustPk_A.getValue());

                shipToCustTMsg = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shipToCustTMsg);

                if (shipToCustTMsg == null) {
                    cMsg.A.no(index).bigDealNum_A.setErrorInfo(1, NPAM1297E);
                    cMsg.setMessageInfo(NPAM1297E);
                    return;
                }

                if (!shipToCustTMsg.ezUpTime.getValue().equals(cMsg.A.no(index).ezUpTime_A1.getValue()) || !shipToCustTMsg.ezUpTimeZone.getValue().equals(cMsg.A.no(index).ezUpTimeZone_A1.getValue())) {
                    cMsg.A.no(index).bigDealNum_A.setErrorInfo(1, NPAM1297E);
                    cMsg.setMessageInfo(NPAM1297E);
                    return;
                }

                // Classification Code
                PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();

                ptyLocWrkTMsg.glblCmpyCd.setValue(glblCmpyCd);
                ptyLocWrkTMsg.ptyLocPk.setValue(shipToCustTMsg.ptyLocPk.getValue());

                ptyLocWrkTMsg = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(ptyLocWrkTMsg);

                // if (ptyLocWrkTMsg != null) {
                // ZYPEZDItemValueSetter.setValue(pMsg.,
                // sellToCustTMsg.dsAcctLegalNm);
                // }

                // Ship to customer flag
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).shipToCustFlg, ZYPConstant.FLG_ON_Y);

                // Location Number
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).locNum, shipToCustTMsg.locNum);

                // First Line Address
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).firstLineAddr, shipToCustTMsg.firstLineAddr);

                // Second Line Address
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).scdLineAddr, shipToCustTMsg.scdLineAddr);

                // Third Line Address
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).thirdLineAddr, shipToCustTMsg.thirdLineAddr);

                // Fourth Line Address
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).frthLineAddr, shipToCustTMsg.frthLineAddr);

                // Fourth Line Address
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).ctyAddr, shipToCustTMsg.ctyAddr);

                // Provience Name
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).provNm, shipToCustTMsg.provNm);

                // County Name
                if (ZYPCommonFunc.hasValue(shipToCustTMsg.cntyPk)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).cntyNm, NPAL1250CommonLogic.getCntyNm(glblCmpyCd, shipToCustTMsg.cntyPk.getValue()));
                }

                // State Code
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).stCd, shipToCustTMsg.stCd.getValue());

                // Postal Code
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).postCd, shipToCustTMsg.postCd.getValue());

                // Country Code
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).ctryCd, shipToCustTMsg.ctryCd.getValue());

                // Location Name
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).locNm, ptyLocWrkTMsg.dsLocNm.getValue());
                
                // Additional Location Name
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).addlLocNm, shipToCustTMsg.addlLocNm.getValue());

                // Global Location Number
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).glnNum, shipToCustTMsg.glnNum.getValue());

                // Telephone Number
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).telNum, shipToCustTMsg.telNum.getValue());

                // FAX Number
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).faxNum, shipToCustTMsg.faxNum.getValue());

                // Tax area ID
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).geoCd, shipToCustTMsg.geoCd.getValue());

                // Direct Sales Inside City Limit Flag
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);

                ZYPEZDItemValueSetter.setValue(pMsg.firstRefCmntTxt, shipToCustTMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.scdRefCmntTxt, shipToCustTMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsNum, shipToCustTMsg.dunsNum);

                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).effFromDt_S, cMsg.A.no(index).effFromDt_A);
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).effThruDt_S, cMsg.A.no(index).effThruDt_A);

                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).relnBillToCustCd_S, shipToCustTMsg.relnBillToCustCd);
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).lineBizTpCd_S, shipToCustTMsg.lineBizTpCd);
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).lineBizTpCd, shipToCustTMsg.lineBizTpCd);
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).indyTpCd, shipToCustTMsg.indyTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).coaAfflCd_S, shipToCustTMsg.coaAfflCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).coaChCd_S, shipToCustTMsg.coaChCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).bigDealNum_S, cMsg.A.no(index).bigDealNum_A);

                pMsg.NMZC001002PMsg.setValidCount(1);
                pMsgList.add(pMsg);
            }
        }

        if (pMsgList.size() > 0) {
            // Call Customer Update API
            NMZC001001 customerUpdateApi = new NMZC001001();
            customerUpdateApi.execute(pMsgList, ONBATCH_TYPE.ONLINE);
            for (NMZC001001PMsg pMsg : pMsgList) {
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                        String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        String[] msgPrms = {pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue(), pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue(), pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_2.getValue(), pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_3.getValue(),
                                pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_4.getValue() };
                        cMsg.setMessageInfo(msgId, msgPrms);
                        if (msgId.endsWith("E")) {
                            return;
                        }
                    }
                }

                for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
                    NMZC001002PMsg pMsg02 = pMsg.NMZC001002PMsg.no(i);
                    if (S21ApiUtil.isXxMsgId(pMsg02)) {
                        for (int j = 0; j < pMsg02.xxMsgIdList.getValidCount(); j++) {
                            String msgId = pMsg02.xxMsgIdList.no(j).xxMsgId.getValue();
                            String[] msgPrms = {pMsg02.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue(), pMsg02.xxMsgIdList.no(j).xxMsgPrmTxt_1.getValue(), pMsg02.xxMsgIdList.no(j).xxMsgPrmTxt_2.getValue(),
                                    pMsg02.xxMsgIdList.no(j).xxMsgPrmTxt_3.getValue(), pMsg02.xxMsgIdList.no(j).xxMsgPrmTxt_4.getValue() };
                            cMsg.setMessageInfo(msgId, msgPrms);
                            if (msgId.endsWith("E")) {
                                return;
                            }
                        }
                    }
                }
            }
        }

        cMsg.setMessageInfo(NZZM0002I);
    }
}
