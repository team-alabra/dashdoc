using Amazon.CognitoIdentityProvider;
using Amazon.CognitoIdentityProvider.Model;

namespace Dashdoc.API.Server.Middleware;

public class UserVerificationMiddleware
{
    private RequestDelegate _next;
    private IAmazonCognitoIdentityProvider _cognitoClient;
    
    public UserVerificationMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        var userRequest = new AdminGetUserRequest
        {
            Username = "alan",
            UserPoolId = "something"
        };
        await _cognitoClient.AdminGetUserAsync(userRequest);
        
        context.Request.Headers.Append("userId", "12345");
        
        await _next(context);
    }
}