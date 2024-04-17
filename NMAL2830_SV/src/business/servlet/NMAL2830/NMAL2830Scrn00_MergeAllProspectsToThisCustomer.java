/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BIZ_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2830.NMAL2830CMsg;
import business.servlet.NMAL2830.common.NMAL2830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2830Scrn00_MergeAllProspectsToThisCustomer
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830Scrn00_MergeAllProspectsToThisCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        NMAL2830CMsg bizMsg = new NMAL2830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum_CH, BigDecimal.valueOf(getButtonSelectNumber()));

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;
        NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2830CommonLogic.initCmnBtnProp(this);
        NMAL2830CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).xxChkBox_AM);
    }
}