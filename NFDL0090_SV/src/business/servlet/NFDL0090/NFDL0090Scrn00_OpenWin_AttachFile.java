/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0090.constant.NFDL0090Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/11   Hitachi         Y.Takeno        Create          QC#24884
 *</pre>
 */
public class NFDL0090Scrn00_OpenWin_AttachFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        int i = 0;
        Object[] params = new Object[9];
        params[i++] = NFDL0090Constant.ZYPL0310_PARAM_DISPLAY_MODE_MODIFICATION;
        params[i++] = NFDL0090Constant.ZYPL0310_PARAM_BIZ_ID;
        params[i++] = scrnMsg.arWrtOffRqstPk_H1.getValue().toPlainString();
        params[i++] = NFDL0090Constant.ZYPL0310_PARAM_FUNC_NM;
        params[i++] = NFDL0090Constant.ZYPL0310_PARAM_PRIMARY_KEY_NM;
        params[i++] = NFDL0090Constant.ZYPL0310_PARAM_DOC_TYPE_TABLE_NM;
        params[i++] = NFDL0090Constant.VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_LIMIT_NUM;
        params[i++] = "";
        params[i++] = NFDL0090Constant.VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_DATA_VOL;

        setArgForSubScreen(params);
    }
}
