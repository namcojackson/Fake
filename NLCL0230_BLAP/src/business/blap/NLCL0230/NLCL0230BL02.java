/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/06   Fujitsu         S.Yoshida       Create          N/A
 * 2013/11/14   Fujitsu         M.Yamada        Update          #2852(i18n)
 *</pre>
 */
package business.blap.NLCL0230;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NLCL0230BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            // Get event id.
            String screenAplID = cMsg.getScreenAplID();

            NLCL0230CMsg bizMsg = (NLCL0230CMsg) cMsg;

            // INIT
            if ("NLCL0230_INIT".equals(screenAplID)) {
                doProcess_NLCL0230_INIT(bizMsg);

            // Search_Inventory
            } else if ("NLCL0230Scrn00_Search_Inventory".equals(screenAplID)) {
                doProcess_NLCL0230Scrn00_Search_Inventory(bizMsg);

            // Unexpected
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0230_INIT(NLCL0230CMsg bizMsg) {

        // Get the Merchandise Name.
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd)) {

            // Find the Merchandise Master.
            MDSETMsg mdseTMsg = new MDSETMsg();
            mdseTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            mdseTMsg.mdseCd.setValue(bizMsg.mdseCd.getValue());
            mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

            // Error of database access.
            if (mdseTMsg == null
                    || !RTNCD_NORMAL.equals(mdseTMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NLCM0002E");

            // Set the Merchandise Name.
            } else {
                bizMsg.mdseNm.setValue(mdseTMsg.mdseNm.getValue());
                bizMsg.mdseCd_F.setValue(bizMsg.mdseCd.getValue());
            }
        }

        // Create pull down for Location Type.
        ZYPCodeDataUtil.createPulldownList("LOC_TP", bizMsg.invtyLocTpCd, bizMsg.xxEdtCdNm, ":");
        // #2852
        EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        String i18nString = converter.convLabel2i18nLabel("", "ALL Loc Type");
        ZYPPulldownValueSetter.insertFirstData("--", bizMsg.invtyLocTpCd, i18nString, bizMsg.xxEdtCdNm, ":");

        // Find the Inventory Location View.
        findInvty(bizMsg);
    }


    private void doProcess_NLCL0230Scrn00_Search_Inventory(NLCL0230CMsg bizMsg) {

        // Find the Inventory Location View.
        findInvty(bizMsg);
    }

    private void findInvty(NLCL0230CMsg bizMsg) {

        // Find the Inventory Location View.
        S21SsmEZDResult ssmResult = null;
        bizMsg.xxRowNum.setValue(bizMsg.A.length() + 2);
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_F)) {
            ssmResult = NLCL0230Query.getInstance().findInvtyWithMdse(bizMsg);
        } else {
            ssmResult = NLCL0230Query.getInstance().findInvty(bizMsg);
        }

        // Error of database access.
        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo("NLCM0004E");
            return;
        }

        // Over the max count of view.
        if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
            bizMsg.setMessageInfo("NLCM0001I");
        }
    }
}
