/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.NFCL2760BMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2020/01/23   Fujitsu         H.Ikeda         Update          QC#54902
 *</pre>
 */
public class NFCL2760_NFCL5050 extends S21CommonHandler implements NFCL2760Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = null;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        scrnMsg.xxCtlNm_H1.setValue(getLastGuard());

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760_NFCL5050(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2760CommonLogic.transMsgCheck(scrnMsg);

        NFCL2760CommonLogic.initialize(this, scrnMsg);

        if (CMN_CLOSE.equals(getLastGuard())) {
            // nothing
        } else {
            // Details position Initialize
            scrnMsg.xxListNum_LX.setValue(0);
            scrnMsg.xxListNum_LY.setValue(0);
        }

        NFCL2760CommonLogic.commonBtnControl_NFCL2760_NFCL5050(this, scrnMsg);

        NFCL2760CommonLogic.setEntryScreen_NFCL2760(this, scrnMsg);

        if (!SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
            setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        } else {
            // do nothing
        }

        NFCL2760CommonLogic.setRowBg(scrnMsg);

        NFCL2760CommonLogic.setCheckAllBtn(this, scrnMsg);

        //NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);
        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);

        scrnMsg.putErrorScreen();

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }
        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        NFCL2760CommonLogic.setAppFracDigit(scrnMsg);

        // 2020/01/23 QC#54902 ADD START
        int lineNum = scrnMsg.xxCellIdx_H1.getValueInt();
        scrnMsg.setFocusItem(scrnMsg.A.no(lineNum).xxDealApplyAmtNum_A1);
        // 2018/01/23 QC#54902 ADD END
    }
}