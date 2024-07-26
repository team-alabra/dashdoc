namespace Dashdoc.API.Domain.Settings;

public class RdsAppSettings
{
    public string? Username { get; init; }
    public string? Password { get; init; }
    public string? Host { get; init; }
    public int Port { get; init; }
    public string? DbName { get; init; }
}