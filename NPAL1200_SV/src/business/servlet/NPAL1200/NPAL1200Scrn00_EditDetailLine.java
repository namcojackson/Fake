/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_EDIT;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.NPAM1216E;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1200.NPAL1200CMsg;
import business.servlet.NPAL1200.common.NPAL1200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 01/31/2017   CITS            M.Naito          Update          QC#12614
 *</pre>
 */
public class NPAL1200Scrn00_EditDetailLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        boolean isError = true;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                isError = false;
                break;
            }
        }
        if (isError) {
            scrnMsg.setMessageInfo(NPAM1216E);
        }
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        NPAL1200CMsg bizMsg = new NPAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (!"E".equals(bizMsg.getMessageKind())) {
            scrnMsg.xxNum_MD.setValue(MODE_EDIT);
        }
        NPAL1200CommonLogic.control(this, scrnMsg);
        NPAL1200CommonLogic.setTableScreen(scrnMsg);

        /** QC#12614# 01/31/2017 M.Naito Start **/
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.A.no(i).xxChkBox_D1.getValue())) {
                // set focus
                scrnMsg.setFocusItem(scrnMsg.A.no(i).mdseItemClsTpCd_SE);
                break;
            }
        }
        /** QC#12614# 01/31/2017 M.Naito End **/
    }
}
