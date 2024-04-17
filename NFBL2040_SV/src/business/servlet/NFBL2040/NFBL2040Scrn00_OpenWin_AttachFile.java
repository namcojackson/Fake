/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/23   Hitachi         T.Tsuchida      Create          QC#4492
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#10995
 * 2016/08/24   Hitachi         Y.Tsuchimoto    Update          QC#13693
 * 2016/11/16   Hitachi         Y.Tsuchimoto    Update          QC#15940
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_AttachFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
        Object[] params = new Object[PARAM_INDEX_9];
        StringBuilder sqNum = new StringBuilder();
        sqNum.append(getGlobalCompanyCode());
        sqNum.append(PARAMS_AP_VND_CD_KEY);
        sqNum.append(scrnMsg.apVndCd.getValue());
        sqNum.append(PARAMS_AP_VND_INV_NUM_KEY);
        sqNum.append(scrnMsg.apVndInvNum.getValue());

        String displayMode = PARAMS_DISPLAY_MODE_READ_ONLY;
        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Invoice Entry Screen(NFBL2040). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        // START 2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        //if (funcIdList.contains(FUNC_ID_NFBL2040T020)) {
        if (funcIdList.contains(FUNC_ID_NFBL2040T010)) {
            // START 2016/07/07 [QC#10995,MOD]
            displayMode = PARAMS_DISPLAY_MODE_MODIFICATION;
            // END   2016/07/07 [QC#10995,MOD]
        }
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,MOD]

        int i = 0;
        params[i++] = displayMode;
        params[i++] = BIZ_ID;
        params[i++] = sqNum.toString();
        params[i++] = PARAMS_FUNCTION_NAME;
        params[i++] = PARAMS_PRIMARY_KEY;
        // START 2016/11/16 Y.Tsuchimoto [QC#15940,MOD]
        //params[i++] = null;
        params[i++] = PARAMS_DOC_TYPE_TABLE_NAME;
        // END   2016/11/16 Y.Tsuchimoto [QC#15940,MOD]
        params[i++] = PARAMS_UPPER_KEY;
        params[i++] = PARAMS_EXTENSION_KEY;
        params[i++] = PARAMS_SIZE_KEY;

        setArgForSubScreen(params);

    }
}
