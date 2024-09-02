using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Dashdoc.API.Data.EntityConfigurations;

public class ProviderEntityConfiguration: IEntityTypeConfiguration<Provider>
{
    public void Configure(EntityTypeBuilder<Provider> builder)
    {
        builder.HasKey(e => e.Id);
        builder.Property(e => e.Email).IsRequired();
        builder.Property(e => e.GetType()).IsRequired();
    }
}