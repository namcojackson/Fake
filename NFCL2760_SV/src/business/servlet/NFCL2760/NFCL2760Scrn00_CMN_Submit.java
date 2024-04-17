/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#6465
 * 2017/12/07   Fujitsu         M.Ohno          Update          QC#21402-2
 * 2018/01/24   Fujitsu         H.Ikeda         Update          QC#23136
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 *</pre>
 */
public class NFCL2760Scrn00_CMN_Submit extends S21CommonHandler implements NFCL2760Constant {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
            // 2017/12/07 QC#21402-2 add start
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // 2018/01/24 QC#23136 mod start
                //if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum) && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox.getValue())){
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arCustRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxTpCd) && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox.getValue())){ 
                // 2018/01/24 QC#23136 mod end
                    
                    scrnMsg.A.no(i).xxChkBox.clear();
                }
            }
            // 2017/12/07 QC#21402-2 add end
            //if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
                NFCL2760CommonLogic.commonInputCheck(scrnMsg);
            //}
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = null;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_CMN_Submit(scrnMsg);
        
        scrnMsg.xxModeInd_H1.setValue(SCRN_MODE_SUBMIT.toString());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        EZDDebugOutput.println(1, "doProcess===START_TIME:" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"), this);
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2760CommonLogic.setCheckAllBtn(this, scrnMsg);
        // START 2016/07/14 K.Kojima [QC#6465,DEL]
        // scrnMsg.putErrorScreen();
        // END 2016/07/14 K.Kojima [QC#6465,DEL]

        NFCL2760CommonLogic.transMsgCheck(scrnMsg);

        NFCL2760CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        // START 2022/01/06 G.Delgado [QC#59329, DEL]
        // scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, DEL]

        NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_CMN_Submit(this, scrnMsg);

        //if (SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
        //    NFCL2760CommonLogic.setErrorScreen_NFCL2760(this, scrnMsg);
        //    setButtonConfirmMsg("CMN_Return", null, null, 1);
        //    if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
        //        this.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "Mail Entry", 1, null);
        //    } else {
        //        // do nothing
        //    }

        //} else {
        //    NFCL2760CommonLogic.setEntryScreen_NFCL2760(this, scrnMsg);
        //    setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        //}

        //if (CANCELERROR_STATUS_Y.equals(scrnMsg.xxRsltStsCd_H2.getValue())) {
        //    NFCL2760CommonLogic.setCancelScreen_NFCL2760(this, scrnMsg);
        //} else {
        //    // do nothing.
        //}

        NFCL2760CommonLogic.setRowBg(scrnMsg);

        //NFCL2760CommonLogic.setCheckAllBtn(this, scrnMsg);

        //if (SCRN_STATUS_Y.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
        //    NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);
        //} else {
        //    NFCL2760CommonLogic.protectAddDetailLineSubmit(scrnMsg, this);
        //    scrnMsg.xxModeInd_H1.setValue(SCRN_MODE_SUBMIT);
        //}
        //if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
        //    NFCL2760CommonLogic.protectCancelSubmit(scrnMsg, this);
        //}

        // START 2016/07/14 K.Kojima [QC#6465,DEL]
        // scrnMsg.putErrorScreen();
        // END 2016/07/14 K.Kojima [QC#6465,DEL]

        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);

        // START 2016/07/14 K.Kojima [QC#6465,ADD]
        scrnMsg.putErrorScreen();
        // END 2016/07/14 K.Kojima [QC#6465,ADD]

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind())) {
                 scrnMsg.putErrorScreen();
                throw new EZDFieldErrorException();
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }

        if (NFCL2760CommonLogic.isApiExecuteBatchMsgCd(scrnMsg)) {
            this.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "MailEntry", 0, null);
        }
        EZDDebugOutput.println(1, "doProcess===END_TIME:" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"), this);
    }
}
