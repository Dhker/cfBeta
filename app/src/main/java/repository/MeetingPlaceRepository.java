package repository;


import java.util.ArrayList;

import model.MeetingPlace;
import model.MeetingPlaceType;

public interface MeetingPlaceRepository {
 
	
	 ArrayList<MeetingPlaceType> getAllMeetingPlaceType() throws Exception;
	 MeetingPlace getMeetingPlaceById(String id) throws Exception;
	ArrayList<MeetingPlace> getFavoriteMeetingPlace(String memberID) throws Exception ;
}
