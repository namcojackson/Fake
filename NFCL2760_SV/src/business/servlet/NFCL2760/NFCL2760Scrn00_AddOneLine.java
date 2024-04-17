/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/11/16   Fujitsu         M.Ohno          Update          QC#21402
 * 2019/09/02   Fujitsu         H.Ikeda         Update          QC#53138
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 *</pre>
 */
public class NFCL2760Scrn00_AddOneLine extends S21CommonHandler implements NFCL2760Constant {

	@Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        // START 2022/01/06 G.Delgado [QC#59329, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, ADD]

        NFCL2760CommonLogic.chkSubmit(scrnMsg, "NFCL2760Scrn00_AddOneLine");
        scrnMsg.putErrorScreen();

        //if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
        //    if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
        //        NFCL2760CommonLogic.commonInputCheck(scrnMsg);
        //    } else {
        //        // do nothing
        //    }
        //} else {
            // do nothing
        //}
        
        if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
            //if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
                NFCL2760CommonLogic.commonInputCheck(scrnMsg, true);
            //}
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        NFCL2760CMsg bizMsg = null;

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_AddOneLine();
        // 2017/11/15 QC#21402 add start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_H1, BigDecimal.valueOf(scrnMsg.A.getValidCount()-1));
        // 2017/11/15 QC#21402 add end
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum_H1.setValue(scrnMsg.xxPageShowFromNum_H1.getValueInt());
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2760CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);

        NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_AddOneLine(this);

        NFCL2760CommonLogic.setRowBg(scrnMsg);

        //NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);

        NFCL2760CommonLogic.transMsgCheck(scrnMsg);
        // START 2019/09/03 H.Ikeda [QC#53138, DEL]
        //scrnMsg.putErrorScreen();
        // END   2019/09/03 H.Ikeda [QC#53138, DEL]
        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        NFCL2760CommonLogic.setAppFracDigit(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).arCustRefNum);
        // START 2019/09/03 H.Ikeda [QC#53138, ADD]
        scrnMsg.putErrorScreen();
        // END   2016/09/03 H.Ikeda [QC#53138, ADD]
    }
}
