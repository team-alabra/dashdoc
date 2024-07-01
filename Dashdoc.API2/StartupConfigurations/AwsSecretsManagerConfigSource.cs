using Dashdoc.API.Infrastructure.VendorServices;

namespace Dashdoc.API.Server.StartupConfigurations;

// Package and code reference: https://aws.amazon.com/blogs/modernizing-with-aws/how-to-load-net-configuration-from-aws-secrets-manager/
public class AwsSecretsManagerConfigSource: IConfigurationSource
{
    private readonly string _region;
    private readonly string _secretName;

    public AwsSecretsManagerConfigSource(string region, string secretName)
    {
        _region = region;
        _secretName = secretName;
    }

    public IConfigurationProvider Build(IConfigurationBuilder builder)
    {
        return new SecretsManagerConfigurationProvider(_region, _secretName);
    }
}