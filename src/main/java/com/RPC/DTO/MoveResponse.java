package com.RPC.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveResponse {
	
	int userScore;
	int serverScore;
	String gameMove;

}
