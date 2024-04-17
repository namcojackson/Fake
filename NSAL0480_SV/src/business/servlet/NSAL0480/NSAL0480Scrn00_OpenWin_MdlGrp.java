/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.*;
import static business.servlet.NSAL0480.constant.NSAL0480Constant.NMAL6050_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NSAL0480Scrn00_OpenWin_MdlGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, TBL_NM.DS_MDL_GRP.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, COL_NM.MDL_GRP_NM.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, COL_NM.MDL_GRP_DESC_TXT.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, COL_NM.MDL_GRP_NM.toString());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, LBL.MODEL_GROUP_POPUP.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, LBL.MODEL_GROUP_NAME.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, LBL.MODEL_GROUP_DESCRIPTION.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, LBL.MODEL_GROUP_NAME.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, LBL.MODEL_GROUP_DESCRIPTION.getLbl());

        scrnMsg.xxCondCd_P.clear();
        scrnMsg.xxCondNm_P.clear();

        Object[] params = new Object[NMAL6050_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.xxTblNm_P;
        params[i++] = scrnMsg.xxTblCdColNm_P;
        params[i++] = scrnMsg.xxTblNmColNm_P;
        params[i++] = scrnMsg.xxTblSortColNm_P;
        params[i++] = scrnMsg.xxScrNm_P;
        params[i++] = scrnMsg.xxHdrCdLbNm_P;
        params[i++] = scrnMsg.xxHdrNmLbNm_P;
        params[i++] = scrnMsg.xxDtlCdLbNm_P;
        params[i++] = scrnMsg.xxDtlNmLbNm_P;
        params[i++] = scrnMsg.mdlGrpNm_H;
        params[i++] = scrnMsg.xxCondNm_P;

        setArgForSubScreen(params);

    }
}
