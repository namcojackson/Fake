package com.canon.cusa.s21.batch.NWA.NWAB265001;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.NWAI1200_01TMsg;

/** <pre>
 * EdiOrdImptHdrBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class EdiOrdImptHdrBean extends EdiOrdImptBaseBean {

    final public NWAI1200_01TMsg srcTMsg;

    final public LinkedHashMap<String, EdiOrdImptDtlBean> ordImptDtlBeanMap;

    public DS_IMPT_ORDTMsg dsImptOrdTMsg;

    public DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg;

    public DS_IMPT_EXT_ATTRBTMsg dsExtAttrbTMsg;

    public static String autoOrdImptFlg = null;

    public EdiOrdImptHdrBean(NWAI1200_01TMsg srcTMsg) {
        super();

        this.srcTMsg = srcTMsg;

        this.ordImptDtlBeanMap = new LinkedHashMap<String, EdiOrdImptDtlBean>();
    }

    @Override
    public String getOrdSrcRefNum() {
        if (srcTMsg != null) {
            return this.srcTMsg.idocPoDocNum.getValue();
        } else {
            return super.getOrdSrcRefNum();
        }
    }

    @Override
    public boolean hasError() {
        if (super.hasError()) {
            return true;
        }

        for (EdiOrdImptDtlBean dtlBean : ordImptDtlBeanMap.values()) {
            if (dtlBean.hasError()) {
                return true;
            }
        }

        return false;
    }
    public List<NWAB265001ErrorInfo> getAllErrorBean() {
        List<NWAB265001ErrorInfo> allErrList = new ArrayList<NWAB265001ErrorInfo>();

        allErrList.addAll(this.errorInfoList);

        for (EdiOrdImptDtlBean dtlBean : this.ordImptDtlBeanMap.values()) {
            allErrList.addAll(dtlBean.errorInfoList);
        }

        return allErrList;
    }

    public String getAllErrMsg() {
        StringBuilder sb = new StringBuilder();
        List<NWAB265001ErrorInfo> errBeanList = this.getAllErrorBean();

        sb.append(String.format("Order Source Reference# : %s\n", this.getOrdSrcRefNum()));

        for (NWAB265001ErrorInfo errBean : errBeanList) {
            sb.append(errBean.getErrMsgText());
            sb.append("\n");
        }

        return sb.toString();
    }
}
