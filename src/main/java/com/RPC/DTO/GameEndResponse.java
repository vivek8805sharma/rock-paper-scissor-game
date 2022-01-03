package com.RPC.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEndResponse {
	
	String Result;
	int userScore;
	int serverScore;
	String gameMove;

}
