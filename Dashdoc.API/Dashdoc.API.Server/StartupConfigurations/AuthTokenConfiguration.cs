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
                options.MetadataAddress = configuration["JwtBearer:MetadataAddress"] ?? throw new InvalidOperationException();
                options.Authority = configuration["JwtBearer:Authority"] ?? throw new InvalidOperationException();
                options.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuer = true,
                    ValidateIssuerSigningKey = true,
                    ValidateAudience = false,
                    ValidIssuer = configuration["JwtBearer:Authority"],
                    RoleClaimType = "cognito:groups"
                };
            });
    }
}