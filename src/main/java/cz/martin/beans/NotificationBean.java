package cz.martin.beans;

import cz.martin.interfaces.services.INotificationsService;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("notification")
@SessionScoped
public class NotificationBean implements Serializable {

    @Inject
    @Normal
    private INotificationsService notificationsService;

    public INotificationsService getNotificationsService() {
        return notificationsService;
    }
}
