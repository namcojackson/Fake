/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4690;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL4690.constant.NMAL4690Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Class name: NMAL4690Query
 * <dd>The class explanation: SSM
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public final class NMAL4690Query extends S21SsmEZDQuerySupport implements NMAL4690Constant {

    /**
     * Singleton instance.
     */
    private static final NMAL4690Query INSTANCE = new NMAL4690Query();

    /**
     * Constructor.
     */
    private NMAL4690Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NMAL4690Query getInstance() {
        return INSTANCE;
    }
    
    /**
     * @param bizMsg NMAL4690CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRecord(NMAL4690CMsg bizMsg) {
        
        String cmpyPk = bizMsg.xxAllPsnNm.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("amal4000", NMAL4000);
        ssmParam.put("amal4750", NMAL4750);
        //'%,2001827'
        ssmParam.put("attDataGrpTxtNMAL4000", "%,"+cmpyPk);
        // '%,2001827,%'
        ssmParam.put("attDataGrpTxtNMAL4750", "%," + cmpyPk + ",%");
        ssmParam.put("ptyLocPk",bizMsg.ptyLocPk.getValue());
        ssmParam.put("cmpyPk",bizMsg.cmpyPk.getValue());
        
        return getSsmEZDClient().queryObjectList("getResult", ssmParam, -1, -1);
    }
}
