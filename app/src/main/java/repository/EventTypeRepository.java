package repository;

import java.util.ArrayList;

import model.EventType;

public interface EventTypeRepository {
 
	 
	ArrayList<EventType> getAll() throws Exception;
	EventType getEventTypeById(String id) ;
 }
