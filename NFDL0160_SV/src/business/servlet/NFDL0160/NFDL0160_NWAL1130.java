/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.BIZ_ID;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_0;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_1;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_2;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_3;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0160.NFDL0160CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/06/03   Fujitsu         S.Yoshida       Update          CSA QC#8887
 *</pre>
 */
public class NFDL0160_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_Clt".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltPsnCd_A, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltPsnNm_A, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltStmtPhoNum_A, scrnMsg.Z.no(IDX_2).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltStmtFaxNum_A, scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_AR".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).arAdjTpCd_A, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).arAdjTpDescTxt_A, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("CR_Equip".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltCrAnlstEquipPsnCd_A, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltCrAnlstEquipPsnNm_A, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("CR_Svc".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltCrAnlstSvcPsnCd_A, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltCrAnlstSvcPsnNm_A, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("CR_Sply".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltCrAnlstSplyPsnCd_A, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltCrAnlstSplyPsnNm_A, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_NextLvl".equals(scrEventNm)) {
            BigDecimal cltPtfoPk = new BigDecimal(scrnMsg.Z.no(IDX_2).xxComnScrColValTxt.getValue());
            String cltPtfoNm = scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).relCltPtfoPk_A, cltPtfoPk);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltPtfoNm_AR, cltPtfoNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).cltPtfoNm_BK, cltPtfoNm);
        }

        NFDL0160CMsg bizMsg = new NFDL0160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        NFDL0160CMsg bizMsg = (NFDL0160CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_Clt".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).cltPsnNm_A);
        } else if ("OpenWin_AR".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).arAdjTpDescTxt_A);
        } else if ("CR_Equip".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).cltCrAnlstEquipPsnNm_A);
        } else if ("CR_Svc".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).cltCrAnlstSvcPsnNm_A);
        } else if ("CR_Sply".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).cltCrAnlstSplyPsnNm_A);
        } else if ("OpenWin_NextLvl".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).cltPtfoNm_AR);
        }
    }
}
