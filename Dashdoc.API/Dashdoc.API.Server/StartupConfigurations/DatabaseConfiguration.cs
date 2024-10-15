using Amazon;
using Dashdoc.API.Data;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Server.StartupConfigurations;

// Alternate configuration using AWS Secrets Manager can be found here: https://aws.amazon.com/blogs/modernizing-with-aws/how-to-load-net-configuration-from-aws-secrets-manager/
// final implementation TBD
public static class DatabaseConfiguration
{
    public static void RegisterDashdocDatabase(this IServiceCollection services, IConfiguration configuration)
    {
        var dbConnectionString = configuration.GetSection("Database").GetValue<string>("DB_CONNECTION_STRING");
        
        services.AddDbContext<DashdocDbContext>(options =>
        {
            options.UseNpgsql(dbConnectionString);
        });
    }
}