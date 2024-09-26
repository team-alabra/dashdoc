using System.IdentityModel.Tokens.Jwt;

namespace Dashdoc.API.Infrastructure.Utils;

public static class TokenUtil
{
    public static string? GetTokenClaim(string? authHeader, string claim)
    {
        if (string.IsNullOrEmpty(authHeader))
            return "placeholder-claim";

        var jwt = authHeader.Split("Bearer ")[1];
        var parsedToken = new JwtSecurityTokenHandler().ReadJwtToken(jwt);
        var claimValue = parsedToken.Claims.FirstOrDefault(c => c.Type == claim)?.Value;
        
        return claimValue;
    }
}