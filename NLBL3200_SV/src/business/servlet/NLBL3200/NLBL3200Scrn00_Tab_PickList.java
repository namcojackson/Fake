/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 *</pre>
 */
public class NLBL3200Scrn00_Tab_PickList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        NLBL3200CommonLogic.addCheckItemApply(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        // Table Data Clear
        ZYPTableUtil.clear(scrnMsg.B);
        scrnMsg.xxPageShowFromNum_B.clear();
        scrnMsg.xxPageShowToNum_B.clear();
        scrnMsg.xxPageShowOfNum_B.clear();

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(NLBL3200Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            // Add Check Item
            NLBL3200CommonLogic.addCheckTableItem(scrnMsg);
            scrnMsg.putErrorScreen();
        } else {
            // Set Tab Pick List
            scrnMsg.xxDplyTab.setValue(NLBL3200Constant.TAB_PICK_LIST);

            // clear image file of sort columns (Gif file. ASC or DESC.)
            S21SortColumnIMGController.clearIMG(NLBL3200Constant.SCRN_ID, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        }

        // screen ctrl
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3200Constant.BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);
    }
}
