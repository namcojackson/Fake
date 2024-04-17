/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
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
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/06/30   Hitachi         A.Kohinata      Update          CSA-QC#11309
 * 2016/10/11   Hitachi         Y.Takeno        Update          CSA-QC#13431
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 *</pre>
 */
public class NMAL2510Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        // 2016/02/08 CSA-QC#2869 Add Start
        NMAL2510CommonLogic.addCheckItems(scrnMsg);
        // 2016/02/08 CSA-QC#2869 Add End

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        String beforeTabName = scrnMsg.xxDplyTab.getValue();
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        try {
            // END 2018/09/14 S.Kosaka [QC#27723,ADD]
            if (scrnMsg.getMessageCode().endsWith(NMAL2510Constant.MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(NMAL2510Constant.MESSAGE_KIND_WARN)) {
                NMAL2510CommonLogic.addCheckItems(scrnMsg);
                scrnMsg.putErrorScreen();
    
                if (ZYPCommonFunc.hasValue(scrnMsg.xxMsgPrmTxt)) {
                    NMAL2510CommonLogic.setFocusForAPIError(scrnMsg);
                }
    
                throw new EZDFieldErrorException();
            }
    
            // 2016/02/23 CSA-QC#4336 Mod Start
            if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
                scrnMsg.setMessageInfo(NMAL2510Constant.NZZM0002I);
            }
            // 2016/02/23 CSA-QC#4336 Mod End
    
            // 2016/06/30 CSA-QC#11309 Add Start
            NMAL2510CommonLogic.setProtectEffectiveFrom(scrnMsg);
            // Add Start 2019/02/27 QC#30564
            NMAL2510CommonLogic.controlBusinessAreaFields(scrnMsg);
            // Add End 2019/02/27 QC#30564
            NMAL2510CommonLogic.setProtectKeyValue(scrnMsg);
            // 2016/06/30 CSA-QC#11309 Add End
            // 2016/10/11 CSA-QC#13431 Add Start
            NMAL2510CommonLogic.controlAttachmentButton(this, scrnMsg);
            // 2016/10/11 CSA-QC#13431 Add End
            // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        } finally {
            NMAL2510CommonLogic.setAllBGWithReset(scrnMsg, beforeTabName);
            NMAL2510CommonLogic.setAddDelButton(this, scrnMsg);
        }
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}
