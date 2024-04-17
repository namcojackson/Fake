/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/07   Fujitsu         C.Tanaka        Update          CSA-QC#11178
 *</pre>
 */
public class NMAL2610_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        int index = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(index);

        // Resource#
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(index).psnNum_D1, scrnMsg.xxPopPrm_0);
        }

        // Employee ID
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(index).psnCd_D1, scrnMsg.xxPopPrm_1);
        }

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_WARN)) {
            // 2016/07/07 CSA-QC#11178 Add Start
            scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt()).psnCd_D1.clear();
            NMAL2610CommonLogic.addCheckItem(scrnMsg, true);
            scrnMsg.putErrorScreen();
            // 2016/07/07 CSA-QC#11178 End Start
            throw new EZDFieldErrorException();
        }

    }
}
