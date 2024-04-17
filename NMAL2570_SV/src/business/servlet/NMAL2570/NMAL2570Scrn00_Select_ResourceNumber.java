/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/04/19   SRAA            Y.Chen          Update          QC#6919
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL2570Scrn00_Select_ResourceNumber extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            int index = getButtonSelectNumber();

            // Resource#
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).psnNum_A1)) {
                ZYPEZDItemValueSetter.setValue(param0, scrnMsg.A.no(index).psnNum_A1);
            }

            // Employee ID
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).psnCd_A1)) {
                ZYPEZDItemValueSetter.setValue(param1, scrnMsg.A.no(index).psnCd_A1);
            }

            // QC#6919
            // Resource Name
            if (params.length >= 4) {
                EZDBStringItem param3 = (EZDBStringItem) params[3];
                String xxPsnNm = scrnMsg.A.no(index).psnFirstNm_A1.getValue() + " " + scrnMsg.A.no(index).psnLastNm_A1.getValue();
                ZYPEZDItemValueSetter.setValue(param3, xxPsnNm);
            }
            
            // QC#7781
            if(NMAL2570Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())){
                if (params instanceof Object[]) {
                    // Person Code
                    if (params.length >= 10) {
                        EZDBStringItem[] param = (EZDBStringItem[]) params[9];
                        for(EZDBStringItem item : param){
                            item.clear();
                        }
                        if (param.length > 0) {
                            ZYPEZDItemValueSetter.setValue(param[0], scrnMsg.A.no(index).psnCd_A1);
                        }
                    }
                }
            }
        }
    }
}
