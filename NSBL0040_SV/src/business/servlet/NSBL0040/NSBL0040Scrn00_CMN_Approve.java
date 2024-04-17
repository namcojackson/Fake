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

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
public class NSBL0040Scrn00_CMN_Approve extends S21CommonHandler implements NSBL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        List<Integer> selectedIndex = ZYPTableUtil.getSelectedRows(scrnMsg.A, NSBL0040Bean.xxChkBox_A1, ZYPConstant.FLG_ON_Y);
        if (selectedIndex == null || selectedIndex.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NSBM0015E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
            scrnMsg.setMessageInfo(NSBM0015E);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        NSBL0040CMsg bizMsg = new NSBL0040CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(UPDATE_FUNCTION);
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

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }

        NSBL0040CommonLogic.alternateTableRowColors(scrnMsg);

        scrnMsg.putErrorScreen();

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        NSBL0040CommonLogic.activateButtons(this, functionList);
        NSBL0040CommonLogic.activateScreenItems(scrnMsg, functionList);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

}
