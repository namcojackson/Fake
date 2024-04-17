/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820;

import static business.servlet.NYEL8820.constant.NYEL8820Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.CTX_ATTR_BIZ_PROC_INF;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.CTX_ATTR_SEL_PROC_INF;

import parts.common.EZDAbendException;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8820.NYEL8820CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfo;
import com.canon.cusa.s21.framework.online.process.S21BusinessTaskInfo;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8820Scrn00_MoveWin_BizScreen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8820Scrn00_MoveWin_BizScreen extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        //
        NYEL8820CMsg bizMsg = new NYEL8820CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        
        // #todo fix
        // From S21 Menu
        String processGroupCd = ctx.getAttributeStr("SelectedProcessGroupId");
        //String processGroupCd = "0000000001";
        
        ZYPEZDItemValueSetter.setValue(scrnMsg.menuProcGrpCd, processGroupCd);
// 2018/05/16 MOD START
//        ZYPEZDItemValueSetter.setValue(scrnMsg.bizAppId, scrnMsg.bizAppId.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.wfBizScrId, scrnMsg.bizAppId.getValue());
// 2018/05/16 MOD END

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        NYEL8820CMsg bizMsg  = (NYEL8820CMsg) cMsg;
        //
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Workflow Process
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess proc = null;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            proc = context.getProcess(bizMsg.wfProcPk.getValue());
        } catch (NumberFormatException e) {
            throw new EZDAbendException(e);
        } catch (S21NwfSystemException e) {
            throw new EZDAbendException(e);
        }
        
        String bizAppId = proc.getToken().getTokenObj().getBizScreenId();

        // Set TAB information
        S21BusinessTaskInfo taskInfo = new S21BusinessTaskInfo();
        taskInfo.setBusinessAplID(bizAppId);
        taskInfo.setCanUse(true);

        String upTabName = scrnMsg.upTabNm.getValue();
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
            setJumpTransition(bizAppId.trim());

            // Params
            Object[] params = proc.getToken().getTokenObj().getBizScreenParams();

            setArgForJump(params);
        }
    }
}
