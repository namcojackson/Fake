/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import static business.servlet.NFCL3030.constant.NFCL3030Constant.*;

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
 * 2016/05/12   Hitachi         K.Kojima        Create          QC#4492
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#4492
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10995
 *</pre>
 */
public class NFCL3030Scrn00_OpenWin_AttachFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
//        Object[] params = new Object[6];
//        String sqNum = scrnMsg.rcptNum_H.getValue().toString();
//        String attDataGrpTxt = scrnMsg.glblCmpyCd.getValue() + sqNum;
//
//        params[0] = PARAMS_MODE;
//        params[1] = PARAMS_DUTIES;
//        params[2] = attDataGrpTxt;
//        params[3] = PARAMS_UPPER_KEY;
//        params[4] = PARAMS_EXTENSION_KEY;
//        params[5] = PARAMS_SIZE_KEY;

        Object[] params = new Object[PARAM_INDEX_9];
        StringBuilder sqNum = new StringBuilder();
        sqNum.append(getGlobalCompanyCode());
        sqNum.append(scrnMsg.rcptNum_H.getValue());

        String displayMode = PARAMS_DISPLAY_MODE_READ_ONLY;
        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(FUNC_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Receipt Entry Screen(NFCL3030). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNCTION_UPDATE)) {
            // START 2016/07/08 K.Kojima [QC#10995,MOD]
            // displayMode = PARAMS_DISPLAY_MODE_UPLOAD_ONLY;
            displayMode = DISPLAY_MODE_MODIFICATION;
            // END 2016/07/08 K.Kojima [QC#10995,MOD]
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
        // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

        setArgForSubScreen(params);
    }
}
