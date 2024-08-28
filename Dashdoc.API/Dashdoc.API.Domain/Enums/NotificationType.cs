namespace Dashdoc.API.Domain.Enums;

public enum NotificationType
{
    Appointment,
    AppointmentCancelled,
    AppointmentConfirmed,
    AppointmentRescheduled,
    AppointmentReminder,
    AppointmentNoShow,
    PasswordReset,
    OneTimePasscode,
    BillingReminder,
    DocumentDeadline,
    DocumentApproved,
    DocumentChangesRequested,
    DocumentSubmitted
}