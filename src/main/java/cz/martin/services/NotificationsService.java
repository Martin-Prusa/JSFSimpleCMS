package cz.martin.services;

import cz.martin.interfaces.services.INotificationsService;
import cz.martin.models.Notification;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
@Normal
public class NotificationsService implements Serializable, INotificationsService {

    private Notification notification;

    @Override
    public void addNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public Notification getNotification() {
        Notification n = this.notification;
        this.notification = null;
        return n;
    }

    @Override
    public boolean isPresent() {
        return this.notification != null;
    }
}
