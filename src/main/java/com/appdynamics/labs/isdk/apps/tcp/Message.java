package com.appdynamics.labs.isdk.apps.tcp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String msg;
	public Map<String, Object> parameters = new HashMap<String, Object>();
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "Message [msg=" + msg + ", parameters=" + parameters + "]";
	}
}