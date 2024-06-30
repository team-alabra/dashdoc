using Amazon.RDS.Util;
using Amazon.SecretsManager.Extensions.Caching;
using Dashdoc.API.Infrastructure;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class DatabaseConfiguration
{
    public static IServiceCollection RegisterDashdocDatabase(this IServiceCollection services)
    {
        services.AddDbContext<DashdocDbContext>(options =>
        {
            var env = Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT");
            var dbUrl = Environment.GetEnvironmentVariable("DB_URI");
            var dbName = Environment.GetEnvironmentVariable("DB_NAME");
            var dbUser = Environment.GetEnvironmentVariable("DB_USERNAME");
            string connectionString;
            
            if (env is null or "Local")
            {
                connectionString = $"Server={dbUrl}; Database={dbName};";
            }
            else
            {
                var cache = new SecretsManagerCache();
                var dbSecretName = Environment.GetEnvironmentVariable("DB_SECRET_NAME");
                var mySecret = cache.GetSecretString(dbSecretName);
                connectionString = $"Server={dbUrl};User Id={dbUser};Password={pwd};Database={dbName};";
            }
            options.UseNpgsql(connectionString);
        });
        
        return services;
    }
}