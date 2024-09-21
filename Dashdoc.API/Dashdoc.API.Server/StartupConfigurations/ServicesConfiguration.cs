using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Infrastructure.AppServices;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class ServicesConfiguration
{
    public static void RegisterAppServices(this IServiceCollection services)
    {
        RegisterDataRepositories(services);
        RegisterLocalServices(services);
    }

    private static void RegisterDataRepositories(IServiceCollection services)
    {
        services.AddScoped<ITestRepository, TestRepository>();

        services.AddScoped<IProviderRepository, ProviderRepository>();

        services.AddScoped<IAgencyRepository, AgencyRepository>();

    }
    
    private static void RegisterLocalServices(IServiceCollection services)
    {
        services.AddScoped<IProviderService, ProviderService>();
    }
    
  
}