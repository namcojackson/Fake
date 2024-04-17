/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.PARAM_PRCH_REQ_TP_CD_1;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.PARAM_PRCH_REQ_TP_CD_2;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.PRCH_REQ_TP_NM_1;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.PRCH_REQ_TP_NM_2;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1160.common.NPAL1160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPAL1160Scrn00_OnChange_Transactions extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;

        int lineNum = getButtonSelectNumber();

        if(APVL_HIST_SRC_TP.PO_REQUISITION.equals(scrnMsg.E.no(lineNum).apvlHistSrcTpCd_ES.getValue())) {

            scrnMsg.E.no(lineNum).prchReqTpCd_EC.clear();
            scrnMsg.E.no(lineNum).prchReqTpNm_ED.clear();

            for (int i = 0; i < 3; i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(lineNum).prchReqTpCd_EC.no(i), PARAM_PRCH_REQ_TP_CD_1[i]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(lineNum).prchReqTpNm_ED.no(i), PRCH_REQ_TP_NM_1[i]);
            }

        } else if (APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(scrnMsg.E.no(lineNum).apvlHistSrcTpCd_ES.getValue())) {

            scrnMsg.E.no(lineNum).prchReqTpCd_EC.clear();
            scrnMsg.E.no(lineNum).prchReqTpNm_ED.clear();

            for (int i = 0; i < 5; i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(lineNum).prchReqTpCd_EC.no(i), PARAM_PRCH_REQ_TP_CD_2[i]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(lineNum).prchReqTpNm_ED.no(i), PRCH_REQ_TP_NM_2[i]);
            }

        }

        NPAL1160CommonLogic.setTableScreenE(this, scrnMsg);

    }
}
