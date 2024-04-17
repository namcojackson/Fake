/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610Scrn00_InsertRow_Resource extends S21CommonHandler {

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

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.D.getValidCount() + 1 > scrnMsg.D.length()) {
            setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RESOURCE, false);
        }
        if (scrnMsg.D.getValidCount() > 0) {
            setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RESOURCE, true);
        }

        NMAL2610CommonLogic.controlRolePullDown(scrnMsg);
        NMAL2610CommonLogic.setResourceStartDate(scrnMsg);
        // 2018/09/21 QC#27726,ADD Add Start
        NMAL2610CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.D, NMAL2610Constant.SCREEN_TABLE_NAME_RESRC);
        // 2018/09/21 QC#27726,ADD Add End
    }
}
