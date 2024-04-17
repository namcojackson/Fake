/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/22   Fujitsu         C.Yokoi         Update          CSA-QC#3280
 * 2016/02/24   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431-1
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 *</pre>
 */
public class NMAL2520Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // 2016/02/05 CSA-QC#2869 Mod Start
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        // 2016/02/05 CSA-QC#2869 Mod End

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        // 2018/09/20 QC#27724,ADD Add Start
        String beforeTabName = scrnMsg.xxDplyTab.getValue();
        // 2018/09/20 QC#27724,ADD Add End
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/09/20 QC#27724,ADD Add Start
        try {
            // 2018/09/20 QC#27724,ADD Add End
    
            //scrnMsg.putErrorScreen();
            if (scrnMsg.getMessageCode().endsWith(NMAL2520Constant.MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(NMAL2520Constant.MESSAGE_KIND_WARN)) {
                NMAL2520CommonLogic.addCheckItems(scrnMsg);
                scrnMsg.putErrorScreen();
                throw new EZDFieldErrorException();
            }
    
            // Add Start 2019/02/27 QC#30564
            NMAL2520CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);

            NMAL2520CommonLogic.controlOrgLink(scrnMsg);
            NMAL2520CommonLogic.controlContract(scrnMsg);
            NMAL2520CommonLogic.controlRscLink(scrnMsg);

            NMAL2520CommonLogic.controlInsertDelete(this, scrnMsg);
            NMAL2520CommonLogic.setAllBGWithClear(scrnMsg);
            NMAL2520CommonLogic.setAddDelButton(this, scrnMsg);
            // Add End 2019/02/27 QC#30564

            // 2016/03/04 CSA-QC#4654 Add Start
            NMAL2520CommonLogic.controlBusinessAreaFields(scrnMsg);
            // 2016/03/04 CSA-QC#4654 Add End
            // QC#13431
            NMAL2520CommonLogic.controlAttachmentsButton(this, scrnMsg);
    
            if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
                scrnMsg.setMessageInfo(NMAL2520Constant.NZZM0002I);
            }
            // 2018/09/20 QC#27724,ADD Add Start
        } finally {
            NMAL2520CommonLogic.setAllBGWithReset(scrnMsg, beforeTabName);
            NMAL2520CommonLogic.setAddDelButton(this, scrnMsg);
        }
        // 2018/09/20 QC#27724,ADD Add End
        // 2016/02/23 CSA-QC#4336 Delete Start
        // else {
        //    if (scrnMsg.getMessageCode().endsWith(NMAL2520Constant.MESSAGE_KIND_INF)) {
        //        scrnMsg.setMessageInfo(NMAL2520Constant.NZZM0002I);
        //    }
        // }
        // 2016/02/23 CSA-QC#4336 Delete End
    }
}
