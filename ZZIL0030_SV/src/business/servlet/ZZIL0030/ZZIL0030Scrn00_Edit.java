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
public class ZZIL0030Scrn00_Edit extends S21CommonHandler {

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

        // Data Set
        int i = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcId_2, scrnMsg.A.no(i).intfcId_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.bizApiId_2, scrnMsg.A.no(i).bizApiId_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcIdDescTxt_2, scrnMsg.A.no(i).intfcIdDescTxt_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.trgtBatJobNm_2, scrnMsg.A.no(i).trgtBatJobNm_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.trgtBatJobDescTxt_2, scrnMsg.A.no(i).trgtBatJobDescTxt_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg_AF, scrnMsg.A.no(i).actvFlg_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.frceQueueEnblFlg_FF, scrnMsg.A.no(i).frceQueueEnblFlg_A);

        // Update Time and Update Timezone
        scrnMsg.ezUpTime.setValue(scrnMsg.A.no(i).ezUpTime_A.getValue());
        scrnMsg.ezUpTimeZone.setValue(scrnMsg.A.no(i).ezUpTimeZone_A.getValue());
        scrnMsg.xxScrEventNm.setValue("Edit");

        ZZIL0030CommonLogic.setResetValue(scrnMsg);
        ZZIL0030CommonLogic.dspScrn01(scrnMsg, this);

    }
}
