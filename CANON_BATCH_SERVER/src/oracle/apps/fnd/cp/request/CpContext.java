/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.fnd.cp.request;

import oracle.apps.fnd.util.ParameterList;

public interface CpContext {

	public LineWriter getOutFile();

	public ReqCompletion getReqCompletion();

	public ReqDetails getReqDetails();

	public ParameterList getParameterList();

	public interface LineWriter {
		public void writeln(Object o);
	}
}
