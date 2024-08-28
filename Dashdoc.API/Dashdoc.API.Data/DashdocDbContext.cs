using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Data;

public class DashdocDbContext: DbContext
{
    public DashdocDbContext() { }
    public DashdocDbContext(DbContextOptions<DashdocDbContext> options): base(options) { }
    
    public virtual DbSet<Provider> TestEntity { get; init; }
    public virtual DbSet<Agency> Agency { get; init; }
}