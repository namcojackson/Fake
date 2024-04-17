/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.CTX_ATTR_BIZ_PROC_INF;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.CTX_ATTR_SEL_PROC_INF;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenSerializer;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfo;
import com.canon.cusa.s21.framework.online.process.S21BusinessTaskInfo;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_MoveWin_BizScreen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/26   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_MoveWin_BizScreen extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        /*
        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        // #todo fix
        // From S21 Menu
        String processGroupCd = ctx.getAttributeStr("SelectedProcessGroupId");
        // String processGroupCd = "0000000001";

        ZYPEZDItemValueSetter.setValue(scrnMsg.menuProcGrpCd, processGroupCd);
        int selectedIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).bizAppId_P, scrnMsg.A.no(selectedIndex).bizAppId_A.getValue());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        */
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        /*
        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // String bizAppId = scrnMsg.P.no(0).bizAppId_P.getValue();

        // Argument
        int selectedIndex = getButtonSelectNumber();

        S21NwfTokenSerializer ser = new S21NwfTokenSerializer();
        S21NwfTokenObj tokenBiz = null;
        try {
            tokenBiz = ser.findByKey(bizMsg.A.no(selectedIndex).wfTokenBizPk_A.getValue());
        } catch (S21NwfSystemException e) {
            e.printStackTrace();
        }

        Object[] params = null;
        String bizAppId = null;

        if (tokenBiz != null) {
            bizAppId = tokenBiz.getBizScreenId();
            params = tokenBiz.getBizScreenParams();
        }

        // Set TAB information
        S21BusinessTaskInfo taskInfo = new S21BusinessTaskInfo();
        taskInfo.setBusinessAplID(bizAppId);
        taskInfo.setCanUse(true);

        String upTabName = scrnMsg.P.no(0).upTabNm_P.getValue();
        taskInfo.setName(upTabName);
        taskInfo.setAbbreviation(upTabName);

        S21BusinessProcessInfo businessProcessInfo = new S21BusinessProcessInfo();
        businessProcessInfo.add(taskInfo);
        ctx.setAttribute(CTX_ATTR_BIZ_PROC_INF, businessProcessInfo);

        String menuProcId = scrnMsg.menuProcId.getValue();
        S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();
        s21Info.setBusinessId(bizAppId);
        s21Info.setProcessId(menuProcId);
        ctx.setAttribute(CTX_ATTR_SEL_PROC_INF, s21Info);

        // Statement below sets workFlow request values in to the
        // session
        if (S21StringUtil.isNotEmpty(bizAppId)) {
            // Set transition over EZD Application ID
            setJumpTransition(bizAppId);
            setArgForJump(params);
        }
        */
    }
}
