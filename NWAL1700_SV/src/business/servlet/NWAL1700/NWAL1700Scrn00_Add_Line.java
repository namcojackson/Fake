/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NWAM8441E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_Add_Line
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/08/10   Fujitsu         M.Yamada        Update          QC#11418
 *</pre>
 */
public class NWAL1700Scrn00_Add_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CommonLogic.clearMandantoryCheckLineCategory(scrnMsg);
        NWAL1700CommonLogic.addCheckItemLineCategory(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NWAM8441E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
//        int nextIdx = scrnMsg.A.getValidCount();
//        scrnMsg.A.setValidCount(nextIdx + 1);
//        scrnMsg.A.no(nextIdx).xxRowId_A.setValue(DB_FLAG_INSERT);
//        final String slsDt = ZYPDateUtil.getSalesDate();
//        scrnMsg.A.no(nextIdx).effFromDt_A.setValue(slsDt);
//
//        int max = 0;
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).lineProcDfnSortNum_A.getValue())) {
//                int sortNum = scrnMsg.A.no(i).lineProcDfnSortNum_A.getValueInt();
//                if (max < sortNum) {
//                    max = sortNum;
//                }
//            }
//        }
//        scrnMsg.A.no(nextIdx).lineProcDfnSortNum_A.setValue(max + 1);
//        return null;

        NWAL1700CMsg bizMsg = new NWAL1700CMsg();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = (NWAL1700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (NWAL1700CommonLogic.isFullUser(getUserProfileService())) {
            NWAL1700CommonLogic.setEditScreenFullandInsertForLineCatgAsgn(FUNCTION_FULL, scrnMsg, scrnMsg.A.getValidCount() - 1);

        } else if (NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
            NWAL1700CommonLogic.setEditScreenFullandInsertForLineCatgAsgn(FUNCTION_INSERT, scrnMsg, scrnMsg.A.getValidCount() - 1);

        } else {
            NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
        }
        NWAL1700CommonLogic.setFocusRule(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).lineProcDfnSortNum_A);
    }
}
