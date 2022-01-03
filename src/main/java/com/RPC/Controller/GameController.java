package com.RPC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RPC.DTO.GameEndResponse;
import com.RPC.DTO.MoveResponse;
import com.RPC.DTO.NewGameResponse;
import com.RPC.Exception.RPCException;
import com.RPC.Service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@GetMapping("/start")
	public ResponseEntity<NewGameResponse> startNewGame(){
		int token=gameService.startNewGame();
		NewGameResponse newGame=new NewGameResponse(token);
		return new ResponseEntity<NewGameResponse>(newGame,HttpStatus.OK);
	}
	
	@GetMapping("/v1/{token}/{move}")
	public ResponseEntity<?> play(@PathVariable int token, @PathVariable String move) throws RPCException{
		MoveResponse resp=gameService.playFor(token, move);
		if(gameService.checkWin(resp)) {
			GameEndResponse gameEnd=new GameEndResponse("SERVER WINS!!",resp.getUserScore(),resp.getServerScore(),resp.getGameMove());
			return new ResponseEntity<GameEndResponse>(gameEnd,HttpStatus.OK);
		}
		return new ResponseEntity<MoveResponse>(resp,HttpStatus.OK);
	}

}
