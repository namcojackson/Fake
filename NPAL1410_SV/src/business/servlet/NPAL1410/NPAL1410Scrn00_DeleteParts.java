/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.NPAM0049E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1410.NPAL1410CMsg;
import business.servlet.NPAL1410.common.NPAL1410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 07/03/2018  CITS       T.Hakodate           Update           QC#26868
 *</pre>
 */
public class NPAL1410Scrn00_DeleteParts extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        boolean isAnyChecked = false;

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.C.no(i).xxChkBox_C1.getValue())) {
                isAnyChecked = true;
                break;
            }
        }

        // QC#26868 ADD START
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_B1.getValue())) {
                isAnyChecked = true;
                break;
            }
        }
        // QC#26868 ADD END

        if (!isAnyChecked) {
            scrnMsg.setMessageInfo(NPAM0049E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;

        NPAL1410CMsg bizMsg = new NPAL1410CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        NPAL1410CMsg bizMsg = (NPAL1410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1410CommonLogic.setAppFracDigit(scrnMsg);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1410CommonLogic.commonInit(scrnMsg);
        NPAL1410CommonLogic.control(this, scrnMsg, funcList);
    }
}
