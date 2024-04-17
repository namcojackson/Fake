/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8230E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8346E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NZZM0011E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#4345
 *</pre>
 */
public class NMAL6750Scrn00_DeleteAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0011E);
            }
        }

        if (checkList.size() == 1 && scrnMsg.A.getValidCount() == 1) {
            scrnMsg.A.no(0).xxChkBox_A1.setErrorInfo(1, NMAM8346E);
        }

        String slsDt = ZYPDateUtil.getSalesDate();
        NMAL6750_ABMsg aBMsg;
        for (int index : checkList) {
            aBMsg = scrnMsg.A.no(index);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).dsCtacPsnRelnPk_A1) && ZYPDateUtil.compare(slsDt, aBMsg.effFromDt_A1.getValue()) >= 0) {
                scrnMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAM8230E);
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
