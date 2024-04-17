/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.CMN_CLOSE;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_N;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770_NFCL5050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770CMsg bizMsg = null;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        scrnMsg.xxCtlNm_H1.setValue(getLastGuard());

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770_NFCL5050(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0770CommonLogic.transMsgCheck(scrnMsg);

        NFCL0770CommonLogic.initialize(this, scrnMsg);

        if (!CMN_CLOSE.equals(getLastGuard())) {
            // Details position Initialize
            scrnMsg.xxListNum_LX.setValue(0);
            scrnMsg.xxListNum_LY.setValue(0);
        }

        NFCL0770CommonLogic.commonBtnControl_NFCL0770_NFCL5050(this, scrnMsg);

        NFCL0770CommonLogic.setEntryScreen_NFCL0770(this, scrnMsg);

        if (!SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
            setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        }

        NFCL0770CommonLogic.setRowBg(scrnMsg);

        NFCL0770CommonLogic.setCheckAllBtn(this, scrnMsg);

        //NFCL0770CommonLogic.protectAddDetailLine(scrnMsg, this);
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);

        scrnMsg.putErrorScreen();

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        NFCL0770CommonLogic.setAppFracDigit(scrnMsg);
        // add start 2022/09/02 QC#60403
        int lineNum = scrnMsg.xxCellIdx_H1.getValueInt();
        scrnMsg.setFocusItem(scrnMsg.A.no(lineNum).xxDealApplyAmtNum_A1);
        // add end 2022/09/02 QC#60403
    }
}
