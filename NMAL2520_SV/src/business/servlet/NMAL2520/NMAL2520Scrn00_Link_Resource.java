/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2018/02/14   Fujitsu         Hd.Sugawara     Update          QC#23905
 *</pre>
 */
public class NMAL2520Scrn00_Link_Resource extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (scrnMsg.C.no(i).effFromDt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("effFromDt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).effFromDt_C1.clearErrorInfo();
                }
            }
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C1);
        }
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 End Start --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Mod Start 2018/02/14 QC#23905
        //return null;
        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // Mod End 2018/02/14 QC#23905
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        // Add Start 2018/02/14 QC#23905
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR || //
                scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
            NMAL2520CommonLogic.addCheckItems(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
        // Add End 2018/02/14 QC#23905

        int index = getButtonSelectNumber();

        if (index >= 0) {
            Object[] params = new Object[1];
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(index).psnNum_C1)) {
                // Resource#
                params[0] = scrnMsg.C.no(index).psnNum_C1;
                setArgForSubScreen(params);
            }
        }
    }
}
