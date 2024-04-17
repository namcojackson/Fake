package com.canon.cusa.s21.batch.NWB.NWBB400001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040004PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC040007PMsg;

/** <pre>
 * invoice Import API Param Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2015   Fujitsu         M.Hara          Create          N/A
 * 05/09/2016   Fujitsu         H.Nagashima     Update          QC#7754
 * 12/14/2018   Fujitsu         H.Nagashima     Update          QC#29630
 * </pre>
 */
public class InvImptApiParamBean {

    private List<NWZC040001PMsg> invHeaderPmsgList;
    private List<NWZC040002PMsg> shipmentsPMsgList;
    private List<NWZC040003PMsg> invLinePMsgList;
    private List<NWZC040004PMsg> promoPMsgList;
    private List<NWZC040005PMsg> taxDtlPMsgList;
    private List<NWZC040006PMsg> serNumPMsgList;
    private List<NWZC040007PMsg> invSlsCrPMsgList;
    private NWZC036101_taxCalculateOutputLinePMsg lastMdseTaxResult;

    public int lineNumCnt;
    public int lineSubNumCnt;

    /** Error Message List */
    public List<String> errMsgList;

    public boolean invCratFlg = true;

    public String getLineNumCntStr() {
        return String.format("%05d", this.lineNumCnt);
    }

    public String getLineSubNumCntStr() {
        return String.format("%03d", this.lineSubNumCnt);
    }

    public NWZC036101_taxCalculateOutputLinePMsg getLastMdseTaxResult() {
        return lastMdseTaxResult;
    }

    public void setLastMdseTaxResult(NWZC036101_taxCalculateOutputLinePMsg lastMdseTaxResult) {
        this.lastMdseTaxResult = lastMdseTaxResult;
    }

    public String getLastPmtInvBolLineNum() {
        String retNum = String.format("%03d", this.shipmentsPMsgList.size());

        return retNum;
    }

    public NWZC040002PMsg getShipMsgByInvBolLineNum(String invBolLineNum) {
        NWZC040002PMsg retPMsg = null;

        for (NWZC040002PMsg pMsg : this.shipmentsPMsgList) {
            if (pMsg.invBolLineNum.getValue().equals(invBolLineNum)) {
                retPMsg = pMsg;
                break;
            }
        }

        return retPMsg;
    }

    public NWZC040002PMsg getLastShipMsg() {
        String retNum = getLastPmtInvBolLineNum();

        return getShipMsgByInvBolLineNum(retNum);
    }


    public NWZC040002PMsg getPrevShipMsgByInvBolLineNum(String invBolLineNum) {
        NWZC040002PMsg retPMsg = null;

        for (NWZC040002PMsg pMsg : this.shipmentsPMsgList) {
            retPMsg = pMsg;
            if (pMsg.invBolLineNum.getValue().equals(invBolLineNum)) {
                break;
            }
        }

        return retPMsg;
    }

    public List<NWZC040005PMsg> getEmptyInvLineSubNumList() {

        List<NWZC040005PMsg> taxEmptMsg = new ArrayList<NWZC040005PMsg>();

        for (NWZC040005PMsg taxMsg : this.taxDtlPMsgList) {
            if (!ZYPCommonFunc.hasValue(taxMsg.invLineSubNum)) {
                taxEmptMsg.add(taxMsg);
            }
        }

        return taxEmptMsg;
    }


    public List<NWZC040001PMsg> getInvHeaderPmsgList() {
        return invHeaderPmsgList;
    }

    public List<NWZC040002PMsg> getShipmentsPMsgList() {
        return shipmentsPMsgList;
    }

    public List<NWZC040003PMsg> getInvLinePMsgList() {
        return invLinePMsgList;
    }

    public void setInvLinePMsgList(List<NWZC040003PMsg> invLinePMsgList) {
        this.invLinePMsgList = invLinePMsgList;
    }

    public List<NWZC040004PMsg> getPromoPMsgList() {
        return promoPMsgList;
    }

    public void setPromoPMsgList(List<NWZC040004PMsg> promoPMsgList) {
        this.promoPMsgList = promoPMsgList;
    }

    public List<NWZC040005PMsg> getTaxDtlPMsgList() {
        return taxDtlPMsgList;
    }

    public void setTaxDtlPMsgList(List<NWZC040005PMsg> taxDtlPMsgList) {
        this.taxDtlPMsgList = taxDtlPMsgList;
    }

    public List<NWZC040006PMsg> getSerNumPMsgList() {
        return serNumPMsgList;
    }

    public void setSerNumPMsgList(List<NWZC040006PMsg> serNumPMsgList) {
        this.serNumPMsgList = serNumPMsgList;
    }

    public List<NWZC040007PMsg> getInvSlsCrPMsgList() {
        return invSlsCrPMsgList;
    }

    public void setInvSlsCrPMsgList(List<NWZC040007PMsg> invSlsCrPMsgList) {
        this.invSlsCrPMsgList = invSlsCrPMsgList;
    }

    public InvImptApiParamBean() {
        this.invHeaderPmsgList = new ArrayList<NWZC040001PMsg>();
        this.shipmentsPMsgList = new ArrayList<NWZC040002PMsg>();
        this.invLinePMsgList = new ArrayList<NWZC040003PMsg>();
        this.promoPMsgList = new ArrayList<NWZC040004PMsg>();
        this.taxDtlPMsgList = new ArrayList<NWZC040005PMsg>();
        this.serNumPMsgList = new ArrayList<NWZC040006PMsg>();
        this.invSlsCrPMsgList = new ArrayList<NWZC040007PMsg>();
        this.errMsgList = new ArrayList<String>();

        this.lineNumCnt = -1;
        this.lineSubNumCnt = -1;
    }

    /**
     * @return invCratFlg
     */
    public boolean isInvCratFlg() {
        return invCratFlg;
    }

    /**
     * @param invCratFlg invCratFlg
     */
    public void setInvCratFlg(boolean invCratFlg) {
        this.invCratFlg = invCratFlg;
    }
    // 2018/12/14 QC#29630 add Start
    public void setInvNum(String invNum) {

        for (NWZC040001PMsg pmsg : invHeaderPmsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
        for (NWZC040002PMsg pmsg : shipmentsPMsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
        for (NWZC040003PMsg pmsg : invLinePMsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
        for (NWZC040004PMsg pmsg : promoPMsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
        for (NWZC040005PMsg pmsg : taxDtlPMsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
        for (NWZC040006PMsg pmsg : serNumPMsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
        for (NWZC040007PMsg pmsg : invSlsCrPMsgList) {
            ZYPEZDItemValueSetter.setValue(pmsg.invNum, invNum);
        }
    }
    // 2018/12/14 QC#29630 add End
}
