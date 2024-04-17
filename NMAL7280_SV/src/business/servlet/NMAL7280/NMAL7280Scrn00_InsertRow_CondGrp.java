/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280;

import static business.servlet.NMAL7280.constant.NMAL7280Constant.COND_GRP_MAX_LENGTH;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.FIRST_SET_VALUE;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM0050E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7280.common.NMAL7280CommonLogic;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7280Scrn00_InsertRow_CondGrp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 * 2016/07/11   Fujitsu         W.Honda         Update          QC#8477
 *</pre>
 */
public class NMAL7280Scrn00_InsertRow_CondGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
            String nextPrcRuleCondGrpCd = NMXC105001PriceMasterUtil.getNextPrcRuleCondGrpCd(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).prcRuleCondGrpCd_A.getValue());

            if (!ZYPCommonFunc.hasValue(nextPrcRuleCondGrpCd)
                // QC#8477 2016/07/11 Add start
                    || nextPrcRuleCondGrpCd.length() > COND_GRP_MAX_LENGTH) {
                // QC#8477 2016/07/11 Add end
                scrnMsg.setMessageInfo(NMAM0050E, new String[] {scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).prcRuleCondGrpCd_A.getValue() });
                throw new EZDFieldErrorException();
            }
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;


        int idx = scrnMsg.A.getValidCount();
        final String slsDt = ZYPDateUtil.getSalesDate();
        scrnMsg.A.no(idx).effFromDt_A.setValue(slsDt);

        String tsNum = "";
        if (idx == 0) {
            tsNum = FIRST_SET_VALUE;
        } else {
            tsNum = scrnMsg.A.no(idx - 1).prcRuleCondGrpCd_A.getValue();
        }
        scrnMsg.A.no(idx).prcRuleCondGrpCd_A.setValue(NMXC105001PriceMasterUtil.getNextPrcRuleCondGrpCd(tsNum));
        scrnMsg.A.setValidCount(idx + 1);
        NMAL7280CommonLogic.setScrnCtrl(this, scrnMsg, getUserProfileService());


    }
}
