using System.ComponentModel.DataAnnotations.Schema;

namespace Dashdoc.API.Domain.Entities;

[Table("test_table")]
public class TestEntity
{
    [Column("id")]
    public long Id { get; init; }
    [Column("name")]
    public string? Name { get; init; }
}