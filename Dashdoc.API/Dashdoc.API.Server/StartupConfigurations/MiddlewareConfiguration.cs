using Dashdoc.API.Server.Middleware;

namespace Dashdoc.API.Server.StartupConfigurations;

public static class MiddlewareConfiguration
{
    public static void UseDashdocMiddleware(this WebApplication app)
    {
        app.UseMiddleware<UserVerificationMiddleware>();
    }
}