/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0500;

import static business.servlet.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0500.NSAL0500CMsg;
import business.servlet.NSAL0500.common.NSAL0500CommonLogic;
import business.servlet.NSAL0500.constant.NSAL0500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/01/13   Hitachi         T.Tsuchida      Update          QC#2820
 * 2017/02/17   Hitachi         K.Kitachi       Update          QC#17564
 *</pre>
 */
public class NSAL0500_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CommonLogic.addCheckAllItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // NSAL0500CMsg bizMsg = NSAL0500CommonLogic.createCMsgForSearch();
        // 
        // if ("CMN_Close".equals(getLastGuard())) {
        //     return null;
        // }
        // 
        // String vndCd = scrnMsg.D.no(0).xxComnScrColValTxt.getValue();
        // String locNm = scrnMsg.D.no(NSAL0500Constant.INDEX_THREE).xxComnScrColValTxt.getValue();
        // 
        // if (vndCd.isEmpty()) {
        //     return null;
        // }
        // 
        // setValue(scrnMsg.vndCd, vndCd);
        // setValue(scrnMsg.prntVndNm, locNm);
        // 
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        // return bizMsg;
        // END 2016/12/14 K.Kojima [QC#15653,DEL]
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        if (!"CMN_Close".equals(getLastGuard())) {
            if (OPENWIN_VENDOR.equals(scrnMsg.xxScrEventNm.getValue())) {
                String vndCd = scrnMsg.D.no(0).xxComnScrColValTxt.getValue();
                String locNm = scrnMsg.D.no(NSAL0500Constant.INDEX_THREE).xxComnScrColValTxt.getValue();
                if (vndCd.isEmpty()) {
                    return null;
                }
                NSAL0500CMsg bizMsg = NSAL0500CommonLogic.createCMsgForSearch();
                setValue(scrnMsg.vndCd, vndCd);
                setValue(scrnMsg.prntVndNm, locNm);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            } else if (OPENWIN_TECH.equals(scrnMsg.xxScrEventNm.getValue())) {
                return null;
            }
        }
        return null;
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CMsg bizMsg = (NSAL0500CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        NSAL0500CommonLogic.screenControlProcess(this, scrnMsg);
        // START 2016/12/14 K.Kojima [QC#15653,MOD]
        // scrnMsg.setFocusItem(scrnMsg.vndCd);
        if (OPENWIN_VENDOR.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.vndCd);
        // START 2017/02/17 K.Kitachi [QC#17564, ADD]
        } else if (OPENWIN_TECH.equals(scrnMsg.xxScrEventNm.getValue()) && !"CMN_Close".equals(getLastGuard())) {
        // END 2017/02/17 K.Kitachi [QC#17564, ADD]
            setValue(scrnMsg.techTocCd, scrnMsg.D.no(0).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.techTocCd);
        }
        // END 2016/12/14 K.Kojima [QC#15653,MOD]
    }
}
