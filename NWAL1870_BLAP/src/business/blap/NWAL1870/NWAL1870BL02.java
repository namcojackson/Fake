/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1870;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;

/**
 *<pre>
 * NWAL1870BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         R.Nakamura      Create          QC#11614
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25616
 * 2018/07/12   Fujitsu         M.Ishii         Update          QC#27096
 *</pre>
 */
public class NWAL1870BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1870CMsg bizMsg = (NWAL1870CMsg) cMsg;
            NWAL1870SMsg glblMsg = (NWAL1870SMsg) sMsg;

            if ("NWAL1870_INIT".equals(screenAplID)) {
                doProcess_NWAL1870_INIT(bizMsg, glblMsg);

            } else if ("NWAL1870Scrn00_CMN_Supersede".equals(screenAplID)) {
                doProcess_NWAL1870Scrn00_CMN_Supersede(bizMsg, glblMsg);

            } else if ("NWAL1870Scrn00_CMN_keepOriginal".equals(screenAplID)) {
                doProcess_NWAL1870Scrn00_CMN_keepOriginal(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1870_INIT(NWAL1870CMsg bizMsg, NWAL1870SMsg glblMsg) {

        MDSETMsg orgMDSETMsg = getMdseNm(bizMsg.mdseCd_I.getValue());
        if (null != orgMDSETMsg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_I, orgMDSETMsg.mdseDescShortTxt);
            // 2018/07/12 QC#27096 ADD START
            ZYPEZDItemValueSetter.setValue(bizMsg.mnfItemCd_I, orgMDSETMsg.mnfItemCd);
            // 2018/07/12 QC#27096 ADD END
        }
        MDSETMsg sprMDSETMsg = getMdseNm(bizMsg.mdseCd_S.getValue());
        if (null != orgMDSETMsg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_S, sprMDSETMsg.mdseDescShortTxt);
            // 2018/07/12 QC#27096 ADD START
            ZYPEZDItemValueSetter.setValue(bizMsg.mnfItemCd_S, sprMDSETMsg.mnfItemCd);
            // 2018/07/12 QC#27096 ADD END
        }

    }

    /**
     * CMN_Supersede Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1870Scrn00_CMN_Supersede(NWAL1870CMsg bizMsg, NWAL1870SMsg glblMsg) {

    }

    /**
     * CMN_keepOriginal Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1870Scrn00_CMN_keepOriginal(NWAL1870CMsg bizMsg, NWAL1870SMsg glblMsg) {

    }

    /**
     * Search Merchandise Name
     * @param mdseCd String
     * @return MDSETMsg
     */
    public MDSETMsg getMdseNm(String mdseCd) {

        String glblCmpyCd = getGlobalCompanyCode();
        //2018/07/06 QC#25616 MOD START
//        MDSETMsg mdseTMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
//        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        //2018/07/06 QC#25616 MOD END
        return mdseTMsg;
    }

}
