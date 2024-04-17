/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/03/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0110.ZZOL0110CMsg;
import business.servlet.ZZOL0110.common.ZZOL0110CommonLogic;
import business.servlet.ZZOL0110.constant.ZZOL0110Constant;
import com.canon.cusa.s21.common.ZZX.ZZXC001001.ZZXC001002;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;

public class ZZOL0110_INIT extends S21CommonHandlerEx implements ZZOL0110Constant {
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZOL0110BMsg scrnMsg = (ZZOL0110BMsg) bMsg;
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0110BMsg scrnMsg = (ZZOL0110BMsg) bMsg;

        String strSysFlg = ZZXC001002.SYS_FLG;
        // PARTS
        if (ZZXC001002.SYS_FLG_PARTS.contains(strSysFlg)) {
            scrnMsg.glblCmpyCd_X1.setValue(ZZXC001002.PARTS_GLBL_CMPY_CD);
            scrnMsg.glblCmpyCd_BK.setValue(ZZXC001002.PARTS_GLBL_CMPY_CD);
        // S21
        } else if (ZZXC001002.SYS_FLG_S21.contains(strSysFlg)) {
            scrnMsg.glblCmpyCd_X1.setValue(getContextUserInfo().getUserCompanyCode());
            scrnMsg.glblCmpyCd_BK.setValue(getContextUserInfo().getUserCompanyCode());
        }

        scrnMsg.sysMenuNm_X1.setValue(HEADER_TITLE);
        
        ZZOL0110CMsg bizMsg = new ZZOL0110CMsg();
        bizMsg.setBusinessID("ZZOL0110");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0110BMsg scrnMsg = (ZZOL0110BMsg) bMsg;
        ZZOL0110CMsg bizMsg = (ZZOL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZOL0110CommonLogic.dspScrn00(scrnMsg, this);

    }

}
