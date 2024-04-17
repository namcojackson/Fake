/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import static business.servlet.NMAL2520.constant.NMAL2520Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539
 *</pre>
 */
public class NMAL2520Scrn00_OpenMultiModeScreen_Parent extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (scrnMsg.A.no(i).effFromDt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_A1.clearErrorInfo();
                }
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add End --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        Object[] params = new Object[3];

        int index = getButtonSelectNumber();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).orgNm_A1)) {
            params[0] = scrnMsg.A.no(index).orgNm_A1;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            params[1] = scrnMsg.bizAreaOrgCd_P1;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd_P1)) {
            params[2] = scrnMsg.lineBizTpCd_P1;
        }

        setArgForSubScreen(params);
        openMultiModeScreen(TAB_NAME);

    }
}
