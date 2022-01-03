package com.RPC.DTO;

import lombok.Data;

@Data
public class NewGameResponse {

	int token;
	String ready;

	public NewGameResponse(int token) {
		this.token=token;
		this.ready="REDAY";
	}

}
