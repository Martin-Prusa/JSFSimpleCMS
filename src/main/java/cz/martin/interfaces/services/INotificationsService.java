package cz.martin.interfaces.services;

import cz.martin.models.Notification;

public interface INotificationsService {
    void addNotification(Notification notification);
    Notification getNotification();
    boolean isPresent();
}
