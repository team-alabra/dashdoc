namespace Dashdoc.API.Domain.Settings;

public class EnvironmentSettings
{
    public SmtpSettings? SmtpSettings { get; set; }
    public RdsAppSettings? RdsAppSettings { get; set; }
}