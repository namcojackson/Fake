/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0450;

import static business.servlet.NSAL0450.common.NSAL0450CommonLogic.cotrolDetailField;
import static business.servlet.NSAL0450.common.NSAL0450CommonLogic.setRowColors;
import static business.servlet.NSAL0450.constant.NSAL0450Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0450.NSAL0450CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/13   Hitachi         J.Kim           Create          N/A
 * 2016/08/26   Hitachi         A.Kohinata      Update          QC#13778
 * 2019/01/21   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public class NSAL0450_NSAL1230 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;

        NSAL0450CMsg bizMsg = new NSAL0450CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;
        NSAL0450CMsg bizMsg = (NSAL0450CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setRowColors(scrnMsg);
        // Add Start 2019/01/22 QC#29782
        cotrolDetailField(scrnMsg);
        // Add End 2019/01/22 QC#29782
    }
}
