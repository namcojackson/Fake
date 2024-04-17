/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/22   SRA             E.Inada         Create          QC#7376
 *</pre>
 */
public class NMAL7010_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        int idx = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_CustAudcVal_01_Acc".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X1);

        } else if ("OpenWin_CustAudcVal_02_Acc".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X2);

        } else if ("OpenWin_CustAudcVal_03_Acc".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X3);
        }
    }
}
