/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2540;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2540.NMAL2540CMsg;
import business.servlet.NMAL2540.constant.NMAL2540Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/11   Fujitsu         N.Sugiura       Update          CSA-QC#4340
 *</pre>
 */
public class NMAL2540Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;
        NMAL2540CMsg bizMsg = new NMAL2540CMsg();

        bizMsg.setBusinessID(NMAL2540Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2540Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;
        NMAL2540CMsg bizMsg = (NMAL2540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_H1);
        scrnMsg.putErrorScreen();

        // 2016/03/11 S21-QC#4340 Mod Start
        if (NMAL2540Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())
                || NMAL2540Constant.MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // 2016/03/11 S21-QC#4340 Mod End

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            Object[] params = (Object[]) getArgForSubScreen();
            if (params instanceof Object[]) {
                EZDBStringItem param0 = (EZDBStringItem) params[NMAL2540Constant.IDX_00];
                EZDBStringItem param1 = (EZDBStringItem) params[NMAL2540Constant.IDX_01];
                EZDBStringItem param2 = (EZDBStringItem) params[NMAL2540Constant.IDX_02];
                EZDBStringItem param3 = (EZDBStringItem) params[NMAL2540Constant.IDX_03];
                EZDBStringItem param4 = (EZDBStringItem) params[NMAL2540Constant.IDX_04];
                EZDBStringItem param5 = (EZDBStringItem) params[NMAL2540Constant.IDX_05];
                EZDBStringItem param6 = (EZDBStringItem) params[NMAL2540Constant.IDX_06];
                EZDBStringItem param7 = (EZDBStringItem) params[NMAL2540Constant.IDX_07];
                EZDBStringItem param8 = (EZDBStringItem) params[NMAL2540Constant.IDX_08];
                EZDBStringItem param9 = (EZDBStringItem) params[NMAL2540Constant.IDX_09];

                param0.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.firstLineAddr_H1)) {
                    param0.setValue(scrnMsg.firstLineAddr_H1.getValue());
                }

                param1.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.scdLineAddr_H1)) {
                    param1.setValue(scrnMsg.scdLineAddr_H1.getValue());
                }

                param2.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.thirdLineAddr_H1)) {
                    param2.setValue(scrnMsg.thirdLineAddr_H1.getValue());
                }

                param3.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.frthLineAddr_H1)) {
                    param3.setValue(scrnMsg.frthLineAddr_H1.getValue());
                }

                param4.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H1)) {
                    param4.setValue(scrnMsg.ctyAddr_H1.getValue());
                }

                param5.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.stCd_H1)) {
                    param5.setValue(scrnMsg.stCd_H1.getValue());
                }

                param6.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.provNm_H1)) {
                    param6.setValue(scrnMsg.provNm_H1.getValue());
                }

                param7.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1)) {
                    param7.setValue(scrnMsg.cntyNm_H1.getValue());
                }

                param8.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.postCd_H1)) {
                    param8.setValue(scrnMsg.postCd_H1.getValue());
                }

                param9.clear();
                if (ZYPCommonFunc.hasValue(scrnMsg.ctryCd_H1)) {
                    param9.setValue(scrnMsg.ctryCd_H1.getValue());
                }
            }
        }
    }
}
