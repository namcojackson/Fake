/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_MoveWin_Detail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/21   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_MoveWin_Detail extends S21CommonHandler {

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
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxWfAprChkFlg_A);
            
            if (S21StringUtil.isNotEmpty(scrnMsg.A.no(i).xxWfAprChkFlg_A.getValue())){
                list.add(i);
            }
        }
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        int cnt = 0;

        for(Integer index: list){
            scrnMsg.P.no(cnt).wfProcPk_P.setValue(scrnMsg.A.no(index).wfProcPk_A.getValue());
            scrnMsg.P.no(cnt).wfWrkItemPk_P.setValue(scrnMsg.A.no(index).wfWrkItemPk_A.getValue());
            scrnMsg.P.no(cnt).wfProcStsCd_P.setValue(scrnMsg.A.no(index).wfProcStsCd_A.getValue());
            cnt++;
        }
        scrnMsg.P.setValidCount(list.size());

        setArgForSubScreen(scrnMsg.P);

    }
}
