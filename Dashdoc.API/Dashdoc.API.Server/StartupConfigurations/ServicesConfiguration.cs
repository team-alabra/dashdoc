using Dashdoc.API.Data;
using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Infrastructure.AppServices;
using Dashdoc.API.Domain.Abstract.Services;
using Dashdoc.API.Domain.Settings;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class ServicesConfiguration
{
    public static void RegisterAppServices(this IServiceCollection services, IConfiguration config)
    {
        RegisterDataRepositories(services);
        RegisterLocalServices(services);
        AddEnvironmentSettings(services, config);
    }

    private static void AddEnvironmentSettings(IServiceCollection services, IConfiguration config)
    {
        services.Configure<EnvironmentSettings>(config);
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