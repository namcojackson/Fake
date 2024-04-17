/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/10/07   Hitachi         Y.Takeno        Update          CSA-QC#13431
 * 2016/11/10   Fujitsu         C.Yokoi         Update          CSA-QC#14219
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */
public class NMAL2510_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/02/08 CSA-QC#2869 Add Start
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        String event = scrnMsg.xxEventFlgTxt.getValue();
        if (NMAL2510Constant.OPEN_WIN_RESOURCE_LOOKUP.equals(event)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_H1, scrnMsg.xxPopPrm_0);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H1, scrnMsg.xxPopPrm_1);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1)) {
                NMAL2510CMsg bizMsg = new NMAL2510CMsg();
                bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
                bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }
        return null;
        // 2016/02/08 CSA-QC#2869 Add End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        String event = scrnMsg.xxEventFlgTxt.getValue();
        // 2016/02/08 CSA-QC#2869 Mod Start
        if (NMAL2510Constant.OPEN_WIN_RESOURCE_LOOKUP.equals(event)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1)) {
                NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                NMAL2510CommonLogic.setProtectEffectiveFrom(scrnMsg);

                NMAL2510CommonLogic.setProtectKeyValue(scrnMsg);
            }
            // 2016/02/08 CSA-QC#2869 Mod End

        } else if (NMAL2510Constant.OPEN_WIN_RESOURCE_LOOKUP_BY_SUPERVISOR.equals(event)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
                // 2016/11/10 CSA-QC#14219 Mod Start
                String hrSupvId = S21StringUtil.subStringByLength(scrnMsg.xxPopPrm_1.getValue(), 0, NMAL2510Constant.HR_SUPV_ID_LENGTH);
                ZYPEZDItemValueSetter.setValue(scrnMsg.hrSupvId_H1, hrSupvId);
                String hrSupvNm = S21StringUtil.subStringByLength(scrnMsg.xxPopPrm_3.getValue(), 0, NMAL2510Constant.HR_SUPV_NM_LENGTH);
                ZYPEZDItemValueSetter.setValue(scrnMsg.hrSupvNm_H1, hrSupvNm);
                // ZYPEZDItemValueSetter.setValue(scrnMsg.hrSupvId_H1, scrnMsg.xxPopPrm_0);
                // 2016/11/10 CSA-QC#14219 Mod End
            }
        }
        // 2016/03/04 CSA-QC#4654 Add Start
        NMAL2510CommonLogic.controlBusinessAreaFields(scrnMsg);
        // 2016/03/04 CSA-QC#4654 Add End
        // QC#13431
        NMAL2510CommonLogic.controlAttachmentButton(this, scrnMsg);

        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        NMAL2510CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2510CommonLogic.setAddDelButton(this, scrnMsg);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}
