/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.initialCheckBox;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.initialControlScreen;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0450.NSBL0450CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = new NSBL0450CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = (NSBL0450CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        // add start 2017/03/01 CSA Defect#17608
        initialCheckBox(this, scrnMsg);
        // add end 2017/03/01 CSA Defect#17608
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        scrnMsg.svcSupplTaskNum_S.setNameForMessage(NAME_SUPP_NUM);
        scrnMsg.techPsnNm_S.setNameForMessage(NAME_TECH);
        scrnMsg.svcSupplTaskTpCd_SS.setNameForMessage(NAME_TASK_NM);
        scrnMsg.mgrNm_S.setNameForMessage(NAME_MANAGER);
        scrnMsg.cratDt_S.setNameForMessage(NAME_CREATE);
        scrnMsg.svcSupplFromDt_D.setNameForMessage(NAME_START);
        scrnMsg.xxDtTm_D1.setNameForMessage(NAME_START);
        scrnMsg.svcSupplToDt_D.setNameForMessage(NAME_END);
        scrnMsg.xxDtTm_D2.setNameForMessage(NAME_END);
        scrnMsg.xxDtTm_D3.setNameForMessage(NAME_TRAVEL);
        scrnMsg.xxDtTm_D5.setNameForMessage(NAME_DURATION);
    }
}
