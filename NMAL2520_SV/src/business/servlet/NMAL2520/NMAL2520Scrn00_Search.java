/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
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
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11453
 * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431-1
 * 2017/12/19   Fujitsu         N.Sugiura       Update          QC#21893-2
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // 2016/02/05 CSA-QC#2869 Mod Start
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        // 2016/02/05 CSA-QC#2869 Mod End
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        scrnMsg.orgCd_H1.clear();

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2520CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);

        // 2016/02/05 CSA-QC#2869 Mod Start
        NMAL2520CommonLogic.controlOrgLink(scrnMsg);
        NMAL2520CommonLogic.controlContract(scrnMsg);
        NMAL2520CommonLogic.controlRscLink(scrnMsg);
        // 2016/02/05 CSA-QC#2869 Mod End

        // 2016/03/04 CSA-QC#4654 Add Start
        NMAL2520CommonLogic.controlBusinessAreaFields(scrnMsg);
        // 2016/03/04 CSA-QC#4654 Add End
        // QC#13431
        NMAL2520CommonLogic.controlAttachmentsButton(this, scrnMsg);

        // 2017/12/19 QC#21893-2 Add Start
        NMAL2520CommonLogic.controlInsertDelete(this, scrnMsg);
        // 2017/12/19 QC#21893-2 Add End

        // 2016/07/27 CSA-QC#11453 Add Start
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NMAL2520Constant.NMAM8182I,
                    new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_SEARCH });
        }
        // 2016/07/27 CSA-QC#11453 Add End

        // 2018/09/20 QC#27724,ADD Add Start
        NMAL2520CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2520CommonLogic.setAddDelButton(this, scrnMsg);
        // 2018/09/20 QC#27724,ADD Add End

        scrnMsg.setFocusItem(scrnMsg.orgNm_H1);
    }
}
