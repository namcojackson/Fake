/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.MODE_CREATE;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.MODE_EDIT;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/08/10   Fujitsu         M.Yamada        Update          QC#11418
 *</pre>
 */
public class NWAL1700Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 2) {
            if (ZYPCommonFunc.hasValue((EZDBStringItem) params[0]) && ZYPCommonFunc.hasValue((EZDBStringItem) params[1])) {
                scrnMsg.xxModeCd.setValue(MODE_EDIT);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, (EZDBStringItem) params[0]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, (EZDBStringItem) params[1]);
            } else {
                clearMsg(scrnMsg);
                scrnMsg.xxModeCd.setValue(MODE_CREATE);
            }
        } else {
            clearMsg(scrnMsg);
            scrnMsg.xxModeCd.setValue(MODE_CREATE);
        }

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

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        if (MODE_CREATE.equals(scrnMsg.xxModeCd.getValue())) {
            NWAL1700CommonLogic.initCmnBtnProp(this, getUserProfileService());
            //            NWAL1700CommonLogic.setInitScreenValue(scrnMsg);
            //            scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd);
            //            int nextIdx = scrnMsg.A.getValidCount();
            //            scrnMsg.A.setValidCount(nextIdx + 1);
            //            scrnMsg.A.no(nextIdx).xxRowId_A.setValue(DB_FLAG_INSERT);
            //            scrnMsg.A.no(nextIdx).effFromDt_A.setValue(ZYPDateUtil.getSalesDate());
            //            scrnMsg.A.no(nextIdx).lineProcDfnSortNum_A.setValue(nextIdx + 1);

            if (NWAL1700CommonLogic.isFullUser(getUserProfileService()) || NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
                NWAL1700CommonLogic.setInitScreenFullandInsert(scrnMsg);
            } else {
                NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
            }
            scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd);
        } else {
            if (NWAL1700CommonLogic.isFullUser(getUserProfileService())) {
                NWAL1700CommonLogic.setEditScreenFullandInsert(scrnMsg, FUNCTION_FULL, this);

            } else if (NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
                NWAL1700CommonLogic.setEditScreenFullandInsert(scrnMsg, FUNCTION_INSERT, this);

            } else {
                NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
            }
        }
    }

    private void clearMsg(NWAL1700BMsg scrnMsg) {
        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);
        ZYPTableUtil.clear(scrnMsg.E);
        ZYPTableUtil.clear(scrnMsg.F);
    }
}
