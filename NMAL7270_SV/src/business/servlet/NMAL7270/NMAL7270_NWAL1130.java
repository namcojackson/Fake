/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#6530
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6924
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20729-2
 *</pre>
 */
public class NMAL7270_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        // Mod Start 2017/09/01 QC#20729-2
        if ("OpenWin_PrcRuleCondVal_Gen".equals(scrnMsg.xxScrEventNm.getValue())) {
            setRuleAttrbTrxCond(scrnMsg);
        } else if ("OpenWin_PrcFmla".equals(scrnMsg.xxScrEventNm.getValue())) {
            setRuleAttrbPrcAdjDtl(scrnMsg);
        } else if ("OpenWin_PrcRuleCondVal_Mdl".equals(scrnMsg.xxScrEventNm.getValue())) {
            setRuleAttrbTrxCond(scrnMsg);
        }
        // Mod End 2017/09/01 QC#20729-2

        scrnMsg.xxScrEventNm.clear();
    }

    private void setRuleAttrbTrxCond(NMAL7270BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        String prcRuleAttrbCd = scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue();

        // Mod Start 2017/09/01 QC#20729-2
        // Mod Start 2016/09/27 QC#6924
        if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd) // QC#6173 2016/04/14 Add
                || PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd) // QC#20249 2017/08/09 Add
                ) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxRecNmTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxRecNmTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxRecNmTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxRecNmTxt_A1, scrnMsg.R.no(6).xxComnScrColValTxt);
        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxRecNmTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        // Mod End 2016/09/27 QC#6924
        // Mod End 2017/09/01 QC#20729-2
    }

    private void setRuleAttrbPrcAdjDtl(NMAL7270BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (PRC_RULE_ATTRB.FORMULA.equals(scrnMsg.B.no(idx).prcRuleAttrbCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcFmlaPk_B1, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
            // QC#6530 2016/05/19 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcFmlaNm_B1,scrnMsg.R.no(2).xxComnScrColValTxt.getValue());
            // QC#6530 2016/05/19 Add End
        }
    }
}
