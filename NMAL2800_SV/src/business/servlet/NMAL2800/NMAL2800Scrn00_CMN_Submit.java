/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_CTY_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_FIRST_LINE_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_POST_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_TRTY_ORG_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FUNC_CD_UPD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.HYPHEN;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MESSAGE_KIND_WARN;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8075E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8229E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8348E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8608E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NZZM0002I;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.PERIOD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.PHONE_FORMAT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.PHONE_PATTERN;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SCRN_ID_00;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SLASH;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.XTRNL_PROS_RVW_CMNT_TXT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.servlet.NMAL2800.common.NMAL2800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROS_RVW_STS;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23145
 * 2018/03/29   Fujitsu         R.Nakamura      Update          QC#23141
 *</pre>
 */
public class NMAL2800Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL2800_ABMsg aBMsg = scrnMsg.A.no(i);
            EZDBStringItem prosRvwStsCd = aBMsg.prosRvwStsCd_A1;

            checkTelFormat(aBMsg);

            if (ZYPCommonFunc.hasValue(prosRvwStsCd)) {
                checkMandatory(aBMsg);
            }
        }

        addCheckItemAll(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        NMAL2800CMsg bizMsg = new NMAL2800CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItemAll(scrnMsg);
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2800CommonLogic.addCheckItem(scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) || MESSAGE_KIND_WARN.equals(bizMsg.getMessageKind())) {
            scrnMsg.setMessageInfo(NZZM0002I, null, 0);
        }

        NMAL2800CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A_Left");
        NMAL2800CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A_Right");
        NMAL2800CommonLogic.ctrlDtlFieldProp(scrnMsg, false);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxTpCd_H1);
    }

    private void checkMandatory(NMAL2800_ABMsg aBMsg) {

        if (PROS_RVW_STS.D.equals(aBMsg.prosRvwStsCd_A1.getValue())) {
            if (!ZYPCommonFunc.hasValue(aBMsg.aftLocNum_A1) && !ZYPCommonFunc.hasValue(aBMsg.aftDsXrefAcctCd_A1)) {
                aBMsg.aftLocNum_A1.setErrorInfo(1, NMAM8229E, new String[] {AFT_LOC_NUM, XTRNL_PROS_RVW_CMNT_TXT });
                aBMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, NMAM8229E, new String[] {AFT_LOC_NUM, XTRNL_PROS_RVW_CMNT_TXT });
            } else if (ZYPCommonFunc.hasValue(aBMsg.aftLocNum_A1) && ZYPCommonFunc.hasValue(aBMsg.aftDsXrefAcctCd_A1)) {
                aBMsg.aftLocNum_A1.setErrorInfo(1, NMAM8229E, new String[] {AFT_LOC_NUM, XTRNL_PROS_RVW_CMNT_TXT });
                aBMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, NMAM8229E, new String[] {AFT_LOC_NUM, XTRNL_PROS_RVW_CMNT_TXT });
            }
        } else if (PROS_RVW_STS.U.equals(aBMsg.prosRvwStsCd_A1.getValue())) {
            if (ZYPCommonFunc.hasValue(aBMsg.aftLocNum_A1)) {
                aBMsg.aftLocNum_A1.setErrorInfo(1, NMAM8608E, new String[] {AFT_LOC_NUM });
            }
            if (ZYPCommonFunc.hasValue(aBMsg.aftDsXrefAcctCd_A1)) {
                aBMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, NMAM8608E, new String[] {XTRNL_PROS_RVW_CMNT_TXT });
            }

            // Del Start 2018/03/29 QC#23141
//            if (!ZYPCommonFunc.hasValue(aBMsg.trtyOrgNm_A1)) {
//                aBMsg.trtyOrgNm_A1.setErrorInfo(1, ZZM9000E, new String[] {AFT_TRTY_ORG_CD });
//            }
            // Del End 2018/03/29 QC#23141
            if (!ZYPCommonFunc.hasValue(aBMsg.aftLocFirstLineAddr_A1)) {
                aBMsg.aftLocFirstLineAddr_A1.setErrorInfo(1, ZZM9000E, new String[] {AFT_LOC_FIRST_LINE_ADDR });
            }
            if (!ZYPCommonFunc.hasValue(aBMsg.aftLocCtyAddr_A1)) {
                aBMsg.aftLocCtyAddr_A1.setErrorInfo(1, ZZM9000E, new String[] {AFT_LOC_CTY_ADDR });
            }
            if (!ZYPCommonFunc.hasValue(aBMsg.aftLocPostCd_A1)) {
                aBMsg.aftLocPostCd_A1.setErrorInfo(1, ZZM9000E, new String[] {AFT_LOC_POST_CD });
            }
        } else if (PROS_RVW_STS.A.equals(aBMsg.prosRvwStsCd_A1.getValue())) {
            if (!ZYPCommonFunc.hasValue(aBMsg.trtyOrgNm_A1)) {
                aBMsg.trtyOrgNm_A1.setErrorInfo(1, ZZM9000E, new String[] {AFT_TRTY_ORG_CD });
            }
        }
    }

    private void checkTelFormat(NMAL2800_ABMsg aBMsg) {

        if (ZYPCommonFunc.hasValue(aBMsg.aftTelNum_A1)) {
            String telNum = aBMsg.aftTelNum_A1.getValue();
            if (telNum.length() < 7 || telNum.length() > 20) {
                aBMsg.aftTelNum_A1.setErrorInfo(1, NMAM8348E);
            } else {
                if (!telNum.matches(PHONE_PATTERN)) {
                    aBMsg.aftTelNum_A1.setErrorInfo(1, NMAM8075E, new String[] {PHONE_FORMAT });
                }

                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(aBMsg.aftTelNum_A1, telNum);
            }
        }
    }

    private void addCheckItemAll(NMAL2800BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL2800_ABMsg aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.prosRvwStsCd_A1);
            scrnMsg.addCheckItem(aBMsg.trtyOrgNm_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocFirstLineAddr_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocScdLineAddr_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocThirdLineAddr_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocFrthLineAddr_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocCtyAddr_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocStCd_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocPostCd_A1);
            scrnMsg.addCheckItem(aBMsg.cntyNm_A1);
            scrnMsg.addCheckItem(aBMsg.aftTelNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftFaxNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsAcctDunsNm_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsLocRevAmt_A1);
            scrnMsg.addCheckItem(aBMsg.aftDunsNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsCustSicDescTxt_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsLocEmpNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsCustSicCd_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsUltDunsNum_A1);
            // Del Start 2018/03/28 QC#23145
//            scrnMsg.addCheckItem(aBMsg.aftGlnNum_A1);
            // Del End 2018/03/28 QC#23145
            scrnMsg.addCheckItem(aBMsg.aftDsPrntDunsNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftHqDunsNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsCmpySicCd_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsCmpySicDescTxt_A1);
            scrnMsg.addCheckItem(aBMsg.aftLocNum_A1);
            scrnMsg.addCheckItem(aBMsg.aftDsXrefAcctCd_A1);
            scrnMsg.addCheckItem(aBMsg.dsAcctUrl_A1);
        }
        scrnMsg.putErrorScreen();
    }
}
