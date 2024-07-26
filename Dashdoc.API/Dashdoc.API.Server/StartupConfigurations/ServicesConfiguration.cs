using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Domain.Abstract;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class ServicesConfiguration
{
    public static IServiceCollection RegisterAppServices(this IServiceCollection services)
    {
        RegisterDataRepositories(services);

        return services;
    }

    private static void RegisterDataRepositories(IServiceCollection services)
    {
        services.AddScoped<ITestRepository, TestRepository>();
    }
}