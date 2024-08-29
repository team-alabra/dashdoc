using System.ComponentModel.DataAnnotations.Schema;

namespace Dashdoc.API.Domain.Abstract;

public abstract class BaseEntity
{
    [Column("created_on")]
    public DateTime CreatedOn { get; set; }
    [Column("last_updated")]
    public DateTime? LastUpdated { get; set; }
}