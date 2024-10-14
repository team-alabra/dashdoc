using Dashdoc.API.Domain.Settings;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class AuthTokenConfiguration
{
    public static void ConfigureDashdocAuthorization(this IServiceCollection services, IConfiguration configuration)
    {
        services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
            .AddJwtBearer(options =>
            {
                // Get JwtSettings from appsettings and then bind the values to the 'settings' object below.
                var settings = new JwtSettings();
                configuration.GetSection("JwtSettings").Bind(settings);
                
                options.Authority = settings.Authority;
                options.MetadataAddress = settings.MetadataAddress!;
                options.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuer = settings.ValidateIssuer,
                    ValidateIssuerSigningKey = settings.ValidateIssuerSigningKey,
                    ValidateAudience = settings.ValidateAudience,
                    ValidIssuer = settings.Authority,
                    RoleClaimType = settings.AuthClaim
                };
            });
    }
}