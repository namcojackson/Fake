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
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/29   Fujitsu         W.Honda         Update          CSA-QC#4536
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/10/07   Hitachi         Y.Takeno        Update          QC#13431
 * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431-1
 * 2017/11/30   Fujitsu         N.Sugiura       Update          QC#21893
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520_NMAL2530 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = new NMAL2520CMsg();

        String event = scrnMsg.xxEventFlgTxt.getValue();

        if (NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP.equals(event)) {
            // 2016/02/08  CSA-QC#2869 Mod Start
            if (!NMAL2520Constant.BTN_CMN_CLOSE.equals(getLastGuard())) {
                // clear all search conditions
                scrnMsg.bizAreaOrgCd_P1.clear();
                scrnMsg.orgNm_H1.clear();
                scrnMsg.orgShortNm_H1.clear();
                scrnMsg.orgDescTxt_H1.clear();
                scrnMsg.orgTierCd_P1.clear();
                scrnMsg.effFromDt_H1.clear();
                scrnMsg.effThruDt_H1.clear();
                scrnMsg.csrRgTpCd_P1.clear();
                // 2016/02/08  CSA-QC#2869 Mod End

                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_0);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_1);
                }
            }

        } else if (NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP_DETAIL.equals(event)) {

            // 2016/02/19  CSA-QC#4536 Add Start
            if (!NMAL2520Constant.BTN_CMN_CLOSE.equals(getLastGuard())) {
                int index = getButtonSelectNumber();
                scrnMsg.xxCellIdx.setValue(index);

                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).orgCd_A1, scrnMsg.xxPopPrm_0);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).orgNm_A1, scrnMsg.xxPopPrm_1);
                }
            }
            // 2016/02/19  CSA-QC#4536 Add Start
        }

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

        String event = scrnMsg.xxEventFlgTxt.getValue();

        if (NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP.equals(event)) {

            if (BIZ_AREA_ORG.CONTRACT.equals(scrnMsg.bizAreaOrgCd_P1.getValue())) {
                scrnMsg.lineBizTpCd_P1.setInputProtected(false);
                scrnMsg.xxChkBox_H1.setInputProtected(false);

            } else {
                scrnMsg.lineBizTpCd_P1.setInputProtected(true);
                scrnMsg.xxChkBox_H1.setInputProtected(true);

            }

            // 2016/03/04 CSA-QC#4654 Add Start
            NMAL2520CommonLogic.controlBusinessAreaFields(scrnMsg);
            // 2016/03/04 CSA-QC#4654 Add End

            // QC#13431
            NMAL2520CommonLogic.controlAttachmentsButton(this, scrnMsg);

            // 2017/11/30 QC#21893 Add Start
            NMAL2520CommonLogic.controlInsertDelete(this, scrnMsg);
            // 2017/11/30 QC#21893 Add End

            // 2018/09/20 QC#27724,ADD Add Start
            NMAL2520CommonLogic.setAllBGWithClear(scrnMsg);
            NMAL2520CommonLogic.setAddDelButton(this, scrnMsg);
            // 2018/09/20 QC#27724,ADD Add End
        } else if (NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP_DETAIL.equals(event)) {
            NMAL2520CommonLogic.controlOrgLink(scrnMsg);
        }
    }
}
