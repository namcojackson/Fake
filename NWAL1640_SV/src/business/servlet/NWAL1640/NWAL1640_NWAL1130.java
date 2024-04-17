/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import static business.servlet.NWAL1640.constant.NWAL1640Constant.BIZ_ID;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1640.NWAL1640CMsg;
import business.servlet.NWAL1640.constant.NWAL1640Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NWAL1640_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyCtyAddr, scrnMsg.L.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyStCd, scrnMsg.L.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyPostCd, scrnMsg.L.no(2).xxComnScrColValTxt);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if (!"CMN_Close".equals(getLastGuard())) {
            NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
            NWAL1640CMsg bizMsg = new NWAL1640CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            NWAL1640CMsg bizMsg = (NWAL1640CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        Map<String, EZDBStringItem> map = new HashMap<String, EZDBStringItem>();
        map.put(NWAL1640Constant.EVENT_OPEN_WIN_CITY, scrnMsg.splyCtyAddr);
        map.put(NWAL1640Constant.EVENT_OPEN_WIN_STATE, scrnMsg.splyStCd);
        map.put(NWAL1640Constant.EVENT_OPEN_WIN_POST, scrnMsg.splyPostCd);
        EZDBStringItem item = map.get(scrnMsg.xxScrEventNm.getValue());
        if (item != null) {
            scrnMsg.setFocusItem(item);
        }
    }
}
