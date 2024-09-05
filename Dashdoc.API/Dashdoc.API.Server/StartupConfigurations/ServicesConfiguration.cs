using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Infrastructure.AppServices;
using Microsoft.AspNetCore.Authorization;

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
        services.AddScoped<IAgencyRepository, AgencyRepository>();
    }
    
    private static void RegisterLocalServices(IServiceCollection services)
    {
        services.AddScoped<IProviderService, ProviderService>();
    }
}