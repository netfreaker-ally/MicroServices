package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name="Response",description = "Schema to hold response information"

)
public class ResponseDto {
	@Schema(description = "Status Code in response")

	private String statusCode;
	@Schema(description = "Status Message in response")

	private String statusMsg;
//	public ResponseDto(String statusCode, String statusMsg) {
//		super();
//		this.statusCode = statusCode;
//		this.statusMsg = statusMsg;
//	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
