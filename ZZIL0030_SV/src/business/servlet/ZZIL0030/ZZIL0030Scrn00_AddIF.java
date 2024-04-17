/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.ZZIL0030.common.ZZIL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030Scrn00_AddIF extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        scrnMsg.intfcId_2.clear();
        scrnMsg.bizApiId_2.clear();
        scrnMsg.intfcIdDescTxt_2.clear();
        scrnMsg.trgtBatJobNm_2.clear();
        scrnMsg.trgtBatJobDescTxt_2.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg_AF, "Y");
        ZYPEZDItemValueSetter.setValue(scrnMsg.frceQueueEnblFlg_FF, "N");
        scrnMsg.xxScrEventNm.setValue("AddIF");

        ZZIL0030CommonLogic.setResetValue(scrnMsg);
        ZZIL0030CommonLogic.dspScrn01(scrnMsg, this);

    }
}
