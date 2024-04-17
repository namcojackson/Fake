/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.CHKBOX_A;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM8346E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NZZM0011E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 *</pre>
 */
public class NMAL6720Scrn00_DeleteAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);
        int size = checkList.size();
        if (size <= 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0011E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
        }

        if (size >= scrnMsg.A.getValidCount()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM8346E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6720CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        NMAL6720CommonLogic.setBgColor(scrnMsg);
    }
}
