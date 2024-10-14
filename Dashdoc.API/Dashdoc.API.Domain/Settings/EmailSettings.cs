namespace Dashdoc.API.Domain.Settings;

public class EmailSettings
{
    public string? SmtpHost { get; init; }
    public int Port { get; init; }
    public string? Username { get; init; }
    public string? Password { get; init; }
}