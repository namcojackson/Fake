/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import static business.servlet.NMAL6760.constant.NMAL6760Constant.BIZ_ID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6760.NMAL6760CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6760Scrn00_SelectAcct
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/02   SRAA            Y.Chen          Update          QC#4389
 * 2018/06/18   Fujitsu         T.Noguchi       Update          QC#14307
 *</pre>
 */
public class NMAL6760Scrn00_SelectAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        NMAL6760CMsg bizMsg = new NMAL6760CMsg();

        // 2018/06/18 QC#14307 Add Start
        int no = getButtonSelectNumber();
        scrnMsg.xxCellIdx_01.setValue(no);
        // 2018/06/18 QC#14307 Add End

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();

        // 2018/06/18 QC#14307 Add Start
        setResult(ZYPConstant.FLG_OFF_N);

        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_SP) || 
            ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SP) ||
            ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SP)) {
            setResult(ZYPConstant.FLG_ON_Y);
            setArgForSubScreen(getArgForSubScreen(scrnMsg));
        } else {
        // 2018/06/18 QC#14307 Add End
            Serializable arg = getArgForSubScreen();

            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;

                if (params.length >= 1) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.C.no(index).dsAcctNum_C1);
                }
                if (params.length >= 2) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.C.no(index).dsAcctNm_C1);
                }
                if (params.length >= 3) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.C.no(index).locNum_C1);
                }
                if (params.length >= 4) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.C.no(index).dsLocNm_C1);
                }
                if (params.length >= 5) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[4], scrnMsg.C.no(index).firstLineAddr_C1);
                }
                if (params.length >= 6) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], scrnMsg.C.no(index).ctyAddr_C1);
                }
                if (params.length >= 7) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.C.no(index).stCd_C1);
                }
                if (params.length >= 8) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[7], scrnMsg.C.no(index).postCd_C1);
                }
                if (params.length >= 9) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[8], bizMsg.xxAcctSrchDplyRelnCd_D4);
                }
                if (params.length >= 10) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], bizMsg.xxAcctSrchModeCd_D1);
                }
                if (params.length >= 11) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[10], bizMsg.xxChkBox_01);
                }
                if (params.length >= 12) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[11], bizMsg.xxAcctSrchStsCd_D2);
                }
                if (params.length >= 13) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[12], bizMsg.xxAcctSrchDplyHrchCd_D3);
                }
                if (params.length >= 14) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[13], scrnMsg.C.no(index).dsAcctTpNm_C1);
                }
                if (params.length >= 15) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[14], scrnMsg.C.no(index).xxCtlNm_C1);
                }
                if (params.length >= 16) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[15], scrnMsg.C.no(index).billToCustCd_C1);
                }
                if (params.length >= 17) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[16], scrnMsg.C.no(index).shipToCustCd_C1);
                }
                if (params.length >= 18) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[17], scrnMsg.C.no(index).scdLineAddr_C1);
                }
                if (params.length >= 19) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[18], scrnMsg.C.no(index).thirdLineAddr_C1);
                }
                if (params.length >= 20) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[19], scrnMsg.C.no(index).frthLineAddr_C1);
                }
                if (params.length >= 21) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[20], scrnMsg.C.no(index).cntyNm_C1);
                }
                if (params.length >= 22) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[21], scrnMsg.C.no(index).provNm_C1);
                }
                if (params.length >= 23) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[22], scrnMsg.C.no(index).ctryCd_C1);
                }
                if (params.length >= 24) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[23], scrnMsg.C.no(index).relnDsAcctNum_C1);
                }

                if (params.length >= 32) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[31], scrnMsg.C.no(index).firstRefCmntTxt_C1);
                }
                if (params.length >= 33) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[32], scrnMsg.C.no(index).scdRefCmntTxt_C1);
                }
            }
        // 2018/06/18 QC#14307 Add Start
        }
        // 2018/06/18 QC#14307 Add End
    }

    // 2018/06/18 QC#14307 Add Start
    private Serializable getArgForSubScreen(NMAL6760BMsg scrnMsg) {
        List<Object> parmeters = new ArrayList<Object>();

        // [0] : Global Company Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, getGlobalCompanyCode());
        parmeters.add(scrnMsg.P.no(0).xxPopPrm);

        // [1] : Function ID
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.funcMstrId_SP);
        parmeters.add(scrnMsg.P.no(1).xxPopPrm);

        // [2] : Function Category ID
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.funcMstrIdDescTxt_SP);
        parmeters.add(scrnMsg.P.no(2).xxPopPrm);

        // [3] : Transaction Type
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.dsTrxRuleTpCd_SP);
        parmeters.add(scrnMsg.P.no(3).xxPopPrm);

        // [4] : Business Area
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, scrnMsg.dsBizAreaCd_SP);
        parmeters.add(scrnMsg.P.no(4).xxPopPrm);

        // [5] : Customer Special Instruction Line Suffix
        parmeters.add("QL");

        // [6] : Customer Special Instruction Line
        int i = 0;

        scrnMsg.Q.clear();

        // Acct#
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).dsAcctNum_QL, scrnMsg.dsAcctNum_SP);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).billToCustCd_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i++).shipToCustCd_QL, "");
        }

        // Bill To
        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).dsAcctNum_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).billToCustCd_QL, scrnMsg.billToCustCd_SP);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i++).shipToCustCd_QL, "");
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).dsAcctNum_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).billToCustCd_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i++).shipToCustCd_QL, scrnMsg.shipToCustCd_SP);
        }

        scrnMsg.Q.setValidCount(i);
        parmeters.add(scrnMsg.Q);

        // 2018/07/10 QC#26661 Add Start
        // [7] : Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.lineBizTpCd_SP);
        parmeters.add(scrnMsg.P.no(5).xxPopPrm);
        // 2018/07/10 QC#26661 Add End

        return parmeters.toArray(new Object[0]);
    }
    // 2018/06/18 QC#14307 Add End
}