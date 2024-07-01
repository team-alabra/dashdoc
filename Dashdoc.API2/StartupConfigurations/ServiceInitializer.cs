using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Infrastructure.Repositories;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class ServiceInitializer
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