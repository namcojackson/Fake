/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010;

import static business.servlet.NWAL2010.constant.NWAL2010Constant.BIZ_ID;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.LINK_STRING_ACCT_NUM;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.NWAM0699E;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.NWZM0971E;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.NZZM0000E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2010.NWAL2010CMsg;
import business.servlet.NWAL2010.common.NWAL2010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2010_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/23   Fujitsu         R.Nakamura      Update          QC#13958
 * 2022/12/14   Hitachi         R.Takau         Update          QC#60823
 * 2024/04/10   CITS            M.Okamura       Update          QC#63757
 *</pre>
 */
public class NWAL2010_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        // Mod Start 2016/09/23 QC#13958
//        if (params != null && params.length == 13) {
        if (params != null && params.length >= 13) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.crCardTrxTpCd, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstTrxInfoTxt, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.scdTrxInfoTxt, (EZDBStringItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdTrxInfoTxt, (EZDBStringItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.frthTrxInfoTxt, (EZDBStringItem) params[5]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.fifthTrxInfoTxt, (EZDBStringItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstTrxInfoNum, (EZDBBigDecimalItem) params[7]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.scdTrxInfoNum, (EZDBBigDecimalItem) params[8]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdTrxInfoNum, (EZDBBigDecimalItem) params[9]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.frthTrxInfoNum, (EZDBBigDecimalItem) params[10]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.fifthTrxInfoNum, (EZDBBigDecimalItem) params[11]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCrCardPk, (EZDBBigDecimalItem) params[12]);

            ZYPEZDItemValueSetter.setValue(scrnMsg.acctCdFlg_LK, ZYPConstant.FLG_ON_Y);

            if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
                scrnMsg.setMessageInfo(NWAM0699E);
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.crCardTrxTpCd)) {
                scrnMsg.setMessageInfo(NWAM0699E);
            }

            if (params.length == 14) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_LK, (EZDBStringItem) params[13]);
            }
        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.acctCd_LK, LINK_STRING_ACCT_NUM);
        }
        // Mod End 2016/09/23 QC#13958

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.sellToCustCd.clear();
        }

        NWAL2010CMsg bizMsg = new NWAL2010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2010CommonLogic.initCmnBtnProp(this);
        // Start 2022/12/14 R.Takau [QC#60823,MOD]
        //NWAL2010CommonLogic.setCmnBtnProp(scrnMsg);
        NWAL2010CommonLogic.setCmnBtnProp(scrnMsg,this);
        // End 2022/12/14 R.Takau [QC#60823,MOD]

//        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)
                && !NZZM0000E.equals(scrnMsg.getMessageCode())
                && !NWZM0971E.equals(scrnMsg.getMessageCode())
            ) {
            NWAL2010CommonLogic.setErrorField(this, scrnMsg);
            return;
        }

        // Start 2024/04/10 M.Okamura [QC#63757,DEL]
        //NWAL2010CommonLogic.setCrCardProp(this, scrnMsg);
        // End 2024/04/10 M.Okamura [QC#63757,DEL]
        NWAL2010CommonLogic.setBgColor(scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            scrnMsg.setFocusItem(scrnMsg.sellToCustCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.crCardCustRefNum);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        scrnMsg.sellToCustCd.setNameForMessage("Account Code");
        scrnMsg.crCardValidFlg.setNameForMessage("Invalid");
        scrnMsg.crCardCustRefNum.setNameForMessage("Cust Ref Box");
        scrnMsg.crCardExprYrMth.setNameForMessage("Valid Thru");
        scrnMsg.crCardTpCd.setNameForMessage("CC Type");
        scrnMsg.crCardLastDigitNum.setNameForMessage("Last 4 digits");
    }
}
