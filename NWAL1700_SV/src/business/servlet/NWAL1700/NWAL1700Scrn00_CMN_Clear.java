/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1700Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();
//        scrnMsg.clear();
//        ZYPTableUtil.clear(scrnMsg.A);
//        ZYPTableUtil.clear(scrnMsg.B);
//        ZYPTableUtil.clear(scrnMsg.C);
//        ZYPTableUtil.clear(scrnMsg.D);
//        ZYPTableUtil.clear(scrnMsg.E);
//        ZYPTableUtil.clear(scrnMsg.F);
//        scrnMsg.xxModeCd.setValue(MODE_CREATE);
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

        NWAL1700CommonLogic.initCmnBtnProp(this, getUserProfileService());
//        NWAL1700CommonLogic.setInitScreenValue(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd);
//        int nextIdx = scrnMsg.A.getValidCount();
//        scrnMsg.A.setValidCount(nextIdx + 1);
//        scrnMsg.A.no(nextIdx).xxRowId_A.setValue(DB_FLAG_INSERT);
//        scrnMsg.A.no(nextIdx).effFromDt_A.setValue(ZYPDateUtil.getSalesDate());
//        scrnMsg.A.no(nextIdx).lineProcDfnSortNum_A.setValue(nextIdx + 1);

        if (NWAL1700CommonLogic.isFullUser(getUserProfileService()) || NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
            NWAL1700CommonLogic.setInitScreenFullandInsert(scrnMsg);
        } else {
            NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
        }
        scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd);
    }
}
