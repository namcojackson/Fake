/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.NMAM8327I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 01/19/2016   Fujitsu         M.Hara          Create          QC#3089
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_DetailApply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START QC#3089 01/19/2016 ADD
        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_DH.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_DH, ZYPConstant.FLG_ON_Y);

            scrnMsg.setMessageInfo(NMAM8327I, new String[] {"Apply"});
            throw new EZDFieldErrorException();
        }
        // END QC#3089 01/19/2016 ADD

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011
    }
}
