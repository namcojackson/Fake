package com.canon.cusa.s21.batch.NWA.NWAB265001;

import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.NWAI1200_10TMsg;

/** <pre>
 * EdiOrdImptDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class EdiOrdImptDtlBean extends EdiOrdImptBaseBean {

    final public NWAI1200_10TMsg srcTMsg;

    final public EdiOrdImptHdrBean hdrBean;

    public DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg;

    public DS_IMPT_DTL_EXT_ATTRBTMsg dsImptDtlExtAttrbTMsg;

    public EdiOrdImptDtlBean(EdiOrdImptHdrBean hdrBean, NWAI1200_10TMsg srcTMsg) {
        super();

        this.srcTMsg = srcTMsg;
        this.hdrBean = hdrBean;
        this.hdrBean.ordImptDtlBeanMap.put(createKey(this.srcTMsg), this);
    }

    public static String createKey(NWAI1200_10TMsg tMsg) {
        return String.format("%s-%.0f-%.0f-%.0f-%.0f"
                                , tMsg.interfaceId.getValue()
                                , tMsg.transactionId.getValue()
                                , tMsg.segmentId.getValue()
                                , tMsg.unitId.getValue()
                                , tMsg.seqNumber.getValue());
    }

    @Override
    public String getOrdSrcRefNum() {
        if (srcTMsg != null) {
            return this.srcTMsg.idocPoDocNum.getValue();
        } else {
            return super.getOrdSrcRefNum();
        }
    }

    public String getOrdSrcRefLineNum() {
        if (srcTMsg != null) {
            return this.srcTMsg.idocPoDtlLineRefNum.getValue();
        } else {
            return "";
        }
    }

    public String getOrdSrcRefLineSubNum() {
        if (srcTMsg != null) {
            return "001";
        } else {
            return "";
        }
    }
}
