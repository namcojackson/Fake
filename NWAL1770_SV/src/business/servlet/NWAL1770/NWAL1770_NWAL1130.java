/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_0;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_1;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_5;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/31   Fujitsu         T.Murai         Update          S21_NA#14020
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * </pre>
 */
public class NWAL1770_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyQuoteCatgCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_SalesRep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            // S21_NA#7861 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepPsnCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            // S21_NA#7861 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd, scrnMsg.Y.no(IDX_5).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_FreightTerms".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondDescTxt, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_CarrierServiceLevel".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlDescTxt, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
            return null;
        } else if ("OpenWin_Warehouse".equals(scrEventNm)) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhCd_B, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhNm_B, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
            // Del 2016/08/31 S21_NA#14020
            // ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, scrnMsg.Y.no(IDX_2).xxComnScrColValTxt.getValue());
            // ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhNm_B, scrnMsg.Y.no(IDX_3).xxComnScrColValTxt.getValue());

            return null;
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm)) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhNm_B, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
            return null;
        } else if ("OpenWin_AssnProgram".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            return null;
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
            NWAL1770CommonLogic.checkItemLineWarningOnlyBMsg(scrnMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            return;
        }

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_CarrierServiceLevel".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.carrAcctNum);
            return;
        } else if ("OpenWin_Warehouse".equals(scrEventNm)) {
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            NWAL1770CommonLogic.checkItemLineWarningOnlyBMsg(scrnMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhNm_B);
            return;
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm)) {
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            NWAL1770CommonLogic.checkItemLineWarningOnlyBMsg(scrnMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhNm_B);
            return;
        } else if ("OpenWin_AssnProgram".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prcContrNum);
            return;
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
        } else if ("OpenWin_SalesRep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
        } else if ("OpenWin_FreightTerms".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
            NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        }
    }
}
