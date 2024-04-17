/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1440;

import static business.blap.NPAL1440.constant.NPAL1440Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1440.constant.NPAL1440Constant.DB_PARAM_PRCH_REQ_LINE_NUM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.DB_PARAM_PRCH_REQ_LINE_SUB_NUM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.DB_PARAM_PRCH_REQ_NUM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.DB_PARAM_PROCR_TP_CD;
import static business.blap.NPAL1440.constant.NPAL1440Constant.EZINAPLID;
import static business.blap.NPAL1440.constant.NPAL1440Constant.EZINTIME;
import static business.blap.NPAL1440.constant.NPAL1440Constant.FULL_PSN_NM_D;
import static business.blap.NPAL1440.constant.NPAL1440Constant.FULL_PSN_NM_H;
import static business.blap.NPAL1440.constant.NPAL1440Constant.LOC_NM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.MDSE_CD;
import static business.blap.NPAL1440.constant.NPAL1440Constant.MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.NPAM0002E;
import static business.blap.NPAL1440.constant.NPAL1440Constant.NZZM0001W;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_APVL_STS_DESC_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_CRAT_DT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_FRZ_FLG;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_LINE_NUM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_LINE_STS_DESC_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_LINE_SUB_NUM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_LOG_EVENT_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_LOG_MODE_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_NUM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_QTY;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_STS_DESC_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRCH_REQ_TP_DESC_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PRNT_VND_NM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.PROCR_TP_DESC_TXT;
import static business.blap.NPAL1440.constant.NPAL1440Constant.RWS_PUT_AWAY_QTY;
import static business.blap.NPAL1440.constant.NPAL1440Constant.SHIP_QTY;
import static business.blap.NPAL1440.constant.NPAL1440Constant.TIME_FORMAT_MMDDYYYYHHMM;
import static business.blap.NPAL1440.constant.NPAL1440Constant.TIME_FORMAT_YYYYMMDDHHMMSSSSS;
import static business.blap.NPAL1440.constant.NPAL1440Constant.TECH_CD;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1440.common.NPAL1440CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1440 PR History Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS       Yasushi Nomura        Create          N/A
 * 02/14/2024   CITS            H.Iwasaki        Update          QC#63476
 *</pre>
 */
public class NPAL1440BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1440_INIT".equals(screenAplID)) {
                doProcess_NPAL1440_INIT((NPAL1440CMsg) cMsg, (NPAL1440SMsg) sMsg);
            } else if ("NPAL1440Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1440Scrn00_PageNext((NPAL1440CMsg) cMsg, (NPAL1440SMsg) sMsg);
            } else if ("NPAL1440Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1440Scrn00_PagePrev((NPAL1440CMsg) cMsg, (NPAL1440SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NPAL1440CMsg
     * @param sMsg NPAL1440SMsg
     */
    private void doProcess_NPAL1440_INIT(NPAL1440CMsg cMsg, NPAL1440SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.prchReqNum)) {
            cMsg.setMessageInfo(NPAM0002E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
        ZYPTableUtil.clear(sMsg.A);
        sMsg.clear();

        // search
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_PRCH_REQ_NUM, cMsg.prchReqNum.getValue());
        ssmParam.put(DB_PARAM_PRCH_REQ_LINE_NUM, cMsg.prchReqLineNum.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.prchReqLineSubNum)) {
            ssmParam.put(DB_PARAM_PRCH_REQ_LINE_SUB_NUM, cMsg.prchReqLineSubNum.getValue());
        } else {
            ssmParam.put(DB_PARAM_PRCH_REQ_LINE_SUB_NUM, null);
        }
        ssmParam.put(DB_PARAM_PROCR_TP_CD, PROCR_TP.WAREHOUSE);

        // Execute
        S21SsmEZDResult result = NPAL1440Query.getInstance().search(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (resultMap.size() >= sMsg.A.length()) {
                // 2000 over
                cMsg.setMessageInfo(NZZM0001W);
            }
            int i = 0;
            for (; i < resultMap.size(); i++) {
                if ((sMsg.A.length()) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                if (i == 0) {
                    // header
                    ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_H, (String) recode.get(PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpDescTxt_H, (String) recode.get(PRCH_REQ_TP_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt_H, (String) recode.get(PRCH_REQ_CRAT_DT));
                    ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsDescTxt_H, (String) recode.get(PRCH_REQ_STS_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm_H, (String) recode.get(FULL_PSN_NM_H));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxNum_D1, new BigDecimal(i + 1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxPopPrm_D1, (String) recode.get(PRCH_REQ_LINE_NUM) + "." + (BigDecimal) recode.get(PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxPopPrm_D2, convertTime2ViewText((String) recode.get(EZINTIME)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).fullPsnNm_D1, (String) recode.get(FULL_PSN_NM_D));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezInAplID_D1, (String) recode.get(EZINAPLID));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLogModeTxt_D1, (String) recode.get(PRCH_REQ_LOG_MODE_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLogEventDescTxt_D1, (String) recode.get(PRCH_REQ_LOG_EVENT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqApvlStsDescTxt_D1, (String) recode.get(PRCH_REQ_APVL_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineStsDescTxt_D1, (String) recode.get(PRCH_REQ_LINE_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_D1, (String) recode.get(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_D1, (String) recode.get(MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqQty_D1, (BigDecimal) recode.get(PRCH_REQ_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).procrTpDescTxt_D1, (String) recode.get(PROCR_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndNm_D1, (String) recode.get(PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_D1, (String) recode.get(LOC_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqFrzFlg_D1, (String) recode.get(PRCH_REQ_FRZ_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipQty_D1, (BigDecimal) recode.get(SHIP_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rwsPutAwayQty_D1, (BigDecimal) recode.get(RWS_PUT_AWAY_QTY));
                // QC#63476
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).techCd_D1, (String) recode.get(TECH_CD));
            }
            sMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(BigDecimal.ONE);
            NPAL1440CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else {
            // not has search result
            cMsg.setMessageInfo(NPAM0002E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    private String convertTime2ViewText(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(TIME_FORMAT_YYYYMMDDHHMMSSSSS);
        SimpleDateFormat sdf2 = new SimpleDateFormat(TIME_FORMAT_MMDDYYYYHHMM);
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1440CMsg
     * @param sMsg NPAL1440SMsg
     */
    private void doProcess_NPAL1440Scrn00_PagePrev(NPAL1440CMsg cMsg, NPAL1440SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1440CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1440CMsg
     * @param sMsg NPAL1440SMsg
     */
    private void doProcess_NPAL1440Scrn00_PageNext(NPAL1440CMsg cMsg, NPAL1440SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        NPAL1440CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
}
