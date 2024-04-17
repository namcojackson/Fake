package business.servlet.ZZZL0010.common;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import business.servlet.ZZZL0010.ZZZL0010_INIT;
import business.servlet.ZZZL0010.xml.StartRouter;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEar;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEarApplication;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEarApplicationBusiness;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEarApplicationSubsystem;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEarReplication;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEarReplicationParameter;
import business.servlet.ZZZL0010.xml.StartRouterEzdApplicationEarReplicationSession;
import business.servlet.ZZZL0010.xml.StartRouterNonezdApplicationSite;
import business.servlet.ZZZL0010.xml.StartRouterNonezdApplicationSiteParameter;

import com.canon.cusa.s21.framework.online.router.S21ApplicationDef;

/**
 * アプリケーション情報リストクラス。
 * @author q02673
 */
public class XmlApplicationDefHolder {

    /** アプリケーション定義 */
    private Map<String, S21ApplicationDef> application = new HashMap<String, S21ApplicationDef>();

    /** 業務ID検索用 */
    private Map<String, S21ApplicationDef> lookupBusinessId = new HashMap<String, S21ApplicationDef>();

    /** サブシステムID検索用 */
    private Map<String, S21ApplicationDef> lookupSubsystemId = new HashMap<String, S21ApplicationDef>();

    /**
     * コンストラクタ。
     */
    public XmlApplicationDefHolder() {
    }

    /**
     * 初期化処理。
     */
    public void init() {
        URL url = ZZZL0010_INIT.class.getClassLoader().getResource("s21router.xml");

        StartRouter def = null;
        try {
            def = new StartRouter(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (SAXException e) {
            e.printStackTrace();
            return;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        }

        // EZD Webアプリケーションモジュール
        StartRouterEzdApplicationEar[] earList = def.getStartRouterEzdApplication().getStartRouterEzdApplicationEar();
        for (int i = 0; i < earList.length; i++) {
            StartRouterEzdApplicationEar ear = earList[i];

            // 初期化
            S21ApplicationDef appdef = new S21ApplicationDef();
            appdef.setWebAppId(ear.getId());
            appdef.setContextRoot(ear.getContextRoot());
            appdef.setUrl(ear.getUrl());
            appdef.setEzdApplication(true);
            appdef.setRouterAvailable(true);

            StartRouterEzdApplicationEarApplication app = ear.getStartRouterEzdApplicationEarApplication();

            StartRouterEzdApplicationEarApplicationSubsystem[] subsystems = app.getStartRouterEzdApplicationEarApplicationSubsystem();
            for (int j = 0; i < subsystems.length; j++) {
                appdef.getSubsystem().put(subsystems[j].getId(), subsystems[j]);
            }

            StartRouterEzdApplicationEarApplicationBusiness[] businesses = app.getStartRouterEzdApplicationEarApplicationBusiness();
            for (int j = 0; i < businesses.length; j++) {
                appdef.getBusiness().put(businesses[j].getId(), businesses[j]);
            }

            StartRouterEzdApplicationEarReplication rep = ear.getStartRouterEzdApplicationEarReplication();

            StartRouterEzdApplicationEarReplicationSession[] session = rep.getStartRouterEzdApplicationEarReplicationSession();
            for (int j = 0; i < session.length; j++) {
                appdef.getReplicationSessionName().add(session[j].getName());
            }

            StartRouterEzdApplicationEarReplicationParameter[] param = rep.getStartRouterEzdApplicationEarReplicationParameter();
            for (int j = 0; i < param.length; j++) {
                appdef.getReplicationParameterName().add(param[j].getName());
            }

            // リストに追加
            application.put(appdef.getWebAppId(), appdef);

            Set bizIdList = appdef.getBusiness().keySet();
            for (Iterator iter = bizIdList.iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                lookupBusinessId.put(key, appdef);
            }

            Set subsystemIdList = appdef.getSubsystem().keySet();
            for (Iterator iter = subsystemIdList.iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                lookupSubsystemId.put(key, appdef);
            }
        }

        // 非EZD Webアプリケーションモジュール
        StartRouterNonezdApplicationSite[] siteList = def.getStartRouterNonezdApplication().getStartRouterNonezdApplicationSite();
        for (int i = 0; i < siteList.length; i++) {
            StartRouterNonezdApplicationSite site = siteList[i];

            // 初期化
            S21ApplicationDef appdef = new S21ApplicationDef();
            appdef.setWebAppId(site.getId());
            appdef.setContextRoot("");
            appdef.setUrl(site.getUrl());
            appdef.setEzdApplication(false);
            appdef.setRouterAvailable(false);

            StartRouterNonezdApplicationSiteParameter[] param = site.getStartRouterNonezdApplicationSiteParameter();
            for (int j = 0; i < param.length; j++) {
                appdef.getReplicationParameterName().add(param[j].getName());
            }

            // リストに追加
            application.put(appdef.getWebAppId(), appdef);
        }

    }

    /**
     * ApplicationIdが一致する遷移先情報を取得します。
     * @param applicationId アプリケーションID
     * @return 遷移先情報
     */
    public S21ApplicationDef getApplicationDefById(String applicationId) {
        for (String id : application.keySet()) {
            if (id.equals(applicationId)) {
                return application.get(id);
            }
        }

        return null;
    }

    /**
     * contextRootが一致する遷移先情報を取得します。
     * @param contextRoot コンテキストルート
     * @return 遷移先情報
     */
    public S21ApplicationDef getApplicationDefByContextRoot(String contextRoot) {
        for (S21ApplicationDef val : application.values()) {
            if (val.getContextRoot().equals(contextRoot)) {
                return val;
            }
        }

        return null;
    }

    /**
     * 業務IDが一致する遷移先情報を取得します。
     * @param businessId 業務ID
     * @return 遷移先情報
     */
    public S21ApplicationDef getApplicationDefByBusinessId(String businessId) {
        return lookupBusinessId.get(businessId);
    }

    /**
     * サブシステムIDが一致する遷移先情報を取得します。
     * @param subsystemId サブシステムID
     * @return 遷移先情報
     */
    public S21ApplicationDef getApplicationDefBySubsystemId(String subsystemId) {
        return lookupSubsystemId.get(subsystemId);
    }
}
