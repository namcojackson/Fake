/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.SCR_NM_ACCOUNT_TYPE_CH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CITS            N.Akaishi       Create          n/a
 * 2016/08/31   CITS            R.Shimamoto     Update          QC#13985
 * 2016/12/12   CITS            R.Shimamoto     Update          QC#15817
 *</pre>
 */
public class NPAL1500_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        int eventRowIndex = scrnMsg.xxNum.getValueInt();
        String accountName = scrnMsg.xxTblNm_P1.getValue();

        NPAL1500CommonLogic.setAccountToPopUpNMAL2550(scrnMsg);
        if (accountName.equals(SCR_NM_ACCOUNT_TYPE_CH)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).xxScrItem130Txt_CH);
        }
        // QC#16341 Mod.
//        else if (accountName.equals(SCR_NM_ACCOUNT_TYPE_AC)) {
//            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).xxScrItem130Txt_AC);
//        } else if (accountName.equals(SCR_NM_ACCOUNT_TYPE_VA)) {
//            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).xxScrItem130Txt_VA);
//        }

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // QC#13985 2016/09/09 Add Start
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // QC#13985 2016/09/09 Add End
    }
}
