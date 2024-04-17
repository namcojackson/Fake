/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8830;

import static business.servlet.NYEL8830.constant.NYEL8830Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.CTX_ATTR_BIZ_PROC_INF;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.CTX_ATTR_SEL_PROC_INF;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8830.NYEL8830CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenSerializer;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfo;
import com.canon.cusa.s21.framework.online.process.S21BusinessTaskInfo;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8830Scrn00_MoveWin_BizScreen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8830Scrn00_MoveWin_BizScreen extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //Nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //Nothing

    }
}
