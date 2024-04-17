/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7220;

import static business.blap.NMAL7220.constant.NMAL7220Constant.NMAM8020E;
import static business.blap.NMAL7220.constant.NMAL7220Constant.NZZM0003E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7220.common.NMAL7220CommonLogic;
import business.db.PRC_FMLATMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * NMAL7220BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7220CMsg bizMsg = (NMAL7220CMsg) cMsg;

            if ("NMAL7220Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7220Scrn00_CMN_Submit(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * 
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7220Scrn00_CMN_Submit(NMAL7220CMsg bizMsg) {
        // check
        if (NMAL7220CommonLogic.isErrCheck(bizMsg)) {
            return;
        }

        submitPrcFmla(bizMsg);
    }

    private void submitPrcFmla(NMAL7220CMsg bizMsg) {
        PRC_FMLATMsg tMsg = new PRC_FMLATMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.prcFmlaPk_BK)) {
            ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaPk, bizMsg.prcFmlaPk_BK);
            tMsg = (PRC_FMLATMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_FMLA_SQ));
        }
        // Header
        ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaNm, bizMsg.prcFmlaNm_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaDescTxt, bizMsg.prcFmlaDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, NMAL7220CommonLogic.setActvFlg(bizMsg.actvFlg_H1.getValue()));
        // Detail
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaTpCd, bizMsg.A.no(i).prcFmlaTpCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.A.no(i).prcCatgCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaOpCd, bizMsg.A.no(i).prcFmlaOpCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaNum, bizMsg.A.no(i).prcFmlaNum_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcFuncTpCd, bizMsg.A.no(i).prcFuncTpCd_A1);
        }

        if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.prcFmlaPk_H1))) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaPk_H1, tMsg.prcFmlaPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaPk_BK, tMsg.prcFmlaPk);
    }

    private boolean submitTbl(EZDTMsg inTMsg, boolean isExists) {
        if (isExists) {
            EZDTBLAccessor.update(inTMsg);
        } else {
            EZDTBLAccessor.insert(inTMsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
}
