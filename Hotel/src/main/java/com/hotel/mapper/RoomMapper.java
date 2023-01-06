package com.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hotel.model.Room;

@Mapper
public interface RoomMapper {

	void AddRoom(Room r);
}
