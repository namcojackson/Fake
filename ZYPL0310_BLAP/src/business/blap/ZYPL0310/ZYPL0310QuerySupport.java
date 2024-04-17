package business.blap.ZYPL0310;

import java.util.concurrent.ConcurrentHashMap;

import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.SsmEZDClientSupport;

public class ZYPL0310QuerySupport extends SsmEZDClientSupport {

    private static final S21Logger logger = S21LoggerFactory.getLogger(ZYPL0310QuerySupport.class);
    private static ConcurrentHashMap<String, ZYPL0310QuerySupport> clientCache = new ConcurrentHashMap();

    /**
     * construct
     * @param useClass
     */
    public ZYPL0310QuerySupport(Class useClass) {
        super(useClass, null, useClass.getSimpleName() + ".xml");
        useClassName = useClass.getSimpleName();
    }

    /**
     * Factory method to get ZZML0040Query2 object that is cached by useClass name in JVM level.
     * On the firat call, ZZML0040Query2 object load SSM mapping file, named useClass.xml.
     * @param useClass
     * @return
     */
    public static ZYPL0310QuerySupport getClient(Class useClass) {
        String useClassName = useClass.getName();
        ZYPL0310QuerySupport client = clientCache.get(useClassName);
        if (client == null) {
            client = new ZYPL0310QuerySupport(useClass);
            clientCache.put(useClassName, client);
        }
        return client;
    }

    /**
     * update it with SSM
     * @param statementId to execute
     * @param param       for execution
     * @return int
     */
    public int update(String statementId, Object param) {
        try {
            return ssmClient.update(useClassName + "." + statementId, param);

        } catch (Exception e) {
            String msg = "SSM: Failed update. statementId=" + statementId + "\n param=" + param;
            logger.error(msg, e);
            throw convertEZDRuntimeException(e, msg);
        }
    }
}
