/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.constant.NMAL6720Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NMAL6720_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_H1, scrnMsg.L.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_P1, scrnMsg.L.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_H1, scrnMsg.L.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_H1, scrnMsg.L.no(3).xxComnScrColValTxt);
        }

        Map<String, EZDBStringItem> map = new HashMap<String, EZDBStringItem>();
        map.put(NMAL6720Constant.EVENT_OPEN_WIN_CITY, scrnMsg.ctyAddr_H1);
        map.put(NMAL6720Constant.EVENT_OPEN_WIN_ST, scrnMsg.stCd_P1);
        map.put(NMAL6720Constant.EVENT_OPEN_WIN_POST, scrnMsg.postCd_H1);
        map.put(NMAL6720Constant.EVENT_OPEN_WIN_CNTY, scrnMsg.cntyNm_H1);
        EZDBStringItem item = map.get(scrnMsg.xxScrEventNm_P.getValue());
        if (item != null) {
            scrnMsg.setFocusItem(item);
        }
    }
}
