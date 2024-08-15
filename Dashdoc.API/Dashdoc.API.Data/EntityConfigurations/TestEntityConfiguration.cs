using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Dashdoc.API.Data.EntityConfigurations;

public class TestEntityConfiguration: IEntityTypeConfiguration<Provider>
{
    public void Configure(EntityTypeBuilder<Provider> builder)
    {
        builder.HasKey(e => e.Id);
        builder.Property(e => e.Name).IsRequired();
    }
}