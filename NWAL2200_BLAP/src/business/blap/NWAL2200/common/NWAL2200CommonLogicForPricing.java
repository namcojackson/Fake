/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.blap.NWAL2200.NWAL2200Query;
import business.blap.NWAL2200.NWAL2200_ACMsg;
import business.blap.NWAL2200.NWAL2200_BCMsg;
import business.blap.NWAL2200.NWAL2200_BSMsg;
import business.blap.NWAL2200.NWAL2200_CCMsg;
import business.blap.NWAL2200.NWAL2200_DCMsg;
import business.blap.NWAL2200.NWAL2200_ICMsg;
import business.blap.NWAL2200.NWAL2200_ISMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.SPEC_COND_PRCTMsg;
import business.db.SPEC_COND_PRCTMsgArray;
import business.db.STTMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import static business.blap.NWAL2200.constant.NWAL2200Constant.BLANK;

/**
 *<pre>
 * NWAL2200CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/07/15   Fujitsu         T.Ishii         Create          S21_NA#11435
 * 2016/07/15   Fujitsu         T.Ishii         Update          S21_NA#11561
 * 2016/09/14   Fujitsu         S.Ohki          Update          S21_NA#14393
 * 2016/10/19   Fujitsu         T.Ishii         Update          S21_NA#13769
 * 2018/01/23   Fujitsu         T.Aoi           Update          S21_NA#18798(Sol#173)
 * 2018/01/25   Fujitsu         Y.Kanefusa      Update          S21_NA#19808
 *</pre>
 */
public class NWAL2200CommonLogicForPricing {

    /**
     * pricing
     * @param bizMsg NWAL2200CMsg
     * @param config NWAL2200_ACMsg
     * @param line NWAL2200_BCMsg
     * @param priceCalcBaseList List<NWAL2200_ICMsg>
     * @param pricingMode String
     */
    public static void pricingForEdi(NWAL2200CMsg bizMsg, NWAL2200_ACMsg config, NWAL2200_BSMsg line, List<NWAL2200_ISMsg> priceCalcBaseList, String pricingMode) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, pricingMode);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.trxHdrNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, bizMsg.negoDealAmt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        // 2018/01/23 S21_NA#18798 Mod Start
        //ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_LD.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot_EM);
        }
        // 2018/01/23 S21_NA#18798 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // line
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).trxLineNum, line.trxLineNum_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).trxLineSubNum, line.trxLineSubNum_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).billToCustCd, config.billToCustLocCd_LC);
        // 09/14/2016 S21_NA#14393 del Start
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).shipToCustCd,
        // config.shipToCustLocCd_LC);
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).dsAcctNum_SH,
        // config.shipToCustAcctCd_LC);
        // 09/14/2016 S21_NA#14393 del End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).dsAcctNum_BL, config.billToCustAcctCd_LC);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).prcCatgCd, line.prcCatgCd_LL);
        if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum) && !ZYPCommonFunc.hasValue(line.csmpContrNum_LL)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).csmpNum, bizMsg.csmpContrNum);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).csmpNum, line.csmpContrNum_LL);
        }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).dlrRefNum, line.dlrRefNum_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).coaBrCd, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).ccyCd, line.ccyCd_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).prcListEquipConfigNum, line.prcListEquipConfigNum_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).mdseCd, line.mdseCd_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).pkgUomCd, line.custUomCd_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).dsOrdLineCatgCd, line.dsOrdLineCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).ordQty, line.ordQty_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).ordCustUomQty, line.ordCustUomQty_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).mdlId, config.mdlId_LC);

        GLBL_CMPYTMsg glblCmpy = new GLBL_CMPYTMsg();
        glblCmpy.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        glblCmpy = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblCmpy);
         if (glblCmpy.ctryCd.getValue().equals(config.shipToCtryCd_LC.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).ctyAddr_SH, config.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).stCd_SH, config.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).postCd_SH, config.shipToPostCd_LC);
        }

        setShipToCustInfo(bizMsg, prcApiPMsg.NWZC157002PMsg.no(0), config.shipToCustLocCd_LC.getValue(), glblCmpy.ctryCd.getValue());
        // 09/14/2016 S21_NA#14393 mod End

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).slsRepOrSlsTeamTocCd, line.slsRepOrSlsTeamTocCd_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).rtlWhCd, line.rtlWhCd_LL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).coaExtnCd, bizMsg.coaExtnCd);
        prcApiPMsg.NWZC157002PMsg.setValidCount(1);

        // price calculation base
        BigDecimal specCondPrcPk = getSpecCondPrcPk(bizMsg.glblCmpyCd.getValue(), PRC_COND_TP.BASE_PRICE);
        int priceCount = 0;
        for (NWAL2200_ISMsg priceCalcBase : priceCalcBaseList) {

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).trxLineNum, priceCalcBase.trxLineNum_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).trxLineSubNum, priceCalcBase.trxLineSubNum_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).configCatgCd, priceCalcBase.configCatgCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCondTpCd, priceCalcBase.prcCondTpCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCondTpDescTxt, priceCalcBase.prcCondTpDescTxt_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcDtlGrpCd, priceCalcBase.prcDtlGrpCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcJrnlGrpCd, priceCalcBase.prcJrnlGrpCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCatgCd, line.prcCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCondManAddFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).pkgUomCd, priceCalcBase.pkgUomCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCondUnitCd, priceCalcBase.prcCondUnitCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).prcCalcMethCd, priceCalcBase.prcCalcMethCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).autoPrcCcyCd, priceCalcBase.autoPrcCcyCd_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).autoPrcAmtRate, priceCalcBase.autoPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).maxPrcAmtRate, priceCalcBase.maxPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).minPrcAmtRate, priceCalcBase.minPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).manPrcAmtRate, priceCalcBase.manPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).calcPrcAmtRate, priceCalcBase.calcPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).unitPrcAmt, priceCalcBase.unitPrcAmt_LP);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).dsMdsePrcPk, priceCalcBase.dsMdsePrcPk_LP);
            if (ZYPCommonFunc.hasValue(priceCalcBase.specCondPrcPk_LP)) {

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).specCondPrcPk, priceCalcBase.specCondPrcPk_LP);
            } else {

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).specCondPrcPk, specCondPrcPk);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(priceCount).frtPerWtPk, priceCalcBase.frtPerWtPk_LP);

            priceCount++;
        }
    }

    // S21_NA#11435 add start
    /**
     * <pre>
     * Call Pricing API Mode:04 Get Order All.
     * If error message will obtain, this method set error message on Biz Message.
     * </pre>
     * @param bizMsg Business Message
     * @return pricing API result PMSG
     */
    public static NWZC157001PMsg callPricingApiGetOrderAllMode(NWAL2200CMsg bizMsg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // header
        setHdrParam(bizMsg, prcApiPMsg);

        // out bound
        setOutboundLineParam(bizMsg, prcApiPMsg);

        // in bound
        setInboundLineParam(bizMsg, prcApiPMsg);

        // call pricing!
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * setHdrParam
     * @param bizMsg NWAL2200CMsg
     * @param prcApiPMsg NWZC157001PMsg
     */
    public static void setHdrParam(NWAL2200CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_ORDER_ALL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        prcApiPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.trxHdrNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, bizMsg.negoDealAmt);
        // S21_NA#13769 modify start
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        // prcApiPMsg.taxCalcFlg.setValue(ZYPConstant.FLG_ON_Y);
        // S21_NA#13769 modify end
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        // 2018/01/23 S21_NA#18798 Mod Start
        //ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_LD.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot_EM);
        }
        // 2018/01/23 S21_NA#18798 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * setOutboundLineParam
     * @param bizMsg NWAL2200CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param lineClose String
     * @param prcCondTpMap Map<String, String>
     */
    public static void setOutboundLineParam(NWAL2200CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        // line
        int priceLineIndex = prcApiPMsg.NWZC157002PMsg.getValidCount();

        for (int lineIndex = 0; lineIndex < bizMsg.B.getValidCount() && priceLineIndex < prcApiPMsg.NWZC157002PMsg.length(); lineIndex++) {

            NWAL2200_BCMsg line = bizMsg.B.no(lineIndex);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end

            NWAL2200_ACMsg config = NWAL2200CommonLogicForLineControl.getConfig(bizMsg.A, line.dsOrdPosnNum_LL.getValue());
            if (config == null) {

                continue;
            }

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsOrdPosnNum, line.dsOrdPosnNum_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).trxLineNum, line.trxLineNum_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).trxLineSubNum, line.trxLineSubNum_LL);
            prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).configCatgCd.setValue(CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).billToCustCd, config.billToCustLocCd_LC);
            // 09/16/2016 S21_NA#14393 del Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).shipToCustCd,
            // config.shipToCustLocCd_LC);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsAcctNum_SH,
            // config.shipToCustAcctCd_LC);
            // 09/16/2016 S21_NA#14393 del End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsAcctNum_BL, config.billToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).prcCatgCd, line.prcCatgCd_LL);
            if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum) && !ZYPCommonFunc.hasValue(line.csmpContrNum_LL)) {

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).csmpNum, bizMsg.csmpContrNum);
            } else {

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).csmpNum, line.csmpContrNum_LL);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dlrRefNum, line.dlrRefNum_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).coaBrCd, bizMsg.coaBrCd);
            if (ZYPCommonFunc.hasValue(line.prcCatgNm_LL)) {

                // Sell Price Category
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ccyCd, line.ccyCd_LL);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).prcListEquipConfigNum, line.prcListEquipConfigNum_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).mdseCd, line.mdseCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).pkgUomCd, line.custUomCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsOrdLineCatgCd, line.dsOrdLineCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ordQty, line.ordQty_LL); // Nead
            // confirmation!!!!
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ordCustUomQty, line.ordCustUomQty_LL); // Nead
            // confirmation!!!
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).invQty, BigDecimal.ZERO);
            prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).mdlId.clear();

            GLBL_CMPYTMsg glblCmpy = new GLBL_CMPYTMsg();
            glblCmpy.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            glblCmpy = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblCmpy);
            // 09/16/2016 S21_NA#14393 mod Start
            // if
            // (glblCmpy.ctryCd.getValue().equals(line.shipToCtryCd_LL.getValue()))
            // {
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ctyAddr_SH,
            // line.shipToCtyAddr_LL);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).stCd_SH,
            // line.shipToStCd_LL);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).postCd_SH,
            // line.shipToPostCd_LL);
            // }

            setShipToCustInfo(bizMsg, prcApiPMsg.NWZC157002PMsg.no(priceLineIndex), config.shipToCustLocCd_LC.getValue(), glblCmpy.ctryCd.getValue());
            // 09/16/2016 S21_NA#14393 mod End

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).slsRepOrSlsTeamTocCd, line.slsRepOrSlsTeamTocCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).rtlWhCd, line.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).coaExtnCd, bizMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).xxUnitPrc, line.cpoDtlDealSlsAmt_LL);

            priceLineIndex++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(priceLineIndex);
    }

    private static void setInboundLineParam(NWAL2200CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        // RMA
        int priceLineIndex = prcApiPMsg.NWZC157002PMsg.getValidCount();

        for (int lineIndex = 0; lineIndex < bizMsg.D.getValidCount() && priceLineIndex < prcApiPMsg.NWZC157002PMsg.length(); lineIndex++) {

            NWAL2200_DCMsg line = bizMsg.D.no(lineIndex);

            NWAL2200_CCMsg config = NWAL2200CommonLogicForLineControl.getConfig(bizMsg.C, line.dsOrdPosnNum_RL.getValue());
            if (config == null) {

                continue;
            }

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsOrdPosnNum, config.dsOrdPosnNum_RC); // QC#6432
            // 2016/04/07
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).trxLineNum, line.trxLineNum_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).trxLineSubNum, line.trxLineSubNum_RL);
            prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).configCatgCd.setValue(CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).billToCustCd, config.billToCustLocCd_RC);
            // 09/16/2016 S21_NA#14393 del Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).shipToCustCd,
            // config.shipToCustLocCd_RC);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsAcctNum_SH,
            // config.shipToCustAcctCd_RC);
            // 09/16/2016 S21_NA#14393 del Start
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsAcctNum_BL, config.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).prcCatgCd, line.prcCatgCd_RL);
            if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum) && !ZYPCommonFunc.hasValue(line.csmpContrNum_RL)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).csmpNum, bizMsg.csmpContrNum);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).csmpNum, line.csmpContrNum_RL);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dlrRefNum, line.dlrRefNum_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ccyCd, line.ccyCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).mdseCd, line.mdseCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).pkgUomCd, line.custUomCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).dsOrdLineCatgCd, line.dsCpoLineCatgCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ordQty, line.ordQty_RL); // Nead
            // confirmation!!!!
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ordCustUomQty, line.ordCustUomQty_RL); // Nead
            // confirmation!!!
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).rtrnRsnCd, line.rtrnRsnCd_RL);
            prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).mdlId.clear();
            // 09/16/2016 S21_NA#14393 mod Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).ctyAddr_SH,
            // config.shipToCtyAddr_RC);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).stCd_SH,
            // config.shipToStCd_RC);

            setShipToCustInfo(bizMsg, prcApiPMsg.NWZC157002PMsg.no(priceLineIndex), config.shipToCustLocCd_RC.getValue(), BLANK);
            // 09/16/2016 S21_NA#14393 mod End

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).slsRepOrSlsTeamTocCd, line.slsRepOrSlsTeamTocCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).rtlWhCd, line.rtlWhCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).coaExtnCd, bizMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(priceLineIndex).xxUnitPrc, line.cpoDtlDealSlsAmt_RL);

            priceLineIndex++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(priceLineIndex);
    }

    // S21_NA#11435 add end

    private static BigDecimal getSpecCondPrcPk(String glblCmpyCd, String prcCondTpCd) {
        SPEC_COND_PRCTMsg inTMsg = new SPEC_COND_PRCTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("prcCondTpCd01", prcCondTpCd);
        SPEC_COND_PRCTMsgArray array = (SPEC_COND_PRCTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return BigDecimal.ZERO;
        }
        return array.no(0).specCondPrcPk.getValue();
    }

    // 09/16/2016 S21_NA#14393 ADD START
    private static void setShipToCustInfo(NWAL2200CMsg bizMsg, NWZC157002PMsg nwzc157002PMsg, String confShipToCustCd, String glblCmpyCtyrCd) {

        String shipToCustCd = BLANK;
        if (ZYPCommonFunc.hasValue(confShipToCustCd)) {
            shipToCustCd = confShipToCustCd;
        } else {
            shipToCustCd = bizMsg.shipToCustCd.getValue();
        }

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getShipToCustInfoList(bizMsg, shipToCustCd);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();

        ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_SH, shipToCustInfoList.get(0).get("DS_ACCT_NUM"));

        String ctryCd = getCtryCd(bizMsg, shipToCustInfoList.get(0).get("ST_CD"));
        if (ctryCd != null) {
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctryCd_SH, ctryCd);
        } else {
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctryCd_SH, BLANK);
        }

        if (!ZYPCommonFunc.hasValue(glblCmpyCtyrCd) || glblCmpyCtyrCd.equals(ctryCd)) {
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctyAddr_SH, shipToCustInfoList.get(0).get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.stCd_SH, shipToCustInfoList.get(0).get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.postCd_SH, shipToCustInfoList.get(0).get("POST_CD"));
        }
    }

    private static String getCtryCd(NWAL2200CMsg bizMsg, String stCd) {

        String ctryCd = null;

        STTMsg stMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(stMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(stMsg.stCd, stCd);

        stMsg = (STTMsg) EZDTBLAccessor.findByKey(stMsg);

        if (stMsg != null) {
            ctryCd = stMsg.ctryCd.getValue();
        }

        return ctryCd;
    }
    // 09/16/2016 S21_NA#14393 MOD END

}
