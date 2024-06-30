using Amazon.SecretsManager.Extensions.Caching;
using Dashdoc.API.Domain.Settings;
using Dashdoc.API.Infrastructure;
using Microsoft.EntityFrameworkCore;
using JsonSerializer = System.Text.Json.JsonSerializer;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class DatabaseConfiguration
{
    public static IServiceCollection RegisterDashdocDatabase(this IServiceCollection services)
    {
        var env = Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT");
        var dbUrl = Environment.GetEnvironmentVariable("DB_URI");
        var dbName = Environment.GetEnvironmentVariable("DB_NAME");

        Console.WriteLine(env);
        Console.WriteLine(dbUrl);
        Console.WriteLine(dbName);
        
        string connectionString;
        
        services.AddDbContext<DashdocDbContext>(options =>
        {
            // Local connection only requires the localhost address and the dBName
            // remote connection uses AWS Secrets Manager to fetch credentials 
            if (env is null or "Local")
            {
                connectionString = $"Server={dbUrl}; Database={dbName};";
            }
            else
            {
                var cache = new SecretsManagerCache();
                var dbSecretName = Environment.GetEnvironmentVariable("DB_SECRET_NAME");
                var mySecret = cache.GetSecretString(dbSecretName).GetAwaiter().GetResult();
                var rds = JsonSerializer.Deserialize<RdsAppSettings>(mySecret);
                
                connectionString = $"Server={rds?.Host};User Id={rds?.Username};Password={rds?.Password};Database={rds?.DbName};";
            }
            
            options.UseNpgsql(connectionString);
        });
        
        return services;
    }
}