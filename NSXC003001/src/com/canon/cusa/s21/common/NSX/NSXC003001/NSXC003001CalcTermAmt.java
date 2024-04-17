package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSXC003001CalcTermAmt implements ZYPConstant {

    /** MODE01_CONTRACT */
    public static final String MODE01_CONTRACT = "01";

    /** MODE02_ANNUAL_ESCALATION */
    public static final String MODE02_ANNUAL_ESCALATION = "02";

    /**
     * @param inBean CalcTermAmtBean
     * @return BigDecimal
     */
    public static BigDecimal calcTermAmt(CalcTermAmtBean inBean) {

        CalcSchdSmryTermAndAmtBean inSmryBean = new CalcSchdSmryTermAndAmtBean();

        inSmryBean.setGlblCmpyCd(inBean.getGlblCmpyCd());
        inSmryBean.setBllgSchdFromDt(inBean.getContrPrcEffFromDt());
        inSmryBean.setBllgSchdThruDt(inBean.getContrPrcEffThruDt());
        inSmryBean.setBllgCycleCd(inBean.getBllgCycleCd());
        inSmryBean.setContrCloDay(inBean.getContrCloDay());
        inSmryBean.setBasePrcDealAmt(inBean.getBasePrcDealAmt());
        inSmryBean.setBasePrcTermDealAmtRate(inBean.getBasePrcTermDealAmtRate());
        inSmryBean.setBaseChrgFlg(FLG_ON_Y);
        inSmryBean.setUsgChrgFlg(FLG_OFF_N);
        inSmryBean.setCcyCd(inBean.getCcyCd());
        CalcSchdSmryTermAndAmtBean outSmryBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inSmryBean);

        BigDecimal calcPrcAmtRate = BigDecimal.ZERO;
        List<CalcSchdSmryTermAndAmtForBaseBean> baseList = outSmryBean.getBaseList();
        for (CalcSchdSmryTermAndAmtForBaseBean baseBean : baseList) {
            calcPrcAmtRate = calcPrcAmtRate.add(baseBean.getBaseSubTotPrcDealAmt());
        }
        return calcPrcAmtRate;
    }
}
