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
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/06/24   Hitachi         A.Kohinata      Update          QC#10276
 * 2016/10/06   Hitachi         Y.Takeno        Update          QC#13431
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610_NMAL2630 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg = new NMAL2610CMsg();

        String event = scrnMsg.xxEventFlgTxt.getValue();

        if (!NMAL2610Constant.BTN_CMN_CLOSE.equals(getLastGuard())) {
            if (NMAL2610Constant.OPEN_WIN_TERRITORY_LOOKUP.equals(event)) {
                    // clear all search conditions
                    scrnMsg.bizAreaOrgCd_P1.clear();
                    scrnMsg.trtyTpCd_P1.clear();
                    scrnMsg.orgNm_H1.clear();
                    scrnMsg.orgTierCd_P1.clear();
                    scrnMsg.orgDescTxt_H1.clear();
                    scrnMsg.trtyGrpTpCd_P1.clear();
                    scrnMsg.effFromDt_H1.clear();
                    scrnMsg.effThruDt_H1.clear();

                    if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_0);
                    }
    
                    if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_1);
                    }

            } else if (NMAL2610Constant.OPEN_WIN_TERRITORY_LOOKUP_DETAIL.equals(event)) {

                int index = getButtonSelectNumber();
                scrnMsg.xxCellIdx.setValue(index);

                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).orgCd_A1, scrnMsg.xxPopPrm_0);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).orgNm_A1, scrnMsg.xxPopPrm_1);
                }
            }

            bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
            bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
    
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (NMAL2610Constant.BTN_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String event = scrnMsg.xxEventFlgTxt.getValue();
        // 2016/06/24 QC#10276 Mod Start
        NMAL2610CommonLogic.controlOrgLink(scrnMsg);
        if (NMAL2610Constant.OPEN_WIN_TERRITORY_LOOKUP.equals(event)) {
            NMAL2610CommonLogic.controlRolePullDown(scrnMsg);
            // QC#13431
            NMAL2610CommonLogic.controlAttachmentButton(this, scrnMsg);
        }
        // 2016/06/24 QC#10276 Mod End
        // 2018/09/21 QC#27726,ADD Add Start
        NMAL2610CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2610CommonLogic.setAddDelButton(this, scrnMsg);
        // 2018/09/21 QC#27726,ADD Add End
    }
}
