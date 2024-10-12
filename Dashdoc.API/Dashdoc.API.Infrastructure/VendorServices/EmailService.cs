using System.Net.Mail;
using Microsoft.AspNetCore.Identity.UI.Services;


namespace Dashdoc.API.Infrastructure.VendorServices;

public class EmailService: IEmailSender
{
    private readonly SmtpClient _emailClient;

    public EmailService(SmtpClient emailClient)
    {
        _emailClient = emailClient;
    }

    public Task SendEmailAsync(string email, string subject, string htmlMessage)
    {
        throw new NotImplementedException();
    }
}