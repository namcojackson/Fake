/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

// import static
// business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NYEL8810.NYEL8810CMsg;
// import business.servlet.NYEL8810.constant.NYEL8810Constant;

import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_MoveWin_ProcessStatus
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/21   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_MoveWin_ProcessStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID("NYEL8810");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        int selectedIndex = -1;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxWfAprChkFlg_A);
            
            if (S21StringUtil.isNotEmpty(scrnMsg.A.no(i).xxWfAprChkFlg_A.getValue())){
                if (selectedIndex == -1){
                    selectedIndex = i;
                }
            }
            
        }
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        Object[] params = new Object[3];

        params[0] = scrnMsg.A.no(selectedIndex).wfProcPk_A;
        params[1] = scrnMsg.A.no(selectedIndex).wfProcStsCd_A;
        //params[0] = scrnMsg.A.no(selectedIndex).wfProcNm_A;
        //params[1] = scrnMsg.A.no(selectedIndex).wfBizAttrbTxt_A1;
        //params[2] = scrnMsg.A.no(selectedIndex).wfProcPk_A;

        setArgForSubScreen(params);

    }
}