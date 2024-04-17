/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2260;

import static business.servlet.NWAL2260.constant.NWAL2260Constant.BIZ_ID;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_RST;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.PRM_LENGTH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2260.NWAL2260CMsg;
import business.servlet.NWAL2260.common.NWAL2260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma          Create          N/A
 *</pre>
 */
public class NWAL2260_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2260BMsg scrnMsg = (NWAL2260BMsg) bMsg;
        NWAL2260CMsg bizMsg = new NWAL2260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length < PRM_LENGTH) {
                scrnMsg.ordSrcRefNum.clear();
                scrnMsg.cpoSrcTpCd.clear();
                scrnMsg.dsImptOrdPk.clear();
                scrnMsg.xxReadOnlyFlg.clear();
                scrnMsg.dsImptOrdDtlPk.clear();
                scrnMsg.ordSrcRefLineNum.clear();
                scrnMsg.ordSrcRefLineSubNum.clear();
            } else {
                int i = 0;
                setValue(scrnMsg.ordSrcRefNum, (EZDBStringItem) params[i++]);
                setValue(scrnMsg.cpoSrcTpCd, (EZDBStringItem) params[i++]);
                setValue(scrnMsg.dsImptOrdPk, (EZDBBigDecimalItem) params[i++]);
                setValue(scrnMsg.xxReadOnlyFlg, (EZDBStringItem) params[i++]);
                setValue(scrnMsg.dsImptOrdDtlPk, (EZDBBigDecimalItem) params[i++]);
                setValue(scrnMsg.ordSrcRefLineNum, (EZDBStringItem) params[i++]);
                setValue(scrnMsg.ordSrcRefLineSubNum, (EZDBStringItem) params[i++]);
            }
        } else {
            scrnMsg.ordSrcRefNum.clear();
            scrnMsg.cpoSrcTpCd.clear();
            scrnMsg.dsImptOrdPk.clear();
            scrnMsg.xxReadOnlyFlg.clear();
            scrnMsg.dsImptOrdDtlPk.clear();
            scrnMsg.ordSrcRefLineNum.clear();
            scrnMsg.ordSrcRefLineSubNum.clear();
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2260BMsg scrnMsg = (NWAL2260BMsg) bMsg;
        NWAL2260CMsg bizMsg = (NWAL2260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setNameForMessageAttrbTxt(scrnMsg);
        NWAL2260CommonLogic.initialControlScreen(this, scrnMsg);

        if ("E".equals(bizMsg.getMessageKind())) {
            setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
            setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL2260BMsg scrnMsg = (NWAL2260BMsg) bMsg;

        scrnMsg.idocPoDocNum.setNameForMessage("IDOC PO Document Number");
        scrnMsg.idocPoOrgValTxt_01.setNameForMessage("IDOC PO Organization Value Text 01");
        scrnMsg.idocPoOrgValTxt_02.setNameForMessage("IDOC PO Organization Value Text 02");
        scrnMsg.idocPoOrdRsnCd.setNameForMessage("IDOC PO Order Reason Code");
        scrnMsg.idocPoCustRefNum.setNameForMessage("IDOC PO Customer Reference Number");
        scrnMsg.idocPoCustRefDt.setNameForMessage("IDOC PO Customer Reference Date");
        scrnMsg.idocPoPtnrTpCd_01.setNameForMessage("IDOC PO Partner Type Code 01");
        scrnMsg.idocPoPtnrTpCd_02.setNameForMessage("IDOC PO Partner Type Code 02");
        scrnMsg.idocPoPtnrTpCd_03.setNameForMessage("IDOC PO Partner Type Code 03");
        scrnMsg.idocPoPtnrTpCd_04.setNameForMessage("IDOC PO Partner Type Code 04");
        scrnMsg.idocPoPtnrTpCd_05.setNameForMessage("IDOC PO Partner Type Code 05");
        scrnMsg.idocPoPtnrTpCd_06.setNameForMessage("IDOC PO Partner Type Code 06");
        scrnMsg.idocPoPtnrNum_01.setNameForMessage("IDOC PO Partner Number 01");
        scrnMsg.idocPoPtnrNum_02.setNameForMessage("IDOC PO Partner Number 02");
        scrnMsg.idocPoPtnrNum_03.setNameForMessage("IDOC PO Partner Number 03");
        scrnMsg.idocPoPtnrNum_04.setNameForMessage("IDOC PO Partner Number 04");
        scrnMsg.idocPoPtnrNum_05.setNameForMessage("IDOC PO Partner Number 05");
        scrnMsg.idocPoPtnrNum_06.setNameForMessage("IDOC PO Partner Number 06");
        scrnMsg.idocPtnrCustRefTxt_01.setNameForMessage("IDOC Partner Customer Reference Text 01");
        scrnMsg.idocPtnrCustRefTxt_02.setNameForMessage("IDOC Partner Customer Reference Text 02");
        scrnMsg.idocPtnrCustRefTxt_03.setNameForMessage("IDOC Partner Customer Reference Text 03");
        scrnMsg.idocPtnrCustRefTxt_04.setNameForMessage("IDOC Partner Customer Reference Text 04");
        scrnMsg.idocPtnrCustRefTxt_05.setNameForMessage("IDOC Partner Customer Reference Text 05");
        scrnMsg.idocPtnrCustRefTxt_06.setNameForMessage("IDOC Partner Customer Reference Text 06");
        scrnMsg.idocPoPtnrId_01.setNameForMessage("IDOC PO Partner ID 01");
        scrnMsg.idocPoPtnrId_02.setNameForMessage("IDOC PO Partner ID 02");
        scrnMsg.idocPoPtnrId_03.setNameForMessage("IDOC PO Partner ID 03");
        scrnMsg.idocPoPtnrId_04.setNameForMessage("IDOC PO Partner ID 04");
        scrnMsg.idocPoPtnrId_05.setNameForMessage("IDOC PO Partner ID 05");
        scrnMsg.idocPoPtnrId_06.setNameForMessage("IDOC PO Partner ID 06");
        scrnMsg.idocPtnrCtacNm_01.setNameForMessage("IDOC Partner Contact Name 01");
        scrnMsg.idocPtnrCtacNm_02.setNameForMessage("IDOC Partner Contact Name 02");
        scrnMsg.idocPtnrCtacNm_03.setNameForMessage("IDOC Partner Contact Name 03");
        scrnMsg.idocPtnrCtacNm_04.setNameForMessage("IDOC Partner Contact Name 04");
        scrnMsg.idocPtnrCtacNm_05.setNameForMessage("IDOC Partner Contact Name 05");
        scrnMsg.idocPtnrCtacNm_06.setNameForMessage("IDOC Partner Contact Name 06");
        scrnMsg.idocPtnrTelNum_01.setNameForMessage("IDOC Partner Telephone Number 01");
        scrnMsg.idocPtnrTelNum_02.setNameForMessage("IDOC Partner Telephone Number 02");
        scrnMsg.idocPtnrTelNum_03.setNameForMessage("IDOC Partner Telephone Number 03");
        scrnMsg.idocPtnrTelNum_04.setNameForMessage("IDOC Partner Telephone Number 04");
        scrnMsg.idocPtnrTelNum_05.setNameForMessage("IDOC Partner Telephone Number 05");
        scrnMsg.idocPtnrTelNum_06.setNameForMessage("IDOC Partner Telephone Number 06");
        scrnMsg.idocFirstLineAddr.setNameForMessage("IDOC First Line Address");
        scrnMsg.idocScdLineAddr.setNameForMessage("IDOC Second Line Address");
        scrnMsg.idocPtnrCtyNm.setNameForMessage("IDOC Partner City Name");
        scrnMsg.idocPtnrStCd.setNameForMessage("IDOC Partner State Code");
        scrnMsg.idocPtnrPostCd.setNameForMessage("IDOC Partner Postal Code");
        scrnMsg.idocPtnrCtryCd.setNameForMessage("IDOC Partner Country Code");
        scrnMsg.idocPoDtValTxt.setNameForMessage("IDOC PO Date Value Text");
        scrnMsg.idocPoDelyCondCd.setNameForMessage("IDOC PO Delivery Condition Code");
        scrnMsg.idocPoDelyCondNm.setNameForMessage("IDOC PO Delivery Condition Name");
        scrnMsg.idocPoNoteTxt.setNameForMessage("IDOC PO Note Text");
        scrnMsg.idocPoRcpntPtnrNum.setNameForMessage("IDOC PO Recipient Partner Number");
        scrnMsg.idocPoDtlLineRefNum.setNameForMessage("IDOC PO Detail Line Reference Number");
        scrnMsg.idocPoDtlMatNum_01.setNameForMessage("IDOC PO Detail Material Number 01");
        scrnMsg.idocPoDtlMatNum_02.setNameForMessage("IDOC PO Detail Material Number 02");
        scrnMsg.idocPoDtlOrdQty.setNameForMessage("IDOC PO Detail Order Quantity");
        scrnMsg.idocPoDtlUomCd.setNameForMessage("IDOC PO Detail UOM Code");
        scrnMsg.idocPoDtlDelyPrtyNm.setNameForMessage("IDOC PO Detail Delivery Priority Name");
    }

    private void setNameForMessageAttrbTxt(NWAL2260BMsg scrnMsg) {

        scrnMsg.dsImptAttrbTxt_01.setNameForMessage(scrnMsg.scrLbNm_01.getValue());
        scrnMsg.dsImptAttrbTxt_02.setNameForMessage(scrnMsg.scrLbNm_02.getValue());
        scrnMsg.dsImptAttrbTxt_03.setNameForMessage(scrnMsg.scrLbNm_03.getValue());
        scrnMsg.dsImptAttrbTxt_04.setNameForMessage(scrnMsg.scrLbNm_04.getValue());
        scrnMsg.dsImptAttrbTxt_05.setNameForMessage(scrnMsg.scrLbNm_05.getValue());
        scrnMsg.dsImptAttrbTxt_06.setNameForMessage(scrnMsg.scrLbNm_06.getValue());
        scrnMsg.dsImptAttrbTxt_07.setNameForMessage(scrnMsg.scrLbNm_07.getValue());
        scrnMsg.dsImptAttrbTxt_08.setNameForMessage(scrnMsg.scrLbNm_08.getValue());
        scrnMsg.dsImptAttrbTxt_09.setNameForMessage(scrnMsg.scrLbNm_09.getValue());
        scrnMsg.dsImptAttrbTxt_10.setNameForMessage(scrnMsg.scrLbNm_10.getValue());
        scrnMsg.dsImptAttrbTxt_11.setNameForMessage(scrnMsg.scrLbNm_11.getValue());
        scrnMsg.dsImptAttrbTxt_12.setNameForMessage(scrnMsg.scrLbNm_12.getValue());
        scrnMsg.dsImptAttrbTxt_13.setNameForMessage(scrnMsg.scrLbNm_13.getValue());
        scrnMsg.dsImptAttrbTxt_14.setNameForMessage(scrnMsg.scrLbNm_14.getValue());
        scrnMsg.dsImptAttrbTxt_15.setNameForMessage(scrnMsg.scrLbNm_15.getValue());

        scrnMsg.dsImptDtlAttrbTxt_01.setNameForMessage(scrnMsg.scrLbNm_01.getValue());
        scrnMsg.dsImptDtlAttrbTxt_02.setNameForMessage(scrnMsg.scrLbNm_02.getValue());
        scrnMsg.dsImptDtlAttrbTxt_03.setNameForMessage(scrnMsg.scrLbNm_03.getValue());
        scrnMsg.dsImptDtlAttrbTxt_04.setNameForMessage(scrnMsg.scrLbNm_04.getValue());
        scrnMsg.dsImptDtlAttrbTxt_05.setNameForMessage(scrnMsg.scrLbNm_05.getValue());
        scrnMsg.dsImptDtlAttrbTxt_06.setNameForMessage(scrnMsg.scrLbNm_06.getValue());
        scrnMsg.dsImptDtlAttrbTxt_07.setNameForMessage(scrnMsg.scrLbNm_07.getValue());
        scrnMsg.dsImptDtlAttrbTxt_08.setNameForMessage(scrnMsg.scrLbNm_08.getValue());
        scrnMsg.dsImptDtlAttrbTxt_09.setNameForMessage(scrnMsg.scrLbNm_09.getValue());
        scrnMsg.dsImptDtlAttrbTxt_10.setNameForMessage(scrnMsg.scrLbNm_10.getValue());
        scrnMsg.dsImptDtlAttrbTxt_11.setNameForMessage(scrnMsg.scrLbNm_11.getValue());
        scrnMsg.dsImptDtlAttrbTxt_12.setNameForMessage(scrnMsg.scrLbNm_12.getValue());
        scrnMsg.dsImptDtlAttrbTxt_13.setNameForMessage(scrnMsg.scrLbNm_13.getValue());
        scrnMsg.dsImptDtlAttrbTxt_14.setNameForMessage(scrnMsg.scrLbNm_14.getValue());
        scrnMsg.dsImptDtlAttrbTxt_15.setNameForMessage(scrnMsg.scrLbNm_15.getValue());
    }
}
