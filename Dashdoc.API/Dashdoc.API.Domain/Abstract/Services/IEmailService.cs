namespace Dashdoc.API.Domain.Abstract.Services;

public interface IEmailService
{
    void Send(string recipient, string subject, string htmlBody);
}