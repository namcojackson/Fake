/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Hitachi         T.Tomita        Create          QC#2690
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6223
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#5522
 *</pre>
 */
public class NSAL0010_NMAL6790 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        // mod start 2016/04/19 CSA Defect#6223
        if ("CMN_Close".equals(getLastGuard()) && scrnMsg.Y.getValidCount() > 0) {
            int rowNum = getButtonSelectNumber();
            setValue(scrnMsg.xxRowNum, new BigDecimal(rowNum));

            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        // mod end 2016/04/19 CSA Defect#6223
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // mod start 2016/04/19 CSA Defect#6223
        if ("CMN_Close".equals(getLastGuard()) && cMsg != null) {
            NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
            NSAL0010CMsg bizMsg  = (NSAL0010CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // add start 2016/04/25 CSA Defect#5522
            scrnMsg.putErrorScreen();
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            // add end 2016/04/25 CSA Defect#5522
        }
        // mod end 2016/04/19 CSA Defect#6223
    }
}
