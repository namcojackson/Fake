/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZIL0030.common.ZZIL0030CommonLogic;
import business.servlet.ZZIL0030.constant.ZZIL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        this.checkBusinessAppGranted(getContextUserInfo().getUserId(), ZZIL0030Constant.BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;

        ZZIL0030CommonLogic.dspScrn00(scrnMsg, this);
        scrnMsg.setFocusItem(scrnMsg.intfcId);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) arg0;

        scrnMsg.intfcId.setNameForMessage("Interface ID");
        scrnMsg.intfcId_2.setNameForMessage("Interface ID");
        scrnMsg.bizApiId_2.setNameForMessage("Business API ID");
        scrnMsg.intfcIdDescTxt_2.setNameForMessage("Interface ID Description");
        scrnMsg.trgtBatJobNm_2.setNameForMessage("Target Batch Job Name");
        scrnMsg.trgtBatJobDescTxt_2.setNameForMessage("Target Batch Job Description");
        scrnMsg.actvFlg_AF.setNameForMessage("Active Flag");
        scrnMsg.frceQueueEnblFlg_FF.setNameForMessage("Force Queuing Enable Flag");
    }
}
