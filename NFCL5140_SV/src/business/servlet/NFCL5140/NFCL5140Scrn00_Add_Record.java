/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 09/29/2010   Fujitsu         I.Kondo         Update          DefID:8207 No.410
 * 11/04/2010   Fujitsu         I.Kondo         Update          DefID:M15
 * </pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBBigDecimalItemArray;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItemArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_Add_Record class.
 */
public class NFCL5140Scrn00_Add_Record extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
        NFCL5140CMsg bizMsg = new NFCL5140CMsg();
        bizMsg.setBusinessID("NFCL5140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

        Object[] params = (Object[]) getArgForSubScreen();

        EZDBStringItemArray para0 = (EZDBStringItemArray) params[PARAMS.NUM_0.getValue()];
        EZDBBigDecimalItemArray para1 = (EZDBBigDecimalItemArray) params[PARAMS.NUM_1.getValue()];
        EZDBStringItemArray para2 = (EZDBStringItemArray) params[PARAMS.NUM_2.getValue()]; // AR_CUST_REF_NUM
        EZDBStringItemArray para3 = (EZDBStringItemArray) params[PARAMS.NUM_3.getValue()]; // TOC
        EZDBStringItemArray para4 = (EZDBStringItemArray) params[PARAMS.NUM_4.getValue()]; // PROD
        EZDBStringItemArray para5 = (EZDBStringItemArray) params[PARAMS.NUM_5.getValue()]; // ADJ_TP
        EZDBStringItemArray para6 = (EZDBStringItemArray) params[PARAMS.NUM_6.getValue()]; // Comments

        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
            if (null != scrnMsg.P.no(i) && ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxInpAmtNum_PA)) {
                if (AR_TRX_TP_CD.ACC.toString().equals(scrnMsg.P.no(i).arTrxTpCd_PA.getValue())) {
                    para0.no(i).setValue(AR_TRX_TP_CD.ACC.getValue());
                    para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue());
                } else if (AR_TRX_TP_CD.DED.toString().equals(scrnMsg.P.no(i).arTrxTpCd_PA.getValue())) {
                    para0.no(i).setValue(AR_TRX_TP_CD.DED.getValue());
                    para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue().negate());
                } else if (AR_TRX_TP_CD.ADJ.toString().equals(scrnMsg.P.no(i).arTrxTpCd_PA.getValue())) {
                    para0.no(i).setValue(AR_TRX_TP_CD.ADJ.getValue());
                    if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.P.no(i).arAdjManEntryFlg_PA.getValue())) {
                        para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue());
                    } else {
                        para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue().negate());
                    }
                }
                para2.no(i).setValue(scrnMsg.P.no(i).arCustRefNum_PA.getValue());
               // para3.no(i).setValue(scrnMsg.P.no(i).tocCd_PA.getValue());
                //para4.no(i).setValue(scrnMsg.P.no(i).coaProdCd_PA.getValue());
                para5.no(i).setValue(scrnMsg.P.no(i).arAdjTpCd_PA.getValue());
                para6.no(i).setValue(scrnMsg.P.no(i).xxInvCmntTxt_PA.getValue());
            } else {
                break;
            }
        }

    }

}
