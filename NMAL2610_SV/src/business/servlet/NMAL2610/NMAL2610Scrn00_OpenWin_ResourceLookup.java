/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 *</pre>
 */
public class NMAL2610Scrn00_OpenWin_ResourceLookup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        // 2016/03/04 S21_NA#4539 Add Start --------------
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

            if (scrnMsg.D.no(i).effFromDt_D1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.D.no(i).getErrorInfo("effFromDt_D1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.D.no(i).effFromDt_D1.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effThruDt_D1);
        }
        // 2016/03/04 S21_NA#4539 Add Start --------------
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        int index = getButtonSelectNumber();

        // Making of sub screen delivery information
        Object[] params = NMAL2610CommonLogic.setParamForResourceSearchPopup(scrnMsg, index);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
