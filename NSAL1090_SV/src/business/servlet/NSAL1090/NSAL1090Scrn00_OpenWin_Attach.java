/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;


import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1090.NSAL1090CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/31   CITS            M.Naito         Create          QC#22908
 * 2019/02/13   Hitachi         K.Kim           Update          QC#30309
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 *</pre>
 */
public class NSAL1090Scrn00_OpenWin_Attach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2021/01/12 [QC#58177] ADD
        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        scrnMsg.xxWrnSkipFlg.clear();
        // END 2021/01/12 [QC#58177] ADD
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = new NSAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;

        Object[] params = new Object[9];

        String custIncdtId = scrnMsg.custIncdtId_H.getValue().toString();
        String dsAcctNum = scrnMsg.dsAcctNum_H.getValue().toString();
        S21UserProfileService userProfileService = getUserProfileService();

        // START 2019/02/13 [QC#30309, MOD]
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        // if (functionIds.contains(FUNC_ID_READ) && !functionIds.contains(FUNC_ID_UPDATE) && !functionIds.contains(FUNC_ID_SER_CHANGE) && !functionIds.contains(FUNC_ID_LGSC) && !functionIds.contains(FUNC_ID_MDSE_EDIT)) {
        //     params[0] = PARAMS_DISPLAY_MODE_READ_ONLY;
        // } else {
        //     params[0] = PARAMS_DISPLAY_MODE;
        // }
        if (!functionIds.isEmpty()) {
            params[0] = PARAMS_DISPLAY_MODE_MODIFICATION;
        } else {
            params[0] = PARAMS_DISPLAY_MODE_READ_ONLY;
        }
        // END 2019/02/13 [QC#30309, MOD]

        params[1] = custIncdtId;
        params[2] = dsAcctNum;
        params[3] = "Credit & Rebil Attachments";
        params[4] = "Ticket#";
        params[5] = null;
        params[6] = PARAMS_UPPER_KEY;
        params[7] = PARAMS_EXTENSION_KEY;
        params[8] = PARAMS_SIZE_KEY;

        setArgForSubScreen(params);

    }
}
