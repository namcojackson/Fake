/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/03/11   Fujitsu         C.Yokoi         Update          CSA-QC#5346
 * 2016/08/29   SRAA            Y.Chen          Update          QC#7966
 * 2017/03/08   Fujitsu         R.Nakamura      Update          QC#15878
 *</pre>
 */
public class NMAL2610Scrn00_Filter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        
        // QC#7966
//        if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_F1.getValue())) {
//            scrnMsg.orgNm_F1.setErrorInfo(1, NMAL2610Constant.ZZZM9007E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM_FILTER });
//            scrnMsg.addCheckItem(scrnMsg.orgNm_F1);
//        }
//
//        if (scrnMsg.A.getValidCount() == 0) {
//            scrnMsg.orgNm_F1.setErrorInfo(1, NMAL2610Constant.NMAM8395E);
//            scrnMsg.addCheckItem(scrnMsg.orgNm_F1);
//        }
        scrnMsg.addCheckItem(scrnMsg.trtyRuleFromValTxt_F1);
        scrnMsg.addCheckItem(scrnMsg.trtyRuleThruValTxt_F1);
        
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

// QC#7966
//        if (NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
//            NMAL2610CommonLogic.controlOrgLink(scrnMsg);
//        }

        // Add Start 2017/03/08 QC#15878
        scrnMsg.clearAllGUIAttribute(NMAL2610Constant.SCREEN_ID);
        NMAL2610CommonLogic.controlScreenTrtyRuleFields(this, scrnMsg);
        // Add End 2017/03/08 QC#15878
    }
}
