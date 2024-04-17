/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.BUSINESS_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0010.NSAL0010CMsg;
//import business.servlet.NSAL0010.constant.NSAL0010Constant;

import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/13   Hitachi         T.Tomita        Update          QC#647
 * 2016/04/28   Hitachi         T.Tomita        Update          QC#5398
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 *</pre>
 */
public class NSAL0010_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2016/04/28 CSA Defect#5398
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        if (!"CMN_Close".equals(getLastGuard())) {
            setValue(scrnMsg.mdseCd_H1, scrnMsg.xxPopPrm_00);
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         // setValue(scrnMsg.mdseNm_H1, scrnMsg.xxPopPrm_01);
            setValue(scrnMsg.mdseDescShortTxt_H1, scrnMsg.xxPopPrm_01);
         // END 2016/09/21 N.Arai [QC#11616, MOD]

            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        return null;
        // mod end 2016/04/28 CSA Defect#5398
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // mod start 2016/04/28 CSA Defect#5398
        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
            NSAL0010CMsg bizMsg  = (NSAL0010CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NSAL0010CommonLogic.addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();

            // START 2018/08/23 K.Kitachi [QC#27773, ADD]
            NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true, getUserProfileService());
            // END 2018/08/23 K.Kitachi [QC#27773, ADD]
        }
        // mod end 2016/04/28 CSA Defect#5398
    }
}
