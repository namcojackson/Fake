/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#13257
 *</pre>
 */
public class NFDL0060_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        String rtnVal = null;
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_SearchCollectorPopup".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(rtnVal)) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H1)) {
                        scrnMsg.xxQueryFltrTxt_H1.setValue(scrnMsg.xxQueryFltrTxt_H1.getValue().concat(",").concat(rtnVal));
                    } else {
                        scrnMsg.xxQueryFltrTxt_H1.setValue(rtnVal);
                    }
                }
                scrnMsg.setFocusItem(scrnMsg.xxQueryFltrTxt_H1);
                // START 2016/09/12 K.Kojima [QC#13257,DEL]
                // // START 2016/07/28 K.Kojima [QC#10260,ADD]
                // } else if
                // ("OpenWin_SearchAccountPopup".equals(scrEventNm)) {
                // rtnVal =
                // scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                // if (ZYPCommonFunc.hasValue(rtnVal)) {
                // if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                // scrnMsg.dsAcctNum_H1.setValue(scrnMsg.dsAcctNum_H1.getValue().concat(",").concat(rtnVal));
                // } else {
                // scrnMsg.dsAcctNum_H1.setValue(rtnVal);
                // }
                // }
                // scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
                // // END 2016/07/28 K.Kojima [QC#10260,ADD]
                // END 2016/09/12 K.Kojima [QC#13257,DEL]
            }
        }
    }
}
