namespace Dashdoc.API.Server.StartupConfigurations;

public static class SecretsManagerConfiguration
{
    public static void AddSecretsManagerConfig(this IConfigurationBuilder builder)
    {
        var region = Environment.GetEnvironmentVariable("AWS_REGION");
        var secretName = Environment.GetEnvironmentVariable("DB_SECRET_NAME");
        
        var configSource = new AwsSecretsManagerConfigSource(region, secretName);
        builder.Add(configSource);
    }
}