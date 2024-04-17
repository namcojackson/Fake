/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.CHK_A;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.DB_FLAG_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NWAM8442E;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NWAM8443E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_Remove_Line
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1700Scrn00_Remove_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        boolean checkFlag = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
                checkFlag = true;
            }
        }

        if (!checkFlag) {
            scrnMsg.setMessageInfo(NWAM8442E);
            throw new EZDFieldErrorException();
        }

        boolean fullUser = NWAL1700CommonLogic.isFullUser(getUserProfileService());
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_A, FLG_ON_Y);
        for (int idx : selectRows) {
            String recordType = scrnMsg.A.no(idx).xxRowId_A.getValue();
            if (ZYPCommonFunc.hasValue(recordType)) {
                if (DB_FLAG_INSERT.equals(recordType)) {
                    continue;
                }

                if (!fullUser) {
                    scrnMsg.A.no(idx).xxChkBox_A.setErrorInfo(1, NWAM8443E);
                    checkFlag = true;
                }
            }
        }

        NWAL1700CommonLogic.clearMandantoryCheckLineCategory(scrnMsg);
        NWAL1700CommonLogic.addCheckItemLineCategory(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = (NWAL1700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            if (NWAL1700CommonLogic.isFullUser(getUserProfileService())) {
                NWAL1700CommonLogic.setEditScreenFullandInsertForLineCatgAsgn(FUNCTION_FULL, scrnMsg, i);

            } else if (NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
                NWAL1700CommonLogic.setEditScreenFullandInsertForLineCatgAsgn(FUNCTION_INSERT, scrnMsg, i);

            } else {
                NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
            }
        }
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).lineProcDfnSortNum_A);
        }

    }
}
