/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#1029
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2017/06/22   Hitachi         Y.Osawa         Update          QC#17496
 *</pre>
 */
public class NSAL0300_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        // START 2016/01/07 T.Tomita [QC#1029, MOD]
//        if ("OpenWin_Customer".equals(scrEventNm)) {
        if (!"CMN_Close".equals(getLastGuard())) {
            // START 2016/04/26 T.Tomita [QC#3886, MOD]
            int idx = getButtonSelectNumber();
            // START 2017/06/22 [QC#17496, MOD]
            // if ("OpenWin_Customer".equals(scrEventNm)) {
            if ("OpenWin_Customer".equals(scrEventNm) || "OpenWin_BillToLoc".equals(scrEventNm)) {
                setValue(scrnMsg.dsAcctNum, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_01);
            // } else if ("OpenWin_BillToLoc".equals(scrEventNm)) {
                // setValue(scrnMsg.altPayerCustCd, scrnMsg.xxPopPrm_15);
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_15)) {
                    setValue(scrnMsg.altPayerCustCd, scrnMsg.xxPopPrm_15);
                }
            // END   2017/06/22 [QC#17496, MOD]
            } else if ("OpenWin_LeaseCompany".equals(scrEventNm)) {
                setValue(scrnMsg.leaseCmpyCd, scrnMsg.xxPopPrm_15);
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            } else if ("OpenWin_ShipTo".equals(scrEventNm)) {
                setValue(scrnMsg.B.no(idx).shipToCustCd_B, scrnMsg.xxPopPrm_16);
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            } else if ("OpenWin_BillTo_Base".equals(scrEventNm)) {
                setValue(scrnMsg.B.no(idx).baseBillToCustCd_B, scrnMsg.xxPopPrm_15);
            } else if ("OpenWin_BillTo_Meter".equals(scrEventNm)) {
                setValue(scrnMsg.B.no(idx).bllgMtrBillToCustCd_B, scrnMsg.xxPopPrm_15);
            }
            // END 2016/04/26 T.Tomita [QC#3886, MOD]
            NSAL0300CMsg bizMsg = new NSAL0300CMsg();
            bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
            bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
        // END 2016/01/07 T.Tomita [QC#1029, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2016/01/07 T.Tomita [QC#1029, MOD]
//        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
//        if ("OpenWin_Customer".equals(scrEventNm)) {
        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
            NSAL0300CommonLogic.addCheckItem(scrnMsg);

            scrnMsg.putErrorScreen();
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.xxScrEventNm.clear();
        // END 2016/01/07 T.Tomita [QC#1029, MOD]
    }
}
