/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */
public class NMAL2510Scrn00_TAB_Revenue extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue())) {
            scrnMsg.psnNum_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9007E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_NUM });
            scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        }

        // 2016/02/08 CSA-QC#2869 Add Start
        NMAL2510CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2510CommonLogic.addCheckItems(scrnMsg);
        // 2016/02/08 CSA-QC#2869 Add End

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2016/02/08 CSA-QC#2869 Delete Start
        // NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        //
        // NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        // bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        // bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // 2016/02/08 CSA-QC#2869 Delete Start
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        // 2016/02/08 CSA-QC#2869 Delete Start
        // NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // if (scrnMsg.getMessageType() ==
        // EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
        // throw new EZDFieldErrorException();
        // }
        // // 2016/02/08 CSA-QC#2869 Delete End
        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        NMAL2510CommonLogic.clearAllRowsBG(scrnMsg);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
        scrnMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE);
        NMAL2510CommonLogic.setProtectEffectiveFrom(scrnMsg);

//      if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1.getValue()) && (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue()))) {
          NMAL2510CommonLogic.setProtectKeyValue(scrnMsg);
//      }
        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        NMAL2510CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2510Constant.SCREEN_TABLE_NAME_REVENUE);
        NMAL2510CommonLogic.setAddDelButton(this, scrnMsg);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}
