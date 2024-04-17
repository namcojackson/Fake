/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3170.constant.NFCL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3170_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         S.Fujita        Create          QC#8534
 *</pre>
 */
public class NFCL3170_NWAL1130 extends S21CommonHandler implements NFCL3170Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
       // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_H1, scrnMsg.L.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_H1, scrnMsg.L.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_H1, scrnMsg.L.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_H1, scrnMsg.L.no(3).xxComnScrColValTxt);
        }
        Map<String, EZDBStringItem> map = new HashMap<String, EZDBStringItem>();
        map.put(NFCL3170Constant.EVENT_CLICK_LINK_CITY, scrnMsg.ctyAddr_H1);
        map.put(NFCL3170Constant.EVENT_CLICK_LINK_STATE, scrnMsg.stCd_H1);
        map.put(NFCL3170Constant.EVENT_CLICK_LINK_POSTAL, scrnMsg.postCd_H1);
        map.put(NFCL3170Constant.EVENT_CLICK_LINK_COUNTY, scrnMsg.cntyNm_H1);
        EZDBStringItem item = map.get(scrnMsg.xxScrEventNm.getValue());
        if(item != null){
            scrnMsg.setFocusItem(item);
        }

    }
}
