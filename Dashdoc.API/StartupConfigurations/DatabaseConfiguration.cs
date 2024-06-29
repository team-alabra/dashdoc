using Dashdoc.API.Infrastructure;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class DatabaseConfiguration
{
    public static IServiceCollection RegisterDashdocDatabase(this IServiceCollection services)
    {
        services.AddDbContext<DashdocDbContext>(options =>
        {
            var connectionString = Environment.GetEnvironmentVariable("DB_CONNECTION_STRING");
            options.UseNpgsql(connectionString);
        });

        return services;
    }
}