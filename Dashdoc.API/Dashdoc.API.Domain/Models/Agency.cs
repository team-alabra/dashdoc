using Dashdoc.API.Domain.Abstract;

namespace Dashdoc.API.Domain.Models;

public class Agency: Party
{
    public string? Name { get; set; }
    public string? LogoUrl { get; set; }
    public string? PhoneNumber { get; set; }
    public string? SecondaryNumber { get; set; }
}