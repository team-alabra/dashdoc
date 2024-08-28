using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Infrastructure.AppServices;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class ServicesConfiguration
{
    public static IServiceCollection RegisterAppServices(this IServiceCollection services)
    {
        RegisterDataRepositories(services);
        RegisterLocalServices(services);
        
        return services;
    }

    private static void RegisterDataRepositories(IServiceCollection services)
    {
        services.AddScoped<ITestRepository, TestRepository>();
        services.AddScoped<IAgencyRepository, AgencyRepository>();
    }
    
    private static void RegisterLocalServices(IServiceCollection services)
    {
        services.AddScoped<IProviderService, ProviderService>();
    }
}