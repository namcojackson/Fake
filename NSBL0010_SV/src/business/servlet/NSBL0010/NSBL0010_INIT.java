/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0010.NSBL0010CMsg;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2017/01/17   Hitachi         N.Arai          Update          QC#16331
 *</pre>
 */
public class NSBL0010_INIT extends S21CommonHandler implements NSBL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        NSBL0010CMsg bizMsg = new NSBL0010CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);

        Serializable arg = getArgForSubScreen();

        if (arg != null && arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcTaskNum, (EZDBStringItem) params[0]);
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // get Function List
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        NSBL0010CommonLogic.initCommonButton(this, funcList);

        NSBL0010CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

// START 2017/01/17 N.Arai [QC#16331, MOD]
        scrnMsg.setFocusItem(scrnMsg.svcContrBrCd);
//END 2017/01/17 N.Arai [QC#16331, MOD]
    }
}
