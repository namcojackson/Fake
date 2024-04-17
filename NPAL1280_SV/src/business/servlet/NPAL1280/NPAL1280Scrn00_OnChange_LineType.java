/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BTN_ACCOUNT_ID_PREFIX;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : OnChange_LineType
 * OnChange_LineType
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 03/24/2016   CITS            K.Ogino          Create          QC#5964
 * 01/17/2017   CITS            S.Endo           Update          QC#15717
 * 12/20/2017   CITS            K.Kameoka        Update          QC#14858(Sol#060)
 * 11/14/2018   CITS            T.Tokutomi       Update          QC#28939
 * 12/17/2018   CITS            S.Takami         Update          QC#29456
 * 12/21/2018   CITS            K.Ogino          Update          QC#29729
 *</pre>
 */
public class NPAL1280Scrn00_OnChange_LineType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        int idx = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineNum, String.valueOf(idx));
        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int lineNum = getButtonSelectNumber();
        if (scrnMsg.A.no(lineNum).prchReqLineTpCd_A1.getValue().equals(scrnMsg.A.no(lineNum).prchReqLineTpCd_HD.getValue())) {
            return;
        } else {
            if (!PRCH_REQ_LINE_TP.GOODS.equals(scrnMsg.A.no(lineNum).prchReqLineTpCd_A1.getValue())) {
                // QC#14858(Sol#060) START
                setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, lineNum, true);
                scrnMsg.A.no(lineNum).xxScrItem130Txt_A1.setInputProtected(false);
                // QC#28939 Add.
                scrnMsg.A.no(lineNum).mdseCd_A1.setIndispensable(false);
                scrnMsg.A.no(lineNum).mdseDescShortTxt_A1.setInputProtected(false);
                // 2018/12/17 QC#29456 Add Start
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(lineNum).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(lineNum).prchReqLineTpCd_A1.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(lineNum).xxScrItem130Txt_A1);
                    scrnMsg.putErrorScreen();
                }
                // QC#29729
                for (int i = lineNum + 1; i < scrnMsg.A.getValidCount(); i++) {
                    if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                        setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, true);
                        scrnMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                    } else {
                        break;
                    }
                }
                // 2018/12/17 QC#29456 Add End
            } else {
                setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, lineNum, false);
                scrnMsg.A.no(lineNum).xxScrItem130Txt_A1.setInputProtected(true);
                scrnMsg.A.no(lineNum).xxScrItem130Txt_A1.clear();
                // QC#28939 Add.
                scrnMsg.A.no(lineNum).mdseCd_A1.setIndispensable(true);
                scrnMsg.A.no(lineNum).mdseDescShortTxt_A1.setInputProtected(true);
                // QC#14858(Sol#060) END
                // QC#29729
                for (int i = lineNum + 1; i < scrnMsg.A.getValidCount(); i++) {
                    if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                        setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, false);
                        scrnMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(true);
                    } else {
                        break;
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).prchReqLineTpCd_HD, scrnMsg.A.no(lineNum).prchReqLineTpCd_A1);
        scrnMsg.setFocusItem(scrnMsg.A.no(lineNum).mdseCd_A1);

    }
}
