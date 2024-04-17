/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0400;

import java.util.List;
import static business.servlet.NSBL0400.constant.NSBL0400Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0400.NSBL0400CMsg;
import business.servlet.NSBL0400.common.NSBL0400CommonLogic;
import business.servlet.NSBL0400.constant.NSBL0400Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 * 2016/09/23   Hitachi         J.Sumi          Update          QC#12582
 * 2018/05/24   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public class NSBL0400_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0400BMsg scrnMsg = (NSBL0400BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NSBL0400CMsg bizMsg = new NSBL0400CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0400BMsg scrnMsg = (NSBL0400BMsg) bMsg;
        NSBL0400CMsg bizMsg  = (NSBL0400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSBL0400Constant.BIZ_ID);

        NSBL0400CommonLogic.activateButtons(this, functionList);
        scrnMsg.setFocusItem(scrnMsg.svcModYr);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0400BMsg scrnMsg = (NSBL0400BMsg) bMsg;
        scrnMsg.svcModYr.setNameForMessage("YYYY");
        scrnMsg.svcModMth.setNameForMessage("MM");
        scrnMsg.svcModDay.setNameForMessage("DD");
        scrnMsg.svcMnfCd.setNameForMessage("MU");
        scrnMsg.svcModSqNum.setNameForMessage("Seq#");
        scrnMsg.svcModNm.setNameForMessage("Mod Plan Desc(*)");
        // START 2016/09/23 J.Sumi [QC#12582, MOD]
        scrnMsg.mdseCd.setNameForMessage("Item Code(*)");
        // END 2016/09/23 J.Sumi [QC#12582, MOD]
        scrnMsg.svcMnfModNum.setNameForMessage("Manufacture Mod#(*)");
        // START 2018/05/24 U.Kim [QC#22393, ADD]
        scrnMsg.serNum.setNameForMessage("Serial#");
        // END 2018/05/24 U.Kim [QC#22393, ADD]
    }
}
