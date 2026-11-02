# Notification service API

Service class: `com.lego.notification.NotificationService`. Errors are `ApiException` with an HTTP status.

## send(recipient, subject, body)
- 200: the notification (`id`, `recipient`, `subject`, `body`)
- 400: recipient is not an email address, blank subject

## listFor(recipient)
Notifications sent to that recipient, oldest first.
