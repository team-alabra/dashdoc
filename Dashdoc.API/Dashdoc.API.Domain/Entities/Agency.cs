using System.ComponentModel.DataAnnotations.Schema;
using Dashdoc.API.Domain.Abstract;

namespace Dashdoc.API.Domain.Entities;

[Table("agency")]
public class Agency: BaseEntity
{
    [Column("id")]
    public long Id { get; set; }
    [Column("email")]
    public string? Email { get; set; }
    [Column("name")]
    public string? Name { get; set; }
    [Column("logo_url")]
    public string? LogoUrl { get; set; }
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
}