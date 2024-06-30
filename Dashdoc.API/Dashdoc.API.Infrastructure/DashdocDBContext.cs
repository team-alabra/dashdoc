using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Infrastructure;

public class DashdocDbContext: DbContext
{
    public DashdocDbContext() { }
    public DashdocDbContext(DbContextOptions<DashdocDbContext> options): base(options) { }
    
    public virtual DbSet<Provider> Provider { get; init; }
}