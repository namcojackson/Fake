/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_0;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_1;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_5;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         T.Murai         Create          N/A
 * 2016/05/13   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/30   Fujitsu         M.Yamada        Update          QC#10754
 *</pre>
 */
public class NWAL1840_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do noting
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }
        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_Category".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_Salesrep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            // S21_NA#7861 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepPsnCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            // S21_NA#7861 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd, scrnMsg.Y.no(IDX_5).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_FreightTerms".equals(scrEventNm) || "OnBlur_FreightTerms".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondDescTxt, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());

        } else if ("OpenWin_CarrierServiceLevel".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlCd, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlDescTxt, scrnMsg.Y.no(IDX_1).xxComnScrColValTxt.getValue());
            return null;

        } else if ("OpenWin_SubstituteItem".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).sbstMdseCd_A, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            return null;

        } else if ("OpenWin_PrcContrNum".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum, scrnMsg.Y.no(IDX_0).xxComnScrColValTxt.getValue());
            return null;
        }

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_CarrierServiceLevel".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.carrAcctNum);
            return;
        } else if ("OpenWin_SubstituteItem".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).schdAllwQty_A);
            return;
        } else if ("OpenWin_PrcContrNum".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prcContrNum);
            return;
        }

        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);

        } else if ("OpenWin_Salesrep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.frtCondDescTxt);

        } else if ("OpenWin_SubstituteItem".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).schdAllwQty_A);

        } else if ("OpenWin_FreightTerms".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
        }
        NWAL1840CommonLogic.setProtect(this, scrnMsg);

    }
}
