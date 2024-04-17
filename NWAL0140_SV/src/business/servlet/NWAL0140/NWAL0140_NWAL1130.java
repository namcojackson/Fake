/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL0140;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NWAL0140_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr, scrnMsg.L.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd, scrnMsg.L.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd, scrnMsg.L.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm, scrnMsg.L.no(3).xxComnScrColValTxt);
        }

        Map<String, EZDBStringItem> map = new HashMap<String, EZDBStringItem>();
        map.put(NWAL0140Constant.EVENT_OPEN_WIN_CITY, scrnMsg.ctyAddr);
        map.put(NWAL0140Constant.EVENT_OPEN_WIN_SHIP_TO_STATE, scrnMsg.stCd);
        map.put(NWAL0140Constant.EVENT_OPEN_WIN_POST, scrnMsg.postCd);
        map.put(NWAL0140Constant.EVENT_OPEN_WIN_CNTY, scrnMsg.cntyNm);
        EZDBStringItem item = map.get(scrnMsg.xxScrEventNm.getValue());
        if (item != null) {
            scrnMsg.setFocusItem(item);
        }
    }
}
