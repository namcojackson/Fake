/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.fnd.cp.request;

public interface ReqCompletion {
	public static final int NORMAL = 0;
	public static final int WARNING = 1;
	public static final int ERROR = 2;
	public static final int PAUSE = 3;

	void setCompletion(int i, String s);
}
