/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL5140;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItemArray;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItemArray;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_Insert_Record class.
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 09/29/2010   Fujitsu         I.Kondo         Update          DefID:8207 No.410
 * 10/01/2010   Fujitsu         I.Kondo         Update          DefID:8180 No.421
 * 11/04/2010   Fujitsu         I.Kondo         Update          DefID:M15
 * 08/24/2016   Hitachi         T.Tsuchida      Update          QC#6464
 * 11/30/2022   Hitachi         R.Takau         Update          QC#57252
 * </pre>
 */
public class NFCL5140Scrn00_Insert_Record extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        if (AR_TRX_TP_CD.ACC.getValue().equals(scrnMsg.xxModeInd.getValue())) {

            // Amount
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum)) {
                scrnMsg.xxInpAmtNum.setErrorInfo(1, "NFCM0038E");
            } else {
                if (scrnMsg.xxInpAmtNum.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    scrnMsg.xxInpAmtNum.setErrorInfo(1, "NFCM0004E");
                } else {
                    // nothing
                }
            }
            scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum);

            scrnMsg.addCheckItem(scrnMsg.xxInvCmntTxt);

        } else if (AR_TRX_TP_CD.DED.getValue().equals(scrnMsg.xxModeInd.getValue())) {
            // Amount
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum)) {
                scrnMsg.xxInpAmtNum.setErrorInfo(1, "NFCM0038E");
            } else {
                if (scrnMsg.xxInpAmtNum.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    scrnMsg.xxInpAmtNum.setErrorInfo(1, "NFCM0004E");
                } else {
                    // nothing
                }
            }
            scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum);

            // AR_CUST_REF_NUM
            if (!ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum)) {
                scrnMsg.arCustRefNum.setErrorInfo(1, "NFCM0038E");
            } else {
                // nothing
            }
            scrnMsg.addCheckItem(scrnMsg.arCustRefNum);

            scrnMsg.addCheckItem(scrnMsg.xxInvCmntTxt);

        } else if (AR_TRX_TP_CD.ADJ.getValue().equals(scrnMsg.xxModeInd.getValue())) {
            // Amount
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum)) {
                scrnMsg.xxInpAmtNum.setErrorInfo(1, "NFCM0038E");
            } else {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.arMinusEntryTrgtFlg.getValue()) && scrnMsg.xxInpAmtNum.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    scrnMsg.xxInpAmtNum.setErrorInfo(1, "NFCM0004E");
                } else {
                    // nothing
                }
            }
            scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum);

            // TOC_CD
            /*if (!ZYPCommonFunc.hasValue(scrnMsg.tocCd)) {
                scrnMsg.tocCd.setErrorInfo(1, "NFCM0038E");
            } else {
                // nothing
            }
            scrnMsg.addCheckItem(scrnMsg.tocCd);

            // COA_PROD_CD
            if (!ZYPCommonFunc.hasValue(scrnMsg.coaProdCd)) {
                scrnMsg.coaProdCd.setErrorInfo(1, "NFCM0038E");
            } else {
                // nothing
            }
            scrnMsg.addCheckItem(scrnMsg.coaProdCd);*/

            // AR_ADJ_TP_CD
            if (!ZYPCommonFunc.hasValue(scrnMsg.arAdjTpCd_P1)) {
                scrnMsg.arAdjTpCd_P1.setErrorInfo(1, "NFCM0028E");
            } else {
                // nothing
            }
            
            //START 2022/11/17 R.Takau [QC#57252,ADD]
            if (SLC_OTHER.equals(scrnMsg.arAdjTpCd_P1.getValue())){
                if (!scrnMsg.xxCmntTxt.isInputProtected() && !ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt)) {
                    scrnMsg.xxCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Charge Account" });
                }
            }
            
            scrnMsg.addCheckItem(scrnMsg.xxCmntTxt);
            //END 2022/11/21 R.Takau [QC#57252,ADD]
            
            scrnMsg.addCheckItem(scrnMsg.arAdjTpCd_P1);

            scrnMsg.addCheckItem(scrnMsg.xxInvCmntTxt);
            
        }

        scrnMsg.putErrorScreen();
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

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // START 2022/11/30 R.Takau [QC#57252,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt);
        // END   2022/11/30 R.Takau [QC#57252,ADD]

        //scrnMsg.addCheckItem(scrnMsg.tocCd);
        //scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        scrnMsg.putErrorScreen();

        NFCL5140CommonLogic.clearHeaderInfoAfterInsert(scrnMsg);

        this.setButtonProperties("Add_Record", "Add_Record", "Add", 1, null);
        this.setButtonProperties("Delete_Record", "Delete_Record", "Delete", 1, null);

        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

        NFCL5140CommonLogic.setRowBg(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxModeInd);

        NFCL5140CommonLogic.setAppFracDigit(scrnMsg);
        
        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

        Object[] params = (Object[]) getArgForSubScreen();

        EZDBStringItemArray para0 = (EZDBStringItemArray) params[PARAMS.NUM_0.getValue()];
        EZDBBigDecimalItemArray para1 = (EZDBBigDecimalItemArray) params[PARAMS.NUM_1.getValue()];
        EZDBStringItemArray para2 = (EZDBStringItemArray) params[PARAMS.NUM_2.getValue()]; // AR_CUST_REF_NUM
        EZDBStringItemArray para3 = (EZDBStringItemArray) params[PARAMS.NUM_3.getValue()]; // TOC
        EZDBStringItemArray para4 = (EZDBStringItemArray) params[PARAMS.NUM_4.getValue()]; // PROD
        EZDBStringItemArray para5 = (EZDBStringItemArray) params[PARAMS.NUM_5.getValue()]; // ADJ_TP
        EZDBStringItemArray para6 = (EZDBStringItemArray) params[PARAMS.NUM_6.getValue()]; // Comments
		// START 2022/11/30 R.Takau [QC#57252,ADD]
        EZDBStringItemArray para11 = (EZDBStringItemArray) params[PARAMS.ADDED_NUM_11.getValue()]; // xxCmntTxt
		// END 2022/11/30 R.Takau [QC#57252,ADD]
	
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
                    // START 2016/08/24 T.Tsuchida [QC#6464,MOD]
                    //if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.P.no(i).arAdjManEntryFlg_PA.getValue())) {
                    //    para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue());
                    //} else {
                    //    para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue().negate());
                    //}
                    para1.no(i).setValue(scrnMsg.P.no(i).xxInpAmtNum_PA.getValue());
                    // END 2016/08/24 T.Tsuchida [QC#6464,MOD]
                    // START 2022/11/28 R.Takau [QC#57252,ADD]
                    para11.no(i).setValue(scrnMsg.xxCmntTxt.getValue());
                    // END 2022/11/28 R.Takau [QC#57252,ADD]
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
