/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8860;


import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NYEL8860.NYEL8860CMsg;
import business.blap.NYEL8860.NYEL8860SMsg;
import business.blap.NYEL8860.common.NYEL8860CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NYEL8860BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/24   Fujitsu         Mz.Takahashi        Create          N/A
 *</pre>
 */
public class NYEL8860BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8860CMsg bizMsg = (NYEL8860CMsg) cMsg;
            NYEL8860SMsg glblMsg = (NYEL8860SMsg) sMsg;

            if ("NYEL8860_INIT".equals(screenAplID)) {
                doProcess_NYEL8860_INIT(bizMsg, glblMsg);

            } else if ("NYEL8860Scrn00_Search".equals(screenAplID)) {
                doProcess_NYEL8860Scrn00_Search(bizMsg, glblMsg);

            } else if( "NYEL8860Scrn00_PageNext".equals( screenAplID ) ) {
                doProcess_NYEL8860Scrn00_PageNext( (NYEL8860CMsg)cMsg, (NYEL8860SMsg)sMsg );

            } else if( "NYEL8860Scrn00_PagePrev".equals( screenAplID ) ) {
                doProcess_NYEL8860Scrn00_PagePrev( (NYEL8860CMsg)cMsg, (NYEL8860SMsg)sMsg );

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
    private void doProcess_NYEL8860_INIT(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.A);
        NYEL8860CommonLogic.search(bizMsg, sMsg, this.getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
        String xxGrpFlg = bizMsg.xxGrpFlg.getValue();
        NYEL8860CommonLogic.initPullDown(bizMsg);

        if (S21StringUtil.isNotEmpty(xxGrpFlg)){
            bizMsg.xxGrpFlg.setValue(xxGrpFlg);
        }
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8860Scrn00_Search(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg) {
        NYEL8860CommonLogic.search(bizMsg, sMsg, this.getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
    }
    

    private void doProcess_NYEL8860Scrn00_PageNext( NYEL8860CMsg cMsg, NYEL8860SMsg sMsg ) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() );
    }

    private void doProcess_NYEL8860Scrn00_PagePrev( NYEL8860CMsg cMsg, NYEL8860SMsg sMsg ) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue( pagenationFrom );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );
    }
}
