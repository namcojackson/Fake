/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import static business.servlet.NFCL3020.constant.NFCL3020Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/07   Hitachi         K.Kojima        Create          QC#13432
 *</pre>
 */
public class NFCL3020Scrn00_OpenWin_AttachFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        Object[] params = new Object[PARAM_INDEX_9];
        StringBuilder sqNum = new StringBuilder();
        sqNum.append(getGlobalCompanyCode());
        sqNum.append(scrnMsg.arBatRcptNm_H.getValue());

        String displayMode = PARAMS_DISPLAY_MODE_READ_ONLY;
        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(FUNC_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Receipt Entry Screen(NFCL3020). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNCTION_UPDATE)) {
            displayMode = DISPLAY_MODE_MODIFICATION;
        }

        int i = 0;
        params[i++] = displayMode;
        params[i++] = FUNC_ID;
        params[i++] = sqNum.toString();
        params[i++] = PARAMS_FUNCTION_NAME;
        params[i++] = PARAMS_PRIMARY_KEY;
        params[i++] = null;
        params[i++] = PARAMS_UPPER_KEY;
        params[i++] = PARAMS_EXTENSION_KEY;
        params[i++] = PARAMS_SIZE_KEY;

        setArgForSubScreen(params);
    }
}
