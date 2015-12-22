package repository;


import model.Event;
import model.MemberFavoriteEvents;

public interface EventRepository {
    
	MemberFavoriteEvents getFavoriteEventsByMemberID(String memeberID) ;
	Event getEventById(String id) throws Exception;
	void createEvent(Event event) throws Exception;
	
	void updateEvent(Event newEvent) throws Exception;
	
}
