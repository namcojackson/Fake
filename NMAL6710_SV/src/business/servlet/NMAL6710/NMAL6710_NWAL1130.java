/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6710.NMAL6710CMsg;
//import business.servlet.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/10/03   Fujitsu         Mz.Takahashi    Update          CSA #14826
 *</pre>
 */
public class NMAL6710_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_AcctNum".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_01, scrnMsg.I.no(0).xxComnScrColValTxt);
        } else if ("OpenWin_AcctGrp".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.I.no(0).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctGrpCd_DP, scrnMsg.I.no(0).xxComnScrColValTxt);
                scrnMsg.dsAcctGrpDescTxt_DP.clear();
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.I.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctGrpDescTxt_DP, scrnMsg.I.no(1).xxComnScrColValTxt);
            }
        }
    }
}
