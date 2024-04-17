package business.blap.NYEL0050;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

public class NYEL0050BL02 extends S21BusinessHandler {

    /**
     * Do process.
     * @param cMsg the c msg
     * @param sMsg the s msg
     * @see parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg,
     * parts.common.EZDSMsg)
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            if ("NYEL0050_INIT".equals(screenAplID)) {
                doProcess_NYEL0050_INIT(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NYEL0050_INIT
     * <dd>The method explanation: Business processing for Init.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NYEL0050_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        // Set up Status list box contents
        NYEL0050CMsg bizMsg = (NYEL0050CMsg) cMsg;
        bizMsg.A.no(0).glblCmpyCd.setValue(getGlobalCompanyCode());

        NYEL0050Query.getInstance().exec(cMsg, sMsg);
        setData(bizMsg);
    }

    /**
     * Copy partial array.
     * @param sMsg the S msg
     * @param cMsg the C msg
     * @param pageNum the page number
     * @param pageSize the page size
     * @return the int
     */
    public int copyPartialArray(EZDSMsgArray sMsg, EZDCMsgArray cMsg, int offset, int pageSize) {

        cMsg.clear();
        int nextPageOffset = offset + pageSize;
        int lastRow = nextPageOffset > sMsg.getValidCount() ? sMsg.getValidCount() : nextPageOffset;
        int rowCounter = 0;
        for (int i = offset; i < lastRow; i++) {
            EZDMsg.copy(sMsg.get(i), null, cMsg.get(rowCounter++), null);
        }
        cMsg.setValidCount(rowCounter);
        return rowCounter;
    }

    /**
     * Sets the data to bMsg for display on jsp page
     * @param bMsg the new data
     */
    private void setData(NYEL0050CMsg bMsg) {

        NYEL0050CMsg bizMsg = (NYEL0050CMsg) bMsg;
        NYEL0050CMsg bMsg_new = new NYEL0050CMsg();
        String listIdVal = null;
        String dbVal = null;
        String actvFlgVal = null;
        try {
            S21UserProfileService profileService = getUserProfileService();
            List bizAppList = profileService.getAuthorizedBizAppIdList();
            int totalRecords = bizMsg.A.getValidCount();

            int count = 0;
            if (bizAppList.size() > 0) {
                for (int j = 0; j < bizAppList.size(); j++) {
                    listIdVal = bizAppList.get(j).toString();
                    for (int i = 0; i < totalRecords; i++) {
                        dbVal = bizMsg.A.no(i).bizAppId.getValue();
                        actvFlgVal = bizMsg.A.no(i).actvFlg.getValue();
                        if (listIdVal.equals(dbVal) && ("Y".equals(actvFlgVal))) {
                            bMsg_new.A.no(count).bizAppId.setValue((String) bizMsg.A.no(i).bizAppId.getValue());
                            bMsg_new.A.no(count).glblCmpyCd.setValue((String) bizMsg.A.no(i).glblCmpyCd.getValue());
                            bMsg_new.A.no(count).bizAppNm.setValue((String) bizMsg.A.no(i).bizAppNm.getValue());
                            bMsg_new.A.no(count).appDescTxt.setValue((String) bizMsg.A.no(i).appDescTxt.getValue());
                            count++;
                        }
                    }
                }
            } else {

                bMsg_new.A.setValidCount(0);
            }
            bMsg_new.A.setValidCount(count);
            EZDMsg.copy(bMsg_new, null, bMsg, null);
        } finally {
            super.postDoProcess(bMsg, null);
        }
    }

}
