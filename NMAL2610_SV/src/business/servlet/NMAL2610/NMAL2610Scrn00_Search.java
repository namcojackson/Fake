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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11453
 * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431
 * 2017/03/02   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CommonLogic.clearMandantoryForHeader(scrnMsg);
        NMAL2610CommonLogic.addCheckItem(scrnMsg, false);

// 2017/12/05 QC#21270 Del Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_H1.getValue())) {
//            scrnMsg.orgNm_H1.setErrorInfo(1, NMAL2610Constant.ZZZM9007E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM });
//            scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
//        }
// 2017/12/05 QC#21270 Del End

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        scrnMsg.orgCd_H1.clear();

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2016/02/05 CSA-QC#2869 Mod Start
        NMAL2610CommonLogic.controlOrgLink(scrnMsg);

        NMAL2610CommonLogic.controlRolePullDown(scrnMsg);
        // 2016/02/05 CSA-QC#2869 Mod End
        // 2016/10/11 CSA-QC#13431 Add Start
        NMAL2610CommonLogic.controlAttachmentButton(this, scrnMsg);
        // 2016/10/11 CSA-QC#13431 Add End
        // Add Start 2017/03/02 QC#15878
        NMAL2610CommonLogic.controlScreenFields(this, scrnMsg);
        // Add End 2017/03/02 QC#15878
        // 2018/09/21 QC#27726,ADD Add Start
        NMAL2610CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2610CommonLogic.setAddDelButton(this, scrnMsg);
        // 2018/09/21 QC#27726,ADD Add End

        // 2016/07/27 CSA-QC#11453 Delete Start
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
           cMsg.setMessageInfo(NMAL2610Constant.NMAM8182I, new String[] {NMAL2610Constant.SEARCH});
        }
        // 2016/07/27 CSA-QC#11453 Delete End
    }
}
