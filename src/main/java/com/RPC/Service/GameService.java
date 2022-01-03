package com.RPC.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RPC.DTO.MoveResponse;
import com.RPC.Entity.Game;
import com.RPC.Exception.RPCException;
import com.RPC.Repository.GameRepository;

@Component
public class GameService {

	@Autowired
	GameRepository gameRepo;

	public int startNewGame() {
		Random rad = new Random();
		int token = rad.nextInt(10000);
		while (gameRepo.existsById(token)) {
			token = rad.nextInt(10000);
		}
		Game newGame = new Game(token, 0, 0);
		gameRepo.save(newGame);
		return token;
	}

	public MoveResponse playFor(int token, String move) throws RPCException {
		if (!gameRepo.existsById(token)) {
			throw new RPCException("INVALID TOKEN");
		}
		if (checkValidMove(move) == false) {
			throw new RPCException("INVALID MOVE");
		}
		Game currentGame = gameRepo.findById(token).get();
		if (currentGame.getServerScore() == 3) {
			throw new RPCException("GAME ALREADY COMPLETED !!");
		}
		String serverMove = getServerMove(move);
		currentGame.setServerScore(currentGame.getServerScore() + 1);
		gameRepo.save(currentGame);
		MoveResponse resp = new MoveResponse(currentGame.getUserScore(), currentGame.getServerScore(), serverMove);
		return resp;
	}

	private boolean checkValidMove(String move) {
		if (move.equals("scissors") || move.equals("SCISSORS") || move.equals("rock") || move.equals("ROCK")
				|| move.equals("paper") || move.equals("PAPER")) {
			return true;
		}
		return false;
	}

	public boolean checkWin(MoveResponse resp) {
		return resp.getServerScore() == 3;
	}

	private String getServerMove(String move) {
		if (move.equals("scissors") || move.equals("SCISSORS")) {
			return "ROCK";
		} else if (move.equals("rock") || move.equals("ROCK")) {
			return "PAPER";
		} else if (move.equals("paper") || move.equals("PAPER")) {
			return "SCISSORS";
		}
		return "";
	}

}
