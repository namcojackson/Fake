/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0310;

import static business.servlet.NSBL0310.constant.NSBL0310Constant.BUSINESS_ID;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.FUNC_CD_20;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0310.NSBL0310CMsg;
import business.servlet.NSBL0310.common.NSBL0310CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 *</pre>
 */
public class NSBL0310_INIT extends  S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        NSBL0310CMsg bizMsg = new NSBL0310CMsg();

        setValue(scrnMsg.svcMgrTpCd_H, "01");

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        NSBL0310CMsg bizMsg  = (NSBL0310CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0310CommonLogic.initCommonButton(this);
        NSBL0310CommonLogic.setProtected(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.orgCd_HT);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        scrnMsg.orgCd_HT.setNameForMessage("Service Group");
        scrnMsg.orgDescTxt_H.setNameForMessage("Service Group Name");
    }
}
