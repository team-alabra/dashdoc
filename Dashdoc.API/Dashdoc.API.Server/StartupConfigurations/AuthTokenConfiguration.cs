using Amazon.RDS.Model;
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
                options.Authority = configuration.GetSection("JwtBearer").GetValue<string>("Authority") ?? throw new Exception("Fake exception");
                options.MetadataAddress = configuration.GetSection("JwtBearer").GetValue<string>("MetadataAddress") ?? throw new InvalidOperationException();
                options.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuer = true,
                    ValidateIssuerSigningKey = true,
                    ValidateAudience = false,
                    ValidIssuer = configuration.GetSection("JwtBearer").GetValue<string>("Authority"),
                    RoleClaimType = configuration.GetSection("JwtBearer").GetValue<string>("AuthClaim")
                };
            });
    }
}