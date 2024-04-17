/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0490.common.NSAL0490CommonLogic;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/03/14   Hitachi         K.Watanabe      Create          QC#63542
 *</pre>
 */
public class NSAL0490Scrn00_OpenWin_CopyModelMdlNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(NSAL0490Constant.ITEM_NM_COPY_MDL_NM);

        // set param
        scrnMsg.xxTblNm.setValue("MDL_NM_V");
        scrnMsg.xxTblCdColNm.setValue("T_MDL_ID");
        scrnMsg.xxTblNmColNm.setValue("T_MDL_NM");
        scrnMsg.xxTblSortColNm.setValue("T_MDL_ID");
        scrnMsg.xxScrNm.setValue("Model Name Popup");
        scrnMsg.xxHdrCdLbNm.setValue("Model ID");
        scrnMsg.xxHdrNmLbNm.setValue("Model Name");
        scrnMsg.xxDtlCdLbNm.setValue("Model ID");
        scrnMsg.xxDtlNmLbNm.setValue("Model Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.mdlNm_CF)) {
            scrnMsg.xxCondNm.setValue(scrnMsg.mdlNm_CF.getValue());
        } else {
            scrnMsg.xxCondNm.clear();
        }
        scrnMsg.xxCondCd.clear();
        setArgForSubScreen(NSAL0490CommonLogic.getParamNMAL6050(scrnMsg));
    }
}
