package business.blap.NFAL0200;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public final class NFAL0200Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFAL0200Query myInstance = new NFAL0200Query();

    /**
     * Constructor.
     */
    private NFAL0200Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NFAL0200Query
     */
    public static NFAL0200Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getCOACmbn(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.A.length()+1 );
        ssmParam.put( "cMsg", cMsg );

        return getSsmEZDClient().queryEZDMsgArray( "getCOACmbn", ssmParam, sMsg.A );
    }

    public S21SsmEZDResult getCOACh(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaChCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.B.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaChCd", coaChCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOACh", ssmParam, sMsg.B );
    }
    
    public S21SsmEZDResult getCOABr(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaBrCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.C.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaBrCd", coaBrCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOABr", ssmParam, sMsg.C );
    }
    
    public S21SsmEZDResult getCOACc(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaCcCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.D.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaCcCd", coaCcCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOACc", ssmParam, sMsg.D );
    }
    
    public S21SsmEZDResult getCOAAcct(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaAcctCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.E.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaAcctCd", coaAcctCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOAAcct", ssmParam, sMsg.E );
    }
    
    public S21SsmEZDResult getCOAProj(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaProjCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.F.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaProjCd", coaProjCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOAProj", ssmParam, sMsg.F );
    }
    
    public S21SsmEZDResult getCOAProd(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaProdCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.G.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaProdCd", coaProdCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOAProd", ssmParam, sMsg.G );
    }
    
    public S21SsmEZDResult getCOAAffl(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaAfflCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.H.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaAfflCd", coaAfflCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOAAffl", ssmParam, sMsg.H );
    }
    
    public S21SsmEZDResult getCOAExtn(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, String coaExtnCd) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put( "rowNum", sMsg.I.length()+1 );
        ssmParam.put( "cMsg", cMsg );
        ssmParam.put( "coaExtnCd", coaExtnCd);

        return getSsmEZDClient().queryEZDMsgArray( "getCOAExtn", ssmParam, sMsg.I );
    }
}
