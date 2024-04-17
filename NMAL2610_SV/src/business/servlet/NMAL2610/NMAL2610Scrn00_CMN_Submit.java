/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
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
 * 2016/06/24   Hitachi         A.Kohinata      Update          CSA-QC#10276
 * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 * 2018/09/28   Fujitsu         M.Ohno          Update          QC#28277
 *</pre>
 */
public class NMAL2610Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        // 2016/02/05 CSA-QC#2869 Delete Start
        // scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_P1);
        // if (!ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1.getValue())) {
        // scrnMsg.bizAreaOrgCd_P1.setErrorInfo(1,
        // NMAL2610Constant.ZZZM9007E, new String[]
        // {NMAL2610Constant.NAME_FOR_MESSAGE_BIZ_AREA_ORG_CD });
        // }
        //
        // scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        // if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_H1.getValue())) {
        // scrnMsg.orgNm_H1.setErrorInfo(1,
        // NMAL2610Constant.ZZZM9007E, new String[]
        // {NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM });
        // }
        //
        // scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        // if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1.getValue())) {
        // scrnMsg.effFromDt_H1.setErrorInfo(1,
        // NMAL2610Constant.ZZZM9007E, new String[]
        // {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
        // }
        // 2016/02/05 CSA-QC#2869 Delete End
        scrnMsg.xxDplyTab_BK.setValue(scrnMsg.xxDplyTab.getValue());
        NMAL2610CommonLogic.addCheckItem(scrnMsg, false);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;

        // 2018/09/21 QC#27726,ADD Add Start
        String beforeTabName = scrnMsg.xxDplyTab.getValue();
        // 2018/09/21 QC#27726,ADD Add End

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2016/06/24 QC#10276 Add Start
        NMAL2610CommonLogic.controlOrgLink(scrnMsg);
        // 2016/06/24 QC#10276 Add End

        // 2016/10/11 CSA-QC#13431 Add Start
        NMAL2610CommonLogic.controlAttachmentButton(this, scrnMsg);
        // 2016/10/11 CSA-QC#13431 Add End
        // 2018/09/21 QC#27726,ADD Add Start

        try {
        // 2018/09/21 QC#27726,ADD Add End

            if (scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_WARN)) {
                // 2018/09/28 QC#28277 add start
//                scrnMsg.orgCd_H1.clear();
                // 2018/09/28 QC#28277 add end
                // 2016/02/05 CSA-QC#2869 Add Start
                NMAL2610CommonLogic.addCheckItem(scrnMsg, true);
                scrnMsg.putErrorScreen();
                scrnMsg.xxDplyTab.setValue(scrnMsg.xxDplyTab_BK.getValue());
                // 2016/02/05 CSA-QC#2869 Add End
                throw new EZDFieldErrorException();
            }
    
            if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
                scrnMsg.setMessageInfo(NMAL2610Constant.NZZM0002I);
            }
            // 2018/09/21 QC#27726,ADD Add Start
        } finally {
            NMAL2610CommonLogic.setAllBGWithReset(scrnMsg, beforeTabName);
            NMAL2610CommonLogic.setAddDelButton(this, scrnMsg);
        }
        // 2018/09/21 QC#27726,ADD Add End

        // 2016/02/23 CSA-QC#4336 Delete Start
        // else {
        //    if (scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_INF)) {
        //        scrnMsg.setMessageInfo(NMAL2610Constant.NZZM0002I);
        //    }
        // }
        // 2016/02/23 CSA-QC#4336 Delete End

    }
}
