using System.Net;
using System.Net.Mail;
using Dashdoc.API.Data;
using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Infrastructure.AppServices;
using Dashdoc.API.Domain.Abstract.Services;
using Dashdoc.API.Infrastructure.VendorServices;
using Microsoft.AspNetCore.Identity.UI.Services;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class ServicesConfiguration
{
    public static void RegisterAppServices(this IServiceCollection services, IConfiguration config)
    {
        RegisterDataRepositories(services);
        RegisterLocalServices(services);
        RegisterEmailService(services, config);
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

    private static void RegisterEmailService(IServiceCollection services, IConfiguration config)
    {
        var smtpClient = new SmtpClient("smtp.gmail.com")
        {
            Port = 587,
            Credentials = new NetworkCredential("username", "password"),
            EnableSsl = true,
        };

        // We add a single in-memory instance of our SMTP client
        // this means every time we send an email, we don't create a new class, but reuse
        // the initialized one
        services.AddSingleton(smtpClient);
        
        // Register our email service for dependency injection
        services.AddTransient<IEmailSender, EmailService>();
    }
}