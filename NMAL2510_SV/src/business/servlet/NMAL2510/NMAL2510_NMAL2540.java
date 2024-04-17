/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/04/29   SRAA            Y.Chen          Update          QC#5860
 *</pre>
 */
public class NMAL2510_NMAL2540 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        // QC#5860
        ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_H1, scrnMsg.xxPopPrm_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_H1, scrnMsg.xxPopPrm_1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_H1, scrnMsg.xxPopPrm_2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_H1, scrnMsg.xxPopPrm_3);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_H1, scrnMsg.xxPopPrm_4);
        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_H1, scrnMsg.xxPopPrm_5);
        ZYPEZDItemValueSetter.setValue(scrnMsg.provNm_H1, scrnMsg.xxPopPrm_6);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_H1, scrnMsg.xxPopPrm_7);
        ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_H1, scrnMsg.xxPopPrm_8);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd_H1, scrnMsg.xxPopPrm_9);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
//
//        StringBuilder sb = new StringBuilder();
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_H1, scrnMsg.xxPopPrm_0);
//            sb.append(scrnMsg.firstLineAddr_H1.getValue());
//            sb.append(NMAL2510Constant.SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_H1, scrnMsg.xxPopPrm_1);
//            sb.append(scrnMsg.scdLineAddr_H1.getValue());
//            sb.append(NMAL2510Constant.SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_2)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_H1, scrnMsg.xxPopPrm_2);
//            sb.append(scrnMsg.thirdLineAddr_H1.getValue());
//            sb.append(NMAL2510Constant.SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_3)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_H1, scrnMsg.xxPopPrm_3);
//            sb.append(scrnMsg.frthLineAddr_H1.getValue());
//            sb.append(NMAL2510Constant.SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_4)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_H1, scrnMsg.xxPopPrm_4);
//            sb.append(scrnMsg.ctyAddr_H1.getValue());
//            sb.append(NMAL2510Constant.COMMMA_SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_5)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_H1, scrnMsg.xxPopPrm_5);
//            sb.append(scrnMsg.stCd_H1.getValue());
//            sb.append(NMAL2510Constant.COMMMA_SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_6)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.provNm_H1, scrnMsg.xxPopPrm_6);
//            sb.append(scrnMsg.provNm_H1.getValue());
//            sb.append(NMAL2510Constant.COMMMA_SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_7)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_H1, scrnMsg.xxPopPrm_7);
//            sb.append(scrnMsg.cntyNm_H1.getValue());
//            sb.append(NMAL2510Constant.COMMMA_SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_8)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_H1, scrnMsg.xxPopPrm_8);
//            sb.append(scrnMsg.postCd_H1.getValue());
//            sb.append(NMAL2510Constant.COMMMA_SPACE);
//        }
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_9)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd_H1, scrnMsg.xxPopPrm_9);
//            sb.append(scrnMsg.ctryCd_H1.getValue());
//        }
//
//        // Physical Location
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllLineAddr_H1, sb.toString());

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
