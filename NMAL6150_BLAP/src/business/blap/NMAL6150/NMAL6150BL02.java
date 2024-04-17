/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6150;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NMAL6150BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6150CMsg bizMsg = (NMAL6150CMsg) cMsg;
        NMAL6150SMsg sharedMsg = (NMAL6150SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6150_INIT".equals(screenAplID)) {
                doProcess_NMAL6150_INIT(bizMsg, sharedMsg);
            } else if ("NMAL6150Scrn00_ApplyOrigAddr".equals(screenAplID)) {
                doProcess_NMAL6150Scrn00_ApplyOrigAddr(bizMsg, sharedMsg);
            } else if ("NMAL6150Scrn00_ApplySugAddr".equals(screenAplID)) {
                doProcess_NMAL6150Scrn00_ApplySugAddr(bizMsg, sharedMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    private void doProcess_NMAL6150_INIT(NMAL6150CMsg cMsg, NMAL6150SMsg sMsg) {
        S21SsmEZDResult res = NMAL6150Query.getInstance().getStList(this.getGlobalCompanyCode());
        if (res.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) res.getResultObject();
            
            int index = 0;
            for(Map<String, Object> map : list){
                if(index < cMsg.stCd_O2.length()){
                    String stCd = (String)map.get("ST_CD");
                    String stNm = (String)map.get("ST_NM");

                    ZYPEZDItemValueSetter.setValue(cMsg.stCd_O2.no(index), stCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.stNm_O2.no(index), stNm);
                    
                    ZYPEZDItemValueSetter.setValue(cMsg.stCd_S2.no(index), stCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.stNm_S2.no(index), stNm);
                } else {
                    break;
                }
                index++;
            }
        }
    }

    private void doProcess_NMAL6150Scrn00_ApplyOrigAddr(NMAL6150CMsg cMsg, NMAL6150SMsg sMsg) {
    }

    private void doProcess_NMAL6150Scrn00_ApplySugAddr(NMAL6150CMsg cMsg, NMAL6150SMsg sMsg) {
    }

}
