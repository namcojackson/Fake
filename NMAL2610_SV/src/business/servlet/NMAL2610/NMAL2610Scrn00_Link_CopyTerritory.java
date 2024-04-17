/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/03/09   Fujitsu         C.Yokoi         Update          CSA-QC#5234
 * 2016/08/09   Fujitsu         C.Yokoi         Update          CSA-QC#13226
 *</pre>
 */
public class NMAL2610Scrn00_Link_CopyTerritory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_H1.getValue())) {
            scrnMsg.orgNm_H1.setErrorInfo(1, NMAL2610Constant.NMAM0014E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM });
            scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        }

        // 2016/03/04 S21_NA#4539 Add Start --------------
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

            if (scrnMsg.C.no(i).effFromDt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("effFromDt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).effFromDt_C1.clearErrorInfo();
                }
            }

            if (scrnMsg.C.no(i).trtyRuleFromValTxt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("trtyRuleFromValTxt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).trtyRuleFromValTxt_C1.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.C.no(i).trtyRuleFromValTxt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).trtyRuleThruValTxt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C1);
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

        // 2016/08/09 CSA-QC#13226 Delete Start
        // if (scrnMsg.C.getValidCount() > 0) {
        // 2016/08/09 CSA-QC#13226 Delete End
            Object[] params = new Object[NMAL2610Constant.POP_PAR_1];

            if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)) {
                params[NMAL2610Constant.POP_PAR_0] = scrnMsg.orgCd_H1;
            } else {
                throw new EZDFieldErrorException();
            }

            setArgForSubScreen(params);
            // 2016/03/09 CSA-QC#5234 Mod Start
            openMultiModeScreen(NMAL2610Constant.UPPER_TAB_SCREEN_NAME);
            // 2016/03/09 CSA-QC#5234 Mod End
        // 2016/08/09 CSA-QC#13226 Delete Start
        // } else {
        //    throw new EZDFieldErrorException();
        // }
        // 2016/08/09 CSA-QC#13226 Delete End
    }
}
