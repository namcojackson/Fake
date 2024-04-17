/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.SlaInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Hitachi         Y.Takeno        Create          QC#447
 * 2016/06/28   Hitachi         K.Kasai         Update          QC#10900
 * 2016/06/30   Hitachi         K.Kasai         Update          QC#11189
 * </pre>
 */
public class CheckSLA {

    /**
     * checkSLAOverlap
     * 
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkSLAOverlap(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);

        if (dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.WARRANTY)) {
            return rtrnTMsgArray;
        }

        // del start 2016/06/28 CSA Defect#10900
        //List<Map<String, Object>> cntrEffTermList = NSZC057001CommonLogic.getAddrChrgContr(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        // del end 2016/06/28 CSA Defect#10900

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            BigDecimal dsContrDtlPk = null;
            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            } else if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                for (int j = 0; j < dsContrDtlTMsgArray.getValidCount(); j++) {
                    DS_CONTR_DTLTMsg fltDsContrDtlTMsg = dsContrDtlTMsgArray.no(j);
                    if (DS_CONTR_DTL_TP.FLEET.equals(fltDsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                        dsContrDtlPk = fltDsContrDtlTMsg.dsContrDtlPk.getValue();
                        break;
                    }
                }
            } else {
                dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
            }

            List<Map<String, Object>> dtlEffTermList = NSZC057001CommonLogic.getAddrChrgContrDtl(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk);
            // del start 2016/06/28 CSA Defect#10900
            //dtlEffTermList.addAll(cntrEffTermList);
            // del end 2016/06/28 CSA Defect#10900
            if (dtlEffTermList.size() <= 1) {
                continue;
            }

            for (int j = 0; j < dtlEffTermList.size(); j++) {
                if (isExistDuplicateLine(dtlEffTermList, j)) {
                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0980E, new String[] {getSerNum(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    break;
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkSLA
     * 
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkSLA(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);

        if (dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.WARRANTY)) {
            return rtrnTMsgArray;
        }
        // add start 2016/06/30 CSA Defect#11189
        BigDecimal dsContrDtlPkFlt = null;
        if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
                if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsgArray.no(i).dsContrDtlTpCd.getValue())) {
                    dsContrDtlPkFlt = dsContrDtlTMsgArray.no(i).dsContrDtlPk.getValue();
                    break;
                }
            }
        }
        // add end 2016/06/30 CSA Defect#11189

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            String slaDt = null;
            if (ZYPDateUtil.compare(dsContrTMsg.contrVrsnEffFromDt.getValue(), param.slsDt.getValue()) == 1) {
                slaDt = dsContrTMsg.contrVrsnEffFromDt.getValue();
            } else {
                slaDt = param.slsDt.getValue();
            }

            SlaInfoBean infoBean = new SlaInfoBean();
            infoBean.setGlblCmpyCd(param.glblCmpyCd.getValue());
            infoBean.setDsContrDtlPk(dsContrDtlTMsg.dsContrDtlPk.getValue());
            // add start 2016/06/30 CSA Defect#11189
            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                infoBean.setDsContrDtlPk(dsContrDtlPkFlt);
            }
            // add end 2016/06/30 CSA Defect#11189
            infoBean.setSlaDt(slaDt);

            if (NSXC001001GetRspTime.getSLA(infoBean)) {
                if (ZYPConstant.FLG_OFF_N.equals(infoBean.getSlaAddlChrgFlg())) {
                    continue;
                }

                boolean result = false;
                String termCondAttrNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_RSP_TM_COMIT, param.glblCmpyCd.getValue());
                Map<String, Object> termCond = NSZC057001CommonLogic.getTermCond(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), slaDt, termCondAttrNm);
                if (termCond != null) {
                    String tcOptValTxt = infoBean.getTermCondOptValTxt();
                    String attrbMapValCd = (String) termCond.get("SVC_TERM_ATTRB_MAP_VAL_CD");
                    if (tcOptValTxt.equals(attrbMapValCd)) {
                        result = true;
                    }
                }

                if (!result) {
                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0981E, new String[] {getSerNum(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                }

            } else {
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(infoBean.getXxMsgId()), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    private static final boolean isExistDuplicateLine(List<Map<String, Object>> resultList, int selIdx) {
        Map<String, Object> selResult = resultList.get(selIdx);
        String selEffFromDt = (String) selResult.get("EFF_FROM_DT");
        String selEffThruDt = (String) selResult.get("EFF_THRU_DT");

        for (int i = 0; i < resultList.size(); i++) {
            if (selIdx == i) {
                continue;
            }

            Map<String, Object> result = resultList.get(i);
            String effFromDt = (String) result.get("EFF_FROM_DT");
            String effThruDt = (String) result.get("EFF_THRU_DT");

            if (ZYPDateUtil.compare(replaceEmptyDt(effThruDt), selEffFromDt) >= 0
                    && ZYPDateUtil.compare(replaceEmptyDt(selEffThruDt), effFromDt) >= 0) {
                return true;
            }
        }

        return false;
    }

    private static final String replaceEmptyDt(String dt) {
        if (!hasValue(dt)) {
            return END_DT;
        }
        return dt;
    }
}
