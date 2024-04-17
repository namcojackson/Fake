/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3180;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NLBL3180.constant.NLBL3180Constant;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;

/**
 * <pre>
 * Ship Detail Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/18   CITS            J.Evangelista   Create          QC#58876
 * 08/18/2021   CITS            K.Ogino         Update          QC#58876-1
 * 08/18/2021   CITS            K.Ogino         Update          QC#58876-2
 * 05/10/2023   CITS            K.Ogino         Update          QC#61443
 * </pre>
 */
public class NLBL3180BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3180_INIT".equals(screenAplID)) {
                doProcess_NLBL3180_INIT((NLBL3180CMsg) cMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3180_INIT
     * @param cMsg NLBL3180CMsg
     */
    private void doProcess_NLBL3180_INIT(NLBL3180CMsg cMsg) {
        search(cMsg);
    }

    /**
     * search
     * @param cMsg NLBL3180CMsg
     */
    @SuppressWarnings("unchecked")
    private void search(NLBL3180CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Create otbdOrdNumList
        List<String> otbdOrdNumList = new ArrayList();
        String soNum = cMsg.soNum_H1.getValue();
        otbdOrdNumList.add(soNum);

        // Get otbdOrdLineNum
        BigDecimal otbdOrdLineNum = null;
        if (ZYPCommonFunc.hasValue(cMsg.trxRefNum)) {
            otbdOrdNumList.add(cMsg.trxRefNum.getValue());
            otbdOrdLineNum = new BigDecimal((String) cMsg.prchReqLineNum.getValue());
        } else {
            String soSlpNum = NLBL3180Query.getInstance().getSoSlpNum(glblCmpyCd, soNum,
                    cMsg.prchReqNum.getValue(), cMsg.prchReqLineNum.getValue(), cMsg.prchReqLineSubNum.getValue());
            if (ZYPCommonFunc.hasValue(soSlpNum)) {
                otbdOrdLineNum = new BigDecimal((String) soSlpNum);
            } else {
                cMsg.setMessageInfo(NLBL3180Constant.NLBM1109E);
                return;
            }
        }

        // Get latest transaction
        // QC#58876-1 Mod Add
        String trxId = "";
        String wrkTrxId = "";
        // QC#58876-2 Start
        String preQuateDelyTxt = "";
        S21SsmEZDResult firstTrxResult = NLBL3180Query.getInstance().getFirstTrxPod(glblCmpyCd, otbdOrdNumList, otbdOrdLineNum);
        if (firstTrxResult.isCodeNormal()) {
            Map<String, Object> firstTrx = (Map<String, Object>) firstTrxResult.getResultObject();
            String plnLclTxt = (String) firstTrx.get("PLN_LOCAL_TXT");
            String shipLclTz = (String) firstTrx.get("SHIP_LCL_TZ");
            // QC#61443 Add start
            if (ZYPCommonFunc.hasValue(plnLclTxt)) {
                plnLclTxt = ZYPDateUtil.DateFormatter(plnLclTxt.substring(0, NLBL3180Constant.TIME_FORMAT_YYYYMMDDHHMM.length()), NLBL3180Constant.TIME_FORMAT_YYYYMMDDHHMM, NLBL3180Constant.TIME_FORMAT_MMDDYYYYHHMM);
                if (ZYPCommonFunc.hasValue(shipLclTz)) {
                    preQuateDelyTxt = plnLclTxt + " " + shipLclTz;
                } else {
                    preQuateDelyTxt = plnLclTxt;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.rqstQuoteDelyTxt_DA, preQuateDelyTxt);
            }
        }

        S21SsmEZDResult latestPodResult = NLBL3180Query.getInstance().getLatestTrxPod(glblCmpyCd, otbdOrdNumList, otbdOrdLineNum);
        if (latestPodResult.isCodeNormal()) {
            Map<String, Object> latestPodTrx = (Map<String, Object>) latestPodResult.getResultObject();
            trxId = (String) latestPodTrx.get(NLBL3180Constant.DB_COLUMN_INTFC_TRX_ID);
            wrkTrxId = (String) latestPodTrx.get(NLBL3180Constant.DB_COLUMN_WRK_TRX_ID);
        }

        S21SsmEZDResult result = NLBL3180Query.getInstance().getLatestTrx(glblCmpyCd, otbdOrdNumList, otbdOrdLineNum, trxId, wrkTrxId);
        if (result.isCodeNormal()) {

            Map<String, Object> latestTrx = (Map<String, Object>) result.getResultObject();
            trxId = (String) latestTrx.get(NLBL3180Constant.DB_COLUMN_INTFC_TRX_ID);
            wrkTrxId = (String) latestTrx.get(NLBL3180Constant.DB_COLUMN_WRK_TRX_ID);

            ZYPEZDItemValueSetter.setValue(cMsg.bolVchNum_H1, (String) latestTrx.get(NLBL3180Constant.DB_COLUMN_BOL_VCH_NUM));
        }

        // Get ship detail
        if (ZYPCommonFunc.hasValue(trxId)) {

            result = NLBL3180Query.getInstance().getShipDetail(glblCmpyCd, trxId, otbdOrdNumList, otbdOrdLineNum, trxId, wrkTrxId);
            if (result.isCodeNormal()) {

                int index = 0;
                List<Map> shipDetailList = (List<Map>) result.getResultObject();
                for (Map<String, Object> shipDetail : shipDetailList) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).unitId_A1, new BigDecimal((String) shipDetail.get(NLBL3180Constant.DB_COLUMN_INTFC_TRX_SQ_NUM)));
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).descHistTxt_A1, (String) shipDetail.get(NLBL3180Constant.DB_COLUMN_DESC_HIST_TXT));
                    String cpltLocalTxt = (String) shipDetail.get(NLBL3180Constant.DB_COLUMN_CPLT_LOCAL_TXT);
                    String shipLclTz = (String) shipDetail.get("SHIP_LCL_TZ");
                    cpltLocalTxt = ZYPDateUtil.DateFormatter(cpltLocalTxt.substring(0, NLBL3180Constant.TIME_FORMAT_YYYYMMDDHHMM.length()), NLBL3180Constant.TIME_FORMAT_YYYYMMDDHHMM, NLBL3180Constant.TIME_FORMAT_MMDDYYYYHHMM);
                    if (ZYPCommonFunc.hasValue(shipLclTz)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxScrItem30Txt_A1, cpltLocalTxt + " " + shipLclTz);
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxScrItem30Txt_A1, cpltLocalTxt);
                    }

                    if (WMS_TASK.POD.equals(shipDetail.get(NLBL3180Constant.DB_COLUMN_WMS_TASK_CD))) {
                        String latestPlanDate = (String) shipDetail.get(NLBL3180Constant.DB_COLUMN_PLN_LOCAL_TXT);
                        // QC#61443 Add start
                        if (ZYPCommonFunc.hasValue(latestPlanDate)) {
                            latestPlanDate = ZYPDateUtil.DateFormatter(latestPlanDate.substring(0, NLBL3180Constant.TIME_FORMAT_YYYYMMDDHHMM.length()), NLBL3180Constant.TIME_FORMAT_YYYYMMDDHHMM, NLBL3180Constant.TIME_FORMAT_MMDDYYYYHHMM);
                            if (ZYPCommonFunc.hasValue(shipLclTz)) {
                                latestPlanDate = latestPlanDate + " " + shipLclTz;
                            }
                            if (ZYPCommonFunc.hasValue(preQuateDelyTxt) && !preQuateDelyTxt.equals(latestPlanDate)) {
                                ZYPEZDItemValueSetter.setValue(cMsg.quoteDelyTxt_DB, latestPlanDate);;
                            }
                        }
                        // QC#61443 Add end
                    }
                    index++;
                }
                cMsg.A.setValidCount(index);
            }
        }
        // QC#58876-1 End
        // QC#58876-2 End

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NLBL3180Constant.NLBM1232I);
        }
    }
}
