package repository;

import java.util.ArrayList;

import model.Message;

public interface MessageRepository {
	
	 Message sendMessage(String IDMember, String IDReciver, String Object, String message) throws Exception;
	 ArrayList<Message> messagesSended(String IDMember, String Perpage, String Page) throws Exception;
	 int messageSendedDelete(String IDMember, String IDMessage) throws Exception;
	 ArrayList<Message>  messagesReceived(String IDMember, String is_not_read, String PerPage, String Page) throws Exception;
	 int message_received_read(String IDMember, String IDMessage) throws Exception;
	 int message_received_delete(String IDMember, String IDMessage) throws Exception;
	int getUnreadMessages(String MemberID) throws Exception;

	 
}
