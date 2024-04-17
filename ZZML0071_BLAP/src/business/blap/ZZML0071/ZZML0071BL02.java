/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Original
 * Author: Q02046 Company: Fujitsu Limited Date: Jul 8, 2009
 */
package business.blap.ZZML0071;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZML0071.common.ZZML0071CommonLogic;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0071BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0071CMsg bizMsg = (ZZML0071CMsg) cMsg;
        ZZML0071SMsg sharedMsg = (ZZML0071SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0071_INIT".equals(screenAplID)) {
                doProcess_ZZML0071_INIT(bizMsg, sharedMsg);
            } else if ("ZZML0071_ZZML0080".equals(screenAplID)) {
                doProcess_ZZML0071_ZZML0080(bizMsg, sharedMsg);
            } else if ("ZZML0071Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZML0071Scrn00_CMN_Reset(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0071_INIT(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {
        
        sMsg.clear();
        sMsg.A.setValidCount(0);

        ZZML0071CommonLogic.searchZZML0071(cMsg, sMsg);
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0071_ZZML0080(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {

        int rowNum = cMsg.xxNum.getValueInt();
        
        S21SsmEZDResult ssmResult = ZZML0071Query.getInstance().getMlGrpAddr(cMsg, sMsg, rowNum);
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9006E", new String[] {"ML_GRP_ADDR table data" });
            return;
        }
        
        Map rstMap = (Map) ssmResult.getResultObject();
        String mlGrpNm = (String)rstMap.get("ML_GRP_NM");
        if ( mlGrpNm == null ) {
            mlGrpNm = "";
        }
        cMsg.A.no(rowNum).mlGrpNm_A.setValue(mlGrpNm);

        String mlGrpDescTxt = (String)rstMap.get("ML_GRP_NM");
        if ( mlGrpDescTxt == null ) {
            mlGrpDescTxt = "";
        }
        cMsg.A.no(rowNum).mlGrpDescTxt_A.setValue(mlGrpDescTxt);
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0071Scrn00_CMN_Reset(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {
        
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        BigDecimal mlUsrAddrPk = cMsg.A.no(0).mlUsrAddrPk_A.getValue();
        String mlGrpId = cMsg.A.no(0).mlGrpId_A.getValue();
        
        cMsg.clear();
        cMsg.A.setValidCount(0);

        sMsg.clear();
        sMsg.A.setValidCount(0);
        
        cMsg.glblCmpyCd.setValue( glblCmpyCd );
        cMsg.A.no(0).mlUsrAddrPk_A.setValue( mlUsrAddrPk );
        cMsg.A.no(0).mlGrpId_A.setValue( mlGrpId );
        
        ZZML0071CommonLogic.searchZZML0071(cMsg, sMsg);
    }

}
