/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_UPD;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/14   Hitachi         J.Kim           Update          QC#6581
 * 2016/04/15   Hitachi         J.Kim           Update          QC#7043
 * 2016/06/24   Hitachi         J.Kim           Update          QC#10637
 * 2016/08/23   Fujitsu         C.Tanaka        Update          QC#11026
 * 2016/09/26   Fujitsu         C.Tanaka        Update          QC#12697, QC#11899
 * 2017/10/26   CITS            M.Naito         Update          QC#22052
 *</pre>
 */
public class NLEL0060Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CommonLogic.addCheckItemForTab(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0060CommonLogic.ctrlCmnSubBtnProp(this, scrnMsg);
        NLEL0060CommonLogic.addCheckItemForTab(scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NLEL0060CommonLogic.setTableBGColor(scrnMsg);
        // START 2016/04/21 J.Kim [QC#7113,DEL]
        // NLEL0060CommonLogic.ctrlFieldProp(scrnMsg);
        // END 2016/04/21 J.Kim [QC#7113,DEL]

        scrnMsg.setFocusItem(scrnMsg.assetTpCd_H1);

        // START 2017/10/26 M.Naito [QC#22052,DEL]
//        scrnMsg.setMessageInfo(NZZM0002I, null, 0);
        // END 2017/10/26 M.Naito [QC#22052,DEL]
    }
}
