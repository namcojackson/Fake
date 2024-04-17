/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.setValueTMsg;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.KEY;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.MAX_SIZE;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.NSZM1327E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/17   CITS            T.Wada           Create          QC#23378
 * 2018/06/04   Hitachi         K.Kim            Update          QC#25868
 * </pre>
 */
public class CheckPriceAllocAmt {

    /**
     * checkPriceAllocAmt
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkPriceAllocAmt(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        if (!ONBATCH_TYPE.ONLINE.equals(mainClass.onBatchType)) {
            return rtrnTMsgArray;
        }

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        // START 2018/06/04 K.Kim [QC#25868, DEL]
        // if (!ZYPConstant.FLG_ON_Y.equals(dsContrTMsg.qltyAsrnHldFlg.getValue())) {
        //     return rtrnTMsgArray;
        // }
        // END 2018/06/04 K.Kim [QC#25868, DEL]

        // get DS_CONTR_DTL Info
        List<Map<String, Object>> dsContrPrcEffInfoList = NSZC057001CommonLogic.getBasePrcDealAmt(mainClass, dsContrTMsg.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());

        for (int i=0; i<dsContrPrcEffInfoList.size(); i++) {

            Map<String, Object> dsContrPrcEffInfo = dsContrPrcEffInfoList.get(i);

            BigDecimal basePrcDealAmt = (BigDecimal)dsContrPrcEffInfo.get("BASE_PRC_DEAL_AMT") ;

            BigDecimal dsContrDtlPk = (BigDecimal)dsContrPrcEffInfo.get("DS_CONTR_DTL_PK") ;

            // get DS_CONTR_PRC_ALLOC Info
            BigDecimal totalPrcAllocAmt = NSZC057001CommonLogic.getTotalPrcAllocAmt(mainClass, dsContrTMsg.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), dsContrDtlPk);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if (!ZYPCommonFunc.hasValue(totalPrcAllocAmt) || !ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                // do Nothing
            } else if (totalPrcAllocAmt.compareTo(basePrcDealAmt) != 0) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1327E), DS_CONTR_DTL_PK, dsContrDtlPk);
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        }
        return rtrnTMsgArray;
    }
}
