using System.ComponentModel.DataAnnotations.Schema;

namespace Dashdoc.API.Domain.Entities;

[Table("agency")]
public class Agency
{
    [Column("id")]
    public long Id { get; set; }
    public string? Email { get; set; }
    public string? Name { get; set; }
    public string? PhoneNumber { get; set; }
    public string? SecondaryNumber { get; set; }
    public string? StreetAddress { get; set; }
    public string? City { get; set; }
    public string? State { get; set; }
    public string? ZipCode { get; set; }
    public DateTime CreatedOn { get; set; }
    public DateTime LastUpdated { get; set; }
}