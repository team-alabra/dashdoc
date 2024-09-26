using Amazon.CognitoIdentityProvider;
using Amazon.CognitoIdentityProvider.Model;
using Dashdoc.API.Infrastructure.Utils;
using Microsoft.Extensions.Primitives;

namespace Dashdoc.API.Server.Middleware;

public class UserVerificationMiddleware
{
    private readonly RequestDelegate _next;
    private readonly IAmazonCognitoIdentityProvider _cognitoClient;
    private readonly IConfiguration _config;
    
    public UserVerificationMiddleware(RequestDelegate next, IAmazonCognitoIdentityProvider cognitoClient, IConfiguration config)
    {
        _next = next;
        _cognitoClient = cognitoClient;
        _config = config;
    }

    public async Task InvokeAsync(HttpContext context)
    {
            var authHeader = context.Request.Headers.Authorization.FirstOrDefault();

            if (string.IsNullOrEmpty(authHeader))
            {
                #region Getting Username for local development
                    context.Request.Headers.TryGetValue("X-Username", out var headerValue);
                    context.Request.Headers.Append("userEmail", headerValue.ToString());
                #endregion
            }
            else
            {
                #region Fetch User From Cognito Using Their Id (Dev and above)

                var userRequest = new AdminGetUserRequest
                {
                    Username = TokenUtil.GetTokenClaim(authHeader, "username"),
                    UserPoolId = _config.GetSection("AWS").GetSection("cognito").GetValue<string>("UserPoolId")
                };

                var user = await _cognitoClient.AdminGetUserAsync(userRequest);

                var userName = user.UserAttributes.FirstOrDefault(x => x.Name == "email")?.Value;
                context.Request.Headers.Append("userEmail", userName);

                #endregion
            }

            await _next(context);
    }
}