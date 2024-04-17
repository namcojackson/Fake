/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.Arrays;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PGM_TP;
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
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2017/09/14   CITS            M.Naito         Update          QC#18534
 * </pre>
 */
public class CheckSupplies {

    //START 2017/09/14 M.Naito [QC#18534,MOD]
    /**
     * checkMdsContract
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMdsContract(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        String termConditionTonerTypeName = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_TONER_TYPE_NAME, param.glblCmpyCd.getValue());
        String termConditionTonerTypeForMps = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_TONER_TYPE_FOR_MPS, param.glblCmpyCd.getValue());
        String[] termConditionTonerTypeForMpsList = termConditionTonerTypeForMps.split(COMMA);

//        NSXC001001GetCovTmpl getCovTmpl = new NSXC001001GetCovTmpl();
//        CovTmplInfo covTmplInfo = new CovTmplInfo();
//        covTmplInfo.setGlblCmpyCd(param.glblCmpyCd.getValue());
//        covTmplInfo.setSlsDt(param.slsDt.getValue());
        String svcPgmTpCd = null;

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

//            covTmplInfo.setSvcPgmMdseCd(dsContrDtlTMsg.svcPgmMdseCd.getValue());
//            // MPS Contract check
//            if (!getCovTmpl.isMps(covTmplInfo)) {
//                continue;
//            }

            // MPS Contract check
            svcPgmTpCd = getSvcPgmTpCd(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue());
            if (!SVC_PGM_TP.MPS.equals(svcPgmTpCd)) {
                continue;
            }

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // check term & condition toner type
            String svcTermAttrbMapValCd = getSvcTermAttrbMapValCd(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), termConditionTonerTypeName);
            if (!Arrays.asList(termConditionTonerTypeForMpsList).contains(svcTermAttrbMapValCd)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0783E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    //END 2017/09/14 M.Naito [QC#18534,MOD]
}
