using System.ComponentModel.DataAnnotations.Schema;

namespace Dashdoc.API.Domain.Entities;

[Table("agency")]
public class Agency
{
    [Column("id")]
    public long Id { get; set; }
    [Column("email")]
    public string? Email { get; set; }
    [Column("name")]
    public string? Name { get; set; }
    [Column("phone_number")]
    public string? PhoneNumber { get; set; }
    [Column("secondary_number")]
    public string? SecondaryNumber { get; set; }
    [Column("street_address")]
    public string? StreetAddress { get; set; }
    [Column("city")]
    public string? City { get; set; }
    [Column("state")]
    public string? State { get; set; }
    [Column("zip_code")]
    public string? ZipCode { get; set; }
    [Column("created_on")]
    public DateTime CreatedOn { get; set; }
    [Column("last_updated")]
    public DateTime LastUpdated { get; set; }
}