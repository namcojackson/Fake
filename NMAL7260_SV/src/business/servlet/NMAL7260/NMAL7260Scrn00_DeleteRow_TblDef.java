/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.MESSAGE_KIND_INFO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/11/02   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7260Scrn00_DeleteRow_TblDef extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_0);

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg  = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!MESSAGE_KIND_INFO.equals(bizMsg.getMessageKind())
                && !MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            // In the case of successful, to clear the flag.
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_0);
        }

        // Add Start 2016/10/20 QC#14936
        // Mod Start 2017/02/27 QC#16011
//        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg);
//        NMAL7260CommonLogic.scrnProtect(scrnMsg);
//        NMAL7260CommonLogic.setBtnProp(this, scrnMsg);
        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.scrnProtect(scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        // Mod Start 2017/02/27 QC#16011
        // Add End 2016/10/20 QC#14936
    }
}
