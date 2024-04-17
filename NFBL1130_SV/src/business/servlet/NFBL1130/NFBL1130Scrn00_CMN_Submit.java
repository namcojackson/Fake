/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1130.NFBL1130CMsg;
import business.servlet.NFBL1130.common.NFBL1130CommonLogic;
import business.servlet.NFBL1130.constant.NFBL1130Constant;
import business.servlet.NFBL1130.NFBL1130BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14178
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 * 2022/04/05   Hitachi         R.Onozuka       Update          QC#57935
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 *</pre>
 */
public class NFBL1130Scrn00_CMN_Submit extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        // compare current value with previous value.
        if (!scrnMsg.poNum.getValue().equals(scrnMsg.poNum_BF.getValue())) {
            scrnMsg.poNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.poNum.getNameForMessage() });	
        }
        if (!scrnMsg.mdseCd.getValue().equals(scrnMsg.mdseCd_BF.getValue())) {
            scrnMsg.mdseCd.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.mdseCd.getNameForMessage() });	
        }
        if (!scrnMsg.delyOrdNum.getValue().equals(scrnMsg.delyOrdNum_BF.getValue())) {
            scrnMsg.delyOrdNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.delyOrdNum.getNameForMessage() });	
        }

        BigDecimal invRcvQty = BigDecimal.ZERO;
        BigDecimal invRcvQty_BF = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(scrnMsg.invRcvQty)) {
            invRcvQty = scrnMsg.invRcvQty.getValue();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.invRcvQty_BF)) {
            invRcvQty_BF = scrnMsg.invRcvQty_BF.getValue();
        }
        if (invRcvQty.compareTo(invRcvQty_BF) != 0) {
            scrnMsg.invRcvQty.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.invRcvQty.getNameForMessage() });	
        }

        if (!scrnMsg.apVndInvNum.getValue().equals(scrnMsg.apVndInvNum_BF.getValue())) {
            scrnMsg.apVndInvNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.apVndInvNum.getNameForMessage() });	
        }
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // if
        // (!scrnMsg.apVndCd.getValue().equals(scrnMsg.apVndCd_BF.getValue()))
        // {
        // scrnMsg.apVndCd.setErrorInfo(1, NFBM0052E, new String[]
        // {scrnMsg.apVndCd.getNameForMessage() });
        // }
        if (!scrnMsg.prntVndCd.getValue().equals(scrnMsg.prntVndCd_BF.getValue())) {
            scrnMsg.prntVndCd.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.prntVndCd.getNameForMessage() });
        }
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        if (!scrnMsg.prntVndNm.getValue().equals(scrnMsg.prntVndNm_BF.getValue())) {
            scrnMsg.prntVndNm.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.prntVndNm.getNameForMessage() });	
        }

        BigDecimal invQty = BigDecimal.ZERO;
        BigDecimal invQty_BF = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(scrnMsg.invQty)) {
            invQty = scrnMsg.invQty.getValue();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.invQty_BF)) {
            invQty_BF = scrnMsg.invQty_BF.getValue();
        }
        if (invQty.compareTo(invQty_BF) != 0) {
            scrnMsg.invQty.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.invQty.getNameForMessage() });	
        }

        if (!scrnMsg.acrlCoaAcctCd_S.getValue().equals(scrnMsg.acrlCoaAcctCd_BF.getValue())) {
            scrnMsg.acrlCoaAcctCd_S.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.acrlCoaAcctCd_S.getNameForMessage() });    
        }
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        if (!scrnMsg.rwsNum.getValue().equals(scrnMsg.rwsNum_BF.getValue())) {
            scrnMsg.rwsNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.rwsNum.getNameForMessage() });    
        }
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        if (!scrnMsg.invDt_FR.getValue().equals(scrnMsg.invDt_BF.getValue())) {
            scrnMsg.invDt_FR.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.invDt_FR.getNameForMessage() });	
        }
        if (!scrnMsg.invDt_TO.getValue().equals(scrnMsg.invDt_BT.getValue())) {
            scrnMsg.invDt_TO.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.invDt_TO.getNameForMessage() });	
        }
        if (!scrnMsg.stkInDt_FR.getValue().equals(scrnMsg.stkInDt_BF.getValue())) {
            scrnMsg.stkInDt_FR.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.stkInDt_FR.getNameForMessage() });	
        }
        if (!scrnMsg.stkInDt_TO.getValue().equals(scrnMsg.stkInDt_BT.getValue())) {
            scrnMsg.stkInDt_TO.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.stkInDt_TO.getNameForMessage() });	
        }
        if (!scrnMsg.xxChkBox_WO.getValue().equals(scrnMsg.xxChkBox_BW.getValue())) {
            scrnMsg.xxChkBox_WO.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.xxChkBox_WO.getNameForMessage() });	
        }
        if (!scrnMsg.xxChkBox_PM.getValue().equals(scrnMsg.xxChkBox_BP.getValue())) {
            scrnMsg.xxChkBox_PM.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.xxChkBox_PM.getNameForMessage() });	
        }
        // add start 2022/08/05 QC#59245
        if (!scrnMsg.xxChkBox_CV.getValue().equals(scrnMsg.xxChkBox_BC.getValue())) {
            scrnMsg.xxChkBox_CV.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.xxChkBox_CV.getNameForMessage() });    
        }
        // add end 2022/08/05 QC#59245
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum);
        scrnMsg.addCheckItem(scrnMsg.invRcvQty);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        scrnMsg.addCheckItem(scrnMsg.invQty);
        scrnMsg.addCheckItem(scrnMsg.acrlCoaAcctCd_S);
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.addCheckItem(scrnMsg.rwsNum);
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.addCheckItem(scrnMsg.invDt_FR);
        scrnMsg.addCheckItem(scrnMsg.invDt_TO);
        scrnMsg.addCheckItem(scrnMsg.stkInDt_FR);
        scrnMsg.addCheckItem(scrnMsg.stkInDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_WO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_PM);
        // add start 2022/08/05 QC#59245
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CV);
        // add end 2022/08/05 QC#59245
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg  = (NFBL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).wrtOffDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).acrlWrtOffRsnCd_S);
            // START 2022/04/05 R.Onozuka [QC#57935, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
            // END 2022/04/05 R.Onozuka [QC#57935, ADD]
        }
        scrnMsg.putErrorScreen();
        
        /** Initialize button control */ 
        NFBL1130CommonLogic.initControl(this, scrnMsg);
        /** Set alternate rows background color */
        NFBL1130CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.poNum);
        // add start 2022/03/28 QC#57935(56588)
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // add end 2022/03/28 QC#57935(56588)

    }
}
