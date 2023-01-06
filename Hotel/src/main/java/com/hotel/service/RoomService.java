package com.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.mapper.RoomMapper;
import com.hotel.model.Room;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService{
	
	@Autowired
	private final RoomMapper Rmapper;
	
	@Transactional
	public void AddRoom(Room r) {
		Rmapper.AddRoom(r);
	}
}
