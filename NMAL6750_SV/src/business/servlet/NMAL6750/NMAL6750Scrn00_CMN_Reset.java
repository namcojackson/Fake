/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Tanaka        Create          CSA
 * 2016/04/06   Fujitsu         C.Yokoi         Update          CSA-QC#6633
 *</pre>
 */
public class NMAL6750Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/04/06 CSA-QC#6633 Add Start
        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        String ctacPsnNum_P1 = scrnMsg.ctacPsnNum_P1.getValue();
        String dsAcctNum_P1 = scrnMsg.dsAcctNum_P1.getValue();
        String locNum_P1 = scrnMsg.locNum_P1.getValue();

        scrnMsg.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnNum_P1, ctacPsnNum_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_P1, dsAcctNum_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_P1, locNum_P1);
        // 2016/04/06 CSA-QC#6633 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
