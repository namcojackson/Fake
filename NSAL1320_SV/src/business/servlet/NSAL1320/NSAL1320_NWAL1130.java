/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1320_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(getButtonSelectNumber()));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int ix = getButtonSelectNumber();
        String eventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_CmnBigShellItem".equals(eventNm) //
                || "OnBlur_DeriveFromSvcPgmCd".equals(eventNm) //
                || "OnBlur_DeriveFromSvcPgmNm".equals(eventNm)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.O.no(0).xxSelFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).svcPgmMdseCd, scrnMsg.O.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).mdseDescShortTxt, scrnMsg.O.no(1).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(ix).svcPgmMdseCd);

        } else if ("OpenWin_CmnBigAddToContract".equals(eventNm)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.O.no(1).xxSelFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).dsContrPk_AD, new BigDecimal(scrnMsg.O.no(0).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).dsContrNum_AD, scrnMsg.O.no(1).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(ix).dsContrNum_AD);

        } else if ("OpenWin_CmnBigDocNum".equals(eventNm)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.O.no(1).xxSelFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).cpoSvcAgmtVerNum, scrnMsg.O.no(1).xxComnScrColValTxt);
            } else {
                scrnMsg.A.no(ix).cpoSvcAgmtVerNum.clear();
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(ix).cpoSvcAgmtVerNum);
        // 2018/05/07 QC#22482 Add Start
        } else if ("OpenWin_Override_Reason".equals(eventNm)) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.O.no(1).xxSelFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).svcMemoRsnCd, scrnMsg.O.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(ix).svcMemoRsnDescTxt, scrnMsg.O.no(1).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(ix).svcCmntTxt);
        // 2018/05/07 QC#22482 Add End
        }
    }
}
