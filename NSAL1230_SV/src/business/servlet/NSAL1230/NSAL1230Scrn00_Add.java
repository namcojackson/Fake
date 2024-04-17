/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1230.NSAL1230CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Takeno        Create          N/A
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 *</pre>
 */
public class NSAL1230Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg = new NSAL1230CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg  = (NSAL1230CMsg) cMsg;

        // QC#23378(Sol#320) Add Start
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.svcInvChrgTpCd) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(scrnMsg.svcInvChrgTpCd.getValue())) {
                scrnMsg.A.no(i).prcAllocAmt_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).prcAllocRate_A1.setIndispensable(true);
                scrnMsg.A.no(i).prcAllocAmt_A1.setInputProtected(true);
            }
            scrnMsg.A.no(i).prcAllocAmt_A1.setAppFracDigit(2);
        }
        // QC#23378(Sol#320) Add End
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
