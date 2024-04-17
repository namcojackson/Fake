/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.DOT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : NMAL2550
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/28/2016   CITS            K.Ogino          Create          QC#4686
 * 03/24/2016   CITS            K.Ogino          Create          QC#5964
 * 12/20/2016   CITS            S.Endo           Update          QC#15717
 * 01/17/2016   CITS            S.Endo           Update          QC#15717
 * 01/24/2016   CITS            S.Endo           Update          QC#15717
 * 2019/01/16   Fujitsu         S.Takami        Update          QC#29778
 *</pre>
 */
public class NPAL1280_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2019/01/16 QC#29778 Mod Start
//        return null;
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = null;

        if (BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            int idx = this.getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineNum, String.valueOf(idx));

            setAccountToPopUpNMAL2550(scrnMsg);

            bizMsg = new NPAL1280CMsg();
            bizMsg.setBusinessID(BUSINESS_APPL_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }
        return bizMsg;
        // 2019/01/16 QC#29778 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        int lineIndex = Integer.parseInt(scrnMsg.xxLineNum.getValue());
        // 2019/01/16 QC#29778 Del Start
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(scrnMsg.xxPopPrm_P1.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P3.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P4.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P5.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P6.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P7.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P2.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P8.getValue());
//        sb.append(DOT);
//        sb.append(scrnMsg.xxPopPrm_P9.getValue());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1, sb.toString());
//
//
//        if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1, scrnMsg.xxPopPrm_P1);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAfflCd_A1, scrnMsg.xxPopPrm_P2);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaBrCd_A1, scrnMsg.xxPopPrm_P3);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCcCd_A1, scrnMsg.xxPopPrm_P4);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAcctCd_A1, scrnMsg.xxPopPrm_P5);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProdCd_HD, scrnMsg.xxPopPrm_P6);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaChCd_A1, scrnMsg.xxPopPrm_P7);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProjCd_A1, scrnMsg.xxPopPrm_P8);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaExtnCd_A1, scrnMsg.xxPopPrm_P9);
//        }
//
//        if (!PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(lineIndex).poMdseCmpsnTpCd_A1.getValue())) {
//            String lineNum = scrnMsg.A.no(lineIndex).xxLineNum_A1.getValue();
//            for (int i = lineIndex + 1; i < scrnMsg.A.getValidCount(); i++) {
//                String chkLine = scrnMsg.A.no(i).xxLineNum_A1.getValue();
//                if (chkLine.startsWith(lineNum)) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxScrItem130Txt_A1, scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaCmpyCd_A1, scrnMsg.A.no(lineIndex).coaCmpyCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaAfflCd_A1, scrnMsg.A.no(lineIndex).coaAfflCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaBrCd_A1, scrnMsg.A.no(lineIndex).coaBrCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaCcCd_A1, scrnMsg.A.no(lineIndex).coaCcCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaAcctCd_A1, scrnMsg.A.no(lineIndex).coaAcctCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaProdCd_HD, scrnMsg.A.no(lineIndex).coaProdCd_HD.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaChCd_A1, scrnMsg.A.no(lineIndex).coaChCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaProjCd_A1, scrnMsg.A.no(lineIndex).coaProjCd_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).coaExtnCd_A1, scrnMsg.A.no(lineIndex).coaExtnCd_A1.getValue());
//                }
//            }
//        }
        // 2019/01/16 QC#29778 Del End
        // 2019/01/16 QC#29778 Add Start
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        // 2019/01/16 QC#29778 Add End
        scrnMsg.setFocusItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
    }

    // 2019/01/16 QC#29778 Add Start
    private void setAccountToPopUpNMAL2550(NPAL1280BMsg scrnMsg) {

        int lineIndex = Integer.parseInt(scrnMsg.xxLineNum.getValue());
        StringBuilder sb = new StringBuilder();

        sb.append(scrnMsg.xxPopPrm_P1.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P3.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P4.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P5.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P6.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P7.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P2.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P8.getValue());
        sb.append(DOT);
        sb.append(scrnMsg.xxPopPrm_P9.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1, sb.toString());


        if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue()) //
                || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1, scrnMsg.xxPopPrm_P1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAfflCd_A1, scrnMsg.xxPopPrm_P2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaBrCd_A1, scrnMsg.xxPopPrm_P3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCcCd_A1, scrnMsg.xxPopPrm_P4);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAcctCd_A1, scrnMsg.xxPopPrm_P5);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProdCd_HD, scrnMsg.xxPopPrm_P6);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaChCd_A1, scrnMsg.xxPopPrm_P7);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProjCd_A1, scrnMsg.xxPopPrm_P8);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaExtnCd_A1, scrnMsg.xxPopPrm_P9);
        }
    }
    // 2019/01/16 QC#29778 Add End
}
