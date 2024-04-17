/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.fnd.cp.request;

public interface ReqDetails {
	public ReqUserInfo getUserInfo();

	public int getRequestId();

	public ReqGeneralInfo getGeneralInfo();

	public ReqParameterInfo getParaInfo();
}
