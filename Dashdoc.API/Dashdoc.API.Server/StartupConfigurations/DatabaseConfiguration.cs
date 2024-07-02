using Amazon;
using Dashdoc.API.Data;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class DatabaseConfiguration
{
    public static IServiceCollection RegisterDashdocDatabase(this IServiceCollection services)
    {
        var env = Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT");
        var dbUrl = Environment.GetEnvironmentVariable("DB_URI");
        var region = Environment.GetEnvironmentVariable("AWS_REGION");
        var dbName = Environment.GetEnvironmentVariable("DB_NAME");
        var dbUsername = Environment.GetEnvironmentVariable("RDS_USERNAME");

        string connectionString;
        
        #region Build Connection String
        
        // Local connection only requires the localhost address and the dBName
        // remote connection uses AWS Secrets Manager to fetch credentials 
        if (env is null or "Local")
        {
            connectionString = $"Server={dbUrl}; Database={dbName};";
        }
        else
        {
            var pwd = Amazon.RDS.Util.RDSAuthTokenGenerator.GenerateAuthToken(RegionEndpoint.GetBySystemName(region), dbUrl, 5432, dbUsername);
            
            connectionString = $"Server={dbUrl};User Id={dbUsername};Password={pwd};Database={dbName};";
        }

        #endregion
        
        services.AddDbContext<DashdocDbContext>(options =>
        {
            options.UseNpgsql(connectionString);
        });

        return services;
    }
}