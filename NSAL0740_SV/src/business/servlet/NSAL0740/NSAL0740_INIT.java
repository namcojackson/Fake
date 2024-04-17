/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.initialControlScreen;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.*;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBBigDecimalItemArray;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/02/08   Hitachi         T.Aoyagi        Update          QC#4089
 * 2016/04/15   Hitachi         T.Tomita        Update          QC#4085
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 *</pre>
 */
public class NSAL0740_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).dsContrPk_P1, (EZDBBigDecimalItem) params[0]);
            scrnMsg.P.setValidCount(1);
        }

        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItemArray) {
            EZDBBigDecimalItemArray array = (EZDBBigDecimalItemArray) params[0];
            int i = 0;
            int cnt = 0;
            for (; i < array.length(); i++) {
                if (ZYPCommonFunc.hasValue(array.no(i))) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(cnt).dsContrPk_P1, array.no(i));
                    cnt++;
                }
            }
            scrnMsg.P.setValidCount(cnt);
        }

        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBMsgArray) {
            EZDMsg.copy(((EZDBMsgArray) params[0]), null, scrnMsg.P, null);
        }

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg = (NSAL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        scrnMsg.contrUplftTpCd_H3.setNameForMessage("New Escalation Type");
        scrnMsg.uplftPrcMethCd_H3.setNameForMessage("New Escalation Method");
        scrnMsg.uplftBasePrcUpRatio_H1.setNameForMessage("New Base %");
        scrnMsg.uplftMtrPrcUpRatio_H1.setNameForMessage("New Overage %");
        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H1.setNameForMessage("Notes");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // START 2016/04/15 T.Tomita [QC#4085, ADD]
            scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1.setNameForMessage("Annual Escalation #Days");
            // END 2016/04/15 T.Tomita [QC#4085, ADD]
            // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//            scrnMsg.A.no(i).basePrcUpRatio_D1.setNameForMessage("Annual Escalation Base %");
//            scrnMsg.A.no(i).mtrPrcUpRatio_D1.setNameForMessage("Annual Escalation Overage %");
            scrnMsg.A.no(i).uplftBasePrcUpRatio_D1.setNameForMessage("Annual Escalation Base %");
            scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1.setNameForMessage("Annual Escalation Overage %");
            // END   2016/02/08 T.Aoyagi [QC#4089, MOD]
            // START 2018/11/16 K.Kitachi [QC#28638, ADD]
            scrnMsg.A.no(i).maxPrcUpRatio_D1.setNameForMessage("Annual Escalation Max %");
            scrnMsg.A.no(i).fixTermInMthAot_D1.setNameForMessage("Fixed Month");
            scrnMsg.A.no(i).uplftFixedDt_D1.setNameForMessage("Hold Price Until Date");
            scrnMsg.A.no(i).uplftPcyDt_D1.setNameForMessage("Policy Date");
            // END 2018/11/16 K.Kitachi [QC#28638, ADD]
        }
    }
}
