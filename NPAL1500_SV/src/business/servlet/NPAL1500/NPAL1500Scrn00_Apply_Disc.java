/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1500.NPAL1500CMsg;
//import business.servlet.NPAL1500.constant.NPAL1500Constant;

import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2019/10/15   CITS            R.Shimamoto     Update          QC#53392
 *</pre>
 */
public class NPAL1500Scrn00_Apply_Disc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.
    	// 2019/10/15 QC#53392 Add Start
    	NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
        	NPAL1500CommonLogic.addCheckDetailItem(scrnMsg);
        }
        scrnMsg.putErrorScreen();
        // 2019/10/15 QC#53392 Add Start

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            NPAL1500CommonLogic.moveErrorTab(scrnMsg);
            NPAL1500CommonLogic.addCheckItem(scrnMsg);
        }
        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        scrnMsg.putErrorScreen();


    }
}
