package repository;


import model.Notification;

public interface NotificationRepository {

	Notification addNotification(String IDMember, String IDReciver, String message) throws Exception;
	int getUnreadNotifications(String MemberID) throws  Exception ;
}
