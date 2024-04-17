/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_OpenWin_PriceChanges
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/06   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_PriceChanges extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.B);
        scrnMsg.addCheckItem(scrnMsg.D);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        Object[] params = getArgForSubScreen(scrnMsg);

        setArgForSubScreen(params);
    }

    private Object[] getArgForSubScreen(NWAL1500BMsg scrnMsg) {

        List<Object> parameterList = new ArrayList<Object>();
        parameterList.add(scrnMsg.xxModeCd_N);
        parameterList.add(scrnMsg.trxHdrNum_N);
        parameterList.add(scrnMsg.prcBaseDt_N);
        parameterList.add(scrnMsg.prcCalcDt_N);
        parameterList.add(scrnMsg.dsOrdTpCd_N);
        parameterList.add(scrnMsg.dsOrdCatgCd_N);
        parameterList.add(scrnMsg.lineBizTpCd_N);
        parameterList.add(scrnMsg.cpoSrcTpCd_N);
        parameterList.add(scrnMsg.custIssPoNum_N);
        parameterList.add(scrnMsg.dsPmtMethCd_N);
        parameterList.add(scrnMsg.spclHdlgTpCd_N);
        parameterList.add(scrnMsg.leasePrchOptCd_N);
        parameterList.add(scrnMsg.dsOrdPosnNum_N);
        parameterList.add(scrnMsg.trxLineNum_N);
        parameterList.add(scrnMsg.trxLineSubNum_N);
        parameterList.add(scrnMsg.configCatgCd_N);
        parameterList.add(scrnMsg.inEachQty_N);
        parameterList.add(scrnMsg.shipToFirstLineAddr_N);
        parameterList.add(scrnMsg.shipToScdLineAddr_N);
        parameterList.add(scrnMsg.shipToCtyAddr_N);
        parameterList.add(scrnMsg.shipToStCd_N);
        parameterList.add(scrnMsg.shipToCntyNm_N);
        parameterList.add(scrnMsg.shipToPostCd_N);
        parameterList.add(scrnMsg.shipToCtryCd_N);
        parameterList.add(scrnMsg.prcCatgCd_N);
        parameterList.add(scrnMsg.csmpNum_N);
        parameterList.add(scrnMsg.dlrRefNum_N);
        parameterList.add(scrnMsg.prcContrNum_N);
        parameterList.add(scrnMsg.coaBrCd_N);
        parameterList.add(scrnMsg.mdseCd_N);
        parameterList.add(scrnMsg.billToCustCd_N);
        parameterList.add(scrnMsg.billToCustAcctCd_N);
        parameterList.add(scrnMsg.sellToCustCd_N);
        parameterList.add(scrnMsg.soldToCustLocCd_N);
        parameterList.add(scrnMsg.shipToCustCd_N);
        parameterList.add(scrnMsg.shipToCustAcctCd_N);
        parameterList.add(scrnMsg.frtCondCd_N);
        parameterList.add(scrnMsg.shpgSvcLvlCd_N);
        parameterList.add(scrnMsg.ccyCd_N);
        parameterList.add(scrnMsg.uomCd_N);
        parameterList.add(scrnMsg.ordCustUomQty_N);
        parameterList.add(scrnMsg.dsCpoLineCatgCd_N);
        parameterList.add(scrnMsg.invQty_N);
        parameterList.add(scrnMsg.mdlId_N);
        parameterList.add(scrnMsg.rtrnRsnCd_N);
        parameterList.add(scrnMsg.coaExtnCd_N);
        parameterList.add(scrnMsg.slsRepOrSlsTeamTocCd_N);
        parameterList.add(scrnMsg.rtlWhCd_N);
        parameterList.add(scrnMsg.xxTotBaseAmt_N);
        parameterList.add(scrnMsg.xxSubTotCalcPrcAmt_N);
        parameterList.add(scrnMsg.xxTotChrgPrcAmt_N);
        parameterList.add(scrnMsg.xxTotDiscAmt_N);
        parameterList.add(scrnMsg.xxTotSpclPrcAmt_N);
        parameterList.add(scrnMsg.xxTotNetDiscAmt_N);
        parameterList.add(scrnMsg.xxUnitNetPrcAmt_N);
        parameterList.add(scrnMsg.xxUnitGrsPrcAmt_N);
        parameterList.add(scrnMsg.xxTotNetPrcAmt_N);
        parameterList.add(scrnMsg.xxGrsAmt_N);
        parameterList.add(scrnMsg.xxUnitFrtAmt_N);
        parameterList.add(scrnMsg.xxTotFrtAmt_N);
        parameterList.add(scrnMsg.xxUnitSpclChrgAmt_N);
        parameterList.add(scrnMsg.xxTotSpclChrgAmt_N);
        parameterList.add(scrnMsg.xxTotFrtSubAmt_N);
        parameterList.add(scrnMsg.xxUnitRestkFeeAmt_N);
        parameterList.add(scrnMsg.xxTotRestkFeeAmt_N);
        parameterList.add(scrnMsg.xxTotNetPrcAmt_N2);
        parameterList.add(scrnMsg.xxTotTaxAmt_N);
        parameterList.add(scrnMsg.xxUnitPrc_N);
        parameterList.add(scrnMsg.xxTotAmt_N);
        parameterList.add(scrnMsg.dealPrcListPrcAmt_N);
        // 2015/11/24 S21_NA#1020 Add Start
        parameterList.add(scrnMsg.dsCpoLineNum_N);
        parameterList.add(scrnMsg.dsCpoLineSubNum_N);
        parameterList.add(scrnMsg.dealSvcRevTrnsfAmt_N);
        parameterList.add(scrnMsg.dealSvcCostTrnsfAmt_N);
        parameterList.add(scrnMsg.dealManFlAdjAmt_N);
        parameterList.add(scrnMsg.dealManRepRevAdjAmt_N);
        // 2015/11/24 S21_NA#1020 Add End
        scrnMsg.xxSfxKeyTxt_N.setValue("NL");
        parameterList.add(scrnMsg.xxSfxKeyTxt_N);
        parameterList.add(scrnMsg.N);
        return parameterList.toArray();
    }
}
