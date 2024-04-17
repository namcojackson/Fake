/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NFDL0010.NFDL0010CMsg;
// import business.servlet.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#13257
 *</pre>
 */
public class NFDL0010_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

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
                // } else if
                // ("OpenWin_SearchAccountPopup".equals(scrEventNm)) {
                // rtnVal =
                // scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                // if (ZYPCommonFunc.hasValue(rtnVal)) {
                // if
                // (ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H2))
                // {
                // scrnMsg.xxQueryFltrTxt_H2.setValue(scrnMsg.xxQueryFltrTxt_H2.getValue().concat(",").concat(rtnVal));
                // } else {
                // scrnMsg.xxQueryFltrTxt_H2.setValue(rtnVal);
                // }
                // }
                // scrnMsg.setFocusItem(scrnMsg.xxQueryFltrTxt_H2);
                // END 2016/09/12 K.Kojima [QC#13257,DEL]
            }
        }
    }
}
