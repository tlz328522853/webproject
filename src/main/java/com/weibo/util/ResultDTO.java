package com.weibo.util;

/**
 * return result class for web response
 * 
 * @author czz
 *
 */
public class ResultDTO {

	private int code;
	private boolean success;
	private String message;
	private Object data;

	public static ResultDTO getSuccess(Object data) {
		ResultDTO res = new ResultDTO(200, true, null, data);
		return res;
	}

	public static ResultDTO getSuccess(Object data, String message) {
		ResultDTO res = new ResultDTO(200, true, message, data);
		return res;
	}

	public static ResultDTO getSuccess(int code, Object data) {
		ResultDTO res = new ResultDTO(code, true, null, data);
		return res;
	}

	public static ResultDTO getSuccess(int code, String message, Object data) {
		ResultDTO res = new ResultDTO(code, true, message, data);
		return res;
	}

	public static ResultDTO getFailure(int code, String message) {
		ResultDTO res = new ResultDTO(code, false, message, null);
		return res;
	}

	public static ResultDTO getFailure(String message) {
		ResultDTO res = new ResultDTO(500, false, message, null);
		return res;
	}

	private ResultDTO(int code, boolean success, String message, Object data) {
		this.code = code;
		this.data = data;
		this.message = message;
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{code:" + code + ", success:" + success + ", message:\"" + message + "\", data:" + data + "}";
	}

}
