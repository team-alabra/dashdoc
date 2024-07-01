using System.Text.Json;
using Amazon;
using Amazon.SecretsManager;
using Amazon.SecretsManager.Model;
using Microsoft.Extensions.Configuration;

namespace Dashdoc.API.Infrastructure.VendorServices;

// Package and code reference: https://aws.amazon.com/blogs/modernizing-with-aws/how-to-load-net-configuration-from-aws-secrets-manager/
public class SecretsManagerConfigurationProvider: ConfigurationProvider
{
    private readonly string _region;
    private readonly string _secretName;
    
    public SecretsManagerConfigurationProvider(string region, string secretName)
    {
        _region = region;
        _secretName = secretName;
    }

    public override void Load()
    {
        var secret = GetSecret();

        Data = JsonSerializer.Deserialize<Dictionary<string, string>>(secret);
    }
    
    private string GetSecret()
    {
        var request = new GetSecretValueRequest
        {
            SecretId = _secretName,
        };

        using var client = new AmazonSecretsManagerClient(RegionEndpoint.GetBySystemName(_region));
        var response = client.GetSecretValueAsync(request).Result;

        string secretString;
        if (response.SecretString != null)
        {
            secretString = response.SecretString;
        }
        else
        {
            var memoryStream = response.SecretBinary;
            var reader = new StreamReader(memoryStream);
            secretString = 
                System.Text.Encoding.UTF8
                    .GetString(Convert.FromBase64String(reader.ReadToEnd()));
        }

        return secretString;
    }
}