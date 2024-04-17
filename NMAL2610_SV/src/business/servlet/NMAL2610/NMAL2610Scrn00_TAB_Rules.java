/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/09   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/08/29   SRAA            Y.Chen          Update          QC#7966
 * 2017/03/08   Fujitsu         R.Nakamura      Update          QC#15878
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610Scrn00_TAB_Rules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CommonLogic.clearMandantoryForHeader(scrnMsg);
        NMAL2610CommonLogic.addCheckItem(scrnMsg, false);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/02/09 S21-QC#2869 Delete Start
        // NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        //
        // NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        // bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        // bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // 2016/02/09 S21-QC#2869 Delete End
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        // 2016/02/09 S21-QC#2869 Delete Start
        // NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // if (scrnMsg.getMessageType() ==
        // EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
        // throw new EZDFieldErrorException();
        // }
        // 2016/02/09 S21-QC#2869 Delete End

        // 2018/09/21 QC#27726,ADD Add Start
        NMAL2610CommonLogic.clearAllRowsBG(scrnMsg);
        // 2018/09/21 QC#27726,ADD Add End
        scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);

        // Add Start 2017/03/08 QC#15878
        scrnMsg.clearAllGUIAttribute(NMAL2610Constant.SCREEN_ID);
        // Add End 2017/03/08 QC#15878
        // QC#7966
        // scrnMsg.orgNm_F1.setInputProtected(true);
        // NMAL2610CommonLogic.disableLinkButton(this);
        NMAL2610CommonLogic.setIniOrgStatDate(scrnMsg);

        // Add Start 2017/03/08 QC#15878
        NMAL2610CommonLogic.controlScreenTrtyRuleFields(this, scrnMsg);
        // Add End 2017/03/08 QC#15878
        // 2018/09/21 QC#27726,ADD Add Start
        NMAL2610CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_RULE);
        NMAL2610CommonLogic.setAddDelButton(this, scrnMsg);
        // 2018/09/21 QC#27726,ADD Add End
    }
}
