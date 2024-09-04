using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Extensions.Options;

namespace Dashdoc.API.Server.StartupConfigurations;

public class JwtBearerConfiguration: IConfigureNamedOptions<JwtBearerOptions>
{
    private readonly IConfiguration _configuration;
    
    public JwtBearerConfiguration(IConfiguration configuration)
    {
        _configuration = configuration;
    }

    public void Configure(JwtBearerOptions options)
    {
        _configuration.GetSection("JwtBearer").Bind(options);
    }

    public void Configure(string? name, JwtBearerOptions options)
    {
        Configure(options);
    }
}