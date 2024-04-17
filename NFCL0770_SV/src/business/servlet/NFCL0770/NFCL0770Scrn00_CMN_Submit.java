/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2018/11/30   Fujitsu         S.Takami        Update          QC#28289-3
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770Scrn00_CMN_Submit extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxTpCd) && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox.getValue())) {

                    scrnMsg.A.no(i).xxChkBox.clear();
                }
            }
            NFCL0770CommonLogic.commonInputCheck(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770CMsg bizMsg = null;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_CMN_Submit(scrnMsg);

        scrnMsg.xxModeInd_H1.setValue(SCRN_MODE_SUBMIT.toString());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        EZDDebugOutput.println(1, "doProcess===START_TIME:" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"), this);
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0770CommonLogic.setCheckAllBtn(this, scrnMsg);

        NFCL0770CommonLogic.transMsgCheck(scrnMsg);

        NFCL0770CommonLogic.initialize(this, scrnMsg);
        // START 2018/11/30 S.Takami [QC#28289-3, Add]
        NFCL0770CommonLogic.commonBtnControl_NFCL0770_INIT(this, scrnMsg);
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        // END   2018/11/30 S.Takami [QC#28289-3, Add]

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        // del start 2022/09/02 QC#60403
        //scrnMsg.xxListNum_LY.setValue(0);
        // del end 2022/09/02 QC#60403

        NFCL0770CommonLogic.setRowBg(scrnMsg);

        scrnMsg.putErrorScreen();

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind())) {
                 scrnMsg.putErrorScreen();
                 throw new EZDFieldErrorException();
            }
        }

        NFCL0770CommonLogic.commonBtnControl_NFCL0770Scrn00_CMN_Submit(this, scrnMsg);
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);

        if (NFCL0770CommonLogic.isApiExecuteBatchMsgCd(scrnMsg)) {
            this.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "MailEntry", 0, null);
        }
        EZDDebugOutput.println(1, "doProcess===END_TIME:" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"), this);
    }
}
