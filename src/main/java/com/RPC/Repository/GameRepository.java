package com.RPC.Repository;

import org.springframework.data.repository.CrudRepository;

import com.RPC.Entity.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {

}
