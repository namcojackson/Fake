/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4948
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23753
 * 2019/02/13   Hitachi         K.Kim           Update          QC#30309
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_Attach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        // START 2016/03/29 M.Gotou [QC#4948, MOD]
        Object[] params = new Object[9];

        String sqNum = scrnMsg.svcMachMstrPk_H1.getValue().toString();

        // START 2018/02/19 M.Kidokoro [QC#23753, MOD]
//        params[0] = PARAMS_DISPLAY_MODE;
        List<String> functionIds = NSAL0010CommonLogic.getFunctionCodeList(getUserProfileService());
        // START 2019/02/13 [QC#30309, MOD]
        if (functionIds.contains(FUNC_ID_READ)
                && !functionIds.contains(FUNC_ID_UPDATE)
                && !functionIds.contains(FUNC_ID_SER_CHANGE)
                && !functionIds.contains(FUNC_ID_LGSC)
                && !functionIds.contains(FUNC_ID_MDSE_EDIT)
                && !functionIds.contains(FUNC_ID_DISPLAY_LIMITED_IB)) {
            params[0] = PARAMS_DISPLAY_MODE_READ_ONLY;
        } else {
            // params[0] = PARAMS_DISPLAY_MODE;
            params[0] = PARAMS_DISPLAY_MODE_MODIFICATION;
        }
        // END 2019/02/13 [QC#30309, MOD]
        // END 2018/02/19 M.Kidokoro [QC#23753, MOD]
        params[1] = BUSINESS_ID;
        params[2] = getGlobalCompanyCode() + sqNum;
        params[3] = "Machine Master Attachments";
        params[4] = "IB ID";
        params[5] = null;
        params[6] = PARAMS_UPPER_KEY;
        params[7] = PARAMS_EXTENSION_KEY;
        params[8] = PARAMS_SIZE_KEY;
        // END 2016/03/29 M.Gotou [QC#4948, MOD]

        setArgForSubScreen(params);
    }
}
