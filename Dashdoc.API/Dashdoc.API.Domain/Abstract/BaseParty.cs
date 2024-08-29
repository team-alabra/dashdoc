namespace Dashdoc.API.Domain.Abstract;

public abstract class BaseParty
{
    public long Id { get; set; }
    public string? Email { get; set; }
    public string? StreetAddress { get; set; }
    public string? City { get; set; }
    public string? State { get; set; }
    public string? ZipCode { get; set; }
    public DateTime CreatedOn { get; set; }
}