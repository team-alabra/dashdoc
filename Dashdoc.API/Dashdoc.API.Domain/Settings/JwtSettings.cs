namespace Dashdoc.API.Domain.Settings;

public class JwtSettings
{
    public string? Authority { get; init; }
    public string? MetadataAddress { get; init; }
    public bool ValidateIssuer { get; init; }
    public bool ValidateIssuerSigningKey { get; init; }
    public bool ValidateAudience { get; init; }
    public string? AuthClaim { get; init; }
}