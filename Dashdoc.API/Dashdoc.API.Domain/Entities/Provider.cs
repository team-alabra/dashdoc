using System.ComponentModel.DataAnnotations.Schema;

namespace Dashdoc.API.Domain.Entities;

[Table("provider")]
public class Provider
{
    // DB specific facing fields for Provider, including respective column names
    [Column("id")]
    public long Id { get; init; }
    [Column("name")]
    public string? Name { get; init; }
}