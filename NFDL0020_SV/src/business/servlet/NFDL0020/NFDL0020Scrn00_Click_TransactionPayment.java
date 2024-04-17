/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 06/14/2016   Hitachi         T.Tsuchida      Update          QC#9829
 * 02/17/2017   Fujitsu         T.Murai         Update          QC#17193
 * 02/28/2017   Fujitsu         T.Murai         Update          QC#17193
 * 01/29/2020   CITS            M.Furugoori     Update          QC#55017
 *</pre>
 */
public class NFDL0020Scrn00_Click_TransactionPayment extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/03/07 [QC#17193, ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        scrnMsg.xxPageTblNm_H.setValue("A");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // START 2017/03/07 [QC#17193, ADD]
        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg; // ADD 20174/03/07 [QC#17193]

        EZDMsg.copy(bizMsg, null, scrnMsg, null); // ADD 20174/03/07 [QC#17193]

        // START 2017/03/07 [QC#17193,DEL]
//        List<Integer> chkNoList = new ArrayList<Integer>();
//
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            if (scrnMsg.A.no(i).xxChkBox_A.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
//                chkNoList.add(i);
//            }
//        }
//
//        if (chkNoList.size() == 0) {
//            scrnMsg.setMessageInfo("NFAM0075E");
//        // START 06/14/2016 T.Tsuchida [QC#9829,DEL]
//        //} else if (chkNoList.size() > 1) {
//        //    scrnMsg.setMessageInfo("NFBM0064E");
//        // END 06/14/2016 T.Tsuchida [QC#9829,DEL]
//        }
//
//        scrnMsg.putErrorScreen();
//        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//            // Search do not set error message on fields.
//            throw new EZDFieldErrorException(); 
//        }
//
//        StringBuilder invNumList = null;
//        boolean errFlg = false; // ADD 2017/02/28 T.Murai [QC#17193]
//
//        for (Integer idx : chkNoList) {
//            // START 2017/02/17 T.Murai [QC#17193,ADD]
//            if (AR_TRX_TP.CREDIT_MEMO.equals(scrnMsg.A.no(idx).arTrxTpCd_A.getValue()) //
//                    || AR_TRX_TP.RECEIPT.equals(scrnMsg.A.no(idx).arTrxTpCd_A.getValue())) {
//
//                scrnMsg.A.no(idx).xxChkBox_A.setErrorInfo(1, "NFDM0045E");
//                scrnMsg.setFocusItem(scrnMsg.A.no(idx).xxChkBox_A);
//                scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxChkBox_A);
//                errFlg = true;
//            } else if (!errFlg) {
//            // END 2017/02/17 T.Murai [QC#17193,ADD]
//                if (invNumList == null) {
//                    invNumList = new StringBuilder(scrnMsg.A.no(idx).arTrxNum_A.getValue());
//                } else {
//                    invNumList.append(",");
//                    invNumList.append(scrnMsg.A.no(idx).arTrxNum_A.getValue());
//                }
//            } // ADD 2017/02/28 T.Murai [QC#17193]
//        }
//        // START 2017/02/28 T.Murai [QC#17193,ADD]
//        if (errFlg) {
//            scrnMsg.putErrorScreen();
//            throw new EZDFieldErrorException();
//        }
//        // END   2017/02/28 T.Murai [QC#17193,ADD]
        // END   2017/03/07 [QC#17193,DEL]
        // START 2017/03/07 [QC#17193,ADD]
        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }

        boolean errFlg = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            if (!errFlg && scrnMsg.A.no(i).xxChkBox_A.getErrorCode() == 1) {
                errFlg = true;
            }
        }
        if (errFlg) {
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
        // START 2017/03/07 [QC#17193,ADD]
        NFDL0020CommonLogic.initialize(this, scrnMsg);
        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }

    private Object[] setPopupParameter(NFDL0020BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Click_TransactionPayment");
        Object[] params = new Object[2];
        params[0] = scrnMsg.dsAcctNum_H;
        // START 2020/01/29 [QC#55017,MOD]
        // params[1] = scrnMsg.xxTrxCdSrchTxt;
        params[1] = scrnMsg.xxTrxNumSrchTxt;
        // END   2020/01/29 [QC#55017,MOD]
        return params;
    }
}