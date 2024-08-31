using System.ComponentModel.DataAnnotations.Schema;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Enums;

namespace Dashdoc.API.Domain.Entities;

[Table("provider")]
public class Provider : BaseEntity
{
    // DB specific facing fields for Provider, including respective column names
    [Column("id")] 
    public long Id { get; init; } 
    [Column("agency_id")]
    public long AgencyId { get; init; }
    [Column("subscription_id")]
    public long SubscriptionId { get; init; }
    [Column("npi")]
    public long Npi { get; init; }
    [Column("first_name")]
    public string? FirstName { get; init; }
    [Column("last_name")]
    public string? LastName { get; init; }
    [Column("email")]
    public string? Email { get; init; }
    [Column("avatar_url")]
    public string? AvatarUrl { get; init; }
    [Column("gender")]
    public Gender Gender { get; init; }
    [Column("street_address")]
    public string? StreetAddress { get; init; }
    [Column("city")]
    public string? City { get; init; }
    [Column("state")]
    public State State { get; init; }
    [Column("zip_code")]
    public string? ZipCode { get; init; }
    [Column("home_number")]
    public string? HomeNumber { get; init; }
    [Column("work_number")]
    public string? WorkNumber { get; init; }
    [Column("mobile_number")]
    public string? MobileNumber { get; init; }
    [Column("birth_date")]
    public DateTime BirthDate { get; init; }
    [Column("discipline")]
    public Discipline Discipline { get; init; }
}