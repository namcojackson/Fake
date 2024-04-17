/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/23   Fujitsu         H.Ikeda         Create          QC#54902
 * 2020/02/26   Fujitsu         H.Ikeda         Update          QC#55910
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 * 2022/08/23   Hitachi         A.Kohinata      Update          QC#60314
 *</pre>
 */
public class NFCL2760Scrn00_OnBlur_Inv extends S21CommonHandler implements NFCL2760Constant{

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        // START 2022/01/06 G.Delgado [QC#59329, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, ADD]

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        NFCL2760CMsg bizMsg = null;
        int lineNum = getButtonSelectNumber();
        scrnMsg.xxCellIdx_H1.setValue(lineNum);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(lineNum).arCustRefNum)) {
            bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_OnBlur_Inv();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        int lineNum = scrnMsg.xxCellIdx_H1.getValueInt();

        if (cMsg != null) {
            NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NFCL2760CommonLogic.initialize(this, scrnMsg);
            NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_SearchTrxLine(this);
            NFCL2760CommonLogic.setRowBg(scrnMsg);
            NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
            NFCL2760CommonLogic.transMsgCheck(scrnMsg);

            scrnMsg.putErrorScreen();
        } 

        // 2020/02/26 QC#55910 mod start
        // 2022/08/23 QC#60314 mod start
        //if (lineNum != MAX_LINE_NUM) {
        if (lineNum != scrnMsg.A.length() - 1) {
        // 2022/08/23 QC#60314 mod end
            scrnMsg.setFocusItem(scrnMsg.A.no(lineNum + 1).arCustRefNum);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(lineNum).xxDealApplyAmtNum_A1);
        }
        // 2020/02/26 QC#55910 mod end
    }
}
