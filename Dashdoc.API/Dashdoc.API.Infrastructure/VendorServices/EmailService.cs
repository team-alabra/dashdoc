using Dashdoc.API.Domain.Abstract.Services;
using Dashdoc.API.Domain.Settings;
using MailKit.Net.Smtp;
using MailKit.Security;
using Microsoft.Extensions.Options;
using MimeKit;
using MimeKit.Text;

namespace Dashdoc.API.Infrastructure.VendorServices;

public class EmailService: IEmailService
{
    private readonly EmailSettings _emailSettings;
    
    public EmailService(IOptions<EmailSettings> emailOptions)
    {
        _emailSettings = emailOptions.Value;
    }

    public void Send(string recipient, string subject, string htmlBody)
    {
        // create message
        var email = new MimeMessage();
        email.From.Add(MailboxAddress.Parse(_emailSettings.FromEmail));
        email.To.Add(MailboxAddress.Parse(recipient));
        email.Subject = subject;
        email.Body = new TextPart(TextFormat.Html) { Text = htmlBody };

        // send email
        using var smtp = new SmtpClient();
        smtp.Connect(_emailSettings.SmtpHost, _emailSettings.Port, SecureSocketOptions.StartTls);
        smtp.Authenticate(_emailSettings.Username, _emailSettings.Password);
        smtp.Send(email);
        smtp.Disconnect(true);
    }
}