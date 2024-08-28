namespace Dashdoc.API.Domain.Models;

public class Agency
{
    public long Id { get; set; }
    public string? Email { get; set; }
    public string? Name { get; set; }
    public string? PhoneNumber { get; set; }
    public string? SecondaryNumber { get; set; }
    public string? StreetAddress { get; set; }
    public string? City { get; set; }
    public string? State { get; set; }
    public string? ZipCode { get; set; }
    public DateTime CreatedOn { get; set; } // Maybe can be used to show how long the agency has been with us
}