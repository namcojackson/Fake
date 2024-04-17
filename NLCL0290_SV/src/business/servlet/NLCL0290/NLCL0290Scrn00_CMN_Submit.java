/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2016/11/09   CITS            K.Kameoka       Update          QC#15558
 * 2017/02/09   CITS            M.Naito         Update          QC#17488
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472
 * 2018/12/07   CITS            T.Hakodate      Update          QC#29172
 *</pre>
 */
public class NLCL0290Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ordQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fromRtlSwhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).toRtlSwhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invtyOrdLineCmntTxt_A);
            // QC#17488
            scrnMsg.addCheckItem(scrnMsg.A.no(i).adjCatgCd_A);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        NLCL0290CMsg bizMsg = new NLCL0290CMsg();
        bizMsg.setBusinessID("NLCL0290");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // QC:18472
        //scrnMsg.addCheckItem(scrnMsg.xxScrItem130Txt_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // QC:18490 Start
            if (i == 0 &&ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).toRtlSwhCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).adjCatgCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).invtyOrdLineCmntTxt_A);
            } else {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).ordQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).fromRtlSwhCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).toRtlSwhCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).invtyOrdLineCmntTxt_A);
                // QC#17488
                scrnMsg.addCheckItem(scrnMsg.A.no(i).adjCatgCd_A);
                // QC:51155
                scrnMsg.addCheckItem(scrnMsg.A.no(i).stkStsCd_A);
            }
            // QC:18490 End
        }
        // QC#15558 Start
        NLCL0290CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
        // QC#15558 End
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // QC#15558 Start
        // NLCL0290CommonLogic.initialControlScreen(this, scrnMsg,
        // bizMsg.getUserID());
        // QC#15558 End
    }
}
