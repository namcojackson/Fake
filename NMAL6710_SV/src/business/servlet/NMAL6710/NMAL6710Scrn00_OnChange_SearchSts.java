/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.STATUS_CD_ALL;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/05   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NMAL6710Scrn00_OnChange_SearchSts extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        if (STATUS_CD_ALL.equals(scrnMsg.xxAcctSrchStsCd_D2.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_02, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_03, ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxChkBox_02.clear();
            scrnMsg.xxChkBox_03.clear();
        }
    }
}
