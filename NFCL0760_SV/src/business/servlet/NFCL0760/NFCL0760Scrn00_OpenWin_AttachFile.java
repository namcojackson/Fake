/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL0760.constant.NFCL0760Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/11   Hitachi         Y.Takeno        Create          QC#24884
 *</pre>
 */
public class NFCL0760Scrn00_OpenWin_AttachFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0760BMsg scrnMsg = (NFCL0760BMsg) bMsg;

        boolean authEdit = getUserProfileService().isFunctionGranted(
                getUserProfileService().getContextUserInfo().getUserId(), NFCL0760Constant.FUNC_ID_NFCL0760T020);

        int i = 0;
        Object[] params = new Object[9];
        if (authEdit && AR_DS_WF_STS.PENDING.equals(scrnMsg.arDsWfStsCd_H.getValue())) {
            params[i++] = NFCL0760Constant.ZYPL0310_PARAM_DISPLAY_MODE_MODIFICATION;
        } else {
            params[i++] = NFCL0760Constant.ZYPL0310_PARAM_DISPLAY_MODE_READ_ONLY;
        }
        params[i++] = NFCL0760Constant.ZYPL0310_PARAM_BIZ_ID;
        params[i++] = scrnMsg.arWrtOffRqstPk_H.getValue().toPlainString();
        params[i++] = NFCL0760Constant.ZYPL0310_PARAM_FUNC_NM;
        params[i++] = NFCL0760Constant.ZYPL0310_PARAM_PRIMARY_KEY_NM;
        params[i++] = NFCL0760Constant.ZYPL0310_PARAM_DOC_TYPE_TABLE_NM;
        params[i++] = NFCL0760Constant.VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_LIMIT_NUM;
        params[i++] = "";
        params[i++] = NFCL0760Constant.VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_DATA_VOL;

        setArgForSubScreen(params);
    }
}
