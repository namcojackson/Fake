/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0040.NSBL0040CMsg;
import business.servlet.NSBL0040.common.NSBL0040CommonLogic;
import business.servlet.NSBL0040.constant.NSBL0040Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2013/09/10   SRA             Yanada          Update          QC2131
 *</pre>
 */
public class NSBL0040Scrn00_Search extends S21CommonHandler implements NSBL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum);
        scrnMsg.addCheckItem(scrnMsg.techCd);
        scrnMsg.addCheckItem(scrnMsg.ezInUserID);
        scrnMsg.addCheckItem(scrnMsg.svcBillTpCd_01);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        NSBL0040CMsg bizMsg = new NSBL0040CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(SEARCH_FUNCTION);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        NSBL0040CMsg bizMsg = (NSBL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum);
        scrnMsg.addCheckItem(scrnMsg.techCd);
        scrnMsg.addCheckItem(scrnMsg.ezInUserID);
        scrnMsg.addCheckItem(scrnMsg.svcBillTpCd_01);

        scrnMsg.putErrorScreen();

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        NSBL0040CommonLogic.activateButtons(this, functionList);
        NSBL0040CommonLogic.activateScreenItems(scrnMsg, functionList);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSBL0040CommonLogic.alternateTableRowColors(scrnMsg);
    }

}
