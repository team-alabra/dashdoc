namespace Dashdoc.API.Domain.Settings;

public class SmtpSettings
{
    public string? SmtpHost { get; set; }
    public int Port { get; set; }
    public string? Username { get; set; }
    public string? Password { get; set; }
}