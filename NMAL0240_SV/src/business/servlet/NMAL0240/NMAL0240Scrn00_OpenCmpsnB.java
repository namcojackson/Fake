/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.BIZ_ID;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0240.NMAL0240CMsg;
import business.servlet.NMAL0240.common.NMAL0240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240Scrn00_OpenCmpsnB
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/18   SRAA            Y.Chen          Update          QC#2645
 *</pre>
 */
public class NMAL0240Scrn00_OpenCmpsnB extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        int sel = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_B, scrnMsg.B.no(sel).xxRowNum_B1.getValue());

        NMAL0240CMsg bizMsg = new NMAL0240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
        NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0240CommonLogic.controlRevisionFields(this, scrnMsg);
        // QC#2645
        NMAL0240CommonLogic.controlTableRowColor(scrnMsg);
    }
}
